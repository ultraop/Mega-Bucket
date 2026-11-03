package com.ultraop.megabucket;

import com.mojang.brigadier.arguments.LongArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.command.permission.Permission;
import net.minecraft.command.permission.PermissionLevel;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MegaBucketMod implements ModInitializer {
    public static final String MOD_ID = "mega_bucket";
    public static final String MOD_VERSION = "0.1.0";
    public static final long MAX_AMOUNT = 1_000_000_000L;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Mega Bucket initialized — Created by UltraOP");

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("mb")
                .then(CommandManager.literal("help")
                    .executes(ctx -> sendHelp(ctx.getSource())))
                .then(CommandManager.literal("info")
                    .executes(ctx -> sendInfo(ctx.getSource())))
                .then(CommandManager.literal("give")
                    .then(CommandManager.literal("iron")
                        .then(CommandManager.argument("amount", LongArgumentType.longArg(1, MAX_AMOUNT))
                            .requires(source -> source.getPermissions().hasPermission(new Permission.Level(PermissionLevel.ADMINS)))
                            .executes(ctx -> pending(ctx.getSource(), "custom iron", LongArgumentType.getLong(ctx, "amount")))))
                    .then(CommandManager.literal("bucket")
                        .then(CommandManager.argument("amount", LongArgumentType.longArg(1, MAX_AMOUNT))
                            .requires(source -> source.getPermissions().hasPermission(new Permission.Level(PermissionLevel.ADMINS)))
                            .executes(ctx -> pending(ctx.getSource(), "bucket capacity", LongArgumentType.getLong(ctx, "amount"))))))
                .then(CommandManager.literal("reload")
                    .requires(source -> source.getPermissions().hasPermission(new Permission.Level(PermissionLevel.ADMINS)))
                    .executes(ctx -> {
                        ctx.getSource().sendFeedback(() -> Text.literal("Configuration reload is not implemented yet.")
                            .formatted(Formatting.YELLOW), true);
                        return 1;
                    }))
            );
        });
    }

    private static int sendHelp(ServerCommandSource source) {
        source.sendFeedback(() -> Text.literal("━━━━━━━━ Mega Bucket ━━━━━━━━").formatted(Formatting.GOLD, Formatting.BOLD), false);
        source.sendFeedback(() -> Text.literal("INFORMATION").formatted(Formatting.AQUA, Formatting.BOLD), false);
        source.sendFeedback(() -> Text.literal("/mb info").formatted(Formatting.YELLOW)
            .append(Text.literal(" — Mod and version details").formatted(Formatting.GRAY)), false);
        source.sendFeedback(() -> Text.literal("/mb help").formatted(Formatting.YELLOW)
            .append(Text.literal(" — Show this help").formatted(Formatting.GRAY)), false);
        source.sendFeedback(() -> Text.literal("ITEMS (OPERATOR)").formatted(Formatting.GREEN, Formatting.BOLD), false);
        source.sendFeedback(() -> Text.literal("/mb give iron <amount>").formatted(Formatting.YELLOW)
            .append(Text.literal(" — Give custom-quantity iron").formatted(Formatting.GRAY)), false);
        source.sendFeedback(() -> Text.literal("/mb give bucket <amount>").formatted(Formatting.YELLOW)
            .append(Text.literal(" — Give a bucket capacity").formatted(Formatting.GRAY)), false);
        source.sendFeedback(() -> Text.literal("/mb reload").formatted(Formatting.YELLOW)
            .append(Text.literal(" — Reload configuration").formatted(Formatting.GRAY)), false);
        source.sendFeedback(() -> Text.literal("Maximum amount: 1,000,000,000").formatted(Formatting.RED), false);
        source.sendFeedback(() -> Text.literal("Created by UltraOP").formatted(Formatting.DARK_GRAY, Formatting.ITALIC), false);
        return 1;
    }

    private static int sendInfo(ServerCommandSource source) {
        source.sendFeedback(() -> Text.literal("━━━━ Mega Bucket • Info ━━━━").formatted(Formatting.GOLD, Formatting.BOLD), false);
        source.sendFeedback(() -> Text.literal("Mod: ").formatted(Formatting.GRAY)
            .append(Text.literal("Mega Bucket").formatted(Formatting.WHITE, Formatting.BOLD)), false);
        source.sendFeedback(() -> Text.literal("Mod Version: ").formatted(Formatting.GRAY)
            .append(Text.literal(MOD_VERSION).formatted(Formatting.WHITE)), false);
        source.sendFeedback(() -> Text.literal("Minecraft: ").formatted(Formatting.GRAY)
            .append(Text.literal("1.21.11").formatted(Formatting.GREEN)), false);
        source.sendFeedback(() -> Text.literal("Java: ").formatted(Formatting.GRAY)
            .append(Text.literal("21").formatted(Formatting.GREEN)), false);
        source.sendFeedback(() -> Text.literal("Mod Loader: ").formatted(Formatting.GRAY)
            .append(Text.literal("Fabric").formatted(Formatting.AQUA)), false);
        source.sendFeedback(() -> Text.literal("Created by ").formatted(Formatting.GRAY)
            .append(Text.literal("UltraOP").formatted(Formatting.GOLD, Formatting.BOLD)), false);
        return 1;
    }

    private static int pending(ServerCommandSource source, String type, long amount) {
        source.sendFeedback(() -> Text.literal("Registered request: " + type + " × " + String.format("%,d", amount)
            + ". Item delivery is not implemented in this scaffold yet.").formatted(Formatting.YELLOW), true);
        return 1;
    }
}
