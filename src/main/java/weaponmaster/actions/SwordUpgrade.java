package weaponmaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import weaponmaster.relics.SwordLevel;

public class SwordUpgrade extends AbstractGameAction {

    private int amount;

    public SwordUpgrade(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasRelic(SwordLevel.ID)){
            ((SwordLevel)AbstractDungeon.player.getRelic(SwordLevel.ID)).upgrade(amount);
        }
        this.isDone = true;
    }
}