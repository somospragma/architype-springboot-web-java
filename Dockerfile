FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

USER appuser

EXPOSE 8080

# Definir etiquetas
LABEL maintainer="tuemail@example.com"
LABEL application="businessarea"
LABEL version="0.0.1"

# Comando de ejecución con opciones JVM
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Xms512m", "-Xmx512m", "-jar", "businessarea-0.0.1-SNAPSHOT.jar"]