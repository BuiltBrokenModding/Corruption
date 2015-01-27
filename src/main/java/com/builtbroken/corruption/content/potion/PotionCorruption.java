package com.builtbroken.corruption.content.potion;

import com.builtbroken.jlib.data.Colors;
import com.builtbroken.mc.prefab.potion.CustomPotion;

/**
 * Created by robert on 1/27/2015.
 */
public class PotionCorruption extends CustomPotion
{
    //TODO when potion effect ends turn entity into corrupted version
    public PotionCorruption()
    {
        super(Colors.CORRUPTION_PURPLE.toInt(), "CorruptionVirus");
    }
}
