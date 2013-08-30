begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
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
name|render
operator|.
name|TextureManager
import|;
end_import

begin_import
import|import
name|org
operator|.
name|lwjgl
operator|.
name|input
operator|.
name|Keyboard
import|;
end_import

begin_import
import|import
name|javax
operator|.
name|imageio
operator|.
name|ImageIO
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|image
operator|.
name|BufferedImage
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|*
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

begin_class
specifier|public
specifier|final
class|class
name|GameSettings
block|{
specifier|public
specifier|static
name|String
name|StatusString
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|String
name|PercentString
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|boolean
name|CanReplaceSlot
init|=
literal|true
decl_stmt|;
specifier|public
specifier|static
name|List
argument_list|<
name|String
argument_list|>
name|typinglog
init|=
operator|new
name|ArrayList
argument_list|<
name|String
argument_list|>
argument_list|()
decl_stmt|;
specifier|public
specifier|static
name|int
name|typinglogpos
init|=
literal|0
decl_stmt|;
specifier|public
name|GameSettings
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|,
name|File
name|minecraftFolder
parameter_list|)
block|{
name|bindings
operator|=
operator|new
name|KeyBinding
index|[]
block|{
name|forwardKey
block|,
name|leftKey
block|,
name|backKey
block|,
name|rightKey
block|,
name|jumpKey
block|,
name|inventoryKey
block|,
name|chatKey
block|,
name|toggleFogKey
block|,
name|saveLocationKey
block|,
name|loadLocationKey
block|,
name|runKey
block|}
expr_stmt|;
name|settingCount
operator|=
literal|10
expr_stmt|;
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
name|settingsFile
operator|=
operator|new
name|File
argument_list|(
name|minecraftFolder
argument_list|,
literal|"options.txt"
argument_list|)
expr_stmt|;
name|load
argument_list|()
expr_stmt|;
block|}
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|renderDistances
init|=
operator|new
name|String
index|[]
block|{
literal|"FAR"
block|,
literal|"NORMAL"
block|,
literal|"SHORT"
block|,
literal|"TINY"
block|}
decl_stmt|;
specifier|public
name|boolean
name|music
init|=
literal|true
decl_stmt|;
specifier|public
name|boolean
name|sound
init|=
literal|true
decl_stmt|;
specifier|public
name|boolean
name|invertMouse
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|showFrameRate
init|=
literal|false
decl_stmt|;
specifier|public
name|int
name|viewDistance
init|=
literal|0
decl_stmt|;
specifier|public
name|boolean
name|viewBobbing
init|=
literal|true
decl_stmt|;
specifier|public
name|boolean
name|anaglyph
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|limitFramerate
init|=
literal|false
decl_stmt|;
specifier|public
name|KeyBinding
name|forwardKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Forward"
argument_list|,
literal|17
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|leftKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Left"
argument_list|,
literal|30
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|backKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Back"
argument_list|,
literal|31
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|rightKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Right"
argument_list|,
literal|32
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|jumpKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Jump"
argument_list|,
literal|57
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|inventoryKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Block List"
argument_list|,
literal|48
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|chatKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Chat"
argument_list|,
literal|20
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|toggleFogKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Toggle fog"
argument_list|,
literal|33
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|saveLocationKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Save location"
argument_list|,
literal|28
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|loadLocationKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Load location"
argument_list|,
literal|19
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|runKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Run"
argument_list|,
literal|42
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
index|[]
name|bindings
decl_stmt|;
specifier|private
name|Minecraft
name|minecraft
decl_stmt|;
specifier|private
name|File
name|settingsFile
decl_stmt|;
specifier|public
name|int
name|settingCount
decl_stmt|;
specifier|public
name|boolean
name|CanSpeed
init|=
literal|true
decl_stmt|;
specifier|public
name|int
name|smoothing
init|=
literal|0
decl_stmt|;
specifier|public
name|String
index|[]
name|smoothingOptions
init|=
operator|new
name|String
index|[]
block|{
literal|"OFF"
block|,
literal|"Automatic"
block|,
literal|"Universal"
block|}
decl_stmt|;
specifier|public
name|int
name|anisotropic
init|=
literal|0
decl_stmt|;
specifier|public
name|String
index|[]
name|anisotropicOptions
init|=
operator|new
name|String
index|[]
block|{
literal|"OFF"
block|,
literal|"ON"
block|}
decl_stmt|;
specifier|public
name|KeyBinding
name|flyKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Fly"
argument_list|,
name|Keyboard
operator|.
name|KEY_Z
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|flyUp
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Fly Up"
argument_list|,
name|Keyboard
operator|.
name|KEY_Q
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
name|flyDown
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Fly Down"
argument_list|,
name|Keyboard
operator|.
name|KEY_E
argument_list|)
decl_stmt|;
specifier|public
name|String
name|getBinding
parameter_list|(
name|int
name|key
parameter_list|)
block|{
return|return
name|bindings
index|[
name|key
index|]
operator|.
name|name
operator|+
literal|": "
operator|+
name|Keyboard
operator|.
name|getKeyName
argument_list|(
name|bindings
index|[
name|key
index|]
operator|.
name|key
argument_list|)
return|;
block|}
specifier|public
name|void
name|setBinding
parameter_list|(
name|int
name|key
parameter_list|,
name|int
name|keyID
parameter_list|)
block|{
name|bindings
index|[
name|key
index|]
operator|.
name|key
operator|=
name|keyID
expr_stmt|;
name|save
argument_list|()
expr_stmt|;
block|}
specifier|public
name|void
name|toggleSetting
parameter_list|(
name|int
name|setting
parameter_list|,
name|int
name|fogValue
parameter_list|)
block|{
if|if
condition|(
name|setting
operator|==
literal|0
condition|)
block|{
name|music
operator|=
operator|!
name|music
expr_stmt|;
block|}
if|if
condition|(
name|setting
operator|==
literal|1
condition|)
block|{
name|sound
operator|=
operator|!
name|sound
expr_stmt|;
block|}
if|if
condition|(
name|setting
operator|==
literal|2
condition|)
block|{
name|invertMouse
operator|=
operator|!
name|invertMouse
expr_stmt|;
block|}
if|if
condition|(
name|setting
operator|==
literal|3
condition|)
block|{
name|showFrameRate
operator|=
operator|!
name|showFrameRate
expr_stmt|;
block|}
if|if
condition|(
name|setting
operator|==
literal|4
condition|)
block|{
name|viewDistance
operator|=
name|viewDistance
operator|+
name|fogValue
operator|&
literal|3
expr_stmt|;
block|}
if|if
condition|(
name|setting
operator|==
literal|5
condition|)
block|{
name|viewBobbing
operator|=
operator|!
name|viewBobbing
expr_stmt|;
block|}
if|if
condition|(
name|setting
operator|==
literal|6
condition|)
block|{
name|anaglyph
operator|=
operator|!
name|anaglyph
expr_stmt|;
name|TextureManager
name|textureManager
init|=
name|minecraft
operator|.
name|textureManager
decl_stmt|;
name|Iterator
name|iterator
init|=
name|this
operator|.
name|minecraft
operator|.
name|textureManager
operator|.
name|textureImages
operator|.
name|keySet
argument_list|()
operator|.
name|iterator
argument_list|()
decl_stmt|;
name|int
name|i
decl_stmt|;
name|BufferedImage
name|image
decl_stmt|;
while|while
condition|(
name|iterator
operator|.
name|hasNext
argument_list|()
condition|)
block|{
name|i
operator|=
operator|(
name|Integer
operator|)
name|iterator
operator|.
name|next
argument_list|()
expr_stmt|;
name|image
operator|=
operator|(
name|BufferedImage
operator|)
name|textureManager
operator|.
name|textureImages
operator|.
name|get
argument_list|(
name|Integer
operator|.
name|valueOf
argument_list|(
name|i
argument_list|)
argument_list|)
expr_stmt|;
name|textureManager
operator|.
name|load
argument_list|(
name|image
argument_list|,
name|i
argument_list|)
expr_stmt|;
block|}
name|iterator
operator|=
name|textureManager
operator|.
name|textures
operator|.
name|keySet
argument_list|()
operator|.
name|iterator
argument_list|()
expr_stmt|;
while|while
condition|(
name|iterator
operator|.
name|hasNext
argument_list|()
condition|)
block|{
name|String
name|s
init|=
operator|(
name|String
operator|)
name|iterator
operator|.
name|next
argument_list|()
decl_stmt|;
try|try
block|{
if|if
condition|(
name|s
operator|.
name|startsWith
argument_list|(
literal|"##"
argument_list|)
condition|)
block|{
name|image
operator|=
name|TextureManager
operator|.
name|load1
argument_list|(
name|ImageIO
operator|.
name|read
argument_list|(
name|TextureManager
operator|.
name|class
operator|.
name|getResourceAsStream
argument_list|(
name|s
operator|.
name|substring
argument_list|(
literal|2
argument_list|)
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|image
operator|=
name|ImageIO
operator|.
name|read
argument_list|(
name|TextureManager
operator|.
name|class
operator|.
name|getResourceAsStream
argument_list|(
name|s
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|i
operator|=
operator|(
name|Integer
operator|)
name|textureManager
operator|.
name|textures
operator|.
name|get
argument_list|(
name|s
argument_list|)
expr_stmt|;
name|textureManager
operator|.
name|load
argument_list|(
name|image
argument_list|,
name|i
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|var6
parameter_list|)
block|{
name|var6
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
block|}
if|if
condition|(
name|setting
operator|==
literal|7
condition|)
block|{
name|limitFramerate
operator|=
operator|!
name|limitFramerate
expr_stmt|;
block|}
if|if
condition|(
name|setting
operator|==
literal|8
condition|)
block|{
if|if
condition|(
name|smoothing
operator|==
name|smoothingOptions
operator|.
name|length
operator|-
literal|1
condition|)
block|{
name|smoothing
operator|=
literal|0
expr_stmt|;
block|}
else|else
block|{
name|smoothing
operator|++
expr_stmt|;
block|}
name|minecraft
operator|.
name|textureManager
operator|.
name|textures
operator|.
name|clear
argument_list|()
expr_stmt|;
name|minecraft
operator|.
name|textureManager
operator|.
name|textureImages
operator|.
name|clear
argument_list|()
expr_stmt|;
name|minecraft
operator|.
name|levelRenderer
operator|.
name|refresh
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|setting
operator|==
literal|9
condition|)
block|{
if|if
condition|(
name|anisotropic
operator|==
name|anisotropicOptions
operator|.
name|length
operator|-
literal|1
condition|)
block|{
name|anisotropic
operator|=
literal|0
expr_stmt|;
block|}
else|else
block|{
name|anisotropic
operator|++
expr_stmt|;
block|}
name|minecraft
operator|.
name|textureManager
operator|.
name|textures
operator|.
name|clear
argument_list|()
expr_stmt|;
name|minecraft
operator|.
name|textureManager
operator|.
name|textureImages
operator|.
name|clear
argument_list|()
expr_stmt|;
name|minecraft
operator|.
name|levelRenderer
operator|.
name|refresh
argument_list|()
expr_stmt|;
block|}
name|save
argument_list|()
expr_stmt|;
block|}
specifier|public
name|String
name|getSetting
parameter_list|(
name|int
name|id
parameter_list|)
block|{
return|return
name|id
operator|==
literal|0
condition|?
literal|"Music: "
operator|+
operator|(
name|music
condition|?
literal|"ON"
else|:
literal|"OFF"
operator|)
else|:
operator|(
name|id
operator|==
literal|1
condition|?
literal|"Sound: "
operator|+
operator|(
name|sound
condition|?
literal|"ON"
else|:
literal|"OFF"
operator|)
else|:
operator|(
name|id
operator|==
literal|2
condition|?
literal|"Invert mouse: "
operator|+
operator|(
name|invertMouse
condition|?
literal|"ON"
else|:
literal|"OFF"
operator|)
else|:
operator|(
name|id
operator|==
literal|3
condition|?
literal|"Show FPS: "
operator|+
operator|(
name|showFrameRate
condition|?
literal|"ON"
else|:
literal|"OFF"
operator|)
else|:
operator|(
name|id
operator|==
literal|4
condition|?
literal|"Render distance: "
operator|+
name|renderDistances
index|[
name|viewDistance
index|]
else|:
operator|(
name|id
operator|==
literal|5
condition|?
literal|"View bobbing: "
operator|+
operator|(
name|viewBobbing
condition|?
literal|"ON"
else|:
literal|"OFF"
operator|)
else|:
operator|(
name|id
operator|==
literal|6
condition|?
literal|"3d anaglyph: "
operator|+
operator|(
name|anaglyph
condition|?
literal|"ON"
else|:
literal|"OFF"
operator|)
else|:
operator|(
name|id
operator|==
literal|7
condition|?
literal|"Limit framerate: "
operator|+
operator|(
name|limitFramerate
condition|?
literal|"ON"
else|:
literal|"OFF"
operator|)
else|:
operator|(
name|id
operator|==
literal|8
condition|?
literal|"Smoothing: "
operator|+
name|smoothingOptions
index|[
name|smoothing
index|]
else|:
operator|(
name|id
operator|==
literal|9
condition|?
literal|"Anisotropic: "
operator|+
name|anisotropicOptions
index|[
name|anisotropic
index|]
else|:
literal|""
operator|)
operator|)
operator|)
operator|)
operator|)
operator|)
operator|)
operator|)
operator|)
return|;
block|}
specifier|private
name|void
name|load
parameter_list|()
block|{
try|try
block|{
if|if
condition|(
name|settingsFile
operator|.
name|exists
argument_list|()
condition|)
block|{
name|FileReader
name|fileReader
init|=
operator|new
name|FileReader
argument_list|(
name|settingsFile
argument_list|)
decl_stmt|;
name|BufferedReader
name|reader
init|=
operator|new
name|BufferedReader
argument_list|(
name|fileReader
argument_list|)
decl_stmt|;
name|String
name|line
init|=
literal|null
decl_stmt|;
while|while
condition|(
operator|(
name|line
operator|=
name|reader
operator|.
name|readLine
argument_list|()
operator|)
operator|!=
literal|null
condition|)
block|{
name|String
index|[]
name|setting
init|=
name|line
operator|.
name|split
argument_list|(
literal|":"
argument_list|)
decl_stmt|;
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"music"
argument_list|)
condition|)
block|{
name|music
operator|=
name|setting
index|[
literal|1
index|]
operator|.
name|equals
argument_list|(
literal|"true"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"sound"
argument_list|)
condition|)
block|{
name|sound
operator|=
name|setting
index|[
literal|1
index|]
operator|.
name|equals
argument_list|(
literal|"true"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"invertYMouse"
argument_list|)
condition|)
block|{
name|invertMouse
operator|=
name|setting
index|[
literal|1
index|]
operator|.
name|equals
argument_list|(
literal|"true"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"showFrameRate"
argument_list|)
condition|)
block|{
name|showFrameRate
operator|=
name|setting
index|[
literal|1
index|]
operator|.
name|equals
argument_list|(
literal|"true"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"viewDistance"
argument_list|)
condition|)
block|{
name|viewDistance
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|setting
index|[
literal|1
index|]
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"bobView"
argument_list|)
condition|)
block|{
name|viewBobbing
operator|=
name|setting
index|[
literal|1
index|]
operator|.
name|equals
argument_list|(
literal|"true"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"anaglyph3d"
argument_list|)
condition|)
block|{
name|anaglyph
operator|=
name|setting
index|[
literal|1
index|]
operator|.
name|equals
argument_list|(
literal|"true"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"limitFramerate"
argument_list|)
condition|)
block|{
name|limitFramerate
operator|=
name|setting
index|[
literal|1
index|]
operator|.
name|equals
argument_list|(
literal|"true"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"smoothing"
argument_list|)
condition|)
block|{
name|smoothing
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|setting
index|[
literal|1
index|]
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"anisotropic"
argument_list|)
condition|)
block|{
name|anisotropic
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|setting
index|[
literal|1
index|]
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|index
init|=
literal|0
init|;
name|index
operator|<
name|this
operator|.
name|bindings
operator|.
name|length
condition|;
name|index
operator|++
control|)
block|{
if|if
condition|(
name|setting
index|[
literal|0
index|]
operator|.
name|equals
argument_list|(
literal|"key_"
operator|+
name|bindings
index|[
name|index
index|]
operator|.
name|name
argument_list|)
condition|)
block|{
name|bindings
index|[
name|index
index|]
operator|.
name|key
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|setting
index|[
literal|1
index|]
argument_list|)
expr_stmt|;
block|}
block|}
block|}
name|reader
operator|.
name|close
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Failed to load options"
argument_list|)
expr_stmt|;
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
specifier|private
name|void
name|save
parameter_list|()
block|{
try|try
block|{
name|FileWriter
name|fileWriter
init|=
operator|new
name|FileWriter
argument_list|(
name|this
operator|.
name|settingsFile
argument_list|)
decl_stmt|;
name|PrintWriter
name|writer
init|=
operator|new
name|PrintWriter
argument_list|(
name|fileWriter
argument_list|)
decl_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"music:"
operator|+
name|music
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"sound:"
operator|+
name|sound
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"invertYMouse:"
operator|+
name|invertMouse
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"showFrameRate:"
operator|+
name|showFrameRate
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"viewDistance:"
operator|+
name|viewDistance
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"bobView:"
operator|+
name|viewBobbing
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"anaglyph3d:"
operator|+
name|anaglyph
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"limitFramerate:"
operator|+
name|limitFramerate
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"smoothing:"
operator|+
name|smoothing
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"anisotropic:"
operator|+
name|anisotropic
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|binding
init|=
literal|0
init|;
name|binding
operator|<
name|bindings
operator|.
name|length
condition|;
name|binding
operator|++
control|)
block|{
name|writer
operator|.
name|println
argument_list|(
literal|"key_"
operator|+
name|bindings
index|[
name|binding
index|]
operator|.
name|name
operator|+
literal|":"
operator|+
name|bindings
index|[
name|binding
index|]
operator|.
name|key
argument_list|)
expr_stmt|;
block|}
name|writer
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
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Failed to save options"
argument_list|)
expr_stmt|;
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

