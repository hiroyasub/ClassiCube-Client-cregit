begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: Mode.java,v 1.2 2003/03/16 01:11:12 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: Mode.java,v $  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
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
name|Mode
block|{
specifier|private
name|boolean
name|blockFlag
decl_stmt|;
specifier|private
name|int
name|windowType
decl_stmt|,
name|transformType
decl_stmt|,
name|mapping
decl_stmt|;
specifier|protected
name|Mode
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
name|blockFlag
operator|=
name|source
operator|.
name|getBit
argument_list|()
expr_stmt|;
name|windowType
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|16
argument_list|)
expr_stmt|;
name|transformType
operator|=
name|source
operator|.
name|getInt
argument_list|(
literal|16
argument_list|)
expr_stmt|;
name|mapping
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
name|windowType
operator|!=
literal|0
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Window type = "
operator|+
name|windowType
operator|+
literal|", != 0"
argument_list|)
throw|;
block|}
if|if
condition|(
name|transformType
operator|!=
literal|0
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Transform type = "
operator|+
name|transformType
operator|+
literal|", != 0"
argument_list|)
throw|;
block|}
if|if
condition|(
name|mapping
operator|>
name|header
operator|.
name|getMappings
argument_list|()
operator|.
name|length
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"Mode mapping number is higher than total number of mappings."
argument_list|)
throw|;
block|}
block|}
specifier|protected
name|boolean
name|getBlockFlag
parameter_list|()
block|{
return|return
name|blockFlag
return|;
block|}
specifier|protected
name|int
name|getMapping
parameter_list|()
block|{
return|return
name|mapping
return|;
block|}
specifier|protected
name|int
name|getTransformType
parameter_list|()
block|{
return|return
name|transformType
return|;
block|}
specifier|protected
name|int
name|getWindowType
parameter_list|()
block|{
return|return
name|windowType
return|;
block|}
block|}
end_class

end_unit

