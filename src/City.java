import java.util.Scanner;

public class City {
    public static int enter_city(Player player, int city_lvl){
        int choice, leave=0;
        Scanner scanner = new Scanner(System.in);


        System.out.println("\nYou entered the city!");
        do{
            System.out.print("\nWhere you want to go?\n1. City square\n2. Tavern\n3. Quest Board\n4. City gate\nYour choice: ");
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    do{
                        System.out.print("\n1. Blacksmith\n2. Armorer\n3. Alchemist\n4. Return\nYour choice: ");
                        choice = scanner.nextInt();
                        switch(choice){
                            case 1:
                                System.out.println("You could use a better weapon!");
                                break;
                            case 2:
                                System.out.println("You need a better armor!");
                                break;
                            case 3:
                                System.out.println("Take a look at my wares!");
                                break;
                            case 4:
                                break;
                        }
                    }
                    while(choice!=4);
                    break;
                case 2:
                    System.out.println("Welcome in my tavern!\n\n1. Drink\n2. Fist fight\n3. Dice Game");
                    choice = scanner.nextInt();

                    switch(choice){
                        case 1:
                            break;

                        case 2:
                            do{
                                System.out.println("Want to earn some??\n1. Yes (20 coins)\n2. No (Leave)");
                                choice = scanner.nextInt();
                                if(choice!=1 && choice!=2)
                                    System.out.println("Wrong choice!");
                            }while(choice!=1 && choice!=2);

                            if(player.gold<20){
                                System.out.println("Come back with money!");
                                continue;
                            }
                            if(choice==1) {
                                Mob_Deafening_Blow boxer = new Mob_Deafening_Blow(7,3,3,3,10,2,"Drunk boxer");
                                if(Fist_Fight.fist_fight(player, boxer)==1){
                                    System.out.println("Good fight!\nYou earned 20 coins!");
                                    player.gold+=20;
                                }
                                else{
                                    System.out.println("Better lie down!\nYou lost 20 coins!");
                                    player.gold-=20;
                                }
                            }
                            break;

                        case 3:
                            player.gold+=(Dice_Poker.Play_Dice_Poker(player));
                    }
                    break;

                case 3:
                    System.out.println("No quests at this moment!");
                    break;

                case 4:
                    System.out.println("You really want to leave city?\n1. Yes\n2. No\nYour choice: ");
                    choice = scanner.nextInt();
                    if(choice==1){
                        leave=1;
                    }
                    else if(choice==2)
                        System.out.println("Smart choice!");
                    else
                        System.out.println("Wrong choice!");
            }
        }while(leave!=1);

        return leave;
    }
}
