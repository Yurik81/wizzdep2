package net.ykuzub.wizzdep.cmd;

/**
 * Created by ykuzub on 05.04.2017.
 */
public interface Cmd {
    String ACTION = "action";
    String SHORT_ACTION = "a";

    String APP = "application";
    String SHORT_APP = "app";

    String CONFIG = "config";
    String SHORT_CONFIG = "c";

    String HOSTNAME = "hostname";
    String SHORT_HOSTNAME = "hn";

    String USER = "user";
    String SHORT_USER = "u";

    String PASSWORD = "password";
    String SHORT_PASSWORD = "p";

    String HELP = "help";
    String SHORT_HELP = "h";
}