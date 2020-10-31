package me.lucko.spark.skyfall;

import io.skyfallsdk.command.options.Command;
import io.skyfallsdk.command.options.CommandExecutor;
import io.skyfallsdk.command.options.Permission;
import io.skyfallsdk.command.options.Sender;
import io.skyfallsdk.command.parameter.argument.Arg;
import io.skyfallsdk.command.parameter.service.Service;
import me.lucko.spark.common.command.sender.CommandSender;

@Command(name = "spark", desc = "Main plugin command.")
@Permission(value = "spark")
public class SkyfallSparkCommand {

    @CommandExecutor
    public void onCommandExecute(@Sender CommandSender sender, @Arg(indexStart = 0) String[] subCommands, @Service SkyfallSparkPlugin plugin) {
        plugin.getPlatform().executeCommand(sender, subCommands);
    }
}
