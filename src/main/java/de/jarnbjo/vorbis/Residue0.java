begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: Residue0.java,v 1.2 2003/03/16 01:11:12 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: Residue0.java,v $  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
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
name|Residue0
extends|extends
name|Residue
block|{
specifier|protected
name|Residue0
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

