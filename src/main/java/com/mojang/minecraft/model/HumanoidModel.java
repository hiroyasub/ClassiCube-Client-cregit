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
name|HumanoidModel
extends|extends
name|Model
block|{
specifier|public
name|ModelPart
name|head
decl_stmt|;
specifier|public
name|ModelPart
name|headwear
decl_stmt|;
specifier|public
name|ModelPart
name|body
decl_stmt|;
specifier|public
name|ModelPart
name|rightArm
decl_stmt|;
specifier|public
name|ModelPart
name|leftArm
decl_stmt|;
specifier|public
name|ModelPart
name|rightLeg
decl_stmt|;
specifier|public
name|ModelPart
name|leftLeg
decl_stmt|;
specifier|public
name|HumanoidModel
parameter_list|()
block|{
name|this
argument_list|(
literal|0.0F
argument_list|)
expr_stmt|;
block|}
specifier|public
name|HumanoidModel
parameter_list|(
name|float
name|var1
parameter_list|)
block|{
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
literal|4.0F
argument_list|,
operator|-
literal|8.0F
argument_list|,
operator|-
literal|4.0F
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
name|var1
argument_list|)
expr_stmt|;
name|headwear
operator|=
operator|new
name|ModelPart
argument_list|(
literal|32
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|headwear
operator|.
name|setBounds
argument_list|(
operator|-
literal|4.0F
argument_list|,
operator|-
literal|8.0F
argument_list|,
operator|-
literal|4.0F
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
name|var1
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
literal|4.0F
argument_list|,
literal|0.0F
argument_list|,
operator|-
literal|2.0F
argument_list|,
literal|8
argument_list|,
literal|12
argument_list|,
literal|4
argument_list|,
name|var1
argument_list|)
expr_stmt|;
name|rightArm
operator|=
operator|new
name|ModelPart
argument_list|(
literal|40
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|rightArm
operator|.
name|setBounds
argument_list|(
operator|-
literal|3.0F
argument_list|,
operator|-
literal|2.0F
argument_list|,
operator|-
literal|2.0F
argument_list|,
literal|4
argument_list|,
literal|12
argument_list|,
literal|4
argument_list|,
name|var1
argument_list|)
expr_stmt|;
name|rightArm
operator|.
name|setPosition
argument_list|(
operator|-
literal|5.0F
argument_list|,
literal|2.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leftArm
operator|=
operator|new
name|ModelPart
argument_list|(
literal|40
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|leftArm
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|leftArm
operator|.
name|setBounds
argument_list|(
operator|-
literal|1.0F
argument_list|,
operator|-
literal|2.0F
argument_list|,
operator|-
literal|2.0F
argument_list|,
literal|4
argument_list|,
literal|12
argument_list|,
literal|4
argument_list|,
name|var1
argument_list|)
expr_stmt|;
name|leftArm
operator|.
name|setPosition
argument_list|(
literal|5.0F
argument_list|,
literal|2.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|rightLeg
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|rightLeg
operator|.
name|setBounds
argument_list|(
operator|-
literal|2.0F
argument_list|,
literal|0.0F
argument_list|,
operator|-
literal|2.0F
argument_list|,
literal|4
argument_list|,
literal|12
argument_list|,
literal|4
argument_list|,
name|var1
argument_list|)
expr_stmt|;
name|rightLeg
operator|.
name|setPosition
argument_list|(
operator|-
literal|2.0F
argument_list|,
literal|12.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leftLeg
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|leftLeg
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|leftLeg
operator|.
name|setBounds
argument_list|(
operator|-
literal|2.0F
argument_list|,
literal|0.0F
argument_list|,
operator|-
literal|2.0F
argument_list|,
literal|4
argument_list|,
literal|12
argument_list|,
literal|4
argument_list|,
name|var1
argument_list|)
expr_stmt|;
name|leftLeg
operator|.
name|setPosition
argument_list|(
literal|2.0F
argument_list|,
literal|12.0F
argument_list|,
literal|0.0F
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
name|var4
parameter_list|,
name|float
name|var5
parameter_list|,
name|float
name|var6
parameter_list|)
block|{
name|setRotationAngles
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|)
expr_stmt|;
name|head
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|body
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|rightArm
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|leftArm
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|rightLeg
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|leftLeg
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|setRotationAngles
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
name|var4
parameter_list|,
name|float
name|var5
parameter_list|,
name|float
name|var6
parameter_list|)
block|{
name|head
operator|.
name|yaw
operator|=
name|var4
operator|/
literal|57.295776F
expr_stmt|;
name|head
operator|.
name|pitch
operator|=
name|var5
operator|/
literal|57.295776F
expr_stmt|;
name|rightArm
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
literal|3.1415927F
argument_list|)
operator|*
literal|2.0F
operator|*
name|var2
expr_stmt|;
name|rightArm
operator|.
name|roll
operator|=
operator|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.2312F
argument_list|)
operator|+
literal|1.0F
operator|)
operator|*
name|var2
expr_stmt|;
name|leftArm
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
literal|2.0F
operator|*
name|var2
expr_stmt|;
name|leftArm
operator|.
name|roll
operator|=
operator|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.2812F
argument_list|)
operator|-
literal|1.0F
operator|)
operator|*
name|var2
expr_stmt|;
name|rightLeg
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
name|leftLeg
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
literal|3.1415927F
argument_list|)
operator|*
literal|1.4F
operator|*
name|var2
expr_stmt|;
name|rightArm
operator|.
name|roll
operator|+=
name|MathHelper
operator|.
name|cos
argument_list|(
name|var3
operator|*
literal|0.09F
argument_list|)
operator|*
literal|0.05F
operator|+
literal|0.05F
expr_stmt|;
name|leftArm
operator|.
name|roll
operator|-=
name|MathHelper
operator|.
name|cos
argument_list|(
name|var3
operator|*
literal|0.09F
argument_list|)
operator|*
literal|0.05F
operator|+
literal|0.05F
expr_stmt|;
name|rightArm
operator|.
name|pitch
operator|+=
name|MathHelper
operator|.
name|sin
argument_list|(
name|var3
operator|*
literal|0.067F
argument_list|)
operator|*
literal|0.05F
expr_stmt|;
name|leftArm
operator|.
name|pitch
operator|-=
name|MathHelper
operator|.
name|sin
argument_list|(
name|var3
operator|*
literal|0.067F
argument_list|)
operator|*
literal|0.05F
expr_stmt|;
block|}
block|}
end_class

end_unit

