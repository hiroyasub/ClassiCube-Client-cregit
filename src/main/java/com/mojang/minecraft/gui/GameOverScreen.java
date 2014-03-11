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
name|org
operator|.
name|lwjgl
operator|.
name|opengl
operator|.
name|GL11
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|GameOverScreen
extends|extends
name|GuiScreen
block|{
annotation|@
name|Override
specifier|protected
specifier|final
name|void
name|onButtonClick
parameter_list|(
name|Button
name|button
parameter_list|)
block|{
if|if
condition|(
name|button
operator|.
name|id
operator|==
literal|0
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|OptionsScreen
argument_list|(
name|minecraft
operator|.
name|settings
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|button
operator|.
name|id
operator|==
literal|1
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|GenerateLevelScreen
argument_list|(
name|this
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|minecraft
operator|.
name|session
operator|!=
literal|null
operator|&&
name|button
operator|.
name|id
operator|==
literal|2
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|LoadLevelScreen
argument_list|(
name|this
argument_list|)
argument_list|)
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
literal|72
argument_list|,
literal|"Generate new level..."
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
literal|96
argument_list|,
literal|"Load level.."
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|minecraft
operator|.
name|session
operator|==
literal|null
condition|)
block|{
name|buttons
operator|.
name|get
argument_list|(
literal|1
argument_list|)
operator|.
name|active
operator|=
literal|false
expr_stmt|;
block|}
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
literal|1615855616
argument_list|,
operator|-
literal|1602211792
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|2F
argument_list|,
literal|2F
argument_list|,
literal|2F
argument_list|)
expr_stmt|;
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
literal|"Game over!"
argument_list|,
name|width
operator|/
literal|2
operator|/
literal|2
argument_list|,
literal|30
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
literal|"Score:&e"
operator|+
name|minecraft
operator|.
name|player
operator|.
name|getScore
argument_list|()
argument_list|,
name|width
operator|/
literal|2
argument_list|,
literal|100
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

