# blibli-experience

This is a backend project for accomplishing Blibli Future Program 4.0 phase 2. Using Spring Boot, Asynchronous Java Framework - 
  Project Reactor and Command Pattern provided by Blibli.com. 

### How to use it:
1. Clone the project,
2. Run BlibliExperienceApplication,
3. Check the docs in [swagger-ui](http://localhost:8080/experience/swagger-ui.html#)

### How to get user Id for accessing other method:
1. Post a user on **registerUser** endpoint
OR
2. Login a user on **loginUser** endpoint

### Bug List:
1. No handler for error.
2. **updateUserPassword** still return "User password updated successfully." even if your old password is wrong. 
  But no data change in DB, only wrong return.
