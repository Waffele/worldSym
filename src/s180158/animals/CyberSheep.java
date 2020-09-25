package s180158.animals;

import s180158.MyPoint;
import s180158.Organism;
import s180158.World;
import s180158.plants.SosnowskiBorscht;

import static s180158.ConstValues.*;

import java.awt.*;
import java.awt.geom.Point2D;

public class CyberSheep extends Sheep {
    public CyberSheep(){}
    public CyberSheep(World _world, MyPoint _field, int _strength, int _initiative, char _symbol, double _chanceToSpread) {
        super(_world, _field, _strength, _initiative, _symbol, _chanceToSpread);
    }

    public CyberSheep(World _world, MyPoint _field) {
        super(_world, _field,CYBERSHEEP_STR,CYBERSHEEP_INI,CYBERSHEEP_SYM,DEFAULT_ANIMAL_REPRODUCE_CHANCE);
    }

    @Override
    public Boolean isTheSameKind(Organism neighbour) {
        return neighbour instanceof CyberSheep;
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }

    @Override
    public void nextMove() {
        MyPoint newMyPoint = findBorscht();
        if(newMyPoint.equals(new MyPoint(-1,-1))){
            super.nextMove();
        }else{
            if(field.getX()-newMyPoint.getX() != 0){
                if(field.getX()-newMyPoint.getX() < 0){
                    move(1,2);
                }else{
                    move(1,0);

                }
            }else{
                if(field.getY()-newMyPoint.getY() < 0){
                    move(1,1);
                }else{
                    move(1,3);

                }
            }
        }
    }

    MyPoint findBorscht(){
        MyPoint newMyPoint = new MyPoint(-1,-1);
        for(int i=0;i<world.getHeight();i++){
            for(int j=0;j<world.getWidth();j++){
                if(world.getOrganismFromPos(new MyPoint(i,j)) instanceof SosnowskiBorscht){
                    if(newMyPoint.equals(new MyPoint(-1,-1))){
                        newMyPoint = new MyPoint(i,j);
                    }else {
                        if(Point2D.distance(i,j,field.getX(),field.getY())<Point2D.distance(newMyPoint.getX(),newMyPoint.getY(),field.getX(),field.getY())){
                            newMyPoint = new MyPoint(i,j);
                        }
                    }
                }
            }
        }

        return newMyPoint;
    }

    @Override
    public Organism getCopy(MyPoint field) {
        return new CyberSheep(world,field,strength,initiative,symbol,chanceToSpread);
    }
}
