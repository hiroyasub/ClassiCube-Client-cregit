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
name|java
operator|.
name|util
operator|.
name|Random
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
name|particle
operator|.
name|SmokeParticle
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
name|particle
operator|.
name|TerrainParticle
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

begin_class
specifier|public
class|class
name|PrimedTnt
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
specifier|public
name|int
name|life
init|=
literal|0
decl_stmt|;
specifier|private
name|boolean
name|defused
decl_stmt|;
specifier|public
name|PrimedTnt
parameter_list|(
name|Level
name|level1
parameter_list|,
name|float
name|x
parameter_list|,
name|float
name|y
parameter_list|,
name|float
name|z
parameter_list|)
block|{
name|super
argument_list|(
name|level1
argument_list|)
expr_stmt|;
name|setSize
argument_list|(
literal|0.98F
argument_list|,
literal|0.98F
argument_list|)
expr_stmt|;
name|heightOffset
operator|=
name|bbHeight
operator|/
literal|2F
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
name|float
name|unknown0
init|=
operator|(
name|float
operator|)
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|3.1415927410125732D
operator|*
literal|2D
operator|)
decl_stmt|;
name|xd
operator|=
operator|-
name|MathHelper
operator|.
name|sin
argument_list|(
name|unknown0
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
operator|/
literal|180F
argument_list|)
operator|*
literal|0.02F
expr_stmt|;
name|yd
operator|=
literal|0.2F
expr_stmt|;
name|zd
operator|=
operator|-
name|MathHelper
operator|.
name|cos
argument_list|(
name|unknown0
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
operator|/
literal|180F
argument_list|)
operator|*
literal|0.02F
expr_stmt|;
name|makeStepSound
operator|=
literal|false
expr_stmt|;
name|life
operator|=
literal|40
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
block|}
annotation|@
name|Override
specifier|public
name|void
name|hurt
parameter_list|(
name|Entity
name|entity
parameter_list|,
name|int
name|damage
parameter_list|)
block|{
if|if
condition|(
operator|!
name|removed
condition|)
block|{
name|super
operator|.
name|hurt
argument_list|(
name|entity
argument_list|,
name|damage
argument_list|)
expr_stmt|;
if|if
condition|(
name|entity
operator|instanceof
name|Player
condition|)
block|{
name|remove
argument_list|()
expr_stmt|;
name|Item
name|item
init|=
operator|new
name|Item
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|Block
operator|.
name|TNT
operator|.
name|id
argument_list|)
decl_stmt|;
name|level
operator|.
name|addEntity
argument_list|(
name|item
argument_list|)
expr_stmt|;
block|}
block|}
block|}
annotation|@
name|Override
specifier|public
name|boolean
name|isPickable
parameter_list|()
block|{
return|return
operator|!
name|removed
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
if|if
condition|(
name|defused
condition|)
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
name|player
operator|.
name|addResource
argument_list|(
name|Block
operator|.
name|TNT
operator|.
name|id
argument_list|)
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
name|remove
argument_list|()
expr_stmt|;
block|}
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
name|int
name|textureID
init|=
name|textureManager
operator|.
name|load
argument_list|(
literal|"/terrain.png"
argument_list|)
decl_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|,
name|textureID
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
operator|-
literal|0.5F
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
literal|0.5F
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
operator|-
literal|0.5F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|ShapeRenderer
name|shapeRenderer
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|Block
operator|.
name|TNT
operator|.
name|renderPreview
argument_list|(
name|shapeRenderer
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_TEXTURE_2D
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_LIGHTING
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
operator|(
name|life
operator|/
literal|4
operator|+
literal|1
operator|)
operator|%
literal|2
operator|*
literal|0.4F
argument_list|)
expr_stmt|;
if|if
condition|(
name|life
operator|<=
literal|16
condition|)
block|{
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
operator|(
name|life
operator|+
literal|1
operator|)
operator|%
literal|2
operator|*
literal|0.6F
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|life
operator|<=
literal|2
condition|)
block|{
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
literal|0.9F
argument_list|)
expr_stmt|;
block|}
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
literal|1
argument_list|)
expr_stmt|;
name|Block
operator|.
name|TNT
operator|.
name|renderPreview
argument_list|(
name|shapeRenderer
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
name|GL11
operator|.
name|GL_BLEND
argument_list|)
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
name|GL11
operator|.
name|glEnable
argument_list|(
name|GL11
operator|.
name|GL_LIGHTING
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
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
name|yd
operator|-=
literal|0.04F
expr_stmt|;
name|move
argument_list|(
name|xd
argument_list|,
name|yd
argument_list|,
name|zd
argument_list|)
expr_stmt|;
name|xd
operator|*=
literal|0.98F
expr_stmt|;
name|yd
operator|*=
literal|0.98F
expr_stmt|;
name|zd
operator|*=
literal|0.98F
expr_stmt|;
if|if
condition|(
name|onGround
condition|)
block|{
name|xd
operator|*=
literal|0.7F
expr_stmt|;
name|zd
operator|*=
literal|0.7F
expr_stmt|;
name|yd
operator|*=
operator|-
literal|0.5F
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|defused
condition|)
block|{
if|if
condition|(
name|life
operator|--
operator|>
literal|0
condition|)
block|{
name|SmokeParticle
name|smokeParticle
init|=
operator|new
name|SmokeParticle
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
operator|+
literal|0.6F
argument_list|,
name|z
argument_list|)
decl_stmt|;
name|level
operator|.
name|particleEngine
operator|.
name|spawnParticle
argument_list|(
name|smokeParticle
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|remove
argument_list|()
expr_stmt|;
name|Random
name|random
init|=
operator|new
name|Random
argument_list|()
decl_stmt|;
name|float
name|radius
init|=
literal|4F
decl_stmt|;
name|level
operator|.
name|explode
argument_list|(
literal|null
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|radius
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
literal|100
condition|;
name|i
operator|++
control|)
block|{
name|float
name|unknown0
init|=
operator|(
name|float
operator|)
name|random
operator|.
name|nextGaussian
argument_list|()
operator|*
name|radius
operator|/
literal|4F
decl_stmt|;
name|float
name|unknown1
init|=
operator|(
name|float
operator|)
name|random
operator|.
name|nextGaussian
argument_list|()
operator|*
name|radius
operator|/
literal|4F
decl_stmt|;
name|float
name|unknown2
init|=
operator|(
name|float
operator|)
name|random
operator|.
name|nextGaussian
argument_list|()
operator|*
name|radius
operator|/
literal|4F
decl_stmt|;
name|float
name|unknown3
init|=
name|MathHelper
operator|.
name|sqrt
argument_list|(
name|unknown0
operator|*
name|unknown0
operator|+
name|unknown1
operator|*
name|unknown1
operator|+
name|unknown2
operator|*
name|unknown2
argument_list|)
decl_stmt|;
name|float
name|unknown4
init|=
name|unknown0
operator|/
name|unknown3
operator|/
name|unknown3
decl_stmt|;
name|float
name|unknown5
init|=
name|unknown1
operator|/
name|unknown3
operator|/
name|unknown3
decl_stmt|;
name|unknown3
operator|=
name|unknown2
operator|/
name|unknown3
operator|/
name|unknown3
expr_stmt|;
name|TerrainParticle
name|terrainParticle
init|=
operator|new
name|TerrainParticle
argument_list|(
name|level
argument_list|,
name|x
operator|+
name|unknown0
argument_list|,
name|y
operator|+
name|unknown1
argument_list|,
name|z
operator|+
name|unknown2
argument_list|,
name|unknown4
argument_list|,
name|unknown5
argument_list|,
name|unknown3
argument_list|,
name|Block
operator|.
name|TNT
argument_list|)
decl_stmt|;
name|level
operator|.
name|particleEngine
operator|.
name|spawnParticle
argument_list|(
name|terrainParticle
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

