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
name|Collection
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashMap
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Iterator
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Map
import|;
end_import

begin_class
specifier|public
class|class
name|NBTTagCompound
extends|extends
name|NBTBase
block|{
comment|/**      * The key-value pairs for the tag. Each key is a UTF string, each value is a tag.      */
specifier|private
name|Map
argument_list|<
name|String
argument_list|,
name|NBTBase
argument_list|>
name|tagMap
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
specifier|public
name|NBTTagCompound
parameter_list|()
block|{
name|super
argument_list|(
literal|""
argument_list|)
expr_stmt|;
block|}
specifier|public
name|NBTTagCompound
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
comment|/**      * Write the actual data contents of the tag, implemented in NBT extension classes      */
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
name|Iterator
argument_list|<
name|NBTBase
argument_list|>
name|iterator
init|=
name|this
operator|.
name|tagMap
operator|.
name|values
argument_list|()
operator|.
name|iterator
argument_list|()
decl_stmt|;
while|while
condition|(
name|iterator
operator|.
name|hasNext
argument_list|()
condition|)
block|{
name|NBTBase
name|nbtbase
init|=
name|iterator
operator|.
name|next
argument_list|()
decl_stmt|;
name|NBTBase
operator|.
name|writeNamedTag
argument_list|(
name|nbtbase
argument_list|,
name|par1DataOutput
argument_list|)
expr_stmt|;
block|}
name|par1DataOutput
operator|.
name|writeByte
argument_list|(
literal|0
argument_list|)
expr_stmt|;
block|}
comment|/**      * Read the actual data contents of the tag, implemented in NBT extension classes      */
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
name|tagMap
operator|.
name|clear
argument_list|()
expr_stmt|;
name|NBTBase
name|nbtbase
decl_stmt|;
while|while
condition|(
operator|(
name|nbtbase
operator|=
name|NBTBase
operator|.
name|readNamedTag
argument_list|(
name|par1DataInput
argument_list|)
operator|)
operator|.
name|getId
argument_list|()
operator|!=
literal|0
condition|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|nbtbase
operator|.
name|getName
argument_list|()
argument_list|,
name|nbtbase
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Returns all the values in the tagMap HashMap.      */
specifier|public
name|Collection
argument_list|<
name|NBTBase
argument_list|>
name|getTags
parameter_list|()
block|{
return|return
name|this
operator|.
name|tagMap
operator|.
name|values
argument_list|()
return|;
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
literal|10
return|;
block|}
comment|/**      * Stores the given tag into the map with the given string key. This is mostly used to store tag lists.      */
specifier|public
name|void
name|setTag
parameter_list|(
name|String
name|name
parameter_list|,
name|NBTBase
name|tag
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
name|tag
operator|.
name|setName
argument_list|(
name|name
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores a new NBTTagByte with the given byte value into the map with the given string key.      */
specifier|public
name|void
name|setByte
parameter_list|(
name|String
name|name
parameter_list|,
name|byte
name|theByte
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
operator|new
name|NBTTagByte
argument_list|(
name|name
argument_list|,
name|theByte
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores a new NBTTagShort with the given short value into the map with the given string key.      */
specifier|public
name|void
name|setShort
parameter_list|(
name|String
name|name
parameter_list|,
name|short
name|theShort
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
operator|new
name|NBTTagShort
argument_list|(
name|name
argument_list|,
name|theShort
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores a new NBTTagInt with the given integer value into the map with the given string key.      */
specifier|public
name|void
name|setInteger
parameter_list|(
name|String
name|name
parameter_list|,
name|int
name|theInt
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
operator|new
name|NBTTagInt
argument_list|(
name|name
argument_list|,
name|theInt
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores a new NBTTagLong with the given long value into the map with the given string key.      */
specifier|public
name|void
name|setLong
parameter_list|(
name|String
name|name
parameter_list|,
name|long
name|theLong
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
operator|new
name|NBTTagLong
argument_list|(
name|name
argument_list|,
name|theLong
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores a new NBTTagFloat with the given float value into the map with the given string key.      */
specifier|public
name|void
name|setFloat
parameter_list|(
name|String
name|name
parameter_list|,
name|float
name|theStr
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
operator|new
name|NBTTagFloat
argument_list|(
name|name
argument_list|,
name|theStr
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores a new NBTTagDouble with the given double value into the map with the given string key.      */
specifier|public
name|void
name|setDouble
parameter_list|(
name|String
name|name
parameter_list|,
name|double
name|theDouble
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
operator|new
name|NBTTagDouble
argument_list|(
name|name
argument_list|,
name|theDouble
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores a new NBTTagString with the given string value into the map with the given string key.      */
specifier|public
name|void
name|setString
parameter_list|(
name|String
name|name
parameter_list|,
name|String
name|theDouble
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
operator|new
name|NBTTagString
argument_list|(
name|name
argument_list|,
name|theDouble
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores a new NBTTagByteArray with the given array as data into the map with the given string key.      */
specifier|public
name|void
name|setByteArray
parameter_list|(
name|String
name|name
parameter_list|,
name|byte
index|[]
name|theByteArray
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
operator|new
name|NBTTagByteArray
argument_list|(
name|name
argument_list|,
name|theByteArray
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores a new NBTTagIntArray with the given array as data into the map with the given string key.      */
specifier|public
name|void
name|setIntArray
parameter_list|(
name|String
name|name
parameter_list|,
name|int
index|[]
name|theIntArray
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
operator|new
name|NBTTagIntArray
argument_list|(
name|name
argument_list|,
name|theIntArray
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores the given NBTTagCompound into the map with the given string key.      */
specifier|public
name|void
name|setCompoundTag
parameter_list|(
name|String
name|name
parameter_list|,
name|NBTTagCompound
name|theCompound
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|put
argument_list|(
name|name
argument_list|,
name|theCompound
operator|.
name|setName
argument_list|(
name|name
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * Stores the given boolean value as a NBTTagByte, storing 1 for true and 0 for false, using the given string key.      */
specifier|public
name|void
name|setBoolean
parameter_list|(
name|String
name|name
parameter_list|,
name|boolean
name|theBool
parameter_list|)
block|{
name|this
operator|.
name|setByte
argument_list|(
name|name
argument_list|,
operator|(
name|byte
operator|)
operator|(
name|theBool
condition|?
literal|1
else|:
literal|0
operator|)
argument_list|)
expr_stmt|;
block|}
comment|/**      * gets a generic tag with the specified name      */
specifier|public
name|NBTBase
name|getTag
parameter_list|(
name|String
name|name
parameter_list|)
block|{
return|return
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
return|;
block|}
comment|/**      * Returns whether the given string has been previously stored as a key in the map.      */
specifier|public
name|boolean
name|hasKey
parameter_list|(
name|String
name|name
parameter_list|)
block|{
return|return
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
return|;
block|}
comment|/**      * Retrieves a byte value using the specified key, or 0 if no such key was stored.      */
specifier|public
name|byte
name|getByte
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
literal|0
else|:
operator|(
operator|(
name|NBTTagByte
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
operator|)
operator|.
name|data
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves a short value using the specified key, or 0 if no such key was stored.      */
specifier|public
name|short
name|getShort
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
literal|0
else|:
operator|(
operator|(
name|NBTTagShort
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
operator|)
operator|.
name|data
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves an integer value using the specified key, or 0 if no such key was stored.      */
specifier|public
name|int
name|getInteger
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
literal|0
else|:
operator|(
operator|(
name|NBTTagInt
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
operator|)
operator|.
name|data
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves a long value using the specified key, or 0 if no such key was stored.      */
specifier|public
name|long
name|getLong
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
literal|0L
else|:
operator|(
operator|(
name|NBTTagLong
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
operator|)
operator|.
name|data
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves a float value using the specified key, or 0 if no such key was stored.      */
specifier|public
name|float
name|getFloat
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
literal|0F
else|:
operator|(
operator|(
name|NBTTagFloat
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
operator|)
operator|.
name|data
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves a double value using the specified key, or 0 if no such key was stored.      */
specifier|public
name|double
name|getDouble
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
literal|0D
else|:
operator|(
operator|(
name|NBTTagDouble
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
operator|)
operator|.
name|data
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves a string value using the specified key, or an empty string if no such key was stored.      */
specifier|public
name|String
name|getString
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
literal|""
else|:
operator|(
operator|(
name|NBTTagString
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
operator|)
operator|.
name|data
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves a byte array using the specified key, or a zero-length array if no such key was stored.      */
specifier|public
name|byte
index|[]
name|getByteArray
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
operator|new
name|byte
index|[
literal|0
index|]
else|:
operator|(
operator|(
name|NBTTagByteArray
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
operator|)
operator|.
name|byteArray
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves an int array using the specified key, or a zero-length array if no such key was stored.      */
specifier|public
name|int
index|[]
name|getIntArray
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
operator|new
name|int
index|[
literal|0
index|]
else|:
operator|(
operator|(
name|NBTTagIntArray
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
operator|)
operator|.
name|intArray
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves a NBTTagCompound subtag matching the specified key, or a new empty NBTTagCompound if no such key was      * stored.      */
specifier|public
name|NBTTagCompound
name|getCompoundTag
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
operator|new
name|NBTTagCompound
argument_list|(
name|name
argument_list|)
else|:
operator|(
name|NBTTagCompound
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves a NBTTagList subtag matching the specified key, or a new empty NBTTagList if no such key was stored.      */
specifier|public
name|NBTTagList
name|getTagList
parameter_list|(
name|String
name|name
parameter_list|)
block|{
try|try
block|{
return|return
operator|!
name|this
operator|.
name|tagMap
operator|.
name|containsKey
argument_list|(
name|name
argument_list|)
condition|?
operator|new
name|NBTTagList
argument_list|(
name|name
argument_list|)
else|:
operator|(
name|NBTTagList
operator|)
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|name
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
name|ClassCastException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
block|}
comment|/**      * Retrieves a boolean value using the specified key, or false if no such key was stored. This uses the getByte      * method.      */
specifier|public
name|boolean
name|getBoolean
parameter_list|(
name|String
name|name
parameter_list|)
block|{
return|return
name|this
operator|.
name|getByte
argument_list|(
name|name
argument_list|)
operator|!=
literal|0
return|;
block|}
comment|/**      * Remove the specified tag.      */
specifier|public
name|void
name|removeTag
parameter_list|(
name|String
name|name
parameter_list|)
block|{
name|this
operator|.
name|tagMap
operator|.
name|remove
argument_list|(
name|name
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
name|String
name|s
init|=
name|this
operator|.
name|getName
argument_list|()
operator|+
literal|":["
decl_stmt|;
name|String
name|s1
decl_stmt|;
for|for
control|(
name|Iterator
argument_list|<
name|String
argument_list|>
name|iter
init|=
name|this
operator|.
name|tagMap
operator|.
name|keySet
argument_list|()
operator|.
name|iterator
argument_list|()
init|;
name|iter
operator|.
name|hasNext
argument_list|()
condition|;
name|s
operator|=
name|s
operator|+
name|s1
operator|+
literal|":"
operator|+
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|s1
argument_list|)
operator|+
literal|","
control|)
block|{
name|s1
operator|=
name|iter
operator|.
name|next
argument_list|()
expr_stmt|;
block|}
return|return
name|s
operator|+
literal|"]"
return|;
block|}
comment|/**      * Return whether this compound has no tags.      */
specifier|public
name|boolean
name|hasNoTags
parameter_list|()
block|{
return|return
name|this
operator|.
name|tagMap
operator|.
name|isEmpty
argument_list|()
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
name|NBTTagCompound
name|finalCompound
init|=
operator|new
name|NBTTagCompound
argument_list|(
name|this
operator|.
name|getName
argument_list|()
argument_list|)
decl_stmt|;
name|Iterator
argument_list|<
name|String
argument_list|>
name|iter
init|=
name|this
operator|.
name|tagMap
operator|.
name|keySet
argument_list|()
operator|.
name|iterator
argument_list|()
decl_stmt|;
while|while
condition|(
name|iter
operator|.
name|hasNext
argument_list|()
condition|)
block|{
name|String
name|s
init|=
name|iter
operator|.
name|next
argument_list|()
decl_stmt|;
name|finalCompound
operator|.
name|setTag
argument_list|(
name|s
argument_list|,
name|this
operator|.
name|tagMap
operator|.
name|get
argument_list|(
name|s
argument_list|)
operator|.
name|copy
argument_list|()
argument_list|)
expr_stmt|;
block|}
return|return
name|finalCompound
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
name|NBTTagCompound
name|tempOther
init|=
operator|(
name|NBTTagCompound
operator|)
name|other
decl_stmt|;
return|return
name|this
operator|.
name|tagMap
operator|.
name|entrySet
argument_list|()
operator|.
name|equals
argument_list|(
name|tempOther
operator|.
name|tagMap
operator|.
name|entrySet
argument_list|()
argument_list|)
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
name|this
operator|.
name|tagMap
operator|.
name|hashCode
argument_list|()
return|;
block|}
comment|/**      * Return the tag map for this compound.      */
specifier|static
name|Map
argument_list|<
name|String
argument_list|,
name|NBTBase
argument_list|>
name|getTagMap
parameter_list|(
name|NBTTagCompound
name|compound
parameter_list|)
block|{
return|return
name|compound
operator|.
name|tagMap
return|;
block|}
block|}
end_class

end_unit

