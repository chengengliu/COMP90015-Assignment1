package gui.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientAddPage extends ClientFunctionalPage{
    JTextArea textArea;
    JTextField jTextField1;
    JTextField jTextField2;
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
        update();
    }
    @Override
    public void initialiseWindow(){
        add(label);
//        add(textField); // Enter the word
        jTextField1 = addTextOnField("Please enter the word here");  // Enter the word you want to add.
//        add(jTextField1);
        jTextField2 = addTextOnField("Please enter the meaning here"); // Enter the meaning of the word.
//        add(jTextField2);
        add(buttonOK);
        add(textArea);
    }

    @Override
    public void errorHandling() {

    }
    private void update(){
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jTextField1.getText());
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jTextField2.getText());
            }
        });
    }
}
