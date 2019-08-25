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
    public DicClient(){

    }

    public static void main(String args[]){
        DicClient client = new DicClient();
        client.connect(args);
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                try{
                    ClientGUI clientGUI = new ClientGUI();
                    clientGUI.setFucntions(clientGUI);
                    clientGUI.addListener(clientGUI);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
    private void connect(String[] args){
        try {
            String host = args[1];
            int port = Integer.parseInt(args[1]);
            if(port > 65535 || port <=2014){
                throw new PortNumberOutOfBounds();
            }
            if(args.length !=2) throw new ArrayIndexOutOfBoundsException();
            Socket socket = new Socket(host, port);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
