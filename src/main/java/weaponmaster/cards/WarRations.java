package weaponmaster.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import weaponmaster.WeaponMaster;
import weaponmaster.patches.AbstractCardEnum;

public class WarRations extends CustomCard {
    public static final String ID = WeaponMaster.makeID("WarRations");
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

    private static final int AMOUNT = 2;

    public WarRations() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CARD_TYPE, CARD_COLOR, CARD_RARITY, CARD_TARGET);
        this.baseDraw = this.draw = AMOUNT;
        this.exhaust = true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new WarRations();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.exhaust = false;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.draw));
    }
}