package com.builtbroken.corruption;

import com.builtbroken.corruption.content.block.*;
import com.builtbroken.mc.lib.mod.AbstractMod;
import com.builtbroken.mc.lib.mod.AbstractProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

/**
 * Mod themed after terraria's corruption zones
 * @author Darkguardsman
 */
@Mod(modid = Corruption.DOMAIN, name = "Corruption", version = Corruption.VERSION, dependencies = "required-after:VoltzEngine")
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

    public static Block corruptedSoil;
    public static Block corruptedGrass;
    public static Block corruptedStone;
    public static Block corruptedLog;
    public static Block corruptedLeaf;

    public static Item corruptedApple;

    public static boolean disableSpread = false;
    public static boolean disableBiomes = false;


    public Corruption()
    {
        super(DOMAIN);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        //Settings
        disableSpread = getConfig().getBoolean("DisableCorruptionSpread", "WorldGen", false, "Prevents corruption from spreading");
        disableBiomes = getConfig().getBoolean("DisableBiomeGeneration", "WorldGen", false, "Turns off world gen of biomes");

        //Blocks
        corruptedSoil = getManager().newBlock(BlockCorruptedSoil.class);
        corruptedGrass = getManager().newBlock(BlockCorruptedGrass.class);
        corruptedStone = getManager().newBlock(BlockCorruptedStone.class);
        corruptedLog = getManager().newBlock(BlockCorruptedLog.class);
        corruptedLeaf = getManager().newBlock(BlockCorruptedLeaf.class);

        //Items
        corruptedApple = new ItemFood(4, 0.3F, false).setUnlocalizedName("apple").setTextureName("apple");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
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
