package weaponmaster.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AttackStancePower extends AbstractPower {
    
    public static final String NAME = "Attack Stance";
    public static final String IDNAME = "wm_attack_power";
    public static final String IMAGE = "";
    public static final String DESCRIPTION = "";

    public AttackStancePower(AbstractCreature owner){
        this.name = NAME;
        this.ID = IDNAME;
        this.owner = owner;
        //this.img = 
        this.isTurnBased = false;

        updateDescription();

        this.type = PowerType.BUFF;
    }

    @Override
	public void updateDescription() {
		this.description = DESCRIPTION;
    }

}