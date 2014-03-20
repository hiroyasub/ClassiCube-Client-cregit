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
name|i
init|=
literal|0
init|;
name|i
operator|<
literal|65536
condition|;
operator|++
name|i
control|)
block|{
name|SIN_TABLE
index|[
name|i
index|]
operator|=
operator|(
name|float
operator|)
name|Math
operator|.
name|sin
argument_list|(
operator|(
operator|(
name|double
operator|)
name|i
operator|)
operator|*
name|Math
operator|.
name|PI
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
name|theta
parameter_list|)
block|{
return|return
name|SIN_TABLE
index|[
operator|(
name|int
operator|)
operator|(
name|theta
operator|*
operator|(
name|float
operator|)
operator|(
literal|32768D
operator|/
name|Math
operator|.
name|PI
operator|)
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
name|theta
parameter_list|)
block|{
return|return
name|SIN_TABLE
index|[
operator|(
name|int
operator|)
operator|(
name|theta
operator|*
operator|(
name|float
operator|)
operator|(
literal|32768D
operator|/
name|Math
operator|.
name|PI
operator|)
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
name|num
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
name|num
argument_list|)
return|;
block|}
block|}
end_class

end_unit

