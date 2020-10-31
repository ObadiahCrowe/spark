package me.lucko.spark.skyfall;

import io.skyfallsdk.Server;
import me.lucko.spark.common.platform.AbstractPlatformInfo;

public class SkyfallPlatformInfo extends AbstractPlatformInfo {

    @Override
    public Type getType() {
        return Type.SERVER;
    }

    @Override
    public String getName() {
        return "Skyfall";
    }

    @Override
    public String getVersion() {
        return Server.get().getExpansionInfo(SkyfallSparkPlugin.class).version();
    }

    @Override
    public String getMinecraftVersion() {
        return Server.get().getBaseVersion().getName();
    }
}
