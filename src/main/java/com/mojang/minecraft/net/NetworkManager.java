begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|net
package|;
end_package

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
name|HashMap
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
name|nio
operator|.
name|charset
operator|.
name|StandardCharsets
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
name|HashSet
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Set
import|;
end_import

begin_class
specifier|public
class|class
name|NetworkManager
block|{
specifier|public
specifier|static
specifier|final
name|int
name|MAX_PACKETS_PER_TICK
init|=
literal|100
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|BUFFER_SIZE
init|=
literal|1048576
decl_stmt|;
specifier|public
specifier|final
name|Set
argument_list|<
name|ProtocolExtension
argument_list|>
name|enabledExtensions
init|=
operator|new
name|HashSet
argument_list|<>
argument_list|()
decl_stmt|;
specifier|private
specifier|final
name|Minecraft
name|minecraft
decl_stmt|;
specifier|private
specifier|volatile
name|boolean
name|connected
decl_stmt|;
specifier|public
name|boolean
name|handshakeSent
init|=
literal|false
decl_stmt|;
specifier|public
name|SocketChannel
name|channel
decl_stmt|;
specifier|public
specifier|final
name|ByteBuffer
name|in
init|=
name|ByteBuffer
operator|.
name|allocate
argument_list|(
name|BUFFER_SIZE
argument_list|)
decl_stmt|;
specifier|public
specifier|final
name|ByteBuffer
name|out
init|=
name|ByteBuffer
operator|.
name|allocate
argument_list|(
name|BUFFER_SIZE
argument_list|)
decl_stmt|;
specifier|private
specifier|final
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
name|ByteArrayOutputStream
name|levelData
decl_stmt|;
specifier|public
name|boolean
name|levelLoaded
init|=
literal|false
decl_stmt|;
specifier|private
specifier|final
name|HashMap
argument_list|<
name|Byte
argument_list|,
name|NetworkPlayer
argument_list|>
name|players
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
specifier|public
name|NetworkManager
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|)
block|{
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
block|}
specifier|public
name|void
name|beginConnect
parameter_list|(
name|String
name|server
parameter_list|,
name|int
name|port
parameter_list|)
block|{
name|minecraft
operator|.
name|isConnecting
operator|=
literal|true
expr_stmt|;
operator|new
name|ServerConnectThread
argument_list|(
name|this
argument_list|,
name|server
argument_list|,
name|port
argument_list|,
name|minecraft
argument_list|)
operator|.
name|start
argument_list|()
expr_stmt|;
block|}
comment|// Called from ServerConnectThread
specifier|public
name|void
name|connect
parameter_list|(
name|String
name|ip
parameter_list|,
name|int
name|port
parameter_list|)
throws|throws
name|IOException
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
comment|/*          * sock = channel.socket(); sock.setTcpNoDelay(true);          * sock.setTrafficClass(soTrafficClass); sock.setKeepAlive(false);          * sock.setReuseAddress(false); sock.setSoTimeout(100);          * sock.getInetAddress().toString();          */
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
name|connected
operator|=
literal|true
expr_stmt|;
block|}
specifier|public
name|boolean
name|isConnected
parameter_list|()
block|{
return|return
name|connected
return|;
block|}
specifier|public
name|void
name|setConnected
parameter_list|(
name|boolean
name|value
parameter_list|)
block|{
name|connected
operator|=
name|value
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
name|String
name|rawStr
init|=
operator|new
name|String
argument_list|(
name|stringBytes
argument_list|,
name|StandardCharsets
operator|.
name|US_ASCII
argument_list|)
decl_stmt|;
if|if
condition|(
name|isExtEnabled
argument_list|(
name|ProtocolExtension
operator|.
name|EMOTE_FIX
argument_list|)
condition|)
block|{
comment|// In EmoteFix mode: trim spaces and nulls only
return|return
name|trimSpacesAndNulls
argument_list|(
name|rawStr
argument_list|)
return|;
block|}
else|else
block|{
comment|// In legacy mode: trim all control characters and spaces too.
return|return
name|rawStr
operator|.
name|trim
argument_list|()
return|;
block|}
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
comment|// Flips the buffer and writes out all pending data.
specifier|public
name|void
name|writeOut
parameter_list|()
throws|throws
name|IOException
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
specifier|public
name|void
name|sendBlockChange
parameter_list|(
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|,
name|int
name|z
parameter_list|,
name|int
name|mode
parameter_list|,
name|int
name|block
parameter_list|)
block|{
name|send
argument_list|(
name|PacketType
operator|.
name|PLAYER_SET_BLOCK
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|mode
argument_list|,
name|block
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|addPlayer
parameter_list|(
name|byte
name|playerId
parameter_list|,
name|NetworkPlayer
name|newPlayer
parameter_list|)
block|{
if|if
condition|(
name|newPlayer
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|IllegalArgumentException
argument_list|(
literal|"newPlayer is null"
argument_list|)
throw|;
block|}
name|players
operator|.
name|put
argument_list|(
name|playerId
argument_list|,
name|newPlayer
argument_list|)
expr_stmt|;
block|}
comment|// TODO: move player list management into a separate class?
specifier|public
name|boolean
name|hasPlayers
parameter_list|()
block|{
return|return
operator|!
name|players
operator|.
name|isEmpty
argument_list|()
return|;
block|}
specifier|public
name|NetworkPlayer
name|getPlayer
parameter_list|(
name|byte
name|playerId
parameter_list|)
block|{
return|return
name|players
operator|.
name|get
argument_list|(
name|playerId
argument_list|)
return|;
block|}
specifier|public
name|NetworkPlayer
name|removePlayer
parameter_list|(
name|byte
name|playerId
parameter_list|)
block|{
return|return
name|players
operator|.
name|remove
argument_list|(
name|playerId
argument_list|)
return|;
block|}
specifier|public
name|Collection
argument_list|<
name|NetworkPlayer
argument_list|>
name|getPlayers
parameter_list|()
block|{
return|return
name|players
operator|.
name|values
argument_list|()
return|;
block|}
specifier|public
name|List
argument_list|<
name|String
argument_list|>
name|getPlayerNames
parameter_list|()
block|{
name|ArrayList
argument_list|<
name|String
argument_list|>
name|list
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|list
operator|.
name|add
argument_list|(
name|minecraft
operator|.
name|session
operator|.
name|username
argument_list|)
expr_stmt|;
for|for
control|(
name|NetworkPlayer
name|networkPlayer
range|:
name|players
operator|.
name|values
argument_list|()
control|)
block|{
name|list
operator|.
name|add
argument_list|(
name|networkPlayer
operator|.
name|name
argument_list|)
expr_stmt|;
block|}
return|return
name|list
return|;
block|}
specifier|private
name|void
name|error
parameter_list|(
name|Exception
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logWarning
argument_list|(
literal|"Network communication error"
argument_list|,
name|ex
argument_list|)
expr_stmt|;
name|close
argument_list|()
expr_stmt|;
name|ErrorScreen
name|errorScreen
init|=
operator|new
name|ErrorScreen
argument_list|(
literal|"Disconnected!"
argument_list|,
name|ex
operator|.
name|getMessage
argument_list|()
argument_list|)
decl_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|errorScreen
argument_list|)
expr_stmt|;
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
comment|// For EmoteFix
specifier|private
specifier|static
name|String
name|trimSpacesAndNulls
parameter_list|(
name|String
name|value
parameter_list|)
block|{
name|int
name|len
init|=
name|value
operator|.
name|length
argument_list|()
decl_stmt|;
name|int
name|st
init|=
literal|0
decl_stmt|;
while|while
condition|(
operator|(
name|st
operator|<
name|len
operator|)
operator|&&
operator|(
name|value
operator|.
name|charAt
argument_list|(
name|st
argument_list|)
operator|==
literal|' '
operator|||
name|value
operator|.
name|charAt
argument_list|(
name|st
argument_list|)
operator|==
literal|0
operator|)
condition|)
block|{
name|st
operator|++
expr_stmt|;
block|}
while|while
condition|(
operator|(
name|st
operator|<
name|len
operator|)
operator|&&
operator|(
name|value
operator|.
name|charAt
argument_list|(
name|len
operator|-
literal|1
argument_list|)
operator|==
literal|' '
operator|||
name|value
operator|.
name|charAt
argument_list|(
name|len
operator|-
literal|1
argument_list|)
operator|==
literal|0
operator|)
condition|)
block|{
name|len
operator|--
expr_stmt|;
block|}
return|return
operator|(
operator|(
name|st
operator|>
literal|0
operator|)
operator|||
operator|(
name|len
operator|<
name|value
operator|.
name|length
argument_list|()
operator|)
operator|)
condition|?
name|value
operator|.
name|substring
argument_list|(
name|st
argument_list|,
name|len
argument_list|)
else|:
name|value
return|;
block|}
comment|/**      * Checks whether an extension is currently enabled (and mutually supported) by this client and      * the server that we are currently connected to.      */
specifier|public
name|boolean
name|isExtEnabled
parameter_list|(
name|ProtocolExtension
name|ext
parameter_list|)
block|{
return|return
name|enabledExtensions
operator|.
name|contains
argument_list|(
name|ext
argument_list|)
return|;
block|}
specifier|public
name|ProtocolExtension
index|[]
name|listEnabledExtensions
parameter_list|()
block|{
name|ProtocolExtension
index|[]
name|extList
init|=
operator|new
name|ProtocolExtension
index|[
name|enabledExtensions
operator|.
name|size
argument_list|()
index|]
decl_stmt|;
return|return
name|enabledExtensions
operator|.
name|toArray
argument_list|(
name|extList
argument_list|)
return|;
block|}
specifier|public
name|void
name|enableExtension
parameter_list|(
name|ProtocolExtension
name|ext
parameter_list|)
block|{
name|enabledExtensions
operator|.
name|add
argument_list|(
name|ext
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

