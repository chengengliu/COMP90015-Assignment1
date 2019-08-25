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

    public ServerThread(Dictionary dictionary, DicServer server, Socket client){
        this.server = server;
        this.client = client;
        this.dictionary = dictionary;
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
            String flag=null, word=null, meaning=null;
            try{
                flag= breader.readLine();
                word = breader.readLine();
                meaning = breader.readLine();

            }catch (IOException e){
                e.printStackTrace();
            }
            switch (flag){
                case "Search":
                    if(dictionary.contain(word)){
                        // Write out meaning from line to line. If the meaning has multiple lines.
                        for(int i=0; i< DicServer.WORD_ROW; i++){
                            printWriter.println(dictionary.meaning(word)[i]);
                            System.out.println("Word looking for meaning is: " + word);
                            System.out.println("Word meaning is : " + dictionary.meaning(word)[i]);
                        }
                    }
                    else {
                        System.out.println("There is no such word. ");
                    }
                    break;
            }
        }
    }
}
