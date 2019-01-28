package weaponmaster.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import weaponmaster.relics.PlayerEquipment;

public class PlayerEquipmentBoostAction extends AbstractGameAction {

    private int amount;

    public PlayerEquipmentBoostAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasRelic(PlayerEquipment.ID)){
            ((PlayerEquipment)AbstractDungeon.player.getRelic(PlayerEquipment.ID)).boost(amount);
        }
        this.isDone = true;
    }
}