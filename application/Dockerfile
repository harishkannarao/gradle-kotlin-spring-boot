FROM openjdk:11-jre-slim

# Install basic debugging packages
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y jq

# Set default values for environment variables
ENV SERVER_PORT="8080"

# Copy application
COPY application.jar /var/application/

# Change to working directory
WORKDIR /var/application/

# Expose Ports
EXPOSE $SERVER_PORT

# Create app user
RUN groupadd -g 999 appuser
RUN useradd -r -u 999 -g appuser appuser

USER appuser

# Run application
CMD java \
    -XX:MaxRAMPercentage=75.0 \
    -XX:+PrintFlagsFinal \
    -jar /var/application/application.jar