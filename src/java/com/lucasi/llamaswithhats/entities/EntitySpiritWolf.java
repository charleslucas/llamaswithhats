package com.lucasi.llamaswithhats.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by CorwinJV on 2/14/2016.
 */
public class EntitySpiritWolf extends EntityWolf
{
    private boolean initialized = false;

    public EntitySpiritWolf(World worldIn)
    {
        super(worldIn);
    }

    public void setInitialized(boolean initialized)
    {
        this.initialized = initialized;
    }

    public void tame(EntityPlayer player)
    {
        setTamed(true);
        getNavigator().clearPathEntity();
        setAttackTarget(null);
        getAISit().setSitting(false);
        setHealth(20.0F);
        setOwnerId(player.getUniqueID());
        //world.setEntityState(this, (byte)7);
        setInitialized(true);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if(initialized && getOwner() == null)
        {
            setDead();
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityMob.class, false));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

}
