begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|model
package|;
end_package

begin_class
specifier|public
specifier|final
class|class
name|TexturedQuad
block|{
specifier|public
name|Vertex
index|[]
name|vertices
decl_stmt|;
specifier|private
name|TexturedQuad
parameter_list|(
name|Vertex
index|[]
name|var1
parameter_list|)
block|{
name|vertices
operator|=
name|var1
expr_stmt|;
block|}
specifier|public
name|TexturedQuad
parameter_list|(
name|Vertex
index|[]
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|float
name|var4
parameter_list|,
name|float
name|var5
parameter_list|)
block|{
name|this
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|var1
index|[
literal|0
index|]
operator|=
name|var1
index|[
literal|0
index|]
operator|.
name|create
argument_list|(
name|var4
argument_list|,
name|var3
argument_list|)
expr_stmt|;
name|var1
index|[
literal|1
index|]
operator|=
name|var1
index|[
literal|1
index|]
operator|.
name|create
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|)
expr_stmt|;
name|var1
index|[
literal|2
index|]
operator|=
name|var1
index|[
literal|2
index|]
operator|.
name|create
argument_list|(
name|var2
argument_list|,
name|var5
argument_list|)
expr_stmt|;
name|var1
index|[
literal|3
index|]
operator|=
name|var1
index|[
literal|3
index|]
operator|.
name|create
argument_list|(
name|var4
argument_list|,
name|var5
argument_list|)
expr_stmt|;
block|}
specifier|public
name|TexturedQuad
parameter_list|(
name|Vertex
index|[]
name|var1
parameter_list|,
name|int
name|var2
parameter_list|,
name|int
name|var3
parameter_list|,
name|int
name|var4
parameter_list|,
name|int
name|var5
parameter_list|)
block|{
name|this
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|float
name|var7
init|=
literal|0.0015625F
decl_stmt|;
name|float
name|var6
init|=
literal|0.003125F
decl_stmt|;
name|var1
index|[
literal|0
index|]
operator|=
name|var1
index|[
literal|0
index|]
operator|.
name|create
argument_list|(
name|var4
operator|/
literal|64.0F
operator|-
name|var7
argument_list|,
name|var3
operator|/
literal|32.0F
operator|+
name|var6
argument_list|)
expr_stmt|;
name|var1
index|[
literal|1
index|]
operator|=
name|var1
index|[
literal|1
index|]
operator|.
name|create
argument_list|(
name|var2
operator|/
literal|64.0F
operator|+
name|var7
argument_list|,
name|var3
operator|/
literal|32.0F
operator|+
name|var6
argument_list|)
expr_stmt|;
name|var1
index|[
literal|2
index|]
operator|=
name|var1
index|[
literal|2
index|]
operator|.
name|create
argument_list|(
name|var2
operator|/
literal|64.0F
operator|+
name|var7
argument_list|,
name|var5
operator|/
literal|32.0F
operator|-
name|var6
argument_list|)
expr_stmt|;
name|var1
index|[
literal|3
index|]
operator|=
name|var1
index|[
literal|3
index|]
operator|.
name|create
argument_list|(
name|var4
operator|/
literal|64.0F
operator|-
name|var7
argument_list|,
name|var5
operator|/
literal|32.0F
operator|-
name|var6
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

