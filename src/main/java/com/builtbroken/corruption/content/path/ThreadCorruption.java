package com.builtbroken.corruption.content.path;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.corruption.content.CorruptionHandler;
import com.builtbroken.mc.imp.transform.vector.Location;
import com.builtbroken.mc.lib.world.edit.BlockEdit;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by robert on 1/27/2015.
 */
public class ThreadCorruption extends ThreadPathfinder
{
    private List<BlockEdit> edits = new ArrayList();
    public int blocksPerTick = 3;

    public ThreadCorruption(Location location)
    {
        super(location);
    }

    @Override
    public boolean canPath(Location location)
    {
        Block block = location.getBlock();
        return block == Blocks.leaves || block == Blocks.log;
    }

    @Override
    public void run()
    {
        super.run();
        if(edits != null && !edits.isEmpty())
        {
            FMLCommonHandler.instance().bus().register(this);
        }
        synchronized (CorruptionHandler.INSTANCE)
        {
            CorruptionHandler.INSTANCE.current_running_thread--;
        }
    }

    @Override
    public void onPath(Location location)
    {
        Block block = location.getBlock();
        int meta = location.getBlockMetadata();
        BlockEdit edit = new BlockEdit(location.world(), location.x(), location.y(), location.z());
        if (block == Blocks.leaves)
        {
            edit.set(Corruption.corruptedLeaf, meta, false, true);
            edits.add(edit);
        }
        else if (block == Blocks.log)
        {
            edit.set(Corruption.corruptedLog, meta, false, true);
            edits.add(edit);
        }
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if(event.side == Side.SERVER && event.phase == TickEvent.Phase.END)
        {
            Iterator<BlockEdit> it = edits.iterator();
            int c = 0;
            while(it.hasNext() && c++ <= blocksPerTick)
            {
                it.next().place();
                it.remove();
            }
        }
        if(edits.isEmpty())
        {
            FMLCommonHandler.instance().bus().unregister(this);
        }
    }
}
