package me.lucko.spark.skyfall;

import io.skyfallsdk.chat.ChatComponent;
import io.skyfallsdk.player.Player;
import io.skyfallsdk.server.CommandSender;
import me.lucko.spark.common.command.sender.AbstractCommandSender;
import net.kyori.text.Component;
import net.kyori.text.serializer.gson.GsonComponentSerializer;

import java.util.UUID;

public class SkyfallCommandSender extends AbstractCommandSender<CommandSender> {

    public SkyfallCommandSender(CommandSender delegate) {
        super(delegate);
    }

    @Override
    public String getName() {
        return super.delegate instanceof Player ? ((Player) super.delegate).getName() : "Server";
    }

    @Override
    public UUID getUniqueId() {
        if (!(super.delegate instanceof Player)) {
            return ((Player) super.delegate).getUuid();
        }

        return null;
    }

    @Override
    public void sendMessage(Component message) {
        super.delegate.sendMessage(ChatComponent.fromJson(GsonComponentSerializer.INSTANCE.serialize(message)));
    }

    @Override
    public boolean hasPermission(String permission) {
        return super.delegate.hasPermission(permission);
    }
}
