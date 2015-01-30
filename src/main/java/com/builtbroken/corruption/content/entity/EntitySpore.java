package com.builtbroken.corruption.content.entity;

import com.builtbroken.mc.prefab.entity.EntityProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * Created by robert on 1/30/2015.
 */
public class EntitySpore extends EntityProjectile
{
    public EntitySpore(World world)
    {
        super(world);
    }

    public EntitySpore(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public EntitySpore(World world, EntityLivingBase shooter, EntityLivingBase target, float p_i1755_4_, float p_i1755_5_)
    {
        super(world, shooter, target, p_i1755_4_, p_i1755_5_);
    }

    public EntitySpore(World world, EntityLivingBase shooter, float f)
    {
        super(world, shooter, f);
    }

    @Override
    protected void onImpactTile()
    {
        super.onImpactTile();
        
    }

    @Override
    protected void onImpactEntity(Entity entityHit, float velocity)
    {
        super.onImpactEntity(entityHit, velocity);
    }
}
