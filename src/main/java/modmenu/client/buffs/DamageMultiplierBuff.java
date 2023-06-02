package modmenu.client.buffs;

import necesse.engine.network.client.Client;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

public class DamageMultiplierBuff extends Buff {

    public DamageMultiplierBuff() {
        canCancel = false;
        isVisible = false;
        shouldSave = false;
    }

    @Override
    public void init(ActiveBuff activeBuff) {
        activeBuff.setModifier(BuffModifiers.ALL_DAMAGE, 5f); // x5 Damage Multiplier
    }

}