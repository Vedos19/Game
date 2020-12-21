import java.util.Scanner;

public class Fight {
    public static int fight(Player player, Mob mob){
        int choice, live = 1;
        player.reset(player);
        double dmg_skill; //dmg_skill - solving error with double damage message
        while(player.hp>0 && mob.hp>0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + player.nick + " (" + player.hp + ") vs " + mob.nick + " (" + mob.hp + ")\n");
                System.out.print("1. Regular attack\n2. Skill list\n3. Equipment\nYour choice: ");
                choice = scanner.nextInt();
                switch(choice){
                    case 1:
                            mob.hp -= player.Attack(player, mob);
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + player.nick + " dealt " + player.Attack(player, mob) + " damage!");
                        break;
                    case 2:
                        System.out.println("Soon1!");
                        break;
                    case 3:
                        System.out.println("Soon2!");
                        break;
                    default:
                        continue;
                }
            if(mob.hp>0){
                player.hp -= mob.Attack(mob, player);
                System.out.println("\n" + mob.nick + " dealt " + mob.Attack(mob, player) + " damage!");
            }
        }
        if(player.hp<=0){
            live=0;
            System.out.println("\nYou died!");
        }
        if(live==1){
            System.out.println("\n" + mob.nick + " died!");
            mob.GetExp(player, mob);
        }
        return live;
    }
}
