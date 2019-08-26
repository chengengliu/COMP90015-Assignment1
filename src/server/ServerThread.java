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

            }catch (IOException e){
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            if(flag == null) {
                continue;
            }
            else {
                switch (flag){
                    case "Search":
                        if(dictionary.contain(word)){
                            System.out.println("Now Enter the Search part. ");
                            for(int i=0; i< dictionary.meaning(word).length+1; i++){
                                try {
                                    if(i==0) printWriter.println(dictionary.meaning((word)).length);
                                    else printWriter.println(dictionary.meaning(word)[i-1]);
                                }catch (ArrayIndexOutOfBoundsException e){
                                    e.printStackTrace();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                System.out.println("Word looking for meaning is: " + word);
                                if(i!=0) System.out.println("Word meaning is : " + dictionary.meaning(word)[i-1]);
                            }
                        }
                        else {
                            // Need to report on client side as well.
                            System.out.println("There is no such word. ");
                            printWriter.println(0);
                            printWriter.println("There is no such word.!!!! ");
                        }
                        break;
                    case "Add":
                        // If the dictionary doesn't contain the word.
                        System.out.println("Enter the stage of adding. ");
                        try {
                            meaning = breader.readLine();
                        }catch (IOException e){
                            e.printStackTrace();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(dictionary.contain(word)){
                            printWriter.println("The dictionary already has the word : "+ word);
                        }
                        else {
                            printWriter.println("The dictionary doesn't have the word. Updating.");
                        }
                        dictionary.add(word,meaning);
                        break;
                    case "Delete":
                        if(dictionary.contain(word)){
                            printWriter.println("Delete the word :" + word);
                            dictionary.delete(word);
                        }
                        else {
                            printWriter.println("The word doesn't exist. Error!");
                        }
                        break;
                    case "Shutdown":
                        server.disconnect();
                        printWriter.close();
                        try {
                            breader.close();
                            client.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        Thread.currentThread().interrupt();
                        return;
                }
            }

        }
    }
}
