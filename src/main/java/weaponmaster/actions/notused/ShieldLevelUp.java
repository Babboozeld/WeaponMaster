package weaponmaster.actions.notused;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import weaponmaster.relics.ShieldLevel;

public class ShieldLevelUp extends AbstractGameAction {

    private int amount;

    public ShieldLevelUp(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasRelic(ShieldLevel.ID)){
            ((ShieldLevel)AbstractDungeon.player.getRelic(ShieldLevel.ID)).levelUp(amount);
        }
        this.isDone = true;
    }
}