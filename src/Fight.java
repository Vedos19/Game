import java.util.Scanner;

public class Fight {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static int fight(Player player, Mob mob, Equipment equipment, Pocket pocket){
        int choice, live = 1;
        player.reset(player);
        mob.reset(mob);
        double dmg_skill; //dmg_skill - solving error with double damage message
        while(player.hp>0 && mob.hp>0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + player.nick + " (" + ANSI_RED + player.hp + ANSI_RESET + "|" +
                    ANSI_CYAN + player.energy + ANSI_RESET + ") vs " + mob.nick + " (" + ANSI_RED + mob.hp + ANSI_RESET + "|" +
                    ANSI_CYAN + mob.energy + ANSI_RESET + ")\n");
            if(!player.stun){
                System.out.print("1. Attack\n2. Skill list\n3. Equipment\nYour choice: ");
                choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        mob.hp -= player.Attack(player, mob, equipment.w_dmg);
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n" + player.nick + " dealt " + player.Attack(player, mob, equipment.w_dmg) + " damage!");
                        break;
                    case 2:
                        if(equipment.w_type.equals("Sword"))
                            System.out.print("1. Slash (2pm)\n2. Fury Slashes (3pm)\n3. Mighty Swing (4pm)\n4. Return\n\nYour choice: ");
                        else if(equipment.w_type.equals("Dagger"))
                            System.out.print("1. Stab (2pm)\n2. Tearing Cut (3pm)\n3. Flurry (4pm)\n4. Return\n\nYour choice: ");
                        else if(equipment.w_type.equals("Mace"))
                            System.out.print("1. Heavy Slam (2pm)\n2. Crushing Blow (3pm)\n3. Powerful Blow (4pm)\n4. Return\n\nYour choice: ");
                        choice = scanner.nextInt();
                        switch(choice){
                            case 1:
                                if(player.energy>1) {
                                    equipment.Skill1(player, mob, equipment.w_dmg, equipment.w_type);
                                    player.energy-=2;
                                }
                                else{
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\nNot enough mana points!");
                                    continue;
                                }
                                break;
                            case 2:
                                if(player.energy>2){
                                    equipment.Skill2(player, mob, equipment.w_dmg, equipment.w_type);
                                    player.energy-=3;
                                }
                                else{
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\nNot enough mana points!");
                                    continue;
                                }
                                break;
                            case 3:
                                if(player.energy>3) {
                                    equipment.Skill3(player, mob, equipment.w_dmg, equipment.w_type);
                                    player.energy-=4;
                                }
                                else{
                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\nNot enough mana points!");
                                    continue;
                                }
                                break;
                            case 4:
                                System.out.println("Return...");
                                continue;
                            default:
                                System.out.println("Wrong choice!"); continue;
                        }
                        break;
                    case 3:
                        pocket.showToolPocket();
                        System.out.print("5. Return\nYour choice: ");
                        choice = scanner.nextInt();
                        if(pocket.tool_pocket[choice]>0){
                            pocket.tool_pocket[choice]--;
                            switch(choice){
                                case 1:
                                    System.out.print(player.nick + " used a small healing potion.\n" + player.hp);
                                    player.hp+=10;
                                    if(player.hp>player.vitality*4)
                                        player.hp=player.vitality*4;
                                    System.out.print(" -> " + player.hp + "\n");
                                    break;
                                case 2:
                                    player.energy+=3;
                                    System.out.print(player.nick + " used a small energy potion.\n" + player.energy);
                                    if(player.energy>player.stamina)
                                        player.energy=player.stamina;
                                    System.out.print(" -> " + player.energy + "\n");
                                    pocket.tool_pocket[1]--;
                                    break;
                                case 3:
                                    System.out.println("Tool1");
                                    break;
                                case 4:
                                    System.out.println("Tool2");
                                    break;
                                default:
                                    continue;
                            }
                        }
                        else{
                            System.out.println("No such item in inventory!\n" + pocket.tool_pocket[choice]);
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
            mob.bleedCooldown(player, mob);

            if(mob.hp>0){
                if(!mob.stun){
                    if(mob.energy>2){
                        dmg_skill = mob.Skill(mob, player, equipment.a_def);
                        player.hp-=dmg_skill;
                        System.out.println("\n" + mob.nick + " dealt " + dmg_skill + " damage!");
                        mob.energy-=3;
                    }
                    else{
                        player.hp -= mob.Attack(mob, player, equipment.a_def);
                        System.out.println("\n" + mob.nick + " dealt " + mob.Attack(mob, player, equipment.a_def) + " damage!");
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