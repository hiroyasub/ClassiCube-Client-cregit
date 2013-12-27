begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|item
package|;
end_package

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|model
operator|.
name|ModelPart
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|model
operator|.
name|TexturedQuad
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|model
operator|.
name|Vertex
import|;
end_import

begin_class
specifier|public
class|class
name|ItemModel
block|{
specifier|private
name|ModelPart
name|model
init|=
operator|new
name|ModelPart
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|)
decl_stmt|;
specifier|public
name|ItemModel
parameter_list|(
name|int
name|tex
parameter_list|)
block|{
name|float
name|var3
init|=
operator|-
literal|2.0F
decl_stmt|;
name|float
name|var4
init|=
operator|-
literal|2.0F
decl_stmt|;
name|float
name|var15
init|=
operator|-
literal|2.0F
decl_stmt|;
name|model
operator|.
name|vertices
operator|=
operator|new
name|Vertex
index|[
literal|8
index|]
expr_stmt|;
name|model
operator|.
name|quads
operator|=
operator|new
name|TexturedQuad
index|[
literal|6
index|]
expr_stmt|;
name|Vertex
name|vertex1
init|=
operator|new
name|Vertex
argument_list|(
name|var15
argument_list|,
name|var4
argument_list|,
name|var3
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
decl_stmt|;
name|Vertex
name|vertex2
init|=
operator|new
name|Vertex
argument_list|(
literal|2.0F
argument_list|,
name|var4
argument_list|,
name|var3
argument_list|,
literal|0.0F
argument_list|,
literal|8.0F
argument_list|)
decl_stmt|;
name|Vertex
name|vertex3
init|=
operator|new
name|Vertex
argument_list|(
literal|2.0F
argument_list|,
literal|2.0F
argument_list|,
name|var3
argument_list|,
literal|8.0F
argument_list|,
literal|8.0F
argument_list|)
decl_stmt|;
name|Vertex
name|vertex4
init|=
operator|new
name|Vertex
argument_list|(
name|var15
argument_list|,
literal|2.0F
argument_list|,
name|var3
argument_list|,
literal|8.0F
argument_list|,
literal|0.0F
argument_list|)
decl_stmt|;
name|Vertex
name|vertex5
init|=
operator|new
name|Vertex
argument_list|(
name|var15
argument_list|,
name|var4
argument_list|,
literal|2.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
decl_stmt|;
name|Vertex
name|vertex6
init|=
operator|new
name|Vertex
argument_list|(
literal|2.0F
argument_list|,
name|var4
argument_list|,
literal|2.0F
argument_list|,
literal|0.0F
argument_list|,
literal|8.0F
argument_list|)
decl_stmt|;
name|Vertex
name|vertex7
init|=
operator|new
name|Vertex
argument_list|(
literal|2.0F
argument_list|,
literal|2.0F
argument_list|,
literal|2.0F
argument_list|,
literal|8.0F
argument_list|,
literal|8.0F
argument_list|)
decl_stmt|;
name|Vertex
name|vertex8
init|=
operator|new
name|Vertex
argument_list|(
name|var15
argument_list|,
literal|2.0F
argument_list|,
literal|2.0F
argument_list|,
literal|8.0F
argument_list|,
literal|0.0F
argument_list|)
decl_stmt|;
name|model
operator|.
name|vertices
index|[
literal|0
index|]
operator|=
name|vertex1
expr_stmt|;
name|model
operator|.
name|vertices
index|[
literal|1
index|]
operator|=
name|vertex2
expr_stmt|;
name|model
operator|.
name|vertices
index|[
literal|2
index|]
operator|=
name|vertex3
expr_stmt|;
name|model
operator|.
name|vertices
index|[
literal|3
index|]
operator|=
name|vertex4
expr_stmt|;
name|model
operator|.
name|vertices
index|[
literal|4
index|]
operator|=
name|vertex5
expr_stmt|;
name|model
operator|.
name|vertices
index|[
literal|5
index|]
operator|=
name|vertex6
expr_stmt|;
name|model
operator|.
name|vertices
index|[
literal|6
index|]
operator|=
name|vertex7
expr_stmt|;
name|model
operator|.
name|vertices
index|[
literal|7
index|]
operator|=
name|vertex8
expr_stmt|;
name|float
name|u1
init|=
operator|(
name|tex
operator|%
literal|16
operator|+
operator|(
literal|1.0F
operator|-
literal|0.25F
operator|)
operator|)
operator|/
literal|16.0F
decl_stmt|;
name|float
name|v1
init|=
operator|(
name|tex
operator|/
literal|16
operator|+
operator|(
literal|1.0F
operator|-
literal|0.25F
operator|)
operator|)
operator|/
literal|16.0F
decl_stmt|;
name|float
name|u2
init|=
operator|(
name|tex
operator|%
literal|16
operator|+
literal|0.25F
operator|)
operator|/
literal|16.0F
decl_stmt|;
name|float
name|v2
init|=
operator|(
name|tex
operator|/
literal|16
operator|+
literal|0.25F
operator|)
operator|/
literal|16.0F
decl_stmt|;
name|Vertex
index|[]
name|vertexes1
init|=
operator|new
name|Vertex
index|[]
block|{
name|vertex6
block|,
name|vertex2
block|,
name|vertex3
block|,
name|vertex7
block|}
decl_stmt|;
name|Vertex
index|[]
name|vertexes2
init|=
operator|new
name|Vertex
index|[]
block|{
name|vertex1
block|,
name|vertex5
block|,
name|vertex8
block|,
name|vertex4
block|}
decl_stmt|;
name|Vertex
index|[]
name|vertexes3
init|=
operator|new
name|Vertex
index|[]
block|{
name|vertex6
block|,
name|vertex5
block|,
name|vertex1
block|,
name|vertex2
block|}
decl_stmt|;
name|Vertex
index|[]
name|vertexes4
init|=
operator|new
name|Vertex
index|[]
block|{
name|vertex3
block|,
name|vertex4
block|,
name|vertex8
block|,
name|vertex7
block|}
decl_stmt|;
name|Vertex
index|[]
name|vertexes5
init|=
operator|new
name|Vertex
index|[]
block|{
name|vertex2
block|,
name|vertex1
block|,
name|vertex4
block|,
name|vertex3
block|}
decl_stmt|;
name|Vertex
index|[]
name|vertexes6
init|=
operator|new
name|Vertex
index|[]
block|{
name|vertex5
block|,
name|vertex6
block|,
name|vertex7
block|,
name|vertex8
block|}
decl_stmt|;
name|model
operator|.
name|quads
index|[
literal|0
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
name|vertexes1
argument_list|,
name|u1
argument_list|,
name|v1
argument_list|,
name|u2
argument_list|,
name|v2
argument_list|)
expr_stmt|;
name|model
operator|.
name|quads
index|[
literal|1
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
name|vertexes2
argument_list|,
name|u1
argument_list|,
name|v1
argument_list|,
name|u2
argument_list|,
name|v2
argument_list|)
expr_stmt|;
name|model
operator|.
name|quads
index|[
literal|2
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
name|vertexes3
argument_list|,
name|u1
argument_list|,
name|v1
argument_list|,
name|u2
argument_list|,
name|v2
argument_list|)
expr_stmt|;
name|model
operator|.
name|quads
index|[
literal|3
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
name|vertexes4
argument_list|,
name|u1
argument_list|,
name|v1
argument_list|,
name|u2
argument_list|,
name|v2
argument_list|)
expr_stmt|;
name|model
operator|.
name|quads
index|[
literal|4
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
name|vertexes5
argument_list|,
name|u1
argument_list|,
name|v1
argument_list|,
name|u2
argument_list|,
name|v2
argument_list|)
expr_stmt|;
name|model
operator|.
name|quads
index|[
literal|5
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
name|vertexes6
argument_list|,
name|u1
argument_list|,
name|v1
argument_list|,
name|u2
argument_list|,
name|v2
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|generateList
parameter_list|()
block|{
name|model
operator|.
name|render
argument_list|(
literal|0.0625F
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

