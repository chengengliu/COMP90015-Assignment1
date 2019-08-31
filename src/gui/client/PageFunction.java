/*
* Author: Chengeng Liu
* Student ID: 813174
* */
package gui.client;

import javax.swing.*;

public interface PageFunction {
    public void errorHandling();
    public void addReturnButton(ClientGUI clientGUI);
    public JTextField addTextOnField(String text);
    public int addSecondConfirm();
}
