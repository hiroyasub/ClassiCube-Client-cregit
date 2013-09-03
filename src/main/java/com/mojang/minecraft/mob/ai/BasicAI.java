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

begin_class
specifier|public
class|class
name|BasicAI
extends|extends
name|AI
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
specifier|protected
name|float
name|yRotA
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
specifier|protected
name|int
name|attackDelay
init|=
literal|0
decl_stmt|;
specifier|public
name|float
name|runSpeed
init|=
literal|0.7F
decl_stmt|;
specifier|protected
name|int
name|noActionTime
init|=
literal|0
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
specifier|public
name|void
name|tick
parameter_list|(
name|Level
name|var1
parameter_list|,
name|Mob
name|var2
parameter_list|)
block|{
operator|++
name|this
operator|.
name|noActionTime
expr_stmt|;
name|Entity
name|var3
decl_stmt|;
if|if
condition|(
name|this
operator|.
name|noActionTime
operator|>
literal|600
operator|&&
name|this
operator|.
name|random
operator|.
name|nextInt
argument_list|(
literal|800
argument_list|)
operator|==
literal|0
operator|&&
operator|(
name|var3
operator|=
name|var1
operator|.
name|getPlayer
argument_list|()
operator|)
operator|!=
literal|null
condition|)
block|{
name|float
name|var4
init|=
name|var3
operator|.
name|x
operator|-
name|var2
operator|.
name|x
decl_stmt|;
name|float
name|var5
init|=
name|var3
operator|.
name|y
operator|-
name|var2
operator|.
name|y
decl_stmt|;
name|float
name|var6
init|=
name|var3
operator|.
name|z
operator|-
name|var2
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
literal|1024.0F
condition|)
block|{
name|this
operator|.
name|noActionTime
operator|=
literal|0
expr_stmt|;
block|}
else|else
block|{
name|var2
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
name|var1
expr_stmt|;
name|this
operator|.
name|mob
operator|=
name|var2
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|attackDelay
operator|>
literal|0
condition|)
block|{
operator|--
name|this
operator|.
name|attackDelay
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|.
name|health
operator|<=
literal|0
condition|)
block|{
name|this
operator|.
name|jumping
operator|=
literal|false
expr_stmt|;
name|this
operator|.
name|xxa
operator|=
literal|0.0F
expr_stmt|;
name|this
operator|.
name|yya
operator|=
literal|0.0F
expr_stmt|;
name|this
operator|.
name|yRotA
operator|=
literal|0.0F
expr_stmt|;
block|}
else|else
block|{
name|this
operator|.
name|update
argument_list|()
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
name|var2
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
name|this
operator|.
name|flyingUp
condition|)
block|{
comment|// System.out.println("flying up");
if|if
condition|(
name|this
operator|.
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
name|this
operator|.
name|flyingDown
condition|)
block|{
comment|// System.out.println("flying down");
if|if
condition|(
name|this
operator|.
name|running
condition|)
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
operator|-
literal|0.08F
expr_stmt|;
else|else
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
if|else if
condition|(
name|this
operator|.
name|jumping
condition|)
block|{
if|if
condition|(
name|this
operator|.
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
name|this
operator|.
name|flyingUp
condition|)
block|{
if|if
condition|(
name|this
operator|.
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
name|this
operator|.
name|flyingDown
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|running
condition|)
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
operator|-
literal|0.48F
expr_stmt|;
else|else
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
if|else if
condition|(
name|this
operator|.
name|jumping
condition|)
block|{
if|if
condition|(
name|this
operator|.
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
name|this
operator|.
name|flyingUp
condition|)
block|{
comment|// System.out.println("flying up");
if|if
condition|(
name|this
operator|.
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
name|this
operator|.
name|flyingDown
condition|)
block|{
comment|// System.out.println("flying down");
if|if
condition|(
name|this
operator|.
name|running
condition|)
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
operator|-
literal|0.08F
expr_stmt|;
else|else
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
if|else if
condition|(
name|this
operator|.
name|jumping
condition|)
block|{
if|if
condition|(
name|this
operator|.
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
name|this
operator|.
name|jumping
operator|&&
name|this
operator|.
name|mob
operator|.
name|isInLava
argument_list|()
condition|)
block|{
if|if
condition|(
name|this
operator|.
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
name|this
operator|.
name|jumping
operator|&&
name|this
operator|.
name|mob
operator|.
name|isInOrOnRope
argument_list|()
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|mob
operator|.
name|yd
operator|>
literal|0.02f
condition|)
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
name|boolean
name|var7
init|=
name|var2
operator|.
name|isInWater
argument_list|()
decl_stmt|;
name|boolean
name|isInLava
init|=
name|var2
operator|.
name|isInLava
argument_list|()
decl_stmt|;
name|boolean
name|isInOrOnRope
init|=
name|var2
operator|.
name|isInOrOnRope
argument_list|()
decl_stmt|;
if|if
condition|(
name|this
operator|.
name|jumping
condition|)
block|{
if|if
condition|(
name|var7
condition|)
block|{
comment|// if in water
if|if
condition|(
operator|!
name|running
condition|)
name|var2
operator|.
name|yd
operator|+=
literal|0.04F
expr_stmt|;
else|else
name|var2
operator|.
name|yd
operator|+=
literal|0.08F
expr_stmt|;
block|}
if|else if
condition|(
name|isInLava
condition|)
block|{
if|if
condition|(
operator|!
name|running
condition|)
name|var2
operator|.
name|yd
operator|+=
literal|0.04F
expr_stmt|;
else|else
name|var2
operator|.
name|yd
operator|+=
literal|0.08F
expr_stmt|;
block|}
if|else if
condition|(
name|isInOrOnRope
condition|)
block|{
if|if
condition|(
operator|!
name|running
condition|)
name|var2
operator|.
name|yd
operator|+=
literal|0.1F
expr_stmt|;
else|else
name|var2
operator|.
name|yd
operator|+=
literal|0.15F
expr_stmt|;
block|}
if|else if
condition|(
name|var2
operator|.
name|onGround
condition|)
block|{
comment|// if on the ground
name|this
operator|.
name|jumpFromGround
argument_list|()
expr_stmt|;
block|}
block|}
name|this
operator|.
name|xxa
operator|*=
literal|0.98F
expr_stmt|;
name|this
operator|.
name|yya
operator|*=
literal|0.98F
expr_stmt|;
name|this
operator|.
name|yRotA
operator|*=
literal|0.9F
expr_stmt|;
name|var2
operator|.
name|travel
argument_list|(
name|this
operator|.
name|xxa
argument_list|,
name|this
operator|.
name|yya
argument_list|)
expr_stmt|;
name|List
argument_list|<
name|Entity
argument_list|>
name|var11
decl_stmt|;
if|if
condition|(
operator|(
name|var11
operator|=
name|var1
operator|.
name|findEntities
argument_list|(
name|var2
argument_list|,
name|var2
operator|.
name|bb
operator|.
name|grow
argument_list|(
literal|0.2F
argument_list|,
literal|0.0F
argument_list|,
literal|0.2F
argument_list|)
argument_list|)
operator|)
operator|!=
literal|null
operator|&&
name|var11
operator|.
name|size
argument_list|()
operator|>
literal|0
condition|)
block|{
for|for
control|(
name|int
name|var8
init|=
literal|0
init|;
name|var8
operator|<
name|var11
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var8
control|)
block|{
name|Entity
name|var10
decl_stmt|;
if|if
condition|(
operator|(
name|var10
operator|=
operator|(
name|Entity
operator|)
name|var11
operator|.
name|get
argument_list|(
name|var8
argument_list|)
operator|)
operator|.
name|isPushable
argument_list|()
condition|)
block|{
name|var10
operator|.
name|push
argument_list|(
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
block|}
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
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.42F
expr_stmt|;
else|else
name|this
operator|.
name|mob
operator|.
name|yd
operator|=
literal|0.84F
expr_stmt|;
block|}
specifier|protected
name|void
name|update
parameter_list|()
block|{
if|if
condition|(
name|this
operator|.
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.07F
condition|)
block|{
name|this
operator|.
name|xxa
operator|=
operator|(
name|this
operator|.
name|random
operator|.
name|nextFloat
argument_list|()
operator|-
literal|0.5F
operator|)
operator|*
name|this
operator|.
name|runSpeed
expr_stmt|;
name|this
operator|.
name|yya
operator|=
name|this
operator|.
name|random
operator|.
name|nextFloat
argument_list|()
operator|*
name|this
operator|.
name|runSpeed
expr_stmt|;
block|}
name|this
operator|.
name|jumping
operator|=
name|this
operator|.
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.01F
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.04F
condition|)
block|{
name|this
operator|.
name|yRotA
operator|=
operator|(
name|this
operator|.
name|random
operator|.
name|nextFloat
argument_list|()
operator|-
literal|0.5F
operator|)
operator|*
literal|60.0F
expr_stmt|;
block|}
name|this
operator|.
name|mob
operator|.
name|yRot
operator|+=
name|this
operator|.
name|yRotA
expr_stmt|;
name|this
operator|.
name|mob
operator|.
name|xRot
operator|=
operator|(
name|float
operator|)
name|this
operator|.
name|defaultLookAngle
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|attackTarget
operator|!=
literal|null
condition|)
block|{
name|this
operator|.
name|yya
operator|=
name|this
operator|.
name|runSpeed
expr_stmt|;
name|this
operator|.
name|jumping
operator|=
name|this
operator|.
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.04F
expr_stmt|;
block|}
name|boolean
name|var1
init|=
name|this
operator|.
name|mob
operator|.
name|isInWater
argument_list|()
decl_stmt|;
name|boolean
name|isInLava
init|=
name|this
operator|.
name|mob
operator|.
name|isInLava
argument_list|()
decl_stmt|;
name|boolean
name|isInSpiderWeb
init|=
name|this
operator|.
name|mob
operator|.
name|isInOrOnRope
argument_list|()
decl_stmt|;
if|if
condition|(
name|var1
operator|||
name|isInLava
operator|||
name|isInSpiderWeb
condition|)
block|{
name|this
operator|.
name|jumping
operator|=
name|this
operator|.
name|random
operator|.
name|nextFloat
argument_list|()
operator|<
literal|0.8F
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|beforeRemove
parameter_list|()
block|{
block|}
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
name|super
operator|.
name|hurt
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
name|this
operator|.
name|noActionTime
operator|=
literal|0
expr_stmt|;
block|}
block|}
end_class

end_unit

