package test;
import javax.swing.*;
import java.awt.*;

public class Simple extends JFrame {
    JFrame j;
    Simple(){
        JButton button = new JButton("Hello");
        button.setBounds(130,100,100,40);
        button.setBackground(Color.blue);
        add(button);
        setSize(400,500);
        setLayout(null);
        setVisible(true);
    }
}
