package weaponmaster.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import weaponmaster.WeaponMaster;

public class ShieldLevel extends CustomRelic {

    public static final String ID = WeaponMaster.makeID("ShieldLevel");
    public static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String NAME = RELIC_STRINGS.NAME;
    public static final String DESCRIPTION = RELIC_STRINGS.DESCRIPTIONS[0];
    public static final String IMG = WeaponMaster.makeResourcePath("relics/ShieldLevel.png");
    public static final String OUTLINE = WeaponMaster.makeResourcePath("relics/ShieldLevelOutline.png");
    public static final RelicTier TIER = RelicTier.STARTER;

    private static int AMOUNT = 1;

    public ShieldLevel(){
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(OUTLINE), TIER, LandingSound.MAGICAL);  //<<sound
        tips.clear();
        tips.add(new PowerTip(NAME, DESCRIPTION));
    }

    public void upgrade(int amount){
        AMOUNT += amount;
        this.counter += AMOUNT;
    }

    public void levelUp(int amount){
        this.counter += amount;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, amount), amount));
    }

    @Override
    public void atBattleStart() {
        this.counter = AMOUNT;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, AMOUNT), AMOUNT));
    }

    @Override
    public void onVictory()
    {
        this.counter = AMOUNT;
    }

    //getUpdatedDescripion

    @Override
    public AbstractRelic makeCopy() {
        return new ShieldLevel();
    }
}