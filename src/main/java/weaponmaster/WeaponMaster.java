package weaponmaster;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.Keyword;
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
import weaponmaster.characters.WeaponMasterPlayer;
import weaponmaster.patches.AbstractCardEnum;
import weaponmaster.patches.PlayerClassEnum;
import weaponmaster.patches.WeaponMasterEnum;
import weaponmaster.relics.PlayerEquipment;
import weaponmaster.cards.*;

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
            WeaponMasterEnum.CARD_ATTACK_PORTRAIT, WeaponMasterEnum.CARD_SKILL_PORTRAIT, WeaponMasterEnum.CARD_POWER_PORTRAIT, WeaponMasterEnum.CARD_ENERGY_ORB_PORTRAIT);
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
        Type typeToken = new TypeToken<Map<String, Keyword>>(){}.getType();
        Gson gson = new Gson();
        String strings = loadJson("localization/eng/WeaponMaster-KeywordStrings.json");
        @SuppressWarnings("unchecked")
        Map<String,Keyword> keywords = (Map<String,Keyword>)gson.fromJson(strings, typeToken);
        for (Keyword kw : keywords.values()) {
            BaseMod.addKeyword(kw.NAMES, kw.DESCRIPTION);
        }
    }
    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }
    
}
