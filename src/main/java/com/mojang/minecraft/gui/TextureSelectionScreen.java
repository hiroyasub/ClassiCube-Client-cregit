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
name|java
operator|.
name|util
operator|.
name|ArrayList
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
name|Minecraft
import|;
end_import

begin_class
specifier|public
class|class
name|TextureSelectionScreen
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
name|ArrayList
argument_list|<
name|TexturePackData
argument_list|>
name|textures
init|=
operator|new
name|ArrayList
argument_list|<
name|TexturePackData
argument_list|>
argument_list|()
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
literal|"Load texture"
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
name|TextureSelectionScreen
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
operator|&&
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
name|openTexture
argument_list|(
name|textures
operator|.
name|get
argument_list|(
name|var1
operator|.
name|id
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
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
name|frozen
operator|=
literal|true
expr_stmt|;
name|TextureDialog
name|var2
decl_stmt|;
operator|(
name|var2
operator|=
operator|new
name|TextureDialog
argument_list|(
name|this
argument_list|,
name|minecraft
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
literal|7
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
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|8
condition|)
block|{
name|this
operator|.
name|minecraft
operator|.
name|textureManager
operator|.
name|currentTerrainPng
operator|=
literal|null
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|textureManager
operator|.
name|customEdgeBlock
operator|=
literal|null
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|textureManager
operator|.
name|customSideBlock
operator|=
literal|null
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|textureManager
operator|.
name|initAtlas
argument_list|()
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|textureManager
operator|.
name|load
argument_list|(
literal|"/terrain.png"
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
name|this
operator|.
name|minecraft
operator|.
name|textureManager
operator|.
name|textures
operator|.
name|clear
argument_list|()
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|levelRenderer
operator|.
name|refresh
argument_list|()
expr_stmt|;
block|}
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
literal|7
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
literal|154
operator|+
literal|22
argument_list|,
literal|"Cancel"
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
literal|8
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
literal|154
argument_list|,
literal|"Default Texture"
argument_list|)
argument_list|)
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
name|this
operator|.
name|minecraft
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
specifier|protected
name|void
name|openTexture
parameter_list|(
name|TexturePackData
name|var1
parameter_list|)
block|{
name|this
operator|.
name|selectedFile
operator|=
operator|new
name|File
argument_list|(
name|var1
operator|.
name|location
argument_list|)
expr_stmt|;
name|openTexture
argument_list|(
name|var1
operator|.
name|location
argument_list|)
expr_stmt|;
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
name|status
operator|=
literal|"Getting texture list.."
expr_stmt|;
name|TexturePackData
name|data
decl_stmt|;
for|for
control|(
name|String
name|file
range|:
operator|(
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|getMinecraftDirectory
argument_list|()
operator|+
literal|"/texturepacks"
argument_list|)
operator|.
name|list
argument_list|()
operator|)
control|)
block|{
if|if
condition|(
operator|!
name|file
operator|.
name|endsWith
argument_list|(
literal|".zip"
argument_list|)
condition|)
continue|continue;
name|data
operator|=
operator|new
name|TexturePackData
argument_list|(
name|file
argument_list|,
name|file
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|file
operator|.
name|indexOf
argument_list|(
literal|"."
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
name|textures
operator|.
name|add
argument_list|(
name|data
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|textures
operator|.
name|size
argument_list|()
operator|>=
literal|1
condition|)
block|{
name|this
operator|.
name|setTextures
argument_list|(
name|this
operator|.
name|textures
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
literal|"Finished loading textures"
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
literal|"Failed to load textures"
expr_stmt|;
name|this
operator|.
name|finished
operator|=
literal|true
expr_stmt|;
block|}
block|}
specifier|protected
name|void
name|setTextures
parameter_list|(
name|ArrayList
argument_list|<
name|TexturePackData
argument_list|>
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
name|Math
operator|.
name|min
argument_list|(
name|var1
operator|.
name|size
argument_list|()
argument_list|,
literal|5
argument_list|)
condition|;
operator|++
name|var2
control|)
block|{
name|this
operator|.
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
operator|.
name|get
argument_list|(
name|var2
argument_list|)
operator|.
name|equals
argument_list|(
literal|"-"
argument_list|)
expr_stmt|;
name|this
operator|.
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
operator|.
name|get
argument_list|(
name|var2
argument_list|)
operator|.
name|name
expr_stmt|;
name|this
operator|.
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
name|selectedFile
operator|=
literal|null
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

