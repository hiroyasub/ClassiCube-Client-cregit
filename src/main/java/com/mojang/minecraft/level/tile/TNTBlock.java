begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|level
operator|.
name|tile
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
name|item
operator|.
name|PrimedTnt
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
name|particle
operator|.
name|ParticleManager
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|TNTBlock
extends|extends
name|Block
block|{
specifier|public
name|TNTBlock
parameter_list|(
name|int
name|id
parameter_list|)
block|{
name|super
argument_list|(
name|id
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|explode
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
parameter_list|)
block|{
if|if
condition|(
operator|!
name|var1
operator|.
name|creativeMode
condition|)
block|{
name|PrimedTnt
name|primedTnt
init|=
operator|new
name|PrimedTnt
argument_list|(
name|var1
argument_list|,
name|x
operator|+
literal|0.5F
argument_list|,
name|y
operator|+
literal|0.5F
argument_list|,
name|z
operator|+
literal|0.5F
argument_list|)
decl_stmt|;
name|primedTnt
operator|.
name|life
operator|=
name|random
operator|.
name|nextInt
argument_list|(
name|primedTnt
operator|.
name|life
operator|/
literal|4
argument_list|)
operator|+
name|primedTnt
operator|.
name|life
operator|/
literal|8
expr_stmt|;
name|var1
operator|.
name|addEntity
argument_list|(
name|primedTnt
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|int
name|getDropCount
parameter_list|()
block|{
return|return
literal|0
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|int
name|getTextureId
parameter_list|(
name|int
name|texture
parameter_list|)
block|{
return|return
name|texture
operator|==
literal|0
condition|?
name|textureId
operator|+
literal|2
else|:
name|texture
operator|==
literal|1
condition|?
name|textureId
operator|+
literal|1
else|:
name|textureId
return|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|spawnBreakParticles
parameter_list|(
name|Level
name|level
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
name|ParticleManager
name|particleManager
parameter_list|)
block|{
if|if
condition|(
operator|!
name|level
operator|.
name|creativeMode
condition|)
block|{
name|level
operator|.
name|addEntity
argument_list|(
operator|new
name|PrimedTnt
argument_list|(
name|level
argument_list|,
name|x
operator|+
literal|0.5F
argument_list|,
name|y
operator|+
literal|0.5F
argument_list|,
name|z
operator|+
literal|0.5F
argument_list|)
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|super
operator|.
name|spawnBreakParticles
argument_list|(
name|level
argument_list|,
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
name|particleManager
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

