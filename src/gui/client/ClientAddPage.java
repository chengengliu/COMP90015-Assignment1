/*
* Author: Chengeng Liu
* Student ID: 813174
* */
package gui.client;

import exceptions.EmptyInputException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;

public class ClientAddPage extends ClientFunctionalPage{
    JTextArea textArea;
    JTextField jTextFieldWord;
    JTextField jTextFieldMeaning;
    ClientGUI clientGUI;
    JOptionPane jOptionPane;
    JTextArea response;
    WindowAdapter adapter;
//    private final int WORD_LIMIT = 256;
    public ClientAddPage(String function, ClientGUI clientGUI){
        super(clientGUI);
        this.clientGUI = clientGUI;
        label.setText("Please enter the word you want to " +function +": ");
        textArea = new JTextArea("Notice that the first line is the word you want to add, the " +
                "second line is the meaning you want to add. The word should not exist in the dictionary.  ");
        textArea.setColumns(30);
        textArea.setBounds(200,200,100,100);
//        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        initialiseWindow();

        super.addReturnButton(clientGUI);
        super.loadIcon();

        add(returnButton);

        setVisible(true);
        add();
    }
    private void initialiseWindow(){
        add(label);
//        add(textField); // Enter the word
        jTextFieldWord = addTextOnField("Please enter the word here");  // Enter the word you want to add.
//        add(jTextFieldWord);
        jTextFieldMeaning = addTextOnField("Please enter the meaning here"); // Enter the meaning of the word.
//        add(jTextFieldMeaning);
        add(buttonOK);
        add(textArea);
        response = new JTextArea("This is the reponse message: \n");
        response.setLineWrap(true);
        response.setWrapStyleWord(true);
        add(response);
    }

    @Override
    public void errorHandling() {

    }
    private void add(){
        // Add listener on ButtonOK, to listen if the user end entering.
        buttonOK.addActionListener(new ActionListener() {
            // If the user has triggered OK Button
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the second confirm page.
                // variable yes is whether Yes/No.
                String word=null, meaning=null;
                int yes = addSecondConfirm();
                if(yes == JOptionPane.YES_OPTION){
                    try{

                        word = jTextFieldWord.getText().trim().toLowerCase();
                        meaning = jTextFieldMeaning.getText().toLowerCase();

                        if(word.equals("please enter the word here")|| word.equals("")) {
                            System.out.println("");
                            response("The word you entered is not correct");
                            clientGUI.printWriter.println("AddFail");
                            clientGUI.printWriter.println(" ");
                            throw new EmptyInputException();
                        }
                        else if(meaning.equals("please enter the meaning here")|| meaning.equals("")) {
                            System.out.println();
                            response("The meaning you entered is not correct");
                            clientGUI.printWriter.println("AddFail");
                            clientGUI.printWriter.println(word);
                            throw new EmptyInputException();
                        }
                        else {
                            clientGUI.printWriter.println("Add");
                            clientGUI.printWriter.println(word);
                            clientGUI.printWriter.println(meaning);
                            System.out.println("Meaning sent to the server: "+meaning);
                            // Retrieve any messages from the server and set it to the GUI.
                            String output = clientGUI.bufferedReader.readLine();
                            response(output);
                        }
                    } catch (IOException ee){
                        ee.printStackTrace();
                    } catch (EmptyInputException ee){
                        ee.printStackTrace();
                    } catch (Exception ee){
                        ee.printStackTrace();
                    }
                }
            }
        });
    }
    @Override
    public void response(String output){
        response.append(output);
        super.response(output);
    }


    @Override
    public int addSecondConfirm(){
        jOptionPane = new JOptionPane();
        int yes = jOptionPane.showConfirmDialog(clientGUI.getjFrame(), "Add the new word and meaning or not? ", "Add",
                jOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        add(jOptionPane);
//        System.out.println(yes); //yes is zero.
        return yes;
    }
}
