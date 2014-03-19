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
name|FileNotFoundException
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
name|org
operator|.
name|lwjgl
operator|.
name|opengl
operator|.
name|GL11
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
name|gamemode
operator|.
name|CreativeGameMode
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
name|gamemode
operator|.
name|GameMode
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
name|gamemode
operator|.
name|SurvivalGameMode
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
name|level
operator|.
name|LevelLoader
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|GamemodeScreen
extends|extends
name|GuiScreen
block|{
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
name|id
operator|==
literal|0
condition|)
block|{
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
name|gamemode
operator|=
operator|new
name|SurvivalGameMode
argument_list|(
name|minecraft
argument_list|)
expr_stmt|;
block|}
name|Level
name|level
init|=
literal|null
decl_stmt|;
try|try
block|{
name|level
operator|=
name|loadLevelFromLoader
argument_list|(
name|minecraft
operator|.
name|gamemode
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|FileNotFoundException
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
name|minecraft
operator|.
name|gamemode
operator|.
name|preparePlayer
argument_list|(
name|minecraft
operator|.
name|player
argument_list|)
expr_stmt|;
if|if
condition|(
name|minecraft
operator|.
name|level
operator|!=
literal|null
condition|)
block|{
name|minecraft
operator|.
name|gamemode
operator|.
name|prepareLevel
argument_list|(
name|minecraft
operator|.
name|level
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|gamemode
operator|.
name|apply
argument_list|(
name|minecraft
operator|.
name|level
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|minecraft
operator|.
name|gamemode
operator|.
name|prepareLevel
argument_list|(
name|level
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|gamemode
operator|.
name|apply
argument_list|(
name|level
argument_list|)
expr_stmt|;
block|}
name|minecraft
operator|.
name|gamemode
operator|.
name|apply
argument_list|(
name|minecraft
operator|.
name|player
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|setCurrentScreen
argument_list|(
literal|null
argument_list|)
expr_stmt|;
block|}
specifier|public
name|Level
name|loadLevelFromLoader
parameter_list|(
name|GameMode
name|gamemode
parameter_list|)
throws|throws
name|FileNotFoundException
throws|,
name|IOException
block|{
name|Level
name|var11
init|=
literal|null
decl_stmt|;
if|if
condition|(
name|gamemode
operator|instanceof
name|CreativeGameMode
condition|)
block|{
if|if
condition|(
operator|(
name|var11
operator|=
operator|new
name|LevelLoader
argument_list|()
operator|.
name|load
argument_list|(
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|mcDir
argument_list|,
literal|"levelc.cw"
argument_list|)
argument_list|,
name|minecraft
operator|.
name|player
argument_list|)
operator|)
operator|!=
literal|null
condition|)
block|{
name|minecraft
operator|.
name|progressBar
operator|.
name|setText
argument_list|(
literal|"Loading saved map.."
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|setLevel
argument_list|(
name|var11
argument_list|)
expr_stmt|;
name|Minecraft
operator|.
name|isSinglePlayer
operator|=
literal|true
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|gamemode
operator|instanceof
name|SurvivalGameMode
condition|)
block|{
if|if
condition|(
operator|(
name|var11
operator|=
operator|new
name|LevelLoader
argument_list|()
operator|.
name|load
argument_list|(
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|mcDir
argument_list|,
literal|"levels.cw"
argument_list|)
argument_list|,
name|minecraft
operator|.
name|player
argument_list|)
operator|)
operator|!=
literal|null
condition|)
block|{
name|minecraft
operator|.
name|setLevel
argument_list|(
name|var11
argument_list|)
expr_stmt|;
block|}
block|}
return|return
name|var11
return|;
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
operator|+
literal|72
argument_list|,
literal|"Creative"
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
literal|96
argument_list|,
literal|"Survival"
argument_list|)
argument_list|)
expr_stmt|;
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
literal|1615855616
argument_list|,
operator|-
literal|1602211792
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|2.0F
argument_list|,
literal|2.0F
argument_list|,
literal|2.0F
argument_list|)
expr_stmt|;
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
literal|"Select your game mode"
argument_list|,
name|width
operator|/
literal|2
operator|/
literal|2
argument_list|,
literal|30
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
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

