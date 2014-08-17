begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|net
package|;
end_package

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|InetSocketAddress
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|ByteBuffer
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|channels
operator|.
name|SocketChannel
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
name|minecraft
operator|.
name|gui
operator|.
name|ErrorScreen
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
name|net
operator|.
name|NetworkManager
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
name|net
operator|.
name|PacketType
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|NetworkHandler
block|{
specifier|public
specifier|volatile
name|boolean
name|connected
decl_stmt|;
specifier|public
name|SocketChannel
name|channel
decl_stmt|;
specifier|public
name|ByteBuffer
name|in
init|=
name|ByteBuffer
operator|.
name|allocate
argument_list|(
literal|1048576
argument_list|)
decl_stmt|;
specifier|public
name|ByteBuffer
name|out
init|=
name|ByteBuffer
operator|.
name|allocate
argument_list|(
literal|1048576
argument_list|)
decl_stmt|;
specifier|public
name|NetworkManager
name|netManager
decl_stmt|;
specifier|protected
name|int
name|soTrafficClass
init|=
literal|0x04
operator||
literal|0x08
operator||
literal|0x010
decl_stmt|;
specifier|private
name|byte
index|[]
name|stringBytes
init|=
operator|new
name|byte
index|[
literal|64
index|]
decl_stmt|;
specifier|public
name|NetworkHandler
parameter_list|(
name|String
name|ip
parameter_list|,
name|int
name|port
parameter_list|,
name|Minecraft
name|minecraft
parameter_list|)
block|{
try|try
block|{
name|channel
operator|=
name|SocketChannel
operator|.
name|open
argument_list|()
expr_stmt|;
name|channel
operator|.
name|connect
argument_list|(
operator|new
name|InetSocketAddress
argument_list|(
name|ip
argument_list|,
name|port
argument_list|)
argument_list|)
expr_stmt|;
name|channel
operator|.
name|configureBlocking
argument_list|(
literal|false
argument_list|)
expr_stmt|;
name|System
operator|.
name|currentTimeMillis
argument_list|()
expr_stmt|;
comment|/*              * sock = channel.socket(); sock.setTcpNoDelay(true);              * sock.setTrafficClass(soTrafficClass); sock.setKeepAlive(false);              * sock.setReuseAddress(false); sock.setSoTimeout(100);              * sock.getInetAddress().toString();              */
name|connected
operator|=
literal|true
expr_stmt|;
name|in
operator|.
name|clear
argument_list|()
expr_stmt|;
name|out
operator|.
name|clear
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logWarning
argument_list|(
literal|"Error initializing network connection to "
operator|+
name|ip
operator|+
literal|":"
operator|+
name|port
argument_list|,
name|ex
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|ErrorScreen
argument_list|(
literal|"Failed to connect"
argument_list|,
literal|"You failed to connect to the server. It\'s probably down!"
argument_list|)
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|isConnecting
operator|=
literal|false
expr_stmt|;
name|minecraft
operator|.
name|networkManager
operator|=
literal|null
expr_stmt|;
name|netManager
operator|.
name|successful
operator|=
literal|false
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|close
parameter_list|()
block|{
try|try
block|{
if|if
condition|(
name|out
operator|.
name|position
argument_list|()
operator|>
literal|0
condition|)
block|{
name|out
operator|.
name|flip
argument_list|()
expr_stmt|;
name|channel
operator|.
name|write
argument_list|(
name|out
argument_list|)
expr_stmt|;
name|out
operator|.
name|compact
argument_list|()
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
block|}
name|connected
operator|=
literal|false
expr_stmt|;
try|try
block|{
name|channel
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
block|}
name|channel
operator|=
literal|null
expr_stmt|;
block|}
annotation|@
name|SuppressWarnings
argument_list|(
literal|"rawtypes"
argument_list|)
specifier|public
name|Object
name|readObject
parameter_list|(
name|Class
name|obj
parameter_list|)
block|{
if|if
condition|(
operator|!
name|connected
condition|)
block|{
return|return
literal|null
return|;
block|}
else|else
block|{
try|try
block|{
if|if
condition|(
name|obj
operator|==
name|Long
operator|.
name|TYPE
condition|)
block|{
return|return
name|in
operator|.
name|getLong
argument_list|()
return|;
block|}
if|else if
condition|(
name|obj
operator|==
name|Integer
operator|.
name|TYPE
condition|)
block|{
return|return
name|in
operator|.
name|getInt
argument_list|()
return|;
block|}
if|else if
condition|(
name|obj
operator|==
name|Short
operator|.
name|TYPE
condition|)
block|{
return|return
name|in
operator|.
name|getShort
argument_list|()
return|;
block|}
if|else if
condition|(
name|obj
operator|==
name|Byte
operator|.
name|TYPE
condition|)
block|{
return|return
name|in
operator|.
name|get
argument_list|()
return|;
block|}
if|else if
condition|(
name|obj
operator|==
name|Double
operator|.
name|TYPE
condition|)
block|{
return|return
name|in
operator|.
name|getDouble
argument_list|()
return|;
block|}
if|else if
condition|(
name|obj
operator|==
name|Float
operator|.
name|TYPE
condition|)
block|{
return|return
name|in
operator|.
name|getFloat
argument_list|()
return|;
block|}
if|else if
condition|(
name|obj
operator|==
name|String
operator|.
name|class
condition|)
block|{
name|in
operator|.
name|get
argument_list|(
name|stringBytes
argument_list|)
expr_stmt|;
return|return
operator|new
name|String
argument_list|(
name|stringBytes
argument_list|,
literal|"UTF-8"
argument_list|)
operator|.
name|trim
argument_list|()
return|;
block|}
if|else if
condition|(
name|obj
operator|==
name|byte
index|[]
operator|.
name|class
condition|)
block|{
name|byte
index|[]
name|theBytes
init|=
operator|new
name|byte
index|[
literal|1024
index|]
decl_stmt|;
name|in
operator|.
name|get
argument_list|(
name|theBytes
argument_list|)
expr_stmt|;
return|return
name|theBytes
return|;
block|}
else|else
block|{
return|return
literal|null
return|;
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|netManager
operator|.
name|error
argument_list|(
name|e
argument_list|)
expr_stmt|;
return|return
literal|null
return|;
block|}
block|}
block|}
annotation|@
name|SuppressWarnings
argument_list|(
literal|"rawtypes"
argument_list|)
specifier|public
specifier|final
name|void
name|send
parameter_list|(
name|PacketType
name|packetType
parameter_list|,
name|Object
modifier|...
name|obj
parameter_list|)
block|{
if|if
condition|(
name|connected
condition|)
block|{
name|out
operator|.
name|put
argument_list|(
name|packetType
operator|.
name|opcode
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|obj
operator|.
name|length
condition|;
operator|++
name|i
control|)
block|{
name|Class
name|packetClass
init|=
name|packetType
operator|.
name|params
index|[
name|i
index|]
decl_stmt|;
name|Object
name|packetObject
init|=
name|obj
index|[
name|i
index|]
decl_stmt|;
if|if
condition|(
name|connected
condition|)
block|{
try|try
block|{
if|if
condition|(
name|packetClass
operator|==
name|Long
operator|.
name|TYPE
condition|)
block|{
name|out
operator|.
name|putLong
argument_list|(
operator|(
name|Long
operator|)
name|packetObject
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|packetClass
operator|==
name|Integer
operator|.
name|TYPE
condition|)
block|{
name|out
operator|.
name|putInt
argument_list|(
operator|(
name|Integer
operator|)
name|packetObject
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|packetClass
operator|==
name|Short
operator|.
name|TYPE
condition|)
block|{
name|out
operator|.
name|putShort
argument_list|(
operator|(
operator|(
name|Number
operator|)
name|packetObject
operator|)
operator|.
name|shortValue
argument_list|()
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|packetClass
operator|==
name|Byte
operator|.
name|TYPE
condition|)
block|{
name|out
operator|.
name|put
argument_list|(
operator|(
operator|(
name|Number
operator|)
name|packetObject
operator|)
operator|.
name|byteValue
argument_list|()
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|packetClass
operator|==
name|Double
operator|.
name|TYPE
condition|)
block|{
name|out
operator|.
name|putDouble
argument_list|(
operator|(
name|Double
operator|)
name|packetObject
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|packetClass
operator|==
name|Float
operator|.
name|TYPE
condition|)
block|{
name|out
operator|.
name|putFloat
argument_list|(
operator|(
name|Float
operator|)
name|packetObject
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|byte
index|[]
name|bytesToSend
decl_stmt|;
if|if
condition|(
name|packetClass
operator|!=
name|String
operator|.
name|class
condition|)
block|{
if|if
condition|(
name|packetClass
operator|==
name|byte
index|[]
operator|.
name|class
condition|)
block|{
if|if
condition|(
operator|(
name|bytesToSend
operator|=
operator|(
name|byte
index|[]
operator|)
name|packetObject
operator|)
operator|.
name|length
operator|<
literal|1024
condition|)
block|{
name|bytesToSend
operator|=
name|Arrays
operator|.
name|copyOf
argument_list|(
name|bytesToSend
argument_list|,
literal|1024
argument_list|)
expr_stmt|;
block|}
name|out
operator|.
name|put
argument_list|(
name|bytesToSend
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|bytesToSend
operator|=
operator|(
operator|(
name|String
operator|)
name|packetObject
operator|)
operator|.
name|getBytes
argument_list|(
literal|"UTF-8"
argument_list|)
expr_stmt|;
name|Arrays
operator|.
name|fill
argument_list|(
name|stringBytes
argument_list|,
operator|(
name|byte
operator|)
literal|32
argument_list|)
expr_stmt|;
name|int
name|j
decl_stmt|;
for|for
control|(
name|j
operator|=
literal|0
init|;
name|j
operator|<
literal|64
operator|&&
name|j
operator|<
name|bytesToSend
operator|.
name|length
condition|;
operator|++
name|j
control|)
block|{
name|stringBytes
index|[
name|j
index|]
operator|=
name|bytesToSend
index|[
name|j
index|]
expr_stmt|;
block|}
for|for
control|(
name|j
operator|=
name|bytesToSend
operator|.
name|length
init|;
name|j
operator|<
literal|64
condition|;
operator|++
name|j
control|)
block|{
name|stringBytes
index|[
name|j
index|]
operator|=
literal|32
expr_stmt|;
block|}
name|out
operator|.
name|put
argument_list|(
name|stringBytes
argument_list|)
expr_stmt|;
block|}
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|netManager
operator|.
name|error
argument_list|(
name|e
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

