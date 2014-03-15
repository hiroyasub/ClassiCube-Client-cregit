begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|particle
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
name|render
operator|.
name|ShapeRenderer
import|;
end_import

begin_class
specifier|public
class|class
name|WaterDropParticle
extends|extends
name|Particle
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|1L
decl_stmt|;
specifier|public
name|WaterDropParticle
parameter_list|(
name|Level
name|level
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
name|level
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|xd
operator|*=
literal|0.3F
expr_stmt|;
name|yd
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|0.2F
operator|+
literal|0.1F
expr_stmt|;
name|zd
operator|*=
literal|0.3F
expr_stmt|;
name|rCol
operator|=
literal|1F
expr_stmt|;
name|gCol
operator|=
literal|1F
expr_stmt|;
name|bCol
operator|=
literal|1F
expr_stmt|;
name|tex
operator|=
literal|16
expr_stmt|;
name|setSize
argument_list|(
literal|0.01F
argument_list|,
literal|0.01F
argument_list|)
expr_stmt|;
name|lifetime
operator|=
operator|(
name|int
operator|)
operator|(
literal|8D
operator|/
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|0.8D
operator|+
literal|0.2D
operator|)
operator|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|tick
parameter_list|()
block|{
name|xo
operator|=
name|x
expr_stmt|;
name|yo
operator|=
name|y
expr_stmt|;
name|zo
operator|=
name|z
expr_stmt|;
name|yd
operator|=
operator|(
name|float
operator|)
operator|(
name|yd
operator|-
literal|0.06D
operator|)
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
literal|0.98F
expr_stmt|;
name|yd
operator|*=
literal|0.98F
expr_stmt|;
name|zd
operator|*=
literal|0.98F
expr_stmt|;
if|if
condition|(
name|lifetime
operator|--
operator|<=
literal|0
condition|)
block|{
name|remove
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|onGround
condition|)
block|{
if|if
condition|(
name|Math
operator|.
name|random
argument_list|()
operator|<
literal|0.5D
condition|)
block|{
name|remove
argument_list|()
expr_stmt|;
block|}
name|xd
operator|*=
literal|0.7F
expr_stmt|;
name|zd
operator|*=
literal|0.7F
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

