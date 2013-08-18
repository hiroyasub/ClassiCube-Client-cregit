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
name|util
operator|.
name|MathHelper
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
name|this
operator|.
name|heightOffset
operator|=
literal|1.62F
expr_stmt|;
name|this
operator|.
name|modelName
operator|=
literal|"creeper"
expr_stmt|;
name|this
operator|.
name|textureName
operator|=
literal|"/mob/creeper.png"
expr_stmt|;
name|this
operator|.
name|ai
operator|=
operator|new
name|Creeper$1
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|this
operator|.
name|ai
operator|.
name|defaultLookAngle
operator|=
literal|45
expr_stmt|;
name|this
operator|.
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
specifier|public
name|float
name|getBrightness
parameter_list|(
name|float
name|var1
parameter_list|)
block|{
name|float
name|var2
init|=
operator|(
name|float
operator|)
operator|(
literal|20
operator|-
name|this
operator|.
name|health
operator|)
operator|/
literal|20.0F
decl_stmt|;
return|return
operator|(
operator|(
name|MathHelper
operator|.
name|sin
argument_list|(
operator|(
name|float
operator|)
name|this
operator|.
name|tickCount
operator|+
name|var1
argument_list|)
operator|*
literal|0.5F
operator|+
literal|0.5F
operator|)
operator|*
name|var2
operator|*
literal|0.5F
operator|+
literal|0.25F
operator|+
name|var2
operator|*
literal|0.25F
operator|)
operator|*
name|super
operator|.
name|getBrightness
argument_list|(
name|var1
argument_list|)
return|;
block|}
block|}
end_class

end_unit

