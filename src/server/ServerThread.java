package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Dictionary dictionary;
    private Socket client;
    private DicServer server;
    public BufferedReader breader;
    public PrintWriter printWriter;

    public ServerThread(DicServer server, Socket client){
        this.server = server;
        this.client = client;
        this.dictionary = null;
        // Later should also pass the dictionary to here.

        try {
            this.breader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.printWriter = new PrintWriter(client.getOutputStream(),true);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true){
            try{
                String flag= breader.readLine();
                String word = breader.readLine();
                String meaning = breader.readLine();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
