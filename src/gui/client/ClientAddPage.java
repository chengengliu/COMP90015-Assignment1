package gui.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
//        add(textField); // Enter the word
        addText("Please enter the word here");  // Enter the word you want to add.
        addText("Please enter the meaning here"); // Enter the meaning of the word.
        add(buttonOK);
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
    private void addText(String text){
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
                meaning.setText(text);
            }
        });
        add(meaning);
    }
    private Font setFont(){
        Font font = new Font("Arial", Font.ITALIC,15);
        return font;
    }
}
