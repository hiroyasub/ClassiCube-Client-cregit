begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: EndOfOggStreamException.java,v 1.1 2003/03/03 21:02:20 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: EndOfOggStreamException.java,v $  * Revision 1.1  2003/03/03 21:02:20  jarnbjo  * no message  *  */
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
comment|/**  * Exception thrown when reaching the end of an Ogg stream  */
end_comment

begin_class
specifier|public
class|class
name|EndOfOggStreamException
extends|extends
name|IOException
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|1L
decl_stmt|;
specifier|public
name|EndOfOggStreamException
parameter_list|()
block|{
block|}
block|}
end_class

end_unit

