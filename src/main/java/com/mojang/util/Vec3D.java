begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|util
package|;
end_package

begin_class
specifier|public
specifier|final
class|class
name|Vec3D
block|{
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
name|Vec3D
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
block|}
specifier|public
specifier|final
name|Vec3D
name|add
parameter_list|(
name|float
name|otherX
parameter_list|,
name|float
name|otherY
parameter_list|,
name|float
name|otherZ
parameter_list|)
block|{
return|return
operator|new
name|Vec3D
argument_list|(
name|x
operator|+
name|otherX
argument_list|,
name|y
operator|+
name|otherY
argument_list|,
name|z
operator|+
name|otherZ
argument_list|)
return|;
block|}
specifier|public
specifier|final
name|float
name|distance
parameter_list|(
name|Vec3D
name|other
parameter_list|)
block|{
name|float
name|xDiff
init|=
name|other
operator|.
name|x
operator|-
name|x
decl_stmt|;
name|float
name|yDiff
init|=
name|other
operator|.
name|y
operator|-
name|y
decl_stmt|;
name|float
name|zDiff
init|=
name|other
operator|.
name|z
operator|-
name|z
decl_stmt|;
return|return
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|xDiff
operator|*
name|xDiff
operator|+
name|yDiff
operator|*
name|yDiff
operator|+
name|zDiff
operator|*
name|zDiff
argument_list|)
return|;
block|}
specifier|public
specifier|final
name|float
name|distanceSquared
parameter_list|(
name|Vec3D
name|other
parameter_list|)
block|{
name|float
name|xDiff
init|=
name|other
operator|.
name|x
operator|-
name|x
decl_stmt|;
name|float
name|yDiff
init|=
name|other
operator|.
name|y
operator|-
name|y
decl_stmt|;
name|float
name|zDiff
init|=
name|other
operator|.
name|z
operator|-
name|z
decl_stmt|;
return|return
name|xDiff
operator|*
name|xDiff
operator|+
name|yDiff
operator|*
name|yDiff
operator|+
name|zDiff
operator|*
name|zDiff
return|;
block|}
specifier|public
specifier|final
name|Vec3D
name|getXIntersection
parameter_list|(
name|Vec3D
name|other
parameter_list|,
name|float
name|xAxis
parameter_list|)
block|{
name|float
name|xDiff
init|=
name|other
operator|.
name|x
operator|-
name|x
decl_stmt|;
name|float
name|yDiff
init|=
name|other
operator|.
name|y
operator|-
name|y
decl_stmt|;
name|float
name|zDiff
init|=
name|other
operator|.
name|z
operator|-
name|z
decl_stmt|;
return|return
name|xDiff
operator|*
name|xDiff
operator|<
literal|1.0E
operator|-
literal|7F
condition|?
literal|null
else|:
operator|(
name|xAxis
operator|=
operator|(
name|xAxis
operator|-
name|x
operator|)
operator|/
name|xDiff
operator|)
operator|>=
literal|0F
operator|&&
name|xAxis
operator|<=
literal|1F
condition|?
operator|new
name|Vec3D
argument_list|(
name|x
operator|+
name|xDiff
operator|*
name|xAxis
argument_list|,
name|y
operator|+
name|yDiff
operator|*
name|xAxis
argument_list|,
name|z
operator|+
name|zDiff
operator|*
name|xAxis
argument_list|)
else|:
literal|null
return|;
block|}
specifier|public
specifier|final
name|Vec3D
name|getYIntersection
parameter_list|(
name|Vec3D
name|other
parameter_list|,
name|float
name|yAxis
parameter_list|)
block|{
name|float
name|xDiff
init|=
name|other
operator|.
name|x
operator|-
name|x
decl_stmt|;
name|float
name|yDiff
init|=
name|other
operator|.
name|y
operator|-
name|y
decl_stmt|;
name|float
name|zDiff
init|=
name|other
operator|.
name|z
operator|-
name|z
decl_stmt|;
return|return
name|yDiff
operator|*
name|yDiff
operator|<
literal|1.0E
operator|-
literal|7F
condition|?
literal|null
else|:
operator|(
name|yAxis
operator|=
operator|(
name|yAxis
operator|-
name|y
operator|)
operator|/
name|yDiff
operator|)
operator|>=
literal|0F
operator|&&
name|yAxis
operator|<=
literal|1F
condition|?
operator|new
name|Vec3D
argument_list|(
name|x
operator|+
name|xDiff
operator|*
name|yAxis
argument_list|,
name|y
operator|+
name|yDiff
operator|*
name|yAxis
argument_list|,
name|z
operator|+
name|zDiff
operator|*
name|yAxis
argument_list|)
else|:
literal|null
return|;
block|}
specifier|public
specifier|final
name|Vec3D
name|getZIntersection
parameter_list|(
name|Vec3D
name|other
parameter_list|,
name|float
name|zAxis
parameter_list|)
block|{
name|float
name|xDiff
init|=
name|other
operator|.
name|x
operator|-
name|x
decl_stmt|;
name|float
name|yDiff
init|=
name|other
operator|.
name|y
operator|-
name|y
decl_stmt|;
name|float
name|zDiff
init|=
name|other
operator|.
name|z
operator|-
name|z
decl_stmt|;
return|return
name|zDiff
operator|*
name|zDiff
operator|<
literal|1.0E
operator|-
literal|7F
condition|?
literal|null
else|:
operator|(
name|zAxis
operator|=
operator|(
name|zAxis
operator|-
name|z
operator|)
operator|/
name|zDiff
operator|)
operator|>=
literal|0F
operator|&&
name|zAxis
operator|<=
literal|1F
condition|?
operator|new
name|Vec3D
argument_list|(
name|x
operator|+
name|xDiff
operator|*
name|zAxis
argument_list|,
name|y
operator|+
name|yDiff
operator|*
name|zAxis
argument_list|,
name|z
operator|+
name|zDiff
operator|*
name|zAxis
argument_list|)
else|:
literal|null
return|;
block|}
specifier|public
specifier|final
name|Vec3D
name|normalize
parameter_list|()
block|{
name|float
name|dist
init|=
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
operator|+
name|z
operator|*
name|z
argument_list|)
decl_stmt|;
return|return
operator|new
name|Vec3D
argument_list|(
name|x
operator|/
name|dist
argument_list|,
name|y
operator|/
name|dist
argument_list|,
name|z
operator|/
name|dist
argument_list|)
return|;
block|}
specifier|public
specifier|final
name|Vec3D
name|subtract
parameter_list|(
name|Vec3D
name|other
parameter_list|)
block|{
return|return
operator|new
name|Vec3D
argument_list|(
name|x
operator|-
name|other
operator|.
name|x
argument_list|,
name|y
operator|-
name|other
operator|.
name|y
argument_list|,
name|z
operator|-
name|other
operator|.
name|z
argument_list|)
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|String
name|toString
parameter_list|()
block|{
return|return
literal|"("
operator|+
name|x
operator|+
literal|", "
operator|+
name|y
operator|+
literal|", "
operator|+
name|z
operator|+
literal|")"
return|;
block|}
block|}
end_class

end_unit

