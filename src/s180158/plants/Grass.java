package s180158.plants;

import s180158.MyPoint;
import s180158.Organism;
import s180158.Plant;
import s180158.World;
import static s180158.ConstValues.*;

import java.awt.*;

public class Grass extends Plant {
    public Grass(){}
    public Grass(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public Grass(World _world, MyPoint _field) {
        super(_world, _field, GRASS_STR, DEFAULT_PLANT_INIT, GRASS_SYM, GRASS_REP);
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new Grass(world,field);
    }
}
