package com.eletrondevelopers.electronmod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.api.distmarker.Dist;
import com.eletrondevelopers.electronmod.ApiHandler;


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
                    items[i] = stack.getItem().getRegistryName().toString();
                }
            }
            String prompt = formatPromptForGemini(items);
            System.out.println("Formatted prompt for Gemini: " + prompt);
            // You would typically call the API here
            ApiHandler.sendPrompt(prompt);
        }
    }

    private static String formatPromptForGemini(String[] items) {
        // Convert the array into a single string formatted for the API
        return "[" + String.join(",", items) + "]";
    }
}
