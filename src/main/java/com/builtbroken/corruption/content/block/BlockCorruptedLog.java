package com.builtbroken.corruption.content.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

/**
 * Created by robert on 1/26/2015.
 */
public class BlockCorruptedLog extends BlockCorruption
{
    public BlockCorruptedLog()
    {
        super(Material.wood);
    }

    @Override
    public String getUnlocalizedName()
    {
        return Blocks.log.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return Blocks.log.getIcon(side, meta);
    }
}
