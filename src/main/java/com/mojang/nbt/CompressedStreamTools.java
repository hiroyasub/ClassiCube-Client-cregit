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
name|BufferedInputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ByteArrayInputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ByteArrayOutputStream
import|;
end_import

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
name|DataInputStream
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
name|DataOutputStream
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
name|io
operator|.
name|InputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|OutputStream
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
name|java
operator|.
name|util
operator|.
name|zip
operator|.
name|GZIPOutputStream
import|;
end_import

begin_class
specifier|public
class|class
name|CompressedStreamTools
block|{
comment|/**      * Load the gzipped compound from the InputStream.      */
specifier|public
specifier|static
name|NBTTagCompound
name|readCompressed
parameter_list|(
name|InputStream
name|stream
parameter_list|)
throws|throws
name|IOException
block|{
name|NBTTagCompound
name|compound
decl_stmt|;
try|try
init|(
name|DataInputStream
name|inStream
init|=
operator|new
name|DataInputStream
argument_list|(
operator|new
name|BufferedInputStream
argument_list|(
operator|new
name|GZIPInputStream
argument_list|(
name|stream
argument_list|)
argument_list|)
argument_list|)
init|)
block|{
name|compound
operator|=
name|read
argument_list|(
name|inStream
argument_list|)
expr_stmt|;
block|}
return|return
name|compound
return|;
block|}
comment|/**      * Write the compound, gzipped, to the OutputStream.      */
specifier|public
specifier|static
name|void
name|writeCompressed
parameter_list|(
name|NBTTagCompound
name|tag
parameter_list|,
name|OutputStream
name|stream
parameter_list|)
throws|throws
name|IOException
block|{
try|try
init|(
name|DataOutputStream
name|outStream
init|=
operator|new
name|DataOutputStream
argument_list|(
operator|new
name|GZIPOutputStream
argument_list|(
name|stream
argument_list|)
argument_list|)
init|)
block|{
name|write
argument_list|(
name|tag
argument_list|,
name|outStream
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|static
name|NBTTagCompound
name|decompress
parameter_list|(
name|byte
index|[]
name|buffer
parameter_list|)
throws|throws
name|IOException
block|{
name|NBTTagCompound
name|compound
decl_stmt|;
try|try
init|(
name|DataInputStream
name|inputStream
init|=
operator|new
name|DataInputStream
argument_list|(
operator|new
name|BufferedInputStream
argument_list|(
operator|new
name|GZIPInputStream
argument_list|(
operator|new
name|ByteArrayInputStream
argument_list|(
name|buffer
argument_list|)
argument_list|)
argument_list|)
argument_list|)
init|)
block|{
name|compound
operator|=
name|read
argument_list|(
name|inputStream
argument_list|)
expr_stmt|;
block|}
return|return
name|compound
return|;
block|}
specifier|public
specifier|static
name|byte
index|[]
name|compress
parameter_list|(
name|NBTTagCompound
name|tag
parameter_list|)
throws|throws
name|IOException
block|{
name|ByteArrayOutputStream
name|byteArrayOutputStream
init|=
operator|new
name|ByteArrayOutputStream
argument_list|()
decl_stmt|;
try|try
init|(
name|DataOutputStream
name|outStream
init|=
operator|new
name|DataOutputStream
argument_list|(
operator|new
name|GZIPOutputStream
argument_list|(
name|byteArrayOutputStream
argument_list|)
argument_list|)
init|)
block|{
name|write
argument_list|(
name|tag
argument_list|,
name|outStream
argument_list|)
expr_stmt|;
block|}
return|return
name|byteArrayOutputStream
operator|.
name|toByteArray
argument_list|()
return|;
block|}
specifier|public
specifier|static
name|void
name|safeWrite
parameter_list|(
name|NBTTagCompound
name|compound
parameter_list|,
name|File
name|file
parameter_list|)
throws|throws
name|IOException
block|{
name|File
name|file2
init|=
operator|new
name|File
argument_list|(
name|file
operator|.
name|getAbsolutePath
argument_list|()
operator|+
literal|"_tmp"
argument_list|)
decl_stmt|;
if|if
condition|(
name|file2
operator|.
name|exists
argument_list|()
condition|)
block|{
name|file2
operator|.
name|delete
argument_list|()
expr_stmt|;
block|}
name|write
argument_list|(
name|compound
argument_list|,
name|file2
argument_list|)
expr_stmt|;
if|if
condition|(
name|file
operator|.
name|exists
argument_list|()
condition|)
block|{
name|file
operator|.
name|delete
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|file
operator|.
name|exists
argument_list|()
condition|)
block|{
throw|throw
operator|new
name|IOException
argument_list|(
literal|"Failed to delete "
operator|+
name|file
argument_list|)
throw|;
block|}
else|else
block|{
name|file2
operator|.
name|renameTo
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**      * Reads from a CompressedStream.      */
specifier|public
specifier|static
name|NBTTagCompound
name|read
parameter_list|(
name|DataInput
name|input
parameter_list|)
throws|throws
name|IOException
block|{
name|NBTBase
name|content
init|=
name|NBTBase
operator|.
name|readNamedTag
argument_list|(
name|input
argument_list|)
decl_stmt|;
if|if
condition|(
name|content
operator|instanceof
name|NBTTagCompound
condition|)
block|{
return|return
operator|(
name|NBTTagCompound
operator|)
name|content
return|;
block|}
else|else
block|{
throw|throw
operator|new
name|IOException
argument_list|(
literal|"Root tag must be a named compound tag"
argument_list|)
throw|;
block|}
block|}
specifier|public
specifier|static
name|void
name|write
parameter_list|(
name|NBTTagCompound
name|compound
parameter_list|,
name|DataOutput
name|output
parameter_list|)
throws|throws
name|IOException
block|{
name|NBTBase
operator|.
name|writeNamedTag
argument_list|(
name|compound
argument_list|,
name|output
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|static
name|void
name|write
parameter_list|(
name|NBTTagCompound
name|tag
parameter_list|,
name|File
name|file
parameter_list|)
throws|throws
name|IOException
block|{
try|try
init|(
name|DataOutputStream
name|outStream
init|=
operator|new
name|DataOutputStream
argument_list|(
operator|new
name|FileOutputStream
argument_list|(
name|file
argument_list|)
argument_list|)
init|)
block|{
name|write
argument_list|(
name|tag
argument_list|,
name|outStream
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|static
name|NBTTagCompound
name|read
parameter_list|(
name|File
name|file
parameter_list|)
throws|throws
name|IOException
block|{
if|if
condition|(
operator|!
name|file
operator|.
name|exists
argument_list|()
condition|)
block|{
return|return
literal|null
return|;
block|}
else|else
block|{
name|NBTTagCompound
name|compound
decl_stmt|;
try|try
init|(
name|DataInputStream
name|inStream
init|=
operator|new
name|DataInputStream
argument_list|(
operator|new
name|FileInputStream
argument_list|(
name|file
argument_list|)
argument_list|)
init|)
block|{
name|compound
operator|=
name|read
argument_list|(
name|inStream
argument_list|)
expr_stmt|;
block|}
return|return
name|compound
return|;
block|}
block|}
block|}
end_class

end_unit

