package com.lucasi.llamaswithhats.entities;

import com.lucasi.llamaswithhats.entities.ai.EntityAILlamaWithHatMatchUp;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILlamaFollowCaravan;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityLlamaWithHat extends EntityLlama {

    private boolean areAdditionalTasksSet;
    
    private int lwhType = 0;  // 0 = Undifferentiated, 1 = Paul, 2 = Carl
    
    public int GetType()
    {
    	return lwhType;
    }

    public boolean IsPaul()
    {
    	if (lwhType == 1) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public boolean IsCarl()
    {
    	if (lwhType == 2) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public boolean IsUndifferentiated()
    {
    	if (lwhType == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    // Is this llamawithhat's parent/lead (current match) still a good match?
    public boolean IsParentMatch() {
    	return this.IsMatch(((EntityLlamaWithHat) this.getCaravanHead()).GetType());
    }
    
    // Is this llamawithhat a good match to meet up and talk with
    public boolean IsMatch(int Type) {
    	if (this.lwhType == 0 && Type == 0) {  // Undifferentiated llamas will meet with each other
    		return true;
    	}
    	else if (this.lwhType == 1 && Type == 2) {  // Paul will talk with Carl
    		return true;
    	}    	
    	else if (this.lwhType == 2 && Type == 1) {  // Carl will talk with Paul
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public EntityLlamaWithHat(World worldIn)
	{
		super(worldIn);
	}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        this.setAdditionalAItasks();
    }

    @Override
	protected void initEntityAI()
	{
    	// High Priority Tasks
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
		
		// Llamaswithhats look for other llamaswithhats to form a caravan
		this.tasks.addTask(2, new EntityAILlamaFollowCaravan(this, 2.0999999046325684D));

		this.tasks.addTask(3, new EntityAIAttackRanged(this, 1.25D, 40, 20.0F));  // Fight
		this.tasks.addTask(3, new EntityAIPanic(this, 1.2D));                     // Flight
		this.tasks.addTask(4, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(5, new EntityAIFollowParent(this, 1.0D));
        //this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.7D));
		// Watch the closest LlamaWithHat
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLlamaWithHat.class, 8.0F));
		// Watch the closest player
		this.tasks.addTask(7, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		// Watch nearest living thing
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLiving.class, 6.0F));

		// Llamaswithhats find each other and pair up
		this.tasks.addTask(9, new EntityAILlamaWithHatMatchUp(this, 2.0999999046325684D));
		this.tasks.addTask(10, new EntityAILookIdle(this));
    	// Low Priority Tasks

		// High Priority TargetTasks
		this.targetTasks.addTask(1, new EntityLlamaWithHat.AIHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityLlamaWithHat.AIDefendTarget(this));
		// Low Priority TargetTasks
	}
	
    private void setAdditionalAItasks()
    {
        if (!this.areAdditionalTasksSet)
        {
            this.areAdditionalTasksSet = true;

            if (this.isChild())
            {
                //this.tasks.addTask(8, new EntityAIPlay(this, 0.32D));
            }
            //else if (this.getProfession() == 0)
            //{
            //    this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6D));
            //}
        }
    }
    
	// CARL!!!
    protected SoundEvent getCarl1Sound()
    {
        return SoundEvents.ENTITY_WOLF_HURT;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }



	static class AIDefendTarget extends EntityAINearestAttackableTarget<EntityWolf>
	{
		public AIDefendTarget(EntityLlamaWithHat llama)
		{
			super(llama, EntityWolf.class, 16, false, true, null);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute()
		{
			return super.shouldExecute();
		}

		protected double getTargetDistance()
		{
			return super.getTargetDistance();
		}
	}

	static class AIHurtByTarget extends EntityAIHurtByTarget
	{
		public AIHurtByTarget(EntityLlamaWithHat llama)
		{
			super(llama, false, new Class[0]);
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean continueExecuting()
		{
			return super.continueExecuting();
		}
	}

}
