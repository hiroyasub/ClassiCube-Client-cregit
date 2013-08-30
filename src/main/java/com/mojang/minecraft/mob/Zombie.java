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
name|ai
operator|.
name|BasicAttackAI
import|;
end_import

begin_class
specifier|public
class|class
name|Zombie
extends|extends
name|HumanoidMob
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
name|Zombie
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
name|this
operator|.
name|modelName
operator|=
literal|"zombie"
expr_stmt|;
name|this
operator|.
name|textureName
operator|=
literal|"/mob/zombie.png"
expr_stmt|;
name|this
operator|.
name|heightOffset
operator|=
literal|1.62F
expr_stmt|;
name|BasicAttackAI
name|var5
init|=
operator|new
name|BasicAttackAI
argument_list|()
decl_stmt|;
name|this
operator|.
name|deathScore
operator|=
literal|80
expr_stmt|;
name|var5
operator|.
name|defaultLookAngle
operator|=
literal|30
expr_stmt|;
comment|// var5.runSpeed = 1.0F;
name|this
operator|.
name|ai
operator|=
name|var5
expr_stmt|;
block|}
block|}
end_class

end_unit

