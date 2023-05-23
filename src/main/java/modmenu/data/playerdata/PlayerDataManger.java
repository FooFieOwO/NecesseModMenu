package modmenu.data.playerdata;

import necesse.entity.mobs.PlayerMob;

import java.util.HashMap;

public final class PlayerDataManger {

    private final HashMap<PlayerMob, PlayerData> dataHashMap = new HashMap<>();

    public PlayerData get(final PlayerMob playerMob) {
        return this.dataHashMap.get(playerMob);
    }

    public void create(final PlayerMob playerMob) {
        if (!this.dataHashMap.containsKey(playerMob))
            this.dataHashMap.put(playerMob, new PlayerData());
    }

    public void destroy(final PlayerMob playerMob) {
        this.dataHashMap.remove(playerMob);
    }

}
