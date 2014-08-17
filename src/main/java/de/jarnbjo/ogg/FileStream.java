begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: FileStream.java,v 1.1 2003/04/10 19:48:22 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: FileStream.java,v $  * Revision 1.1  2003/04/10 19:48:22  jarnbjo  * no message  *  *  */
end_comment

begin_package
package|package
name|de
operator|.
name|jarnbjo
operator|.
name|ogg
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
name|io
operator|.
name|RandomAccessFile
import|;
end_import

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
name|Collection
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

begin_comment
comment|/**  * Implementation of the<code>PhysicalOggStream</code> interface for accessing  * normal disk files.  */
end_comment

begin_class
specifier|public
class|class
name|FileStream
implements|implements
name|PhysicalOggStream
block|{
specifier|private
name|boolean
name|closed
init|=
literal|false
decl_stmt|;
specifier|private
name|RandomAccessFile
name|source
decl_stmt|;
specifier|private
name|long
index|[]
name|pageOffsets
decl_stmt|;
specifier|private
name|HashMap
argument_list|<
name|Integer
argument_list|,
name|LogicalOggStreamImpl
argument_list|>
name|logicalStreams
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
comment|/**      * Creates access to the specified file through the      *<code>PhysicalOggStream</code> interface. The specified source file must      * have been opened for reading.      *      * @param source      *            the file to read from      *      * @throws OggFormatException      *             if the stream format is incorrect      * @throws IOException      *             if some other IO error occurs when reading the file      */
specifier|public
name|FileStream
parameter_list|(
name|RandomAccessFile
name|source
parameter_list|)
throws|throws
name|OggFormatException
throws|,
name|IOException
block|{
name|this
operator|.
name|source
operator|=
name|source
expr_stmt|;
name|ArrayList
argument_list|<
name|Long
argument_list|>
name|po
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|int
name|pageNumber
init|=
literal|0
decl_stmt|;
try|try
block|{
while|while
condition|(
literal|true
condition|)
block|{
name|po
operator|.
name|add
argument_list|(
name|this
operator|.
name|source
operator|.
name|getFilePointer
argument_list|()
argument_list|)
expr_stmt|;
comment|// skip data if pageNumber>0
name|OggPage
name|op
init|=
name|getNextPage
argument_list|(
name|pageNumber
operator|>
literal|0
argument_list|)
decl_stmt|;
if|if
condition|(
name|op
operator|==
literal|null
condition|)
block|{
break|break;
block|}
name|LogicalOggStreamImpl
name|los
init|=
operator|(
name|LogicalOggStreamImpl
operator|)
name|getLogicalStream
argument_list|(
name|op
operator|.
name|getStreamSerialNumber
argument_list|()
argument_list|)
decl_stmt|;
if|if
condition|(
name|los
operator|==
literal|null
condition|)
block|{
name|los
operator|=
operator|new
name|LogicalOggStreamImpl
argument_list|(
name|this
argument_list|)
expr_stmt|;
name|logicalStreams
operator|.
name|put
argument_list|(
name|op
operator|.
name|getStreamSerialNumber
argument_list|()
argument_list|,
name|los
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|pageNumber
operator|==
literal|0
condition|)
block|{
name|los
operator|.
name|checkFormat
argument_list|(
name|op
argument_list|)
expr_stmt|;
block|}
name|los
operator|.
name|addPageNumberMapping
argument_list|(
name|pageNumber
argument_list|)
expr_stmt|;
name|los
operator|.
name|addGranulePosition
argument_list|(
name|op
operator|.
name|getAbsoluteGranulePosition
argument_list|()
argument_list|)
expr_stmt|;
if|if
condition|(
name|pageNumber
operator|>
literal|0
condition|)
block|{
name|this
operator|.
name|source
operator|.
name|seek
argument_list|(
name|this
operator|.
name|source
operator|.
name|getFilePointer
argument_list|()
operator|+
name|op
operator|.
name|getTotalLength
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|pageNumber
operator|++
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|EndOfOggStreamException
name|e
parameter_list|)
block|{
comment|// ok
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
throw|throw
name|e
throw|;
block|}
comment|// System.out.println("pageNumber: "+pageNumber);
name|this
operator|.
name|source
operator|.
name|seek
argument_list|(
literal|0L
argument_list|)
expr_stmt|;
name|pageOffsets
operator|=
operator|new
name|long
index|[
name|po
operator|.
name|size
argument_list|()
index|]
expr_stmt|;
name|int
name|i
init|=
literal|0
decl_stmt|;
for|for
control|(
name|Long
name|next
range|:
name|po
control|)
block|{
name|pageOffsets
index|[
name|i
operator|++
index|]
operator|=
name|next
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|close
parameter_list|()
throws|throws
name|IOException
block|{
name|closed
operator|=
literal|true
expr_stmt|;
name|source
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
specifier|private
name|LogicalOggStream
name|getLogicalStream
parameter_list|(
name|int
name|serialNumber
parameter_list|)
block|{
return|return
name|logicalStreams
operator|.
name|get
argument_list|(
operator|new
name|Integer
argument_list|(
name|serialNumber
argument_list|)
argument_list|)
return|;
block|}
specifier|public
name|Collection
argument_list|<
name|LogicalOggStreamImpl
argument_list|>
name|getLogicalStreams
parameter_list|()
block|{
return|return
name|logicalStreams
operator|.
name|values
argument_list|()
return|;
block|}
specifier|private
name|OggPage
name|getNextPage
parameter_list|(
name|boolean
name|skipData
parameter_list|)
throws|throws
name|EndOfOggStreamException
throws|,
name|IOException
throws|,
name|OggFormatException
block|{
return|return
name|OggPage
operator|.
name|create
argument_list|(
name|source
argument_list|,
name|skipData
argument_list|)
return|;
block|}
specifier|public
name|OggPage
name|getOggPage
parameter_list|(
name|int
name|index
parameter_list|)
throws|throws
name|IOException
block|{
name|source
operator|.
name|seek
argument_list|(
name|pageOffsets
index|[
name|index
index|]
argument_list|)
expr_stmt|;
return|return
name|OggPage
operator|.
name|create
argument_list|(
name|source
argument_list|)
return|;
block|}
specifier|public
name|boolean
name|isOpen
parameter_list|()
block|{
return|return
operator|!
name|closed
return|;
block|}
comment|/**      * @return always<code>true</code>      */
specifier|public
name|boolean
name|isSeekable
parameter_list|()
block|{
return|return
literal|true
return|;
block|}
specifier|public
name|void
name|setTime
parameter_list|(
name|long
name|granulePosition
parameter_list|)
throws|throws
name|IOException
block|{
for|for
control|(
name|LogicalOggStreamImpl
name|los
range|:
name|logicalStreams
operator|.
name|values
argument_list|()
control|)
block|{
name|los
operator|.
name|setTime
argument_list|(
name|granulePosition
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

