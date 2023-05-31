package modmenu.client.menu;

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

    public CurrentMenu currentMenu = CurrentMenu.Main;

    public CheatMenu(final FormManager formManager, final Client client) {
        super("Cheats", 500, 330);
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
        for (int i = 1; i < CurrentMenu.values().length; i++) {
            final CurrentMenu menu = CurrentMenu.values()[i];
            final FormTextButton button = new FormTextButton(menu.name, menu.description, 10, 10 + (12 * ((i - 1) * 3)), 100);

            button.onClicked(e -> {
                this.clearComponents();
                this.currentMenu = menu;
                this.initPane();
            });

            this.addComponent(button);
        }
    }

    public void initPane() {
        final FormTextButton button = new FormTextButton("<", "", 5, 5, 30);

        button.onClicked(e -> {
            this.clearComponents();
            this.currentMenu = CurrentMenu.Main;
            this.initMenu();
        });

        this.addComponent(button);
    }

}
