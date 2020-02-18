package Server;

import clientapp.DataToGUI;

import java.util.ArrayList;

import com.google.gson.Gson;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Threads extends Thread {


    Socket myClientSocket;
    boolean m_bRunThread = true;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    boolean ServerOn = true;
    DictionaryManipulation df =new DictionaryManipulation();
	private PrintWriter out;

    public Threads() {
        super();
    }

    Threads(Socket s) {
        myClientSocket = s;
    }

    public void run() {

       out = null;

        System.out.println(
                "Accepted Client ");
        
       System.out.println("");
        try {
            System.out.println("inside try");
            inputStream = new ObjectInputStream(myClientSocket.getInputStream());

            System.out.println(inputStream.toString());

            Gson gson = new Gson();
            outputStream = new ObjectOutputStream(myClientSocket.getOutputStream());

           

                SerialisableDataStructure sds;

                sds =(SerialisableDataStructure) gson.fromJson((String) inputStream.readObject(), SerialisableDataStructure.class);
            
                System.out.println(sds.getWord());
                System.out.println(sds.getFunctionID());
                System.out.println("don loaded");

                if (!ServerOn) {
                    System.out.print("Server is inactive");
                    out.println("Server is inactive");
                    out.flush();
                    m_bRunThread = false;
                }
                // Search Meaning
   
                if(sds.getFunctionID()==1){
                    boolean checker =df.checkHashKeyValue(sds.getWord());
                    System.out.println(checker);

                    if( checker== true){
                        System.out.println(checker);
                        ArrayList<String>Meanings=df.search(sds.getWord());
                        DataToGUI dout= new DataToGUI("Word found",sds.getWord(),Meanings);
                        outputStream.writeObject(gson.toJson(dout));
                        df.displayMeaniningsFunction(Meanings);
                    }
                    else{
                        ArrayList<String>Meanings=df.search(sds.getWord());
                        Meanings.add("No meanings found for this word.");
                        DataToGUI dout= new DataToGUI("Not found",sds.getWord(),Meanings);
                        outputStream.writeObject(gson.toJson(dout));
                        System.out.println("not found");
                    }

                }

                // Add word
                if(sds.getFunctionID()==3){
                    if(df.checkHashKeyValue(sds.getWord())){
                        DataToGUI dout= new DataToGUI("Word Already exists in Dictionary");
                        outputStream.writeObject(gson.toJson(dout));
                    }
                    else{
                        DataToGUI dout= new DataToGUI(df.add(sds.getWord(),sds.getMeanings()));
                        outputStream.writeObject(gson.toJson(dout));
                    }

                }

                // Remove word
                if(sds.getFunctionID()==2){
                	
                    if(!df.checkHashKeyValue(sds.getWord())){
                        DataToGUI dout= new DataToGUI("Word doesn't exist in dictionary");
                        outputStream.writeObject(gson.toJson(dout));
                    }
                    else{
                        df.delete(sds.getWord());
                        DataToGUI dout= new DataToGUI(sds.getWord()+" Deleted");
                        outputStream.writeObject(gson.toJson(dout));

                    }
                }
                // exit
                if(sds.getFunctionID()==-1){
                    DataToGUI dout= new DataToGUI("Stopping client thread for client : ");
                    outputStream.writeObject(gson.toJson(dout));
                    m_bRunThread = false;
                }
            
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
    




}