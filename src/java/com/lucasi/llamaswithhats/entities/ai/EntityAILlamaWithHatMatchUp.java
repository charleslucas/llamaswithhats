package com.lucasi.llamaswithhats.entities.ai;

import java.util.List;

import com.lucasi.llamaswithhats.entities.EntityLlamaWithHat;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.util.math.Vec3d;

public class EntityAILlamaWithHatMatchUp extends EntityAIBase
{
    public EntityLlamaWithHat llama;
    private double speedModifier;
    private int distCheckCounter;

    public EntityAILlamaWithHatMatchUp(EntityLlamaWithHat llamaIn, double speedModifierIn)
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
    	// If not leashed and has no parent/lead llama
        if (!this.llama.getLeashed() && !this.llama.inCaravan())
        {
            List<EntityLlamaWithHat> list = this.llama.world.<EntityLlamaWithHat>getEntitiesWithinAABB(this.llama.getClass(), this.llama.getEntityBoundingBox().expand(20.0D, 4.0D, 20.0D));
            EntityLlamaWithHat entityllama = null;
            double d0 = Double.MAX_VALUE;

            // Iterate over all llamas we found nearby
            for (EntityLlamaWithHat entityllama1 : list)
            {
            	// If llama is not leashed && does not have have parent/lead llama && does not have a child && is not us
                if (!entityllama1.getLeashed() && !entityllama1.inCaravan() && !entityllama1.hasCaravanTrail()
                		&& llama.IsMatch(entityllama1.GetType()) && entityllama1 != this.llama)
                {
                    double d1 = this.llama.getDistanceSqToEntity(entityllama1);

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
            else if (entityllama.getLeashed() || entityllama.inCaravan())
            {
    	    	System.out.println("Llamas double-check!");
                return false;
            }
            else
            {
            	// Meet up with that llama!
    	    	System.out.println("Llamas wants to meet!");
                this.llama.joinCaravan(entityllama);  // Follow that llama
                entityllama.joinCaravan(this.llama);  // Set that llama to follow us
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
        if (this.llama.inCaravan() && this.llama.getCaravanHead().isEntityAlive() && this.llama.IsParentMatch())
        {
        	// Get distance to matchup llama
            double d0 = this.llama.getDistanceSqToEntity(this.llama.getCaravanHead());

            // If we're really far away from our matchup llama
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
            if (this.distCheckCounter > 0)
            {
                --this.distCheckCounter;
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
    	if (this.llama.getCaravanHead() != null)  // If we llama we're following is following us
    	{
    		this.llama.getCaravanHead().leaveCaravan();  // Clear her parent and child fields	
    	}
        this.llama.leaveCaravan(); // Clear our parent and child fields
        this.speedModifier = 2.1D;           // Reset our speed to normal
    }

    /**
     * Updates the task (updates the location this llama is moving towards while trying to match up)
     */
    public void updateTask()
    {
        if (this.llama.inCaravan())
        {
            EntityLlama entityllama = this.llama.getCaravanHead();            // Get this llama's matchup llama
            double d0 = (double)this.llama.getDistanceToEntity(entityllama);  // Distance to the matchup llama
            float f = 2.0F;
            // Get the position of the parent/lead llama
            Vec3d vec3d = (new Vec3d(entityllama.posX - this.llama.posX, entityllama.posY - this.llama.posY, entityllama.posZ - this.llama.posZ)).normalize().scale(Math.max(d0 - 2.0D, 0.0D));
            // Try to move to that llama's position
            this.llama.getNavigator().tryMoveToXYZ(this.llama.posX + vec3d.xCoord, this.llama.posY + vec3d.yCoord, this.llama.posZ + vec3d.zCoord, this.speedModifier);
        }
    }

}
