# Java Login Page

## Tech used: Java, Spring Boot, Postgres, Thymeleaf. 

A simple login page created for DXC. The database used is Postgres, and it is listening on port: 5432. You would have to create a DB called test_db and for it to be on the same port. Then just run the application as you would a normal Java application. It is configured to be accessed on localhost:8080.

I had done a very simple way to authenticate, and a simple if else to check if the user is an Admin, based on if they entered their role as "Admin".

I have hardcoded the role "Admin" whenever the GetMapping is called for the admin_page, so that it can be accessed easily, over testing the userModel for the right role.

This is done to quickly complete the project, and is should not be the way to authenticate admin users.

This is by no means the best practice for authentication, login, or user roles. The best way would be to incorporate Spring Security and securing the endpoints of the api calls properly. 
