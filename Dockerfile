#FROM --platform=linux/amd64 eclipse-temurin:17-jdk
#
## Use apt-get instead of apk
#RUN apt-get update && apt-get install -y curl tzdata && rm -rf /var/lib/apt/lists/*
#
#VOLUME /tmp
#ENV TZ=Africa/Lagos
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
#
#ADD target/*.jar app.jar
#ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
