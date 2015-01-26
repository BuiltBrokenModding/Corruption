package com.builtbroken.corruption.content.item;

import net.minecraft.item.ItemFood;

/**
 * Created by robert on 1/26/2015.
 */
public class ItemCorruptedFood extends ItemFood
{
    //TODO cause corruption effect on player
    //TODO poison player
    //TODO if player is not corrupted do damage and reduce fullness level

    public ItemCorruptedFood(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_)
    {
        super(p_i45339_1_, p_i45339_2_, p_i45339_3_);
    }

    public ItemCorruptedFood(int p_i45340_1_, boolean p_i45340_2_)
    {
        super(p_i45340_1_, p_i45340_2_);
    }
}
