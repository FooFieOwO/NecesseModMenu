package modmenu.features.listener;

import modmenu.ModMenu;
import necesse.engine.GameEventListener;
import necesse.engine.events.ServerClientDisconnectEvent;

public class ServerClientDisconnectListener extends GameEventListener<ServerClientDisconnectEvent> {

    @Override
    public void onEvent(final ServerClientDisconnectEvent event) {
        ModMenu.instance.playerDataManger.create(event.client.playerMob);
    }

}
