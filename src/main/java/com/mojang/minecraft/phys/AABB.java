begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|phys
package|;
end_package

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
name|minecraft
operator|.
name|model
operator|.
name|Vec3D
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|Serializable
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
literal|0.0F
decl_stmt|;
specifier|public
name|float
name|x0
decl_stmt|;
specifier|public
name|float
name|y0
decl_stmt|;
specifier|public
name|float
name|z0
decl_stmt|;
specifier|public
name|float
name|x1
decl_stmt|;
specifier|public
name|float
name|y1
decl_stmt|;
specifier|public
name|float
name|z1
decl_stmt|;
specifier|public
name|AABB
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
name|this
operator|.
name|x0
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|y0
operator|=
name|var2
expr_stmt|;
name|this
operator|.
name|z0
operator|=
name|var3
expr_stmt|;
name|this
operator|.
name|x1
operator|=
name|var4
expr_stmt|;
name|this
operator|.
name|y1
operator|=
name|var5
expr_stmt|;
name|this
operator|.
name|z1
operator|=
name|var6
expr_stmt|;
block|}
specifier|public
name|AABB
name|expand
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
name|float
name|var4
init|=
name|this
operator|.
name|x0
decl_stmt|;
name|float
name|var5
init|=
name|this
operator|.
name|y0
decl_stmt|;
name|float
name|var6
init|=
name|this
operator|.
name|z0
decl_stmt|;
name|float
name|var7
init|=
name|this
operator|.
name|x1
decl_stmt|;
name|float
name|var8
init|=
name|this
operator|.
name|y1
decl_stmt|;
name|float
name|var9
init|=
name|this
operator|.
name|z1
decl_stmt|;
if|if
condition|(
name|var1
operator|<
literal|0.0F
condition|)
block|{
name|var4
operator|+=
name|var1
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|>
literal|0.0F
condition|)
block|{
name|var7
operator|+=
name|var1
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|<
literal|0.0F
condition|)
block|{
name|var5
operator|+=
name|var2
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|>
literal|0.0F
condition|)
block|{
name|var8
operator|+=
name|var2
expr_stmt|;
block|}
if|if
condition|(
name|var3
operator|<
literal|0.0F
condition|)
block|{
name|var6
operator|+=
name|var3
expr_stmt|;
block|}
if|if
condition|(
name|var3
operator|>
literal|0.0F
condition|)
block|{
name|var9
operator|+=
name|var3
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
specifier|public
name|AABB
name|grow
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
name|float
name|var4
init|=
name|this
operator|.
name|x0
operator|-
name|var1
decl_stmt|;
name|float
name|var5
init|=
name|this
operator|.
name|y0
operator|-
name|var2
decl_stmt|;
name|float
name|var6
init|=
name|this
operator|.
name|z0
operator|-
name|var3
decl_stmt|;
name|var1
operator|+=
name|this
operator|.
name|x1
expr_stmt|;
name|var2
operator|+=
name|this
operator|.
name|y1
expr_stmt|;
name|float
name|var7
init|=
name|this
operator|.
name|z1
operator|+
name|var3
decl_stmt|;
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
name|var1
argument_list|,
name|var2
argument_list|,
name|var7
argument_list|)
return|;
block|}
specifier|public
name|AABB
name|cloneMove
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
return|return
operator|new
name|AABB
argument_list|(
name|this
operator|.
name|x0
operator|+
name|var3
argument_list|,
name|this
operator|.
name|y0
operator|+
name|var2
argument_list|,
name|this
operator|.
name|z0
operator|+
name|var3
argument_list|,
name|this
operator|.
name|x1
operator|+
name|var1
argument_list|,
name|this
operator|.
name|y1
operator|+
name|var2
argument_list|,
name|this
operator|.
name|z1
operator|+
name|var3
argument_list|)
return|;
block|}
specifier|public
name|float
name|clipXCollide
parameter_list|(
name|AABB
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
if|if
condition|(
name|var1
operator|.
name|y1
operator|>
name|this
operator|.
name|y0
operator|&&
name|var1
operator|.
name|y0
operator|<
name|this
operator|.
name|y1
condition|)
block|{
if|if
condition|(
name|var1
operator|.
name|z1
operator|>
name|this
operator|.
name|z0
operator|&&
name|var1
operator|.
name|z0
operator|<
name|this
operator|.
name|z1
condition|)
block|{
name|float
name|var3
decl_stmt|;
if|if
condition|(
name|var2
operator|>
literal|0.0F
operator|&&
name|var1
operator|.
name|x1
operator|<=
name|this
operator|.
name|x0
operator|&&
operator|(
name|var3
operator|=
name|this
operator|.
name|x0
operator|-
name|var1
operator|.
name|x1
operator|-
name|this
operator|.
name|epsilon
operator|)
operator|<
name|var2
condition|)
block|{
name|var2
operator|=
name|var3
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|<
literal|0.0F
operator|&&
name|var1
operator|.
name|x0
operator|>=
name|this
operator|.
name|x1
operator|&&
operator|(
name|var3
operator|=
name|this
operator|.
name|x1
operator|-
name|var1
operator|.
name|x0
operator|+
name|this
operator|.
name|epsilon
operator|)
operator|>
name|var2
condition|)
block|{
name|var2
operator|=
name|var3
expr_stmt|;
block|}
return|return
name|var2
return|;
block|}
else|else
block|{
return|return
name|var2
return|;
block|}
block|}
else|else
block|{
return|return
name|var2
return|;
block|}
block|}
specifier|public
name|float
name|clipYCollide
parameter_list|(
name|AABB
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
if|if
condition|(
name|var1
operator|.
name|x1
operator|>
name|this
operator|.
name|x0
operator|&&
name|var1
operator|.
name|x0
operator|<
name|this
operator|.
name|x1
condition|)
block|{
if|if
condition|(
name|var1
operator|.
name|z1
operator|>
name|this
operator|.
name|z0
operator|&&
name|var1
operator|.
name|z0
operator|<
name|this
operator|.
name|z1
condition|)
block|{
name|float
name|var3
decl_stmt|;
if|if
condition|(
name|var2
operator|>
literal|0.0F
operator|&&
name|var1
operator|.
name|y1
operator|<=
name|this
operator|.
name|y0
operator|&&
operator|(
name|var3
operator|=
name|this
operator|.
name|y0
operator|-
name|var1
operator|.
name|y1
operator|-
name|this
operator|.
name|epsilon
operator|)
operator|<
name|var2
condition|)
block|{
name|var2
operator|=
name|var3
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|<
literal|0.0F
operator|&&
name|var1
operator|.
name|y0
operator|>=
name|this
operator|.
name|y1
operator|&&
operator|(
name|var3
operator|=
name|this
operator|.
name|y1
operator|-
name|var1
operator|.
name|y0
operator|+
name|this
operator|.
name|epsilon
operator|)
operator|>
name|var2
condition|)
block|{
name|var2
operator|=
name|var3
expr_stmt|;
block|}
return|return
name|var2
return|;
block|}
else|else
block|{
return|return
name|var2
return|;
block|}
block|}
else|else
block|{
return|return
name|var2
return|;
block|}
block|}
specifier|public
name|float
name|clipZCollide
parameter_list|(
name|AABB
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
if|if
condition|(
name|var1
operator|.
name|x1
operator|>
name|this
operator|.
name|x0
operator|&&
name|var1
operator|.
name|x0
operator|<
name|this
operator|.
name|x1
condition|)
block|{
if|if
condition|(
name|var1
operator|.
name|y1
operator|>
name|this
operator|.
name|y0
operator|&&
name|var1
operator|.
name|y0
operator|<
name|this
operator|.
name|y1
condition|)
block|{
name|float
name|var3
decl_stmt|;
if|if
condition|(
name|var2
operator|>
literal|0.0F
operator|&&
name|var1
operator|.
name|z1
operator|<=
name|this
operator|.
name|z0
operator|&&
operator|(
name|var3
operator|=
name|this
operator|.
name|z0
operator|-
name|var1
operator|.
name|z1
operator|-
name|this
operator|.
name|epsilon
operator|)
operator|<
name|var2
condition|)
block|{
name|var2
operator|=
name|var3
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|<
literal|0.0F
operator|&&
name|var1
operator|.
name|z0
operator|>=
name|this
operator|.
name|z1
operator|&&
operator|(
name|var3
operator|=
name|this
operator|.
name|z1
operator|-
name|var1
operator|.
name|z0
operator|+
name|this
operator|.
name|epsilon
operator|)
operator|>
name|var2
condition|)
block|{
name|var2
operator|=
name|var3
expr_stmt|;
block|}
return|return
name|var2
return|;
block|}
else|else
block|{
return|return
name|var2
return|;
block|}
block|}
else|else
block|{
return|return
name|var2
return|;
block|}
block|}
specifier|public
name|boolean
name|intersects
parameter_list|(
name|AABB
name|var1
parameter_list|)
block|{
return|return
name|var1
operator|.
name|x1
operator|>
name|this
operator|.
name|x0
operator|&&
name|var1
operator|.
name|x0
operator|<
name|this
operator|.
name|x1
condition|?
operator|(
name|var1
operator|.
name|y1
operator|>
name|this
operator|.
name|y0
operator|&&
name|var1
operator|.
name|y0
argument_list|<
name|this
operator|.
name|y1
operator|?
name|var1
operator|.
name|z1
argument_list|>
name|this
operator|.
name|z0
operator|&&
name|var1
operator|.
name|z0
operator|<
name|this
operator|.
name|z1
else|:
literal|false
block_content|)
block|: false
function|;
block|}
end_class

begin_function
specifier|public
name|boolean
name|intersectsInner
parameter_list|(
name|AABB
name|var1
parameter_list|)
block|{
return|return
name|var1
operator|.
name|x1
operator|>=
name|this
operator|.
name|x0
operator|&&
name|var1
operator|.
name|x0
operator|<=
name|this
operator|.
name|x1
condition|?
operator|(
name|var1
operator|.
name|y1
operator|>=
name|this
operator|.
name|y0
operator|&&
name|var1
operator|.
name|y0
operator|<=
name|this
operator|.
name|y1
condition|?
name|var1
operator|.
name|z1
operator|>=
name|this
operator|.
name|z0
operator|&&
name|var1
operator|.
name|z0
operator|<=
name|this
operator|.
name|z1
else|:
literal|false
operator|)
else|:
literal|false
return|;
block|}
end_function

begin_function
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
name|this
operator|.
name|x0
operator|+=
name|var1
expr_stmt|;
name|this
operator|.
name|y0
operator|+=
name|var2
expr_stmt|;
name|this
operator|.
name|z0
operator|+=
name|var3
expr_stmt|;
name|this
operator|.
name|x1
operator|+=
name|var1
expr_stmt|;
name|this
operator|.
name|y1
operator|+=
name|var2
expr_stmt|;
name|this
operator|.
name|z1
operator|+=
name|var3
expr_stmt|;
block|}
end_function

begin_function
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
name|var4
operator|>
name|this
operator|.
name|x0
operator|&&
name|var1
operator|<
name|this
operator|.
name|x1
condition|?
operator|(
name|var5
operator|>
name|this
operator|.
name|y0
operator|&&
name|var2
argument_list|<
name|this
operator|.
name|y1
operator|?
name|var6
argument_list|>
name|this
operator|.
name|z0
operator|&&
name|var3
operator|<
name|this
operator|.
name|z1
else|:
literal|false
block_content|)
block|: false
function|;
end_function

begin_function
unit|}      public
name|boolean
name|contains
parameter_list|(
name|Vec3D
name|var1
parameter_list|)
block|{
return|return
name|var1
operator|.
name|x
operator|>
name|this
operator|.
name|x0
operator|&&
name|var1
operator|.
name|x
operator|<
name|this
operator|.
name|x1
condition|?
operator|(
name|var1
operator|.
name|y
operator|>
name|this
operator|.
name|y0
operator|&&
name|var1
operator|.
name|y
argument_list|<
name|this
operator|.
name|y1
operator|?
name|var1
operator|.
name|z
argument_list|>
name|this
operator|.
name|z0
operator|&&
name|var1
operator|.
name|z
operator|<
name|this
operator|.
name|z1
else|:
literal|false
block_content|)
block|: false
function|;
end_function

begin_function
unit|}      public
name|float
name|getSize
parameter_list|()
block|{
name|float
name|var1
init|=
name|this
operator|.
name|x1
operator|-
name|this
operator|.
name|x0
decl_stmt|;
name|float
name|var2
init|=
name|this
operator|.
name|y1
operator|-
name|this
operator|.
name|y0
decl_stmt|;
name|float
name|var3
init|=
name|this
operator|.
name|z1
operator|-
name|this
operator|.
name|z0
decl_stmt|;
return|return
operator|(
name|var1
operator|+
name|var2
operator|+
name|var3
operator|)
operator|/
literal|3.0F
return|;
block|}
end_function

begin_function
specifier|public
name|AABB
name|shrink
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
name|float
name|var4
init|=
name|this
operator|.
name|x0
decl_stmt|;
name|float
name|var5
init|=
name|this
operator|.
name|y0
decl_stmt|;
name|float
name|var6
init|=
name|this
operator|.
name|z0
decl_stmt|;
name|float
name|var7
init|=
name|this
operator|.
name|x1
decl_stmt|;
name|float
name|var8
init|=
name|this
operator|.
name|y1
decl_stmt|;
name|float
name|var9
init|=
name|this
operator|.
name|z1
decl_stmt|;
if|if
condition|(
name|var1
operator|<
literal|0.0F
condition|)
block|{
name|var4
operator|-=
name|var1
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|>
literal|0.0F
condition|)
block|{
name|var7
operator|-=
name|var1
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|<
literal|0.0F
condition|)
block|{
name|var5
operator|-=
name|var2
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|>
literal|0.0F
condition|)
block|{
name|var8
operator|-=
name|var2
expr_stmt|;
block|}
if|if
condition|(
name|var3
operator|<
literal|0.0F
condition|)
block|{
name|var6
operator|-=
name|var3
expr_stmt|;
block|}
if|if
condition|(
name|var3
operator|>
literal|0.0F
condition|)
block|{
name|var9
operator|-=
name|var3
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
end_function

begin_function
specifier|public
name|AABB
name|copy
parameter_list|()
block|{
return|return
operator|new
name|AABB
argument_list|(
name|this
operator|.
name|x0
argument_list|,
name|this
operator|.
name|y0
argument_list|,
name|this
operator|.
name|z0
argument_list|,
name|this
operator|.
name|x1
argument_list|,
name|this
operator|.
name|y1
argument_list|,
name|this
operator|.
name|z1
argument_list|)
return|;
block|}
end_function

begin_function
specifier|public
name|MovingObjectPosition
name|clip
parameter_list|(
name|Vec3D
name|var1
parameter_list|,
name|Vec3D
name|var2
parameter_list|)
block|{
name|Vec3D
name|var3
init|=
name|var1
operator|.
name|getXIntersection
argument_list|(
name|var2
argument_list|,
name|this
operator|.
name|x0
argument_list|)
decl_stmt|;
name|Vec3D
name|var4
init|=
name|var1
operator|.
name|getXIntersection
argument_list|(
name|var2
argument_list|,
name|this
operator|.
name|x1
argument_list|)
decl_stmt|;
name|Vec3D
name|var5
init|=
name|var1
operator|.
name|getYIntersection
argument_list|(
name|var2
argument_list|,
name|this
operator|.
name|y0
argument_list|)
decl_stmt|;
name|Vec3D
name|var6
init|=
name|var1
operator|.
name|getYIntersection
argument_list|(
name|var2
argument_list|,
name|this
operator|.
name|y1
argument_list|)
decl_stmt|;
name|Vec3D
name|var7
init|=
name|var1
operator|.
name|getZIntersection
argument_list|(
name|var2
argument_list|,
name|this
operator|.
name|z0
argument_list|)
decl_stmt|;
name|var2
operator|=
name|var1
operator|.
name|getZIntersection
argument_list|(
name|var2
argument_list|,
name|this
operator|.
name|z1
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|this
operator|.
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
name|this
operator|.
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
name|this
operator|.
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
name|this
operator|.
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
name|this
operator|.
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
name|this
operator|.
name|zIntersects
argument_list|(
name|var2
argument_list|)
condition|)
block|{
name|var2
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
name|var1
operator|.
name|distanceSquared
argument_list|(
name|var4
argument_list|)
operator|<
name|var1
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
name|var1
operator|.
name|distanceSquared
argument_list|(
name|var5
argument_list|)
operator|<
name|var1
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
name|var1
operator|.
name|distanceSquared
argument_list|(
name|var6
argument_list|)
operator|<
name|var1
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
name|var1
operator|.
name|distanceSquared
argument_list|(
name|var7
argument_list|)
operator|<
name|var1
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
name|var2
operator|!=
literal|null
operator|&&
operator|(
name|var8
operator|==
literal|null
operator|||
name|var1
operator|.
name|distanceSquared
argument_list|(
name|var2
argument_list|)
operator|<
name|var1
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
name|var2
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
name|var2
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
end_function

begin_function
specifier|private
name|boolean
name|xIntersects
parameter_list|(
name|Vec3D
name|var1
parameter_list|)
block|{
return|return
name|var1
operator|==
literal|null
condition|?
literal|false
else|:
name|var1
operator|.
name|y
operator|>=
name|this
operator|.
name|y0
operator|&&
name|var1
operator|.
name|y
operator|<=
name|this
operator|.
name|y1
operator|&&
name|var1
operator|.
name|z
operator|>=
name|this
operator|.
name|z0
operator|&&
name|var1
operator|.
name|z
operator|<=
name|this
operator|.
name|z1
return|;
block|}
end_function

begin_function
specifier|private
name|boolean
name|yIntersects
parameter_list|(
name|Vec3D
name|var1
parameter_list|)
block|{
return|return
name|var1
operator|==
literal|null
condition|?
literal|false
else|:
name|var1
operator|.
name|x
operator|>=
name|this
operator|.
name|x0
operator|&&
name|var1
operator|.
name|x
operator|<=
name|this
operator|.
name|x1
operator|&&
name|var1
operator|.
name|z
operator|>=
name|this
operator|.
name|z0
operator|&&
name|var1
operator|.
name|z
operator|<=
name|this
operator|.
name|z1
return|;
block|}
end_function

begin_function
specifier|private
name|boolean
name|zIntersects
parameter_list|(
name|Vec3D
name|var1
parameter_list|)
block|{
return|return
name|var1
operator|==
literal|null
condition|?
literal|false
else|:
name|var1
operator|.
name|x
operator|>=
name|this
operator|.
name|x0
operator|&&
name|var1
operator|.
name|x
operator|<=
name|this
operator|.
name|x1
operator|&&
name|var1
operator|.
name|y
operator|>=
name|this
operator|.
name|y0
operator|&&
name|var1
operator|.
name|y
operator|<=
name|this
operator|.
name|y1
return|;
block|}
end_function

unit|}
end_unit

