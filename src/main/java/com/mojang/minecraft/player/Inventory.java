begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|player
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
name|GameSettings
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
name|SessionData
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

begin_class
specifier|public
class|class
name|Inventory
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
specifier|static
specifier|final
name|int
name|POP_TIME_DURATION
init|=
literal|5
decl_stmt|;
specifier|public
name|int
index|[]
name|slots
init|=
operator|new
name|int
index|[
literal|9
index|]
decl_stmt|;
specifier|public
name|int
index|[]
name|count
init|=
operator|new
name|int
index|[
literal|9
index|]
decl_stmt|;
specifier|public
name|int
index|[]
name|popTime
init|=
operator|new
name|int
index|[
literal|9
index|]
decl_stmt|;
specifier|public
name|int
name|selected
init|=
literal|0
decl_stmt|;
specifier|public
name|Inventory
parameter_list|()
block|{
for|for
control|(
name|int
name|var1
init|=
literal|0
init|;
name|var1
operator|<
literal|9
condition|;
operator|++
name|var1
control|)
block|{
name|slots
index|[
name|var1
index|]
operator|=
operator|-
literal|1
expr_stmt|;
name|count
index|[
name|var1
index|]
operator|=
literal|0
expr_stmt|;
block|}
block|}
specifier|public
name|boolean
name|addResource
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
name|int
name|var2
decl_stmt|;
if|if
condition|(
operator|(
name|var2
operator|=
name|getSlot
argument_list|(
name|var1
argument_list|)
operator|)
operator|<
literal|0
condition|)
block|{
name|var2
operator|=
name|getSlot
argument_list|(
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|<
literal|0
condition|)
block|{
return|return
literal|false
return|;
block|}
if|else if
condition|(
name|count
index|[
name|var2
index|]
operator|>=
literal|99
condition|)
block|{
return|return
literal|false
return|;
block|}
else|else
block|{
name|slots
index|[
name|var2
index|]
operator|=
name|var1
expr_stmt|;
operator|++
name|count
index|[
name|var2
index|]
expr_stmt|;
name|popTime
index|[
name|var2
index|]
operator|=
literal|5
expr_stmt|;
return|return
literal|true
return|;
block|}
block|}
specifier|public
name|int
name|getSelected
parameter_list|()
block|{
return|return
name|slots
index|[
name|selected
index|]
return|;
block|}
specifier|private
name|int
name|getSlot
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
for|for
control|(
name|int
name|var2
init|=
literal|0
init|;
name|var2
operator|<
name|slots
operator|.
name|length
condition|;
operator|++
name|var2
control|)
block|{
if|if
condition|(
name|var1
operator|==
name|slots
index|[
name|var2
index|]
condition|)
block|{
return|return
name|var2
return|;
block|}
block|}
return|return
operator|-
literal|1
return|;
block|}
specifier|public
name|void
name|grabTexture
parameter_list|(
name|int
name|var1
parameter_list|,
name|boolean
name|var2
parameter_list|)
block|{
if|if
condition|(
name|GameSettings
operator|.
name|CanReplaceSlot
condition|)
block|{
name|int
name|var3
decl_stmt|;
if|if
condition|(
operator|(
name|var3
operator|=
name|getSlot
argument_list|(
name|var1
argument_list|)
operator|)
operator|>=
literal|0
condition|)
block|{
name|selected
operator|=
name|var3
expr_stmt|;
block|}
else|else
block|{
if|if
condition|(
name|var2
operator|&&
name|var1
operator|>
literal|0
operator|&&
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|contains
argument_list|(
name|Block
operator|.
name|blocks
index|[
name|var1
index|]
argument_list|)
condition|)
block|{
name|this
operator|.
name|replaceSlot
argument_list|(
name|Block
operator|.
name|blocks
index|[
name|var1
index|]
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
specifier|public
name|boolean
name|removeResource
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
if|if
condition|(
operator|(
name|var1
operator|=
name|getSlot
argument_list|(
name|var1
argument_list|)
operator|)
operator|<
literal|0
condition|)
block|{
return|return
literal|false
return|;
block|}
else|else
block|{
if|if
condition|(
operator|--
name|count
index|[
name|var1
index|]
operator|<=
literal|0
condition|)
block|{
name|slots
index|[
name|var1
index|]
operator|=
operator|-
literal|1
expr_stmt|;
block|}
return|return
literal|true
return|;
block|}
block|}
specifier|public
name|void
name|replaceSlot
parameter_list|(
name|Block
name|var1
parameter_list|)
block|{
if|if
condition|(
name|GameSettings
operator|.
name|CanReplaceSlot
operator|&&
name|var1
operator|!=
literal|null
condition|)
block|{
name|int
name|var2
decl_stmt|;
if|if
condition|(
operator|(
name|var2
operator|=
name|getSlot
argument_list|(
name|var1
operator|.
name|id
argument_list|)
operator|)
operator|>=
literal|0
condition|)
block|{
name|slots
index|[
name|var2
index|]
operator|=
name|slots
index|[
name|selected
index|]
expr_stmt|;
block|}
name|slots
index|[
name|selected
index|]
operator|=
name|var1
operator|.
name|id
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|replaceSlot
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
if|if
condition|(
name|GameSettings
operator|.
name|CanReplaceSlot
operator|&&
name|var1
operator|>=
literal|0
condition|)
block|{
name|this
operator|.
name|replaceSlot
argument_list|(
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|get
argument_list|(
name|var1
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|swapPaint
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
if|if
condition|(
name|GameSettings
operator|.
name|CanReplaceSlot
condition|)
block|{
if|if
condition|(
name|var1
operator|>
literal|0
condition|)
block|{
name|var1
operator|=
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|<
literal|0
condition|)
block|{
name|var1
operator|=
operator|-
literal|1
expr_stmt|;
block|}
for|for
control|(
name|selected
operator|-=
name|var1
init|;
name|selected
operator|<
literal|0
condition|;
name|selected
operator|+=
name|slots
operator|.
name|length
control|)
block|{
empty_stmt|;
block|}
while|while
condition|(
name|selected
operator|>=
name|slots
operator|.
name|length
condition|)
block|{
name|selected
operator|-=
name|slots
operator|.
name|length
expr_stmt|;
block|}
block|}
block|}
specifier|public
name|void
name|tick
parameter_list|()
block|{
for|for
control|(
name|int
name|var1
init|=
literal|0
init|;
name|var1
operator|<
name|popTime
operator|.
name|length
condition|;
operator|++
name|var1
control|)
block|{
if|if
condition|(
name|popTime
index|[
name|var1
index|]
operator|>
literal|0
condition|)
block|{
operator|--
name|popTime
index|[
name|var1
index|]
expr_stmt|;
block|}
block|}
block|}
block|}
end_class

end_unit

