import java.util.Random;
import java.util.Scanner;

public class Player extends Mob{
    Player(int strenght, int endurance, int vitality, int stamina, int exp, int lvl, String nick){
        super(strenght, endurance, vitality, stamina, exp, lvl, nick);
        hp = vitality*4;
    }
    Random random = new Random();

    double Attack(Player attacker, Mob receiver, double w_dmg){
        double damage;
        damage = attacker.strenght + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        return damage;
    }
    int Fist_Deafening_blow(Player attacker, Mob receiver){
        int rng, stun;
        rng = random.nextInt(10);
        if(rng==0){
            receiver.stun_cooldown=3;
            System.out.println(attacker.nick + ANSI_YELLOW + " stuns " + ANSI_RESET + receiver.nick + " for 3 turns!");
            stun = 3;
        }
        else if(rng<4) {
            receiver.stun_cooldown=2;
            System.out.println(attacker.nick + ANSI_YELLOW + " stuns " + ANSI_RESET + receiver.nick + " for 2 turns!");
            stun = 2;
        }
        else if(rng<8){
            receiver.stun_cooldown=1;
            System.out.println(attacker.nick + ANSI_YELLOW + " stuns " + ANSI_RESET + receiver.nick + " for 1 turn!");
            stun = 1;
        }
        else{
            System.out.println(attacker.nick + ANSI_YELLOW + " tried to stun " + ANSI_RESET + receiver.nick + " but didn't success!");
            stun = 0;
        }
        return stun;
    }
    void setVitality(int vitality){ this.vitality = vitality; }
    void setStrenght(int strenght){ this.strenght = strenght; }
    void setEndurance(int endurance){
        this.endurance = endurance;
    }
    public void create(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome in character creator!\n\nChoose your name: ");
        String nick = scanner.nextLine();

        //easter_egg
        if(nick.equals("Vedoes")){
            setStrenght(this.strenght+97);
            setEndurance(this.endurance+97);
            setVitality(this.vitality+247);
            hp+=989;
        }
        else {
            System.out.print("\n" +
                    "You have 5 points to distribute.\nThe process cannot be undone!\n\n" +
                    "Strength..: Increases damage dealt by 1\n" +
                    "Endurance.: Increases your resistance by 1\n" +
                    "Vitality..: Increases your health by 4\n\n");
            upgrade(5);
        }
    }
    public void upgrade(int points){
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<points;i++)
        {
            System.out.print((points-i) + " points left!\n1. +1 Strength (" + strenght + ")\n2. +1 Endurance (" + endurance + ")" +
                    "\n3. +1 Vitality (" + vitality + ")\nYour choice: ");
            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    setStrenght(this.strenght+1);
                    break;
                case 2:
                    setEndurance(this.endurance+1);
                    break;
                case 3:
                    setVitality(this.vitality+1);
                    hp+=4;
                    break;
                default:
                    System.out.println("\nWrong choice!\n"); i--;
            }
        }
        System.out.println("\nYour character:\n" + nick + "\nStrength (" + strenght + ")\nEndurance (" + endurance + ")" +
                "\nVitality (" + vitality + ")");
    }
    public boolean drink(){
        beer_counter++;
        int chance = random.nextInt(100);
        System.out.println(chance + " vs " + (60 + endurance*3 - beer_counter*10) + " <-- your chance");
        if(chance > 60 + endurance*3 - beer_counter*10)
            return false;
        return true;
    }
}
