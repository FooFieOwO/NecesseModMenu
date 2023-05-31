package modmenu.client.commands;

import modmenu.utils.AchievementsUnlocker;
import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class UnlockAchievements extends ModularChatCommand {

    public UnlockAchievements() {
        super("unlockachievements", "This command will unlock all steam achievements", PermissionLevel.ADMIN, true);
    }

    @Override
    public void runModular(Client client, Server server, ServerClient serverClient, Object[] args, String[] errors, CommandLog logs) {
        AchievementsUnlocker.unlock();
    }
}
