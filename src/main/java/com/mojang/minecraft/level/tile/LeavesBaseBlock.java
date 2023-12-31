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
class|class
name|LeavesBaseBlock
extends|extends
name|Block
block|{
specifier|private
name|boolean
name|showNeighborSides
init|=
literal|true
decl_stmt|;
specifier|protected
name|LeavesBaseBlock
parameter_list|(
name|int
name|id
parameter_list|)
block|{
name|super
argument_list|(
name|id
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
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
name|int
name|var6
init|=
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
decl_stmt|;
return|return
operator|!
operator|(
operator|!
name|showNeighborSides
operator|&&
name|var6
operator|==
name|id
operator|)
operator|&&
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
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|boolean
name|isOpaque
parameter_list|()
block|{
return|return
literal|false
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|boolean
name|isSolid
parameter_list|()
block|{
return|return
literal|false
return|;
block|}
block|}
end_class

end_unit

