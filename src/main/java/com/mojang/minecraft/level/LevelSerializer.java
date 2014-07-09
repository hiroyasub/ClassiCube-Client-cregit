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
package|;
end_package

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|File
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|FileNotFoundException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|FileOutputStream
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
name|UUID
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|LogUtil
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|Minecraft
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|nbt
operator|.
name|CompressedStreamTools
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|nbt
operator|.
name|NBTTagCompound
import|;
end_import

begin_class
specifier|public
class|class
name|LevelSerializer
block|{
name|Level
name|level
decl_stmt|;
name|String
name|EXT
init|=
literal|".cw"
decl_stmt|;
specifier|public
name|LevelSerializer
parameter_list|(
name|Level
name|level
parameter_list|)
block|{
name|this
operator|.
name|level
operator|=
name|level
expr_stmt|;
block|}
specifier|private
specifier|static
name|byte
index|[]
name|asByteArray
parameter_list|(
name|UUID
name|uuid
parameter_list|)
block|{
name|long
name|msb
init|=
name|uuid
operator|.
name|getMostSignificantBits
argument_list|()
decl_stmt|;
name|long
name|lsb
init|=
name|uuid
operator|.
name|getLeastSignificantBits
argument_list|()
decl_stmt|;
name|byte
index|[]
name|buffer
init|=
operator|new
name|byte
index|[
literal|16
index|]
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
literal|8
condition|;
name|i
operator|++
control|)
block|{
name|buffer
index|[
name|i
index|]
operator|=
operator|(
name|byte
operator|)
operator|(
name|msb
operator|>>>
literal|8
operator|*
operator|(
literal|7
operator|-
name|i
operator|)
operator|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|8
init|;
name|i
operator|<
literal|16
condition|;
name|i
operator|++
control|)
block|{
name|buffer
index|[
name|i
index|]
operator|=
operator|(
name|byte
operator|)
operator|(
name|lsb
operator|>>>
literal|8
operator|*
operator|(
literal|7
operator|-
name|i
operator|)
operator|)
expr_stmt|;
block|}
return|return
name|buffer
return|;
block|}
name|void
name|save
parameter_list|(
name|File
name|fullFilePath
parameter_list|)
throws|throws
name|FileNotFoundException
throws|,
name|IOException
throws|,
name|Exception
block|{
name|LogUtil
operator|.
name|logInfo
argument_list|(
literal|"Saving level "
operator|+
name|fullFilePath
operator|.
name|getAbsolutePath
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|level
operator|==
literal|null
condition|)
block|{
comment|// TODO Don't throw generic exception
throw|throw
operator|new
name|Exception
argument_list|(
literal|"level"
argument_list|)
throw|;
block|}
name|NBTTagCompound
name|master
init|=
operator|new
name|NBTTagCompound
argument_list|(
literal|"ClassicWorld"
argument_list|)
decl_stmt|;
name|master
operator|.
name|setByte
argument_list|(
literal|"FormatVersion"
argument_list|,
operator|(
name|byte
operator|)
literal|1
argument_list|)
expr_stmt|;
name|master
operator|.
name|setString
argument_list|(
literal|"Name"
argument_list|,
literal|"SinglePlayerMap"
argument_list|)
expr_stmt|;
name|master
operator|.
name|setByteArray
argument_list|(
literal|"UUID"
argument_list|,
name|asByteArray
argument_list|(
name|UUID
operator|.
name|randomUUID
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
name|master
operator|.
name|setShort
argument_list|(
literal|"X"
argument_list|,
operator|(
name|short
operator|)
name|level
operator|.
name|width
argument_list|)
expr_stmt|;
name|master
operator|.
name|setShort
argument_list|(
literal|"Y"
argument_list|,
operator|(
name|short
operator|)
name|level
operator|.
name|height
argument_list|)
expr_stmt|;
name|master
operator|.
name|setShort
argument_list|(
literal|"Z"
argument_list|,
operator|(
name|short
operator|)
name|level
operator|.
name|length
argument_list|)
expr_stmt|;
name|master
operator|.
name|setByteArray
argument_list|(
literal|"BlockArray"
argument_list|,
name|level
operator|.
name|blocks
argument_list|)
expr_stmt|;
name|NBTTagCompound
name|createdBy
init|=
operator|new
name|NBTTagCompound
argument_list|(
literal|"CreatedBy"
argument_list|)
decl_stmt|;
name|createdBy
operator|.
name|setString
argument_list|(
literal|"Service"
argument_list|,
literal|"ClassiCube"
argument_list|)
expr_stmt|;
name|createdBy
operator|.
name|setString
argument_list|(
literal|"Username"
argument_list|,
literal|"ClassiCube User"
argument_list|)
expr_stmt|;
name|master
operator|.
name|setCompoundTag
argument_list|(
literal|"CreatedBy"
argument_list|,
name|createdBy
argument_list|)
expr_stmt|;
name|NBTTagCompound
name|spawn
init|=
operator|new
name|NBTTagCompound
argument_list|(
literal|"Spawn"
argument_list|)
decl_stmt|;
name|spawn
operator|.
name|setShort
argument_list|(
literal|"X"
argument_list|,
operator|(
name|short
operator|)
name|level
operator|.
name|player
operator|.
name|x
argument_list|)
expr_stmt|;
name|spawn
operator|.
name|setShort
argument_list|(
literal|"Y"
argument_list|,
operator|(
name|short
operator|)
name|level
operator|.
name|player
operator|.
name|y
argument_list|)
expr_stmt|;
name|spawn
operator|.
name|setShort
argument_list|(
literal|"Z"
argument_list|,
operator|(
name|short
operator|)
name|level
operator|.
name|player
operator|.
name|z
argument_list|)
expr_stmt|;
name|spawn
operator|.
name|setByte
argument_list|(
literal|"H"
argument_list|,
operator|(
name|byte
operator|)
name|level
operator|.
name|player
operator|.
name|xRot
argument_list|)
expr_stmt|;
name|spawn
operator|.
name|setByte
argument_list|(
literal|"P"
argument_list|,
operator|(
name|byte
operator|)
name|level
operator|.
name|player
operator|.
name|yRot
argument_list|)
expr_stmt|;
name|master
operator|.
name|setCompoundTag
argument_list|(
literal|"Spawn"
argument_list|,
name|spawn
argument_list|)
expr_stmt|;
comment|// Metadata tag is required by ClassicWorld specs, even if empty.
name|master
operator|.
name|setCompoundTag
argument_list|(
literal|"Metadata"
argument_list|,
operator|new
name|NBTTagCompound
argument_list|(
literal|"Metadata"
argument_list|)
argument_list|;
name|String
name|fileName
operator|=
name|fullFilePath
operator|+
operator|(
name|fullFilePath
operator|.
name|getAbsolutePath
argument_list|()
operator|.
name|endsWith
argument_list|(
name|EXT
argument_list|)
condition|?
literal|""
else|:
name|EXT
operator|)
argument_list|;         try
operator|(
name|FileOutputStream
name|fs
operator|=
operator|new
name|FileOutputStream
argument_list|(
operator|new
name|File
argument_list|(
name|fileName
argument_list|)
argument_list|)
operator|)
block|{
name|CompressedStreamTools
operator|.
name|writeCompressed
argument_list|(
name|master
argument_list|,
name|fs
argument_list|)
block|;         }
block|}
specifier|public
name|void
name|saveMap
parameter_list|(
name|File
name|file
parameter_list|)
throws|throws
name|FileNotFoundException
throws|,
name|IOException
throws|,
name|Exception
block|{
name|save
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|saveMap
parameter_list|(
name|String
name|Name
parameter_list|)
throws|throws
name|FileNotFoundException
throws|,
name|IOException
throws|,
name|Exception
block|{
name|save
argument_list|(
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|getMinecraftDirectory
argument_list|()
argument_list|,
name|Name
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

