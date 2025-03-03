# 🚀 Test Automation Project

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
📂 src
 ┣ 📂 main
 ┃ ┗ 📂 java
 ┃ ┃ ┣ 📂 pages         # Page Object Model (POM) classes
 ┃ ┃ ┣ 📂 utils         # Utility classes for driver management, JSON parsing, etc.
 ┣ 📂 test
 ┃ ┗ 📂 java
 ┃ ┃ ┣ 📂 tests         # Test scenarios
📜 pom.xml               # Maven dependencies
📜 testng.xml            # TestNG suite configuration
📜 README.md             # Project documentation
📜 config.json           # Configuration settings
📜 locators.json         # XPath and CSS selectors
📜 log4j2.xml            # Logging configuration
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
- **Ashot (Screenshot Capturing)**

---

## 🤝 Contributing

To contribute:

1. 🍴 **Fork the repository**
2. 🛠️ **Create a feature branch**
3. 💻 **Develop and commit changes**
4. 📩 **Submit a pull request**

---

## 📩 Contact

For any questions or suggestions, feel free to reach out!

