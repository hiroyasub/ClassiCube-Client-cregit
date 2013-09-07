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
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|ProgressBarDisplay
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|BufferedReader
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
name|DataInputStream
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
name|InputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|InputStreamReader
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ObjectOutputStream
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
name|net
operator|.
name|HttpURLConnection
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|URL
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
specifier|final
class|class
name|LevelIO
block|{
specifier|private
name|ProgressBarDisplay
name|progressBar
decl_stmt|;
specifier|public
name|LevelIO
parameter_list|(
name|ProgressBarDisplay
name|var1
parameter_list|)
block|{
name|this
operator|.
name|progressBar
operator|=
name|var1
expr_stmt|;
block|}
specifier|public
specifier|final
name|boolean
name|save
parameter_list|(
name|Level
name|var1
parameter_list|,
name|File
name|var2
parameter_list|)
block|{
try|try
block|{
name|FileOutputStream
name|fos
init|=
operator|new
name|FileOutputStream
argument_list|(
name|var2
argument_list|)
decl_stmt|;
name|GZIPOutputStream
name|gos
init|=
operator|new
name|GZIPOutputStream
argument_list|(
name|fos
argument_list|)
decl_stmt|;
name|ObjectOutputStream
name|out
init|=
operator|new
name|ObjectOutputStream
argument_list|(
name|gos
argument_list|)
decl_stmt|;
name|out
operator|.
name|writeLong
argument_list|(
name|Level
operator|.
name|serialVersionUID
argument_list|)
expr_stmt|;
name|out
operator|.
name|writeObject
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|out
operator|.
name|close
argument_list|()
expr_stmt|;
name|gos
operator|.
name|close
argument_list|()
expr_stmt|;
name|fos
operator|.
name|close
argument_list|()
expr_stmt|;
return|return
literal|true
return|;
block|}
catch|catch
parameter_list|(
name|Exception
name|var4
parameter_list|)
block|{
name|var4
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Failed!"
argument_list|)
expr_stmt|;
block|}
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|1000L
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|var3
parameter_list|)
block|{
empty_stmt|;
block|}
return|return
literal|false
return|;
block|}
block|}
specifier|public
specifier|final
name|Level
name|load
parameter_list|(
name|File
name|var1
parameter_list|)
block|{
try|try
block|{
name|FileInputStream
name|var5
init|=
operator|new
name|FileInputStream
argument_list|(
name|var1
argument_list|)
decl_stmt|;
name|Level
name|var2
init|=
name|this
operator|.
name|load
argument_list|(
operator|(
name|InputStream
operator|)
name|var5
argument_list|)
decl_stmt|;
name|var5
operator|.
name|close
argument_list|()
expr_stmt|;
return|return
name|var2
return|;
block|}
catch|catch
parameter_list|(
name|Exception
name|var4
parameter_list|)
block|{
name|var4
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Failed!"
argument_list|)
expr_stmt|;
block|}
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|1000L
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|var3
parameter_list|)
block|{
empty_stmt|;
block|}
return|return
literal|null
return|;
block|}
block|}
specifier|public
specifier|final
name|boolean
name|saveOnline
parameter_list|(
name|Level
name|var1
parameter_list|,
name|String
name|var2
parameter_list|,
name|String
name|var3
parameter_list|,
name|String
name|var4
parameter_list|,
name|String
name|var5
parameter_list|,
name|int
name|var6
parameter_list|)
block|{
if|if
condition|(
name|var4
operator|==
literal|null
condition|)
block|{
name|var4
operator|=
literal|""
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setTitle
argument_list|(
literal|"Saving level"
argument_list|)
expr_stmt|;
block|}
try|try
block|{
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Compressing.."
argument_list|)
expr_stmt|;
block|}
name|ByteArrayOutputStream
name|var7
init|=
operator|new
name|ByteArrayOutputStream
argument_list|()
decl_stmt|;
name|save
argument_list|(
name|var1
argument_list|,
operator|(
name|OutputStream
operator|)
name|var7
argument_list|)
expr_stmt|;
name|var7
operator|.
name|close
argument_list|()
expr_stmt|;
name|byte
index|[]
name|var10
init|=
name|var7
operator|.
name|toByteArray
argument_list|()
decl_stmt|;
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Connecting.."
argument_list|)
expr_stmt|;
block|}
name|HttpURLConnection
name|var12
decl_stmt|;
operator|(
name|var12
operator|=
operator|(
name|HttpURLConnection
operator|)
operator|(
operator|new
name|URL
argument_list|(
literal|"http://"
operator|+
name|var2
operator|+
literal|"/level/save.html"
argument_list|)
operator|)
operator|.
name|openConnection
argument_list|()
operator|)
operator|.
name|setDoInput
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|var12
operator|.
name|setDoOutput
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|var12
operator|.
name|setRequestMethod
argument_list|(
literal|"POST"
argument_list|)
expr_stmt|;
name|DataOutputStream
name|var13
decl_stmt|;
operator|(
name|var13
operator|=
operator|new
name|DataOutputStream
argument_list|(
name|var12
operator|.
name|getOutputStream
argument_list|()
argument_list|)
operator|)
operator|.
name|writeUTF
argument_list|(
name|var3
argument_list|)
expr_stmt|;
name|var13
operator|.
name|writeUTF
argument_list|(
name|var4
argument_list|)
expr_stmt|;
name|var13
operator|.
name|writeUTF
argument_list|(
name|var5
argument_list|)
expr_stmt|;
name|var13
operator|.
name|writeByte
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|var13
operator|.
name|writeInt
argument_list|(
name|var10
operator|.
name|length
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Saving.."
argument_list|)
expr_stmt|;
block|}
name|var13
operator|.
name|write
argument_list|(
name|var10
argument_list|)
expr_stmt|;
name|var13
operator|.
name|close
argument_list|()
expr_stmt|;
name|BufferedReader
name|var11
init|=
operator|new
name|BufferedReader
argument_list|(
operator|new
name|InputStreamReader
argument_list|(
name|var12
operator|.
name|getInputStream
argument_list|()
argument_list|)
argument_list|)
decl_stmt|;
name|String
name|s
init|=
name|var11
operator|.
name|readLine
argument_list|()
decl_stmt|;
if|if
condition|(
name|s
operator|==
literal|null
operator|||
operator|!
operator|(
name|s
operator|.
name|equalsIgnoreCase
argument_list|(
literal|"ok"
argument_list|)
operator|)
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Failed: "
operator|+
name|var11
operator|.
name|readLine
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|var11
operator|.
name|close
argument_list|()
expr_stmt|;
name|Thread
operator|.
name|sleep
argument_list|(
literal|1000L
argument_list|)
expr_stmt|;
return|return
literal|false
return|;
block|}
else|else
block|{
name|var11
operator|.
name|close
argument_list|()
expr_stmt|;
return|return
literal|true
return|;
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|var9
parameter_list|)
block|{
name|var9
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Failed!"
argument_list|)
expr_stmt|;
block|}
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|1000L
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|var8
parameter_list|)
block|{
empty_stmt|;
block|}
return|return
literal|false
return|;
block|}
block|}
specifier|public
specifier|final
name|Level
name|loadOnline
parameter_list|(
name|String
name|var1
parameter_list|,
name|String
name|var2
parameter_list|,
name|int
name|var3
parameter_list|)
block|{
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setTitle
argument_list|(
literal|"Loading level"
argument_list|)
expr_stmt|;
block|}
try|try
block|{
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Connecting.."
argument_list|)
expr_stmt|;
block|}
name|HttpURLConnection
name|var6
decl_stmt|;
operator|(
name|var6
operator|=
operator|(
name|HttpURLConnection
operator|)
operator|(
operator|new
name|URL
argument_list|(
literal|"http://"
operator|+
name|var1
operator|+
literal|"/level/load.html?id="
operator|+
name|var3
operator|+
literal|"&user="
operator|+
name|var2
argument_list|)
operator|)
operator|.
name|openConnection
argument_list|()
operator|)
operator|.
name|setDoInput
argument_list|(
literal|true
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Loading.."
argument_list|)
expr_stmt|;
block|}
name|DataInputStream
name|var7
decl_stmt|;
if|if
condition|(
operator|(
name|var7
operator|=
operator|new
name|DataInputStream
argument_list|(
name|var6
operator|.
name|getInputStream
argument_list|()
argument_list|)
operator|)
operator|.
name|readUTF
argument_list|()
operator|.
name|equalsIgnoreCase
argument_list|(
literal|"ok"
argument_list|)
condition|)
block|{
return|return
name|this
operator|.
name|load
argument_list|(
operator|(
name|InputStream
operator|)
name|var7
argument_list|)
return|;
block|}
else|else
block|{
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Failed: "
operator|+
name|var7
operator|.
name|readUTF
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|var7
operator|.
name|close
argument_list|()
expr_stmt|;
name|Thread
operator|.
name|sleep
argument_list|(
literal|1000L
argument_list|)
expr_stmt|;
return|return
literal|null
return|;
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|var5
parameter_list|)
block|{
name|var5
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Failed!"
argument_list|)
expr_stmt|;
block|}
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|3000L
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|var4
parameter_list|)
block|{
empty_stmt|;
block|}
return|return
literal|null
return|;
block|}
block|}
specifier|public
specifier|final
name|Level
name|load
parameter_list|(
name|InputStream
name|var1
parameter_list|)
block|{
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setTitle
argument_list|(
literal|"Loading level"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|progressBar
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Reading.."
argument_list|)
expr_stmt|;
block|}
try|try
block|{
name|DataInputStream
name|var10
decl_stmt|;
if|if
condition|(
operator|(
name|var10
operator|=
operator|new
name|DataInputStream
argument_list|(
operator|new
name|GZIPInputStream
argument_list|(
name|var1
argument_list|)
argument_list|)
operator|)
operator|.
name|readInt
argument_list|()
operator|!=
literal|656127880
condition|)
block|{
return|return
literal|null
return|;
block|}
else|else
block|{
name|byte
name|var12
decl_stmt|;
if|if
condition|(
operator|(
name|var12
operator|=
name|var10
operator|.
name|readByte
argument_list|()
operator|)
operator|>
literal|2
condition|)
block|{
return|return
literal|null
return|;
block|}
if|else if
condition|(
name|var12
operator|<=
literal|1
condition|)
block|{
name|String
name|var14
init|=
name|var10
operator|.
name|readUTF
argument_list|()
decl_stmt|;
name|String
name|var15
init|=
name|var10
operator|.
name|readUTF
argument_list|()
decl_stmt|;
name|long
name|var3
init|=
name|var10
operator|.
name|readLong
argument_list|()
decl_stmt|;
name|short
name|var5
init|=
name|var10
operator|.
name|readShort
argument_list|()
decl_stmt|;
name|short
name|var6
init|=
name|var10
operator|.
name|readShort
argument_list|()
decl_stmt|;
name|short
name|var7
init|=
name|var10
operator|.
name|readShort
argument_list|()
decl_stmt|;
name|byte
index|[]
name|var8
init|=
operator|new
name|byte
index|[
name|var5
operator|*
name|var6
operator|*
name|var7
index|]
decl_stmt|;
name|var10
operator|.
name|readFully
argument_list|(
name|var8
argument_list|)
expr_stmt|;
name|var10
operator|.
name|close
argument_list|()
expr_stmt|;
name|Level
name|var11
decl_stmt|;
operator|(
name|var11
operator|=
operator|new
name|Level
argument_list|()
operator|)
operator|.
name|setData
argument_list|(
name|var5
argument_list|,
name|var7
argument_list|,
name|var6
argument_list|,
name|var8
argument_list|)
expr_stmt|;
name|var11
operator|.
name|name
operator|=
name|var14
expr_stmt|;
name|var11
operator|.
name|creator
operator|=
name|var15
expr_stmt|;
name|var11
operator|.
name|createTime
operator|=
name|var3
expr_stmt|;
return|return
name|var11
return|;
block|}
else|else
block|{
name|Level
name|var2
decl_stmt|;
name|LevelObjectInputStream
name|var13
decl_stmt|;
operator|(
name|var2
operator|=
operator|(
name|Level
operator|)
operator|(
name|var13
operator|=
operator|new
name|LevelObjectInputStream
argument_list|(
name|var10
argument_list|)
operator|)
operator|.
name|readObject
argument_list|()
operator|)
operator|.
name|initTransient
argument_list|()
expr_stmt|;
name|var13
operator|.
name|close
argument_list|()
expr_stmt|;
return|return
name|var2
return|;
block|}
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|var9
parameter_list|)
block|{
name|var9
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Failed to load level: "
operator|+
name|var9
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
return|return
literal|null
return|;
block|}
block|}
specifier|public
specifier|static
name|void
name|save
parameter_list|(
name|Level
name|var0
parameter_list|,
name|OutputStream
name|var1
parameter_list|)
block|{
try|try
block|{
name|DataOutputStream
name|var3
decl_stmt|;
operator|(
name|var3
operator|=
operator|new
name|DataOutputStream
argument_list|(
operator|new
name|GZIPOutputStream
argument_list|(
name|var1
argument_list|)
argument_list|)
operator|)
operator|.
name|writeInt
argument_list|(
literal|656127880
argument_list|)
expr_stmt|;
name|var3
operator|.
name|writeByte
argument_list|(
literal|2
argument_list|)
expr_stmt|;
name|ObjectOutputStream
name|var4
decl_stmt|;
operator|(
name|var4
operator|=
operator|new
name|ObjectOutputStream
argument_list|(
name|var3
argument_list|)
operator|)
operator|.
name|writeObject
argument_list|(
name|var0
argument_list|)
expr_stmt|;
name|var4
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|var2
parameter_list|)
block|{
name|var2
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
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
block|}
end_class

end_unit

