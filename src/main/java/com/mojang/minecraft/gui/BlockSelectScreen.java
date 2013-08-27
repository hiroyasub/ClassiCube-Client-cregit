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
name|SessionData
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
name|level
operator|.
name|tile
operator|.
name|Block
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
name|level
operator|.
name|tile
operator|.
name|BlockID
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
specifier|final
class|class
name|BlockSelectScreen
extends|extends
name|GuiScreen
block|{
name|int
name|BlocksPerRow
init|=
literal|13
decl_stmt|;
name|int
name|Spacing
init|=
literal|20
decl_stmt|;
specifier|public
name|BlockSelectScreen
parameter_list|()
block|{
name|this
operator|.
name|grabsMouse
operator|=
literal|true
expr_stmt|;
block|}
specifier|private
name|int
name|getBlockOnScreen
parameter_list|(
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
for|for
control|(
name|int
name|var3
init|=
literal|0
init|;
name|var3
operator|<
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var3
control|)
block|{
name|int
name|var4
init|=
name|this
operator|.
name|width
operator|/
literal|2
operator|+
name|var3
operator|%
name|BlocksPerRow
operator|*
name|Spacing
operator|+
operator|-
literal|128
operator|-
literal|3
decl_stmt|;
name|int
name|var5
init|=
name|this
operator|.
name|height
operator|/
literal|2
operator|+
name|var3
operator|/
name|BlocksPerRow
operator|*
name|Spacing
operator|+
operator|-
literal|60
operator|+
literal|3
decl_stmt|;
if|if
condition|(
name|var1
operator|>=
name|var4
operator|&&
name|var1
operator|<=
name|var4
operator|+
literal|22
operator|&&
name|var2
operator|>=
name|var5
operator|-
name|BlocksPerRow
operator|&&
name|var2
operator|<=
name|var5
operator|+
name|BlocksPerRow
condition|)
block|{
return|return
name|var3
return|;
block|}
block|}
return|return
operator|-
literal|1
return|;
block|}
name|float
name|lastRotation
init|=
literal|0
decl_stmt|;
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
name|var1
operator|=
name|this
operator|.
name|getBlockOnScreen
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
name|drawFadingBox
argument_list|(
name|this
operator|.
name|width
operator|/
literal|2
operator|-
literal|140
argument_list|,
literal|30
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
operator|+
literal|140
argument_list|,
literal|180
argument_list|,
operator|-
literal|1878719232
argument_list|,
operator|-
literal|1070583712
argument_list|)
expr_stmt|;
if|if
condition|(
name|var1
operator|>=
literal|0
condition|)
block|{
name|var2
operator|=
name|this
operator|.
name|width
operator|/
literal|2
operator|+
name|var1
operator|%
name|BlocksPerRow
operator|*
name|Spacing
operator|+
operator|-
literal|128
expr_stmt|;
name|drawCenteredString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
name|GetBlockName
argument_list|(
name|var1
argument_list|)
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
argument_list|,
literal|165
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
name|drawCenteredString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
literal|"Select block"
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
argument_list|,
literal|40
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|TextureManager
name|var7
init|=
name|this
operator|.
name|minecraft
operator|.
name|textureManager
decl_stmt|;
name|ShapeRenderer
name|var8
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|var2
operator|=
name|var7
operator|.
name|load
argument_list|(
literal|"/terrain.png"
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|var2
argument_list|)
expr_stmt|;
for|for
control|(
name|var2
operator|=
literal|0
init|;
name|var2
operator|<
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var2
control|)
block|{
name|Block
name|var4
init|=
operator|(
name|Block
operator|)
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|get
argument_list|(
name|var2
argument_list|)
decl_stmt|;
if|if
condition|(
name|var4
operator|!=
literal|null
condition|)
block|{
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|int
name|var5
init|=
name|this
operator|.
name|width
operator|/
literal|2
operator|+
name|var2
operator|%
name|BlocksPerRow
operator|*
name|Spacing
operator|+
operator|-
literal|128
decl_stmt|;
name|int
name|var6
init|=
name|this
operator|.
name|height
operator|/
literal|2
operator|+
name|var2
operator|/
name|BlocksPerRow
operator|*
name|Spacing
operator|+
operator|-
literal|60
decl_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
operator|(
name|float
operator|)
name|var5
argument_list|,
operator|(
name|float
operator|)
name|var6
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|9.0F
argument_list|,
literal|9.0F
argument_list|,
literal|9.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
literal|1.0F
argument_list|,
literal|0.5F
argument_list|,
literal|8.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
literal|30.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
literal|45.0F
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
if|if
condition|(
name|var1
operator|==
name|var2
condition|)
block|{
name|lastRotation
operator|+=
literal|0.7F
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|1.6F
argument_list|,
literal|1.6F
argument_list|,
literal|1.6F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
name|lastRotation
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glTranslatef
argument_list|(
operator|-
literal|1.5F
argument_list|,
literal|0.5F
argument_list|,
literal|0.5F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|)
expr_stmt|;
name|var8
operator|.
name|begin
argument_list|()
expr_stmt|;
name|var4
operator|.
name|renderFullbright
argument_list|(
name|var8
argument_list|)
expr_stmt|;
name|var8
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
block|}
name|String
name|GetBlockName
parameter_list|(
name|int
name|id
parameter_list|)
block|{
name|String
name|s
decl_stmt|;
if|if
condition|(
name|id
operator|==
literal|0
operator|||
name|id
operator|==
literal|255
condition|)
return|return
literal|""
return|;
try|try
block|{
name|Block
name|b
init|=
operator|(
name|Block
operator|)
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|get
argument_list|(
name|id
argument_list|)
decl_stmt|;
if|if
condition|(
name|b
operator|==
literal|null
condition|)
return|return
literal|""
return|;
name|int
name|ID
init|=
name|b
operator|.
name|id
decl_stmt|;
name|BlockID
name|bid
init|=
name|BlockID
operator|.
name|values
argument_list|()
index|[
name|ID
operator|+
literal|1
index|]
decl_stmt|;
if|if
condition|(
name|bid
operator|==
literal|null
condition|)
name|s
operator|=
literal|""
expr_stmt|;
else|else
name|s
operator|=
name|bid
operator|.
name|name
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
return|return
literal|""
return|;
block|}
return|return
name|s
return|;
block|}
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
condition|)
block|{
name|this
operator|.
name|minecraft
operator|.
name|player
operator|.
name|inventory
operator|.
name|replaceSlot
argument_list|(
name|this
operator|.
name|getBlockOnScreen
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
argument_list|)
expr_stmt|;
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
block|}
block|}
block|}
end_class

end_unit

