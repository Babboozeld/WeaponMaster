package weaponmaster.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.abstracts.CustomCard;
import weaponmaster.WeaponMaster;
import weaponmaster.WeaponMaster.Stance;
import weaponmaster.characters.WeaponMasterPlayer;
import weaponmaster.patches.AbstractCardEnum;

public class Pushover extends CustomCard {
    public static final String ID = WeaponMaster.makeID("Pushover");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMG = WeaponMaster.makeResourcePath("cards/placeholder_attack.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    private static final AbstractCard.CardType CARD_TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor CARD_COLOR = AbstractCardEnum.WEAPONMASTER_COLOR;
    private static final AbstractCard.CardRarity CARD_RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget CARD_TARGET = AbstractCard.CardTarget.ENEMY;

    private static final int AMOUNT = 7;
    private static final int UPGRADE_PLUS_AMOUNT = 3;

    public Pushover() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CARD_TYPE, CARD_COLOR, CARD_RARITY, CARD_TARGET);
        this.baseDamage = this.damage = AMOUNT;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Pushover();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.upgradeDamage(UPGRADE_PLUS_AMOUNT);
            this.upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int plusAmount = 0;
        if(p instanceof WeaponMasterPlayer && p.hasPower(StrengthPower.POWER_ID)){
            if (((WeaponMasterPlayer)p).stance == Stance.OFFENSE) {
                plusAmount = p.getPower(StrengthPower.POWER_ID).amount;
                if (this.upgraded) plusAmount += plusAmount; 
            }
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage + plusAmount)));
    }
}
