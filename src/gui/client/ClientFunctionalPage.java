/*
* Author: Chengeng Liu
* Student ID: 813174
* */
package gui.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ClientFunctionalPage extends JFrame implements PageFunction{
    JLabel label;
    JTextField textField;
    JButton buttonOK;
    JLabel labelResult;
    JButton returnButton;
    WindowAdapter adapter;
    JOptionPane jOptionPane;
    ClientGUI clientGUI;

    public ClientFunctionalPage(ClientGUI clientGUI){
        setLayout(new FlowLayout());
        label = new JLabel();
        textField = new JTextField(20);
        buttonOK = new JButton();
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.clientGUI = clientGUI;
        jOptionPane = new JOptionPane();
        adapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Hello");
                int yes = jOptionPane.showConfirmDialog(clientGUI.getjFrame(),"Sure you want to exit?","Exit",
                        jOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(yes==JOptionPane.YES_OPTION){
                    clientGUI.printWriter.write("Shutdown");
                    clientGUI.printWriter.close();
                    System.out.println("Exit");
                    try {
                        clientGUI.bufferedReader.close();
                        clientGUI.socket.close();
                    } catch (IOException ee){
                        ee.printStackTrace();
                    }
                    System.exit(0);
                }
                else {
                    System.out.println("Stay awake");
                }
//
            }
        };
        this.addWindowListener(adapter);

    }
    private void initialiseWindow(){
        add(label);
        add(textField);
        add(buttonOK);

//        add(labelResult);
    }

    /**
     *  Instantiate a new Response object and create the class with response message, rendering a pop-up page.
     * @param output: response message that needs to render.
     */
    public void response(String output){
        Response response = new Response(output,clientGUI);
    }

    /**
     * Add a return button on the page, with an action listener attached.
     * @param clientGUI: client side GUI
     */
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

    /**
     * This feature gives the client clearer intuition of what they need to do. The instruction will appear
     * within the jtextField. When the mouse is moving out of the text field, the message will disappear.
     * @param text Add the text on the text field.
     * @return a jTextField object
     */
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

    /**
     * Load Icon for return button and checked button.
     */
    public void loadIcon(){
        Image returnImage = new ImageIcon(this.getClass().getResource("/resources/return.png")).getImage();
        returnButton.setIcon(new ImageIcon(returnImage));
        Image okImage = new ImageIcon(this.getClass().getResource("/resources/checked.png")).getImage();
        buttonOK.setIcon(new ImageIcon(okImage));

    }

    /**
     * Set font.
     * @return
     */
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
