begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|sound
package|;
end_package

begin_comment
comment|// TODO.
end_comment

begin_class
specifier|public
specifier|final
class|class
name|Sound
implements|implements
name|Audio
block|{
specifier|private
name|AudioInfo
name|info
decl_stmt|;
specifier|private
name|SoundPos
name|pos
decl_stmt|;
specifier|private
name|float
name|pitch
init|=
literal|0.0F
decl_stmt|;
specifier|private
name|float
name|volume
init|=
literal|1.0F
decl_stmt|;
specifier|private
name|short
index|[]
name|data
init|=
operator|new
name|short
index|[
literal|1
index|]
decl_stmt|;
specifier|public
name|Sound
parameter_list|(
name|AudioInfo
name|var1
parameter_list|,
name|SoundPos
name|var2
parameter_list|)
block|{
name|this
operator|.
name|info
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|pos
operator|=
name|var2
expr_stmt|;
name|this
operator|.
name|pitch
operator|=
name|var2
operator|.
name|getRotationDiff
argument_list|()
expr_stmt|;
name|this
operator|.
name|volume
operator|=
name|var2
operator|.
name|getDistanceSq
argument_list|()
operator|*
name|var1
operator|.
name|volume
expr_stmt|;
block|}
specifier|public
specifier|final
name|boolean
name|play
parameter_list|(
name|short
index|[]
name|var1
parameter_list|,
name|short
index|[]
name|var2
parameter_list|,
name|int
name|var3
parameter_list|)
block|{
if|if
condition|(
name|data
operator|.
name|length
operator|<
name|var3
condition|)
block|{
name|data
operator|=
operator|new
name|short
index|[
name|var3
index|]
expr_stmt|;
block|}
name|int
name|var4
decl_stmt|;
name|boolean
name|var5
init|=
operator|(
name|var4
operator|=
name|this
operator|.
name|info
operator|.
name|update
argument_list|(
name|data
argument_list|,
name|var3
argument_list|)
operator|)
operator|>
literal|0
decl_stmt|;
name|float
name|var6
init|=
name|this
operator|.
name|pos
operator|.
name|getRotationDiff
argument_list|()
decl_stmt|;
name|float
name|var7
init|=
name|this
operator|.
name|pos
operator|.
name|getDistanceSq
argument_list|()
operator|*
name|this
operator|.
name|info
operator|.
name|volume
decl_stmt|;
name|int
name|var8
init|=
operator|(
name|int
operator|)
operator|(
operator|(
name|this
operator|.
name|pitch
operator|>
literal|0.0F
condition|?
literal|1.0F
operator|-
name|this
operator|.
name|pitch
else|:
literal|1.0F
operator|)
operator|*
name|this
operator|.
name|volume
operator|*
literal|65536.0F
operator|)
decl_stmt|;
name|int
name|var9
init|=
operator|(
name|int
operator|)
operator|(
operator|(
name|this
operator|.
name|pitch
operator|<
literal|0.0F
condition|?
literal|1.0F
operator|+
name|this
operator|.
name|pitch
else|:
literal|1.0F
operator|)
operator|*
name|this
operator|.
name|volume
operator|*
literal|65536.0F
operator|)
decl_stmt|;
name|int
name|var10
init|=
operator|(
name|int
operator|)
operator|(
operator|(
name|var6
operator|>
literal|0.0F
condition|?
literal|1.0F
operator|-
name|var6
else|:
literal|1.0F
operator|)
operator|*
name|var7
operator|*
literal|65536.0F
operator|)
decl_stmt|;
name|int
name|var11
init|=
operator|(
name|int
operator|)
operator|(
operator|(
name|var6
operator|<
literal|0.0F
condition|?
name|var6
operator|+
literal|1.0F
else|:
literal|1.0F
operator|)
operator|*
name|var7
operator|*
literal|65536.0F
operator|)
decl_stmt|;
name|var10
operator|-=
name|var8
expr_stmt|;
name|var11
operator|-=
name|var9
expr_stmt|;
name|int
name|var12
decl_stmt|;
name|int
name|var13
decl_stmt|;
name|int
name|var14
decl_stmt|;
if|if
condition|(
name|var10
operator|==
literal|0
operator|&&
name|var11
operator|==
literal|0
condition|)
block|{
if|if
condition|(
name|var8
operator|>=
literal|0
operator|||
name|var9
operator|!=
literal|0
condition|)
block|{
name|var12
operator|=
name|var8
expr_stmt|;
name|var13
operator|=
name|var9
expr_stmt|;
for|for
control|(
name|var14
operator|=
literal|0
init|;
name|var14
operator|<
name|var4
condition|;
operator|++
name|var14
control|)
block|{
name|var1
index|[
name|var14
index|]
operator|+=
name|data
index|[
name|var14
index|]
operator|*
name|var12
operator|>>
literal|16
expr_stmt|;
name|var2
index|[
name|var14
index|]
operator|+=
name|data
index|[
name|var14
index|]
operator|*
name|var13
operator|>>
literal|16
expr_stmt|;
block|}
block|}
block|}
else|else
block|{
for|for
control|(
name|var12
operator|=
literal|0
init|;
name|var12
operator|<
name|var4
condition|;
operator|++
name|var12
control|)
block|{
name|var13
operator|=
name|var8
operator|+
name|var10
operator|*
name|var12
operator|/
name|var3
expr_stmt|;
name|var14
operator|=
name|var9
operator|+
name|var11
operator|*
name|var12
operator|/
name|var3
expr_stmt|;
name|var1
index|[
name|var12
index|]
operator|+=
name|data
index|[
name|var12
index|]
operator|*
name|var13
operator|>>
literal|16
expr_stmt|;
name|var2
index|[
name|var12
index|]
operator|+=
name|data
index|[
name|var12
index|]
operator|*
name|var14
operator|>>
literal|16
expr_stmt|;
block|}
block|}
name|this
operator|.
name|pitch
operator|=
name|var6
expr_stmt|;
name|this
operator|.
name|volume
operator|=
name|var7
expr_stmt|;
return|return
name|var5
return|;
block|}
block|}
end_class

end_unit

