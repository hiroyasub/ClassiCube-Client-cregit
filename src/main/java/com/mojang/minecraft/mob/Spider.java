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
name|mob
operator|.
name|ai
operator|.
name|JumpAttackAI
import|;
end_import

begin_class
specifier|public
class|class
name|Spider
extends|extends
name|QuadrupedMob
block|{
specifier|public
name|Spider
parameter_list|(
name|Level
name|level
parameter_list|,
name|float
name|posX
parameter_list|,
name|float
name|posY
parameter_list|,
name|float
name|posZ
parameter_list|)
block|{
name|super
argument_list|(
name|level
argument_list|,
name|posX
argument_list|,
name|posY
argument_list|,
name|posZ
argument_list|)
expr_stmt|;
name|heightOffset
operator|=
literal|0.72F
expr_stmt|;
name|modelName
operator|=
literal|"spider"
expr_stmt|;
name|textureName
operator|=
literal|"/mob/spider.png"
expr_stmt|;
name|setSize
argument_list|(
literal|1.4F
argument_list|,
literal|0.9F
argument_list|)
expr_stmt|;
name|this
operator|.
name|setPos
argument_list|(
name|posX
argument_list|,
name|posY
argument_list|,
name|posZ
argument_list|)
expr_stmt|;
name|deathScore
operator|=
literal|105
expr_stmt|;
name|bobStrength
operator|=
literal|0F
expr_stmt|;
name|ai
operator|=
operator|new
name|JumpAttackAI
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

