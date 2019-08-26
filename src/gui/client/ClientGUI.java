package gui.client;
//TODO:客户端先关闭时候，应该有个监听来切断socket。
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClientGUI {
    private JButton buttonSearch;
    private JPanel panelMain;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JFrame jFrame;
    private HashMap<String, JButton> buttons;

    public BufferedReader bufferedReader;
    private Socket socket;
    public PrintWriter printWriter;

    public ClientGUI(Socket socket, BufferedReader bufferedReader, PrintWriter printWriter) {
        this.socket = socket;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    public void addListener(ClientGUI clientGUI){
        buttons = new HashMap<String, JButton>();
        buttons.put("Search", buttonSearch);
        buttons.put("Add", buttonAdd);
        buttons.put("Delete", buttonDelete);

        Iterator iterator = buttons.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)iterator.next();
            // Add eventListener to link to other frames/pages.
            switch ((String)mapElement.getKey()){
                case "Delete":
                    ((JButton)mapElement.getValue()).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new ClientDeletePage("delete", clientGUI);
                        }
                    });
                    continue;
                case "Add":
                    ((JButton)mapElement.getValue()).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new ClientAddPage("add", clientGUI);
                        }
                    });
                    continue;
                case "Search":
                    ((JButton)mapElement.getValue()).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new ClientSearchPage("search",clientGUI);
                        }
                    });
                    continue;
            }
        }
    }

    public void setFucntions(ClientGUI clientGUI) {
        jFrame = new JFrame("HelloDS");
        jFrame.setSize(400,400);
//        clientGUI.panelMain.setBounds(0,0,400,400);
        jFrame.setContentPane(clientGUI.panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.pack(); // Set up everything floating to the size.

        jFrame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printWriter.write("Shutdown");
                printWriter.close();
                try {
                    bufferedReader.close();
                    socket.close();
                } catch (IOException ee){
                    ee.printStackTrace();
                }
                System.exit(0);
            }
        });
        jFrame.setVisible(true);
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
