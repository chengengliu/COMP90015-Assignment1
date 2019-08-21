package gui.client;

import javax.swing.*;

public class ClientMainWindow extends ClientWindow{
    private JButton buttonSearch, buttonAdd, buttonDelete;
    ClientMainWindow(String nameSearch, String nameAdd, String nameDelete){
        super();
        buttonSearch = new JButton(nameSearch);
        buttonAdd = new JButton(nameAdd);
        buttonDelete = new JButton(nameDelete);

        super.buttons.put(nameSearch, buttonSearch);
        super.buttons.put(nameAdd, buttonAdd);
        super.buttons.put(nameDelete, buttonDelete);

        setup();
    }
    private  void setup(){
        setBounds();
        setupFrame();
        setVisible();
    }
    private void setupFrame(){
        super.frame.setSize(500,600);
        super.frame.add(buttonSearch);
    }
    private void setVisible(){
        super.frame.setLayout(null);
        super.frame.setVisible(true);
    }
    private void setBounds(){
        buttonSearch.setBounds(130,100,100,40);
    }

}
