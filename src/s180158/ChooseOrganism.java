package s180158;
import s180158.animals.*;
import s180158.plants.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChooseOrganism extends JFrame {
    private World world;
    private MyPoint point;
    private JList<String> orgList;
    private JPanel panel;

    public ChooseOrganism(JPanel _panel,World _world, MyPoint _point){
        world = _world;
        point = new MyPoint(((int)_point.getY()-70)/(600/world.getHeight()),((int)_point.getX()-5)/(600/world.getHeight()));
        panel = _panel;
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Antylope");
        model.addElement("CyberSheep");
        model.addElement("Fox");
        model.addElement("Sheep");
        model.addElement("Turtle");
        model.addElement("Wolf");
        model.addElement("Grass");
        model.addElement("Guarana");
        model.addElement("Sonchus");
        model.addElement("SosnowskisBorscht");
        model.addElement("Wolfberry");

        if(world.getOrganismFromPos(point)!=null){
            world.kill(world.getOrganismFromPos(point));
        }

        orgList = new JList<>(model);
        orgList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orgList.addListSelectionListener(listSelectionEvent -> {
            if(orgList.getValueIsAdjusting()){
                switch(orgList.getSelectedValue()){
                    case "Antylope":
                        world.addOrganism(new Antelope(world, point));
                        break;
                    case "CyberSheep":
                        world.addOrganism(new CyberSheep(world, point));
                        break;
                    case "Fox":
                        world.addOrganism(new Fox(world, point));
                        break;
                    case "Sheep":
                        world.addOrganism(new Sheep(world, point));
                        break;
                    case "Turtle":
                        world.addOrganism(new Turtle(world, point));
                        break;
                    case "Wolf":
                        world.addOrganism(new Wolf(world, point));
                        break;
                    case "Guarana":
                        world.addOrganism(new Guarana(world, point));
                        break;
                    case "Grass":
                        world.addOrganism(new Grass(world, point));
                        break;
                    case "Sonchus":
                        world.addOrganism(new Sonchus(world, point));
                        break;
                    case "SosnowskisBorscht":
                        world.addOrganism(new SosnowskiBorscht(world, point));
                        break;
                    case "Wolfberry":
                        world.addOrganism(new Wolfberry(world, point));
                        break;
                }
                world.updateOrganisms();

            }

            panel.repaint();
            SwingUtilities.getWindowAncestor(orgList).setVisible(false);
        });

        add(orgList);
        setTitle("Choose Organism");
        orgList.setSize(400,400);
        setSize(400,400);
        setLocation(400,400);
        setVisible(true);
    }

}
