package modmenu.features.methodpatches;

import modmenu.utils.reflection.FieldReflection;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.world.WorldEntity;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.level.gameObject.SurfaceGrassObject;
import necesse.level.maps.Level;
import net.bytebuddy.asm.Advice;

import java.util.Iterator;

@ModMethodPatch(target = Mob.class, name = "setHealthHidden", arguments = {int.class, float.class, float.class, Attacker.class})
public class SetHealthPatch {

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    static boolean onEnter(@Advice.This Mob mob) {
        if (mob instanceof PlayerMob) {
            FieldReflection.setFieldValue(Mob.class, mob, "isStatic", false, true);
        }

        return false;
    }

    @Advice.OnMethodExit
    static void onExit(@Advice.This Mob mob) {
        if (mob instanceof PlayerMob) {
            FieldReflection.setFieldValue(Mob.class, mob, "isStatic", false, false);
        }
    }

}
