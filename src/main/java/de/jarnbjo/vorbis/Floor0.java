begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: Floor0.java,v 1.2 2003/03/16 01:11:12 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: Floor0.java,v $  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
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
name|Floor0
extends|extends
name|Floor
block|{
specifier|private
name|int
name|bookList
index|[]
decl_stmt|;
specifier|protected
name|Floor0
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
name|bookCount
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
name|bookList
operator|=
operator|new
name|int
index|[
name|bookCount
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
name|bookList
operator|.
name|length
condition|;
name|i
operator|++
control|)
block|{
name|bookList
index|[
name|i
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
name|bookList
index|[
name|i
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
literal|"A floor0_book_list entry is higher than the code book count."
argument_list|)
throw|;
block|}
block|}
block|}
specifier|protected
name|void
name|computeFloor
parameter_list|(
name|float
index|[]
name|vector
parameter_list|)
block|{
comment|/** @todo implement */
throw|throw
operator|new
name|UnsupportedOperationException
argument_list|()
throw|;
block|}
specifier|protected
name|Floor
name|decodeFloor
parameter_list|(
name|VorbisStream
name|vorbis
parameter_list|,
name|BitInputStream
name|source
parameter_list|)
throws|throws
name|VorbisFormatException
throws|,
name|IOException
block|{
comment|/** @todo implement */
throw|throw
operator|new
name|UnsupportedOperationException
argument_list|()
throw|;
block|}
specifier|protected
name|int
name|getType
parameter_list|()
block|{
return|return
literal|0
return|;
block|}
block|}
end_class

end_unit

