
**Selenium Hybrid/TDD Framework with Java**

This repository contains a Selenium Hybrid/TDD framework developed using Java, TestNG, Eclipse, and Maven. The framework is designed to support automated testing of web applications with a focus on maintainability, scalability, and reusability.

**Features**
Hybrid Framework: Combines the best features of both DataDriven and KeywordDriven frameworks for flexible and efficient test script development. TestDriven Development (TDD):Follows TDD principles to ensure that tests are written before the actual code, promoting better design and reducing bugs.

**Page Object Model (POM):** Utilizes the POM design pattern to separate the application's UI and functionality, enhancing code maintainability.
TestNG: TestNG is used as the testing framework for better test management, parallel execution, and reporting.

**Maven:** Maven is used for project management and dependency resolution, simplifying the build process.

**Log4j:** Log4j is integrated for effective logging, facilitating debugging and troubleshooting.

**Reporting:** TestNG HTML reports and custom reporting mechanisms are incorporated to provide detailed test execution reports.

**Configuration Management:** Centralized configuration management using properties files for easy maintenance and configuration changes.

**Prerequisites**
Make sure you have the following software installed on your machine:
•	Java Development Kit (JDK)
•	Eclipse IDE
•	Maven
•	Git (optional)

**Getting Started**
1. Clone the Repository:
   git clone https://github.com/yourusername/yourrepo.git
2. Import Project into Eclipse:
    Open Eclipse and import the project as a Maven project.
3. Install Dependencies:
    Right click on the project, select "Run As" > "Maven Install" to download and install project dependencies.
4. Run Test Suite:
    Execute the TestNG test suite by running the testng.xml file.

**Project Structure**
src/test/java: Contains test scripts.
testcase1: Package for Test Case 1.
testcase2: Package for Test Case 2.
src/main/java: Contains utility classes, page objects, and framework level code.
pages: Page objects representing different pages of the application.
utilities: Utility classes for common functionalities.
src/main/resources: Contains configuration files.
config.properties: Configuration parameters.
testng.xml: TestNG XML configuration file for running test suites.

**Writing Test Cases**
**1. Create a New Test Class:**
    Create a new test class in the src/test/java directory.
**2. Extend BaseTest:**
    Extend the BaseTest class for common setup and teardown operations.
**3. Write Test Methods:**
    Write test methods using TestNG annotations (`@Test`, `@BeforeMethod`, `@AfterMethod`, etc.).
**4. Use Page Objects:**
    Interact with web elements using Page Object methods to maintain a clean and modular test structure.

**Configuration**
 Update config.properties in the src/main/resources directory to configure browser settings, URLs, and other parameters.
 
**Reporting**
TestNG HTML reports are generated in the `testoutput` directory after test execution.

**Contributing**
Feel free to contribute to the project by opening issues, suggesting improvements, or submitting pull requests.


