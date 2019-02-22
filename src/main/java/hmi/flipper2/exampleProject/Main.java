package hmi.flipper2.exampleProject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import hmi.flipper2.launcher.FlipperLauncherThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class.getName());
    private static FlipperLauncherThread flt;

    public static void main(String[] args){
        String help = "Expecting commandline arguments in the form of \"-<argname> <arg>\".\nAccepting the following argnames: config";
        String flipperPropFile = "flipper/flipper.properties";


        if (args.length % 2 != 0) {
            System.err.println(help);
            System.exit(0);
        }

        for (int i = 0; i < args.length; i = i + 2) {
            if (args[i].equals("-config")) {
                flipperPropFile = args[i + 1];
            } else {
                System.err.println("Unknown commandline argument: \"" + args[i] + " " + args[i + 1] + "\".\n" + help);
                System.exit(0);
            }
        }

        Properties ps = new Properties();
        InputStream flipperPropStream = Main.class.getClassLoader().getResourceAsStream(flipperPropFile);
        try {
            ps.load(flipperPropStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("Flipperlauncher: Starting Thread");
        flt = new FlipperLauncherThread(ps);
        flt.start();


    }
}
