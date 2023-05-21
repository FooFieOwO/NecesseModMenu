package modmenu.features.methodpatches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import net.bytebuddy.asm.Advice;

import java.util.ArrayList;
import java.util.List;

@ModMethodPatch(target = Mob.class, name = "setHealthHidden", arguments = {int.class, float.class, float.class, Attacker.class})
public class SetHealthPatch {

    public static final List<PlayerMob> players = new ArrayList<>();

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    static boolean onEnter(@Advice.This Mob mob, @Advice.Argument(0) int health) {
        if (mob instanceof PlayerMob) {
            final PlayerMob playerMob = (PlayerMob) mob;

            if (players.contains(playerMob) && mob.getHealth() > health)
                return true;
        }

        return false;
    }

}
