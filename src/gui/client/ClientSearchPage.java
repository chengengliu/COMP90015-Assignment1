package gui.client;

import exceptions.EmptyInputException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientSearchPage extends ClientFunctionalPage implements  PageFunction{
    JTextArea textArea;
    JOptionPane jOptionPane;
    JTextField jTextFieldWord;
    ClientGUI clientGUI;
    public ClientSearchPage(String function, ClientGUI clientGUI){
        super(clientGUI);
        this.clientGUI =clientGUI;
        label.setText("Please enter the word you want to" + function + ":");
        initialiseWindow();

        super.addReturnButton(clientGUI);
        add(returnButton);

        setVisible(true);

        search();
    }

    private void initialiseWindow(){
        add(label);
        jTextFieldWord = addTextOnField("Please enter the word here:");
        add(buttonOK);
    }

    private void search(){
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int yes = addSecondConfirm();
                try{
                    String word;
                    word = jTextFieldWord.getText().toLowerCase();
//                    System.out.println(word);
                    if(word.equals("")|| word.equals("please enter the word here")){
                        throw new EmptyInputException();
                    }
                    clientGUI.printWriter.println("Search");
                    clientGUI.printWriter.println(word);
                    String output = clientGUI.bufferedReader.readLine(); // Later this will be the returning message.
                    System.out.println(output);
                }catch (EmptyInputException ee){
                    ee.printStackTrace();
                }catch (IOException ee){
                    ee.printStackTrace();
                }catch (Exception ee){
                    ee.printStackTrace();
                }
                // Now the input is valid and is not empty.
            }
        });
    }

    @Override
    public int addSecondConfirm(){
        jOptionPane = new JOptionPane();
        int yes = jOptionPane.showConfirmDialog(clientGUI.getjFrame(), "Add the new word and meaning or not? ", "Add",
                jOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        add(jOptionPane);
        return  yes;
    }

    @Override
    public void errorHandling() {

    }

}
