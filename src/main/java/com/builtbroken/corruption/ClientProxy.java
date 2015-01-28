package com.builtbroken.corruption;

import com.builtbroken.corruption.content.CorruptionHandler;
import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.core.Engine;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by robert on 1/26/2015.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        super.preInit();
        //Fluids
        Corruption.corruptedWater_fluid = new Fluid(Corruption.CORRUPTED_FLUID_NAME)
        {
            @Override
            public int getColor()
            {
                return Colors.getIntFromColor(CorruptionHandler.getCorruptionColor(Engine.proxy.getPlayerDim()));
            }
        };
    }
}
