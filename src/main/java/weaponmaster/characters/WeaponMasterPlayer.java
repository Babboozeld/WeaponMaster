package weaponmaster.characters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import weaponmaster.patches.PlayerClassEnum;
import weaponmaster.powers.OffenceStancePower;
import weaponmaster.relics.PlayerEquipment;
import weaponmaster.WeaponMaster;
import weaponmaster.WeaponMaster.Stance;
import weaponmaster.patches.AbstractCardEnum;

public class WeaponMasterPlayer extends CustomPlayer {

    public static final String ID = WeaponMaster.makeID("WeaponMasterPlayer");
    public static final CharacterStrings CHARACTER_STRINGS = CardCrawlGame.languagePack.getCharacterString(ID);
    public static final String[] NAMES = CHARACTER_STRINGS.NAMES;
    public static final String[] TEXT = CHARACTER_STRINGS.TEXT;

    //starter values
    public static final int STARTER_HP = 75;
    public static final int MAX_HP = 75;
    public static final int MAX_ORBS = 0;
    public static final int GOLD = 99;
    public static final int CARD_DRAW = 5;
    
    public static final int ENERGY_PER_TURN = 3;

    //player assets
    public static final String PLAYER_TEXTURES_LOCATION = "images/char/WeaponMaster/";
    public static final String PLAYER_BUTTON = "characterButton.png";
    public static final String PLAYER_PORTRAIT = "characterPortrait.jpg";
    public static final String PLAYER_SHOULDER_1 = "shoulder.png";
    public static final String PLAYER_SHOULDER_2 = "shoulder2.png";
    public static final String PLAYER_CORPSE = "corpse.png";
    public static final String PLAYER_SKELETON_ATLAS = "skeleton.atlas";
    public static final String PLAYER_SKELETON_JSON = "skeleton.json";
    public static final String PLAYER_MODEL = "model.g3dj";
    public static final String PLAYER_ANIMATION = "character_Render|idle";
    public static final String[] ORBTEXTURES = {
        PLAYER_TEXTURES_LOCATION + "orb/layer1.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer2.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer3.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer4.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer5.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer6.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer1d.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer2d.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer3d.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer4d.png",
        PLAYER_TEXTURES_LOCATION + "orb/layer5d.png"
    };
    public static final String ORBVFX_LOCATION = "orb/vfx.png";

    public Stance stance = Stance.OFFENCE;

    public WeaponMasterPlayer(String name, PlayerClass setClass) {
        super(name, setClass, ORBTEXTURES, PLAYER_TEXTURES_LOCATION + ORBVFX_LOCATION, null);//PLAYER_TEXTURES_LOCATION + PLAYER_MODEL, PLAYER_ANIMATION);
        
        this.dialogX = this.drawX + 0.0f * Settings.scale;              //< ?
        this.dialogY = this.drawY + 170.0f * Settings.scale;

        initializeClass(null, PLAYER_TEXTURES_LOCATION + PLAYER_SHOULDER_2, PLAYER_TEXTURES_LOCATION + PLAYER_SHOULDER_1, 
            PLAYER_TEXTURES_LOCATION + PLAYER_CORPSE, getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); /*hb_x, hb_y, hb_w, hb_h < ?*/
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public CardColor getCardColor() {
        return AbstractCardEnum.WEAPONMASTER_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.MAROON;                                    // <<<
    }

    @Override
    public Color getCardTrailColor() {
        return AbstractCardEnum.CARD_BG_COLOR;                  // <<<
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return null;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0], STARTER_HP, MAX_HP, MAX_ORBS, GOLD, CARD_DRAW,
            this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public Color getSlashAttackColor() {
        return AbstractCardEnum.CARD_BG_COLOR;                     // <<<
    }

    @Override
    public AttackEffect[] getSpireHeartSlashEffect() {
        return null;
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];                              // <<<
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> startingDeck = new ArrayList<>();
        //startingDeck.Add("kaard id naam");
        return startingDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> startingRelics = new ArrayList<>();
        startingRelics.add(PlayerEquipment.ID);
        UnlockTracker.markRelicAsSeen(PlayerEquipment.ID);
        return startingRelics;
    }

    @Override
    public String getTitle(PlayerClass arg0) {
        return NAMES[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];                                       // <<<
    }

    @Override
    public AbstractPlayer newInstance() {
        return new WeaponMasterPlayer(this.name, PlayerClassEnum.WEAPON_MASTER_PLAYER);
    }
    
    //questionable
    @Override
    public void preBattlePrep() {
        super.preBattlePrep();
        this.stance = Stance.OFFENCE;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new OffenceStancePower(this), 0));
    }

}