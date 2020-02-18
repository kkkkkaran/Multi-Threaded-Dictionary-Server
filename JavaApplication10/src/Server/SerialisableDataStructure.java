package Server;

import java.io.Serializable;
import java.util.ArrayList;

public class SerialisableDataStructure implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;
    private int functionID;
    private String word;
    private ArrayList<String> meanings;

  
    public SerialisableDataStructure(int fid, String wrd, ArrayList<String>ml){

        this.functionID=fid;
        this.word=wrd;
        this.meanings=ml;


    }

    public int getFunctionID() {
        return functionID;
    }

    public void setFunctionID(int functionID) {
        this.functionID = functionID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getMeanings() {
        return meanings;
    }

    public void setMeanings(ArrayList<String> meanings) {
        this.meanings = meanings;
    }




    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerialisableDataStructure d1 = (SerialisableDataStructure)o;
        if(functionID!= d1.functionID)return false;
        return true;
    }



}