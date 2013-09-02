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
name|Creeper
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
name|mob
operator|.
name|Mob
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
specifier|private
name|List
argument_list|<
name|PositionUpdate
argument_list|>
name|moveQueue
init|=
operator|new
name|LinkedList
argument_list|<
name|PositionUpdate
argument_list|>
argument_list|()
decl_stmt|;
specifier|private
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
name|var1
parameter_list|,
name|int
name|var2
parameter_list|,
name|String
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
parameter_list|,
name|float
name|var7
parameter_list|,
name|float
name|var8
parameter_list|)
block|{
name|super
argument_list|(
name|var1
operator|.
name|level
argument_list|,
operator|(
name|float
operator|)
name|var4
argument_list|,
operator|(
name|float
operator|)
name|var5
argument_list|,
operator|(
name|float
operator|)
name|var6
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|displayName
operator|=
name|var3
expr_stmt|;
name|var3
operator|=
name|FontRenderer
operator|.
name|stripColor
argument_list|(
name|var3
argument_list|)
expr_stmt|;
name|this
operator|.
name|name
operator|=
name|var3
expr_stmt|;
name|this
operator|.
name|xp
operator|=
name|var4
expr_stmt|;
name|this
operator|.
name|yp
operator|=
name|var5
expr_stmt|;
name|this
operator|.
name|zp
operator|=
name|var6
expr_stmt|;
name|this
operator|.
name|heightOffset
operator|=
literal|0.0F
expr_stmt|;
name|this
operator|.
name|pushthrough
operator|=
literal|0.8F
expr_stmt|;
name|this
operator|.
name|setPos
argument_list|(
operator|(
name|float
operator|)
name|var4
operator|/
literal|32.0F
argument_list|,
operator|(
name|float
operator|)
name|var5
operator|/
literal|32.0F
argument_list|,
operator|(
name|float
operator|)
name|var6
operator|/
literal|32.0F
argument_list|)
expr_stmt|;
name|this
operator|.
name|xRot
operator|=
name|var8
expr_stmt|;
name|this
operator|.
name|yRot
operator|=
name|var7
expr_stmt|;
name|this
operator|.
name|armor
operator|=
name|this
operator|.
name|helmet
operator|=
literal|false
expr_stmt|;
name|this
operator|.
name|renderOffset
operator|=
literal|0.6875F
expr_stmt|;
name|this
operator|.
name|allowAlpha
operator|=
literal|false
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|modelName
operator|==
literal|"humanoid"
condition|)
block|{
name|downloadSkin
argument_list|()
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|downloadSkin
parameter_list|()
block|{
operator|(
operator|new
name|SkinDownloadThread
argument_list|(
name|this
argument_list|)
operator|)
operator|.
name|start
argument_list|()
expr_stmt|;
block|}
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
do|do
block|{
if|if
condition|(
name|this
operator|.
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
operator|(
name|PositionUpdate
operator|)
name|this
operator|.
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
name|this
operator|.
name|moveQueue
operator|.
name|size
argument_list|()
operator|>
literal|10
condition|)
do|;
name|this
operator|.
name|onGround
operator|=
literal|true
expr_stmt|;
block|}
specifier|public
name|void
name|bindTexture
parameter_list|(
name|TextureManager
name|var1
parameter_list|)
block|{
name|this
operator|.
name|textures
operator|=
name|var1
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|newTexture
operator|!=
literal|null
condition|)
block|{
name|BufferedImage
name|var2
init|=
name|this
operator|.
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
name|this
operator|.
name|hasHair
operator|=
name|var10001
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|modelName
operator|==
literal|"humanoid"
condition|)
block|{
name|this
operator|.
name|a
operator|=
name|var1
operator|.
name|load
argument_list|(
name|this
operator|.
name|newTexture
argument_list|)
expr_stmt|;
block|}
name|this
operator|.
name|newTexture
operator|=
literal|null
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|a
operator|<
literal|0
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|modelName
operator|==
literal|"humanoid"
condition|)
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|var1
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
literal|3553
argument_list|,
name|var1
operator|.
name|load
argument_list|(
literal|"/mob/"
operator|+
name|this
operator|.
name|modelName
operator|+
literal|".png"
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|this
operator|.
name|a
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|renderHover
parameter_list|(
name|TextureManager
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
name|FontRenderer
name|var3
init|=
name|this
operator|.
name|minecraft
operator|.
name|fontRenderer
decl_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
name|this
operator|.
name|xo
operator|+
operator|(
name|this
operator|.
name|x
operator|-
name|this
operator|.
name|xo
operator|)
operator|*
name|var2
argument_list|,
name|this
operator|.
name|yo
operator|+
operator|(
name|this
operator|.
name|y
operator|-
name|this
operator|.
name|yo
operator|)
operator|*
name|var2
operator|+
literal|0.8F
operator|+
name|this
operator|.
name|renderOffset
argument_list|,
name|this
operator|.
name|zo
operator|+
operator|(
name|this
operator|.
name|z
operator|-
name|this
operator|.
name|zo
operator|)
operator|*
name|var2
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
name|this
operator|.
name|minecraft
operator|.
name|player
operator|.
name|yRot
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var2
operator|=
literal|0.05F
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|0.05F
argument_list|,
operator|-
name|var2
argument_list|,
name|var2
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
operator|(
name|float
operator|)
operator|(
operator|-
name|var3
operator|.
name|getWidth
argument_list|(
name|this
operator|.
name|displayName
argument_list|)
operator|)
operator|/
literal|2.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glNormal3f
argument_list|(
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|2896
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|16384
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|name
operator|.
name|equalsIgnoreCase
argument_list|(
literal|"Notch"
argument_list|)
condition|)
block|{
name|var3
operator|.
name|renderNoShadow
argument_list|(
name|this
operator|.
name|displayName
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
literal|16776960
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|var3
operator|.
name|renderNoShadow
argument_list|(
name|this
operator|.
name|displayName
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glDepthFunc
argument_list|(
literal|516
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
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.8F
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
name|var3
operator|.
name|renderNoShadow
argument_list|(
name|this
operator|.
name|displayName
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
literal|16777215
argument_list|)
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
name|glDepthMask
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDepthFunc
argument_list|(
literal|515
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
operator|-
literal|0.05F
argument_list|)
expr_stmt|;
name|var3
operator|.
name|renderNoShadow
argument_list|(
name|this
operator|.
name|name
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
literal|5263440
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|16384
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|2896
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
name|queue
parameter_list|(
name|byte
name|var1
parameter_list|,
name|byte
name|var2
parameter_list|,
name|byte
name|var3
parameter_list|,
name|float
name|var4
parameter_list|,
name|float
name|var5
parameter_list|)
block|{
name|float
name|var6
init|=
name|var4
operator|-
name|this
operator|.
name|yRot
decl_stmt|;
name|float
name|var7
decl_stmt|;
for|for
control|(
name|var7
operator|=
name|var5
operator|-
name|this
operator|.
name|xRot
init|;
name|var6
operator|>=
literal|180.0F
condition|;
name|var6
operator|-=
literal|360.0F
control|)
block|{
empty_stmt|;
block|}
while|while
condition|(
name|var6
operator|<
operator|-
literal|180.0F
condition|)
block|{
name|var6
operator|+=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|var7
operator|>=
literal|180.0F
condition|)
block|{
name|var7
operator|-=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|var7
operator|<
operator|-
literal|180.0F
condition|)
block|{
name|var7
operator|+=
literal|360.0F
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
name|this
operator|.
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
operator|(
operator|(
name|float
operator|)
name|this
operator|.
name|xp
operator|+
operator|(
name|float
operator|)
name|var1
operator|/
literal|2.0F
operator|)
operator|/
literal|32.0F
argument_list|,
operator|(
operator|(
name|float
operator|)
name|this
operator|.
name|yp
operator|+
operator|(
name|float
operator|)
name|var2
operator|/
literal|2.0F
operator|)
operator|/
literal|32.0F
argument_list|,
operator|(
operator|(
name|float
operator|)
name|this
operator|.
name|zp
operator|+
operator|(
name|float
operator|)
name|var3
operator|/
literal|2.0F
operator|)
operator|/
literal|32.0F
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
argument_list|)
expr_stmt|;
name|this
operator|.
name|xp
operator|+=
name|var1
expr_stmt|;
name|this
operator|.
name|yp
operator|+=
name|var2
expr_stmt|;
name|this
operator|.
name|zp
operator|+=
name|var3
expr_stmt|;
name|this
operator|.
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
operator|(
name|float
operator|)
name|this
operator|.
name|xp
operator|/
literal|32.0F
argument_list|,
operator|(
name|float
operator|)
name|this
operator|.
name|yp
operator|/
literal|32.0F
argument_list|,
operator|(
name|float
operator|)
name|this
operator|.
name|zp
operator|/
literal|32.0F
argument_list|,
name|var4
argument_list|,
name|var5
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|teleport
parameter_list|(
name|short
name|var1
parameter_list|,
name|short
name|var2
parameter_list|,
name|short
name|var3
parameter_list|,
name|float
name|var4
parameter_list|,
name|float
name|var5
parameter_list|)
block|{
name|float
name|var6
init|=
name|var4
operator|-
name|this
operator|.
name|yRot
decl_stmt|;
name|float
name|var7
decl_stmt|;
for|for
control|(
name|var7
operator|=
name|var5
operator|-
name|this
operator|.
name|xRot
init|;
name|var6
operator|>=
literal|180.0F
condition|;
name|var6
operator|-=
literal|360.0F
control|)
block|{
empty_stmt|;
block|}
while|while
condition|(
name|var6
operator|<
operator|-
literal|180.0F
condition|)
block|{
name|var6
operator|+=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|var7
operator|>=
literal|180.0F
condition|)
block|{
name|var7
operator|-=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|var7
operator|<
operator|-
literal|180.0F
condition|)
block|{
name|var7
operator|+=
literal|360.0F
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
name|this
operator|.
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
operator|(
name|float
operator|)
operator|(
name|this
operator|.
name|xp
operator|+
name|var1
operator|)
operator|/
literal|64.0F
argument_list|,
operator|(
name|float
operator|)
operator|(
name|this
operator|.
name|yp
operator|+
name|var2
operator|)
operator|/
literal|64.0F
argument_list|,
operator|(
name|float
operator|)
operator|(
name|this
operator|.
name|zp
operator|+
name|var3
operator|)
operator|/
literal|64.0F
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
argument_list|)
expr_stmt|;
name|this
operator|.
name|xp
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|yp
operator|=
name|var2
expr_stmt|;
name|this
operator|.
name|zp
operator|=
name|var3
expr_stmt|;
name|this
operator|.
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
operator|(
name|float
operator|)
name|this
operator|.
name|xp
operator|/
literal|32.0F
argument_list|,
operator|(
name|float
operator|)
name|this
operator|.
name|yp
operator|/
literal|32.0F
argument_list|,
operator|(
name|float
operator|)
name|this
operator|.
name|zp
operator|/
literal|32.0F
argument_list|,
name|var4
argument_list|,
name|var5
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|queue
parameter_list|(
name|byte
name|var1
parameter_list|,
name|byte
name|var2
parameter_list|,
name|byte
name|var3
parameter_list|)
block|{
name|this
operator|.
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
operator|(
operator|(
name|float
operator|)
name|this
operator|.
name|xp
operator|+
operator|(
name|float
operator|)
name|var1
operator|/
literal|2.0F
operator|)
operator|/
literal|32.0F
argument_list|,
operator|(
operator|(
name|float
operator|)
name|this
operator|.
name|yp
operator|+
operator|(
name|float
operator|)
name|var2
operator|/
literal|2.0F
operator|)
operator|/
literal|32.0F
argument_list|,
operator|(
operator|(
name|float
operator|)
name|this
operator|.
name|zp
operator|+
operator|(
name|float
operator|)
name|var3
operator|/
literal|2.0F
operator|)
operator|/
literal|32.0F
argument_list|)
argument_list|)
expr_stmt|;
name|this
operator|.
name|xp
operator|+=
name|var1
expr_stmt|;
name|this
operator|.
name|yp
operator|+=
name|var2
expr_stmt|;
name|this
operator|.
name|zp
operator|+=
name|var3
expr_stmt|;
name|this
operator|.
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
operator|(
name|float
operator|)
name|this
operator|.
name|xp
operator|/
literal|32.0F
argument_list|,
operator|(
name|float
operator|)
name|this
operator|.
name|yp
operator|/
literal|32.0F
argument_list|,
operator|(
name|float
operator|)
name|this
operator|.
name|zp
operator|/
literal|32.0F
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|queue
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
name|float
name|var3
init|=
name|var1
operator|-
name|this
operator|.
name|yRot
decl_stmt|;
name|float
name|var4
decl_stmt|;
for|for
control|(
name|var4
operator|=
name|var2
operator|-
name|this
operator|.
name|xRot
init|;
name|var3
operator|>=
literal|180.0F
condition|;
name|var3
operator|-=
literal|360.0F
control|)
block|{
empty_stmt|;
block|}
while|while
condition|(
name|var3
operator|<
operator|-
literal|180.0F
condition|)
block|{
name|var3
operator|+=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|var4
operator|>=
literal|180.0F
condition|)
block|{
name|var4
operator|-=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|var4
operator|<
operator|-
literal|180.0F
condition|)
block|{
name|var4
operator|+=
literal|360.0F
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
name|this
operator|.
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
name|this
operator|.
name|moveQueue
operator|.
name|add
argument_list|(
operator|new
name|PositionUpdate
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|clear
parameter_list|()
block|{
if|if
condition|(
name|this
operator|.
name|a
operator|>=
literal|0
operator|&&
name|this
operator|.
name|textures
operator|!=
literal|null
condition|)
block|{
name|TextureManager
name|var10000
init|=
name|this
operator|.
name|textures
decl_stmt|;
name|int
name|var1
init|=
name|this
operator|.
name|a
decl_stmt|;
name|TextureManager
name|var2
init|=
name|this
operator|.
name|textures
decl_stmt|;
name|var10000
operator|.
name|textureImages
operator|.
name|remove
argument_list|(
name|Integer
operator|.
name|valueOf
argument_list|(
name|var1
argument_list|)
argument_list|)
expr_stmt|;
name|var2
operator|.
name|idBuffer
operator|.
name|clear
argument_list|()
expr_stmt|;
name|var2
operator|.
name|idBuffer
operator|.
name|put
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|var2
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
name|var2
operator|.
name|idBuffer
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

