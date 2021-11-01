import java.util.Scanner;

public class City {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static int enter_city(Player player, Equipment player_equipment, Pocket player_pocket, int city_lvl){
        int choice, leave=0;
        Scanner scanner = new Scanner(System.in);

        //Creating items for shops
        Pocket weaponsmith_pocket = new Pocket(100);
        Pocket armorsmith_pocket = new Pocket(100);
        Weapon sword1 = new Weapon("One-handed Sword", 25, 2, "Sword");
        Weapon dagger1 = new Weapon("Handy Dagger", 25, 2, "Dagger");
        Weapon mace1 = new Weapon("Iron Mace", 25, 3, "Mace");
        Armor cloth1 = new Armor("Travel Clothes", 30, 1, "Light Robe");
        Armor jacket1 = new Armor("Thin Leather Jacket", 30, 2, "Leather Jacket");
        Armor armor1 = new Armor("Chain Mail", 30, 3, "Heavy Armor");
        weaponsmith_pocket.pickupWeapon(sword1);
        weaponsmith_pocket.pickupWeapon(dagger1);
        weaponsmith_pocket.pickupWeapon(mace1);
        armorsmith_pocket.pickupArmor(cloth1);
        armorsmith_pocket.pickupArmor(jacket1);
        armorsmith_pocket.pickupArmor(armor1);

        System.out.println("\nYou entered the city!");
        do{
            System.out.print("\nWhere you want to go?\n1. City square\n2. Tavern\n3. Quest Board\n" +
                    "4. Character Management\n5. City gate\n\nYour choice: ");
            choice = scanner.nextInt();
            switch(choice){
                //----- City square -----
                case 1:
                    do{
                        System.out.print("\n1. Blacksmith\n2. Armorer\n3. Alchemist\n4. Return\n\nYour choice: ");
                        choice = scanner.nextInt();
                        switch(choice){
                            case 1:
                                System.out.print("1. Buy weapon\n2. Sell weapon\n3. Return\n\nYour choice: ");
                                choice = scanner.nextInt();
                                switch(choice){
                                    case 1:
                                        weaponsmith_pocket.showWeaponPocket();
                                        System.out.println();
                                        player_equipment.showWeapon();
                                        player_pocket.showGold();
                                        if(player_pocket.gold>=25){
                                            System.out.println("\nYou could use a better weapon!");
                                            System.out.print("\n1. " + dagger1.name + " (" + ANSI_YELLOW +dagger1.value +
                                                    ANSI_RESET + ")\n2. " + sword1.name + " (" + ANSI_YELLOW + sword1.value +
                                                    ANSI_RESET + ")\n3. " + mace1.name + " (" + ANSI_YELLOW + mace1.value +
                                                    ANSI_RESET + ")\n4. Return\n\nYour choice: ");
                                            choice = scanner.nextInt();
                                            switch(choice){
                                                case 1:
                                                    player_pocket.gold-=25;
                                                    weaponsmith_pocket.dropFromWeaponPocket(0);
                                                    player_pocket.pickupWeapon(sword1);
                                                    break;
                                                case 2:
                                                    player_pocket.gold-=25;
                                                    weaponsmith_pocket.dropFromWeaponPocket(1);
                                                    player_pocket.pickupWeapon(dagger1);
                                                    break;
                                                case 3:
                                                    player_pocket.gold-=25;
                                                    weaponsmith_pocket.dropFromWeaponPocket(2);
                                                    player_pocket.pickupWeapon(mace1);
                                                    break;
                                                case 4:
                                                    System.out.println("See you around!");
                                                    break;
                                            }
                                        }
                                        else
                                            System.out.println("\nCome back when you got enough money!");
                                        break;
                                    case 2:
                                        player_pocket.showWeaponPocket();
                                        System.out.print("\n\nWhich weapon you want to sell? \n6. Return\n\nYour choice: ");
                                        choice = scanner.nextInt()-1;
                                        if(choice>=0 && choice<5){
                                            System.out.println(player_pocket.weapon_pocket.get(choice).name +
                                                    " has been sold");
                                            player_pocket.gold += player_pocket.weapon_pocket.get(choice).value/2;
                                            player_pocket.showGold();
                                            player_pocket.dropFromWeaponPocket(choice);
                                        }
                                        break;
                                }
                                break;
                            case 2:
                                System.out.print("1. Buy armor\n2. Sell armor\n3. Return\n\nYour choice: ");
                                choice = scanner.nextInt();
                                switch(choice){
                                    case 1:
                                        armorsmith_pocket.showArmorPocket();
                                        System.out.println();
                                        player_equipment.showArmor();
                                        player_pocket.showGold();
                                        if(player_pocket.gold>=30){
                                            System.out.println("\nYou have to protect yourself!");
                                            System.out.print("\n1. " + cloth1.name + " (" + ANSI_YELLOW +cloth1.value +
                                                    ANSI_RESET + ")\n2. " + jacket1.name + " (" + ANSI_YELLOW + jacket1.value +
                                                    ANSI_RESET + ")\n3. " + armor1.name + " (" + ANSI_YELLOW + armor1.value +
                                                    ANSI_RESET + ")\n4. Return\n\nYour choice: ");
                                            choice = scanner.nextInt();
                                            switch(choice){
                                                case 1:
                                                    player_pocket.gold-=30;
                                                    armorsmith_pocket.dropFromArmorPocket(0);
                                                    player_pocket.pickupArmor(cloth1);
                                                    break;
                                                case 2:
                                                    player_pocket.gold-=30;
                                                    armorsmith_pocket.dropFromArmorPocket(1);
                                                    player_pocket.pickupArmor(jacket1);
                                                    break;
                                                case 3:
                                                    player_pocket.gold-=30;
                                                    armorsmith_pocket.dropFromArmorPocket(2);
                                                    player_pocket.pickupArmor(armor1);
                                                    break;
                                                case 4:
                                                    System.out.println("See you around!");
                                                    break;
                                            }
                                        }
                                        else
                                            System.out.println("\nCome back when you got enough money!");
                                        break;
                                    case 2:
                                        player_pocket.showArmorPocket();
                                        System.out.print("\n\nWhich armor you want to sell? \n6. Return\n\nYour choice: ");
                                        choice = scanner.nextInt()-1;
                                        if(choice>=0 && choice<5){
                                            System.out.println(player_pocket.armor_pocket.get(choice).name +
                                                    " has been sold");
                                            player_pocket.gold += player_pocket.armor_pocket.get(choice).value/2;
                                            player_pocket.showGold();
                                            player_pocket.dropFromArmorPocket(choice);
                                        }
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("Take a look at my wares!");
                                do{
                                    System.out.print("\n\n1. Buy Health Potion\n" +
                                            "2. Buy Energy Potion\n3. Buy tool1\n4. Buy tool2\n" +
                                            "5. Fill everything\n6. Return\n\nYour choice: ");
                                    choice = scanner.nextInt();
                                    switch(choice){
                                        case 1: player_pocket.pickupTool(1,0,0,0); break;
                                        case 2: player_pocket.pickupTool(0,1,0,0); break;
                                        case 3: player_pocket.pickupTool(0,0,1,0); break;
                                        case 4: player_pocket.pickupTool(0,0,0,1); break;
                                        case 5: player_pocket.fillToolBag();
                                    }
                                }while(choice>0 && choice<6);

                                break;
                            case 4:
                                break;
                        }
                    }
                    while(choice!=4);
                    break;
                //----- Tavern -----
                case 2:
                    System.out.println("Welcome in my tavern!");
                    do{
                        System.out.print("\n1. Drink\n2. Fist fight\n3. Dice Game\n4. Return\n\nYour choice: ");
                        choice = scanner.nextInt();
                        switch(choice){
                            case 1:
                                if(player_pocket.gold>=5){
                                    do{
                                        System.out.print("You want a drink? (" + player.beer_counter +
                                                ")\n1. Yes (5g)\n2. No\n\nYour choice: ");
                                        choice = scanner.nextInt();
                                        switch (choice){
                                            case 1:
                                                System.out.println("Here's your beer!");
                                                player_pocket.gold -= 5;
                                                if(!player.drink()){
                                                    System.out.println("Enough for you!");
                                                    choice = 2;
                                                }
                                                break;
                                            case 2:
                                                System.out.println("As you wish.");
                                                break;
                                            default: System.out.println("Wrong choice!");
                                        }
                                    }while(player_pocket.gold >= 5 && choice != 2);
                                }
                                else
                                    System.out.println("We don't serve free drinks, you know?");


                                break;
                            case 2:
                                do{
                                    System.out.println("Want to earn some??\n1. Yes (20 coins)\n2. No (Leave)");
                                    choice = scanner.nextInt();
                                    if(choice!=1 && choice!=2)
                                        System.out.println("Wrong choice!");
                                }while(choice!=1 && choice!=2);

                                if(player_pocket.gold<20){
                                    System.out.println("Come back with money!");
                                    continue;
                                }
                                if(choice==1) {
                                    Mob_Deafening_Blow boxer = new Mob_Deafening_Blow(7,3,10,10,0,2,"Drunk Bruiser");
                                    if(Fist_Fight.fist_fight(player, boxer)==1){
                                        System.out.println("Good fight!\nYou earned 20 coins!");
                                        player_pocket.gold+=20;
                                    }
                                    else{
                                        System.out.println("Better lie down!\nYou lost 20 coins!");
                                        player_pocket.gold-=20;
                                    }
                                }
                                break;

                            case 3:
                                player_pocket.gold+=(Dice_Poker.Play_Dice_Poker(player.nick, player_pocket));
                                break;
                            case 4:
                                System.out.println("Come back when you feel thirsty");
                                break;
                            default: System.out.println("Wrong choice!");
                        }
                    }while(choice>0 && choice<4);
                    break;
                //----- Quest Board -----
                case 3:
                    System.out.println("Which quest you want to do?");
                    //mocna glowa
                    //wypij 3 piwa i wroc do tablicy o wlasnych silach
                    //generalnie trzeba pamietac ze gra jest endless wiec
                    //trzeba zrobic klase random quest
                    break;
                //----- Character Management -----
                case 4:
                    do{
                        System.out.print("\n\n1. Show attributes\n2. Show gear\n3. Show equipment\n4. Return\n\nYour choice: ");
                        choice = scanner.nextInt();
                        switch(choice){
                            case 1:
                                System.out.print("\nYour character:\n" + player.nick + "\nStrength (" +
                                        player.strenght + ")\nEndurance (" + player.endurance + ")" +
                                        "\nVitality (" + player.vitality + ")\nWeapon power: (+" +
                                        player_equipment.w_dmg + ")\nArmor defense: (+" + player_equipment.a_def + ")");
                                break;
                            case 2:
                                player_equipment.showWeapon();
                                player_equipment.showArmor();
                                System.out.println("\nDo you want to swap equipment?\n1. Swap weapon\n" +
                                        "2. Swap armor\n3. Return\n\nYour choice: ");
                                choice = scanner.nextInt();
                                switch(choice){
                                    case 1:
                                        player_pocket.showWeaponPocket();
                                        System.out.print("\n\nWhich weapon you want to equip? ");
                                        choice = scanner.nextInt()-1;
                                        player_equipment.equipWeapon(player_pocket.weapon_pocket.get(choice));
                                        System.out.println(player_pocket.weapon_pocket.get(choice).name +
                                                " has been equipped");
                                        break;
                                    case 2:
                                        player_pocket.showArmorPocket();
                                        System.out.print("\n\nWhich armor you want to equip? ");
                                        choice = scanner.nextInt()-1;
                                        player_equipment.equipArmor(player_pocket.armor_pocket.get(choice));
                                        System.out.println(player_pocket.armor_pocket.get(choice).name +
                                                " has been equipped");
                                        break;
                                    case 3:
                                        continue;
                                }
                                break;
                            case 3:
                                player_pocket.showArmorPocket();
                                System.out.print("\n---|----------------------|---------|-------|--------\n");
                                player_pocket.showWeaponPocket();
                                break;
                        }
                    }while(choice!=4);
                    break;
                //----- City Gate -----
                case 5:
                    System.out.println("You really want to leave city?\n1. Yes\n2. No\n\nYour choice: ");
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