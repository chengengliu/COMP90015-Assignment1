package gui.client;

import javax.swing.*;
import java.awt.*;

public class ResponsePage extends JFrame {
    String response;
    JTextArea jTextArea ;
    JPanel jPanel;
    public ResponsePage(String response){
        this.response = response;
        setLayout(new FlowLayout());
        jTextArea = new JTextArea(this.response);
        showResponse();
    }
    private void showResponse(){
        jTextArea.setSize(300,300);
        jTextArea.setBounds(200,200,300,30);
        setContentPane(jPanel);
        add(jTextArea);
        setVisible(true);
    }
}
