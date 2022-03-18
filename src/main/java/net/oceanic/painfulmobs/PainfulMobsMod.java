package net.oceanic.painfulmobs;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.oceanic.painfulmobs.eventhandlers.EventHandler;

import java.lang.reflect.Method;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("painfulmobs")
public class PainfulMobsMod
{

    // Directly reference a slf4j logger

    public PainfulMobsMod()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
//        Method createBoolean = ObfuscationReflectionHelper.findMethod(GameRules.IntegerValue.class, "create",int.class);
//        createBoolean.setAccessible(true);
//            try {
//                Object boolTrue = createBoolean.invoke(GameRules.IntegerValue.class, 20);
//                furnaceSpeedMultiplier = GameRules.register("furnaceSpeedMultiplier", GameRules.Category.UPDATES, (GameRules.Type<GameRules.IntegerValue>) boolTrue);
//            } catch (IllegalAccessException e) {
//                LOGGER.error("Illegal Access Exception!");
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                LOGGER.error("Invocation Target Exception!");
//                e.printStackTrace();
//            }
    }

    private void setup(final FMLCommonSetupEvent event)
    {
//        furnaceSpeedMultiplier=createInteger("furnaceSpeedMultiplier", 20, GameRules.Category.UPDATES);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // Some example code to dispatch IMC to another mod
    }
    public static GameRules.Key<GameRules.IntegerValue> createInteger(String id, int defaultVal, GameRules.Category cat) {
        try {
            Method m = ObfuscationReflectionHelper.findMethod(GameRules.IntegerValue.class, "m_46312_", int.class);
            m.setAccessible(true);
            GameRules.Type<GameRules.IntegerValue> ruleTypeBoolean = (GameRules.Type<GameRules.IntegerValue>)m.invoke(null, defaultVal);
            GameRules.Key<GameRules.IntegerValue> rule = GameRules.register(id, cat, ruleTypeBoolean);
            return rule;
        }
        catch (Exception e) {
            return null;
        }
    }
//public static GameRules.Key<GameRules.BooleanValue> createBoolean(String id, boolean defaultVal, GameRules.Category cat) {
//    //access transformers cfg SHULD make this create public
////      Type<BooleanValue> ruleTypeBoolean2 = GameRules.BooleanValue.create(true); // this works if AT works
//    try {
//        Method m = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "m_46250_", boolean.class);
////      m.setAccessible(true);
//        GameRules.Type<GameRules.BooleanValue> ruleTypeBoolean = (GameRules.Type<GameRules.BooleanValue>) m.invoke(null, defaultVal);
//        GameRules.Key<GameRules.BooleanValue> rule = GameRules.register(id, cat, ruleTypeBoolean);
//        return rule;
//    }
//    catch (Exception e) {
//        FasterFurnaceMod.LOGGER.error("Create gamerule error", e);
//    }
//    return null;
//}
    private void processIMC(final InterModProcessEvent event)
    {
    }
    public static GameRules.Key<GameRules.IntegerValue> furnaceSpeedMultiplier;
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts

    }



    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
        }
    }
}
