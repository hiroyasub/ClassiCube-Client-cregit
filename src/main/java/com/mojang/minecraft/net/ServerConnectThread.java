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

begin_import
import|import
name|com
operator|.
name|oyasunadev
operator|.
name|mcraft
operator|.
name|client
operator|.
name|util
operator|.
name|Constants
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|ConnectException
import|;
end_import

begin_class
specifier|public
class|class
name|ServerConnectThread
extends|extends
name|Thread
block|{
specifier|private
name|String
name|server
decl_stmt|;
specifier|private
name|int
name|port
decl_stmt|;
specifier|private
name|String
name|username
decl_stmt|;
specifier|private
name|String
name|key
decl_stmt|;
specifier|private
name|Minecraft
name|minecraft
decl_stmt|;
specifier|private
name|NetworkManager
name|netManager
decl_stmt|;
specifier|public
name|ServerConnectThread
parameter_list|(
name|NetworkManager
name|networkManager
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
parameter_list|,
name|Minecraft
name|minecraft
parameter_list|)
block|{
name|super
argument_list|()
expr_stmt|;
name|netManager
operator|=
name|networkManager
expr_stmt|;
name|this
operator|.
name|server
operator|=
name|server
expr_stmt|;
name|this
operator|.
name|port
operator|=
name|port
expr_stmt|;
name|this
operator|.
name|username
operator|=
name|username
expr_stmt|;
name|this
operator|.
name|key
operator|=
name|key
expr_stmt|;
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|run
parameter_list|()
block|{
try|try
block|{
name|tryConnect
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|ConnectException
name|var3
parameter_list|)
block|{
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
name|isOnline
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
name|void
name|tryConnect
parameter_list|()
throws|throws
name|ConnectException
block|{
name|netManager
operator|.
name|netHandler
operator|=
operator|new
name|NetworkHandler
argument_list|(
name|server
argument_list|,
name|port
argument_list|,
name|minecraft
argument_list|)
expr_stmt|;
name|netManager
operator|.
name|netHandler
operator|.
name|netManager
operator|=
name|netManager
expr_stmt|;
name|netManager
operator|.
name|netHandler
operator|.
name|send
argument_list|(
name|PacketType
operator|.
name|IDENTIFICATION
argument_list|,
name|Constants
operator|.
name|PROTOCOL_VERSION
argument_list|,
name|username
argument_list|,
name|key
argument_list|,
operator|(
name|int
operator|)
name|Constants
operator|.
name|CLIENT_TYPE
argument_list|)
expr_stmt|;
name|netManager
operator|.
name|successful
operator|=
literal|true
expr_stmt|;
block|}
block|}
end_class

end_unit

