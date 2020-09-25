package s180158.animals;

import s180158.Animal;
import s180158.MyPoint;
import s180158.Organism;
import s180158.World;
import static s180158.ConstValues.*;
import java.util.Random;

import java.awt.*;

public class Turtle extends Animal {
    public Turtle(){}
    public Turtle(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public Turtle(World _world, MyPoint _field) {
        super(_world, _field, TURTLE_STR, TURTLE_INI, TURTLE_SYM, DEFAULT_ANIMAL_REPRODUCE_CHANCE);
    }

    @Override
    public Color getColor() {
        return new Color(0, 51, 0);
    }

    @Override
    public Boolean isTheSameKind(Organism neighbour) {
        return neighbour instanceof Turtle;
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new Turtle(world,field,strength,initiative,symbol,chanceToSpread);
    }

    @Override
    public int avoidAttack(Organism attacker) {
        if (attacker.getStrength() < 5) {
            System.out.println("Turtle hides in shell at " + field);
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public void nextMove() {
        Random rand = new Random();
        int i = rand.nextInt(4);
        if (i == 0) {
            i= rand.nextInt(4);
            move(1,i);
        }
        else {
            System.out.println("Turtle stays at " + field);
        }
    }
}
