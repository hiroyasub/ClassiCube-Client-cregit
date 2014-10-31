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
name|Timer
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|TimerTask
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
name|BlockSelectScreen
extends|extends
name|GuiScreen
block|{
specifier|private
specifier|final
name|Timer
name|timer
init|=
operator|new
name|Timer
argument_list|()
decl_stmt|;
specifier|private
specifier|final
name|int
name|milliseconds
init|=
literal|30
decl_stmt|;
specifier|public
name|TimerTask
name|timertask
decl_stmt|;
name|boolean
name|defaultSizeBlocks
init|=
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|size
argument_list|()
operator|<=
literal|50
decl_stmt|;
name|int
name|blocksPerRow
init|=
literal|13
decl_stmt|;
name|int
name|spacing
init|=
literal|20
decl_stmt|;
name|float
name|lastRotation
init|=
literal|0
decl_stmt|;
specifier|public
name|BlockSelectScreen
parameter_list|()
block|{
name|grabsMouse
operator|=
literal|true
expr_stmt|;
name|start
argument_list|()
expr_stmt|;
if|if
condition|(
name|defaultSizeBlocks
condition|)
block|{
name|blocksPerRow
operator|=
literal|11
expr_stmt|;
name|spacing
operator|=
literal|24
expr_stmt|;
block|}
block|}
name|String
name|getBlockName
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
operator|<
literal|0
operator|||
name|id
operator|>
literal|255
condition|)
block|{
return|return
literal|""
return|;
block|}
try|try
block|{
name|Block
name|b
init|=
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
block|{
return|return
literal|""
return|;
block|}
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
block|{
name|s
operator|=
literal|""
expr_stmt|;
block|}
else|else
block|{
name|s
operator|=
name|bid
operator|.
name|name
argument_list|()
expr_stmt|;
block|}
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
specifier|private
name|int
name|getBlockOnScreen
parameter_list|(
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|)
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
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|size
argument_list|()
condition|;
operator|++
name|i
control|)
block|{
name|int
name|var4
init|=
name|width
operator|/
literal|2
operator|+
name|i
operator|%
name|blocksPerRow
operator|*
name|spacing
operator|+
operator|-
literal|128
operator|-
literal|3
decl_stmt|;
name|int
name|var5
init|=
name|height
operator|/
literal|2
operator|+
name|i
operator|/
name|blocksPerRow
operator|*
name|spacing
operator|+
operator|-
literal|60
operator|+
literal|3
decl_stmt|;
if|if
condition|(
name|x
operator|>=
name|var4
operator|&&
name|x
operator|<=
name|var4
operator|+
literal|22
operator|&&
name|y
operator|>=
name|var5
operator|-
name|blocksPerRow
operator|&&
name|y
operator|<=
name|var5
operator|+
name|blocksPerRow
condition|)
block|{
return|return
name|i
return|;
block|}
block|}
return|return
operator|-
literal|1
return|;
block|}
annotation|@
name|Override
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
name|clickType
operator|==
literal|0
condition|)
block|{
name|minecraft
operator|.
name|player
operator|.
name|inventory
operator|.
name|replaceSlot
argument_list|(
name|getBlockOnScreen
argument_list|(
name|x
argument_list|,
name|y
argument_list|)
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
literal|null
argument_list|)
expr_stmt|;
block|}
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
name|var1
operator|=
name|getBlockOnScreen
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
if|if
condition|(
name|defaultSizeBlocks
condition|)
block|{
name|drawFadingBox
argument_list|(
name|width
operator|/
literal|2
operator|-
literal|140
argument_list|,
literal|30
argument_list|,
name|width
operator|/
literal|2
operator|+
literal|140
argument_list|,
literal|195
argument_list|,
operator|-
literal|1878719232
argument_list|,
operator|-
literal|1070583712
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|drawFadingBox
argument_list|(
name|width
operator|/
literal|2
operator|-
literal|140
argument_list|,
literal|30
argument_list|,
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
block|}
if|if
condition|(
name|var1
operator|>=
literal|0
condition|)
block|{
name|var2
operator|=
name|width
operator|/
literal|2
operator|+
name|var1
operator|%
name|blocksPerRow
operator|*
name|spacing
operator|+
operator|-
literal|128
expr_stmt|;
if|if
condition|(
name|defaultSizeBlocks
condition|)
block|{
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
name|getBlockName
argument_list|(
name|var1
argument_list|)
argument_list|,
name|width
operator|/
literal|2
argument_list|,
literal|180
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
name|getBlockName
argument_list|(
name|var1
argument_list|)
argument_list|,
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
block|}
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
literal|"Select block"
argument_list|,
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
name|textureManager
init|=
name|minecraft
operator|.
name|textureManager
decl_stmt|;
name|ShapeRenderer
name|shapeRenderer
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|var2
operator|=
name|textureManager
operator|.
name|load
argument_list|(
name|Textures
operator|.
name|TERRAIN
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
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|size
argument_list|()
condition|;
operator|++
name|i
control|)
block|{
name|Block
name|block
init|=
name|SessionData
operator|.
name|allowedBlocks
operator|.
name|get
argument_list|(
name|i
argument_list|)
decl_stmt|;
if|if
condition|(
name|block
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
name|width
operator|/
literal|2
operator|+
name|i
operator|%
name|blocksPerRow
operator|*
name|spacing
operator|+
operator|-
literal|128
decl_stmt|;
name|int
name|var6
init|=
name|height
operator|/
literal|2
operator|+
name|i
operator|/
name|blocksPerRow
operator|*
name|spacing
operator|+
operator|-
literal|60
decl_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
name|var5
argument_list|,
name|var6
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|9F
argument_list|,
literal|9F
argument_list|,
literal|9F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
literal|1F
argument_list|,
literal|0.5F
argument_list|,
literal|8F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
literal|30F
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
literal|45F
argument_list|,
literal|0F
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
if|if
condition|(
name|var1
operator|==
name|i
condition|)
block|{
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
literal|0F
argument_list|,
literal|1F
argument_list|,
literal|0F
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
literal|1F
argument_list|,
operator|-
literal|1F
argument_list|,
operator|-
literal|1F
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|begin
argument_list|()
expr_stmt|;
name|block
operator|.
name|renderFullBrightness
argument_list|(
name|shapeRenderer
argument_list|)
expr_stmt|;
name|shapeRenderer
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
name|void
name|rotate
parameter_list|()
block|{
name|lastRotation
operator|+=
literal|2.7F
expr_stmt|;
block|}
specifier|public
name|void
name|start
parameter_list|()
block|{
name|timer
operator|.
name|scheduleAtFixedRate
argument_list|(
operator|new
name|TimerTask
argument_list|()
block|{
annotation|@
name|Override
specifier|public
name|void
name|run
parameter_list|()
block|{
name|rotate
argument_list|()
expr_stmt|;
block|}
block|}
argument_list|,
name|milliseconds
argument_list|,
name|milliseconds
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

