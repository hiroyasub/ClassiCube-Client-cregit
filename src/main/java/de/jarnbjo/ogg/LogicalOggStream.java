begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: LogicalOggStream.java,v 1.2 2003/04/10 19:48:22 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: LogicalOggStream.java,v $  * Revision 1.2  2003/04/10 19:48:22  jarnbjo  * no message  *  * Revision 1.1  2003/03/03 21:02:20  jarnbjo  * no message  *  */
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

begin_comment
comment|/**  * Interface providing access to a logical Ogg stream as part of a physical Ogg  * stream.  */
end_comment

begin_interface
specifier|public
interface|interface
name|LogicalOggStream
block|{
specifier|public
specifier|static
specifier|final
name|String
name|FORMAT_UNKNOWN
init|=
literal|"application/octet-stream"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|FORMAT_VORBIS
init|=
literal|"audio/x-vorbis"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|FORMAT_FLAC
init|=
literal|"audio/x-flac"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|FORMAT_THEORA
init|=
literal|"video/x-theora"
decl_stmt|;
comment|/**      * Closes this stream. After invoking this method, no further access to the      * streams data is possible.      *       * @throws IOException      *             if an IO error occurs      */
specifier|public
name|void
name|close
parameter_list|()
throws|throws
name|IOException
function_decl|;
comment|/**      * @return the content type of this stream      *       * @see #FORMAT_UNKNOWN      * @see #FORMAT_VORBIS      * @see #FORMAT_FLAC      * @see #FORMAT_THEORA      */
specifier|public
name|String
name|getFormat
parameter_list|()
function_decl|;
comment|/**      * This method does not work if the physical Ogg stream is not seekable.      *       * @return the granule position of the last page within this stream      */
specifier|public
name|long
name|getMaximumGranulePosition
parameter_list|()
function_decl|;
comment|/**      *<i>Note:</i> To read from the stream, you must use either this method or      * the method<code>getNextOggPage</code>. Mixing calls to the two methods      * will cause data corruption.      *       * @return the next packet as a byte array      *       * @see #getNextOggPage()      *       * @throws OggFormatException      *             if the ogg stream is corrupted      * @throws IOException      *             if some other IO error occurs      */
specifier|public
name|byte
index|[]
name|getNextOggPacket
parameter_list|()
throws|throws
name|OggFormatException
throws|,
name|IOException
function_decl|;
comment|/**      *<i>Note:</i> To read from the stream, you must use either this method or      * the method<code>getNextOggPacket</code>. Mixing calls to the two methods      * will cause data corruption.      *       * @return the next Ogg page      *       * @see #getNextOggPacket()      *       * @throws OggFormatException      *             if the ogg stream is corrupted      * @throws IOException      *             if some other IO error occurs      */
specifier|public
name|OggPage
name|getNextOggPage
parameter_list|()
throws|throws
name|OggFormatException
throws|,
name|IOException
function_decl|;
comment|/**      * @return the last parsed granule position of this stream      */
specifier|public
name|long
name|getTime
parameter_list|()
function_decl|;
comment|/**      * Checks if this stream is open for reading.      *       * @return<code>true</code> if this stream is open for reading,      *<code>false</code> otherwise      */
specifier|public
name|boolean
name|isOpen
parameter_list|()
function_decl|;
comment|/**      * Sets the stream's position to the beginning of the stream. This method      * does not work if the physical Ogg stream is not seekable.      *       * @throws OggFormatException      *             if the ogg stream is corrupted      * @throws IOException      *             if some other IO error occurs      */
specifier|public
name|void
name|reset
parameter_list|()
throws|throws
name|OggFormatException
throws|,
name|IOException
function_decl|;
comment|/**      * This method is invoked on all logical streams when calling the same      * method on the physical stream. The same restrictions as mentioned there      * apply. This method does not work if the physical Ogg stream is not      * seekable.      *       * @param granulePosition      *       * @see PhysicalOggStream#setTime(long)      *       * @throws IOException      *             if an IO error occurs      */
specifier|public
name|void
name|setTime
parameter_list|(
name|long
name|granulePosition
parameter_list|)
throws|throws
name|IOException
function_decl|;
block|}
end_interface

end_unit

