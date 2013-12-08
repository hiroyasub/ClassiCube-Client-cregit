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

begin_class
specifier|public
specifier|final
class|class
name|StepSoundStone
extends|extends
name|StepSound
block|{
specifier|public
name|StepSoundStone
parameter_list|(
name|String
name|soundName
parameter_list|,
name|float
name|soundVolume
parameter_list|,
name|float
name|soundPitch
parameter_list|)
block|{
name|super
argument_list|(
name|soundName
argument_list|,
name|soundVolume
argument_list|,
name|soundPitch
argument_list|)
expr_stmt|;
block|}
specifier|public
name|String
name|getBreakSound
parameter_list|()
block|{
return|return
literal|"random.glass"
return|;
block|}
specifier|public
name|String
name|getPlaceSound
parameter_list|()
block|{
return|return
literal|"step.stone"
return|;
block|}
block|}
end_class

end_unit

