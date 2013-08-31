begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: CodeBook.java,v 1.3 2003/04/10 19:49:04 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: CodeBook.java,v $  * Revision 1.3  2003/04/10 19:49:04  jarnbjo  * no message  *  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
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
name|*
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|*
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
class|class
name|CodeBook
block|{
specifier|private
name|HuffmanNode
name|huffmanRoot
decl_stmt|;
specifier|private
name|int
name|dimensions
decl_stmt|,
name|entries
decl_stmt|;
specifier|private
name|int
index|[]
name|entryLengths
decl_stmt|;
specifier|private
name|float
index|[]
index|[]
name|valueVector
decl_stmt|;
specifier|protected
name|CodeBook
parameter_list|(
name|BitInputStream
name|source
parameter_list|)
throws|throws
name|VorbisFormatException
throws|,
name|IOException
block|{
comment|// check sync
if|if
condition|(
name|source
operator|.
name|getInt
argument_list|(
literal|24
argument_list|)
operator|!=
literal|0x564342
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"The code book sync pattern is not correct."
argument_list|)
throw|;
block|}
name|dimensions
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|16
argument_list|)
expr_stmt|;
name|entries
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|24
argument_list|)
expr_stmt|;
name|entryLengths
operator|=
operator|new
name|int
index|[
name|entries
index|]
expr_stmt|;
name|boolean
name|ordered
init|=
name|source
operator|.
name|getBit
argument_list|()
decl_stmt|;
if|if
condition|(
name|ordered
condition|)
block|{
name|int
name|cl
init|=
name|source
operator|.
name|getInt
argument_list|(
literal|5
argument_list|)
operator|+
literal|1
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|entryLengths
operator|.
name|length
condition|;
control|)
block|{
name|int
name|num
init|=
name|source
operator|.
name|getInt
argument_list|(
name|Util
operator|.
name|ilog
argument_list|(
name|entryLengths
operator|.
name|length
operator|-
name|i
argument_list|)
argument_list|)
decl_stmt|;
if|if
condition|(
name|i
operator|+
name|num
operator|>
name|entryLengths
operator|.
name|length
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"The codebook entry length list is longer than the actual number of entry lengths."
argument_list|)
throw|;
block|}
name|Arrays
operator|.
name|fill
argument_list|(
name|entryLengths
argument_list|,
name|i
argument_list|,
name|i
operator|+
name|num
argument_list|,
name|cl
argument_list|)
expr_stmt|;
name|cl
operator|++
expr_stmt|;
name|i
operator|+=
name|num
expr_stmt|;
block|}
block|}
else|else
block|{
comment|// !ordered
name|boolean
name|sparse
init|=
name|source
operator|.
name|getBit
argument_list|()
decl_stmt|;
if|if
condition|(
name|sparse
condition|)
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|entryLengths
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|source
operator|.
name|getBit
argument_list|()
condition|)
block|{
name|entryLengths
index|[
name|i
index|]
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|5
argument_list|)
operator|+
literal|1
expr_stmt|;
block|}
else|else
block|{
name|entryLengths
index|[
name|i
index|]
operator|=
operator|-
literal|1
expr_stmt|;
block|}
block|}
block|}
else|else
block|{
comment|// !sparse
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|entryLengths
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|entryLengths
index|[
name|i
index|]
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|5
argument_list|)
operator|+
literal|1
expr_stmt|;
block|}
block|}
block|}
if|if
condition|(
operator|!
name|createHuffmanTree
argument_list|(
name|entryLengths
argument_list|)
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"An exception was thrown when building the codebook Huffman tree."
argument_list|)
throw|;
block|}
name|int
name|codeBookLookupType
init|=
name|source
operator|.
name|getInt
argument_list|(
literal|4
argument_list|)
decl_stmt|;
switch|switch
condition|(
name|codeBookLookupType
condition|)
block|{
case|case
literal|0
case|:
comment|// codebook has no scalar vectors to be calculated
break|break;
case|case
literal|1
case|:
case|case
literal|2
case|:
name|float
name|codeBookMinimumValue
init|=
name|Util
operator|.
name|float32unpack
argument_list|(
name|source
operator|.
name|getInt
argument_list|(
literal|32
argument_list|)
argument_list|)
decl_stmt|;
name|float
name|codeBookDeltaValue
init|=
name|Util
operator|.
name|float32unpack
argument_list|(
name|source
operator|.
name|getInt
argument_list|(
literal|32
argument_list|)
argument_list|)
decl_stmt|;
name|int
name|codeBookValueBits
init|=
name|source
operator|.
name|getInt
argument_list|(
literal|4
argument_list|)
operator|+
literal|1
decl_stmt|;
name|boolean
name|codeBookSequenceP
init|=
name|source
operator|.
name|getBit
argument_list|()
decl_stmt|;
name|int
name|codeBookLookupValues
init|=
literal|0
decl_stmt|;
if|if
condition|(
name|codeBookLookupType
operator|==
literal|1
condition|)
block|{
name|codeBookLookupValues
operator|=
name|Util
operator|.
name|lookup1Values
argument_list|(
name|entries
argument_list|,
name|dimensions
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|codeBookLookupValues
operator|=
name|entries
operator|*
name|dimensions
expr_stmt|;
block|}
name|int
name|codeBookMultiplicands
index|[]
init|=
operator|new
name|int
index|[
name|codeBookLookupValues
index|]
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|codeBookMultiplicands
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|codeBookMultiplicands
index|[
name|i
index|]
operator|=
name|source
operator|.
name|getInt
argument_list|(
name|codeBookValueBits
argument_list|)
expr_stmt|;
block|}
name|valueVector
operator|=
operator|new
name|float
index|[
name|entries
index|]
index|[
name|dimensions
index|]
expr_stmt|;
if|if
condition|(
name|codeBookLookupType
operator|==
literal|1
condition|)
block|{
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|entries
condition|;
name|i
operator|++
control|)
block|{
name|float
name|last
init|=
literal|0
decl_stmt|;
name|int
name|indexDivisor
init|=
literal|1
decl_stmt|;
for|for
control|(
name|int
name|j
init|=
literal|0
init|;
name|j
operator|<
name|dimensions
condition|;
name|j
operator|++
control|)
block|{
name|int
name|multiplicandOffset
init|=
operator|(
name|i
operator|/
name|indexDivisor
operator|)
operator|%
name|codeBookLookupValues
decl_stmt|;
name|valueVector
index|[
name|i
index|]
index|[
name|j
index|]
operator|=
name|codeBookMultiplicands
index|[
name|multiplicandOffset
index|]
operator|*
name|codeBookDeltaValue
operator|+
name|codeBookMinimumValue
operator|+
name|last
expr_stmt|;
if|if
condition|(
name|codeBookSequenceP
condition|)
block|{
name|last
operator|=
name|valueVector
index|[
name|i
index|]
index|[
name|j
index|]
expr_stmt|;
block|}
name|indexDivisor
operator|*=
name|codeBookLookupValues
expr_stmt|;
block|}
block|}
block|}
else|else
block|{
throw|throw
operator|new
name|UnsupportedOperationException
argument_list|()
throw|;
comment|/** @todo implement */
block|}
break|break;
default|default:
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Unsupported codebook lookup type: "
operator|+
name|codeBookLookupType
argument_list|)
throw|;
block|}
block|}
specifier|private
name|boolean
name|createHuffmanTree
parameter_list|(
name|int
index|[]
name|entryLengths
parameter_list|)
block|{
name|huffmanRoot
operator|=
operator|new
name|HuffmanNode
argument_list|()
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
name|entryLengths
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|int
name|el
init|=
name|entryLengths
index|[
name|i
index|]
decl_stmt|;
if|if
condition|(
name|el
operator|>
literal|0
condition|)
block|{
if|if
condition|(
operator|!
name|huffmanRoot
operator|.
name|setNewValue
argument_list|(
name|el
argument_list|,
name|i
argument_list|)
condition|)
block|{
return|return
literal|false
return|;
block|}
block|}
block|}
return|return
literal|true
return|;
block|}
specifier|protected
name|int
name|getDimensions
parameter_list|()
block|{
return|return
name|dimensions
return|;
block|}
specifier|protected
name|int
name|getEntries
parameter_list|()
block|{
return|return
name|entries
return|;
block|}
specifier|protected
name|HuffmanNode
name|getHuffmanRoot
parameter_list|()
block|{
return|return
name|huffmanRoot
return|;
block|}
comment|// public float[] readVQ(ReadableBitChannel source) throws IOException {
comment|// return valueVector[readInt(source)];
comment|// }
specifier|protected
name|int
name|readInt
parameter_list|(
specifier|final
name|BitInputStream
name|source
parameter_list|)
throws|throws
name|IOException
block|{
return|return
name|source
operator|.
name|getInt
argument_list|(
name|huffmanRoot
argument_list|)
return|;
comment|/* 	 * HuffmanNode node; for(node=huffmanRoot; node.value==null; 	 * node=source.getBit()?node.o1:node.o0); return node.value.intValue(); 	 */
block|}
specifier|protected
name|void
name|readVvAdd
parameter_list|(
name|float
index|[]
index|[]
name|a
parameter_list|,
name|BitInputStream
name|source
parameter_list|,
name|int
name|offset
parameter_list|,
name|int
name|length
parameter_list|)
throws|throws
name|VorbisFormatException
throws|,
name|IOException
block|{
name|int
name|i
decl_stmt|,
name|j
decl_stmt|;
comment|// k;//entry;
name|int
name|chptr
init|=
literal|0
decl_stmt|;
name|int
name|ch
init|=
name|a
operator|.
name|length
decl_stmt|;
if|if
condition|(
name|ch
operator|==
literal|0
condition|)
block|{
return|return;
block|}
name|int
name|lim
init|=
operator|(
name|offset
operator|+
name|length
operator|)
operator|/
name|ch
decl_stmt|;
for|for
control|(
name|i
operator|=
name|offset
operator|/
name|ch
init|;
name|i
operator|<
name|lim
condition|;
control|)
block|{
specifier|final
name|float
index|[]
name|ve
init|=
name|valueVector
index|[
name|source
operator|.
name|getInt
argument_list|(
name|huffmanRoot
argument_list|)
index|]
decl_stmt|;
for|for
control|(
name|j
operator|=
literal|0
init|;
name|j
operator|<
name|dimensions
condition|;
name|j
operator|++
control|)
block|{
name|a
index|[
name|chptr
operator|++
index|]
index|[
name|i
index|]
operator|+=
name|ve
index|[
name|j
index|]
expr_stmt|;
if|if
condition|(
name|chptr
operator|==
name|ch
condition|)
block|{
name|chptr
operator|=
literal|0
expr_stmt|;
name|i
operator|++
expr_stmt|;
block|}
block|}
block|}
block|}
comment|/*      * public void readVAdd(double[] a, ReadableBitChannel source, int offset,      * int length) throws FormatException, IOException {      *       * int i,j,entry; int t;      *       * if(dimensions>8){ for(i=0;i<length;){ entry = readInt(source);      * //if(entry==-1)return(-1); //t=entry*dimensions; for(j=0;j<dimensions;){      * a[offset+(i++)]+=valueVector[entry][j++];//valuelist[t+(j++)]; } } }      * else{ for(i=0;i<length;){ entry=readInt(source);      * //if(entry==-1)return(-1); //t=entry*dim; j=0; switch(dimensions){ case      * 8: a[offset+(i++)]+=valueVector[entry][j++];//valuelist[t+(j++)]; case 7:      * a[offset+(i++)]+=valueVector[entry][j++];//valuelist[t+(j++)]; case 6:      * a[offset+(i++)]+=valueVector[entry][j++];//valuelist[t+(j++)]; case 5:      * a[offset+(i++)]+=valueVector[entry][j++];//valuelist[t+(j++)]; case 4:      * a[offset+(i++)]+=valueVector[entry][j++];//valuelist[t+(j++)]; case 3:      * a[offset+(i++)]+=valueVector[entry][j++];//valuelist[t+(j++)]; case 2:      * a[offset+(i++)]+=valueVector[entry][j++];//valuelist[t+(j++)]; case 1:      * a[offset+(i++)]+=valueVector[entry][j++];//valuelist[t+(j++)]; case 0:      * break; } } } }      */
block|}
end_class

end_unit

