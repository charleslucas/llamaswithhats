package com.lucasi.llamaswithhats.entities.ai;

import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.util.math.Vec3d;

public class EntityAILlamaWithHatFollowCaravan extends EntityAIBase
{
    public EntityLlama field_190859_a;
    private double field_190860_b;
    private int field_190861_c;

    public EntityAILlamaWithHatFollowCaravan(EntityLlama p_i47305_1_, double p_i47305_2_)
    {
        this.field_190859_a = p_i47305_1_;  // This llama
        this.field_190860_b = p_i47305_2_;  // This llama's speed
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	// If not leashed and range is not NULL
        if (!this.field_190859_a.getLeashed() && !this.field_190859_a.func_190718_dR())
        {
            List<EntityLlama> list = this.field_190859_a.worldObj.<EntityLlama>getEntitiesWithinAABB(this.field_190859_a.getClass(), this.field_190859_a.getEntityBoundingBox().expand(9.0D, 4.0D, 9.0D));
            EntityLlama entityllama = null;
            double d0 = Double.MAX_VALUE;

            // Iterate over all llamas we found nearby
            for (EntityLlama entityllama1 : list)
            {
            	// If llama has parent/lead llama && does not have a child
                if (entityllama1.func_190718_dR() && !entityllama1.func_190712_dQ())
                {
                    double d1 = this.field_190859_a.getDistanceSqToEntity(entityllama1);

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
                    if (entityllama2.getLeashed() && !entityllama2.func_190712_dQ())
                    {
                        double d2 = this.field_190859_a.getDistanceSqToEntity(entityllama2);

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
            else if (!entityllama.getLeashed() && !this.func_190858_a(entityllama, 1))
            {
                return false;
            }
            else
            {
            	// Follow that llama!
                this.field_190859_a.func_190715_a(entityllama);
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
        if (this.field_190859_a.func_190718_dR() && this.field_190859_a.func_190716_dS().isEntityAlive() && this.func_190858_a(this.field_190859_a, 0))
        {
        	// Get distance to parent/lead llama
            double d0 = this.field_190859_a.getDistanceSqToEntity(this.field_190859_a.func_190716_dS());

            // If we're really far away from our parent/lead llama
            if (d0 > 676.0D)
            {
            	// If we're going slow
                if (this.field_190860_b <= 3.0D)
                {
                    this.field_190860_b *= 1.2D;  // Speed up
                    this.field_190861_c = 40;     // Start our countdown timer
                    return true;
                }

                // If we haven't caught up in 40 iterations of this
                if (this.field_190861_c == 0)
                {
                	// Stop following
                    return false;
                }
            }

            // Decrement our iteration countdown timer
            if (this.field_190861_c > 0)
            {
                --this.field_190861_c;
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
        this.field_190859_a.func_190709_dP();  // Clear parent and child fields
        this.field_190860_b = 2.1D;            // Reset our speed to normal
    }

    /**
     * Updates the task (updates the location this llama is moving towards while following)
     */
    public void updateTask()
    {
        if (this.field_190859_a.func_190718_dR())
        {
            EntityLlama entityllama = this.field_190859_a.func_190716_dS();            // Get this llama's parent/lead llama
            double d0 = (double)this.field_190859_a.getDistanceToEntity(entityllama);  // Distance to the parent/lead llama
            float f = 2.0F;
            // Get the position of the parent/lead llama
            Vec3d vec3d = (new Vec3d(entityllama.posX - this.field_190859_a.posX, entityllama.posY - this.field_190859_a.posY, entityllama.posZ - this.field_190859_a.posZ)).normalize().scale(Math.max(d0 - 2.0D, 0.0D));
            // Try to move to that llama's position
            this.field_190859_a.getNavigator().tryMoveToXYZ(this.field_190859_a.posX + vec3d.xCoord, this.field_190859_a.posY + vec3d.yCoord, this.field_190859_a.posZ + vec3d.zCoord, this.field_190860_b);
        }
    }

    // Test if this is a valid caravan llama to follow (has a parent/lead llama or a leash)
    private boolean func_190858_a(EntityLlama p_190858_1_, int p_190858_2_)
    {
        if (p_190858_2_ > 8)
        {
        	// Recursion limit - if the llama caravan is 8 long already, we can't add to it
            return false;
        }
        // If this llama has a parent/lead
        else if (p_190858_1_.func_190718_dR())
        {
        	// If this llama's parent is leashed
            if (p_190858_1_.func_190716_dS().getLeashed())
            {
                return true;
            }
            else  // If this llama's parent is *not* leashed
            {
            	// Get this llama's parent/lead
                EntityLlama entityllama = p_190858_1_.func_190716_dS();
                // Increase the known length of our chain
                ++p_190858_2_;
                // Recursive call (limit 7 recursions) - return true if this llama's parent/lead has a parent/lead or a leash
                return this.func_190858_a(entityllama, p_190858_2_);
            }
        }
        else // Don't follow
        {
            return false;
        }
    }

}
