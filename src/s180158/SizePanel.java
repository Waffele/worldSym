package s180158;
import s180158.animals.*;
import s180158.plants.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class SizePanel extends JFrame implements ActionListener, PropertyChangeListener {
    private World world;
    private Object lock;
    protected JPanel panel;

    private JFormattedTextField x;
    private JFormattedTextField y;
    private JLabel xL;
    private JLabel yL;
    private NumberFormat numberF;
    private int xVal=20,yVal=20;

    public SizePanel(World _world, Object _lock){
        world = _world;
        lock = _lock;
        panel = new JPanel();
        setPreferredSize(new Dimension(300, 100));
        setLocation(400,400);
        setTitle("Chose Size");
        setIconImage(null);

        xL = new JLabel("X");
        yL = new JLabel("Y");

        x = new JFormattedTextField(numberF);
        x.setValue(xVal);
        x.setColumns(10);
        x.addPropertyChangeListener("value", this);

        y = new JFormattedTextField(numberF);
        y.setValue(yVal);
        y.setColumns(10);
        y.addPropertyChangeListener("value", this);

        xL.setLabelFor(x);
        yL.setLabelFor(y);


        panel.add(xL);
        panel.add(x);

        panel.add(yL);
        panel.add(y);

        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world = new World(xVal,yVal);
                System.out.println("OK");
                panel.setVisible(false);
                synchronized (lock) {
                    setVisible(false);
                    lock.notify();
                }

            }
        });
        addPropertyChangeListener(this);
        panel.add(ok);

        add(panel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }



    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == x) {
            xVal = (int) x.getValue();
        } else if (source == y) {
            yVal = (int) y.getValue();
        }
        requestFocus();

    }

    public World getWorld(){
        return world;
    }
}
