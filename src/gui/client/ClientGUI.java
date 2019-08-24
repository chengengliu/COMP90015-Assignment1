package gui.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public ClientGUI() {
        buttons = new HashMap<String, JButton>();
        buttons.put("Search", buttonSearch);
        buttons.put("Add", buttonAdd);
        buttons.put("Delete", buttonDelete);
    }

    public static void main (String args[]){
        ClientGUI clientGUI = new ClientGUI();
        clientGUI.setFucntions(clientGUI);
        clientGUI.addListener(clientGUI);
    }


    public void addListener(ClientGUI clientGUI){
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
        jFrame = new JFrame("HelloWorld");
        jFrame.setSize(400,400);
//        clientGUI.panelMain.setBounds(0,0,400,400);
        jFrame.setContentPane(clientGUI.panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.pack(); // Set up everything floating to the size.
        jFrame.setVisible(true);
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
