public class Mob_Strong_Attack extends Mob{
    Mob_Strong_Attack(double strenght, double endurance, double vitality, double exp, int stamina, int lvl, String nick){
        super(strenght, endurance, vitality, stamina, exp, lvl, nick);
    }

    @Override
    double Skill(Mob attacker, Mob receiver, double a_def){
        double damage;
        damage = attacker.strenght * 2 - receiver.endurance;
        if(damage<0) damage = 0;
        return damage;
    }
}
