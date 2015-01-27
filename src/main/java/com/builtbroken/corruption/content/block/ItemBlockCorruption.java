package com.builtbroken.corruption.content.block;

import com.builtbroken.corruption.content.CorruptionHandler;
import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.prefab.tile.item.ItemBlockMetadata;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

/**
 * Created by robert on 1/26/2015.
 */
public class ItemBlockCorruption extends ItemBlockMetadata
{
    public ItemBlockCorruption(Block block)
    {
        super(block);
    }

    @Override @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int i)
    {
        if(Minecraft.getMinecraft().thePlayer != null)
        {
            return Colors.getIntFromColor(CorruptionHandler.getCorruptionColor(Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId));
        }
        return Colors.CORRUPTION_PURPLE.toInt();
    }
}
