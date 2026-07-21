package dev.yaghito.moonlightmc;

import com.dwarslooper.cactus.client.gui.hud.element.HudElement;
import com.dwarslooper.cactus.client.systems.config.settings.impl.BooleanSetting;
import com.dwarslooper.cactus.client.addon.v2.ICactusAddon;
import com.dwarslooper.cactus.client.addon.v2.RegistryBus;
import com.dwarslooper.cactus.client.feature.command.Command;
import com.dwarslooper.cactus.client.feature.content.ContentPack;
import com.dwarslooper.cactus.client.feature.content.ContentPackManager;
import com.dwarslooper.cactus.client.feature.module.Category;
import com.dwarslooper.cactus.client.feature.module.Module;
import com.dwarslooper.cactus.client.feature.module.ModuleManager;
import dev.yaghito.moonlightmc.util.compat.IncompatibilityRegistry;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Set;
import java.util.function.Supplier;

import static dev.yaghito.moonlightmc.Moonlight.MOD_ID;

public class MoonlightCactus implements ICactusAddon {
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    // Utility to get the name of a Setting via reflection
    private static String getSettingName(Object setting) {
        try {
            var field = setting.getClass().getSuperclass().getDeclaredField("name");
            field.setAccessible(true);
            return (String) field.get(setting);
        } catch (Exception e) {
            return null;
        }
    }

    // Single static cached instance to ensure registration matches completely
    private static Category cachedCategory;

    public static Category getCategory() {
        if (cachedCategory == null) {
            cachedCategory = new Category("moonlight", net.minecraft.world.item.Items.GLOW_BERRIES);
        }
        return cachedCategory;
    }

    @Override
    public void onInitialize(RegistryBus registryBus) {
        // This is called when the addon is initialized. It provides a RegistryBus
        // which will be used to register new features and content

        LOGGER.info("Hello, Cactus!");

        //GlowberryPlaceholders.register(registryBus);

        //registryBus.register(Category.class, (list, ctx) -> list.add(getCategory()));

        //registryBus.register(HudElement.class, ctx -> new PickUpLogHud());

        // Register our modules inside the custom category
        //registerModule(registryBus, "lightLevel", () -> new LightLevelModule(getCategory()));
        //registryBus.register(Command.class, ctx -> new CalculatorCommand("calc"));
    }


    private void registerModule(RegistryBus registryBus, String moduleId, Supplier<Module> factory) {
        if (IncompatibilityRegistry.isModuleBlocked(moduleId)) {
            Set<String> blockingMods = IncompatibilityRegistry.blockingModsForModule(moduleId);
            LOGGER.info("Skipping module '{}' due to incompatibility with loaded mod(s): {}", moduleId, blockingMods);
            return;
        }

        registryBus.register(Module.class, ctx -> factory.get());
    }

    @Override
    public void onLoadComplete() {
        // Register our Cheats content pack after Cactus is fully initialized
        //ContentPackManager contentPackManager = ContentPackManager.get();
        //if (contentPackManager != null) {
        //    ContentPack cheatsPack = new ContentPack(
        //            "glowberry_cheats",
        //            ContentPack.ActivationPolicy.DEFAULT_DISABLED,
        //            Items.COMMAND_BLOCK
        //    );
        //    contentPackManager.registerPack(cheatsPack);

        //    // Apply initial state and listen for toggle changes
        //    syncCheatModules(cheatsPack.isEnabled());
        //    cheatsPack.setChangedListener(pack -> syncCheatModules(pack.isEnabled()));

         //    LOGGER.info("Registered 'Cheats' content pack");
        //} else {
        //    LOGGER.warn("ContentPackManager not available, skipping Cheats content pack registration");
        //}
    }

    @Override
    public void onShutdown() {
        // This is called when the client is shutting down
    }
}