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

begin_class
specifier|public
specifier|final
class|class
name|LeavesBlock
extends|extends
name|LeavesBaseBlock
block|{
specifier|protected
name|LeavesBlock
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
name|super
argument_list|(
name|var1
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|int
name|getDrop
parameter_list|()
block|{
return|return
name|Block
operator|.
name|sapling
operator|.
name|id
return|;
block|}
specifier|public
specifier|final
name|int
name|getDropCount
parameter_list|()
block|{
return|return
name|random
operator|.
name|nextInt
argument_list|(
literal|10
argument_list|)
operator|==
literal|0
condition|?
literal|1
else|:
literal|0
return|;
block|}
block|}
end_class

end_unit

