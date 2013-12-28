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

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|GameSettings
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|OptionsScreen
extends|extends
name|GuiScreen
block|{
specifier|private
name|String
name|title
init|=
literal|"Options"
decl_stmt|;
specifier|private
name|GameSettings
name|settings
decl_stmt|;
specifier|public
name|OptionsScreen
parameter_list|(
name|GuiScreen
name|var1
parameter_list|,
name|GameSettings
name|var2
parameter_list|)
block|{
name|settings
operator|=
name|var2
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
name|active
condition|)
block|{
if|if
condition|(
name|var1
operator|.
name|id
operator|<
literal|100
condition|)
block|{
name|settings
operator|.
name|toggleSetting
argument_list|(
name|var1
operator|.
name|id
argument_list|,
literal|1
argument_list|)
expr_stmt|;
name|var1
operator|.
name|text
operator|=
name|settings
operator|.
name|getSetting
argument_list|(
name|var1
operator|.
name|id
argument_list|)
expr_stmt|;
block|}
name|buttons
operator|.
name|get
argument_list|(
literal|9
argument_list|)
operator|.
name|active
operator|=
name|minecraft
operator|.
name|settings
operator|.
name|smoothing
operator|>
literal|0
expr_stmt|;
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|100
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|AdvancedOptionsScreen
argument_list|(
name|this
argument_list|,
name|settings
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|200
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|ControlsScreen
argument_list|(
name|this
argument_list|,
name|settings
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|300
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|PauseScreen
argument_list|()
argument_list|)
expr_stmt|;
block|}
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
for|for
control|(
name|int
name|var1
init|=
literal|0
init|;
name|var1
operator|<
literal|10
condition|;
operator|++
name|var1
control|)
block|{
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|OptionButton
argument_list|(
name|var1
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|155
operator|+
name|var1
operator|%
literal|2
operator|*
literal|160
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|24
operator|*
operator|(
name|var1
operator|>>
literal|1
operator|)
argument_list|,
name|settings
operator|.
name|getSetting
argument_list|(
name|var1
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|100
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|90
operator|+
literal|32
argument_list|,
literal|"Advanced Options..."
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
literal|200
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|120
operator|+
literal|26
argument_list|,
literal|"Controls..."
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
literal|300
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|6
operator|+
literal|168
argument_list|,
literal|"Done"
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|get
argument_list|(
literal|9
argument_list|)
operator|.
name|active
operator|=
name|minecraft
operator|.
name|settings
operator|.
name|smoothing
operator|>
literal|0
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
name|title
argument_list|,
name|width
operator|/
literal|2
argument_list|,
literal|20
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

