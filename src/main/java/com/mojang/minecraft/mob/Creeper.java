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

begin_class
specifier|public
class|class
name|Creeper
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
name|Creeper
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
argument_list|)
expr_stmt|;
name|heightOffset
operator|=
literal|1.62F
expr_stmt|;
name|modelName
operator|=
literal|"creeper"
expr_stmt|;
name|textureName
operator|=
literal|"/mob/creeper.png"
expr_stmt|;
name|ai
operator|=
operator|new
name|Creeper$1
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|ai
operator|.
name|defaultLookAngle
operator|=
literal|45
expr_stmt|;
name|deathScore
operator|=
literal|200
expr_stmt|;
name|this
operator|.
name|setPos
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|float
name|getBrightness
parameter_list|()
block|{
return|return
literal|80
return|;
block|}
block|}
end_class

end_unit

