package Managers;

public class FakeManager {
    // Managers initialize..
    public FakeManager(){

    }
    // and provide payload inputs...
    public String doSomething(String payload){

        // then return their response. :)
        return payload + " had something done to it.";
    }
}
