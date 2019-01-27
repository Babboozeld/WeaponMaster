package weaponmaster.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import weaponmaster.WeaponMaster;

public class StanceSwitch extends CustomCard {
    public static final String ID = WeaponMaster.makeID("StanceSwitch");
    public static final String NAME = "Switch Stance";
    public static final String DESCRIPTION = "";
    public static final String IMG = WeaponMaster.makeResourcePath("cards/placeholder.png");
    public static final int COST = 0;
    public static final AbstractCard.CardType CARD_TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor CARD_COLOR = AbstractCard.CardColor.RED;
    public static final AbstractCard.CardRarity CARD_RARITY = AbstractCard.CardRarity.UNCOMMON; 
    public static final AbstractCard.CardTarget CARD_TARGET = AbstractCard.CardTarget.SELF;

    public StanceSwitch() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CARD_TYPE, CARD_COLOR, CARD_RARITY, CARD_TARGET);
    }

    @Override
    public AbstractCard makeCopy() {
        return new StanceSwitch();
    }


    @Override
    public void upgrade() {
        
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        
    }

    
}