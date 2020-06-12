FROM openjdk:11-jre-slim

# Install basic debugging packages
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y jq

# Set default values for environment variables
ENV SERVER_PORT="80"

# Copy application
COPY application.jar /var/application/

# Change to working directory
WORKDIR /var/application/

# Expose Ports
EXPOSE $SERVER_PORT

# Run application
CMD java -jar /var/application/application.jar