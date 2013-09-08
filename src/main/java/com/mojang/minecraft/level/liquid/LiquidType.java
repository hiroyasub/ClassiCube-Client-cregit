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
name|liquid
package|;
end_package

begin_class
specifier|public
class|class
name|LiquidType
block|{
specifier|private
name|LiquidType
index|[]
name|values
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LiquidType
name|NOT_LIQUID
init|=
operator|new
name|LiquidType
argument_list|(
literal|0
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LiquidType
name|WATER
init|=
operator|new
name|LiquidType
argument_list|(
literal|1
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LiquidType
name|LAVA
init|=
operator|new
name|LiquidType
argument_list|(
literal|2
argument_list|)
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|LiquidType
name|SNOW
init|=
operator|new
name|LiquidType
argument_list|(
literal|3
argument_list|)
decl_stmt|;
specifier|private
name|LiquidType
parameter_list|(
name|int
name|type
parameter_list|)
block|{
name|values
operator|=
operator|new
name|LiquidType
index|[
literal|5
index|]
expr_stmt|;
name|values
index|[
name|type
index|]
operator|=
name|this
expr_stmt|;
block|}
block|}
end_class

end_unit

