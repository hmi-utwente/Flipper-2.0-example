package hmi.flipper2.exampleProject.input;

public class Sphinx4 {

    private String speech;

    public Sphinx4(){
        speech = "";
    }

    public boolean init(){
        return true;
    }

    public boolean hasMessage(){
        return !speech.equals("");
    }

    public String getMessage(){
        return speech;
    }

}
