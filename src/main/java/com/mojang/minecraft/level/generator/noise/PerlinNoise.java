begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|level
operator|.
name|generator
operator|.
name|noise
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Random
import|;
end_import

begin_class
specifier|public
class|class
name|PerlinNoise
extends|extends
name|Noise
block|{
specifier|public
name|PerlinNoise
parameter_list|()
block|{
name|this
argument_list|(
operator|new
name|Random
argument_list|()
argument_list|)
expr_stmt|;
block|}
specifier|public
name|PerlinNoise
parameter_list|(
name|Random
name|random
parameter_list|)
block|{
name|noise
operator|=
operator|new
name|int
index|[
literal|512
index|]
expr_stmt|;
for|for
control|(
name|int
name|count
init|=
literal|0
init|;
name|count
operator|<
literal|256
condition|;
name|noise
index|[
name|count
index|]
operator|=
name|count
operator|++
control|)
block|{
block|}
for|for
control|(
name|int
name|count
init|=
literal|0
init|;
name|count
operator|<
literal|256
condition|;
name|count
operator|++
control|)
block|{
name|int
name|unknown0
init|=
name|random
operator|.
name|nextInt
argument_list|(
literal|256
operator|-
name|count
argument_list|)
operator|+
name|count
decl_stmt|;
name|int
name|unknown1
init|=
name|noise
index|[
name|count
index|]
decl_stmt|;
name|noise
index|[
name|count
index|]
operator|=
name|noise
index|[
name|unknown0
index|]
expr_stmt|;
name|noise
index|[
name|unknown0
index|]
operator|=
name|unknown1
expr_stmt|;
name|noise
index|[
name|count
operator|+
literal|256
index|]
operator|=
name|noise
index|[
name|count
index|]
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|double
name|compute
parameter_list|(
name|double
name|x
parameter_list|,
name|double
name|z
parameter_list|)
block|{
name|double
name|unknown0
init|=
literal|0.0D
decl_stmt|;
name|double
name|unknown1
init|=
name|z
decl_stmt|;
name|double
name|unknown2
init|=
name|x
decl_stmt|;
name|int
name|unknown3
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
operator|&
literal|255
decl_stmt|;
name|int
name|unknown4
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
operator|&
literal|255
decl_stmt|;
name|int
name|unknown5
init|=
operator|(
name|int
operator|)
name|Math
operator|.
name|floor
argument_list|(
literal|0.0D
argument_list|)
operator|&
literal|255
decl_stmt|;
name|unknown2
operator|-=
name|Math
operator|.
name|floor
argument_list|(
name|unknown2
argument_list|)
expr_stmt|;
name|unknown1
operator|-=
name|Math
operator|.
name|floor
argument_list|(
name|unknown1
argument_list|)
expr_stmt|;
name|unknown0
operator|=
literal|0.0D
operator|-
name|Math
operator|.
name|floor
argument_list|(
literal|0.0D
argument_list|)
expr_stmt|;
name|double
name|unknown6
init|=
name|a
argument_list|(
name|unknown2
argument_list|)
decl_stmt|;
name|double
name|unknown7
init|=
name|a
argument_list|(
name|unknown1
argument_list|)
decl_stmt|;
name|double
name|unknown8
init|=
name|a
argument_list|(
name|unknown0
argument_list|)
decl_stmt|;
name|int
name|unknown9
init|=
name|noise
index|[
name|unknown3
index|]
operator|+
name|unknown4
decl_stmt|;
name|int
name|unknown10
init|=
name|noise
index|[
name|unknown9
index|]
operator|+
name|unknown5
decl_stmt|;
name|unknown9
operator|=
name|noise
index|[
name|unknown9
operator|+
literal|1
index|]
operator|+
name|unknown5
expr_stmt|;
name|unknown3
operator|=
name|noise
index|[
name|unknown3
operator|+
literal|1
index|]
operator|+
name|unknown4
expr_stmt|;
name|unknown4
operator|=
name|noise
index|[
name|unknown3
index|]
operator|+
name|unknown5
expr_stmt|;
name|unknown3
operator|=
name|noise
index|[
name|unknown3
operator|+
literal|1
index|]
operator|+
name|unknown5
expr_stmt|;
comment|// TODO: Maybe organize better.
return|return
name|lerp
argument_list|(
name|unknown8
argument_list|,
name|lerp
argument_list|(
name|unknown7
argument_list|,
name|lerp
argument_list|(
name|unknown6
argument_list|,
name|grad
argument_list|(
name|noise
index|[
name|unknown10
index|]
argument_list|,
name|unknown2
argument_list|,
name|unknown1
argument_list|,
name|unknown0
argument_list|)
argument_list|,
name|grad
argument_list|(
name|noise
index|[
name|unknown4
index|]
argument_list|,
name|unknown2
operator|-
literal|1.0D
argument_list|,
name|unknown1
argument_list|,
name|unknown0
argument_list|)
argument_list|)
argument_list|,
name|lerp
argument_list|(
name|unknown6
argument_list|,
name|grad
argument_list|(
name|noise
index|[
name|unknown9
index|]
argument_list|,
name|unknown2
argument_list|,
name|unknown1
operator|-
literal|1.0D
argument_list|,
name|unknown0
argument_list|)
argument_list|,
name|grad
argument_list|(
name|noise
index|[
name|unknown3
index|]
argument_list|,
name|unknown2
operator|-
literal|1.0D
argument_list|,
name|unknown1
operator|-
literal|1.0D
argument_list|,
name|unknown0
argument_list|)
argument_list|)
argument_list|)
argument_list|,
name|lerp
argument_list|(
name|unknown7
argument_list|,
name|lerp
argument_list|(
name|unknown6
argument_list|,
name|grad
argument_list|(
name|noise
index|[
name|unknown10
operator|+
literal|1
index|]
argument_list|,
name|unknown2
argument_list|,
name|unknown1
argument_list|,
name|unknown0
operator|-
literal|1.0D
argument_list|)
argument_list|,
name|grad
argument_list|(
name|noise
index|[
name|unknown4
operator|+
literal|1
index|]
argument_list|,
name|unknown2
operator|-
literal|1.0D
argument_list|,
name|unknown1
argument_list|,
name|unknown0
operator|-
literal|1.0D
argument_list|)
argument_list|)
argument_list|,
name|lerp
argument_list|(
name|unknown6
argument_list|,
name|grad
argument_list|(
name|noise
index|[
name|unknown9
operator|+
literal|1
index|]
argument_list|,
name|unknown2
argument_list|,
name|unknown1
operator|-
literal|1.0D
argument_list|,
name|unknown0
operator|-
literal|1.0D
argument_list|)
argument_list|,
name|grad
argument_list|(
name|noise
index|[
name|unknown3
operator|+
literal|1
index|]
argument_list|,
name|unknown2
operator|-
literal|1.0D
argument_list|,
name|unknown1
operator|-
literal|1.0D
argument_list|,
name|unknown0
operator|-
literal|1.0D
argument_list|)
argument_list|)
argument_list|)
argument_list|)
return|;
block|}
specifier|private
name|int
index|[]
name|noise
decl_stmt|;
specifier|private
specifier|static
name|double
name|a
parameter_list|(
name|double
name|unknown0
parameter_list|)
block|{
return|return
name|unknown0
operator|*
name|unknown0
operator|*
name|unknown0
operator|*
operator|(
name|unknown0
operator|*
operator|(
name|unknown0
operator|*
literal|6.0D
operator|-
literal|15.0D
operator|)
operator|+
literal|10.0D
operator|)
return|;
block|}
specifier|private
specifier|static
name|double
name|lerp
parameter_list|(
name|double
name|unknown0
parameter_list|,
name|double
name|unknown1
parameter_list|,
name|double
name|unknown2
parameter_list|)
block|{
return|return
name|unknown1
operator|+
name|unknown0
operator|*
operator|(
name|unknown2
operator|-
name|unknown1
operator|)
return|;
block|}
specifier|private
specifier|static
name|double
name|grad
parameter_list|(
name|int
name|unknown0
parameter_list|,
name|double
name|unknown1
parameter_list|,
name|double
name|unknown2
parameter_list|,
name|double
name|unknown3
parameter_list|)
block|{
name|double
name|unknown4
init|=
operator|(
name|unknown0
operator|&=
literal|15
operator|)
operator|<
literal|8
condition|?
name|unknown1
else|:
name|unknown2
decl_stmt|;
name|double
name|unknown5
init|=
name|unknown0
operator|<
literal|4
condition|?
name|unknown2
else|:
operator|(
name|unknown0
operator|!=
literal|12
operator|&&
name|unknown0
operator|!=
literal|14
condition|?
name|unknown3
else|:
name|unknown1
operator|)
decl_stmt|;
return|return
operator|(
operator|(
name|unknown0
operator|&
literal|1
operator|)
operator|==
literal|0
condition|?
name|unknown4
else|:
operator|-
name|unknown4
operator|)
operator|+
operator|(
operator|(
name|unknown0
operator|&
literal|2
operator|)
operator|==
literal|0
condition|?
name|unknown5
else|:
operator|-
name|unknown5
operator|)
return|;
block|}
block|}
end_class

end_unit

