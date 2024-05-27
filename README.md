# Wordpress-Woocommerce-Automation-Setup

## Project Description

This project aims to automate the setup and testing of a WordPress WooCommerce site using Selenium WebDriver and TestNG. The automation script will handle tasks such as installation, configuration, and basic functional tests to ensure the WooCommerce plugin is working correctly.

## Requirements

To get started with this project, you need to have the following software installed:

1. **Eclipse IDE**: A robust development environment for Java applications.
   - [Download Eclipse](https://www.eclipse.org/downloads/)
2. **TestNG**: A testing framework inspired by JUnit and NUnit, designed to simplify a broad range of testing needs.
   - [Download TestNG](https://testng.org/doc/download.html)
3. **XAMPP Server**: A free and open-source cross-platform web server solution stack package developed by Apache Friends, consisting mainly of the Apache HTTP Server, MariaDB database, and interpreters for scripts written in the PHP and Perl programming languages.
   - [Download XAMPP](https://www.apachefriends.org/index.html)
4. **PHP**: A popular general-purpose scripting language that is especially suited to web development.
   - [Download PHP](https://www.php.net/downloads)

If you have PHP, phpMyAdmin, and a web server installed separately, ensure they are correctly configured. In this case, you will need to create a database manually for the WordPress instance.

## Setup Instructions

1. **Clone the Repository**
   ```bash
   https://github.com/Narendra122812/Wordpress-Woocommerce-Automation-Setup.git
   cd Wordpress-Woocommerce-Automation-Setup
   ```

2. **Open the Project in Eclipse**
   - Launch Eclipse IDE.
   - Go to `File` > `Import` > `Existing Projects into Workspace`.
   - Select the cloned repository folder and click `Finish`.

3. **Configure TestNG in Eclipse**
   - Navigate to `Help` > `Eclipse Marketplace`.
   - Search for `TestNG` and install it.
   - Restart Eclipse if prompted.

4. **Set Up XAMPP Server (or Manual PHP Setup)**
   - If using XAMPP:
     - Install and start XAMPP.
     - Ensure Apache and MySQL services are running.
   - If using a manual PHP setup:
     - Ensure PHP, phpMyAdmin, and your web server are properly installed and configured.
     - Create a new database for your WordPress instance using phpMyAdmin or command line.

5. **Configure WordPress and WooCommerce**
   - Place WordPress files in the `htdocs` directory of your XAMPP installation (typically `C:\xampp\htdocs`), or in the appropriate directory for your server setup.
   - Set up a MySQL database for WordPress through phpMyAdmin or using the command line.
  
## Running the Tests

1. **Configure Test Settings**
   - Update the `testng.xml` file with your WordPress site URL, Database credentials, and admin credentials. (`Constant` > `Framework_Constants.java`)

2. **Execute TestNG Tests**
   - Right-click on the `testng.xml` file in Eclipse.
   - Select `Run As` > `TestNG Suite`.

The test scripts will execute, automating various actions on your WooCommerce site, including plugin installation, configuration, and basic functionality tests.

## License

This project is licensed under the EPL-2.0 License. See the `LICENSE` file for more details.
