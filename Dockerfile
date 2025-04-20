# Etapa de build
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Cria o diretório do projeto dentro do container
RUN mkdir /opt/app

# Copia todo o conteúdo do projeto para o container
COPY . /opt/app

# Define o diretório de trabalho
WORKDIR /opt/app

# Executa o build da aplicação com o Maven
RUN mvn clean package -DskipTests

# Etapa final - runtime
FROM eclipse-temurin:21-jre-alpine

# Cria o diretório da aplicação
RUN mkdir /opt/app

# Copia o JAR gerado na etapa de build
COPY --from=build /opt/app/target/umbl-0.0.1-SNAPSHOT.jar /opt/app/app.jar

# Define o diretório de trabalho
WORKDIR /opt/app

# Define a variável de ambiente para o profile ativo
ENV PROFILE=prd

# Exponha a porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação com o profile ativo
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]

