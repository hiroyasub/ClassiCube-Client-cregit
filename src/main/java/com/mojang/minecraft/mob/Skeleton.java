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
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|0L
decl_stmt|;
comment|// $FF: synthetic method
specifier|static
name|void
name|shootRandomArrow
parameter_list|(
name|Skeleton
name|var0
parameter_list|)
block|{
name|int
name|var1
init|=
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
literal|3.0D
operator|+
literal|4.0D
operator|)
decl_stmt|;
for|for
control|(
name|int
name|var2
init|=
literal|0
init|;
name|var2
operator|<
name|var1
condition|;
operator|++
name|var2
control|)
block|{
name|var0
operator|.
name|level
operator|.
name|addEntity
argument_list|(
operator|new
name|Arrow
argument_list|(
name|var0
operator|.
name|level
argument_list|,
name|var0
operator|.
name|level
operator|.
name|getPlayer
argument_list|()
argument_list|,
name|var0
operator|.
name|x
argument_list|,
name|var0
operator|.
name|y
operator|-
literal|0.2F
argument_list|,
name|var0
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
literal|360.0F
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
literal|60.0F
argument_list|,
literal|0.4F
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|Skeleton
parameter_list|(
name|Level
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
parameter_list|)
block|{
name|super
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
name|modelName
operator|=
literal|"skeleton"
expr_stmt|;
name|textureName
operator|=
literal|"/mob/skeleton.png"
expr_stmt|;
name|Skeleton$1
name|var5
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
name|var5
operator|.
name|runSpeed
operator|=
literal|0.3F
expr_stmt|;
name|var5
operator|.
name|damage
operator|=
literal|8
expr_stmt|;
name|ai
operator|=
name|var5
expr_stmt|;
block|}
specifier|public
name|void
name|shootArrow
parameter_list|(
name|Level
name|var1
parameter_list|)
block|{
name|var1
operator|.
name|addEntity
argument_list|(
operator|new
name|Arrow
argument_list|(
name|var1
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
literal|180.0F
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
literal|45.0D
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
literal|45.0D
operator|-
literal|10.0D
operator|)
argument_list|,
literal|1.0F
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

