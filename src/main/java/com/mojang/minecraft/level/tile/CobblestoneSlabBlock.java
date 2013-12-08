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
name|tile
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
name|level
operator|.
name|Level
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|CobblestoneSlabBlock
extends|extends
name|Block
block|{
specifier|private
name|boolean
name|doubleSlab
decl_stmt|;
specifier|public
name|CobblestoneSlabBlock
parameter_list|(
name|int
name|var1
parameter_list|,
name|boolean
name|var2
parameter_list|)
block|{
name|super
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|this
operator|.
name|doubleSlab
operator|=
name|var2
expr_stmt|;
if|if
condition|(
operator|!
name|var2
condition|)
block|{
name|this
operator|.
name|setBounds
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.5F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|boolean
name|canRenderSide
parameter_list|(
name|Level
name|level
parameter_list|,
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|,
name|int
name|z
parameter_list|,
name|int
name|side
parameter_list|)
block|{
if|if
condition|(
name|this
operator|!=
name|slabCobblestone
condition|)
block|{
name|super
operator|.
name|canRenderSide
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|side
argument_list|)
expr_stmt|;
block|}
return|return
name|side
operator|==
literal|1
condition|?
literal|true
else|:
operator|(
operator|!
name|super
operator|.
name|canRenderSide
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|side
argument_list|)
condition|?
literal|false
else|:
operator|(
name|side
operator|==
literal|0
condition|?
literal|true
else|:
name|level
operator|.
name|getTile
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
operator|!=
name|this
operator|.
name|id
operator|)
operator|)
return|;
block|}
specifier|public
specifier|final
name|int
name|getDrop
parameter_list|()
block|{
return|return
name|slabCobblestone
operator|.
name|id
return|;
block|}
specifier|protected
specifier|final
name|int
name|getTextureId
parameter_list|(
name|int
name|texture
parameter_list|)
block|{
return|return
literal|16
return|;
block|}
specifier|public
specifier|final
name|boolean
name|isCube
parameter_list|()
block|{
return|return
name|this
operator|.
name|doubleSlab
return|;
block|}
specifier|public
specifier|final
name|boolean
name|isSolid
parameter_list|()
block|{
return|return
name|this
operator|.
name|doubleSlab
return|;
block|}
specifier|public
specifier|final
name|void
name|onAdded
parameter_list|(
name|Level
name|level
parameter_list|,
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|,
name|int
name|z
parameter_list|)
block|{
if|if
condition|(
name|this
operator|!=
name|slabCobblestone
condition|)
block|{
name|super
operator|.
name|onAdded
argument_list|(
name|level
argument_list|,
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
name|level
operator|.
name|getTile
argument_list|(
name|x
argument_list|,
name|y
operator|-
literal|1
argument_list|,
name|z
argument_list|)
operator|==
name|slabCobblestone
operator|.
name|id
condition|)
block|{
name|level
operator|.
name|setTile
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|level
operator|.
name|setTile
argument_list|(
name|x
argument_list|,
name|y
operator|-
literal|1
argument_list|,
name|z
argument_list|,
name|slabCobblestone
operator|.
name|id
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|onNeighborChange
parameter_list|(
name|Level
name|var1
parameter_list|,
name|int
name|var2
parameter_list|,
name|int
name|var3
parameter_list|,
name|int
name|var4
parameter_list|,
name|int
name|var5
parameter_list|)
block|{
if|if
condition|(
name|this
operator|==
name|slabCobblestone
condition|)
block|{
empty_stmt|;
block|}
block|}
block|}
end_class

end_unit

