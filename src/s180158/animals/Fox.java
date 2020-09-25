package s180158.animals;

import s180158.Animal;
import s180158.MyPoint;
import s180158.Organism;
import s180158.World;

import static s180158.ConstValues.*;

import java.awt.*;
import java.util.Random;

public class Fox extends Animal {
    public Fox(){}
    public Fox(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public Fox(World _world, MyPoint _field) {
        super(_world, _field, FOX_STR, FOX_INI, FOX_SYM, DEFAULT_ANIMAL_REPRODUCE_CHANCE);
    }

    @Override
    public Boolean isTheSameKind(Organism neighbour) {
        return neighbour instanceof Fox;
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new Fox(world,field,strength,initiative,symbol,chanceToSpread);
    }

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }

    @Override
    public void nextMove() {
        int surrounded=0;
        Random rand = new Random();
        int i = rand.nextInt(4);
        MyPoint newMyPoint;
        do{
            if(surrounded == 32){
                System.out.println("Fox surrounded at"+ field.toString());
                return;
            }
            surrounded++;
            i=rand.nextInt(4);
            newMyPoint = chooseDirection(1,i);

        }while(goodSmell(world.getOrganismFromPos(newMyPoint)));

        move(1,i);
    }

    boolean goodSmell(Organism neighbour) {
        if (neighbour != null && neighbour.getStrength() > strength) {
            return true;
        }
        else {
            return false;
        }
    }
}
