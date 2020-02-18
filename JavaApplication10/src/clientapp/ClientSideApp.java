/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapp;
import com.google.gson.*;

import Server.SerialisableDataStructure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
/**
 *
 *
 */
public class ClientSideApp {

private Socket socket = null;
private ObjectInputStream inputStream = null;
private ObjectOutputStream outputStream = null;
boolean isConnected = false;
int function;
DataToGUI dout;
SerialisableDataStructure din;



public DataToGUI coreExec(SerialisableDataStructure dinFromGUI, int port) throws ClassNotFoundException {
    try {
    socket = new Socket("localHost", port);
    
    System.out.println("Connected"+port);
    isConnected = true;
    outputStream = new ObjectOutputStream(socket.getOutputStream());
    
    
    SerialisableDataStructure din= dinFromGUI;
    Gson gson = new Gson();
        
    function = din.getFunctionID();
    if(function==1 || function==2 || function==3) {
    	outputStream.writeObject(gson.toJson(din));
        outputStream.write(1);
        System.out.println(outputStream.toString());
        inputStream = new ObjectInputStream(socket.getInputStream());
        String a = (String) inputStream.readObject();
        System.out.println(a);
        DataToGUI doutToGUI =gson.fromJson(a, DataToGUI.class);
         return doutToGUI;
    }
    
    if(function == -1){
        isConnected = false;
    }
    outputStream.writeObject(gson.toJson(din));
    outputStream.write(1);
    System.out.println(outputStream.toString());
    inputStream = new ObjectInputStream(socket.getInputStream());
    returnData((DataToGUI) inputStream.readObject());
    System.out.println(dout.Message);
    

        } catch (SocketException se) {
        se.printStackTrace();
        DataToGUI doutToGUI =new DataToGUI();
        doutToGUI.setMessage("Connection Failed");
        // System.exit(0);
        } 
        catch (IOException e) {
        e.printStackTrace();
            }
    DataToGUI doutToGUIFinal =new DataToGUI();
    doutToGUIFinal.setMessage("Connection Failed");
    return doutToGUIFinal;
}


    public SerialisableDataStructure getData(int a, String b, ArrayList<String> c  ){
    
    SerialisableDataStructure d1 = new SerialisableDataStructure(a, b, c);
    
    return d1;
    }
    
    public void returnData(DataToGUI d){
        this.dout = d;
    }
    
}
