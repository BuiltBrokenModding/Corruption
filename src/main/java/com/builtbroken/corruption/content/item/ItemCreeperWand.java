package com.builtbroken.corruption.content.item;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.mc.lib.transform.vector.Location;
import com.builtbroken.mc.prefab.entity.EntityCreeperEx;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by robert on 2/3/2015.
 */
public class ItemCreeperWand extends Item
{
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xHit, float yHit, float zHit)
    {
        if(!world.isRemote)
        {
            EntityCreeperEx cex = new EntityCreeperEx(new Location(world, x, y, z), Corruption.corruption_ex, 2, null);
            world.spawnEntityInWorld(cex);
        }
        return true;
    }
}
