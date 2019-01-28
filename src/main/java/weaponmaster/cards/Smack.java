
package weaponmaster.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import weaponmaster.WeaponMaster;
import weaponmaster.characters.WeaponMasterPlayer;
import weaponmaster.patches.AbstractCardEnum;

public class Smack extends CustomCard {
    public static final String ID = WeaponMaster.makeID("Smack");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String IMG = WeaponMaster.makeResourcePath("cards/placeholder.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final AbstractCard.CardType CARD_TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor CARD_COLOR = AbstractCardEnum.WEAPONMASTER_COLOR;
    private static final AbstractCard.CardRarity CARD_RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget CARD_TARGET = AbstractCard.CardTarget.ENEMY;
    private static final int AMOUNT_A = 11;
    private static final int AMOUNT_M = 1;
    private static final int UPGRADE_PLUS_AMOUNT = 1;

    public Smack() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CARD_TYPE, CARD_COLOR, CARD_RARITY, CARD_TARGET);
        this.baseDamage = this.damage = AMOUNT_A;
        this.magicNumber = this.baseMagicNumber = AMOUNT_M;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Smack();
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
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage)));
        if (p instanceof WeaponMasterPlayer){
            AbstractPower effect = null;
            switch (((WeaponMasterPlayer)p).stance) {
                    case ATTACK: 
                    effect = new VulnerablePower(m, this.magicNumber, false);
                    break;
                case DEFEND:
                    effect = new WeakPower(m, this.magicNumber, false);
                    break;
            }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, effect, this.magicNumber));
        }
    }
}