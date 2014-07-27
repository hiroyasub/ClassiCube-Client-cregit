begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|gui
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
name|level
operator|.
name|Level
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
name|level
operator|.
name|LevelLoader
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
name|java
operator|.
name|io
operator|.
name|File
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|ErrorScreen
extends|extends
name|GuiScreen
block|{
specifier|private
name|String
name|title
decl_stmt|;
specifier|private
name|String
name|text
decl_stmt|;
specifier|private
name|int
name|timeOpen
init|=
literal|359
decl_stmt|;
specifier|public
name|ErrorScreen
parameter_list|(
name|String
name|title
parameter_list|,
name|String
name|subtitle
parameter_list|)
block|{
name|this
operator|.
name|title
operator|=
name|title
expr_stmt|;
name|text
operator|=
name|subtitle
expr_stmt|;
name|isOpaque
operator|=
literal|true
expr_stmt|;
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|void
name|onButtonClick
parameter_list|(
name|Button
name|button
parameter_list|)
block|{
if|if
condition|(
name|button
operator|.
name|id
operator|==
literal|0
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
literal|null
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|Minecraft
operator|.
name|isSinglePlayer
condition|)
block|{
name|minecraft
operator|.
name|networkManager
operator|=
operator|new
name|NetworkManager
argument_list|(
name|minecraft
argument_list|,
name|minecraft
operator|.
name|server
argument_list|,
name|minecraft
operator|.
name|port
argument_list|,
name|minecraft
operator|.
name|session
operator|.
name|username
argument_list|,
name|minecraft
operator|.
name|session
operator|.
name|mppass
argument_list|)
expr_stmt|;
block|}
else|else
block|{
try|try
block|{
if|if
condition|(
operator|!
name|minecraft
operator|.
name|isLevelLoaded
condition|)
block|{
comment|// Try to load a previously-saved level
name|Level
name|level
init|=
operator|new
name|LevelLoader
argument_list|()
operator|.
name|load
argument_list|(
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|mcDir
argument_list|,
literal|"levelc.cw"
argument_list|)
argument_list|,
name|minecraft
operator|.
name|player
argument_list|)
decl_stmt|;
if|if
condition|(
name|level
operator|!=
literal|null
condition|)
block|{
name|minecraft
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Loading saved map..."
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|setLevel
argument_list|(
name|level
argument_list|)
expr_stmt|;
name|Minecraft
operator|.
name|isSinglePlayer
operator|=
literal|true
expr_stmt|;
block|}
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Failed to load a saved singleplayer level."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|minecraft
operator|.
name|level
operator|==
literal|null
condition|)
block|{
comment|// If loading failed, generate a new level.
name|minecraft
operator|.
name|generateLevel
argument_list|(
literal|1
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|void
name|onKeyPress
parameter_list|(
name|char
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|onOpen
parameter_list|()
block|{
name|buttons
operator|.
name|clear
argument_list|()
expr_stmt|;
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|0
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|this
operator|.
name|height
operator|/
literal|4
operator|+
literal|96
argument_list|,
operator|!
name|Minecraft
operator|.
name|isSinglePlayer
condition|?
literal|"Try to reconnect..."
operator|+
name|timeOpen
operator|/
literal|60
else|:
literal|"Restart ClassiCube"
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|minecraft
operator|.
name|isFullScreen
condition|)
block|{
name|minecraft
operator|.
name|toggleFullscreen
argument_list|()
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|render
parameter_list|(
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
name|drawFadingBox
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
name|width
argument_list|,
name|height
argument_list|,
operator|-
literal|12574688
argument_list|,
operator|-
literal|11530224
argument_list|)
expr_stmt|;
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
name|title
argument_list|,
name|width
operator|/
literal|2
argument_list|,
literal|90
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
name|text
argument_list|,
name|width
operator|/
literal|2
argument_list|,
literal|110
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|super
operator|.
name|render
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|set
argument_list|(
literal|0
argument_list|,
operator|new
name|Button
argument_list|(
literal|0
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|this
operator|.
name|height
operator|/
literal|4
operator|+
literal|96
argument_list|,
operator|!
name|Minecraft
operator|.
name|isSinglePlayer
condition|?
operator|(
name|timeOpen
operator|/
literal|60
operator|>
literal|0
condition|?
literal|"Try to reconnect..."
operator|+
name|timeOpen
operator|/
literal|60
else|:
literal|"Try to reconnect"
operator|)
else|:
literal|"Restart ClassiCube"
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|timeOpen
operator|/
literal|60
operator|>
literal|0
operator|&&
operator|!
name|Minecraft
operator|.
name|isSinglePlayer
condition|)
block|{
operator|--
name|timeOpen
expr_stmt|;
name|buttons
operator|.
name|get
argument_list|(
literal|0
argument_list|)
operator|.
name|active
operator|=
literal|false
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

