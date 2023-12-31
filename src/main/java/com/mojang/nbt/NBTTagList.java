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

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
import|;
end_import

begin_class
specifier|public
class|class
name|NBTTagList
extends|extends
name|NBTBase
block|{
comment|/**      * The array list containing the tags encapsulated in this list.      */
specifier|private
name|List
argument_list|<
name|NBTBase
argument_list|>
name|tagList
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
comment|/**      * The type byte for the tags in the list - they must all be of the same      * type.      */
specifier|private
name|byte
name|tagType
decl_stmt|;
specifier|public
name|NBTTagList
parameter_list|()
block|{
name|super
argument_list|(
literal|""
argument_list|)
expr_stmt|;
block|}
specifier|public
name|NBTTagList
parameter_list|(
name|String
name|data
parameter_list|)
block|{
name|super
argument_list|(
name|data
argument_list|)
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
if|if
condition|(
operator|!
name|this
operator|.
name|tagList
operator|.
name|isEmpty
argument_list|()
condition|)
block|{
name|this
operator|.
name|tagType
operator|=
name|this
operator|.
name|tagList
operator|.
name|get
argument_list|(
literal|0
argument_list|)
operator|.
name|getId
argument_list|()
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|tagType
operator|=
literal|1
expr_stmt|;
block|}
name|output
operator|.
name|writeByte
argument_list|(
name|this
operator|.
name|tagType
argument_list|)
expr_stmt|;
name|output
operator|.
name|writeInt
argument_list|(
name|this
operator|.
name|tagList
operator|.
name|size
argument_list|()
argument_list|)
expr_stmt|;
for|for
control|(
name|NBTBase
name|aTagList
range|:
name|this
operator|.
name|tagList
control|)
block|{
name|aTagList
operator|.
name|write
argument_list|(
name|output
argument_list|)
expr_stmt|;
block|}
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
name|tagType
operator|=
name|input
operator|.
name|readByte
argument_list|()
expr_stmt|;
name|int
name|i
init|=
name|input
operator|.
name|readInt
argument_list|()
decl_stmt|;
name|this
operator|.
name|tagList
operator|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
expr_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|i
condition|;
operator|++
name|j
control|)
block|{
name|NBTBase
name|nbtbase
init|=
name|NBTBase
operator|.
name|newTag
argument_list|(
name|this
operator|.
name|tagType
argument_list|,
literal|null
argument_list|)
decl_stmt|;
name|nbtbase
operator|.
name|load
argument_list|(
name|input
argument_list|)
expr_stmt|;
name|this
operator|.
name|tagList
operator|.
name|add
argument_list|(
name|nbtbase
argument_list|)
expr_stmt|;
block|}
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
literal|9
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
name|tagList
operator|.
name|size
argument_list|()
operator|+
literal|" entries of type "
operator|+
name|NBTBase
operator|.
name|getTagName
argument_list|(
name|this
operator|.
name|tagType
argument_list|)
return|;
block|}
comment|/**      * Adds the provided tag to the end of the list. There is no check to verify      * this tag is of the same type as any previous tag.      */
specifier|public
name|void
name|appendTag
parameter_list|(
name|NBTBase
name|tag
parameter_list|)
block|{
name|this
operator|.
name|tagType
operator|=
name|tag
operator|.
name|getId
argument_list|()
expr_stmt|;
name|this
operator|.
name|tagList
operator|.
name|add
argument_list|(
name|tag
argument_list|)
expr_stmt|;
block|}
comment|/**      * Removes a tag at the given index.      */
specifier|public
name|NBTBase
name|removeTag
parameter_list|(
name|int
name|index
parameter_list|)
block|{
return|return
name|this
operator|.
name|tagList
operator|.
name|remove
argument_list|(
name|index
argument_list|)
return|;
block|}
comment|/**      * Retrieves the tag at the specified index from the list.      */
specifier|public
name|NBTBase
name|tagAt
parameter_list|(
name|int
name|index
parameter_list|)
block|{
return|return
name|this
operator|.
name|tagList
operator|.
name|get
argument_list|(
name|index
argument_list|)
return|;
block|}
comment|/**      * Returns the number of tags in the list.      */
specifier|public
name|int
name|tagCount
parameter_list|()
block|{
return|return
name|this
operator|.
name|tagList
operator|.
name|size
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
name|NBTTagList
name|finalTagList
init|=
operator|new
name|NBTTagList
argument_list|(
name|this
operator|.
name|getName
argument_list|()
argument_list|)
decl_stmt|;
name|finalTagList
operator|.
name|tagType
operator|=
name|this
operator|.
name|tagType
expr_stmt|;
for|for
control|(
name|NBTBase
name|nextTag
range|:
name|this
operator|.
name|tagList
control|)
block|{
name|NBTBase
name|nextTagByValue
init|=
name|nextTag
operator|.
name|copy
argument_list|()
decl_stmt|;
name|finalTagList
operator|.
name|tagList
operator|.
name|add
argument_list|(
name|nextTagByValue
argument_list|)
expr_stmt|;
block|}
return|return
name|finalTagList
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
name|NBTTagList
name|tempOther
init|=
operator|(
name|NBTTagList
operator|)
name|other
decl_stmt|;
if|if
condition|(
name|this
operator|.
name|tagType
operator|==
name|tempOther
operator|.
name|tagType
condition|)
block|{
return|return
name|this
operator|.
name|tagList
operator|.
name|equals
argument_list|(
name|tempOther
operator|.
name|tagList
argument_list|)
return|;
block|}
block|}
return|return
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
name|this
operator|.
name|tagList
operator|.
name|hashCode
argument_list|()
return|;
block|}
block|}
end_class

end_unit

