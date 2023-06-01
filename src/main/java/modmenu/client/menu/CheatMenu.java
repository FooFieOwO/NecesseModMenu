package modmenu.client.menu;

import modmenu.utils.AchievementsUnlocker;
import necesse.engine.control.InputEvent;
import necesse.engine.network.client.Client;
import necesse.engine.tickManager.TickManager;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.fairType.FairType;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.FormManager;
import necesse.gfx.forms.components.FormTextBox;
import necesse.gfx.forms.components.FormTextButton;
import necesse.gfx.gameFont.FontOptions;

import java.awt.*;

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
        this.currentMenu = CurrentMenu.Main;

        for (int i = 1; i < CurrentMenu.values().length; i++) {
            final CurrentMenu menu = CurrentMenu.values()[i];

            this.addComponent(
                    new FormTextButton(menu.name, menu.description, 5, 5 + (12 * ((i - 1) * 3)), 100)
                            .onClicked(e -> {
                                this.clearComponents();
                                this.currentMenu = menu;
                                this.initPane();
                            })
            );
        }
    }

    public void initPane() {
        this.addComponent(new FormTextButton("<", "Back", 460, 5, 30).onClicked(e -> {
            this.clearComponents();
            this.initMenu();
        }));

        switch (this.currentMenu) {
            case Player:
                this.initPlayerMenu();
                break;
            case Server:
                this.initServerMenu();
                break;
            case Misc:
                this.initMiscMenu();
                break;
        }
    }

    public void initPlayerMenu() {
        final FormTextBox speedTextBox = new FormTextBox(new FontOptions(16), FairType.TextAlign.LEFT, Color.white, 5, 15, 80);
        speedTextBox.setText(String.valueOf(client.getPlayer().getSpeed()));
        this.addComponent(speedTextBox);
        this.addComponent(new FormTextButton("Apply", "", 90, 5, 150).onClicked(e -> {
            client.getPlayer().setSpeed(Float.parseFloat(speedTextBox.getText()));
        }));
    }

    public void initServerMenu() {

    }

    public void initMiscMenu() {
        this.addComponent(new FormTextButton("Unlock steam", "This Button will unlock all steam achievements!", 5, 5, 150).onClicked(e -> {
            AchievementsUnlocker.unlock();
        }));
    }

}
