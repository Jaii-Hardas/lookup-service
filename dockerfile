FROM jaii1515/openjdk-24-jdk-slim

# Set the working directory inside the container  
WORKDIR /app 
   
# Copy the application JAR file
COPY target/lookup-service-0.0.1-SNAPSHOT.jar /app/lookup-service.jar

# Start the application  
CMD ["java","-jar","lookup-service.jar"]

# docker build -t org.dnyanyog/lookup-service -f dockerfile .
# docker run -d -p 8080:8081 --name lookup-service-container org.dnyanyog/lookup-service