package hmi.flipper2.exampleProject.output;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaryTTS {

    private MaryInterface marytts;
    private static Logger logger = LoggerFactory.getLogger(MaryTTS.class.getName());

    public MaryTTS(){

    }

    public boolean init(){
        try {
            String voice = new LocalMaryInterface().getVoice();
            return true;
        } catch (MaryConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean speak(String text){

        return true;
    }
}
