package weaponmaster;

import java.nio.charset.StandardCharsets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import weaponmaster.cards.ArtOfWar;
import weaponmaster.cards.CrossingSwords;
import weaponmaster.cards.DefenceIsOffence;
import weaponmaster.cards.Defend;
import weaponmaster.cards.HammerAndAnvil;
import weaponmaster.cards.Leap;
import weaponmaster.cards.LessonsOfWar;
import weaponmaster.cards.OffenceIsDefence;
import weaponmaster.cards.QuickReflexes;
import weaponmaster.cards.Smack;
import weaponmaster.cards.SpartanKick;
import weaponmaster.cards.StanceSwitch;
import weaponmaster.cards.Strike;
import weaponmaster.cards.TheHighGround;
import weaponmaster.cards.WarOfAttrition;
import weaponmaster.characters.WeaponMasterPlayer;
import weaponmaster.patches.AbstractCardEnum;
import weaponmaster.patches.PlayerClassEnum;
import weaponmaster.patches.WeaponMasterEnum;
import weaponmaster.relics.PlayerEquipment;

@SpireInitializer
public class WeaponMaster implements PostInitializeSubscriber, EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditStringsSubscriber, EditKeywordsSubscriber { 

    public static final String ASSETS = "images";
    public static final String MOD_NAME = "WeaponMaster";
    private static final String MODNAME = "Weapon Master";
    private static final String AUTHOR = "Babboozeld";
    private static final String DESCRIPTION = "A mod that adds the Weapon Master character to the game Slay the Spire.";
    
    public enum Stance {
        OFFENCE, DEFENCE
    }

    public WeaponMaster() {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.WEAPONMASTER_COLOR, WeaponMasterEnum.CARD_BG_COLOR, WeaponMasterEnum.CARD_BACK_COLOR, WeaponMasterEnum.CARD_FRAME_COLOR,
            WeaponMasterEnum.CARD_FRAME_OUTLINE_COLOR, WeaponMasterEnum.CARD_DESC_BOX_COLOR, WeaponMasterEnum.CARD_TRAIL_VFX_COLOR, WeaponMasterEnum.CARD_GLOW_COLOR, 
            WeaponMasterEnum.CARD_ATTACK, WeaponMasterEnum.CARD_SKILL, WeaponMasterEnum.CARD_POWER, WeaponMasterEnum.CARD_ENERGY_ORB, 
            WeaponMasterEnum.CARD_ATTACK_PORTRAIT, WeaponMasterEnum.CARD_SKILL_PORTRAIT, WeaponMasterEnum.CARD_POWER_PORTRAIT, 
            WeaponMasterEnum.CARD_ENERGY_ORB_PORTRAIT, WeaponMasterEnum.CARD_SMALL_ENERGY_ORB);
    }

    public static final String makeResourcePath(String resource) {
		return ASSETS + "/" + resource;
    }

    public static String makeID(String idText) {
        return MOD_NAME + ":" + idText;
    }

    public static void initialize() {
        new WeaponMaster();
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture(WeaponMasterEnum.BADGE_IMAGE);
        //Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("No settings as of now.", 400.0f, 700.0f,
            settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }

    @Override
    public void receiveEditCards() {
        // Add cards:
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new Smack());
        BaseMod.addCard(new StanceSwitch());
        BaseMod.addCard(new ArtOfWar());
        BaseMod.addCard(new LessonsOfWar());
        BaseMod.addCard(new WarOfAttrition());
        BaseMod.addCard(new CrossingSwords());
        BaseMod.addCard(new TheHighGround());
        BaseMod.addCard(new HammerAndAnvil());
        BaseMod.addCard(new Leap());
        BaseMod.addCard(new DefenceIsOffence());
        BaseMod.addCard(new SpartanKick());
        BaseMod.addCard(new OffenceIsDefence());
        BaseMod.addCard(new QuickReflexes());

        // Unlock cards:
        UnlockTracker.unlockCard(Strike.ID);
        UnlockTracker.unlockCard(Defend.ID);
        UnlockTracker.unlockCard(Smack.ID);
        UnlockTracker.unlockCard(StanceSwitch.ID);
        UnlockTracker.unlockCard(ArtOfWar.ID);
        UnlockTracker.unlockCard(LessonsOfWar.ID);
        UnlockTracker.unlockCard(WarOfAttrition.ID);
        UnlockTracker.unlockCard(CrossingSwords.ID);
        UnlockTracker.unlockCard(TheHighGround.ID);
        UnlockTracker.unlockCard(HammerAndAnvil.ID);
        UnlockTracker.unlockCard(Leap.ID);
        UnlockTracker.unlockCard(DefenceIsOffence.ID);
        UnlockTracker.unlockCard(SpartanKick.ID);
        UnlockTracker.unlockCard(OffenceIsDefence.ID);
        UnlockTracker.unlockCard(QuickReflexes.ID);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new PlayerEquipment(), AbstractCardEnum.WEAPONMASTER_COLOR);
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new WeaponMasterPlayer(WeaponMasterPlayer.NAMES[0], PlayerClassEnum.WEAPON_MASTER_PLAYER), WeaponMasterPlayer.PLAYER_TEXTURES_LOCATION + WeaponMasterPlayer.PLAYER_BUTTON, 
            WeaponMasterPlayer.PLAYER_TEXTURES_LOCATION + WeaponMasterPlayer.PLAYER_PORTRAIT, PlayerClassEnum.WEAPON_MASTER_PLAYER);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/eng/WeaponMaster-CardStrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "localization/eng/WeaponMaster-CharacterStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/eng/WeaponMaster-PowerStrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/eng/WeaponMaster-RelicStrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal("localization/eng/WeaponMaster-KeywordStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    
}
