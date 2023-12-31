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
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Arrays
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
import|;
end_import

begin_import
import|import
name|javax
operator|.
name|sound
operator|.
name|sampled
operator|.
name|SourceDataLine
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
name|GameSettings
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

begin_comment
comment|// TODO.
end_comment

begin_class
specifier|public
specifier|final
class|class
name|SoundPlayer
implements|implements
name|Runnable
block|{
specifier|public
name|boolean
name|running
init|=
literal|false
decl_stmt|;
specifier|public
name|SourceDataLine
name|dataLine
decl_stmt|;
specifier|public
name|GameSettings
name|settings
decl_stmt|;
specifier|private
name|List
argument_list|<
name|Audio
argument_list|>
name|audioQueue
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
specifier|public
name|SoundPlayer
parameter_list|(
name|GameSettings
name|var1
parameter_list|)
block|{
name|settings
operator|=
name|var1
expr_stmt|;
block|}
specifier|public
name|void
name|clear
parameter_list|()
block|{
name|audioQueue
operator|.
name|clear
argument_list|()
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|play
parameter_list|(
name|Audio
name|var1
parameter_list|)
block|{
if|if
condition|(
name|running
condition|)
block|{
synchronized|synchronized
init|(
name|audioQueue
init|)
block|{
name|audioQueue
operator|.
name|add
argument_list|(
name|var1
argument_list|)
expr_stmt|;
block|}
block|}
block|}
specifier|public
specifier|final
name|void
name|play
parameter_list|(
name|AudioInfo
name|var1
parameter_list|,
name|SoundPos
name|var2
parameter_list|)
block|{
name|this
operator|.
name|play
argument_list|(
operator|new
name|Sound
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|)
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|final
name|void
name|run
parameter_list|()
block|{
name|int
index|[]
name|var1
init|=
operator|new
name|int
index|[
literal|4410
index|]
decl_stmt|;
name|int
index|[]
name|var2
init|=
operator|new
name|int
index|[
literal|4410
index|]
decl_stmt|;
for|for
control|(
name|byte
index|[]
name|var3
init|=
operator|new
name|byte
index|[
literal|17640
index|]
init|;
name|running
condition|;
name|dataLine
operator|.
name|write
argument_list|(
name|var3
argument_list|,
literal|0
argument_list|,
literal|17640
argument_list|)
control|)
block|{
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|1L
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logWarning
argument_list|(
literal|"Error waiting to play a sound"
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
name|Arrays
operator|.
name|fill
argument_list|(
name|var1
argument_list|,
literal|0
argument_list|,
literal|4410
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|Arrays
operator|.
name|fill
argument_list|(
name|var2
argument_list|,
literal|0
argument_list|,
literal|4410
argument_list|,
literal|0
argument_list|)
expr_stmt|;
name|int
index|[]
name|var5
init|=
name|var2
decl_stmt|;
name|int
index|[]
name|var6
init|=
name|var1
decl_stmt|;
synchronized|synchronized
init|(
name|audioQueue
init|)
block|{
name|int
name|i
init|=
literal|0
decl_stmt|;
while|while
condition|(
literal|true
condition|)
block|{
if|if
condition|(
name|i
operator|>=
name|audioQueue
operator|.
name|size
argument_list|()
condition|)
block|{
break|break;
block|}
if|if
condition|(
operator|!
name|audioQueue
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|.
name|play
argument_list|(
name|var6
argument_list|,
name|var5
argument_list|,
literal|4410
argument_list|)
condition|)
block|{
name|audioQueue
operator|.
name|remove
argument_list|(
name|i
operator|--
argument_list|)
expr_stmt|;
block|}
operator|++
name|i
expr_stmt|;
block|}
block|}
name|int
name|i
decl_stmt|;
if|if
condition|(
operator|!
name|settings
operator|.
name|music
operator|&&
operator|!
name|settings
operator|.
name|sound
condition|)
block|{
for|for
control|(
name|i
operator|=
literal|0
init|;
name|i
operator|<
literal|4410
condition|;
operator|++
name|i
control|)
block|{
name|var3
index|[
name|i
operator|<<
literal|2
index|]
operator|=
literal|0
expr_stmt|;
name|var3
index|[
operator|(
name|i
operator|<<
literal|2
operator|)
operator|+
literal|1
index|]
operator|=
literal|0
expr_stmt|;
name|var3
index|[
operator|(
name|i
operator|<<
literal|2
operator|)
operator|+
literal|2
index|]
operator|=
literal|0
expr_stmt|;
name|var3
index|[
operator|(
name|i
operator|<<
literal|2
operator|)
operator|+
literal|3
index|]
operator|=
literal|0
expr_stmt|;
block|}
block|}
else|else
block|{
for|for
control|(
name|i
operator|=
literal|0
init|;
name|i
operator|<
literal|4410
condition|;
operator|++
name|i
control|)
block|{
name|int
name|var15
init|=
name|var1
index|[
name|i
index|]
decl_stmt|;
name|int
name|var14
init|=
name|var2
index|[
name|i
index|]
decl_stmt|;
if|if
condition|(
name|var15
operator|<
operator|-
literal|32000
condition|)
block|{
name|var15
operator|=
operator|-
literal|32000
expr_stmt|;
block|}
if|if
condition|(
name|var14
operator|<
operator|-
literal|32000
condition|)
block|{
name|var14
operator|=
operator|-
literal|32000
expr_stmt|;
block|}
if|if
condition|(
name|var15
operator|>=
literal|32000
condition|)
block|{
name|var15
operator|=
literal|32000
expr_stmt|;
block|}
if|if
condition|(
name|var14
operator|>=
literal|32000
condition|)
block|{
name|var14
operator|=
literal|32000
expr_stmt|;
block|}
name|var3
index|[
name|i
operator|<<
literal|2
index|]
operator|=
operator|(
name|byte
operator|)
operator|(
name|var15
operator|>>
literal|8
operator|)
expr_stmt|;
name|var3
index|[
operator|(
name|i
operator|<<
literal|2
operator|)
operator|+
literal|1
index|]
operator|=
operator|(
name|byte
operator|)
name|var15
expr_stmt|;
name|var3
index|[
operator|(
name|i
operator|<<
literal|2
operator|)
operator|+
literal|2
index|]
operator|=
operator|(
name|byte
operator|)
operator|(
name|var14
operator|>>
literal|8
operator|)
expr_stmt|;
name|var3
index|[
operator|(
name|i
operator|<<
literal|2
operator|)
operator|+
literal|3
index|]
operator|=
operator|(
name|byte
operator|)
name|var14
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

