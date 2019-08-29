package server;
//TODO: 接下来的实现目标： 服务器能否返回信息到Client端. 已完成
//TODO: 以及服务器端的信息能否收到？正在写。
//TODO: 字典能否顺利读写？？？


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ServerGUI implements ActionListener{
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private JFrame jFrame;
    private JPanel panel;
    private JTextArea textIP;
    private JTextArea textPort;
    private JTextArea textStatus;
    private JButton buttonShutdown;
    public JTextArea serverStatus;
    private JLabel clientIP;
    private JLabel clientPort;
    private JLabel clientStatus;
    private DicServer server;
    private WindowAdapter adapter;
    private JOptionPane jOptionPane;
    private JScrollPane scrollPane;

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
        jFrame.setContentPane(this.panel);
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        jOptionPane = new JOptionPane();
        adapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Hello");
                int yes = jOptionPane.showConfirmDialog(jFrame,"Sure you want to exit?","Exit",
                        jOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(yes==JOptionPane.YES_OPTION){
                    System.out.println("Exit");
                    try {
                        server.serverShutdown();
                    } catch (Exception ee){
                        ee.printStackTrace();
                    }
                }
                else {
                    System.out.println("Stay awake");
                }
//
            }
        };
        jFrame.addWindowListener(adapter);

    }
    public void updateIpAndPort(int order){
        textIP.append("\n"+server.getIpAndPort().get(order).getKey()); // Key is ip
        System.out.println(server.getIpAndPort().get(order).getKey());
        textPort.append("\n"+server.getIpAndPort().get(order).getValue()); //value is port
        textStatus.append("\n");
    }
    public void updateClientStatus(String status){
        textIP.append("\n");
        textPort.append("\n");
        textStatus.append("\n" + status);
    }

    public JFrame getFrame(){
        return this.jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
