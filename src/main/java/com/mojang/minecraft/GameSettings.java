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
name|java
operator|.
name|io
operator|.
name|BufferedReader
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
name|java
operator|.
name|io
operator|.
name|FileReader
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|FileWriter
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|PrintWriter
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|Serializable
import|;
end_import

begin_import
import|import
name|java
operator|.
name|text
operator|.
name|DecimalFormat
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
name|org
operator|.
name|lwjgl
operator|.
name|opengl
operator|.
name|Display
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
name|render
operator|.
name|TextureManager
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|GameSettings
implements|implements
name|Serializable
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|2L
decl_stmt|;
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
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|viewDistanceOptions
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
argument_list|<>
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
name|boolean
name|showClouds
init|=
literal|true
decl_stmt|;
specifier|public
name|byte
name|thirdPersonMode
init|=
literal|0
decl_stmt|;
specifier|public
name|boolean
name|CanSpeed
init|=
literal|true
decl_stmt|;
specifier|public
specifier|transient
name|Minecraft
name|minecraft
decl_stmt|;
specifier|private
specifier|final
name|File
name|settingsFile
decl_stmt|;
specifier|public
name|int
name|settingCount
decl_stmt|;
comment|//==== BINDINGS ===============================================================================
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
name|KeyBinding
name|noClip
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"NoClip"
argument_list|,
name|Keyboard
operator|.
name|KEY_X
argument_list|)
decl_stmt|;
specifier|public
name|KeyBinding
index|[]
name|bindings
decl_stmt|;
specifier|public
name|KeyBinding
index|[]
name|bindingsmore
decl_stmt|;
comment|//==== SETTINGS ===============================================================================
specifier|public
name|int
name|HackType
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|ShowNames
init|=
literal|0
decl_stmt|;
specifier|public
name|String
name|lastUsedTexturePack
decl_stmt|;
specifier|public
name|boolean
name|HacksEnabled
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
name|boolean
name|limitFramerate
init|=
literal|true
decl_stmt|;
specifier|public
name|boolean
name|viewBobbing
init|=
literal|true
decl_stmt|;
specifier|public
name|int
name|viewDistance
decl_stmt|;
comment|// 0 = off, higher values mean powers-of-2 (e.g. 1=>2x, 2=>4x, 3=>8x, 4=>16x)
specifier|public
name|int
name|anisotropy
decl_stmt|;
comment|// Interface font scale, as a ratio of default font (1.0 => 100%)
specifier|public
name|float
name|scale
init|=
literal|1
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
name|canServerChangeTextures
init|=
literal|true
decl_stmt|;
specifier|public
name|boolean
name|showDebug
init|=
literal|false
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
block|}
expr_stmt|;
name|bindingsmore
operator|=
operator|new
name|KeyBinding
index|[]
block|{
name|runKey
block|,
name|flyKey
block|,
name|flyUp
block|,
name|flyDown
block|,
name|noClip
block|}
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
name|String
name|getBindingMore
parameter_list|(
name|int
name|key
parameter_list|)
block|{
return|return
name|bindingsmore
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
name|bindingsmore
index|[
name|key
index|]
operator|.
name|key
argument_list|)
return|;
block|}
specifier|private
specifier|static
name|String
name|toOnOff
parameter_list|(
name|boolean
name|value
parameter_list|)
block|{
return|return
operator|(
name|value
condition|?
literal|"ON"
else|:
literal|"OFF"
operator|)
return|;
block|}
specifier|public
name|String
name|getSetting
parameter_list|(
name|Setting
name|id
parameter_list|)
block|{
switch|switch
condition|(
name|id
condition|)
block|{
case|case
name|MUSIC
case|:
return|return
literal|"Music: "
operator|+
name|toOnOff
argument_list|(
name|music
argument_list|)
return|;
case|case
name|SOUND
case|:
return|return
literal|"Sound: "
operator|+
name|toOnOff
argument_list|(
name|sound
argument_list|)
return|;
case|case
name|INVERT_MOUSE
case|:
return|return
literal|"Invert mouse: "
operator|+
name|toOnOff
argument_list|(
name|invertMouse
argument_list|)
return|;
case|case
name|SHOW_DEBUG
case|:
return|return
literal|"Show Debug: "
operator|+
name|toOnOff
argument_list|(
name|showDebug
argument_list|)
return|;
case|case
name|RENDER_DISTANCE
case|:
return|return
literal|"Render distance: "
operator|+
name|viewDistanceOptions
index|[
name|viewDistance
index|]
return|;
case|case
name|VIEW_BOBBING
case|:
return|return
literal|"View bobbing: "
operator|+
name|toOnOff
argument_list|(
name|viewBobbing
argument_list|)
return|;
case|case
name|LIMIT_FRAMERATE
case|:
return|return
literal|"Limit framerate: "
operator|+
name|toOnOff
argument_list|(
name|limitFramerate
argument_list|)
return|;
case|case
name|SMOOTHING
case|:
return|return
literal|"Smoothing: "
operator|+
name|smoothingOptions
index|[
name|smoothing
index|]
return|;
case|case
name|ANISOTROPIC
case|:
return|return
literal|"Anisotropic: "
operator|+
operator|(
name|anisotropy
operator|==
literal|0
condition|?
literal|"OFF"
else|:
operator|(
literal|1
operator|<<
name|anisotropy
operator|)
operator|+
literal|"x"
operator|)
return|;
case|case
name|ALLOW_SERVER_TEXTURES
case|:
return|return
literal|"Allow server textures: "
operator|+
operator|(
name|canServerChangeTextures
condition|?
literal|"Yes"
else|:
literal|"No"
operator|)
return|;
case|case
name|SPEEDHACK_TYPE
case|:
return|return
literal|"SpeedHack type: "
operator|+
operator|(
name|HackType
operator|==
literal|0
condition|?
literal|"Normal"
else|:
literal|"Adv"
operator|)
return|;
case|case
name|FONT_SCALE
case|:
return|return
literal|"Font Scale: "
operator|+
operator|new
name|DecimalFormat
argument_list|(
literal|"#.#"
argument_list|)
operator|.
name|format
argument_list|(
name|scale
argument_list|)
return|;
case|case
name|ENABLE_HACKS
case|:
return|return
literal|"Enable Hacks: "
operator|+
operator|(
name|HacksEnabled
condition|?
literal|"Yes"
else|:
literal|"No"
operator|)
return|;
case|case
name|SHOW_NAMES
case|:
return|return
literal|"Show Names: "
operator|+
operator|(
name|ShowNames
operator|==
literal|0
condition|?
literal|"Hover"
else|:
literal|"Always"
operator|)
return|;
default|default:
throw|throw
operator|new
name|IllegalArgumentException
argument_list|()
throw|;
block|}
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
try|try
init|(
name|FileReader
name|fileReader
init|=
operator|new
name|FileReader
argument_list|(
name|settingsFile
argument_list|)
init|)
block|{
name|BufferedReader
name|reader
init|=
operator|new
name|BufferedReader
argument_list|(
name|fileReader
argument_list|)
decl_stmt|;
name|HashMap
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
name|rawSettings
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
name|String
name|line
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
name|rawSettings
operator|.
name|put
argument_list|(
name|setting
index|[
literal|0
index|]
operator|.
name|toLowerCase
argument_list|()
argument_list|,
name|setting
index|[
literal|1
index|]
argument_list|)
expr_stmt|;
block|}
name|parseLoadedSettings
argument_list|(
name|rawSettings
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
name|parseLoadedSettings
parameter_list|(
name|HashMap
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
name|settings
parameter_list|)
block|{
for|for
control|(
name|String
name|key
range|:
name|settings
operator|.
name|keySet
argument_list|()
control|)
block|{
name|String
name|value
init|=
name|settings
operator|.
name|get
argument_list|(
name|key
argument_list|)
decl_stmt|;
name|boolean
name|isTrue
init|=
literal|"true"
operator|.
name|equalsIgnoreCase
argument_list|(
name|value
argument_list|)
operator|||
literal|"1"
operator|.
name|equals
argument_list|(
name|value
argument_list|)
decl_stmt|;
switch|switch
condition|(
name|value
condition|)
block|{
case|case
literal|"music"
case|:
name|music
operator|=
name|isTrue
expr_stmt|;
break|break;
case|case
literal|"sound"
case|:
name|sound
operator|=
name|isTrue
expr_stmt|;
break|break;
case|case
literal|"invertymouse"
case|:
name|invertMouse
operator|=
name|isTrue
expr_stmt|;
break|break;
case|case
literal|"showdebug"
case|:
name|showDebug
operator|=
name|isTrue
expr_stmt|;
break|break;
case|case
literal|"viewdistance"
case|:
name|viewDistance
operator|=
name|Math
operator|.
name|min
argument_list|(
name|Math
operator|.
name|max
argument_list|(
name|Integer
operator|.
name|parseInt
argument_list|(
name|value
argument_list|)
argument_list|,
literal|0
argument_list|)
argument_list|,
name|viewDistanceOptions
operator|.
name|length
operator|-
literal|1
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"bobview"
case|:
name|viewBobbing
operator|=
name|isTrue
expr_stmt|;
break|break;
case|case
literal|"limitframerate"
case|:
name|limitFramerate
operator|=
name|isTrue
expr_stmt|;
name|Display
operator|.
name|setVSyncEnabled
argument_list|(
name|limitFramerate
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"smoothing"
case|:
name|smoothing
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|value
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"anisotropic"
case|:
name|smoothing
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|value
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"canserverchangetextures"
case|:
name|canServerChangeTextures
operator|=
name|isTrue
expr_stmt|;
break|break;
case|case
literal|"hacktype"
case|:
name|HackType
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|value
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"scale"
case|:
name|scale
operator|=
name|Float
operator|.
name|parseFloat
argument_list|(
name|value
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"hacksenabled"
case|:
name|HacksEnabled
operator|=
name|isTrue
expr_stmt|;
break|break;
case|case
literal|"shownames"
case|:
name|ShowNames
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|value
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"texturepack"
case|:
name|lastUsedTexturePack
operator|=
name|value
expr_stmt|;
break|break;
default|default:
for|for
control|(
name|KeyBinding
name|binding
range|:
name|bindings
control|)
block|{
if|if
condition|(
operator|(
literal|"key_"
operator|+
name|binding
operator|.
name|name
operator|.
name|toLowerCase
argument_list|()
operator|)
operator|.
name|equals
argument_list|(
name|value
argument_list|)
condition|)
block|{
name|binding
operator|.
name|key
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|value
argument_list|)
expr_stmt|;
break|break;
block|}
block|}
break|break;
block|}
block|}
block|}
specifier|public
name|void
name|save
parameter_list|()
block|{
try|try
block|{
try|try
init|(
name|FileWriter
name|fileWriter
init|=
operator|new
name|FileWriter
argument_list|(
name|settingsFile
argument_list|)
init|)
block|{
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
literal|"showDebug:"
operator|+
name|showDebug
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
name|anisotropy
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"canServerChangeTextures:"
operator|+
name|canServerChangeTextures
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"HackType:"
operator|+
name|HackType
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"Scale:"
operator|+
name|scale
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"HacksEnabled:"
operator|+
name|HacksEnabled
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"ShowNames:"
operator|+
name|ShowNames
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"texturepack:"
operator|+
name|lastUsedTexturePack
argument_list|)
expr_stmt|;
for|for
control|(
name|KeyBinding
name|binding
range|:
name|bindings
control|)
block|{
name|writer
operator|.
name|println
argument_list|(
literal|"key_"
operator|+
name|binding
operator|.
name|name
operator|+
literal|":"
operator|+
name|binding
operator|.
name|key
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
name|setBindingMore
parameter_list|(
name|int
name|key
parameter_list|,
name|int
name|keyID
parameter_list|)
block|{
name|bindingsmore
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
name|Setting
name|setting
parameter_list|,
name|int
name|fogValue
parameter_list|)
block|{
switch|switch
condition|(
name|setting
condition|)
block|{
case|case
name|MUSIC
case|:
name|music
operator|=
operator|!
name|music
expr_stmt|;
break|break;
case|case
name|SOUND
case|:
name|sound
operator|=
operator|!
name|sound
expr_stmt|;
break|break;
case|case
name|INVERT_MOUSE
case|:
name|invertMouse
operator|=
operator|!
name|invertMouse
expr_stmt|;
break|break;
case|case
name|SHOW_DEBUG
case|:
name|showDebug
operator|=
operator|!
name|showDebug
expr_stmt|;
break|break;
case|case
name|RENDER_DISTANCE
case|:
name|int
name|newViewDist
init|=
name|viewDistance
operator|+
name|fogValue
decl_stmt|;
if|if
condition|(
name|newViewDist
operator|<
literal|0
condition|)
block|{
name|newViewDist
operator|=
name|viewDistanceOptions
operator|.
name|length
operator|-
literal|1
expr_stmt|;
block|}
else|else
block|{
name|newViewDist
operator|=
name|newViewDist
operator|%
name|viewDistanceOptions
operator|.
name|length
expr_stmt|;
block|}
name|viewDistance
operator|=
name|newViewDist
expr_stmt|;
break|break;
case|case
name|VIEW_BOBBING
case|:
name|viewBobbing
operator|=
operator|!
name|viewBobbing
expr_stmt|;
break|break;
case|case
name|LIMIT_FRAMERATE
case|:
name|limitFramerate
operator|=
operator|!
name|limitFramerate
expr_stmt|;
if|if
condition|(
name|Display
operator|.
name|isCreated
argument_list|()
condition|)
block|{
name|Display
operator|.
name|setVSyncEnabled
argument_list|(
name|limitFramerate
argument_list|)
expr_stmt|;
block|}
break|break;
case|case
name|SMOOTHING
case|:
name|smoothing
operator|=
operator|(
name|smoothing
operator|+
literal|1
operator|)
operator|%
name|smoothingOptions
operator|.
name|length
expr_stmt|;
name|minecraft
operator|.
name|textureManager
operator|.
name|textures
operator|.
name|clear
argument_list|()
expr_stmt|;
comment|// minecraft.levelRenderer.refresh(); // (?)
break|break;
case|case
name|ANISOTROPIC
case|:
name|anisotropy
operator|=
operator|(
name|anisotropy
operator|+
literal|1
operator|)
operator|%
name|TextureManager
operator|.
name|getMaxAnisotropySetting
argument_list|()
expr_stmt|;
name|minecraft
operator|.
name|textureManager
operator|.
name|textures
operator|.
name|clear
argument_list|()
expr_stmt|;
break|break;
case|case
name|ALLOW_SERVER_TEXTURES
case|:
name|canServerChangeTextures
operator|=
operator|!
name|canServerChangeTextures
expr_stmt|;
break|break;
case|case
name|SPEEDHACK_TYPE
case|:
if|if
condition|(
name|HackType
operator|==
literal|1
condition|)
block|{
name|HackType
operator|=
literal|0
expr_stmt|;
block|}
else|else
block|{
name|HackType
operator|++
expr_stmt|;
block|}
break|break;
case|case
name|FONT_SCALE
case|:
name|scale
operator|+=
literal|0.1
expr_stmt|;
if|if
condition|(
name|scale
operator|>
literal|1.2f
condition|)
block|{
name|scale
operator|=
literal|0.6f
expr_stmt|;
block|}
break|break;
case|case
name|ENABLE_HACKS
case|:
name|HacksEnabled
operator|=
operator|!
name|HacksEnabled
expr_stmt|;
break|break;
case|case
name|SHOW_NAMES
case|:
if|if
condition|(
name|ShowNames
operator|==
literal|0
condition|)
block|{
name|ShowNames
operator|=
literal|1
expr_stmt|;
block|}
else|else
block|{
name|ShowNames
operator|=
literal|0
expr_stmt|;
block|}
break|break;
block|}
name|save
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

