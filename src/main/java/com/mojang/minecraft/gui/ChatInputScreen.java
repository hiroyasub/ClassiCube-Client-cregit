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
name|net
operator|.
name|NetworkManager
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
name|net
operator|.
name|PacketType
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|ChatInputScreen
extends|extends
name|GuiScreen
block|{
specifier|public
name|String
name|message
init|=
literal|""
decl_stmt|;
specifier|private
name|int
name|counter
init|=
literal|0
decl_stmt|;
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|onClose
parameter_list|()
block|{
name|Keyboard
operator|.
name|enableRepeatEvents
argument_list|(
literal|false
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|protected
specifier|final
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
name|Keyboard
operator|.
name|KEY_UP
condition|)
block|{
name|GameSettings
operator|.
name|typinglogpos
operator|--
expr_stmt|;
if|if
condition|(
name|GameSettings
operator|.
name|typinglogpos
operator|<
literal|0
condition|)
block|{
name|GameSettings
operator|.
name|typinglogpos
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|GameSettings
operator|.
name|typinglogpos
operator|>=
literal|0
operator|&&
name|GameSettings
operator|.
name|typinglogpos
operator|<
name|GameSettings
operator|.
name|typinglog
operator|.
name|size
argument_list|()
condition|)
block|{
name|message
operator|=
name|GameSettings
operator|.
name|typinglog
operator|.
name|get
argument_list|(
name|GameSettings
operator|.
name|typinglogpos
argument_list|)
expr_stmt|;
block|}
block|}
if|if
condition|(
name|var2
operator|==
name|Keyboard
operator|.
name|KEY_DOWN
condition|)
block|{
name|GameSettings
operator|.
name|typinglogpos
operator|++
expr_stmt|;
if|if
condition|(
name|GameSettings
operator|.
name|typinglogpos
operator|>
name|GameSettings
operator|.
name|typinglog
operator|.
name|size
argument_list|()
condition|)
block|{
name|GameSettings
operator|.
name|typinglogpos
operator|=
name|GameSettings
operator|.
name|typinglog
operator|.
name|size
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|GameSettings
operator|.
name|typinglogpos
operator|>=
literal|0
operator|&&
name|GameSettings
operator|.
name|typinglogpos
operator|<
name|GameSettings
operator|.
name|typinglog
operator|.
name|size
argument_list|()
condition|)
block|{
name|message
operator|=
name|GameSettings
operator|.
name|typinglog
operator|.
name|get
argument_list|(
name|GameSettings
operator|.
name|typinglogpos
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|GameSettings
operator|.
name|typinglogpos
operator|==
name|GameSettings
operator|.
name|typinglog
operator|.
name|size
argument_list|()
condition|)
block|{
name|message
operator|=
literal|""
expr_stmt|;
block|}
block|}
if|if
condition|(
name|var2
operator|==
name|Keyboard
operator|.
name|KEY_ESCAPE
condition|)
block|{
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
block|}
if|else if
condition|(
name|var2
operator|==
name|Keyboard
operator|.
name|KEY_RETURN
condition|)
block|{
name|NetworkManager
name|var10000
init|=
name|minecraft
operator|.
name|networkManager
decl_stmt|;
name|String
name|var4
init|=
name|message
operator|.
name|trim
argument_list|()
decl_stmt|;
name|NetworkManager
name|var3
init|=
name|var10000
decl_stmt|;
if|if
condition|(
operator|(
name|var4
operator|=
name|var4
operator|.
name|trim
argument_list|()
operator|)
operator|.
name|length
argument_list|()
operator|>
literal|0
condition|)
block|{
name|var3
operator|.
name|netHandler
operator|.
name|send
argument_list|(
name|PacketType
operator|.
name|CHAT_MESSAGE
argument_list|,
operator|new
name|Object
index|[]
block|{
name|Integer
operator|.
name|valueOf
argument_list|(
operator|-
literal|1
argument_list|)
block|,
name|var4
block|}
argument_list|)
expr_stmt|;
name|GameSettings
operator|.
name|typinglog
operator|.
name|add
argument_list|(
name|var4
argument_list|)
expr_stmt|;
name|GameSettings
operator|.
name|typinglogpos
operator|=
name|GameSettings
operator|.
name|typinglog
operator|.
name|size
argument_list|()
expr_stmt|;
block|}
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
block|}
else|else
block|{
if|if
condition|(
name|var2
operator|==
name|Keyboard
operator|.
name|KEY_BACK
operator|&&
name|message
operator|.
name|length
argument_list|()
operator|>
literal|0
condition|)
block|{
name|message
operator|=
name|message
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|message
operator|.
name|length
argument_list|()
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
literal|"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_\'*!\\\"#%/()=+?[]{}<>@|$;^`~"
operator|.
name|indexOf
argument_list|(
name|var1
argument_list|)
operator|>=
literal|0
operator|&&
name|message
operator|.
name|length
argument_list|()
operator|<
literal|64
operator|-
operator|(
name|minecraft
operator|.
name|session
operator|.
name|username
operator|.
name|length
argument_list|()
operator|+
literal|2
operator|)
condition|)
block|{
name|message
operator|=
name|message
operator|+
name|var1
expr_stmt|;
block|}
block|}
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|void
name|onMouseClick
parameter_list|(
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|,
name|int
name|var3
parameter_list|)
block|{
if|if
condition|(
name|var3
operator|==
literal|0
operator|&&
name|minecraft
operator|.
name|hud
operator|.
name|hoveredPlayer
operator|!=
literal|null
condition|)
block|{
if|if
condition|(
name|message
operator|.
name|length
argument_list|()
operator|>
literal|0
operator|&&
operator|!
name|message
operator|.
name|endsWith
argument_list|(
literal|" "
argument_list|)
condition|)
block|{
name|message
operator|=
name|message
operator|+
literal|" "
expr_stmt|;
block|}
name|message
operator|=
name|message
operator|+
name|minecraft
operator|.
name|hud
operator|.
name|hoveredPlayer
expr_stmt|;
name|var1
operator|=
literal|64
operator|-
operator|(
name|minecraft
operator|.
name|session
operator|.
name|username
operator|.
name|length
argument_list|()
operator|+
literal|2
operator|)
expr_stmt|;
if|if
condition|(
name|message
operator|.
name|length
argument_list|()
operator|>
name|var1
condition|)
block|{
name|message
operator|=
name|message
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|var1
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
name|Keyboard
operator|.
name|enableRepeatEvents
argument_list|(
literal|true
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
name|int
name|x1
init|=
literal|2
decl_stmt|;
comment|/* Add the beginning position of the box          * + the length of '> _'          * + the length of the trimmed message          * + the x position of the '>  _' string.          */
name|int
name|x2
init|=
name|x1
operator|+
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"> _"
operator|+
name|message
argument_list|)
operator|+
literal|4
decl_stmt|;
name|int
name|y1
init|=
name|height
operator|-
literal|14
decl_stmt|;
name|int
name|y2
init|=
name|y1
operator|+
literal|12
decl_stmt|;
name|super
operator|.
name|drawBox
argument_list|(
name|x1
argument_list|,
name|y1
argument_list|,
name|x2
argument_list|,
name|y2
argument_list|,
name|ChatInputScreenExtension
operator|.
name|ChatRGB
argument_list|)
expr_stmt|;
comment|//drawBox(2, height - 14, width - 2, height - 2, ChatInputScreenExtension.ChatRGB);
name|drawString
argument_list|(
name|fontRenderer
argument_list|,
literal|"> "
operator|+
name|message
operator|+
operator|(
name|counter
operator|/
literal|6
operator|%
literal|2
operator|==
literal|0
condition|?
literal|"_"
else|:
literal|""
operator|)
argument_list|,
literal|4
argument_list|,
name|height
operator|-
literal|12
argument_list|,
literal|14737632
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|tick
parameter_list|()
block|{
operator|++
name|counter
expr_stmt|;
block|}
block|}
end_class

end_unit

