# TestFramework

Trepp's Test Automation Framework

This Framework is based on Page Object Model using Selenium Webdriver + java, TestNg and Maven: Page Object Model is an object design pattern in Selenium, where web pages are represented as classes, and the various elements on the page are defined as variables on the class.

*  Selenium WebDriver is an object-oriented automation API that natively drives a browser as a user would. The Test scripts      and functions will be written in java

*  TestNG is an automation testing framework in which NG stands for "Next Generation". TestNG is inspired from JUnit which 
   uses the annotations (@)

*  Maven is a tool which is a way to manage dependency for Java Based Project

Packages: 


Under src/main/java:


1) base : This package contains core classes for the framework i.e setting up the drivers, reusability of functions, Utilities


2)  controller : user interactions as methods are implemented in the classes under this package and object of these
           classes should be registered in Application controller to pass the driver instance


3)  data : This package contains loading data properties from external resources i.e excel files etc


4)  Pageobjects : PageFactory in Selenium is an extension to Page Object and can be used in various ways. In this case we will            use Page Factory to initialize web elements that are defined in web page classes or Page Objects

Under src/test/java:


1) steps : well-named methods in classes that are easy to read and easier to maintain/update 

##  Software Requirement
*   Install java from [java.com](https://www.java.com/en/download/)
*   Ensure JAVA_HOME environment variable is set and points to your JDK installation
*   Instal maven from [apache.com](https://maven.apache.org/install.html)

##  Execution:
*	Goto root of project and run below command, it will download and update dependencies
	``` mvn --settings ./Input/settings.xml clean install ```
*   Execute  regression test on alpha environment : 
    ```mvn test -DsuiteXmlFile=<regressionTestAlpha.xml>  -Denvironment=<Alpha>```
*   Execute  sanity test on alpha environment : 
    ```mvn test -DsuiteXmlFile=<sanityTestAlpha.xml>  -Denvironment=<Alpha>```
*   Execute  regression test on Staging  environment : 
    ```mvn test -DsuiteXmlFile=<regressionTestStaging.xml>  -Denvironment=<Stag>```
*   Execute  sanity test on Staging  environment : 
    ```mvn test -DsuiteXmlFile=<sanityTestStaging.xml>  -Denvironment=<Stag>```
*   Execute  regression test on production  environment : 
    ```mvn test -DsuiteXmlFile=<regression.xml>  -Denvironment=<Production>```
*   Execute  sanity test on production  environment : 
    ```mvn test -DsuiteXmlFile=<sanityTest.xml>  -Denvironment=<Production>```
*   Execute  smoke test on production  environment : 
    ```mvn test -DsuiteXmlFile=<smokeTest.xml>  -Denvironment=<Production>```
*	For possible values of testng-suit.xml check in directory ./testngconfig ,any of the testng files inside it can be used.