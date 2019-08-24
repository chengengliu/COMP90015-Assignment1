package gui.client;

//import process.ProcessData;

public class ClientDeletePage extends  ClientFunctionalPage {

    public ClientDeletePage(String function, ClientGUI clientGUI){
        super(clientGUI);
        label.setText("Please enter the word you want to " + function +":");
        initialiseWindow();

        addReturnButton(clientGUI);
        add(super.returnButton);

        setVisible(true);
    }
    @Override
    public void initialiseWindow(){
        add(label);
        addTextOnField("Please enter the word here:");
        add(buttonOK);
    }
//    @Override
//    public ProcessData getInputClient(){
//        ProcessData processData = new ProcessData();
//
//
//
//        return processData;
//    }
}
