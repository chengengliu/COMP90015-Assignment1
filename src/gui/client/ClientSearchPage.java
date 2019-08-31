/*
* Author: Chengeng Liu
* Student ID: 813174
* */
package gui.client;

import exceptions.EmptyInputException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class ClientSearchPage extends ClientFunctionalPage implements  PageFunction{
    JTextArea textArea;
    JOptionPane jOptionPane;
    JTextField jTextFieldWord;
    ClientGUI clientGUI;
    JTextArea jTextArea;
    public ClientSearchPage(String function, ClientGUI clientGUI){
        super(clientGUI);
        this.clientGUI =clientGUI;
        label.setText("Please enter the word you want to" + function + ":");
        initialiseWindow();

        super.addReturnButton(clientGUI);
        add(returnButton);
        super.loadIcon();

        setVisible(true);

        search();
    }

    private void initialiseWindow(){
        add(label);
        jTextFieldWord = addTextOnField("Please enter the word here:");
        jTextArea = new JTextArea("This is the response message:");
        add(buttonOK);
        add(jTextArea);
    }

    private void search(){
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word =null, result =null;
                int yes = addSecondConfirm();
                try{
                    word = jTextFieldWord.getText().trim().toLowerCase();
//                    System.out.println(word);
                    if(word.equals("")|| word.equals("please enter the word here")){
                        throw new EmptyInputException();
                    }
                }catch (NumberFormatException ee){
                    ee.printStackTrace();
                }catch (EmptyInputException ee){
                    ee.printStackTrace();
                }catch (Exception ee){
                    ee.printStackTrace();
                }
                // Now the input is valid and is not empty.
                if(yes == JOptionPane.YES_OPTION){
                    try{
                        clientGUI.printWriter.println("Search");
                        clientGUI.printWriter.println(word);
                        int length = Integer.parseInt(clientGUI.bufferedReader.readLine()); // Later this will be the returning message.
                        if(length!=0){
                            String[] output = new String[length];
                            for(int i=0; i<length; i++){
                                output[i] = clientGUI.bufferedReader.readLine();
                                // Meaning is stored in array
                                // of list with newline character.
                                System.out.println(output[i]);
                            }
                            result = stringParser(output);
                        }
                        else {
                            result = "There is no such word.";
                            clientGUI.bufferedReader.readLine(); // have to skip the last line, when the returning message is "not found"
                        }
                        System.out.println("This is the search result:" + result);
                        response(result);

                    }catch (IOException ee){
                        ee.printStackTrace();
                    }catch (Exception ee){
                        ee.printStackTrace();
                    }
                }
            }
        });
    }
    private String stringParser(String[] output){
        String str = Arrays.toString(output);
        str = str.substring(1,str.length()-1).replace(",","");
        return  str;
    }
    @Override
    public void response(String output){
        jTextArea.append("\n"+output);
        super.response(output);
    }

    @Override
    public int addSecondConfirm(){
        jOptionPane = new JOptionPane();
        int yes = jOptionPane.showConfirmDialog(clientGUI.getjFrame(), "Confirm search the word? ", "Add",
                jOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        add(jOptionPane);
        return  yes;
    }

    @Override
    public void errorHandling() {

    }

}
