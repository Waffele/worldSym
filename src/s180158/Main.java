package s180158;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class Main {

    private static Object lock = new Object();


    public static void main(String[] args) {

        World world = null;
        SizePanel sizePanel = new SizePanel(world,lock);

        Thread t = new Thread() {
            public void run() {
                synchronized(lock) {
                    while (sizePanel.isVisible())
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    if(sizePanel.getWorld()!=null){
                        MyPanel panel = new MyPanel(sizePanel.getWorld());
                        new MyFrame(panel);
                    }else{
                        System.exit(0);
                    }
                }
            }
        };
        t.start();

        sizePanel.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent arg0) {
                synchronized (lock) {
                    sizePanel.setVisible(false);
                    lock.notify();
                }
            }

        });

        try{

            t.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }







    }
}
