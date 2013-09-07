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
name|java
operator|.
name|io
operator|.
name|File
import|;
end_import

begin_import
import|import
name|javax
operator|.
name|swing
operator|.
name|JFileChooser
import|;
end_import

begin_import
import|import
name|javax
operator|.
name|swing
operator|.
name|SwingUtilities
import|;
end_import

begin_class
specifier|public
class|class
name|LoadLevelScreen
extends|extends
name|GuiScreen
implements|implements
name|Runnable
block|{
specifier|protected
name|GuiScreen
name|parent
decl_stmt|;
specifier|private
name|boolean
name|finished
init|=
literal|false
decl_stmt|;
specifier|private
name|boolean
name|loaded
init|=
literal|false
decl_stmt|;
specifier|private
name|String
index|[]
name|levels
init|=
literal|null
decl_stmt|;
specifier|private
name|String
name|status
init|=
literal|""
decl_stmt|;
specifier|protected
name|String
name|title
init|=
literal|"Load level"
decl_stmt|;
name|boolean
name|frozen
init|=
literal|false
decl_stmt|;
name|JFileChooser
name|chooser
decl_stmt|;
specifier|protected
name|boolean
name|saving
init|=
literal|false
decl_stmt|;
specifier|protected
name|File
name|selectedFile
decl_stmt|;
specifier|public
name|LoadLevelScreen
parameter_list|(
name|GuiScreen
name|var1
parameter_list|)
block|{
name|this
operator|.
name|parent
operator|=
name|var1
expr_stmt|;
block|}
specifier|public
name|void
name|run
parameter_list|()
block|{
try|try
block|{
if|if
condition|(
name|this
operator|.
name|frozen
condition|)
block|{
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|100L
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
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
name|this
operator|.
name|levels
operator|=
operator|new
name|String
index|[]
block|{
literal|""
block|}
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|levels
operator|.
name|length
operator|>=
literal|5
condition|)
block|{
name|this
operator|.
name|setLevels
argument_list|(
name|this
operator|.
name|levels
argument_list|)
expr_stmt|;
name|this
operator|.
name|loaded
operator|=
literal|true
expr_stmt|;
return|return;
block|}
name|this
operator|.
name|status
operator|=
name|this
operator|.
name|levels
index|[
literal|0
index|]
expr_stmt|;
name|this
operator|.
name|finished
operator|=
literal|true
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|var3
parameter_list|)
block|{
name|var3
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
name|this
operator|.
name|status
operator|=
literal|"Failed to load levels"
expr_stmt|;
comment|//this.finished = true;
block|}
block|}
specifier|protected
name|void
name|setLevels
parameter_list|(
name|String
index|[]
name|var1
parameter_list|)
block|{
for|for
control|(
name|int
name|var2
init|=
literal|0
init|;
name|var2
operator|<
literal|5
condition|;
operator|++
name|var2
control|)
block|{
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
name|var2
argument_list|)
operator|)
operator|.
name|active
operator|=
operator|!
name|var1
index|[
name|var2
index|]
operator|.
name|equals
argument_list|(
literal|"-"
argument_list|)
expr_stmt|;
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
name|var2
argument_list|)
operator|)
operator|.
name|text
operator|=
name|var1
index|[
name|var2
index|]
expr_stmt|;
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
name|var2
argument_list|)
operator|)
operator|.
name|visible
operator|=
literal|true
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|onOpen
parameter_list|()
block|{
operator|(
operator|new
name|Thread
argument_list|(
name|this
argument_list|)
operator|)
operator|.
name|start
argument_list|()
expr_stmt|;
for|for
control|(
name|int
name|var1
init|=
literal|0
init|;
name|var1
operator|<
literal|5
condition|;
operator|++
name|var1
control|)
block|{
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
name|var1
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
literal|6
operator|+
name|var1
operator|*
literal|24
argument_list|,
literal|"---"
argument_list|)
argument_list|)
expr_stmt|;
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
name|var1
argument_list|)
operator|)
operator|.
name|visible
operator|=
literal|false
expr_stmt|;
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
name|var1
argument_list|)
operator|)
operator|.
name|active
operator|=
literal|false
expr_stmt|;
block|}
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|5
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
literal|6
operator|+
literal|120
operator|+
literal|12
argument_list|,
literal|"Load file..."
argument_list|)
argument_list|)
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
literal|6
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
literal|6
operator|+
literal|168
argument_list|,
literal|"Cancel"
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|protected
specifier|final
name|void
name|onButtonClick
parameter_list|(
name|Button
name|var1
parameter_list|)
block|{
if|if
condition|(
operator|!
name|this
operator|.
name|frozen
condition|)
block|{
if|if
condition|(
name|var1
operator|.
name|active
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|loaded
operator|&&
name|var1
operator|.
name|id
operator|<
literal|5
condition|)
block|{
name|this
operator|.
name|openLevel
argument_list|(
name|var1
operator|.
name|id
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|finished
operator|||
name|this
operator|.
name|loaded
operator|&&
name|var1
operator|.
name|id
operator|==
literal|5
condition|)
block|{
name|this
operator|.
name|frozen
operator|=
literal|true
expr_stmt|;
name|LevelDialog
name|var2
decl_stmt|;
operator|(
name|var2
operator|=
operator|new
name|LevelDialog
argument_list|(
name|this
argument_list|)
operator|)
operator|.
name|setDaemon
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|SwingUtilities
operator|.
name|invokeLater
argument_list|(
name|var2
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|finished
operator|||
name|this
operator|.
name|loaded
operator|&&
name|var1
operator|.
name|id
operator|==
literal|6
condition|)
block|{
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|this
operator|.
name|parent
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
specifier|protected
name|void
name|openLevel
parameter_list|(
name|File
name|var1
parameter_list|)
block|{
name|File
name|var2
init|=
name|var1
decl_stmt|;
name|Minecraft
name|var4
init|=
name|this
operator|.
name|minecraft
decl_stmt|;
name|Level
name|var5
decl_stmt|;
if|if
condition|(
operator|(
name|var5
operator|=
name|this
operator|.
name|minecraft
operator|.
name|levelIo
operator|.
name|load
argument_list|(
name|var2
argument_list|)
operator|)
operator|==
literal|null
condition|)
block|{
block|}
else|else
block|{
name|var4
operator|.
name|setLevel
argument_list|(
name|var5
argument_list|)
expr_stmt|;
block|}
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|this
operator|.
name|parent
argument_list|)
expr_stmt|;
block|}
specifier|protected
name|void
name|openLevel
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
name|this
operator|.
name|minecraft
operator|.
name|loadOnlineLevel
argument_list|(
name|this
operator|.
name|minecraft
operator|.
name|session
operator|.
name|username
argument_list|,
name|var1
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|(
name|GuiScreen
operator|)
literal|null
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|grabMouse
argument_list|()
expr_stmt|;
block|}
specifier|public
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
name|this
operator|.
name|width
argument_list|,
name|this
operator|.
name|height
argument_list|,
literal|1610941696
argument_list|,
operator|-
literal|1607454624
argument_list|)
expr_stmt|;
name|drawCenteredString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
name|this
operator|.
name|title
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
argument_list|,
literal|20
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|frozen
condition|)
block|{
name|drawCenteredString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
literal|"Selecting file.."
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
argument_list|,
name|this
operator|.
name|height
operator|/
literal|2
operator|-
literal|4
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|20L
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|var3
parameter_list|)
block|{
name|var3
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
else|else
block|{
if|if
condition|(
operator|!
name|this
operator|.
name|loaded
condition|)
block|{
name|drawCenteredString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
name|this
operator|.
name|status
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
argument_list|,
name|this
operator|.
name|height
operator|/
literal|2
operator|-
literal|4
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
name|super
operator|.
name|render
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|onClose
parameter_list|()
block|{
name|super
operator|.
name|onClose
argument_list|()
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|chooser
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|chooser
operator|.
name|cancelSelection
argument_list|()
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|tick
parameter_list|()
block|{
name|super
operator|.
name|tick
argument_list|()
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|selectedFile
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|openLevel
argument_list|(
name|this
operator|.
name|selectedFile
argument_list|)
expr_stmt|;
name|this
operator|.
name|selectedFile
operator|=
literal|null
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

