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
operator|.
name|ai
package|;
end_package

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
name|java
operator|.
name|util
operator|.
name|Random
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
name|player
operator|.
name|Player
import|;
end_import

begin_class
specifier|public
class|class
name|BasicAI
extends|extends
name|AI
block|{
specifier|public
name|Random
name|random
init|=
operator|new
name|Random
argument_list|()
decl_stmt|;
specifier|public
name|float
name|xxa
decl_stmt|;
specifier|public
name|float
name|yya
decl_stmt|;
specifier|public
name|Level
name|level
decl_stmt|;
specifier|public
name|Mob
name|mob
decl_stmt|;
specifier|public
name|boolean
name|jumping
init|=
literal|false
decl_stmt|;
specifier|public
name|float
name|runSpeed
init|=
literal|0.7F
decl_stmt|;
specifier|public
name|Entity
name|attackTarget
init|=
literal|null
decl_stmt|;
specifier|public
name|boolean
name|running
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|flying
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|flyingUp
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|flyingDown
init|=
literal|false
decl_stmt|;
specifier|protected
name|float
name|yRotA
decl_stmt|;
specifier|protected
name|int
name|attackDelay
init|=
literal|0
decl_stmt|;
specifier|protected
name|int
name|noActionTime
init|=
literal|0
decl_stmt|;
annotation|@
name|Override
specifier|public
name|void
name|beforeRemove
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
name|entity
parameter_list|,
name|int
name|amount
parameter_list|)
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
name|noActionTime
operator|=
literal|0
expr_stmt|;
block|}
specifier|protected
name|void
name|jumpFromGround
parameter_list|()
block|{
if|if
condition|(
operator|!
name|running
condition|)
block|{
name|mob
operator|.
name|yd
operator|=
literal|0.42F
expr_stmt|;
block|}
else|else
block|{
name|mob
operator|.
name|yd
operator|=
literal|0.84F
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|tick
parameter_list|(
name|Level
name|level
parameter_list|,
name|Mob
name|mob
parameter_list|)
block|{
operator|++
name|noActionTime
expr_stmt|;
name|Entity
name|player
init|=
name|level
operator|.
name|getPlayer
argument_list|()
decl_stmt|;
if|if
condition|(
name|noActionTime
operator|>
literal|600
operator|&&
name|random
operator|.
name|nextInt
argument_list|(
literal|800
argument_list|)
operator|==
literal|0
operator|&&
name|player
operator|!=
literal|null
condition|)
block|{
name|float
name|var4
init|=
name|player
operator|.
name|x
operator|-
name|mob
operator|.
name|x
decl_stmt|;
name|float
name|var5
init|=
name|player
operator|.
name|y
operator|-
name|mob
operator|.
name|y
decl_stmt|;
name|float
name|var6
init|=
name|player
operator|.
name|z
operator|-
name|mob
operator|.
name|z
decl_stmt|;
if|if
condition|(
name|var4
operator|*
name|var4
operator|+
name|var5
operator|*
name|var5
operator|+
name|var6
operator|*
name|var6
operator|<
literal|1024F
condition|)
block|{
name|noActionTime
operator|=
literal|0
expr_stmt|;
block|}
else|else
block|{
name|mob
operator|.
name|remove
argument_list|()
expr_stmt|;
block|}
block|}
name|this
operator|.
name|level
operator|=
name|level
expr_stmt|;
name|this
operator|.
name|mob
operator|=
name|mob
expr_stmt|;
if|if
condition|(
name|attackDelay
operator|>
literal|0
condition|)
block|{
operator|--
name|attackDelay
expr_stmt|;
block|}
if|if
condition|(
name|mob
operator|.
name|health
operator|<=
literal|0
condition|)
block|{
name|jumping
operator|=
literal|false
expr_stmt|;
name|xxa
operator|=
literal|0F
expr_stmt|;
name|yya
operator|=
literal|0F
expr_stmt|;
name|yRotA
operator|=
literal|0F
expr_stmt|;
block|}
else|else
block|{
name|update
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|mob
operator|instanceof
name|Player
operator|&&
operator|(
operator|(
name|Player
operator|)
name|this
operator|.
name|mob
operator|)
operator|.
name|input
operator|.
name|HacksMode
operator|==
literal|0
condition|)
block|{
comment|// if normal hax
if|if
condition|(
operator|!
name|HackState
operator|.
name|fly
condition|)
block|{
name|flyingDown
operator|=
literal|false
expr_stmt|;
name|flyingUp
operator|=
literal|false
expr_stmt|;
name|this
operator|.
name|mob
operator|.
name|flyingMode
operator|=
literal|false
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|HackState
operator|.
name|noclip
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|noPhysics
operator|=
literal|false
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|HackState
operator|.
name|speed
condition|)
block|{
name|running
operator|=
literal|false
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|mob
operator|.
name|flyingMode
operator|||
name|this
operator|.
name|mob
operator|.
name|noPhysics
condition|)
block|{
name|mob
operator|.
name|yd
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|mob
operator|.
name|flyingMode
operator|&&
operator|!
name|this
operator|.
name|mob
operator|.
name|noPhysics
condition|)
block|{
if|if
condition|(
name|flyingUp
condition|)
block|{
comment|// LogUtil.logInfo("flying up");
if|if
condition|(
name|running
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.08F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.06F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|flyingDown
condition|)
block|{
comment|// LogUtil.logInfo("flying down");
if|if
condition|(
name|running
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
operator|-
literal|0.08F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
operator|-
literal|0.06F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|jumping
condition|)
block|{
if|if
condition|(
name|running
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.08F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.06F
expr_stmt|;
block|}
block|}
block|}
if|else if
condition|(
name|this
operator|.
name|mob
operator|.
name|noPhysics
operator|&&
operator|!
name|this
operator|.
name|mob
operator|.
name|flyingMode
condition|)
block|{
if|if
condition|(
name|flyingUp
condition|)
block|{
if|if
condition|(
name|running
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.48F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.26F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|flyingDown
condition|)
block|{
if|if
condition|(
name|running
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
operator|-
literal|0.48F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
operator|-
literal|0.26F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|jumping
condition|)
block|{
if|if
condition|(
name|running
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.48F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.26F
expr_stmt|;
block|}
block|}
block|}
if|else if
condition|(
name|this
operator|.
name|mob
operator|.
name|noPhysics
operator|&&
name|this
operator|.
name|mob
operator|.
name|flyingMode
condition|)
block|{
if|if
condition|(
name|flyingUp
condition|)
block|{
comment|// LogUtil.logInfo("flying up");
if|if
condition|(
name|running
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.08F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.06F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|flyingDown
condition|)
block|{
comment|// LogUtil.logInfo("flying down");
if|if
condition|(
name|running
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
operator|-
literal|0.08F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
operator|-
literal|0.06F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|jumping
condition|)
block|{
if|if
condition|(
name|running
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.08F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.06F
expr_stmt|;
block|}
block|}
block|}
else|else
block|{
if|if
condition|(
name|jumping
operator|&&
name|this
operator|.
name|mob
operator|.
name|isInOrOnRope
argument_list|()
operator|&&
name|this
operator|.
name|mob
operator|.
name|yd
operator|>
literal|0.02f
condition|)
block|{
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.02F
expr_stmt|;
block|}
block|}
block|}
if|if
condition|(
name|jumping
condition|)
block|{
if|if
condition|(
name|mob
operator|.
name|isInWater
argument_list|()
condition|)
block|{
if|if
condition|(
operator|!
name|running
condition|)
block|{
name|mob
operator|.
name|yd
operator|+=
literal|0.04F
expr_stmt|;
block|}
else|else
block|{
name|mob
operator|.
name|yd
operator|+=
literal|0.08F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|mob
operator|.
name|isInLava
argument_list|()
condition|)
block|{
if|if
condition|(
operator|!
name|running
condition|)
block|{
name|mob
operator|.
name|yd
operator|+=
literal|0.04F
expr_stmt|;
block|}
else|else
block|{
name|mob
operator|.
name|yd
operator|+=
literal|0.08F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|mob
operator|.
name|isInOrOnRope
argument_list|()
condition|)
block|{
if|if
condition|(
operator|!
name|running
condition|)
block|{
name|mob
operator|.
name|yd
operator|+=
literal|0.1F
expr_stmt|;
block|}
else|else
block|{
name|mob
operator|.
name|yd
operator|+=
literal|0.15F
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|mob
operator|.
name|onGround
condition|)
block|{
comment|// if on the ground
name|jumpFromGround
argument_list|()
expr_stmt|;
block|}
block|}
name|xxa
operator|*=
literal|0.98F
expr_stmt|;
name|yya
operator|*=
literal|0.98F
expr_stmt|;
name|yRotA
operator|*=
literal|0.9F
expr_stmt|;
name|mob
operator|.
name|travel
argument_list|(
name|xxa
argument_list|,
name|yya
argument_list|)
expr_stmt|;
name|List
argument_list|<
name|Entity
argument_list|>
name|neighbourEntities
init|=
name|level
operator|.
name|findEntities
argument_list|(
name|mob
argument_list|,
name|mob
operator|.
name|boundingBox
operator|.
name|grow
argument_list|(
literal|0.2F
argument_list|,
literal|0F
argument_list|,
literal|0.2F
argument_list|)
argument_list|)
decl_stmt|;
if|if
condition|(
name|neighbourEntities
operator|!=
literal|null
operator|&&
name|neighbourEntities
operator|.
name|size
argument_list|()
operator|>
literal|0
condition|)
block|{
for|for
control|(
name|Entity
name|entity
range|:
name|neighbourEntities
control|)
block|{
if|if
condition|(
name|entity
operator|.
name|isPushable
argument_list|()
condition|)
block|{
name|entity
operator|.
name|push
argument_list|(
name|mob
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
specifier|protected
name|void
name|update
parameter_list|()
block|{
if|if
condition|(
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.07F
condition|)
block|{
name|xxa
operator|=
operator|(
name|random
operator|.
name|nextFloat
argument_list|()
operator|-
literal|0.5F
operator|)
operator|*
name|runSpeed
expr_stmt|;
name|yya
operator|=
name|random
operator|.
name|nextFloat
argument_list|()
operator|*
name|runSpeed
expr_stmt|;
block|}
name|jumping
operator|=
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.01F
expr_stmt|;
if|if
condition|(
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.04F
condition|)
block|{
name|yRotA
operator|=
operator|(
name|random
operator|.
name|nextFloat
argument_list|()
operator|-
literal|0.5F
operator|)
operator|*
literal|60F
expr_stmt|;
block|}
name|mob
operator|.
name|yRot
operator|+=
name|yRotA
expr_stmt|;
name|mob
operator|.
name|xRot
operator|=
name|defaultLookAngle
expr_stmt|;
if|if
condition|(
name|attackTarget
operator|!=
literal|null
condition|)
block|{
name|yya
operator|=
name|runSpeed
expr_stmt|;
name|jumping
operator|=
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.04F
expr_stmt|;
block|}
if|if
condition|(
name|mob
operator|.
name|isInWater
argument_list|()
operator|||
name|mob
operator|.
name|isInLava
argument_list|()
condition|)
block|{
name|jumping
operator|=
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.8F
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

