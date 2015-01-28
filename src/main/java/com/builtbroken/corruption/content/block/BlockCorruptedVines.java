package com.builtbroken.corruption.content.block;

import com.builtbroken.corruption.content.CorruptionHandler;
import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.core.Engine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by robert on 1/28/2015.
 */
public class BlockCorruptedVines extends BlockVine
{
    @Override @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
    }

    @Override @SideOnly(Side.CLIENT)
    public IIcon getIcon(int i, int j)
    {
        return Blocks.vine.getIcon(i, j);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);
        CorruptionHandler.spreadCorruption(world, x, y, z, rand);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        if(world instanceof World)
        {
            return Colors.getIntFromColor(CorruptionHandler.getCorruptionColor(((World) world).provider.dimensionId));
        }
        return Colors.CORRUPTION_PURPLE.toInt();
    }

    @Override @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return Colors.getIntFromColor(CorruptionHandler.getCorruptionColor(Engine.proxy.getPlayerDim()));
    }

    @Override @SideOnly(Side.CLIENT)
    public int getRenderColor(int i)
    {
        return this.getBlockColor();
    }
}
