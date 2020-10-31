package me.lucko.spark.skyfall;

import io.skyfallsdk.Server;
import io.skyfallsdk.expansion.Expansion;
import io.skyfallsdk.expansion.ExpansionInfo;
import me.lucko.spark.common.SparkPlatform;
import me.lucko.spark.common.SparkPlugin;
import me.lucko.spark.common.command.sender.CommandSender;
import me.lucko.spark.common.platform.PlatformInfo;
import me.lucko.spark.common.sampler.ThreadDumper;
import me.lucko.spark.common.sampler.tick.TickHook;

import java.nio.file.Path;
import java.util.stream.Stream;

@ExpansionInfo(name = "Spark", version = "@version@", authors = {"Lucko", "Obadiah Crowe"}, dependencies = {})
public class SkyfallSparkPlugin implements Expansion, SparkPlugin {

    private SparkPlatform platform;

    @Override
    public void onStartup() {
        this.platform = new SparkPlatform(this);
        this.platform.enable();

        this.registerCommand(SkyfallSparkCommand.class);
    }

    @Override
    public void onShutdown() {
        this.platform.disable();
    }

    public SparkPlatform getPlatform() {
        return this.platform;
    }

    @Override
    public String getVersion() {
        return Server.get().getExpansionInfo(SkyfallSparkPlugin.class).version();
    }

    @Override
    public Path getPluginDirectory() {
        return Server.get().getPath();
    }

    @Override
    public String getCommandName() {
        return "spark";
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stream<? extends CommandSender> getSendersWithPermission(String permission) {
        return (Stream<? extends CommandSender>) Stream.concat(
          Server.get().getPlayers().stream().filter(player -> player.hasPermission(permission)),
          Stream.of(Server.get())
        );
    }

    @Override
    public void executeAsync(Runnable task) {
        Server.get().getScheduler().execute(task);
    }

    @Override
    public PlatformInfo getPlatformInfo() {
        return new SkyfallPlatformInfo();
    }

    @Override
    public TickHook createTickHook() {
        return new SkyfallTickHook();
    }

    @Override
    public ThreadDumper getDefaultThreadDumper() {
        return new ThreadDumper.Specific(new long[]{Thread.currentThread().getId()});
    }
}
