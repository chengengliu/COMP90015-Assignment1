package gui.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        buttonOK = new JButton();
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
        returnButton = new JButton();
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientGUI.getjFrame().toFront();
                ClientFunctionalPage.super.dispose(); // Close the current window and return back to main page.
            }
        });
        returnButton.setBounds(new Rectangle(3,3,250,250));

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
    public void loadIcon(){
        Image returnImage = new ImageIcon(this.getClass().getResource("/resources/return.png")).getImage();
        returnButton.setIcon(new ImageIcon(returnImage));
        Image okImage = new ImageIcon(this.getClass().getResource("/resources/checked.png")).getImage();
        buttonOK.setIcon(new ImageIcon(okImage));

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
