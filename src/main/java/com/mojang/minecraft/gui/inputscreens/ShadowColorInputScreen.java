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
name|gui
operator|.
name|AdvancedOptionsScreen
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
name|GuiScreen
import|;
end_import

begin_class
specifier|public
class|class
name|ShadowColorInputScreen
extends|extends
name|InputValueScreen
block|{
specifier|public
name|ShadowColorInputScreen
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
name|button
parameter_list|)
block|{
if|if
condition|(
name|button
operator|.
name|active
condition|)
block|{
if|if
condition|(
name|button
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
name|minecraft
operator|.
name|level
operator|.
name|customShadowColour
operator|=
name|ColorCache
operator|.
name|parseHex
argument_list|(
name|name
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|levelRenderer
operator|.
name|refresh
argument_list|()
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|AdvancedOptionsScreen
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
name|button
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
name|AdvancedOptionsScreen
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
name|button
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
name|customShadowColour
operator|=
operator|new
name|ColorCache
argument_list|(
literal|0.6f
argument_list|,
literal|0.6f
argument_list|,
literal|0.6f
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|levelRenderer
operator|.
name|refresh
argument_list|()
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|AdvancedOptionsScreen
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

