package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

public class ServerThread implements Runnable{
    private Dictionary dictionary;
    private Socket client;
    private DicServer server;
    public BufferedReader breader;
    public PrintWriter printWriter;
    private int order;

    public ServerThread(Dictionary dictionary, DicServer server, Socket client, int order){
        this.server = server;
        this.client = client;
        this.dictionary = dictionary;
        this.order= order;
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
                            server.serverGUI.updateIpAndPort(this.order);
                            server.serverGUI.updateClientStatus("Client Search word: "+word);
                        }
                        else {
                            // Need to report on client side as well.
                            System.out.println("There is no such word. ");
                            printWriter.println(0);
                            printWriter.println("There is no such word.!!!! ");
                            server.serverGUI.updateIpAndPort(this.order);
                            server.serverGUI.updateClientStatus("Client Search word: "+word+" : "+" Fail! No such word!" );
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
                        // If the word is already in the dictionary, test if the meaning is the same as previous one.
                        // If the same, show warning. If not, show success.
                        if(dictionary.contain(word)){
                            if(assertMeaningEquals(word,meaning)){
                                printWriter.println("The dictionary already has the word : "+ word+" and meaning. This is duplication");
                                server.serverGUI.updateIpAndPort(this.order);
                                server.serverGUI.updateClientStatus("Client Add word: "+word+" : "+"Unsuccessfully due to duplication");
                            }
                            else{
                                printWriter.println("The dictionary has the word:" + word+" but with a different meaning. Update the meaning...");
                                // Replace the old meaning with new meaning.
                                dictionary.delete(word);
                                dictionary.add(word,meaning,"input");
                                server.serverGUI.updateIpAndPort(this.order);
                                server.serverGUI.updateClientStatus("Client Add word: "+word+": Successfully, even though the word already exists(meaning different)");
                            }
                        }
                        else {
                            printWriter.println("The dictionary doesn't have the word. Adding the word and meaning");
                            dictionary.add(word,meaning,"input");
                            server.serverGUI.updateIpAndPort(this.order);
                            server.serverGUI.updateClientStatus("Client Add word: "+word+" : "+meaning );
                        }
                        for(int i=0; i<dictionary.meaning(word).length; i++){
                            System.out.println("To make sure the meaning is updated correctly: "+dictionary.meaning(word)[i]);
                        }
                        System.out.println(meaning);
                        break;
                    case "Delete":
                        if(dictionary.contain(word)){
                            printWriter.println("Delete the word :" + word);
                            dictionary.delete(word);
                            server.serverGUI.updateIpAndPort(this.order);
                            server.serverGUI.updateClientStatus("Client Delete word: " + word+" successfully");

                        }
                        else {
                            printWriter.println("The word doesn't exist. Error!");
                            server.serverGUI.updateIpAndPort(this.order);
                            server.serverGUI.updateClientStatus("Client tries to Delete word: " + word+" , but failed. The word not exist!");
                        }
                        break;
                    case "AddFail":
                        server.serverGUI.updateIpAndPort(this.order);
                        if(word.equals(" ")){
                            server.serverGUI.updateClientStatus("Client tries to Add word: "+word+" , but failed. No word detected!");
                        }
                        else  server.serverGUI.updateClientStatus("Client tries to Add word: "+word+" , but failed. No meaning detected!");

                        break;
                    case "Shutdown":
                        server.clientDisconnect();
                        System.out.println("SERVER SHUT DOWN TRIGERRED");
                        printWriter.close();
                        try {
                            breader.close();
                            client.close();
                        }catch (SocketException e){
                            e.printStackTrace();
                            System.out.println("Socket close warnning");
                        }catch (IOException e){
                            e.printStackTrace();
                            System.out.println("Outputstream not closing correctly");
                        }
                        server.serverGUI.updateIpAndPort(this.order);
                        server.serverGUI.updateClientStatus("The client has disconnected");
                        Thread.currentThread().interrupt();
                        return;
                }
            }

        }
    }
    private boolean assertMeaningEquals(String word, String meaning){
        String[] meaningFromDic = dictionary.meaning(word);
        String meaningDic = Arrays.toString(meaningFromDic);
        meaningDic = meaningDic.substring(1,meaningDic.length()-1).replace(",","");
        return meaningDic.equals(meaning);
    }
}
