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

begin_class
specifier|public
specifier|final
class|class
name|FontRenderer
block|{
specifier|public
name|int
name|charHeight
decl_stmt|;
specifier|public
name|int
name|charWidth
decl_stmt|;
specifier|public
name|int
index|[]
name|font
init|=
operator|new
name|int
index|[
literal|256
index|]
decl_stmt|;
specifier|private
name|int
name|fontId
init|=
literal|0
decl_stmt|;
specifier|private
name|GameSettings
name|settings
decl_stmt|;
specifier|public
name|FontRenderer
parameter_list|(
name|GameSettings
name|settings
parameter_list|,
name|String
name|fontImage
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
try|try
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
name|fontImage
argument_list|)
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
throw|throw
operator|new
name|IOException
argument_list|(
literal|"Missing resource"
argument_list|)
throw|;
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
name|charWidth
operator|=
name|width
expr_stmt|;
name|charHeight
operator|=
name|height
expr_stmt|;
name|int
index|[]
name|fontData
init|=
operator|new
name|int
index|[
literal|256
operator|*
literal|256
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
for|for
control|(
name|int
name|character
init|=
literal|0
init|;
name|character
operator|<
literal|256
condition|;
operator|++
name|character
control|)
block|{
name|int
name|var6
init|=
name|character
operator|%
literal|16
decl_stmt|;
name|int
name|var7
init|=
name|character
operator|/
literal|16
decl_stmt|;
name|float
name|chWidth
init|=
literal|0
decl_stmt|;
for|for
control|(
name|boolean
name|var9
init|=
literal|false
init|;
name|chWidth
operator|<
literal|8
operator|&&
operator|!
name|var9
condition|;
name|chWidth
operator|++
control|)
block|{
name|int
name|var10
init|=
operator|(
name|var6
operator|<<
literal|3
operator|)
operator|+
operator|(
name|int
operator|)
name|chWidth
decl_stmt|;
name|var9
operator|=
literal|true
expr_stmt|;
for|for
control|(
name|int
name|var11
init|=
literal|0
init|;
name|var11
operator|<
literal|8
operator|&&
name|var9
condition|;
operator|++
name|var11
control|)
block|{
name|int
name|var12
init|=
operator|(
operator|(
name|var7
operator|<<
literal|3
operator|)
operator|+
name|var11
operator|)
operator|*
name|width
decl_stmt|;
if|if
condition|(
operator|(
name|fontData
index|[
name|var10
operator|+
name|var12
index|]
operator|&
literal|255
operator|)
operator|>
literal|128
condition|)
block|{
name|var9
operator|=
literal|false
expr_stmt|;
block|}
block|}
block|}
if|if
condition|(
name|character
operator|==
literal|32
condition|)
block|{
name|chWidth
operator|=
literal|4
operator|*
name|this
operator|.
name|settings
operator|.
name|scale
expr_stmt|;
block|}
name|this
operator|.
name|font
index|[
name|character
index|]
operator|=
operator|(
name|int
operator|)
name|chWidth
expr_stmt|;
block|}
name|fontId
operator|=
name|textures
operator|.
name|load
argument_list|(
name|fontImage
argument_list|)
expr_stmt|;
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
name|float
name|getScale
parameter_list|()
block|{
return|return
literal|7F
operator|/
name|charHeight
operator|*
name|settings
operator|.
name|scale
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
name|char
index|[]
name|arrayOfChar
init|=
name|text
operator|.
name|toCharArray
argument_list|()
decl_stmt|;
name|int
name|i
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
name|arrayOfChar
operator|.
name|length
condition|;
name|j
operator|++
control|)
block|{
name|int
name|k
init|=
name|arrayOfChar
index|[
name|j
index|]
decl_stmt|;
if|if
condition|(
name|k
operator|==
literal|38
condition|)
block|{
name|j
operator|++
expr_stmt|;
block|}
else|else
block|{
name|i
operator|+=
name|font
index|[
name|k
index|]
expr_stmt|;
block|}
block|}
return|return
operator|(
name|int
operator|)
name|Math
operator|.
name|floor
argument_list|(
name|i
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
name|charHeight
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
operator|!=
literal|null
condition|)
block|{
name|char
index|[]
name|chars
init|=
name|text
operator|.
name|toCharArray
argument_list|()
decl_stmt|;
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
name|float
name|f1
init|=
name|settings
operator|.
name|scale
decl_stmt|;
name|float
name|f2
init|=
literal|1F
operator|/
name|f1
decl_stmt|;
name|x
operator|=
name|x
operator|*
name|f2
expr_stmt|;
name|y
operator|=
name|y
operator|*
name|f2
expr_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|fontId
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
name|int
name|var7
init|=
literal|0
decl_stmt|;
for|for
control|(
name|int
name|count
init|=
literal|0
init|;
name|count
operator|<
name|chars
operator|.
name|length
condition|;
operator|++
name|count
control|)
block|{
if|if
condition|(
name|chars
index|[
name|count
index|]
operator|==
literal|'&'
operator|&&
name|chars
operator|.
name|length
operator|>
name|count
operator|+
literal|1
condition|)
block|{
name|int
name|code
init|=
literal|"0123456789abcdef"
operator|.
name|indexOf
argument_list|(
name|chars
index|[
name|count
operator|+
literal|1
index|]
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
name|chars
operator|.
name|length
operator|-
literal|2
operator|==
name|count
condition|)
block|{
break|break;
block|}
name|count
operator|+=
literal|2
expr_stmt|;
block|}
name|color
operator|=
name|chars
index|[
name|count
index|]
operator|%
literal|16
operator|<<
literal|3
expr_stmt|;
name|int
name|var9
init|=
name|chars
index|[
name|count
index|]
operator|/
literal|16
operator|<<
literal|3
decl_stmt|;
name|float
name|var13
init|=
literal|7.99F
decl_stmt|;
name|ShapeRenderer
operator|.
name|instance
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|var7
argument_list|,
name|y
operator|+
name|var13
argument_list|,
literal|0F
argument_list|,
name|color
operator|/
literal|128F
argument_list|,
operator|(
name|var9
operator|+
name|var13
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
name|var7
operator|+
name|var13
argument_list|,
name|y
operator|+
name|var13
argument_list|,
literal|0F
argument_list|,
operator|(
name|color
operator|+
name|var13
operator|)
operator|/
literal|128F
argument_list|,
operator|(
name|var9
operator|+
name|var13
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
name|var7
operator|+
name|var13
argument_list|,
name|y
argument_list|,
literal|0F
argument_list|,
operator|(
name|color
operator|+
name|var13
operator|)
operator|/
literal|128F
argument_list|,
name|var9
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
name|var7
argument_list|,
name|y
argument_list|,
literal|0F
argument_list|,
name|color
operator|/
literal|128F
argument_list|,
name|var9
operator|/
literal|128F
argument_list|)
expr_stmt|;
if|if
condition|(
name|chars
index|[
name|count
index|]
operator|<
name|font
operator|.
name|length
condition|)
block|{
name|var7
operator|+=
name|font
index|[
name|chars
index|[
name|count
index|]
index|]
expr_stmt|;
block|}
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
name|f1
argument_list|,
name|f1
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

