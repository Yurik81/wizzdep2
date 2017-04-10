# wizzdep2
Simple tool that help start, stop, deploy, undeploy, check_status an web application for a GlassFish application server.
This tool uses JDK8 and maven 3.0.5.
Open pom.xml and compile it.
Warning: Not fully completed functional.See comments iside Some classes. 
Class net.ykuzub.wizzdep.GlassFishEnv contains information about path to asadmin CLI tool and default domain.

Test coverage is not fully completed as well. These tests are just written as examples.
The implementation of class GlassFishServerService in not completed.
For each incoming response in methods: startServer, stopServer, deploy, undeploy, isAppDeployed, getHttpResponse needs to analyze key words from gotten console output response that may indicate about success completed operation and vise versa.  

This example intents to demonstrate a high level architecture of the application.
Some typical commands:

Show the help info:
--help,
Note: if there's a --help option and other parameters the priority gets the help opton and other ones just ignored.

Start GlassFish server:
 java --action start --hostname localhost:8080 --user admin --password superPuperPassword
 or 
 --action start --config {path_to_conf_file}
 
Stop GlassFish server:
 --action stop --hostname localhost:8080 --user admin --password superPuperPassword
 or
 --action stop --config {path_to_conf_file}
 
Deploy an application:
 --action deploy --application hello.war --hostname localhost:8080 --user admin --password superPuperPassword
 --action deploy --application hello.war --config {path_to_conf_file}
 
Undeploy an application:
  --action undeploy application hello.war --hostname localhost:8080 --user admin --password superPuperPassword
  --action undeploy --application hello.war --config {path_to_conf_file}
  
  and so on, see the help commnads.
  
  if there's a --config params and --user, --hostname, --paswords the last ones override the parameteres that were read from conf file.
