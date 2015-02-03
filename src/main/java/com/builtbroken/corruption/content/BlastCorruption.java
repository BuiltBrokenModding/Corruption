package com.builtbroken.corruption.content;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.mc.api.entity.IExCreeperTexture;
import com.builtbroken.mc.lib.transform.vector.Location;
import com.builtbroken.mc.lib.world.edit.BlockEdit;
import com.builtbroken.mc.prefab.explosive.blast.BlastSimplePath;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by robert on 1/28/2015.
 */
public class BlastCorruption extends BlastSimplePath implements IExCreeperTexture
{
    @SideOnly(Side.CLIENT)
    public static ResourceLocation corruption_creeper_texture;

    @Override
    public BlockEdit changeBlock(Location loc)
    {
        return CorruptionHandler.getReplacementBlock(loc, loc.getBlock());
    }

    @Override
    public ResourceLocation getCreeperTexture(Entity entity)
    {
        if(corruption_creeper_texture == null)
        {
            corruption_creeper_texture = new ResourceLocation(Corruption.DOMAIN, "textures/entity/creeper.png");
        }
        return corruption_creeper_texture;
    }
}
