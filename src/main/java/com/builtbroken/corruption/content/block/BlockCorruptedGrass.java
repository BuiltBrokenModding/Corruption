package com.builtbroken.corruption.content.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by robert on 1/26/2015.
 */
public class BlockCorruptedGrass extends BlockCorruption implements IGrowable
{
    public BlockCorruptedGrass()
    {
        super(Material.grass);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return Blocks.grass.getIcon(side, meta);
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerGrass.getGrassColor(d0, d1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int p_149741_1_)
    {
        return this.getBlockColor();
    }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean b)
    {
        return true;
    }

    @Override
    public boolean func_149852_a(World world, Random rand, int x, int y, int z)
    {
        return true;
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial() == Material.air)
        {
            if (rand.nextInt(8) != 0)
            {
                if (Blocks.tallgrass.canBlockStay(world, x, y, z))
                {
                    world.setBlock(x, y, z, Blocks.tallgrass, 1, 3);
                }
            }
            else
            {
                world.getBiomeGenForCoords(x, z).plantFlower(world, rand, x, y, z);
            }
        }
    }
}
