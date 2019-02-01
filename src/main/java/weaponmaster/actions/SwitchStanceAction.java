package weaponmaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import weaponmaster.WeaponMaster.Stance;
import weaponmaster.characters.WeaponMasterPlayer;
import weaponmaster.powers.DefenseStancePower;
import weaponmaster.powers.OffenseStancePower;

public class SwitchStanceAction extends AbstractGameAction {

    private Stance switchTo;
    private AbstractPlayer p;

    public SwitchStanceAction(AbstractPlayer p) {
        if (p instanceof WeaponMasterPlayer) {
            this.p = p;
            this.switchTo = ((WeaponMasterPlayer)this.p).stance == Stance.OFFENSE ? Stance.DEFENSE : Stance.OFFENSE;
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
                case OFFENSE:
                    removePower = DefenseStancePower.ID_NAME;
                    applyPower = new OffenseStancePower(p);
                    break;
                case DEFENSE:
                    removePower = OffenseStancePower.ID_NAME;
                    applyPower = new DefenseStancePower(p);
                    break;
            }
            if (p.hasPower(removePower)) AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.p, this.p, removePower));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, applyPower, 0));
            ((WeaponMasterPlayer)this.p).stance = switchTo;
        }
        this.isDone = true;
    }

}
