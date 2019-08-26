package gui.client;

import exceptions.EmptyInputException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientAddPage extends ClientFunctionalPage{
    JTextArea textArea;
    JTextField jTextFieldWord;
    JTextField jTextFieldMeaning;
    ClientGUI clientGUI;
    JOptionPane jOptionPane;
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
                try{

                    word = jTextFieldWord.getText().trim().toLowerCase();
                    meaning = jTextFieldMeaning.getText().toLowerCase();

                    // What if the user enter?????
                    // After testing it seems all good. Enter will not effect the String.
//                    System.out.println(jTextFieldWord.getText()); // word entered by the user
//                    System.out.println(jTextFieldMeaning.getText()); // meaning

                    if(word.equals("please enter the word here")|| word.equals("")) {
                        System.out.println("The word you entered is not correct");
                        throw new EmptyInputException();
                    }
                    if(meaning.equals("please enter the meaning here")|| meaning.equals("")) {
                        System.out.println("The meaning you entered is not correct");
                        throw new EmptyInputException();
                    }

                } catch (EmptyInputException ee) {
                    ee.printStackTrace();
                } catch (Exception ee){
                    ee.printStackTrace();
                }
                // The user has confirmed the inputs and the inputs are in correct format.
                if(yes == JOptionPane.YES_OPTION){
                    try{
                        clientGUI.printWriter.println("Add");
                        clientGUI.printWriter.println(word);
                        clientGUI.printWriter.println(meaning);
                        System.out.println("Meaning sent to the server: "+meaning);
                        // Retrieve any messages from the server and set it to the GUI.
                        String output = clientGUI.bufferedReader.readLine();
                        System.out.println("Add output:"+output);
                    } catch (IOException ee){
                        ee.printStackTrace();
                    } catch (Exception ee){
                        ee.printStackTrace();
                    }
                }
            }
        });
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
