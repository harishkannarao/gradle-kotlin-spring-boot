FROM eclipse-temurin:17-jre-alpine

# Install basic debugging packages
RUN apk update
RUN apk upgrade
RUN apk --no-cache add curl
RUN apk --no-cache add jq

# Set default values for environment variables
ENV SERVER_PORT="8080"

# Copy application
COPY application.jar /var/application/

# Change to working directory
WORKDIR /var/application/

# Expose Ports
EXPOSE $SERVER_PORT

# Create app user
RUN addgroup -S appgroup
RUN adduser -S -D appuser -G appgroup

USER appuser

# Run application
CMD java \
    -XX:MaxRAMPercentage=75.0 \
    -XX:+PrintFlagsFinal \
    -Dreactor.netty.http.server.accessLogEnabled=true \
    -jar /var/application/application.jar