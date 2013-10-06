begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|player
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
name|ColorCache
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
name|Entity
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
name|HackState
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
name|ProgressBarDisplay
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
name|Level
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
name|model
operator|.
name|HumanoidModel
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
name|util
operator|.
name|MathHelper
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
name|List
import|;
end_import

begin_class
specifier|public
class|class
name|Player
extends|extends
name|Mob
block|{
specifier|private
name|int
name|flyTrig
init|=
literal|0
decl_stmt|;
specifier|private
name|int
name|noclipTrig
init|=
literal|0
decl_stmt|;
specifier|private
name|int
name|speedTrig
init|=
literal|0
decl_stmt|;
specifier|private
name|int
name|jumpCount
init|=
literal|0
decl_stmt|;
name|boolean
name|HacksEnabled
decl_stmt|;
name|boolean
name|isOnIce
init|=
literal|false
decl_stmt|;
specifier|public
specifier|static
name|boolean
name|noPush
init|=
literal|false
decl_stmt|;
specifier|public
specifier|transient
name|GameSettings
name|settings
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|0L
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|MAX_HEALTH
init|=
literal|20
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|MAX_ARROWS
init|=
literal|99
decl_stmt|;
specifier|public
specifier|transient
name|InputHandler
name|input
decl_stmt|;
specifier|public
name|Inventory
name|inventory
init|=
operator|new
name|Inventory
argument_list|()
decl_stmt|;
specifier|public
name|byte
name|userType
init|=
literal|0
decl_stmt|;
specifier|public
name|float
name|oBob
decl_stmt|;
specifier|public
name|float
name|bob
decl_stmt|;
specifier|public
name|int
name|score
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|arrows
init|=
literal|20
decl_stmt|;
specifier|private
specifier|static
name|int
name|newTextureId
init|=
operator|-
literal|1
decl_stmt|;
specifier|public
specifier|static
name|BufferedImage
name|newTexture
decl_stmt|;
specifier|public
name|Player
parameter_list|(
name|Level
name|var1
parameter_list|,
name|GameSettings
name|gs
parameter_list|)
block|{
name|super
argument_list|(
name|var1
argument_list|)
expr_stmt|;
if|if
condition|(
name|var1
operator|!=
literal|null
condition|)
block|{
name|var1
operator|.
name|player
operator|=
name|this
expr_stmt|;
name|var1
operator|.
name|removeEntity
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|var1
operator|.
name|addEntity
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
name|this
operator|.
name|heightOffset
operator|=
literal|1.62F
expr_stmt|;
name|this
operator|.
name|health
operator|=
literal|20
expr_stmt|;
name|this
operator|.
name|modelName
operator|=
literal|"humanoid"
expr_stmt|;
name|this
operator|.
name|rotOffs
operator|=
literal|180.0F
expr_stmt|;
name|this
operator|.
name|ai
operator|=
operator|new
name|Player$1
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|this
operator|.
name|settings
operator|=
name|gs
expr_stmt|;
block|}
specifier|public
name|boolean
name|addResource
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
return|return
name|this
operator|.
name|inventory
operator|.
name|addResource
argument_list|(
name|var1
argument_list|)
return|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|aiStep
parameter_list|()
block|{
if|if
condition|(
name|settings
operator|.
name|HackType
operator|==
literal|0
condition|)
block|{
name|this
operator|.
name|inventory
operator|.
name|tick
argument_list|()
expr_stmt|;
name|this
operator|.
name|oBob
operator|=
name|this
operator|.
name|bob
expr_stmt|;
name|this
operator|.
name|input
operator|.
name|updateMovement
argument_list|(
name|settings
operator|.
name|HackType
argument_list|)
expr_stmt|;
name|super
operator|.
name|aiStep
argument_list|()
expr_stmt|;
name|float
name|var1
init|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|this
operator|.
name|xd
operator|*
name|this
operator|.
name|xd
operator|+
name|this
operator|.
name|zd
operator|*
name|this
operator|.
name|zd
argument_list|)
decl_stmt|;
name|float
name|var2
init|=
operator|(
name|float
operator|)
name|Math
operator|.
name|atan
argument_list|(
operator|(
name|double
operator|)
operator|(
operator|-
name|this
operator|.
name|yd
operator|*
literal|0.2F
operator|)
argument_list|)
operator|*
literal|15.0F
decl_stmt|;
if|if
condition|(
name|var1
operator|>
literal|0.1F
condition|)
block|{
name|var1
operator|=
literal|0.1F
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|this
operator|.
name|onGround
operator|||
name|this
operator|.
name|health
operator|<=
literal|0
condition|)
block|{
name|var1
operator|=
literal|0.0F
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|onGround
operator|||
name|this
operator|.
name|health
operator|<=
literal|0
condition|)
block|{
name|var2
operator|=
literal|0.0F
expr_stmt|;
block|}
name|this
operator|.
name|bob
operator|+=
operator|(
name|var1
operator|-
name|this
operator|.
name|bob
operator|)
operator|*
literal|0.4F
expr_stmt|;
name|this
operator|.
name|tilt
operator|+=
operator|(
name|var2
operator|-
name|this
operator|.
name|tilt
operator|)
operator|*
literal|0.8F
expr_stmt|;
name|List
argument_list|<
name|?
argument_list|>
name|var3
decl_stmt|;
if|if
condition|(
name|this
operator|.
name|health
operator|>
literal|0
operator|&&
operator|(
name|var3
operator|=
name|this
operator|.
name|level
operator|.
name|findEntities
argument_list|(
name|this
argument_list|,
name|this
operator|.
name|bb
operator|.
name|grow
argument_list|(
literal|1.0F
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|)
argument_list|)
operator|)
operator|!=
literal|null
condition|)
block|{
for|for
control|(
name|int
name|var4
init|=
literal|0
init|;
name|var4
operator|<
name|var3
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var4
control|)
block|{
operator|(
operator|(
name|Entity
operator|)
name|var3
operator|.
name|get
argument_list|(
name|var4
argument_list|)
operator|)
operator|.
name|playerTouch
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
block|}
else|else
block|{
name|this
operator|.
name|oBob
operator|=
name|this
operator|.
name|bob
expr_stmt|;
name|this
operator|.
name|HacksEnabled
operator|=
name|settings
operator|.
name|HacksEnabled
expr_stmt|;
name|this
operator|.
name|input
operator|.
name|updateMovement
argument_list|(
name|settings
operator|.
name|HackType
argument_list|)
expr_stmt|;
name|super
operator|.
name|aiStep
argument_list|()
expr_stmt|;
name|float
name|fx
init|=
name|xd
decl_stmt|;
name|float
name|fy
init|=
name|yd
decl_stmt|;
name|float
name|fz
init|=
name|zd
decl_stmt|;
if|if
condition|(
name|fx
operator|>
literal|0.1f
condition|)
name|fx
operator|=
literal|0.1f
expr_stmt|;
if|if
condition|(
name|fy
operator|>
literal|0.1f
condition|)
name|fy
operator|=
literal|0.1f
expr_stmt|;
if|if
condition|(
name|fz
operator|>
literal|0.1f
condition|)
name|fz
operator|=
literal|0.1f
expr_stmt|;
if|if
condition|(
name|fx
operator|<
operator|-
literal|0.1f
condition|)
name|fx
operator|=
operator|-
literal|0.1f
expr_stmt|;
if|if
condition|(
name|fy
operator|<
operator|-
literal|0.1f
condition|)
name|fy
operator|=
operator|-
literal|0.1f
expr_stmt|;
if|if
condition|(
name|fz
operator|<
operator|-
literal|0.1f
condition|)
name|fz
operator|=
operator|-
literal|0.1f
expr_stmt|;
name|float
name|aaa
init|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|fx
operator|*
name|fx
operator|+
name|fz
operator|*
name|fz
argument_list|)
decl_stmt|;
name|float
name|bbb
init|=
operator|(
name|float
operator|)
name|Math
operator|.
name|atan
argument_list|(
operator|(
name|double
operator|)
operator|(
operator|-
name|fy
operator|*
literal|0.2F
operator|)
argument_list|)
operator|*
literal|15.0F
decl_stmt|;
name|this
operator|.
name|bob
operator|+=
operator|(
name|aaa
operator|-
name|this
operator|.
name|bob
operator|)
operator|*
literal|0.4F
expr_stmt|;
name|this
operator|.
name|tilt
operator|+=
operator|(
name|bbb
operator|-
name|this
operator|.
name|tilt
operator|)
operator|*
literal|0.8F
expr_stmt|;
name|this
operator|.
name|speedTrig
operator|=
operator|-
literal|1
expr_stmt|;
comment|// speed
name|this
operator|.
name|flyTrig
operator|=
operator|-
literal|1
expr_stmt|;
comment|// fly
name|this
operator|.
name|noclipTrig
operator|=
operator|-
literal|1
expr_stmt|;
comment|// noclip
comment|// -1 = yes, 1 = no
if|if
condition|(
name|HackState
operator|.
name|Fly
condition|)
name|flyTrig
operator|=
operator|-
literal|1
expr_stmt|;
else|else
name|flyTrig
operator|=
literal|1
expr_stmt|;
if|if
condition|(
name|HackState
operator|.
name|Speed
condition|)
name|speedTrig
operator|=
operator|-
literal|1
expr_stmt|;
else|else
name|speedTrig
operator|=
literal|1
expr_stmt|;
if|if
condition|(
name|HackState
operator|.
name|Noclip
condition|)
name|noclipTrig
operator|=
operator|-
literal|1
expr_stmt|;
else|else
name|noclipTrig
operator|=
literal|1
expr_stmt|;
name|int
name|i
init|=
literal|0
decl_stmt|;
name|int
name|j
init|=
literal|0
decl_stmt|;
name|int
name|k
init|=
literal|1
decl_stmt|;
name|float
name|f1
init|=
literal|1.0F
decl_stmt|;
name|this
operator|.
name|oBob
operator|=
name|this
operator|.
name|bob
expr_stmt|;
if|if
condition|(
operator|(
name|this
operator|.
name|input
operator|.
name|fly
operator|)
operator|&&
operator|(
name|this
operator|.
name|flyTrig
operator|<
literal|1
operator|)
condition|)
name|i
operator|=
literal|1
expr_stmt|;
if|if
condition|(
operator|(
name|this
operator|.
name|input
operator|.
name|noclip
operator|)
operator|&&
operator|(
name|this
operator|.
name|noclipTrig
operator|<
literal|0
operator|)
condition|)
name|j
operator|=
literal|1
expr_stmt|;
if|if
condition|(
operator|(
name|this
operator|.
name|input
operator|.
name|mult
operator|>
literal|1.0F
operator|)
operator|&&
operator|(
name|this
operator|.
name|speedTrig
operator|<
literal|1
operator|)
condition|)
block|{
name|f1
operator|=
name|this
operator|.
name|input
operator|.
name|mult
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|HacksEnabled
condition|)
block|{
name|i
operator|=
literal|0
expr_stmt|;
name|j
operator|=
literal|0
expr_stmt|;
name|k
operator|=
literal|0
expr_stmt|;
name|f1
operator|=
literal|1.0F
expr_stmt|;
block|}
if|if
condition|(
operator|(
name|this
operator|.
name|flyTrig
operator|>
literal|0
operator|)
operator|||
operator|(
name|this
operator|.
name|speedTrig
operator|>
literal|0
operator|)
condition|)
block|{
name|k
operator|=
literal|0
expr_stmt|;
block|}
name|this
operator|.
name|xo
operator|=
name|this
operator|.
name|x
expr_stmt|;
name|this
operator|.
name|yo
operator|=
name|this
operator|.
name|y
expr_stmt|;
name|this
operator|.
name|zo
operator|=
name|this
operator|.
name|z
expr_stmt|;
name|this
operator|.
name|xRotO
operator|=
name|this
operator|.
name|xRot
expr_stmt|;
name|this
operator|.
name|yRotO
operator|=
name|this
operator|.
name|yRot
expr_stmt|;
name|boolean
name|bool1
init|=
name|isInWater
argument_list|()
decl_stmt|;
name|boolean
name|bool2
init|=
name|isInLava
argument_list|()
decl_stmt|;
name|boolean
name|bool3
init|=
name|isInOrOnRope
argument_list|()
decl_stmt|;
name|float
name|f2
init|=
literal|0.0F
decl_stmt|;
name|this
operator|.
name|input
operator|.
name|calc
argument_list|()
expr_stmt|;
if|if
condition|(
operator|(
name|i
operator|!=
literal|0
operator|)
operator|||
operator|(
name|j
operator|!=
literal|0
operator|)
condition|)
name|this
operator|.
name|yd
operator|=
name|this
operator|.
name|input
operator|.
name|elevate
expr_stmt|;
if|if
condition|(
operator|(
name|this
operator|.
name|onGround
operator|)
operator|||
operator|(
name|i
operator|!=
literal|0
operator|)
condition|)
name|this
operator|.
name|jumpCount
operator|=
literal|0
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|input
operator|.
name|jump
condition|)
block|{
if|if
condition|(
name|bool1
condition|)
block|{
name|this
operator|.
name|yd
operator|+=
literal|0.08F
expr_stmt|;
block|}
if|else if
condition|(
name|bool3
condition|)
block|{
name|this
operator|.
name|yd
operator|+=
literal|0.06F
expr_stmt|;
block|}
if|else if
condition|(
name|bool2
condition|)
block|{
name|this
operator|.
name|yd
operator|+=
literal|0.07F
expr_stmt|;
block|}
if|else if
condition|(
name|i
operator|!=
literal|0
condition|)
block|{
name|this
operator|.
name|yd
operator|+=
literal|0.05F
expr_stmt|;
block|}
if|else if
condition|(
name|this
operator|.
name|onGround
condition|)
block|{
if|if
condition|(
operator|!
name|this
operator|.
name|input
operator|.
name|fall
condition|)
block|{
if|if
condition|(
operator|(
operator|!
name|HacksEnabled
operator|)
operator|&&
operator|(
name|k
operator|!=
literal|0
operator|)
condition|)
name|this
operator|.
name|yd
operator|=
literal|0.48F
expr_stmt|;
else|else
name|this
operator|.
name|yd
operator|=
literal|0.35F
expr_stmt|;
name|this
operator|.
name|input
operator|.
name|fall
operator|=
literal|true
expr_stmt|;
name|this
operator|.
name|jumpCount
operator|+=
literal|1
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|HacksEnabled
operator|&&
operator|(
operator|!
name|this
operator|.
name|input
operator|.
name|fall
operator|)
operator|&&
operator|(
name|k
operator|!=
literal|0
operator|)
operator|&&
operator|(
name|this
operator|.
name|jumpCount
operator|<
literal|3
operator|)
condition|)
block|{
name|this
operator|.
name|yd
operator|=
literal|0.5F
expr_stmt|;
name|this
operator|.
name|input
operator|.
name|fall
operator|=
literal|true
expr_stmt|;
name|this
operator|.
name|jumpCount
operator|+=
literal|1
expr_stmt|;
block|}
block|}
else|else
block|{
name|this
operator|.
name|input
operator|.
name|fall
operator|=
literal|false
expr_stmt|;
block|}
if|if
condition|(
name|HacksEnabled
operator|&&
operator|(
name|k
operator|!=
literal|0
operator|)
operator|&&
operator|(
name|this
operator|.
name|jumpCount
operator|>
literal|1
operator|)
condition|)
block|{
name|f1
operator|*=
literal|2.5F
expr_stmt|;
if|if
condition|(
operator|!
name|this
operator|.
name|isOnIce
condition|)
block|{
name|f1
operator|*=
name|this
operator|.
name|jumpCount
expr_stmt|;
block|}
else|else
name|this
operator|.
name|jumpCount
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
operator|(
name|bool1
operator|)
operator|&&
operator|(
name|i
operator|==
literal|0
operator|)
operator|&&
operator|(
name|j
operator|==
literal|0
operator|)
condition|)
block|{
name|f2
operator|=
name|this
operator|.
name|y
expr_stmt|;
name|super
operator|.
name|moveRelative
argument_list|(
name|this
operator|.
name|input
operator|.
name|strafe
argument_list|,
name|this
operator|.
name|input
operator|.
name|move
argument_list|,
literal|0.02F
operator|*
name|f1
argument_list|)
expr_stmt|;
name|super
operator|.
name|move
argument_list|(
name|this
operator|.
name|xd
operator|*
name|f1
argument_list|,
name|this
operator|.
name|yd
operator|*
name|f1
argument_list|,
name|this
operator|.
name|zd
operator|*
name|f1
argument_list|)
expr_stmt|;
name|this
operator|.
name|xd
operator|*=
literal|0.8F
expr_stmt|;
name|this
operator|.
name|yd
operator|*=
literal|0.8F
expr_stmt|;
name|this
operator|.
name|zd
operator|*=
literal|0.8F
expr_stmt|;
name|this
operator|.
name|yd
operator|=
operator|(
operator|(
name|float
operator|)
operator|(
name|this
operator|.
name|yd
operator|-
literal|0.02D
operator|)
operator|)
expr_stmt|;
if|if
condition|(
operator|(
name|this
operator|.
name|horizontalCollision
operator|)
operator|&&
operator|(
name|isFree
argument_list|(
name|this
operator|.
name|xd
argument_list|,
name|this
operator|.
name|yd
operator|+
literal|0.6F
operator|-
name|this
operator|.
name|y
operator|+
name|f2
argument_list|,
name|this
operator|.
name|zd
argument_list|)
operator|)
condition|)
name|this
operator|.
name|yd
operator|=
literal|0.3F
expr_stmt|;
return|return;
block|}
if|if
condition|(
operator|(
name|bool2
operator|)
operator|&&
operator|(
name|i
operator|==
literal|0
operator|)
operator|&&
operator|(
name|j
operator|==
literal|0
operator|)
condition|)
block|{
name|f2
operator|=
name|this
operator|.
name|y
expr_stmt|;
name|super
operator|.
name|moveRelative
argument_list|(
name|this
operator|.
name|input
operator|.
name|strafe
argument_list|,
name|this
operator|.
name|input
operator|.
name|move
argument_list|,
literal|0.02F
operator|*
name|f1
argument_list|)
expr_stmt|;
name|super
operator|.
name|move
argument_list|(
name|this
operator|.
name|xd
operator|*
name|f1
argument_list|,
name|this
operator|.
name|yd
operator|*
name|f1
argument_list|,
name|this
operator|.
name|zd
operator|*
name|f1
argument_list|)
expr_stmt|;
name|this
operator|.
name|xd
operator|*=
literal|0.5F
expr_stmt|;
name|this
operator|.
name|yd
operator|*=
literal|0.5F
expr_stmt|;
name|this
operator|.
name|zd
operator|*=
literal|0.5F
expr_stmt|;
name|this
operator|.
name|yd
operator|=
operator|(
operator|(
name|float
operator|)
operator|(
name|this
operator|.
name|yd
operator|-
literal|0.02D
operator|)
operator|)
expr_stmt|;
if|if
condition|(
operator|(
name|this
operator|.
name|horizontalCollision
operator|)
operator|&&
operator|(
name|isFree
argument_list|(
name|this
operator|.
name|xd
argument_list|,
name|this
operator|.
name|yd
operator|+
literal|0.6F
operator|-
name|this
operator|.
name|y
operator|+
name|f2
argument_list|,
name|this
operator|.
name|zd
argument_list|)
operator|)
condition|)
name|this
operator|.
name|yd
operator|=
literal|0.3F
expr_stmt|;
return|return;
block|}
if|if
condition|(
name|i
operator|!=
literal|0
condition|)
block|{
name|f1
operator|=
operator|(
name|float
operator|)
operator|(
name|f1
operator|*
literal|1.2D
operator|)
expr_stmt|;
block|}
name|float
name|f4
init|=
literal|0.0F
decl_stmt|;
name|float
name|f3
init|=
literal|0.0f
decl_stmt|;
if|if
condition|(
name|j
operator|!=
literal|0
condition|)
block|{
name|f4
operator|=
name|i
operator|!=
literal|0
condition|?
literal|0.72F
else|:
literal|0.71F
expr_stmt|;
if|if
condition|(
name|i
operator|!=
literal|0
condition|)
name|this
operator|.
name|yd
operator|=
name|this
operator|.
name|input
operator|.
name|elevate
expr_stmt|;
name|f3
operator|=
literal|0.2F
expr_stmt|;
block|}
if|else if
condition|(
operator|(
name|this
operator|.
name|onGround
operator|)
operator|||
operator|(
name|this
operator|.
name|jumpCount
operator|>
literal|0
operator|)
operator|||
operator|(
name|i
operator|!=
literal|0
operator|)
condition|)
block|{
name|f3
operator|=
literal|0.1F
expr_stmt|;
block|}
else|else
block|{
name|f3
operator|=
literal|0.02F
expr_stmt|;
block|}
name|super
operator|.
name|moveRelative
argument_list|(
name|this
operator|.
name|input
operator|.
name|strafe
argument_list|,
name|this
operator|.
name|input
operator|.
name|move
argument_list|,
name|f3
operator|*
name|f1
argument_list|)
expr_stmt|;
if|if
condition|(
operator|(
name|j
operator|!=
literal|0
operator|)
operator|&&
operator|(
operator|(
name|this
operator|.
name|xd
operator|!=
literal|0.0F
operator|)
operator|||
operator|(
name|this
operator|.
name|zd
operator|!=
literal|0.0F
operator|)
operator|)
condition|)
block|{
name|super
operator|.
name|moveTo
argument_list|(
name|this
operator|.
name|x
operator|+
name|this
operator|.
name|xd
argument_list|,
name|this
operator|.
name|y
operator|+
name|this
operator|.
name|yd
operator|-
name|f4
argument_list|,
name|this
operator|.
name|z
operator|+
name|this
operator|.
name|zd
argument_list|,
name|this
operator|.
name|yRot
argument_list|,
name|this
operator|.
name|xRot
argument_list|)
expr_stmt|;
name|this
operator|.
name|yo
operator|=
operator|(
name|this
operator|.
name|y
operator|+=
name|f4
operator|)
expr_stmt|;
block|}
else|else
block|{
name|super
operator|.
name|move
argument_list|(
name|this
operator|.
name|xd
operator|*
name|f1
argument_list|,
name|this
operator|.
name|yd
operator|*
name|f1
argument_list|,
name|this
operator|.
name|zd
operator|*
name|f1
argument_list|)
expr_stmt|;
block|}
name|int
name|var1
init|=
name|this
operator|.
name|level
operator|.
name|getTile
argument_list|(
operator|(
name|int
operator|)
name|this
operator|.
name|x
argument_list|,
operator|(
name|int
operator|)
operator|(
operator|(
name|this
operator|.
name|y
operator|)
operator|-
literal|2.12F
operator|)
argument_list|,
operator|(
name|int
operator|)
name|this
operator|.
name|z
argument_list|)
decl_stmt|;
if|if
condition|(
name|Block
operator|.
name|blocks
index|[
name|var1
index|]
operator|!=
name|Block
operator|.
name|ICE
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|jumpCount
operator|==
literal|0
condition|)
block|{
name|this
operator|.
name|isOnIce
operator|=
literal|false
expr_stmt|;
block|}
name|f2
operator|=
literal|0.6F
expr_stmt|;
name|this
operator|.
name|xd
operator|*=
literal|0.91F
expr_stmt|;
name|this
operator|.
name|yd
operator|*=
literal|0.98F
expr_stmt|;
name|this
operator|.
name|zd
operator|*=
literal|0.91F
expr_stmt|;
if|if
condition|(
name|i
operator|!=
literal|0
condition|)
block|{
name|this
operator|.
name|yd
operator|*=
name|f2
operator|/
literal|4.0F
expr_stmt|;
name|this
operator|.
name|walkDist
operator|=
literal|0.0F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|yd
operator|=
operator|(
operator|(
name|float
operator|)
operator|(
name|this
operator|.
name|yd
operator|-
literal|0.01D
operator|)
operator|)
expr_stmt|;
block|}
name|this
operator|.
name|xd
operator|*=
name|f2
expr_stmt|;
name|this
operator|.
name|zd
operator|*=
name|f2
expr_stmt|;
name|this
operator|.
name|tilt
operator|=
literal|0f
expr_stmt|;
block|}
else|else
block|{
name|isOnIce
operator|=
literal|true
expr_stmt|;
block|}
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|awardKillScore
parameter_list|(
name|Entity
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
name|this
operator|.
name|score
operator|+=
name|var2
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|bindTexture
parameter_list|(
name|TextureManager
name|var1
parameter_list|)
block|{
if|if
condition|(
name|newTexture
operator|!=
literal|null
condition|)
block|{
name|newTextureId
operator|=
name|var1
operator|.
name|load
argument_list|(
name|newTexture
argument_list|)
expr_stmt|;
name|newTexture
operator|=
literal|null
expr_stmt|;
block|}
name|int
name|var2
decl_stmt|;
if|if
condition|(
name|newTextureId
operator|<
literal|0
condition|)
block|{
name|var2
operator|=
name|var1
operator|.
name|load
argument_list|(
literal|"/char.png"
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
block|}
else|else
block|{
name|var2
operator|=
name|newTextureId
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
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|die
parameter_list|(
name|Entity
name|var1
parameter_list|)
block|{
name|this
operator|.
name|setSize
argument_list|(
literal|0.2F
argument_list|,
literal|0.2F
argument_list|)
expr_stmt|;
name|this
operator|.
name|setPos
argument_list|(
name|this
operator|.
name|x
argument_list|,
name|this
operator|.
name|y
argument_list|,
name|this
operator|.
name|z
argument_list|)
expr_stmt|;
name|this
operator|.
name|yd
operator|=
literal|0.1F
expr_stmt|;
if|if
condition|(
name|var1
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|xd
operator|=
operator|-
name|MathHelper
operator|.
name|cos
argument_list|(
operator|(
name|this
operator|.
name|hurtDir
operator|+
name|this
operator|.
name|yRot
operator|)
operator|*
literal|3.1415927F
operator|/
literal|180.0F
argument_list|)
operator|*
literal|0.1F
expr_stmt|;
name|this
operator|.
name|zd
operator|=
operator|-
name|MathHelper
operator|.
name|sin
argument_list|(
operator|(
name|this
operator|.
name|hurtDir
operator|+
name|this
operator|.
name|yRot
operator|)
operator|*
literal|3.1415927F
operator|/
literal|180.0F
argument_list|)
operator|*
literal|0.1F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|xd
operator|=
name|this
operator|.
name|zd
operator|=
literal|0.0F
expr_stmt|;
block|}
name|this
operator|.
name|heightOffset
operator|=
literal|0.1F
expr_stmt|;
block|}
specifier|public
name|HumanoidModel
name|getModel
parameter_list|()
block|{
return|return
operator|(
name|HumanoidModel
operator|)
name|modelCache
operator|.
name|getModel
argument_list|(
name|this
operator|.
name|modelName
argument_list|)
return|;
block|}
specifier|public
name|int
name|getScore
parameter_list|()
block|{
return|return
name|this
operator|.
name|score
return|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|hurt
parameter_list|(
name|Entity
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
if|if
condition|(
operator|!
name|this
operator|.
name|level
operator|.
name|creativeMode
condition|)
block|{
name|super
operator|.
name|hurt
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|boolean
name|isCreativeModeAllowed
parameter_list|()
block|{
return|return
literal|true
return|;
block|}
annotation|@
name|Override
specifier|public
name|boolean
name|isShootable
parameter_list|()
block|{
return|return
literal|true
return|;
block|}
specifier|public
name|void
name|releaseAllKeys
parameter_list|()
block|{
name|this
operator|.
name|input
operator|.
name|resetKeys
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|remove
parameter_list|()
block|{
block|}
annotation|@
name|Override
specifier|public
name|void
name|render
parameter_list|(
name|TextureManager
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
if|if
condition|(
operator|!
name|this
operator|.
name|settings
operator|.
name|thirdPersonMode
condition|)
return|return;
if|if
condition|(
name|this
operator|.
name|modelName
operator|!=
literal|null
condition|)
block|{
name|float
name|var3
decl_stmt|;
if|if
condition|(
operator|(
name|var3
operator|=
operator|(
name|float
operator|)
name|this
operator|.
name|attackTime
operator|-
name|var2
operator|)
operator|<
literal|0.0F
condition|)
block|{
name|var3
operator|=
literal|0.0F
expr_stmt|;
block|}
while|while
condition|(
name|this
operator|.
name|yBodyRotO
operator|-
name|this
operator|.
name|yBodyRot
operator|<
operator|-
literal|180.0F
condition|)
block|{
name|this
operator|.
name|yBodyRotO
operator|+=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|this
operator|.
name|yBodyRotO
operator|-
name|this
operator|.
name|yBodyRot
operator|>=
literal|180.0F
condition|)
block|{
name|this
operator|.
name|yBodyRotO
operator|-=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|this
operator|.
name|xRotO
operator|-
name|this
operator|.
name|xRot
operator|<
operator|-
literal|180.0F
condition|)
block|{
name|this
operator|.
name|xRotO
operator|+=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|this
operator|.
name|xRotO
operator|-
name|this
operator|.
name|xRot
operator|>=
literal|180.0F
condition|)
block|{
name|this
operator|.
name|xRotO
operator|-=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|this
operator|.
name|yRotO
operator|-
name|this
operator|.
name|yRot
operator|<
operator|-
literal|180.0F
condition|)
block|{
name|this
operator|.
name|yRotO
operator|+=
literal|360.0F
expr_stmt|;
block|}
while|while
condition|(
name|this
operator|.
name|yRotO
operator|-
name|this
operator|.
name|yRot
operator|>=
literal|180.0F
condition|)
block|{
name|this
operator|.
name|yRotO
operator|-=
literal|360.0F
expr_stmt|;
block|}
name|float
name|var4
init|=
name|this
operator|.
name|yBodyRotO
operator|+
operator|(
operator|(
name|this
operator|.
name|yBodyRot
operator|-
name|this
operator|.
name|yBodyRotO
operator|)
operator|*
name|var2
operator|)
decl_stmt|;
name|float
name|var5
init|=
name|this
operator|.
name|oRun
operator|+
operator|(
name|this
operator|.
name|run
operator|-
name|this
operator|.
name|oRun
operator|)
operator|*
name|var2
decl_stmt|;
name|float
name|var6
init|=
name|this
operator|.
name|yRotO
operator|+
operator|(
name|this
operator|.
name|yRot
operator|-
name|this
operator|.
name|yRotO
operator|)
operator|*
name|var2
decl_stmt|;
name|float
name|var7
init|=
name|this
operator|.
name|xRotO
operator|+
operator|(
name|this
operator|.
name|xRot
operator|-
name|this
operator|.
name|xRotO
operator|)
operator|*
name|var2
decl_stmt|;
name|var6
operator|-=
name|var4
expr_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|float
name|var8
init|=
name|this
operator|.
name|animStepO
operator|+
operator|(
name|this
operator|.
name|animStep
operator|-
name|this
operator|.
name|animStepO
operator|)
operator|*
name|var2
decl_stmt|;
name|ColorCache
name|c
init|=
name|this
operator|.
name|getBrightnessColor
argument_list|(
name|var2
argument_list|)
decl_stmt|;
name|GL11
operator|.
name|glColor3f
argument_list|(
name|c
operator|.
name|R
argument_list|,
name|c
operator|.
name|G
argument_list|,
name|c
operator|.
name|B
argument_list|)
expr_stmt|;
name|float
name|var9
init|=
literal|0.0625F
decl_stmt|;
name|float
name|var10
init|=
operator|-
name|Math
operator|.
name|abs
argument_list|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var8
operator|*
literal|0.6662F
argument_list|)
argument_list|)
operator|*
literal|5.0F
operator|*
name|var5
operator|*
name|this
operator|.
name|bobStrength
operator|-
literal|23.0F
decl_stmt|;
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
operator|-
literal|1.62F
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
name|float
name|var11
decl_stmt|;
if|if
condition|(
operator|(
name|var11
operator|=
operator|(
name|float
operator|)
name|this
operator|.
name|hurtTime
operator|-
name|var2
operator|)
operator|>
literal|0.0F
operator|||
name|this
operator|.
name|health
operator|<=
literal|0
condition|)
block|{
if|if
condition|(
name|var11
operator|<
literal|0.0F
condition|)
block|{
name|var11
operator|=
literal|0.0F
expr_stmt|;
block|}
else|else
block|{
name|var11
operator|=
name|MathHelper
operator|.
name|sin
argument_list|(
operator|(
name|var11
operator|/=
operator|(
name|float
operator|)
name|this
operator|.
name|hurtDuration
operator|)
operator|*
name|var11
operator|*
name|var11
operator|*
name|var11
operator|*
literal|3.1415927F
argument_list|)
operator|*
literal|14.0F
expr_stmt|;
block|}
name|float
name|var12
init|=
literal|0.0F
decl_stmt|;
if|if
condition|(
name|this
operator|.
name|health
operator|<=
literal|0
condition|)
block|{
name|var12
operator|=
operator|(
operator|(
name|float
operator|)
name|this
operator|.
name|deathTime
operator|+
name|var2
operator|)
operator|/
literal|20.0F
expr_stmt|;
if|if
condition|(
operator|(
name|var11
operator|+=
name|var12
operator|*
name|var12
operator|*
literal|800.0F
operator|)
operator|>
literal|90.0F
condition|)
block|{
name|var11
operator|=
literal|90.0F
expr_stmt|;
block|}
block|}
name|var12
operator|=
name|this
operator|.
name|hurtDir
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
literal|180.0F
operator|-
name|var4
operator|+
name|this
operator|.
name|rotOffs
operator|+
literal|45
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
name|var12
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
name|var11
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
name|var12
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
operator|(
literal|180.0F
operator|-
name|var4
operator|+
name|this
operator|.
name|rotOffs
operator|)
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
literal|0.0F
argument_list|,
operator|-
name|var10
operator|*
name|var9
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glScalef
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
name|glRotatef
argument_list|(
literal|180.0F
operator|-
name|var4
operator|+
name|this
operator|.
name|rotOffs
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
operator|!
name|this
operator|.
name|allowAlpha
condition|)
block|{
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3008
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|GL11
operator|.
name|glDisable
argument_list|(
literal|2884
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glScalef
argument_list|(
operator|-
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|modelCache
operator|.
name|getModel
argument_list|(
name|this
operator|.
name|modelName
argument_list|)
operator|.
name|attackOffset
operator|=
name|var3
operator|/
literal|5.0F
expr_stmt|;
name|this
operator|.
name|bindTexture
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|this
operator|.
name|renderModel
argument_list|(
name|var1
argument_list|,
name|var8
argument_list|,
name|var2
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|,
name|var9
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|invulnerableTime
operator|>
name|this
operator|.
name|invulnerableDuration
operator|-
literal|10
condition|)
block|{
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
literal|0.75F
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
literal|1
argument_list|)
expr_stmt|;
name|this
operator|.
name|bindTexture
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|this
operator|.
name|renderModel
argument_list|(
name|var1
argument_list|,
name|var8
argument_list|,
name|var2
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|,
name|var9
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
name|glBlendFunc
argument_list|(
literal|770
argument_list|,
literal|771
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glEnable
argument_list|(
literal|3008
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|allowAlpha
condition|)
block|{
name|GL11
operator|.
name|glEnable
argument_list|(
literal|2884
argument_list|)
expr_stmt|;
block|}
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
literal|1.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|renderModel
parameter_list|(
name|TextureManager
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|float
name|var4
parameter_list|,
name|float
name|var5
parameter_list|,
name|float
name|var6
parameter_list|,
name|float
name|var7
parameter_list|)
block|{
name|modelCache
operator|.
name|getModel
argument_list|(
name|this
operator|.
name|modelName
argument_list|)
operator|.
name|render
argument_list|(
name|var2
argument_list|,
name|var4
argument_list|,
operator|(
name|float
operator|)
name|this
operator|.
name|tickCount
operator|+
name|var3
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|resetPos
parameter_list|()
block|{
name|this
operator|.
name|heightOffset
operator|=
literal|1.62F
expr_stmt|;
name|this
operator|.
name|setSize
argument_list|(
literal|0.6F
argument_list|,
literal|1.8F
argument_list|)
expr_stmt|;
name|super
operator|.
name|resetPos
argument_list|()
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|level
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|level
operator|.
name|player
operator|=
name|this
expr_stmt|;
block|}
name|this
operator|.
name|health
operator|=
literal|20
expr_stmt|;
name|this
operator|.
name|deathTime
operator|=
literal|0
expr_stmt|;
block|}
specifier|public
name|void
name|setKey
parameter_list|(
name|int
name|var1
parameter_list|,
name|boolean
name|var2
parameter_list|)
block|{
name|this
operator|.
name|input
operator|.
name|setKeyState
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

