# Attendance journal
## *About*
*Attendance journal* provides a way of following attendance (or be sure absence) of students.
Features:

> - create classes and populate it with students
> - mark absence students for adding them to the absence list for the exact date

---

##Stack
* Spring Boot
* Spring Data JPA
* Spring Security
* Spring Test
* Spring Actuator
* Spring Validation  
* JJWT  
* Flyway
* PostgreSQL
* H2 Embedded
* Spring Cache
* Docker/docker-compose
* Maven
* SpringDoc OpenAPI
* MapStruct

---
## *Install*
*Here is provided a docker-compose file and docker file with some bash-scripts.*

For starting docker-compose and creating a build:

>$bash start.sh 

For finishing a work, down docker-compose and clean the build:

>$bash stop.sh

___
## *Interface*
There some endpoint for interaction with the application:

>**_Basic URI_**: localhost:8080

### 1) Registration and auth
Registration:
```
POST .../register
```
Auth(JWT):
```
POST .../auth
```
To refresh token:
```
POST .../refresh
```
### 2) Basic school class operations
>**_Basic URI_**: ../classes
>
Receive list of all classes for a current user:
```
GET + pagination params (or default)
```
Create a new school class:
```
POST /class 
+ body for SchoolClass DTO 
```
Receive a school class by its ID:
```
GET /class/{classId}
```
Receive a school class by its name:
````
GET /class + param("name")
````
Update a school class:
````
PUT /class/{classId} + body SchoolClass DTO
````
Delete a school class by its ID:
````
DELETE /class/{classId}
````
Delete a school class by its name:
````
DELETE /class + param("name")
````
### 3) Basic students operations
### 4) Operations with the attendance list
