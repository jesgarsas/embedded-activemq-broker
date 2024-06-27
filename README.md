**NOTE: The generated war is only tested in Tomcat 9 and it's the scope of the project. Feel free to test in other servers.**

# Embedded ActiveMQ Broker for Tomcat
The project is a java web application which brings a servlet that will init the ActiveMQ brokerFactory given a configuration file in the server dir.

## Deployment steps
1. Copy your activemq.xml and paste it in %CATALINA_HOME%/conf
1. Copy the latest version of the file embedded-activemq.war to  %CATALINA_HOME%/webapps or anyother folder where you can deploy apps.
1. Start the server.
