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
name|ChatClickData
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
name|ChatClickData
operator|.
name|LinkData
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

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|phys
operator|.
name|AABB
import|;
end_import

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
name|java
operator|.
name|awt
operator|.
name|Desktop
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|Toolkit
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|datatransfer
operator|.
name|DataFlavor
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|datatransfer
operator|.
name|StringSelection
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|datatransfer
operator|.
name|Transferable
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|datatransfer
operator|.
name|UnsupportedFlavorException
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
name|net
operator|.
name|URI
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Vector
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

begin_class
specifier|public
class|class
name|ChatInputScreenExtension
extends|extends
name|GuiScreen
block|{
specifier|public
name|String
name|inputLine
init|=
literal|""
decl_stmt|;
specifier|private
name|int
name|tickCount
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|caretPos
init|=
literal|0
decl_stmt|;
specifier|private
name|int
name|historyPos
init|=
literal|0
decl_stmt|;
specifier|public
specifier|static
name|Vector
argument_list|<
name|String
argument_list|>
name|history
init|=
operator|new
name|Vector
argument_list|<
name|String
argument_list|>
argument_list|()
decl_stmt|;
name|int
name|j
decl_stmt|;
specifier|private
name|String
name|getClipboard
parameter_list|()
block|{
name|Transferable
name|localTransferable
init|=
name|Toolkit
operator|.
name|getDefaultToolkit
argument_list|()
operator|.
name|getSystemClipboard
argument_list|()
operator|.
name|getContents
argument_list|(
literal|null
argument_list|)
decl_stmt|;
try|try
block|{
if|if
condition|(
operator|(
name|localTransferable
operator|!=
literal|null
operator|)
operator|&&
operator|(
name|localTransferable
operator|.
name|isDataFlavorSupported
argument_list|(
name|DataFlavor
operator|.
name|stringFlavor
argument_list|)
operator|)
condition|)
return|return
operator|(
name|String
operator|)
name|localTransferable
operator|.
name|getTransferData
argument_list|(
name|DataFlavor
operator|.
name|stringFlavor
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
name|UnsupportedFlavorException
name|localUnsupportedFlavorException
parameter_list|)
block|{
block|}
catch|catch
parameter_list|(
name|IOException
name|localIOException
parameter_list|)
block|{
block|}
return|return
literal|null
return|;
block|}
specifier|private
name|void
name|insertTextAtCaret
parameter_list|(
name|String
name|paramString
parameter_list|)
block|{
name|int
name|i
init|=
literal|64
operator|-
name|this
operator|.
name|minecraft
operator|.
name|session
operator|.
name|username
operator|.
name|length
argument_list|()
operator|-
literal|2
decl_stmt|;
name|int
name|j
init|=
name|paramString
operator|.
name|length
argument_list|()
decl_stmt|;
name|this
operator|.
name|inputLine
operator|=
operator|(
name|this
operator|.
name|inputLine
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|this
operator|.
name|caretPos
argument_list|)
operator|+
name|paramString
operator|+
name|this
operator|.
name|inputLine
operator|.
name|substring
argument_list|(
name|this
operator|.
name|caretPos
argument_list|)
operator|)
expr_stmt|;
name|this
operator|.
name|caretPos
operator|+=
name|j
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|inputLine
operator|.
name|length
argument_list|()
operator|>
name|i
condition|)
block|{
name|this
operator|.
name|inputLine
operator|=
name|this
operator|.
name|inputLine
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|i
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|caretPos
operator|>
name|this
operator|.
name|inputLine
operator|.
name|length
argument_list|()
condition|)
name|this
operator|.
name|caretPos
operator|=
name|this
operator|.
name|inputLine
operator|.
name|length
argument_list|()
expr_stmt|;
block|}
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
name|paramChar
parameter_list|,
name|int
name|paramInt
parameter_list|)
block|{
if|if
condition|(
name|paramInt
operator|==
literal|1
condition|)
block|{
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
return|return;
block|}
if|if
condition|(
name|paramInt
operator|==
name|Keyboard
operator|.
name|KEY_F2
condition|)
block|{
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
name|takeAndSaveScreenshot
argument_list|(
name|this
operator|.
name|minecraft
operator|.
name|width
argument_list|,
name|this
operator|.
name|minecraft
operator|.
name|height
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|Keyboard
operator|.
name|isKeyDown
argument_list|(
name|Keyboard
operator|.
name|KEY_TAB
argument_list|)
condition|)
return|return;
if|if
condition|(
name|paramInt
operator|==
literal|28
condition|)
block|{
name|String
name|str1
init|=
name|this
operator|.
name|inputLine
operator|.
name|trim
argument_list|()
decl_stmt|;
if|if
condition|(
name|str1
operator|.
name|length
argument_list|()
operator|>
literal|0
condition|)
block|{
name|NetworkManager
name|var10000
init|=
name|this
operator|.
name|minecraft
operator|.
name|networkManager
decl_stmt|;
name|NetworkManager
name|var3
init|=
name|var10000
decl_stmt|;
if|if
condition|(
operator|(
name|str1
operator|=
name|str1
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
name|str1
block|}
argument_list|)
expr_stmt|;
name|history
operator|.
name|add
argument_list|(
name|str1
argument_list|)
expr_stmt|;
block|}
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
return|return;
block|}
name|int
name|i
init|=
name|this
operator|.
name|inputLine
operator|.
name|length
argument_list|()
decl_stmt|;
if|if
condition|(
operator|(
name|paramInt
operator|==
literal|14
operator|)
operator|&&
operator|(
name|i
operator|>
literal|0
operator|)
operator|&&
operator|(
name|this
operator|.
name|caretPos
operator|>
literal|0
operator|)
condition|)
block|{
name|this
operator|.
name|inputLine
operator|=
operator|(
name|this
operator|.
name|inputLine
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|this
operator|.
name|caretPos
operator|-
literal|1
argument_list|)
operator|+
name|this
operator|.
name|inputLine
operator|.
name|substring
argument_list|(
name|this
operator|.
name|caretPos
argument_list|)
operator|)
expr_stmt|;
name|this
operator|.
name|caretPos
operator|-=
literal|1
expr_stmt|;
block|}
if|if
condition|(
operator|(
name|paramInt
operator|==
literal|203
operator|)
operator|&&
operator|(
name|this
operator|.
name|caretPos
operator|>
literal|0
operator|)
condition|)
block|{
name|this
operator|.
name|caretPos
operator|-=
literal|1
expr_stmt|;
block|}
if|if
condition|(
operator|(
name|paramInt
operator|==
literal|205
operator|)
operator|&&
operator|(
name|this
operator|.
name|caretPos
operator|<
name|i
operator|)
condition|)
block|{
name|this
operator|.
name|caretPos
operator|+=
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|paramInt
operator|==
literal|199
condition|)
block|{
name|this
operator|.
name|caretPos
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|paramInt
operator|==
literal|207
condition|)
block|{
name|this
operator|.
name|caretPos
operator|=
name|i
expr_stmt|;
block|}
if|if
condition|(
operator|(
name|Keyboard
operator|.
name|isKeyDown
argument_list|(
literal|219
argument_list|)
operator|)
operator|||
operator|(
name|Keyboard
operator|.
name|isKeyDown
argument_list|(
literal|220
argument_list|)
operator|)
operator|||
operator|(
name|Keyboard
operator|.
name|isKeyDown
argument_list|(
literal|29
argument_list|)
operator|)
operator|||
operator|(
name|Keyboard
operator|.
name|isKeyDown
argument_list|(
literal|157
argument_list|)
operator|)
condition|)
block|{
if|if
condition|(
name|paramInt
operator|==
literal|47
condition|)
block|{
name|paramChar
operator|=
literal|'\000'
expr_stmt|;
name|String
name|str2
init|=
name|getClipboard
argument_list|()
decl_stmt|;
name|insertTextAtCaret
argument_list|(
name|str2
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|paramInt
operator|==
literal|46
condition|)
block|{
name|paramChar
operator|=
literal|'\000'
expr_stmt|;
name|setClipboard
argument_list|(
name|this
operator|.
name|inputLine
argument_list|)
expr_stmt|;
block|}
block|}
if|if
condition|(
name|paramInt
operator|==
literal|200
condition|)
block|{
name|j
operator|=
name|history
operator|.
name|size
argument_list|()
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|historyPos
operator|<
name|j
condition|)
block|{
name|this
operator|.
name|historyPos
operator|+=
literal|1
expr_stmt|;
name|this
operator|.
name|inputLine
operator|=
operator|(
operator|(
name|String
operator|)
name|history
operator|.
name|get
argument_list|(
name|j
operator|-
name|this
operator|.
name|historyPos
argument_list|)
operator|)
expr_stmt|;
name|this
operator|.
name|caretPos
operator|=
name|this
operator|.
name|inputLine
operator|.
name|length
argument_list|()
expr_stmt|;
block|}
block|}
if|if
condition|(
name|paramInt
operator|==
literal|208
condition|)
block|{
name|j
operator|=
name|history
operator|.
name|size
argument_list|()
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|historyPos
operator|>
literal|0
condition|)
block|{
name|this
operator|.
name|historyPos
operator|-=
literal|1
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|historyPos
operator|>
literal|0
condition|)
block|{
name|this
operator|.
name|inputLine
operator|=
operator|(
operator|(
name|String
operator|)
name|history
operator|.
name|get
argument_list|(
name|j
operator|-
name|this
operator|.
name|historyPos
argument_list|)
operator|)
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|inputLine
operator|=
literal|""
expr_stmt|;
block|}
name|this
operator|.
name|caretPos
operator|=
name|this
operator|.
name|inputLine
operator|.
name|length
argument_list|()
expr_stmt|;
block|}
block|}
name|int
name|j
init|=
literal|"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_'*!\\\"#%/()=+?[]{}<>@|$;~`^"
operator|.
name|indexOf
argument_list|(
name|paramChar
argument_list|)
operator|>=
literal|0
condition|?
literal|1
else|:
literal|0
decl_stmt|;
if|if
condition|(
name|j
operator|!=
literal|0
condition|)
name|insertTextAtCaret
argument_list|(
name|String
operator|.
name|valueOf
argument_list|(
name|paramChar
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|protected
specifier|final
name|void
name|onMouseClick
parameter_list|(
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|,
name|int
name|clickType
parameter_list|)
block|{
if|if
condition|(
operator|(
name|clickType
operator|==
literal|0
operator|)
operator|&&
operator|(
name|this
operator|.
name|minecraft
operator|.
name|hud
operator|.
name|hoveredPlayer
operator|!=
literal|null
operator|)
condition|)
name|insertTextAtCaret
argument_list|(
name|this
operator|.
name|minecraft
operator|.
name|hud
operator|.
name|hoveredPlayer
operator|+
literal|" "
argument_list|)
expr_stmt|;
if|if
condition|(
name|clickType
operator|==
literal|0
condition|)
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|this
operator|.
name|minecraft
operator|.
name|hud
operator|.
name|chat
operator|.
name|size
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
for|for
control|(
name|ChatScreenData
name|data
range|:
name|this
operator|.
name|minecraft
operator|.
name|hud
operator|.
name|chatsOnScreen
control|)
block|{
if|if
condition|(
name|x
operator|>
name|data
operator|.
name|bounds
operator|.
name|x0
operator|&&
name|x
argument_list|<
name|data
operator|.
name|bounds
operator|.
name|x1
operator|&&
name|y
argument_list|>
name|data
operator|.
name|bounds
operator|.
name|y0
operator|&&
name|y
operator|<
name|data
operator|.
name|bounds
operator|.
name|y1
condition|)
block|{
name|ChatClickData
name|chatClickData
init|=
operator|new
name|ChatClickData
argument_list|(
name|fontRenderer
argument_list|,
name|this
operator|.
name|minecraft
operator|.
name|hud
operator|.
name|chat
operator|.
name|get
argument_list|(
name|i
argument_list|)
argument_list|,
name|x
argument_list|,
name|y
argument_list|)
decl_stmt|;
if|if
condition|(
name|data
operator|.
name|string
operator|==
name|chatClickData
operator|.
name|message
condition|)
block|{
for|for
control|(
name|LinkData
name|ld
range|:
name|chatClickData
operator|.
name|getClickedUrls
argument_list|()
control|)
block|{
if|if
condition|(
name|ld
operator|!=
literal|null
condition|)
block|{
if|if
condition|(
name|x
operator|>
name|ld
operator|.
name|x0
operator|&&
name|x
argument_list|<
name|ld
operator|.
name|x1
operator|&&
name|y
argument_list|>
name|data
operator|.
name|bounds
operator|.
name|y0
operator|&&
name|y
operator|<
name|data
operator|.
name|bounds
operator|.
name|y1
condition|)
block|{
name|String
name|s
init|=
name|FontRenderer
operator|.
name|stripColor
argument_list|(
name|ld
operator|.
name|link
argument_list|)
decl_stmt|;
name|URI
name|uri
init|=
name|chatClickData
operator|.
name|getURI
argument_list|(
name|s
argument_list|)
decl_stmt|;
if|if
condition|(
name|uri
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|openWebpage
argument_list|(
name|uri
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
block|}
block|}
block|}
block|}
block|}
specifier|public
name|void
name|openWebpage
parameter_list|(
name|URI
name|uri
parameter_list|)
block|{
name|Desktop
name|desktop
init|=
name|Desktop
operator|.
name|isDesktopSupported
argument_list|()
condition|?
name|Desktop
operator|.
name|getDesktop
argument_list|()
else|:
literal|null
decl_stmt|;
if|if
condition|(
name|desktop
operator|!=
literal|null
operator|&&
name|desktop
operator|.
name|isSupported
argument_list|(
name|Desktop
operator|.
name|Action
operator|.
name|BROWSE
argument_list|)
condition|)
block|{
try|try
block|{
name|desktop
operator|.
name|browse
argument_list|(
name|uri
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
block|}
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
specifier|public
name|void
name|render
parameter_list|(
name|int
name|paramInt1
parameter_list|,
name|int
name|paramInt2
parameter_list|)
block|{
name|super
operator|.
name|drawBox
argument_list|(
literal|2
argument_list|,
name|this
operator|.
name|height
operator|-
literal|14
argument_list|,
name|this
operator|.
name|width
operator|-
literal|2
argument_list|,
name|this
operator|.
name|height
operator|-
literal|2
argument_list|,
operator|-
literal|2147483648
argument_list|)
expr_stmt|;
name|char
index|[]
name|temp
init|=
operator|new
name|char
index|[
literal|128
index|]
decl_stmt|;
for|for
control|(
name|int
name|a
init|=
literal|0
init|;
name|a
operator|<
name|this
operator|.
name|inputLine
operator|.
name|length
argument_list|()
condition|;
name|a
operator|++
control|)
block|{
name|temp
index|[
name|a
index|]
operator|=
name|this
operator|.
name|inputLine
operator|.
name|toCharArray
argument_list|()
index|[
name|a
index|]
expr_stmt|;
block|}
if|if
condition|(
name|temp
operator|.
name|length
operator|==
literal|0
condition|)
name|temp
index|[
name|temp
operator|.
name|length
index|]
operator|=
operator|(
name|this
operator|.
name|tickCount
operator|/
literal|6
operator|%
literal|2
operator|==
literal|0
condition|?
literal|'_'
else|:
literal|' '
operator|)
expr_stmt|;
else|else
name|temp
index|[
name|this
operator|.
name|caretPos
index|]
operator|=
operator|(
name|this
operator|.
name|tickCount
operator|/
literal|6
operator|%
literal|2
operator|==
literal|0
condition|?
literal|'_'
else|:
name|temp
index|[
name|this
operator|.
name|caretPos
index|]
operator|)
expr_stmt|;
name|String
name|string
init|=
literal|""
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
name|temp
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|string
operator|+=
name|temp
index|[
name|i
index|]
expr_stmt|;
block|}
name|drawString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
literal|"> "
operator|+
name|string
argument_list|,
literal|4
argument_list|,
name|this
operator|.
name|height
operator|-
literal|12
argument_list|,
literal|14737632
argument_list|)
expr_stmt|;
name|int
name|x
init|=
name|Mouse
operator|.
name|getEventX
argument_list|()
operator|*
name|this
operator|.
name|width
operator|/
name|this
operator|.
name|minecraft
operator|.
name|width
decl_stmt|;
name|int
name|y
init|=
name|this
operator|.
name|height
operator|-
name|Mouse
operator|.
name|getEventY
argument_list|()
operator|*
name|this
operator|.
name|height
operator|/
name|this
operator|.
name|minecraft
operator|.
name|height
operator|-
literal|1
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
name|this
operator|.
name|minecraft
operator|.
name|hud
operator|.
name|chat
operator|.
name|size
argument_list|()
condition|;
name|i
operator|++
control|)
block|{
for|for
control|(
name|ChatScreenData
name|data
range|:
name|this
operator|.
name|minecraft
operator|.
name|hud
operator|.
name|chatsOnScreen
control|)
block|{
if|if
condition|(
name|x
operator|>
name|data
operator|.
name|bounds
operator|.
name|x0
operator|&&
name|x
argument_list|<
name|data
operator|.
name|bounds
operator|.
name|x1
operator|&&
name|y
argument_list|>
name|data
operator|.
name|bounds
operator|.
name|y0
operator|&&
name|y
operator|<
name|data
operator|.
name|bounds
operator|.
name|y1
condition|)
block|{
name|ChatClickData
name|chatClickData
init|=
operator|new
name|ChatClickData
argument_list|(
name|fontRenderer
argument_list|,
name|this
operator|.
name|minecraft
operator|.
name|hud
operator|.
name|chat
operator|.
name|get
argument_list|(
name|i
argument_list|)
argument_list|,
name|x
argument_list|,
name|y
argument_list|)
decl_stmt|;
if|if
condition|(
name|data
operator|.
name|string
operator|==
name|chatClickData
operator|.
name|message
condition|)
block|{
for|for
control|(
name|LinkData
name|ld
range|:
name|chatClickData
operator|.
name|getClickedUrls
argument_list|()
control|)
block|{
if|if
condition|(
name|ld
operator|!=
literal|null
condition|)
block|{
if|if
condition|(
name|x
operator|>
name|ld
operator|.
name|x0
operator|&&
name|x
argument_list|<
name|ld
operator|.
name|x1
operator|&&
name|y
argument_list|>
name|data
operator|.
name|bounds
operator|.
name|y0
operator|&&
name|y
operator|<
name|data
operator|.
name|bounds
operator|.
name|y1
condition|)
block|{
name|super
operator|.
name|drawBox
argument_list|(
name|ld
operator|.
name|x0
argument_list|,
name|data
operator|.
name|y
operator|-
literal|1
argument_list|,
name|ld
operator|.
name|x1
operator|+
literal|3
argument_list|,
name|data
operator|.
name|y
operator|+
literal|9
argument_list|,
operator|-
literal|2147483648
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
block|}
block|}
block|}
specifier|private
name|void
name|setClipboard
parameter_list|(
name|String
name|paramString
parameter_list|)
block|{
name|StringSelection
name|localStringSelection
init|=
operator|new
name|StringSelection
argument_list|(
name|paramString
argument_list|)
decl_stmt|;
name|Toolkit
operator|.
name|getDefaultToolkit
argument_list|()
operator|.
name|getSystemClipboard
argument_list|()
operator|.
name|setContents
argument_list|(
name|localStringSelection
argument_list|,
literal|null
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|tick
parameter_list|()
block|{
operator|++
name|this
operator|.
name|tickCount
expr_stmt|;
block|}
block|}
end_class

end_unit

