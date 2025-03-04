/*
* Author: Chengeng Liu
* Student ID: 813174
* */
package server;

import exceptions.PortNumberOutOfBounds;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Main driver function for the server side.
 * Hold the most responsibility of error handling, format checking, thread initialisation etc.
 * Severe functionality such as shutting the system, is controlled by the driver as well.
 */
public class DicServer {
    private int port = 12345; // default value
    private String address =null;
    private int clientsNumber =0;
    private ServerSocket listening;
    public static final int WORD_LIMIT = 256;
    public static final int WORD_ROW = 5;
    private static Dictionary dictionary;  // Not too sure if it is static or non-static.
    public ServerGUI serverGUI;
    private HashMap<Integer, Pair<InetAddress,Integer>> map;

    public DicServer(){

    }

    /**
     * Main function for the system.
     * @param args command line arguments: [port_number] [path_of_dictionary]
     */
    public static void main (String args[]){


        DicServer dicServer = new DicServer();
        dicServer.map =new HashMap<>();
        dicServer.serverGUI = new ServerGUI(dicServer);
        dicServer.serverGUI.getFrame().setVisible(true);


        dicServer.validatePortNumber(args);
        dicServer.validateAddress(args);
//        dictionary = new Dictionary(args[1]);

        dicServer.connect();
    }


    private void validatePortNumber(String[] args){
        try{
            port = Integer.parseInt(args[0]);
            if(port > 65535 || port <=1042){
                System.out.println("Port Number: " + port);
                throw new PortNumberOutOfBounds();
            }
            if(args.length !=2)throw new ArrayIndexOutOfBoundsException();
        } catch (PortNumberOutOfBounds e){
            System.out.println("Please enter the port number between 1024 and 65535");
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.out.println("Please enter only number, not something else.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Are you sure that the port number is correctly entered?");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void validateAddress(String[] args){
        try{
            address = args[1];
            File dicFile = new File(address);
            if(!dicFile.exists()) throw new FileNotFoundException();
            if(args.length!=2) throw new ArrayIndexOutOfBoundsException();
            DicServer.dictionary = new Dictionary(args[1]);
        } catch (FileNotFoundException e){
            System.out.println("File not found in the path. Check the path first.");
            DicServer.dictionary = new Dictionary(null); // File not found. Use default dictionary.
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please make sure feed the command line with two arguments.");
            e.printStackTrace();
        }
    }

    private void connect(){
        try {
            listening = new ServerSocket(port);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Start Listening: ");
        while (true){
            Socket client;
            try {
                client = listening.accept();
                clientsNumber++;
                System.out.println("Now the server and the client have established the connection. ");

                // Register current ip and port and notify the server GUI to update.
                registerIpAndPort(client,clientsNumber);
                serverGUI.updateIpAndPort(clientsNumber);

                // Threading start.
                ServerThread serverThread = new ServerThread(dictionary,this, client,clientsNumber);
                new Thread(serverThread).start();
            } catch (IOException e){
                e.printStackTrace();
                break;
            } catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }

    private void registerIpAndPort(Socket client, int order){
        InetAddress address = client.getInetAddress();
        int port = client.getPort();
        Pair<InetAddress, Integer> pair = new Pair<>(address, port);
        map.put(order,pair);
    }

    /**
     * Return the Ip and port of the client. The map contains the order of the client(for later
     * check if all clients disconnects from the server).
     * @return Hashmap of client order number and ip, port.
     */
    public HashMap<Integer,Pair<InetAddress,Integer>> getIpAndPort(){
        return this.map;
    }

    /**
     * Method used by the client side to notify the server that the client has shut down the connection.
     */
    public void clientDisconnect(){
        clientsNumber--;
    }

    /**
     * Shut down the server and close the server side.
     */
    public void serverShutdown(){
        try {
            if(clientsNumber == 0){
                listening.close();
                System.exit(0);
            }
            else {
                serverGUI.serverStatus.append("\nThere are clients online");
                serverGUI.serverStatus.append("\n"+clientsNumber+" clients still remain connected");
                serverGUI.serverStatus.append("\nPlease shutdwon later");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


