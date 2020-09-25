package s180158;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {

    private int counter = 0;
    private String userInput = "";
    private final String secret = "secret";

    public Key() {

    }

    @Override
    public void keyPressed(KeyEvent evt) {
        char c = evt.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

    @Override
    public void keyTyped(KeyEvent evt) {

    }
}
