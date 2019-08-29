package gui.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Response {
    private JPanel panel;
    private JTextArea responseHeader;
    private JTextArea responseMessage;
    private JButton buttonReturn;
    private JFrame jFrame;
    private String response;
    private JOptionPane jOptionPane;
    private WindowAdapter adapter;
    private ClientGUI clientGUI;
    public Response(String output,ClientGUI clientGUI){
        this.response = output;
        this.clientGUI = clientGUI;
        initialise();
        addResponse();
        prevenClosing();
        jFrame.setVisible(true);
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                clientGUI.getjFrame().toFront();
            }
        });
    }
    private void initialise(){
        jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(400,400);
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jFrame.setContentPane(this.panel);
        Image okImage = new ImageIcon(this.getClass().getResource("/resources/return.png")).getImage();
        buttonReturn.setIcon(new ImageIcon(okImage));

    }
    private void addResponse(){
        responseMessage.append("\n"+response);
    }
    private void prevenClosing(){
        jOptionPane = new JOptionPane();
        adapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int yes = jOptionPane.showConfirmDialog(jFrame,"Sure you want to close the client?","Exit",
                        jOptionPane.YES_NO_OPTION,jOptionPane.WARNING_MESSAGE);
                if (yes == jOptionPane.YES_OPTION){
                    clientGUI.printWriter.println("Shutdown");
                    clientGUI.printWriter.close();
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
            }
        };
        jFrame.addWindowListener(adapter);
    }
}
