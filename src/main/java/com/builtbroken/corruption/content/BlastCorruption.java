package com.builtbroken.corruption.content;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.mc.lib.transform.vector.Location;
import com.builtbroken.mc.lib.world.edit.BlockEdit;
import com.builtbroken.mc.prefab.explosive.blast.Blast;
import com.builtbroken.mc.prefab.explosive.blast.BlastBasic;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/28/2015.
 */
public class BlastCorruption extends Blast
{
    private List<Location> pathed = new ArrayList();

    @Override
    public void getEffectedBlocks(List<BlockEdit> list)
    {
        path(new Location(world(), x(), y(), z()), list);
    }

    public void path(Location location, List<BlockEdit> list)
    {
        pathed.add(location);
        for(ForgeDirection dir: ForgeDirection.VALID_DIRECTIONS)
        {
            Location loc = location.add(dir);
            if(!pathed.contains(loc))
            {
                BlockEdit edit = CorruptionHandler.getReplacementBlock(loc, loc.getBlock());
                if(edit.hasChanged())
                {
                    list.add(edit);
                }
            }
        }
    }
}
