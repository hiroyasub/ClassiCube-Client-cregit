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
name|ColorCache
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

begin_class
specifier|public
specifier|final
class|class
name|MagmaBlock
extends|extends
name|Block
block|{
specifier|protected
name|MagmaBlock
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
name|super
argument_list|(
name|var1
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|ColorCache
name|getBrightness
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
parameter_list|)
block|{
return|return
operator|new
name|ColorCache
argument_list|(
literal|255.0F
operator|/
literal|255.0F
argument_list|,
literal|255.0F
operator|/
literal|255.0F
argument_list|,
literal|255.0F
operator|/
literal|255.0F
argument_list|)
return|;
block|}
block|}
end_class

end_unit

