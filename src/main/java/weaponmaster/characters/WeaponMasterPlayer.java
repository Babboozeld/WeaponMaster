package weaponmaster.characters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import basemod.abstracts.CustomPlayer;
import weaponmaster.patches.PlayerClassEnum;
import weaponmaster.patches.WeaponMasterCardEnum;

public class WeaponMasterPlayer extends CustomPlayer {

    public static final String NAME = "The Weapon Master";
    public static final String DESCRIPTION = "A skilled fighter that mastered the arts of his weapons, that knows when to defend and attack.";

    //starter values
    public static final int STARTER_HP = 75;
    public static final int MAX_HP = 75;
    public static final int MAX_ORBS = 0;
    public static final int GOLD = 99;
    public static final int CARD_DRAW = 5;
    
    public static final int ENERGY_PER_TURN = 3;

    //player assets
    public static final String PLAYER_TEXTURES_LOCATION = "images/char/WeaponMaster/";
    public static final String PLAYER_BUTTON = "necromancerButton.png";
    public static final String PLAYER_PORTRAIT = "necromancerPortrait.jpg";
    public static final String PLAYER_SHOULDER_1 = "shoulder.png";
    public static final String PLAYER_SHOULDER_2 = "shoulder2.png";
    public static final String PLAYER_CORPSE = "corpse.png";
    public static final String PLAYER_SKELETON_ATLAS = "skeleton.atlas";
    public static final String PLAYER_SKELETON_JSON = "skeleton.json";
    public static final String PLAYER_MODEL = "model.g3dj";
    public static final String PLAYER_ANIMATION = "Necromancer_Render|idle";
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

    public WeaponMasterPlayer(String name, PlayerClass setClass) {
        super(name, setClass, ORBTEXTURES, PLAYER_TEXTURES_LOCATION + ORBVFX_LOCATION, PLAYER_TEXTURES_LOCATION + PLAYER_MODEL, PLAYER_ANIMATION);
        
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
        return WeaponMasterCardEnum.WEAPONMASTER_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.MAROON;
    }

    @Override
    public Color getCardTrailColor() {
        return WeaponMasterCardEnum.CARD_COLOR;
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
        return new CharSelectInfo(NAME, DESCRIPTION, STARTER_HP, MAX_HP, MAX_ORBS, GOLD, CARD_DRAW,
            this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAME;
    }

    @Override
    public Color getSlashAttackColor() {
        return WeaponMasterCardEnum.CARD_COLOR;
    }

    @Override
    public AttackEffect[] getSpireHeartSlashEffect() {
        return null;
    }

    @Override
    public String getSpireHeartText() {
        return "NL Spire heart text.";
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
        //startingRelics.Add("relic id naam");
        //UnlockTracker.markRelicAsSeen("Vampire_Amulet"); ?
        return startingRelics;
    }

    @Override
    public String getTitle(PlayerClass arg0) {
        return NAME;
    }

    @Override
    public String getVampireText() {
        return "vampire text";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new WeaponMasterPlayer(NAME, PlayerClassEnum.WEAPON_MASTER_PLAYER);
    }
    
}