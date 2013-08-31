begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: BitInputStream.java,v 1.5 2003/04/10 19:48:31 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: BitInputStream.java,v $  * Revision 1.5  2003/04/10 19:48:31  jarnbjo  * no message  *  * Revision 1.4  2003/03/16 20:57:06  jarnbjo  * no message  *  * Revision 1.3  2003/03/16 20:56:56  jarnbjo  * no message  *  * Revision 1.2  2003/03/16 01:11:39  jarnbjo  * no message  *  * Revision 1.1  2003/03/03 21:02:20  jarnbjo  * no message  *  */
end_comment

begin_package
package|package
name|de
operator|.
name|jarnbjo
operator|.
name|util
operator|.
name|io
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
comment|/**  * An interface with methods allowing bit-wise reading from an input stream. All  * methods in this interface are optional and an implementation not support a  * method or a specific state (e.g. endian) will throw an  * UnspportedOperationException if such a method is being called. This should be  * speicified in the implementation documentation.  */
end_comment

begin_interface
specifier|public
interface|interface
name|BitInputStream
block|{
comment|/**      * constant for setting this stream's mode to little endian      *       * @see #setEndian(int)      */
specifier|public
specifier|static
specifier|final
name|int
name|LITTLE_ENDIAN
init|=
literal|0
decl_stmt|;
comment|/**      * constant for setting this stream's mode to big endian      *       * @see #setEndian(int)      */
specifier|public
specifier|static
specifier|final
name|int
name|BIG_ENDIAN
init|=
literal|1
decl_stmt|;
comment|/**      * reads one bit (as a boolean) from the input stream      *       * @return<code>true</code> if the next bit is 1,<code>false</code>      *         otherwise      *       * @throws IOException      *             if an I/O error occurs      * @throws UnsupportedOperationException      *             if the method is not supported by the implementation      */
specifier|public
name|boolean
name|getBit
parameter_list|()
throws|throws
name|IOException
function_decl|;
comment|/**      * reads<code>bits</code> number of bits from the input stream      *       * @return the unsigned integer value read from the stream      *       * @throws IOException      *             if an I/O error occurs      * @throws UnsupportedOperationException      *             if the method is not supported by the implementation      */
specifier|public
name|int
name|getInt
parameter_list|(
name|int
name|bits
parameter_list|)
throws|throws
name|IOException
function_decl|;
comment|/**      * reads<code>bits</code> number of bits from the input stream      *       * @return the signed integer value read from the stream      *       * @throws IOException      *             if an I/O error occurs      * @throws UnsupportedOperationException      *             if the method is not supported by the implementation      */
specifier|public
name|int
name|getSignedInt
parameter_list|(
name|int
name|bits
parameter_list|)
throws|throws
name|IOException
function_decl|;
comment|/**      * reads a huffman codeword based on the<code>root</code> parameter and      * returns the decoded value      *       * @param root      *            the root of the Huffman tree used to decode the codeword      * @return the decoded unsigned integer value read from the stream      *       * @throws IOException      *             if an I/O error occurs      * @throws UnsupportedOperationException      *             if the method is not supported by the implementation      */
specifier|public
name|int
name|getInt
parameter_list|(
name|HuffmanNode
name|root
parameter_list|)
throws|throws
name|IOException
function_decl|;
comment|/**      * reads an integer encoded as "signed rice" as described in the FLAC audio      * format specification      *       * @param order      * @return the decoded integer value read from the stream      *       * @throws IOException      *             if an I/O error occurs      * @throws UnsupportedOperationException      *             if the method is not supported by the implementation      */
specifier|public
name|int
name|readSignedRice
parameter_list|(
name|int
name|order
parameter_list|)
throws|throws
name|IOException
function_decl|;
comment|/**      * fills the array from<code>offset</code> with<code>len</code> integers      * encoded as "signed rice" as described in the FLAC audio format      * specification      *       * @param order      * @param buffer      * @param offset      * @param len      * @return the decoded integer value read from the stream      *       * @throws IOException      *             if an I/O error occurs      * @throws UnsupportedOperationException      *             if the method is not supported by the implementation      */
specifier|public
name|void
name|readSignedRice
parameter_list|(
name|int
name|order
parameter_list|,
name|int
index|[]
name|buffer
parameter_list|,
name|int
name|offset
parameter_list|,
name|int
name|len
parameter_list|)
throws|throws
name|IOException
function_decl|;
comment|/**      * reads<code>bits</code> number of bits from the input stream      *       * @return the unsigned long value read from the stream      *       * @throws IOException      *             if an I/O error occurs      * @throws UnsupportedOperationException      *             if the method is not supported by the implementation      */
specifier|public
name|long
name|getLong
parameter_list|(
name|int
name|bits
parameter_list|)
throws|throws
name|IOException
function_decl|;
comment|/**      * causes the read pointer to be moved to the beginning of the next byte,      * remaining bits in the current byte are discarded      *       * @throws UnsupportedOperationException      *             if the method is not supported by the implementation      */
specifier|public
name|void
name|align
parameter_list|()
function_decl|;
comment|/**      * changes the endian mode used when reading bit-wise from the stream,      * changing the mode mid-stream will cause the read cursor to move to the      * beginning of the next byte (as if calling the<code>allign</code> method      *       * @see #align()      *       * @throws UnsupportedOperationException      *             if the method is not supported by the implementation      */
specifier|public
name|void
name|setEndian
parameter_list|(
name|int
name|endian
parameter_list|)
function_decl|;
block|}
end_interface

end_unit

