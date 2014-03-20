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
name|input
operator|.
name|Mouse
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
name|GL11
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
name|GuiScreen
extends|extends
name|Screen
block|{
specifier|public
name|int
name|width
decl_stmt|;
specifier|public
name|int
name|height
decl_stmt|;
specifier|public
name|boolean
name|grabsMouse
init|=
literal|false
decl_stmt|;
specifier|protected
name|Minecraft
name|minecraft
decl_stmt|;
specifier|protected
name|List
argument_list|<
name|Button
argument_list|>
name|buttons
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
specifier|protected
name|FontRenderer
name|fontRenderer
decl_stmt|;
specifier|public
specifier|final
name|void
name|clearButtons
parameter_list|()
block|{
name|buttons
operator|.
name|clear
argument_list|()
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|doInput
parameter_list|()
block|{
while|while
condition|(
name|Mouse
operator|.
name|next
argument_list|()
condition|)
block|{
name|mouseEvent
argument_list|()
expr_stmt|;
block|}
while|while
condition|(
name|Keyboard
operator|.
name|next
argument_list|()
condition|)
block|{
name|keyboardEvent
argument_list|()
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|keyboardEvent
parameter_list|()
block|{
if|if
condition|(
name|Keyboard
operator|.
name|getEventKeyState
argument_list|()
condition|)
block|{
name|onKeyPress
argument_list|(
name|Keyboard
operator|.
name|getEventCharacter
argument_list|()
argument_list|,
name|Keyboard
operator|.
name|getEventKey
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|mouseEvent
parameter_list|()
block|{
if|if
condition|(
name|Mouse
operator|.
name|getEventButtonState
argument_list|()
condition|)
block|{
name|int
name|mouseX
init|=
name|Mouse
operator|.
name|getEventX
argument_list|()
operator|*
name|width
operator|/
name|minecraft
operator|.
name|width
decl_stmt|;
name|int
name|mouseY
init|=
name|height
operator|-
name|Mouse
operator|.
name|getEventY
argument_list|()
operator|*
name|height
operator|/
name|minecraft
operator|.
name|height
operator|-
literal|1
decl_stmt|;
name|onMouseClick
argument_list|(
name|mouseX
argument_list|,
name|mouseY
argument_list|,
name|Mouse
operator|.
name|getEventButton
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
specifier|protected
name|void
name|onButtonClick
parameter_list|(
name|Button
name|var1
parameter_list|)
block|{
block|}
specifier|public
name|void
name|onClose
parameter_list|()
block|{
block|}
specifier|protected
name|void
name|onKeyPress
parameter_list|(
name|char
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
if|if
condition|(
name|var2
operator|==
literal|1
condition|)
block|{
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
if|if
condition|(
name|Keyboard
operator|.
name|getEventKey
argument_list|()
operator|==
name|Keyboard
operator|.
name|KEY_F2
condition|)
block|{
name|minecraft
operator|.
name|takeAndSaveScreenshot
argument_list|(
name|minecraft
operator|.
name|width
argument_list|,
name|minecraft
operator|.
name|height
argument_list|)
expr_stmt|;
block|}
block|}
specifier|protected
name|void
name|onMouseClick
parameter_list|(
name|int
name|mouseX
parameter_list|,
name|int
name|mouseY
parameter_list|,
name|int
name|mouseButton
parameter_list|)
block|{
if|if
condition|(
name|mouseButton
operator|==
literal|0
condition|)
block|{
comment|// Left-click
for|for
control|(
name|Button
name|button
range|:
name|buttons
control|)
block|{
if|if
condition|(
name|button
operator|.
name|active
operator|&&
name|mouseX
operator|>=
name|button
operator|.
name|x
operator|&&
name|mouseY
operator|>=
name|button
operator|.
name|y
operator|&&
name|mouseX
operator|<
name|button
operator|.
name|x
operator|+
name|button
operator|.
name|width
operator|&&
name|mouseY
operator|<
name|button
operator|.
name|y
operator|+
name|button
operator|.
name|height
condition|)
block|{
name|onButtonClick
argument_list|(
name|button
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
specifier|public
name|void
name|onOpen
parameter_list|()
block|{
block|}
specifier|public
specifier|final
name|void
name|open
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|,
name|int
name|width
parameter_list|,
name|int
name|height
parameter_list|)
block|{
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
name|fontRenderer
operator|=
name|minecraft
operator|.
name|fontRenderer
expr_stmt|;
name|this
operator|.
name|width
operator|=
name|width
expr_stmt|;
name|this
operator|.
name|height
operator|=
name|height
expr_stmt|;
name|onOpen
argument_list|()
expr_stmt|;
block|}
specifier|public
name|void
name|render
parameter_list|(
name|int
name|mouseX
parameter_list|,
name|int
name|mouseY
parameter_list|)
block|{
for|for
control|(
name|Button
name|button
range|:
name|buttons
control|)
block|{
if|if
condition|(
operator|!
name|button
operator|.
name|visible
condition|)
block|{
continue|continue;
block|}
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|minecraft
operator|.
name|textureManager
operator|.
name|load
argument_list|(
literal|"/gui/gui.png"
argument_list|)
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
literal|1F
argument_list|,
literal|1F
argument_list|,
literal|1F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|byte
name|spriteOffset
init|=
literal|1
decl_stmt|;
name|boolean
name|isHovered
init|=
operator|(
name|mouseX
operator|>=
name|button
operator|.
name|x
operator|)
operator|&&
operator|(
name|mouseY
operator|>=
name|button
operator|.
name|y
operator|)
operator|&&
operator|(
name|mouseX
operator|<
name|button
operator|.
name|x
operator|+
name|button
operator|.
name|width
operator|)
operator|&&
operator|(
name|mouseY
operator|<
name|button
operator|.
name|y
operator|+
name|button
operator|.
name|height
operator|)
decl_stmt|;
if|if
condition|(
operator|!
name|button
operator|.
name|active
condition|)
block|{
name|spriteOffset
operator|=
literal|0
expr_stmt|;
block|}
if|else if
condition|(
name|isHovered
condition|)
block|{
name|spriteOffset
operator|=
literal|2
expr_stmt|;
block|}
name|button
operator|.
name|drawImage
argument_list|(
name|button
operator|.
name|x
argument_list|,
name|button
operator|.
name|y
argument_list|,
literal|0
argument_list|,
literal|46
operator|+
name|spriteOffset
operator|*
literal|20
argument_list|,
name|button
operator|.
name|width
operator|/
literal|2
argument_list|,
name|button
operator|.
name|height
argument_list|)
expr_stmt|;
name|button
operator|.
name|drawImage
argument_list|(
name|button
operator|.
name|x
operator|+
name|button
operator|.
name|width
operator|/
literal|2
argument_list|,
name|button
operator|.
name|y
argument_list|,
literal|200
operator|-
name|button
operator|.
name|width
operator|/
literal|2
argument_list|,
literal|46
operator|+
name|spriteOffset
operator|*
literal|20
argument_list|,
name|button
operator|.
name|width
operator|/
literal|2
argument_list|,
name|button
operator|.
name|height
argument_list|)
expr_stmt|;
name|int
name|textColorRGBA
decl_stmt|;
if|if
condition|(
operator|!
name|button
operator|.
name|active
condition|)
block|{
name|textColorRGBA
operator|=
operator|-
literal|6250336
expr_stmt|;
comment|// A0A0A0FF
block|}
if|else if
condition|(
name|isHovered
condition|)
block|{
name|textColorRGBA
operator|=
literal|16777120
expr_stmt|;
comment|// A0FFFF00
block|}
else|else
block|{
name|textColorRGBA
operator|=
literal|14737632
expr_stmt|;
comment|// E0E0E000
block|}
name|drawCenteredString
argument_list|(
name|minecraft
operator|.
name|fontRenderer
argument_list|,
name|button
operator|.
name|text
argument_list|,
name|button
operator|.
name|x
operator|+
name|button
operator|.
name|width
operator|/
literal|2
argument_list|,
name|button
operator|.
name|y
operator|+
operator|(
name|button
operator|.
name|height
operator|-
literal|8
operator|)
operator|/
literal|2
argument_list|,
name|textColorRGBA
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|tick
parameter_list|()
block|{
block|}
block|}
end_class

end_unit

