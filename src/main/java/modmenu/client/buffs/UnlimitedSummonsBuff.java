package modmenu.client.buffs;

import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;
public class UnlimitedSummonsBuff extends Buff {

    public UnlimitedSummonsBuff() {
        canCancel = false;
        isVisible = false;
        shouldSave = true;
    }

    @Override
    public void init(ActiveBuff activeBuff) {
        activeBuff.setModifier(BuffModifiers.MAX_SUMMONS, 1000); // +1000 Max Summons
    }

}