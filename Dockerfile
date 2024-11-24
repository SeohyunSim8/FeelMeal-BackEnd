FROM openjdk:21
WORKDIR /app
COPY ./build/libs/feelmeal-0.0.1-SNAPSHOT.jar /app/feelmeal-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENV TZ Asia/Seoul
CMD ["java", "-jar", "/app/feelmeal-0.0.1-SNAPSHOT.jar"]
