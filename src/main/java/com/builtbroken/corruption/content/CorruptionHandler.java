package com.builtbroken.corruption.content;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.corruption.content.path.ThreadCorruption;
import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.lib.transform.vector.Location;
import com.builtbroken.mc.prefab.recipe.ItemStackWrapper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by robert on 1/26/2015.
 */
public class CorruptionHandler
{
    private static final HashMap<Integer, Color> dimToColor = new HashMap();
    private static final HashMap<Block, Block> replacementBlockMap = new HashMap();
    private static final HashMap<ItemStackWrapper, ItemStackWrapper> replacementItemMap = new HashMap();

    private static final int MAX_RUNNING_THREADS = 5;
    public int current_running_thread = 5;

    public static final CorruptionHandler INSTANCE = new CorruptionHandler();

    static
    {
        setCorruptionColor(0, Colors.CORRUPTION_PURPLE.color());
        setCorruptionColor(1, Colors.DARK_BLUE.color());
        setCorruptionColor(-1, Colors.BLACK.color());
    }

    private CorruptionHandler(){}

    /**
     * Sets the corruption color for a dim, most dims default to Corrupted purple
     * @param dim - dim id to use to look up when in the world
     * @param color - color object, does not support alpha
     */
    public static void setCorruptionColor(int dim, Color color)
    {
        dimToColor.put(dim, color);
    }

    /**
     * Registers a block to replace as the corruption spreads. Ex Dirt -> Corrupted Dirt
     */
    public static void registerReplacementBlock(Block original, Block replacement)
    {
        if (original == replacement)
        {
            Corruption.instance.logger().error("A mod tried to register a replacement for the same block");
            return;
        }
        if(original == null || replacement == null)
        {
            Corruption.instance.logger().error("A mod tried to register a null replacement block");
            return;
        }
        replacementBlockMap.put(original, replacement);
    }

    /**
     * Adds an entry to replace one item with another when effected by corruption
     * @param original
     * @param replacement
     */
    public static void registerReplacementItem(ItemStack original, ItemStack replacement)
    {
        if (original == replacement)
        {
            Corruption.instance.logger().error("A mod tried to register a replacement for the same item");
            return;
        }
        if(original == null || replacement == null)
        {
            Corruption.instance.logger().error("A mod tried to register a null replacement item");
            return;
        }
        replacementItemMap.put(new ItemStackWrapper(original), new ItemStackWrapper(replacement));
    }


    public static Color getCorruptionColor(int dim)
    {
        return dimToColor.containsKey(dim) ? dimToColor.get(dim) : Colors.CORRUPTION_PURPLE.color();
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
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
            {
                int i1 = x + dir.offsetX;
                int j1 = y + dir.offsetY;
                int k1 = z + dir.offsetZ;

                Block block = world.getBlock(i1, j1, k1);

                if(block == Blocks.leaves || block == Blocks.log)
                {
                    if(INSTANCE.current_running_thread < MAX_RUNNING_THREADS)
                    {
                        INSTANCE.current_running_thread++;
                        new ThreadCorruption(new Location(world, x, y, z)).start();
                    }
                }
                else if (replacementBlockMap.containsKey(block))
                {
                    world.setBlock(i1, j1, k1, replacementBlockMap.get(block), world.getBlockMetadata(x, y, z), 2);
                }
            }
        }
    }
}
