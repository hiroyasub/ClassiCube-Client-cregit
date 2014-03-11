begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|gui
operator|.
name|inputscreens
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
name|Minecraft
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
name|gui
operator|.
name|Button
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
name|gui
operator|.
name|CloudOptionsScreen
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
name|gui
operator|.
name|GuiScreen
import|;
end_import

begin_empty_stmt
empty_stmt|;
end_empty_stmt

begin_class
specifier|public
class|class
name|CloudLevelInputScreen
extends|extends
name|InputValueScreen
block|{
specifier|public
name|CloudLevelInputScreen
parameter_list|(
name|GuiScreen
name|var1
parameter_list|,
name|String
name|var2
parameter_list|,
name|int
name|var3
parameter_list|,
name|String
name|Title
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
name|Title
argument_list|)
expr_stmt|;
comment|// TODO Auto-generated constructor stub
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|void
name|onButtonClick
parameter_list|(
name|Button
name|var1
parameter_list|)
block|{
if|if
condition|(
name|var1
operator|.
name|active
condition|)
block|{
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|0
operator|&&
name|name
operator|.
name|length
argument_list|()
operator|>
literal|0
condition|)
block|{
name|Minecraft
name|var10000
init|=
name|minecraft
decl_stmt|;
name|String
name|var2
init|=
name|name
decl_stmt|;
name|Minecraft
name|var4
init|=
name|var10000
decl_stmt|;
name|var4
operator|.
name|level
operator|.
name|cloudLevel
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|var2
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|CloudOptionsScreen
argument_list|(
name|parent
argument_list|,
name|minecraft
operator|.
name|settings
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|1
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|CloudOptionsScreen
argument_list|(
name|parent
argument_list|,
name|minecraft
operator|.
name|settings
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|800
condition|)
block|{
name|minecraft
operator|.
name|level
operator|.
name|cloudLevel
operator|=
name|minecraft
operator|.
name|level
operator|.
name|height
operator|+
literal|2
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|CloudOptionsScreen
argument_list|(
name|parent
argument_list|,
name|minecraft
operator|.
name|settings
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
end_class

end_unit
