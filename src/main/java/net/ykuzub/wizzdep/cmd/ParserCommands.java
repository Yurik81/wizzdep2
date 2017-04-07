package net.ykuzub.wizzdep.cmd;

import net.ykuzub.wizzdep.exceptions.ValidationException;
import net.ykuzub.wizzdep.help.ValidatorHelp;
import net.ykuzub.wizzdep.core.ActionConfig;
import org.apache.commons.cli.*;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by ykuzub on 05.04.2017.
 */
public class ParserCommands {
    private Options options;
    private final Option action = new Option(Cmd.SHORT_ACTION, Cmd.ACTION, true, "List of operations {deploy | undeploy | start | stop | check}");
    private final Option app = new Option(Cmd.SHORT_APP, Cmd.APP, true, "Path to .war file");
    private final Option config = new Option(Cmd.SHORT_CONFIG, Cmd.CONFIG, true, "Path to conf.properties file");
    private final Option hostname = new Option(Cmd.SHORT_HOSTNAME, Cmd.HOSTNAME, true, "The name of host machine like http://{server:port}/");
    private final Option user = new Option(Cmd.SHORT_USER, Cmd.USER, true, "user name");
    private final Option password = new Option(Cmd.SHORT_PASSWORD, Cmd.PASSWORD, true, "password");
    private final Option help = new Option(Cmd.SHORT_HELP, Cmd.HELP, false, "Output hel" + "p");
    private ActionConfig actionConfig;

    public ParserCommands(String[] args) throws ValidationException, ParseException {
        this.options = new Options();
        options.addOption(action);
        options.addOption(app);
        options.addOption(config);
        options.addOption(hostname);
        options.addOption(user);
        options.addOption(password);
        options.addOption(help);

        actionConfig = new ActionConfig();
        collectCommands(args);
    }

    public ActionConfig getActionConfig() {
        return actionConfig;
    }

    private void collectCommands(String[] args) throws ValidationException, ParseException {
        CommandLineParser parser = new BasicParser();
        CommandLine line = null;

        line = parser.parse(options, args);


        Iterator<Option> iterator = line.iterator();
        while (iterator.hasNext()) {
            Option opt = iterator.next();
            String longOption = opt.getLongOpt().toLowerCase();
            String param = opt.getValue().trim();
            switch (longOption) {
                case Cmd.ACTION: {
                    actionConfig.setAction(ValidatorHelp.getValidatedActionParam(param));
                    break;
                }

                case Cmd.APP: {
                    String filePath = null;
                    filePath = ValidatorHelp.getValidatedPath(param);
                    actionConfig.setAppPath(filePath);
                    actionConfig.setAppName(ValidatorHelp.getValidatedAppName(filePath));
                    break;
                }

                case Cmd.CONFIG: {
                    String pathToConf = null;
                    pathToConf = ValidatorHelp.getValidatedPath(param);
                    actionConfig.setPathToConfig(pathToConf);
                    break;
                }

                case Cmd.HOSTNAME: {
                    actionConfig.setHostName(ValidatorHelp.getValidatedHostName(param));
                    break;
                }

                case Cmd.USER: {
                    actionConfig.setUser(ValidatorHelp.getValidatedUser(param));
                    break;
                }

                case Cmd.PASSWORD: {
                    actionConfig.setPassword(ValidatorHelp.getValidatedPassword(param));
                    break;
                }
            }
        }
    }

    public void setPropepertiesFromFile(String pathToConf) {
        Properties properties = readPropertiesFromFile(pathToConf);
        String hostName = properties.getProperty(Cmd.HOSTNAME);
        String userName = properties.getProperty(Cmd.USER);
        String password = properties.getProperty(Cmd.PASSWORD);

        if (actionConfig.getHostName() == null) {
            try {
                actionConfig.setHostName(ValidatorHelp.getValidatedHostName(hostName));
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }

        if (actionConfig.getUser() == null) {
            try {
                actionConfig.setUser(ValidatorHelp.getValidatedHostName(userName));
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }

        if (actionConfig.getPassword() == null) {
            try {
                actionConfig.setPassword(ValidatorHelp.getValidatedHostName(password));
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }
    }

    private Properties readPropertiesFromFile(String path) {
        String propertiesFile = path;

        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream(propertiesFile);
        try {
            inputStream = new FileInputStream(propertiesFile);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Error: reason -> Couldn't find the specified file to properties file.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: reason -> Caused an unexpected error during loading properties from specified file.");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Warning: reason -> The file inputStrean assosiated with specified file couldn't be closed successfully");
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

}