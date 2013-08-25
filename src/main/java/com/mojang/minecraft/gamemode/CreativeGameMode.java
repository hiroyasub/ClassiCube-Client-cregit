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
name|SessionData
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
name|gui
operator|.
name|BlockSelectScreen
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
name|player
operator|.
name|Player
import|;
end_import

begin_class
specifier|public
class|class
name|CreativeGameMode
extends|extends
name|GameMode
block|{
specifier|public
name|CreativeGameMode
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
name|instantBreak
operator|=
literal|true
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
name|level
operator|.
name|removeAllNonCreativeModeEntities
argument_list|()
expr_stmt|;
name|level
operator|.
name|creativeMode
operator|=
literal|true
expr_stmt|;
name|level
operator|.
name|growTrees
operator|=
literal|false
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|openInventory
parameter_list|()
block|{
name|BlockSelectScreen
name|blockSelectScreen
init|=
operator|new
name|BlockSelectScreen
argument_list|()
decl_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|blockSelectScreen
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|boolean
name|isSurvival
parameter_list|()
block|{
return|return
literal|false
return|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|apply
parameter_list|(
name|Player
name|player
parameter_list|)
block|{
comment|//default starting blocks
name|Block
index|[]
name|blocks
init|=
operator|new
name|Block
index|[]
block|{
name|Block
operator|.
name|STONE
block|,
name|Block
operator|.
name|COBBLESTONE
block|,
name|Block
operator|.
name|BRICK
block|,
name|Block
operator|.
name|DIRT
block|,
name|Block
operator|.
name|WOOD
block|,
name|Block
operator|.
name|LOG
block|,
name|Block
operator|.
name|LEAVES
block|,
name|Block
operator|.
name|GRASS
block|,
name|Block
operator|.
name|SLAB
block|}
decl_stmt|;
name|boolean
name|CanProceed
init|=
literal|true
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|blocks
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
operator|!
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|contains
argument_list|(
name|blocks
index|[
name|i
index|]
argument_list|)
condition|)
block|{
name|CanProceed
operator|=
literal|false
expr_stmt|;
block|}
block|}
comment|//if one of them is banned, instead pick 9 blocks from allowed blocks
if|if
condition|(
operator|!
name|CanProceed
condition|)
block|{
name|blocks
operator|=
operator|new
name|Block
index|[]
block|{}
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|blocks
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|blocks
index|[
name|i
index|]
operator|=
operator|(
name|Block
operator|)
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|get
argument_list|(
name|i
argument_list|)
expr_stmt|;
block|}
block|}
comment|//set them
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|blocks
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|player
operator|.
name|inventory
operator|.
name|slots
index|[
name|i
index|]
operator|=
name|blocks
index|[
name|i
index|]
operator|.
name|id
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

