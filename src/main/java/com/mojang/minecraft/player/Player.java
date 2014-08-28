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
import|import static
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|net
operator|.
name|NetworkPlayer
operator|.
name|isInteger
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
name|util
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
name|level
operator|.
name|tile
operator|.
name|FireBlock
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
name|FlowerBlock
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
name|model
operator|.
name|Model
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
name|util
operator|.
name|MathHelper
import|;
end_import

begin_class
specifier|public
class|class
name|Player
extends|extends
name|Mob
block|{
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
specifier|static
name|boolean
name|noPush
init|=
literal|false
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
specifier|transient
name|GameSettings
name|settings
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
name|boolean
name|HacksEnabled
decl_stmt|;
name|boolean
name|isOnIce
init|=
literal|false
decl_stmt|;
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
name|heightOffset
operator|=
literal|1.62F
expr_stmt|;
name|health
operator|=
literal|20
expr_stmt|;
name|modelName
operator|=
literal|"humanoid"
expr_stmt|;
name|rotOffs
operator|=
literal|180F
expr_stmt|;
name|ai
operator|=
operator|new
name|Player$1
argument_list|(
name|this
argument_list|)
expr_stmt|;
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
operator|||
operator|!
operator|(
name|HackState
operator|.
name|fly
operator|||
name|HackState
operator|.
name|speed
operator|||
name|HackState
operator|.
name|noclip
operator|)
operator|&&
name|input
operator|.
name|canMove
condition|)
block|{
name|inventory
operator|.
name|tick
argument_list|()
expr_stmt|;
name|oBob
operator|=
name|bob
expr_stmt|;
name|input
operator|.
name|updateMovement
argument_list|(
literal|0
argument_list|)
expr_stmt|;
comment|// for the event that hacktype
comment|// is 1 but server has -hax.
comment|// Otherwise you won't be able to move without manually setting
comment|// your hacktype back to 'normal' in the options menu.
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
name|xd
operator|*
name|xd
operator|+
name|zd
operator|*
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
operator|-
name|yd
operator|*
literal|0.2F
argument_list|)
operator|*
literal|15F
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
name|onGround
operator|||
name|health
operator|<=
literal|0
condition|)
block|{
name|var1
operator|=
literal|0F
expr_stmt|;
block|}
if|if
condition|(
name|onGround
operator|||
name|health
operator|<=
literal|0
condition|)
block|{
name|var2
operator|=
literal|0F
expr_stmt|;
block|}
name|bob
operator|+=
operator|(
name|var1
operator|-
name|bob
operator|)
operator|*
literal|0.4F
expr_stmt|;
name|tilt
operator|+=
operator|(
name|var2
operator|-
name|tilt
operator|)
operator|*
literal|0.8F
expr_stmt|;
name|List
argument_list|<
name|?
argument_list|>
name|neighbourEntities
init|=
name|level
operator|.
name|findEntities
argument_list|(
name|this
argument_list|,
name|boundingBox
operator|.
name|grow
argument_list|(
literal|1F
argument_list|,
literal|0F
argument_list|,
literal|1F
argument_list|)
argument_list|)
decl_stmt|;
if|if
condition|(
name|health
operator|>
literal|0
operator|&&
name|neighbourEntities
operator|!=
literal|null
condition|)
block|{
for|for
control|(
name|Object
name|neighbour
range|:
name|neighbourEntities
control|)
block|{
operator|(
operator|(
name|Entity
operator|)
name|neighbour
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
name|oBob
operator|=
name|bob
expr_stmt|;
name|HacksEnabled
operator|=
name|settings
operator|.
name|HacksEnabled
expr_stmt|;
name|input
operator|.
name|updateMovement
argument_list|(
literal|1
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
block|{
name|fx
operator|=
literal|0.1f
expr_stmt|;
block|}
if|if
condition|(
name|fy
operator|>
literal|0.1f
condition|)
block|{
name|fy
operator|=
literal|0.1f
expr_stmt|;
block|}
if|if
condition|(
name|fz
operator|>
literal|0.1f
condition|)
block|{
name|fz
operator|=
literal|0.1f
expr_stmt|;
block|}
if|if
condition|(
name|fx
operator|<
operator|-
literal|0.1f
condition|)
block|{
name|fx
operator|=
operator|-
literal|0.1f
expr_stmt|;
block|}
if|if
condition|(
name|fy
operator|<
operator|-
literal|0.1f
condition|)
block|{
name|fy
operator|=
operator|-
literal|0.1f
expr_stmt|;
block|}
if|if
condition|(
name|fz
operator|<
operator|-
literal|0.1f
condition|)
block|{
name|fz
operator|=
operator|-
literal|0.1f
expr_stmt|;
block|}
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
operator|-
name|fy
operator|*
literal|0.2F
argument_list|)
operator|*
literal|15F
decl_stmt|;
name|bob
operator|+=
operator|(
name|aaa
operator|-
name|bob
operator|)
operator|*
literal|0.4F
expr_stmt|;
name|tilt
operator|+=
operator|(
name|bbb
operator|-
name|tilt
operator|)
operator|*
literal|0.8F
expr_stmt|;
name|speedTrig
operator|=
operator|-
literal|1
expr_stmt|;
comment|// speed
name|flyTrig
operator|=
operator|-
literal|1
expr_stmt|;
comment|// fly
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
name|fly
condition|)
block|{
name|flyTrig
operator|=
operator|-
literal|1
expr_stmt|;
block|}
else|else
block|{
name|flyTrig
operator|=
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|HackState
operator|.
name|speed
condition|)
block|{
name|speedTrig
operator|=
operator|-
literal|1
expr_stmt|;
block|}
else|else
block|{
name|speedTrig
operator|=
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|HackState
operator|.
name|noclip
condition|)
block|{
name|noclipTrig
operator|=
operator|-
literal|1
expr_stmt|;
block|}
else|else
block|{
name|noclipTrig
operator|=
literal|1
expr_stmt|;
block|}
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
literal|1F
decl_stmt|;
name|oBob
operator|=
name|bob
expr_stmt|;
if|if
condition|(
name|flyingMode
operator|&&
name|flyTrig
operator|<
literal|1
condition|)
block|{
name|i
operator|=
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|noPhysics
operator|&&
name|noclipTrig
operator|<
literal|0
condition|)
block|{
name|j
operator|=
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|input
operator|.
name|mult
operator|>
literal|1F
operator|&&
name|speedTrig
operator|<
literal|1
condition|)
block|{
name|f1
operator|=
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
literal|1F
expr_stmt|;
block|}
if|if
condition|(
name|flyTrig
operator|>
literal|0
operator|||
name|speedTrig
operator|>
literal|0
condition|)
block|{
name|k
operator|=
literal|0
expr_stmt|;
block|}
name|xo
operator|=
name|x
expr_stmt|;
name|yo
operator|=
name|y
expr_stmt|;
name|zo
operator|=
name|z
expr_stmt|;
name|xRotO
operator|=
name|xRot
expr_stmt|;
name|yRotO
operator|=
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
literal|0F
decl_stmt|;
comment|// this.input.updateMovement(1);
if|if
condition|(
name|i
operator|!=
literal|0
operator|||
name|j
operator|!=
literal|0
condition|)
block|{
name|yd
operator|=
name|input
operator|.
name|elevate
expr_stmt|;
block|}
if|if
condition|(
name|onGround
operator|||
name|i
operator|!=
literal|0
condition|)
block|{
name|jumpCount
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
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
name|yd
operator|+=
literal|0.05F
expr_stmt|;
block|}
if|else if
condition|(
name|onGround
condition|)
block|{
if|if
condition|(
operator|!
name|input
operator|.
name|fall
condition|)
block|{
if|if
condition|(
operator|!
name|HacksEnabled
operator|&&
name|k
operator|!=
literal|0
condition|)
block|{
name|yd
operator|=
literal|0.48F
expr_stmt|;
block|}
else|else
block|{
name|yd
operator|=
literal|0.35F
expr_stmt|;
block|}
name|input
operator|.
name|fall
operator|=
literal|true
expr_stmt|;
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
operator|!
name|input
operator|.
name|fall
operator|&&
name|k
operator|!=
literal|0
operator|&&
name|jumpCount
operator|<
literal|3
condition|)
block|{
name|yd
operator|=
literal|0.5F
expr_stmt|;
name|input
operator|.
name|fall
operator|=
literal|true
expr_stmt|;
name|jumpCount
operator|+=
literal|1
expr_stmt|;
block|}
block|}
else|else
block|{
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
name|k
operator|!=
literal|0
operator|&&
name|jumpCount
operator|>
literal|1
condition|)
block|{
name|f1
operator|*=
literal|2.5F
expr_stmt|;
if|if
condition|(
operator|!
name|isOnIce
condition|)
block|{
name|f1
operator|*=
name|jumpCount
expr_stmt|;
block|}
else|else
block|{
name|jumpCount
operator|=
literal|0
expr_stmt|;
block|}
block|}
if|if
condition|(
name|bool1
operator|&&
name|i
operator|==
literal|0
operator|&&
name|j
operator|==
literal|0
condition|)
block|{
name|f2
operator|=
name|y
expr_stmt|;
name|super
operator|.
name|moveRelative
argument_list|(
name|input
operator|.
name|strafe
argument_list|,
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
name|xd
operator|*
name|f1
argument_list|,
name|yd
operator|*
name|f1
argument_list|,
name|zd
operator|*
name|f1
argument_list|)
expr_stmt|;
name|xd
operator|*=
literal|0.8F
expr_stmt|;
name|yd
operator|*=
literal|0.8F
expr_stmt|;
name|zd
operator|*=
literal|0.8F
expr_stmt|;
name|yd
operator|=
operator|(
name|float
operator|)
operator|(
name|yd
operator|-
literal|0.02D
operator|)
expr_stmt|;
if|if
condition|(
name|horizontalCollision
operator|&&
name|isFree
argument_list|(
name|xd
argument_list|,
name|yd
operator|+
literal|0.6F
operator|-
name|y
operator|+
name|f2
argument_list|,
name|zd
argument_list|)
condition|)
block|{
name|yd
operator|=
literal|0.3F
expr_stmt|;
block|}
return|return;
block|}
if|if
condition|(
name|bool2
operator|&&
name|i
operator|==
literal|0
operator|&&
name|j
operator|==
literal|0
condition|)
block|{
name|f2
operator|=
name|y
expr_stmt|;
name|super
operator|.
name|moveRelative
argument_list|(
name|input
operator|.
name|strafe
argument_list|,
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
name|xd
operator|*
name|f1
argument_list|,
name|yd
operator|*
name|f1
argument_list|,
name|zd
operator|*
name|f1
argument_list|)
expr_stmt|;
name|xd
operator|*=
literal|0.5F
expr_stmt|;
name|yd
operator|*=
literal|0.5F
expr_stmt|;
name|zd
operator|*=
literal|0.5F
expr_stmt|;
name|yd
operator|=
operator|(
name|float
operator|)
operator|(
name|yd
operator|-
literal|0.02D
operator|)
expr_stmt|;
if|if
condition|(
name|horizontalCollision
operator|&&
name|isFree
argument_list|(
name|xd
argument_list|,
name|yd
operator|+
literal|0.6F
operator|-
name|y
operator|+
name|f2
argument_list|,
name|zd
argument_list|)
condition|)
block|{
name|yd
operator|=
literal|0.3F
expr_stmt|;
block|}
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
literal|0F
decl_stmt|;
name|float
name|f3
init|=
literal|0F
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
block|{
name|yd
operator|=
name|input
operator|.
name|elevate
expr_stmt|;
block|}
name|f3
operator|=
literal|0.2F
expr_stmt|;
block|}
if|else if
condition|(
name|onGround
operator|||
name|jumpCount
operator|>
literal|0
operator|||
name|i
operator|!=
literal|0
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
name|input
operator|.
name|strafe
argument_list|,
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
name|j
operator|!=
literal|0
operator|&&
operator|(
name|xd
operator|!=
literal|0F
operator|||
name|zd
operator|!=
literal|0F
operator|)
condition|)
block|{
name|super
operator|.
name|moveTo
argument_list|(
name|x
operator|+
name|xd
argument_list|,
name|y
operator|+
name|yd
operator|-
name|f4
argument_list|,
name|z
operator|+
name|zd
argument_list|,
name|yRot
argument_list|,
name|xRot
argument_list|)
expr_stmt|;
name|yo
operator|=
name|y
operator|+=
name|f4
expr_stmt|;
block|}
else|else
block|{
name|super
operator|.
name|move
argument_list|(
name|xd
operator|*
name|f1
argument_list|,
name|yd
operator|*
name|f1
argument_list|,
name|zd
operator|*
name|f1
argument_list|)
expr_stmt|;
block|}
name|int
name|var1
init|=
name|level
operator|.
name|getTile
argument_list|(
operator|(
name|int
operator|)
name|x
argument_list|,
operator|(
name|int
operator|)
operator|(
name|y
operator|-
literal|2.12F
operator|)
argument_list|,
operator|(
name|int
operator|)
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
name|jumpCount
operator|==
literal|0
condition|)
block|{
name|isOnIce
operator|=
literal|false
expr_stmt|;
block|}
name|f2
operator|=
literal|0.6F
expr_stmt|;
name|xd
operator|*=
literal|0.91F
expr_stmt|;
name|yd
operator|*=
literal|0.98F
expr_stmt|;
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
name|yd
operator|*=
name|f2
operator|/
literal|4F
expr_stmt|;
name|walkDist
operator|=
literal|0F
expr_stmt|;
block|}
else|else
block|{
name|yd
operator|=
operator|(
name|float
operator|)
operator|(
name|yd
operator|-
literal|0.01D
operator|)
expr_stmt|;
block|}
name|xd
operator|*=
name|f2
expr_stmt|;
name|zd
operator|*=
name|f2
expr_stmt|;
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
name|score
parameter_list|)
block|{
name|this
operator|.
name|score
operator|+=
name|score
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
comment|//if (modelName.equals("humanoid")) {
name|newTextureId
operator|=
name|textureManager
operator|.
name|load
argument_list|(
name|newTexture
argument_list|)
expr_stmt|;
comment|//}
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
name|modelName
operator|.
name|equals
argument_list|(
literal|"humanoid"
argument_list|)
condition|?
name|textureManager
operator|.
name|load
argument_list|(
literal|"/char.png"
argument_list|)
else|:
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
expr_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
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
name|GL11
operator|.
name|GL_TEXTURE_2D
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
name|killedBy
parameter_list|)
block|{
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
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
expr_stmt|;
name|yd
operator|=
literal|0.1F
expr_stmt|;
if|if
condition|(
name|killedBy
operator|!=
literal|null
condition|)
block|{
name|xd
operator|=
operator|-
name|MathHelper
operator|.
name|cos
argument_list|(
operator|(
name|hurtDir
operator|+
name|yRot
operator|)
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
operator|/
literal|180F
argument_list|)
operator|*
literal|0.1F
expr_stmt|;
name|zd
operator|=
operator|-
name|MathHelper
operator|.
name|sin
argument_list|(
operator|(
name|hurtDir
operator|+
name|yRot
operator|)
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
operator|/
literal|180F
argument_list|)
operator|*
literal|0.1F
expr_stmt|;
block|}
else|else
block|{
name|xd
operator|=
name|zd
operator|=
literal|0F
expr_stmt|;
block|}
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
name|entity
parameter_list|,
name|int
name|amount
parameter_list|)
block|{
if|if
condition|(
operator|!
name|level
operator|.
name|creativeMode
condition|)
block|{
name|super
operator|.
name|hurt
argument_list|(
name|entity
argument_list|,
name|amount
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
name|input
operator|.
name|resetKeys
argument_list|()
expr_stmt|;
name|input
operator|.
name|canMove
operator|=
literal|false
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
name|textureManager
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
if|if
condition|(
name|settings
operator|.
name|thirdPersonMode
operator|==
literal|0
condition|)
block|{
return|return;
block|}
if|if
condition|(
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
name|attackTime
operator|-
name|var2
operator|)
operator|<
literal|0F
condition|)
block|{
name|var3
operator|=
literal|0F
expr_stmt|;
block|}
while|while
condition|(
name|yBodyRotO
operator|-
name|yBodyRot
operator|<
operator|-
literal|180F
condition|)
block|{
name|yBodyRotO
operator|+=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|yBodyRotO
operator|-
name|yBodyRot
operator|>=
literal|180F
condition|)
block|{
name|yBodyRotO
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|xRotO
operator|-
name|xRot
operator|<
operator|-
literal|180F
condition|)
block|{
name|xRotO
operator|+=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|xRotO
operator|-
name|xRot
operator|>=
literal|180F
condition|)
block|{
name|xRotO
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|yRotO
operator|-
name|yRot
operator|<
operator|-
literal|180F
condition|)
block|{
name|yRotO
operator|+=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|yRotO
operator|-
name|yRot
operator|>=
literal|180F
condition|)
block|{
name|yRotO
operator|-=
literal|360F
expr_stmt|;
block|}
name|float
name|var4
init|=
name|yBodyRotO
operator|+
operator|(
name|yBodyRot
operator|-
name|yBodyRotO
operator|)
operator|*
name|var2
decl_stmt|;
name|float
name|var5
init|=
name|oRun
operator|+
operator|(
name|run
operator|-
name|oRun
operator|)
operator|*
name|var2
decl_stmt|;
name|float
name|var6
init|=
name|yRotO
operator|+
operator|(
name|yRot
operator|-
name|yRotO
operator|)
operator|*
name|var2
decl_stmt|;
name|float
name|var7
init|=
name|xRotO
operator|+
operator|(
name|xRot
operator|-
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
name|animStepO
operator|+
operator|(
name|animStep
operator|-
name|animStepO
operator|)
operator|*
name|var2
decl_stmt|;
name|ColorCache
name|c
init|=
name|getBrightnessColor
argument_list|()
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
comment|// 1 / 16
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
literal|5F
operator|*
name|var5
operator|*
name|bobStrength
operator|-
literal|23F
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
name|var2
argument_list|,
name|yo
operator|+
operator|(
name|y
operator|-
name|yo
operator|)
operator|*
name|var2
operator|-
literal|1.62F
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
name|hurtTime
operator|-
name|var2
operator|)
operator|>
literal|0F
operator|||
name|health
operator|<=
literal|0
condition|)
block|{
if|if
condition|(
name|var11
operator|<
literal|0F
condition|)
block|{
name|var11
operator|=
literal|0F
expr_stmt|;
block|}
else|else
block|{
name|var11
operator|/=
name|hurtDuration
expr_stmt|;
name|var11
operator|=
name|MathHelper
operator|.
name|sin
argument_list|(
name|var11
operator|*
name|var11
operator|*
name|var11
operator|*
name|var11
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
operator|*
literal|14F
expr_stmt|;
block|}
name|float
name|var12
init|=
literal|0F
decl_stmt|;
if|if
condition|(
name|health
operator|<=
literal|0
condition|)
block|{
name|var12
operator|=
operator|(
name|deathTime
operator|+
name|var2
operator|)
operator|/
literal|20F
expr_stmt|;
name|var11
operator|+=
name|var12
operator|*
name|var12
operator|*
literal|800F
expr_stmt|;
if|if
condition|(
name|var11
operator|>
literal|90F
condition|)
block|{
name|var11
operator|=
literal|90F
expr_stmt|;
block|}
block|}
name|var12
operator|=
name|hurtDir
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
literal|180F
operator|-
name|var4
operator|+
name|rotOffs
operator|+
literal|45
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
name|glScalef
argument_list|(
literal|1F
argument_list|,
literal|1F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
name|var12
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
name|var11
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
name|var12
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
operator|(
literal|180F
operator|-
name|var4
operator|+
name|rotOffs
operator|)
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
literal|0F
argument_list|,
operator|-
name|var10
operator|*
name|var9
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glScalef
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
name|glRotatef
argument_list|(
literal|180F
operator|-
name|var4
operator|+
name|rotOffs
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
operator|!
name|allowAlpha
condition|)
block|{
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_ALPHA_TEST
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_CULL_FACE
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glScalef
argument_list|(
operator|-
literal|1F
argument_list|,
literal|1F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|modelCache
operator|.
name|getModel
argument_list|(
name|modelName
argument_list|)
operator|.
name|attackOffset
operator|=
name|var3
operator|/
literal|5F
expr_stmt|;
name|bindTexture
argument_list|(
name|textureManager
argument_list|)
expr_stmt|;
name|renderModel
argument_list|(
name|textureManager
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
name|invulnerableTime
operator|>
name|invulnerableDuration
operator|-
literal|10
condition|)
block|{
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
literal|0.75F
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
name|GL_ONE
argument_list|)
expr_stmt|;
name|bindTexture
argument_list|(
name|textureManager
argument_list|)
expr_stmt|;
name|renderModel
argument_list|(
name|textureManager
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
block|}
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_ALPHA_TEST
argument_list|)
expr_stmt|;
if|if
condition|(
name|allowAlpha
condition|)
block|{
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_CULL_FACE
argument_list|)
expr_stmt|;
block|}
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
literal|1F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
block|}
block|}
annotation|@
name|Override
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
if|if
condition|(
name|isInteger
argument_list|(
name|modelName
argument_list|)
condition|)
block|{
try|try
block|{
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_ALPHA_TEST
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
name|glPushMatrix
argument_list|()
expr_stmt|;
comment|// These are here to revert the scalef calls in Mob.java.
comment|// While those calls are useful for entity models, they cause the
comment|// block models to be rendered upside down.
name|GL11
operator|.
name|glScalef
argument_list|(
operator|-
literal|1F
argument_list|,
literal|1F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|1F
argument_list|,
operator|-
literal|1F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|Block
name|block
init|=
name|Block
operator|.
name|blocks
index|[
name|Integer
operator|.
name|parseInt
argument_list|(
name|modelName
argument_list|)
index|]
decl_stmt|;
comment|// TODO: Implement proper detection of which blocks need translation.
name|float
name|yTranslation
init|=
operator|-
literal|1.4F
decl_stmt|;
if|if
condition|(
name|block
operator|instanceof
name|FlowerBlock
operator|||
name|block
operator|instanceof
name|FireBlock
condition|)
block|{
name|yTranslation
operator|=
operator|-
literal|1.8F
expr_stmt|;
block|}
name|GL11
operator|.
name|glTranslatef
argument_list|(
operator|-
literal|0.5F
argument_list|,
name|yTranslation
argument_list|,
operator|-
literal|0.2F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|var1
operator|.
name|load
argument_list|(
literal|"/terrain.png"
argument_list|)
argument_list|)
expr_stmt|;
name|block
operator|.
name|renderPreview
argument_list|(
name|ShapeRenderer
operator|.
name|instance
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
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
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|modelName
operator|=
literal|"humanoid"
expr_stmt|;
block|}
return|return;
block|}
name|Model
name|model
init|=
name|modelCache
operator|.
name|getModel
argument_list|(
name|modelName
argument_list|)
decl_stmt|;
if|if
condition|(
name|hasHair
operator|&&
name|model
operator|instanceof
name|HumanoidModel
condition|)
block|{
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_CULL_FACE
argument_list|)
expr_stmt|;
name|HumanoidModel
name|modelHeadwear
init|=
literal|null
decl_stmt|;
name|modelHeadwear
operator|=
operator|(
name|HumanoidModel
operator|)
name|model
expr_stmt|;
name|modelHeadwear
operator|.
name|headwear
operator|.
name|yaw
operator|=
name|modelHeadwear
operator|.
name|head
operator|.
name|yaw
expr_stmt|;
name|modelHeadwear
operator|.
name|headwear
operator|.
name|pitch
operator|=
name|modelHeadwear
operator|.
name|head
operator|.
name|pitch
expr_stmt|;
name|modelHeadwear
operator|.
name|headwear
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_CULL_FACE
argument_list|)
expr_stmt|;
block|}
name|model
operator|.
name|render
argument_list|(
name|var2
argument_list|,
name|var4
argument_list|,
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
name|heightOffset
operator|=
literal|1.62F
expr_stmt|;
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
name|level
operator|!=
literal|null
condition|)
block|{
name|level
operator|.
name|player
operator|=
name|this
expr_stmt|;
block|}
name|health
operator|=
literal|20
expr_stmt|;
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

