package gui.client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientWindow {
    JFrame frame;
    protected HashMap<String, JButton> buttons;
    ClientWindow(){
        frame = new JFrame();
        buttons = new HashMap<>();
    }
}
