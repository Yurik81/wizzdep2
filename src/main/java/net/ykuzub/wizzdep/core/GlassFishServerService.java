package net.ykuzub.wizzdep.core;

import net.ykuzub.wizzdep.GlassFishEnv;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Stream;

/**
 * Created by ykuzub on 06.04.2017.
 * Not fully completed from functional perspective:
 * For each incoming response in methods: startServer, stopServer, deploy, undeploy, isAppDeployed, getHttpResponse
 * needs to analyze key words that may indicate about success completed operation and vise versa
 */
public class GlassFishServerService {
    private static GlassFishServerService instance = null;
    private boolean serverUp = false;
    private boolean lastDeployedStatus = false;

    private GlassFishServerService() {
    }

    public static GlassFishServerService getInstance() {
        if (instance == null) {
            instance = new GlassFishServerService();
        }
        return instance;
    }

    private static ConsoleExecutor consoleExecutor = new ConsoleExecutor();

    public boolean isServerUp() {
        return serverUp;
    }

    public boolean isDeployed(String appName) {
        return lastDeployedStatus;
    }

    public void startServer() throws IOException {
        String command = GlassFishEnv.ASADMIN_PATH + " start-domain " + GlassFishEnv.DEF_DOMAIN;
        Stream<String> response = consoleExecutor.execute(command);

        /* hust a mock here*/
        // TODO: Parse response here, if response contains "success result" and vise versa in the stream
        if (serverUp) {
            System.out.println("The applicaton server is already running.");
            return;
        } else {
            System.out.println("The application server is strating running. Please, wait a minute.");
            serverUp = true;
        }
    }

    public void stopServer() throws IOException {
        String command = GlassFishEnv.ASADMIN_PATH + " stop-domain " + GlassFishEnv.DEF_DOMAIN;
        Stream<String> response = consoleExecutor.execute(command);

        /* just a mock below*/
        // TODO: Parse response here, if response contains "success result" and vise versa in the stream
        if (serverUp) {
            System.out.println("The application server is  stopping. Please, wait a few seconds.");
            serverUp = false;
        } else {
            System.out.println("The application server stopped successfully.");
            serverUp = true;
        }

    }

    public void deploy(String pathToWar) throws IOException {
        String command = GlassFishEnv.ASADMIN_PATH + " deploy " + pathToWar;
        Stream<String> response = consoleExecutor.execute(command);

        // Just mocking below
        // TODO: Parse response here, if response contains "success result" and vise versa in the stream
        if (lastDeployedStatus == true) {
            System.out.println("The application is already deployed on the server.");
        } else {
            lastDeployedStatus = true;
            System.out.println("The application has been deployed successfully.");
        }
    }

    public void undeploy(String appName) throws IOException {
        String command = GlassFishEnv.ASADMIN_PATH + " deploy " + appName;
        Stream<String> response = consoleExecutor.execute(command);

        // Just a mock below
        // TODO: Parse response here, if response contains "success result" and vise versa in the stream
        if (lastDeployedStatus == true) {
            System.out.println("The application has been undeployed successfully.");
            lastDeployedStatus = false;
        } else {
            System.out.println("The application is already undeployed.");
        }
    }

    public boolean isAppDeployed(String appName) throws IOException {
        String command = GlassFishEnv.ASADMIN_PATH + " list-applications";
        Stream<String> response = consoleExecutor.execute(command);

        // just a mock below
        // TODO: Parse response here, if response contains "success result" and vise versa in the stream
        return lastDeployedStatus;
    }

    public boolean getHttpResponse(String hostName, String appName) throws MalformedURLException {
/*        URL url = new URL("http", hostName, "/"+appName);
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;*/

        //just a mock below
        return (lastDeployedStatus ? true : false);
    }
}