package Server;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DictBackend {

    HashMap<String, List<String>> Dictionary = new HashMap<String, List<String>>();
    Properties properties = new Properties();

    public HashMap<String, List<String>> getDictionary() {
        return Dictionary;
    }

    public void setDictionary(HashMap<String, List<String>> dictionary) {
        Dictionary = dictionary;
    }

    public void loadHashFile(){

        HashMap<Integer, String> map = null;
        try
        {
            FileInputStream fis = new FileInputStream("data.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.Dictionary = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized HashMap..");
    }
    public void loadHashInProperties(){

        Dictionary = new HashMap<String, List<String>>((Map)properties);
    }
    
    public void saveHashFile() {
        try {

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
            out.writeObject(Dictionary);
            out.close();
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void saveHashInProperties(){

        Properties properties = new Properties();
        properties.putAll(Dictionary);


    }
    public void printDictionary(){
        System.out.println( Dictionary.keySet().toString());
        System.out.println(Dictionary.values().toString());



    }
  


}
