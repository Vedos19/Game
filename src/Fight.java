import java.util.Scanner;

public class Fight {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static int fight(Player player, Mob mob){
        int choice, live = 1;
        double status;
        player.reset(player);
        while(player.hp>0 && mob.hp>0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + player.nick + " (" + ANSI_RED + player.hp + ANSI_RESET + "|" +
                    ANSI_CYAN + player.energy + ANSI_RESET + ") vs " + mob.nick + " (" + ANSI_RED + mob.hp + ANSI_RESET + "|" +
                    ANSI_CYAN + mob.energy + ANSI_RESET + ")\n");
            if(!player.stun){
                System.out.print("1. Regular attack\n2. Skill list\n3. Equipment\nYour choice: ");
                choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        mob.hp -= player.Attack(player, mob);
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + player.nick + " dealt " + player.Attack(player, mob) + " damage!");
                        break;
                    case 2:
                        System.out.println("1. Heavy Attack (2pm)\n2. Tearing Cut (2pm)\n3. Deafening Blow (2pm)\n4. Return\nYour choice:");
                        choice = scanner.nextInt();
                        switch(choice){
                            case 1:
                                if(player.energy>1){
                                    mob.hp -= player.Strong_Attack(player, mob);
                                    System.out.println(player.nick + " dealt " + player.Strong_Attack(player, mob) + " damage!");
                                    player.energy-=2;
                                }
                                else{
                                    System.out.println("Not enough energy!");
                                    continue;
                                }
                                break;
                            case 2:
                                if(player.energy>1){
                                    status = player.Tearing_Cut(player, mob);
                                    mob.hp -= status;
                                    System.out.println(player.nick + " dealt " + status + " damage!");
                                    player.energy-=2;
                                }
                                else{
                                    System.out.println("Not enough energy!");
                                    continue;
                                }
                                break;
                            case 3:
                                if(player.energy>1){
                                    status = player.Deafening_Blow(player, mob);
                                    mob.hp -= status;
                                    System.out.println(player.nick + " dealt " + status + " damage!");
                                    player.energy-=2;
                                }
                                else{
                                    System.out.println("Not enough energy!");
                                    continue;
                                }
                                System.out.println("Status: " + status);
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("Soon2!");
                        break;
                    default:
                        continue;
                }
            }
            else
                System.out.println(player.nick + " is " + ANSI_YELLOW + "stunned(" + player.stun_cooldown + ")!" + ANSI_RESET);

            mob.stunCooldown(mob);
            mob.bleedCooldown(player, mob);

            if(mob.hp>0){
                if(!mob.stun){
                    if(mob.energy>2){ //atak/skill depends on enemy's energy
                        dmg_skill = mob.Skill(mob, player);
                        player.hp-=dmg_skill;
                        System.out.println("\n" + mob.nick + " dealt " + dmg_skill + " damage!");
                        mob.energy-=3;
                    }
                    else{
                        player.hp -= mob.Attack(mob, player);
                        System.out.println("\n" + mob.nick + " dealt " + mob.Attack(mob, player) + " damage!");
                    }
                }
                else
                    System.out.println(mob.nick + " is " + ANSI_YELLOW + "stunned(" + mob.stun_cooldown + ")!" + ANSI_RESET);

                if(player.stamina > player.energy) player.energy++;
                if(mob.stamina > mob.energy) mob.energy++;
            }
            player.stunCooldown(player);
            player.bleedCooldown(mob, player);
        }
        if(player.hp<=0){
            live=0;
            System.out.println(ANSI_RED + "\nYou died!" + ANSI_RESET);
        }
        if(live==1){
            System.out.println(ANSI_RED + "\n" + mob.nick + " died!" + ANSI_RESET);
            mob.GetExp(player, mob);
        }
        return live;
    }
}
