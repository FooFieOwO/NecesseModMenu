package modmenu.client.buffs;

import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

public class SuperSummonsBuff extends Buff {

    public SuperSummonsBuff() {
        canCancel = false;
        isVisible = false;
        shouldSave = false;
    }

    @Override
    public void init(ActiveBuff activeBuff) {
        activeBuff.setModifier(BuffModifiers.SUMMONS_SPEED, 20f); // +20 Speed
        activeBuff.setModifier(BuffModifiers.SUMMON_ATTACK_SPEED, 100f); // +100 ATS
    }

}