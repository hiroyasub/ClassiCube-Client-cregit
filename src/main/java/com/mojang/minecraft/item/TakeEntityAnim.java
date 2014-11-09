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
name|render
operator|.
name|TextureManager
import|;
end_import

begin_class
specifier|public
class|class
name|TakeEntityAnim
extends|extends
name|Entity
block|{
specifier|private
name|int
name|time
init|=
literal|0
decl_stmt|;
specifier|private
specifier|final
name|Entity
name|item
decl_stmt|;
specifier|private
specifier|final
name|Entity
name|player
decl_stmt|;
specifier|private
specifier|final
name|float
name|xorg
decl_stmt|,
name|yorg
decl_stmt|,
name|zorg
decl_stmt|;
specifier|public
name|TakeEntityAnim
parameter_list|(
name|Level
name|level
parameter_list|,
name|Entity
name|item
parameter_list|,
name|Entity
name|player
parameter_list|)
block|{
name|super
argument_list|(
name|level
argument_list|)
expr_stmt|;
name|this
operator|.
name|item
operator|=
name|item
expr_stmt|;
name|this
operator|.
name|player
operator|=
name|player
expr_stmt|;
name|setSize
argument_list|(
literal|1F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|xorg
operator|=
name|item
operator|.
name|x
expr_stmt|;
name|yorg
operator|=
name|item
operator|.
name|y
expr_stmt|;
name|zorg
operator|=
name|item
operator|.
name|z
expr_stmt|;
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
name|delta
parameter_list|)
block|{
name|item
operator|.
name|render
argument_list|(
name|textureManager
argument_list|,
name|delta
argument_list|)
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
if|if
condition|(
name|time
operator|>=
literal|3
condition|)
block|{
name|remove
argument_list|()
expr_stmt|;
block|}
comment|// TODO: Is this right?
name|float
name|distance
init|=
operator|(
name|distance
operator|=
name|time
operator|/
literal|3F
operator|)
operator|*
name|distance
decl_stmt|;
name|xo
operator|=
name|item
operator|.
name|xo
operator|=
name|item
operator|.
name|x
expr_stmt|;
name|yo
operator|=
name|item
operator|.
name|yo
operator|=
name|item
operator|.
name|y
expr_stmt|;
name|zo
operator|=
name|item
operator|.
name|zo
operator|=
name|item
operator|.
name|z
expr_stmt|;
name|x
operator|=
name|item
operator|.
name|x
operator|=
name|xorg
operator|+
operator|(
name|player
operator|.
name|x
operator|-
name|xorg
operator|)
operator|*
name|distance
expr_stmt|;
name|y
operator|=
name|item
operator|.
name|y
operator|=
name|yorg
operator|+
operator|(
name|player
operator|.
name|y
operator|-
literal|1F
operator|-
name|yorg
operator|)
operator|*
name|distance
expr_stmt|;
name|z
operator|=
name|item
operator|.
name|z
operator|=
name|zorg
operator|+
operator|(
name|player
operator|.
name|z
operator|-
name|zorg
operator|)
operator|*
name|distance
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
block|}
block|}
end_class

end_unit

