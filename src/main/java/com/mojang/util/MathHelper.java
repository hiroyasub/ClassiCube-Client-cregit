begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|util
package|;
end_package

begin_class
specifier|public
specifier|final
class|class
name|MathHelper
block|{
specifier|private
specifier|static
name|float
index|[]
name|SIN_TABLE
init|=
operator|new
name|float
index|[
literal|65536
index|]
decl_stmt|;
static|static
block|{
for|for
control|(
name|int
name|var0
init|=
literal|0
init|;
name|var0
operator|<
literal|65536
condition|;
operator|++
name|var0
control|)
block|{
name|SIN_TABLE
index|[
name|var0
index|]
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|sin
argument_list|(
name|var0
operator|*
literal|3.141592653589793D
operator|*
literal|2D
operator|/
literal|65536D
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|static
name|float
name|cos
parameter_list|(
name|float
name|var0
parameter_list|)
block|{
return|return
name|SIN_TABLE
index|[
operator|(
name|int
operator|)
operator|(
name|var0
operator|*
literal|10430.378F
operator|+
literal|16384F
operator|)
operator|&
literal|'\uffff'
index|]
return|;
block|}
specifier|public
specifier|static
name|float
name|sin
parameter_list|(
name|float
name|var0
parameter_list|)
block|{
return|return
name|SIN_TABLE
index|[
operator|(
name|int
operator|)
operator|(
name|var0
operator|*
literal|10430.378F
operator|)
operator|&
literal|'\uffff'
index|]
return|;
block|}
specifier|public
specifier|static
name|float
name|sqrt
parameter_list|(
name|float
name|var0
parameter_list|)
block|{
return|return
operator|(
name|float
operator|)
name|Math
operator|.
name|sqrt
argument_list|(
name|var0
argument_list|)
return|;
block|}
block|}
end_class

end_unit

