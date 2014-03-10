begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|nbt
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataInput
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|DataOutput
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|IOException
import|;
end_import

begin_class
specifier|public
class|class
name|NBTTagDouble
extends|extends
name|NBTBase
block|{
comment|/** The double value for the tag. */
specifier|public
name|double
name|data
decl_stmt|;
specifier|public
name|NBTTagDouble
parameter_list|(
name|String
name|name
parameter_list|)
block|{
name|super
argument_list|(
name|name
argument_list|)
expr_stmt|;
block|}
specifier|public
name|NBTTagDouble
parameter_list|(
name|String
name|name
parameter_list|,
name|double
name|data
parameter_list|)
block|{
name|super
argument_list|(
name|name
argument_list|)
expr_stmt|;
name|this
operator|.
name|data
operator|=
name|data
expr_stmt|;
block|}
comment|/**      * Write the actual data contents of the tag, implemented in NBT extension      * classes      */
annotation|@
name|Override
name|void
name|write
parameter_list|(
name|DataOutput
name|par1DataOutput
parameter_list|)
throws|throws
name|IOException
block|{
name|par1DataOutput
operator|.
name|writeDouble
argument_list|(
name|this
operator|.
name|data
argument_list|)
expr_stmt|;
block|}
comment|/**      * Read the actual data contents of the tag, implemented in NBT extension      * classes      */
annotation|@
name|Override
name|void
name|load
parameter_list|(
name|DataInput
name|par1DataInput
parameter_list|)
throws|throws
name|IOException
block|{
name|this
operator|.
name|data
operator|=
name|par1DataInput
operator|.
name|readDouble
argument_list|()
expr_stmt|;
block|}
comment|/**      * Gets the type byte for the tag.      */
annotation|@
name|Override
specifier|public
name|byte
name|getId
parameter_list|()
block|{
return|return
operator|(
name|byte
operator|)
literal|6
return|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
return|return
literal|""
operator|+
name|this
operator|.
name|data
return|;
block|}
comment|/**      * Creates a clone of the tag.      */
annotation|@
name|Override
specifier|public
name|NBTBase
name|copy
parameter_list|()
block|{
return|return
operator|new
name|NBTTagDouble
argument_list|(
name|this
operator|.
name|getName
argument_list|()
argument_list|,
name|this
operator|.
name|data
argument_list|)
return|;
block|}
annotation|@
name|Override
specifier|public
name|boolean
name|equals
parameter_list|(
name|Object
name|other
parameter_list|)
block|{
if|if
condition|(
name|super
operator|.
name|equals
argument_list|(
name|other
argument_list|)
condition|)
block|{
name|NBTTagDouble
name|tempOther
init|=
operator|(
name|NBTTagDouble
operator|)
name|other
decl_stmt|;
return|return
name|this
operator|.
name|data
operator|==
name|tempOther
operator|.
name|data
return|;
block|}
else|else
block|{
return|return
literal|false
return|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|int
name|hashCode
parameter_list|()
block|{
name|long
name|i
init|=
name|Double
operator|.
name|doubleToLongBits
argument_list|(
name|this
operator|.
name|data
argument_list|)
decl_stmt|;
return|return
name|super
operator|.
name|hashCode
argument_list|()
operator|^
operator|(
name|int
operator|)
operator|(
name|i
operator|^
name|i
operator|>>>
literal|32
operator|)
return|;
block|}
block|}
end_class

end_unit

