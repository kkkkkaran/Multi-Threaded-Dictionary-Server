package clientapp;

import java.io.Serializable;
import java.util.ArrayList;

public class DataToGUI implements Serializable {

    private static final long serialVersionUID = 5950169519310163576L;

    DataToGUI() {
       
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public ArrayList<String> getMeanings() {
        return Meanings;
    }

    public void setMeanings(ArrayList<String> meanings) {
        Meanings = meanings;
    }

    String Message;
    String Word;
    ArrayList<String > Meanings;

    public DataToGUI(String msg, String wrd){
        this.Message =msg;
        this.Word =wrd;
    }
    
    

    public DataToGUI(String msg){
        this.Message =msg;
    }

    public DataToGUI(String msg, String wrd, ArrayList<String> mngs){
        this.Word=wrd;
        this.Message=msg;
        this.Meanings=mngs;

    }
}