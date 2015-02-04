package com.builtbroken.corruption.content.block;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.corruption.content.CorruptionHandler;
import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.core.Engine;
import com.builtbroken.mc.lib.helper.MathUtility;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by robert on 1/27/2015.
 */
public class BlockCorruptedTallGrass extends BlockTallGrass
{
    public BlockCorruptedTallGrass()
    {
        this.setCreativeTab(Corruption.creativeTab);
        this.setBlockName(Corruption.PREFIX + "tallGrass");
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

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        entity.setInWeb();
        if (!Corruption.disableTallGrassThornDamage)
            entity.attackEntityFrom(CorruptionHandler.THORN, 1.0F);
        if(MathUtility.rand.nextFloat() >= 0.9f)
        {
            world.playAuxSFXAtEntity(null, 2001, x, y, z, 0);
            world.setBlockToAir(x, y, z);
        }
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

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return Blocks.tallgrass.getIcon(side, meta);
    }

    @Override
    public String getUnlocalizedName()
    {
        return Blocks.tallgrass.getUnlocalizedName();
    }
}
