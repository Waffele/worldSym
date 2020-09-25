package s180158;

import s180158.plants.*;
import s180158.animals.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

public class MyPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
    public World world;
    public MyPanel(World _world) {
        world = _world;
        setPreferredSize(new Dimension(1200, 800));

        addButtons();
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawTable(world,g2d);
        printColors(g2d);
        printLabels(g2d);
        fillTable(g2d);
    }
    private void addButtons() {

        JButton nextT = new JButton("Next turn");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");

        nextT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Next turn");
                world.nextRound(-1);
                repaint();
                requestFocus();
            }
        });

        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save button");
                world.saveToFile();
                requestFocus();
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Load button");
                world.loadGame();
                repaint();
                requestFocus();
            }
        });


        nextT.setPreferredSize(new Dimension(100, 50));
        save.setPreferredSize(new Dimension(100, 50));
        load.setPreferredSize(new Dimension(100, 50));


        add(nextT);
        add(save);
        add(load);

        nextT.setVisible(true);
        save.setVisible(true);
        load.setVisible(true);
    }

    public void drawTable(World world,Graphics2D g2d){
        for(int i=0;i<world.getHeight();i++){
            for(int j=0;j<world.getWidth();j++){
                Rectangle2D rectangle = new Rectangle2D.Double( (600/world.getWidth())*j+5,(600/world.getHeight())*i+70,(600/world.getWidth()),  (600/world.getHeight()));
                g2d.draw(rectangle);
            }
        }
        requestFocusInWindow();
    }
    private void printLabels(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN,25));
        g.drawString("Grass", 700, 100);
        g.drawString("Guarana", 700, 150);
        g.drawString("Sosnowski's Borscht", 700, 200);
        g.drawString("Sonchus",700,250);
        g.drawString("Wolf Berries",700,300);
        g.drawString("Antylope",700,350);
        g.drawString("CyberSheep", 700, 400);
        g.drawString("Fox",700,450);
        g.drawString("Sheep",700,500);
        g.drawString("Turtle",700,550);
        g.drawString("Wolf",700,600);
        if(world.human != null && world.human.isAbilityActive()){
            g.setColor(Color.RED);
        }
        g.drawString("Human",700,650);
        g.setColor(Color.BLACK);
    }
    private void printColors(Graphics g){
        int a=670;
        g.setColor(new Grass().getColor());
        g.fillRect(a, 75, 30,30);
        g.setColor(new Guarana().getColor());
        g.fillRect(a, 125, 30,30);
        g.setColor(new SosnowskiBorscht().getColor());
        g.fillRect(a, 175, 30,30);
        g.setColor((new Sonchus().getColor()));
        g.fillRect(a,225,30,30);
        g.setColor(new Wolfberry().getColor());
        g.fillRect(a, 275, 30,30);
        g.setColor(new Antelope().getColor());
        g.fillRect(a, 325, 30,30);
        g.setColor(new CyberSheep().getColor());
        g.fillRect(a, 375, 30,30);
        g.setColor(new Fox().getColor());
        g.fillRect(a, 425, 30,30);
        g.setColor(new Sheep().getColor());
        g.fillRect(a, 475, 30,30);
        g.setColor(new Turtle().getColor());
        g.fillRect(a, 525, 30,30);
        g.setColor(new Wolf().getColor());
        g.fillRect(a, 575, 30,30);
        g.setColor(new Man().getColor());
        g.fillRect(a,625, 30,30);

    }
    private void fillTable(Graphics g){

        Organism field =null;
        for (int i = 0; i < world.getHeight(); i++) {
            for(int j=0;j< world.getWidth();j++)
            {
                field = world.getOrganismFromPos(new MyPoint(i,j));

                if (field == null) {

                }
                else {
                    g.setColor(field.getColor());
                    g.fillRect((600/world.getWidth() )*j +6+(600/world.getWidth()/4) , (600/world.getHeight() )*i +71+(600/world.getHeight()/4)
                            ,(600/world.getWidth())-(600/world.getWidth()/2), (600/world.getHeight())-(600/world.getHeight()/2));
                }
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.print("key pressed ");
        if(world.human == null){
            return;
        }
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:

                System.out.println("UP");
                world.nextRound(0);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                world.nextRound(2);
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                world.nextRound(1);
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                world.nextRound(3);
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("ENTER");
                world.startManAbility();
                break;

        }
        repaint();
        requestFocus();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println("Mouse clicked Y: "+mouseEvent.getY()+" X: "+mouseEvent.getX());
        if(mouseEvent.getY()>70 && mouseEvent.getX() > 5 && mouseEvent.getX() < 607 && mouseEvent.getY() <670){
            ChooseOrganism cO = new ChooseOrganism(this,world,new MyPoint(mouseEvent.getX(),mouseEvent.getY()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
