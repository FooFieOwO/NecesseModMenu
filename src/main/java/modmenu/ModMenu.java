package modmenu;

import modmenu.features.commands.GodCommand;
import necesse.engine.commands.CommandsManager;
import necesse.engine.modLoader.annotations.ModEntry;

@ModEntry
public class ModMenu {

    public void init() {

    }

    public void initResources() {

    }

    public void postInit() {
        CommandsManager.registerServerCommand(new GodCommand());
    }

}
