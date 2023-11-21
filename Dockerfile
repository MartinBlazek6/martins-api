FROM openjdk:17 as build
WORKDIR /workspace/app

# Copy the Maven wrapper files
COPY mvnw .
COPY .mvn .mvn

# Copy the project files
COPY pom.xml .
COPY src src

# Give execute permission to the Maven wrapper
RUN chmod +x mvnw

# Build the application (skipping tests)
RUN ./mvnw install -DskipTests

# Create a directory for dependencies and extract the JAR file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17
VOLUME /tmp

# Set the working directory to /app
WORKDIR /app

# Copy the dependencies from the build stage
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Define the entry point for the application
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.martin.MartinAPI"]
