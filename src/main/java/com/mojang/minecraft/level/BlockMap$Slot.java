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
name|Entity
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
class|class
name|BlockMap$Slot
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
comment|// $FF: synthetic method
specifier|static
name|int
name|getXSlot
parameter_list|(
name|BlockMap$Slot
name|slot
parameter_list|)
block|{
return|return
name|slot
operator|.
name|xSlot
return|;
block|}
comment|// $FF: synthetic method
specifier|static
name|int
name|getYSlot
parameter_list|(
name|BlockMap$Slot
name|slot
parameter_list|)
block|{
return|return
name|slot
operator|.
name|ySlot
return|;
block|}
comment|// $FF: synthetic method
specifier|static
name|int
name|getZSlot
parameter_list|(
name|BlockMap$Slot
name|slot
parameter_list|)
block|{
return|return
name|slot
operator|.
name|zSlot
return|;
block|}
specifier|private
name|int
name|xSlot
decl_stmt|;
specifier|private
name|int
name|ySlot
decl_stmt|;
specifier|private
name|int
name|zSlot
decl_stmt|;
comment|// $FF: synthetic field
specifier|final
name|BlockMap
name|blockMap
decl_stmt|;
specifier|public
name|BlockMap$Slot
parameter_list|(
name|BlockMap
name|blockMap
parameter_list|)
block|{
name|this
operator|.
name|blockMap
operator|=
name|blockMap
expr_stmt|;
block|}
specifier|public
name|BlockMap$Slot
name|init
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
name|xSlot
operator|=
operator|(
name|int
operator|)
operator|(
name|x
operator|/
literal|16F
operator|)
expr_stmt|;
name|ySlot
operator|=
operator|(
name|int
operator|)
operator|(
name|y
operator|/
literal|16F
operator|)
expr_stmt|;
name|zSlot
operator|=
operator|(
name|int
operator|)
operator|(
name|z
operator|/
literal|16F
operator|)
expr_stmt|;
if|if
condition|(
name|xSlot
operator|<
literal|0
condition|)
block|{
name|xSlot
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|ySlot
operator|<
literal|0
condition|)
block|{
name|ySlot
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|zSlot
operator|<
literal|0
condition|)
block|{
name|zSlot
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|xSlot
operator|>=
name|BlockMap
operator|.
name|getWidth
argument_list|(
name|blockMap
argument_list|)
condition|)
block|{
name|xSlot
operator|=
name|BlockMap
operator|.
name|getWidth
argument_list|(
name|blockMap
argument_list|)
operator|-
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|ySlot
operator|>=
name|BlockMap
operator|.
name|getDepth
argument_list|(
name|blockMap
argument_list|)
condition|)
block|{
name|ySlot
operator|=
name|BlockMap
operator|.
name|getDepth
argument_list|(
name|blockMap
argument_list|)
operator|-
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|zSlot
operator|>=
name|BlockMap
operator|.
name|getHeight
argument_list|(
name|blockMap
argument_list|)
condition|)
block|{
name|zSlot
operator|=
name|BlockMap
operator|.
name|getHeight
argument_list|(
name|blockMap
argument_list|)
operator|-
literal|1
expr_stmt|;
block|}
return|return
name|this
return|;
block|}
comment|/**      * Adds an entity to the BlockMap slot.      * @param entity      */
specifier|public
name|void
name|add
parameter_list|(
name|Entity
name|entity
parameter_list|)
block|{
if|if
condition|(
name|xSlot
operator|>=
literal|0
operator|&&
name|ySlot
operator|>=
literal|0
operator|&&
name|zSlot
operator|>=
literal|0
condition|)
block|{
name|blockMap
operator|.
name|entityGrid
index|[
operator|(
name|zSlot
operator|*
name|BlockMap
operator|.
name|getDepth
argument_list|(
name|blockMap
argument_list|)
operator|+
name|ySlot
operator|)
operator|*
name|BlockMap
operator|.
name|getWidth
argument_list|(
name|blockMap
argument_list|)
operator|+
name|xSlot
index|]
operator|.
name|add
argument_list|(
name|entity
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Removes an entity from the BlockMap slot.      * @param entity      */
specifier|public
name|void
name|remove
parameter_list|(
name|Entity
name|entity
parameter_list|)
block|{
if|if
condition|(
name|xSlot
operator|>=
literal|0
operator|&&
name|ySlot
operator|>=
literal|0
operator|&&
name|zSlot
operator|>=
literal|0
condition|)
block|{
name|blockMap
operator|.
name|entityGrid
index|[
operator|(
name|zSlot
operator|*
name|BlockMap
operator|.
name|getDepth
argument_list|(
name|blockMap
argument_list|)
operator|+
name|ySlot
operator|)
operator|*
name|BlockMap
operator|.
name|getWidth
argument_list|(
name|blockMap
argument_list|)
operator|+
name|xSlot
index|]
operator|.
name|remove
argument_list|(
name|entity
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

