package gui.client;

import javax.swing.*;

public class ClientSearchPage extends ClientFunctionalPage implements  PageFunction{
    JTextArea textArea;
    public ClientSearchPage(String function, ClientGUI clientGUI){
        super(clientGUI);
        label.setText("Please enter the word you want to" + function + ":");
        initialiseWindow();

        addReturnButton(clientGUI);
        add(returnButton);
        setVisible(true);
    }
    @Override
    public void initialiseWindow(){
        add(label);
        addTextOnField("Please enter the word here:");
        add(buttonOK);
    }

    @Override
    public void errorHandling() {

    }

}
