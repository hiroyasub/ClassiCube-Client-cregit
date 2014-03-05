begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: PhysicalOggStream.java,v 1.3 2003/04/10 19:48:22 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: PhysicalOggStream.java,v $  * Revision 1.3  2003/04/10 19:48:22  jarnbjo  * no message  *  * Revision 1.2  2003/03/31 00:23:04  jarnbjo  * no message  *  * Revision 1.1  2003/03/03 21:02:20  jarnbjo  * no message  *  */
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
name|util
operator|.
name|Collection
import|;
end_import

begin_comment
comment|/**  * Interface providing access to a physical Ogg stream. Typically this is a  * file.  */
end_comment

begin_interface
specifier|public
interface|interface
name|PhysicalOggStream
block|{
comment|/** 	 * Closes this stream. After invoking this method, no further access to the 	 * streams data is possible. 	 *  	 * @throws IOException 	 */
specifier|public
name|void
name|close
parameter_list|()
throws|throws
name|IOException
function_decl|;
comment|/** 	 * Returns a collection of objects implementing 	 *<code>LogicalOggStream</code> for accessing the separate logical streams 	 * within this physical Ogg stream. 	 *  	 * @return a collection of objects implementing 	 *<code>LogicalOggStream</code> which are representing the logical 	 *		 streams contained within this physical stream 	 *  	 * @see LogicalOggStream 	 */
specifier|public
name|Collection
argument_list|<
name|LogicalOggStreamImpl
argument_list|>
name|getLogicalStreams
parameter_list|()
function_decl|;
comment|/** 	 * Return the Ogg page with the absolute index<code>index</code>, 	 * independent from the logical structure of this stream or if the index 	 * parameter is -1, the next Ogg page is returned. This method should only 	 * be used by implementations of<code>LogicalOggStream</code> to access the 	 * raw pages. 	 *  	 * @param index 	 *			the absolute index starting from 0 at the beginning of the 	 *			file or stream or -1 to get the next page in a non-seekable 	 *			stream 	 *  	 * @return the Ogg page with the physical absolute index<code>index</code> 	 *  	 * @throws OggFormatException 	 *			 if the ogg stream is corrupted 	 * @throws IOException 	 *			 if some other IO error occurs 	 */
specifier|public
name|OggPage
name|getOggPage
parameter_list|(
name|int
name|index
parameter_list|)
throws|throws
name|OggFormatException
throws|,
name|IOException
function_decl|;
comment|/** 	 * Checks if this stream is open for reading. 	 *  	 * @return<code>true</code> if this stream is open for reading, 	 *<code>false</code> otherwise 	 */
specifier|public
name|boolean
name|isOpen
parameter_list|()
function_decl|;
comment|/** 	 * @return<code>true</code> if the stream is seekable,<code>false</code> 	 *		 otherwise 	 */
specifier|public
name|boolean
name|isSeekable
parameter_list|()
function_decl|;
comment|/** 	 * Sets this stream's (and its logical stream's) position to the granule 	 * position. The next packet read from any logical stream will be the first 	 * packet beginning on the first page with a granule position higher than 	 * the argument.<br> 	 *<br> 	 *  	 * At the moment, this method only works correctly for Ogg files with a 	 * single logical Vorbis stream, and due to the different interpretations of 	 * the granule position, depending on mixed content, this method will never 	 * be able to work for mixed streams. Chained and interleaved streams are 	 * also not yet supported. Actually, this method is only a hack to support 	 * seeking from JMF, but may of course be abused otherwise too :) 	 *  	 * @param granulePosition 	 *  	 * @throws OggFormatException 	 *			 if the ogg stream is corrupted 	 * @throws IOException 	 *			 if some other IO error occurs 	 */
specifier|public
name|void
name|setTime
parameter_list|(
name|long
name|granulePosition
parameter_list|)
throws|throws
name|OggFormatException
throws|,
name|IOException
function_decl|;
block|}
end_interface

end_unit

