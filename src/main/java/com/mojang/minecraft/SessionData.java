begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
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
name|tile
operator|.
name|Block
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|SessionData
block|{
specifier|public
specifier|static
name|List
name|allowedBlocks
decl_stmt|;
specifier|public
name|String
name|username
decl_stmt|;
specifier|public
name|String
name|sessionId
decl_stmt|;
specifier|public
name|String
name|mppass
decl_stmt|;
specifier|public
name|boolean
name|haspaid
decl_stmt|;
specifier|public
name|SessionData
parameter_list|(
name|String
name|var1
parameter_list|,
name|String
name|var2
parameter_list|)
block|{
name|this
operator|.
name|username
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|sessionId
operator|=
name|var2
expr_stmt|;
block|}
static|static
block|{
operator|(
name|allowedBlocks
operator|=
operator|new
name|ArrayList
argument_list|()
operator|)
operator|.
name|add
argument_list|(
name|Block
operator|.
name|STONE
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|COBBLESTONE
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|BRICK
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|GRASS
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|DIRT
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|GRASS
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|WOOD
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|LOG
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|LEAVES
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|GLASS
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|SLAB
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|DOUBLE_SLAB
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|MOSSY_COBBLESTONE
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|SAPLING
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|DANDELION
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|ROSE
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|BROWN_MUSHROOM
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|RED_MUSHROOM
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|SAND
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|GRAVEL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|SPONGE
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|RED_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|ORANGE_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|YELLOW_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|LIME_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|GREEN_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|AQUA_GREEN_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|CYAN_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|BLUE_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|PURPLE_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|INDIGO_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|VIOLET_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|MAGENTA_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|PINK_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|BLACK_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|GRAY_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|WHITE_WOOL
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|COAL_ORE
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|IRON_ORE
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|GOLD_ORE
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|IRON_BLOCK
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|GOLD_BLOCK
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|BOOKSHELF
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|TNT
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|OBSIDIAN
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|BEDROCK
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|WATER
argument_list|)
expr_stmt|;
name|allowedBlocks
operator|.
name|add
argument_list|(
name|Block
operator|.
name|LAVA
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

