begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: Residue2.java,v 1.2 2003/03/16 01:11:12 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: Residue2.java,v $  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
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
name|util
operator|.
name|io
operator|.
name|BitInputStream
import|;
end_import

begin_class
class|class
name|Residue2
extends|extends
name|Residue
block|{
specifier|private
name|double
index|[]
index|[]
name|decodedVectors
decl_stmt|;
specifier|private
name|Residue2
parameter_list|()
block|{
block|}
specifier|protected
name|Residue2
parameter_list|(
name|BitInputStream
name|source
parameter_list|,
name|SetupHeader
name|header
parameter_list|)
throws|throws
name|VorbisFormatException
throws|,
name|IOException
block|{
name|super
argument_list|(
name|source
argument_list|,
name|header
argument_list|)
expr_stmt|;
block|}
specifier|public
name|Object
name|clone
parameter_list|()
block|{
name|Residue2
name|clone
init|=
operator|new
name|Residue2
argument_list|()
decl_stmt|;
name|fill
argument_list|(
name|clone
argument_list|)
expr_stmt|;
return|return
name|clone
return|;
block|}
specifier|protected
name|void
name|decodeResidue
parameter_list|(
name|VorbisStream
name|vorbis
parameter_list|,
name|BitInputStream
name|source
parameter_list|,
name|Mode
name|mode
parameter_list|,
name|int
name|ch
parameter_list|,
name|boolean
index|[]
name|doNotDecodeFlags
parameter_list|,
name|float
index|[]
index|[]
name|vectors
parameter_list|)
throws|throws
name|VorbisFormatException
throws|,
name|IOException
block|{
name|Look
name|look
init|=
name|getLook
argument_list|(
name|vorbis
argument_list|,
name|mode
argument_list|)
decl_stmt|;
name|int
name|nToRead
init|=
name|getEnd
argument_list|()
operator|-
name|getBegin
argument_list|()
decl_stmt|;
name|int
name|partitionsToRead
init|=
name|nToRead
operator|/
name|getPartitionSize
argument_list|()
decl_stmt|;
comment|// partvals
name|int
name|samplesPerPartition
init|=
name|getPartitionSize
argument_list|()
decl_stmt|;
name|int
name|partitionsPerWord
init|=
name|look
operator|.
name|getPhraseBook
argument_list|()
operator|.
name|getDimensions
argument_list|()
decl_stmt|;
name|int
name|partWords
init|=
operator|(
name|partitionsToRead
operator|+
name|partitionsPerWord
operator|-
literal|1
operator|)
operator|/
name|partitionsPerWord
decl_stmt|;
name|int
name|realCh
init|=
literal|0
decl_stmt|;
for|for
control|(
name|boolean
name|doNotDecodeFlag
range|:
name|doNotDecodeFlags
control|)
block|{
if|if
condition|(
operator|!
name|doNotDecodeFlag
condition|)
block|{
name|realCh
operator|++
expr_stmt|;
block|}
block|}
name|float
index|[]
index|[]
name|realVectors
init|=
operator|new
name|float
index|[
name|realCh
index|]
index|[]
decl_stmt|;
name|realCh
operator|=
literal|0
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
name|doNotDecodeFlags
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
operator|!
name|doNotDecodeFlags
index|[
name|i
index|]
condition|)
block|{
name|realVectors
index|[
name|realCh
operator|++
index|]
operator|=
name|vectors
index|[
name|i
index|]
expr_stmt|;
block|}
block|}
name|int
index|[]
index|[]
name|partword
init|=
operator|new
name|int
index|[
name|partWords
index|]
index|[]
decl_stmt|;
for|for
control|(
name|int
name|s
init|=
literal|0
init|;
name|s
operator|<
name|look
operator|.
name|getStages
argument_list|()
condition|;
name|s
operator|++
control|)
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|,
name|l
init|=
literal|0
init|;
name|i
operator|<
name|partitionsToRead
condition|;
name|l
operator|++
control|)
block|{
if|if
condition|(
name|s
operator|==
literal|0
condition|)
block|{
comment|// int temp=look.getPhraseBook().readInt(source);
name|int
name|temp
init|=
name|source
operator|.
name|getInt
argument_list|(
name|look
operator|.
name|getPhraseBook
argument_list|()
operator|.
name|getHuffmanRoot
argument_list|()
argument_list|)
decl_stmt|;
if|if
condition|(
name|temp
operator|==
operator|-
literal|1
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|""
argument_list|)
throw|;
block|}
name|partword
index|[
name|l
index|]
operator|=
name|look
operator|.
name|getDecodeMap
argument_list|()
index|[
name|temp
index|]
expr_stmt|;
if|if
condition|(
name|partword
index|[
name|l
index|]
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|""
argument_list|)
throw|;
block|}
block|}
for|for
control|(
name|int
name|k
init|=
literal|0
init|;
name|k
operator|<
name|partitionsPerWord
operator|&&
name|i
operator|<
name|partitionsToRead
condition|;
name|k
operator|++
operator|,
name|i
operator|++
control|)
block|{
name|int
name|offset
init|=
name|begin
operator|+
name|i
operator|*
name|samplesPerPartition
decl_stmt|;
if|if
condition|(
operator|(
name|cascade
index|[
name|partword
index|[
name|l
index|]
index|[
name|k
index|]
index|]
operator|&
operator|(
literal|1
operator|<<
name|s
operator|)
operator|)
operator|!=
literal|0
condition|)
block|{
name|CodeBook
name|stagebook
init|=
name|vorbis
operator|.
name|getSetupHeader
argument_list|()
operator|.
name|getCodeBooks
argument_list|()
index|[
name|look
operator|.
name|getPartBooks
argument_list|()
index|[
name|partword
index|[
name|l
index|]
index|[
name|k
index|]
index|]
index|[
name|s
index|]
index|]
decl_stmt|;
if|if
condition|(
name|stagebook
operator|!=
literal|null
condition|)
block|{
name|stagebook
operator|.
name|readVvAdd
argument_list|(
name|realVectors
argument_list|,
name|source
argument_list|,
name|offset
argument_list|,
name|samplesPerPartition
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
block|}
specifier|protected
name|double
index|[]
index|[]
name|getDecodedVectors
parameter_list|()
block|{
return|return
name|decodedVectors
return|;
block|}
specifier|protected
name|int
name|getType
parameter_list|()
block|{
return|return
literal|2
return|;
block|}
block|}
end_class

end_unit

