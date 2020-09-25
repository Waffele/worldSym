package s180158;

import s180158.MyPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame {

    MyPanel panel;
    public MyFrame(MyPanel _panel) {
        super("WORLD");
        panel = _panel;
        setSize(1400,1000);
        setLocation(350,150);

        add(panel);

        pack();
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }


}
