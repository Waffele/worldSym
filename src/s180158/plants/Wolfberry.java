package s180158.plants;
import s180158.MyPoint;
import s180158.Organism;
import s180158.Plant;
import s180158.World;

import java.awt.*;

import static s180158.ConstValues.*;

public class Wolfberry extends Plant {
    public Wolfberry(){}
    public Wolfberry(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public Wolfberry(World _world, MyPoint _field) {
        super(_world, _field, WOLFBERRY_STR, DEFAULT_PLANT_INIT, WOLFBERRY_SYM, WOLFBERRY_REP);
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println(attacker.toString() + " ate wolfberries and died at"+field.toString());
        world.kill(attacker);
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new Wolfberry(world,field);
    }
}
