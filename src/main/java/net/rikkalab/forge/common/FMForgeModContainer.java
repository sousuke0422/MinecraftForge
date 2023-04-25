package net.rikkalab.forge.common;

import static net.minecraftforge.common.ForgeVersion.buildVersion;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModMetadata;
import net.minecraftforge.common.config.Configuration;

public class FMForgeModContainer extends DummyModContainer {
    private URL updateJSONUrl = null;
    public FMForgeModContainer() {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId       = "FMForge";
        meta.name        = "FMForge";
        meta.version     = String.format("%d", buildVersion);
        //meta.credits     = "Made possible with help from many people";
        meta.authorList  = Arrays.asList("aki");
        meta.description = "FMMods's Minecraft Forge";
        /*
        meta.url         = "https://MinecraftForge.net";
        meta.updateUrl   = "https://MinecraftForge.net/forum/index.php/topic,5.0.html";
        meta.screenshots = new String[0];
        meta.logoFile    = "/forge_logo.png";
        try {
            updateJSONUrl    = new URL("https://raw.githubusercontent.com/sousuke0422/ForgePromotions/main/promotions_slim.json");
        } catch (MalformedURLException e) {}
        */
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        bus.register(this);
        return true;
    }
}
