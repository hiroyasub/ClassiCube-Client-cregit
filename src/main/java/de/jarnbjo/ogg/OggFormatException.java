begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: OggFormatException.java,v 1.1 2003/03/03 21:02:20 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: OggFormatException.java,v $  * Revision 1.1  2003/03/03 21:02:20  jarnbjo  * no message  *  */
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
comment|/**  * Exception thrown when trying to read a corrupted Ogg stream.  */
end_comment

begin_class
specifier|public
class|class
name|OggFormatException
extends|extends
name|IOException
block|{
specifier|public
name|OggFormatException
parameter_list|()
block|{
name|super
argument_list|()
expr_stmt|;
block|}
specifier|public
name|OggFormatException
parameter_list|(
name|String
name|message
parameter_list|)
block|{
name|super
argument_list|(
name|message
argument_list|)
expr_stmt|;
block|}
block|}
end_class

end_unit

