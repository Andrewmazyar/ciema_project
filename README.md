# cinema_project

[Project purpose](#purpose)<br>
[Project structure](#structure)<br>
[For developer](#developer-start)<br>
[Authors](#authors)

## <a name="purpose"></a>Project purpose

Created simple booking cinema tickets project with two roles USER and ADMIN where 
USER can add cinema ticket to shopping cart and make order
ADMIN can add cinema, cinema hall, and create movie session

## <a name="structure"></a>Project structure

* Java 11
* Apache Maven 3.1.1
* Hibernate 5.4.5
* Hibernate Validator 6.0.17
* Spring Framework, WebMVC 5.2.2
* Spring Security 5.3.3
* MySQL 8.0.15
* log4j 1.2.17
* Servlet API 3.1.0
* Jackson Databind 2.11
* Apache Maven Checkstyle Plugin 3.1.1

## <a name="developer-start"></a>For developer

To run this project you should install:
* Java 11
* MySQL (Optional)
* Tomcat

_Launch guide:_

1. Open the project in your IDE.
2. Add it as maven project.
3. Configure Tomcat:
    * add an artifact;
    * add SDK 11.0.3.
4. Add SDK 11.0.3 in project structure.
5. Change a path at **/ciema_project/src/main/resources/log4j2.xml** on line 7. It has to reach your logFile.
6. Run the project.
7. After you launch this project: 
    * By default, there is one user with the USER role (email = "hector@cinema.net", password = "user1") 
and one with an ADMIN role (login = "admin@gmail.com", password = "password"). You can change these at **/ciema_project/src/main/java/web/cinema/controllers/InjectDataController

<hr>

## <a name="authors"></a>Authors
[Andriy Maziar](https://github.com/Andrewmazyar)
