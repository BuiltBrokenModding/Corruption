package com.builtbroken.corruption;

import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.lib.mod.AbstractProxy;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by robert on 1/26/2015.
 */
public class CommonProxy extends AbstractProxy
{
    @Override
    public void preInit()
    {
        super.preInit();
        Corruption.corruptedWater_fluid = new Fluid(Corruption.CORRUPTED_FLUID_NAME)
        {
            @Override
            public int getColor()
            {
                return Colors.CORRUPTION_PURPLE.toInt();
            }
        };
    }
}
