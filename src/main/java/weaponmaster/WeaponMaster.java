package weaponmaster;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import basemod.BaseMod;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import weaponmaster.characters.WeaponMasterPlayer;
import weaponmaster.patches.WeaponMasterCardEnum;
import weaponmaster.patches.PlayerClassEnum;

@SpireInitializer
public class WeaponMaster implements PostInitializeSubscriber, EditCharactersSubscriber{

    public static final String ASSETS = "image";

    public WeaponMaster() {
        BaseMod.subscribe(this);    // V Same color for everything atm V
        BaseMod.addColor(WeaponMasterCardEnum.WEAPONMASTER_COLOR, WeaponMasterCardEnum.CARD_COLOR, WeaponMasterCardEnum.CARD_COLOR, WeaponMasterCardEnum.CARD_COLOR,
            WeaponMasterCardEnum.CARD_COLOR, WeaponMasterCardEnum.CARD_COLOR, WeaponMasterCardEnum.CARD_COLOR, WeaponMasterCardEnum.CARD_COLOR, 
            WeaponMasterCardEnum.CARD_ATTACK, WeaponMasterCardEnum.CARD_SKILL, WeaponMasterCardEnum.CARD_POWER, WeaponMasterCardEnum.CARD_ENERGY_ORB, 
            WeaponMasterCardEnum.CARD_ATTACK_PORTRAIT, WeaponMasterCardEnum.CARD_SKILL_PORTRAIT, WeaponMasterCardEnum.CARD_POWER_PORTRAIT, WeaponMasterCardEnum.CARD_ENERGY_ORB_PORTRAIT);
    }

    public static void initialize() {
        new WeaponMaster();
    }

    @Override
    public void receivePostInitialize() {

    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new WeaponMasterPlayer(WeaponMasterPlayer.NAME, PlayerClassEnum.WEAPON_MASTER_PLAYER), WeaponMasterPlayer.PLAYER_TEXTURES_LOCATION + WeaponMasterPlayer.PLAYER_BUTTON, 
            WeaponMasterPlayer.PLAYER_TEXTURES_LOCATION + WeaponMasterPlayer.PLAYER_PORTRAIT, PlayerClassEnum.WEAPON_MASTER_PLAYER);
    }
    

}
