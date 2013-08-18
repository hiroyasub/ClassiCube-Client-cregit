begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|gamemode
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
name|Minecraft
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
name|tile
operator|.
name|Block
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
name|Tile$SoundType
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
name|player
operator|.
name|Player
import|;
end_import

begin_class
specifier|public
class|class
name|GameMode
block|{
specifier|public
name|GameMode
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|)
block|{
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
name|instantBreak
operator|=
literal|false
expr_stmt|;
block|}
specifier|public
name|Minecraft
name|minecraft
decl_stmt|;
specifier|public
name|boolean
name|instantBreak
decl_stmt|;
specifier|public
name|void
name|apply
parameter_list|(
name|Level
name|level
parameter_list|)
block|{
name|level
operator|.
name|creativeMode
operator|=
literal|false
expr_stmt|;
name|level
operator|.
name|growTrees
operator|=
literal|true
expr_stmt|;
block|}
specifier|public
name|void
name|openInventory
parameter_list|()
block|{
block|}
specifier|public
name|void
name|hitBlock
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
name|this
operator|.
name|breakBlock
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
expr_stmt|;
block|}
specifier|public
name|boolean
name|canPlace
parameter_list|(
name|int
name|block
parameter_list|)
block|{
return|return
literal|true
return|;
block|}
specifier|public
name|void
name|breakBlock
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
name|Level
name|level
init|=
name|minecraft
operator|.
name|level
decl_stmt|;
name|Block
name|block
init|=
name|Block
operator|.
name|blocks
index|[
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
index|]
decl_stmt|;
name|boolean
name|success
init|=
name|level
operator|.
name|netSetTile
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
literal|0
argument_list|)
decl_stmt|;
if|if
condition|(
name|block
operator|!=
literal|null
operator|&&
name|success
condition|)
block|{
if|if
condition|(
name|minecraft
operator|.
name|isOnline
argument_list|()
condition|)
block|{
name|minecraft
operator|.
name|networkManager
operator|.
name|sendBlockChange
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
literal|0
argument_list|,
name|minecraft
operator|.
name|player
operator|.
name|inventory
operator|.
name|getSelected
argument_list|()
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|block
operator|.
name|stepsound
operator|!=
name|Tile$SoundType
operator|.
name|none
condition|)
block|{
name|level
operator|.
name|playSound
argument_list|(
literal|"step."
operator|+
name|block
operator|.
name|stepsound
operator|.
name|name
argument_list|,
operator|(
name|float
operator|)
name|x
argument_list|,
operator|(
name|float
operator|)
name|y
argument_list|,
operator|(
name|float
operator|)
name|z
argument_list|,
operator|(
name|block
operator|.
name|stepsound
operator|.
name|getVolume
argument_list|()
operator|+
literal|1.0F
operator|)
operator|/
literal|2.0F
argument_list|,
name|block
operator|.
name|stepsound
operator|.
name|getPitch
argument_list|()
operator|*
literal|0.8F
argument_list|)
expr_stmt|;
block|}
name|block
operator|.
name|spawnBreakParticles
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|minecraft
operator|.
name|particleManager
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|hitBlock
parameter_list|(
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
block|}
specifier|public
name|void
name|resetHits
parameter_list|()
block|{
block|}
specifier|public
name|void
name|applyCracks
parameter_list|(
name|float
name|time
parameter_list|)
block|{
block|}
specifier|public
name|float
name|getReachDistance
parameter_list|()
block|{
return|return
literal|5.0F
return|;
block|}
specifier|public
name|boolean
name|useItem
parameter_list|(
name|Player
name|player
parameter_list|,
name|int
name|type
parameter_list|)
block|{
return|return
literal|false
return|;
block|}
specifier|public
name|void
name|preparePlayer
parameter_list|(
name|Player
name|player
parameter_list|)
block|{
block|}
specifier|public
name|void
name|spawnMob
parameter_list|()
block|{
block|}
specifier|public
name|void
name|prepareLevel
parameter_list|(
name|Level
name|level
parameter_list|)
block|{
block|}
specifier|public
name|boolean
name|isSurvival
parameter_list|()
block|{
return|return
literal|true
return|;
block|}
specifier|public
name|void
name|apply
parameter_list|(
name|Player
name|player
parameter_list|)
block|{
block|}
block|}
end_class

end_unit

