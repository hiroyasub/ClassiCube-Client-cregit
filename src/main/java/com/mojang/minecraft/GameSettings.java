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

begin_class
specifier|public
specifier|final
class|class
name|GameSettings
block|{
comment|// ==== CONSTANTS =============================================================================
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
specifier|public
specifier|static
name|String
index|[]
name|showNamesOptions
init|=
operator|new
name|String
index|[]
block|{
literal|"Hover"
block|,
literal|"Hover (No Scaling)"
block|,
literal|"Always"
block|,
literal|"Always (No Scaling)"
block|}
decl_stmt|;
comment|// showNames values
specifier|public
specifier|static
specifier|final
name|int
name|SHOWNAMES_HOVER
init|=
literal|0
decl_stmt|,
name|SHOWNAMES_HOVER_UNSCALED
init|=
literal|1
decl_stmt|,
name|SHOWNAMES_ALWAYS
init|=
literal|2
decl_stmt|,
name|SHOWNAMES_ALWAYS_UNSCALED
init|=
literal|3
decl_stmt|;
comment|// common framerate limits
specifier|public
specifier|static
name|int
name|MAX_SUPPORTED_FRAMERATE
init|=
literal|60
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
index|[]
name|FRAMERATE_LIMITS
init|=
block|{
literal|20
block|,
literal|30
block|,
literal|40
block|,
literal|60
block|,
literal|75
block|,
literal|85
block|,
literal|120
block|,
literal|144
block|}
decl_stmt|;
comment|// thirdPersonMode values
specifier|public
specifier|static
specifier|final
name|int
name|FIRST_PERSON
init|=
literal|0
decl_stmt|,
name|THIRD_PERSON_BACK
init|=
literal|1
decl_stmt|,
name|THIRD_PERSON_FRONT
init|=
literal|2
decl_stmt|;
comment|// hackType values
specifier|public
specifier|static
specifier|final
name|int
name|HACKTYPE_NORMAL
init|=
literal|0
decl_stmt|,
name|HACKTYPE_ADVANCED
init|=
literal|1
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|viewDistanceOptions
init|=
block|{
literal|"TINY (8)"
block|,
literal|"TINY (16)"
block|,
literal|"SHORT (32)"
block|,
literal|"SHORT (64)"
block|,
literal|"NORMAL (128)"
block|,
literal|"NORMAL (256)"
block|,
literal|"FAR (512)"
block|,
literal|"FAR (1024)"
block|}
decl_stmt|;
comment|// valid range of values for viewDistance
specifier|public
specifier|static
specifier|final
name|int
name|VIEWDISTANCE_MIN
init|=
literal|0
decl_stmt|,
name|VIEWDISTANCE_MAX
init|=
name|viewDistanceOptions
operator|.
name|length
operator|-
literal|1
decl_stmt|;
comment|// smoothing values
specifier|public
specifier|static
specifier|final
name|int
name|SMOOTHING_OFF
init|=
literal|0
decl_stmt|,
name|SMOOTHING_AUTO
init|=
literal|1
decl_stmt|,
name|SMOOTHING_UNIVERSAL
init|=
literal|2
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|float
name|SCALE_MIN
init|=
literal|0.6f
decl_stmt|,
name|SCALE_MAX
init|=
literal|1.2f
decl_stmt|;
comment|// min valid value for anisotropy. Max is set by TextureManager.
specifier|public
specifier|static
specifier|final
name|int
name|ANISOTROPY_OFF
init|=
literal|0
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
name|boolean
name|CanReplaceSlot
init|=
literal|true
decl_stmt|;
comment|// TODO Below two never used
specifier|public
specifier|static
name|List
argument_list|<
name|String
argument_list|>
name|typingLog
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
specifier|public
specifier|static
name|int
name|typingLogPos
init|=
literal|0
decl_stmt|;
specifier|private
specifier|final
name|File
name|settingsFile
decl_stmt|;
specifier|public
name|boolean
name|showClouds
init|=
literal|true
decl_stmt|;
specifier|public
name|ThirdPersonMode
name|thirdPersonMode
init|=
name|ThirdPersonMode
operator|.
name|NONE
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
specifier|public
name|int
name|settingCount
decl_stmt|;
comment|// TODO Never used
comment|// ==== BINDINGS ==============================================================================
specifier|public
name|KeyBinding
name|forwardKey
init|=
operator|new
name|KeyBinding
argument_list|(
literal|"Forward"
argument_list|,
name|Keyboard
operator|.
name|KEY_W
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
name|Keyboard
operator|.
name|KEY_A
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
name|Keyboard
operator|.
name|KEY_S
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
name|Keyboard
operator|.
name|KEY_D
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
name|Keyboard
operator|.
name|KEY_SPACE
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
name|Keyboard
operator|.
name|KEY_B
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
name|Keyboard
operator|.
name|KEY_T
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
name|Keyboard
operator|.
name|KEY_F
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
name|Keyboard
operator|.
name|KEY_RETURN
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
name|Keyboard
operator|.
name|KEY_R
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
name|Keyboard
operator|.
name|KEY_LSHIFT
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
comment|// ==== SETTINGS ==============================================================================
specifier|public
name|int
name|hackType
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|showNames
init|=
literal|0
decl_stmt|;
specifier|public
name|String
name|lastUsedTexturePack
decl_stmt|;
specifier|public
name|boolean
name|hacksEnabled
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
name|int
name|framerateLimit
init|=
literal|60
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
init|=
literal|4
decl_stmt|;
comment|// default to "normal (128)"
comment|// 0 = off, higher values mean nth-powers-of-2 (e.g. 1 => 2x, 2 => 4x, 3 => 8x, 4 => 16x)
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
name|VIEW_DISTANCE
case|:
return|return
literal|"View distance: "
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
name|FRAMERATE_LIMIT
case|:
return|return
literal|"Framerate limit: "
operator|+
operator|(
name|framerateLimit
operator|==
literal|0
condition|?
literal|"OFF"
else|:
name|framerateLimit
operator|+
literal|" FPS"
operator|)
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
name|hackType
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
name|Math
operator|.
name|round
argument_list|(
name|scale
operator|*
literal|100
argument_list|)
operator|+
literal|"%"
return|;
case|case
name|ENABLE_HACKS
case|:
return|return
literal|"Enable Hacks: "
operator|+
operator|(
name|hacksEnabled
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
name|showNamesOptions
index|[
name|showNames
index|]
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
init|;
name|BufferedReader
name|reader
init|=
operator|new
name|BufferedReader
argument_list|(
name|fileReader
argument_list|)
init|)
block|{
comment|// Read the raw settings keys/values
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
name|String
name|key
init|=
name|setting
index|[
literal|0
index|]
operator|.
name|toLowerCase
argument_list|()
decl_stmt|;
name|String
name|value
init|=
name|setting
index|[
literal|1
index|]
decl_stmt|;
try|try
block|{
name|parseOneSetting
argument_list|(
name|key
argument_list|,
name|value
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|ex
parameter_list|)
block|{
name|String
name|errorMsg
init|=
name|String
operator|.
name|format
argument_list|(
literal|"Error parsing a setting: %s=%s"
argument_list|,
name|key
argument_list|,
name|value
argument_list|)
decl_stmt|;
name|LogUtil
operator|.
name|logWarning
argument_list|(
name|errorMsg
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
else|else
block|{
name|LogUtil
operator|.
name|logWarning
argument_list|(
literal|"Options file not found at "
operator|+
name|settingsFile
operator|+
literal|", using defaults."
argument_list|)
expr_stmt|;
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
literal|"Failed to load options from "
operator|+
name|settingsFile
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
specifier|private
name|void
name|parseOneSetting
parameter_list|(
name|String
name|key
parameter_list|,
name|String
name|value
parameter_list|)
block|{
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
name|key
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
name|Byte
operator|.
name|parseByte
argument_list|(
name|value
argument_list|)
argument_list|,
name|VIEWDISTANCE_MIN
argument_list|)
argument_list|,
name|VIEWDISTANCE_MAX
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
comment|// Not used any more. Replaced by framerateLimit.
comment|// Left here for legacy/compatibility reasons.
if|if
condition|(
name|isTrue
condition|)
block|{
name|framerateLimit
operator|=
literal|60
expr_stmt|;
block|}
else|else
block|{
name|framerateLimit
operator|=
literal|0
expr_stmt|;
block|}
break|break;
case|case
literal|"frameratelimit"
case|:
name|framerateLimit
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|value
argument_list|)
expr_stmt|;
if|if
condition|(
name|framerateLimit
operator|!=
literal|0
condition|)
block|{
name|framerateLimit
operator|=
name|Math
operator|.
name|min
argument_list|(
name|framerateLimit
argument_list|,
name|MAX_SUPPORTED_FRAMERATE
argument_list|)
expr_stmt|;
name|framerateLimit
operator|=
name|closestTo
argument_list|(
name|FRAMERATE_LIMITS
argument_list|,
name|framerateLimit
argument_list|)
expr_stmt|;
block|}
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
name|framerateLimit
operator|!=
literal|0
argument_list|)
expr_stmt|;
block|}
break|break;
case|case
literal|"smoothing"
case|:
name|smoothing
operator|=
name|Math
operator|.
name|min
argument_list|(
name|Math
operator|.
name|max
argument_list|(
name|Byte
operator|.
name|parseByte
argument_list|(
name|value
argument_list|)
argument_list|,
name|SMOOTHING_OFF
argument_list|)
argument_list|,
name|SMOOTHING_UNIVERSAL
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"anisotropic"
case|:
name|anisotropy
operator|=
name|Byte
operator|.
name|parseByte
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
name|hackType
operator|=
name|Math
operator|.
name|min
argument_list|(
name|Math
operator|.
name|max
argument_list|(
name|Byte
operator|.
name|parseByte
argument_list|(
name|value
argument_list|)
argument_list|,
name|HACKTYPE_NORMAL
argument_list|)
argument_list|,
name|HACKTYPE_ADVANCED
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"scale"
case|:
comment|// Round scale to nearest 10% step (0.1)
name|float
name|roundedVal
init|=
name|Math
operator|.
name|round
argument_list|(
name|Float
operator|.
name|parseFloat
argument_list|(
name|value
argument_list|)
operator|*
literal|10
argument_list|)
operator|/
literal|10f
decl_stmt|;
name|scale
operator|=
name|Math
operator|.
name|min
argument_list|(
name|Math
operator|.
name|max
argument_list|(
name|roundedVal
argument_list|,
name|SCALE_MIN
argument_list|)
argument_list|,
name|SCALE_MAX
argument_list|)
expr_stmt|;
break|break;
case|case
literal|"hacksenabled"
case|:
name|hacksEnabled
operator|=
name|isTrue
expr_stmt|;
break|break;
case|case
literal|"shownames"
case|:
name|showNames
operator|=
name|Math
operator|.
name|min
argument_list|(
name|Math
operator|.
name|max
argument_list|(
name|Byte
operator|.
name|parseByte
argument_list|(
name|value
argument_list|)
argument_list|,
name|SHOWNAMES_HOVER
argument_list|)
argument_list|,
name|SHOWNAMES_ALWAYS_UNSCALED
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
name|key
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
init|;
name|PrintWriter
name|writer
init|=
operator|new
name|PrintWriter
argument_list|(
name|fileWriter
argument_list|)
init|)
block|{
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
literal|"framerateLimit:"
operator|+
name|framerateLimit
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
literal|"hackType:"
operator|+
name|hackType
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"scale:"
operator|+
name|scale
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"hacksEnabled:"
operator|+
name|hacksEnabled
argument_list|)
expr_stmt|;
name|writer
operator|.
name|println
argument_list|(
literal|"showNames:"
operator|+
name|showNames
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
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Failed to save options."
argument_list|,
name|ex
argument_list|)
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
name|VIEW_DISTANCE
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
name|VIEWDISTANCE_MIN
condition|)
block|{
name|newViewDist
operator|=
name|VIEWDISTANCE_MAX
expr_stmt|;
block|}
if|else if
condition|(
name|newViewDist
operator|>
name|VIEWDISTANCE_MAX
condition|)
block|{
name|newViewDist
operator|=
name|VIEWDISTANCE_MIN
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
name|FRAMERATE_LIMIT
case|:
if|if
condition|(
name|framerateLimit
operator|==
literal|0
condition|)
block|{
comment|// From "Off" to lowest limit
name|framerateLimit
operator|=
name|FRAMERATE_LIMITS
index|[
literal|0
index|]
expr_stmt|;
block|}
if|else if
condition|(
name|framerateLimit
operator|==
name|MAX_SUPPORTED_FRAMERATE
condition|)
block|{
comment|// From highest limit to "Off"
name|framerateLimit
operator|=
literal|0
expr_stmt|;
block|}
else|else
block|{
comment|// Go to the next higher framerate
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|FRAMERATE_LIMITS
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|framerateLimit
operator|==
name|FRAMERATE_LIMITS
index|[
name|i
index|]
condition|)
block|{
if|if
condition|(
name|FRAMERATE_LIMITS
index|[
name|i
operator|+
literal|1
index|]
operator|>
name|MAX_SUPPORTED_FRAMERATE
condition|)
block|{
if|if
condition|(
name|FRAMERATE_LIMITS
index|[
name|i
index|]
operator|<
name|MAX_SUPPORTED_FRAMERATE
condition|)
block|{
comment|// Special case: go up to screen refresh rate that's not on our list
name|framerateLimit
operator|=
name|MAX_SUPPORTED_FRAMERATE
expr_stmt|;
block|}
else|else
block|{
comment|// Wrap around to "Off"
name|framerateLimit
operator|=
literal|0
expr_stmt|;
block|}
block|}
else|else
block|{
comment|// Go up to the next higher limit
name|framerateLimit
operator|=
name|FRAMERATE_LIMITS
index|[
name|i
operator|+
literal|1
index|]
expr_stmt|;
block|}
break|break;
block|}
block|}
block|}
comment|// TODO: decouple vsync from framerate limit
name|Display
operator|.
name|setVSyncEnabled
argument_list|(
name|framerateLimit
operator|!=
literal|0
argument_list|)
expr_stmt|;
break|break;
case|case
name|SMOOTHING
case|:
name|smoothing
operator|++
expr_stmt|;
if|if
condition|(
name|smoothing
operator|>
name|SMOOTHING_UNIVERSAL
condition|)
block|{
name|smoothing
operator|=
name|SMOOTHING_OFF
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
break|break;
case|case
name|ANISOTROPIC
case|:
name|anisotropy
operator|++
expr_stmt|;
if|if
condition|(
name|anisotropy
operator|>
name|TextureManager
operator|.
name|getMaxAnisotropySetting
argument_list|()
condition|)
block|{
name|anisotropy
operator|=
name|ANISOTROPY_OFF
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
name|hackType
operator|++
expr_stmt|;
if|if
condition|(
name|hackType
operator|>
name|HACKTYPE_ADVANCED
condition|)
block|{
name|hackType
operator|=
name|HACKTYPE_NORMAL
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
name|SCALE_MAX
condition|)
block|{
name|scale
operator|=
name|SCALE_MIN
expr_stmt|;
block|}
break|break;
case|case
name|ENABLE_HACKS
case|:
name|hacksEnabled
operator|=
operator|!
name|hacksEnabled
expr_stmt|;
break|break;
case|case
name|SHOW_NAMES
case|:
name|showNames
operator|++
expr_stmt|;
if|if
condition|(
name|showNames
operator|>
name|SHOWNAMES_ALWAYS_UNSCALED
condition|)
block|{
name|showNames
operator|=
name|SHOWNAMES_HOVER
expr_stmt|;
block|}
break|break;
block|}
name|save
argument_list|()
expr_stmt|;
block|}
specifier|private
specifier|static
name|int
name|closestTo
parameter_list|(
name|int
index|[]
name|options
parameter_list|,
name|int
name|target
parameter_list|)
block|{
if|if
condition|(
name|options
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|NullPointerException
argument_list|(
literal|"options"
argument_list|)
throw|;
block|}
name|int
name|closest
init|=
name|Integer
operator|.
name|MAX_VALUE
decl_stmt|;
name|long
name|minDifference
init|=
name|Integer
operator|.
name|MAX_VALUE
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|options
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|long
name|difference
init|=
name|Math
operator|.
name|abs
argument_list|(
operator|(
name|long
operator|)
name|options
index|[
name|i
index|]
operator|-
name|target
argument_list|)
decl_stmt|;
if|if
condition|(
name|minDifference
operator|>
name|difference
condition|)
block|{
name|minDifference
operator|=
name|difference
expr_stmt|;
name|closest
operator|=
name|options
index|[
name|i
index|]
expr_stmt|;
block|}
block|}
return|return
name|closest
return|;
block|}
specifier|public
name|void
name|capRefreshRate
parameter_list|(
name|int
name|maxRefreshRate
parameter_list|)
block|{
name|MAX_SUPPORTED_FRAMERATE
operator|=
name|maxRefreshRate
expr_stmt|;
if|if
condition|(
name|framerateLimit
operator|>
name|maxRefreshRate
condition|)
block|{
name|framerateLimit
operator|=
name|maxRefreshRate
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

