begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: OnDemandUrlStream.java,v 1.1 2003/04/10 19:48:22 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: OnDemandUrlStream.java,v $  * Revision 1.1  2003/04/10 19:48:22  jarnbjo  * no message  *  * Revision 1.1  2003/03/31 00:23:04  jarnbjo  * no message  *  */
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
name|*
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
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

begin_comment
comment|/**  * Implementation of the<code>PhysicalOggStream</code> interface for reading  * an Ogg stream from a URL. This class performs  *  no internal caching, and will not read data from the network before  *  requested to do so. It is intended to be used in non-realtime applications  *  like file download managers or similar.  */
end_comment

begin_class
specifier|public
class|class
name|OnDemandUrlStream
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
name|URLConnection
name|source
decl_stmt|;
specifier|private
name|InputStream
name|sourceStream
decl_stmt|;
specifier|private
name|Object
name|drainLock
init|=
operator|new
name|Object
argument_list|()
decl_stmt|;
specifier|private
name|LinkedList
name|pageCache
init|=
operator|new
name|LinkedList
argument_list|()
decl_stmt|;
specifier|private
name|long
name|numberOfSamples
init|=
operator|-
literal|1
decl_stmt|;
specifier|private
name|int
name|contentLength
init|=
literal|0
decl_stmt|;
specifier|private
name|int
name|position
init|=
literal|0
decl_stmt|;
specifier|private
name|HashMap
name|logicalStreams
init|=
operator|new
name|HashMap
argument_list|()
decl_stmt|;
specifier|private
name|OggPage
name|firstPage
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|PAGECACHE_SIZE
init|=
literal|20
decl_stmt|;
specifier|public
name|OnDemandUrlStream
parameter_list|(
name|URL
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
operator|.
name|openConnection
argument_list|()
expr_stmt|;
name|this
operator|.
name|sourceStream
operator|=
name|this
operator|.
name|source
operator|.
name|getInputStream
argument_list|()
expr_stmt|;
name|contentLength
operator|=
name|this
operator|.
name|source
operator|.
name|getContentLength
argument_list|()
expr_stmt|;
name|firstPage
operator|=
name|OggPage
operator|.
name|create
argument_list|(
name|sourceStream
argument_list|)
expr_stmt|;
name|position
operator|+=
name|firstPage
operator|.
name|getTotalLength
argument_list|()
expr_stmt|;
name|LogicalOggStreamImpl
name|los
init|=
operator|new
name|LogicalOggStreamImpl
argument_list|(
name|this
argument_list|,
name|firstPage
operator|.
name|getStreamSerialNumber
argument_list|()
argument_list|)
decl_stmt|;
name|logicalStreams
operator|.
name|put
argument_list|(
operator|new
name|Integer
argument_list|(
name|firstPage
operator|.
name|getStreamSerialNumber
argument_list|()
argument_list|)
argument_list|,
name|los
argument_list|)
expr_stmt|;
name|los
operator|.
name|checkFormat
argument_list|(
name|firstPage
argument_list|)
expr_stmt|;
block|}
specifier|public
name|Collection
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
name|sourceStream
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
specifier|public
name|int
name|getContentLength
parameter_list|()
block|{
return|return
name|contentLength
return|;
block|}
specifier|public
name|int
name|getPosition
parameter_list|()
block|{
return|return
name|position
return|;
block|}
name|int
name|pageNumber
init|=
literal|2
decl_stmt|;
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
if|if
condition|(
name|firstPage
operator|!=
literal|null
condition|)
block|{
name|OggPage
name|tmp
init|=
name|firstPage
decl_stmt|;
name|firstPage
operator|=
literal|null
expr_stmt|;
return|return
name|tmp
return|;
block|}
else|else
block|{
name|OggPage
name|page
init|=
name|OggPage
operator|.
name|create
argument_list|(
name|sourceStream
argument_list|)
decl_stmt|;
name|position
operator|+=
name|page
operator|.
name|getTotalLength
argument_list|()
expr_stmt|;
return|return
name|page
return|;
block|}
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
operator|(
name|LogicalOggStream
operator|)
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
name|void
name|setTime
parameter_list|(
name|long
name|granulePosition
parameter_list|)
throws|throws
name|IOException
block|{
throw|throw
operator|new
name|UnsupportedOperationException
argument_list|(
literal|"Method not supported by this class"
argument_list|)
throw|;
block|}
comment|/**  	 *  @return always<code>false</code> 	 */
specifier|public
name|boolean
name|isSeekable
parameter_list|()
block|{
return|return
literal|false
return|;
block|}
block|}
end_class

end_unit

