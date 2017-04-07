package net.ykuzub.wizzdep.core;

import java.io.IOException;

/**
 * Created by ykuzub on 06.04.2017.
 */
public class ActionRunner {
    public static void runAction(ActionConfig actionConfig) throws IOException {
        GlassFishServerService service = GlassFishServerService.getInstance();
        switch (actionConfig.getAction()) {
            case start: {
                service.startServer();
                break;
            }

            case stop: {
                service.stopServer();
                break;
            }

            case deploy: {
                service.deploy(actionConfig.getAppPath());
                break;
            }

            case undeploy: {
                service.undeploy(actionConfig.getAppName());
                break;
            }

            case check_status: {
                service.isAppDeployed(actionConfig.getAppPath());
                break;
            }

            case check_response: {
                service.getHttpResponse(actionConfig.getHostName(), actionConfig.getAppName());
                break;
            }
            default:{

            }
        }
    }
}
