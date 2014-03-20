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
name|awt
operator|.
name|Color
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
name|ColorCache
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
name|GameSettings
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
name|Setting
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
name|inputscreens
operator|.
name|FogColorInputScreen
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
name|inputscreens
operator|.
name|LightColorInputScreen
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
name|inputscreens
operator|.
name|ShadowColorInputScreen
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
name|inputscreens
operator|.
name|SkyColorInputScreen
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
name|inputscreens
operator|.
name|WaterLevelInputScreen
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|AdvancedOptionsScreen
extends|extends
name|GuiScreen
block|{
specifier|private
specifier|final
specifier|static
name|Setting
index|[]
name|settingsOrder
init|=
operator|new
name|Setting
index|[]
block|{
name|Setting
operator|.
name|ENABLE_HACKS
block|,
name|Setting
operator|.
name|SPEEDHACK_TYPE
block|,
name|Setting
operator|.
name|ALLOW_SERVER_TEXTURES
block|,
name|Setting
operator|.
name|SHOW_DEBUG
block|}
decl_stmt|;
specifier|private
specifier|final
name|GuiScreen
name|parent
decl_stmt|;
specifier|private
specifier|final
name|String
name|title
init|=
literal|"Advanced Options"
decl_stmt|;
specifier|private
specifier|final
name|GameSettings
name|settings
decl_stmt|;
specifier|public
name|AdvancedOptionsScreen
parameter_list|(
name|GuiScreen
name|parent
parameter_list|,
name|GameSettings
name|settings
parameter_list|)
block|{
name|this
operator|.
name|parent
operator|=
name|parent
expr_stmt|;
name|this
operator|.
name|settings
operator|=
name|settings
expr_stmt|;
block|}
specifier|public
specifier|static
name|String
name|decToHex
parameter_list|(
name|int
name|dec
parameter_list|)
block|{
name|int
name|sizeOfIntInHalfBytes
init|=
literal|8
decl_stmt|;
name|int
name|numberOfBitsInAHalfByte
init|=
literal|4
decl_stmt|;
name|int
name|halfByte
init|=
literal|0x0F
decl_stmt|;
name|char
index|[]
name|hexDigits
init|=
block|{
literal|'0'
block|,
literal|'1'
block|,
literal|'2'
block|,
literal|'3'
block|,
literal|'4'
block|,
literal|'5'
block|,
literal|'6'
block|,
literal|'7'
block|,
literal|'8'
block|,
literal|'9'
block|,
literal|'A'
block|,
literal|'B'
block|,
literal|'C'
block|,
literal|'D'
block|,
literal|'E'
block|,
literal|'F'
block|}
decl_stmt|;
name|StringBuilder
name|hexBuilder
init|=
operator|new
name|StringBuilder
argument_list|(
name|sizeOfIntInHalfBytes
argument_list|)
decl_stmt|;
name|hexBuilder
operator|.
name|setLength
argument_list|(
name|sizeOfIntInHalfBytes
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
name|sizeOfIntInHalfBytes
operator|-
literal|1
init|;
name|i
operator|>=
literal|0
condition|;
operator|--
name|i
control|)
block|{
name|int
name|j
init|=
name|dec
operator|&
name|halfByte
decl_stmt|;
name|hexBuilder
operator|.
name|setCharAt
argument_list|(
name|i
argument_list|,
name|hexDigits
index|[
name|j
index|]
argument_list|)
expr_stmt|;
name|dec
operator|>>=
name|numberOfBitsInAHalfByte
expr_stmt|;
block|}
return|return
name|hexBuilder
operator|.
name|toString
argument_list|()
return|;
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|void
name|onButtonClick
parameter_list|(
name|Button
name|clickedButton
parameter_list|)
block|{
if|if
condition|(
name|clickedButton
operator|.
name|active
condition|)
block|{
if|if
condition|(
name|clickedButton
operator|.
name|id
operator|<
literal|100
condition|)
block|{
name|Setting
name|affectedSetting
init|=
name|settingsOrder
index|[
name|clickedButton
operator|.
name|id
index|]
decl_stmt|;
name|settings
operator|.
name|toggleSetting
argument_list|(
name|affectedSetting
argument_list|,
literal|1
argument_list|)
expr_stmt|;
name|clickedButton
operator|.
name|text
operator|=
name|settings
operator|.
name|getSetting
argument_list|(
name|affectedSetting
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|clickedButton
operator|.
name|id
operator|==
literal|100
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|CloudOptionsScreen
argument_list|(
name|this
argument_list|,
name|settings
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|clickedButton
operator|.
name|id
operator|==
literal|200
condition|)
block|{
name|WaterLevelInputScreen
name|screen
init|=
operator|new
name|WaterLevelInputScreen
argument_list|(
name|parent
argument_list|,
literal|""
operator|+
name|minecraft
operator|.
name|level
operator|.
name|waterLevel
argument_list|,
name|height
argument_list|,
literal|"Enter new value for water level..."
argument_list|)
decl_stmt|;
name|screen
operator|.
name|numbersOnly
operator|=
literal|true
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|screen
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|clickedButton
operator|.
name|id
operator|==
literal|300
condition|)
block|{
name|SkyColorInputScreen
name|screen
init|=
operator|new
name|SkyColorInputScreen
argument_list|(
name|parent
argument_list|,
literal|""
operator|+
name|Integer
operator|.
name|toHexString
argument_list|(
name|minecraft
operator|.
name|level
operator|.
name|skyColor
argument_list|)
argument_list|,
name|height
argument_list|,
literal|"Enter new value for sky color..."
argument_list|)
decl_stmt|;
name|screen
operator|.
name|allowedChars
operator|=
literal|"ABCDEFabcdef1234567890"
expr_stmt|;
name|screen
operator|.
name|stringLimit
operator|=
literal|6
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|screen
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|clickedButton
operator|.
name|id
operator|==
literal|400
condition|)
block|{
name|FogColorInputScreen
name|screen
init|=
operator|new
name|FogColorInputScreen
argument_list|(
name|parent
argument_list|,
literal|""
operator|+
name|Integer
operator|.
name|toHexString
argument_list|(
name|minecraft
operator|.
name|level
operator|.
name|fogColor
argument_list|)
argument_list|,
name|height
argument_list|,
literal|"Enter new value for fog color..."
argument_list|)
decl_stmt|;
name|screen
operator|.
name|allowedChars
operator|=
literal|"ABCDEFabcdef1234567890"
expr_stmt|;
name|screen
operator|.
name|stringLimit
operator|=
literal|6
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|screen
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|clickedButton
operator|.
name|id
operator|==
literal|500
condition|)
block|{
name|ColorCache
name|c
init|=
name|minecraft
operator|.
name|level
operator|.
name|customLightColour
decl_stmt|;
name|Color
name|color
init|=
operator|new
name|Color
argument_list|(
literal|255
argument_list|,
literal|255
argument_list|,
literal|255
argument_list|)
decl_stmt|;
name|String
name|colorString
init|=
literal|""
decl_stmt|;
if|if
condition|(
name|c
operator|!=
literal|null
condition|)
block|{
name|colorString
operator|=
name|String
operator|.
name|format
argument_list|(
literal|"%02x%02x%02x"
argument_list|,
operator|(
name|int
operator|)
operator|(
name|c
operator|.
name|R
operator|*
literal|255
operator|)
argument_list|,
operator|(
name|int
operator|)
operator|(
name|c
operator|.
name|G
operator|*
literal|255
operator|)
argument_list|,
operator|(
name|int
operator|)
operator|(
name|c
operator|.
name|B
operator|*
literal|255
operator|)
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|colorString
operator|=
name|String
operator|.
name|format
argument_list|(
literal|"%02x%02x%02x"
argument_list|,
name|color
operator|.
name|getRed
argument_list|()
argument_list|,
name|color
operator|.
name|getGreen
argument_list|()
argument_list|,
name|color
operator|.
name|getBlue
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|LightColorInputScreen
name|screen
init|=
operator|new
name|LightColorInputScreen
argument_list|(
name|parent
argument_list|,
literal|""
operator|+
name|colorString
argument_list|,
name|height
argument_list|,
literal|"Enter new value for light color..."
argument_list|)
decl_stmt|;
name|screen
operator|.
name|allowedChars
operator|=
literal|"ABCDEFabcdef1234567890"
expr_stmt|;
name|screen
operator|.
name|stringLimit
operator|=
literal|6
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|screen
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|clickedButton
operator|.
name|id
operator|==
literal|600
condition|)
block|{
name|ColorCache
name|c
init|=
name|minecraft
operator|.
name|level
operator|.
name|customShadowColour
decl_stmt|;
name|Color
name|color
init|=
operator|new
name|Color
argument_list|(
literal|155
argument_list|,
literal|155
argument_list|,
literal|155
argument_list|)
decl_stmt|;
name|String
name|colorString
init|=
literal|""
decl_stmt|;
if|if
condition|(
name|c
operator|!=
literal|null
condition|)
block|{
name|colorString
operator|=
name|String
operator|.
name|format
argument_list|(
literal|"%02x%02x%02x"
argument_list|,
operator|(
name|int
operator|)
operator|(
name|c
operator|.
name|R
operator|*
literal|255
operator|)
argument_list|,
operator|(
name|int
operator|)
operator|(
name|c
operator|.
name|G
operator|*
literal|255
operator|)
argument_list|,
operator|(
name|int
operator|)
operator|(
name|c
operator|.
name|B
operator|*
literal|255
operator|)
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|colorString
operator|=
name|String
operator|.
name|format
argument_list|(
literal|"%02x%02x%02x"
argument_list|,
name|color
operator|.
name|getRed
argument_list|()
argument_list|,
name|color
operator|.
name|getGreen
argument_list|()
argument_list|,
name|color
operator|.
name|getBlue
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|ShadowColorInputScreen
name|screen
init|=
operator|new
name|ShadowColorInputScreen
argument_list|(
name|parent
argument_list|,
literal|""
operator|+
name|colorString
argument_list|,
name|height
argument_list|,
literal|"Enter new value for shadow color..."
argument_list|)
decl_stmt|;
name|screen
operator|.
name|allowedChars
operator|=
literal|"ABCDEFabcdef1234567890"
expr_stmt|;
name|screen
operator|.
name|stringLimit
operator|=
literal|6
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|screen
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|clickedButton
operator|.
name|id
operator|==
literal|700
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|OptionsScreen
argument_list|(
name|settings
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|onOpen
parameter_list|()
block|{
name|int
name|heightSeparator
init|=
literal|0
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
name|settingsOrder
operator|.
name|length
condition|;
operator|++
name|i
control|)
block|{
comment|// TODO: advanced settings
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
name|i
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|155
operator|+
name|heightSeparator
operator|%
literal|2
operator|*
literal|160
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|24
operator|*
operator|(
name|heightSeparator
operator|>>
literal|1
operator|)
argument_list|,
name|settings
operator|.
name|getSetting
argument_list|(
name|settingsOrder
index|[
name|i
index|]
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
name|heightSeparator
operator|++
expr_stmt|;
block|}
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
literal|100
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|155
operator|+
name|heightSeparator
operator|%
literal|2
operator|*
literal|160
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|24
operator|*
operator|(
name|heightSeparator
operator|>>
literal|1
operator|)
argument_list|,
literal|"Clouds"
argument_list|)
argument_list|)
expr_stmt|;
name|heightSeparator
operator|++
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
literal|200
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|155
operator|+
name|heightSeparator
operator|%
literal|2
operator|*
literal|160
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|24
operator|*
operator|(
name|heightSeparator
operator|>>
literal|1
operator|)
argument_list|,
literal|"Water Level"
argument_list|)
argument_list|)
expr_stmt|;
name|heightSeparator
operator|++
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
literal|300
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|155
operator|+
name|heightSeparator
operator|%
literal|2
operator|*
literal|160
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|24
operator|*
operator|(
name|heightSeparator
operator|>>
literal|1
operator|)
argument_list|,
literal|"Sky Color"
argument_list|)
argument_list|)
expr_stmt|;
name|heightSeparator
operator|++
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
literal|400
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|155
operator|+
name|heightSeparator
operator|%
literal|2
operator|*
literal|160
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|24
operator|*
operator|(
name|heightSeparator
operator|>>
literal|1
operator|)
argument_list|,
literal|"Fog Color"
argument_list|)
argument_list|)
expr_stmt|;
name|heightSeparator
operator|++
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
literal|500
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|155
operator|+
name|heightSeparator
operator|%
literal|2
operator|*
literal|160
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|24
operator|*
operator|(
name|heightSeparator
operator|>>
literal|1
operator|)
argument_list|,
literal|"Sunlight Color"
argument_list|)
argument_list|)
expr_stmt|;
name|heightSeparator
operator|++
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
literal|600
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|155
operator|+
name|heightSeparator
operator|%
literal|2
operator|*
literal|160
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|24
operator|*
operator|(
name|heightSeparator
operator|>>
literal|1
operator|)
argument_list|,
literal|"Shadow Color"
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|700
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
literal|"Done"
argument_list|)
argument_list|)
expr_stmt|;
comment|// [Allow server textures] requires you to be on a server
name|buttons
operator|.
name|get
argument_list|(
literal|2
argument_list|)
operator|.
name|active
operator|=
name|minecraft
operator|.
name|session
operator|!=
literal|null
expr_stmt|;
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
end_class

end_unit

