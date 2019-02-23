package hmi.flipper2.exampleProject.output;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.AudioInputStream;

public class MaryTTS {

    private MaryInterface marytts;
    private static Logger logger = LoggerFactory.getLogger(MaryTTS.class.getName());

    public MaryTTS(){

    }

    public boolean init(){
        try {
            String voice = new LocalMaryInterface().getVoice();
            marytts = new LocalMaryInterface();
            marytts.setVoice(voice);
            logger.debug("Initialized MaryTTS");
            return true;
        } catch (MaryConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean speak(String text){
        AudioInputStream audio;
        try {
            audio = marytts.generateAudio(text);
            AudioPlayer ap = new AudioPlayer();
            ap.setAudio(audio);
            ap.start();
            return true;
        } catch (SynthesisException e) {
            logger.error("Failed to state: {}. Exception : {}",text,e);
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args){
        MaryTTS maryTTS = new MaryTTS();
        maryTTS.init();
    }
}
