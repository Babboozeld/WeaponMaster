package weaponmaster.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import basemod.abstracts.CustomCard;
import weaponmaster.WeaponMaster;
import weaponmaster.WeaponMaster.Stance;
import weaponmaster.characters.WeaponMasterPlayer;
import weaponmaster.patches.AbstractCardEnum;

public class QuickReflexes extends CustomCard {
    public static final String ID = WeaponMaster.makeID("QuickReflexes");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMG = WeaponMaster.makeResourcePath("cards/placeholder_skill.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    private static final AbstractCard.CardType CARD_TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor CARD_COLOR = AbstractCardEnum.WEAPONMASTER_COLOR;
    private static final AbstractCard.CardRarity CARD_RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget CARD_TARGET = AbstractCard.CardTarget.SELF;

    private static final int AMOUNT = 7;
    private static final int UPGRADE_PLUS_AMOUNT = 3;

    public QuickReflexes() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CARD_TYPE, CARD_COLOR, CARD_RARITY, CARD_TARGET);
        this.baseBlock = this.block = AMOUNT;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public AbstractCard makeCopy() {
        return new QuickReflexes();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.upgradeBlock(UPGRADE_PLUS_AMOUNT);
            this.upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int plusAmount = 0;
        if(p instanceof WeaponMasterPlayer && p.hasPower(DexterityPower.POWER_ID)){
            if (((WeaponMasterPlayer)p).stance == Stance.DEFENSE) {
                plusAmount = p.getPower(DexterityPower.POWER_ID).amount;
                if (this.upgraded) plusAmount += plusAmount; 
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block + plusAmount));
    }
}
