package modmenu.client.buffs;

import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

public class SuperMiningBuff extends Buff {

    public SuperMiningBuff() {
        canCancel = false;
        isVisible = false;
        shouldSave = false;
    }

    @Override
    public void init(ActiveBuff activeBuff) {
        activeBuff.setModifier(BuffModifiers.MINING_SPEED, 100f); // +100 Speed
        activeBuff.setModifier(BuffModifiers.MINING_RANGE, 20f); // +20 Range
    }

}