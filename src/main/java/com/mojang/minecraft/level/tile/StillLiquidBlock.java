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
name|level
operator|.
name|liquid
operator|.
name|LiquidType
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Random
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|StillLiquidBlock
extends|extends
name|LiquidBlock
block|{
specifier|protected
name|StillLiquidBlock
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
argument_list|,
name|var2
argument_list|)
expr_stmt|;
name|this
operator|.
name|movingId
operator|=
name|var1
operator|-
literal|1
expr_stmt|;
name|this
operator|.
name|stillId
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|setPhysics
argument_list|(
literal|false
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
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
name|boolean
name|var6
init|=
literal|false
decl_stmt|;
if|if
condition|(
name|var1
operator|.
name|getTile
argument_list|(
name|var2
operator|-
literal|1
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
operator|==
literal|0
condition|)
block|{
name|var6
operator|=
literal|true
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|.
name|getTile
argument_list|(
name|var2
operator|+
literal|1
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
operator|==
literal|0
condition|)
block|{
name|var6
operator|=
literal|true
expr_stmt|;
block|}
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
operator|-
literal|1
argument_list|)
operator|==
literal|0
condition|)
block|{
name|var6
operator|=
literal|true
expr_stmt|;
block|}
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
operator|+
literal|1
argument_list|)
operator|==
literal|0
condition|)
block|{
name|var6
operator|=
literal|true
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|.
name|getTile
argument_list|(
name|var2
argument_list|,
name|var3
operator|-
literal|1
argument_list|,
name|var4
argument_list|)
operator|==
literal|0
condition|)
block|{
name|var6
operator|=
literal|true
expr_stmt|;
block|}
if|if
condition|(
name|var5
operator|!=
literal|0
condition|)
block|{
name|LiquidType
name|var7
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
name|this
operator|.
name|type
operator|==
name|LiquidType
operator|.
name|water
operator|&&
name|var7
operator|==
name|LiquidType
operator|.
name|lava
operator|||
name|var7
operator|==
name|LiquidType
operator|.
name|water
operator|&&
name|this
operator|.
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
name|STONE
operator|.
name|id
argument_list|)
expr_stmt|;
return|return;
block|}
block|}
if|if
condition|(
name|var6
condition|)
block|{
name|var1
operator|.
name|setTileNoUpdate
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|this
operator|.
name|movingId
argument_list|)
expr_stmt|;
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
name|this
operator|.
name|movingId
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
specifier|final
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
block|}
block|}
end_class

end_unit

