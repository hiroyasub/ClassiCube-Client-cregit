begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|player
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
name|mob
operator|.
name|ai
operator|.
name|BasicAI
import|;
end_import

begin_comment
comment|// PlayerAI
end_comment

begin_class
specifier|public
class|class
name|Player$1
extends|extends
name|BasicAI
block|{
specifier|public
name|Player$1
parameter_list|(
name|Player
name|player
parameter_list|)
block|{
name|this
operator|.
name|player
operator|=
name|player
expr_stmt|;
block|}
annotation|@
name|Override
specifier|protected
name|void
name|update
parameter_list|()
block|{
comment|//this.jumping = player.input.jumping;
comment|//this.running = player.input.running;
comment|//this.xxa = player.input.xxa;
comment|//this.yya = player.input.yya;
block|}
specifier|public
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|0L
decl_stmt|;
specifier|private
name|Player
name|player
decl_stmt|;
block|}
end_class

end_unit

