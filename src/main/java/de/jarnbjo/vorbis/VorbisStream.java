begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: VorbisStream.java,v 1.4 2003/04/10 19:49:04 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: VorbisStream.java,v $  * Revision 1.4  2003/04/10 19:49:04  jarnbjo  * no message  *  * Revision 1.3  2003/03/31 00:20:16  jarnbjo  * no message  *  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
end_comment

begin_package
package|package
name|de
operator|.
name|jarnbjo
operator|.
name|vorbis
package|;
end_package

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
name|de
operator|.
name|jarnbjo
operator|.
name|ogg
operator|.
name|LogicalOggStream
import|;
end_import

begin_import
import|import
name|de
operator|.
name|jarnbjo
operator|.
name|util
operator|.
name|io
operator|.
name|BitInputStream
import|;
end_import

begin_import
import|import
name|de
operator|.
name|jarnbjo
operator|.
name|util
operator|.
name|io
operator|.
name|ByteArrayBitInputStream
import|;
end_import

begin_comment
comment|/**  */
end_comment

begin_class
specifier|public
class|class
name|VorbisStream
block|{
specifier|private
name|LogicalOggStream
name|oggStream
decl_stmt|;
specifier|private
name|IdentificationHeader
name|identificationHeader
decl_stmt|;
specifier|private
name|CommentHeader
name|commentHeader
decl_stmt|;
specifier|private
name|SetupHeader
name|setupHeader
decl_stmt|;
specifier|private
name|AudioPacket
name|lastAudioPacket
decl_stmt|;
specifier|private
name|byte
index|[]
name|currentPcm
decl_stmt|;
specifier|private
name|int
name|currentPcmIndex
decl_stmt|;
specifier|private
name|int
name|currentPcmLimit
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|IDENTIFICATION_HEADER
init|=
literal|1
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|COMMENT_HEADER
init|=
literal|3
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|SETUP_HEADER
init|=
literal|5
decl_stmt|;
specifier|private
name|Object
name|streamLock
init|=
operator|new
name|Object
argument_list|()
decl_stmt|;
specifier|private
name|int
name|currentBitRate
init|=
literal|0
decl_stmt|;
specifier|private
name|long
name|currentGranulePosition
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|BIG_ENDIAN
init|=
literal|0
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|int
name|LITTLE_ENDIAN
init|=
literal|1
decl_stmt|;
specifier|public
name|VorbisStream
parameter_list|()
block|{
block|}
specifier|public
name|VorbisStream
parameter_list|(
name|LogicalOggStream
name|oggStream
parameter_list|)
throws|throws
name|VorbisFormatException
throws|,
name|IOException
block|{
name|this
operator|.
name|oggStream
operator|=
name|oggStream
expr_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
literal|3
condition|;
name|i
operator|++
control|)
block|{
name|BitInputStream
name|source
init|=
operator|new
name|ByteArrayBitInputStream
argument_list|(
name|oggStream
operator|.
name|getNextOggPacket
argument_list|()
argument_list|)
decl_stmt|;
name|int
name|headerType
init|=
name|source
operator|.
name|getInt
argument_list|(
literal|8
argument_list|)
decl_stmt|;
switch|switch
condition|(
name|headerType
condition|)
block|{
case|case
name|IDENTIFICATION_HEADER
case|:
name|identificationHeader
operator|=
operator|new
name|IdentificationHeader
argument_list|(
name|source
argument_list|)
expr_stmt|;
break|break;
case|case
name|COMMENT_HEADER
case|:
name|commentHeader
operator|=
operator|new
name|CommentHeader
argument_list|(
name|source
argument_list|)
expr_stmt|;
break|break;
case|case
name|SETUP_HEADER
case|:
name|setupHeader
operator|=
operator|new
name|SetupHeader
argument_list|(
name|this
argument_list|,
name|source
argument_list|)
expr_stmt|;
break|break;
block|}
block|}
if|if
condition|(
name|identificationHeader
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"The file has no identification header."
argument_list|)
throw|;
block|}
if|if
condition|(
name|commentHeader
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"The file has no commentHeader."
argument_list|)
throw|;
block|}
if|if
condition|(
name|setupHeader
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"The file has no setup header."
argument_list|)
throw|;
block|}
comment|// currentPcm=new int[identificationHeader.getChannels()][16384];
name|currentPcm
operator|=
operator|new
name|byte
index|[
name|identificationHeader
operator|.
name|getChannels
argument_list|()
operator|*
name|identificationHeader
operator|.
name|getBlockSize1
argument_list|()
operator|*
literal|2
index|]
expr_stmt|;
comment|// new BufferThread().start();
block|}
specifier|public
name|void
name|close
parameter_list|()
throws|throws
name|IOException
block|{
name|oggStream
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
specifier|public
name|CommentHeader
name|getCommentHeader
parameter_list|()
block|{
return|return
name|commentHeader
return|;
block|}
specifier|public
name|int
name|getCurrentBitRate
parameter_list|()
block|{
return|return
name|currentBitRate
return|;
block|}
specifier|public
name|long
name|getCurrentGranulePosition
parameter_list|()
block|{
return|return
name|currentGranulePosition
return|;
block|}
specifier|public
name|IdentificationHeader
name|getIdentificationHeader
parameter_list|()
block|{
return|return
name|identificationHeader
return|;
block|}
specifier|private
name|AudioPacket
name|getNextAudioPacket
parameter_list|()
throws|throws
name|VorbisFormatException
throws|,
name|IOException
block|{
name|byte
index|[]
name|data
init|=
name|oggStream
operator|.
name|getNextOggPacket
argument_list|()
decl_stmt|;
name|AudioPacket
name|res
init|=
literal|null
decl_stmt|;
while|while
condition|(
name|res
operator|==
literal|null
condition|)
block|{
try|try
block|{
name|res
operator|=
operator|new
name|AudioPacket
argument_list|(
name|this
argument_list|,
operator|new
name|ByteArrayBitInputStream
argument_list|(
name|data
argument_list|)
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|ArrayIndexOutOfBoundsException
name|e
parameter_list|)
block|{
comment|// ignore and continue with next packet
block|}
block|}
name|currentGranulePosition
operator|+=
name|res
operator|.
name|getNumberOfSamples
argument_list|()
expr_stmt|;
name|currentBitRate
operator|=
name|data
operator|.
name|length
operator|*
literal|8
operator|*
name|identificationHeader
operator|.
name|getSampleRate
argument_list|()
operator|/
name|res
operator|.
name|getNumberOfSamples
argument_list|()
expr_stmt|;
return|return
name|res
return|;
block|}
specifier|protected
name|SetupHeader
name|getSetupHeader
parameter_list|()
block|{
return|return
name|setupHeader
return|;
block|}
specifier|public
name|boolean
name|isOpen
parameter_list|()
block|{
return|return
name|oggStream
operator|.
name|isOpen
argument_list|()
return|;
block|}
specifier|public
name|byte
index|[]
name|processPacket
parameter_list|(
name|byte
index|[]
name|packet
parameter_list|)
throws|throws
name|VorbisFormatException
throws|,
name|IOException
block|{
if|if
condition|(
name|packet
operator|.
name|length
operator|==
literal|0
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Cannot decode a vorbis packet with length = 0"
argument_list|)
throw|;
block|}
if|if
condition|(
operator|(
operator|(
name|int
operator|)
name|packet
index|[
literal|0
index|]
operator|&
literal|1
operator|)
operator|==
literal|1
condition|)
block|{
comment|// header packet
name|BitInputStream
name|source
init|=
operator|new
name|ByteArrayBitInputStream
argument_list|(
name|packet
argument_list|)
decl_stmt|;
switch|switch
condition|(
name|source
operator|.
name|getInt
argument_list|(
literal|8
argument_list|)
condition|)
block|{
case|case
name|IDENTIFICATION_HEADER
case|:
name|identificationHeader
operator|=
operator|new
name|IdentificationHeader
argument_list|(
name|source
argument_list|)
expr_stmt|;
break|break;
case|case
name|COMMENT_HEADER
case|:
name|commentHeader
operator|=
operator|new
name|CommentHeader
argument_list|(
name|source
argument_list|)
expr_stmt|;
break|break;
case|case
name|SETUP_HEADER
case|:
name|setupHeader
operator|=
operator|new
name|SetupHeader
argument_list|(
name|this
argument_list|,
name|source
argument_list|)
expr_stmt|;
break|break;
block|}
return|return
literal|null
return|;
block|}
else|else
block|{
comment|// audio packet
if|if
condition|(
name|identificationHeader
operator|==
literal|null
operator|||
name|commentHeader
operator|==
literal|null
operator|||
name|setupHeader
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Cannot decode audio packet before all three header packets have been decoded."
argument_list|)
throw|;
block|}
name|AudioPacket
name|ap
init|=
operator|new
name|AudioPacket
argument_list|(
name|this
argument_list|,
operator|new
name|ByteArrayBitInputStream
argument_list|(
name|packet
argument_list|)
argument_list|)
decl_stmt|;
name|currentGranulePosition
operator|+=
name|ap
operator|.
name|getNumberOfSamples
argument_list|()
expr_stmt|;
if|if
condition|(
name|lastAudioPacket
operator|==
literal|null
condition|)
block|{
name|lastAudioPacket
operator|=
name|ap
expr_stmt|;
return|return
literal|null
return|;
block|}
name|byte
index|[]
name|res
init|=
operator|new
name|byte
index|[
name|identificationHeader
operator|.
name|getChannels
argument_list|()
operator|*
name|ap
operator|.
name|getNumberOfSamples
argument_list|()
operator|*
literal|2
index|]
decl_stmt|;
try|try
block|{
name|ap
operator|.
name|getPcm
argument_list|(
name|lastAudioPacket
argument_list|,
name|res
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IndexOutOfBoundsException
name|e
parameter_list|)
block|{
name|java
operator|.
name|util
operator|.
name|Arrays
operator|.
name|fill
argument_list|(
name|res
argument_list|,
operator|(
name|byte
operator|)
literal|0
argument_list|)
expr_stmt|;
block|}
name|lastAudioPacket
operator|=
name|ap
expr_stmt|;
return|return
name|res
return|;
block|}
block|}
specifier|public
name|int
name|readPcm
parameter_list|(
name|byte
index|[]
name|buffer
parameter_list|,
name|int
name|offset
parameter_list|,
name|int
name|length
parameter_list|)
throws|throws
name|IOException
block|{
synchronized|synchronized
init|(
name|streamLock
init|)
block|{
name|identificationHeader
operator|.
name|getChannels
argument_list|()
expr_stmt|;
if|if
condition|(
name|lastAudioPacket
operator|==
literal|null
condition|)
block|{
name|lastAudioPacket
operator|=
name|getNextAudioPacket
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|currentPcm
operator|==
literal|null
operator|||
name|currentPcmIndex
operator|>=
name|currentPcmLimit
condition|)
block|{
name|AudioPacket
name|ap
init|=
name|getNextAudioPacket
argument_list|()
decl_stmt|;
try|try
block|{
name|ap
operator|.
name|getPcm
argument_list|(
name|lastAudioPacket
argument_list|,
name|currentPcm
argument_list|)
expr_stmt|;
name|currentPcmLimit
operator|=
name|ap
operator|.
name|getNumberOfSamples
argument_list|()
operator|*
name|identificationHeader
operator|.
name|getChannels
argument_list|()
operator|*
literal|2
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|ArrayIndexOutOfBoundsException
name|e
parameter_list|)
block|{
return|return
literal|0
return|;
block|}
name|currentPcmIndex
operator|=
literal|0
expr_stmt|;
name|lastAudioPacket
operator|=
name|ap
expr_stmt|;
block|}
name|int
name|written
init|=
literal|0
decl_stmt|;
name|int
name|i
init|=
literal|0
decl_stmt|;
name|int
name|arrIx
init|=
literal|0
decl_stmt|;
for|for
control|(
name|i
operator|=
name|currentPcmIndex
init|;
name|i
operator|<
name|currentPcmLimit
operator|&&
name|arrIx
operator|<
name|length
condition|;
name|i
operator|++
control|)
block|{
name|buffer
index|[
name|offset
operator|+
name|arrIx
operator|++
index|]
operator|=
name|currentPcm
index|[
name|i
index|]
expr_stmt|;
name|written
operator|++
expr_stmt|;
block|}
name|currentPcmIndex
operator|=
name|i
expr_stmt|;
return|return
name|written
return|;
block|}
block|}
block|}
end_class

end_unit

