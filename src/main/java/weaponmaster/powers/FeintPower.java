package weaponmaster.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import weaponmaster.WeaponMaster;
import weaponmaster.patches.WeaponMasterEnum;

public class FeintPower extends AbstractPower {

    public static final String ID_NAME = WeaponMaster.makeID("FeintPower");
    public static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID_NAME);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String IMG = WeaponMaster.makeResourcePath("powers/placeholder_r.png");
    public static final String[] DESCRIPTION = POWER_STRINGS.DESCRIPTIONS;

    public FeintPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = ID_NAME;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        // this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = ImageMaster.loadImage(IMG);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if (card.hasTag(WeaponMasterEnum.WEAPONMASTER_SWITCHSTANCE)) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));
        }
    }

    @Override
	public void updateDescription() {
		this.description = DESCRIPTION[0];
    }

}