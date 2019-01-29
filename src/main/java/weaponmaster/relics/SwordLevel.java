package weaponmaster.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import weaponmaster.WeaponMaster;

public class SwordLevel extends CustomRelic {

    public static final String ID = WeaponMaster.makeID("SwordLevel");
    public static final RelicStrings RELIC_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String NAME = RELIC_STRINGS.NAME;
    public static final String DESCRIPTION = RELIC_STRINGS.DESCRIPTIONS[0];
    public static final String IMG = WeaponMaster.makeResourcePath("relics/placeholder.png");//SwordLevel
    public static final String OUTLINE = WeaponMaster.makeResourcePath("relics/placeholder_outline.png");//SwordLevelOutline
    public static final RelicTier TIER = RelicTier.STARTER;

    private static int AMOUNT = 1;
  
    public SwordLevel(){
        super(ID, ImageMaster.loadImage(IMG), new Texture(OUTLINE), TIER, LandingSound.MAGICAL);  //<<sound
        tips.clear();
        tips.add(new PowerTip(NAME, DESCRIPTION));
    }

    public void upgrade(int amount){
        AMOUNT += amount;
        this.counter = AMOUNT;
    }

    public void levelUp(int amount){
        this.counter += amount;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, amount), amount));
    }

    @Override
    public void atBattleStart() {
        this.counter = AMOUNT;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, AMOUNT), AMOUNT));
    }

    @Override
    public void onVictory()
    {
        this.counter = AMOUNT;
    }

    //getUpdatedDescripion

    @Override
    public AbstractRelic makeCopy() {
        return new SwordLevel();
    }
}