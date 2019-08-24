package client;

import gui.client.ClientGUI;

import java.awt.*;

// This is the client side driver function.
// remember to EventQueue.invokeLater. Calculation threads block until GUI is updated.
public class DicClient {
    public static void main(String args[]){

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
}
