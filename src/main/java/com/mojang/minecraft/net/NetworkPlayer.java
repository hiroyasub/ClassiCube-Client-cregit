begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|net
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
name|util
operator|.
name|LinkedList
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
name|FontRenderer
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
name|mob
operator|.
name|HumanoidMob
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
class|class
name|NetworkPlayer
extends|extends
name|HumanoidMob
block|{
specifier|public
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|77479605454997290L
decl_stmt|;
specifier|public
specifier|static
name|boolean
name|isInteger
parameter_list|(
name|String
name|s
parameter_list|)
block|{
try|try
block|{
name|Integer
operator|.
name|parseInt
argument_list|(
name|s
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|NumberFormatException
name|e
parameter_list|)
block|{
return|return
literal|false
return|;
block|}
return|return
literal|true
return|;
block|}
specifier|public
specifier|transient
name|List
argument_list|<
name|PositionUpdate
argument_list|>
name|moveQueue
init|=
operator|new
name|LinkedList
argument_list|<>
argument_list|()
decl_stmt|;
specifier|private
specifier|transient
name|Minecraft
name|minecraft
decl_stmt|;
specifier|private
name|int
name|xp
decl_stmt|;
specifier|private
name|int
name|yp
decl_stmt|;
specifier|private
name|int
name|zp
decl_stmt|;
specifier|private
specifier|transient
name|int
name|a
init|=
operator|-
literal|1
decl_stmt|;
specifier|public
specifier|transient
name|BufferedImage
name|newTexture
init|=
literal|null
decl_stmt|;
specifier|public
name|String
name|name
decl_stmt|;
specifier|public
name|String
name|displayName
decl_stmt|;
name|int
name|tickCount
init|=
literal|0
decl_stmt|;
specifier|private
specifier|transient
name|TextureManager
name|textures
decl_stmt|;
specifier|public
name|String
name|SkinName
init|=
literal|null
decl_stmt|;
specifier|public
name|NetworkPlayer
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|,
name|String
name|displayName
parameter_list|,
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|,
name|int
name|z
parameter_list|,
name|float
name|xRot
parameter_list|,
name|float
name|yRot
parameter_list|)
block|{
name|super
argument_list|(
name|minecraft
operator|.
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
name|this
operator|.
name|displayName
operator|=
name|displayName
expr_stmt|;
name|displayName
operator|=
name|FontRenderer
operator|.
name|stripColor
argument_list|(
name|displayName
argument_list|)
expr_stmt|;
name|name
operator|=
name|displayName
expr_stmt|;
name|xp
operator|=
name|x
expr_stmt|;
name|yp
operator|=
name|y
expr_stmt|;
name|zp
operator|=
name|z
expr_stmt|;
name|heightOffset
operator|=
literal|0F
expr_stmt|;
name|pushthrough
operator|=
literal|0.8F
expr_stmt|;
name|this
operator|.
name|setPos
argument_list|(
name|x
operator|/
literal|32F
argument_list|,
name|y
operator|/
literal|32F
argument_list|,
name|z
operator|/
literal|32F
argument_list|)
expr_stmt|;
name|this
operator|.
name|xRot
operator|=
name|xRot
expr_stmt|;
name|this
operator|.
name|yRot
operator|=
name|yRot
expr_stmt|;
name|armor
operator|=
name|helmet
operator|=
literal|false
expr_stmt|;
name|renderOffset
operator|=
literal|0.6875F
expr_stmt|;
name|allowAlpha
operator|=
literal|false
expr_stmt|;
comment|/*if (name.equalsIgnoreCase("Jonty800") || name.equalsIgnoreCase("Jonty800+")                 || name.equalsIgnoreCase("Jonty800@")) {             modelName = "sheep";         }*/
if|if
condition|(
name|modelName
operator|.
name|equals
argument_list|(
literal|"humanoid"
argument_list|)
condition|)
block|{
name|downloadSkin
argument_list|()
expr_stmt|;
block|}
if|else if
condition|(
name|isInteger
argument_list|(
name|modelName
argument_list|)
condition|)
block|{
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
literal|"/terrain.png"
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|aiStep
parameter_list|()
block|{
name|int
name|var1
init|=
literal|5
decl_stmt|;
if|if
condition|(
name|moveQueue
operator|!=
literal|null
condition|)
block|{
do|do
block|{
if|if
condition|(
name|moveQueue
operator|.
name|size
argument_list|()
operator|>
literal|0
condition|)
block|{
name|this
operator|.
name|setPos
argument_list|(
name|moveQueue
operator|.
name|remove
argument_list|(
literal|0
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
do|while
condition|(
name|var1
operator|--
operator|>
literal|0
operator|&&
name|moveQueue
operator|.
name|size
argument_list|()
operator|>
literal|10
condition|)
do|;
block|}
name|onGround
operator|=
literal|true
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|bindTexture
parameter_list|(
name|TextureManager
name|textureManager
parameter_list|)
block|{
name|textures
operator|=
name|textureManager
expr_stmt|;
if|if
condition|(
name|newTexture
operator|!=
literal|null
condition|)
block|{
name|BufferedImage
name|var2
init|=
name|newTexture
decl_stmt|;
name|int
index|[]
name|var3
init|=
operator|new
name|int
index|[
literal|512
index|]
decl_stmt|;
name|var2
operator|.
name|getRGB
argument_list|(
literal|32
argument_list|,
literal|0
argument_list|,
literal|32
argument_list|,
literal|16
argument_list|,
name|var3
argument_list|,
literal|0
argument_list|,
literal|32
argument_list|)
expr_stmt|;
name|int
name|var5
init|=
literal|0
decl_stmt|;
name|boolean
name|var10001
decl_stmt|;
while|while
condition|(
literal|true
condition|)
block|{
if|if
condition|(
name|var5
operator|>=
name|var3
operator|.
name|length
condition|)
block|{
name|var10001
operator|=
literal|false
expr_stmt|;
break|break;
block|}
if|if
condition|(
name|var3
index|[
name|var5
index|]
operator|>>>
literal|24
operator|<
literal|128
condition|)
block|{
name|var10001
operator|=
literal|true
expr_stmt|;
break|break;
block|}
operator|++
name|var5
expr_stmt|;
block|}
name|hasHair
operator|=
name|var10001
expr_stmt|;
if|if
condition|(
name|modelName
operator|.
name|equals
argument_list|(
literal|"humanoid"
argument_list|)
condition|)
block|{
name|a
operator|=
name|textureManager
operator|.
name|load
argument_list|(
name|newTexture
argument_list|)
expr_stmt|;
block|}
name|newTexture
operator|=
literal|null
expr_stmt|;
block|}
if|if
condition|(
name|isInteger
argument_list|(
name|modelName
argument_list|)
condition|)
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|textureManager
operator|.
name|load
argument_list|(
literal|"/terrain.png"
argument_list|)
argument_list|)
expr_stmt|;
return|return;
block|}
if|else if
condition|(
operator|!
name|modelName
operator|.
name|startsWith
argument_list|(
literal|"humanoid"
argument_list|)
condition|)
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|textureManager
operator|.
name|load
argument_list|(
literal|"/mob/"
operator|+
name|modelName
operator|.
name|replace
argument_list|(
literal|'.'
argument_list|,
literal|'_'
argument_list|)
operator|+
literal|".png"
argument_list|)
argument_list|)
expr_stmt|;
return|return;
block|}
if|if
condition|(
name|a
operator|<
literal|0
condition|)
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|textureManager
operator|.
name|load
argument_list|(
literal|"/char.png"
argument_list|)
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|a
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|clear
parameter_list|()
block|{
if|if
condition|(
name|a
operator|>=
literal|0
operator|&&
name|textures
operator|!=
literal|null
condition|)
block|{
name|TextureManager
name|textureManager
init|=
name|textures
decl_stmt|;
name|textureManager
operator|.
name|textureImages
operator|.
name|remove
argument_list|(
name|Integer
operator|.
name|valueOf
argument_list|(
name|a
argument_list|)
argument_list|)
expr_stmt|;
name|textureManager
operator|.
name|idBuffer
operator|.
name|clear
argument_list|()
expr_stmt|;
name|textureManager
operator|.
name|idBuffer
operator|.
name|put
argument_list|(
name|a
argument_list|)
expr_stmt|;
name|textureManager
operator|.
name|idBuffer
operator|.
name|flip
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glDeleteTextures
argument_list|(
name|textureManager
operator|.
name|idBuffer
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|downloadSkin
parameter_list|()
block|{
operator|new
name|SkinDownloadThread
argument_list|(
name|this
argument_list|,
name|minecraft
operator|.
name|skinServer
argument_list|)
operator|.
name|start
argument_list|()
expr_stmt|;
block|}
specifier|public
name|void
name|queue
parameter_list|(
name|byte
name|x
parameter_list|,
name|byte
name|y
parameter_list|,
name|byte
name|z
parameter_list|)
block|{
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
operator|(
name|xp
operator|+
name|x
operator|/
literal|2F
operator|)
operator|/
literal|32F
argument_list|,
operator|(
name|yp
operator|+
name|y
operator|/
literal|2F
operator|)
operator|/
literal|32F
argument_list|,
operator|(
name|zp
operator|+
name|z
operator|/
literal|2F
operator|)
operator|/
literal|32F
argument_list|)
argument_list|)
expr_stmt|;
name|xp
operator|+=
name|x
expr_stmt|;
name|yp
operator|+=
name|y
expr_stmt|;
name|zp
operator|+=
name|z
expr_stmt|;
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
name|xp
operator|/
literal|32F
argument_list|,
name|yp
operator|/
literal|32F
argument_list|,
name|zp
operator|/
literal|32F
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|queue
parameter_list|(
name|byte
name|x
parameter_list|,
name|byte
name|y
parameter_list|,
name|byte
name|z
parameter_list|,
name|float
name|xRot
parameter_list|,
name|float
name|yRot
parameter_list|)
block|{
name|float
name|var6
init|=
name|yRot
operator|-
name|this
operator|.
name|yRot
decl_stmt|;
name|float
name|var7
init|=
name|xRot
operator|-
name|this
operator|.
name|xRot
decl_stmt|;
while|while
condition|(
name|var6
operator|>=
literal|180F
condition|)
block|{
name|var6
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|var6
operator|<
operator|-
literal|180F
condition|)
block|{
name|var6
operator|+=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|var7
operator|>=
literal|180F
condition|)
block|{
name|var7
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|var7
operator|<
operator|-
literal|180F
condition|)
block|{
name|var7
operator|+=
literal|360F
expr_stmt|;
block|}
name|var6
operator|=
name|this
operator|.
name|yRot
operator|+
name|var6
operator|*
literal|0.5F
expr_stmt|;
name|var7
operator|=
name|this
operator|.
name|xRot
operator|+
name|var7
operator|*
literal|0.5F
expr_stmt|;
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
operator|(
name|xp
operator|+
name|x
operator|/
literal|2F
operator|)
operator|/
literal|32F
argument_list|,
operator|(
name|yp
operator|+
name|y
operator|/
literal|2F
operator|)
operator|/
literal|32F
argument_list|,
operator|(
name|zp
operator|+
name|z
operator|/
literal|2F
operator|)
operator|/
literal|32F
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
argument_list|)
expr_stmt|;
name|xp
operator|+=
name|x
expr_stmt|;
name|yp
operator|+=
name|y
expr_stmt|;
name|zp
operator|+=
name|z
expr_stmt|;
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
name|xp
operator|/
literal|32F
argument_list|,
name|yp
operator|/
literal|32F
argument_list|,
name|zp
operator|/
literal|32F
argument_list|,
name|yRot
argument_list|,
name|xRot
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|queue
parameter_list|(
name|float
name|xRot
parameter_list|,
name|float
name|yRot
parameter_list|)
block|{
name|float
name|var3
init|=
name|yRot
operator|-
name|this
operator|.
name|yRot
decl_stmt|;
name|float
name|var4
init|=
name|xRot
operator|-
name|this
operator|.
name|xRot
decl_stmt|;
while|while
condition|(
name|var3
operator|>=
literal|180F
condition|)
block|{
name|var3
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|var3
operator|<
operator|-
literal|180F
condition|)
block|{
name|var3
operator|+=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|var4
operator|>=
literal|180F
condition|)
block|{
name|var4
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|var4
operator|<
operator|-
literal|180F
condition|)
block|{
name|var4
operator|+=
literal|360F
expr_stmt|;
block|}
name|var3
operator|=
name|this
operator|.
name|yRot
operator|+
name|var3
operator|*
literal|0.5F
expr_stmt|;
name|var4
operator|=
name|this
operator|.
name|xRot
operator|+
name|var4
operator|*
literal|0.5F
expr_stmt|;
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
name|var3
argument_list|,
name|var4
argument_list|)
argument_list|)
expr_stmt|;
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
name|yRot
argument_list|,
name|xRot
argument_list|)
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|renderHover
parameter_list|(
name|TextureManager
name|textureManager
parameter_list|)
block|{
name|FontRenderer
name|fontRenderer
init|=
name|minecraft
operator|.
name|fontRenderer
decl_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|float
name|var1
init|=
name|minecraft
operator|.
name|player
operator|.
name|distanceTo
argument_list|(
name|this
argument_list|)
operator|/
literal|128
decl_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
name|xo
operator|+
operator|(
name|x
operator|-
name|xo
operator|)
operator|*
name|var1
argument_list|,
name|yo
operator|+
operator|(
name|y
operator|-
name|yo
operator|)
operator|*
name|var1
operator|+
literal|0.8F
operator|+
name|renderOffset
argument_list|,
name|zo
operator|+
operator|(
name|z
operator|-
name|zo
operator|)
operator|*
name|var1
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
name|minecraft
operator|.
name|player
operator|.
name|yRot
argument_list|,
literal|0F
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
name|minecraft
operator|.
name|player
operator|.
name|xRot
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
if|if
condition|(
name|minecraft
operator|.
name|settings
operator|.
name|ShowNames
operator|==
literal|1
operator|||
name|minecraft
operator|.
name|settings
operator|.
name|ShowNames
operator|==
literal|3
operator|&&
name|minecraft
operator|.
name|player
operator|.
name|userType
operator|>=
literal|100
condition|)
block|{
name|GL11
operator|.
name|glScalef
argument_list|(
name|var1
argument_list|,
operator|-
name|var1
argument_list|,
name|var1
argument_list|)
expr_stmt|;
block|}
else|else
name|GL11
operator|.
name|glScalef
argument_list|(
literal|0.05F
argument_list|,
operator|-
literal|0.05F
argument_list|,
literal|0.05F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|displayName
argument_list|)
operator|/
literal|2F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glNormal3f
argument_list|(
literal|1F
argument_list|,
operator|-
literal|1F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_LIGHTING
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_LIGHT0
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|renderNoShadow
argument_list|(
name|displayName
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
comment|// #FFFFFF
name|GL11
operator|.
name|glDepthFunc
argument_list|(
name|GL11
operator|.
name|GL_GREATER
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDepthMask
argument_list|(
literal|false
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
literal|0.8F
argument_list|)
expr_stmt|;
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
name|fontRenderer
operator|.
name|renderNoShadow
argument_list|(
name|displayName
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
comment|// #FFFFFF
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_BLEND
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDepthMask
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDepthFunc
argument_list|(
name|GL11
operator|.
name|GL_LEQUAL
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
literal|1F
argument_list|,
literal|1F
argument_list|,
operator|-
literal|0.05F
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|renderNoShadow
argument_list|(
name|name
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
literal|5263440
argument_list|)
expr_stmt|;
comment|// #505050
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_LIGHT0
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_LIGHTING
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
block|}
specifier|public
name|void
name|teleport
parameter_list|(
name|short
name|x
parameter_list|,
name|short
name|y
parameter_list|,
name|short
name|z
parameter_list|,
name|float
name|xRot
parameter_list|,
name|float
name|yRot
parameter_list|)
block|{
name|float
name|var6
init|=
name|yRot
operator|-
name|this
operator|.
name|yRot
decl_stmt|;
name|float
name|var7
init|=
name|xRot
operator|-
name|this
operator|.
name|xRot
decl_stmt|;
comment|// Normalize values?
while|while
condition|(
name|var6
operator|>=
literal|180F
condition|)
block|{
name|var6
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|var6
operator|<
operator|-
literal|180F
condition|)
block|{
name|var6
operator|+=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|var7
operator|>=
literal|180F
condition|)
block|{
name|var7
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|var7
operator|<
operator|-
literal|180F
condition|)
block|{
name|var7
operator|+=
literal|360F
expr_stmt|;
block|}
name|var6
operator|=
name|this
operator|.
name|yRot
operator|+
name|var6
operator|*
literal|0.5F
expr_stmt|;
name|var7
operator|=
name|this
operator|.
name|xRot
operator|+
name|var7
operator|*
literal|0.5F
expr_stmt|;
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
operator|(
name|xp
operator|+
name|x
operator|)
operator|/
literal|64F
argument_list|,
operator|(
name|yp
operator|+
name|y
operator|)
operator|/
literal|64F
argument_list|,
operator|(
name|zp
operator|+
name|z
operator|)
operator|/
literal|64F
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
argument_list|)
expr_stmt|;
name|xp
operator|=
name|x
expr_stmt|;
name|yp
operator|=
name|y
expr_stmt|;
name|zp
operator|=
name|z
expr_stmt|;
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
name|xp
operator|/
literal|32F
argument_list|,
name|yp
operator|/
literal|32F
argument_list|,
name|zp
operator|/
literal|32F
argument_list|,
name|yRot
argument_list|,
name|xRot
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

