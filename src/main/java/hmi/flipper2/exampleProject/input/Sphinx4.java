package hmi.flipper2.exampleProject.input;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Sphinx4 {

    private SpeechResult result;
    private LiveSpeechRecognizer recognizer;
    private BlockingQueue<String> messages = new LinkedBlockingQueue();


    public Sphinx4(){
    }

    /**
     * Initialize the Sphinx4 ASR with default EN-us and start the speech recognizer
     * @return true if successfully started
     */
    public boolean init(){
        Thread sphinxThread = new Thread(() -> initSphinx());
        sphinxThread.start();
        if(sphinxThread.isAlive()){
            return true;
        }
        return false;

    }

    private void initSphinx(){
        try {
            String utterance;
            Logger logger = LoggerFactory.getLogger(Sphinx4.class.getName());
            Configuration configuration = new Configuration();
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
            recognizer = new LiveSpeechRecognizer(configuration);
            recognizer.startRecognition(true);
            boolean x = true;
            while(x){
                result = recognizer.getResult();
                utterance = result.getHypothesis();
                messages.add(utterance);
                logger.debug("Said: " + utterance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Stop the Sphinx4 ASR
     * @return
     */
    public boolean stop(){
        this.recognizer.stopRecognition();
        return true;
    }

    /**
     * Start the Sphinx4 ASR (do not call after initialization)
     * @return if succeeded
     */
    public boolean start(){
        this.recognizer.startRecognition(false);
        return true;
    }

    /**
     * General method for if it has transcribed speech
     * @return true if a result is final
     */
    public boolean hasMessage(){
        return messages.size() != 0;
    }

    /**
     * General method for retrieving transcribed speech
     * @return the hypothesis speech of Sphinx4
     */
    public String getMessage(){
        try {
            return messages.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        Sphinx4 sphinx = new Sphinx4();
        sphinx.init();

    }


}
