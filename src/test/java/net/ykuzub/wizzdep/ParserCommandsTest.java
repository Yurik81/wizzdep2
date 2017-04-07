package net.ykuzub.wizzdep;

import junit.framework.Assert;
import net.ykuzub.wizzdep.cmd.Cmd;
import net.ykuzub.wizzdep.cmd.ParserCommands;
import net.ykuzub.wizzdep.core.ActionConfig;
import net.ykuzub.wizzdep.exceptions.ValidationException;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.Test;

/**
 * Created by ykuzub on 07.04.2017.
 * <p>
 * Test coverage is very small.
 * In production all possible cases must be covered.
 * These tests are just examples
 */
public class ParserCommandsTest {

    @Test
    public void testGetActionParam() throws ValidationException, ParseException {
        String[] args = {
                "--" + Cmd.ACTION,
                "start"};

        ParserCommands parserCommands = new ParserCommands(args);
        ActionConfig actionConfig = parserCommands.getActionConfig();
        Assert.assertTrue(args[1].equalsIgnoreCase(actionConfig.getAction().toString()));
    }

    @Test
    public void testGetApplicationParam() throws ValidationException, ParseException {
        String[] args = {
                "--" + Cmd.ACTION,
                "start",
                "--" + Cmd.APP,
                "c:\\TestFolder\\hello.war"};

        ParserCommands parserCommands = new ParserCommands(args);
        ActionConfig actionConfig = parserCommands.getActionConfig();
        Assert.assertTrue(args[3].equalsIgnoreCase(actionConfig.getAppPath()));
    }

    @Test
    public void testGetConfigPathParam() throws ValidationException, ParseException {
        String[] args = {
                "--" + Cmd.ACTION,
                "start",
                "--" + Cmd.APP,
                "c:\\TestFolder\\hello.war",
                "--" + Cmd.CONFIG,
                "c:\\TestFolder\\config.properties"};
        ParserCommands parserCommands = new ParserCommands(args);
        ActionConfig actionConfig = parserCommands.getActionConfig();
        Assert.assertEquals(args[5], actionConfig.getPathToConfig());
    }

    @Test
    public void testGetUserParam() throws ValidationException, ParseException {
        String[] args = {
                "--" + Cmd.ACTION,
                "start",
                "--" + Cmd.APP,
                "c:\\TestFolder\\hello.war",
                "--" + Cmd.CONFIG,
                "c:\\TestFolder\\config.properties",
                "--" + Cmd.USER,
                "admin"};
        ParserCommands parserCommands = new ParserCommands(args);
        ActionConfig actionConfig = parserCommands.getActionConfig();
        Assert.assertEquals(args[7], actionConfig.getUser());
    }

    @Test
    public void testGetPasswordParam() throws ValidationException, ParseException {
        String[] args = {
                "--" + Cmd.ACTION,
                "start",
                "--" + Cmd.APP,
                "c:\\TestFolder\\hello.war",
                "--" + Cmd.CONFIG,
                "c:\\TestFolder\\config.properties",
                "--" + Cmd.PASSWORD,
                "superpassword"};
        ParserCommands parserCommands = new ParserCommands(args);
        ActionConfig actionConfig = parserCommands.getActionConfig();
        Assert.assertEquals(args[7], actionConfig.getPassword());
    }

    @Test
    public void testGetHostNameParam() throws ValidationException, ParseException {
        String[] args = {
                "--" + Cmd.ACTION,
                "start",
                "--" + Cmd.APP,
                "c:\\TestFolder\\hello.war",
                "--" + Cmd.CONFIG,
                "c:\\TestFolder\\config.properties",
                "--" + Cmd.HOSTNAME,
                "localhost:8080"};
        ParserCommands parserCommands = new ParserCommands(args);
        ActionConfig actionConfig = parserCommands.getActionConfig();
        Assert.assertEquals(args[7], actionConfig.getHostName());
    }

    @Test(expected = ValidationException.class)
    public void testWrongPortNumberInHostName() throws ValidationException, ParseException {
        String[] args = {
                "--" + Cmd.ACTION,
                "start",
                "--" + Cmd.APP,
                "c:\\TestFolder\\hello.war",
                "--" + Cmd.CONFIG,
                "c:\\TestFolder\\config.properties",
                "--" + Cmd.HOSTNAME,
                "localhost:80803"};
        ParserCommands parserCommands = new ParserCommands(args);
        ActionConfig actionConfig = parserCommands.getActionConfig();
        Assert.assertEquals(args[7], actionConfig.getHostName());
    }

    @Test(expected = ValidationException.class)
    public void testWrongActionParam() throws ValidationException, ParseException {
        String[] args = {
                "--" + Cmd.ACTION,
                "sstart"};
        ParserCommands parserCommands = new ParserCommands(args);
        ActionConfig actionConfig = parserCommands.getActionConfig();
        Assert.assertEquals(args[1], actionConfig.getAction());
    }

    @Test (expected = UnrecognizedOptionException.class)
    public void testUnrecognizedOption() throws ValidationException, ParseException {
        String[] args = {
                "--assadasda",
                "deploy"};
        ParserCommands parserCommands = new ParserCommands(args);
        ActionConfig actionConfig = parserCommands.getActionConfig();
        Assert.assertEquals(args[1], actionConfig.getAction());
    }
}
