package modmenu.client.buffs;

import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

public class ManyMobsBuff extends Buff {

    public ManyMobsBuff() {
        canCancel = false;
        isVisible = false;
        shouldSave = false;
    }

    @Override
    public void init(ActiveBuff activeBuff) {
        activeBuff.setModifier(BuffModifiers.MOB_SPAWN_RATE, 1000f); // Massive Spawns
        activeBuff.setModifier(BuffModifiers.MOB_SPAWN_CAP, 100f); // 100er Cap
    }

}