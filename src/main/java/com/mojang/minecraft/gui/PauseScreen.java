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
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|LogUtil
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

begin_class
specifier|public
specifier|final
class|class
name|PauseScreen
extends|extends
name|GuiScreen
block|{
name|int
name|greenColor
init|=
literal|8454016
decl_stmt|;
name|int
name|orangeColor
init|=
literal|16750160
decl_stmt|;
name|int
name|redColor
init|=
literal|16737380
decl_stmt|;
name|String
name|VersionString
init|=
literal|"0.13"
decl_stmt|;
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
name|minecraft
operator|.
name|session
operator|!=
literal|null
condition|)
block|{
if|if
condition|(
name|button
operator|.
name|id
operator|==
literal|0
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|OptionsScreen
argument_list|(
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
name|SaveLevelScreen
argument_list|(
name|this
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
literal|2
condition|)
block|{
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
name|button
operator|.
name|id
operator|==
literal|3
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
literal|null
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|grabMouse
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|button
operator|.
name|id
operator|==
literal|4
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
name|ex
parameter_list|)
block|{
comment|// TODO: add dialog for fallback
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error opening screenshots folder."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
if|if
condition|(
name|button
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
name|ex
parameter_list|)
block|{
comment|// TODO: add dialog for fallback
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error opening chat logs folder."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
block|}
else|else
block|{
if|if
condition|(
name|button
operator|.
name|id
operator|==
literal|0
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|OptionsScreen
argument_list|(
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
name|GenerateLevelScreen
argument_list|(
name|this
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
literal|2
condition|)
block|{
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
name|button
operator|.
name|id
operator|==
literal|3
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
operator|new
name|LoadLevelScreen
argument_list|(
name|this
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
literal|4
condition|)
block|{
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
name|button
operator|.
name|id
operator|==
literal|5
condition|)
block|{
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
literal|null
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|grabMouse
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|button
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
name|ex
parameter_list|)
block|{
comment|// TODO: add dialog for fallback
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error opening screenshots folder."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
if|if
condition|(
name|button
operator|.
name|id
operator|==
literal|7
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
name|ex
parameter_list|)
block|{
comment|// TODO: add dialog for fallback
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error opening chat logs folder."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|onOpen
parameter_list|()
block|{
name|buttons
operator|.
name|clear
argument_list|()
expr_stmt|;
if|if
condition|(
name|minecraft
operator|.
name|session
operator|!=
literal|null
condition|)
block|{
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|0
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
argument_list|,
literal|"Options..."
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|1
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
operator|+
literal|24
argument_list|,
literal|"Save level..."
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|2
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
operator|+
literal|48
argument_list|,
literal|"Change texture pack..."
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|3
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|-
literal|36
argument_list|,
literal|"Back to game"
argument_list|)
argument_list|)
expr_stmt|;
name|int
name|w
init|=
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"Screenshots..."
argument_list|)
decl_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|4
argument_list|,
name|width
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"Screenshots..."
argument_list|)
operator|-
literal|15
argument_list|,
name|height
operator|-
literal|36
argument_list|,
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
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|5
argument_list|,
name|width
operator|-
name|w
operator|-
literal|15
argument_list|,
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
block|}
else|else
block|{
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|0
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
argument_list|,
literal|"Options..."
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|1
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
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
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|2
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
operator|+
literal|48
argument_list|,
literal|"Save level..."
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|3
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
operator|+
literal|72
argument_list|,
literal|"Load level..."
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|4
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|/
literal|4
operator|+
literal|96
argument_list|,
literal|"Change texture pack..."
argument_list|)
argument_list|)
expr_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|5
argument_list|,
name|width
operator|/
literal|2
operator|-
literal|100
argument_list|,
name|height
operator|-
literal|36
argument_list|,
literal|"Back to game"
argument_list|)
argument_list|)
expr_stmt|;
name|int
name|w
init|=
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"Screenshots..."
argument_list|)
decl_stmt|;
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|6
argument_list|,
name|width
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"Screenshots..."
argument_list|)
operator|-
literal|15
argument_list|,
name|height
operator|-
literal|36
argument_list|,
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
name|buttons
operator|.
name|add
argument_list|(
operator|new
name|Button
argument_list|(
literal|7
argument_list|,
name|width
operator|-
name|w
operator|-
literal|15
argument_list|,
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
block|}
block|}
annotation|@
name|Override
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
name|String
name|titlePrint
init|=
name|ProgressBarDisplay
operator|.
name|title
decl_stmt|;
if|if
condition|(
name|minecraft
operator|.
name|session
operator|==
literal|null
condition|)
block|{
name|titlePrint
operator|=
literal|"Singleplayer"
expr_stmt|;
block|}
name|drawFadingBox
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
name|width
argument_list|,
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
name|fontRenderer
argument_list|,
literal|"Game menu"
argument_list|,
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
name|fontRenderer
argument_list|,
name|titlePrint
argument_list|,
name|width
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|titlePrint
argument_list|)
operator|-
literal|15
argument_list|,
literal|2
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|drawString
argument_list|(
name|fontRenderer
argument_list|,
literal|"ClassiCube "
operator|+
name|VersionString
argument_list|,
name|width
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
literal|"ClassiCube "
operator|+
name|VersionString
argument_list|)
operator|-
literal|15
argument_list|,
literal|13
argument_list|,
literal|14474460
argument_list|)
expr_stmt|;
name|double
name|cpuUsage
init|=
name|minecraft
operator|.
name|monitoringThread
operator|.
name|getAverageUsagePerCPU
argument_list|()
decl_stmt|;
name|double
name|roundedCpuUsage
init|=
name|Math
operator|.
name|round
argument_list|(
name|cpuUsage
operator|*
literal|100.0
argument_list|)
operator|/
literal|100.0
decl_stmt|;
name|int
name|colorToUse
init|=
name|greenColor
decl_stmt|;
if|if
condition|(
name|cpuUsage
operator|>=
literal|21
condition|)
block|{
name|colorToUse
operator|=
name|orangeColor
expr_stmt|;
block|}
if|else if
condition|(
name|cpuUsage
operator|>=
literal|32
condition|)
block|{
name|colorToUse
operator|=
name|redColor
expr_stmt|;
block|}
if|else if
condition|(
name|cpuUsage
operator|<=
literal|20
condition|)
block|{
name|colorToUse
operator|=
name|greenColor
expr_stmt|;
block|}
name|String
name|s
init|=
literal|"Average CPU: "
operator|+
name|roundedCpuUsage
operator|+
literal|"%"
decl_stmt|;
name|drawString
argument_list|(
name|fontRenderer
argument_list|,
name|s
argument_list|,
name|width
operator|-
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
name|colorToUse
argument_list|)
expr_stmt|;
name|long
name|dMem
init|=
name|minecraft
operator|.
name|monitoringThread
operator|.
name|totalMemory
operator|-
name|minecraft
operator|.
name|monitoringThread
operator|.
name|freeMemory
decl_stmt|;
name|float
name|percent
init|=
name|dMem
operator|*
literal|100L
operator|/
name|minecraft
operator|.
name|monitoringThread
operator|.
name|maxMemory
decl_stmt|;
if|if
condition|(
name|percent
operator|>=
literal|75
condition|)
block|{
name|colorToUse
operator|=
name|redColor
expr_stmt|;
block|}
if|else if
condition|(
name|percent
operator|>=
literal|50
condition|)
block|{
name|colorToUse
operator|=
name|orangeColor
expr_stmt|;
block|}
else|else
block|{
name|colorToUse
operator|=
name|greenColor
expr_stmt|;
block|}
name|String
name|Usage
init|=
literal|"Used memory: "
operator|+
name|percent
operator|+
literal|"% ("
operator|+
name|dMem
operator|/
literal|1024L
operator|/
literal|1024L
operator|+
literal|"MB)"
decl_stmt|;
name|drawString
argument_list|(
name|fontRenderer
argument_list|,
name|Usage
argument_list|,
name|width
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|Usage
argument_list|)
operator|-
literal|15
argument_list|,
literal|35
argument_list|,
name|colorToUse
argument_list|)
expr_stmt|;
name|String
name|max
init|=
literal|"Allocated memory: "
operator|+
name|minecraft
operator|.
name|monitoringThread
operator|.
name|maxMemory
operator|/
literal|1024L
operator|/
literal|1024L
operator|+
literal|"MB"
decl_stmt|;
name|drawString
argument_list|(
name|fontRenderer
argument_list|,
name|max
argument_list|,
name|width
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|max
argument_list|)
operator|-
literal|15
argument_list|,
literal|46
argument_list|,
literal|15132260
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

