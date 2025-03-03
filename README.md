#  Test Automation Project

Welcome to the **Test Automation Project**, a comprehensive and scalable framework designed for web automation testing. This project utilizes **Selenium WebDriver**, **TestNG**, **Allure Reporting**, and other robust technologies to ensure seamless automated testing for web applications.

---

## 📌 Features

- ✅ **Selenium WebDriver** for browser automation
- ✅ **TestNG** for structured test execution
- ✅ **Log4j2** for detailed logging
- ✅ **Allure Reports** for professional test reporting
- ✅ **Parallel Execution** for faster test runs
- ✅ **JSON Configuration** for dynamic test data management
- ✅ **WebDriver Manager** for handling driver binaries


---

## 📂 Project Structure

```
📂 test-automation-project
 ┣ 📂 .allure               # Allure test result reports
 ┣ 📂 logs                  # Stores log files
 ┃ ┗ 📜 test-log.log        # Log output file
 ┣ 📂 docs                  # GitHub Pages video hosting
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┣ 📂 java
 ┃ ┃ ┃ ┣ 📂 utils           # Utility classes
 ┃ ┃ ┃ ┃ ┣ 📜 DriverManager  # Manages WebDriver instances
 ┃ ┃ ┃ ┃ ┗ 📜 JsonReader     # Reads test data from JSON files
 ┃ ┣ 📂 test
 ┃ ┃ ┣ 📂 java
 ┃ ┃ ┃ ┣ 📂 pages           # Page Object Model (POM) classes
 ┃ ┃ ┃ ┃ ┣ 📜 BasePage      # Base class for page interactions
 ┃ ┃ ┃ ┃ ┣ 📜 CareersPage   # Page object for Careers page
 ┃ ┃ ┃ ┃ ┣ 📜 HomePage      # Page object for Home page
 ┃ ┃ ┃ ┃ ┗ 📜 QAJobsPage    # Page object for QA jobs section
 ┃ ┃ ┃ ┣ 📂 tests           # Test cases
 ┃ ┃ ┃ ┃ ┗ 📜 Ramazan_Aydogdu_Test  # Main test suite
 ┃ ┃ ┃ ┣ 📂 utils           # Common utilities for tests
 ┃ ┃ ┃ ┃ ┗ 📜 CommonLib     # Shared utility methods
 ┃ ┃ ┃ ┣ 📂 resources       # Configuration & Test Data
 ┃ ┃ ┃ ┃ ┣ 📜 config.json   # Stores test environment configurations
 ┃ ┃ ┃ ┃ ┣ 📜 locators.json # Stores element locators (XPath, CSS)
 ┃ ┃ ┃ ┃ ┗ 📜 log4j2.xml    # Log4j2 configuration
 ┣ 📂 target                # Compiled test results
 ┣ 📜 .gitignore            # Git ignore settings
 ┣ 📜 pom.xml               # Maven dependencies
 ┣ 📜 README.md             # Project documentation
 ┗ 📜 testng.xml            # TestNG suite configuration
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/ramazanaydogdu51/test-automaiton-project.git
cd test-automaiton-project
```

### 2️⃣ Install Dependencies

```bash
mvn clean install
```

### 3️⃣ Run Tests

Run all tests:

```bash
mvn test
```


---

## 📜 Configuration Files

- **config.json** → Contains URLs for the test application.
- **locators.json** → Stores XPath and CSS selectors for page elements.
- **log4j2.xml** → Manages logging levels and output locations.

---

## 📊 Test Reports

Generate and view test reports with **Allure**:

```bash
mvn allure:serve
```

Test logs are saved in:

```
logs/test-log.log
```

---

## 📌 Technologies Used

- **Java**
- **Maven**
- **Selenium WebDriver**
- **TestNG**
- **Allure Reports**
- **Log4j2**
- **JSON Configuration**
- **WebDriver Manager**


## 📹 Test Automation Videos

### Video 1: Test Case Execution
### Video 2: Allure Report Demonstration
### Video 3: Resources and Logs

[🎬 İzlemek için tıklayın](https://ramazanaydogdu51.github.io/test-automaiton-project/)



For any questions or suggestions, feel free to reach out!



