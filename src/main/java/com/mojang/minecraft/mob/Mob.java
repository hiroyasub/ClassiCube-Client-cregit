begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|mob
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
name|ai
operator|.
name|AI
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
name|ai
operator|.
name|BasicAI
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
name|ModelManager
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
name|Mob
extends|extends
name|Entity
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
name|ATTACK_DURATION
init|=
literal|5
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|TOTAL_AIR_SUPPLY
init|=
literal|300
decl_stmt|;
specifier|public
specifier|static
name|ModelManager
name|modelCache
decl_stmt|;
specifier|public
name|int
name|invulnerableDuration
init|=
literal|20
decl_stmt|;
specifier|public
name|float
name|rot
decl_stmt|;
specifier|public
name|float
name|timeOffs
decl_stmt|;
specifier|public
name|float
name|speed
decl_stmt|;
specifier|public
name|float
name|rotA
init|=
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|+
literal|1.0D
operator|)
operator|*
literal|0.01F
decl_stmt|;
specifier|protected
name|float
name|yBodyRot
init|=
literal|0F
decl_stmt|;
specifier|protected
name|float
name|yBodyRotO
init|=
literal|0F
decl_stmt|;
specifier|protected
name|float
name|oRun
decl_stmt|;
specifier|protected
name|float
name|run
decl_stmt|;
specifier|protected
name|float
name|animStep
decl_stmt|;
specifier|protected
name|float
name|animStepO
decl_stmt|;
specifier|protected
name|int
name|tickCount
init|=
literal|0
decl_stmt|;
specifier|public
name|boolean
name|hasHair
init|=
literal|true
decl_stmt|;
specifier|protected
name|String
name|textureName
init|=
literal|"/char.png"
decl_stmt|;
specifier|public
name|boolean
name|allowAlpha
init|=
literal|true
decl_stmt|;
specifier|public
name|float
name|rotOffs
init|=
literal|0F
decl_stmt|;
specifier|public
name|String
name|modelName
init|=
literal|null
decl_stmt|;
specifier|protected
name|float
name|bobStrength
init|=
literal|1F
decl_stmt|;
specifier|protected
name|int
name|deathScore
init|=
literal|0
decl_stmt|;
specifier|public
name|float
name|renderOffset
init|=
literal|0F
decl_stmt|;
specifier|public
name|int
name|health
init|=
literal|20
decl_stmt|;
specifier|public
name|int
name|lastHealth
decl_stmt|;
specifier|public
name|int
name|invulnerableTime
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|airSupply
init|=
literal|300
decl_stmt|;
specifier|public
name|int
name|hurtTime
decl_stmt|;
specifier|public
name|int
name|hurtDuration
decl_stmt|;
specifier|public
name|float
name|hurtDir
init|=
literal|0F
decl_stmt|;
specifier|public
name|int
name|deathTime
init|=
literal|0
decl_stmt|;
specifier|public
name|int
name|attackTime
init|=
literal|0
decl_stmt|;
specifier|public
name|float
name|oTilt
decl_stmt|;
specifier|public
name|float
name|tilt
decl_stmt|;
specifier|protected
name|boolean
name|dead
init|=
literal|false
decl_stmt|;
specifier|public
name|AI
name|ai
decl_stmt|;
specifier|public
name|Mob
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
name|timeOffs
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|12398F
expr_stmt|;
name|rot
operator|=
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|3.1415927410125732D
operator|*
literal|2.0D
operator|)
expr_stmt|;
name|speed
operator|=
literal|1F
expr_stmt|;
name|ai
operator|=
operator|new
name|BasicAI
argument_list|()
expr_stmt|;
name|footSize
operator|=
literal|0.5F
expr_stmt|;
block|}
specifier|public
name|void
name|aiStep
parameter_list|()
block|{
if|if
condition|(
name|ai
operator|!=
literal|null
condition|)
block|{
name|ai
operator|.
name|tick
argument_list|(
name|level
argument_list|,
name|this
argument_list|)
expr_stmt|;
block|}
block|}
specifier|protected
name|void
name|bindTexture
parameter_list|(
name|TextureManager
name|var1
parameter_list|)
block|{
name|textureId
operator|=
name|var1
operator|.
name|load
argument_list|(
name|textureName
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
name|textureId
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|protected
name|void
name|causeFallDamage
parameter_list|(
name|float
name|var1
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
name|int
name|var2
decl_stmt|;
if|if
condition|(
operator|(
name|var2
operator|=
operator|(
name|int
operator|)
name|Math
operator|.
name|ceil
argument_list|(
name|var1
operator|-
literal|3F
argument_list|)
operator|)
operator|>
literal|0
condition|)
block|{
name|hurt
argument_list|(
operator|(
name|Entity
operator|)
literal|null
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
block|}
specifier|public
name|void
name|die
parameter_list|(
name|Entity
name|var1
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
if|if
condition|(
name|deathScore
operator|>
literal|0
operator|&&
name|var1
operator|!=
literal|null
condition|)
block|{
name|var1
operator|.
name|awardKillScore
argument_list|(
name|this
argument_list|,
name|deathScore
argument_list|)
expr_stmt|;
block|}
name|dead
operator|=
literal|true
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|heal
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
if|if
condition|(
name|health
operator|>
literal|0
condition|)
block|{
name|health
operator|+=
name|var1
expr_stmt|;
if|if
condition|(
name|health
operator|>
literal|20
condition|)
block|{
name|health
operator|=
literal|20
expr_stmt|;
block|}
name|invulnerableTime
operator|=
name|invulnerableDuration
operator|/
literal|2
expr_stmt|;
block|}
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
name|level
operator|.
name|creativeMode
condition|)
block|{
if|if
condition|(
name|health
operator|>
literal|0
condition|)
block|{
if|if
condition|(
name|ai
operator|!=
literal|null
condition|)
block|{
name|ai
operator|.
name|hurt
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|invulnerableTime
operator|>
name|invulnerableDuration
operator|/
literal|2F
condition|)
block|{
if|if
condition|(
name|lastHealth
operator|-
name|var2
operator|>=
name|health
condition|)
block|{
return|return;
block|}
name|health
operator|=
name|lastHealth
operator|-
name|var2
expr_stmt|;
block|}
else|else
block|{
name|lastHealth
operator|=
name|health
expr_stmt|;
name|invulnerableTime
operator|=
name|invulnerableDuration
expr_stmt|;
name|health
operator|-=
name|var2
expr_stmt|;
name|hurtTime
operator|=
name|hurtDuration
operator|=
literal|10
expr_stmt|;
block|}
name|hurtDir
operator|=
literal|0F
expr_stmt|;
if|if
condition|(
name|var1
operator|!=
literal|null
condition|)
block|{
name|float
name|var3
init|=
name|var1
operator|.
name|x
operator|-
name|x
decl_stmt|;
name|float
name|var4
init|=
name|var1
operator|.
name|z
operator|-
name|z
decl_stmt|;
name|hurtDir
operator|=
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|atan2
argument_list|(
name|var4
argument_list|,
name|var3
argument_list|)
operator|*
literal|180.0D
operator|/
literal|3.1415927410125732D
operator|)
operator|-
name|yRot
expr_stmt|;
name|knockback
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
else|else
block|{
name|hurtDir
operator|=
operator|(
name|int
operator|)
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|2.0D
operator|)
operator|*
literal|180
expr_stmt|;
block|}
if|if
condition|(
name|health
operator|<=
literal|0
condition|)
block|{
name|die
argument_list|(
name|var1
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
annotation|@
name|Override
specifier|public
name|boolean
name|isPickable
parameter_list|()
block|{
return|return
operator|!
name|removed
return|;
block|}
annotation|@
name|Override
specifier|public
name|boolean
name|isPushable
parameter_list|()
block|{
return|return
operator|!
name|removed
operator|&&
operator|!
name|noPhysics
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
name|knockback
parameter_list|(
name|Entity
name|var1
parameter_list|,
name|int
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|float
name|var4
parameter_list|)
block|{
name|float
name|var5
init|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|var3
operator|*
name|var3
operator|+
name|var4
operator|*
name|var4
argument_list|)
decl_stmt|;
name|float
name|var6
init|=
literal|0.4F
decl_stmt|;
name|xd
operator|/=
literal|2F
expr_stmt|;
name|yd
operator|/=
literal|2F
expr_stmt|;
name|zd
operator|/=
literal|2F
expr_stmt|;
name|xd
operator|-=
name|var3
operator|/
name|var5
operator|*
name|var6
expr_stmt|;
name|yd
operator|+=
literal|0.4F
expr_stmt|;
name|zd
operator|-=
name|var4
operator|/
name|var5
operator|*
name|var6
expr_stmt|;
if|if
condition|(
name|yd
operator|>
literal|0.4F
condition|)
block|{
name|yd
operator|=
literal|0.4F
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
name|varaa
init|=
name|getBrightnessColor
argument_list|()
decl_stmt|;
name|GL11
operator|.
name|glColor3f
argument_list|(
name|varaa
operator|.
name|R
argument_list|,
name|varaa
operator|.
name|G
argument_list|,
name|varaa
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
operator|=
name|MathHelper
operator|.
name|sin
argument_list|(
operator|(
name|var11
operator|/=
name|hurtDuration
operator|)
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
if|if
condition|(
operator|(
name|var11
operator|+=
name|var12
operator|*
name|var12
operator|*
literal|800F
operator|)
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
name|var1
argument_list|)
expr_stmt|;
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
name|var1
argument_list|)
expr_stmt|;
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
name|modelName
argument_list|)
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
specifier|final
name|void
name|tick
parameter_list|()
block|{
name|super
operator|.
name|tick
argument_list|()
expr_stmt|;
name|oTilt
operator|=
name|tilt
expr_stmt|;
if|if
condition|(
name|attackTime
operator|>
literal|0
condition|)
block|{
operator|--
name|attackTime
expr_stmt|;
block|}
if|if
condition|(
name|hurtTime
operator|>
literal|0
condition|)
block|{
operator|--
name|hurtTime
expr_stmt|;
block|}
if|if
condition|(
name|invulnerableTime
operator|>
literal|0
condition|)
block|{
operator|--
name|invulnerableTime
expr_stmt|;
block|}
if|if
condition|(
name|health
operator|<=
literal|0
condition|)
block|{
operator|++
name|deathTime
expr_stmt|;
if|if
condition|(
name|deathTime
operator|>
literal|20
condition|)
block|{
if|if
condition|(
name|ai
operator|!=
literal|null
condition|)
block|{
name|ai
operator|.
name|beforeRemove
argument_list|()
expr_stmt|;
block|}
name|remove
argument_list|()
expr_stmt|;
block|}
block|}
if|if
condition|(
name|isUnderWater
argument_list|()
condition|)
block|{
if|if
condition|(
name|airSupply
operator|>
literal|0
condition|)
block|{
operator|--
name|airSupply
expr_stmt|;
block|}
else|else
block|{
name|hurt
argument_list|(
operator|(
name|Entity
operator|)
literal|null
argument_list|,
literal|2
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|airSupply
operator|=
literal|300
expr_stmt|;
block|}
if|if
condition|(
name|isInWater
argument_list|()
condition|)
block|{
name|fallDistance
operator|=
literal|0F
expr_stmt|;
block|}
if|if
condition|(
name|isInLava
argument_list|()
condition|)
block|{
name|hurt
argument_list|(
operator|(
name|Entity
operator|)
literal|null
argument_list|,
literal|10
argument_list|)
expr_stmt|;
block|}
name|animStepO
operator|=
name|animStep
expr_stmt|;
name|yBodyRotO
operator|=
name|yBodyRot
expr_stmt|;
name|yRotO
operator|=
name|yRot
expr_stmt|;
name|xRotO
operator|=
name|xRot
expr_stmt|;
operator|++
name|tickCount
expr_stmt|;
name|aiStep
argument_list|()
expr_stmt|;
name|float
name|var1
init|=
name|x
operator|-
name|xo
decl_stmt|;
name|float
name|var2
init|=
name|z
operator|-
name|zo
decl_stmt|;
name|float
name|var3
init|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|var1
operator|*
name|var1
operator|+
name|var2
operator|*
name|var2
argument_list|)
decl_stmt|;
name|float
name|var4
init|=
name|yBodyRot
decl_stmt|;
name|float
name|var5
init|=
literal|0F
decl_stmt|;
name|oRun
operator|=
name|run
expr_stmt|;
name|float
name|var6
init|=
literal|0F
decl_stmt|;
if|if
condition|(
name|var3
operator|>
literal|0.05F
condition|)
block|{
name|var6
operator|=
literal|1F
expr_stmt|;
name|var5
operator|=
name|var3
operator|*
literal|3F
expr_stmt|;
name|var4
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|atan2
argument_list|(
name|var2
argument_list|,
name|var1
argument_list|)
operator|*
literal|180F
operator|/
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
operator|-
literal|90F
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|onGround
condition|)
block|{
name|var6
operator|=
literal|0F
expr_stmt|;
block|}
name|run
operator|+=
operator|(
name|var6
operator|-
name|run
operator|)
operator|*
literal|0.3F
expr_stmt|;
for|for
control|(
name|var1
operator|=
name|var4
operator|-
name|yBodyRot
init|;
name|var1
operator|<
operator|-
literal|180F
condition|;
name|var1
operator|+=
literal|360F
control|)
block|{
empty_stmt|;
block|}
while|while
condition|(
name|var1
operator|>=
literal|180F
condition|)
block|{
name|var1
operator|-=
literal|360F
expr_stmt|;
block|}
name|yBodyRot
operator|+=
name|var1
operator|*
literal|0.1F
expr_stmt|;
for|for
control|(
name|var1
operator|=
name|yRot
operator|-
name|yBodyRot
init|;
name|var1
operator|<
operator|-
literal|180F
condition|;
name|var1
operator|+=
literal|360F
control|)
block|{
empty_stmt|;
block|}
while|while
condition|(
name|var1
operator|>=
literal|180F
condition|)
block|{
name|var1
operator|-=
literal|360F
expr_stmt|;
block|}
name|boolean
name|var7
init|=
name|var1
operator|<
operator|-
literal|90F
operator|||
name|var1
operator|>=
literal|90F
decl_stmt|;
if|if
condition|(
name|var1
operator|<
operator|-
literal|75F
condition|)
block|{
name|var1
operator|=
operator|-
literal|75F
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|>=
literal|75F
condition|)
block|{
name|var1
operator|=
literal|75F
expr_stmt|;
block|}
name|yBodyRot
operator|=
name|yRot
operator|-
name|var1
expr_stmt|;
name|yBodyRot
operator|+=
name|var1
operator|*
literal|0.1F
expr_stmt|;
if|if
condition|(
name|var7
condition|)
block|{
name|var5
operator|=
operator|-
name|var5
expr_stmt|;
block|}
while|while
condition|(
name|yRot
operator|-
name|yRotO
operator|<
operator|-
literal|180F
condition|)
block|{
name|yRotO
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|yRot
operator|-
name|yRotO
operator|>=
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
name|yBodyRot
operator|-
name|yBodyRotO
operator|<
operator|-
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
name|yBodyRot
operator|-
name|yBodyRotO
operator|>=
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
name|xRot
operator|-
name|xRotO
operator|<
operator|-
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
name|xRot
operator|-
name|xRotO
operator|>=
literal|180F
condition|)
block|{
name|xRotO
operator|+=
literal|360F
expr_stmt|;
block|}
name|animStep
operator|+=
name|var5
expr_stmt|;
block|}
specifier|public
name|void
name|travel
parameter_list|(
name|float
name|yya
parameter_list|,
name|float
name|xxa
parameter_list|)
block|{
name|float
name|y1
decl_stmt|;
name|float
name|multiply
init|=
literal|1F
decl_stmt|;
if|if
condition|(
name|ai
operator|instanceof
name|BasicAI
condition|)
block|{
name|BasicAI
name|ai1
init|=
operator|(
name|BasicAI
operator|)
name|ai
decl_stmt|;
if|if
condition|(
operator|!
name|flyingMode
condition|)
block|{
if|if
condition|(
name|ai1
operator|.
name|running
condition|)
block|{
name|multiply
operator|=
literal|10F
expr_stmt|;
comment|// 6x with momentum
block|}
else|else
block|{
name|multiply
operator|=
literal|1F
expr_stmt|;
comment|// 1x
block|}
block|}
if|else if
condition|(
name|flyingMode
operator|&&
name|ai1
operator|.
name|running
condition|)
block|{
name|multiply
operator|=
literal|90F
expr_stmt|;
comment|// 6x
block|}
else|else
block|{
name|multiply
operator|=
literal|15F
expr_stmt|;
comment|// 1x
block|}
block|}
if|if
condition|(
name|isInWater
argument_list|()
operator|&&
operator|!
name|flyingMode
operator|&&
operator|!
name|noPhysics
condition|)
block|{
name|y1
operator|=
name|y
expr_stmt|;
name|moveRelative
argument_list|(
name|yya
argument_list|,
name|xxa
operator|*
name|multiply
argument_list|,
literal|0.02F
operator|*
name|multiply
argument_list|)
expr_stmt|;
name|move
argument_list|(
name|xd
argument_list|,
name|yd
argument_list|,
name|zd
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
name|y1
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
block|}
if|else if
condition|(
name|isInLava
argument_list|()
operator|&&
operator|!
name|flyingMode
operator|&&
operator|!
name|noPhysics
condition|)
block|{
name|y1
operator|=
name|y
expr_stmt|;
name|moveRelative
argument_list|(
name|yya
argument_list|,
name|xxa
operator|*
name|multiply
argument_list|,
literal|0.02F
operator|*
name|multiply
argument_list|)
expr_stmt|;
name|move
argument_list|(
name|xd
argument_list|,
name|yd
argument_list|,
name|zd
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
name|y1
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
block|}
if|else if
condition|(
name|isInOrOnRope
argument_list|()
operator|&&
operator|!
name|flyingMode
operator|&&
operator|!
name|noPhysics
condition|)
block|{
name|y1
operator|=
name|y
expr_stmt|;
name|multiply
operator|=
literal|1.7f
expr_stmt|;
name|moveRelative
argument_list|(
name|yya
argument_list|,
name|xxa
argument_list|,
literal|0.02F
operator|*
name|multiply
argument_list|)
expr_stmt|;
name|move
argument_list|(
name|xd
argument_list|,
name|yd
argument_list|,
name|zd
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
operator|*
name|multiply
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
name|y1
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
block|}
else|else
block|{
if|if
condition|(
operator|!
name|flyingMode
condition|)
block|{
name|moveRelative
argument_list|(
name|yya
argument_list|,
name|xxa
argument_list|,
operator|(
name|onGround
condition|?
literal|0.1F
else|:
literal|0.02F
operator|)
operator|*
name|multiply
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|moveRelative
argument_list|(
name|yya
argument_list|,
name|xxa
argument_list|,
literal|0.02F
operator|*
name|multiply
argument_list|)
expr_stmt|;
block|}
name|float
name|m
init|=
name|multiply
operator|/
literal|5
decl_stmt|;
if|if
condition|(
name|m
operator|<
literal|1
condition|)
block|{
name|m
operator|=
literal|1
expr_stmt|;
block|}
name|move
argument_list|(
name|xd
argument_list|,
name|yd
operator|*
name|m
argument_list|,
name|zd
argument_list|)
expr_stmt|;
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
name|yd
operator|=
operator|(
name|float
operator|)
operator|(
name|yd
operator|-
literal|0.08D
operator|)
expr_stmt|;
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
name|flyingMode
condition|)
block|{
name|y1
operator|=
literal|0F
expr_stmt|;
name|xd
operator|*=
name|y1
expr_stmt|;
name|zd
operator|*=
name|y1
expr_stmt|;
block|}
if|if
condition|(
name|onGround
operator|&&
operator|!
name|flyingMode
condition|)
block|{
name|y1
operator|=
literal|0.6F
expr_stmt|;
name|xd
operator|*=
name|y1
expr_stmt|;
name|zd
operator|*=
name|y1
expr_stmt|;
block|}
block|}
else|else
block|{
name|double
name|limit
init|=
literal|0.246D
decl_stmt|;
if|if
condition|(
name|xd
operator|>
name|limit
operator|||
name|xd
operator|<
operator|-
name|limit
operator|||
name|zd
operator|<
operator|-
name|limit
operator|||
name|zd
operator|>
name|limit
condition|)
block|{
name|tilt
operator|=
operator|-
literal|20f
expr_stmt|;
block|}
if|if
condition|(
name|xd
operator|>
name|limit
condition|)
block|{
name|xd
operator|=
operator|(
name|float
operator|)
name|limit
expr_stmt|;
block|}
if|if
condition|(
name|xd
operator|<
operator|-
name|limit
condition|)
block|{
name|xd
operator|=
operator|(
name|float
operator|)
operator|-
name|limit
expr_stmt|;
block|}
if|if
condition|(
name|zd
operator|<
operator|-
name|limit
condition|)
block|{
name|zd
operator|=
operator|(
name|float
operator|)
operator|-
name|limit
expr_stmt|;
block|}
if|if
condition|(
name|zd
operator|>
name|limit
condition|)
block|{
name|zd
operator|=
operator|(
name|float
operator|)
name|limit
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

