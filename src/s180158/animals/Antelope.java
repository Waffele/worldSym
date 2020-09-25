package s180158.animals;

import s180158.Animal;
import s180158.MyPoint;
import s180158.Organism;
import s180158.World;
import static s180158.ConstValues.*;
import java.util.Random;

import java.awt.*;

public class Antelope extends Animal {
    public Antelope(){}
    public Antelope(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread ){
        super(_world,_field,_strength,_initiative,_symbol,_chanceToSpread);
    }
    public Antelope(World _world, MyPoint _field){
        super(_world,_field, ANTELOPE_STR ,ANTELOPE_INI,ANTELOPE_SYM,DEFAULT_ANIMAL_REPRODUCE_CHANCE);
    }
    @Override
    public Boolean isTheSameKind(Organism neighbour) {
        return neighbour instanceof Antelope;
    }

    @Override
    public Organism getCopy(MyPoint field) {

        return new Antelope(world,field,strength,initiative,symbol,chanceToSpread);
    }

    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }

    @Override
    public int avoidAttack(Organism attacker) {
        Random rand = new Random();
        int i = rand.nextInt(2);
        if (i ==0) {
            MyPoint newPoint = getEmptyPosition();
            if (newPoint == new Point(-1, -1)) {
                System.out.println("Antelope has nowhere to run at "+getPosition());
                return 0;
            }
            else {
                field = newPoint;
                System.out.println("Antelope runs from " + field + " to " + newPoint );
                return 2;
            }

        }
	else {
            return 0;
        }
    }
    @Override
    public void nextMove(){
        Random rand = new Random();
        int i = rand.nextInt(4);
        move(2,i);
    }
}
