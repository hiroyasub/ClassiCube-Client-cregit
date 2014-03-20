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

begin_class
specifier|final
class|class
name|TextureDialog
extends|extends
name|Thread
block|{
specifier|private
name|TextureSelectionScreen
name|screen
decl_stmt|;
specifier|private
name|Minecraft
name|mc
decl_stmt|;
name|TextureDialog
parameter_list|(
name|TextureSelectionScreen
name|screen
parameter_list|,
name|Minecraft
name|minecraft
parameter_list|)
block|{
name|super
argument_list|()
expr_stmt|;
name|this
operator|.
name|screen
operator|=
name|screen
expr_stmt|;
name|mc
operator|=
name|minecraft
expr_stmt|;
block|}
specifier|protected
name|void
name|openTexture
parameter_list|(
name|String
name|file
parameter_list|)
block|{
try|try
block|{
name|mc
operator|.
name|textureManager
operator|.
name|loadTexturePack
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error loading texture pack from "
operator|+
name|file
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
name|mc
operator|.
name|setCurrentScreen
argument_list|(
literal|null
argument_list|)
expr_stmt|;
name|mc
operator|.
name|grabMouse
argument_list|()
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
name|fileChooser
decl_stmt|;
try|try
block|{
name|fileChooser
operator|=
operator|new
name|JFileChooser
argument_list|()
expr_stmt|;
name|screen
operator|.
name|chooser
operator|=
name|fileChooser
expr_stmt|;
name|FileNameExtensionFilter
name|var3
init|=
operator|new
name|FileNameExtensionFilter
argument_list|(
literal|".Zip Texture Packs"
argument_list|,
literal|"zip"
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
name|openTexture
argument_list|(
name|screen
operator|.
name|chooser
operator|.
name|getSelectedFile
argument_list|()
operator|.
name|getName
argument_list|()
argument_list|)
expr_stmt|;
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
name|fileChooser
operator|=
literal|null
expr_stmt|;
name|screen
operator|.
name|chooser
operator|=
name|fileChooser
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

