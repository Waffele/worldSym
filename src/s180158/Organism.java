package s180158;

import java.awt.*;
public interface Organism {

    int getStrength();
    int getInitiative();
    MyPoint getPosition();
    char getSymbol();
    double getChanceToSpread();
    Organism getCopy(MyPoint field);
    MyPoint getEmptyPosition();
    void setStrength(int strength);
    void setInitiative(int initiative);
    void nextMove();
    String toString();
    Color getColor();
    void collision(Organism attacker);
    int avoidAttack(Organism attacker);


}

