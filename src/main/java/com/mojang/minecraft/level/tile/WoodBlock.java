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
name|WoodBlock
extends|extends
name|Block
block|{
specifier|protected
name|WoodBlock
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
name|super
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|this
operator|.
name|textureId
operator|=
literal|20
expr_stmt|;
block|}
specifier|public
specifier|final
name|int
name|getDropCount
parameter_list|()
block|{
return|return
name|random
operator|.
name|nextInt
argument_list|(
literal|3
argument_list|)
operator|+
literal|3
return|;
block|}
specifier|public
specifier|final
name|int
name|getDrop
parameter_list|()
block|{
return|return
name|WOOD
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
name|texture
operator|==
literal|1
condition|?
literal|21
else|:
operator|(
name|texture
operator|==
literal|0
condition|?
literal|21
else|:
literal|20
operator|)
return|;
block|}
block|}
end_class

end_unit

