package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ServerGUI implements ActionListener{
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private JFrame jFrame;
    private JPanel panel;
    private JLabel clientIP;
    private JLabel clientPort;
    private JLabel clientStatus;
    private JTextArea textIP;
    private JTextArea textPort;
    private JTextArea textStatus;
    private JButton buttonShutdown;
    public JTextArea serverStatus;
    private DicServer server;

    public ServerGUI(DicServer server){
        jFrame = new JFrame();
        this.server = server;
//        this.bufferedReader = bufferedReader;
//        this.printWriter = printWriter;
        initialise();
        buttonShutdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.serverShutdown();
            }
        });
    }
    public void initialise(){
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(600,600);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(this.panel);
    }

    public JFrame getFrame(){
        return this.jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){

    }
}
