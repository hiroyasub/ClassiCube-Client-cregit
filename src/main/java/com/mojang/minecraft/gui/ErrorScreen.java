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
name|com
operator|.
name|oyasunadev
operator|.
name|mcraft
operator|.
name|client
operator|.
name|core
operator|.
name|MinecraftStandalone
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|ErrorScreen
extends|extends
name|GuiScreen
block|{
specifier|private
name|String
name|title
decl_stmt|;
specifier|private
name|String
name|text
decl_stmt|;
specifier|public
name|ErrorScreen
parameter_list|(
name|String
name|var1
parameter_list|,
name|String
name|var2
parameter_list|)
block|{
name|title
operator|=
name|var1
expr_stmt|;
name|text
operator|=
name|var2
expr_stmt|;
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
name|id
operator|==
literal|0
condition|)
block|{
name|minecraft
operator|.
name|running
operator|=
literal|false
expr_stmt|;
name|minecraft
operator|.
name|shutdown
argument_list|()
expr_stmt|;
comment|// System.exit(0);
name|MinecraftStandalone
operator|.
name|main
argument_list|(
name|MinecraftStandalone
operator|.
name|storedArgs
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|void
name|onKeyPress
parameter_list|(
name|char
name|var1
parameter_list|,
name|int
name|var2
parameter_list|)
block|{
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
comment|// this.buttons.add(new Button(0, this.width / 2 - 100, this.height / 4
comment|// + 96, this.minecraft
comment|// .isOnline() ? "Try to reconnect..." : "Restart ClassiCube"));
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
operator|-
literal|12574688
argument_list|,
operator|-
literal|11530224
argument_list|)
expr_stmt|;
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
name|title
argument_list|,
name|width
operator|/
literal|2
argument_list|,
literal|90
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|drawCenteredString
argument_list|(
name|fontRenderer
argument_list|,
name|text
argument_list|,
name|width
operator|/
literal|2
argument_list|,
literal|110
argument_list|,
literal|16777215
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

