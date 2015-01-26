package com.builtbroken.corruption.content.block;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.corruption.content.CorruptionHandler;
import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.lib.transform.vector.Location;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by robert on 1/26/2015.
 */
public class BlockCorruption extends Block
{
    public BlockCorruption(Material material)
    {
        super(material);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {

    }


    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        spreadCorruption(world, x, y, z, rand);
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

    /**
     * Spreads corruption from one block to another. Can spread up to 2 blocks in any direction from
     * source block. This may change in the future when world change events are implemented and treading
     * is added. In which more complex checks can be applied to do line of sight. As well as pathfinding
     * to ensure that the spreading only happens to blocks that should be effected. In other words walls
     * and barriers would be able to stop the spreading of the corruption.
     */
    public static void spreadCorruption(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote && !Corruption.disableSpread)
        {
            for (int i = 0; i < 4; i++)
            {
                int i1 = x + rand.nextInt(3) - 2;
                int j1 = y + rand.nextInt(3) - 2;
                int k1 = z + rand.nextInt(3) - 2;

                Block block = world.getBlock(i1, j1, k1);

                if (block == Blocks.dirt || block == Blocks.netherrack)
                {
                    world.setBlock(i1, j1, k1, Corruption.corruptedSoil);
                }
                else if (block == Blocks.grass)
                {
                    world.setBlock(i1, j1, k1, Corruption.corruptedGrass);
                }
                else if (block == Blocks.stone)
                {
                    world.setBlock(i1, j1, k1, Corruption.corruptedStone);
                }
                else if (block == Blocks.log)
                {
                    world.setBlock(i1, j1, k1, Corruption.corruptedLog);
                }
                else if (block == Blocks.leaves)
                {
                    world.setBlock(i1, j1, k1, Corruption.corruptedLeaf);
                }
            }
        }
    }
}
