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
name|java
operator|.
name|util
operator|.
name|Random
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
name|level
operator|.
name|liquid
operator|.
name|LiquidType
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
name|physics
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

begin_class
specifier|public
class|class
name|LiquidBlock
extends|extends
name|Block
block|{
specifier|protected
name|LiquidType
name|type
decl_stmt|;
specifier|protected
name|int
name|stillId
decl_stmt|;
specifier|protected
name|int
name|movingId
decl_stmt|;
specifier|protected
name|LiquidBlock
parameter_list|(
name|int
name|var1
parameter_list|,
name|LiquidType
name|var2
parameter_list|)
block|{
name|super
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|type
operator|=
name|var2
expr_stmt|;
name|textureId
operator|=
literal|14
expr_stmt|;
if|if
condition|(
name|var2
operator|==
name|LiquidType
operator|.
name|lava
condition|)
block|{
name|textureId
operator|=
literal|30
expr_stmt|;
block|}
name|Block
operator|.
name|liquid
index|[
name|var1
index|]
operator|=
literal|true
expr_stmt|;
name|movingId
operator|=
name|var1
expr_stmt|;
name|stillId
operator|=
name|var1
operator|+
literal|1
expr_stmt|;
name|float
name|var4
init|=
literal|0.01F
decl_stmt|;
name|float
name|var3
init|=
literal|0.1F
decl_stmt|;
name|setBounds
argument_list|(
name|var4
operator|+
literal|0F
argument_list|,
literal|0F
operator|-
name|var3
operator|+
name|var4
argument_list|,
name|var4
operator|+
literal|0F
argument_list|,
name|var4
operator|+
literal|1F
argument_list|,
literal|1F
operator|-
name|var3
operator|+
name|var4
argument_list|,
name|var4
operator|+
literal|1F
argument_list|)
expr_stmt|;
name|setPhysics
argument_list|(
literal|true
argument_list|)
expr_stmt|;
block|}
specifier|private
name|boolean
name|canFlow
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
parameter_list|)
block|{
if|if
condition|(
name|type
operator|==
name|LiquidType
operator|.
name|water
condition|)
block|{
for|for
control|(
name|int
name|var7
init|=
name|var2
operator|-
literal|2
init|;
name|var7
operator|<=
name|var2
operator|+
literal|2
condition|;
operator|++
name|var7
control|)
block|{
for|for
control|(
name|int
name|var5
init|=
name|var3
operator|-
literal|2
init|;
name|var5
operator|<=
name|var3
operator|+
literal|2
condition|;
operator|++
name|var5
control|)
block|{
for|for
control|(
name|int
name|var6
init|=
name|var4
operator|-
literal|2
init|;
name|var6
operator|<=
name|var4
operator|+
literal|2
condition|;
operator|++
name|var6
control|)
block|{
if|if
condition|(
name|var1
operator|.
name|getTile
argument_list|(
name|var7
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|)
operator|==
name|Block
operator|.
name|SPONGE
operator|.
name|id
condition|)
block|{
return|return
literal|false
return|;
block|}
block|}
block|}
block|}
block|}
return|return
literal|true
return|;
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
decl_stmt|;
return|return
name|x
operator|>=
literal|0
operator|&&
name|y
operator|>=
literal|0
operator|&&
name|z
operator|>=
literal|0
operator|&&
name|x
operator|<
name|level
operator|.
name|width
operator|&&
name|z
operator|<
name|level
operator|.
name|length
condition|?
operator|(
name|var6
operator|=
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
operator|)
operator|!=
name|movingId
operator|&&
name|var6
operator|!=
name|stillId
condition|?
name|side
operator|==
literal|1
operator|&&
operator|(
name|level
operator|.
name|getTile
argument_list|(
name|x
operator|-
literal|1
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
operator|==
literal|0
operator|||
name|level
operator|.
name|getTile
argument_list|(
name|x
operator|+
literal|1
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
operator|==
literal|0
operator|||
name|level
operator|.
name|getTile
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
operator|-
literal|1
argument_list|)
operator|==
literal|0
operator|||
name|level
operator|.
name|getTile
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
operator|+
literal|1
argument_list|)
operator|==
literal|0
operator|)
condition|?
literal|true
else|:
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
else|:
literal|false
else|:
literal|false
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|dropItems
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
name|float
name|var5
parameter_list|)
block|{
block|}
specifier|private
name|boolean
name|flow
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
parameter_list|)
block|{
if|if
condition|(
name|var1
operator|.
name|getTile
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
operator|==
literal|0
condition|)
block|{
if|if
condition|(
operator|!
name|canFlow
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
condition|)
block|{
return|return
literal|false
return|;
block|}
if|if
condition|(
name|var1
operator|.
name|setTile
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|movingId
argument_list|)
condition|)
block|{
name|var1
operator|.
name|addToTickNextTick
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|movingId
argument_list|)
expr_stmt|;
block|}
block|}
return|return
literal|false
return|;
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
if|if
condition|(
name|type
operator|==
name|LiquidType
operator|.
name|lava
condition|)
block|{
specifier|final
name|ColorCache
name|c
init|=
operator|new
name|ColorCache
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|)
decl_stmt|;
name|c
operator|.
name|R
operator|=
literal|100F
expr_stmt|;
name|c
operator|.
name|G
operator|=
literal|100F
expr_stmt|;
name|c
operator|.
name|B
operator|=
literal|100F
expr_stmt|;
return|return
name|c
return|;
block|}
else|else
block|{
return|return
name|level
operator|.
name|getBrightnessColor
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
return|;
block|}
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
name|LiquidType
name|getLiquidType
parameter_list|()
block|{
return|return
name|type
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|int
name|getRenderPass
parameter_list|()
block|{
return|return
name|type
operator|==
name|LiquidType
operator|.
name|water
condition|?
literal|1
else|:
literal|0
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|int
name|getTickDelay
parameter_list|()
block|{
return|return
name|type
operator|==
name|LiquidType
operator|.
name|lava
condition|?
literal|5
else|:
literal|0
return|;
block|}
annotation|@
name|Override
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
annotation|@
name|Override
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
name|void
name|onBreak
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
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
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
name|var5
operator|!=
literal|0
condition|)
block|{
name|LiquidType
name|var6
init|=
name|Block
operator|.
name|blocks
index|[
name|var5
index|]
operator|.
name|getLiquidType
argument_list|()
decl_stmt|;
if|if
condition|(
name|type
operator|==
name|LiquidType
operator|.
name|water
operator|&&
name|var6
operator|==
name|LiquidType
operator|.
name|lava
operator|||
name|var6
operator|==
name|LiquidType
operator|.
name|water
operator|&&
name|type
operator|==
name|LiquidType
operator|.
name|lava
condition|)
block|{
name|var1
operator|.
name|setTile
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|Block
operator|.
name|OBSIDIAN
operator|.
name|id
argument_list|)
expr_stmt|;
return|return;
block|}
block|}
name|var1
operator|.
name|addToTickNextTick
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|var5
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|onPlace
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
name|level
operator|.
name|addToTickNextTick
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|movingId
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|renderInside
parameter_list|(
name|ShapeRenderer
name|shapeRenderer
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
name|super
operator|.
name|renderInside
argument_list|(
name|shapeRenderer
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
name|super
operator|.
name|renderSide
argument_list|(
name|shapeRenderer
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
annotation|@
name|Override
specifier|public
name|void
name|update
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
name|Random
name|rand
parameter_list|)
block|{
name|boolean
name|var8
init|=
literal|false
decl_stmt|;
name|boolean
name|var6
decl_stmt|;
do|do
block|{
operator|--
name|y
expr_stmt|;
if|if
condition|(
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
literal|0
operator|||
operator|!
name|canFlow
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
condition|)
block|{
break|break;
block|}
if|if
condition|(
name|var6
operator|=
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
name|movingId
argument_list|)
condition|)
block|{
name|var8
operator|=
literal|true
expr_stmt|;
block|}
block|}
do|while
condition|(
name|var6
operator|&&
name|type
operator|!=
name|LiquidType
operator|.
name|lava
condition|)
do|;
operator|++
name|y
expr_stmt|;
if|if
condition|(
name|type
operator|==
name|LiquidType
operator|.
name|water
operator|||
operator|!
name|var8
condition|)
block|{
name|var8
operator|=
name|var8
operator||
name|flow
argument_list|(
name|level
argument_list|,
name|x
operator|-
literal|1
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
operator||
name|flow
argument_list|(
name|level
argument_list|,
name|x
operator|+
literal|1
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
operator||
name|flow
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
operator|-
literal|1
argument_list|)
operator||
name|flow
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
operator|+
literal|1
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|var8
condition|)
block|{
name|level
operator|.
name|setTileNoUpdate
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|stillId
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|level
operator|.
name|addToTickNextTick
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|movingId
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

