begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|gui
package|;
end_package

begin_class
specifier|public
specifier|final
class|class
name|GenerateLevelScreen
extends|extends
name|GuiScreen
block|{
specifier|private
name|GuiScreen
name|parent
decl_stmt|;
specifier|public
name|GenerateLevelScreen
parameter_list|(
name|GuiScreen
name|var1
parameter_list|)
block|{
name|parent
operator|=
name|var1
expr_stmt|;
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|void
name|onButtonClick
parameter_list|(
name|Button
name|var1
parameter_list|)
block|{
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|3
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
name|parent
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|minecraft
operator|.
name|generateLevel
argument_list|(
name|var1
operator|.
name|id
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|(
name|GuiScreen
operator|)
literal|null
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|grabMouse
argument_list|()
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|onOpen
parameter_list|()
block|{
name|buttons
operator|.
name|clear
argument_list|()
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|0
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
argument_list|,
literal|"Small"
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|1
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
operator|+
literal|24
argument_list|,
literal|"Normal"
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|2
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
operator|+
literal|48
argument_list|,
literal|"Huge"
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|3
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
operator|+
literal|120
argument_list|,
literal|"Cancel"
argument_list|)
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
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
name|drawFadingBox
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
name|width
argument_list|,
name|height
argument_list|,
literal|1610941696
argument_list|,
operator|-
literal|1607454624
argument_list|)
expr_stmt|;
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
literal|"Generate new level"
argument_list|,
name|width
operator|/
literal|2
argument_list|,
literal|40
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|super
operator|.
name|render
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

