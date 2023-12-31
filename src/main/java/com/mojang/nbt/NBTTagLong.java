begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
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
name|NBTTagLong
extends|extends
name|NBTBase
block|{
comment|/**      * The long value for the tag.      */
specifier|public
name|long
name|data
decl_stmt|;
specifier|public
name|NBTTagLong
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
name|NBTTagLong
parameter_list|(
name|String
name|name
parameter_list|,
name|long
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
comment|/**      * Write the actual data contents of the tag, implemented in NBT extension classes.      *      * @param output The output stream to write to.      */
annotation|@
name|Override
name|void
name|write
parameter_list|(
name|DataOutput
name|output
parameter_list|)
throws|throws
name|IOException
block|{
name|output
operator|.
name|writeLong
argument_list|(
name|this
operator|.
name|data
argument_list|)
expr_stmt|;
block|}
comment|/**      * Read the actual data contents of the tag, implemented in NBT extension classes.      *      * @param input The input stream to read from.      */
annotation|@
name|Override
name|void
name|load
parameter_list|(
name|DataInput
name|input
parameter_list|)
throws|throws
name|IOException
block|{
name|this
operator|.
name|data
operator|=
name|input
operator|.
name|readLong
argument_list|()
expr_stmt|;
block|}
comment|/**      * Gets the type byte for the tag.      *      * @return byte      */
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
literal|4
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
name|NBTTagLong
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
name|NBTTagLong
name|tempOther
init|=
operator|(
name|NBTTagLong
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
name|this
operator|.
name|data
operator|^
name|this
operator|.
name|data
operator|>>>
literal|32
operator|)
return|;
block|}
block|}
end_class

end_unit

