public class Main {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";

        public static void main(String[] args) {
                int path_lvl = 0;
                System.out.println();
                Weapon dagger0 = new Weapon("Tiny Dagger", 10, 1, 1);
                Weapon sword0 = new Weapon("Rusty Sword", 10, 1, 2);
                Weapon mace0 = new Weapon("Light Mace", 10, 1, 3);
                Armor armor0 = new Armor("\n" + "Worn Travel Clothes", 1, 0, 1);
                Equipment equipment = new Equipment(sword0, armor0);
                Pocket pocket = new Pocket();

                Player player = new Player(3, 3, 3, 4, 1, 0, "Vedos", 30);
                player.create(player);

                //lvl1
                Mob wolf1 = new Mob(5, 2, 5, 3, 30, 1, "Young Wolf");
                Mob_Tearing_Cut wolf2 = new Mob_Tearing_Cut(6, 2, 6, 3, 50, 1, "Wolf");
                Mob_Tearing_Cut wolf3 = new Mob_Tearing_Cut(6, 3, 8, 3, 100, 2, "Alpha Wolf");
                //lvl2
                Mob_Deafening_Blow bandit1 = new Mob_Deafening_Blow(5, 5,8,7,150, 2, "Thug");
                Mob_Tearing_Cut bandit2 = new Mob_Tearing_Cut(7, 3,6,3,200, 2, "Cutthroat");
                Mob_Strong_Attack bandit3 = new Mob_Strong_Attack(6, 6,8,3,250, 3, "Bandit Leader");

                //fight 1
                do path_lvl += Path.path(player, equipment, pocket, wolf1, wolf2, wolf3);
                while(path_lvl!=1);
                player.upgrade(player, 3);

                //city 1, fight 2
                do{
                    City.enter_city(player, equipment, 1);
                    path_lvl += Path.path(player, equipment, pocket, bandit1, bandit2, bandit3);
                }
                while(path_lvl!=2);
                player.upgrade(player, 3);

                //city 2, fight 3
                do{
                    City.enter_city(player, equipment, 2);
                    path_lvl += Path.path(player, equipment, pocket, bandit1, bandit2, bandit3);
                }
                while(path_lvl!=3);
                player.upgrade(player, 3);
        }
}