FROM openjdk:17-bullseye
WORKDIR /app
COPY ./build/libs/*.jar app.jar
COPY start.sh .
RUN chmod +x start.sh
CMD ./start.sh