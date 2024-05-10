package com.electrondevelopers.electronmod;
import com.electrondevelopers.electronmod.ElectronMod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;


import net.minecraft.world.inventory.CraftingContainer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.api.distmarker.Dist;

import com.electrondevelopers.electronmod.ApiHandler;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

@EventBusSubscriber(modid = ElectronMod.MOD_ID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class CraftingListener {

    @SubscribeEvent
    public static void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        if (event.getInventory() instanceof CraftingContainer) {
            CraftingContainer craftingInventory = (CraftingContainer) event.getInventory();
            String[] items = new String[9];
            for (int i = 0; i < 9; i++) {
                ItemStack stack = craftingInventory.getItem(i);
                if (stack.isEmpty()) {
                    items[i] = "0";
                } else {
                    if (!stack.isEmpty()) {
                        items[i] = ForgeRegistries.ITEMS.getKey(stack.getItem()).toString();
                    }                    
                }
            }
            String prompt = formatPromptForGemini(items);
            System.out.println("Formatted prompt for Gemini: " + prompt);
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.displayClientMessage(Component.literal("prompt: " + prompt), true);
            }
            // You would typically call the API here
            ApiHandler.sendPrompt(prompt);
        }
    }

    private static String formatPromptForGemini(String[] items) {
        // Convert the array into a single string formatted for the API
        return "{" + String.join(",", items) + "}";
    }
}
