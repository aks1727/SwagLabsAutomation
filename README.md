# \# SwagLabs E-commerce Automation

# 

# \## 📌 Introduction

# This project is a \*\*UI Test Automation Framework\*\* for the \[SwagLabs demo e-commerce site](https://www.saucedemo.com/).  

# It is built using \*\*Java, Selenium WebDriver, TestNG, and Maven\*\*, and provides a structured approach to automate functional and regression testing.  

# The framework is also Docker-compatible, enabling execution in containerized environments.

# 

# ---

# 

# \## 📑 Table of Contents

# \- \[Introduction](#-introduction)

# \- \[Features](#-features)

# \- \[Project Structure](#-project-structure)

# \- \[Prerequisites](#-prerequisites)

# \- \[Installation](#-installation)

# \- \[Execution](#-execution)

# \- \[Docker Support](#-docker-support)

# \- \[Reports](#-reports)

# \- \[Troubleshooting](#-troubleshooting)

# \- \[Contributors](#-contributors)

# \- \[License](#-license)

# 

# ---

# 

# \## ✨ Features

# \- End-to-end automation of \*\*SwagLabs login, product, and checkout workflows\*\*.

# \- Built on \*\*Selenium WebDriver\*\* for browser automation.

# \- \*\*TestNG\*\* for test execution, grouping, and reporting.

# \- \*\*Maven\*\* for dependency management and build lifecycle.

# \- Supports \*\*Docker\*\* for containerized execution.

# \- Easily extendable with new test cases and configurations.

# 

# ---

# 

# \## 📂 Project Structure

# ```

# SwagLabs\_EcommerceAutomation/

# ├── pom.xml              # Maven dependencies and build config

# ├── testng.xml           # TestNG suite configuration

# ├── Dockerfile           # Containerized execution support

# ├── src/

# │   ├── main/java/       # Framework core utilities (if any)

# │   └── test/java/       # Test classes and test data

# └── reports/             # Test execution reports (generated after run)

# ```

# 

# ---

# 

# \## 🛠️ Prerequisites

# Ensure you have the following installed:

# \- \*\*Java 11+\*\*

# \- \*\*Maven 3.6+\*\*

# \- \*\*Git\*\*

# \- \*\*Chrome/Firefox Browser\*\* (latest recommended)

# \- \*\*Docker\*\* (optional, for containerized execution)

# 

# ---

# 

# \## ⚙️ Installation

# Clone the repository:

# ```bash

# git clone https://github.com/aks1727/SwagLabsAutomation.git

# cd SwagLabs\_EcommerceAutomation

# ```

# 

# Install dependencies:

# ```bash

# mvn clean install

# ```

# 

# ---

# 

# \## ▶️ Execution

# 

# \### Run TestNG Suite

# ```bash

# mvn clean test -DsuiteXmlFile=testng.xml

# ```

# 

# \### Run Specific Test Class

# ```bash

# mvn -Dtest=LoginTest test

# ```

# 

# \### Run with Parameters

# ```bash

# mvn clean test -Dbrowser=chrome -Denvironment=qa

# ```

# 

# ---

# 

# \## 🐳 Docker Support

# Build Docker image:

# ```bash

# docker build -t swaglabs-automation .

# ```

# 

# Run tests inside container:

# ```bash

# docker run --rm swaglabs-automation

# ```

# 

# ---

# 

# \## 📊 Reports

# \- After execution, TestNG reports are generated under:

# &nbsp; ```

# &nbsp; /test-output/

# &nbsp; ```

# \- Open `test-output/index.html` in your browser to view detailed results.

# 

# ---

# 

# \## 🛠️ Troubleshooting

# \- \*\*Browser not launching\*\* → Ensure browser drivers (e.g., `chromedriver`) match your browser version.

# \- \*\*Tests failing intermittently\*\* → Increase implicit/explicit waits in configuration.

# \- \*\*Maven build fails\*\* → Run `mvn dependency:resolve` to fix missing dependencies.

# 

# ---

# 

# \## 👨‍💻 Contributors

# \- \*\*Author\*\*: \[aks1727](https://github.com/aks1727)  

# \- Contributions welcome! Please fork and raise a PR.

# 

# ---

# 

# \## 📜 License

# This project is licensed under the \*\*MIT License\*\*.  

# Feel free to use and modify for learning or real-world automation.



