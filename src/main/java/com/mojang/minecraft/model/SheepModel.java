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

begin_class
specifier|public
specifier|final
class|class
name|SheepModel
extends|extends
name|AnimalModel
block|{
specifier|public
name|SheepModel
parameter_list|()
block|{
name|super
argument_list|(
literal|12
argument_list|)
expr_stmt|;
name|this
operator|.
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
name|this
operator|.
name|head
operator|.
name|setBounds
argument_list|(
operator|-
literal|3.0F
argument_list|,
operator|-
literal|4.0F
argument_list|,
operator|-
literal|6.0F
argument_list|,
literal|6
argument_list|,
literal|6
argument_list|,
literal|8
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|this
operator|.
name|head
operator|.
name|setPosition
argument_list|(
literal|0.0F
argument_list|,
literal|6.0F
argument_list|,
operator|-
literal|8.0F
argument_list|)
expr_stmt|;
name|this
operator|.
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
name|this
operator|.
name|body
operator|.
name|setBounds
argument_list|(
operator|-
literal|4.0F
argument_list|,
operator|-
literal|10.0F
argument_list|,
operator|-
literal|7.0F
argument_list|,
literal|8
argument_list|,
literal|16
argument_list|,
literal|6
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|this
operator|.
name|body
operator|.
name|setPosition
argument_list|(
literal|0.0F
argument_list|,
literal|5.0F
argument_list|,
literal|2.0F
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

