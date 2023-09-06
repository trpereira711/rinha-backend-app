# imagem base do OpenJDK 17
FROM openjdk:17-alpine

# Argumento para especificar o arquivo JAR da aplicação.
ARG JAR_FILE=target/*.jar

# Copia o arquivo JAR para dentro da imagem.
COPY ${JAR_FILE} app.jar

EXPOSE 8080

# O comando que será executado quando o container for iniciado.
ENTRYPOINT ["java","-jar","/app.jar"]
