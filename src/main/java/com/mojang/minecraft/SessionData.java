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
name|Arrays
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
name|SetAllowedBlocks
argument_list|(
literal|true
argument_list|,
literal|null
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|static
name|void
name|SetAllowedBlocks
parameter_list|(
name|boolean
name|AllBlocks
parameter_list|,
name|ArrayList
name|disallowedBlocks
parameter_list|)
block|{
if|if
condition|(
name|AllBlocks
condition|)
block|{
name|allowedBlocks
operator|=
name|Arrays
operator|.
name|asList
argument_list|(
name|Block
operator|.
name|blocks
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|List
argument_list|<
name|Block
argument_list|>
name|temp
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
literal|0
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
if|if
condition|(
operator|!
name|disallowedBlocks
operator|.
name|contains
argument_list|(
name|i
argument_list|)
condition|)
block|{
name|temp
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
block|}
name|allowedBlocks
operator|=
name|temp
expr_stmt|;
block|}
block|}
specifier|public
specifier|static
name|void
name|SetAllowedBlocks
parameter_list|(
name|byte
index|[]
name|disallowedBlocks
parameter_list|)
block|{
name|ArrayList
name|temp
init|=
operator|new
name|ArrayList
argument_list|()
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
name|disallowedBlocks
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|temp
operator|.
name|add
argument_list|(
name|disallowedBlocks
index|[
name|i
index|]
argument_list|)
expr_stmt|;
block|}
name|SetAllowedBlocks
argument_list|(
literal|false
argument_list|,
name|temp
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

