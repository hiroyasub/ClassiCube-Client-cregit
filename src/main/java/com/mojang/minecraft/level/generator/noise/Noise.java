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
specifier|abstract
class|class
name|Noise
block|{
specifier|public
specifier|abstract
name|double
name|compute
parameter_list|(
name|double
name|x
parameter_list|,
name|double
name|z
parameter_list|)
function_decl|;
block|}
end_class

end_unit

