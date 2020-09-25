package s180158;

import s180158.animals.*;
import s180158.plants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static s180158.ConstValues.*;
import java.awt.*;


public class World {
    protected String log;
    protected int width,height,round=0;
    protected ArrayList<Organism> organisms= new ArrayList<Organism>();
    protected ArrayList<Organism> newOrganisms= new ArrayList<Organism>();
    protected ArrayList<ArrayList<Organism>> worldTable = new ArrayList<ArrayList<Organism>>();
    protected Man human;
    World(){
        log="";
        width=20;
        height=20;
        initTable();
        createPopulation();
        sortOrganisms();
        updateTable();
        drawWorld();
    }
    World(int _height, int _width){
        width = _width;
        height = _height;
        initTable();
        createPopulation();
        sortOrganisms();
        updateTable();
        drawWorld();
    }
    World(boolean i){
        log="";
        width=3;
        height=3;
        initTable();
        human = new Man(this, randomField(width, height));
        addOrganism(human);

        sortOrganisms();
        updateTable();
        drawWorld();
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }


    void createPopulation() {
        human = new Man(this, randomField(width, height));
        addOrganism(human);
        updateTable();
        for (int i = 1; i < ((width * height * POPILATION_SIZE_MULTIPLIER)/100); i++) {
            addOrganism(generateRandomOrganism());
            updateTable();
        }
        updateOrganisms();
    }
    public void saveToFile() {
        try {
            File myFile = new File("savegame.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
                myFile.delete();
                myFile.createNewFile();
                System.out.println("File created: " + myFile.getName());
            }

            FileWriter myWriter = new FileWriter("savegame.txt");
            myWriter.write(height + " " + width +" "+ organisms.size()+" "+
                    round+System.getProperty("line.separator"));
            for (Organism org : organisms) {
                myWriter.write(org.getSymbol() + " " + org.getPosition().getX() +
                        " "+ org.getPosition().getY()+" "+org.getStrength()+" " + org.getInitiative() +
                        " " + org.getChanceToSpread()+System.getProperty("line.separator"));
                }
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Error while writing to file");
            e.printStackTrace();
        }


    }
    public void loadGame() {
        char symbol;
        int str, init, orgSize;
        double x,y,chance;
        initTable();
        try{
            File myfile = new File("savegame.txt");
            Scanner myReader = new Scanner(myfile);

            myReader.useLocale(Locale.US);

            height = myReader.nextInt();
            width = myReader.nextInt();
            orgSize = myReader.nextInt();
            round = myReader.nextInt();
            organisms.clear();
            organisms.trimToSize();
            worldTable.clear();
            worldTable.trimToSize();
            initTable();

            for (int i = 0; i < orgSize; i++) {

                symbol = myReader.next().charAt(0);
                x = myReader.nextDouble();
                y = myReader.nextDouble();
                str = myReader.nextInt();
                init = myReader.nextInt();
                chance = myReader.nextDouble();
                addOrganism(getOrganismFromSymbol((int)x,(int)y,str,init,(int)chance,symbol));

            }

        }catch(FileNotFoundException e){
            System.out.println("No file");
            e.printStackTrace();
        }
        System.out.println("Loaded");
        updateOrganisms();
        updateTable();
        drawWorld();

    }
    public Organism getOrganismFromSymbol(int x, int y, int str, int init, int chance,char symbol) {

        switch (symbol) {
            case 'W': return new Wolf(this, new MyPoint(x,y),str,init,symbol,chance);
            case 'T': return new Turtle(this, new MyPoint(x, y), str, init, symbol,chance);
            case 'S': return new Sheep(this, new MyPoint(x, y), str, init, symbol,chance);
            case 'A': return new Antelope(this, new MyPoint(x, y), str, init, symbol, chance);
            case 'F': return new Fox(this, new MyPoint(x, y), str, init, symbol, chance);
            case 'G': return new Grass(this, new MyPoint(x, y), str, init, symbol, chance);
            case 'N': return new Sonchus(this, new MyPoint(x, y), str, init, symbol, chance);
            case 'U': return new Guarana(this, new MyPoint(x,y),str,init,symbol, chance);
            case 'B': return new Wolfberry(this, new MyPoint(x,y),str,init,symbol, chance);
            case 'O': return new SosnowskiBorscht(this, new MyPoint(x,y),str,init,symbol, chance);
            case 'M': return new Man(this, new MyPoint(x, y), str, init, symbol, chance);

        }
        return null;
    }
    public void clearConsole(){
        for (int i = 0; i < 50; ++i) System.out.println();
    }
    void initTable() {
        for (int y = 0; y < height; ++y)
        {
            worldTable.add(new ArrayList<Organism>());
            for (int x = 0; x < width; ++x)
                worldTable.get(y).add(null);
        }
    }
    void sortOrganisms() {
        Collections.sort(organisms, new Comparator<Organism>() {
            @Override
            public int compare(Organism t0, Organism t1) {
                if(t0.getInitiative()<t1.getInitiative()){
                    return 1;
                }
                else if(t0.getInitiative()==t1.getInitiative()){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        });
    }
    public Organism getOrganismFromPos(Point pos) {
        return worldTable.get((int) pos.getX()).get((int)pos.getY());
    }
    void addOrganism(Organism organism) {
        newOrganisms.add(organism);
        updateTable();
    }
    MyPoint randomField(int width, int height) {
        Random rand = new Random();

        int x,y;
        do {
            x = rand.nextInt(height );
            y = rand.nextInt( width  );
        } while (getOrganismFromPos(new Point(x, y)) != null);
        return new MyPoint(x, y);
    }
    Organism generateRandomOrganism() {
        Random rand = new Random();
        int i = rand.nextInt(10);
        switch (i) {
            case 0: return new Wolf(this, randomField(width,height));
            case 1: return new Turtle(this, randomField(width, height));
            case 2: return new Sheep(this, randomField(width, height));
            case 3: return new Antelope(this, randomField(width, height));
            case 4: return new Fox(this, randomField(width, height));
            case 5: return new Grass(this, randomField(width, height));
            case 6: return new Sonchus(this, randomField(width, height));
            case 7: return new Guarana(this, randomField(width, height));
            case 8: return new Wolfberry(this, randomField(width, height));
            case 9: return new SosnowskiBorscht(this, randomField(width, height));
        }

        return null;
    }
    void updateOrganisms(){
        for (int i = 0; i < newOrganisms.size(); i++) {
            Organism org = newOrganisms.get(i);
            organisms.add(org);
        }
        newOrganisms.clear();
    }
    void updateTable() {
        Point point;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                worldTable.get(i).set(j,null);
            }
        }
        organisms.trimToSize();
        for (int i = 0; i < organisms.size(); i++) {
            Organism org = organisms.get(i);
            if(org == null){
                organisms.remove(org);
                organisms.trimToSize();
            }
        }

        for (int i = 0; i < organisms.size(); i++) {
            Organism org = organisms.get(i);
            worldTable.get((int) org.getPosition().getX() ).set( (int) org.getPosition().getY(), org );
        }
        for (int i = 0; i < newOrganisms.size(); i++) {
            Organism org = newOrganisms.get(i);
            worldTable.get((int) org.getPosition().getX() ).set( (int) org.getPosition().getY(), org );
        }
    }
    public void drawWorld() {
        int i;
        for (i = 0; i < width+2; i++) {
            System.out.print("* ");
        }
        System.out.print("\n");
        for (i = 0; i < height; i++) {
            System.out.print("* ");
            for(Organism field : worldTable.get(i))
            {
                if (field == null) {
                    System.out.print(". ");
                }
                else {
                    System.out.print(field.toString()+" ");
                }
            }
            System.out.print("*\n");
        }
        for (i = 0; i < width+2; i++) {
            System.out.print("* ");
        }
        System.out.print("\n");
    }
    Boolean fight(Organism attacker, Organism defender) {

        System.out.println("fight at " + defender.getPosition().toString() + defender.toString() +" from " + attacker.getPosition().toString() + attacker.toString());
        int a = defender.avoidAttack(attacker);
        if (a== 1) {
            return false;
        }
        else if (a == 2) {
            return true;
        }else {
            if (attacker.getStrength() < defender.getStrength()) {
                kill(attacker);
                return false;
            }
            else {
                defender.collision(attacker);
                kill(defender);
                return true;
            }
        }
    }
    Boolean meal(Organism attacker, Organism defender) {
        System.out.println("Plant is being eaten at " + defender.getPosition() +" by "+attacker.toString());
        defender.collision(attacker);
        kill(defender);
        updateTable();
        return true;
    }
    Boolean collision(Organism attacker, Point field) {

        Organism defender = worldTable.get((int) field.getX()).get((int) field.getY());
        if (isAnimal(defender)) {
            return fight(attacker, defender);
        }
        else {
            return meal(attacker, defender);
        }
    }
    public Boolean isAnimal(Organism aim) {
        return aim instanceof Animal;
    }
    public void kill(Organism victim) {
        System.out.println("Organism "+victim.toString() +" dies at " + victim.getPosition());
        if(victim instanceof Man){
            human = null;
        }
        worldTable.get((int) victim.getPosition().getX()).set((int) victim.getPosition().getY(),null);
        organisms.remove(victim);
        newOrganisms.remove(victim);
        newOrganisms.trimToSize();
        organisms.trimToSize();
        updateTable();
    }
    public void nextRound(int dir) {
        clearConsole();
        drawWorld();
        round++;
        sortOrganisms();
        for (int i = 0; i < organisms.size();i++) {
            if(organisms.get(i) instanceof Man){
                ((Man) organisms.get(i)).nextMove(dir);
                updateTable();
                drawWorld();
            }else{
                organisms.get(i).nextMove();
                updateTable();
            }
        }
        updateOrganisms();
        updateTable();
        drawWorld();

    }
    void startManAbility(){
        human.startAbility();
    }
}
