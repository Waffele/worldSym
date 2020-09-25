package s180158.animals;

import s180158.Animal;
import s180158.MyPoint;
import s180158.Organism;
import s180158.World;

import static s180158.ConstValues.*;

import java.awt.*;

public class Man extends Animal {
    //
    // IMPORTANT: CHANCE TO SPEED IS FOR OPERATING ABILITY
    //
    public Man(){}
    public Man(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }
    public Man(World _world, MyPoint _field) {
        super(_world, _field, MAN_STR, MAN_INI, MAN_SYM, 0);
    }

    public void nextMove(int dir) {
        move(1,dir);
        if (chanceToSpread > 0)chanceToSpread--;
    }

    @Override
    public Color getColor() {
        return new Color(204, 204, 255);
    }
    public void startAbility(){
        if (chanceToSpread == 0) {
            chanceToSpread = 10;
            System.out.println("Alzure's shield activated");
        }
        else if (chanceToSpread > 5) {

            System.out.println("(Alzure's shield is active for "+ (chanceToSpread-5) +" round(s))");
        }
        else {
            System.out.println("(Alzure's shield will be available in "+ chanceToSpread+" round(s))");
        }
    }
    public boolean isAbilityActive(){
        if (chanceToSpread > 5) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public int avoidAttack(Organism attacker) {
        if (chanceToSpread > 5) {
            System.out.println("Alzure's shield defended Man");
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public Boolean isTheSameKind(Organism neighbour) {
        return false;
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return null;
    }
}
