package gui.client;
//TODO: 1. Client GUI还需要美化和修改。 比如： 窗口大小调整，加上图片。
//TODO: 2. Client GUI 之间的联动也有问题， 应该是打开一个，之前的关闭，return 会关闭当前窗口，打开之前的窗口。
//TODO: 3. Server Side GUI

// Images credit: Icon made by Google (https://www.flaticon.com/authors/google) from www.flaticon.com

import javax.swing.*;
import java.awt.*;
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

    public void initialiseGUI(ClientGUI clientGUI) {
        jFrame = new JFrame("HelloDS");
        jFrame.setSize(600,600);
//        clientGUI.panelMain.setBounds(0,0,400,400);
        jFrame.setContentPane(clientGUI.panelMain);
        // Default close.
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.pack(); // Set up everything floating to the size.
        JOptionPane jOptionPane = new JOptionPane();
        WindowAdapter adapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Hello");
                int yes = jOptionPane.showConfirmDialog(jFrame,"Sure you want to exit?","Exit",
                        jOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(yes==JOptionPane.YES_OPTION){
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
//
            }
        };
        jFrame.addWindowListener(adapter);
        loadIcon();
        jFrame.setVisible(true);
    }

    public JFrame getjFrame() {
        return jFrame;
    }
    private void loadIcon(){
        Image searchImage = new ImageIcon(this.getClass().getResource("/resources/search.png")).getImage();
        buttonSearch.setIcon(new ImageIcon(searchImage));
        buttonSearch.setBounds(new Rectangle(10,10,450,450));

        Image addImage = new ImageIcon(this.getClass().getResource("/resources/add.png")).getImage();
        buttonAdd.setIcon(new ImageIcon(addImage));
        buttonAdd.setBounds(new Rectangle(10,10,250,250));

        Image deleteImage = new ImageIcon(this.getClass().getResource("/resources/delete.png")).getImage();
        buttonDelete.setIcon(new ImageIcon(deleteImage));
        buttonDelete.setBounds(new Rectangle(10,10,100,100));

    }
}
