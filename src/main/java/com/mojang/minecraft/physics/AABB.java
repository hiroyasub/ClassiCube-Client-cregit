begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|physics
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
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|MovingObjectPosition
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
name|IntersectionHelper
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

begin_class
specifier|public
class|class
name|AABB
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
specifier|private
name|float
name|epsilon
init|=
literal|0F
decl_stmt|;
specifier|public
name|float
name|maxX
decl_stmt|;
specifier|public
name|float
name|maxY
decl_stmt|;
specifier|public
name|float
name|maxZ
decl_stmt|;
specifier|public
name|float
name|minX
decl_stmt|;
specifier|public
name|float
name|minY
decl_stmt|;
specifier|public
name|float
name|minZ
decl_stmt|;
specifier|public
name|AABB
parameter_list|(
name|float
name|maxX
parameter_list|,
name|float
name|maxY
parameter_list|,
name|float
name|maxZ
parameter_list|,
name|float
name|minX
parameter_list|,
name|float
name|minY
parameter_list|,
name|float
name|minZ
parameter_list|)
block|{
name|this
operator|.
name|maxX
operator|=
name|maxX
expr_stmt|;
name|this
operator|.
name|maxY
operator|=
name|maxY
expr_stmt|;
name|this
operator|.
name|maxZ
operator|=
name|maxZ
expr_stmt|;
name|this
operator|.
name|minX
operator|=
name|minX
expr_stmt|;
name|this
operator|.
name|minY
operator|=
name|minY
expr_stmt|;
name|this
operator|.
name|minZ
operator|=
name|minZ
expr_stmt|;
block|}
specifier|public
name|MovingObjectPosition
name|clip
parameter_list|(
name|Vec3D
name|vec
parameter_list|,
name|Vec3D
name|other
parameter_list|)
block|{
name|Vec3D
name|var3
init|=
name|vec
operator|.
name|getXIntersection
argument_list|(
name|other
argument_list|,
name|maxX
argument_list|)
decl_stmt|;
name|Vec3D
name|var4
init|=
name|vec
operator|.
name|getXIntersection
argument_list|(
name|other
argument_list|,
name|minX
argument_list|)
decl_stmt|;
name|Vec3D
name|var5
init|=
name|vec
operator|.
name|getYIntersection
argument_list|(
name|other
argument_list|,
name|maxY
argument_list|)
decl_stmt|;
name|Vec3D
name|var6
init|=
name|vec
operator|.
name|getYIntersection
argument_list|(
name|other
argument_list|,
name|minY
argument_list|)
decl_stmt|;
name|Vec3D
name|var7
init|=
name|vec
operator|.
name|getZIntersection
argument_list|(
name|other
argument_list|,
name|maxZ
argument_list|)
decl_stmt|;
name|other
operator|=
name|vec
operator|.
name|getZIntersection
argument_list|(
name|other
argument_list|,
name|minZ
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|xIntersects
argument_list|(
name|var3
argument_list|)
condition|)
block|{
name|var3
operator|=
literal|null
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|xIntersects
argument_list|(
name|var4
argument_list|)
condition|)
block|{
name|var4
operator|=
literal|null
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|yIntersects
argument_list|(
name|var5
argument_list|)
condition|)
block|{
name|var5
operator|=
literal|null
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|yIntersects
argument_list|(
name|var6
argument_list|)
condition|)
block|{
name|var6
operator|=
literal|null
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|zIntersects
argument_list|(
name|var7
argument_list|)
condition|)
block|{
name|var7
operator|=
literal|null
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|zIntersects
argument_list|(
name|other
argument_list|)
condition|)
block|{
name|other
operator|=
literal|null
expr_stmt|;
block|}
name|Vec3D
name|var8
init|=
literal|null
decl_stmt|;
if|if
condition|(
name|var3
operator|!=
literal|null
condition|)
block|{
name|var8
operator|=
name|var3
expr_stmt|;
block|}
if|if
condition|(
name|var4
operator|!=
literal|null
operator|&&
operator|(
name|var8
operator|==
literal|null
operator|||
name|vec
operator|.
name|distanceSquared
argument_list|(
name|var4
argument_list|)
operator|<
name|vec
operator|.
name|distanceSquared
argument_list|(
name|var8
argument_list|)
operator|)
condition|)
block|{
name|var8
operator|=
name|var4
expr_stmt|;
block|}
if|if
condition|(
name|var5
operator|!=
literal|null
operator|&&
operator|(
name|var8
operator|==
literal|null
operator|||
name|vec
operator|.
name|distanceSquared
argument_list|(
name|var5
argument_list|)
operator|<
name|vec
operator|.
name|distanceSquared
argument_list|(
name|var8
argument_list|)
operator|)
condition|)
block|{
name|var8
operator|=
name|var5
expr_stmt|;
block|}
if|if
condition|(
name|var6
operator|!=
literal|null
operator|&&
operator|(
name|var8
operator|==
literal|null
operator|||
name|vec
operator|.
name|distanceSquared
argument_list|(
name|var6
argument_list|)
operator|<
name|vec
operator|.
name|distanceSquared
argument_list|(
name|var8
argument_list|)
operator|)
condition|)
block|{
name|var8
operator|=
name|var6
expr_stmt|;
block|}
if|if
condition|(
name|var7
operator|!=
literal|null
operator|&&
operator|(
name|var8
operator|==
literal|null
operator|||
name|vec
operator|.
name|distanceSquared
argument_list|(
name|var7
argument_list|)
operator|<
name|vec
operator|.
name|distanceSquared
argument_list|(
name|var8
argument_list|)
operator|)
condition|)
block|{
name|var8
operator|=
name|var7
expr_stmt|;
block|}
if|if
condition|(
name|other
operator|!=
literal|null
operator|&&
operator|(
name|var8
operator|==
literal|null
operator|||
name|vec
operator|.
name|distanceSquared
argument_list|(
name|other
argument_list|)
operator|<
name|vec
operator|.
name|distanceSquared
argument_list|(
name|var8
argument_list|)
operator|)
condition|)
block|{
name|var8
operator|=
name|other
expr_stmt|;
block|}
if|if
condition|(
name|var8
operator|==
literal|null
condition|)
block|{
return|return
literal|null
return|;
block|}
else|else
block|{
name|byte
name|var9
init|=
operator|-
literal|1
decl_stmt|;
if|if
condition|(
name|var8
operator|==
name|var3
condition|)
block|{
name|var9
operator|=
literal|4
expr_stmt|;
block|}
if|if
condition|(
name|var8
operator|==
name|var4
condition|)
block|{
name|var9
operator|=
literal|5
expr_stmt|;
block|}
if|if
condition|(
name|var8
operator|==
name|var5
condition|)
block|{
name|var9
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|var8
operator|==
name|var6
condition|)
block|{
name|var9
operator|=
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|var8
operator|==
name|var7
condition|)
block|{
name|var9
operator|=
literal|2
expr_stmt|;
block|}
if|if
condition|(
name|var8
operator|==
name|other
condition|)
block|{
name|var9
operator|=
literal|3
expr_stmt|;
block|}
return|return
operator|new
name|MovingObjectPosition
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
name|var9
argument_list|,
name|var8
argument_list|)
return|;
block|}
block|}
specifier|public
name|float
name|clipXCollide
parameter_list|(
name|AABB
name|aabb
parameter_list|,
name|float
name|x
parameter_list|)
block|{
if|if
condition|(
operator|(
name|aabb
operator|.
name|minY
operator|>
name|maxY
operator|&&
name|aabb
operator|.
name|maxY
operator|<
name|minY
operator|)
operator|&&
operator|(
name|aabb
operator|.
name|minZ
operator|>
name|maxZ
operator|&&
name|aabb
operator|.
name|maxZ
operator|<
name|minZ
operator|)
condition|)
block|{
name|float
name|var3
decl_stmt|;
if|if
condition|(
name|x
operator|>
literal|0F
operator|&&
name|aabb
operator|.
name|minX
operator|<=
name|maxX
operator|&&
operator|(
name|var3
operator|=
name|maxX
operator|-
name|aabb
operator|.
name|minX
operator|-
name|epsilon
operator|)
operator|<
name|x
condition|)
block|{
name|x
operator|=
name|var3
expr_stmt|;
block|}
if|if
condition|(
name|x
operator|<
literal|0F
operator|&&
name|aabb
operator|.
name|maxX
operator|>=
name|minX
operator|&&
operator|(
name|var3
operator|=
name|minX
operator|-
name|aabb
operator|.
name|maxX
operator|+
name|epsilon
operator|)
operator|>
name|x
condition|)
block|{
name|x
operator|=
name|var3
expr_stmt|;
block|}
block|}
return|return
name|x
return|;
block|}
specifier|public
name|float
name|clipYCollide
parameter_list|(
name|AABB
name|aabb
parameter_list|,
name|float
name|y
parameter_list|)
block|{
if|if
condition|(
operator|(
name|aabb
operator|.
name|minX
operator|>
name|maxX
operator|&&
name|aabb
operator|.
name|maxX
operator|<
name|minX
operator|)
operator|&&
operator|(
name|aabb
operator|.
name|minZ
operator|>
name|maxZ
operator|&&
name|aabb
operator|.
name|maxZ
operator|<
name|minZ
operator|)
condition|)
block|{
name|float
name|var3
decl_stmt|;
if|if
condition|(
name|y
operator|>
literal|0F
operator|&&
name|aabb
operator|.
name|minY
operator|<=
name|maxY
operator|&&
operator|(
name|var3
operator|=
name|maxY
operator|-
name|aabb
operator|.
name|minY
operator|-
name|epsilon
operator|)
operator|<
name|y
condition|)
block|{
name|y
operator|=
name|var3
expr_stmt|;
block|}
if|if
condition|(
name|y
operator|<
literal|0F
operator|&&
name|aabb
operator|.
name|maxY
operator|>=
name|minY
operator|&&
operator|(
name|var3
operator|=
name|minY
operator|-
name|aabb
operator|.
name|maxY
operator|+
name|epsilon
operator|)
operator|>
name|y
condition|)
block|{
name|y
operator|=
name|var3
expr_stmt|;
block|}
block|}
return|return
name|y
return|;
block|}
specifier|public
name|float
name|clipZCollide
parameter_list|(
name|AABB
name|aabb
parameter_list|,
name|float
name|z
parameter_list|)
block|{
if|if
condition|(
operator|(
name|aabb
operator|.
name|minX
operator|>
name|maxX
operator|&&
name|aabb
operator|.
name|maxX
operator|<
name|minX
operator|)
operator|&&
operator|(
name|aabb
operator|.
name|minY
operator|>
name|maxY
operator|&&
name|aabb
operator|.
name|maxY
operator|<
name|minY
operator|)
condition|)
block|{
name|float
name|var3
decl_stmt|;
if|if
condition|(
name|z
operator|>
literal|0F
operator|&&
name|aabb
operator|.
name|minZ
operator|<=
name|maxZ
operator|&&
operator|(
name|var3
operator|=
name|maxZ
operator|-
name|aabb
operator|.
name|minZ
operator|-
name|epsilon
operator|)
operator|<
name|z
condition|)
block|{
name|z
operator|=
name|var3
expr_stmt|;
block|}
if|if
condition|(
name|z
operator|<
literal|0F
operator|&&
name|aabb
operator|.
name|maxZ
operator|>=
name|minZ
operator|&&
operator|(
name|var3
operator|=
name|minZ
operator|-
name|aabb
operator|.
name|maxZ
operator|+
name|epsilon
operator|)
operator|>
name|z
condition|)
block|{
name|z
operator|=
name|var3
expr_stmt|;
block|}
block|}
return|return
name|z
return|;
block|}
specifier|public
name|AABB
name|cloneMove
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
return|return
operator|new
name|AABB
argument_list|(
name|maxX
operator|+
name|z
argument_list|,
name|maxY
operator|+
name|y
argument_list|,
name|maxZ
operator|+
name|z
argument_list|,
name|minX
operator|+
name|x
argument_list|,
name|minY
operator|+
name|y
argument_list|,
name|minZ
operator|+
name|z
argument_list|)
return|;
block|}
comment|/**      * Checks if the AABB contains the vector.      * @param vector      * @return      */
specifier|public
name|boolean
name|contains
parameter_list|(
name|Vec3D
name|vector
parameter_list|)
block|{
return|return
operator|(
operator|(
name|vector
operator|.
name|x
operator|>
name|maxX
operator|&&
name|vector
operator|.
name|x
operator|<
name|minX
operator|)
operator|&&
operator|(
name|vector
operator|.
name|y
operator|>
name|maxY
operator|&&
name|vector
operator|.
name|y
operator|<
name|minY
operator|)
operator|&&
operator|(
name|vector
operator|.
name|z
operator|>
name|maxZ
operator|&&
name|vector
operator|.
name|z
operator|<
name|minZ
operator|)
operator|)
return|;
block|}
specifier|public
name|AABB
name|copy
parameter_list|()
block|{
return|return
operator|new
name|AABB
argument_list|(
name|maxX
argument_list|,
name|maxY
argument_list|,
name|maxZ
argument_list|,
name|minX
argument_list|,
name|minY
argument_list|,
name|minZ
argument_list|)
return|;
block|}
comment|/**      * Expands the AABB by the dimensions specified.      * @param x      * @param y      * @param z      * @return      */
specifier|public
name|AABB
name|expand
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
init|=
name|maxX
decl_stmt|;
name|float
name|var5
init|=
name|maxY
decl_stmt|;
name|float
name|var6
init|=
name|maxZ
decl_stmt|;
name|float
name|var7
init|=
name|minX
decl_stmt|;
name|float
name|var8
init|=
name|minY
decl_stmt|;
name|float
name|var9
init|=
name|minZ
decl_stmt|;
if|if
condition|(
name|x
operator|<
literal|0F
condition|)
block|{
name|var4
operator|+=
name|x
expr_stmt|;
block|}
if|if
condition|(
name|x
operator|>
literal|0F
condition|)
block|{
name|var7
operator|+=
name|x
expr_stmt|;
block|}
if|if
condition|(
name|y
operator|<
literal|0F
condition|)
block|{
name|var5
operator|+=
name|y
expr_stmt|;
block|}
if|if
condition|(
name|y
operator|>
literal|0F
condition|)
block|{
name|var8
operator|+=
name|y
expr_stmt|;
block|}
if|if
condition|(
name|z
operator|<
literal|0F
condition|)
block|{
name|var6
operator|+=
name|z
expr_stmt|;
block|}
if|if
condition|(
name|z
operator|>
literal|0F
condition|)
block|{
name|var9
operator|+=
name|z
expr_stmt|;
block|}
return|return
operator|new
name|AABB
argument_list|(
name|var4
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|,
name|var8
argument_list|,
name|var9
argument_list|)
return|;
block|}
comment|/**      * Gets the size of the AABB.      * @return The size of the AABB.      */
specifier|public
name|float
name|getSize
parameter_list|()
block|{
return|return
operator|(
name|minX
operator|-
name|maxX
operator|+
name|minY
operator|-
name|maxY
operator|+
name|minZ
operator|-
name|maxZ
operator|)
operator|/
literal|3F
return|;
block|}
comment|/**      * Grows the AABB by the dimensions specified.      * @param x      * @param y      * @param z      * @return      */
specifier|public
name|AABB
name|grow
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
name|newX0
init|=
name|maxX
operator|-
name|x
decl_stmt|;
name|float
name|newY0
init|=
name|maxY
operator|-
name|y
decl_stmt|;
name|float
name|newZ0
init|=
name|maxZ
operator|-
name|z
decl_stmt|;
name|x
operator|+=
name|minX
expr_stmt|;
name|y
operator|+=
name|minY
expr_stmt|;
name|float
name|newZ1
init|=
name|minZ
operator|+
name|z
decl_stmt|;
return|return
operator|new
name|AABB
argument_list|(
name|newX0
argument_list|,
name|newY0
argument_list|,
name|newZ0
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|newZ1
argument_list|)
return|;
block|}
comment|/**      * Returns whether the given bounding box intersects with this one.      * @param aabb      * @return      */
specifier|public
name|boolean
name|intersects
parameter_list|(
name|AABB
name|aabb
parameter_list|)
block|{
return|return
operator|(
operator|(
name|aabb
operator|.
name|minX
operator|>
name|maxX
operator|&&
name|aabb
operator|.
name|maxX
operator|<
name|minX
operator|)
operator|&&
operator|(
name|aabb
operator|.
name|minY
operator|>
name|maxY
operator|&&
name|aabb
operator|.
name|maxY
operator|<
name|minY
operator|)
operator|&&
operator|(
name|aabb
operator|.
name|minZ
operator|>
name|maxZ
operator|&&
name|aabb
operator|.
name|maxZ
operator|<
name|minZ
operator|)
operator|)
return|;
block|}
comment|/**      * Returns whether the given bounding box intersects with this one.      * @param x0      * @param y0      * @param z0      * @param x1      * @param y1      * @param z1      * @return      */
specifier|public
name|boolean
name|intersects
parameter_list|(
name|float
name|x0
parameter_list|,
name|float
name|y0
parameter_list|,
name|float
name|z0
parameter_list|,
name|float
name|x1
parameter_list|,
name|float
name|y1
parameter_list|,
name|float
name|z1
parameter_list|)
block|{
return|return
operator|(
operator|(
name|x1
operator|>
name|this
operator|.
name|maxX
operator|&&
name|x0
operator|<
name|this
operator|.
name|minX
operator|)
operator|&&
operator|(
name|y1
operator|>
name|this
operator|.
name|maxY
operator|&&
name|y0
operator|<
name|this
operator|.
name|minY
operator|)
operator|&&
operator|(
name|z1
operator|>
name|this
operator|.
name|maxZ
operator|&&
name|z0
operator|<
name|this
operator|.
name|minZ
operator|)
operator|)
return|;
block|}
comment|/**      * Returns if the supplied AABB is completely inside the bounding box      * @param aabb      * @return      */
specifier|public
name|boolean
name|intersectsInner
parameter_list|(
name|AABB
name|aabb
parameter_list|)
block|{
return|return
operator|(
operator|(
name|aabb
operator|.
name|minX
operator|>=
name|maxX
operator|&&
name|aabb
operator|.
name|maxX
operator|<=
name|minX
operator|)
operator|&&
operator|(
name|aabb
operator|.
name|minY
operator|>=
name|maxY
operator|&&
name|aabb
operator|.
name|maxY
operator|<=
name|minY
operator|)
operator|&&
operator|(
name|aabb
operator|.
name|minZ
operator|>=
name|maxZ
operator|&&
name|aabb
operator|.
name|maxZ
operator|<=
name|minZ
operator|)
operator|)
return|;
block|}
comment|/**      * Shifts the AABB by the dimensions specified.      * @param x      * @param y      * @param z      */
specifier|public
name|void
name|move
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
name|maxX
operator|+=
name|x
expr_stmt|;
name|maxY
operator|+=
name|y
expr_stmt|;
name|maxZ
operator|+=
name|z
expr_stmt|;
name|minX
operator|+=
name|x
expr_stmt|;
name|minY
operator|+=
name|y
expr_stmt|;
name|minZ
operator|+=
name|z
expr_stmt|;
block|}
comment|/**      * Shrinks the AABB by the dimensions specified.      * @param x      * @param y      * @param z      * @return A new AABB instance with the new dimensions.      */
specifier|public
name|AABB
name|shrink
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
init|=
name|maxX
decl_stmt|;
name|float
name|var5
init|=
name|maxY
decl_stmt|;
name|float
name|var6
init|=
name|maxZ
decl_stmt|;
name|float
name|var7
init|=
name|minX
decl_stmt|;
name|float
name|var8
init|=
name|minY
decl_stmt|;
name|float
name|var9
init|=
name|minZ
decl_stmt|;
if|if
condition|(
name|x
operator|<
literal|0F
condition|)
block|{
name|var4
operator|-=
name|x
expr_stmt|;
block|}
if|if
condition|(
name|x
operator|>
literal|0F
condition|)
block|{
name|var7
operator|-=
name|x
expr_stmt|;
block|}
if|if
condition|(
name|y
operator|<
literal|0F
condition|)
block|{
name|var5
operator|-=
name|y
expr_stmt|;
block|}
if|if
condition|(
name|y
operator|>
literal|0F
condition|)
block|{
name|var8
operator|-=
name|y
expr_stmt|;
block|}
if|if
condition|(
name|z
operator|<
literal|0F
condition|)
block|{
name|var6
operator|-=
name|z
expr_stmt|;
block|}
if|if
condition|(
name|z
operator|>
literal|0F
condition|)
block|{
name|var9
operator|-=
name|z
expr_stmt|;
block|}
return|return
operator|new
name|AABB
argument_list|(
name|var4
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|,
name|var8
argument_list|,
name|var9
argument_list|)
return|;
block|}
specifier|private
name|boolean
name|xIntersects
parameter_list|(
name|Vec3D
name|vec
parameter_list|)
block|{
return|return
name|IntersectionHelper
operator|.
name|xIntersects
argument_list|(
name|vec
argument_list|,
name|maxY
argument_list|,
name|maxZ
argument_list|,
name|minY
argument_list|,
name|minZ
argument_list|)
return|;
block|}
specifier|private
name|boolean
name|yIntersects
parameter_list|(
name|Vec3D
name|vec
parameter_list|)
block|{
return|return
name|IntersectionHelper
operator|.
name|yIntersects
argument_list|(
name|vec
argument_list|,
name|maxX
argument_list|,
name|maxZ
argument_list|,
name|minX
argument_list|,
name|minZ
argument_list|)
return|;
block|}
specifier|private
name|boolean
name|zIntersects
parameter_list|(
name|Vec3D
name|vec
parameter_list|)
block|{
return|return
name|IntersectionHelper
operator|.
name|zIntersects
argument_list|(
name|vec
argument_list|,
name|maxX
argument_list|,
name|maxY
argument_list|,
name|minX
argument_list|,
name|minY
argument_list|)
return|;
block|}
block|}
end_class

end_unit
