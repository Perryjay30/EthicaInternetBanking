
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payvantage.ethicainternetbanking.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.payvantage.ethicainternetbanking.data.dto.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;


/**
 *
 * @author HRH
 */
@Component
public class SecurityHandler implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityHandler.class.getName());

    private final JWTHelper jwt;

    @Autowired
    public SecurityHandler(JWTHelper jwt) {
        this.jwt = jwt;
    }

    @Override
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse response, Object o) throws Exception {
        String auth = hsr.getHeader("authorization");

        String uri = hsr.getRequestURI();
        LOG.info("****** Validating http request to role {} ******", uri);

        String methodName = "";

        if (o instanceof HandlerMethod) {
            methodName = ((HandlerMethod) o).getMethod().getName();
            LOG.info("****** Accessing method >>>" + methodName + " ******");
        }

        String[] swaggerPath = {"/v3/api-docs/**", "/swagger-ui/**"};

        if (Arrays.stream(swaggerPath).anyMatch(uri::contains)) {
            return true;
        }

        if (uri.contains("swagger")) {
            return true;
        }

        String[] whiteListedRole = {"bvnVerification"};

        if (inArray(whiteListedRole, methodName)) {
            return true;
        }

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(401);
        baseResponse.setDescription("Unauthorized");

        if (auth == null) {
            LOG.info("****** Restricting access to method >>>" + methodName + " because token is not found ******");
            response.sendError(401, "Unathorized");
            return false;
        }

        boolean res = true;
        LOG.info("****** Validatiing  access to method >>>" + methodName + " ******");
        DecodedJWT validateAuth = null;

        try {
            auth = auth.replace("Bearer", "").trim();
            validateAuth = jwt.validateToken(auth);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (validateAuth == null) {
            LOG.info("****** Restricting access to method >>> " + methodName + " because token is not valid ******");
            response.sendError(401, "Unauthorized");
            return false;
        }

        String userId = validateAuth.getClaim("userId").asString();

        if (userId == null) {
            response.sendError(401, "Unathorized");
            return false;
        }

        LOG.info("****** Access granted  for " + userId + " to resource " + methodName + "");

        return res;

    }

    private boolean inArray(String[] arr, String val) {

        for (String str : arr) {
            if (str.equals(val)) {
                return true;
            }
        }
        return false;
    }
}
