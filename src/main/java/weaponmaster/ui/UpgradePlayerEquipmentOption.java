package weaponmaster.ui;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import weaponmaster.WeaponMaster;
import weaponmaster.relics.PlayerEquipment;

public class UpgradePlayerEquipmentOption extends AbstractCampfireOption {
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(WeaponMaster.makeID("UpgradePlayerEquipmentOption"));
    public static final String LABEL = UI_STRINGS.TEXT[0];
    public static final String DESCRIPTION = UI_STRINGS.TEXT[1];
    public boolean isFree = true;

    public static final int AMOUNT = 1;

    public UpgradePlayerEquipmentOption() {
        this.label = LABEL;
        this.usable = true;
        setImageAndDescription();
    }

    public void setImageAndDescription() {
        this.description = DESCRIPTION;
        this.img = new Texture(WeaponMaster.makeResourcePath("ui/placeholder_option.png"));
    }

    @Override
    public void useOption() {
        if(this.usable) {
            CardCrawlGame.sound.play("SLEEP_BLANKET");
            if (AbstractDungeon.player.hasRelic(PlayerEquipment.ID)){
                ((PlayerEquipment)AbstractDungeon.player.getRelic(PlayerEquipment.ID)).upgrade(AMOUNT);
            }
            AbstractDungeon.effectList.add(new UpgradePlayerEquipmentEffect());
            for (int i = 0; i < 30; i++) {
                AbstractDungeon.topLevelEffects.add(new com.megacrit.cardcrawl.vfx.campfire.CampfireSleepScreenCoverEffect());
            }
            CardCrawlGame.metricData.campfire_rested += 1;
            CardCrawlGame.metricData.addCampfireChoiceData("REST");
        }
    }
}