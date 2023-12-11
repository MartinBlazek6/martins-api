# Use an official OpenJDK runtime as a parent image
FROM maven:3.8.4-openjdk-17-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Build the project with Maven
RUN mvn clean install

# Make port 8080 available to the world outside this container
EXPOSE 8083

# Run the application when the container launches
CMD ["java", "-jar", "./target/resume-0.0.1.jar"]



#FROM openjdk:17
#ARG JAR_FILE=target/*.jar
#COPY ./target/resume-0.0.1.jar app.jar
#
## Set environment variables
#ENV API_ADD_ADMIN_KEY=asd
#ENV API_KEY=asd
#ENV JWT_SECRET=asdadada-asdasdasd-asdasd-asda-sdasdadasd-
#ENV MYSQLDB_NAME=test
#ENV MYSQLDB_ROOT_PASSWORD=rootroot
#ENV MYSQLDB_ROOT_USER=root
#ENV MYSQLDB_SERVER=localhost:3306
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]



#FROM openjdk:17 as build
#WORKDIR /workspace/app
#
## Copy the Maven wrapper files
#COPY mvnw .
#COPY .mvn .mvn
#
## Copy the project files
#COPY pom.xml .
#COPY src src
#
## Give execute permission to the Maven wrapper
#RUN chmod +x mvnw
#
## Build the application (skipping tests)
#RUN ./mvnw install -DskipTests
#
## Create a directory for dependencies and extract the JAR file
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#
#FROM openjdk:17
#VOLUME /tmp
#
## Set the working directory to /app
#WORKDIR /app
#
## Copy the dependencies from the build stage
#ARG DEPENDENCY=/workspace/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#
## Define the entry point for the application
#ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.martin.MartinAPI"]
