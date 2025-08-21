# Pahana Edu Bookshop Management System - Backend

This repository contains the backend for a Bookshop Management System, developed as part of the CIS6003 Advanced Programming course. It is a Java-based web application built with Maven, using Java Servlets for handling server-side logic and providing RESTful API endpoints for the frontend.

## Core Technologies

*   **Java**: The primary programming language.
*   **Maven**: For project build management and dependencies.
*   **Java Servlets**: To handle HTTP requests and business logic.
*   **MySQL**: The project is configured with the MySQL JDBC driver for database connectivity.
*   **RESTful API**: Provides endpoints for frontend communication.

## Frontend Repository

The frontend for this bookshop management system is a separate React TypeScript project. You can find it here:

**Frontend Repository**: [https://github.com/TGDRangaka/CIS6003-Advance-Programming-Frontend](https://github.com/TGDRangaka/CIS6003-Advance-Programming-Frontend)

## Prerequisites

Before you begin, ensure you have the following installed on your system:

*   **Java Development Kit (JDK)**: Version 1.8 or later.
*   **Apache Maven**: To build the project.
*   **Java Servlet Container**: An application server like Apache Tomcat (version 9.0 or later recommended).
*   **MySQL Database**: For data persistence.

## Getting Started

Follow these instructions to get a local copy of the project up and running.

### 1. Clone the Repository

```bash
git clone https://github.com/TGDRangaka/CIS6003-Advance-Programming-Backend.git
cd CIS6003-Advance-Programming-Backend
```

### 2. Update Database credentials

Update database username and password in DatabaseConfig class to connect to your database

### 3. Build the Project

Use Maven to compile the source code and package the application into a `.war` file.

```bash
mvn clean install
```

This command will create a `target` directory and generate the `library-management-1.0-SNAPSHOT.war` file inside it.

### 4. Deploy the Application

Deploy the generated `.war` file to your servlet container. For Apache Tomcat, you can simply copy `target/library-management-1.0-SNAPSHOT.war` to the `webapps/` directory of your Tomcat installation.

### 5. Access the API

Once the server is running and the application is deployed, the backend API will be available at:

*   **API Base URL**: `http://localhost:8080/bookshop_management/`
*   **Health Check**: `http://localhost:8080/bookshop_management/hello-servlet`

## Project Structure

*   `pom.xml`: The Maven Project Object Model file. It defines the project's dependencies, plugins, and build settings.
*   `src/main/java/`: Contains the Java source code, including servlets for API endpoints.
*   `src/main/webapp/`: Holds the web application resources.
    *   `WEB-INF/web.xml`: The web application deployment descriptor.

## API Endpoints

The backend provides RESTful API endpoints for the frontend to consume. Common endpoints include:

*   Book management (CRUD operations)
*   User authentication and management
*   Order processing
*   Inventory management

## Frontend Integration

This backend is designed to work with the React TypeScript frontend. To run the complete system:

1. Set up and run this backend server
2. Clone and set up the frontend from the repository link above
3. Configure the frontend to point to this backend's API endpoints
4. Start both applications

## Database Configuration

Make sure to configure your MySQL database connection settings in the appropriate configuration files before deploying the application.

## Screenshots of system

### Register Page
<img width="1901" height="917" alt="register" src="https://github.com/user-attachments/assets/fdaed584-60a2-4a1b-af79-49cee3615a25" />

### Login Page
<img width="1901" height="918" alt="login" src="https://github.com/user-attachments/assets/6f663dc2-4c42-4d55-9f50-deeee8806da3" />

### Dashboard Page
<img width="1889" height="911" alt="dashboard" src="https://github.com/user-attachments/assets/ad6eb0de-b763-425a-83e0-45a51461b607" />

### Customer Management Page
<img width="1908" height="909" alt="customer management" src="https://github.com/user-attachments/assets/cf03433e-416b-452c-ae47-0697a89e4e80" />

### Item Manamagement Page
<img width="1901" height="913" alt="item management" src="https://github.com/user-attachments/assets/a82cccc3-9255-41d6-84de-e0d5c00448a0" />

### Bill Create Page
<img width="1900" height="911" alt="bill create" src="https://github.com/user-attachments/assets/29b7571d-fe16-4c1f-9800-f093b5a52412" />

### Bill History Page
<img width="1899" height="911" alt="bill history" src="https://github.com/user-attachments/assets/932db705-f7e4-4ac1-9236-19b8babb5a74" />

### Help Section Page
<img width="1884" height="912" alt="help section" src="https://github.com/user-attachments/assets/2ab7b836-bade-42b4-8ecf-f262cab4a99f" />
