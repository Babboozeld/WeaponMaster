package weaponmaster.patches;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;

import weaponmaster.WeaponMaster;

public class WeaponMasterCardEnum {

    @SpireEnum
    public static AbstractCard.CardColor WEAPONMASTER_COLOR;
    
    //card colors
    public static final Color CARD_COLOR = CardHelper.getColor(255.0f, 250.0f, 250.0f);
    
    //card color assets
    public static final String CARD_ASSETS_FOLDER = WeaponMaster.ASSETS + "cards/";
	
	public static final String CARD_ATTACK = "512/bg_attack_white.png";
	public static final String CARD_SKILL = "512/bg_skill_white.png";
	public static final String CARD_POWER = "512/bg_power_white.png";
	public static final String CARD_ENERGY_ORB = "512/card_white_orb.png";
	
	public static final String CARD_ATTACK_PORTRAIT = "1024/bg_attack_white.png";
	public static final String CARD_SKILL_PORTRAIT = "1024/bg_skill_white.png";
    public static final String CARD_POWER_PORTRAIT = "1024/bg_power_white.png";
    public static final String CARD_ENERGY_ORB_PORTRAIT = "1024/card_white_orb.png";

}