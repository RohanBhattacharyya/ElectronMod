package com.electrondevelopers.electronmod.registry;
import com.electrondevelopers.electronmod.ElectronMod;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs; 
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DynamicRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ElectronMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ElectronMod.MOD_ID);

    public static final RegistryObject<Block> YOUR_BLOCK = BLOCKS.register("your_block_name", 
    () -> new Block(BlockBehaviour.Properties.of().strength(1.5f).requiresCorrectToolForDrops()));
    public static RegistryObject<Block> createBlock(String name, String description, String nbtData) {
        return BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.of().strength(1.5f).requiresCorrectToolForDrops()));
    }    

    public static RegistryObject<Item> createItem(String name, String description, String nbtData, boolean isTool) {
        Item.Properties properties = new Item.Properties();
        if (isTool) {
            properties.tab(CreativeModeTabs.TAB_TOOLS);  // Example: Adding the item to the tools tab.
        }
        return ITEMS.register(name, () -> new Item(properties));
    }
    
}
