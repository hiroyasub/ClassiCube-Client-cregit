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
name|Entity
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
name|physics
operator|.
name|AABB
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
name|minecraft
operator|.
name|render
operator|.
name|ShapeRenderer
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
name|render
operator|.
name|TextureManager
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
class|class
name|Arrow
extends|extends
name|Entity
block|{
specifier|public
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|0L
decl_stmt|;
specifier|private
name|float
name|xd
decl_stmt|;
specifier|private
name|float
name|yd
decl_stmt|;
specifier|private
name|float
name|zd
decl_stmt|;
specifier|private
name|float
name|xRot
decl_stmt|;
specifier|private
name|float
name|yRot
decl_stmt|;
specifier|private
name|float
name|yRotO
decl_stmt|;
specifier|private
name|float
name|xRotO
decl_stmt|;
specifier|private
name|boolean
name|hasHit
init|=
literal|false
decl_stmt|;
specifier|private
name|int
name|stickTime
init|=
literal|0
decl_stmt|;
specifier|private
name|Entity
name|owner
decl_stmt|;
specifier|private
name|int
name|time
init|=
literal|0
decl_stmt|;
specifier|private
name|int
name|type
init|=
literal|0
decl_stmt|;
specifier|private
name|float
name|gravity
init|=
literal|0F
decl_stmt|;
specifier|private
name|int
name|damage
decl_stmt|;
specifier|public
name|Arrow
parameter_list|(
name|Level
name|level
parameter_list|,
name|Entity
name|owner
parameter_list|,
name|float
name|x
parameter_list|,
name|float
name|y
parameter_list|,
name|float
name|z
parameter_list|,
name|float
name|unknown0
parameter_list|,
name|float
name|unknown1
parameter_list|,
name|float
name|unknown2
parameter_list|)
block|{
name|super
argument_list|(
name|level
argument_list|)
expr_stmt|;
name|this
operator|.
name|owner
operator|=
name|owner
expr_stmt|;
name|setSize
argument_list|(
literal|0.3F
argument_list|,
literal|0.5F
argument_list|)
expr_stmt|;
name|heightOffset
operator|=
name|bbHeight
operator|/
literal|2F
expr_stmt|;
name|damage
operator|=
literal|3
expr_stmt|;
if|if
condition|(
operator|!
operator|(
name|owner
operator|instanceof
name|Player
operator|)
condition|)
block|{
name|type
operator|=
literal|1
expr_stmt|;
block|}
else|else
block|{
name|damage
operator|=
literal|7
expr_stmt|;
block|}
name|heightOffset
operator|=
literal|0.25F
expr_stmt|;
name|float
name|unknown3
init|=
name|MathHelper
operator|.
name|cos
argument_list|(
operator|-
name|unknown0
operator|*
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|PI
operator|/
literal|180D
operator|)
operator|-
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
decl_stmt|;
name|float
name|unknown4
init|=
name|MathHelper
operator|.
name|sin
argument_list|(
operator|-
name|unknown0
operator|*
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|PI
operator|/
literal|180D
operator|)
operator|-
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
decl_stmt|;
name|unknown0
operator|=
name|MathHelper
operator|.
name|cos
argument_list|(
operator|-
name|unknown1
operator|*
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|PI
operator|/
literal|180D
operator|)
argument_list|)
expr_stmt|;
name|unknown1
operator|=
name|MathHelper
operator|.
name|sin
argument_list|(
operator|-
name|unknown1
operator|*
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|PI
operator|/
literal|180D
operator|)
argument_list|)
expr_stmt|;
name|slide
operator|=
literal|false
expr_stmt|;
name|gravity
operator|=
literal|1F
operator|/
name|unknown2
expr_stmt|;
name|xo
operator|-=
name|unknown3
operator|*
literal|0.2F
expr_stmt|;
name|zo
operator|+=
name|unknown4
operator|*
literal|0.2F
expr_stmt|;
name|x
operator|-=
name|unknown3
operator|*
literal|0.2F
expr_stmt|;
name|z
operator|+=
name|unknown4
operator|*
literal|0.2F
expr_stmt|;
name|xd
operator|=
name|unknown4
operator|*
name|unknown0
operator|*
name|unknown2
expr_stmt|;
name|yd
operator|=
name|unknown1
operator|*
name|unknown2
expr_stmt|;
name|zd
operator|=
name|unknown3
operator|*
name|unknown0
operator|*
name|unknown2
expr_stmt|;
name|setPos
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
expr_stmt|;
name|unknown3
operator|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|xd
operator|*
name|xd
operator|+
name|zd
operator|*
name|zd
argument_list|)
expr_stmt|;
name|yRotO
operator|=
name|yRot
operator|=
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|atan2
argument_list|(
name|xd
argument_list|,
name|zd
argument_list|)
operator|*
literal|180D
operator|/
name|Math
operator|.
name|PI
operator|)
expr_stmt|;
name|xRotO
operator|=
name|xRot
operator|=
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|atan2
argument_list|(
name|yd
argument_list|,
name|unknown3
argument_list|)
operator|*
literal|180D
operator|/
name|Math
operator|.
name|PI
operator|)
expr_stmt|;
name|makeStepSound
operator|=
literal|false
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|awardKillScore
parameter_list|(
name|Entity
name|entity
parameter_list|,
name|int
name|score
parameter_list|)
block|{
name|owner
operator|.
name|awardKillScore
argument_list|(
name|entity
argument_list|,
name|score
argument_list|)
expr_stmt|;
block|}
specifier|public
name|Entity
name|getOwner
parameter_list|()
block|{
return|return
name|owner
return|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|playerTouch
parameter_list|(
name|Entity
name|entity
parameter_list|)
block|{
name|Player
name|player
init|=
operator|(
name|Player
operator|)
name|entity
decl_stmt|;
if|if
condition|(
name|hasHit
operator|&&
name|owner
operator|==
name|player
operator|&&
name|player
operator|.
name|arrows
operator|<
literal|99
condition|)
block|{
name|TakeEntityAnim
name|takeEntityAnim
init|=
operator|new
name|TakeEntityAnim
argument_list|(
name|level
argument_list|,
name|this
argument_list|,
name|player
argument_list|)
decl_stmt|;
name|level
operator|.
name|addEntity
argument_list|(
name|takeEntityAnim
argument_list|)
expr_stmt|;
name|player
operator|.
name|arrows
operator|++
expr_stmt|;
name|remove
argument_list|()
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|render
parameter_list|(
name|TextureManager
name|textureManager
parameter_list|,
name|float
name|unknown0
parameter_list|)
block|{
name|textureId
operator|=
name|textureManager
operator|.
name|load
argument_list|(
literal|"/item/arrows.png"
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|textureId
argument_list|)
expr_stmt|;
name|float
name|brightness
init|=
name|level
operator|.
name|getBrightness
argument_list|(
operator|(
name|int
operator|)
name|x
argument_list|,
operator|(
name|int
operator|)
name|y
argument_list|,
operator|(
name|int
operator|)
name|z
argument_list|)
decl_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
name|brightness
argument_list|,
name|brightness
argument_list|,
name|brightness
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
name|xo
operator|+
operator|(
name|x
operator|-
name|xo
operator|)
operator|*
name|unknown0
argument_list|,
name|yo
operator|+
operator|(
name|y
operator|-
name|yo
operator|)
operator|*
name|unknown0
operator|-
name|heightOffset
operator|/
literal|2F
argument_list|,
name|zo
operator|+
operator|(
name|z
operator|-
name|zo
operator|)
operator|*
name|unknown0
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
name|yRotO
operator|+
operator|(
name|yRot
operator|-
name|yRotO
operator|)
operator|*
name|unknown0
operator|-
literal|90F
argument_list|,
literal|0F
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
name|xRotO
operator|+
operator|(
name|xRot
operator|-
name|xRotO
operator|)
operator|*
name|unknown0
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
literal|45F
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|ShapeRenderer
name|shapeRenderer
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|unknown0
operator|=
literal|0.5F
expr_stmt|;
name|float
name|unknown1
init|=
operator|(
name|type
operator|*
literal|10
operator|)
operator|/
literal|32F
decl_stmt|;
name|float
name|unknown2
init|=
operator|(
literal|5
operator|+
name|type
operator|*
literal|10
operator|)
operator|/
literal|32F
decl_stmt|;
name|float
name|unknown3
init|=
literal|0.15625F
decl_stmt|;
name|float
name|unknown4
init|=
operator|(
literal|5
operator|+
name|type
operator|*
literal|10
operator|)
operator|/
literal|32F
decl_stmt|;
name|float
name|unknown5
init|=
operator|(
literal|10
operator|+
name|type
operator|*
literal|10
operator|)
operator|/
literal|32F
decl_stmt|;
name|float
name|unknown6
init|=
literal|0.05625F
decl_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|0.05625F
argument_list|,
name|unknown6
argument_list|,
name|unknown6
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glNormal3f
argument_list|(
name|unknown6
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|begin
argument_list|()
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|7F
argument_list|,
operator|-
literal|2F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
name|unknown4
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|7F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|2F
argument_list|,
name|unknown3
argument_list|,
name|unknown4
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|7F
argument_list|,
literal|2F
argument_list|,
literal|2F
argument_list|,
name|unknown3
argument_list|,
name|unknown5
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|7F
argument_list|,
literal|2F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
name|unknown5
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|end
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glNormal3f
argument_list|(
operator|-
name|unknown6
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|begin
argument_list|()
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|7F
argument_list|,
literal|2F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
name|unknown4
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|7F
argument_list|,
literal|2F
argument_list|,
literal|2F
argument_list|,
name|unknown3
argument_list|,
name|unknown4
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|7F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|2F
argument_list|,
name|unknown3
argument_list|,
name|unknown5
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|7F
argument_list|,
operator|-
literal|2F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
name|unknown5
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|end
argument_list|()
expr_stmt|;
name|shapeRenderer
operator|.
name|begin
argument_list|()
expr_stmt|;
for|for
control|(
name|int
name|unknown7
init|=
literal|0
init|;
name|unknown7
operator|<
literal|4
condition|;
name|unknown7
operator|++
control|)
block|{
name|GL11
operator|.
name|glRotatef
argument_list|(
literal|90F
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glNormal3f
argument_list|(
literal|0F
argument_list|,
operator|-
name|unknown6
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|8F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
name|unknown1
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
literal|8F
argument_list|,
operator|-
literal|2F
argument_list|,
literal|0F
argument_list|,
name|unknown0
argument_list|,
name|unknown1
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
literal|8F
argument_list|,
literal|2F
argument_list|,
literal|0F
argument_list|,
name|unknown0
argument_list|,
name|unknown2
argument_list|)
expr_stmt|;
name|shapeRenderer
operator|.
name|vertexUV
argument_list|(
operator|-
literal|8F
argument_list|,
literal|2F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
name|unknown2
argument_list|)
expr_stmt|;
block|}
name|shapeRenderer
operator|.
name|end
argument_list|()
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
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|tick
parameter_list|()
block|{
name|time
operator|++
expr_stmt|;
name|xRotO
operator|=
name|xRot
expr_stmt|;
name|yRotO
operator|=
name|yRot
expr_stmt|;
name|xo
operator|=
name|x
expr_stmt|;
name|yo
operator|=
name|y
expr_stmt|;
name|zo
operator|=
name|z
expr_stmt|;
if|if
condition|(
name|hasHit
condition|)
block|{
name|stickTime
operator|++
expr_stmt|;
if|if
condition|(
name|type
operator|==
literal|0
condition|)
block|{
if|if
condition|(
name|stickTime
operator|>=
literal|300
operator|&&
name|Math
operator|.
name|random
argument_list|()
operator|<
literal|0.009999999776482582D
condition|)
block|{
name|remove
argument_list|()
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|type
operator|==
literal|1
operator|&&
name|stickTime
operator|>=
literal|20
condition|)
block|{
name|remove
argument_list|()
expr_stmt|;
block|}
block|}
else|else
block|{
name|xd
operator|*=
literal|0.998F
expr_stmt|;
name|yd
operator|*=
literal|0.998F
expr_stmt|;
name|zd
operator|*=
literal|0.998F
expr_stmt|;
name|yd
operator|-=
literal|0.02F
operator|*
name|gravity
expr_stmt|;
name|int
name|unknown0
init|=
operator|(
name|int
operator|)
operator|(
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|xd
operator|*
name|xd
operator|+
name|yd
operator|*
name|yd
operator|+
name|zd
operator|*
name|zd
argument_list|)
operator|/
literal|0.2F
operator|+
literal|1F
operator|)
decl_stmt|;
name|float
name|x0
init|=
name|xd
operator|/
name|unknown0
decl_stmt|;
name|float
name|y0
init|=
name|yd
operator|/
name|unknown0
decl_stmt|;
name|float
name|z0
init|=
name|zd
operator|/
name|unknown0
decl_stmt|;
for|for
control|(
name|int
name|unknown4
init|=
literal|0
init|;
name|unknown4
operator|<
name|unknown0
operator|&&
operator|!
name|collision
condition|;
name|unknown4
operator|++
control|)
block|{
name|AABB
name|unknown5
init|=
name|boundingBox
operator|.
name|expand
argument_list|(
name|x0
argument_list|,
name|y0
argument_list|,
name|z0
argument_list|)
decl_stmt|;
if|if
condition|(
name|level
operator|.
name|getCubes
argument_list|(
name|unknown5
argument_list|)
operator|.
name|size
argument_list|()
operator|>
literal|0
condition|)
block|{
name|collision
operator|=
literal|true
expr_stmt|;
block|}
for|for
control|(
name|Entity
name|entity
range|:
name|level
operator|.
name|blockMap
operator|.
name|getEntities
argument_list|(
name|this
argument_list|,
name|unknown5
argument_list|)
control|)
block|{
if|if
condition|(
name|entity
operator|.
name|isShootable
argument_list|()
operator|&&
operator|(
name|entity
operator|!=
name|owner
operator|||
name|time
operator|>
literal|5
operator|)
condition|)
block|{
name|entity
operator|.
name|hurt
argument_list|(
name|this
argument_list|,
name|damage
argument_list|)
expr_stmt|;
name|collision
operator|=
literal|true
expr_stmt|;
name|remove
argument_list|()
expr_stmt|;
return|return;
block|}
block|}
if|if
condition|(
operator|!
name|collision
condition|)
block|{
name|boundingBox
operator|.
name|move
argument_list|(
name|x0
argument_list|,
name|y0
argument_list|,
name|z0
argument_list|)
expr_stmt|;
name|x
operator|+=
name|x0
expr_stmt|;
name|y
operator|+=
name|y0
expr_stmt|;
name|z
operator|+=
name|z0
expr_stmt|;
name|blockMap
operator|.
name|moved
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
block|}
if|if
condition|(
name|collision
condition|)
block|{
name|hasHit
operator|=
literal|true
expr_stmt|;
name|xd
operator|=
name|yd
operator|=
name|zd
operator|=
literal|0F
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|hasHit
condition|)
block|{
name|float
name|unknown6
init|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|xd
operator|*
name|xd
operator|+
name|zd
operator|*
name|zd
argument_list|)
decl_stmt|;
name|xRot
operator|=
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|atan2
argument_list|(
name|yd
argument_list|,
name|unknown6
argument_list|)
operator|*
literal|180D
operator|/
name|Math
operator|.
name|PI
operator|)
expr_stmt|;
name|yRot
operator|=
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|atan2
argument_list|(
name|xd
argument_list|,
name|zd
argument_list|)
operator|*
literal|180D
operator|/
name|Math
operator|.
name|PI
operator|)
expr_stmt|;
while|while
condition|(
name|xRot
operator|-
name|xRotO
operator|<
operator|-
literal|180F
condition|)
block|{
name|xRotO
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|xRot
operator|-
name|xRotO
operator|>=
literal|180F
condition|)
block|{
name|xRotO
operator|+=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|yRot
operator|-
name|yRotO
operator|<
operator|-
literal|180F
condition|)
block|{
name|yRotO
operator|-=
literal|360F
expr_stmt|;
block|}
while|while
condition|(
name|yRot
operator|-
name|yRotO
operator|>=
literal|180F
condition|)
block|{
name|yRotO
operator|+=
literal|360F
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

