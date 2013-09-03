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

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|phys
operator|.
name|AABB
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|SnowBlock
extends|extends
name|Block
block|{
name|int
name|Texture
decl_stmt|;
name|int
name|id
decl_stmt|;
specifier|public
name|SnowBlock
parameter_list|(
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
name|super
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
name|id
operator|=
name|var1
expr_stmt|;
name|Texture
operator|=
name|var2
expr_stmt|;
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
literal|0.20F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
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
name|Texture
return|;
block|}
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
specifier|public
specifier|final
name|boolean
name|isOpaque
parameter_list|()
block|{
return|return
literal|true
return|;
block|}
specifier|public
specifier|final
name|int
name|getDrop
parameter_list|()
block|{
return|return
name|SNOW
operator|.
name|id
return|;
block|}
specifier|public
specifier|final
name|boolean
name|isCube
parameter_list|()
block|{
return|return
literal|false
return|;
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
name|SNOW
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
annotation|@
name|Override
specifier|public
name|AABB
name|getCollisionBox
parameter_list|(
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
return|return
literal|null
return|;
block|}
block|}
end_class

end_unit

