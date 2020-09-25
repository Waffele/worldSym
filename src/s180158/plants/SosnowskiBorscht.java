package s180158.plants;

import s180158.MyPoint;
import s180158.Organism;
import s180158.Plant;
import s180158.World;
import s180158.animals.CyberSheep;

import java.awt.*;

import static s180158.ConstValues.*;

public class SosnowskiBorscht extends Plant{
    public SosnowskiBorscht(){}
    public SosnowskiBorscht(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public SosnowskiBorscht(World _world, MyPoint _field) {
        super(_world, _field, SBORSCHT_STR, DEFAULT_PLANT_INIT, SBORSCHT_SYM, SBORSCHT_REP);
    }

    @Override
    public Color getColor() {
        return Color.PINK;
    }

    @Override
    public void nextMove() {
        killAllAround();
        super.nextMove();
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println(attacker.toString() + " ate Sosnowski's borscht and died at" + field.toString());
        world.kill(attacker);
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new SosnowskiBorscht(world,field);
    }
    public MyPoint getOccupiedPosition() {
        MyPoint newPoint= new MyPoint(field);

        if (newPoint.getX() > 0) {
            newPoint.setLocation(newPoint.getX() - 1,newPoint.getY());
            if (world.getOrganismFromPos(newPoint) != null && world.isAnimal(world.getOrganismFromPos(newPoint)) && !(world.getOrganismFromPos(newPoint) instanceof CyberSheep)) {
                return newPoint;
            }
            newPoint.setLocation(newPoint.getX() + 1,newPoint.getY());
        }
        if (newPoint.getY() > 0) {
            newPoint.setLocation(newPoint.getX(),newPoint.getY() - 1);
            if (world.getOrganismFromPos(newPoint) != null && world.isAnimal(world.getOrganismFromPos(newPoint))&& !(world.getOrganismFromPos(newPoint) instanceof CyberSheep)) {
                return newPoint;
            }
            newPoint.setLocation(newPoint.getX(),newPoint.getY() + 1);
        }
        if (newPoint.getY() < world.getWidth() - 1) {
            newPoint.setLocation(newPoint.getX(),newPoint.getY() + 1);
            if (world.getOrganismFromPos(newPoint) != null && world.isAnimal(world.getOrganismFromPos(newPoint))&& !(world.getOrganismFromPos(newPoint) instanceof CyberSheep)) {
                return newPoint;
            }
            newPoint.setLocation(newPoint.getX(),newPoint.getY() - 1);
        }
        if (newPoint.getX() < world.getHeight() - 1) {
            newPoint.setLocation(newPoint.getX() + 1,newPoint.getY());
            if (world.getOrganismFromPos(newPoint) != null && world.isAnimal(world.getOrganismFromPos(newPoint))&& !(world.getOrganismFromPos(newPoint) instanceof CyberSheep)) {
                return newPoint;
            }
            newPoint.setLocation(newPoint.getX() - 1,newPoint.getY());
        }

        return new MyPoint(-1, -1);
    }

    public void killAllAround(){
        world.drawWorld();
        Point posToKill = getOccupiedPosition();
        Organism toKill;
        while (!posToKill.equals(new Point(-1, -1)) ) {
            toKill = world.getOrganismFromPos(posToKill);
            System.out.println(toKill.toString() +posToKill.toString() + " went too close to Sosnowski's borscht at " + field.toString());
            world.kill(toKill);
            posToKill = getOccupiedPosition();
        }
    }
}
