package com.builtbroken.corruption.content.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

/**
 * Created by robert on 1/26/2015.
 */
public class BlockCorruptedSoil extends BlockCorruption
{
    //TODO add sub types for farm land, sand, gravel, and other soil blocks
    public BlockCorruptedSoil()
    {
        super(Material.ground);
    }

    @Override
    public String getUnlocalizedName()
    {
        return Blocks.dirt.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return Blocks.dirt.getIcon(side, meta);
    }
}
