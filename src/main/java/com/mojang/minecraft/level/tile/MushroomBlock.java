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
name|level
operator|.
name|Level
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Random
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|MushroomBlock
extends|extends
name|FlowerBlock
block|{
specifier|protected
name|MushroomBlock
parameter_list|(
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
name|super
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
name|float
name|var3
init|=
literal|0.2F
decl_stmt|;
name|this
operator|.
name|setBounds
argument_list|(
literal|0.5F
operator|-
name|var3
argument_list|,
literal|0.0F
argument_list|,
literal|0.5F
operator|-
name|var3
argument_list|,
name|var3
operator|+
literal|0.5F
argument_list|,
name|var3
operator|*
literal|2.0F
argument_list|,
name|var3
operator|+
literal|0.5F
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|update
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
name|Random
name|rand
parameter_list|)
block|{
name|int
name|var6
init|=
name|level
operator|.
name|getTile
argument_list|(
name|x
argument_list|,
name|y
operator|-
literal|1
argument_list|,
name|z
argument_list|)
decl_stmt|;
if|if
condition|(
name|level
operator|.
name|isLit
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|)
operator|||
name|var6
operator|!=
name|STONE
operator|.
name|id
operator|&&
name|var6
operator|!=
name|GRAVEL
operator|.
name|id
operator|&&
name|var6
operator|!=
name|COBBLESTONE
operator|.
name|id
condition|)
block|{
name|level
operator|.
name|setTile
argument_list|(
name|x
argument_list|,
name|y
argument_list|,
name|z
argument_list|,
literal|0
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

