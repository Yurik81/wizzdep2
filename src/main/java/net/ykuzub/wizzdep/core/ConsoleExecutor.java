package net.ykuzub.wizzdep.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by ykuzub on 06.04.2017.
 */

public class ConsoleExecutor {
    private static Process process;
    private static final long timeOutInMinutes = 2;

    public static Stream<String> execute(String command) throws IOException {
        process = Runtime.getRuntime().exec(command);
        try {
            process.waitFor(timeOutInMinutes, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("Exception cause due to some process is being stopped by some unexpected situation.");
            e.printStackTrace();
            System.exit(-1);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"))) {
            return reader.lines();
        }
    }
}
