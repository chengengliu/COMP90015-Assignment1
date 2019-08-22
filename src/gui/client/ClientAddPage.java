package gui.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientAddPage extends ClientFunctionalPage implements PageFunction{
    JTextArea textArea;
    JButton returnButton;
    public ClientAddPage(String function, ClientGUI clientGUI){
        super(clientGUI);
        label.setText("Enter the word you want to " +function +": ");
        textArea = new JTextArea("Notice that the first line is the word you want to add, the " +
                "second line is the meaning you want to add. The word should not exist in the dictionary.  ");
        textArea.setColumns(30);
        textArea.setBounds(200,200,100,100);
//        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        initialiseWindow();
        addReturnButton(clientGUI);
        setVisible(true);

    }
    @Override
    public void initialiseWindow(){
        add(label);
        add(textField);
        add(buttonOK);
//        add(labelResult);
        add(textArea);


    }

    @Override
    public void errorHandling() {

    }

    @Override
    public void addReturnButton(ClientGUI clientGUI) {
        returnButton = new JButton("Return");
        add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientGUI.getjFrame().toFront();
            }
        });
    }
}
