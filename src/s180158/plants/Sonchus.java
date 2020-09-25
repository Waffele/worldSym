package s180158.plants;

import s180158.MyPoint;
import s180158.Organism;
import s180158.Plant;
import s180158.World;

import javax.sound.midi.Soundbank;
import java.awt.*;

import static s180158.ConstValues.*;

public class Sonchus extends Plant {
    public Sonchus(){}
    public Sonchus(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public Sonchus(World _world, MyPoint _field) {
        super(_world, _field, SONCHUS_STR, DEFAULT_PLANT_INIT, SONCHUS_SYM, SONCHUS_REP);
    }

    @Override
    public void nextMove() {
        reproduce();
        reproduce();
        reproduce();
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new Sonchus(world,field);
    }
}
