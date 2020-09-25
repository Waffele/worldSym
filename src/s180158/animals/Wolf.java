package s180158.animals;

import s180158.Animal;
import s180158.MyPoint;
import s180158.Organism;
import s180158.World;
import static s180158.ConstValues.*;

import java.awt.*;

public class Wolf extends Animal {

    public Wolf(){}


    public Wolf(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public Wolf(World _world, MyPoint _field) {
        super(_world, _field,WOLF_STR , WOLF_INI, WOLF_SYM, DEFAULT_ANIMAL_REPRODUCE_CHANCE);
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public Boolean isTheSameKind(Organism neighbour) {
        return neighbour instanceof Wolf;
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new Wolf(world,field,strength,initiative,symbol,chanceToSpread);
    }
}
