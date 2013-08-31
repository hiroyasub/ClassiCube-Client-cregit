begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: UncachedUrlStream.java,v 1.1 2003/04/10 19:48:22 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: UncachedUrlStream.java,v $  * Revision 1.1  2003/04/10 19:48:22  jarnbjo  * no message  *  */
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
comment|/**  *  Implementation of the<code>PhysicalOggStream</code> interface for reading  *  an Ogg stream from a URL. This class performs only the necessary caching  *  to provide continous playback. Seeking within the stream is not supported.  */
end_comment

begin_class
specifier|public
class|class
name|UncachedUrlStream
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
argument_list|<
name|OggPage
argument_list|>
name|pageCache
init|=
operator|new
name|LinkedList
argument_list|<
name|OggPage
argument_list|>
argument_list|()
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
argument_list|<
name|Integer
argument_list|,
name|LogicalOggStreamImpl
argument_list|>
argument_list|()
decl_stmt|;
specifier|private
name|LoaderThread
name|loaderThread
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|int
name|PAGECACHE_SIZE
init|=
literal|10
decl_stmt|;
comment|/** Creates an instance of the<code>PhysicalOggStream</code> interface 	 *  suitable for reading an Ogg stream from a URL.  	 */
specifier|public
name|UncachedUrlStream
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
name|loaderThread
operator|=
operator|new
name|LoaderThread
argument_list|(
name|sourceStream
argument_list|,
name|pageCache
argument_list|)
expr_stmt|;
operator|new
name|Thread
argument_list|(
name|loaderThread
argument_list|)
operator|.
name|start
argument_list|()
expr_stmt|;
while|while
condition|(
operator|!
name|loaderThread
operator|.
name|isBosDone
argument_list|()
operator|||
name|pageCache
operator|.
name|size
argument_list|()
operator|<
name|PAGECACHE_SIZE
condition|)
block|{
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|200
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|ex
parameter_list|)
block|{
block|}
comment|//System.out.print("caching "+pageCache.size()+"/"+PAGECACHE_SIZE+" pages\r");
block|}
comment|//System.out.println();
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
comment|/*    public long getCacheLength() {       return cacheLength;    }    */
comment|/*    private OggPage getNextPage() throws EndOfOggStreamException, IOException, OggFormatException  {       return getNextPage(false);    }     private OggPage getNextPage(boolean skipData) throws EndOfOggStreamException, IOException, OggFormatException  {       return OggPage.create(sourceStream, skipData);    }    */
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
while|while
condition|(
name|pageCache
operator|.
name|size
argument_list|()
operator|==
literal|0
condition|)
block|{
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|100
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|ex
parameter_list|)
block|{
block|}
block|}
synchronized|synchronized
init|(
name|drainLock
init|)
block|{
comment|//OggPage page=(OggPage)pageCache.getFirst();
comment|//pageCache.removeFirst();
comment|//return page;
return|return
operator|(
name|OggPage
operator|)
name|pageCache
operator|.
name|removeFirst
argument_list|()
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
specifier|public
class|class
name|LoaderThread
implements|implements
name|Runnable
block|{
specifier|private
name|InputStream
name|source
decl_stmt|;
specifier|private
name|LinkedList
argument_list|<
name|OggPage
argument_list|>
name|pageCache
decl_stmt|;
specifier|private
name|boolean
name|bosDone
init|=
literal|false
decl_stmt|;
specifier|public
name|LoaderThread
parameter_list|(
name|InputStream
name|source
parameter_list|,
name|LinkedList
argument_list|<
name|OggPage
argument_list|>
name|pageCache
parameter_list|)
block|{
name|this
operator|.
name|source
operator|=
name|source
expr_stmt|;
name|this
operator|.
name|pageCache
operator|=
name|pageCache
expr_stmt|;
block|}
specifier|public
name|void
name|run
parameter_list|()
block|{
try|try
block|{
name|boolean
name|eos
init|=
literal|false
decl_stmt|;
while|while
condition|(
operator|!
name|eos
condition|)
block|{
name|OggPage
name|op
init|=
name|OggPage
operator|.
name|create
argument_list|(
name|source
argument_list|)
decl_stmt|;
synchronized|synchronized
init|(
name|drainLock
init|)
block|{
name|pageCache
operator|.
name|add
argument_list|(
name|op
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
operator|!
name|op
operator|.
name|isBos
argument_list|()
condition|)
block|{
name|bosDone
operator|=
literal|true
expr_stmt|;
block|}
if|if
condition|(
name|op
operator|.
name|isEos
argument_list|()
condition|)
block|{
name|eos
operator|=
literal|true
expr_stmt|;
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
name|UncachedUrlStream
operator|.
name|this
argument_list|)
expr_stmt|;
name|logicalStreams
operator|.
name|put
argument_list|(
operator|new
name|Integer
argument_list|(
name|op
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
name|op
argument_list|)
expr_stmt|;
block|}
while|while
condition|(
name|pageCache
operator|.
name|size
argument_list|()
operator|>
name|PAGECACHE_SIZE
condition|)
block|{
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|200
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|ex
parameter_list|)
block|{
block|}
block|}
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
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
specifier|public
name|boolean
name|isBosDone
parameter_list|()
block|{
return|return
name|bosDone
return|;
block|}
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

