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
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.UP;

/**
 * Created by robert on 1/26/2015.
 */
public class BlockCorruption extends Block
{
    Block blockToMimic;
    public BlockCorruption(Block block)
    {
        super(block.getMaterial());
        this.blockToMimic = block;
        this.setCreativeTab(Corruption.creativeTab);
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
        CorruptionHandler.spreadCorruption(world, x, y, z, rand);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        if(world instanceof World)
        {
            return Colors.getIntFromColor(CorruptionHandler.getCorruptionColor(((World)world).provider.dimensionId));
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

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return blockToMimic != null ? blockToMimic.getIcon(side, meta) : null;
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
        Block plant = plantable.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

        switch (plantType)
        {
            case Desert: return getMaterial() == Material.sand;
            case Cave:   return isSideSolid(world, x, y, z, UP);
            case Plains: return getMaterial() == Material.ground || getMaterial() == Material.grass;
            case Water:  return world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0;
            case Beach:
                boolean isBeach = getMaterial() == Material.ground || getMaterial() == Material.grass || getMaterial() == Material.sand;
                boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                        world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                        world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                        world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
                return isBeach && hasWater;
        }

        return blockToMimic != null && blockToMimic.canSustainPlant(world, x, y, z, direction, plantable);
    }

    @Override
    public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
        if (this.isAssociatedBlock(Blocks.grass) || this.isAssociatedBlock(Blocks.dirt))
        {
            world.setBlock(x, y, z, Blocks.dirt, 0, 2);
        }
    }

    @Override
    public boolean isFertile(World world, int x, int y, int z)
    {
        return blockToMimic != null && blockToMimic.isFertile(world, x, y, z);
    }

    @Override
    public boolean isAssociatedBlock(Block other)
    {
        return this == other || blockToMimic != null && other == blockToMimic;
    }
}
