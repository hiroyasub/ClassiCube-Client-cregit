begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|mob
operator|.
name|ai
package|;
end_package

begin_class
specifier|public
class|class
name|JumpAttackAI
extends|extends
name|BasicAttackAI
block|{
specifier|public
name|JumpAttackAI
parameter_list|()
block|{
comment|// this.runSpeed *= 0.8F;
block|}
annotation|@
name|Override
specifier|protected
name|void
name|jumpFromGround
parameter_list|()
block|{
if|if
condition|(
name|attackTarget
operator|==
literal|null
condition|)
block|{
name|super
operator|.
name|jumpFromGround
argument_list|()
expr_stmt|;
block|}
else|else
block|{
name|mob
operator|.
name|xd
operator|=
literal|0F
expr_stmt|;
name|mob
operator|.
name|zd
operator|=
literal|0F
expr_stmt|;
name|mob
operator|.
name|moveRelative
argument_list|(
literal|0F
argument_list|,
literal|1F
argument_list|,
literal|0.6F
argument_list|)
expr_stmt|;
name|mob
operator|.
name|yd
operator|=
literal|0.5F
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

