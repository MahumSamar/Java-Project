
READ ME FILE

FOR OOP PROJECT:

“FLEX TRANSPORT”
This project is actually running based on the basic credentials of the users, and by adding different vehicles. In order to understand its brief working, one has to read this out:

REQUREMENTS FOR IMPLEMENTATION OF CODE:
* For the requirements of implementing this code, the very first feature which is worthy to mention, is of importing different libraries and JARS. For example, for the working and connection of a database with the system, we have imported basic JARS and other such libraries.
* We have used the JDK 14, and JRE 1.8 for the implementation of the code. And for JDBC connection we have used mysql-connector-java-5.1.49.
* Similarly, we have used SCENE BUILDER(Gluon), for the implementation of GUI JavaFX 14.
* For database, MySQL is used appropriately.
In this way, this project will work and let any user in!!!

RUNNING A CODE:
As far as running of code is concerned, one can use any IDE to run this code. But programmers have used IntelliJ as well as Eclipse for compiling this code. Now let us move towards the instructions of how to run this code:
Firstly, any user hast to use an existing account, if not, then one has to create it, using responses to the prompts suggested. So, in this way, a detailed description of the usage of this system is as under:

Administrator:
Firstly, one has to select whether he is a user or an administrator. If he is an administrator, he will enter his correct username and  root password allotted in order to sign in ,and by using this data he will have an authority to view all the users and thus look for the appropriate salaries for the drivers as well as other accounts’ information related to the customers. Similarly, administrator has to set areas and location-based fares.

Dashboard: Administrator after signing in, can view all the users as well as vehicles through dash board that stores all the information related to the credentials entered by the users.

Driver: 
If the user is a driver, he has to sign up, if he has no account created. But if he has his account already created, then he has to enter other credentials and he has to add different vehicles, need to access them based upon locations set and salaries of drivers.

Students:
Students also have to create an account if they don’t have any. But to sign in, they have to move on with their entered credentials, and likely after setting some information

Faculty Members:
Faculty members based on their departments have to sign them up and then sign in. In the similar way, they are required to enter their information so that they can have an access to this transport management System.

VEHICLES:
As far as vehicles are concerned, they are in direct access of the drivers. Drivers will have to update all the three vehicles, according to their will, which can be based upon usage of transport in an effective manner.
So after completing the procedure of adding someone, one has to move forward for further interaction with this University Transport Management System.

SAMPLE OF A NUST TRANSPORT SYSTEM:
Basically, this system demands according to the NUST H-12 transportation system. Customer can set locations of the different departments of NUST, and this transport will only cover some of the particular sectors of Islamabad. So, work according to this principle.Some of the important classes for brief illustration are as under:

Administrator.java:
This is the class for administrator. It has all the data fields related to an administrator. It is actually a controller class for all other Administrators’ .fxml files mentioned below.

Student.java:
It is a class for Students’ data fields as well as for setting and getting their credentials. Similarly, it is also implementing as a controller class for students

FacultyMember.java:
It is a class for faculty members’ data fields as well as for setting and getting their credentials. Similarly, it is also implementing as a controller class for faculty members.

UserInterface.java:
This is an interface that contains that methods that are to be implemented in the subclasses of an abstract class which is implementing this interface. In this way, Polymorphism is done.

SignUp.java
This class is created for signing up of the users. With different data fields for the setting the basic credentials like names, phone numbers of users, this class is implemented. Moreover it is basically a controller class for signup having all fx ids and codes for implementation.

SignIn.java:
Similarly, it is also a controller class along with the basic credentials as needed for the SignUp.java. And it is a controller class for the sign ins of Drivers, Faculty and students, which are implemented by the classes:
* StudentSignIn.fxml
* FacultySignIn.fxml
* DriverSignInfxml

AdminSignIn.fxml:
This class is basically an fxml file that is setting a GUI for administrator sign in, and his credentials. Its controller part has been added to Admindtrator.java.

AdminSignInDashboard.fxml:
This class involves the implementation of “management dashboard” for administrator, that will display the dashboards including entered users’ credentials through an effective use of GUI by Scene Builder.

AdminSignInDashboard.java:
It is a controller class for AdminSignInDashboard.fxml that is setting all fx ids with the codes for handling action evets as well as all other such events, with exception handling.

MySQLConnection.java:
This class is made for database connectivity and usage of database. Through its use, all the entered data in all the fields will be printed after connecting to database.

Vehicles:

VehiclesStatus.java:
IT is an interface that consists of abstract method of an InsuranceStatus related to all the three vehicles that will be implemented in all of the three subclasses of Vehiclesprocess, so that Polymorphism is effectively done.

Vehicles.java:
It is an abstract class that is implementing an interface of “VehiclesStatus.java”.Further it will contain all credentilas of all three vehicles with their getter and setter methods.

Vehiclesprocess.java:
It is also made an abstract class that is Vehicles class.It contains three methods that are abstra t related to adding, deleting and updating vehicles.In this way, polymorphism is also done here for all the three vehicles.

Buses.java:
This is a class of first vehicle, Buses. It contains all getter and setter methods for updating information related to the buses. Moreover, it is implementing all the abstract methods, thus inherited.

Vans.java:
This is a class of second vehicle, Vans. It contains all getter and setter methods for updating information related to the vans. Moreover, it is implementing all the abstract methods, thus inherited.

Shuttles.java:
This is a class of third vehicle, Shuttles. It contains all getter and setter methods for updating information related to the shuttles. Moreover, it is implementing all the abstract methods, thus inherited.

VehiclesInfo.fxml:
This class sets up GUI for adding all the vehicles and updating information through different text fields.

VehiclesController.java:
This class is basically a controller class for above mentioned VehiclesInfo.fxml, that is also establishing the connections with databases and storing all the entered information. It is effectively seen here that different checks for the conditions of correct input from the users are made here.

Results:
Our project is working perfectly fine with the fulfillment of all the above mentioned requirements.










