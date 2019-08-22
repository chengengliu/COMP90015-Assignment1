package gui.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFunctionalPage extends JFrame {

    ClientFunctionalPage(String text){
        setLayout(new FlowLayout());
        JLabel labelDelete = new JLabel("The word you want to " + text + " :");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("OK");
        JLabel label = new JLabel("Resultt");
        add(labelDelete);
        add(textField);
        add(button);
        add(label);

        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(3);
        addReturnButton();
    }
    private void addReturnButton(){
        JButton returnButton = new JButton("Return");
        add(returnButton);
        returnButton.setBounds(20,20,20,20);
    }
}
