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

begin_comment
comment|/*  * Based on PrinterModel.java from Minechem v4.x. Minechem v4.x is licensed under Creative Commons  * Attribution-ShareAlike 3.0 Unported.   * The details of the licence can be found at: http://creativecommons.org/licenses/by-sa/3.0/us/  */
end_comment

begin_class
specifier|public
class|class
name|PrinterModel
extends|extends
name|Model
block|{
comment|//fields
name|ModelPart
name|Base
decl_stmt|;
name|ModelPart
name|RightWall
decl_stmt|;
name|ModelPart
name|LeftWall
decl_stmt|;
name|ModelPart
name|MiddleComp
decl_stmt|;
name|ModelPart
name|WholeBase
decl_stmt|;
name|ModelPart
name|Rack
decl_stmt|;
name|ModelPart
name|Back
decl_stmt|;
name|ModelPart
name|TopLeftNobble
decl_stmt|;
name|ModelPart
name|TopRightNobble
decl_stmt|;
name|ModelPart
name|TopMiddleNobble
decl_stmt|;
name|ModelPart
name|LeftLine
decl_stmt|;
name|ModelPart
name|RightLine
decl_stmt|;
specifier|public
name|PrinterModel
parameter_list|()
block|{
comment|// textureWidth = 128;
comment|// textureHeight = 128;
name|Base
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|Base
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|16
argument_list|,
literal|1
argument_list|,
literal|16
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|Base
operator|.
name|setPosition
argument_list|(
operator|-
literal|8F
argument_list|,
literal|23F
argument_list|,
operator|-
literal|8F
argument_list|)
expr_stmt|;
comment|// Base.setTextureSize(128, 128);
name|Base
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|Base
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|RightWall
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|19
argument_list|)
expr_stmt|;
name|RightWall
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|1
argument_list|,
literal|5
argument_list|,
literal|14
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|RightWall
operator|.
name|setPosition
argument_list|(
literal|6F
argument_list|,
literal|18F
argument_list|,
operator|-
literal|7F
argument_list|)
expr_stmt|;
comment|//RightWall.setTextureSize(128, 128);
name|RightWall
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|RightWall
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|LeftWall
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|19
argument_list|)
expr_stmt|;
name|LeftWall
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|1
argument_list|,
literal|5
argument_list|,
literal|14
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|LeftWall
operator|.
name|setPosition
argument_list|(
operator|-
literal|7F
argument_list|,
literal|18F
argument_list|,
operator|-
literal|7F
argument_list|)
expr_stmt|;
comment|//LeftWall.setTextureSize(128, 128);
name|LeftWall
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|LeftWall
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|MiddleComp
operator|=
operator|new
name|ModelPart
argument_list|(
literal|31
argument_list|,
literal|19
argument_list|)
expr_stmt|;
name|MiddleComp
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
literal|4
argument_list|,
literal|11
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|MiddleComp
operator|.
name|setPosition
argument_list|(
operator|-
literal|4F
argument_list|,
literal|17.5F
argument_list|,
operator|-
literal|7F
argument_list|)
expr_stmt|;
comment|// MiddleComp.setTextureSize(128, 128);
name|MiddleComp
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|MiddleComp
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|WholeBase
operator|=
operator|new
name|ModelPart
argument_list|(
literal|65
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|WholeBase
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|12
argument_list|,
literal|5
argument_list|,
literal|11
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|WholeBase
operator|.
name|setPosition
argument_list|(
operator|-
literal|6F
argument_list|,
literal|17F
argument_list|,
operator|-
literal|6.5F
argument_list|)
expr_stmt|;
comment|//WholeBase.setTextureSize(128, 128);
name|WholeBase
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|WholeBase
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|Rack
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|40
argument_list|)
expr_stmt|;
name|Rack
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
operator|-
literal|1F
argument_list|,
literal|0F
argument_list|,
literal|12
argument_list|,
literal|8
argument_list|,
literal|1
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|Rack
operator|.
name|setPosition
argument_list|(
operator|-
literal|6F
argument_list|,
literal|13F
argument_list|,
literal|7F
argument_list|)
expr_stmt|;
comment|// Rack.setTextureSize(128, 128);
name|Rack
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|Rack
argument_list|,
operator|-
literal|0.3346075F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|Back
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|50
argument_list|)
expr_stmt|;
name|Back
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|12
argument_list|,
literal|4
argument_list|,
literal|1
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|Back
operator|.
name|setPosition
argument_list|(
operator|-
literal|6F
argument_list|,
literal|19F
argument_list|,
literal|5.8F
argument_list|)
expr_stmt|;
comment|//  Back.setTextureSize(128, 128);
name|Back
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|Back
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|TopLeftNobble
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|58
argument_list|)
expr_stmt|;
name|TopLeftNobble
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|3
argument_list|,
literal|1
argument_list|,
literal|3
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|TopLeftNobble
operator|.
name|setPosition
argument_list|(
operator|-
literal|5F
argument_list|,
literal|16F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
comment|//TopLeftNobble.setTextureSize(128, 128);
name|TopLeftNobble
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|TopLeftNobble
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|TopRightNobble
operator|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|58
argument_list|)
expr_stmt|;
name|TopRightNobble
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|3
argument_list|,
literal|1
argument_list|,
literal|3
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|TopRightNobble
operator|.
name|setPosition
argument_list|(
literal|2F
argument_list|,
literal|16F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
comment|// TopRightNobble.setTextureSize(128, 128);
name|TopRightNobble
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|TopRightNobble
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|TopMiddleNobble
operator|=
operator|new
name|ModelPart
argument_list|(
literal|13
argument_list|,
literal|58
argument_list|)
expr_stmt|;
name|TopMiddleNobble
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|4
argument_list|,
literal|1
argument_list|,
literal|6
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|TopMiddleNobble
operator|.
name|setPosition
argument_list|(
operator|-
literal|2F
argument_list|,
literal|16.5F
argument_list|,
operator|-
literal|3F
argument_list|)
expr_stmt|;
comment|// TopMiddleNobble.setTextureSize(128, 128);
name|TopMiddleNobble
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|TopMiddleNobble
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|LeftLine
operator|=
operator|new
name|ModelPart
argument_list|(
literal|28
argument_list|,
literal|42
argument_list|)
expr_stmt|;
name|LeftLine
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|1
argument_list|,
literal|1
argument_list|,
literal|9
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|LeftLine
operator|.
name|setPosition
argument_list|(
operator|-
literal|4F
argument_list|,
literal|16.5F
argument_list|,
operator|-
literal|7F
argument_list|)
expr_stmt|;
comment|// LeftLine.setTextureSize(128, 128);
name|LeftLine
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|LeftLine
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|RightLine
operator|=
operator|new
name|ModelPart
argument_list|(
literal|28
argument_list|,
literal|42
argument_list|)
expr_stmt|;
name|RightLine
operator|.
name|setBounds
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|1
argument_list|,
literal|1
argument_list|,
literal|9
argument_list|,
literal|0.0f
argument_list|)
expr_stmt|;
name|RightLine
operator|.
name|setPosition
argument_list|(
literal|3F
argument_list|,
literal|16.5F
argument_list|,
operator|-
literal|7F
argument_list|)
expr_stmt|;
comment|// RightLine.setTextureSize(128, 128);
name|RightLine
operator|.
name|mirror
operator|=
literal|true
expr_stmt|;
name|setRotation
argument_list|(
name|RightLine
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
block|}
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
name|f5
parameter_list|)
block|{
name|Base
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|RightWall
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|LeftWall
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|MiddleComp
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|WholeBase
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|Rack
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|Back
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|TopLeftNobble
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|TopRightNobble
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|TopMiddleNobble
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|LeftLine
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
name|RightLine
operator|.
name|render
argument_list|(
name|f5
argument_list|)
expr_stmt|;
block|}
specifier|private
name|void
name|setRotation
parameter_list|(
name|ModelPart
name|model
parameter_list|,
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
name|model
operator|.
name|pitch
operator|=
name|x
expr_stmt|;
name|model
operator|.
name|yaw
operator|=
name|y
expr_stmt|;
name|model
operator|.
name|roll
operator|=
name|z
expr_stmt|;
block|}
block|}
end_class

end_unit

