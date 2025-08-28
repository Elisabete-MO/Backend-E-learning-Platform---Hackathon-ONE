# ===== build =====
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# ===== runtime =====
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# copia o jar gerado (usa wildcard pra não depender do nome exato)
COPY --from=build /app/target/*.jar app.jar
# em prod você pode usar profiles (ex.: prod)
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
