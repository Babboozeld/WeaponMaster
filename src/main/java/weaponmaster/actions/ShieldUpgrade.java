package weaponmaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import weaponmaster.relics.ShieldLevel;

public class ShieldUpgrade extends AbstractGameAction {

    private int amount;

    public ShieldUpgrade(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasRelic(ShieldLevel.ID)){
            ((ShieldLevel)AbstractDungeon.player.getRelic(ShieldLevel.ID)).upgrade(amount);
        }
        this.isDone = true;
    }
}