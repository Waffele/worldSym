package s180158.animals;

import s180158.Animal;
import s180158.MyPoint;
import s180158.Organism;
import s180158.World;
import static s180158.ConstValues.*;

import java.awt.*;

public class Sheep extends Animal {
    public Sheep(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public Sheep(World _world, MyPoint _field) {
        super(_world, _field, SHEEP_STR, SHEEP_INI, SHEEP_SYM, DEFAULT_ANIMAL_REPRODUCE_CHANCE);
    }

    public Sheep() {
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
    }

    @Override
    public Boolean isTheSameKind(Organism neighbour) {
        return neighbour instanceof Sheep;
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new Sheep(world,field,strength,initiative,symbol,chanceToSpread);
    }
}
