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
specifier|abstract
class|class
name|NBTBase
block|{
specifier|public
specifier|static
specifier|final
name|String
index|[]
name|NBTTypes
init|=
operator|new
name|String
index|[]
block|{
literal|"END"
block|,
literal|"BYTE"
block|,
literal|"SHORT"
block|,
literal|"INT"
block|,
literal|"LONG"
block|,
literal|"FLOAT"
block|,
literal|"DOUBLE"
block|,
literal|"BYTE[]"
block|,
literal|"STRING"
block|,
literal|"LIST"
block|,
literal|"COMPOUND"
block|,
literal|"INT[]"
block|}
decl_stmt|;
comment|/** The UTF string key used to lookup values. */
specifier|private
name|String
name|name
decl_stmt|;
comment|/**      * Write the actual data contents of the tag, implemented in NBT extension classes      */
specifier|abstract
name|void
name|write
parameter_list|(
name|DataOutput
name|dataoutput
parameter_list|)
throws|throws
name|IOException
function_decl|;
comment|/**      * Read the actual data contents of the tag, implemented in NBT extension classes      */
specifier|abstract
name|void
name|load
parameter_list|(
name|DataInput
name|datainput
parameter_list|)
throws|throws
name|IOException
function_decl|;
comment|/**      * Gets the type byte for the tag.      */
specifier|public
specifier|abstract
name|byte
name|getId
parameter_list|()
function_decl|;
specifier|protected
name|NBTBase
parameter_list|(
name|String
name|name
parameter_list|)
block|{
if|if
condition|(
name|name
operator|==
literal|null
condition|)
block|{
name|this
operator|.
name|name
operator|=
literal|""
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|name
operator|=
name|name
expr_stmt|;
block|}
block|}
comment|/**      * Sets the name for this tag and returns this for convenience.      */
specifier|public
name|NBTBase
name|setName
parameter_list|(
name|String
name|name
parameter_list|)
block|{
if|if
condition|(
name|name
operator|==
literal|null
condition|)
block|{
name|this
operator|.
name|name
operator|=
literal|""
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|name
operator|=
name|name
expr_stmt|;
block|}
return|return
name|this
return|;
block|}
comment|/**      * Gets the name corresponding to the tag, or an empty string if none set.      */
specifier|public
name|String
name|getName
parameter_list|()
block|{
return|return
name|this
operator|.
name|name
operator|==
literal|null
condition|?
literal|""
else|:
name|this
operator|.
name|name
return|;
block|}
comment|/**      * Reads and returns a tag from the given DataInput, or the End tag if no tag could be read.      */
specifier|public
specifier|static
name|NBTBase
name|readNamedTag
parameter_list|(
name|DataInput
name|par0DataInput
parameter_list|)
throws|throws
name|IOException
block|{
name|byte
name|b0
init|=
name|par0DataInput
operator|.
name|readByte
argument_list|()
decl_stmt|;
if|if
condition|(
name|b0
operator|==
literal|0
condition|)
block|{
return|return
operator|new
name|NBTTagEnd
argument_list|()
return|;
block|}
else|else
block|{
name|String
name|s
init|=
name|par0DataInput
operator|.
name|readUTF
argument_list|()
decl_stmt|;
name|NBTBase
name|nbtbase
init|=
name|newTag
argument_list|(
name|b0
argument_list|,
name|s
argument_list|)
decl_stmt|;
try|try
block|{
name|nbtbase
operator|.
name|load
argument_list|(
name|par0DataInput
argument_list|)
expr_stmt|;
return|return
name|nbtbase
return|;
block|}
catch|catch
parameter_list|(
name|IOException
name|ioexception
parameter_list|)
block|{
throw|throw
name|ioexception
throw|;
block|}
block|}
block|}
comment|/**      * Writes the specified tag to the given DataOutput, writing the type byte, the UTF string key and then calling the      * tag to write its data.      */
specifier|public
specifier|static
name|void
name|writeNamedTag
parameter_list|(
name|NBTBase
name|par0NBTBase
parameter_list|,
name|DataOutput
name|par1DataOutput
parameter_list|)
throws|throws
name|IOException
block|{
name|par1DataOutput
operator|.
name|writeByte
argument_list|(
name|par0NBTBase
operator|.
name|getId
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|par0NBTBase
operator|.
name|getId
argument_list|()
operator|!=
literal|0
condition|)
block|{
name|par1DataOutput
operator|.
name|writeUTF
argument_list|(
name|par0NBTBase
operator|.
name|getName
argument_list|()
argument_list|)
expr_stmt|;
name|par0NBTBase
operator|.
name|write
argument_list|(
name|par1DataOutput
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Creates and returns a new tag of the specified type, or null if invalid.      */
specifier|public
specifier|static
name|NBTBase
name|newTag
parameter_list|(
name|byte
name|typeID
parameter_list|,
name|String
name|name
parameter_list|)
block|{
switch|switch
condition|(
name|tagTypeID
condition|)
block|{
case|case
literal|0
case|:
return|return
operator|new
name|NBTTagEnd
argument_list|()
return|;
case|case
literal|1
case|:
return|return
operator|new
name|NBTTagByte
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|2
case|:
return|return
operator|new
name|NBTTagShort
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|3
case|:
return|return
operator|new
name|NBTTagInt
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|4
case|:
return|return
operator|new
name|NBTTagLong
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|5
case|:
return|return
operator|new
name|NBTTagFloat
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|6
case|:
return|return
operator|new
name|NBTTagDouble
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|7
case|:
return|return
operator|new
name|NBTTagByteArray
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|8
case|:
return|return
operator|new
name|NBTTagString
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|9
case|:
return|return
operator|new
name|NBTTagList
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|10
case|:
return|return
operator|new
name|NBTTagCompound
argument_list|(
name|name
argument_list|)
return|;
case|case
literal|11
case|:
return|return
operator|new
name|NBTTagIntArray
argument_list|(
name|name
argument_list|)
return|;
default|default:
return|return
literal|null
return|;
block|}
block|}
comment|/**      * Returns the string name of a tag with the specified type, or 'UNKNOWN' if invalid.      */
specifier|public
specifier|static
name|String
name|getname
parameter_list|(
name|byte
name|typeID
parameter_list|)
block|{
switch|switch
condition|(
name|tagTypeID
condition|)
block|{
case|case
literal|0
case|:
return|return
literal|"TAG_End"
return|;
case|case
literal|1
case|:
return|return
literal|"TAG_Byte"
return|;
case|case
literal|2
case|:
return|return
literal|"TAG_Short"
return|;
case|case
literal|3
case|:
return|return
literal|"TAG_Int"
return|;
case|case
literal|4
case|:
return|return
literal|"TAG_Long"
return|;
case|case
literal|5
case|:
return|return
literal|"TAG_Float"
return|;
case|case
literal|6
case|:
return|return
literal|"TAG_Double"
return|;
case|case
literal|7
case|:
return|return
literal|"TAG_Byte_Array"
return|;
case|case
literal|8
case|:
return|return
literal|"TAG_String"
return|;
case|case
literal|9
case|:
return|return
literal|"TAG_List"
return|;
case|case
literal|10
case|:
return|return
literal|"TAG_Compound"
return|;
case|case
literal|11
case|:
return|return
literal|"TAG_Int_Array"
return|;
default|default:
return|return
literal|"UNKNOWN"
return|;
block|}
block|}
comment|/**      * Creates a clone of the tag.      */
specifier|public
specifier|abstract
name|NBTBase
name|copy
parameter_list|()
function_decl|;
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
operator|!
operator|(
name|other
operator|instanceof
name|NBTBase
operator|)
condition|)
block|{
return|return
literal|false
return|;
block|}
else|else
block|{
name|NBTBase
name|tempOther
init|=
operator|(
name|NBTBase
operator|)
name|other
decl_stmt|;
return|return
name|this
operator|.
name|getId
argument_list|()
operator|!=
name|tempOther
operator|.
name|getId
argument_list|()
condition|?
literal|false
else|:
operator|(
operator|(
name|this
operator|.
name|name
operator|!=
literal|null
operator|||
name|tempOther
operator|.
name|name
operator|==
literal|null
operator|)
operator|&&
operator|(
name|this
operator|.
name|name
operator|==
literal|null
operator|||
name|tempOther
operator|.
name|name
operator|!=
literal|null
operator|)
condition|?
name|this
operator|.
name|name
operator|==
literal|null
operator|||
name|this
operator|.
name|name
operator|.
name|equals
argument_list|(
name|tempOther
operator|.
name|name
argument_list|)
else|:
literal|false
operator|)
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
name|this
operator|.
name|name
operator|.
name|hashCode
argument_list|()
operator|^
name|this
operator|.
name|getId
argument_list|()
return|;
block|}
block|}
end_class

end_unit

