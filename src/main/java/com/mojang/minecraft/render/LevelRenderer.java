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
name|chunksToUpdate
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
specifier|public
name|Chunk
index|[]
name|chunkCache
decl_stmt|;
specifier|public
name|Minecraft
name|minecraft
decl_stmt|;
specifier|public
name|int
name|ticks
init|=
literal|0
decl_stmt|;
specifier|public
name|float
name|cracks
decl_stmt|;
specifier|private
specifier|final
name|int
name|bedrockListId
decl_stmt|,
name|waterListId
decl_stmt|;
specifier|private
name|Chunk
index|[]
name|loadQueue
decl_stmt|;
specifier|private
name|int
name|xChunks
decl_stmt|,
name|yChunks
decl_stmt|,
name|zChunks
decl_stmt|;
specifier|private
name|int
name|baseListId
decl_stmt|;
specifier|private
name|int
name|listsCount
init|=
operator|-
literal|1
decl_stmt|;
specifier|private
specifier|final
name|int
index|[]
name|chunkDataCache
init|=
operator|new
name|int
index|[
literal|50000
index|]
decl_stmt|;
specifier|private
name|float
name|lastLoadX
init|=
operator|-
literal|9999F
decl_stmt|;
specifier|private
name|float
name|lastLoadY
init|=
operator|-
literal|9999F
decl_stmt|;
specifier|private
name|float
name|lastLoadZ
init|=
operator|-
literal|9999F
decl_stmt|;
specifier|public
name|LevelRenderer
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|,
name|TextureManager
name|textureManager
parameter_list|)
block|{
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
name|this
operator|.
name|textureManager
operator|=
name|textureManager
expr_stmt|;
name|bedrockListId
operator|=
name|GL11
operator|.
name|glGenLists
argument_list|(
literal|2
argument_list|)
expr_stmt|;
name|waterListId
operator|=
name|bedrockListId
operator|+
literal|1
expr_stmt|;
block|}
comment|// Requires GL_TEXTURE_2D to be enabled and rock.png to be set as texture.
comment|// Sets ambient color (with glColor4f)
specifier|public
name|void
name|renderBedrock
parameter_list|()
block|{
name|GL11
operator|.
name|glCallList
argument_list|(
name|bedrockListId
argument_list|)
expr_stmt|;
comment|// rock edges
block|}
comment|// Requires GL_TEXTURE_2D to be enabled and water.png to be set as texture.
comment|// Enables GL_BLEND and changes the BlendFunc.
specifier|public
name|void
name|renderOutsideWater
parameter_list|()
block|{
name|GL11
operator|.
name|glCallList
argument_list|(
name|waterListId
argument_list|)
expr_stmt|;
block|}
specifier|static
name|int
name|nextMultipleOf16
parameter_list|(
name|int
name|value
parameter_list|)
block|{
name|int
name|remainder
init|=
name|value
operator|%
literal|16
decl_stmt|;
if|if
condition|(
name|remainder
operator|!=
literal|0
condition|)
block|{
return|return
name|value
operator|+
operator|(
literal|16
operator|-
name|remainder
operator|)
return|;
block|}
return|return
name|value
return|;
block|}
specifier|public
specifier|final
name|void
name|queueChunks
parameter_list|(
name|int
name|x1
parameter_list|,
name|int
name|y1
parameter_list|,
name|int
name|z1
parameter_list|,
name|int
name|x2
parameter_list|,
name|int
name|y2
parameter_list|,
name|int
name|z2
parameter_list|)
block|{
name|x1
operator|/=
literal|16
expr_stmt|;
name|y1
operator|/=
literal|16
expr_stmt|;
name|z1
operator|/=
literal|16
expr_stmt|;
name|x2
operator|/=
literal|16
expr_stmt|;
name|y2
operator|/=
literal|16
expr_stmt|;
name|z2
operator|/=
literal|16
expr_stmt|;
if|if
condition|(
name|x1
operator|<
literal|0
condition|)
block|{
name|x1
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|y1
operator|<
literal|0
condition|)
block|{
name|y1
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|z1
operator|<
literal|0
condition|)
block|{
name|z1
operator|=
literal|0
expr_stmt|;
block|}
if|if
condition|(
name|x2
operator|>
name|xChunks
operator|-
literal|1
condition|)
block|{
name|x2
operator|=
name|xChunks
operator|-
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|y2
operator|>
name|yChunks
operator|-
literal|1
condition|)
block|{
name|y2
operator|=
name|yChunks
operator|-
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|z2
operator|>
name|zChunks
operator|-
literal|1
condition|)
block|{
name|z2
operator|=
name|zChunks
operator|-
literal|1
expr_stmt|;
block|}
for|for
control|(
name|int
name|x
init|=
name|x1
init|;
name|x
operator|<=
name|x2
condition|;
operator|++
name|x
control|)
block|{
for|for
control|(
name|int
name|y
init|=
name|y1
init|;
name|y
operator|<=
name|y2
condition|;
operator|++
name|y
control|)
block|{
for|for
control|(
name|int
name|z
init|=
name|z1
init|;
name|z
operator|<=
name|z2
condition|;
operator|++
name|z
control|)
block|{
name|Chunk
name|chunk
init|=
name|chunkCache
index|[
operator|(
name|z
operator|*
name|yChunks
operator|+
name|y
operator|)
operator|*
name|xChunks
operator|+
name|x
index|]
decl_stmt|;
if|if
condition|(
operator|!
name|chunk
operator|.
name|loaded
condition|)
block|{
name|chunk
operator|.
name|loaded
operator|=
literal|true
expr_stmt|;
name|chunksToUpdate
operator|.
name|add
argument_list|(
name|chunk
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
if|if
condition|(
name|chunkCache
operator|!=
literal|null
condition|)
block|{
for|for
control|(
name|Chunk
name|aChunkCache
range|:
name|chunkCache
control|)
block|{
name|aChunkCache
operator|.
name|dispose
argument_list|()
expr_stmt|;
block|}
block|}
if|if
condition|(
name|listsCount
operator|>
operator|-
literal|1
condition|)
block|{
name|GL11
operator|.
name|glDeleteLists
argument_list|(
name|baseListId
argument_list|,
name|listsCount
argument_list|)
expr_stmt|;
block|}
comment|// So that worlds that are not multiples of 16 do not have invisible chunks.
name|int
name|paddedWidth
init|=
name|nextMultipleOf16
argument_list|(
name|level
operator|.
name|width
argument_list|)
decl_stmt|;
name|int
name|paddedHeight
init|=
name|nextMultipleOf16
argument_list|(
name|level
operator|.
name|height
argument_list|)
decl_stmt|;
name|int
name|paddedLength
init|=
name|nextMultipleOf16
argument_list|(
name|level
operator|.
name|length
argument_list|)
decl_stmt|;
name|xChunks
operator|=
name|paddedWidth
operator|/
literal|16
expr_stmt|;
name|yChunks
operator|=
name|paddedHeight
operator|/
literal|16
expr_stmt|;
name|zChunks
operator|=
name|paddedLength
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
name|int
name|offset
init|=
literal|0
decl_stmt|;
name|listsCount
operator|=
name|xChunks
operator|*
name|yChunks
operator|*
name|zChunks
operator|*
literal|2
expr_stmt|;
name|baseListId
operator|=
name|GL11
operator|.
name|glGenLists
argument_list|(
name|listsCount
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|x
init|=
literal|0
init|;
name|x
operator|<
name|xChunks
condition|;
operator|++
name|x
control|)
block|{
for|for
control|(
name|int
name|y
init|=
literal|0
init|;
name|y
operator|<
name|yChunks
condition|;
operator|++
name|y
control|)
block|{
for|for
control|(
name|int
name|z
init|=
literal|0
init|;
name|z
operator|<
name|zChunks
condition|;
operator|++
name|z
control|)
block|{
name|chunkCache
index|[
operator|(
name|z
operator|*
name|yChunks
operator|+
name|y
operator|)
operator|*
name|xChunks
operator|+
name|x
index|]
operator|=
operator|new
name|Chunk
argument_list|(
name|level
argument_list|,
name|x
operator|*
literal|16
argument_list|,
name|y
operator|*
literal|16
argument_list|,
name|z
operator|*
literal|16
argument_list|,
name|baseListId
operator|+
name|offset
argument_list|)
expr_stmt|;
name|loadQueue
index|[
operator|(
name|z
operator|*
name|yChunks
operator|+
name|y
operator|)
operator|*
name|xChunks
operator|+
name|x
index|]
operator|=
name|chunkCache
index|[
operator|(
name|z
operator|*
name|yChunks
operator|+
name|y
operator|)
operator|*
name|xChunks
operator|+
name|x
index|]
expr_stmt|;
name|offset
operator|+=
literal|2
expr_stmt|;
block|}
block|}
block|}
for|for
control|(
name|Chunk
name|chunk
range|:
name|chunksToUpdate
control|)
block|{
name|chunk
operator|.
name|loaded
operator|=
literal|false
expr_stmt|;
block|}
name|chunksToUpdate
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
name|paddedWidth
argument_list|,
name|paddedHeight
argument_list|,
name|paddedLength
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
name|bedrockListId
argument_list|,
name|GL11
operator|.
name|GL_COMPILE
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
literal|1F
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
literal|1F
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
literal|0F
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
literal|0F
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
literal|0F
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
literal|0F
argument_list|,
literal|0F
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
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
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
literal|0F
argument_list|,
literal|0F
argument_list|,
name|size
argument_list|,
literal|0F
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
literal|0F
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
literal|0F
argument_list|,
literal|0F
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
literal|0F
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
literal|0F
argument_list|,
name|level
operator|.
name|length
argument_list|,
name|size
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
literal|0F
argument_list|,
name|level
operator|.
name|length
argument_list|,
literal|0F
argument_list|,
literal|0F
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
literal|0F
argument_list|,
name|groundLevel
argument_list|,
name|z
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
literal|0F
argument_list|,
name|groundLevel
argument_list|,
name|z
operator|+
name|size
argument_list|,
name|size
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|renderer
operator|.
name|vertexUV
argument_list|(
literal|0F
argument_list|,
literal|0F
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
literal|0F
argument_list|,
literal|0F
argument_list|,
name|z
argument_list|,
literal|0F
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
literal|0F
argument_list|,
name|z
argument_list|,
literal|0F
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
literal|0F
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
literal|0F
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
literal|0F
argument_list|,
literal|0F
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
name|waterListId
argument_list|,
name|GL11
operator|.
name|GL_COMPILE
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
literal|1F
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
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_BLEND
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBlendFunc
argument_list|(
name|GL11
operator|.
name|GL_SRC_ALPHA
argument_list|,
name|GL11
operator|.
name|GL_ONE_MINUS_SRC_ALPHA
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
literal|0F
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
literal|0F
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
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
comment|// Seems to be rendered twice? Not sure why, possibly used
comment|// for animated textures? TODO: investigate
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
literal|0F
argument_list|,
literal|0F
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
literal|0F
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
literal|0F
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
name|player
parameter_list|,
name|int
name|renderPass
parameter_list|)
block|{
name|float
name|distX
init|=
name|player
operator|.
name|x
operator|-
name|lastLoadX
decl_stmt|;
name|float
name|distY
init|=
name|player
operator|.
name|y
operator|-
name|lastLoadY
decl_stmt|;
name|float
name|distZ
init|=
name|player
operator|.
name|z
operator|-
name|lastLoadZ
decl_stmt|;
if|if
condition|(
name|distX
operator|*
name|distX
operator|+
name|distY
operator|*
name|distY
operator|+
name|distZ
operator|*
name|distZ
operator|>
literal|64f
condition|)
block|{
name|lastLoadX
operator|=
name|player
operator|.
name|x
expr_stmt|;
name|lastLoadY
operator|=
name|player
operator|.
name|y
expr_stmt|;
name|lastLoadZ
operator|=
name|player
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
name|player
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|int
name|count
init|=
literal|0
decl_stmt|;
for|for
control|(
name|Chunk
name|chunk
range|:
name|loadQueue
control|)
block|{
name|count
operator|=
name|chunk
operator|.
name|appendLists
argument_list|(
name|chunkDataCache
argument_list|,
name|count
argument_list|,
name|renderPass
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
name|count
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
name|GL11
operator|.
name|GL_TEXTURE_2D
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
specifier|public
name|void
name|drawSky
parameter_list|(
name|ShapeRenderer
name|shapeRenderer
parameter_list|,
name|float
name|playerY
parameter_list|,
name|float
name|skyColorRed
parameter_list|,
name|float
name|skyColorBlue
parameter_list|,
name|float
name|skyColorGreen
parameter_list|)
block|{
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|begin
argument_list|()
expr_stmt|;
name|shapeRenderer
operator|.
name|color
argument_list|(
name|skyColorRed
argument_list|,
name|skyColorBlue
argument_list|,
name|skyColorGreen
argument_list|)
expr_stmt|;
name|int
name|levelHeight
init|=
name|level
operator|.
name|height
operator|+
literal|10
decl_stmt|;
if|if
condition|(
name|playerY
operator|>
name|level
operator|.
name|height
condition|)
block|{
comment|// If player is above the level boundary, move the sky upwards
name|levelHeight
operator|=
operator|(
name|int
operator|)
operator|(
name|playerY
operator|+
literal|10
operator|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|x
init|=
operator|-
literal|2048
init|;
name|x
operator|<
name|level
operator|.
name|width
operator|+
literal|2048
condition|;
name|x
operator|+=
literal|512
control|)
block|{
for|for
control|(
name|int
name|y
init|=
operator|-
literal|2048
init|;
name|y
operator|<
name|level
operator|.
name|length
operator|+
literal|2048
condition|;
name|y
operator|+=
literal|512
control|)
block|{
name|shapeRenderer
operator|.
name|vertex
argument_list|(
name|x
argument_list|,
name|levelHeight
argument_list|,
name|y
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertex
argument_list|(
name|x
operator|+
literal|512
argument_list|,
name|levelHeight
argument_list|,
name|y
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertex
argument_list|(
name|x
operator|+
literal|512
argument_list|,
name|levelHeight
argument_list|,
name|y
operator|+
literal|512
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertex
argument_list|(
name|x
argument_list|,
name|levelHeight
argument_list|,
name|y
operator|+
literal|512
argument_list|)
expr_stmt|;
block|}
block|}
name|shapeRenderer
operator|.
name|end
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|drawClouds
parameter_list|(
name|float
name|delta
parameter_list|,
name|ShapeRenderer
name|shapeRenderer
parameter_list|)
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|textureManager
operator|.
name|load
argument_list|(
literal|"/clouds.png"
argument_list|)
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
literal|1F
argument_list|,
literal|1F
argument_list|,
literal|1F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|float
name|cloudColorRed
init|=
operator|(
name|level
operator|.
name|cloudColor
operator|>>
literal|16
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|cloudColorBlue
init|=
operator|(
name|level
operator|.
name|cloudColor
operator|>>
literal|8
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
name|float
name|cloudColorGreen
init|=
operator|(
name|level
operator|.
name|cloudColor
operator|&
literal|255
operator|)
operator|/
literal|255F
decl_stmt|;
if|if
condition|(
name|level
operator|.
name|cloudLevel
operator|<
literal|0
condition|)
block|{
name|level
operator|.
name|cloudLevel
operator|=
name|level
operator|.
name|height
operator|+
literal|2
expr_stmt|;
block|}
name|int
name|cloudLevel
init|=
name|level
operator|.
name|cloudLevel
decl_stmt|;
name|float
name|unknownCloud
init|=
literal|1F
operator|/
literal|2048F
decl_stmt|;
name|float
name|cloudTickOffset
init|=
operator|(
name|ticks
operator|+
name|delta
operator|)
operator|*
name|unknownCloud
operator|*
literal|0.03F
decl_stmt|;
name|shapeRenderer
operator|.
name|begin
argument_list|()
expr_stmt|;
name|shapeRenderer
operator|.
name|color
argument_list|(
name|cloudColorRed
argument_list|,
name|cloudColorBlue
argument_list|,
name|cloudColorGreen
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|x
init|=
operator|-
literal|2048
init|;
name|x
operator|<
name|level
operator|.
name|width
operator|+
literal|2048
condition|;
name|x
operator|+=
literal|512
control|)
block|{
for|for
control|(
name|int
name|y
init|=
operator|-
literal|2048
init|;
name|y
operator|<
name|level
operator|.
name|length
operator|+
literal|2048
condition|;
name|y
operator|+=
literal|512
control|)
block|{
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|cloudLevel
argument_list|,
name|y
operator|+
literal|512
argument_list|,
name|x
operator|*
name|unknownCloud
operator|+
name|cloudTickOffset
argument_list|,
operator|(
name|y
operator|+
literal|512
operator|)
operator|*
name|unknownCloud
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
literal|512
argument_list|,
name|cloudLevel
argument_list|,
name|y
operator|+
literal|512
argument_list|,
operator|(
name|x
operator|+
literal|512
operator|)
operator|*
name|unknownCloud
operator|+
name|cloudTickOffset
argument_list|,
operator|(
name|y
operator|+
literal|512
operator|)
operator|*
name|unknownCloud
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
literal|512
argument_list|,
name|cloudLevel
argument_list|,
name|y
argument_list|,
operator|(
name|x
operator|+
literal|512
operator|)
operator|*
name|unknownCloud
operator|+
name|cloudTickOffset
argument_list|,
name|y
operator|*
name|unknownCloud
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|cloudLevel
argument_list|,
name|y
argument_list|,
name|x
operator|*
name|unknownCloud
operator|+
name|cloudTickOffset
argument_list|,
name|y
operator|*
name|unknownCloud
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|cloudLevel
argument_list|,
name|y
argument_list|,
name|x
operator|*
name|unknownCloud
operator|+
name|cloudTickOffset
argument_list|,
name|y
operator|*
name|unknownCloud
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
literal|512
argument_list|,
name|cloudLevel
argument_list|,
name|y
argument_list|,
operator|(
name|x
operator|+
literal|512
operator|)
operator|*
name|unknownCloud
operator|+
name|cloudTickOffset
argument_list|,
name|y
operator|*
name|unknownCloud
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|x
operator|+
literal|512
argument_list|,
name|cloudLevel
argument_list|,
name|y
operator|+
literal|512
argument_list|,
operator|(
name|x
operator|+
literal|512
operator|)
operator|*
name|unknownCloud
operator|+
name|cloudTickOffset
argument_list|,
operator|(
name|y
operator|+
literal|512
operator|)
operator|*
name|unknownCloud
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
name|x
argument_list|,
name|cloudLevel
argument_list|,
name|y
operator|+
literal|512
argument_list|,
name|x
operator|*
name|unknownCloud
operator|+
name|cloudTickOffset
argument_list|,
operator|(
name|y
operator|+
literal|512
operator|)
operator|*
name|unknownCloud
argument_list|)
expr_stmt|;
block|}
block|}
name|shapeRenderer
operator|.
name|end
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

