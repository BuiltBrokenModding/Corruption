package com.builtbroken.corruption.content.block;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.corruption.content.CorruptionHandler;
import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.core.Engine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by robert on 1/28/2015.
 */
public class BlockCorruptedSand extends BlockFalling
{
    public BlockCorruptedSand()
    {
        this.setCreativeTab(Corruption.creativeTab);
        this.setBlockName(Corruption.PREFIX + "sand");
    }

    @Override @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
    }

    @Override @SideOnly(Side.CLIENT)
    public IIcon getIcon(int i, int j)
    {
        return Blocks.sand.getIcon(i, j);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
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
