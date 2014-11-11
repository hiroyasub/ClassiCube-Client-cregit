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
name|IOException
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
name|render
operator|.
name|ShapeRenderer
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
name|minecraft
operator|.
name|render
operator|.
name|texture
operator|.
name|Textures
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|FontRenderer
block|{
specifier|public
name|int
name|textureHeight
decl_stmt|;
specifier|public
name|int
name|textureWidth
decl_stmt|;
specifier|public
name|int
index|[]
name|charOffsets
init|=
operator|new
name|int
index|[
literal|256
index|]
decl_stmt|;
specifier|public
name|int
index|[]
name|charWidths
init|=
operator|new
name|int
index|[
literal|256
index|]
decl_stmt|;
specifier|private
specifier|final
name|int
name|fontTextureId
decl_stmt|;
specifier|private
specifier|final
name|GameSettings
name|settings
decl_stmt|;
specifier|public
name|FontRenderer
parameter_list|(
name|GameSettings
name|settings
parameter_list|,
name|TextureManager
name|textures
parameter_list|)
throws|throws
name|IOException
block|{
name|this
operator|.
name|settings
operator|=
name|settings
expr_stmt|;
name|BufferedImage
name|fontTexture
decl_stmt|;
if|if
condition|(
name|textures
operator|.
name|customFont
operator|!=
literal|null
condition|)
block|{
name|fontTexture
operator|=
name|textures
operator|.
name|customFont
expr_stmt|;
block|}
else|else
block|{
name|fontTexture
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
name|Textures
operator|.
name|FONT
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|int
name|width
init|=
name|fontTexture
operator|.
name|getWidth
argument_list|()
decl_stmt|;
name|int
name|height
init|=
name|fontTexture
operator|.
name|getHeight
argument_list|()
decl_stmt|;
name|textureWidth
operator|=
name|width
expr_stmt|;
name|textureHeight
operator|=
name|height
expr_stmt|;
name|calculateCharWidths
argument_list|(
name|fontTexture
argument_list|,
name|width
argument_list|,
name|height
argument_list|)
expr_stmt|;
name|fontTextureId
operator|=
name|textures
operator|.
name|load
argument_list|(
name|Textures
operator|.
name|FONT
argument_list|)
expr_stmt|;
block|}
comment|// Calculates width and offset of every character, to space characters correctly.
specifier|private
name|void
name|calculateCharWidths
parameter_list|(
name|BufferedImage
name|fontTexture
parameter_list|,
name|int
name|width
parameter_list|,
name|int
name|height
parameter_list|)
block|{
name|int
index|[]
name|fontData
init|=
operator|new
name|int
index|[
name|width
operator|*
name|height
index|]
decl_stmt|;
name|fontTexture
operator|.
name|getRGB
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
name|width
argument_list|,
name|height
argument_list|,
name|fontData
argument_list|,
literal|0
argument_list|,
name|width
argument_list|)
expr_stmt|;
name|int
name|maxCharWidth
init|=
name|width
operator|/
literal|16
decl_stmt|;
name|int
name|maxCharHeight
init|=
name|height
operator|/
literal|16
decl_stmt|;
for|for
control|(
name|int
name|character
init|=
literal|0
init|;
name|character
operator|<
literal|128
condition|;
operator|++
name|character
control|)
block|{
name|int
name|col
init|=
name|character
operator|%
literal|16
decl_stmt|;
name|int
name|row
init|=
name|character
operator|/
literal|16
decl_stmt|;
name|int
name|offset
init|=
operator|(
name|col
operator|*
name|maxCharWidth
operator|)
operator|+
operator|(
name|row
operator|*
name|maxCharHeight
operator|*
name|width
operator|)
decl_stmt|;
if|if
condition|(
name|character
operator|==
literal|32
condition|)
block|{
comment|// Space is always 50% width
name|charWidths
index|[
literal|32
index|]
operator|=
name|maxCharWidth
operator|/
literal|2
expr_stmt|;
block|}
else|else
block|{
comment|// Other chars' width is determined by examining pixels
comment|// First, find start of character (first non-empty row)
name|int
name|chStart
init|=
literal|0
decl_stmt|;
for|for
control|(
name|int
name|c
init|=
literal|0
init|;
name|c
operator|<
name|maxCharWidth
condition|;
name|c
operator|++
control|)
block|{
name|chStart
operator|=
name|c
expr_stmt|;
if|if
condition|(
operator|!
name|isColEmpty
argument_list|(
name|fontData
argument_list|,
name|offset
operator|+
name|c
argument_list|,
name|width
argument_list|,
name|maxCharHeight
argument_list|)
condition|)
block|{
break|break;
block|}
block|}
comment|// Next, find end of character (last non-empty row)
name|int
name|chEnd
init|=
name|maxCharWidth
operator|-
literal|1
decl_stmt|;
for|for
control|(
name|int
name|c
init|=
name|maxCharWidth
operator|-
literal|1
init|;
name|c
operator|>
name|chStart
condition|;
name|c
operator|--
control|)
block|{
name|chEnd
operator|=
name|c
expr_stmt|;
if|if
condition|(
operator|!
name|isColEmpty
argument_list|(
name|fontData
argument_list|,
name|offset
operator|+
name|c
argument_list|,
name|width
argument_list|,
name|maxCharHeight
argument_list|)
condition|)
block|{
break|break;
block|}
block|}
name|charOffsets
index|[
name|character
index|]
operator|=
name|chStart
expr_stmt|;
name|charWidths
index|[
name|character
index|]
operator|=
name|chEnd
operator|-
name|chStart
operator|+
literal|1
expr_stmt|;
block|}
block|}
block|}
specifier|private
specifier|static
name|boolean
name|isColEmpty
parameter_list|(
name|int
index|[]
name|imgData
parameter_list|,
name|int
name|offset
parameter_list|,
name|int
name|imageWidth
parameter_list|,
name|int
name|maxCharHeight
parameter_list|)
block|{
comment|// Checks if a column of pixels contains any non-transparent pixels
for|for
control|(
name|int
name|row
init|=
literal|0
init|;
name|row
operator|<
name|maxCharHeight
condition|;
name|row
operator|++
control|)
block|{
name|int
name|rowOffset
init|=
name|offset
operator|+
name|row
operator|*
name|imageWidth
decl_stmt|;
if|if
condition|(
operator|(
operator|(
name|imgData
index|[
name|rowOffset
index|]
operator|>>
literal|24
operator|)
operator|&
literal|0xFF
operator|)
operator|>
literal|128
condition|)
block|{
comment|// Non-transparent pixel found in column!
return|return
literal|false
return|;
block|}
block|}
return|return
literal|true
return|;
block|}
specifier|public
specifier|static
name|String
name|stripColor
parameter_list|(
name|String
name|message
parameter_list|)
block|{
if|if
condition|(
name|message
operator|==
literal|null
condition|)
block|{
return|return
literal|null
return|;
block|}
name|int
name|start
init|=
name|message
operator|.
name|indexOf
argument_list|(
literal|'&'
argument_list|)
decl_stmt|;
if|if
condition|(
name|start
operator|==
operator|-
literal|1
condition|)
block|{
return|return
name|message
return|;
block|}
name|int
name|lastInsert
init|=
literal|0
decl_stmt|;
name|StringBuilder
name|output
init|=
operator|new
name|StringBuilder
argument_list|(
name|message
operator|.
name|length
argument_list|()
argument_list|)
decl_stmt|;
while|while
condition|(
name|start
operator|!=
operator|-
literal|1
condition|)
block|{
name|output
operator|.
name|append
argument_list|(
name|message
argument_list|,
name|lastInsert
argument_list|,
name|start
argument_list|)
expr_stmt|;
name|lastInsert
operator|=
name|Math
operator|.
name|min
argument_list|(
name|start
operator|+
literal|2
argument_list|,
name|message
operator|.
name|length
argument_list|()
argument_list|)
expr_stmt|;
name|start
operator|=
name|message
operator|.
name|indexOf
argument_list|(
literal|'&'
argument_list|,
name|lastInsert
argument_list|)
expr_stmt|;
block|}
name|output
operator|.
name|append
argument_list|(
name|message
argument_list|,
name|lastInsert
argument_list|,
name|message
operator|.
name|length
argument_list|()
argument_list|)
expr_stmt|;
return|return
name|output
operator|.
name|toString
argument_list|()
return|;
block|}
specifier|public
name|int
name|getWidth
parameter_list|(
name|String
name|text
parameter_list|)
block|{
if|if
condition|(
name|text
operator|==
literal|null
condition|)
block|{
return|return
literal|0
return|;
block|}
name|float
name|charWidthScale
init|=
literal|128f
operator|/
name|textureWidth
decl_stmt|;
name|float
name|width
init|=
literal|0
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|text
operator|.
name|length
argument_list|()
condition|;
name|j
operator|++
control|)
block|{
name|int
name|k
init|=
name|text
operator|.
name|charAt
argument_list|(
name|j
argument_list|)
decl_stmt|;
if|if
condition|(
name|k
operator|==
literal|'&'
condition|)
block|{
name|j
operator|++
expr_stmt|;
block|}
else|else
block|{
name|width
operator|+=
name|charWidths
index|[
name|k
index|]
operator|*
name|charWidthScale
operator|+
literal|1
expr_stmt|;
block|}
block|}
return|return
operator|(
name|int
operator|)
name|Math
operator|.
name|ceil
argument_list|(
name|width
operator|*
name|settings
operator|.
name|scale
argument_list|)
return|;
block|}
specifier|public
name|int
name|getHeight
parameter_list|()
block|{
return|return
operator|(
name|int
operator|)
name|Math
operator|.
name|floor
argument_list|(
name|textureHeight
operator|*
name|settings
operator|.
name|scale
argument_list|)
return|;
block|}
specifier|private
name|void
name|render
parameter_list|(
name|String
name|text
parameter_list|,
name|float
name|x
parameter_list|,
name|float
name|y
parameter_list|,
name|int
name|color
parameter_list|,
name|boolean
name|shadow
parameter_list|)
block|{
if|if
condition|(
name|text
operator|==
literal|null
condition|)
block|{
return|return;
block|}
if|if
condition|(
name|shadow
condition|)
block|{
name|color
operator|=
operator|(
name|color
operator|&
literal|16579836
operator|)
operator|>>
literal|2
expr_stmt|;
block|}
name|x
operator|/=
name|settings
operator|.
name|scale
expr_stmt|;
name|y
operator|/=
name|settings
operator|.
name|scale
expr_stmt|;
name|float
name|charWidthScale
init|=
literal|128f
operator|/
name|textureWidth
decl_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|fontTextureId
argument_list|)
expr_stmt|;
name|ShapeRenderer
operator|.
name|instance
operator|.
name|begin
argument_list|()
expr_stmt|;
name|ShapeRenderer
operator|.
name|instance
operator|.
name|color
argument_list|(
name|color
argument_list|)
expr_stmt|;
name|float
name|xOffset
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
name|text
operator|.
name|length
argument_list|()
condition|;
operator|++
name|i
control|)
block|{
name|char
name|ch
init|=
name|text
operator|.
name|charAt
argument_list|(
name|i
argument_list|)
decl_stmt|;
if|if
condition|(
name|ch
operator|==
literal|'&'
operator|&&
name|text
operator|.
name|length
argument_list|()
operator|>
name|i
operator|+
literal|1
condition|)
block|{
comment|// Color code handling
name|int
name|code
init|=
literal|"0123456789abcdef"
operator|.
name|indexOf
argument_list|(
name|text
operator|.
name|charAt
argument_list|(
name|i
operator|+
literal|1
argument_list|)
argument_list|)
decl_stmt|;
if|if
condition|(
name|code
operator|<
literal|0
condition|)
block|{
name|code
operator|=
literal|15
expr_stmt|;
block|}
name|int
name|intensity
init|=
operator|(
name|code
operator|&
literal|8
operator|)
operator|<<
literal|3
decl_stmt|;
name|int
name|blue
init|=
operator|(
name|code
operator|&
literal|1
operator|)
operator|*
literal|191
operator|+
name|intensity
decl_stmt|;
name|int
name|green
init|=
operator|(
operator|(
name|code
operator|&
literal|2
operator|)
operator|>>
literal|1
operator|)
operator|*
literal|191
operator|+
name|intensity
decl_stmt|;
name|int
name|red
init|=
operator|(
operator|(
name|code
operator|&
literal|4
operator|)
operator|>>
literal|2
operator|)
operator|*
literal|191
operator|+
name|intensity
decl_stmt|;
name|int
name|c
init|=
name|red
operator|<<
literal|16
operator||
name|green
operator|<<
literal|8
operator||
name|blue
decl_stmt|;
if|if
condition|(
name|shadow
condition|)
block|{
name|c
operator|=
operator|(
name|c
operator|&
literal|16579836
operator|)
operator|>>
literal|2
expr_stmt|;
block|}
name|ShapeRenderer
operator|.
name|instance
operator|.
name|color
argument_list|(
name|c
argument_list|)
expr_stmt|;
if|if
condition|(
name|text
operator|.
name|length
argument_list|()
operator|-
literal|2
operator|==
name|i
condition|)
block|{
break|break;
block|}
name|i
operator|+=
literal|2
expr_stmt|;
name|ch
operator|=
name|text
operator|.
name|charAt
argument_list|(
name|i
argument_list|)
expr_stmt|;
block|}
name|int
name|colOffset
init|=
name|ch
operator|%
literal|16
operator|<<
literal|3
decl_stmt|;
name|int
name|rowOffset
init|=
name|ch
operator|/
literal|16
operator|<<
literal|3
decl_stmt|;
name|float
name|charQuadSize
init|=
literal|7.99F
decl_stmt|;
name|xOffset
operator|-=
name|charOffsets
index|[
name|ch
index|]
operator|*
name|charWidthScale
expr_stmt|;
name|ShapeRenderer
operator|.
name|instance
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|xOffset
argument_list|,
name|y
operator|+
name|charQuadSize
argument_list|,
literal|0F
argument_list|,
name|colOffset
operator|/
literal|128F
argument_list|,
operator|(
name|rowOffset
operator|+
name|charQuadSize
operator|)
operator|/
literal|128F
argument_list|)
expr_stmt|;
name|ShapeRenderer
operator|.
name|instance
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|xOffset
operator|+
name|charQuadSize
argument_list|,
name|y
operator|+
name|charQuadSize
argument_list|,
literal|0F
argument_list|,
operator|(
name|colOffset
operator|+
name|charQuadSize
operator|)
operator|/
literal|128F
argument_list|,
operator|(
name|rowOffset
operator|+
name|charQuadSize
operator|)
operator|/
literal|128F
argument_list|)
expr_stmt|;
name|ShapeRenderer
operator|.
name|instance
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|xOffset
operator|+
name|charQuadSize
argument_list|,
name|y
argument_list|,
literal|0F
argument_list|,
operator|(
name|colOffset
operator|+
name|charQuadSize
operator|)
operator|/
literal|128F
argument_list|,
name|rowOffset
operator|/
literal|128F
argument_list|)
expr_stmt|;
name|ShapeRenderer
operator|.
name|instance
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|xOffset
argument_list|,
name|y
argument_list|,
literal|0F
argument_list|,
name|colOffset
operator|/
literal|128F
argument_list|,
name|rowOffset
operator|/
literal|128F
argument_list|)
expr_stmt|;
name|xOffset
operator|+=
operator|(
name|charWidths
index|[
name|ch
index|]
operator|+
name|charOffsets
index|[
name|ch
index|]
operator|)
operator|*
name|charWidthScale
operator|+
literal|1
expr_stmt|;
block|}
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
name|settings
operator|.
name|scale
argument_list|,
name|settings
operator|.
name|scale
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|ShapeRenderer
operator|.
name|instance
operator|.
name|end
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|render
parameter_list|(
name|String
name|text
parameter_list|,
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|,
name|int
name|color
parameter_list|)
block|{
name|this
operator|.
name|render
argument_list|(
name|text
argument_list|,
name|x
operator|+
literal|1
operator|*
name|settings
operator|.
name|scale
argument_list|,
name|y
operator|+
literal|1
operator|*
name|settings
operator|.
name|scale
argument_list|,
name|color
argument_list|,
literal|true
argument_list|)
expr_stmt|;
name|renderNoShadow
argument_list|(
name|text
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|color
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|renderNoShadow
parameter_list|(
name|String
name|text
parameter_list|,
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|,
name|int
name|color
parameter_list|)
block|{
name|this
operator|.
name|render
argument_list|(
name|text
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|color
argument_list|,
literal|false
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

