begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|oyasunadev
operator|.
name|mcraft
operator|.
name|client
operator|.
name|util
package|;
end_package

begin_class
specifier|public
class|class
name|ExtData
block|{
specifier|public
name|int
name|Version
decl_stmt|;
specifier|public
name|String
name|Name
decl_stmt|;
specifier|public
name|ExtData
parameter_list|(
name|String
name|Name
parameter_list|,
name|int
name|Version
parameter_list|)
block|{
name|this
operator|.
name|Name
operator|=
name|Name
expr_stmt|;
name|this
operator|.
name|Version
operator|=
name|Version
expr_stmt|;
block|}
block|}
end_class

end_unit

