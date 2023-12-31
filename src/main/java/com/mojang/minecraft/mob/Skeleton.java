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
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|item
operator|.
name|Arrow
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

begin_class
specifier|public
class|class
name|Skeleton
extends|extends
name|Zombie
block|{
specifier|public
name|Skeleton
parameter_list|(
name|Level
name|level
parameter_list|,
name|float
name|posX
parameter_list|,
name|float
name|posY
parameter_list|,
name|float
name|posZ
parameter_list|)
block|{
name|super
argument_list|(
name|level
argument_list|,
name|posX
argument_list|,
name|posY
argument_list|,
name|posZ
argument_list|)
expr_stmt|;
name|modelName
operator|=
literal|"skeleton"
expr_stmt|;
name|textureName
operator|=
literal|"/mob/skeleton.png"
expr_stmt|;
name|Skeleton$1
name|skeletonAI
init|=
operator|new
name|Skeleton$1
argument_list|(
name|this
argument_list|)
decl_stmt|;
name|deathScore
operator|=
literal|120
expr_stmt|;
name|skeletonAI
operator|.
name|runSpeed
operator|=
literal|0.3F
expr_stmt|;
name|skeletonAI
operator|.
name|damage
operator|=
literal|8
expr_stmt|;
name|ai
operator|=
name|skeletonAI
expr_stmt|;
block|}
comment|// $FF: synthetic method
specifier|static
name|void
name|shootRandomArrow
parameter_list|(
name|Skeleton
name|skeleton
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
operator|(
name|int
operator|)
operator|(
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|+
name|Math
operator|.
name|random
argument_list|()
operator|)
operator|*
literal|3D
operator|+
literal|4D
operator|)
condition|;
operator|++
name|i
control|)
block|{
name|skeleton
operator|.
name|level
operator|.
name|addEntity
argument_list|(
operator|new
name|Arrow
argument_list|(
name|skeleton
operator|.
name|level
argument_list|,
name|skeleton
operator|.
name|level
operator|.
name|getPlayer
argument_list|()
argument_list|,
name|skeleton
operator|.
name|x
argument_list|,
name|skeleton
operator|.
name|y
operator|-
literal|0.2F
argument_list|,
name|skeleton
operator|.
name|z
argument_list|,
operator|(
name|float
operator|)
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|360F
argument_list|,
operator|-
operator|(
operator|(
name|float
operator|)
name|Math
operator|.
name|random
argument_list|()
operator|)
operator|*
literal|60F
argument_list|,
literal|0.4F
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|shootArrow
parameter_list|(
name|Level
name|level
parameter_list|)
block|{
name|level
operator|.
name|addEntity
argument_list|(
operator|new
name|Arrow
argument_list|(
name|level
argument_list|,
name|this
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|yRot
operator|+
literal|180F
operator|+
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|45D
operator|-
literal|22.5D
operator|)
argument_list|,
name|xRot
operator|-
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|45D
operator|-
literal|10D
operator|)
argument_list|,
literal|1F
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

