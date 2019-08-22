package gui.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFunctionalPage extends JFrame {
    JLabel label;
    JTextField textField;
    JButton buttonOK;
    JLabel labelResult;

    public ClientFunctionalPage(ClientGUI clientGUI){
        setLayout(new FlowLayout());
        label = new JLabel();
        textField = new JTextField(20);
        buttonOK = new JButton("OK");
        labelResult = new JLabel("Result");
        setSize(400, 400);
        setDefaultCloseOperation(3);
    }
    public void initialiseWindow(){
        add(label);
        add(textField);
        add(buttonOK);
        add(labelResult);
    }
}
