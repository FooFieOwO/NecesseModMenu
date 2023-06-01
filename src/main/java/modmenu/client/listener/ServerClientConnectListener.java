package modmenu.client.listener;

import modmenu.ModMenu;
import modmenu.client.buffs.UnlimitedSummonsBuff;
import necesse.engine.GameEventListener;
import necesse.engine.events.ServerClientConnectedEvent;
import necesse.engine.registries.BuffRegistry;
import necesse.entity.mobs.buffs.ActiveBuff;

public class ServerClientConnectListener extends GameEventListener<ServerClientConnectedEvent> {

    @Override
    public void onEvent(final ServerClientConnectedEvent event) {
        ModMenu.instance.playerDataManger.create(event.client.playerMob);
        necesse.entity.mobs.buffs.staticBuffs.Buff buff = BuffRegistry.getBuff("unlimitedsummonsbuff");
        ActiveBuff ab = new ActiveBuff(buff, event.client.playerMob, 999999999, null); // initiate and configure buff
        event.client.playerMob.buffManager.addBuff(ab, true); // set buff
    }

}
