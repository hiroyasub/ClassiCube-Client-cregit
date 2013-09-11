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
package|;
end_package

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|Desktop
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|File
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|IOException
import|;
end_import

begin_import
import|import
name|java
operator|.
name|lang
operator|.
name|management
operator|.
name|ManagementFactory
import|;
end_import

begin_import
import|import
name|java
operator|.
name|lang
operator|.
name|reflect
operator|.
name|Method
import|;
end_import

begin_import
import|import
name|java
operator|.
name|lang
operator|.
name|reflect
operator|.
name|Modifier
import|;
end_import

begin_import
import|import
name|javax
operator|.
name|management
operator|.
name|MBeanServerConnection
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
name|ProgressBarDisplay
import|;
end_import

begin_import
import|import
name|com
operator|.
name|sun
operator|.
name|management
operator|.
name|OperatingSystemMXBean
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|PauseScreen
extends|extends
name|GuiScreen
block|{
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
name|id
operator|==
literal|0
condition|)
block|{
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|OptionsScreen
argument_list|(
name|this
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
name|GenerateLevelScreen
argument_list|(
name|this
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
literal|2
condition|)
block|{
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|SaveLevelScreen
argument_list|(
name|this
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
literal|3
condition|)
block|{
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|TextureSelectionScreen
argument_list|(
name|this
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
literal|4
condition|)
block|{
name|this
operator|.
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|(
name|GuiScreen
operator|)
literal|null
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|grabMouse
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|5
condition|)
block|{
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|getMinecraftDirectory
argument_list|()
argument_list|,
literal|"/Screenshots/"
argument_list|)
decl_stmt|;
name|file
operator|.
name|mkdirs
argument_list|()
expr_stmt|;
try|try
block|{
name|Desktop
operator|.
name|getDesktop
argument_list|()
operator|.
name|open
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
comment|// TODO Auto-generated catch block
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
if|if
condition|(
name|var1
operator|.
name|id
operator|==
literal|6
condition|)
block|{
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|getMinecraftDirectory
argument_list|()
argument_list|,
literal|"/logs/"
argument_list|)
decl_stmt|;
name|file
operator|.
name|mkdirs
argument_list|()
expr_stmt|;
try|try
block|{
name|Desktop
operator|.
name|getDesktop
argument_list|()
operator|.
name|open
argument_list|(
name|file
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
comment|// TODO Auto-generated catch block
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
block|}
specifier|public
specifier|final
name|void
name|onOpen
parameter_list|()
block|{
name|this
operator|.
name|buttons
operator|.
name|clear
argument_list|()
expr_stmt|;
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|0
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|this
operator|.
name|height
operator|/
literal|4
argument_list|,
literal|"Options..."
argument_list|)
argument_list|)
expr_stmt|;
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|1
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|this
operator|.
name|height
operator|/
literal|4
operator|+
literal|24
argument_list|,
literal|"Generate new level..."
argument_list|)
argument_list|)
expr_stmt|;
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|2
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|this
operator|.
name|height
operator|/
literal|4
operator|+
literal|48
argument_list|,
literal|"Save level.."
argument_list|)
argument_list|)
expr_stmt|;
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|3
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|this
operator|.
name|height
operator|/
literal|4
operator|+
literal|72
argument_list|,
literal|"Change texture pack.."
argument_list|)
argument_list|)
expr_stmt|;
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|4
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|this
operator|.
name|height
operator|/
literal|4
operator|+
literal|120
argument_list|,
literal|"Back to game"
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|minecraft
operator|.
name|session
operator|==
literal|null
condition|)
block|{
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
literal|2
argument_list|)
operator|)
operator|.
name|active
operator|=
literal|true
expr_stmt|;
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
literal|3
argument_list|)
operator|)
operator|.
name|active
operator|=
literal|true
expr_stmt|;
block|}
name|int
name|w
init|=
name|this
operator|.
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"Screenshots..."
argument_list|)
decl_stmt|;
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|5
argument_list|,
name|this
operator|.
name|width
operator|-
name|this
operator|.
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"Screenshots..."
argument_list|)
operator|-
literal|15
argument_list|,
name|this
operator|.
name|height
operator|-
literal|36
argument_list|,
name|this
operator|.
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"Screenshots..."
argument_list|)
argument_list|,
literal|"Screenshots"
argument_list|)
argument_list|)
expr_stmt|;
name|this
operator|.
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|6
argument_list|,
name|this
operator|.
name|width
operator|-
name|w
operator|-
literal|15
argument_list|,
name|this
operator|.
name|height
operator|-
literal|58
argument_list|,
name|w
argument_list|,
literal|"Chat Logs"
argument_list|)
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|minecraft
operator|.
name|networkManager
operator|!=
literal|null
condition|)
block|{
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
literal|1
argument_list|)
operator|)
operator|.
name|active
operator|=
literal|false
expr_stmt|;
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
literal|2
argument_list|)
operator|)
operator|.
name|active
operator|=
literal|true
expr_stmt|;
operator|(
operator|(
name|Button
operator|)
name|this
operator|.
name|buttons
operator|.
name|get
argument_list|(
literal|3
argument_list|)
operator|)
operator|.
name|active
operator|=
literal|true
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|render
parameter_list|(
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
name|drawFadingBox
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
name|this
operator|.
name|width
argument_list|,
name|this
operator|.
name|height
argument_list|,
literal|1610941696
argument_list|,
operator|-
literal|1607454624
argument_list|)
expr_stmt|;
name|drawCenteredString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
literal|"Game menu"
argument_list|,
name|this
operator|.
name|width
operator|/
literal|2
argument_list|,
literal|40
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|drawString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
name|ProgressBarDisplay
operator|.
name|title
argument_list|,
name|this
operator|.
name|width
operator|-
name|this
operator|.
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|ProgressBarDisplay
operator|.
name|title
argument_list|)
operator|-
literal|15
argument_list|,
literal|2
argument_list|,
literal|11835030
argument_list|)
expr_stmt|;
name|drawString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
literal|"ClassiCube 0.1"
argument_list|,
name|this
operator|.
name|width
operator|-
name|this
operator|.
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"ClassiCube 0.1"
argument_list|)
operator|-
literal|15
argument_list|,
literal|13
argument_list|,
literal|13158600
argument_list|)
expr_stmt|;
name|String
name|s
init|=
literal|"Average CPU: "
operator|+
name|this
operator|.
name|minecraft
operator|.
name|monitoringThread
operator|.
name|getAvarageUsagePerCPU
argument_list|()
operator|+
literal|"%"
decl_stmt|;
name|drawString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
name|s
argument_list|,
name|this
operator|.
name|width
operator|-
name|this
operator|.
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|s
argument_list|)
operator|-
literal|15
argument_list|,
literal|24
argument_list|,
literal|9868980
argument_list|)
expr_stmt|;
name|String
name|s2
init|=
literal|"Usage: "
operator|+
name|this
operator|.
name|minecraft
operator|.
name|monitoringThread
operator|.
name|getTotalUsage
argument_list|()
operator|+
literal|"MB"
decl_stmt|;
name|drawString
argument_list|(
name|this
operator|.
name|fontRenderer
argument_list|,
name|s2
argument_list|,
name|this
operator|.
name|width
operator|-
name|this
operator|.
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|s2
argument_list|)
operator|-
literal|15
argument_list|,
literal|35
argument_list|,
literal|9868980
argument_list|)
expr_stmt|;
name|super
operator|.
name|render
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

