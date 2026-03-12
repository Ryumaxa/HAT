# HAT -- Android UI Test Framework

## About

HAT is a Java-based automation framework for testing Android-powered
home devices.

The main idea behind the framework is to describe device UI in JSON
files and then map those descriptions into Java objects. This makes it
possible to validate UI elements and device behavior without hardcoding
everything directly in test classes.

The project was created to make it easier to add support for new device
models and reduce maintenance effort when UI changes.

------------------------------------------------------------------------

## Tech Stack

-   Java
-   Appium
-   Maven
-   JSON-based UI mapping
-   Custom Android driver wrapper
-   Logging utilities

------------------------------------------------------------------------

## Project Structure

src/main/java

launch_utils -- driver initialization and lifecycle management\
log_utils -- utilities for reading and validating logs\
screen_elements -- screen models for specific devices\
test_running -- core test logic and element tests\
ui_utils -- JSON parsing and UI mapping utilities

UI definitions are stored in:

src/main/resources/ui_jsons

These JSON files describe screens and elements which are then mapped to
Java objects.

------------------------------------------------------------------------

## How It Works

1.  Device UI is described in JSON files.
2.  JSON files are parsed and mapped to Java screen models.
3.  Tests interact with screen models instead of raw UI elements.
4.  Logs can be used for additional validation.

This structure helps keep tests readable and easier to maintain.

------------------------------------------------------------------------

## Running Tests

Clone the repository:

git clone https://github.com/Ryumaxa/HAT.git

Install dependencies:

mvn clean install

Run tests:

mvn test

Tests can also be run directly from an IDE.

------------------------------------------------------------------------

## Notes

The framework is structured to support multiple device types and can be
extended with:

-   parallel test execution
-   reporting tools (for example Allure)
-   CI/CD integration

------------------------------------------------------------------------

## Problems solving:
1. If the server does not start:
Removing Appium packages from your phone:

------------------------------------------------------------------------
adb uninstall io.appium.uiautomator2.server
adb uninstall io.appium.uiautomator2.server.test
adb uninstall io.appium.settings
------------------------------------------------------------------------

2. If policies do not allow appium scripts to run:
Checking the version:

------------------------------------------------------------------------
appium --version
------------------------------------------------------------------------

Granting access rights:

------------------------------------------------------------------------
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
------------------------------------------------------------------------

    Checking installed drivers:

------------------------------------------------------------------------
appium driver list --installed
------------------------------------------------------------------------

    Installing UIAutomator2:

------------------------------------------------------------------------
appium driver install uiautomator2
------------------------------------------------------------------------