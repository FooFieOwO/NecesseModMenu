package modmenu.features.commands;

import modmenu.ModMenu;
import modmenu.data.playerdata.PlayerData;
import necesse.engine.commands.CmdParameter;
import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.commands.parameterHandlers.ServerClientParameterHandler;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.entity.mobs.PlayerMob;

public class GodCommand extends ModularChatCommand {

    public GodCommand() {
        super("god", "This command sets a player to godmode", PermissionLevel.ADMIN, true,
                new CmdParameter("player", new ServerClientParameterHandler()));
    }

    @Override
    public void runModular(Client client, Server server, ServerClient serverClient, Object[] args, String[] errors, CommandLog logs) {
        final ServerClient target = (ServerClient) args[0];

        if (target != null) {
            final PlayerMob playerMob = target.playerMob;
            final PlayerData playerData = ModMenu.instance.playerDataManger.get(playerMob);

            if (playerData.godMode) {
                playerData.godMode = false;
                logs.add("Godmode disabled for player " + playerMob.getDisplayName() + "!");
            } else {
                playerData.godMode = true;
                logs.add("Godmode enabled for player " + playerMob.getDisplayName() + "!");
            }
        } else {
            logs.add("Cannot find player");
        }
    }
}
