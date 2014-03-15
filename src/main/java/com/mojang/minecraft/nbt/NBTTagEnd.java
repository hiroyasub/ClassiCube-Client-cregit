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
name|NBTTagEnd
extends|extends
name|NBTBase
block|{
specifier|public
name|NBTTagEnd
parameter_list|()
block|{
name|super
argument_list|(
literal|null
argument_list|)
expr_stmt|;
block|}
comment|/**      * Read the actual data contents of the tag, implemented in NBT extension classes.      * @param input The input stream to read from.      */
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
block|}
comment|/**      * Write the actual data contents of the tag, implemented in NBT extension classes.      * @param output The output stream to write to.      */
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
block|}
comment|/**      * Gets the type byte for the tag.      * @return byte.      */
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
literal|0
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
literal|"END"
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
name|NBTTagEnd
argument_list|()
return|;
block|}
block|}
end_class

end_unit

