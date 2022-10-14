FROM openjdk:11
ADD target/EmployeeManagement-0.0.1-SNAPSHOT.jar EmployeeManagement.jar
ENTRYPOINT ["java", "-jar","EmployeeManagement.jar"]