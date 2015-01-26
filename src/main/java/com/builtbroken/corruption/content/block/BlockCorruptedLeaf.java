package com.builtbroken.corruption.content.block;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.jlib.data.Colors;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by robert on 1/26/2015.
 */
public class BlockCorruptedLeaf extends BlockOldLeaf
{
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int i, int j)
    {
        return Blocks.leaves.getIcon(i, j);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {

    }

    @Override
    public String[] func_150125_e()
    {
        return ((BlockOldLeaf) Blocks.leaves).field_150131_O;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return Colors.PURPLE.toInt();
    }

    @Override
    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack)
    {
        //TODO auto corrupt drops
        if (stack != null && stack.getItem() == Items.apple)
        {
            super.dropBlockAsItem(world, x, y, z, new ItemStack(Corruption.corruptedApple));
        }
        else
        {
            super.dropBlockAsItem(world, x, y, z, stack);
        }
    }
}
