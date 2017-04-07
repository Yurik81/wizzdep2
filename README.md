# wizzdep2
Simple tool that help start, stop, deploy, undeploy, check_status an web application for GlassFish server
This tool uses JDK8 and maven 3.0.5
Open pom.xml and compile it.

Not fully completed functional.
This interface net.ykuzub.wizzdep.GlassFishEnv contains information about path to asadmin CLI tool and default domain.
See comments iside there.

 Test coverage is very small.
 These tests are just written as examples.
 In production all possible cases must be covered.
 
 The implementation of class GlassFishServerService in not completed.
  For each incoming response in methods: startServer, stopServer, deploy, undeploy, isAppDeployed, getHttpResponse
  needs to analyze key words from gotten console output response that may indicate about success completed operation and vise versa.
  
  This example intents to demonstrate a high level architecture of the application.
