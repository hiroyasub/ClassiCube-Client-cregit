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
name|java
operator|.
name|nio
operator|.
name|IntBuffer
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Arrays
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
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

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|Minecraft
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
name|level
operator|.
name|Level
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
name|player
operator|.
name|Player
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|LevelRenderer
block|{
specifier|public
name|Level
name|level
decl_stmt|;
specifier|public
name|TextureManager
name|textureManager
decl_stmt|;
specifier|public
name|int
name|listId
decl_stmt|;
specifier|public
name|IntBuffer
name|buffer
init|=
name|BufferUtils
operator|.
name|createIntBuffer
argument_list|(
literal|65536
argument_list|)
decl_stmt|;
specifier|public
name|List
argument_list|<
name|Chunk
argument_list|>
name|chunks
init|=
operator|new
name|ArrayList
argument_list|<
name|Chunk
argument_list|>
argument_list|()
decl_stmt|;
specifier|private
name|Chunk
index|[]
name|loadQueue
decl_stmt|;
specifier|public
name|Chunk
index|[]
name|chunkCache
decl_stmt|;
specifier|private
name|int
name|xChunks
decl_stmt|;
specifier|private
name|int
name|yChunks
decl_stmt|;
specifier|private
name|int
name|zChunks
decl_stmt|;
specifier|private
name|int
name|baseListId
decl_stmt|;
specifier|public
name|Minecraft
name|minecraft
decl_stmt|;
specifier|private
name|int
index|[]
name|chunkDataCache
init|=
operator|new
name|int
index|[
literal|'\uc350'
index|]
decl_stmt|;
specifier|public
name|int
name|ticks
init|=
literal|0
decl_stmt|;
specifier|private
name|float
name|lastLoadX
init|=
operator|-
literal|9999.0F
decl_stmt|;
specifier|private
name|float
name|lastLoadY
init|=
operator|-
literal|9999.0F
decl_stmt|;
specifier|private
name|float
name|lastLoadZ
init|=
operator|-
literal|9999.0F
decl_stmt|;
specifier|public
name|float
name|cracks
decl_stmt|;
specifier|public
name|List
argument_list|<
name|BlockData
argument_list|>
name|iceBlocks
init|=
operator|new
name|ArrayList
argument_list|<
name|BlockData
argument_list|>
argument_list|()
decl_stmt|;
specifier|public
name|LevelRenderer
parameter_list|(
name|Minecraft
name|var1
parameter_list|,
name|TextureManager
name|var2
parameter_list|)
block|{
name|minecraft
operator|=
name|var1
expr_stmt|;
name|textureManager
operator|=
name|var2
expr_stmt|;
name|listId
operator|=
name|GL11
operator|.
name|glGenLists
argument_list|(
literal|2
argument_list|)
expr_stmt|;
name|baseListId
operator|=
name|GL11
operator|.
name|glGenLists
argument_list|(
literal|4096
operator|<<
literal|6
operator|<<
literal|1
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|queueChunks
parameter_list|(
name|int
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
parameter_list|,
name|int
name|var6
parameter_list|)
block|{
name|var1
operator|/=
literal|16
expr_stmt|;
name|var2
operator|/=
literal|16
expr_stmt|;
name|var3
operator|/=
literal|16
expr_stmt|;
name|var4
operator|/=
literal|16
expr_stmt|;
name|var5
operator|/=
literal|16
expr_stmt|;
name|var6
operator|/=
literal|16
expr_stmt|;
if|if
condition|(
name|var1
operator|<
literal|0
condition|)
block|{
name|var1
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|<
literal|0
condition|)
block|{
name|var2
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|var3
operator|<
literal|0
condition|)
block|{
name|var3
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|var4
operator|>
name|xChunks
operator|-
literal|1
condition|)
block|{
name|var4
operator|=
name|xChunks
operator|-
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|var5
operator|>
name|yChunks
operator|-
literal|1
condition|)
block|{
name|var5
operator|=
name|yChunks
operator|-
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|var6
operator|>
name|zChunks
operator|-
literal|1
condition|)
block|{
name|var6
operator|=
name|zChunks
operator|-
literal|1
expr_stmt|;
block|}
for|for
control|(
init|;
name|var1
operator|<=
name|var4
condition|;
operator|++
name|var1
control|)
block|{
for|for
control|(
name|int
name|var7
init|=
name|var2
init|;
name|var7
operator|<=
name|var5
condition|;
operator|++
name|var7
control|)
block|{
for|for
control|(
name|int
name|var8
init|=
name|var3
init|;
name|var8
operator|<=
name|var6
condition|;
operator|++
name|var8
control|)
block|{
name|Chunk
name|var9
decl_stmt|;
if|if
condition|(
operator|!
operator|(
name|var9
operator|=
name|chunkCache
index|[
operator|(
name|var8
operator|*
name|yChunks
operator|+
name|var7
operator|)
operator|*
name|xChunks
operator|+
name|var1
index|]
operator|)
operator|.
name|loaded
condition|)
block|{
name|var9
operator|.
name|loaded
operator|=
literal|true
expr_stmt|;
name|chunks
operator|.
name|add
argument_list|(
name|chunkCache
index|[
operator|(
name|var8
operator|*
name|yChunks
operator|+
name|var7
operator|)
operator|*
name|xChunks
operator|+
name|var1
index|]
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
specifier|public
specifier|final
name|void
name|refresh
parameter_list|()
block|{
name|int
name|var1
decl_stmt|;
if|if
condition|(
name|chunkCache
operator|!=
literal|null
condition|)
block|{
for|for
control|(
name|var1
operator|=
literal|0
init|;
name|var1
operator|<
name|chunkCache
operator|.
name|length
condition|;
operator|++
name|var1
control|)
block|{
name|chunkCache
index|[
name|var1
index|]
operator|.
name|dispose
argument_list|()
expr_stmt|;
block|}
block|}
name|xChunks
operator|=
name|level
operator|.
name|width
operator|/
literal|16
expr_stmt|;
name|yChunks
operator|=
name|level
operator|.
name|height
operator|/
literal|16
expr_stmt|;
name|zChunks
operator|=
name|level
operator|.
name|length
operator|/
literal|16
expr_stmt|;
name|chunkCache
operator|=
operator|new
name|Chunk
index|[
name|xChunks
operator|*
name|yChunks
operator|*
name|zChunks
index|]
expr_stmt|;
name|loadQueue
operator|=
operator|new
name|Chunk
index|[
name|xChunks
operator|*
name|yChunks
operator|*
name|zChunks
index|]
expr_stmt|;
name|var1
operator|=
literal|0
expr_stmt|;
name|int
name|var2
decl_stmt|;
name|int
name|var4
decl_stmt|;
for|for
control|(
name|var2
operator|=
literal|0
init|;
name|var2
operator|<
name|xChunks
condition|;
operator|++
name|var2
control|)
block|{
for|for
control|(
name|int
name|var3
init|=
literal|0
init|;
name|var3
operator|<
name|yChunks
condition|;
operator|++
name|var3
control|)
block|{
for|for
control|(
name|var4
operator|=
literal|0
init|;
name|var4
operator|<
name|zChunks
condition|;
operator|++
name|var4
control|)
block|{
name|chunkCache
index|[
operator|(
name|var4
operator|*
name|yChunks
operator|+
name|var3
operator|)
operator|*
name|xChunks
operator|+
name|var2
index|]
operator|=
operator|new
name|Chunk
argument_list|(
name|level
argument_list|,
name|var2
operator|<<
literal|4
argument_list|,
name|var3
operator|<<
literal|4
argument_list|,
name|var4
operator|<<
literal|4
argument_list|,
name|baseListId
operator|+
name|var1
argument_list|)
expr_stmt|;
name|loadQueue
index|[
operator|(
name|var4
operator|*
name|yChunks
operator|+
name|var3
operator|)
operator|*
name|xChunks
operator|+
name|var2
index|]
operator|=
name|chunkCache
index|[
operator|(
name|var4
operator|*
name|yChunks
operator|+
name|var3
operator|)
operator|*
name|xChunks
operator|+
name|var2
index|]
expr_stmt|;
name|var1
operator|+=
literal|2
expr_stmt|;
block|}
block|}
block|}
for|for
control|(
name|var2
operator|=
literal|0
init|;
name|var2
operator|<
name|chunks
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var2
control|)
block|{
name|chunks
operator|.
name|get
argument_list|(
name|var2
argument_list|)
operator|.
name|loaded
operator|=
literal|false
expr_stmt|;
block|}
name|chunks
operator|.
name|clear
argument_list|()
expr_stmt|;
name|refreshEnvironment
argument_list|()
expr_stmt|;
name|queueChunks
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
name|level
operator|.
name|width
argument_list|,
name|level
operator|.
name|height
argument_list|,
name|level
operator|.
name|length
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|refreshEnvironment
parameter_list|()
block|{
name|GL11
operator|.
name|glNewList
argument_list|(
name|listId
argument_list|,
literal|4864
argument_list|)
expr_stmt|;
if|if
condition|(
name|level
operator|.
name|customLightColour
operator|!=
literal|null
condition|)
block|{
name|GL11
operator|.
name|glColor4f
argument_list|(
name|level
operator|.
name|customLightColour
operator|.
name|R
argument_list|,
name|level
operator|.
name|customLightColour
operator|.
name|G
argument_list|,
name|level
operator|.
name|customLightColour
operator|.
name|B
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|GL11
operator|.
name|glColor4f
argument_list|(
literal|0.5F
argument_list|,
literal|0.5F
argument_list|,
literal|0.5F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
block|}
name|int
name|size
init|=
literal|128
decl_stmt|;
if|if
condition|(
name|size
operator|>
name|level
operator|.
name|width
condition|)
block|{
name|size
operator|=
name|level
operator|.
name|width
expr_stmt|;
block|}
if|if
condition|(
name|size
operator|>
name|level
operator|.
name|length
condition|)
block|{
name|size
operator|=
name|level
operator|.
name|length
expr_stmt|;
block|}
name|int
name|extent
init|=
literal|2048
operator|/
name|size
decl_stmt|;
name|ShapeRenderer
name|renderer
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|float
name|groundLevel
init|=
name|level
operator|.
name|getGroundLevel
argument_list|()
decl_stmt|;
name|renderer
operator|.
name|begin
argument_list|()
expr_stmt|;
comment|// Bedrock horizontal axis. (beneath and outside map)
for|for
control|(
name|int
name|x
init|=
operator|-
name|size
operator|*
name|extent
init|;
name|x
operator|<
name|level
operator|.
name|width
operator|+
name|size
operator|*
name|extent
condition|;
name|x
operator|+=
name|size
control|)
block|{
for|for
control|(
name|int
name|z
init|=
operator|-
name|size
operator|*
name|extent
init|;
name|z
operator|<
name|level
operator|.
name|length
operator|+
name|size
operator|*
name|extent
condition|;
name|z
operator|+=
name|size
control|)
block|{
name|float
name|y
init|=
name|groundLevel
decl_stmt|;
if|if
condition|(
name|x
operator|>=
literal|0
operator|&&
name|z
operator|>=
literal|0
operator|&&
name|x
operator|<
name|level
operator|.
name|width
operator|&&
name|z
operator|<
name|level
operator|.
name|length
condition|)
block|{
name|y
operator|=
literal|0.0F
expr_stmt|;
block|}
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
operator|+
name|size
argument_list|,
literal|0.0F
argument_list|,
name|size
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
name|y
argument_list|,
name|z
operator|+
name|size
argument_list|,
name|size
argument_list|,
name|size
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|size
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
block|}
block|}
comment|// Bedrock vertical X axis.
for|for
control|(
name|int
name|x
init|=
literal|0
init|;
name|x
operator|<
name|level
operator|.
name|width
condition|;
name|x
operator|+=
name|size
control|)
block|{
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
name|size
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
name|groundLevel
argument_list|,
literal|0.0F
argument_list|,
name|size
argument_list|,
name|groundLevel
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|groundLevel
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
name|groundLevel
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|groundLevel
argument_list|,
name|level
operator|.
name|length
argument_list|,
literal|0.0F
argument_list|,
name|groundLevel
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
name|groundLevel
argument_list|,
name|level
operator|.
name|length
argument_list|,
name|size
argument_list|,
name|groundLevel
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
literal|0.0F
argument_list|,
name|level
operator|.
name|length
argument_list|,
name|size
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
literal|0.0F
argument_list|,
name|level
operator|.
name|length
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
block|}
comment|// Bedrock vertical Z axis.
for|for
control|(
name|int
name|z
init|=
literal|0
init|;
name|z
operator|<
name|level
operator|.
name|length
condition|;
name|z
operator|+=
name|size
control|)
block|{
name|renderer
operator|.
name|vertexUV
argument_list|(
literal|0.0F
argument_list|,
name|groundLevel
argument_list|,
name|z
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
literal|0.0F
argument_list|,
name|groundLevel
argument_list|,
name|z
operator|+
name|size
argument_list|,
name|size
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
name|z
operator|+
name|size
argument_list|,
name|size
argument_list|,
name|groundLevel
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
name|z
argument_list|,
literal|0.0F
argument_list|,
name|groundLevel
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|level
operator|.
name|width
argument_list|,
literal|0.0F
argument_list|,
name|z
argument_list|,
literal|0.0F
argument_list|,
name|groundLevel
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|level
operator|.
name|width
argument_list|,
literal|0.0F
argument_list|,
name|z
operator|+
name|size
argument_list|,
name|size
argument_list|,
name|groundLevel
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|level
operator|.
name|width
argument_list|,
name|groundLevel
argument_list|,
name|z
operator|+
name|size
argument_list|,
name|size
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|level
operator|.
name|width
argument_list|,
name|groundLevel
argument_list|,
name|z
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
block|}
name|renderer
operator|.
name|end
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glEndList
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glNewList
argument_list|(
name|listId
operator|+
literal|1
argument_list|,
literal|4864
argument_list|)
expr_stmt|;
if|if
condition|(
name|level
operator|.
name|customLightColour
operator|!=
literal|null
condition|)
block|{
name|GL11
operator|.
name|glColor4f
argument_list|(
name|level
operator|.
name|customLightColour
operator|.
name|R
argument_list|,
name|level
operator|.
name|customLightColour
operator|.
name|G
argument_list|,
name|level
operator|.
name|customLightColour
operator|.
name|B
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
block|}
name|float
name|waterLevel
init|=
name|level
operator|.
name|getWaterLevel
argument_list|()
decl_stmt|;
name|GL11
operator|.
name|glBlendFunc
argument_list|(
literal|770
argument_list|,
literal|771
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|begin
argument_list|()
expr_stmt|;
comment|// Water horizontal axis. (outside map)
for|for
control|(
name|int
name|x
init|=
operator|-
name|size
operator|*
name|extent
init|;
name|x
operator|<
name|level
operator|.
name|width
operator|+
name|size
operator|*
name|extent
condition|;
name|x
operator|+=
name|size
control|)
block|{
for|for
control|(
name|int
name|z
init|=
operator|-
name|size
operator|*
name|extent
init|;
name|z
operator|<
name|level
operator|.
name|length
operator|+
name|size
operator|*
name|extent
condition|;
name|z
operator|+=
name|size
control|)
block|{
name|float
name|y
init|=
name|waterLevel
operator|-
literal|0.1F
decl_stmt|;
if|if
condition|(
name|x
operator|<
literal|0
operator|||
name|z
operator|<
literal|0
operator|||
name|x
operator|>=
name|level
operator|.
name|width
operator|||
name|z
operator|>=
name|level
operator|.
name|length
condition|)
block|{
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
operator|+
name|size
argument_list|,
literal|0.0F
argument_list|,
name|size
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
name|y
argument_list|,
name|z
operator|+
name|size
argument_list|,
name|size
argument_list|,
name|size
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|size
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
comment|// Seems to be rendered twice? Not sure why, possibly used for animated textures?
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|size
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
name|size
argument_list|,
name|y
argument_list|,
name|z
operator|+
name|size
argument_list|,
name|size
argument_list|,
name|size
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
operator|+
name|size
argument_list|,
literal|0.0F
argument_list|,
name|size
argument_list|)
expr_stmt|;
block|}
block|}
block|}
name|renderer
operator|.
name|end
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3042
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEndList
argument_list|()
expr_stmt|;
block|}
specifier|public
specifier|final
name|int
name|sortChunks
parameter_list|(
name|Player
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
name|float
name|var3
init|=
name|var1
operator|.
name|x
operator|-
name|lastLoadX
decl_stmt|;
name|float
name|var4
init|=
name|var1
operator|.
name|y
operator|-
name|lastLoadY
decl_stmt|;
name|float
name|var5
init|=
name|var1
operator|.
name|z
operator|-
name|lastLoadZ
decl_stmt|;
if|if
condition|(
name|var3
operator|*
name|var3
operator|+
name|var4
operator|*
name|var4
operator|+
name|var5
operator|*
name|var5
operator|>
literal|64.0F
condition|)
block|{
name|lastLoadX
operator|=
name|var1
operator|.
name|x
expr_stmt|;
name|lastLoadY
operator|=
name|var1
operator|.
name|y
expr_stmt|;
name|lastLoadZ
operator|=
name|var1
operator|.
name|z
expr_stmt|;
name|Arrays
operator|.
name|sort
argument_list|(
name|loadQueue
argument_list|,
operator|new
name|ChunkDirtyDistanceComparator
argument_list|(
name|var1
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|int
name|var6
init|=
literal|0
decl_stmt|;
for|for
control|(
name|int
name|var7
init|=
literal|0
init|;
name|var7
operator|<
name|loadQueue
operator|.
name|length
condition|;
operator|++
name|var7
control|)
block|{
name|var6
operator|=
name|loadQueue
index|[
name|var7
index|]
operator|.
name|appendLists
argument_list|(
name|chunkDataCache
argument_list|,
name|var6
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
name|buffer
operator|.
name|clear
argument_list|()
expr_stmt|;
name|buffer
operator|.
name|put
argument_list|(
name|chunkDataCache
argument_list|,
literal|0
argument_list|,
name|var6
argument_list|)
expr_stmt|;
name|buffer
operator|.
name|flip
argument_list|()
expr_stmt|;
if|if
condition|(
name|buffer
operator|.
name|remaining
argument_list|()
operator|>
literal|0
condition|)
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|textureManager
operator|.
name|load
argument_list|(
literal|"/terrain.png"
argument_list|)
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glCallLists
argument_list|(
name|buffer
argument_list|)
expr_stmt|;
block|}
return|return
name|buffer
operator|.
name|remaining
argument_list|()
return|;
block|}
block|}
end_class

end_unit

