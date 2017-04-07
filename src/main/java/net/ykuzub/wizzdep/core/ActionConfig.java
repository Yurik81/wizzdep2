package net.ykuzub.wizzdep.core;

import net.ykuzub.wizzdep.cmd.EAction;

/**
 * Created by ykuzub on 05.04.2017.
 */
public class ActionConfig {
    private EAction action;
    private String appPath;
    private String pathToConfig;
    private String hostName;
    private String user;
    private String password;
    private String appName;

    public EAction getAction() {
        return action;
    }

    public void setAction(EAction action) {
        this.action = action;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getPathToConfig() {
        return pathToConfig;
    }

    public void setPathToConfig(String pathToConfig) {
        this.pathToConfig = pathToConfig;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
