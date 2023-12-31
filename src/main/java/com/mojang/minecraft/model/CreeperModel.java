begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|model
package|;
end_package

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
specifier|final
class|class
name|CreeperModel
extends|extends
name|Model
block|{
specifier|private
name|ModelPart
name|head
init|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|)
decl_stmt|;
specifier|private
name|ModelPart
name|unused
decl_stmt|;
specifier|private
name|ModelPart
name|body
decl_stmt|;
specifier|private
name|ModelPart
name|leg1
decl_stmt|;
specifier|private
name|ModelPart
name|leg2
decl_stmt|;
specifier|private
name|ModelPart
name|leg3
decl_stmt|;
specifier|private
name|ModelPart
name|leg4
decl_stmt|;
specifier|public
name|CreeperModel
parameter_list|()
block|{
name|headOffset
operator|=
literal|0.25F
expr_stmt|;
name|head
operator|.
name|setBounds
argument_list|(
operator|-
literal|4F
argument_list|,
operator|-
literal|8F
argument_list|,
operator|-
literal|4F
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|head
operator|.
name|setPosition
argument_list|(
literal|0F
argument_list|,
literal|4F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|unused
operator|=
operator|new
name|ModelPart
argument_list|(
literal|32
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|unused
operator|.
name|setBounds
argument_list|(
operator|-
literal|4F
argument_list|,
operator|-
literal|8F
argument_list|,
operator|-
literal|4F
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
literal|0F
operator|+
literal|0.5F
argument_list|)
expr_stmt|;
name|body
operator|=
operator|new
name|ModelPart
argument_list|(
literal|16
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|body
operator|.
name|setBounds
argument_list|(
operator|-
literal|4F
argument_list|,
literal|0F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|8
argument_list|,
literal|12
argument_list|,
literal|4
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|body
operator|.
name|setPosition
argument_list|(
literal|0F
argument_list|,
literal|4F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|leg1
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|leg1
operator|.
name|setBounds
argument_list|(
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|4
argument_list|,
literal|6
argument_list|,
literal|4
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|leg1
operator|.
name|setPosition
argument_list|(
operator|-
literal|2F
argument_list|,
literal|16F
argument_list|,
literal|4F
argument_list|)
expr_stmt|;
name|leg2
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|leg2
operator|.
name|setBounds
argument_list|(
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|4
argument_list|,
literal|6
argument_list|,
literal|4
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|leg2
operator|.
name|setPosition
argument_list|(
literal|2F
argument_list|,
literal|16F
argument_list|,
literal|4F
argument_list|)
expr_stmt|;
name|leg3
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|leg3
operator|.
name|setBounds
argument_list|(
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|4
argument_list|,
literal|6
argument_list|,
literal|4
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|leg3
operator|.
name|setPosition
argument_list|(
operator|-
literal|2F
argument_list|,
literal|16F
argument_list|,
operator|-
literal|4F
argument_list|)
expr_stmt|;
name|leg4
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|leg4
operator|.
name|setBounds
argument_list|(
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|4
argument_list|,
literal|6
argument_list|,
literal|4
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|leg4
operator|.
name|setPosition
argument_list|(
literal|2F
argument_list|,
literal|16F
argument_list|,
operator|-
literal|4F
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|render
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|float
name|yawDegrees
parameter_list|,
name|float
name|pitchDegrees
parameter_list|,
name|float
name|scale
parameter_list|)
block|{
name|head
operator|.
name|yaw
operator|=
name|yawDegrees
operator|/
operator|(
name|float
operator|)
operator|(
literal|180D
operator|/
name|Math
operator|.
name|PI
operator|)
expr_stmt|;
name|head
operator|.
name|pitch
operator|=
name|pitchDegrees
operator|/
operator|(
name|float
operator|)
operator|(
literal|180D
operator|/
name|Math
operator|.
name|PI
operator|)
expr_stmt|;
name|leg1
operator|.
name|pitch
operator|=
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.6662F
argument_list|)
operator|*
literal|1.4F
operator|*
name|var2
expr_stmt|;
name|leg2
operator|.
name|pitch
operator|=
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.6662F
operator|+
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
operator|*
literal|1.4F
operator|*
name|var2
expr_stmt|;
name|leg3
operator|.
name|pitch
operator|=
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.6662F
operator|+
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
operator|*
literal|1.4F
operator|*
name|var2
expr_stmt|;
name|leg4
operator|.
name|pitch
operator|=
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.6662F
argument_list|)
operator|*
literal|1.4F
operator|*
name|var2
expr_stmt|;
name|head
operator|.
name|render
argument_list|(
name|scale
argument_list|)
expr_stmt|;
name|body
operator|.
name|render
argument_list|(
name|scale
argument_list|)
expr_stmt|;
name|leg1
operator|.
name|render
argument_list|(
name|scale
argument_list|)
expr_stmt|;
name|leg2
operator|.
name|render
argument_list|(
name|scale
argument_list|)
expr_stmt|;
name|leg3
operator|.
name|render
argument_list|(
name|scale
argument_list|)
expr_stmt|;
name|leg4
operator|.
name|render
argument_list|(
name|scale
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

