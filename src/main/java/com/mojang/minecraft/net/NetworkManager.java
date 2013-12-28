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
name|Iterator
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
name|net
operator|.
name|NetworkHandler
import|;
end_import

begin_class
specifier|public
class|class
name|NetworkManager
block|{
specifier|public
name|ByteArrayOutputStream
name|levelData
decl_stmt|;
specifier|public
name|NetworkHandler
name|netHandler
decl_stmt|;
specifier|public
name|Minecraft
name|minecraft
decl_stmt|;
specifier|public
name|boolean
name|successful
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|levelLoaded
init|=
literal|false
decl_stmt|;
specifier|public
name|HashMap
argument_list|<
name|Byte
argument_list|,
name|NetworkPlayer
argument_list|>
name|players
decl_stmt|;
specifier|public
name|NetworkManager
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|,
name|String
name|server
parameter_list|,
name|int
name|port
parameter_list|,
name|String
name|username
parameter_list|,
name|String
name|key
parameter_list|)
block|{
name|minecraft
operator|.
name|online
operator|=
literal|true
expr_stmt|;
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
name|players
operator|=
operator|new
name|HashMap
argument_list|<
name|Byte
argument_list|,
name|NetworkPlayer
argument_list|>
argument_list|()
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
name|username
argument_list|,
name|key
argument_list|,
name|minecraft
argument_list|)
operator|.
name|start
argument_list|()
expr_stmt|;
block|}
specifier|public
name|void
name|error
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|netHandler
operator|.
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
name|e
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
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
specifier|public
name|List
argument_list|<
name|String
argument_list|>
name|getPlayers
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
argument_list|<
name|String
argument_list|>
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
name|Iterator
argument_list|<
name|NetworkPlayer
argument_list|>
name|playerIterator
init|=
name|players
operator|.
name|values
argument_list|()
operator|.
name|iterator
argument_list|()
decl_stmt|;
while|while
condition|(
name|playerIterator
operator|.
name|hasNext
argument_list|()
condition|)
block|{
name|NetworkPlayer
name|networkPlayer
init|=
name|playerIterator
operator|.
name|next
argument_list|()
decl_stmt|;
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
specifier|public
name|boolean
name|isConnected
parameter_list|()
block|{
return|return
name|netHandler
operator|!=
literal|null
operator|&&
name|netHandler
operator|.
name|connected
return|;
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
name|netHandler
operator|.
name|send
argument_list|(
name|PacketType
operator|.
name|PLAYER_SET_BLOCK
argument_list|,
operator|new
name|Object
index|[]
block|{
name|x
block|,
name|y
block|,
name|z
block|,
name|mode
block|,
name|block
block|}
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

