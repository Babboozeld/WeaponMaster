package weaponmaster.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import basemod.abstracts.CustomCard;
import weaponmaster.WeaponMaster;
import weaponmaster.patches.AbstractCardEnum;

public class CrossingSwords extends CustomCard {
    public static final String ID = WeaponMaster.makeID("CrossingSwords");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMG = WeaponMaster.makeResourcePath("cards/placeholder_power.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final AbstractCard.CardType CARD_TYPE = AbstractCard.CardType.POWER;
    public static final AbstractCard.CardColor CARD_COLOR = AbstractCardEnum.WEAPONMASTER_COLOR;
    private static final AbstractCard.CardRarity CARD_RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget CARD_TARGET = AbstractCard.CardTarget.SELF;

    private static final int AMOUNT = 3;
    private static final int UPGRADE_PLUS_AMOUNT = 1;

    public CrossingSwords() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CARD_TYPE, CARD_COLOR, CARD_RARITY, CARD_TARGET);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
    }

    @Override
    public AbstractCard makeCopy() {
        return new CrossingSwords();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_AMOUNT);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
    }
}