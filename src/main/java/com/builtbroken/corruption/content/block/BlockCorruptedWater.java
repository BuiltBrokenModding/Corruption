package com.builtbroken.corruption.content.block;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.corruption.content.CorruptionHandler;
import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.core.Engine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;

import java.util.Random;

/**
 * Created by robert on 1/28/2015.
 */
public class BlockCorruptedWater extends BlockFluidClassic
{
    ForgeDirection[] dirs = new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};

    public BlockCorruptedWater()
    {
        super(Corruption.corruptedWater_fluid, Material.water);
        setHardness(100.0F);
        setLightOpacity(3);
        setBlockName("water");
        setBlockTextureName("water_still");
        disableStats();
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        if (block == Blocks.water)
        {
            return false;
        }
        return super.displaceIfPossible(world, x, y, z);
    }

    @Override
    protected void flowIntoBlock(World world, int x, int y, int z, int meta)
    {
        //Handle infinite source system similar to MC's water
        Block block = world.getBlock(x, y, z);
        if(block == Blocks.water)
        {
            world.setBlock(x, y, z, this, 0, 2);
            world.scheduleBlockUpdate(x, y, z, this, tickRate(world));
        }
        else if (block == this || block == Blocks.flowing_water)
        {
            int i = 0;

            //Check all side for valid source blocks
            for (ForgeDirection dir : dirs)
            {
                block = world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
                if (this.isSourceBlock(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ))
                {
                    i++;
                }
                else if (block == Blocks.water && world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) <= 1)
                {
                    i++;
                }
            }
            //If 2 or more source blocks set to a source block
            if (i >= 2)
            {
                world.setBlock(x, y, z, this, 0, 2);
                world.scheduleBlockUpdate(x, y, z, this, tickRate(world));
            }
        }
        else
        {
            super.flowIntoBlock(world, x, y, z, meta);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return Blocks.water.getIcon(side, meta);
    }

    @Override
    public boolean getTickRandomly()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {

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
        if (world instanceof World)
        {
            return Colors.getIntFromColor(CorruptionHandler.getCorruptionColor(((World) world).provider.dimensionId));
        }
        return Colors.CORRUPTION_PURPLE.toInt();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return Colors.getIntFromColor(CorruptionHandler.getCorruptionColor(Engine.proxy.getPlayerDim()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int i)
    {
        return this.getBlockColor();
    }

    @Override
    public String getUnlocalizedName()
    {
        return Blocks.water.getUnlocalizedName();
    }
}
