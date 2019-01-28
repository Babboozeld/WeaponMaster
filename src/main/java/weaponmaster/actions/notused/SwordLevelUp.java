package weaponmaster.actions.notused;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import weaponmaster.relics.SwordLevel;

public class SwordLevelUp extends AbstractGameAction {

    private int amount;

    public SwordLevelUp(int amount){
        this.amount = amount;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasRelic(SwordLevel.ID)){
            ((SwordLevel)AbstractDungeon.player.getRelic(SwordLevel.ID)).levelUp(amount);
        }
        this.isDone = true;
    }
}