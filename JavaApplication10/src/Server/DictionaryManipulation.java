package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;



public class DictionaryManipulation {

	public synchronized String add( String keyWordIn,ArrayList<String> Meanings ){
        String keyWord =keyWordIn;
        ArrayList<String> MeaningList= (ArrayList<String>) Meanings;
        DictBackend d1 =new DictBackend();
        d1.loadHashFile();
        d1.getDictionary().put(keyWord,MeaningList);
        d1.saveHashFile();
        d1.saveHashInProperties();
        d1.printDictionary();
        System.out.println(keyWord+" Saved to dictionary");

        return keyWord+"Saved to Dictionary";

    }

    public ArrayList<String> search (String keyWordIn){
        DictBackend d1 =new DictBackend();
        d1.loadHashFile();
        ArrayList<String> Meanings = new ArrayList<String>();
        System.out.println(d1.getDictionary().containsKey(keyWordIn));
        if(d1.getDictionary().containsKey(keyWordIn))
            Meanings= new ArrayList<>(d1.getDictionary().get(keyWordIn));

        return Meanings;
    }

    public synchronized String delete(String keyWordIn){
        String message;
        DictBackend d1 =new DictBackend();
        d1.loadHashFile();
        if(d1.getDictionary().containsKey(keyWordIn)){
            message =keyWordIn+ "Deleted from  dictionary";

            d1.getDictionary().remove(keyWordIn);
        }
        else
            message =keyWordIn+ "Not found in dictionary ";
        d1.saveHashFile();
        d1.printDictionary();
        return message;


    }
    public boolean checkHashKeyValue(String a){
        DictBackend d1 =new DictBackend();
        d1.loadHashFile();
        System.out.println(d1.getDictionary().containsKey(a));
        return d1.getDictionary().containsKey(a);

    }
    public String LoadFile(File fileName) {
         HashMap<String, ArrayList<String>> dic = new HashMap<String, ArrayList<String>>();
        DictBackend d1 =new DictBackend();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(" +", 2);
                String[] abc = info[1].split(",");
                dic.put(info[0],new ArrayList<>(Arrays.asList(abc)));
            }


            d1.loadHashFile();

            d1.getDictionary().putAll(dic);
            d1.saveHashFile();
            d1.saveHashInProperties();
            d1.printDictionary();

            br.close();
        } catch (Exception exc) {
            exc.printStackTrace();
            System.exit(1);
        }
       
        return "Success";
    }
    public void displayMeaniningsFunction( List<String>meanings){
        if( meanings!=null){
            int temp;

            System.out.println("Meaning(s) :");
            for(temp=0; temp <meanings.size(); temp++){
                System.out.println((temp+1)+"  " + meanings.get(temp).toString());
            }
        }
        else
        {
            System.out.println( "Word not found in dictionary please check again or add the word to dictionary.");
        }
    }
}
