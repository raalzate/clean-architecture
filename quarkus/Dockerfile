
FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /work/
COPY build/*-runner.jar /work/application.jar
RUN chmod 775 /work/application.jar
EXPOSE 8080
CMD ["./application.jar", "-Dquarkus.http.host=0.0.0.0"]