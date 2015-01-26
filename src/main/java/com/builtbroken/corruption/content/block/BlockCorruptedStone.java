package com.builtbroken.corruption.content.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

/**
 * Created by robert on 1/26/2015.
 */
public class BlockCorruptedStone extends BlockCorruption
{
    //TODO add sub types for stone bricks and mob spawn eggs
    public BlockCorruptedStone()
    {
        super(Material.rock);
    }

    @Override
    public String getUnlocalizedName()
    {
        return Blocks.stone.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return Blocks.stone.getIcon(side, meta);
    }
}
