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

begin_import
import|import
name|org
operator|.
name|lwjgl
operator|.
name|opengl
operator|.
name|GL11
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|Vec3D
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|Vertex
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|ModelPart
block|{
specifier|public
name|Vertex
index|[]
name|vertices
decl_stmt|;
specifier|public
name|TexturedQuad
index|[]
name|quads
decl_stmt|;
specifier|public
name|float
name|x
decl_stmt|;
specifier|public
name|float
name|y
decl_stmt|;
specifier|public
name|float
name|z
decl_stmt|;
specifier|public
name|float
name|pitch
decl_stmt|;
specifier|public
name|float
name|yaw
decl_stmt|;
specifier|public
name|float
name|roll
decl_stmt|;
specifier|public
name|boolean
name|hasList
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|allowTransparency
init|=
literal|true
decl_stmt|;
specifier|public
name|int
name|list
init|=
literal|0
decl_stmt|;
specifier|public
name|boolean
name|mirror
init|=
literal|false
decl_stmt|;
specifier|public
name|boolean
name|render
init|=
literal|true
decl_stmt|;
specifier|private
specifier|final
name|int
name|u
decl_stmt|;
specifier|private
specifier|final
name|int
name|v
decl_stmt|;
specifier|public
name|ModelPart
parameter_list|(
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
name|u
operator|=
name|var1
expr_stmt|;
name|v
operator|=
name|var2
expr_stmt|;
block|}
specifier|public
name|void
name|generateList
parameter_list|(
name|float
name|scale
parameter_list|)
block|{
name|list
operator|=
name|GL11
operator|.
name|glGenLists
argument_list|(
literal|1
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glNewList
argument_list|(
name|list
argument_list|,
name|GL11
operator|.
name|GL_COMPILE
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBegin
argument_list|(
name|GL11
operator|.
name|GL_QUADS
argument_list|)
expr_stmt|;
for|for
control|(
name|TexturedQuad
name|quad
range|:
name|quads
control|)
block|{
name|Vec3D
name|edge1
init|=
name|quad
operator|.
name|vertices
index|[
literal|1
index|]
operator|.
name|vector
operator|.
name|subtract
argument_list|(
name|quad
operator|.
name|vertices
index|[
literal|0
index|]
operator|.
name|vector
argument_list|)
operator|.
name|normalize
argument_list|()
decl_stmt|;
name|Vec3D
name|edge2
init|=
name|quad
operator|.
name|vertices
index|[
literal|1
index|]
operator|.
name|vector
operator|.
name|subtract
argument_list|(
name|quad
operator|.
name|vertices
index|[
literal|2
index|]
operator|.
name|vector
argument_list|)
operator|.
name|normalize
argument_list|()
decl_stmt|;
name|Vec3D
name|normal
init|=
operator|new
name|Vec3D
argument_list|(
name|edge1
operator|.
name|y
operator|*
name|edge2
operator|.
name|z
operator|-
name|edge1
operator|.
name|z
operator|*
name|edge2
operator|.
name|y
argument_list|,
name|edge1
operator|.
name|z
operator|*
name|edge2
operator|.
name|x
operator|-
name|edge1
operator|.
name|x
operator|*
name|edge2
operator|.
name|z
argument_list|,
name|edge1
operator|.
name|x
operator|*
name|edge2
operator|.
name|y
operator|-
name|edge1
operator|.
name|y
operator|*
name|edge2
operator|.
name|x
argument_list|)
operator|.
name|normalize
argument_list|()
decl_stmt|;
name|GL11
operator|.
name|glNormal3f
argument_list|(
name|normal
operator|.
name|x
argument_list|,
name|normal
operator|.
name|y
argument_list|,
name|normal
operator|.
name|z
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|vIndex
init|=
literal|0
init|;
name|vIndex
operator|<
literal|4
condition|;
operator|++
name|vIndex
control|)
block|{
name|Vertex
name|vertex
init|=
name|quad
operator|.
name|vertices
index|[
name|vIndex
index|]
decl_stmt|;
name|GL11
operator|.
name|glTexCoord2f
argument_list|(
name|vertex
operator|.
name|u
argument_list|,
name|vertex
operator|.
name|v
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex3f
argument_list|(
name|vertex
operator|.
name|vector
operator|.
name|x
operator|*
name|scale
argument_list|,
name|vertex
operator|.
name|vector
operator|.
name|y
operator|*
name|scale
argument_list|,
name|vertex
operator|.
name|vector
operator|.
name|z
operator|*
name|scale
argument_list|)
expr_stmt|;
block|}
block|}
name|GL11
operator|.
name|glEnd
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glEndList
argument_list|()
expr_stmt|;
name|hasList
operator|=
literal|true
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|render
parameter_list|(
name|float
name|scale
parameter_list|)
block|{
if|if
condition|(
name|render
condition|)
block|{
if|if
condition|(
operator|!
name|hasList
condition|)
block|{
name|generateList
argument_list|(
name|scale
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|allowTransparency
condition|)
block|{
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_ALPHA_TEST
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_CULL_FACE
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|pitch
operator|==
literal|0F
operator|&&
name|yaw
operator|==
literal|0F
operator|&&
name|roll
operator|==
literal|0F
condition|)
block|{
if|if
condition|(
name|x
operator|==
literal|0F
operator|&&
name|y
operator|==
literal|0F
operator|&&
name|z
operator|==
literal|0F
condition|)
block|{
name|GL11
operator|.
name|glCallList
argument_list|(
name|list
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|GL11
operator|.
name|glTranslatef
argument_list|(
name|x
operator|*
name|scale
argument_list|,
name|y
operator|*
name|scale
argument_list|,
name|z
operator|*
name|scale
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glCallList
argument_list|(
name|list
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
operator|-
name|x
operator|*
name|scale
argument_list|,
operator|-
name|y
operator|*
name|scale
argument_list|,
operator|-
name|z
operator|*
name|scale
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
name|x
operator|*
name|scale
argument_list|,
name|y
operator|*
name|scale
argument_list|,
name|z
operator|*
name|scale
argument_list|)
expr_stmt|;
if|if
condition|(
name|roll
operator|!=
literal|0F
condition|)
block|{
name|GL11
operator|.
name|glRotatef
argument_list|(
name|roll
operator|*
operator|(
name|float
operator|)
operator|(
literal|180D
operator|/
name|Math
operator|.
name|PI
operator|)
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|yaw
operator|!=
literal|0F
condition|)
block|{
name|GL11
operator|.
name|glRotatef
argument_list|(
name|yaw
operator|*
operator|(
name|float
operator|)
operator|(
literal|180D
operator|/
name|Math
operator|.
name|PI
operator|)
argument_list|,
literal|0F
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|pitch
operator|!=
literal|0F
condition|)
block|{
name|GL11
operator|.
name|glRotatef
argument_list|(
name|pitch
operator|*
operator|(
name|float
operator|)
operator|(
literal|180D
operator|/
name|Math
operator|.
name|PI
operator|)
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glCallList
argument_list|(
name|list
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|allowTransparency
condition|)
block|{
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_CULL_FACE
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_ALPHA_TEST
argument_list|)
expr_stmt|;
block|}
block|}
block|}
specifier|public
specifier|final
name|void
name|setBounds
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|int
name|var4
parameter_list|,
name|int
name|var5
parameter_list|,
name|int
name|var6
parameter_list|,
name|float
name|var7
parameter_list|)
block|{
name|vertices
operator|=
operator|new
name|Vertex
index|[
literal|8
index|]
expr_stmt|;
name|quads
operator|=
operator|new
name|TexturedQuad
index|[
literal|6
index|]
expr_stmt|;
name|float
name|var8
init|=
name|var1
operator|+
name|var4
decl_stmt|;
name|float
name|var9
init|=
name|var2
operator|+
name|var5
decl_stmt|;
name|float
name|var10
init|=
name|var3
operator|+
name|var6
decl_stmt|;
name|var1
operator|-=
name|var7
expr_stmt|;
name|var2
operator|-=
name|var7
expr_stmt|;
name|var3
operator|-=
name|var7
expr_stmt|;
name|var8
operator|+=
name|var7
expr_stmt|;
name|var9
operator|+=
name|var7
expr_stmt|;
name|var10
operator|+=
name|var7
expr_stmt|;
if|if
condition|(
name|mirror
condition|)
block|{
name|var7
operator|=
name|var8
expr_stmt|;
name|var8
operator|=
name|var1
expr_stmt|;
name|var1
operator|=
name|var7
expr_stmt|;
block|}
name|Vertex
name|var20
init|=
operator|new
name|Vertex
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
decl_stmt|;
name|Vertex
name|var11
init|=
operator|new
name|Vertex
argument_list|(
name|var8
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
literal|0F
argument_list|,
literal|8F
argument_list|)
decl_stmt|;
name|Vertex
name|var12
init|=
operator|new
name|Vertex
argument_list|(
name|var8
argument_list|,
name|var9
argument_list|,
name|var3
argument_list|,
literal|8F
argument_list|,
literal|8F
argument_list|)
decl_stmt|;
name|Vertex
name|var18
init|=
operator|new
name|Vertex
argument_list|(
name|var1
argument_list|,
name|var9
argument_list|,
name|var3
argument_list|,
literal|8F
argument_list|,
literal|0F
argument_list|)
decl_stmt|;
name|Vertex
name|var13
init|=
operator|new
name|Vertex
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var10
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
decl_stmt|;
name|Vertex
name|var15
init|=
operator|new
name|Vertex
argument_list|(
name|var8
argument_list|,
name|var2
argument_list|,
name|var10
argument_list|,
literal|0F
argument_list|,
literal|8F
argument_list|)
decl_stmt|;
name|Vertex
name|var21
init|=
operator|new
name|Vertex
argument_list|(
name|var8
argument_list|,
name|var9
argument_list|,
name|var10
argument_list|,
literal|8F
argument_list|,
literal|8F
argument_list|)
decl_stmt|;
name|Vertex
name|var14
init|=
operator|new
name|Vertex
argument_list|(
name|var1
argument_list|,
name|var9
argument_list|,
name|var10
argument_list|,
literal|8F
argument_list|,
literal|0F
argument_list|)
decl_stmt|;
name|vertices
index|[
literal|0
index|]
operator|=
name|var20
expr_stmt|;
name|vertices
index|[
literal|1
index|]
operator|=
name|var11
expr_stmt|;
name|vertices
index|[
literal|2
index|]
operator|=
name|var12
expr_stmt|;
name|vertices
index|[
literal|3
index|]
operator|=
name|var18
expr_stmt|;
name|vertices
index|[
literal|4
index|]
operator|=
name|var13
expr_stmt|;
name|vertices
index|[
literal|5
index|]
operator|=
name|var15
expr_stmt|;
name|vertices
index|[
literal|6
index|]
operator|=
name|var21
expr_stmt|;
name|vertices
index|[
literal|7
index|]
operator|=
name|var14
expr_stmt|;
name|quads
index|[
literal|0
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
operator|new
name|Vertex
index|[]
block|{
name|var15
block|,
name|var11
block|,
name|var12
block|,
name|var21
block|}
argument_list|,
name|u
operator|+
name|var6
operator|+
name|var4
argument_list|,
name|v
operator|+
name|var6
argument_list|,
name|u
operator|+
name|var6
operator|+
name|var4
operator|+
name|var6
argument_list|,
name|v
operator|+
name|var6
operator|+
name|var5
argument_list|)
expr_stmt|;
name|quads
index|[
literal|1
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
operator|new
name|Vertex
index|[]
block|{
name|var20
block|,
name|var13
block|,
name|var14
block|,
name|var18
block|}
argument_list|,
name|u
argument_list|,
name|v
operator|+
name|var6
argument_list|,
name|u
operator|+
name|var6
argument_list|,
name|v
operator|+
name|var6
operator|+
name|var5
argument_list|)
expr_stmt|;
name|quads
index|[
literal|2
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
operator|new
name|Vertex
index|[]
block|{
name|var15
block|,
name|var13
block|,
name|var20
block|,
name|var11
block|}
argument_list|,
name|u
operator|+
name|var6
argument_list|,
name|v
argument_list|,
name|u
operator|+
name|var6
operator|+
name|var4
argument_list|,
name|v
operator|+
name|var6
argument_list|)
expr_stmt|;
name|quads
index|[
literal|3
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
operator|new
name|Vertex
index|[]
block|{
name|var12
block|,
name|var18
block|,
name|var14
block|,
name|var21
block|}
argument_list|,
name|u
operator|+
name|var6
operator|+
name|var4
argument_list|,
name|v
argument_list|,
name|u
operator|+
name|var6
operator|+
name|var4
operator|+
name|var4
argument_list|,
name|v
operator|+
name|var6
argument_list|)
expr_stmt|;
name|quads
index|[
literal|4
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
operator|new
name|Vertex
index|[]
block|{
name|var11
block|,
name|var20
block|,
name|var18
block|,
name|var12
block|}
argument_list|,
name|u
operator|+
name|var6
argument_list|,
name|v
operator|+
name|var6
argument_list|,
name|u
operator|+
name|var6
operator|+
name|var4
argument_list|,
name|v
operator|+
name|var6
operator|+
name|var5
argument_list|)
expr_stmt|;
name|quads
index|[
literal|5
index|]
operator|=
operator|new
name|TexturedQuad
argument_list|(
operator|new
name|Vertex
index|[]
block|{
name|var13
block|,
name|var15
block|,
name|var21
block|,
name|var14
block|}
argument_list|,
name|u
operator|+
name|var6
operator|+
name|var4
operator|+
name|var6
argument_list|,
name|v
operator|+
name|var6
argument_list|,
name|u
operator|+
name|var6
operator|+
name|var4
operator|+
name|var6
operator|+
name|var4
argument_list|,
name|v
operator|+
name|var6
operator|+
name|var5
argument_list|)
expr_stmt|;
if|if
condition|(
name|mirror
condition|)
block|{
for|for
control|(
name|TexturedQuad
name|quad
range|:
name|quads
control|)
block|{
name|Vertex
index|[]
name|newVertices
init|=
operator|new
name|Vertex
index|[
name|quad
operator|.
name|vertices
operator|.
name|length
index|]
decl_stmt|;
for|for
control|(
name|int
name|vIndex
init|=
literal|0
init|;
name|vIndex
operator|<
name|quad
operator|.
name|vertices
operator|.
name|length
condition|;
operator|++
name|vIndex
control|)
block|{
name|newVertices
index|[
name|vIndex
index|]
operator|=
name|quad
operator|.
name|vertices
index|[
name|quad
operator|.
name|vertices
operator|.
name|length
operator|-
name|vIndex
operator|-
literal|1
index|]
expr_stmt|;
block|}
name|quad
operator|.
name|vertices
operator|=
name|newVertices
expr_stmt|;
block|}
block|}
block|}
specifier|public
specifier|final
name|void
name|setPosition
parameter_list|(
name|float
name|newX
parameter_list|,
name|float
name|newY
parameter_list|,
name|float
name|newZ
parameter_list|)
block|{
name|x
operator|=
name|newX
expr_stmt|;
name|y
operator|=
name|newY
expr_stmt|;
name|z
operator|=
name|newZ
expr_stmt|;
block|}
block|}
end_class

end_unit

