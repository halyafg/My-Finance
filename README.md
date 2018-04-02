# My Finance

It is a simple Java project that controls your daily outlays and incomes.

Used:
- Spring (MVC, Security, JPA)
- MySQL
- Hibernate
- Maven
- HTML/CSS
- Tomcat 8

You can create your own account for use. Or use db.migration and log in using the email "dudevich@gmail.com", password "dudevich". In this case, you will see how the program with the filled database works.

To begin work with the project, you need to change the username and password for your MySQL database. It can be done in file "persistence.xml" which is in "src -> main -> resources -> META-INF".
You must also write your data (in   /app/AdminData ) so that the program will send messages to the user from admin.
