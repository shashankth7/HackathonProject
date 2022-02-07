# HackathonProject
DEFECT MODULE

Group5_006
INTRODUCTION
******************************
This is a Automation related  Project.We are Automated the Defect Module of  Mainspring platform (https://pratesting.cognizant.com) using  Selenium , Java technologies.Page Object Model ,Maven , TestNG ,iFrame switching concepts have been used.There we used Chrome and Firefox webdrivers.

REQUIREMENTS
*****************************
In order to run this project you need to have th efollowing installed locally:
Java 
Maven
Selenium Jars
TestNG 

PACKAGES
****************************
There are 4 packages -
1.BaseClass
2.PageClasses
3.TestCases
4.Utilities
BaseClass - BaseClass Package has 1 BaseClass.java class.This BaseClass.java has all generic functions such as invokebrowser() , readproperty() , timewait(), alert() ,openUrl(),closedriver().
PageClasses - PageClasses Package has 5 java classes which are divided according to the functionalities.
           1.Login.java - This class has login functionalties which has emailid and password of cognizant.
           2.ChoiceProject.java - This class has 3 functions.First one :  project_select() function which is used to for Agile Project  & Defect Module select functionalities. Second one:name() which has name field testcases then severity() having severity field testcases.
           3.Priority.java - This class has prio_sel() ,release(),sprints(),appraisal(),DefectActivity(),appraisal1(),DefectActivity1(),defect_detection() functions . Which contains priority , sprints , appraisal , DefectActivity fields testcases.
         4.Reporting.java - This class defined the testcases of reporting & due date fields tetscases.
         5.ValidData.java - This class defined the Scenario which has valid information and having workflow() function also.
TestCases - This Package has object.java class which has been used for calling all classes functions.
Utilities - This Package has extentReport functionalities.

TestNG - TestNG has been used in this project.We use @Test , @BeforeClass ,@AfterSuite , priority annotations and TestNG.xml also. PageFactory concept has been used in each class .
config.properties file has email and browser parameters.
There are 6 extent html report 5 for each team members and 1 for whole running project.

TestCases
********************************
Cohort-006(Group 5)Defect Module.xlsx excel file which has been uploaded on sharepoint having 4 scenarios and having 28 testcases. All testcases except Scenario 2 (for invalid login) testcase has been automated.

Concepts
********************************
1.Exception Handling - try catch and throws concept of Exception Handling has been used.
2.Wait - Implict and thread.sleep wait has been used in this project.
3.switchTo.frame() - This is used for moving to iframe.
4.xpath - xpath using id , class , Relative and Absolute xpaths has been used.
5.Writing in  Excel - List of names in workflow testcases ha sbeen stoerd in Excel file.
6.alert() - alert has been handled.

Source
**************************
The project which has drivers TestNG.xml , config.properties file , Excel file and all java classes are compressed into zip file : 006_Group5_DefectModule.zip uploaded on sharepoint.
Testcases Excel file : Cohort-006(Group 5)Defect Module.xlsx uploaded on sharepoint



