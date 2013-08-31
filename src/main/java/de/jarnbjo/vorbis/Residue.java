begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: Residue.java,v 1.3 2003/04/04 08:33:02 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: Residue.java,v $  * Revision 1.3  2003/04/04 08:33:02  jarnbjo  * no message  *  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
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
name|java
operator|.
name|util
operator|.
name|HashMap
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
name|*
import|;
end_import

begin_class
specifier|abstract
class|class
name|Residue
block|{
specifier|protected
name|int
name|begin
decl_stmt|,
name|end
decl_stmt|;
specifier|protected
name|int
name|partitionSize
decl_stmt|;
comment|// grouping
specifier|protected
name|int
name|classifications
decl_stmt|;
comment|// partitions
specifier|protected
name|int
name|classBook
decl_stmt|;
comment|// groupbook
specifier|protected
name|int
index|[]
name|cascade
decl_stmt|;
comment|// secondstages
specifier|protected
name|int
index|[]
index|[]
name|books
decl_stmt|;
specifier|protected
name|HashMap
argument_list|<
name|Mode
argument_list|,
name|Look
argument_list|>
name|looks
init|=
operator|new
name|HashMap
argument_list|<
name|Mode
argument_list|,
name|Look
argument_list|>
argument_list|()
decl_stmt|;
specifier|protected
name|Residue
parameter_list|()
block|{
block|}
specifier|protected
name|Residue
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
name|begin
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|24
argument_list|)
expr_stmt|;
name|end
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|24
argument_list|)
expr_stmt|;
name|partitionSize
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|24
argument_list|)
operator|+
literal|1
expr_stmt|;
name|classifications
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|6
argument_list|)
operator|+
literal|1
expr_stmt|;
name|classBook
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|8
argument_list|)
expr_stmt|;
name|cascade
operator|=
operator|new
name|int
index|[
name|classifications
index|]
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
name|classifications
condition|;
name|i
operator|++
control|)
block|{
name|int
name|highBits
init|=
literal|0
decl_stmt|,
name|lowBits
init|=
literal|0
decl_stmt|;
name|lowBits
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|3
argument_list|)
expr_stmt|;
if|if
condition|(
name|source
operator|.
name|getBit
argument_list|()
condition|)
block|{
name|highBits
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|5
argument_list|)
expr_stmt|;
block|}
name|cascade
index|[
name|i
index|]
operator|=
operator|(
name|highBits
operator|<<
literal|3
operator|)
operator||
name|lowBits
expr_stmt|;
block|}
name|books
operator|=
operator|new
name|int
index|[
name|classifications
index|]
index|[
literal|8
index|]
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
name|classifications
condition|;
name|i
operator|++
control|)
block|{
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
literal|8
condition|;
name|j
operator|++
control|)
block|{
if|if
condition|(
operator|(
name|cascade
index|[
name|i
index|]
operator|&
operator|(
literal|1
operator|<<
name|j
operator|)
operator|)
operator|!=
literal|0
condition|)
block|{
name|books
index|[
name|i
index|]
index|[
name|j
index|]
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|8
argument_list|)
expr_stmt|;
if|if
condition|(
name|books
index|[
name|i
index|]
index|[
name|j
index|]
operator|>
name|header
operator|.
name|getCodeBooks
argument_list|()
operator|.
name|length
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Reference to invalid codebook entry in residue header."
argument_list|)
throw|;
block|}
block|}
block|}
block|}
block|}
specifier|protected
specifier|static
name|Residue
name|createInstance
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
name|int
name|type
init|=
name|source
operator|.
name|getInt
argument_list|(
literal|16
argument_list|)
decl_stmt|;
switch|switch
condition|(
name|type
condition|)
block|{
case|case
literal|0
case|:
comment|//System.out.println("residue type 0");
return|return
operator|new
name|Residue0
argument_list|(
name|source
argument_list|,
name|header
argument_list|)
return|;
case|case
literal|1
case|:
comment|//System.out.println("residue type 1");
return|return
operator|new
name|Residue2
argument_list|(
name|source
argument_list|,
name|header
argument_list|)
return|;
case|case
literal|2
case|:
comment|//System.out.println("residue type 2");
return|return
operator|new
name|Residue2
argument_list|(
name|source
argument_list|,
name|header
argument_list|)
return|;
default|default:
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Residue type "
operator|+
name|type
operator|+
literal|" is not supported."
argument_list|)
throw|;
block|}
block|}
specifier|protected
specifier|abstract
name|int
name|getType
parameter_list|()
function_decl|;
specifier|protected
specifier|abstract
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
function_decl|;
comment|//public abstract double[][] getDecodedVectors();
specifier|protected
name|int
name|getBegin
parameter_list|()
block|{
return|return
name|begin
return|;
block|}
specifier|protected
name|int
name|getEnd
parameter_list|()
block|{
return|return
name|end
return|;
block|}
specifier|protected
name|int
name|getPartitionSize
parameter_list|()
block|{
return|return
name|partitionSize
return|;
block|}
specifier|protected
name|int
name|getClassifications
parameter_list|()
block|{
return|return
name|classifications
return|;
block|}
specifier|protected
name|int
name|getClassBook
parameter_list|()
block|{
return|return
name|classBook
return|;
block|}
specifier|protected
name|int
index|[]
name|getCascade
parameter_list|()
block|{
return|return
name|cascade
return|;
block|}
specifier|protected
name|int
index|[]
index|[]
name|getBooks
parameter_list|()
block|{
return|return
name|books
return|;
block|}
specifier|protected
specifier|final
name|void
name|fill
parameter_list|(
name|Residue
name|clone
parameter_list|)
block|{
name|clone
operator|.
name|begin
operator|=
name|begin
expr_stmt|;
name|clone
operator|.
name|books
operator|=
name|books
expr_stmt|;
name|clone
operator|.
name|cascade
operator|=
name|cascade
expr_stmt|;
name|clone
operator|.
name|classBook
operator|=
name|classBook
expr_stmt|;
name|clone
operator|.
name|classifications
operator|=
name|classifications
expr_stmt|;
name|clone
operator|.
name|end
operator|=
name|end
expr_stmt|;
name|clone
operator|.
name|partitionSize
operator|=
name|partitionSize
expr_stmt|;
block|}
specifier|protected
name|Look
name|getLook
parameter_list|(
name|VorbisStream
name|source
parameter_list|,
name|Mode
name|key
parameter_list|)
block|{
comment|//return new Look(source, key);
name|Look
name|look
init|=
operator|(
name|Look
operator|)
name|looks
operator|.
name|get
argument_list|(
name|key
argument_list|)
decl_stmt|;
if|if
condition|(
name|look
operator|==
literal|null
condition|)
block|{
name|look
operator|=
operator|new
name|Look
argument_list|(
name|source
argument_list|,
name|key
argument_list|)
expr_stmt|;
name|looks
operator|.
name|put
argument_list|(
name|key
argument_list|,
name|look
argument_list|)
expr_stmt|;
block|}
return|return
name|look
return|;
block|}
class|class
name|Look
block|{
name|int
name|map
decl_stmt|;
name|int
name|parts
decl_stmt|;
name|int
name|stages
decl_stmt|;
name|CodeBook
index|[]
name|fullbooks
decl_stmt|;
name|CodeBook
name|phrasebook
decl_stmt|;
name|int
index|[]
index|[]
name|partbooks
decl_stmt|;
name|int
name|partvals
decl_stmt|;
name|int
index|[]
index|[]
name|decodemap
decl_stmt|;
name|int
name|postbits
decl_stmt|;
name|int
name|phrasebits
decl_stmt|;
name|int
name|frames
decl_stmt|;
specifier|protected
name|Look
parameter_list|(
name|VorbisStream
name|source
parameter_list|,
name|Mode
name|mode
parameter_list|)
block|{
name|int
name|dim
init|=
literal|0
decl_stmt|,
name|maxstage
init|=
literal|0
decl_stmt|;
name|map
operator|=
name|mode
operator|.
name|getMapping
argument_list|()
expr_stmt|;
name|parts
operator|=
name|Residue
operator|.
name|this
operator|.
name|getClassifications
argument_list|()
expr_stmt|;
name|fullbooks
operator|=
name|source
operator|.
name|getSetupHeader
argument_list|()
operator|.
name|getCodeBooks
argument_list|()
expr_stmt|;
name|phrasebook
operator|=
name|fullbooks
index|[
name|Residue
operator|.
name|this
operator|.
name|getClassBook
argument_list|()
index|]
expr_stmt|;
name|dim
operator|=
name|phrasebook
operator|.
name|getDimensions
argument_list|()
expr_stmt|;
name|partbooks
operator|=
operator|new
name|int
index|[
name|parts
index|]
index|[]
expr_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|parts
condition|;
name|j
operator|++
control|)
block|{
name|int
name|stages
init|=
name|Util
operator|.
name|ilog
argument_list|(
name|Residue
operator|.
name|this
operator|.
name|getCascade
argument_list|()
index|[
name|j
index|]
argument_list|)
decl_stmt|;
if|if
condition|(
name|stages
operator|!=
literal|0
condition|)
block|{
if|if
condition|(
name|stages
operator|>
name|maxstage
condition|)
block|{
name|maxstage
operator|=
name|stages
expr_stmt|;
block|}
name|partbooks
index|[
name|j
index|]
operator|=
operator|new
name|int
index|[
name|stages
index|]
expr_stmt|;
for|for
control|(
name|int
name|k
init|=
literal|0
init|;
name|k
operator|<
name|stages
condition|;
name|k
operator|++
control|)
block|{
if|if
condition|(
operator|(
name|Residue
operator|.
name|this
operator|.
name|getCascade
argument_list|()
index|[
name|j
index|]
operator|&
operator|(
literal|1
operator|<<
name|k
operator|)
operator|)
operator|!=
literal|0
condition|)
block|{
name|partbooks
index|[
name|j
index|]
index|[
name|k
index|]
operator|=
name|Residue
operator|.
name|this
operator|.
name|getBooks
argument_list|()
index|[
name|j
index|]
index|[
name|k
index|]
expr_stmt|;
block|}
block|}
block|}
block|}
name|partvals
operator|=
operator|(
name|int
operator|)
name|Math
operator|.
name|rint
argument_list|(
name|Math
operator|.
name|pow
argument_list|(
name|parts
argument_list|,
name|dim
argument_list|)
argument_list|)
expr_stmt|;
name|stages
operator|=
name|maxstage
expr_stmt|;
name|decodemap
operator|=
operator|new
name|int
index|[
name|partvals
index|]
index|[]
expr_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|partvals
condition|;
name|j
operator|++
control|)
block|{
name|int
name|val
init|=
name|j
decl_stmt|;
name|int
name|mult
init|=
name|partvals
operator|/
name|parts
decl_stmt|;
name|decodemap
index|[
name|j
index|]
operator|=
operator|new
name|int
index|[
name|dim
index|]
expr_stmt|;
for|for
control|(
name|int
name|k
init|=
literal|0
init|;
name|k
operator|<
name|dim
condition|;
name|k
operator|++
control|)
block|{
name|int
name|deco
init|=
name|val
operator|/
name|mult
decl_stmt|;
name|val
operator|-=
name|deco
operator|*
name|mult
expr_stmt|;
name|mult
operator|/=
name|parts
expr_stmt|;
name|decodemap
index|[
name|j
index|]
index|[
name|k
index|]
operator|=
name|deco
expr_stmt|;
block|}
block|}
block|}
specifier|protected
name|int
index|[]
index|[]
name|getDecodeMap
parameter_list|()
block|{
return|return
name|decodemap
return|;
block|}
specifier|protected
name|int
name|getFrames
parameter_list|()
block|{
return|return
name|frames
return|;
block|}
specifier|protected
name|int
name|getMap
parameter_list|()
block|{
return|return
name|map
return|;
block|}
specifier|protected
name|int
index|[]
index|[]
name|getPartBooks
parameter_list|()
block|{
return|return
name|partbooks
return|;
block|}
specifier|protected
name|int
name|getParts
parameter_list|()
block|{
return|return
name|parts
return|;
block|}
specifier|protected
name|int
name|getPartVals
parameter_list|()
block|{
return|return
name|partvals
return|;
block|}
specifier|protected
name|int
name|getPhraseBits
parameter_list|()
block|{
return|return
name|phrasebits
return|;
block|}
specifier|protected
name|CodeBook
name|getPhraseBook
parameter_list|()
block|{
return|return
name|phrasebook
return|;
block|}
specifier|protected
name|int
name|getPostBits
parameter_list|()
block|{
return|return
name|postbits
return|;
block|}
specifier|protected
name|int
name|getStages
parameter_list|()
block|{
return|return
name|stages
return|;
block|}
block|}
block|}
end_class

end_unit

