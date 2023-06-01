package modmenu;

import modmenu.client.ClientSideStash;
import modmenu.client.commands.UnlockAchievements;
import modmenu.client.listener.ServerClientConnectListener;
import modmenu.client.listener.ServerClientDisconnectListener;
import modmenu.server.commands.GodCommand;
import modmenu.server.playerdata.PlayerDataManger;
import necesse.engine.GameEvents;
import necesse.engine.commands.CommandsManager;
import necesse.engine.events.ServerClientConnectedEvent;
import necesse.engine.events.ServerClientDisconnectEvent;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.*;

@ModEntry
public class ModMenu {

    public static ModMenu instance;

    public PlayerDataManger playerDataManger;
    public ClientSideStash clientSideStash = new ClientSideStash();

    public void init() {
        instance = this;

        this.playerDataManger = new PlayerDataManger();

        GameEvents.addListener(ServerClientDisconnectEvent.class, new ServerClientDisconnectListener());
        GameEvents.addListener(ServerClientConnectedEvent.class, new ServerClientConnectListener());
        BuffRegistry.registerBuff("unlimitedsummonsbuff", new UnlimitedSummonsBuff());
    }

    public void initResources() {
    }

    public void postInit() {
        CommandsManager.registerServerCommand(new GodCommand());
        CommandsManager.registerClientCommand(new UnlockAchievements());
    }

}
