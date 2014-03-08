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
name|SpiderModel
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
literal|32
argument_list|,
literal|4
argument_list|)
decl_stmt|;
specifier|private
name|ModelPart
name|neck
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
specifier|private
name|ModelPart
name|leg5
decl_stmt|;
specifier|private
name|ModelPart
name|leg6
decl_stmt|;
specifier|private
name|ModelPart
name|leg7
decl_stmt|;
specifier|private
name|ModelPart
name|leg8
decl_stmt|;
specifier|public
name|SpiderModel
parameter_list|()
block|{
name|head
operator|.
name|setBounds
argument_list|(
operator|-
literal|4.0F
argument_list|,
operator|-
literal|4.0F
argument_list|,
operator|-
literal|8.0F
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
literal|8
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|head
operator|.
name|setPosition
argument_list|(
literal|0.0F
argument_list|,
literal|16.0F
argument_list|,
operator|-
literal|3.0F
argument_list|)
expr_stmt|;
name|neck
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|neck
operator|.
name|setBounds
argument_list|(
operator|-
literal|3.0F
argument_list|,
operator|-
literal|3.0F
argument_list|,
operator|-
literal|3.0F
argument_list|,
literal|6
argument_list|,
literal|6
argument_list|,
literal|6
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|neck
operator|.
name|setPosition
argument_list|(
literal|0.0F
argument_list|,
literal|16.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|body
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|12
argument_list|)
expr_stmt|;
name|body
operator|.
name|setBounds
argument_list|(
operator|-
literal|5.0F
argument_list|,
operator|-
literal|4.0F
argument_list|,
operator|-
literal|6.0F
argument_list|,
literal|10
argument_list|,
literal|8
argument_list|,
literal|12
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|body
operator|.
name|setPosition
argument_list|(
literal|0.0F
argument_list|,
literal|16.0F
argument_list|,
literal|9.0F
argument_list|)
expr_stmt|;
name|leg1
operator|=
operator|new
name|ModelPart
argument_list|(
literal|18
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|leg1
operator|.
name|setBounds
argument_list|(
operator|-
literal|15.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|16
argument_list|,
literal|2
argument_list|,
literal|2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg1
operator|.
name|setPosition
argument_list|(
operator|-
literal|4.0F
argument_list|,
literal|16.0F
argument_list|,
literal|2.0F
argument_list|)
expr_stmt|;
name|leg2
operator|=
operator|new
name|ModelPart
argument_list|(
literal|18
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|leg2
operator|.
name|setBounds
argument_list|(
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|16
argument_list|,
literal|2
argument_list|,
literal|2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg2
operator|.
name|setPosition
argument_list|(
literal|4.0F
argument_list|,
literal|16.0F
argument_list|,
literal|2.0F
argument_list|)
expr_stmt|;
name|leg3
operator|=
operator|new
name|ModelPart
argument_list|(
literal|18
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|leg3
operator|.
name|setBounds
argument_list|(
operator|-
literal|15.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|16
argument_list|,
literal|2
argument_list|,
literal|2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg3
operator|.
name|setPosition
argument_list|(
operator|-
literal|4.0F
argument_list|,
literal|16.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|leg4
operator|=
operator|new
name|ModelPart
argument_list|(
literal|18
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|leg4
operator|.
name|setBounds
argument_list|(
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|16
argument_list|,
literal|2
argument_list|,
literal|2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg4
operator|.
name|setPosition
argument_list|(
literal|4.0F
argument_list|,
literal|16.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|leg5
operator|=
operator|new
name|ModelPart
argument_list|(
literal|18
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|leg5
operator|.
name|setBounds
argument_list|(
operator|-
literal|15.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|16
argument_list|,
literal|2
argument_list|,
literal|2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg5
operator|.
name|setPosition
argument_list|(
operator|-
literal|4.0F
argument_list|,
literal|16.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg6
operator|=
operator|new
name|ModelPart
argument_list|(
literal|18
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|leg6
operator|.
name|setBounds
argument_list|(
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|16
argument_list|,
literal|2
argument_list|,
literal|2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg6
operator|.
name|setPosition
argument_list|(
literal|4.0F
argument_list|,
literal|16.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg7
operator|=
operator|new
name|ModelPart
argument_list|(
literal|18
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|leg7
operator|.
name|setBounds
argument_list|(
operator|-
literal|15.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|16
argument_list|,
literal|2
argument_list|,
literal|2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg7
operator|.
name|setPosition
argument_list|(
operator|-
literal|4.0F
argument_list|,
literal|16.0F
argument_list|,
operator|-
literal|1.0F
argument_list|)
expr_stmt|;
name|leg8
operator|=
operator|new
name|ModelPart
argument_list|(
literal|18
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|leg8
operator|.
name|setBounds
argument_list|(
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|16
argument_list|,
literal|2
argument_list|,
literal|2
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|leg8
operator|.
name|setPosition
argument_list|(
literal|4.0F
argument_list|,
literal|16.0F
argument_list|,
operator|-
literal|1.0F
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
name|head
operator|.
name|yaw
operator|=
name|var4
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
name|var5
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
name|var4
operator|=
literal|0.7853982F
expr_stmt|;
name|leg1
operator|.
name|roll
operator|=
operator|-
name|var4
expr_stmt|;
name|leg2
operator|.
name|roll
operator|=
name|var4
expr_stmt|;
name|leg3
operator|.
name|roll
operator|=
operator|-
name|var4
operator|*
literal|0.74F
expr_stmt|;
name|leg4
operator|.
name|roll
operator|=
name|var4
operator|*
literal|0.74F
expr_stmt|;
name|leg5
operator|.
name|roll
operator|=
operator|-
name|var4
operator|*
literal|0.74F
expr_stmt|;
name|leg6
operator|.
name|roll
operator|=
name|var4
operator|*
literal|0.74F
expr_stmt|;
name|leg7
operator|.
name|roll
operator|=
operator|-
name|var4
expr_stmt|;
name|leg8
operator|.
name|roll
operator|=
name|var4
expr_stmt|;
name|var4
operator|=
literal|0.3926991F
expr_stmt|;
name|leg1
operator|.
name|yaw
operator|=
name|var4
operator|*
literal|2.0F
expr_stmt|;
name|leg2
operator|.
name|yaw
operator|=
operator|-
name|var4
operator|*
literal|2.0F
expr_stmt|;
name|leg3
operator|.
name|yaw
operator|=
name|var4
expr_stmt|;
name|leg4
operator|.
name|yaw
operator|=
operator|-
name|var4
expr_stmt|;
name|leg5
operator|.
name|yaw
operator|=
operator|-
name|var4
expr_stmt|;
name|leg6
operator|.
name|yaw
operator|=
name|var4
expr_stmt|;
name|leg7
operator|.
name|yaw
operator|=
operator|-
name|var4
operator|*
literal|2.0F
expr_stmt|;
name|leg8
operator|.
name|yaw
operator|=
name|var4
operator|*
literal|2.0F
expr_stmt|;
name|var4
operator|=
operator|-
operator|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.6662F
operator|*
literal|2.0F
argument_list|)
operator|*
literal|0.4F
operator|)
operator|*
name|var2
expr_stmt|;
name|var5
operator|=
operator|-
operator|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.6662F
operator|*
literal|2.0F
operator|+
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
operator|*
literal|0.4F
operator|)
operator|*
name|var2
expr_stmt|;
name|float
name|var7
init|=
operator|-
operator|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.6662F
operator|*
literal|2.0F
operator|+
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|PI
operator|/
literal|2D
operator|)
argument_list|)
operator|*
literal|0.4F
operator|)
operator|*
name|var2
decl_stmt|;
name|float
name|var8
init|=
operator|-
operator|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var1
operator|*
literal|0.6662F
operator|*
literal|2.0F
operator|+
literal|4.712389F
argument_list|)
operator|*
literal|0.4F
operator|)
operator|*
name|var2
decl_stmt|;
name|float
name|var9
init|=
name|Math
operator|.
name|abs
argument_list|(
name|MathHelper
operator|.
name|sin
argument_list|(
name|var1
operator|*
literal|0.6662F
argument_list|)
operator|*
literal|0.4F
argument_list|)
operator|*
name|var2
decl_stmt|;
name|float
name|var10
init|=
name|Math
operator|.
name|abs
argument_list|(
name|MathHelper
operator|.
name|sin
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
literal|0.4F
argument_list|)
operator|*
name|var2
decl_stmt|;
name|float
name|var11
init|=
name|Math
operator|.
name|abs
argument_list|(
name|MathHelper
operator|.
name|sin
argument_list|(
name|var1
operator|*
literal|0.6662F
operator|+
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|PI
operator|/
literal|2D
operator|)
argument_list|)
operator|*
literal|0.4F
argument_list|)
operator|*
name|var2
decl_stmt|;
name|var2
operator|=
name|Math
operator|.
name|abs
argument_list|(
name|MathHelper
operator|.
name|sin
argument_list|(
name|var1
operator|*
literal|0.6662F
operator|+
literal|4.712389F
argument_list|)
operator|*
literal|0.4F
argument_list|)
operator|*
name|var2
expr_stmt|;
name|leg1
operator|.
name|yaw
operator|+=
name|var4
expr_stmt|;
name|leg2
operator|.
name|yaw
operator|-=
name|var4
expr_stmt|;
name|leg3
operator|.
name|yaw
operator|+=
name|var5
expr_stmt|;
name|leg4
operator|.
name|yaw
operator|-=
name|var5
expr_stmt|;
name|leg5
operator|.
name|yaw
operator|+=
name|var7
expr_stmt|;
name|leg6
operator|.
name|yaw
operator|-=
name|var7
expr_stmt|;
name|leg7
operator|.
name|yaw
operator|+=
name|var8
expr_stmt|;
name|leg8
operator|.
name|yaw
operator|-=
name|var8
expr_stmt|;
name|leg1
operator|.
name|roll
operator|+=
name|var9
expr_stmt|;
name|leg2
operator|.
name|roll
operator|-=
name|var9
expr_stmt|;
name|leg3
operator|.
name|roll
operator|+=
name|var10
expr_stmt|;
name|leg4
operator|.
name|roll
operator|-=
name|var10
expr_stmt|;
name|leg5
operator|.
name|roll
operator|+=
name|var11
expr_stmt|;
name|leg6
operator|.
name|roll
operator|-=
name|var11
expr_stmt|;
name|leg7
operator|.
name|roll
operator|+=
name|var2
expr_stmt|;
name|leg8
operator|.
name|roll
operator|-=
name|var2
expr_stmt|;
name|head
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|neck
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
name|leg1
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|leg2
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|leg3
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|leg4
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|leg5
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|leg6
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|leg7
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
name|leg8
operator|.
name|render
argument_list|(
name|var6
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

