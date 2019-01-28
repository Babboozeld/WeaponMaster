package weaponmaster.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import weaponmaster.WeaponMaster;
import weaponmaster.WeaponMaster.Stance;
import weaponmaster.characters.WeaponMasterPlayer;

public class GainBlockByOffenceAttackPower extends AbstractPower {

    public static final String ID_NAME = WeaponMaster.makeID("GainBlockByOffenceAttackPower");
    public static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID_NAME);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String IMG = WeaponMaster.makeResourcePath("power/placeholder.png");
    public static final String[] DESCRIPTION = POWER_STRINGS.DESCRIPTIONS;

    public GainBlockByOffenceAttackPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = ID_NAME;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG);
        this.isTurnBased = false;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if (this.owner instanceof WeaponMasterPlayer){
            if (((WeaponMasterPlayer)this.owner).stance == Stance.OFFENCE && card.type == CardType.ATTACK) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, this.amount));
            }
        }
    }

    @Override
	public void updateDescription() {
		this.description = DESCRIPTION[0];
    }

}