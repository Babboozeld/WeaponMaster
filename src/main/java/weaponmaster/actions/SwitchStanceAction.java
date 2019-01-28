package weaponmaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import weaponmaster.WeaponMaster.Stance;
import weaponmaster.characters.WeaponMasterPlayer;
import weaponmaster.powers.AttackStancePower;
import weaponmaster.powers.DefendStancePower;

public class SwitchStanceAction extends AbstractGameAction {

    private Stance switchTo;
    private AbstractPlayer p;

    public SwitchStanceAction(AbstractPlayer p) {
        if (p instanceof WeaponMasterPlayer) {
            this.p = p;
            this.switchTo = ((WeaponMasterPlayer)this.p).stance == Stance.ATTACK ? Stance.DEFEND : Stance.ATTACK;
        }
    }

    public SwitchStanceAction(AbstractPlayer p, Stance switchTo) {
        if (p instanceof WeaponMasterPlayer) {
            this.p = p;
            this.switchTo = switchTo;
        }
    }

    @Override
    public void update() {
        if (this.switchTo != null) {
            String removePower = "";
            AbstractPower applyPower = null;
            switch (this.switchTo) {
                case ATTACK:
                    removePower = DefendStancePower.ID_NAME;
                    applyPower = new AttackStancePower(p);
                    break;
                case DEFEND:
                    removePower = AttackStancePower.ID_NAME;
                    applyPower = new DefendStancePower(p);
                    break;
            }
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.p, this.p, removePower));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, applyPower, 0));
            ((WeaponMasterPlayer)this.p).stance = switchTo;
        }
        this.isDone = true;
    }

}