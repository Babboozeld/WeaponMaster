package weaponmaster.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import weaponmaster.WeaponMaster;

public class OffenceStancePower extends AbstractPower {
    
    public static final String ID_NAME = WeaponMaster.makeID("OffenceStancePower");
    public static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID_NAME);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String IMG = WeaponMaster.makeResourcePath("power/placeholder.png");
    public static final String[] DESCRIPTION = POWER_STRINGS.DESCRIPTIONS;

    public OffenceStancePower(AbstractCreature owner){
        this.name = NAME;
        this.ID = ID_NAME;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG);
        this.isTurnBased = false;

        updateDescription();

        this.type = PowerType.BUFF;
    }

    @Override
	public void updateDescription() {
		this.description = DESCRIPTION[0];
    }

}