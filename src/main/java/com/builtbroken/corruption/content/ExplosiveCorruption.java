package com.builtbroken.corruption.content;

import com.builtbroken.corruption.Corruption;
import com.builtbroken.mc.api.edit.IWorldChangeAction;
import com.builtbroken.mc.api.event.TriggerCause;
import com.builtbroken.mc.api.explosive.IExCreeperHandler;
import com.builtbroken.mc.lib.transform.vector.Location;
import com.builtbroken.mc.lib.world.edit.BlockEdit;
import com.builtbroken.mc.prefab.explosive.AbstractExplosiveHandler;
import com.builtbroken.mc.prefab.explosive.blast.BlastSimplePath;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/** Explosive for spreading corruption in the world
 * Created by robert on 2/3/2015.
 */
public class ExplosiveCorruption extends AbstractExplosiveHandler implements IExCreeperHandler
{
    @SideOnly(Side.CLIENT)
    public static ResourceLocation corruption_creeper_texture;

    public ExplosiveCorruption()
    {
        super("corruption");
    }

    @Override
    protected double getYieldModifier(ItemStack stack)
    {
        return 2;
    }

    @Override
    public IWorldChangeAction createBlastForTrigger(World world, double x, double y, double z, TriggerCause triggerCause, double size, NBTTagCompound tag)
    {
        return new BlastCorruption().setLocation(world, (int) x, (int) y, (int) z).setCause(triggerCause).setYield(size);
    }

    @Override
    public ResourceLocation getCreeperTexture(Entity entity)
    {
        if (corruption_creeper_texture == null)
        {
            corruption_creeper_texture = new ResourceLocation(Corruption.DOMAIN, "textures/entity/creeper.png");
        }
        return corruption_creeper_texture;
    }

    @Override
    public String getTranslationKey(Entity entity)
    {
        return "entity." + Corruption.PREFIX + "creeper";
    }

    /**
     * Internal simple blast to use with the explosive
     */
    public static class BlastCorruption extends BlastSimplePath
    {
        @Override
        public BlockEdit changeBlock(Location loc)
        {
            return CorruptionHandler.getReplacementBlock(loc, loc.getBlock());
        }
    }
}
