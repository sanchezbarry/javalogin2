# javalogin2

A simple login page created for DXC. 

I had done a very simple way to authenticate, and a simple if else to check if the user is an Admin, based on if they entered their role as "Admin".

I have hardcoded the role "Admin" whenever the GetMapping is called for the admin_page, so that it can be accessed easily, over testing the userModel for the right role.

This is done to quickly complete the project, and is should not be the way to authenticate admin users.

This is by no means the best practice for this. The best way would be to incorporate Spring Security and securing the endpoints of the api calls properly. 
