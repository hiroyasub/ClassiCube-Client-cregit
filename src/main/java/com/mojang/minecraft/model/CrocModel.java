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
class|class
name|CrocModel
extends|extends
name|Model
block|{
comment|// fields
name|ModelPart
name|tail
decl_stmt|;
name|ModelPart
name|head
decl_stmt|;
name|ModelPart
name|body
decl_stmt|;
name|ModelPart
name|leg1
decl_stmt|;
name|ModelPart
name|leg2
decl_stmt|;
name|ModelPart
name|leg4
decl_stmt|;
name|ModelPart
name|leg3
decl_stmt|;
specifier|public
name|CrocModel
parameter_list|()
block|{
name|headOffset
operator|=
literal|0.937F
expr_stmt|;
name|tail
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|tail
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|8
argument_list|,
literal|2
argument_list|,
literal|17
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|tail
operator|.
name|setPosition
argument_list|(
operator|-
literal|4F
argument_list|,
literal|11F
argument_list|,
literal|5F
argument_list|)
expr_stmt|;
name|tail
operator|.
name|pitch
operator|=
literal|0F
expr_stmt|;
name|tail
operator|.
name|yaw
operator|=
literal|0F
expr_stmt|;
name|tail
operator|.
name|roll
operator|=
literal|0F
expr_stmt|;
name|tail
operator|.
name|mirror
operator|=
literal|false
expr_stmt|;
name|head
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|head
operator|.
name|setBounds
argument_list|(
operator|-
literal|4F
argument_list|,
operator|-
literal|4F
argument_list|,
operator|-
literal|8F
argument_list|,
literal|8
argument_list|,
literal|5
argument_list|,
literal|11
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
literal|15F
argument_list|,
operator|-
literal|9F
argument_list|)
expr_stmt|;
name|head
operator|.
name|pitch
operator|=
literal|0F
expr_stmt|;
name|head
operator|.
name|yaw
operator|=
literal|0F
expr_stmt|;
name|head
operator|.
name|roll
operator|=
literal|0F
expr_stmt|;
name|head
operator|.
name|mirror
operator|=
literal|false
expr_stmt|;
name|body
operator|=
operator|new
name|ModelPart
argument_list|(
literal|28
argument_list|,
literal|8
argument_list|)
expr_stmt|;
name|body
operator|.
name|setBounds
argument_list|(
operator|-
literal|5F
argument_list|,
operator|-
literal|10F
argument_list|,
operator|-
literal|7F
argument_list|,
literal|10
argument_list|,
literal|16
argument_list|,
literal|8
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
literal|11F
argument_list|,
literal|2F
argument_list|)
expr_stmt|;
name|body
operator|.
name|pitch
operator|=
literal|1.5708F
expr_stmt|;
name|body
operator|.
name|yaw
operator|=
literal|0F
expr_stmt|;
name|body
operator|.
name|roll
operator|=
literal|0F
expr_stmt|;
name|body
operator|.
name|mirror
operator|=
literal|false
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
literal|3F
argument_list|,
literal|18F
argument_list|,
literal|7F
argument_list|)
expr_stmt|;
name|leg1
operator|.
name|pitch
operator|=
literal|0F
expr_stmt|;
name|leg1
operator|.
name|yaw
operator|=
literal|0F
expr_stmt|;
name|leg1
operator|.
name|roll
operator|=
literal|0F
expr_stmt|;
name|leg1
operator|.
name|mirror
operator|=
literal|false
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
literal|3F
argument_list|,
literal|18F
argument_list|,
literal|7F
argument_list|)
expr_stmt|;
name|leg2
operator|.
name|pitch
operator|=
literal|0F
expr_stmt|;
name|leg2
operator|.
name|yaw
operator|=
literal|0F
expr_stmt|;
name|leg2
operator|.
name|roll
operator|=
literal|0F
expr_stmt|;
name|leg2
operator|.
name|mirror
operator|=
literal|false
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
literal|3F
argument_list|,
literal|18F
argument_list|,
operator|-
literal|5F
argument_list|)
expr_stmt|;
name|leg4
operator|.
name|pitch
operator|=
literal|0F
expr_stmt|;
name|leg4
operator|.
name|yaw
operator|=
literal|0F
expr_stmt|;
name|leg4
operator|.
name|roll
operator|=
literal|0F
expr_stmt|;
name|leg4
operator|.
name|mirror
operator|=
literal|false
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
literal|18F
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
literal|3F
argument_list|,
literal|0F
argument_list|,
operator|-
literal|5F
argument_list|)
expr_stmt|;
name|leg3
operator|.
name|pitch
operator|=
literal|0F
expr_stmt|;
name|leg3
operator|.
name|yaw
operator|=
literal|0F
expr_stmt|;
name|leg3
operator|.
name|roll
operator|=
literal|0F
expr_stmt|;
name|leg3
operator|.
name|mirror
operator|=
literal|false
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|render
parameter_list|(
name|float
name|f
parameter_list|,
name|float
name|f1
parameter_list|,
name|float
name|f2
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
name|super
operator|.
name|render
argument_list|(
name|f
argument_list|,
name|f1
argument_list|,
name|f2
argument_list|,
name|yawDegrees
argument_list|,
name|pitchDegrees
argument_list|,
name|scale
argument_list|)
expr_stmt|;
name|setRotationAngles
argument_list|(
name|f
argument_list|,
name|f1
argument_list|,
name|f2
argument_list|,
name|yawDegrees
argument_list|,
name|pitchDegrees
argument_list|,
name|scale
argument_list|)
expr_stmt|;
name|tail
operator|.
name|render
argument_list|(
name|scale
argument_list|)
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
name|leg4
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
block|}
specifier|public
name|void
name|setRotationAngles
parameter_list|(
name|float
name|f
parameter_list|,
name|float
name|f1
parameter_list|,
name|float
name|f2
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
comment|// super.setRotationAngles(f, f1, f2, f3, f4, f5);
name|tail
operator|.
name|yaw
operator|=
name|MathHelper
operator|.
name|cos
argument_list|(
name|f
operator|/
operator|(
literal|0.9595538255F
operator|)
operator|*
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|PI
operator|/
literal|90
operator|)
operator|*
name|f1
operator|+
literal|0
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

