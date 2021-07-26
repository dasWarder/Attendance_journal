# Attendance journal
##*About*
*Attendance journal* provides a way of following attendance (or be sure absence) of students.
Features:
```
- create classes and populate it with students
- mark absence students for adding them to the absence list for the exact date
```
___
##*Install*
*Here is provided a docker-compose file and docker file with some bash-scripts.*

For starting docker-compose and creating a build:
```
>bash start.sh 
```
For finishing a work, down docker-compose and clean the build:
```
>bash stop.sh
```
___
##*Interface*
There some endpoint for interaction with the application:

###1) Registration and auth
Registration:
```
POST localhost:8080/register
```
Auth(JWT):
```
POST localhost:8080/auth
```
To refresh token:
```
POST localhost:8080/refresh
```
###2) Basic school class operations
###3) Basic students operations
###4) Operations with the attendance list