package com.lucasi.llamaswithhats.entities.ai;

import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.util.math.Vec3d;

public class EntityAILlamaWithHatFollowCaravan extends EntityAIBase
{
    public EntityLlama llama;
    private double speedModifier;
    private int distCheckCounter;

    public EntityAILlamaWithHatFollowCaravan(EntityLlama llamaIn, double speedModifierIn)
    {
        this.llama = llamaIn;  // This llama
        this.speedModifier = speedModifierIn;  // This llama's speed
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	// If not leashed and range is not NULL
        if (!this.llama.getLeashed() && !this.llama.inCaravan())
        {
            List<EntityLlama> list = this.llama.world.<EntityLlama>getEntitiesWithinAABB(this.llama.getClass(), this.llama.getEntityBoundingBox().expand(9.0D, 4.0D, 9.0D));
            EntityLlama entityllama = null;
            double d0 = Double.MAX_VALUE;

            // Iterate over all llamas we found nearby
            for (EntityLlama entityllama1 : list)
            {
            	// If llama has parent/lead llama && does not have a child
                if (entityllama1.inCaravan() && !entityllama1.hasCaravanTrail())
                {
                    double d1 = this.llama.getDistanceSqToEntity(entityllama1);

                    if (d1 <= d0) // Iterate until we find the closest qualified llama
                    {
                        d0 = d1;
                        entityllama = entityllama1;
                    }
                }
            }

            // If no llamas with parents/leads met our qualifications, look for llamas with leashes
            if (entityllama == null)
            {
            	// Iterate over the llama list again
                for (EntityLlama entityllama2 : list)
                {
                	// If leashed                 && does not have a child
                    if (entityllama2.getLeashed() && !entityllama2.hasCaravanTrail())
                    {
                        double d2 = this.llama.getDistanceSqToEntity(entityllama2);

                        if (d2 <= d0) // Iterate until we find the closest qualified llama
                        {
                            d0 = d2;
                            entityllama = entityllama2;
                        }
                    }
                }
            }

            // If we found a llama that met our qualifications
            if (entityllama == null)
            {
                return false;
            }
            else if (d0 < 4.0D) // Is us, or is too close?
            {
                return false;
            }
            // If this llama is not leashed and is not part of a caravan (double-check?)
            else if (!entityllama.getLeashed() && !this.firstIsLeashed(entityllama, 1))
            {
                return false;
            }
            else
            {
            	// Follow that llama!
                this.llama.joinCaravan(entityllama);
                return true;
            }
        }
        else
        {
        	// Do not follow any llama
            return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
    	// If this llama has a parent/lead llama && that parent/lead llama is still alive                && that parent/lead llama is still part of a caravan
        if (this.llama.inCaravan() && this.llama.getCaravanHead().isEntityAlive() && this.firstIsLeashed(this.llama, 0))
        {
        	// Get distance to parent/lead llama
            double d0 = this.llama.getDistanceSqToEntity(this.llama.getCaravanHead());

            // If we're really far away from our parent/lead llama
            if (d0 > 676.0D)
            {
            	// If we're going slow
                if (this.speedModifier <= 3.0D)
                {
                    this.speedModifier *= 1.2D;  // Speed up
                    this.distCheckCounter = 40;     // Start our countdown timer
                    return true;
                }

                // If we haven't caught up in 40 iterations of this
                if (this.distCheckCounter == 0)
                {
                	// Stop following
                    return false;
                }
            }

            // Decrement our iteration countdown timer
            if (this.distCheckCounter > 0)
            {
                --this.distCheckCounter;
            }

            // Keep following
            return true;
        }
        else
        {
        	// Stop following
            return false;
        }
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.llama.leaveCaravan();  // Clear parent and child fields
        this.speedModifier = 2.1D;            // Reset our speed to normal
    }

    /**
     * Updates the task (updates the location this llama is moving towards while following)
     */
    public void updateTask()
    {
        if (this.llama.inCaravan())
        {
            EntityLlama entityllama = this.llama.getCaravanHead();            // Get this llama's parent/lead llama
            double d0 = (double)this.llama.getDistanceToEntity(entityllama);  // Distance to the parent/lead llama
            float f = 2.0F;
            // Get the position of the parent/lead llama
            Vec3d vec3d = (new Vec3d(entityllama.posX - this.llama.posX, entityllama.posY - this.llama.posY, entityllama.posZ - this.llama.posZ)).normalize().scale(Math.max(d0 - 2.0D, 0.0D));
            // Try to move to that llama's position
            this.llama.getNavigator().tryMoveToXYZ(this.llama.posX + vec3d.xCoord, this.llama.posY + vec3d.yCoord, this.llama.posZ + vec3d.zCoord, this.speedModifier);
        }
    }

    // Test if this is a valid caravan llama to follow (has a parent/lead llama or a leash)
    private boolean firstIsLeashed(EntityLlama p_190858_1_, int p_190858_2_)
    {
        if (p_190858_2_ > 8)
        {
        	// Recursion limit - if the llama caravan is 8 long already, we can't add to it
            return false;
        }
        // If this llama has a parent/lead
        else if (p_190858_1_.inCaravan())
        {
        	// If this llama's parent is leashed
            if (p_190858_1_.getCaravanHead().getLeashed())
            {
                return true;
            }
            else  // If this llama's parent is *not* leashed
            {
            	// Get this llama's parent/lead
                EntityLlama entityllama = p_190858_1_.getCaravanHead();
                // Increase the known length of our chain
                ++p_190858_2_;
                // Recursive call (limit 7 recursions) - return true if this llama's parent/lead has a parent/lead or a leash
                return this.firstIsLeashed(entityllama, p_190858_2_);
            }
        }
        else // Don't follow
        {
            return false;
        }
    }

}
