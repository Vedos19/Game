public class Mob_Tearing_Cut extends Mob{
    Mob_Tearing_Cut(double strenght, double endurance, double vitality, double exp, int stamina, int lvl, String nick){
        super(strenght, endurance, vitality, exp, stamina, lvl, nick);
    }

    @Override
    double Skill(Mob attacker, Mob receiver){
        int rng, bleed;
        rng = random.nextInt(10);
        if(rng==0){
            receiver.bleed_cooldown=3;
            System.out.println(receiver.nick + ANSI_RED + " is bleeding " + ANSI_RESET + " for 3 turns!");
            bleed = 3;
        }
        else if(rng<4) {
            receiver.bleed_cooldown=2;
            System.out.println(receiver.nick + ANSI_RED + " is bleeding " + ANSI_RESET + " for 2 turns!");
            bleed = 2;
        }
        else if(rng<8){
            receiver.bleed_cooldown=1;
            System.out.println(receiver.nick + ANSI_RED + " is bleeding " + ANSI_RESET + " for 1 turn!");
            bleed = 1;
        }
        else{
            System.out.println(attacker.nick + " tried to " + ANSI_RED + "bleed " + ANSI_RESET + receiver.nick + " out but didn't success!");
            bleed = 0;
        }
        return bleed;
    }
}
