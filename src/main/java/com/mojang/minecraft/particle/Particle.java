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
name|render
operator|.
name|ShapeRenderer
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
name|Particle
extends|extends
name|Entity
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|1L
decl_stmt|;
specifier|protected
name|int
name|tex
decl_stmt|;
specifier|protected
name|float
name|uo
decl_stmt|;
specifier|protected
name|float
name|vo
decl_stmt|;
specifier|protected
name|int
name|age
init|=
literal|0
decl_stmt|;
specifier|protected
name|int
name|lifetime
init|=
literal|0
decl_stmt|;
specifier|protected
name|float
name|size
decl_stmt|;
specifier|protected
name|float
name|gravity
decl_stmt|;
specifier|protected
name|float
name|rCol
decl_stmt|;
specifier|protected
name|float
name|gCol
decl_stmt|;
specifier|protected
name|float
name|bCol
decl_stmt|;
specifier|public
name|Particle
parameter_list|(
name|Level
name|level
parameter_list|,
name|float
name|x
parameter_list|,
name|float
name|y
parameter_list|,
name|float
name|z
parameter_list|,
name|float
name|destX
parameter_list|,
name|float
name|destY
parameter_list|,
name|float
name|destZ
parameter_list|)
block|{
name|super
argument_list|(
name|level
argument_list|)
expr_stmt|;
name|setSize
argument_list|(
literal|0.2F
argument_list|,
literal|0.2F
argument_list|)
expr_stmt|;
name|heightOffset
operator|=
name|bbHeight
operator|/
literal|2F
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
name|rCol
operator|=
name|gCol
operator|=
name|bCol
operator|=
literal|1F
expr_stmt|;
name|xd
operator|=
name|destX
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
literal|2D
operator|-
literal|1D
operator|)
operator|*
literal|0.4F
expr_stmt|;
name|yd
operator|=
name|destY
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
literal|2D
operator|-
literal|1D
operator|)
operator|*
literal|0.4F
expr_stmt|;
name|zd
operator|=
name|destZ
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
literal|2D
operator|-
literal|1D
operator|)
operator|*
literal|0.4F
expr_stmt|;
name|float
name|var8
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
name|Math
operator|.
name|random
argument_list|()
operator|+
literal|1D
operator|)
operator|*
literal|0.15F
decl_stmt|;
name|x
operator|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|xd
operator|*
name|xd
operator|+
name|yd
operator|*
name|yd
operator|+
name|zd
operator|*
name|zd
argument_list|)
expr_stmt|;
name|xd
operator|=
name|xd
operator|/
name|x
operator|*
name|var8
operator|*
literal|0.4F
expr_stmt|;
name|yd
operator|=
name|yd
operator|/
name|x
operator|*
name|var8
operator|*
literal|0.4F
operator|+
literal|0.1F
expr_stmt|;
name|zd
operator|=
name|zd
operator|/
name|x
operator|*
name|var8
operator|*
literal|0.4F
expr_stmt|;
name|uo
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|3F
expr_stmt|;
name|vo
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|3F
expr_stmt|;
name|size
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
literal|0.5D
operator|+
literal|0.5D
operator|)
expr_stmt|;
name|lifetime
operator|=
operator|(
name|int
operator|)
operator|(
literal|4D
operator|/
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|0.9D
operator|+
literal|0.1D
operator|)
operator|)
expr_stmt|;
name|age
operator|=
literal|0
expr_stmt|;
name|makeStepSound
operator|=
literal|false
expr_stmt|;
block|}
specifier|public
name|int
name|getParticleTexture
parameter_list|()
block|{
return|return
literal|0
return|;
block|}
specifier|public
name|void
name|render
parameter_list|(
name|ShapeRenderer
name|shapeRenderer
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
name|float
name|var8
decl_stmt|;
name|float
name|var9
init|=
operator|(
name|var8
operator|=
name|tex
operator|%
literal|16
operator|/
literal|16F
operator|)
operator|+
literal|0.0624375F
decl_stmt|;
name|float
name|var10
decl_stmt|;
name|float
name|var11
init|=
operator|(
name|var10
operator|=
name|tex
operator|/
literal|16
operator|/
literal|16F
operator|)
operator|+
literal|0.0624375F
decl_stmt|;
name|float
name|var12
init|=
literal|0.1F
operator|*
name|size
decl_stmt|;
name|float
name|var13
init|=
name|xo
operator|+
operator|(
name|x
operator|-
name|xo
operator|)
operator|*
name|var2
decl_stmt|;
name|float
name|var14
init|=
name|yo
operator|+
operator|(
name|y
operator|-
name|yo
operator|)
operator|*
name|var2
decl_stmt|;
name|float
name|var15
init|=
name|zo
operator|+
operator|(
name|z
operator|-
name|zo
operator|)
operator|*
name|var2
decl_stmt|;
name|ColorCache
name|var21
init|=
name|getBrightnessColor
argument_list|()
decl_stmt|;
name|shapeRenderer
operator|.
name|color
argument_list|(
name|rCol
operator|*
name|var21
operator|.
name|R
argument_list|,
name|gCol
operator|*
name|var21
operator|.
name|G
argument_list|,
name|bCol
operator|*
name|var21
operator|.
name|B
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|var13
operator|-
name|var3
operator|*
name|var12
operator|-
name|var6
operator|*
name|var12
argument_list|,
name|var14
operator|-
name|var4
operator|*
name|var12
argument_list|,
name|var15
operator|-
name|var5
operator|*
name|var12
operator|-
name|var7
operator|*
name|var12
argument_list|,
name|var8
argument_list|,
name|var11
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|var13
operator|-
name|var3
operator|*
name|var12
operator|+
name|var6
operator|*
name|var12
argument_list|,
name|var14
operator|+
name|var4
operator|*
name|var12
argument_list|,
name|var15
operator|-
name|var5
operator|*
name|var12
operator|+
name|var7
operator|*
name|var12
argument_list|,
name|var8
argument_list|,
name|var10
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|var13
operator|+
name|var3
operator|*
name|var12
operator|+
name|var6
operator|*
name|var12
argument_list|,
name|var14
operator|+
name|var4
operator|*
name|var12
argument_list|,
name|var15
operator|+
name|var5
operator|*
name|var12
operator|+
name|var7
operator|*
name|var12
argument_list|,
name|var9
argument_list|,
name|var10
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|var13
operator|+
name|var3
operator|*
name|var12
operator|-
name|var6
operator|*
name|var12
argument_list|,
name|var14
operator|-
name|var4
operator|*
name|var12
argument_list|,
name|var15
operator|+
name|var5
operator|*
name|var12
operator|-
name|var7
operator|*
name|var12
argument_list|,
name|var9
argument_list|,
name|var11
argument_list|)
expr_stmt|;
block|}
specifier|public
name|Particle
name|scale
parameter_list|(
name|float
name|scale
parameter_list|)
block|{
name|setSize
argument_list|(
literal|0.2F
operator|*
name|scale
argument_list|,
literal|0.2F
operator|*
name|scale
argument_list|)
expr_stmt|;
name|size
operator|*=
name|scale
expr_stmt|;
return|return
name|this
return|;
block|}
specifier|public
name|Particle
name|setPower
parameter_list|(
name|float
name|power
parameter_list|)
block|{
name|xd
operator|*=
name|power
expr_stmt|;
name|yd
operator|=
operator|(
name|yd
operator|-
literal|0.1F
operator|)
operator|*
name|power
operator|+
literal|0.1F
expr_stmt|;
name|zd
operator|*=
name|power
expr_stmt|;
return|return
name|this
return|;
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
if|if
condition|(
name|age
operator|++
operator|>=
name|lifetime
condition|)
block|{
name|remove
argument_list|()
expr_stmt|;
block|}
name|yd
operator|=
operator|(
name|float
operator|)
operator|(
name|yd
operator|-
literal|0.04D
operator|*
name|gravity
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
name|onGround
condition|)
block|{
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

