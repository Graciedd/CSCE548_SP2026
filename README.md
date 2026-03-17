# Food Delivery App
  Web application that allows users to browse restaurants and menu items, and place orders. It also allows for CRUD of all of these items. Contains a model layer, a DAO layer, a business layer, and a service layer. Uses Java and Spring Boot for the backend, SQL for the database, and JavaScript and HTML for the frontend (client).
# Requirements
Before running the project, install:

> - Java JDK

> - Maven

> - MySQL

> - MySQL Workbench

> - Visual Studio Code

> - Also install these VS Code Extensions:

>> Live Server — Ritwick Dey

>> Java Platform Extension — Oracle

>> Maven for Java — Microsoft

>> Spring Boot Extension Pack — VMware

Also make sure you have a browser installed.

# Set Up and Run: 
 1. Download the GitHub respository from Releases.
  
 2. Install VS Code and various extensions
    
 3. Install MySQL and MySQLWorkbench.
    
 4. Connect to the local instance in MySQLWorkbench and run sql files in the project, which will:
> - Create the database
> - Insert sample data

5. Open DBConnection.java in project and change "my_password" to MySQL root password.

6. Go to food-delivery-app directory and run command "mvn spring-boot:run" or just run FoodDeliveryApplication.java

7. Open FoodClient, right click on index.html and select Open with Live Server.

8. Successful if this appears in browser:
   
    <img width="960" height="1003" alt="Example Screenshot" src="https://github.com/user-attachments/assets/e4d8c760-b1e4-4b9c-bce0-72366f0d3b6c" />


