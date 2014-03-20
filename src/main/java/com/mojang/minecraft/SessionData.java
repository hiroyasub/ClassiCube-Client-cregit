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
name|Arrays
name|import
name|java
operator|.
name|util
operator|.
name|List
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
name|oyasunadev
operator|.
name|mcraft
operator|.
name|client
operator|.
name|util
operator|.
name|Constants
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
argument_list|<
name|Block
argument_list|>
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
static|static
block|{
name|addStandardMinecraftBlocks
argument_list|()
expr_stmt|;
comment|// init
block|}
specifier|public
name|SessionData
parameter_list|(
name|String
name|username
parameter_list|,
name|String
name|sessionID
parameter_list|)
block|{
name|this
operator|.
name|username
operator|=
name|username
expr_stmt|;
name|sessionId
operator|=
name|sessionID
expr_stmt|;
block|}
specifier|public
specifier|static
name|void
name|addStandardMinecraftBlocks
parameter_list|()
block|{
name|ArrayList
argument_list|<
name|Block
argument_list|>
name|ab
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|ab
operator|.
name|addAll
argument_list|(
name|Arrays
operator|.
name|asList
argument_list|(
name|Block
operator|.
name|blocks
argument_list|)
operator|.
name|subList
argument_list|(
literal|1
argument_list|,
literal|50
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|Minecraft
operator|.
name|isSinglePlayer
condition|)
block|{
name|ab
operator|.
name|remove
argument_list|(
name|Block
operator|.
name|BEDROCK
argument_list|)
expr_stmt|;
comment|// players can't delete this
block|}
name|allowedBlocks
operator|=
name|ab
expr_stmt|;
block|}
specifier|public
specifier|static
name|void
name|setAllowedBlocks
parameter_list|(
name|byte
name|supportLevel
parameter_list|)
block|{
comment|// latest
if|if
condition|(
name|supportLevel
operator|==
name|Constants
operator|.
name|CUSTOM_BLOCK_SUPPORT_LEVEL
condition|)
block|{
name|ArrayList
argument_list|<
name|Block
argument_list|>
name|ab
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|ab
operator|.
name|addAll
argument_list|(
name|Arrays
operator|.
name|asList
argument_list|(
name|Block
operator|.
name|blocks
argument_list|)
operator|.
name|subList
argument_list|(
literal|1
argument_list|,
name|Block
operator|.
name|blocks
operator|.
name|length
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|Minecraft
operator|.
name|isSinglePlayer
condition|)
block|{
name|ab
operator|.
name|remove
argument_list|(
name|Block
operator|.
name|BEDROCK
argument_list|)
expr_stmt|;
block|}
name|allowedBlocks
operator|=
name|ab
expr_stmt|;
block|}
if|else if
condition|(
name|supportLevel
operator|==
literal|1
condition|)
block|{
comment|// level 1
name|ArrayList
argument_list|<
name|Block
argument_list|>
name|ab
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|ab
operator|.
name|addAll
argument_list|(
name|Arrays
operator|.
name|asList
argument_list|(
name|Block
operator|.
name|blocks
argument_list|)
operator|.
name|subList
argument_list|(
literal|1
argument_list|,
literal|65
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|Minecraft
operator|.
name|isSinglePlayer
condition|)
block|{
name|ab
operator|.
name|remove
argument_list|(
name|Block
operator|.
name|BEDROCK
argument_list|)
expr_stmt|;
block|}
name|allowedBlocks
operator|=
name|ab
expr_stmt|;
block|}
if|else if
condition|(
name|supportLevel
operator|<=
literal|0
condition|)
block|{
comment|// minecraft
name|addStandardMinecraftBlocks
argument_list|()
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

