package com.builtbroken.corruption;

import com.builtbroken.corruption.content.CorruptionHandler;
import com.builtbroken.corruption.content.ExplosiveCorruption;
import com.builtbroken.corruption.content.block.*;
import com.builtbroken.corruption.content.item.ItemCreeperWand;
import com.builtbroken.mc.api.explosive.IExplosiveHandler;
import com.builtbroken.mc.lib.mod.AbstractMod;
import com.builtbroken.mc.lib.mod.AbstractProxy;
import com.builtbroken.mc.lib.mod.ModCreativeTab;
import com.builtbroken.mc.lib.world.explosive.ExplosiveRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Mod themed after terraria's corruption zones
 *
 * @author Darkguardsman
 */
@Mod(modid = Corruption.DOMAIN, name = "Corruption", version = Corruption.VERSION, dependencies = "required-after:voltzengine")
public class Corruption extends AbstractMod
{

    public static final String DOMAIN = "corruption";
    public static final String PREFIX = DOMAIN + ":";

    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    @SidedProxy(clientSide = "com.builtbroken.corruption.ClientProxy", serverSide = "com.builtbroken.corruption.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(Corruption.DOMAIN)
    public static Corruption instance;

    public static Block corruptedSoil;
    public static Block corruptedSand;
    public static Block corruptedGrass;
    public static Block corruptedStone;
    public static Block corruptedLog;
    public static Block corruptedLeaf;
    public static Block corruptedTallGrass;
    public static Block corruptedWater;
    public static Block corruptedVines;

    public static Item corruptedApple;
    public static Item corruptedWaterBucket;
    public static Item itemCreeperWand;

    public static Fluid corruptedWater_fluid;

    public static IExplosiveHandler corruption_ex;

    public static final String CORRUPTED_FLUID_NAME = "corruptedWater";

    public static boolean disableSpread = false;
    public static boolean disableBiomes = false;
    public static boolean disableTallGrassThornDamage = true;
    public static float corruptionSpreadChance = 0.4f;

    public static ModCreativeTab creativeTab;

    public Corruption()
    {
        super(DOMAIN, "Corruption");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        creativeTab = new ModCreativeTab(DOMAIN);
        this.manager.setTab(creativeTab);

        //Fluids
        if (!FluidRegistry.registerFluid(corruptedWater_fluid))
        {
            logger().error("Error registering corruption fluid, requesting fluid from register. This could cause rendering changes and errors with recipes.");
            corruptedWater_fluid = FluidRegistry.getFluid(CORRUPTED_FLUID_NAME);
        }

        //Settings
        disableSpread = getConfig().getBoolean("DisableCorruptionSpread", "WorldGen", false, "Prevents corruption from spreading");
        disableBiomes = getConfig().getBoolean("DisableBiomeGeneration", "WorldGen", false, "Turns off world gen of biomes");
        disableTallGrassThornDamage = getConfig().getBoolean("DisableTallGrassThornDamage", "Settings", false, "Turns off thorn damage when colliding with corrupted tall grass");
        corruptionSpreadChance = getConfig().getFloat("SpreadChance", "Settings", corruptionSpreadChance, 0, 1, "Chance that corruption will spread, lower value equals more often");

        //Blocks
        corruptedSoil = getManager().newBlock("CorruptedSoil", new BlockCorruption(Blocks.dirt), ItemBlockCorruption.class).setBlockName(PREFIX + "soil");
        corruptedSand = getManager().newBlock(BlockCorruptedSand.class, ItemBlockCorruption.class);
        corruptedGrass = getManager().newBlock(BlockCorruptedGrass.class, ItemBlockCorruption.class);
        corruptedStone = getManager().newBlock("CorruptedStone", new BlockCorruption(Blocks.stone), ItemBlockCorruption.class).setBlockName(PREFIX + "stone");
        corruptedLog = getManager().newBlock(BlockCorruptedLog.class, ItemBlockCorruption.class);
        corruptedLeaf = getManager().newBlock(BlockCorruptedLeaf.class, ItemBlockCorruption.class);
        corruptedTallGrass = getManager().newBlock(BlockCorruptedTallGrass.class, ItemBlockCorruption.class);
        corruptedWater = getManager().newBlock(BlockCorruptedWater.class);
        corruptedVines = getManager().newBlock(BlockCorruptedVines.class, ItemBlockCorruption.class);

        //Items
        corruptedApple = getManager().newItem("corruptedApple", new ItemFood(4, 0.3F, false).setUnlocalizedName("apple").setTextureName("apple"));
        corruptedWaterBucket = getManager().newItem("corruptedWaterBucket", new ItemBucket(corruptedWater).setTextureName(PREFIX +"bucket.water"));
        itemCreeperWand = getManager().newItem(ItemCreeperWand.class);

        creativeTab.itemStack = new ItemStack(corruptedGrass);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        super.init(event);

        corruption_ex = new ExplosiveCorruption();
        ExplosiveRegistry.registerExplosive(DOMAIN, "corruption", corruption_ex);

        CorruptionHandler.registerReplacementBlock(Blocks.grass, corruptedGrass);
        CorruptionHandler.registerReplacementBlock(Blocks.dirt, corruptedSoil);
        CorruptionHandler.registerReplacementBlock(Blocks.sand, corruptedSand);
        CorruptionHandler.registerReplacementBlock(Blocks.stone, corruptedStone);
        CorruptionHandler.registerReplacementBlock(Blocks.log, corruptedLog);
        CorruptionHandler.registerReplacementBlock(Blocks.leaves, corruptedLeaf);
        CorruptionHandler.registerReplacementBlock(Blocks.tallgrass, corruptedTallGrass);
        CorruptionHandler.registerReplacementBlock(Blocks.vine, corruptedVines);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }

    @Override
    public AbstractProxy getProxy()
    {
        return proxy;
    }
}
