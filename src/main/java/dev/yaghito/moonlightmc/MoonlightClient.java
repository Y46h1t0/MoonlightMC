package dev.yaghito.moonlightmc;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.yaghito.moonlightmc.Moonlight.MOD_ID;

public class MoonlightClient  implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {

    }
}
