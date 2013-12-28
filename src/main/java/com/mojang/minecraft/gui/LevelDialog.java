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
name|filechooser
operator|.
name|FileNameExtensionFilter
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
name|LevelSerializer
import|;
end_import

begin_class
specifier|final
class|class
name|LevelDialog
extends|extends
name|Thread
block|{
comment|// $FF: synthetic field
specifier|private
name|LoadLevelScreen
name|screen
decl_stmt|;
name|LevelDialog
parameter_list|(
name|LoadLevelScreen
name|var1
parameter_list|)
block|{
name|super
argument_list|()
expr_stmt|;
name|screen
operator|=
name|var1
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|run
parameter_list|()
block|{
name|JFileChooser
name|var1
decl_stmt|;
try|try
block|{
name|LoadLevelScreen
name|var10000
init|=
name|screen
decl_stmt|;
name|var1
operator|=
operator|new
name|JFileChooser
argument_list|()
expr_stmt|;
name|var10000
operator|.
name|chooser
operator|=
name|var1
expr_stmt|;
name|FileNameExtensionFilter
name|var3
init|=
operator|new
name|FileNameExtensionFilter
argument_list|(
literal|"Minecraft levels"
argument_list|,
operator|new
name|String
index|[]
block|{
literal|"dat"
block|}
argument_list|)
decl_stmt|;
name|screen
operator|.
name|chooser
operator|.
name|setFileFilter
argument_list|(
name|var3
argument_list|)
expr_stmt|;
name|screen
operator|.
name|chooser
operator|.
name|setMultiSelectionEnabled
argument_list|(
literal|false
argument_list|)
expr_stmt|;
name|int
name|var7
decl_stmt|;
if|if
condition|(
name|screen
operator|.
name|saving
condition|)
block|{
name|var7
operator|=
name|screen
operator|.
name|chooser
operator|.
name|showSaveDialog
argument_list|(
name|screen
operator|.
name|minecraft
operator|.
name|canvas
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|var7
operator|=
name|screen
operator|.
name|chooser
operator|.
name|showOpenDialog
argument_list|(
name|screen
operator|.
name|minecraft
operator|.
name|canvas
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var7
operator|==
literal|0
condition|)
block|{
name|screen
operator|.
name|selectedFile
operator|=
name|screen
operator|.
name|chooser
operator|.
name|getSelectedFile
argument_list|()
expr_stmt|;
name|screen
operator|.
name|selectedFile
operator|=
operator|new
name|File
argument_list|(
name|screen
operator|.
name|selectedFile
operator|+
literal|""
argument_list|)
expr_stmt|;
try|try
block|{
operator|new
name|LevelSerializer
argument_list|(
name|screen
operator|.
name|minecraft
operator|.
name|level
argument_list|)
operator|.
name|saveMap
argument_list|(
name|screen
operator|.
name|selectedFile
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|FileNotFoundException
name|e
parameter_list|)
block|{
comment|// TODO Auto-generated catch block
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
comment|// TODO Auto-generated catch block
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
block|}
finally|finally
block|{
name|screen
operator|.
name|frozen
operator|=
literal|false
expr_stmt|;
name|var1
operator|=
literal|null
expr_stmt|;
name|screen
operator|.
name|chooser
operator|=
name|var1
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

