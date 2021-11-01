import java.util.Scanner;

public class Fist_Fight {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static int fist_fight(Player player, Mob mob){
        int choice, live=1;
        player.reset(player);
        mob.reset(mob);
        while(player.hp>0 && mob.hp>0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(player.nick + " (" + ANSI_RED + player.hp + ANSI_RESET + "|" +
                    ANSI_CYAN + player.energy + ANSI_RESET + ") vs " + mob.nick + " (" + ANSI_RED + mob.hp + ANSI_RESET + "|" +
                    ANSI_CYAN + mob.energy + ANSI_RESET + ")\n");
            if(!player.stun){
                System.out.print("1. Attack\n2. Strong attack\n3. Stunning blow\nYour choice: ");
                choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        mob.hp -= player.FistAttack(player, mob);
                        System.out.println("\n\n\n\n\n\n\n\n\n\n" + player.nick + " dealt " + player.FistAttack(player, mob) + " damage!");
                        break;
                    case 2:
                        if(player.energy>2)
                        {
                            mob.hp -= player.Strong_fist_attack(player, mob);
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + player.nick + " dealt " + player.Strong_fist_attack(player, mob) + " damage!");
                            player.energy-=3;
                        }
                        else{
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\nNot enough energy!");
                            continue;
                        }
                        break;
                    case 3:
                        if(player.energy>2)
                        {
                            mob.hp -= player.Fist_Deafening_blow(player, mob);
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + player.nick + " dealt " + player.Fist_Deafening_blow(player, mob) + " damage!");
                            mob.stun_cooldown = player.Fist_Deafening_blow(player, mob);
                            player.energy-=3;
                        }
                        else{
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\nNot enough energy!");
                            continue;
                        }
                        break;
                    default:
                        continue;
                }
            }
            else
                System.out.println(player.nick + " is " + ANSI_YELLOW + "stunned(" + player.stun_cooldown + ")!" + ANSI_RESET);

            mob.stunCooldown(mob);
            if(mob.hp>0){
                if(!mob.stun){
                    player.hp -= mob.FistAttack(mob, player);
                    System.out.println(mob.nick + " dealt " + mob.FistAttack(mob, player) + " damage!\n");
                }
                else
                    System.out.println(mob.nick + " is " + ANSI_YELLOW + "stunned(" + mob.stun_cooldown + ")!" + ANSI_RESET);
                if(player.stamina > player.energy) player.energy++;
                if(mob.stamina > mob.energy) mob.energy++;
            }
            player.stunCooldown(player);
        }
        if(player.hp<=0){
            live=0;
            System.out.println(ANSI_RED + "You've been defeated!\n" + ANSI_RESET);
        }
        if(live==1){
            System.out.println(ANSI_RED + mob.nick + " is defeated!\n" + ANSI_RESET);
            player.GetExp(player, mob);
        }
        player.reset(player);
        return live;
    }
}