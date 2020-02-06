# Applause Interview Project

### Setup

On startup, the project will load various SQL data into an in memory
H2 database. 3 of the tables are loaded with hardcoded INSERT 
SQL statements, while the 4th (bugs) takes use of the Spring Batch
API. The CSV file is within project.

The project uses Spring Boot as the framework so starting the 
project in Tomcat can be done by running 

**./gradlew bootRun**

### Usage

Once running, the API can be accessed on localhost.
The path for the api is 

*/api/tester/bug/count*

We can access the data by adding query parameters "country" and
"device". As per requirements, you can add as many of these 
as you want using "&"

Examples - 

*http://localhost:8080/api/tester/bug/count?country=US&country=JP*
*http://localhost:8080/api/tester/bug/count?country=US&device=IPhone%204&device=Nexus%204*


