package modmenu.features.commands;

import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.engine.steam.SteamData;

import java.util.Arrays;
import java.util.List;

public class UnlockAchievements extends ModularChatCommand {

    public UnlockAchievements() {
        super("unlockachievements", "This command will unlock all steam achievements", PermissionLevel.ADMIN, true);
    }

    private final List<String> achievementIds = Arrays.asList(
            "defeat_boss",
            "get_pet",
            "start_settlement",
            "set_spawn",
            "enchant_item",
            "magical_drop",
            "village_helper",
            "hoarder",
            "self_proclaimed",
            "double_catch",
            "complete_host",
            "getting_hot",
            "my_jam",
            "cloud_nine",
            "one_tapped",
            "too_easy",
            "headhunter",
            "rematch",
            "defeat_pirate",
            "grave_digger",
            "equip_ability",
            "obtain_items",
            "visit_biomes",
            "fish_up",
            "run_marathon",
            "play_24h"
    );

    @Override
    public void runModular(Client client, Server server, ServerClient serverClient, Object[] args, String[] errors, CommandLog logs) {
        for (final String id : this.achievementIds) {
            SteamData.setAchievement(id);
        }

        SteamData.forceStoreStatsAndAchievements();
    }
}
