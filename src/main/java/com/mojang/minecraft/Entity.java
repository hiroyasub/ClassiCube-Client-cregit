begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|Serializable
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|level
operator|.
name|BlockMap
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|level
operator|.
name|Level
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|level
operator|.
name|liquid
operator|.
name|LiquidType
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|level
operator|.
name|tile
operator|.
name|Block
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|Vec3D
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|net
operator|.
name|PositionUpdate
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|phys
operator|.
name|AABB
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|player
operator|.
name|Player
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|render
operator|.
name|TextureManager
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|sound
operator|.
name|StepSound
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|MathHelper
import|;
end_import

begin_class
specifier|public
specifier|abstract
class|class
name|Entity
implements|implements
name|Serializable
block|{
specifier|public
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|0L
decl_stmt|;
specifier|public
name|Level
name|level
decl_stmt|;
specifier|public
name|float
name|xo
decl_stmt|;
specifier|public
name|float
name|yo
decl_stmt|;
specifier|public
name|float
name|zo
decl_stmt|;
specifier|public
name|float
name|x
decl_stmt|;
specifier|public
name|float
name|y
decl_stmt|;
specifier|public
name|float
name|z
decl_stmt|;
specifier|public
name|float
name|xd
decl_stmt|;
specifier|public
name|float
name|yd
decl_stmt|;
specifier|public
name|float
name|zd
decl_stmt|;
specifier|public
name|float
name|yRot
decl_stmt|;
specifier|public
name|float
name|xRot
decl_stmt|;
specifier|public
name|float
name|yRotO
decl_stmt|;
specifier|public
name|float
name|xRotO
decl_stmt|;
comment|/**      * The bounding box of this Entity.      */
specifier|public
name|AABB
name|bb
decl_stmt|;
specifier|public
name|boolean
name|onGround
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|horizontalCollision
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|collision
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|slide
init|=
literal|true
decl_stmt|;
specifier|public
name|boolean
name|removed
init|=
literal|false
decl_stmt|;
specifier|public
name|float
name|heightOffset
init|=
literal|0.0F
decl_stmt|;
specifier|public
name|float
name|bbWidth
init|=
literal|0.6F
decl_stmt|;
specifier|public
name|float
name|bbHeight
init|=
literal|1.8F
decl_stmt|;
specifier|public
name|float
name|walkDistO
init|=
literal|0.0F
decl_stmt|;
specifier|public
name|float
name|walkDist
init|=
literal|0.0F
decl_stmt|;
specifier|public
name|boolean
name|makeStepSound
init|=
literal|true
decl_stmt|;
specifier|public
name|float
name|fallDistance
init|=
literal|0.0F
decl_stmt|;
specifier|private
name|int
name|nextStep
init|=
literal|1
decl_stmt|;
specifier|public
name|BlockMap
name|blockMap
decl_stmt|;
specifier|public
name|float
name|xOld
decl_stmt|;
specifier|public
name|float
name|yOld
decl_stmt|;
specifier|public
name|float
name|zOld
decl_stmt|;
specifier|public
name|int
name|textureId
init|=
literal|0
decl_stmt|;
specifier|public
name|float
name|ySlideOffset
init|=
literal|0.0F
decl_stmt|;
specifier|public
name|float
name|footSize
init|=
literal|0.0F
decl_stmt|;
specifier|public
name|boolean
name|noPhysics
init|=
literal|false
decl_stmt|;
specifier|public
name|float
name|pushthrough
init|=
literal|0.0F
decl_stmt|;
specifier|public
name|boolean
name|hovered
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|flyingMode
init|=
literal|false
decl_stmt|;
specifier|private
name|int
name|nextStepDistance
decl_stmt|;
specifier|public
name|float
name|prevDistanceWalkedModified
decl_stmt|;
specifier|public
name|float
name|distanceWalkedModified
decl_stmt|;
specifier|public
name|float
name|distanceWalkedOnStepModified
decl_stmt|;
specifier|public
name|Entity
parameter_list|(
name|Level
name|var1
parameter_list|)
block|{
name|level
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|setPos
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|awardKillScore
parameter_list|(
name|Entity
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
block|}
specifier|protected
name|void
name|causeFallDamage
parameter_list|(
name|float
name|var1
parameter_list|)
block|{
block|}
comment|/**      * Calculates the distance from this entity to the specified entity.      *      * @param otherEntity      *            Entity to calculate the distance to.      * @return The distance between the two entities.      */
specifier|public
name|float
name|distanceTo
parameter_list|(
name|Entity
name|otherEntity
parameter_list|)
block|{
return|return
name|distanceTo
argument_list|(
name|otherEntity
operator|.
name|x
argument_list|,
name|otherEntity
operator|.
name|y
argument_list|,
name|otherEntity
operator|.
name|z
argument_list|)
return|;
block|}
comment|/**      * Calculates the distance from this entity to the specified position.      *      * @param posX      *            X-Coordinate of the position to calculate the distance to.      * @param posY      *            Y-Coordinate of the position to calculate the distance to.      * @param posZ      *            Z-Coordinate of the position to calculate the distance to.      * @return The distance between the entity and the position.      */
specifier|public
name|float
name|distanceTo
parameter_list|(
name|float
name|posX
parameter_list|,
name|float
name|posY
parameter_list|,
name|float
name|posZ
parameter_list|)
block|{
comment|// Euclidean distance
name|float
name|dx
init|=
name|x
operator|-
name|posX
decl_stmt|;
name|float
name|dy
init|=
name|y
operator|-
name|posY
decl_stmt|;
name|float
name|dz
init|=
name|z
operator|-
name|posZ
decl_stmt|;
return|return
name|MathHelper
operator|.
name|sqrt
argument_list|(
operator|(
name|dx
operator|*
name|dx
operator|)
operator|+
operator|(
name|dy
operator|*
name|dy
operator|)
operator|+
operator|(
name|dz
operator|*
name|dz
operator|)
argument_list|)
return|;
block|}
comment|/**      * Calculates the distance from this entity to the specified entity squared.      * This is basically calculating distance without using the expensive      * Math.sqrt function. Should only be used for relative distance.      *      * @param otherEntity      *            Entity to calculate the distance to.      * @return The distance between the two entities squared.      */
specifier|public
name|float
name|distanceToSqr
parameter_list|(
name|Entity
name|otherEntity
parameter_list|)
block|{
name|float
name|dx
init|=
name|x
operator|-
name|otherEntity
operator|.
name|x
decl_stmt|;
name|float
name|dy
init|=
name|y
operator|-
name|otherEntity
operator|.
name|y
decl_stmt|;
name|float
name|dz
init|=
name|z
operator|-
name|otherEntity
operator|.
name|z
decl_stmt|;
return|return
operator|(
name|dx
operator|*
name|dx
operator|)
operator|+
operator|(
name|dy
operator|*
name|dy
operator|)
operator|+
operator|(
name|dz
operator|*
name|dz
operator|)
return|;
block|}
comment|/**      * Gets the brightness of this entity      *      * @return Brightness of the entity.      */
specifier|public
name|float
name|getBrightness
parameter_list|()
block|{
name|int
name|posX
init|=
operator|(
name|int
operator|)
name|x
decl_stmt|;
name|int
name|posY
init|=
operator|(
name|int
operator|)
operator|(
name|y
operator|+
name|heightOffset
operator|/
literal|2.0F
operator|-
literal|0.5F
operator|)
decl_stmt|;
name|int
name|posZ
init|=
operator|(
name|int
operator|)
name|z
decl_stmt|;
return|return
name|level
operator|.
name|getBrightness
argument_list|(
name|posX
argument_list|,
name|posY
argument_list|,
name|posZ
argument_list|)
return|;
block|}
comment|/**      * Gets the brightness color of this entity.      *      * @return ColorCache containing brightness color information.      */
specifier|public
name|ColorCache
name|getBrightnessColor
parameter_list|()
block|{
name|int
name|posX
init|=
operator|(
name|int
operator|)
name|x
decl_stmt|;
name|int
name|posY
init|=
operator|(
name|int
operator|)
operator|(
name|y
operator|+
name|heightOffset
operator|/
literal|2.0F
operator|-
literal|0.5F
operator|)
decl_stmt|;
name|int
name|posZ
init|=
operator|(
name|int
operator|)
name|z
decl_stmt|;
return|return
name|level
operator|.
name|getBrightnessColor
argument_list|(
name|posX
argument_list|,
name|posY
argument_list|,
name|posZ
argument_list|)
return|;
block|}
comment|/**      * Gets the texture ID of this entity.      *      * @return Entity's Texture ID.      */
specifier|public
name|int
name|getTexture
parameter_list|()
block|{
return|return
name|textureId
return|;
block|}
specifier|public
name|void
name|hurt
parameter_list|(
name|Entity
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
block|}
specifier|public
name|void
name|interpolateTurn
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
name|yRot
operator|=
operator|(
name|float
operator|)
operator|(
name|yRot
operator|+
name|var1
operator|*
literal|0.15D
operator|)
expr_stmt|;
name|xRot
operator|=
operator|(
name|float
operator|)
operator|(
name|xRot
operator|-
name|var2
operator|*
literal|0.15D
operator|)
expr_stmt|;
if|if
condition|(
name|xRot
operator|<
operator|-
literal|90.0F
condition|)
block|{
name|xRot
operator|=
operator|-
literal|90.0F
expr_stmt|;
block|}
if|if
condition|(
name|xRot
operator|>
literal|90.0F
condition|)
block|{
name|xRot
operator|=
literal|90.0F
expr_stmt|;
block|}
block|}
specifier|public
name|boolean
name|intersects
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|float
name|var4
parameter_list|,
name|float
name|var5
parameter_list|,
name|float
name|var6
parameter_list|)
block|{
return|return
name|bb
operator|.
name|intersects
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|)
return|;
block|}
specifier|public
name|boolean
name|isCreativeModeAllowed
parameter_list|()
block|{
return|return
literal|false
return|;
block|}
specifier|public
name|boolean
name|isFree
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|)
block|{
name|AABB
name|var4
init|=
name|bb
operator|.
name|cloneMove
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|)
decl_stmt|;
return|return
name|level
operator|.
name|getCubes
argument_list|(
name|var4
argument_list|)
operator|.
name|size
argument_list|()
operator|>
literal|0
condition|?
literal|false
else|:
operator|!
name|level
operator|.
name|containsAnyLiquid
argument_list|(
name|var4
argument_list|)
return|;
block|}
specifier|public
name|boolean
name|isFree
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|float
name|var4
parameter_list|)
block|{
name|AABB
name|var5
init|=
name|bb
operator|.
name|grow
argument_list|(
name|var4
argument_list|,
name|var4
argument_list|,
name|var4
argument_list|)
operator|.
name|cloneMove
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|)
decl_stmt|;
return|return
name|level
operator|.
name|getCubes
argument_list|(
name|var5
argument_list|)
operator|.
name|size
argument_list|()
operator|>
literal|0
condition|?
literal|false
else|:
operator|!
name|level
operator|.
name|containsAnyLiquid
argument_list|(
name|var5
argument_list|)
return|;
block|}
specifier|public
name|boolean
name|isInLava
parameter_list|()
block|{
return|return
name|level
operator|.
name|containsLiquid
argument_list|(
name|bb
operator|.
name|grow
argument_list|(
literal|0.0F
argument_list|,
operator|-
literal|0.4F
argument_list|,
literal|0.0F
argument_list|)
argument_list|,
name|LiquidType
operator|.
name|lava
argument_list|)
return|;
block|}
specifier|public
name|boolean
name|isInOrOnRope
parameter_list|()
block|{
return|return
name|level
operator|.
name|containsBlock
argument_list|(
name|bb
operator|.
name|grow
argument_list|(
operator|-
literal|0.5F
argument_list|,
literal|0.0F
argument_list|,
operator|-
literal|0.5F
argument_list|)
argument_list|,
name|Block
operator|.
name|ROPE
argument_list|)
return|;
block|}
specifier|public
name|boolean
name|isInWater
parameter_list|()
block|{
return|return
name|level
operator|.
name|containsLiquid
argument_list|(
name|bb
operator|.
name|grow
argument_list|(
literal|0.0F
argument_list|,
operator|-
literal|0.4F
argument_list|,
literal|0.0F
argument_list|)
argument_list|,
name|LiquidType
operator|.
name|water
argument_list|)
return|;
block|}
specifier|public
name|boolean
name|isLit
parameter_list|()
block|{
name|int
name|var1
init|=
operator|(
name|int
operator|)
name|x
decl_stmt|;
name|int
name|var2
init|=
operator|(
name|int
operator|)
name|y
decl_stmt|;
name|int
name|var3
init|=
operator|(
name|int
operator|)
name|z
decl_stmt|;
return|return
name|level
operator|.
name|isLit
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|)
return|;
block|}
specifier|public
name|boolean
name|isPickable
parameter_list|()
block|{
return|return
literal|false
return|;
block|}
specifier|public
name|boolean
name|isPushable
parameter_list|()
block|{
return|return
literal|false
return|;
block|}
specifier|public
name|boolean
name|isShootable
parameter_list|()
block|{
return|return
literal|false
return|;
block|}
specifier|public
name|boolean
name|isUnderWater
parameter_list|()
block|{
name|int
name|var1
decl_stmt|;
return|return
operator|(
name|var1
operator|=
name|level
operator|.
name|getTile
argument_list|(
operator|(
name|int
operator|)
name|x
argument_list|,
operator|(
name|int
operator|)
operator|(
name|y
operator|+
literal|0.12F
operator|)
argument_list|,
operator|(
name|int
operator|)
name|z
argument_list|)
operator|)
operator|!=
literal|0
condition|?
name|Block
operator|.
name|blocks
index|[
name|var1
index|]
operator|.
name|getLiquidType
argument_list|()
operator|.
name|equals
argument_list|(
name|LiquidType
operator|.
name|water
argument_list|)
else|:
literal|false
return|;
block|}
specifier|public
name|void
name|move
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|)
block|{
if|if
condition|(
name|noPhysics
condition|)
block|{
name|bb
operator|.
name|move
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|)
expr_stmt|;
name|x
operator|=
operator|(
name|bb
operator|.
name|x0
operator|+
name|bb
operator|.
name|x1
operator|)
operator|/
literal|2.0F
expr_stmt|;
comment|// if((this.bb.y0 + this.heightOffset - this.ySlideOffset)> y){
name|y
operator|=
name|bb
operator|.
name|y0
operator|+
name|heightOffset
operator|-
name|ySlideOffset
expr_stmt|;
comment|// }
name|z
operator|=
operator|(
name|bb
operator|.
name|z0
operator|+
name|bb
operator|.
name|z1
operator|)
operator|/
literal|2.0F
expr_stmt|;
comment|// this.yd = 0;
block|}
else|else
block|{
name|float
name|var4
init|=
name|x
decl_stmt|;
name|float
name|var5
init|=
name|z
decl_stmt|;
name|float
name|var6
init|=
name|var1
decl_stmt|;
name|float
name|var7
init|=
name|var2
decl_stmt|;
name|float
name|var8
init|=
name|var3
decl_stmt|;
name|AABB
name|var9
init|=
name|bb
operator|.
name|copy
argument_list|()
decl_stmt|;
name|ArrayList
argument_list|<
name|AABB
argument_list|>
name|var10
init|=
name|level
operator|.
name|getCubes
argument_list|(
name|bb
operator|.
name|expand
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|)
argument_list|)
decl_stmt|;
for|for
control|(
name|int
name|var11
init|=
literal|0
init|;
name|var11
operator|<
name|var10
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var11
control|)
block|{
name|var2
operator|=
name|var10
operator|.
name|get
argument_list|(
name|var11
argument_list|)
operator|.
name|clipYCollide
argument_list|(
name|bb
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
name|bb
operator|.
name|move
argument_list|(
literal|0.0F
argument_list|,
name|var2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|slide
operator|&&
name|var7
operator|!=
name|var2
condition|)
block|{
name|var3
operator|=
literal|0.0F
expr_stmt|;
name|var2
operator|=
literal|0.0F
expr_stmt|;
name|var1
operator|=
literal|0.0F
expr_stmt|;
block|}
name|boolean
name|var16
init|=
name|onGround
operator|||
name|var7
operator|!=
name|var2
operator|&&
name|var7
operator|<
literal|0.0F
decl_stmt|;
name|int
name|var12
decl_stmt|;
for|for
control|(
name|var12
operator|=
literal|0
init|;
name|var12
operator|<
name|var10
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var12
control|)
block|{
name|var1
operator|=
name|var10
operator|.
name|get
argument_list|(
name|var12
argument_list|)
operator|.
name|clipXCollide
argument_list|(
name|bb
argument_list|,
name|var1
argument_list|)
expr_stmt|;
block|}
name|bb
operator|.
name|move
argument_list|(
name|var1
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|slide
operator|&&
name|var6
operator|!=
name|var1
condition|)
block|{
name|var3
operator|=
literal|0.0F
expr_stmt|;
name|var2
operator|=
literal|0.0F
expr_stmt|;
name|var1
operator|=
literal|0.0F
expr_stmt|;
block|}
for|for
control|(
name|var12
operator|=
literal|0
init|;
name|var12
operator|<
name|var10
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var12
control|)
block|{
name|var3
operator|=
name|var10
operator|.
name|get
argument_list|(
name|var12
argument_list|)
operator|.
name|clipZCollide
argument_list|(
name|bb
argument_list|,
name|var3
argument_list|)
expr_stmt|;
block|}
name|bb
operator|.
name|move
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
name|var3
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|slide
operator|&&
name|var8
operator|!=
name|var3
condition|)
block|{
name|var3
operator|=
literal|0.0F
expr_stmt|;
name|var2
operator|=
literal|0.0F
expr_stmt|;
name|var1
operator|=
literal|0.0F
expr_stmt|;
block|}
name|float
name|var17
decl_stmt|;
name|float
name|var18
decl_stmt|;
if|if
condition|(
name|footSize
operator|>
literal|0.0F
operator|&&
name|var16
operator|&&
name|ySlideOffset
operator|<
literal|0.05F
operator|&&
operator|(
name|var6
operator|!=
name|var1
operator|||
name|var8
operator|!=
name|var3
operator|)
condition|)
block|{
name|var18
operator|=
name|var1
expr_stmt|;
name|var17
operator|=
name|var2
expr_stmt|;
name|float
name|var13
init|=
name|var3
decl_stmt|;
name|var1
operator|=
name|var6
expr_stmt|;
name|var2
operator|=
name|footSize
expr_stmt|;
name|var3
operator|=
name|var8
expr_stmt|;
name|AABB
name|var14
init|=
name|bb
operator|.
name|copy
argument_list|()
decl_stmt|;
name|bb
operator|=
name|var9
operator|.
name|copy
argument_list|()
expr_stmt|;
name|var10
operator|=
name|level
operator|.
name|getCubes
argument_list|(
name|bb
operator|.
name|expand
argument_list|(
name|var6
argument_list|,
name|var2
argument_list|,
name|var8
argument_list|)
argument_list|)
expr_stmt|;
name|int
name|var15
decl_stmt|;
for|for
control|(
name|var15
operator|=
literal|0
init|;
name|var15
operator|<
name|var10
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var15
control|)
block|{
name|var2
operator|=
name|var10
operator|.
name|get
argument_list|(
name|var15
argument_list|)
operator|.
name|clipYCollide
argument_list|(
name|bb
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
name|bb
operator|.
name|move
argument_list|(
literal|0.0F
argument_list|,
name|var2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|slide
operator|&&
name|var7
operator|!=
name|var2
condition|)
block|{
name|var3
operator|=
literal|0.0F
expr_stmt|;
name|var2
operator|=
literal|0.0F
expr_stmt|;
name|var1
operator|=
literal|0.0F
expr_stmt|;
block|}
for|for
control|(
name|var15
operator|=
literal|0
init|;
name|var15
operator|<
name|var10
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var15
control|)
block|{
name|var1
operator|=
name|var10
operator|.
name|get
argument_list|(
name|var15
argument_list|)
operator|.
name|clipXCollide
argument_list|(
name|bb
argument_list|,
name|var1
argument_list|)
expr_stmt|;
block|}
name|bb
operator|.
name|move
argument_list|(
name|var1
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|slide
operator|&&
name|var6
operator|!=
name|var1
condition|)
block|{
name|var3
operator|=
literal|0.0F
expr_stmt|;
name|var2
operator|=
literal|0.0F
expr_stmt|;
name|var1
operator|=
literal|0.0F
expr_stmt|;
block|}
for|for
control|(
name|var15
operator|=
literal|0
init|;
name|var15
operator|<
name|var10
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var15
control|)
block|{
name|var3
operator|=
name|var10
operator|.
name|get
argument_list|(
name|var15
argument_list|)
operator|.
name|clipZCollide
argument_list|(
name|bb
argument_list|,
name|var3
argument_list|)
expr_stmt|;
block|}
name|bb
operator|.
name|move
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
name|var3
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|slide
operator|&&
name|var8
operator|!=
name|var3
condition|)
block|{
name|var3
operator|=
literal|0.0F
expr_stmt|;
name|var2
operator|=
literal|0.0F
expr_stmt|;
name|var1
operator|=
literal|0.0F
expr_stmt|;
block|}
if|if
condition|(
name|var18
operator|*
name|var18
operator|+
name|var13
operator|*
name|var13
operator|>=
name|var1
operator|*
name|var1
operator|+
name|var3
operator|*
name|var3
condition|)
block|{
name|var1
operator|=
name|var18
expr_stmt|;
name|var2
operator|=
name|var17
expr_stmt|;
name|var3
operator|=
name|var13
expr_stmt|;
name|bb
operator|=
name|var14
operator|.
name|copy
argument_list|()
expr_stmt|;
block|}
else|else
block|{
name|ySlideOffset
operator|=
operator|(
name|float
operator|)
operator|(
name|ySlideOffset
operator|+
literal|0.5D
operator|)
expr_stmt|;
block|}
block|}
name|horizontalCollision
operator|=
name|var6
operator|!=
name|var1
operator|||
name|var8
operator|!=
name|var3
expr_stmt|;
name|onGround
operator|=
name|var7
operator|!=
name|var2
operator|&&
name|var7
operator|<
literal|0.0F
expr_stmt|;
name|collision
operator|=
name|horizontalCollision
operator|||
name|var7
operator|!=
name|var2
expr_stmt|;
if|if
condition|(
name|onGround
condition|)
block|{
if|if
condition|(
name|fallDistance
operator|>
literal|0.0F
condition|)
block|{
name|causeFallDamage
argument_list|(
name|fallDistance
operator|/
literal|2
argument_list|)
expr_stmt|;
name|fallDistance
operator|=
literal|0.0F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|var2
operator|<
literal|0.0F
condition|)
block|{
name|fallDistance
operator|-=
name|var2
expr_stmt|;
block|}
if|if
condition|(
name|var6
operator|!=
name|var1
condition|)
block|{
name|xd
operator|=
literal|0.0F
expr_stmt|;
block|}
if|if
condition|(
name|var7
operator|!=
name|var2
condition|)
block|{
name|yd
operator|=
literal|0.0F
expr_stmt|;
block|}
if|if
condition|(
name|var8
operator|!=
name|var3
condition|)
block|{
name|zd
operator|=
literal|0.0F
expr_stmt|;
block|}
name|x
operator|=
operator|(
name|bb
operator|.
name|x0
operator|+
name|bb
operator|.
name|x1
operator|)
operator|/
literal|2.0F
expr_stmt|;
name|y
operator|=
name|bb
operator|.
name|y0
operator|+
name|heightOffset
operator|-
name|ySlideOffset
expr_stmt|;
name|z
operator|=
operator|(
name|bb
operator|.
name|z0
operator|+
name|bb
operator|.
name|z1
operator|)
operator|/
literal|2.0F
expr_stmt|;
name|var18
operator|=
name|x
operator|-
name|var4
expr_stmt|;
name|var17
operator|=
name|z
operator|-
name|var5
expr_stmt|;
name|walkDist
operator|=
operator|(
name|float
operator|)
operator|(
name|walkDist
operator|+
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|var18
operator|*
name|var18
operator|+
name|var17
operator|*
name|var17
argument_list|)
operator|*
literal|0.6D
operator|)
expr_stmt|;
block|}
name|int
name|var39
init|=
operator|(
name|int
operator|)
name|Math
operator|.
name|floor
argument_list|(
name|x
argument_list|)
decl_stmt|;
name|int
name|var30
init|=
operator|(
name|int
operator|)
name|Math
operator|.
name|floor
argument_list|(
name|y
operator|-
literal|0.20000000298023224D
operator|-
name|heightOffset
argument_list|)
decl_stmt|;
name|int
name|var31
init|=
operator|(
name|int
operator|)
name|Math
operator|.
name|floor
argument_list|(
name|z
argument_list|)
decl_stmt|;
name|int
name|var32
init|=
name|level
operator|.
name|getTile
argument_list|(
name|var39
argument_list|,
name|var30
argument_list|,
name|var31
argument_list|)
decl_stmt|;
if|if
condition|(
name|makeStepSound
operator|&&
name|onGround
operator|&&
operator|!
name|noPhysics
condition|)
block|{
if|if
condition|(
name|this
operator|instanceof
name|Player
operator|&&
operator|!
operator|(
operator|(
name|Player
operator|)
name|this
operator|)
operator|.
name|noPhysics
condition|)
block|{
name|distanceWalkedModified
operator|=
operator|(
name|float
operator|)
operator|(
name|distanceWalkedModified
operator|+
name|Math
operator|.
name|sqrt
argument_list|(
name|var1
operator|*
name|var1
operator|+
name|var3
operator|*
name|var3
argument_list|)
operator|*
literal|0.6D
operator|)
expr_stmt|;
name|distanceWalkedOnStepModified
operator|=
operator|(
name|float
operator|)
operator|(
name|distanceWalkedOnStepModified
operator|+
name|Math
operator|.
name|sqrt
argument_list|(
name|var1
operator|*
name|var1
operator|+
name|var2
operator|*
name|var2
operator|+
name|var3
operator|*
name|var3
argument_list|)
operator|*
literal|0.6D
operator|)
expr_stmt|;
if|if
condition|(
name|distanceWalkedOnStepModified
operator|>
name|nextStepDistance
operator|&&
name|var32
operator|>
literal|0
condition|)
block|{
name|nextStepDistance
operator|=
operator|(
name|int
operator|)
name|distanceWalkedOnStepModified
operator|+
literal|1
expr_stmt|;
if|if
condition|(
name|onGround
condition|)
block|{
name|playStepSound
argument_list|(
name|var32
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
if|if
condition|(
name|walkDist
operator|>
name|nextStep
operator|&&
name|var32
operator|>
literal|0
condition|)
block|{
operator|++
name|nextStep
expr_stmt|;
block|}
name|ySlideOffset
operator|*=
literal|0.4F
expr_stmt|;
block|}
specifier|public
name|void
name|moveRelative
parameter_list|(
name|float
name|x
parameter_list|,
name|float
name|y
parameter_list|,
name|float
name|z
parameter_list|)
block|{
name|float
name|var4
decl_stmt|;
if|if
condition|(
operator|(
name|var4
operator|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|x
operator|*
name|x
operator|+
name|y
operator|*
name|y
argument_list|)
operator|)
operator|>=
literal|0.01F
condition|)
block|{
if|if
condition|(
name|var4
operator|<
literal|1.0F
condition|)
block|{
name|var4
operator|=
literal|1.0F
expr_stmt|;
block|}
name|var4
operator|=
name|z
operator|/
name|var4
expr_stmt|;
name|x
operator|*=
name|var4
expr_stmt|;
name|y
operator|*=
name|var4
expr_stmt|;
name|z
operator|=
name|MathHelper
operator|.
name|sin
argument_list|(
name|yRot
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
operator|/
literal|180.0F
argument_list|)
expr_stmt|;
name|var4
operator|=
name|MathHelper
operator|.
name|cos
argument_list|(
name|yRot
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
operator|/
literal|180.0F
argument_list|)
expr_stmt|;
name|xd
operator|+=
name|x
operator|*
name|var4
operator|-
name|y
operator|*
name|z
expr_stmt|;
name|zd
operator|+=
name|y
operator|*
name|var4
operator|+
name|x
operator|*
name|z
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|moveTo
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|float
name|var4
parameter_list|,
name|float
name|var5
parameter_list|)
block|{
name|xo
operator|=
name|x
operator|=
name|var1
expr_stmt|;
name|yo
operator|=
name|y
operator|=
name|var2
expr_stmt|;
name|zo
operator|=
name|z
operator|=
name|var3
expr_stmt|;
name|yRot
operator|=
name|var4
expr_stmt|;
name|xRot
operator|=
name|var5
expr_stmt|;
name|this
operator|.
name|setPos
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|playerTouch
parameter_list|(
name|Entity
name|var1
parameter_list|)
block|{
block|}
specifier|public
name|void
name|playSound
parameter_list|(
name|String
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|)
block|{
name|level
operator|.
name|playSound
argument_list|(
name|var1
argument_list|,
name|this
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
literal|false
argument_list|)
expr_stmt|;
block|}
specifier|protected
name|void
name|playStepSound
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
name|StepSound
name|var2
init|=
name|Block
operator|.
name|blocks
index|[
name|var1
index|]
operator|.
name|stepSound
decl_stmt|;
if|if
condition|(
operator|!
name|Block
operator|.
name|blocks
index|[
name|var1
index|]
operator|.
name|isLiquid
argument_list|()
condition|)
block|{
name|playSound
argument_list|(
name|var2
operator|.
name|getStepSound
argument_list|()
argument_list|,
name|var2
operator|.
name|getVolume
argument_list|()
operator|*
literal|0.70F
argument_list|,
name|var2
operator|.
name|getPitch
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|push
parameter_list|(
name|Entity
name|var1
parameter_list|)
block|{
name|float
name|var2
init|=
name|var1
operator|.
name|x
operator|-
name|x
decl_stmt|;
name|float
name|var3
init|=
name|var1
operator|.
name|z
operator|-
name|z
decl_stmt|;
name|float
name|var4
decl_stmt|;
if|if
condition|(
operator|(
name|var4
operator|=
name|var2
operator|*
name|var2
operator|+
name|var3
operator|*
name|var3
operator|)
operator|>=
literal|0.01F
condition|)
block|{
name|var4
operator|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|var4
argument_list|)
expr_stmt|;
name|var2
operator|/=
name|var4
expr_stmt|;
name|var3
operator|/=
name|var4
expr_stmt|;
name|var2
operator|/=
name|var4
expr_stmt|;
name|var3
operator|/=
name|var4
expr_stmt|;
name|var2
operator|*=
literal|0.05F
expr_stmt|;
name|var3
operator|*=
literal|0.05F
expr_stmt|;
name|var2
operator|*=
literal|1.0F
operator|-
name|pushthrough
expr_stmt|;
name|var3
operator|*=
literal|1.0F
operator|-
name|pushthrough
expr_stmt|;
name|this
operator|.
name|push
argument_list|(
operator|-
name|var2
argument_list|,
literal|0.0F
argument_list|,
operator|-
name|var3
argument_list|)
expr_stmt|;
name|var1
operator|.
name|push
argument_list|(
name|var2
argument_list|,
literal|0.0F
argument_list|,
name|var3
argument_list|)
expr_stmt|;
block|}
block|}
specifier|protected
name|void
name|push
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|)
block|{
name|xd
operator|+=
name|var1
expr_stmt|;
name|yd
operator|+=
name|var2
expr_stmt|;
name|zd
operator|+=
name|var3
expr_stmt|;
block|}
specifier|public
name|void
name|remove
parameter_list|()
block|{
name|removed
operator|=
literal|true
expr_stmt|;
block|}
specifier|public
name|void
name|render
parameter_list|(
name|TextureManager
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
block|}
specifier|public
name|void
name|renderHover
parameter_list|(
name|TextureManager
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
block|}
specifier|public
name|void
name|resetPos
parameter_list|()
block|{
if|if
condition|(
name|level
operator|!=
literal|null
condition|)
block|{
name|float
name|var1
init|=
name|level
operator|.
name|xSpawn
operator|+
literal|0.5F
decl_stmt|;
name|float
name|var2
init|=
name|level
operator|.
name|ySpawn
decl_stmt|;
for|for
control|(
name|double
name|var3
init|=
name|level
operator|.
name|zSpawn
operator|+
literal|0.5F
init|;
name|var2
operator|>
literal|0.0F
condition|;
operator|++
name|var2
control|)
block|{
name|this
operator|.
name|setPos
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
operator|(
name|float
operator|)
name|var3
argument_list|)
expr_stmt|;
if|if
condition|(
name|level
operator|.
name|isInBounds
argument_list|(
operator|(
name|int
operator|)
name|var1
argument_list|,
operator|(
name|int
operator|)
name|var2
argument_list|,
operator|(
name|int
operator|)
name|var3
argument_list|)
condition|)
block|{
if|if
condition|(
name|level
operator|.
name|getCubes
argument_list|(
name|bb
argument_list|)
operator|.
name|size
argument_list|()
operator|==
literal|0
condition|)
block|{
break|break;
block|}
block|}
else|else
block|{
name|var2
operator|=
name|level
operator|.
name|ySpawn
expr_stmt|;
break|break;
block|}
block|}
block|}
block|}
specifier|public
name|void
name|setLevel
parameter_list|(
name|Level
name|var1
parameter_list|)
block|{
name|level
operator|=
name|var1
expr_stmt|;
block|}
specifier|public
name|void
name|setPos
parameter_list|(
name|float
name|x
parameter_list|,
name|float
name|y
parameter_list|,
name|float
name|z
parameter_list|)
block|{
name|this
operator|.
name|x
operator|=
name|x
expr_stmt|;
name|this
operator|.
name|y
operator|=
name|y
expr_stmt|;
name|this
operator|.
name|z
operator|=
name|z
expr_stmt|;
name|float
name|var4
init|=
name|bbWidth
operator|/
literal|2.0F
decl_stmt|;
name|float
name|var5
init|=
name|bbHeight
operator|/
literal|2.0F
decl_stmt|;
name|bb
operator|=
operator|new
name|AABB
argument_list|(
name|x
operator|-
name|var4
argument_list|,
name|y
operator|-
name|var5
argument_list|,
name|z
operator|-
name|var4
argument_list|,
name|x
operator|+
name|var4
argument_list|,
name|y
operator|+
name|var5
argument_list|,
name|z
operator|+
name|var4
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|setPos
parameter_list|(
name|PositionUpdate
name|var1
parameter_list|)
block|{
if|if
condition|(
name|var1
operator|.
name|position
condition|)
block|{
name|this
operator|.
name|setPos
argument_list|(
name|var1
operator|.
name|x
argument_list|,
name|var1
operator|.
name|y
argument_list|,
name|var1
operator|.
name|z
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|setPos
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|.
name|rotation
condition|)
block|{
name|setRot
argument_list|(
name|var1
operator|.
name|yaw
argument_list|,
name|var1
operator|.
name|pitch
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|setRot
argument_list|(
name|yRot
argument_list|,
name|xRot
argument_list|)
expr_stmt|;
block|}
block|}
specifier|protected
name|void
name|setRot
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
name|yRot
operator|=
name|var1
expr_stmt|;
name|xRot
operator|=
name|var2
expr_stmt|;
block|}
specifier|public
name|void
name|setSize
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
name|bbWidth
operator|=
name|var1
expr_stmt|;
name|bbHeight
operator|=
name|var2
expr_stmt|;
block|}
specifier|public
name|boolean
name|shouldRender
parameter_list|(
name|Vec3D
name|var1
parameter_list|)
block|{
name|float
name|var2
init|=
name|x
operator|-
name|var1
operator|.
name|x
decl_stmt|;
name|float
name|var3
init|=
name|y
operator|-
name|var1
operator|.
name|y
decl_stmt|;
name|float
name|var4
init|=
name|z
operator|-
name|var1
operator|.
name|z
decl_stmt|;
name|var4
operator|=
name|var2
operator|*
name|var2
operator|+
name|var3
operator|*
name|var3
operator|+
name|var4
operator|*
name|var4
expr_stmt|;
return|return
name|shouldRenderAtSqrDistance
argument_list|(
name|var4
argument_list|)
return|;
block|}
specifier|public
name|boolean
name|shouldRenderAtSqrDistance
parameter_list|(
name|float
name|var1
parameter_list|)
block|{
name|float
name|var2
init|=
name|bb
operator|.
name|getSize
argument_list|()
operator|*
literal|64.0F
decl_stmt|;
return|return
name|var1
operator|<
name|var2
operator|*
name|var2
return|;
block|}
specifier|public
name|void
name|tick
parameter_list|()
block|{
name|walkDistO
operator|=
name|walkDist
expr_stmt|;
name|xo
operator|=
name|x
expr_stmt|;
name|yo
operator|=
name|y
expr_stmt|;
name|zo
operator|=
name|z
expr_stmt|;
name|xRotO
operator|=
name|xRot
expr_stmt|;
name|yRotO
operator|=
name|yRot
expr_stmt|;
block|}
specifier|public
name|void
name|turn
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
name|float
name|var3
init|=
name|xRot
decl_stmt|;
name|float
name|var4
init|=
name|yRot
decl_stmt|;
name|yRot
operator|=
operator|(
name|float
operator|)
operator|(
name|yRot
operator|+
name|var1
operator|*
literal|0.15D
operator|)
expr_stmt|;
name|xRot
operator|=
operator|(
name|float
operator|)
operator|(
name|xRot
operator|-
name|var2
operator|*
literal|0.15D
operator|)
expr_stmt|;
if|if
condition|(
name|xRot
operator|<
operator|-
literal|90.0F
condition|)
block|{
name|xRot
operator|=
operator|-
literal|90.0F
expr_stmt|;
block|}
if|if
condition|(
name|xRot
operator|>
literal|90.0F
condition|)
block|{
name|xRot
operator|=
literal|90.0F
expr_stmt|;
block|}
name|xRotO
operator|+=
name|xRot
operator|-
name|var3
expr_stmt|;
name|yRotO
operator|+=
name|yRot
operator|-
name|var4
expr_stmt|;
block|}
block|}
end_class

end_unit

