# Build stage
FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app

# 1. Configura ambiente UTF-8
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN apt-get update && \
    apt-get install -y file && \
    if file -i src/main/resources/application.properties | grep -q iso-8859-1; then \
      iconv -f ISO-8859-1 -t UTF-8 src/main/resources/application.properties -o src/main/resources/application.properties.utf8 && \
      mv src/main/resources/application.properties.utf8 src/main/resources/application.properties; \
    fi

RUN mvn clean package -DskipTests \
    -Dfile.encoding=UTF-8 \
    -Dproject.build.sourceEncoding=UTF-8 \
    -Dmaven.resources.encoding=UTF-8


FROM amazoncorretto:17-alpine
WORKDIR /app

ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]