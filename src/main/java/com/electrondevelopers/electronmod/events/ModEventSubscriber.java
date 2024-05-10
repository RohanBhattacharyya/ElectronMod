package com.electrondevelopers.electronmod.events;
import com.electrondevelopers.electronmod.registry.DynamicRegistry;
import com.electrondevelopers.electronmod.ElectronMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModEventSubscriber {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        DynamicRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DynamicRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
