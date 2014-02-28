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
name|DataInputStream
import|;
end_import

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
name|FileInputStream
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
name|IOException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|InputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|zip
operator|.
name|GZIPInputStream
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
name|minecraft
operator|.
name|nbt
operator|.
name|NBTTagCompound
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
name|player
operator|.
name|Player
import|;
end_import

begin_class
specifier|public
class|class
name|LevelLoader
block|{
comment|// Used for recieved map streams from servers
specifier|public
specifier|static
name|byte
index|[]
name|decompress
parameter_list|(
name|InputStream
name|var0
parameter_list|)
block|{
try|try
block|{
name|DataInputStream
name|var3
decl_stmt|;
name|byte
index|[]
name|var1
init|=
operator|new
name|byte
index|[
operator|(
name|var3
operator|=
operator|new
name|DataInputStream
argument_list|(
operator|new
name|GZIPInputStream
argument_list|(
name|var0
argument_list|)
argument_list|)
operator|)
operator|.
name|readInt
argument_list|()
index|]
decl_stmt|;
name|var3
operator|.
name|readFully
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|var3
operator|.
name|close
argument_list|()
expr_stmt|;
return|return
name|var1
return|;
block|}
catch|catch
parameter_list|(
name|Exception
name|var2
parameter_list|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
name|var2
argument_list|)
throw|;
block|}
block|}
name|Level
name|level
decl_stmt|;
specifier|public
name|LevelLoader
parameter_list|()
block|{
block|}
specifier|public
name|Level
name|load
parameter_list|(
name|File
name|fullFilePath
parameter_list|,
name|Player
name|player
parameter_list|)
throws|throws
name|FileNotFoundException
throws|,
name|IOException
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Loading level "
operator|+
name|fullFilePath
operator|.
name|getAbsolutePath
argument_list|()
argument_list|)
expr_stmt|;
name|NBTTagCompound
name|tc
init|=
name|CompressedStreamTools
operator|.
name|readCompressed
argument_list|(
operator|new
name|FileInputStream
argument_list|(
name|fullFilePath
argument_list|)
argument_list|)
decl_stmt|;
name|Level
name|level
init|=
operator|new
name|Level
argument_list|()
decl_stmt|;
name|byte
name|FormatVersion
decl_stmt|;
name|String
name|Name
decl_stmt|;
name|byte
index|[]
name|UUID
decl_stmt|;
name|byte
index|[]
name|blocks
init|=
literal|null
decl_stmt|;
name|short
name|X
init|=
literal|0
decl_stmt|;
name|short
name|Y
init|=
literal|0
decl_stmt|;
name|short
name|Z
init|=
literal|0
decl_stmt|;
name|FormatVersion
operator|=
name|tc
operator|.
name|getByte
argument_list|(
literal|"FormatVersion"
argument_list|)
expr_stmt|;
name|Name
operator|=
name|tc
operator|.
name|getString
argument_list|(
literal|"Name"
argument_list|)
expr_stmt|;
name|UUID
operator|=
name|tc
operator|.
name|getByteArray
argument_list|(
literal|"UUID"
argument_list|)
expr_stmt|;
name|X
operator|=
name|tc
operator|.
name|getShort
argument_list|(
literal|"X"
argument_list|)
expr_stmt|;
name|Y
operator|=
name|tc
operator|.
name|getShort
argument_list|(
literal|"Y"
argument_list|)
expr_stmt|;
name|Z
operator|=
name|tc
operator|.
name|getShort
argument_list|(
literal|"Z"
argument_list|)
expr_stmt|;
name|blocks
operator|=
name|tc
operator|.
name|getByteArray
argument_list|(
literal|"BlockArray"
argument_list|)
expr_stmt|;
name|level
operator|.
name|width
operator|=
name|X
expr_stmt|;
name|level
operator|.
name|length
operator|=
name|Z
expr_stmt|;
name|level
operator|.
name|height
operator|=
name|Y
expr_stmt|;
name|level
operator|.
name|blocks
operator|=
name|blocks
expr_stmt|;
name|NBTTagCompound
name|spawn
init|=
name|tc
operator|.
name|getCompoundTag
argument_list|(
literal|"Spawn"
argument_list|)
decl_stmt|;
name|short
name|x
init|=
name|spawn
operator|.
name|getShort
argument_list|(
literal|"X"
argument_list|)
decl_stmt|;
name|short
name|y
init|=
name|spawn
operator|.
name|getShort
argument_list|(
literal|"Y"
argument_list|)
decl_stmt|;
name|short
name|z
init|=
name|spawn
operator|.
name|getShort
argument_list|(
literal|"Z"
argument_list|)
decl_stmt|;
name|short
name|r
init|=
name|spawn
operator|.
name|getByte
argument_list|(
literal|"H"
argument_list|)
decl_stmt|;
name|short
name|l
init|=
name|spawn
operator|.
name|getByte
argument_list|(
literal|"P"
argument_list|)
decl_stmt|;
name|level
operator|.
name|desiredSpawn
operator|=
operator|new
name|short
index|[]
block|{
name|x
block|,
name|y
block|,
name|z
block|,
name|r
block|,
name|l
block|}
expr_stmt|;
name|boolean
name|debug
init|=
literal|false
decl_stmt|;
if|if
condition|(
name|debug
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"FormatVersion="
operator|+
name|FormatVersion
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Name="
operator|+
name|Name
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"UUID=byte["
operator|+
name|UUID
operator|.
name|length
operator|+
literal|"]"
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"X="
operator|+
name|X
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Y="
operator|+
name|Y
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Z="
operator|+
name|Z
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"blocks=byte["
operator|+
name|blocks
operator|.
name|length
operator|+
literal|"]"
argument_list|)
expr_stmt|;
block|}
return|return
name|level
return|;
block|}
block|}
end_class

end_unit

