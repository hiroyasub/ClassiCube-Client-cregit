begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
package|;
end_package

begin_class
specifier|public
class|class
name|KeyBinding
block|{
specifier|public
name|KeyBinding
parameter_list|(
name|String
name|name
parameter_list|,
name|int
name|key
parameter_list|)
block|{
name|this
operator|.
name|name
operator|=
name|name
expr_stmt|;
name|this
operator|.
name|key
operator|=
name|key
expr_stmt|;
block|}
specifier|public
name|String
name|name
decl_stmt|;
specifier|public
name|int
name|key
decl_stmt|;
block|}
end_class

end_unit

