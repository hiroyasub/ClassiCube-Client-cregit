begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: CommentHeader.java,v 1.2 2003/03/16 01:11:12 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: CommentHeader.java,v $  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
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
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashMap
import|;
end_import

begin_comment
comment|/**  */
end_comment

begin_class
specifier|public
class|class
name|CommentHeader
block|{
specifier|public
specifier|static
specifier|final
name|String
name|TITLE
init|=
literal|"TITLE"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|ARTIST
init|=
literal|"ARTIST"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|ALBUM
init|=
literal|"ALBUM"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|TRACKNUMBER
init|=
literal|"TRACKNUMBER"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|VERSION
init|=
literal|"VERSION"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|PERFORMER
init|=
literal|"PERFORMER"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|COPYRIGHT
init|=
literal|"COPYRIGHT"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|LICENSE
init|=
literal|"LICENSE"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|ORGANIZATION
init|=
literal|"ORGANIZATION"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|DESCRIPTION
init|=
literal|"DESCRIPTION"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|GENRE
init|=
literal|"GENRE"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|DATE
init|=
literal|"DATE"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|LOCATION
init|=
literal|"LOCATION"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|CONTACT
init|=
literal|"CONTACT"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|ISRC
init|=
literal|"ISRC"
decl_stmt|;
specifier|private
name|String
name|vendor
decl_stmt|;
specifier|private
name|HashMap
argument_list|<
name|String
argument_list|,
name|ArrayList
argument_list|<
name|String
argument_list|>
argument_list|>
name|comments
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|long
name|HEADER
init|=
literal|0x736962726f76L
decl_stmt|;
comment|// 'vorbis'
specifier|public
name|CommentHeader
parameter_list|(
name|BitInputStream
name|source
parameter_list|)
throws|throws
name|IOException
block|{
if|if
condition|(
name|source
operator|.
name|getLong
argument_list|(
literal|48
argument_list|)
operator|!=
name|HEADER
condition|)
block|{
throw|throw
operator|new
name|VorbisFormatException
argument_list|(
literal|"The identification header has an illegal leading."
argument_list|)
throw|;
block|}
name|vendor
operator|=
name|getString
argument_list|(
name|source
argument_list|)
expr_stmt|;
name|int
name|ucLength
init|=
name|source
operator|.
name|getInt
argument_list|(
literal|32
argument_list|)
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|ucLength
condition|;
name|i
operator|++
control|)
block|{
name|String
name|comment
init|=
name|getString
argument_list|(
name|source
argument_list|)
decl_stmt|;
name|int
name|ix
init|=
name|comment
operator|.
name|indexOf
argument_list|(
literal|'='
argument_list|)
decl_stmt|;
name|String
name|key
init|=
name|comment
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|ix
argument_list|)
decl_stmt|;
name|String
name|value
init|=
name|comment
operator|.
name|substring
argument_list|(
name|ix
operator|+
literal|1
argument_list|)
decl_stmt|;
comment|// comments.put(key, value);
name|addComment
argument_list|(
name|key
argument_list|,
name|value
argument_list|)
expr_stmt|;
block|}
block|}
specifier|private
name|void
name|addComment
parameter_list|(
name|String
name|key
parameter_list|,
name|String
name|value
parameter_list|)
block|{
name|ArrayList
argument_list|<
name|String
argument_list|>
name|al
init|=
name|comments
operator|.
name|get
argument_list|(
name|key
argument_list|)
decl_stmt|;
if|if
condition|(
name|al
operator|==
literal|null
condition|)
block|{
name|al
operator|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
expr_stmt|;
name|comments
operator|.
name|put
argument_list|(
name|key
argument_list|,
name|al
argument_list|)
expr_stmt|;
block|}
name|al
operator|.
name|add
argument_list|(
name|value
argument_list|)
expr_stmt|;
block|}
specifier|public
name|String
name|getAlbum
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|ALBUM
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getAlbums
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|ALBUM
argument_list|)
return|;
block|}
specifier|public
name|String
name|getArtist
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|ARTIST
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getArtists
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|ARTIST
argument_list|)
return|;
block|}
specifier|public
name|String
name|getComment
parameter_list|(
name|String
name|key
parameter_list|)
block|{
name|ArrayList
argument_list|<
name|String
argument_list|>
name|al
init|=
name|comments
operator|.
name|get
argument_list|(
name|key
argument_list|)
decl_stmt|;
return|return
name|al
operator|==
literal|null
condition|?
literal|null
else|:
name|al
operator|.
name|get
argument_list|(
literal|0
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getComments
parameter_list|(
name|String
name|key
parameter_list|)
block|{
name|ArrayList
argument_list|<
name|String
argument_list|>
name|al
init|=
name|comments
operator|.
name|get
argument_list|(
name|key
argument_list|)
decl_stmt|;
return|return
name|al
operator|==
literal|null
condition|?
operator|new
name|String
index|[
literal|0
index|]
else|:
name|al
operator|.
name|toArray
argument_list|(
operator|new
name|String
index|[
name|al
operator|.
name|size
argument_list|()
index|]
argument_list|)
return|;
block|}
specifier|public
name|String
name|getContact
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|CONTACT
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getContacts
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|CONTACT
argument_list|)
return|;
block|}
specifier|public
name|String
name|getCopyright
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|COPYRIGHT
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getCopyrights
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|COPYRIGHT
argument_list|)
return|;
block|}
specifier|public
name|String
name|getDate
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|DATE
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getDates
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|DATE
argument_list|)
return|;
block|}
specifier|public
name|String
name|getDescription
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|DESCRIPTION
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getDescriptions
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|DESCRIPTION
argument_list|)
return|;
block|}
specifier|public
name|String
name|getGenre
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|GENRE
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getGenres
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|GENRE
argument_list|)
return|;
block|}
specifier|public
name|String
name|getIsrc
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|ISRC
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getIsrcs
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|ISRC
argument_list|)
return|;
block|}
specifier|public
name|String
name|getLicense
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|LICENSE
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getLicenses
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|LICENSE
argument_list|)
return|;
block|}
specifier|public
name|String
name|getLocation
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|LOCATION
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getLocations
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|LOCATION
argument_list|)
return|;
block|}
specifier|public
name|String
name|getOrganization
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|ORGANIZATION
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getOrganizations
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|ORGANIZATION
argument_list|)
return|;
block|}
specifier|public
name|String
name|getPerformer
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|PERFORMER
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getPerformers
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|PERFORMER
argument_list|)
return|;
block|}
specifier|private
name|String
name|getString
parameter_list|(
name|BitInputStream
name|source
parameter_list|)
throws|throws
name|IOException
block|{
name|int
name|length
init|=
name|source
operator|.
name|getInt
argument_list|(
literal|32
argument_list|)
decl_stmt|;
name|byte
index|[]
name|strArray
init|=
operator|new
name|byte
index|[
name|length
index|]
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|length
condition|;
name|i
operator|++
control|)
block|{
name|strArray
index|[
name|i
index|]
operator|=
operator|(
name|byte
operator|)
name|source
operator|.
name|getInt
argument_list|(
literal|8
argument_list|)
expr_stmt|;
block|}
return|return
operator|new
name|String
argument_list|(
name|strArray
argument_list|,
literal|"UTF-8"
argument_list|)
return|;
block|}
specifier|public
name|String
name|getTitle
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|TITLE
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getTitles
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|TITLE
argument_list|)
return|;
block|}
specifier|public
name|String
name|getTrackNumber
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|TRACKNUMBER
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getTrackNumbers
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|TRACKNUMBER
argument_list|)
return|;
block|}
specifier|public
name|String
name|getVendor
parameter_list|()
block|{
return|return
name|vendor
return|;
block|}
specifier|public
name|String
name|getVersion
parameter_list|()
block|{
return|return
name|getComment
argument_list|(
name|VERSION
argument_list|)
return|;
block|}
specifier|public
name|String
index|[]
name|getVersions
parameter_list|()
block|{
return|return
name|getComments
argument_list|(
name|VERSION
argument_list|)
return|;
block|}
block|}
end_class

end_unit

