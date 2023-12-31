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
name|MobSpawner
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
name|mob
operator|.
name|Mob
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
specifier|final
class|class
name|SurvivalGameMode
extends|extends
name|GameMode
block|{
specifier|private
name|int
name|hitX
decl_stmt|;
specifier|private
name|int
name|hitY
decl_stmt|;
specifier|private
name|int
name|hitZ
decl_stmt|;
specifier|private
name|int
name|hits
decl_stmt|;
specifier|private
name|int
name|hardness
decl_stmt|;
specifier|private
name|int
name|hitDelay
decl_stmt|;
specifier|public
name|SurvivalGameMode
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|)
block|{
name|super
argument_list|(
name|minecraft
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|apply
parameter_list|(
name|Level
name|level
parameter_list|)
block|{
name|super
operator|.
name|apply
argument_list|(
name|level
argument_list|)
expr_stmt|;
comment|// spawner = new MobSpawner(level, this.minecraft.settings.Peaceful);
block|}
annotation|@
name|Override
specifier|public
name|void
name|stopSpawner
parameter_list|(
name|Level
name|level
parameter_list|)
block|{
name|this
operator|.
name|spawner
operator|.
name|hasStopped
operator|=
literal|true
expr_stmt|;
block|}
annotation|@
name|Override
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
if|if
condition|(
name|hitDelay
operator|>
literal|0
condition|)
block|{
name|hitDelay
operator|--
expr_stmt|;
block|}
if|else if
condition|(
name|x
operator|==
name|hitX
operator|&&
name|y
operator|==
name|hitY
operator|&&
name|z
operator|==
name|hitZ
condition|)
block|{
name|int
name|type
init|=
name|minecraft
operator|.
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
if|if
condition|(
name|type
operator|!=
literal|0
condition|)
block|{
name|Block
name|block
init|=
name|Block
operator|.
name|blocks
index|[
name|type
index|]
decl_stmt|;
name|hardness
operator|=
name|block
operator|.
name|getHardness
argument_list|()
expr_stmt|;
name|block
operator|.
name|spawnBlockParticles
argument_list|(
name|minecraft
operator|.
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|side
argument_list|,
name|minecraft
operator|.
name|particleManager
argument_list|)
expr_stmt|;
name|hits
operator|++
expr_stmt|;
if|if
condition|(
name|hits
operator|==
name|hardness
operator|+
literal|1
condition|)
block|{
name|breakBlock
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
expr_stmt|;
name|hits
operator|=
literal|0
expr_stmt|;
name|hitDelay
operator|=
literal|5
expr_stmt|;
block|}
block|}
block|}
else|else
block|{
comment|// TODO: I think if I don't set hits to 0 you can continue breaking
comment|// from where you left off.
name|hits
operator|=
literal|0
expr_stmt|;
name|hitX
operator|=
name|x
expr_stmt|;
name|hitY
operator|=
name|y
expr_stmt|;
name|hitZ
operator|=
name|z
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|boolean
name|canPlace
parameter_list|(
name|int
name|block
parameter_list|)
block|{
return|return
name|minecraft
operator|.
name|player
operator|.
name|inventory
operator|.
name|removeResource
argument_list|(
name|block
argument_list|)
return|;
block|}
annotation|@
name|Override
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
name|int
name|block
init|=
name|minecraft
operator|.
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
name|Block
operator|.
name|blocks
index|[
name|block
index|]
operator|.
name|onBreak
argument_list|(
name|minecraft
operator|.
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
expr_stmt|;
name|super
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
annotation|@
name|Override
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
name|int
name|block
init|=
name|this
operator|.
name|minecraft
operator|.
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
if|if
condition|(
name|block
operator|>
literal|0
operator|&&
name|Block
operator|.
name|blocks
index|[
name|block
index|]
operator|.
name|getHardness
argument_list|()
operator|==
literal|0
condition|)
block|{
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
block|}
annotation|@
name|Override
specifier|public
name|void
name|resetHits
parameter_list|()
block|{
name|this
operator|.
name|hits
operator|=
literal|0
expr_stmt|;
name|this
operator|.
name|hitDelay
operator|=
literal|0
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|applyCracks
parameter_list|(
name|float
name|time
parameter_list|)
block|{
if|if
condition|(
name|hits
operator|<=
literal|0
condition|)
block|{
name|minecraft
operator|.
name|levelRenderer
operator|.
name|cracks
operator|=
literal|0F
expr_stmt|;
block|}
else|else
block|{
name|minecraft
operator|.
name|levelRenderer
operator|.
name|cracks
operator|=
operator|(
name|hits
operator|+
name|time
operator|-
literal|1F
operator|)
operator|/
name|hardness
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|float
name|getReachDistance
parameter_list|()
block|{
return|return
literal|4F
return|;
block|}
annotation|@
name|Override
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
name|Block
name|block
init|=
name|Block
operator|.
name|blocks
index|[
name|type
index|]
decl_stmt|;
if|if
condition|(
name|block
operator|==
name|Block
operator|.
name|RED_MUSHROOM
operator|&&
name|minecraft
operator|.
name|player
operator|.
name|inventory
operator|.
name|removeResource
argument_list|(
name|type
argument_list|)
condition|)
block|{
name|player
operator|.
name|health
operator|+=
literal|4
expr_stmt|;
if|if
condition|(
name|player
operator|.
name|health
operator|>
literal|30
condition|)
block|{
name|player
operator|.
name|health
operator|=
literal|30
expr_stmt|;
block|}
return|return
literal|true
return|;
block|}
if|else if
condition|(
name|block
operator|==
name|Block
operator|.
name|BROWN_MUSHROOM
operator|&&
name|minecraft
operator|.
name|player
operator|.
name|inventory
operator|.
name|removeResource
argument_list|(
name|type
argument_list|)
condition|)
block|{
name|player
operator|.
name|health
operator|+=
literal|4
expr_stmt|;
if|if
condition|(
name|player
operator|.
name|health
operator|>
literal|15
condition|)
block|{
name|player
operator|.
name|health
operator|=
literal|30
expr_stmt|;
block|}
return|return
literal|true
return|;
block|}
else|else
block|{
return|return
literal|false
return|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|preparePlayer
parameter_list|(
name|Player
name|player
parameter_list|)
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
literal|49
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|i
operator|!=
name|Block
operator|.
name|TNT
operator|.
name|id
condition|)
block|{
name|player
operator|.
name|inventory
operator|.
name|removeResource
argument_list|(
name|i
argument_list|)
expr_stmt|;
block|}
block|}
name|player
operator|.
name|inventory
operator|.
name|slots
index|[
literal|8
index|]
operator|=
name|Block
operator|.
name|TNT
operator|.
name|id
expr_stmt|;
name|player
operator|.
name|inventory
operator|.
name|count
index|[
literal|8
index|]
operator|=
literal|20
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|spawnMob
parameter_list|()
block|{
if|if
condition|(
name|this
operator|.
name|spawner
operator|.
name|hasStopped
condition|)
block|{
return|return;
block|}
name|int
name|area
init|=
name|this
operator|.
name|spawner
operator|.
name|level
operator|.
name|width
operator|*
name|this
operator|.
name|spawner
operator|.
name|level
operator|.
name|length
operator|*
name|spawner
operator|.
name|level
operator|.
name|height
operator|/
literal|64
operator|/
literal|64
operator|/
literal|64
decl_stmt|;
if|if
condition|(
name|spawner
operator|.
name|level
operator|.
name|random
operator|.
name|nextInt
argument_list|(
literal|100
argument_list|)
operator|<
name|area
operator|&&
name|this
operator|.
name|spawner
operator|.
name|level
operator|.
name|countInstanceOf
argument_list|(
name|Mob
operator|.
name|class
argument_list|)
operator|<
name|area
operator|*
literal|10
condition|)
block|{
name|this
operator|.
name|spawner
operator|.
name|spawn
argument_list|(
name|area
argument_list|,
name|this
operator|.
name|spawner
operator|.
name|level
operator|.
name|player
argument_list|,
literal|null
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|prepareLevel
parameter_list|(
name|Level
name|level
parameter_list|)
block|{
name|this
operator|.
name|spawner
operator|=
operator|new
name|MobSpawner
argument_list|(
name|level
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Spawning.."
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

