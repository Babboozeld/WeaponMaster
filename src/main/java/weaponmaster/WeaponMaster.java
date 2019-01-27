package weaponmaster;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;

import basemod.BaseMod;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

import weaponmaster.characters.WeaponMasterPlayer;
import weaponmaster.patches.AbstractCardEnum;
import weaponmaster.patches.PlayerClassEnum;

@SpireInitializer
public class WeaponMaster implements PostInitializeSubscriber, EditCharactersSubscriber, EditStringsSubscriber{ //EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber

    public static final String ASSETS = "images";
    public static final String MOD_NAME = "WeaponMaster";
    //private static final String MODNAME = "Default Mod";
    //private static final String AUTHOR = "Gremious";
    //private static final String DESCRIPTION = "A base for Slay the Spire to start your own mod from, feat. the Default.";

    public WeaponMaster() {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.WEAPONMASTER_COLOR, AbstractCardEnum.CARD_BG_COLOR, AbstractCardEnum.CARD_BACK_COLOR, AbstractCardEnum.CARD_FRAME_COLOR,
            AbstractCardEnum.CARD_FRAME_OUTLINE_COLOR, AbstractCardEnum.CARD_DESC_BOX_COLOR, AbstractCardEnum.CARD_TRAIL_VFX_COLOR, AbstractCardEnum.CARD_GLOW_COLOR, 
            AbstractCardEnum.CARD_ATTACK, AbstractCardEnum.CARD_SKILL, AbstractCardEnum.CARD_POWER, AbstractCardEnum.CARD_ENERGY_ORB, 
            AbstractCardEnum.CARD_ATTACK_PORTRAIT, AbstractCardEnum.CARD_SKILL_PORTRAIT, AbstractCardEnum.CARD_POWER_PORTRAIT, AbstractCardEnum.CARD_ENERGY_ORB_PORTRAIT);
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

    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new WeaponMasterPlayer(WeaponMasterPlayer.NAMES[0], PlayerClassEnum.WEAPON_MASTER_PLAYER), WeaponMasterPlayer.PLAYER_TEXTURES_LOCATION + WeaponMasterPlayer.PLAYER_BUTTON, 
            WeaponMasterPlayer.PLAYER_TEXTURES_LOCATION + WeaponMasterPlayer.PLAYER_PORTRAIT, PlayerClassEnum.WEAPON_MASTER_PLAYER);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStrings(CardStrings.class, "localization/eng/WeaponMaster-CardStrings.json");
        BaseMod.loadCustomStrings(CharacterStrings.class, "localization/eng/WeaponMaster-CharacterStrings.json");
    }
    

}
