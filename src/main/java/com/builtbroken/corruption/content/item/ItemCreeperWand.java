package com.builtbroken.corruption.content.item;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.mc.core.content.entity.EntityExCreeper;
import com.builtbroken.mc.lib.helper.LanguageUtility;
import com.builtbroken.mc.lib.transform.vector.Location;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.Language;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/** Glorified spawn test item
 * Created by robert on 2/3/2015.
 */
public class ItemCreeperWand extends Item
{
    public ItemCreeperWand()
    {
        this.setTextureName(Corruption.PREFIX + "wand.creeper");
    }

    @Override @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        String s = LanguageUtility.getLocal(getUnlocalizedName() +".info.name");
        if(s != null && !s.isEmpty())
        {
            list.add(s);
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xHit, float yHit, float zHit)
    {
        if(!world.isRemote)
        {
            EntityExCreeper cex = new EntityExCreeper(new Location(world, x, y, z), Corruption.corruption_ex, 2, null);
            world.spawnEntityInWorld(cex);
        }
        return true;
    }
}
