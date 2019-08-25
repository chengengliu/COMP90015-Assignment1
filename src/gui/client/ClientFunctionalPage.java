package gui.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ClientFunctionalPage extends JFrame implements PageFunction{
    JLabel label;
    JTextField textField;
    JButton buttonOK;
    JLabel labelResult;
    JButton returnButton;

    public ClientFunctionalPage(ClientGUI clientGUI){
        setLayout(new FlowLayout());
        label = new JLabel();
        textField = new JTextField(20);
        buttonOK = new JButton("OK");
//        labelResult = new JLabel("Result");
        setSize(400, 400);
        setDefaultCloseOperation(3);
    }
    private void initialiseWindow(){
        add(label);
        add(textField);
        add(buttonOK);
//        add(labelResult);
    }

    @Override
    public void addReturnButton(ClientGUI clientGUI) {
        returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientGUI.getjFrame().toFront();
            }
        });
    }

    @Override
    public JTextField addTextOnField(String text) {

        JTextField meaning = new JTextField(text, 20);
        meaning.setFont(setFont());
        meaning.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                meaning.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                // Nothing
//                meaning.setText(text);
            }
        });
        add(meaning);
        return meaning;
    }

    public Font setFont(){
        Font font = new Font("Arial", Font.ITALIC, 15);
        return font;
    }

    @Override
    public void errorHandling() {

    }

    // Default is yes. YES_OPTION is 0.
    @Override
    public int addSecondConfirm(){
        return 0;
    }



}
