public class Mob_Deafening_Blow extends Mob{
    Mob_Deafening_Blow(int strenght, int endurance, int vitality, int stamina, int exp, int lvl, String nick){
        super(strenght, endurance, vitality, stamina, exp, lvl, nick);
    }

    @Override
    double Skill(Mob attacker, Mob receiver, double a_def){
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
}
