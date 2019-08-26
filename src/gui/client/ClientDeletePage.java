package gui.client;

//import process.ProcessData;

import exceptions.EmptyInputException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientDeletePage extends  ClientFunctionalPage {
    JOptionPane jOptionPane;
    JTextField jTextFieldWord;
    ClientGUI clientGUI;
    public ClientDeletePage(String function, ClientGUI clientGUI){
        super(clientGUI);
        this.clientGUI = clientGUI;
        label.setText("Please enter the word you want to " + function +":");
        initialiseWindow();

        super.addReturnButton(clientGUI);
        add(returnButton);

        setVisible(true);
        delete();
    }
    private void initialiseWindow(){
        add(label);
        jTextFieldWord = addTextOnField("Please enter the word here:");
        add(buttonOK);
    }
    private void delete(){
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int yes = addSecondConfirm();
                try{
                    String word;
                    word = jTextFieldWord.getText().trim().toLowerCase();
                    System.out.println(word);
                    if(word.equals("")||word.equals("please enter the word here:")){
                        throw new EmptyInputException();
                    }
                    // Send messages to the server
                    clientGUI.printWriter.println("Delete");
                    clientGUI.printWriter.println(word); // word to delete
                    //TODO: Add retrieved message to the server.
                    // Return message to be added in the GUI later
                    String output = clientGUI.bufferedReader.readLine();
                    System.out.println(output);

                }catch (EmptyInputException ee){
                    ee.printStackTrace();
                }catch (Exception ee){
                    ee.printStackTrace();
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
        return yes;
    }
}
