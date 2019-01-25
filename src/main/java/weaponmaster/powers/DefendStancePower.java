package weaponmaster.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DefendStancePower extends AbstractPower {
    
    public static final String NAME = "Defend Stance";
    public static final String IDNAME = "wm_defend_power";
    public static final String IMAGE = "";
    public static final String DESCRIPTION = "";

    public DefendStancePower(AbstractCreature owner){
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