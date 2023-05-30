package modmenu.features.methodpatches;

import modmenu.menu.CheatInventoryButton;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.client.Client;
import necesse.gfx.forms.MainGameFormManager;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = MainGameFormManager.class, name = "setup", arguments = {})
public class FormPatch {

    @Advice.OnMethodExit
    public static void onExit(@Advice.This final MainGameFormManager formManager, @Advice.FieldValue("client") final Client client) {
        formManager.rightQuickbar.addButton(new CheatInventoryButton(formManager, client));
        formManager.rightQuickbar.updateButtons();
    }

}
 