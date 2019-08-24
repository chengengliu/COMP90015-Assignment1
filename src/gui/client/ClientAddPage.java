package gui.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ClientAddPage extends ClientFunctionalPage{
    JTextArea textArea;
    public ClientAddPage(String function, ClientGUI clientGUI){
        super(clientGUI);
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

    }
    @Override
    public void initialiseWindow(){
        add(label);
//        add(textField); // Enter the word
        addTextOnField("Please enter the word here");  // Enter the word you want to add.
        addTextOnField("Please enter the meaning here"); // Enter the meaning of the word.
        add(buttonOK);
        add(textArea);
    }

    @Override
    public void errorHandling() {

    }
}
