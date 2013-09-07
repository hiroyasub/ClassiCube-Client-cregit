begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|sound
package|;
end_package

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|ByteBuffer
import|;
end_import

begin_comment
comment|// TODO.
end_comment

begin_class
specifier|final
class|class
name|MusicPlayThread
extends|extends
name|Thread
block|{
comment|// $FF: synthetic field
specifier|private
name|Music
name|music
decl_stmt|;
specifier|public
name|MusicPlayThread
parameter_list|(
name|Music
name|var1
parameter_list|)
block|{
name|super
argument_list|()
expr_stmt|;
name|this
operator|.
name|music
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|setPriority
argument_list|(
literal|10
argument_list|)
expr_stmt|;
name|this
operator|.
name|setDaemon
argument_list|(
literal|true
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|run
parameter_list|()
block|{
try|try
block|{
do|do
block|{
if|if
condition|(
name|this
operator|.
name|music
operator|.
name|stopped
condition|)
block|{
return|return;
block|}
name|ByteBuffer
name|var2
decl_stmt|;
if|if
condition|(
name|this
operator|.
name|music
operator|.
name|playing
operator|==
literal|null
operator|&&
name|this
operator|.
name|music
operator|.
name|current
operator|!=
literal|null
condition|)
block|{
name|var2
operator|=
name|this
operator|.
name|music
operator|.
name|current
expr_stmt|;
name|this
operator|.
name|music
operator|.
name|playing
operator|=
name|var2
expr_stmt|;
name|var2
operator|=
literal|null
expr_stmt|;
name|this
operator|.
name|music
operator|.
name|current
operator|=
literal|null
expr_stmt|;
name|this
operator|.
name|music
operator|.
name|playing
operator|.
name|clear
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|music
operator|.
name|playing
operator|!=
literal|null
operator|&&
name|this
operator|.
name|music
operator|.
name|playing
operator|.
name|remaining
argument_list|()
operator|!=
literal|0
condition|)
block|{
while|while
condition|(
literal|true
condition|)
block|{
if|if
condition|(
name|this
operator|.
name|music
operator|.
name|playing
operator|.
name|remaining
argument_list|()
operator|==
literal|0
condition|)
block|{
break|break;
block|}
name|var2
operator|=
name|this
operator|.
name|music
operator|.
name|playing
expr_stmt|;
name|int
name|var10
init|=
name|this
operator|.
name|music
operator|.
name|stream
operator|.
name|readPcm
argument_list|(
name|var2
operator|.
name|array
argument_list|()
argument_list|,
name|var2
operator|.
name|position
argument_list|()
argument_list|,
name|var2
operator|.
name|remaining
argument_list|()
argument_list|)
decl_stmt|;
name|var2
operator|.
name|position
argument_list|(
name|var2
operator|.
name|position
argument_list|()
operator|+
name|var10
argument_list|)
expr_stmt|;
if|if
condition|(
name|var10
operator|<=
literal|0
condition|)
block|{
name|this
operator|.
name|music
operator|.
name|finished
operator|=
literal|true
expr_stmt|;
name|this
operator|.
name|music
operator|.
name|stopped
operator|=
literal|true
expr_stmt|;
break|break;
block|}
block|}
block|}
if|if
condition|(
name|this
operator|.
name|music
operator|.
name|playing
operator|!=
literal|null
operator|&&
name|this
operator|.
name|music
operator|.
name|previous
operator|==
literal|null
condition|)
block|{
name|this
operator|.
name|music
operator|.
name|playing
operator|.
name|flip
argument_list|()
expr_stmt|;
name|var2
operator|=
name|this
operator|.
name|music
operator|.
name|playing
expr_stmt|;
name|this
operator|.
name|music
operator|.
name|previous
operator|=
name|var2
expr_stmt|;
name|var2
operator|=
literal|null
expr_stmt|;
name|this
operator|.
name|music
operator|.
name|playing
operator|=
name|var2
expr_stmt|;
block|}
name|Thread
operator|.
name|sleep
argument_list|(
literal|10L
argument_list|)
expr_stmt|;
block|}
do|while
condition|(
name|this
operator|.
name|music
operator|.
name|player
operator|.
name|running
condition|)
do|;
return|return;
block|}
catch|catch
parameter_list|(
name|Exception
name|var7
parameter_list|)
block|{
name|var7
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
return|return;
block|}
finally|finally
block|{
name|this
operator|.
name|music
operator|.
name|finished
operator|=
literal|true
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

