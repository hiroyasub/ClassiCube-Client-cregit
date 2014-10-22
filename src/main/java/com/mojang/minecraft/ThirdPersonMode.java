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

begin_enum
specifier|public
enum|enum
name|ThirdPersonMode
block|{
name|NONE
block|,
name|BACK_FACING
block|,
name|FRONT_FACING
block|{
annotation|@
name|Override
specifier|public
name|ThirdPersonMode
name|next
parameter_list|()
block|{
return|return
name|values
argument_list|()
index|[
literal|0
index|]
return|;
comment|// rollover to the first element
block|}
block|;     }
block|;
specifier|public
name|ThirdPersonMode
name|next
parameter_list|()
block|{
comment|// No bounds checking required here, because the last instance overrides
return|return
name|values
argument_list|()
index|[
name|ordinal
argument_list|()
operator|+
literal|1
index|]
return|;
block|}
block|}
end_enum

end_unit

