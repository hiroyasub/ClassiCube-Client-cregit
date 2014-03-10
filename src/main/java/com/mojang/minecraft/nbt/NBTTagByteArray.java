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

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Arrays
import|;
end_import

begin_class
specifier|public
class|class
name|NBTTagByteArray
extends|extends
name|NBTBase
block|{
comment|/** The byte array stored in the tag. */
specifier|public
name|byte
index|[]
name|byteArray
decl_stmt|;
specifier|public
name|NBTTagByteArray
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
name|NBTTagByteArray
parameter_list|(
name|String
name|name
parameter_list|,
name|byte
index|[]
name|byteArrayInput
parameter_list|)
block|{
name|super
argument_list|(
name|name
argument_list|)
expr_stmt|;
name|this
operator|.
name|byteArray
operator|=
name|byteArrayInput
expr_stmt|;
block|}
comment|/** 	 * Write the actual data contents of the tag, implemented in NBT extension 	 * classes 	 */
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
name|writeInt
argument_list|(
name|this
operator|.
name|byteArray
operator|.
name|length
argument_list|)
expr_stmt|;
name|par1DataOutput
operator|.
name|write
argument_list|(
name|this
operator|.
name|byteArray
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Read the actual data contents of the tag, implemented in NBT extension 	 * classes 	 */
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
name|int
name|i
init|=
name|par1DataInput
operator|.
name|readInt
argument_list|()
decl_stmt|;
name|this
operator|.
name|byteArray
operator|=
operator|new
name|byte
index|[
name|i
index|]
expr_stmt|;
name|par1DataInput
operator|.
name|readFully
argument_list|(
name|this
operator|.
name|byteArray
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Gets the type byte for the tag. 	 */
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
literal|7
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
literal|"["
operator|+
name|this
operator|.
name|byteArray
operator|.
name|length
operator|+
literal|" bytes]"
return|;
block|}
comment|/** 	 * Creates a clone of the tag. 	 */
annotation|@
name|Override
specifier|public
name|NBTBase
name|copy
parameter_list|()
block|{
name|byte
index|[]
name|abyte
init|=
operator|new
name|byte
index|[
name|this
operator|.
name|byteArray
operator|.
name|length
index|]
decl_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|this
operator|.
name|byteArray
argument_list|,
literal|0
argument_list|,
name|abyte
argument_list|,
literal|0
argument_list|,
name|this
operator|.
name|byteArray
operator|.
name|length
argument_list|)
expr_stmt|;
return|return
operator|new
name|NBTTagByteArray
argument_list|(
name|this
operator|.
name|getName
argument_list|()
argument_list|,
name|abyte
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
return|return
name|super
operator|.
name|equals
argument_list|(
name|other
argument_list|)
condition|?
name|Arrays
operator|.
name|equals
argument_list|(
name|this
operator|.
name|byteArray
argument_list|,
operator|(
operator|(
name|NBTTagByteArray
operator|)
name|other
operator|)
operator|.
name|byteArray
argument_list|)
else|:
literal|false
return|;
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
name|Arrays
operator|.
name|hashCode
argument_list|(
name|this
operator|.
name|byteArray
argument_list|)
return|;
block|}
block|}
end_class

end_unit

