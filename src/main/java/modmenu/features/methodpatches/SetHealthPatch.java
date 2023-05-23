package modmenu.features.methodpatches;

import modmenu.ModMenu;
import modmenu.data.playerdata.PlayerData;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = Mob.class, name = "setHealthHidden", arguments = {int.class, float.class, float.class, Attacker.class})
public class SetHealthPatch {

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    static boolean onEnter(@Advice.This Mob mob, @Advice.Argument(0) int health) {
        if (mob instanceof PlayerMob) {
            final PlayerMob playerMob = (PlayerMob) mob;
            final PlayerData playerData = ModMenu.instance.playerDataManger.get(playerMob);

            if (playerData != null && playerData.godMode && mob.getHealth() > health)
                return true;
        }

        return false;
    }

}
