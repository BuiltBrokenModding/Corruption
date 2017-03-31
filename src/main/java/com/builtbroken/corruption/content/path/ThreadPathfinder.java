package com.builtbroken.corruption.content.path;

import com.builtbroken.mc.imp.transform.vector.Location;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/27/2015.
 */
public abstract class ThreadPathfinder extends Thread
{
    Location location;

    private List<Location> pathedLocations = new ArrayList();

    public ThreadPathfinder(Location location)
    {
        this.location = location;
    }

    @Override
    public void run()
    {
        path(location, ForgeDirection.UNKNOWN);
    }

    public void path(Location location, ForgeDirection from)
    {
        if(!pathedLocations.contains(location))
        {
            onPath(location);
            pathedLocations.add(location);
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
            {
                if(dir.getOpposite() != from)
                {
                    Location newLoc = location.add(dir);
                    if (canPath(newLoc))
                    {
                        path(newLoc, dir);
                    }
                }
            }
        }
    }

    public abstract boolean canPath(Location location);

    public abstract void onPath(Location location);
}
