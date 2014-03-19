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
name|IOException
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

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
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
name|parent
parameter_list|)
block|{
name|this
operator|.
name|parent
operator|=
name|parent
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
operator|!
name|frozen
condition|)
block|{
if|if
condition|(
name|button
operator|.
name|active
condition|)
block|{
if|if
condition|(
name|loaded
operator|&&
name|button
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
name|button
operator|.
name|id
argument_list|)
expr_stmt|;
block|}
comment|// if (finished || loaded&& var1.id == 5) {
comment|// frozen = true;
comment|// LevelDialog var2;
comment|// (var2 = new LevelDialog(this)).setDaemon(true);
comment|// SwingUtilities.invokeLater(var2);
comment|// }
if|if
condition|(
name|finished
operator|||
name|loaded
operator|&&
name|button
operator|.
name|id
operator|==
literal|6
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|parent
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
annotation|@
name|Override
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
name|chooser
operator|!=
literal|null
condition|)
block|{
name|chooser
operator|.
name|cancelSelection
argument_list|()
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|onOpen
parameter_list|()
block|{
operator|new
name|Thread
argument_list|(
name|this
argument_list|)
operator|.
name|start
argument_list|()
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
literal|5
condition|;
operator|++
name|i
control|)
block|{
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
name|i
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|6
operator|+
name|i
operator|*
literal|24
argument_list|,
literal|"---"
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|.
name|visible
operator|=
literal|false
expr_stmt|;
name|buttons
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|.
name|active
operator|=
literal|false
expr_stmt|;
block|}
comment|// buttons.add(new Button(5, width / 2 - 100, height / 6 + 120 + 12,
comment|// "Load file..."));
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|6
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
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
specifier|protected
name|void
name|openLevel
parameter_list|(
name|File
name|file
parameter_list|)
block|{
try|try
block|{
name|Level
name|level
init|=
operator|new
name|LevelLoader
argument_list|()
operator|.
name|load
argument_list|(
name|file
argument_list|,
name|this
operator|.
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
name|setLevel
argument_list|(
name|level
argument_list|)
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|IOException
name|ex
parameter_list|)
block|{
comment|// TODO Auto-generated catch block
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error loading level from file."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
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
comment|// this.minecraft.loadOnlineLevel(this.minecraft.session.username,
comment|// var1);
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
literal|null
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|grabMouse
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
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
name|width
argument_list|,
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
name|fontRenderer
argument_list|,
name|title
argument_list|,
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
name|frozen
condition|)
block|{
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
literal|"Selecting file.."
argument_list|,
name|width
operator|/
literal|2
argument_list|,
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
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error waiting to render LoadLevelScreen."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
if|if
condition|(
operator|!
name|loaded
condition|)
block|{
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
name|status
argument_list|,
name|width
operator|/
literal|2
argument_list|,
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
annotation|@
name|Override
specifier|public
name|void
name|run
parameter_list|()
block|{
try|try
block|{
if|if
condition|(
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
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error waiting to run LoadLevelScreen."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
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
name|levels
operator|.
name|length
operator|>=
literal|5
condition|)
block|{
name|setLevels
argument_list|(
name|levels
argument_list|)
expr_stmt|;
name|loaded
operator|=
literal|true
expr_stmt|;
return|return;
block|}
name|status
operator|=
name|levels
index|[
literal|0
index|]
expr_stmt|;
name|finished
operator|=
literal|true
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
name|logError
argument_list|(
literal|"Error while running LoadLevelScreen."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
name|status
operator|=
literal|"Failed to load levels"
expr_stmt|;
comment|// this.finished = true;
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
name|buttons
operator|.
name|get
argument_list|(
name|var2
argument_list|)
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
name|buttons
operator|.
name|get
argument_list|(
name|var2
argument_list|)
operator|.
name|text
operator|=
name|var1
index|[
name|var2
index|]
expr_stmt|;
name|buttons
operator|.
name|get
argument_list|(
name|var2
argument_list|)
operator|.
name|visible
operator|=
literal|true
expr_stmt|;
block|}
block|}
annotation|@
name|Override
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
name|selectedFile
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|openLevel
argument_list|(
name|selectedFile
argument_list|)
expr_stmt|;
name|selectedFile
operator|=
literal|null
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

