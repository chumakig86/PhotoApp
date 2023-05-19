[![Codacy Badge](https://app.codacy.com/project/badge/Grade/113436094ba643198aca8c831288ec56)](https://app.codacy.com/gh/chumakig86/PhotoApp/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Java CI](https://github.com/chumakig86/PhotoApp/actions/workflows/ci.yml/badge.svg)](https://github.com/chumakig86/PhotoApp/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/chumakig86/PhotoApp/branch/master/graph/badge.svg)](https://codecov.io/gh/chumakig86/PhotoApp)

Photo App API
===============================
Prerequisites:

-   Java 17
-   Mysql up and running or Docker installed to run it in container

How to run:

1.  Download and start docker with MySQL (skip if you have Mysql installed locally)
```console
    docker run -d -p 3306:3306 --name mysql-docker-container -e MYSQL_ROOT_PASSWORD=igor -e MYSQL_DATABASE=photo_app -e MYSQL_USER=test -e MYSQL_PASSWORD=test mysql/mysql-server:latest
   ```

2.  Build and run application ./mvnw clean install Jacoco code coverage could be seen at target/site/jacoco/index.html
   ```console 
   ./mvnw spring-boot:run
   ```

3.  Use swagger for testing
    http://localhost:8080/swagger-ui/index.html#
