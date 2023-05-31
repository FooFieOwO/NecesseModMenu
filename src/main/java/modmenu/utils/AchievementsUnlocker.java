package modmenu.utils;

import necesse.engine.steam.SteamData;

import java.util.Arrays;
import java.util.List;

public class AchievementsUnlocker {

    private static final List<String> achievementIds = Arrays.asList(
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

    public static void unlock() {
        for (final String id : achievementIds) {
            SteamData.setAchievement(id);
        }

        SteamData.forceStoreStatsAndAchievements();
    }

}
