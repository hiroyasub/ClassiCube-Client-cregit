begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|level
operator|.
name|generator
operator|.
name|noise
package|;
end_package

begin_class
specifier|public
specifier|final
class|class
name|CombinedNoise
extends|extends
name|Noise
block|{
specifier|private
name|Noise
name|noise1
decl_stmt|;
specifier|private
name|Noise
name|noise2
decl_stmt|;
specifier|public
name|CombinedNoise
parameter_list|(
name|Noise
name|noise1
parameter_list|,
name|Noise
name|noise2
parameter_list|)
block|{
name|this
operator|.
name|noise1
operator|=
name|noise1
expr_stmt|;
name|this
operator|.
name|noise2
operator|=
name|noise2
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|double
name|compute
parameter_list|(
name|double
name|x
parameter_list|,
name|double
name|z
parameter_list|)
block|{
return|return
name|noise1
operator|.
name|compute
argument_list|(
name|x
operator|+
name|noise2
operator|.
name|compute
argument_list|(
name|x
argument_list|,
name|z
argument_list|)
argument_list|,
name|z
argument_list|)
return|;
block|}
block|}
end_class

end_unit

