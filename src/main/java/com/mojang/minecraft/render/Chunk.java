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
name|level
operator|.
name|tile
operator|.
name|Block
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

begin_class
specifier|public
specifier|final
class|class
name|Chunk
block|{
specifier|private
name|Level
name|level
decl_stmt|;
specifier|private
name|int
name|baseListId
init|=
operator|-
literal|1
decl_stmt|;
specifier|private
specifier|static
name|ShapeRenderer
name|shapeRenderer
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
specifier|public
specifier|static
name|int
name|chunkUpdates
init|=
literal|0
decl_stmt|;
specifier|private
name|int
name|x
decl_stmt|;
specifier|private
name|int
name|y
decl_stmt|;
specifier|private
name|int
name|z
decl_stmt|;
specifier|private
name|int
name|chunkSize
decl_stmt|;
specifier|public
name|boolean
name|visible
init|=
literal|false
decl_stmt|;
specifier|private
name|boolean
index|[]
name|dirty
init|=
operator|new
name|boolean
index|[
literal|2
index|]
decl_stmt|;
specifier|public
name|boolean
name|loaded
decl_stmt|;
specifier|public
name|Chunk
parameter_list|(
name|Level
name|var1
parameter_list|,
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|,
name|int
name|z
parameter_list|,
name|int
name|listID
parameter_list|)
block|{
name|level
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|x
operator|=
name|x
expr_stmt|;
name|this
operator|.
name|y
operator|=
name|y
expr_stmt|;
name|this
operator|.
name|z
operator|=
name|z
expr_stmt|;
name|chunkSize
operator|=
literal|16
expr_stmt|;
name|baseListId
operator|=
name|listID
expr_stmt|;
name|setAllDirty
argument_list|()
expr_stmt|;
block|}
specifier|public
specifier|final
name|int
name|appendLists
parameter_list|(
name|int
index|[]
name|chunkData
parameter_list|,
name|int
name|count
parameter_list|,
name|int
name|pass
parameter_list|)
block|{
if|if
condition|(
operator|!
name|visible
condition|)
block|{
return|return
name|count
return|;
block|}
else|else
block|{
if|if
condition|(
operator|!
name|dirty
index|[
name|pass
index|]
condition|)
block|{
name|chunkData
index|[
name|count
operator|++
index|]
operator|=
name|baseListId
operator|+
name|pass
expr_stmt|;
block|}
return|return
name|count
return|;
block|}
block|}
specifier|public
specifier|final
name|void
name|clip
parameter_list|(
name|Frustrum
name|frustrum
parameter_list|)
block|{
name|visible
operator|=
name|frustrum
operator|.
name|isBoxInFrustum
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|x
operator|+
name|chunkSize
argument_list|,
name|y
operator|+
name|chunkSize
argument_list|,
name|z
operator|+
name|chunkSize
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|dispose
parameter_list|()
block|{
name|setAllDirty
argument_list|()
expr_stmt|;
name|level
operator|=
literal|null
expr_stmt|;
block|}
specifier|public
specifier|final
name|float
name|distanceSquared
parameter_list|(
name|Player
name|player
parameter_list|)
block|{
name|float
name|dx
init|=
name|player
operator|.
name|x
operator|-
name|x
decl_stmt|;
name|float
name|dy
init|=
name|player
operator|.
name|y
operator|-
name|y
decl_stmt|;
name|float
name|dz
init|=
name|player
operator|.
name|z
operator|-
name|z
decl_stmt|;
return|return
name|dx
operator|*
name|dx
operator|+
name|dy
operator|*
name|dy
operator|+
name|dz
operator|*
name|dz
return|;
block|}
specifier|private
name|void
name|setAllDirty
parameter_list|()
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
literal|2
condition|;
operator|++
name|i
control|)
block|{
name|dirty
index|[
name|i
index|]
operator|=
literal|true
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|update
parameter_list|()
block|{
name|chunkUpdates
operator|++
expr_stmt|;
name|int
name|sx
init|=
name|x
decl_stmt|;
name|int
name|sy
init|=
name|y
decl_stmt|;
name|int
name|sz
init|=
name|z
decl_stmt|;
name|int
name|ex
init|=
name|x
operator|+
name|chunkSize
decl_stmt|;
name|int
name|ey
init|=
name|y
operator|+
name|chunkSize
decl_stmt|;
name|int
name|ez
init|=
name|z
operator|+
name|chunkSize
decl_stmt|;
name|int
name|renderPassType
decl_stmt|;
for|for
control|(
name|renderPassType
operator|=
literal|0
init|;
name|renderPassType
operator|<
literal|2
condition|;
operator|++
name|renderPassType
control|)
block|{
name|dirty
index|[
name|renderPassType
index|]
operator|=
literal|true
expr_stmt|;
block|}
for|for
control|(
name|renderPassType
operator|=
literal|0
init|;
name|renderPassType
operator|<
literal|2
condition|;
operator|++
name|renderPassType
control|)
block|{
name|boolean
name|needNextPass
init|=
literal|false
decl_stmt|;
name|boolean
name|wasRendered
init|=
literal|false
decl_stmt|;
name|GL11
operator|.
name|glNewList
argument_list|(
name|baseListId
operator|+
name|renderPassType
argument_list|,
name|GL11
operator|.
name|GL_COMPILE
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|begin
argument_list|()
expr_stmt|;
for|for
control|(
name|int
name|posX
init|=
name|sx
init|;
name|posX
operator|<
name|ex
condition|;
operator|++
name|posX
control|)
block|{
for|for
control|(
name|int
name|posY
init|=
name|sy
init|;
name|posY
operator|<
name|ey
condition|;
operator|++
name|posY
control|)
block|{
for|for
control|(
name|int
name|posZ
init|=
name|sz
init|;
name|posZ
operator|<
name|ez
condition|;
operator|++
name|posZ
control|)
block|{
name|int
name|tile
init|=
name|level
operator|.
name|getTile
argument_list|(
name|posX
argument_list|,
name|posY
argument_list|,
name|posZ
argument_list|)
decl_stmt|;
if|if
condition|(
name|tile
operator|>
literal|0
condition|)
block|{
name|Block
name|block
init|=
name|Block
operator|.
name|blocks
index|[
name|tile
index|]
decl_stmt|;
if|if
condition|(
name|block
operator|.
name|getRenderPass
argument_list|()
operator|!=
name|renderPassType
condition|)
block|{
name|needNextPass
operator|=
literal|true
expr_stmt|;
block|}
else|else
block|{
name|wasRendered
operator||=
name|block
operator|.
name|render
argument_list|(
name|level
argument_list|,
name|posX
argument_list|,
name|posY
argument_list|,
name|posZ
argument_list|,
name|shapeRenderer
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
name|shapeRenderer
operator|.
name|end
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glEndList
argument_list|()
expr_stmt|;
if|if
condition|(
name|wasRendered
condition|)
block|{
name|dirty
index|[
name|renderPassType
index|]
operator|=
literal|false
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|needNextPass
condition|)
block|{
break|break;
block|}
block|}
block|}
block|}
end_class

end_unit

