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
name|render
operator|.
name|ShapeRenderer
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

begin_class
specifier|public
class|class
name|Screen
block|{
specifier|protected
specifier|static
name|void
name|drawBox
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
parameter_list|)
block|{
name|float
name|var5
init|=
operator|(
name|float
operator|)
operator|(
name|var4
operator|>>>
literal|24
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var6
init|=
operator|(
name|float
operator|)
operator|(
name|var4
operator|>>
literal|16
operator|&
literal|255
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var7
init|=
operator|(
name|float
operator|)
operator|(
name|var4
operator|>>
literal|8
operator|&
literal|255
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var9
init|=
operator|(
name|float
operator|)
operator|(
name|var4
operator|&
literal|255
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|ShapeRenderer
name|var8
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|3042
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3553
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
name|glColor4f
argument_list|(
name|var6
argument_list|,
name|var7
argument_list|,
name|var9
argument_list|,
name|var5
argument_list|)
expr_stmt|;
name|var8
operator|.
name|begin
argument_list|()
expr_stmt|;
name|var8
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
name|var0
argument_list|,
operator|(
name|float
operator|)
name|var3
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var8
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
name|var2
argument_list|,
operator|(
name|float
operator|)
name|var3
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var8
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
name|var2
argument_list|,
operator|(
name|float
operator|)
name|var1
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var8
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
name|var0
argument_list|,
operator|(
name|float
operator|)
name|var1
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var8
operator|.
name|end
argument_list|()
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
name|glDisable
argument_list|(
literal|3042
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|static
name|void
name|drawCenteredString
parameter_list|(
name|FontRenderer
name|var0
parameter_list|,
name|String
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
parameter_list|)
block|{
name|var0
operator|.
name|render
argument_list|(
name|var1
argument_list|,
name|var2
operator|-
name|var0
operator|.
name|getWidth
argument_list|(
name|var1
argument_list|)
operator|/
literal|2
argument_list|,
name|var3
argument_list|,
name|var4
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
literal|0.0F
argument_list|)
expr_stmt|;
name|float
name|var6
init|=
operator|(
name|float
operator|)
operator|(
name|var4
operator|>>>
literal|24
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var7
init|=
operator|(
name|float
operator|)
operator|(
name|var4
operator|>>
literal|16
operator|&
literal|255
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var8
init|=
operator|(
name|float
operator|)
operator|(
name|var4
operator|>>
literal|8
operator|&
literal|255
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var12
init|=
operator|(
name|float
operator|)
operator|(
name|var4
operator|&
literal|255
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var9
init|=
operator|(
name|float
operator|)
operator|(
name|var5
operator|>>>
literal|24
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var10
init|=
operator|(
name|float
operator|)
operator|(
name|var5
operator|>>
literal|16
operator|&
literal|255
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var11
init|=
operator|(
name|float
operator|)
operator|(
name|var5
operator|>>
literal|8
operator|&
literal|255
operator|)
operator|/
literal|255.0F
decl_stmt|;
name|float
name|var13
init|=
operator|(
name|float
operator|)
operator|(
name|var5
operator|&
literal|255
operator|)
operator|/
literal|255.0F
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
operator|(
name|float
operator|)
name|var2
argument_list|,
operator|(
name|float
operator|)
name|var1
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
operator|(
name|float
operator|)
name|var0
argument_list|,
operator|(
name|float
operator|)
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
operator|(
name|float
operator|)
name|var0
argument_list|,
operator|(
name|float
operator|)
name|var3
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
operator|(
name|float
operator|)
name|var2
argument_list|,
operator|(
name|float
operator|)
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
block|}
specifier|public
specifier|static
name|void
name|drawString
parameter_list|(
name|FontRenderer
name|var0
parameter_list|,
name|String
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
parameter_list|)
block|{
name|var0
operator|.
name|render
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
expr_stmt|;
block|}
specifier|protected
name|float
name|imgZ
init|=
literal|0.0F
decl_stmt|;
specifier|public
specifier|final
name|void
name|drawImage
parameter_list|(
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
parameter_list|,
name|int
name|var6
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
name|var9
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|ShapeRenderer
operator|.
name|instance
operator|.
name|begin
argument_list|()
expr_stmt|;
name|var9
operator|.
name|vertexUV
argument_list|(
operator|(
name|float
operator|)
name|var1
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var2
operator|+
name|var6
operator|)
argument_list|,
name|this
operator|.
name|imgZ
argument_list|,
operator|(
name|float
operator|)
name|var3
operator|*
name|var7
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var4
operator|+
name|var6
operator|)
operator|*
name|var8
argument_list|)
expr_stmt|;
name|var9
operator|.
name|vertexUV
argument_list|(
operator|(
name|float
operator|)
operator|(
name|var1
operator|+
name|var5
operator|)
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var2
operator|+
name|var6
operator|)
argument_list|,
name|this
operator|.
name|imgZ
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var3
operator|+
name|var5
operator|)
operator|*
name|var7
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var4
operator|+
name|var6
operator|)
operator|*
name|var8
argument_list|)
expr_stmt|;
name|var9
operator|.
name|vertexUV
argument_list|(
operator|(
name|float
operator|)
operator|(
name|var1
operator|+
name|var5
operator|)
argument_list|,
operator|(
name|float
operator|)
name|var2
argument_list|,
name|this
operator|.
name|imgZ
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var3
operator|+
name|var5
operator|)
operator|*
name|var7
argument_list|,
operator|(
name|float
operator|)
name|var4
operator|*
name|var8
argument_list|)
expr_stmt|;
name|var9
operator|.
name|vertexUV
argument_list|(
operator|(
name|float
operator|)
name|var1
argument_list|,
operator|(
name|float
operator|)
name|var2
argument_list|,
name|this
operator|.
name|imgZ
argument_list|,
operator|(
name|float
operator|)
name|var3
operator|*
name|var7
argument_list|,
operator|(
name|float
operator|)
name|var4
operator|*
name|var8
argument_list|)
expr_stmt|;
name|var9
operator|.
name|end
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

