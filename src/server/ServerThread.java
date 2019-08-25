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
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            switch (flag){
                case "Search":
                    if(dictionary.contain(word)){
                        System.out.println("Now Enter the Search part. ");
                        printWriter.println("HelloWorld");
                        // Write out meaning from line to line. If the meaning has multiple lines

                        //TODO: 这里我觉得不对。。 因为事先不知道有多大的array，所以会触及到boundry 之外的。。
                        for(int i=0; i< DicServer.WORD_ROW; i++){
                            try {
                                printWriter.println(dictionary.meaning(word)[i]);
                            }catch (ArrayIndexOutOfBoundsException e){
                                e.printStackTrace();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
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
