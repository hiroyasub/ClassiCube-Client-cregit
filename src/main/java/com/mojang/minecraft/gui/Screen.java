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
name|render
operator|.
name|ShapeRenderer
import|;
end_import

begin_comment
comment|/**  * Base class for any kind of screen.  */
end_comment

begin_class
specifier|public
class|class
name|Screen
block|{
comment|/**      * Draws a box to the screen      *      * @param x1      *            X coordinate of the first point of the box.      * @param y1      *            Y coordinate of the first point of the box.      * @param x2      *            X coordinate of the second point of the box.      * @param y2      *            Y coordinate of the second point of the box.      * @param colorRGB      *            The color of the box. See {@Color}      */
specifier|protected
specifier|static
name|void
name|drawBox
parameter_list|(
name|float
name|x1
parameter_list|,
name|float
name|y1
parameter_list|,
name|float
name|x2
parameter_list|,
name|float
name|y2
parameter_list|,
name|int
name|colorRGB
parameter_list|)
block|{
name|float
name|alpha
init|=
operator|(
name|colorRGB
operator|>>>
literal|24
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|red
init|=
operator|(
name|colorRGB
operator|>>
literal|16
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|green
init|=
operator|(
name|colorRGB
operator|>>
literal|8
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|blue
init|=
operator|(
name|colorRGB
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|ShapeRenderer
name|renderer
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_BLEND
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBlendFunc
argument_list|(
name|GL11
operator|.
name|GL_SRC_ALPHA
argument_list|,
name|GL11
operator|.
name|GL_ONE_MINUS_SRC_ALPHA
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
name|red
argument_list|,
name|green
argument_list|,
name|blue
argument_list|,
name|alpha
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|begin
argument_list|()
expr_stmt|;
name|renderer
operator|.
name|vertex
argument_list|(
name|x1
argument_list|,
name|y2
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertex
argument_list|(
name|x2
argument_list|,
name|y2
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertex
argument_list|(
name|x2
argument_list|,
name|y1
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertex
argument_list|(
name|x1
argument_list|,
name|y1
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|end
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_BLEND
argument_list|)
expr_stmt|;
block|}
comment|/**      * Draws a box to the screen      *      * @param x1      *            X coordinate of the first point of the box.      * @param y1      *            Y coordinate of the first point of the box.      * @param x2      *            X coordinate of the second point of the box.      * @param y2      *            Y coordinate of the second point of the box.      * @param colorRGB      *            The color of the box. See {@Color}      */
specifier|protected
specifier|static
name|void
name|drawBox
parameter_list|(
name|int
name|x1
parameter_list|,
name|int
name|y1
parameter_list|,
name|int
name|x2
parameter_list|,
name|int
name|y2
parameter_list|,
name|int
name|colorRGB
parameter_list|)
block|{
name|drawBox
argument_list|(
operator|(
name|float
operator|)
name|x1
argument_list|,
operator|(
name|float
operator|)
name|y1
argument_list|,
operator|(
name|float
operator|)
name|x2
argument_list|,
operator|(
name|float
operator|)
name|y2
argument_list|,
name|colorRGB
argument_list|)
expr_stmt|;
block|}
comment|/**      * Draws a string that is centered.      *      * @param renderer      *            {@FontRenderer} used to render the used font.      * @param text      *            Text to draw and center      * @param x      *            X-Coordinate of position to draw.      * @param y      *            Y-Coordinate of position to draw.      * @param colorRGB      *            The color of the box. See {@Color}      */
specifier|public
specifier|static
name|void
name|drawCenteredString
parameter_list|(
name|FontRenderer
name|renderer
parameter_list|,
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
name|colorRGB
parameter_list|)
block|{
comment|// Measure the length of the text with the current font and then divide
comment|// it by two
name|drawString
argument_list|(
name|renderer
argument_list|,
name|text
argument_list|,
name|x
operator|-
name|renderer
operator|.
name|getWidth
argument_list|(
name|text
argument_list|)
operator|/
literal|2
argument_list|,
name|y
argument_list|,
name|colorRGB
argument_list|)
expr_stmt|;
block|}
comment|/**      * Draws a given string      *      * @param renderer      *            {@FontRenderer} used to render the used font.      * @param text      *            Text to draw      * @param x      *            X-Coordinate of position to draw.      * @param y      *            Y-Coordinate of position to draw.      * @param colorRGB      *            The color of the box. See {@Color}      */
specifier|public
specifier|static
name|void
name|drawString
parameter_list|(
name|FontRenderer
name|renderer
parameter_list|,
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
name|colorRGB
parameter_list|)
block|{
name|renderer
operator|.
name|render
argument_list|(
name|text
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|colorRGB
argument_list|)
expr_stmt|;
block|}
specifier|protected
specifier|static
name|void
name|drawFadingBox
parameter_list|(
name|int
name|var0
parameter_list|,
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|,
name|int
name|var3
parameter_list|,
name|int
name|var4
parameter_list|,
name|int
name|var5
parameter_list|)
block|{
name|GL11
operator|.
name|glAlphaFunc
argument_list|(
literal|516
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|float
name|var6
init|=
operator|(
name|var4
operator|>>>
literal|24
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|var7
init|=
operator|(
name|var4
operator|>>
literal|16
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|var8
init|=
operator|(
name|var4
operator|>>
literal|8
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|var12
init|=
operator|(
name|var4
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|var9
init|=
operator|(
name|var5
operator|>>>
literal|24
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|var10
init|=
operator|(
name|var5
operator|>>
literal|16
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|var11
init|=
operator|(
name|var5
operator|>>
literal|8
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|var13
init|=
operator|(
name|var5
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3553
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|3042
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBlendFunc
argument_list|(
literal|770
argument_list|,
literal|771
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBegin
argument_list|(
literal|7
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
name|var7
argument_list|,
name|var8
argument_list|,
name|var12
argument_list|,
name|var6
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
name|var2
argument_list|,
name|var1
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
name|var0
argument_list|,
name|var1
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
name|var10
argument_list|,
name|var11
argument_list|,
name|var13
argument_list|,
name|var9
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
name|var0
argument_list|,
name|var3
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnd
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3042
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|3553
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glAlphaFunc
argument_list|(
literal|516
argument_list|,
literal|0.5F
argument_list|)
expr_stmt|;
block|}
specifier|protected
name|float
name|imgZ
init|=
literal|0
decl_stmt|;
specifier|public
specifier|final
name|void
name|drawImage
parameter_list|(
name|int
name|screenX
parameter_list|,
name|int
name|screenY
parameter_list|,
name|int
name|u
parameter_list|,
name|int
name|v
parameter_list|,
name|int
name|width
parameter_list|,
name|int
name|height
parameter_list|)
block|{
name|float
name|var7
init|=
literal|0.00390625F
decl_stmt|;
name|float
name|var8
init|=
literal|0.00390625F
decl_stmt|;
name|ShapeRenderer
name|renderer
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|renderer
operator|.
name|begin
argument_list|()
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|screenX
argument_list|,
name|screenY
operator|+
name|height
argument_list|,
name|imgZ
argument_list|,
name|u
operator|*
name|var7
argument_list|,
operator|(
name|v
operator|+
name|height
operator|)
operator|*
name|var8
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|screenX
operator|+
name|width
argument_list|,
name|screenY
operator|+
name|height
argument_list|,
name|imgZ
argument_list|,
operator|(
name|u
operator|+
name|width
operator|)
operator|*
name|var7
argument_list|,
operator|(
name|v
operator|+
name|height
operator|)
operator|*
name|var8
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|screenX
operator|+
name|width
argument_list|,
name|screenY
argument_list|,
name|imgZ
argument_list|,
operator|(
name|u
operator|+
name|width
operator|)
operator|*
name|var7
argument_list|,
name|v
operator|*
name|var8
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|screenX
argument_list|,
name|screenY
argument_list|,
name|imgZ
argument_list|,
name|u
operator|*
name|var7
argument_list|,
name|v
operator|*
name|var8
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|end
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

