package weaponmaster.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import weaponmaster.WeaponMaster;

public class PlayerEquipment extends CustomRelic {

    public static final String ID = WeaponMaster.makeID("PlayerEquipment");
    public static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String NAME = RELIC_STRINGS.NAME;
    public static final String DESCRIPTION = RELIC_STRINGS.DESCRIPTIONS[0];
    public static final String IMG = WeaponMaster.makeResourcePath("relics/placeholder.png");//PlayerEquipment
    public static final String OUTLINE = WeaponMaster.makeResourcePath("relics/placeholder_outline.png");//PlayerEquipmentOutline
    public static final RelicTier TIER = RelicTier.STARTER;

    public int AMOUNT = 0;

    public PlayerEquipment() {
        super(ID, ImageMaster.loadImage(IMG), new Texture(OUTLINE), TIER, LandingSound.MAGICAL); // <<sound
        this.counter = 1;
    }

    public void upgrade(int amount) {
        this.counter += amount;
    }

    public void boost(int amount) {
        AMOUNT = amount;
        this.counter += AMOUNT;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new DexterityPower(AbstractDungeon.player, AMOUNT), AMOUNT));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, AMOUNT), AMOUNT));
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new DexterityPower(AbstractDungeon.player, this.counter), this.counter));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, this.counter), this.counter));
    }

    @Override
    public void onVictory() {   
        this.counter -= AMOUNT;
        AMOUNT = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTION;
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new PlayerEquipment();
    }

}