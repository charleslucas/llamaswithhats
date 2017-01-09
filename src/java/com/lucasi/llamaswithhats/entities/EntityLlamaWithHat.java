package com.lucasi.llamaswithhats.entities;

import com.lucasi.llamaswithhats.entities.ai.EntityAILlamaWithHatMatchUp;

import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLlamaWithHat extends EntityLlama {

    private boolean areAdditionalTasksSet;

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
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
		
		// Carl and Paul llamaswithhats find each other and pair up
//		this.tasks.addTask(2, new EntityAILlamaFollowCaravan(this, 2.0999999046325684D));
		this.tasks.addTask(2, new EntityAILlamaWithHatMatchUp(this, 2.0999999046325684D));

		this.tasks.addTask(3, new EntityAIAttackRanged(this, 1.25D, 40, 20.0F));
		this.tasks.addTask(3, new EntityAIPanic(this, 1.2D));
		this.tasks.addTask(4, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(5, new EntityAIFollowParent(this, 1.0D));
        //this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.7D));
		// Watch the closest LlamaWithHat
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityLlamaWithHat.class, 8.0F));
		// Watch the closest player
		this.tasks.addTask(8, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
		// Watch nearest living thing
		//this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityLiving.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityLlamaWithHat.AIHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityLlamaWithHat.AIDefendTarget(this));
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
