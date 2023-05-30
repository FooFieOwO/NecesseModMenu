package modmenu.menu;

import necesse.engine.control.InputEvent;
import necesse.engine.network.client.Client;
import necesse.engine.tickManager.TickManager;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.FormManager;
import necesse.gfx.forms.components.FormTextButton;

public class CheatMenu extends Form {

    private final FormManager formManager;
    private final Client client;
    public boolean shown;

    public CheatMenu(final FormManager formManager, final Client client) {
        super("Attributes", 500, 330);
        this.setPosition(700, 150);

        this.formManager = formManager;
        this.client = client;
    }

    @Override
    public void handleInputEvent(final InputEvent event, final TickManager tickManager, final PlayerMob perspective) {
        super.handleInputEvent(event, tickManager, perspective);

        if (event.isKeyboardEvent() && event.getID() == 256) {
            this.shown = false;
            this.formManager.removeComponent(this);
        }
    }

    public void initMenu() {
        this.addComponent(new FormTextButton("GodMode", "Enables your godmode", 10, 10, 100).onClicked(e -> {
            System.out.println("GodMode");
        }));
    }

}
