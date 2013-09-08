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
name|AddStandardMinecraftBlocks
argument_list|()
expr_stmt|;
block|}
specifier|public
specifier|static
name|void
name|AddStandardMinecraftBlocks
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
argument_list|<
name|Block
argument_list|>
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
literal|50
condition|;
name|i
operator|++
control|)
block|{
comment|// ignore air
name|ab
operator|.
name|add
argument_list|(
name|Block
operator|.
name|blocks
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
name|allowedBlocks
operator|=
name|ab
expr_stmt|;
block|}
specifier|public
specifier|static
name|void
name|SetAllowedBlocks
parameter_list|(
name|byte
name|SupportLevel
parameter_list|)
block|{
comment|// latest
if|if
condition|(
name|SupportLevel
operator|==
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
operator|.
name|SupportLevel
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
argument_list|<
name|Block
argument_list|>
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
name|Block
operator|.
name|blocks
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|ab
operator|.
name|add
argument_list|(
name|Block
operator|.
name|blocks
index|[
name|i
index|]
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
name|SupportLevel
operator|==
literal|1
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
argument_list|<
name|Block
argument_list|>
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<
literal|65
condition|;
name|i
operator|++
control|)
block|{
name|ab
operator|.
name|add
argument_list|(
name|Block
operator|.
name|blocks
index|[
name|i
index|]
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
name|SupportLevel
operator|<=
literal|0
condition|)
block|{
name|AddStandardMinecraftBlocks
argument_list|()
expr_stmt|;
block|}
block|}
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
block|}
end_class

end_unit

