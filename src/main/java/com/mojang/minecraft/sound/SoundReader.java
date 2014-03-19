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
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|LogUtil
import|;
end_import

begin_import
import|import
name|de
operator|.
name|jarnbjo
operator|.
name|ogg
operator|.
name|LogicalOggStreamImpl
import|;
end_import

begin_import
import|import
name|de
operator|.
name|jarnbjo
operator|.
name|ogg
operator|.
name|OnDemandUrlStream
import|;
end_import

begin_import
import|import
name|de
operator|.
name|jarnbjo
operator|.
name|vorbis
operator|.
name|IdentificationHeader
import|;
end_import

begin_import
import|import
name|de
operator|.
name|jarnbjo
operator|.
name|vorbis
operator|.
name|VorbisStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|URL
import|;
end_import

begin_comment
comment|// TODO.
end_comment

begin_class
specifier|public
specifier|final
class|class
name|SoundReader
block|{
specifier|public
specifier|static
name|SoundData
name|read
parameter_list|(
name|URL
name|var0
parameter_list|)
block|{
name|VorbisStream
name|vorbisStream
init|=
literal|null
decl_stmt|;
try|try
block|{
name|LogicalOggStreamImpl
name|OggStream
init|=
operator|new
name|OnDemandUrlStream
argument_list|(
name|var0
argument_list|)
operator|.
name|getLogicalStreams
argument_list|()
operator|.
name|iterator
argument_list|()
operator|.
name|next
argument_list|()
decl_stmt|;
name|vorbisStream
operator|=
operator|new
name|VorbisStream
argument_list|(
name|OggStream
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logWarning
argument_list|(
literal|"Error loading sound stream from "
operator|+
name|var0
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
name|byte
index|[]
name|var2
init|=
operator|new
name|byte
index|[
literal|4096
index|]
decl_stmt|;
name|int
name|var3
init|=
literal|0
decl_stmt|;
name|int
name|var4
init|=
name|vorbisStream
operator|.
name|getIdentificationHeader
argument_list|()
operator|.
name|getChannels
argument_list|()
decl_stmt|;
name|short
index|[]
name|var5
init|=
operator|new
name|short
index|[
literal|4096
index|]
decl_stmt|;
name|int
name|var6
init|=
literal|0
decl_stmt|;
name|label51
label|:
while|while
condition|(
name|var3
operator|>=
literal|0
condition|)
block|{
name|int
name|var15
init|=
literal|0
decl_stmt|;
while|while
condition|(
literal|true
condition|)
block|{
try|try
block|{
if|if
condition|(
name|var15
operator|<
name|var2
operator|.
name|length
operator|&&
operator|(
name|var3
operator|=
name|vorbisStream
operator|.
name|readPcm
argument_list|(
name|var2
argument_list|,
name|var15
argument_list|,
name|var2
operator|.
name|length
operator|-
name|var15
argument_list|)
operator|)
operator|>
literal|0
condition|)
block|{
name|var15
operator|+=
name|var3
expr_stmt|;
continue|continue;
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|var10
parameter_list|)
block|{
name|var3
operator|=
operator|-
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|var15
operator|<=
literal|0
condition|)
block|{
break|break;
block|}
name|int
name|var16
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
name|var16
operator|>=
name|var15
condition|)
block|{
continue|continue
name|label51
continue|;
block|}
name|int
name|var8
init|=
literal|0
decl_stmt|;
for|for
control|(
name|int
name|var9
init|=
literal|0
init|;
name|var9
operator|<
name|var4
condition|;
operator|++
name|var9
control|)
block|{
name|var8
operator|+=
name|var2
index|[
name|var16
operator|++
index|]
operator|<<
literal|8
operator||
name|var2
index|[
name|var16
operator|++
index|]
operator|&
literal|255
expr_stmt|;
block|}
if|if
condition|(
name|var6
operator|==
name|var5
operator|.
name|length
condition|)
block|{
name|short
index|[]
name|var18
init|=
name|var5
decl_stmt|;
name|var5
operator|=
operator|new
name|short
index|[
name|var5
operator|.
name|length
operator|<<
literal|1
index|]
expr_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|var18
argument_list|,
literal|0
argument_list|,
name|var5
argument_list|,
literal|0
argument_list|,
name|var6
argument_list|)
expr_stmt|;
block|}
name|var5
index|[
name|var6
operator|++
index|]
operator|=
operator|(
name|short
operator|)
operator|(
name|var8
operator|/
name|var4
operator|)
expr_stmt|;
block|}
block|}
block|}
if|if
condition|(
name|var6
operator|!=
name|var5
operator|.
name|length
condition|)
block|{
name|short
index|[]
name|var17
init|=
name|var5
decl_stmt|;
name|var5
operator|=
operator|new
name|short
index|[
name|var6
index|]
expr_stmt|;
name|System
operator|.
name|arraycopy
argument_list|(
name|var17
argument_list|,
literal|0
argument_list|,
name|var5
argument_list|,
literal|0
argument_list|,
name|var6
argument_list|)
expr_stmt|;
block|}
annotation|@
name|SuppressWarnings
argument_list|(
literal|"unused"
argument_list|)
name|IdentificationHeader
name|var13
decl_stmt|;
return|return
operator|new
name|SoundData
argument_list|(
name|var5
argument_list|,
operator|(
name|var13
operator|=
name|vorbisStream
operator|.
name|getIdentificationHeader
argument_list|()
operator|)
operator|.
name|getSampleRate
argument_list|()
argument_list|)
return|;
block|}
block|}
end_class

end_unit

