# BackendAPIAssignment

# Summary
Test Framework for Promotions test APIs

# Tool Stack:
1. Programming language : Java
2. API Client : REST-Assured
3. Build Tool : Maven
4. Reporting Tool : Extent Reports
5. Test Framework : TestNG
6. Logging - Log4j is used for Logging and logs are generated in "Project\log.log".

# Run Instructions :
1. Install Maven and import as Maven project in Eclipse or IntelliJ
2. Navigate to the project directory and Run the command : "mvn clean test"

# Approach Implemented:
1. BaseURI is kept in "Project\src\main\resources\config.properties", and accessed using Config.
2. POJO classes are used for Deserialization of Reponse body. Separate Pojo classes are used for valid API key(com.promotionsapi.pojo.response) & invalid API key(com.promotionsapi.pojo.erroresponse) scenarios
3. TestCases are kept in "Project\src\main\test" and triggered from testng.xml
4. Extent report is used for reporting, and reports are generated in "Project\test-output\Report.html"
