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
name|item
operator|.
name|Item
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

begin_class
specifier|public
class|class
name|Pig
extends|extends
name|QuadrupedMob
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
name|Pig
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
name|heightOffset
operator|=
literal|1.72F
expr_stmt|;
name|modelName
operator|=
literal|"pig"
expr_stmt|;
name|textureName
operator|=
literal|"/mob/pig.png"
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
if|if
condition|(
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
literal|10
argument_list|)
expr_stmt|;
block|}
name|int
name|var2
init|=
operator|(
name|int
operator|)
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
operator|+
literal|1.0D
operator|)
decl_stmt|;
for|for
control|(
name|int
name|var3
init|=
literal|0
init|;
name|var3
operator|<
name|var2
condition|;
operator|++
name|var3
control|)
block|{
name|level
operator|.
name|addEntity
argument_list|(
operator|new
name|Item
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|Block
operator|.
name|BROWN_MUSHROOM
operator|.
name|id
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|super
operator|.
name|die
argument_list|(
name|var1
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

