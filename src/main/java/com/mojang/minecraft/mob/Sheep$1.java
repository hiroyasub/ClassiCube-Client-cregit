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
name|mob
operator|.
name|ai
operator|.
name|BasicAI
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
specifier|final
class|class
name|Sheep$1
extends|extends
name|BasicAI
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|1L
decl_stmt|;
comment|// $FF: synthetic field
specifier|final
name|Sheep
name|sheep
decl_stmt|;
name|Sheep$1
parameter_list|(
name|Sheep
name|var1
parameter_list|)
block|{
name|this
operator|.
name|sheep
operator|=
name|var1
expr_stmt|;
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|void
name|update
parameter_list|()
block|{
name|float
name|var1
init|=
name|MathHelper
operator|.
name|sin
argument_list|(
name|this
operator|.
name|sheep
operator|.
name|yRot
operator|*
literal|3.1415927F
operator|/
literal|180.0F
argument_list|)
decl_stmt|;
name|float
name|var2
init|=
name|MathHelper
operator|.
name|cos
argument_list|(
name|this
operator|.
name|sheep
operator|.
name|yRot
operator|*
literal|3.1415927F
operator|/
literal|180.0F
argument_list|)
decl_stmt|;
name|var1
operator|=
operator|-
literal|0.7F
operator|*
name|var1
expr_stmt|;
name|var2
operator|=
literal|0.7F
operator|*
name|var2
expr_stmt|;
name|int
name|var4
init|=
operator|(
name|int
operator|)
operator|(
name|this
operator|.
name|mob
operator|.
name|x
operator|+
name|var1
operator|)
decl_stmt|;
name|int
name|var3
init|=
operator|(
name|int
operator|)
operator|(
name|this
operator|.
name|mob
operator|.
name|y
operator|-
literal|2.0F
operator|)
decl_stmt|;
name|int
name|var5
init|=
operator|(
name|int
operator|)
operator|(
name|this
operator|.
name|mob
operator|.
name|z
operator|+
name|var2
operator|)
decl_stmt|;
if|if
condition|(
name|this
operator|.
name|sheep
operator|.
name|grazing
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|level
operator|.
name|getTile
argument_list|(
name|var4
argument_list|,
name|var3
argument_list|,
name|var5
argument_list|)
operator|!=
name|Block
operator|.
name|GRASS
operator|.
name|id
condition|)
block|{
name|this
operator|.
name|sheep
operator|.
name|grazing
operator|=
literal|false
expr_stmt|;
block|}
else|else
block|{
if|if
condition|(
operator|++
name|this
operator|.
name|sheep
operator|.
name|grazingTime
operator|==
literal|60
condition|)
block|{
name|this
operator|.
name|level
operator|.
name|setTile
argument_list|(
name|var4
argument_list|,
name|var3
argument_list|,
name|var5
argument_list|,
name|Block
operator|.
name|DIRT
operator|.
name|id
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|random
operator|.
name|nextInt
argument_list|(
literal|5
argument_list|)
operator|==
literal|0
condition|)
block|{
name|this
operator|.
name|sheep
operator|.
name|hasFur
operator|=
literal|true
expr_stmt|;
block|}
block|}
name|this
operator|.
name|xxa
operator|=
literal|0.0F
expr_stmt|;
name|this
operator|.
name|yya
operator|=
literal|0.0F
expr_stmt|;
name|this
operator|.
name|mob
operator|.
name|xRot
operator|=
literal|40
operator|+
name|this
operator|.
name|sheep
operator|.
name|grazingTime
operator|/
literal|2
operator|%
literal|2
operator|*
literal|10
expr_stmt|;
block|}
block|}
else|else
block|{
if|if
condition|(
name|this
operator|.
name|level
operator|.
name|getTile
argument_list|(
name|var4
argument_list|,
name|var3
argument_list|,
name|var5
argument_list|)
operator|==
name|Block
operator|.
name|GRASS
operator|.
name|id
condition|)
block|{
name|this
operator|.
name|sheep
operator|.
name|grazing
operator|=
literal|true
expr_stmt|;
name|this
operator|.
name|sheep
operator|.
name|grazingTime
operator|=
literal|0
expr_stmt|;
block|}
name|super
operator|.
name|update
argument_list|()
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

