begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|render
package|;
end_package

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|MathHelper
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|FloatBuffer
import|;
end_import

begin_import
import|import
name|org
operator|.
name|lwjgl
operator|.
name|BufferUtils
import|;
end_import

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

begin_class
specifier|public
specifier|final
class|class
name|FrustrumImpl
extends|extends
name|Frustrum
block|{
specifier|private
specifier|static
name|FrustrumImpl
name|instance
init|=
operator|new
name|FrustrumImpl
argument_list|()
decl_stmt|;
specifier|private
specifier|static
name|void
name|normalize
parameter_list|(
name|float
index|[]
index|[]
name|var0
parameter_list|,
name|int
name|var1
parameter_list|)
block|{
name|float
name|var2
init|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|var0
index|[
name|var1
index|]
index|[
literal|0
index|]
operator|*
name|var0
index|[
name|var1
index|]
index|[
literal|0
index|]
operator|+
name|var0
index|[
name|var1
index|]
index|[
literal|1
index|]
operator|*
name|var0
index|[
name|var1
index|]
index|[
literal|1
index|]
operator|+
name|var0
index|[
name|var1
index|]
index|[
literal|2
index|]
operator|*
name|var0
index|[
name|var1
index|]
index|[
literal|2
index|]
argument_list|)
decl_stmt|;
name|var0
index|[
name|var1
index|]
index|[
literal|0
index|]
operator|/=
name|var2
expr_stmt|;
name|var0
index|[
name|var1
index|]
index|[
literal|1
index|]
operator|/=
name|var2
expr_stmt|;
name|var0
index|[
name|var1
index|]
index|[
literal|2
index|]
operator|/=
name|var2
expr_stmt|;
name|var0
index|[
name|var1
index|]
index|[
literal|3
index|]
operator|/=
name|var2
expr_stmt|;
block|}
specifier|public
specifier|static
name|Frustrum
name|update
parameter_list|()
block|{
name|FrustrumImpl
name|var0
init|=
name|instance
decl_stmt|;
name|instance
operator|.
name|projectionBuff
operator|.
name|clear
argument_list|()
expr_stmt|;
name|var0
operator|.
name|modelviewBuff
operator|.
name|clear
argument_list|()
expr_stmt|;
name|var0
operator|.
name|unused
operator|.
name|clear
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glGetFloat
argument_list|(
literal|2983
argument_list|,
name|var0
operator|.
name|projectionBuff
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glGetFloat
argument_list|(
literal|2982
argument_list|,
name|var0
operator|.
name|modelviewBuff
argument_list|)
expr_stmt|;
name|var0
operator|.
name|projectionBuff
operator|.
name|flip
argument_list|()
operator|.
name|limit
argument_list|(
literal|16
argument_list|)
expr_stmt|;
name|var0
operator|.
name|projectionBuff
operator|.
name|get
argument_list|(
name|var0
operator|.
name|projection
argument_list|)
expr_stmt|;
name|var0
operator|.
name|modelviewBuff
operator|.
name|flip
argument_list|()
operator|.
name|limit
argument_list|(
literal|16
argument_list|)
expr_stmt|;
name|var0
operator|.
name|modelviewBuff
operator|.
name|get
argument_list|(
name|var0
operator|.
name|modelview
argument_list|)
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|0
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|0
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|0
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|1
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|4
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|2
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|8
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|3
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|12
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|1
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|0
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|1
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|1
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|5
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|2
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|9
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|3
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|13
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|2
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|0
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|2
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|1
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|6
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|2
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|10
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|3
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|14
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|3
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|0
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|3
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|1
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|7
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|2
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|11
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|3
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|15
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|4
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|4
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|0
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|5
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|4
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|6
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|8
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|7
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|12
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|5
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|4
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|1
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|5
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|5
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|6
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|9
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|7
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|13
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|6
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|4
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|2
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|5
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|6
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|6
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|10
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|7
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|14
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|7
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|4
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|3
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|5
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|7
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|6
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|11
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|7
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|15
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|8
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|8
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|0
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|9
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|4
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|10
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|8
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|11
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|12
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|9
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|8
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|1
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|9
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|5
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|10
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|9
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|11
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|13
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|10
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|8
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|2
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|9
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|6
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|10
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|10
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|11
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|14
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|11
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|8
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|3
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|9
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|7
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|10
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|11
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|11
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|15
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|12
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|12
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|0
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|13
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|4
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|14
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|8
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|15
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|12
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|13
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|12
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|1
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|13
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|5
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|14
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|9
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|15
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|13
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|14
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|12
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|2
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|13
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|6
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|14
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|10
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|15
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|14
index|]
expr_stmt|;
name|var0
operator|.
name|clipping
index|[
literal|15
index|]
operator|=
name|var0
operator|.
name|modelview
index|[
literal|12
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|3
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|13
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|7
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|14
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|11
index|]
operator|+
name|var0
operator|.
name|modelview
index|[
literal|15
index|]
operator|*
name|var0
operator|.
name|projection
index|[
literal|15
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|0
index|]
index|[
literal|0
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|3
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|0
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|0
index|]
index|[
literal|1
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|7
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|4
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|0
index|]
index|[
literal|2
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|11
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|8
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|0
index|]
index|[
literal|3
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|15
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|12
index|]
expr_stmt|;
name|normalize
argument_list|(
name|var0
operator|.
name|frustrum
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|1
index|]
index|[
literal|0
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|3
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|0
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|1
index|]
index|[
literal|1
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|7
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|4
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|1
index|]
index|[
literal|2
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|11
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|8
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|1
index|]
index|[
literal|3
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|15
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|12
index|]
expr_stmt|;
name|normalize
argument_list|(
name|var0
operator|.
name|frustrum
argument_list|,
literal|1
argument_list|)
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|2
index|]
index|[
literal|0
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|3
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|1
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|2
index|]
index|[
literal|1
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|7
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|5
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|2
index|]
index|[
literal|2
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|11
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|9
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|2
index|]
index|[
literal|3
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|15
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|13
index|]
expr_stmt|;
name|normalize
argument_list|(
name|var0
operator|.
name|frustrum
argument_list|,
literal|2
argument_list|)
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|3
index|]
index|[
literal|0
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|3
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|1
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|3
index|]
index|[
literal|1
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|7
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|5
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|3
index|]
index|[
literal|2
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|11
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|9
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|3
index|]
index|[
literal|3
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|15
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|13
index|]
expr_stmt|;
name|normalize
argument_list|(
name|var0
operator|.
name|frustrum
argument_list|,
literal|3
argument_list|)
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|4
index|]
index|[
literal|0
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|3
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|2
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|4
index|]
index|[
literal|1
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|7
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|6
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|4
index|]
index|[
literal|2
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|11
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|10
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|4
index|]
index|[
literal|3
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|15
index|]
operator|-
name|var0
operator|.
name|clipping
index|[
literal|14
index|]
expr_stmt|;
name|normalize
argument_list|(
name|var0
operator|.
name|frustrum
argument_list|,
literal|4
argument_list|)
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|5
index|]
index|[
literal|0
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|3
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|2
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|5
index|]
index|[
literal|1
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|7
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|6
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|5
index|]
index|[
literal|2
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|11
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|10
index|]
expr_stmt|;
name|var0
operator|.
name|frustrum
index|[
literal|5
index|]
index|[
literal|3
index|]
operator|=
name|var0
operator|.
name|clipping
index|[
literal|15
index|]
operator|+
name|var0
operator|.
name|clipping
index|[
literal|14
index|]
expr_stmt|;
name|normalize
argument_list|(
name|var0
operator|.
name|frustrum
argument_list|,
literal|5
argument_list|)
expr_stmt|;
return|return
name|instance
return|;
block|}
specifier|private
name|FloatBuffer
name|projectionBuff
init|=
name|BufferUtils
operator|.
name|createFloatBuffer
argument_list|(
literal|16
argument_list|)
decl_stmt|;
specifier|private
name|FloatBuffer
name|modelviewBuff
init|=
name|BufferUtils
operator|.
name|createFloatBuffer
argument_list|(
literal|16
argument_list|)
decl_stmt|;
specifier|private
name|FloatBuffer
name|unused
init|=
name|BufferUtils
operator|.
name|createFloatBuffer
argument_list|(
literal|16
argument_list|)
decl_stmt|;
block|}
end_class

end_unit

