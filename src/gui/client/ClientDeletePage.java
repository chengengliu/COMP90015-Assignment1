package gui.client;

//import process.ProcessData;

import exceptions.EmptyInputException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientDeletePage extends  ClientFunctionalPage {
    JOptionPane jOptionPane;
    JTextField jTextFieldWord;
    ClientGUI clientGUI;
    JTextArea jTextArea;
    public ClientDeletePage(String function, ClientGUI clientGUI){
        super(clientGUI);
        this.clientGUI = clientGUI;
        label.setText("Please enter the word you want to " + function +":");
        initialiseWindow();

        super.addReturnButton(clientGUI);
        add(returnButton);
        super.loadIcon();

        setVisible(true);
        delete();
    }
    private void initialiseWindow(){
        add(label);
        jTextFieldWord = addTextOnField("Please enter the word here:");
        jTextArea = new JTextArea();
        add(buttonOK);
        add(jTextArea);
    }
    private void delete(){
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = null;
                int yes = addSecondConfirm();
                try{
                    word = jTextFieldWord.getText().trim().toLowerCase();
//                    System.out.println(word);
                    if(word.equals("")||word.equals("please enter the word here:")){
                        throw new EmptyInputException();
                    }
                    // Send messages to the server

                }catch (EmptyInputException ee){
                    ee.printStackTrace();
                }catch (Exception ee){
                    ee.printStackTrace();
                }
                if(yes==JOptionPane.YES_OPTION){
                    try{
                        clientGUI.printWriter.println("Delete");
                        clientGUI.printWriter.println(word); // word to delete
                        // Return message to be added in the GUI later
                        String output = clientGUI.bufferedReader.readLine();
//                        showResponse(output);
                        System.out.println(output);
                        response(output);
                    }catch (IOException ee){
                        ee.printStackTrace();
                    }catch (Exception ee){
                        ee.printStackTrace();
                    }
                }
            }
        });
    }
    @Override
    public void response(String output){
        jTextArea.append("\n"+output);
//        Response response = new Response(output,clientGUI);
        super.response(output);
    }

    @Override
    public int addSecondConfirm(){
        jOptionPane = new JOptionPane();
        int yes = jOptionPane.showConfirmDialog(clientGUI.getjFrame(), "Are you sure to delete the word? ", "Add",
                jOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
//        add(jOptionPane);
        return yes;
    }
}
