begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|sound
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
name|util
operator|.
name|MathHelper
import|;
end_import

begin_class
specifier|public
specifier|abstract
class|class
name|BaseSoundPos
implements|implements
name|SoundPos
block|{
specifier|private
name|Entity
name|listener
decl_stmt|;
specifier|public
name|BaseSoundPos
parameter_list|(
name|Entity
name|listener
parameter_list|)
block|{
name|this
operator|.
name|listener
operator|=
name|listener
expr_stmt|;
block|}
specifier|public
name|float
name|getDistanceSq
parameter_list|(
name|float
name|x
parameter_list|,
name|float
name|y
parameter_list|,
name|float
name|z
parameter_list|)
block|{
name|x
operator|-=
name|listener
operator|.
name|x
expr_stmt|;
name|y
operator|-=
name|listener
operator|.
name|y
expr_stmt|;
name|float
name|var4
init|=
name|z
operator|-
name|listener
operator|.
name|z
decl_stmt|;
name|var4
operator|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|x
operator|*
name|x
operator|+
name|y
operator|*
name|y
operator|+
name|var4
operator|*
name|var4
argument_list|)
expr_stmt|;
if|if
condition|(
operator|(
name|var4
operator|=
literal|1F
operator|-
name|var4
operator|/
literal|32F
operator|)
operator|<
literal|0F
condition|)
block|{
name|var4
operator|=
literal|0F
expr_stmt|;
block|}
return|return
name|var4
return|;
block|}
specifier|public
name|float
name|getRotationDiff
parameter_list|(
name|float
name|x
parameter_list|,
name|float
name|y
parameter_list|)
block|{
name|x
operator|-=
name|listener
operator|.
name|x
expr_stmt|;
name|y
operator|-=
name|listener
operator|.
name|z
expr_stmt|;
name|float
name|var3
init|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|x
operator|*
name|x
operator|+
name|y
operator|*
name|y
argument_list|)
decl_stmt|;
name|x
operator|/=
name|var3
expr_stmt|;
name|y
operator|/=
name|var3
expr_stmt|;
if|if
condition|(
operator|(
name|var3
operator|/=
literal|2F
operator|)
operator|>
literal|1F
condition|)
block|{
name|var3
operator|=
literal|1F
expr_stmt|;
block|}
name|float
name|var4
init|=
name|MathHelper
operator|.
name|cos
argument_list|(
operator|-
name|listener
operator|.
name|yRot
operator|*
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|PI
operator|/
literal|180D
operator|)
operator|+
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
decl_stmt|;
return|return
operator|(
name|MathHelper
operator|.
name|sin
argument_list|(
operator|-
name|listener
operator|.
name|yRot
operator|*
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|PI
operator|/
literal|180D
operator|)
operator|+
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
operator|*
name|y
operator|-
name|var4
operator|*
name|x
operator|)
operator|*
name|var3
return|;
block|}
block|}
end_class

end_unit

