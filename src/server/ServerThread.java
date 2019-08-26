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
//                        printWriter.println("HelloWorld");
                            // Write out meaning from line to line. If the meaning has multiple lines

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
                        }
                        break;
                    case "Add":
                        // If the dictionary doesn't contain the word.
                        if(!dictionary.contain(word)){
                            System.out.println("Now enter the process of Add");

                            try{
                                meaning = breader.readLine();
                            } catch (IOException e){
                                e.printStackTrace();
                            } catch (NullPointerException e){
                                e.printStackTrace();
                            }
                            // Normally it should work fine, as the meaning is checked on client side to
                            // avoid empty input. Here is to double check if the function is working as expected.
                            if(!meaning.isEmpty()){
                                System.out.println("The word and meaning have been received. Meaning is : "+meaning);
                                printWriter.println("The dictionary doesn't have the word. The "+word+" will be added. ");
                                dictionary.add(word,meaning);
                            }
                            else continue;
                        }
                        else continue;
                }
            }

        }
    }
}
