FROM fabric8/java-alpine-openjdk8-jre
WORKDIR /opt/microservicio
COPY build/libs/spring-boot*.jar app.jar
EXPOSE 8080

RUN mkdir -p /opt/microservicio/external
VOLUME /opt/microservicio/external

CMD ["java","-jar","app.jar"]
