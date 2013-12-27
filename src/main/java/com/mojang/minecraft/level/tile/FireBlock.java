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

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|render
operator|.
name|ShapeRenderer
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|MathHelper
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
name|ColorCache
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|FireBlock
extends|extends
name|Block
block|{
specifier|protected
name|FireBlock
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
name|float
name|var3
init|=
literal|0.4F
decl_stmt|;
name|this
operator|.
name|setBounds
argument_list|(
literal|0.5F
operator|-
name|var3
argument_list|,
literal|0.0F
argument_list|,
literal|0.5F
operator|-
name|var3
argument_list|,
name|var3
operator|+
literal|0.5F
argument_list|,
name|var3
operator|*
literal|2.0F
argument_list|,
name|var3
operator|+
literal|0.5F
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|ColorCache
name|getBrightness
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
return|return
operator|new
name|ColorCache
argument_list|(
literal|255.0F
operator|/
literal|255.0F
argument_list|,
literal|255.0F
operator|/
literal|255.0F
argument_list|,
literal|255.0F
operator|/
literal|255.0F
argument_list|)
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
annotation|@
name|Override
specifier|public
specifier|final
name|boolean
name|render
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
name|ShapeRenderer
name|var5
parameter_list|)
block|{
name|ColorCache
name|var6
init|=
name|getBrightness
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
decl_stmt|;
name|var5
operator|.
name|color
argument_list|(
name|var6
operator|.
name|R
argument_list|,
name|var6
operator|.
name|G
argument_list|,
name|var6
operator|.
name|B
argument_list|)
expr_stmt|;
name|this
operator|.
name|render
argument_list|(
name|var5
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
expr_stmt|;
return|return
literal|true
return|;
block|}
specifier|private
name|void
name|render
parameter_list|(
name|ShapeRenderer
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|float
name|var4
parameter_list|)
block|{
name|int
name|var15
decl_stmt|;
name|int
name|var5
init|=
operator|(
name|var15
operator|=
name|this
operator|.
name|getTextureId
argument_list|(
literal|15
argument_list|)
operator|)
operator|%
literal|16
operator|<<
literal|4
decl_stmt|;
name|int
name|var6
init|=
name|var15
operator|/
literal|16
operator|<<
literal|4
decl_stmt|;
name|float
name|var16
init|=
name|var5
operator|/
literal|256.0F
decl_stmt|;
name|float
name|var17
init|=
operator|(
name|var5
operator|+
literal|15.99F
operator|)
operator|/
literal|256.0F
decl_stmt|;
name|float
name|var7
init|=
name|var6
operator|/
literal|256.0F
decl_stmt|;
name|float
name|var18
init|=
operator|(
name|var6
operator|+
literal|15.99F
operator|)
operator|/
literal|256.0F
decl_stmt|;
for|for
control|(
name|int
name|var8
init|=
literal|0
init|;
name|var8
operator|<
literal|2
condition|;
operator|++
name|var8
control|)
block|{
name|float
name|var9
init|=
operator|(
name|float
operator|)
operator|(
name|MathHelper
operator|.
name|sin
argument_list|(
name|var8
operator|*
literal|3.1415927F
operator|/
literal|2.0F
operator|+
literal|0.7853982F
argument_list|)
operator|*
literal|0.5D
operator|)
decl_stmt|;
name|float
name|var10
init|=
operator|(
name|float
operator|)
operator|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var8
operator|*
literal|3.1415927F
operator|/
literal|2.0F
operator|+
literal|0.7853982F
argument_list|)
operator|*
literal|0.5D
operator|)
decl_stmt|;
name|float
name|var11
init|=
name|var2
operator|+
literal|0.5F
operator|-
name|var9
decl_stmt|;
name|var9
operator|+=
name|var2
operator|+
literal|0.5F
expr_stmt|;
name|float
name|var13
init|=
name|var3
operator|+
literal|1.0F
decl_stmt|;
name|float
name|var14
init|=
name|var4
operator|+
literal|0.5F
operator|-
name|var10
decl_stmt|;
name|var10
operator|+=
name|var4
operator|+
literal|0.5F
expr_stmt|;
name|var1
operator|.
name|vertexUV
argument_list|(
name|var11
argument_list|,
name|var13
argument_list|,
name|var14
argument_list|,
name|var17
argument_list|,
name|var7
argument_list|)
expr_stmt|;
name|var1
operator|.
name|vertexUV
argument_list|(
name|var9
argument_list|,
name|var13
argument_list|,
name|var10
argument_list|,
name|var16
argument_list|,
name|var7
argument_list|)
expr_stmt|;
name|var1
operator|.
name|vertexUV
argument_list|(
name|var9
argument_list|,
name|var3
argument_list|,
name|var10
argument_list|,
name|var16
argument_list|,
name|var18
argument_list|)
expr_stmt|;
name|var1
operator|.
name|vertexUV
argument_list|(
name|var11
argument_list|,
name|var3
argument_list|,
name|var14
argument_list|,
name|var17
argument_list|,
name|var18
argument_list|)
expr_stmt|;
name|var1
operator|.
name|vertexUV
argument_list|(
name|var9
argument_list|,
name|var13
argument_list|,
name|var10
argument_list|,
name|var17
argument_list|,
name|var7
argument_list|)
expr_stmt|;
name|var1
operator|.
name|vertexUV
argument_list|(
name|var11
argument_list|,
name|var13
argument_list|,
name|var14
argument_list|,
name|var16
argument_list|,
name|var7
argument_list|)
expr_stmt|;
name|var1
operator|.
name|vertexUV
argument_list|(
name|var11
argument_list|,
name|var3
argument_list|,
name|var14
argument_list|,
name|var16
argument_list|,
name|var18
argument_list|)
expr_stmt|;
name|var1
operator|.
name|vertexUV
argument_list|(
name|var9
argument_list|,
name|var3
argument_list|,
name|var10
argument_list|,
name|var17
argument_list|,
name|var18
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|renderFullbright
parameter_list|(
name|ShapeRenderer
name|shapeRenderer
parameter_list|)
block|{
name|shapeRenderer
operator|.
name|color
argument_list|(
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|this
operator|.
name|render
argument_list|(
name|shapeRenderer
argument_list|,
operator|-
literal|2
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|renderPreview
parameter_list|(
name|ShapeRenderer
name|var1
parameter_list|)
block|{
name|var1
operator|.
name|normal
argument_list|(
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var1
operator|.
name|begin
argument_list|()
expr_stmt|;
name|this
operator|.
name|render
argument_list|(
name|var1
argument_list|,
literal|0.0F
argument_list|,
literal|0.4F
argument_list|,
operator|-
literal|0.3F
argument_list|)
expr_stmt|;
name|var1
operator|.
name|end
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

