package modmenu.client.buffs;

import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

public class InfinityAmmoBuff extends Buff {

    public InfinityAmmoBuff() {
        canCancel = false;
        isVisible = false;
        shouldSave = false;
    }

    @Override
    public void init(ActiveBuff activeBuff) {
        activeBuff.setModifier(BuffModifiers.ARROW_USAGE, -100000f);
        activeBuff.setModifier(BuffModifiers.BULLET_USAGE, -100000f);
    }

}