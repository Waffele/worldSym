package s180158;

import java.awt.*;
import java.util.Random;

public abstract class Animal implements Organism{
    protected MyPoint field;
    protected int strength,initiative;
    protected double chanceToSpread;
    protected char symbol;
    protected World world;
    public Animal(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread ){
        world=_world;
        symbol=_symbol;
        chanceToSpread=_chanceToSpread;
        strength=_strength;
        initiative=_initiative;
        field=_field;
    }

    protected Animal() {
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getInitiative() {
        return initiative;
    }

    @Override
    public MyPoint getPosition() {
        return field;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public double getChanceToSpread() {
        return chanceToSpread;
    }

    @Override
    public String toString() {
        return symbol+"";
    }

    @Override
    public MyPoint getEmptyPosition() {
        MyPoint newPoint = new MyPoint(field);
        if (newPoint.getX() > 0) {
            newPoint.setLocation(newPoint.getX() - 1,newPoint.getY());
            if (world.getOrganismFromPos(newPoint) == null) {
                return newPoint;
            }
            newPoint.setLocation(newPoint.getX() + 1,newPoint.getY());
        }
        if (newPoint.getY() > 0) {
            newPoint.setLocation(newPoint.getX() ,newPoint.getY() - 1);
            if (world.getOrganismFromPos(newPoint) == null) {
                return newPoint;
            }
            newPoint.setLocation(newPoint.getX() ,newPoint.getY() + 1);
        }
        if (newPoint.getY() < world.getWidth()-1) {
            newPoint.setLocation(newPoint.getX() ,newPoint.getY() + 1);
            if (world.getOrganismFromPos(newPoint) == null) {
                return newPoint;
            }
            newPoint.setLocation(newPoint.getX() ,newPoint.getY() - 1);
        }
        if (newPoint.getX() < world.getHeight()-1) {
            newPoint.setLocation(newPoint.getX() + 1,newPoint.getY());
            if (world.getOrganismFromPos(newPoint) == null) {
                return newPoint;
            }
            newPoint.setLocation(newPoint.getX() - 1,newPoint.getY());
        }
        return newPoint;
    }

    @Override
    public void setStrength(int _strength) {
        strength= _strength;
    }

    @Override
    public void setInitiative(int _initiative) {
        initiative = _initiative;
    }

    @Override
    public void nextMove() {
        Random rand = new Random();
        int i = rand.nextInt(4);
        move(1,i);
    }

    @Override
    public void collision(Organism attacker) {

    }

    @Override
    public int avoidAttack(Organism attacker) {
        return 0;
    }
    void reproduce(Organism partner){
        MyPoint newLife = this.getEmptyPosition();
        System.out.println("reproducing from" + field + " at " + partner.getPosition());
        if (newLife.equals(field)) {
            newLife = partner.getEmptyPosition();
        }
        if (newLife.equals(partner.getPosition())) {
            System.out.println("no space for new life at " + partner.getPosition());
            return; }
        else {
            System.out.println("newLife at "+newLife);
            Organism org = getCopy(newLife);
            world.addOrganism(org);
        }
    }
    public MyPoint chooseDirection(int reach,int i){
        MyPoint newPoint = new MyPoint(field);
        switch (i) {
            case 0:
                if (field.getX() > reach-1)
                    newPoint.setLocation(newPoint.getX() - reach,newPoint.getY());
                else {
                    System.out.println("Blocked at edges at " + field);
                    return newPoint;
                }
                break;
            case 1:
                if (field.getY() < (world.getWidth() - reach))
                    newPoint.setLocation(newPoint.getX() ,newPoint.getY() + reach);
                else {
                    System.out.println("Blocked at edges at " + field);
                    return newPoint;
                }
                break;
            case 2:
                if (field.getX() < (world.getHeight() - reach))
                    newPoint.setLocation(newPoint.getX()+ reach ,newPoint.getY() );
                else {
                    System.out.println("Blocked at edges at " + field);
                    return newPoint;
                }
                break;
            case 3:
                if (field.getY() > reach-1)
                    newPoint.setLocation(newPoint.getX(),newPoint.getY()- reach );
                else {
                    System.out.println("Blocked at edges at " + field);
                    return newPoint;
                }
                break;
        }

        return newPoint;
    }
    public void move(int reach, int i){



        System.out.print(this.toString()+" ");
        MyPoint newPoint = chooseDirection(reach,i);
        if(newPoint.equals(field)){
            System.out.println("stays");
            return;
        }

        Organism neighbour = world.getOrganismFromPos(newPoint);
        if (neighbour!= null) {
            if (isTheSameKind(neighbour)) {
                reproduce(neighbour);
            }
            else {
                if (world.collision(this, newPoint)) {
                    field = newPoint;
                }
            }
        }
        else {
            System.out.println("move form " + field + " to " + newPoint);
            field = newPoint;
        }
    }
    public abstract Boolean isTheSameKind(Organism neighbour);
}
