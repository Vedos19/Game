import java.util.Scanner;

public class Player extends Mob{

    Player(double strenght, double endurance, double vitality, int lvl, double exp, String nick, int gold){
        super(strenght, endurance, vitality, exp, lvl, nick);
        this.gold = gold;
    }
    int gold;

    double Attack(Player attacker, Mob receiver, double w_dmg){
        double damage;
        damage = attacker.strenght + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        return damage;
    }
    void setVitality(double vitality){ this.vitality = vitality; }
    void setStrenght(double strenght){ this.strenght = strenght; }
    void setEndurance(double endurance){
        this.endurance = endurance;
    }
    public void create(Player player){
        Scanner scanner = new Scanner(System.in);
        String nick;
        System.out.print("Welcome in character creator!\n\nChoose your name: ");
        nick = scanner.nextLine();
        player.nick = nick;


        System.out.print("\n" + "You have 5 points to distribute.\n\nThe process cannot be undone!\n\n" +
                "Strength (" + player.strenght + ") - Increases damage dealt\n" +
                "Endurance (" + player.endurance + ") - Increases your resistance\n" +
                "Vitality (" + player.vitality + ") - Increases your health by 4\n\n");
        player.upgrade(player, 5);
    }
    public void upgrade(Player player, int points){
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<points;i++)
        {
            System.out.print((points-i) + " points left!\n1. Strength (" + player.strenght + ")\n2. Endurance (" + player.endurance + ")" +
                    "\n3. Vitality (" + player.vitality + ")\nYour choice: ");
            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    player.setStrenght(this.strenght+1);
                    break;
                case 2:
                    player.setEndurance(this.endurance+1);
                    break;
                case 3:
                    player.setVitality(this.vitality+1);
                    player.hp+=4;
                    break;
                default:
                    System.out.println("\nWrong choice!\n"); i--;
            }
        }
        System.out.println("Your character:\n" + player.nick + "\nStrength (" + player.strenght + ")\nEndurance (" + player.endurance + ")" +
                "\nVitality (" + player.vitality + ")");
    }
}
