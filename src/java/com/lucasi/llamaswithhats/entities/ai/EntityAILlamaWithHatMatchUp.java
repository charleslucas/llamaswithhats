package com.lucasi.llamaswithhats.entities.ai;

import java.util.List;

import com.lucasi.llamaswithhats.entities.EntityLlamaWithHat;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.util.math.Vec3d;

public class EntityAILlamaWithHatMatchUp extends EntityAIBase
{
    public EntityLlamaWithHat field_190859_a;
    private double field_190860_b;
    private int field_190861_c;

    public EntityAILlamaWithHatMatchUp(EntityLlamaWithHat p_i47305_1_, double p_i47305_2_)
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
    	// If not leashed and has no parent/lead llama
        if (!this.field_190859_a.getLeashed() && !this.field_190859_a.func_190718_dR())
        {
            List<EntityLlamaWithHat> list = this.field_190859_a.worldObj.<EntityLlamaWithHat>getEntitiesWithinAABB(this.field_190859_a.getClass(), this.field_190859_a.getEntityBoundingBox().expand(20.0D, 4.0D, 20.0D));
            EntityLlamaWithHat entityllama = null;
            double d0 = Double.MAX_VALUE;

            // Iterate over all llamas we found nearby
            for (EntityLlamaWithHat entityllama1 : list)
            {
            	// If llama is not leashed && does not have have parent/lead llama && does not have a child && is not us
                if (!entityllama1.getLeashed() && !entityllama1.func_190718_dR() && !entityllama1.func_190712_dQ()
                		&& field_190859_a.IsMatch(entityllama1.GetType()) && entityllama1 != this.field_190859_a)
                {
                    double d1 = this.field_190859_a.getDistanceSqToEntity(entityllama1);

                    if (d1 <= d0) // Iterate and store the closest qualified llama that's not us
                    {
            	    	System.out.println("Llama found at D" + d1 + "!");
                        d0 = d1;
                        entityllama = entityllama1;
                    }
                }
            }  // end for

            // If we found a llama that met our qualifications
            if (entityllama == null)
            {
    	    	System.out.println("Null llama!");
                return false;
            }
            else if (d0 < 7.0D) // We are already next to our meetup llama (allow for a buffer)
            {
                return false;
            }
            // If this llama is leashed or is already following something (double-check?)
            else if (entityllama.getLeashed() || entityllama.func_190718_dR())
            {
    	    	System.out.println("Llamas double-check!");
                return false;
            }
            else
            {
            	// Meet up with that llama!
    	    	System.out.println("Llamas wants to meet!");
                this.field_190859_a.func_190715_a(entityllama);  // Follow that llama
                entityllama.func_190715_a(this.field_190859_a);  // Set that llama to follow us
                return true;
            }
        }
        else
        {
        	// Do not meet with any llama
            return false;
        }
    }
    
    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
    	// If this llama has a matchup llama && that matchup llama is still alive && that meetup llama is still a good match
        if (this.field_190859_a.func_190718_dR() && this.field_190859_a.func_190716_dS().isEntityAlive() && this.field_190859_a.IsParentMatch())
        {
        	// Get distance to matchup llama
            double d0 = this.field_190859_a.getDistanceSqToEntity(this.field_190859_a.func_190716_dS());

            // If we're really far away from our matchup llama
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
                	// Stop trying to meet
                    return false;
                }
            }

            // If we're really far away from our matchup llama
            if (d0 < 6)
            {
            	// We've gotten close enough
            	return false;
            }

            // Decrement our iteration countdown timer
            if (this.field_190861_c > 0)
            {
                --this.field_190861_c;
            }

            // Keep trying to meet up
            return true;
        }
        else
        {
        	// Stop meeting
            return false;
        }
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
    	System.out.println("End friendship!");
    	if (this.field_190859_a.func_190716_dS() != null)  // If we llama we're following is following us
    	{
    		this.field_190859_a.func_190716_dS().func_190709_dP();  // Clear her parent and child fields	
    	}
        this.field_190859_a.func_190709_dP(); // Clear our parent and child fields
        this.field_190860_b = 2.1D;           // Reset our speed to normal
    }

    /**
     * Updates the task (updates the location this llama is moving towards while trying to match up)
     */
    public void updateTask()
    {
        if (this.field_190859_a.func_190718_dR())
        {
            EntityLlama entityllama = this.field_190859_a.func_190716_dS();            // Get this llama's matchup llama
            double d0 = (double)this.field_190859_a.getDistanceToEntity(entityllama);  // Distance to the matchup llama
            float f = 2.0F;
            // Get the position of the parent/lead llama
            Vec3d vec3d = (new Vec3d(entityllama.posX - this.field_190859_a.posX, entityllama.posY - this.field_190859_a.posY, entityllama.posZ - this.field_190859_a.posZ)).normalize().scale(Math.max(d0 - 2.0D, 0.0D));
            // Try to move to that llama's position
            this.field_190859_a.getNavigator().tryMoveToXYZ(this.field_190859_a.posX + vec3d.xCoord, this.field_190859_a.posY + vec3d.yCoord, this.field_190859_a.posZ + vec3d.zCoord, this.field_190860_b);
        }
    }

}
