package net.ykuzub.wizzdep;

import net.ykuzub.wizzdep.cmd.ParserCommands;
import net.ykuzub.wizzdep.core.ActionConfig;
import net.ykuzub.wizzdep.core.ActionRunner;
import net.ykuzub.wizzdep.exceptions.ValidationException;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

/**
 * Created by ykuzub on 04.04.2017.
 */
public class WizzTool {

    public static void main(String[] args) throws IOException, ValidationException {
        ParserCommands parserCommands = null;
        try {
            parserCommands = new ParserCommands(args);
        } catch (ValidationException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        ActionConfig actionConfig = parserCommands.getActionConfig();
        parserCommands.setPropepertiesFromFile(actionConfig.getPathToConfig());
        if (actionConfig.getNeedHelp()) {
            parserCommands.showHelpInfo();
            return;
        }
        ActionRunner.runAction(actionConfig);
    }
}