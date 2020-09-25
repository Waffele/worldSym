package s180158;

import java.awt.*;

import java.util.Random;

public abstract class Plant implements Organism {
    protected MyPoint field;
    protected int strength,initiative;
    protected double chanceToSpread;
    protected char symbol;
    protected World world;

    public Plant(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread ) {
        world=_world;
        symbol=_symbol;
        chanceToSpread=_chanceToSpread;
        strength=_strength;
        initiative=_initiative;
        field=_field;
    }

    protected Plant() {
    }

    @Override
    public String toString() {
        return symbol+"";
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
    public void setStrength(int _strength) {
        strength = _strength;
    }

    @Override
    public void setInitiative(int _initiative) {
        initiative= _initiative;
    }

    @Override
    public void nextMove() {
        reproduce();
    }

    @Override
    public void collision(Organism attacker) {

    }

    @Override
    public int avoidAttack(Organism attacker) {
        return 0;
    }

    public void reproduce() {
        MyPoint newLife = getEmptyPosition();
        System.out.println(this.getSymbol() + " Spread attempt at " + field.toString());

        Random rand = new Random();
        int chance = rand.nextInt(101);
        if (chance < chanceToSpread) {
            if (!newLife.equals(new MyPoint(-1, -1))) {
                System.out.println(this.getSymbol() + " is spreading to " + newLife.toString() + " from " + field.toString());
                Organism org = getCopy(newLife);
                world.addOrganism(org);
            }
            else {
                System.out.println("no space for new plant at " + field.toString() );
            }
        }
        else {
            System.out.println("Attempt failed");
        }
    }
    @Override
    public MyPoint getEmptyPosition() {
        MyPoint newPoint= new MyPoint(field);
        Random rand = new Random();
        int i = rand.nextInt(4);
        for (int j = 0; j < 4; j++, i++) {
            switch (i % 4) {
                case 0:
                    if (newPoint.getX() > 0) {
                        newPoint.setLocation(newPoint.getX() - 1 , newPoint.getY());
                        if (world.getOrganismFromPos(newPoint) == null) {
                            return newPoint;
                        }
                        newPoint.setLocation(newPoint.getX() + 1 , newPoint.getY());
                    }
                    break;
                case 1:
                    if (newPoint.getY() > 0) {
                        newPoint.setLocation(newPoint.getX() , newPoint.getY()-1);
                        if (world.getOrganismFromPos(newPoint) == null) {
                            return newPoint;
                        }

                        newPoint.setLocation(newPoint.getX() , newPoint.getY()+1);
                    }
                    break;
                case 2:
                    if (newPoint.getY() < world.getWidth() - 1) {
                        newPoint.setLocation(newPoint.getX() , newPoint.getY()+1);
                        if (world.getOrganismFromPos(newPoint) == null) {

                            return newPoint;
                        }
                        newPoint.setLocation(newPoint.getX() , newPoint.getY()-1);
                    }
                    break;
                case 3:
                    if (newPoint.getX() < world.getHeight() - 1) {
                        newPoint.setLocation(newPoint.getX() + 1 , newPoint.getY());
                        if (world.getOrganismFromPos(newPoint) == null) {
                            return newPoint;
                        }
                        newPoint.setLocation(newPoint.getX() - 1 , newPoint.getY());
                    }
                    break;
            }
        }

        return new MyPoint(-1, -1);
    }



}
