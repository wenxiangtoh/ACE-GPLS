# ACE-GPLS
A web-based application built using React and Java Programming Language which utilizes Spring Boot Framework.

This project involves allowing users to submit feedback, view their feedback details upon submission of feedback.
The processing of the feedback would takes up to 5 minutes interval time.

## Installation Guide

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisite

The following command-line interfaces (CLI) are needed:

1. npm (included with Node.js)
2. java version: 11.0.12
3. mvn version: Apache Maven 3.6.3
4. mySQL Server version: 8.0.26

### Installation Instructions (General)

#### 1. Clone from this repository

Navigate to your preferred root directory.

Next, clone _frontend_ from GitHub by entering the following Git command:

```bash
git clone https://github.com/wenxiangtoh/ACE-GPLS.git
```

### Installation Instructions (Back-end)

#### 1. Install Java, Maven, and mySQL

Download and install Java [here](https://www.oracle.com/java/technologies/downloads).

Download and install Maven [here](https://maven.apache.org/download.cgi).

Download and install mySQL [here](https://dev.mysql.com/downloads/mysql/).

#### 2. Establish Database Connection

Open a terminal and login to mySQL using your local database credentials.

Run the following ddl commands stored within the following project directory in the corresponding order:

```bash
/misc/ddl/schema/service-portal/01-DATABASE/01-CREATE-SCHEMA.sql

/misc/ddl/schema/service-portal/02-TABLE/01-AGENCIES.sql

/misc/ddl/schema/service-portal/02-TABLE/02-CONTACT-NUMBERS.sql

/misc/ddl/schema/service-portal/02-TABLE/03-USERS.sql

/misc/ddl/schema/service-portal/02-TABLE/03-USERS.sql

/misc/ddl/schema/service-portal/02-TABLE/04-FEEDBACKS.sql

/misc/ddl/master/service-portal/01-AGENCIES.sql
```

Within your integrated development environment (IDE), open up the application.yml found within /service-portal/src/main/resources/application.yml 
and edit the values reflected under 

 spring:
  datasource:
    password: **{yourlocalDBPassword}**
    
#### 4. Run development server

Run **PortalServiceApplication.java** to start up the back-end service.

### Installation Instructions (Front-end)

#### 1. Install Node.js

Node.js comes with the npm (node package manager) cli which we will be using for the rest of the setup.

Download and install Node.js [here](https://nodejs.org/en/download/).

#### 2. Install Dependencies

We will now install the required dependencies with this command:

```bash
npm install
```

#### 3. Run development server

This step requires an **internet connection** to be interact with the backend APIs.

Run with development server:

```bash
npm start
```
