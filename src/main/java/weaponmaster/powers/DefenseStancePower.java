package weaponmaster.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import weaponmaster.WeaponMaster;

public class DefenseStancePower extends AbstractPower {

    public static final String ID_NAME = WeaponMaster.makeID("DefenseStancePower");
    public static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID_NAME);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String IMG = WeaponMaster.makeResourcePath("powers/placeholder_r_b.png");
    public static final String[] DESCRIPTION = POWER_STRINGS.DESCRIPTIONS;

    public DefenseStancePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = ID_NAME;
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        //this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = ImageMaster.loadImage(IMG);
    }

    @Override
	public void updateDescription() {
		this.description = DESCRIPTION[0];
    }

}
