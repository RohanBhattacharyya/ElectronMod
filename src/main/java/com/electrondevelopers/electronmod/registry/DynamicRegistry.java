import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DynamicRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "modid");
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "modid");

    public static RegistryObject<Block> createBlock(String name, String description, String nbtData) {
        return BLOCKS.register(name, () -> new Block(Block.Properties.of(Material.STONE))); // Simplified
    }

    public static RegistryObject<Item> createItem(String name, String description, String nbtData, boolean isTool) {
        return ITEMS.register(name, () -> new Item(new Item.Properties())); // Simplified
    }
}
