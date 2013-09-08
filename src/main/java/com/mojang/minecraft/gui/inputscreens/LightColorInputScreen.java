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
name|java
operator|.
name|awt
operator|.
name|Color
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

begin_empty_stmt
empty_stmt|;
end_empty_stmt

begin_class
specifier|public
class|class
name|LightColorInputScreen
extends|extends
name|InputValueScreen
block|{
specifier|public
specifier|static
name|ColorCache
name|hex2Rgb
parameter_list|(
name|String
name|colorStr
parameter_list|)
block|{
name|Color
name|c
init|=
name|Color
operator|.
name|decode
argument_list|(
literal|"#"
operator|+
name|colorStr
argument_list|)
decl_stmt|;
name|float
name|red
init|=
name|c
operator|.
name|getRed
argument_list|()
operator|/
literal|255f
decl_stmt|;
name|float
name|blue
init|=
name|c
operator|.
name|getBlue
argument_list|()
operator|/
literal|255f
decl_stmt|;
name|float
name|green
init|=
name|c
operator|.
name|getGreen
argument_list|()
operator|/
literal|255f
decl_stmt|;
return|return
operator|new
name|ColorCache
argument_list|(
name|red
argument_list|,
name|blue
argument_list|,
name|green
argument_list|)
return|;
block|}
specifier|public
name|LightColorInputScreen
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
name|this
operator|.
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
name|this
operator|.
name|minecraft
decl_stmt|;
name|String
name|var2
init|=
name|this
operator|.
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
name|customLightColour
operator|=
name|hex2Rgb
argument_list|(
name|var2
argument_list|)
expr_stmt|;
name|var4
operator|.
name|levelRenderer
operator|.
name|refresh
argument_list|()
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|AdvancedOptionsScreen
argument_list|(
name|parent
argument_list|,
name|this
operator|.
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
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|AdvancedOptionsScreen
argument_list|(
name|parent
argument_list|,
name|this
operator|.
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

