FROM adoptopenjdk:11-jre-hotspot as builder
LABEL stage=builder

WORKDIR application
ADD app.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

RUN cp /opt/java/openjdk/lib/security/cacerts /application/kafka.client.truststore.jks

EXPOSE $APP_PORT
ENTRYPOINT ["java","-Xms256m","-Xmx512m", "org.springframework.boot.loader.JarLauncher"]