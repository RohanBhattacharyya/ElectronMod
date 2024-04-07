package com.electrondevelopers.electronmod.items;

import com.electrondevelopers.electronmod.ElectronMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;



public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ElectronMod.MODID);
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
