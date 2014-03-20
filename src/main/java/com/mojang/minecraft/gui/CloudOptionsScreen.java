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
name|gui
operator|.
name|inputscreens
operator|.
name|CloudColorInputScreen
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
name|CloudLevelInputScreen
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|CloudOptionsScreen
extends|extends
name|GuiScreen
block|{
specifier|private
name|GuiScreen
name|parent
decl_stmt|;
specifier|private
name|String
name|title
init|=
literal|"Cloud Options"
decl_stmt|;
specifier|private
name|GameSettings
name|settings
decl_stmt|;
specifier|public
name|CloudOptionsScreen
parameter_list|(
name|GuiScreen
name|guiScreen
parameter_list|,
name|GameSettings
name|settings
parameter_list|)
block|{
name|parent
operator|=
name|guiScreen
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
name|button
parameter_list|)
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
name|button
operator|.
name|id
operator|==
literal|1
condition|)
block|{
name|CloudLevelInputScreen
name|screen
init|=
operator|new
name|CloudLevelInputScreen
argument_list|(
name|parent
argument_list|,
literal|""
operator|+
name|minecraft
operator|.
name|level
operator|.
name|cloudLevel
argument_list|,
name|height
argument_list|,
literal|"Enter new value for cloud level..."
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
name|button
operator|.
name|id
operator|==
literal|2
condition|)
block|{
name|CloudColorInputScreen
name|screen
init|=
operator|new
name|CloudColorInputScreen
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
name|cloudColor
argument_list|)
argument_list|,
name|height
argument_list|,
literal|"Enter new value for cloud color..."
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
name|button
operator|.
name|id
operator|==
literal|3
condition|)
block|{
name|settings
operator|.
name|showClouds
operator|=
operator|!
name|settings
operator|.
name|showClouds
expr_stmt|;
name|buttons
operator|.
name|set
argument_list|(
literal|2
argument_list|,
operator|new
name|OptionButton
argument_list|(
literal|3
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|77
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|72
argument_list|,
literal|"Clouds: "
operator|+
operator|(
name|settings
operator|.
name|showClouds
condition|?
literal|"On"
else|:
literal|"Off"
operator|)
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|button
operator|.
name|id
operator|==
literal|4
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|AdvancedOptionsScreen
argument_list|(
name|this
argument_list|,
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
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
literal|1
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|77
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|24
argument_list|,
literal|"Cloud Level"
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
literal|2
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|77
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|48
argument_list|,
literal|"Cloud Color"
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
literal|3
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|77
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|72
argument_list|,
literal|"Clouds: "
operator|+
operator|(
name|settings
operator|.
name|showClouds
condition|?
literal|"On"
else|:
literal|"Off"
operator|)
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
literal|4
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

