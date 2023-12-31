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
name|OreBlock
extends|extends
name|Block
block|{
specifier|public
name|OreBlock
parameter_list|(
name|int
name|id
parameter_list|)
block|{
name|super
argument_list|(
name|id
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|int
name|getDrop
parameter_list|()
block|{
return|return
name|this
operator|==
name|COAL_ORE
condition|?
name|SLAB
operator|.
name|id
else|:
name|this
operator|==
name|GOLD_ORE
condition|?
name|GOLD_BLOCK
operator|.
name|id
else|:
name|this
operator|==
name|IRON_ORE
condition|?
name|IRON_BLOCK
operator|.
name|id
else|:
name|id
return|;
block|}
annotation|@
name|Override
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
literal|3
argument_list|)
operator|+
literal|1
return|;
block|}
block|}
end_class

end_unit

