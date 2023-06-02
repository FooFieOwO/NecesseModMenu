package modmenu.client.buffs;

import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

public class MaxHealthBuff extends Buff {

    public MaxHealthBuff() {
        canCancel = false;
        isVisible = false;
        shouldSave = false;
    }

    @Override
    public void init(ActiveBuff activeBuff) {
        activeBuff.setModifier(BuffModifiers.MAX_HEALTH, 10f);
    }

}