package client;

import exceptions.PortNumberOutOfBounds;
import gui.client.ClientGUI;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

// This is the client side driver function.
// remember to EventQueue.invokeLater. Calculation threads block until GUI is updated.
public class DicClient {
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private Socket socket;
    private DicClient(){

    }

    public static void main(String args[]){
        DicClient client = new DicClient();
        // Connect to the server.
//        client.connect(args);
        // Start GUI.
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                try{
                    ClientGUI clientGUI = new ClientGUI(client.socket,
                            client.bufferedReader,client.printWriter);
                    clientGUI.addListener(clientGUI);
                    clientGUI.initialiseGUI(clientGUI); // setVisible and start GUI.
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
    private void connect(String[] args){
        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            if(port > 65535 || port <=2014){
                throw new PortNumberOutOfBounds();
            }
            if(args.length !=2) throw new ArrayIndexOutOfBoundsException();
            Socket socket = new Socket(host, port);
            // Establish string writer and reader associated with the socket.
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Client side connection succeed. ");
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (PortNumberOutOfBounds e){
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}
