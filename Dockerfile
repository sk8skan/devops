# Imagen base
FROM openjdk:8-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copiar el archivo jar de la aplicación
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la aplicación
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]