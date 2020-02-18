package Server;

import java.io.IOException;

import java.net.ServerSocket;

import java.net.Socket;




public class DictServer  {


    ServerSocket myServerSocket;
    
    boolean ServerOn = true;
    public DictServer(int port) {
        try {
            myServerSocket = new ServerSocket(port);
        } catch (IOException ioe) {
            System.out.println("Could not create server socket on port"+port+". Quitting.");
            System.exit(-1);
        }



        while (ServerOn) {
            try {
                Socket clientSocket = myServerSocket.accept();
                Threads cliThread = new Threads(clientSocket);
                cliThread.start();
            } catch (IOException ioe) {
                System.out.println("Exception found on accept. Ignoring. Stack Trace :");
                ioe.printStackTrace();
            }
        }
        try {
            myServerSocket.close();
            System.out.println("Server Stopped");
        } catch (Exception ioe) {
            System.out.println("Error Found stopping server socket");
            System.exit(-1);
        }
        

    }
    
     public DictServer(int port,boolean a) {
    	if(a==false) {
    		try {
                myServerSocket.close();
                System.out.println("Server Stopped");
            } catch (Exception ioe) {
                System.out.println("Error Found stopping server socket");
                System.exit(-1);
            }
    	}
    	
    }
     public static void serverClose(int port) throws IOException {
    	 
    	 ServerSocket myServerSocket;
    	 myServerSocket = new ServerSocket(port);
    	 try {
             myServerSocket.close();
             System.out.println("Server Stopped");
         } catch (Exception ioe) {
             System.out.println("Error Found stopping server socket");
             System.exit(-1);
         }
    	 
     }

}



