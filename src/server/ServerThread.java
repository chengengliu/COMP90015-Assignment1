package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Dictionary dictionary;
    private Socket client;
    private DicServer server;
    private BufferedReader breader;

    public ServerThread(DicServer server, Socket client){
        this.server = server;
        this.client = client;

        try {
            this.breader = new BufferedReader(new InputStreamReader(client.getInputStream()));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true){
            try{
                int flag= Integer.parseInt(breader.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
