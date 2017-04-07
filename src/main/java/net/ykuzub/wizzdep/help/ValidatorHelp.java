package net.ykuzub.wizzdep.help;

import net.ykuzub.wizzdep.cmd.EAction;
import net.ykuzub.wizzdep.exceptions.ValidationException;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by ykuzub on 06.04.2017.
 */
public class ValidatorHelp {

    private static final String pathRegex = "^(.*/)?(?:$|(.+?)(?:(\\.[^.]*$)|$))";
    private static final String ipAddrRegex = "[a-z0-9]+[\\.]{1}[a-z0-9]+[\\.]{1}[a-z0-9]+[\\.]{1}[a-z0-9]+";
    private static final String nameAddrRegex = "^(?=.{1,255}$)[0-9A-Za-z](?:(?:[0-9A-Za-z]|-){0,61}[0-9A-Za-z])?(?:\\.[0-9A-Za-z](?:(?:[0-9A-Za-z]|-){0,61}[0-9A-Za-z])?)*\\.?$";
    private static final String userRegex = "^[a-z0-9_-]{3,255}$";

    public static String getValidatedHostName(String hostName) throws ValidationException {
        String[] hostnameParts;
        if (!hostName.equals("")) {
            hostnameParts = hostName.split("\\:");
        } else {
            // TODO: output help info about command here
            throw new ValidationException("Wrong hostname and port. Please, check it again.");
        }

        boolean isMatchedIpPattern = Pattern.matches(ipAddrRegex, hostnameParts[0]);
        boolean isMatchedNameAddrRegex = Pattern.matches(nameAddrRegex, hostnameParts[0]);
        int port = Integer.parseInt(hostnameParts[1]);
        if (!(port>1024&&port<=65535)) {
            throw new ValidationException("Wrong port number. Number must be more than 0 and less than 65535. Please, check it again.");
        }
        if (isMatchedIpPattern || isMatchedNameAddrRegex) {
            return (hostnameParts[0]+":"+hostnameParts[1]);
        }
        // TODO: output help info about command here
        throw new ValidationException("Check the name of action parameter. You should use another parameter.");
    }

    public static String getValidatedAppName(String filePath) throws ValidationException {
        File warFile = new File(filePath);
        String filename = warFile.getName();
        if (!filename.equals("")) {
            String[] fileparts = filename.split("\\.");
            return fileparts[0];
        } else {
            // TODO: output help info about command here
            throw new ValidationException("Wrong file path or .war file does not exist in your file path");
        }
    }

    public static EAction getValidatedActionParam(String inputAction) throws ValidationException {
        for (EAction act : EAction.values()) {
            if (act.toString().equalsIgnoreCase(inputAction)) {
                return act;
            }
        }
        // TODO: output help info about command here
        throw new ValidationException("Check the name of action parameter. You should use another parameter.");
    }

    public static String getValidatedPath(String inputPath) throws ValidationException {
        boolean isMatched = Pattern.matches(pathRegex,inputPath);
        if (isMatched) {
            return inputPath;
        }
        throw new ValidationException("Check the correctness of the path to your application.");
    }

    public static String getValidatedUser(String userName) throws ValidationException {
        boolean isMatched = Pattern.matches(userRegex,userName);
        if (isMatched) {
            return userName;
        }
        // TODO: output help info about command here
        throw new ValidationException("Check the correctness of the path to your application.");
    }

    public static String getValidatedPassword(String password) {
        return (password == null? "": password);
    }
}
