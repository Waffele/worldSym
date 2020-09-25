package s180158.plants;

import s180158.MyPoint;
import s180158.Organism;
import s180158.Plant;
import s180158.World;
import static s180158.ConstValues.*;

import java.awt.*;

public class Guarana extends Plant {
    public Guarana(){}
    public Guarana(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public Guarana(World _world, MyPoint _field) {
        super(_world, _field, GUARANA_STR, DEFAULT_PLANT_INIT, GUARANA_SYM, GUARANA_REP);
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new Guarana(world,field);
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public void collision(Organism attacker) {
        System.out.println(attacker.toString() + " ate Guarana and gained 3 strength");
        attacker.setStrength(attacker.getStrength() + 3);
    }
}
