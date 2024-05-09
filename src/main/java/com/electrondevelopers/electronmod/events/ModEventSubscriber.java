
import com.eletrondevelopers.electronmod.registry.DynamicRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModEventSubscriber {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        DynamicRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DynamicRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
