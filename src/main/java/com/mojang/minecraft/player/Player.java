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
specifier|public
name|Player
parameter_list|(
name|Level
name|var1
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
block|}
annotation|@
name|Override
specifier|public
name|void
name|aiStep
parameter_list|()
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
argument_list|()
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
name|render
parameter_list|(
name|TextureManager
name|var1
parameter_list|,
name|float
name|var2
parameter_list|)
block|{
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
name|remove
parameter_list|()
block|{
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
block|}
end_class

end_unit

