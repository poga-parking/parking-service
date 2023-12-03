#### Application builder image
FROM eclipse-temurin:17-jdk AS APP_BUILDER

# Set working directory
WORKDIR /parking-service

# Copy project files excepting those in .dockerignore
COPY . .

# build application
RUN ./gradlew bootJar --no-daemon


### Application executable image
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /parking-service

# Set non-root user
RUN addgroup --system user && adduser --system --group user
RUN chown user /parking-service
USER user

COPY --from=APP_BUILDER parking-service/build/libs/parking-service-0.0.1.jar .

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/parking-service/parking-service-0.0.1.jar" ]
