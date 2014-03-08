begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|mob
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
name|item
operator|.
name|Item
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
name|model
operator|.
name|AnimalModel
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
name|TextureManager
import|;
end_import

begin_class
specifier|public
class|class
name|Sheep
extends|extends
name|QuadrupedMob
block|{
specifier|public
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|0L
decl_stmt|;
specifier|public
name|boolean
name|hasFur
init|=
literal|true
decl_stmt|;
specifier|public
name|boolean
name|grazing
init|=
literal|false
decl_stmt|;
specifier|public
name|int
name|grazingTime
init|=
literal|0
decl_stmt|;
specifier|public
name|float
name|graze
decl_stmt|;
specifier|public
name|float
name|grazeO
decl_stmt|;
specifier|public
name|Sheep
parameter_list|(
name|Level
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
parameter_list|)
block|{
name|super
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
expr_stmt|;
name|setSize
argument_list|(
literal|1.4F
argument_list|,
literal|1.72F
argument_list|)
expr_stmt|;
name|this
operator|.
name|setPos
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
expr_stmt|;
name|heightOffset
operator|=
literal|1.72F
expr_stmt|;
name|modelName
operator|=
literal|"sheep"
expr_stmt|;
name|textureName
operator|=
literal|"/mob/sheep.png"
expr_stmt|;
name|ai
operator|=
operator|new
name|Sheep$1
argument_list|(
name|this
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|aiStep
parameter_list|()
block|{
name|super
operator|.
name|aiStep
argument_list|()
expr_stmt|;
name|grazeO
operator|=
name|graze
expr_stmt|;
if|if
condition|(
name|grazing
condition|)
block|{
name|graze
operator|+=
literal|0.2F
expr_stmt|;
block|}
else|else
block|{
name|graze
operator|-=
literal|0.2F
expr_stmt|;
block|}
if|if
condition|(
name|graze
operator|<
literal|0.0F
condition|)
block|{
name|graze
operator|=
literal|0.0F
expr_stmt|;
block|}
if|if
condition|(
name|graze
operator|>
literal|1.0F
condition|)
block|{
name|graze
operator|=
literal|1.0F
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|die
parameter_list|(
name|Entity
name|var1
parameter_list|)
block|{
if|if
condition|(
name|var1
operator|!=
literal|null
condition|)
block|{
name|var1
operator|.
name|awardKillScore
argument_list|(
name|this
argument_list|,
literal|10
argument_list|)
expr_stmt|;
block|}
name|int
name|var2
init|=
operator|(
name|int
operator|)
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|+
name|Math
operator|.
name|random
argument_list|()
operator|+
literal|1.0D
operator|)
decl_stmt|;
for|for
control|(
name|int
name|var3
init|=
literal|0
init|;
name|var3
operator|<
name|var2
condition|;
operator|++
name|var3
control|)
block|{
name|level
operator|.
name|addEntity
argument_list|(
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
name|BROWN_MUSHROOM
operator|.
name|id
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|super
operator|.
name|die
argument_list|(
name|var1
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|hurt
parameter_list|(
name|Entity
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
if|if
condition|(
name|hasFur
operator|&&
name|var1
operator|instanceof
name|Player
condition|)
block|{
name|hasFur
operator|=
literal|false
expr_stmt|;
name|int
name|var3
init|=
operator|(
name|int
operator|)
operator|(
name|Math
operator|.
name|random
argument_list|()
operator|*
literal|3.0D
operator|+
literal|1.0D
operator|)
decl_stmt|;
for|for
control|(
name|var2
operator|=
literal|0
init|;
name|var2
operator|<
name|var3
condition|;
operator|++
name|var2
control|)
block|{
name|level
operator|.
name|addEntity
argument_list|(
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
name|WHITE_WOOL
operator|.
name|id
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|super
operator|.
name|hurt
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|void
name|renderModel
parameter_list|(
name|TextureManager
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
parameter_list|,
name|float
name|var6
parameter_list|,
name|float
name|var7
parameter_list|)
block|{
name|AnimalModel
name|var8
decl_stmt|;
name|float
name|var9
init|=
operator|(
name|var8
operator|=
operator|(
name|AnimalModel
operator|)
name|modelCache
operator|.
name|getModel
argument_list|(
literal|"sheep"
argument_list|)
operator|)
operator|.
name|head
operator|.
name|y
decl_stmt|;
name|float
name|var10
init|=
name|var8
operator|.
name|head
operator|.
name|z
decl_stmt|;
name|var8
operator|.
name|head
operator|.
name|y
operator|+=
operator|(
name|grazeO
operator|+
operator|(
name|graze
operator|-
name|grazeO
operator|)
operator|*
name|var3
operator|)
operator|*
literal|8.0F
expr_stmt|;
name|var8
operator|.
name|head
operator|.
name|z
operator|-=
name|grazeO
operator|+
operator|(
name|graze
operator|-
name|grazeO
operator|)
operator|*
name|var3
expr_stmt|;
name|super
operator|.
name|renderModel
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
expr_stmt|;
if|if
condition|(
name|hasFur
operator|||
name|modelName
operator|.
name|equals
argument_list|(
literal|"sheep.fur"
argument_list|)
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
name|var1
operator|.
name|load
argument_list|(
literal|"/mob/sheep_fur.png"
argument_list|)
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
name|AnimalModel
name|var11
decl_stmt|;
operator|(
name|var11
operator|=
operator|(
name|AnimalModel
operator|)
name|modelCache
operator|.
name|getModel
argument_list|(
literal|"sheep.fur"
argument_list|)
operator|)
operator|.
name|head
operator|.
name|yaw
operator|=
name|var8
operator|.
name|head
operator|.
name|yaw
expr_stmt|;
name|var11
operator|.
name|head
operator|.
name|pitch
operator|=
name|var8
operator|.
name|head
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|head
operator|.
name|y
operator|=
name|var8
operator|.
name|head
operator|.
name|y
expr_stmt|;
name|var11
operator|.
name|head
operator|.
name|x
operator|=
name|var8
operator|.
name|head
operator|.
name|x
expr_stmt|;
name|var11
operator|.
name|body
operator|.
name|yaw
operator|=
name|var8
operator|.
name|body
operator|.
name|yaw
expr_stmt|;
name|var11
operator|.
name|body
operator|.
name|pitch
operator|=
name|var8
operator|.
name|body
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|leg1
operator|.
name|pitch
operator|=
name|var8
operator|.
name|leg1
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|leg2
operator|.
name|pitch
operator|=
name|var8
operator|.
name|leg2
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|leg3
operator|.
name|pitch
operator|=
name|var8
operator|.
name|leg3
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|leg4
operator|.
name|pitch
operator|=
name|var8
operator|.
name|leg4
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|head
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|body
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|leg1
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|leg2
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|leg3
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|leg4
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
block|}
name|var8
operator|.
name|head
operator|.
name|y
operator|=
name|var9
expr_stmt|;
name|var8
operator|.
name|head
operator|.
name|z
operator|=
name|var10
expr_stmt|;
block|}
block|}
end_class

end_unit

