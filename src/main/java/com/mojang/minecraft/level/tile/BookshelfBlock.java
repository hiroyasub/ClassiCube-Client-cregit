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

begin_class
specifier|public
specifier|final
class|class
name|BookshelfBlock
extends|extends
name|Block
block|{
specifier|public
name|BookshelfBlock
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
name|int
name|getDropCount
parameter_list|()
block|{
return|return
literal|0
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|int
name|getTextureId
parameter_list|(
name|int
name|texture
parameter_list|)
block|{
return|return
name|texture
operator|<=
literal|1
condition|?
literal|4
else|:
name|textureId
return|;
block|}
block|}
end_class

end_unit

