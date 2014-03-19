begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
package|;
end_package

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|gui
operator|.
name|FontRenderer
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|URI
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|URISyntaxException
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
name|regex
operator|.
name|Matcher
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|regex
operator|.
name|Pattern
import|;
end_import

begin_comment
comment|/**  * Class used to store data for clicking URLs in the chat screen  *   * @author Jon  *   */
end_comment

begin_class
specifier|public
class|class
name|ChatClickData
block|{
specifier|public
class|class
name|LinkData
block|{
specifier|public
name|String
name|link
decl_stmt|;
specifier|public
name|int
name|x0
decl_stmt|;
specifier|public
name|int
name|x1
decl_stmt|;
specifier|public
name|LinkData
parameter_list|(
name|String
name|textualLink
parameter_list|,
name|int
name|x0
parameter_list|,
name|int
name|x1
parameter_list|)
block|{
name|link
operator|=
name|textualLink
expr_stmt|;
name|this
operator|.
name|x0
operator|=
name|x0
expr_stmt|;
name|this
operator|.
name|x1
operator|=
name|x1
expr_stmt|;
block|}
block|}
comment|/**      * The idea is to work with urls http, fpt, sftp, gopher, telnet and file      * (tee hee)      */
specifier|private
specifier|final
name|String
name|urlPattern
init|=
literal|"((https?|ftp|sftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)"
decl_stmt|;
specifier|private
specifier|final
name|Pattern
name|compiledPattern
init|=
name|Pattern
operator|.
name|compile
argument_list|(
name|urlPattern
argument_list|,
name|Pattern
operator|.
name|CASE_INSENSITIVE
argument_list|)
decl_stmt|;
specifier|public
specifier|final
name|String
name|message
decl_stmt|;
specifier|private
specifier|final
name|ArrayList
argument_list|<
name|LinkData
argument_list|>
name|clickedUrls
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|Pattern
name|patternControlCode
init|=
name|Pattern
operator|.
name|compile
argument_list|(
literal|"(?i)\\u00A7[0-9A-FK-OR]"
argument_list|)
decl_stmt|;
specifier|public
specifier|static
name|String
name|stripControlCodes
parameter_list|(
name|String
name|string
parameter_list|)
block|{
return|return
name|patternControlCode
operator|.
name|matcher
argument_list|(
name|string
argument_list|)
operator|.
name|replaceAll
argument_list|(
literal|""
argument_list|)
return|;
block|}
specifier|public
name|ChatClickData
parameter_list|(
name|FontRenderer
name|fontRenderer
parameter_list|,
name|ChatLine
name|chatLine
parameter_list|)
block|{
name|message
operator|=
name|chatLine
operator|.
name|message
expr_stmt|;
name|clickedUrls
operator|=
name|pullLinks
argument_list|(
name|message
argument_list|,
name|fontRenderer
argument_list|)
expr_stmt|;
block|}
specifier|public
name|ArrayList
argument_list|<
name|LinkData
argument_list|>
name|getClickedUrls
parameter_list|()
block|{
return|return
name|clickedUrls
return|;
block|}
specifier|public
name|URI
name|getURI
parameter_list|(
name|String
name|message
parameter_list|)
block|{
name|Matcher
name|urlMatcher
init|=
name|compiledPattern
operator|.
name|matcher
argument_list|(
name|message
argument_list|)
decl_stmt|;
if|if
condition|(
name|urlMatcher
operator|.
name|matches
argument_list|()
condition|)
block|{
try|try
block|{
name|String
name|url
init|=
name|urlMatcher
operator|.
name|group
argument_list|(
literal|0
argument_list|)
decl_stmt|;
if|if
condition|(
name|urlMatcher
operator|.
name|group
argument_list|(
literal|1
argument_list|)
operator|==
literal|null
condition|)
block|{
comment|// will dis happen?
name|url
operator|=
literal|"http://"
operator|+
name|url
expr_stmt|;
block|}
return|return
operator|new
name|URI
argument_list|(
name|url
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
name|URISyntaxException
name|uriE
parameter_list|)
block|{
comment|// Not sure if we need to do anything here
comment|// I'm sure no error needs to be recorded
block|}
block|}
return|return
literal|null
return|;
block|}
comment|/**      * Strips any URLs from the the line where the user clicked      *       * @param text      *            The text in question      * @param fr      *            The font renderer instance      * @return ArrayList of LinkData      */
specifier|private
name|ArrayList
argument_list|<
name|LinkData
argument_list|>
name|pullLinks
parameter_list|(
name|String
name|text
parameter_list|,
name|FontRenderer
name|fr
parameter_list|)
block|{
name|ArrayList
argument_list|<
name|LinkData
argument_list|>
name|links
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|Matcher
name|m
init|=
name|compiledPattern
operator|.
name|matcher
argument_list|(
name|text
argument_list|)
decl_stmt|;
while|while
condition|(
name|m
operator|.
name|find
argument_list|()
condition|)
block|{
name|String
name|urlStr
init|=
name|m
operator|.
name|group
argument_list|()
decl_stmt|;
if|if
condition|(
name|urlStr
operator|.
name|startsWith
argument_list|(
literal|"("
argument_list|)
operator|&&
name|urlStr
operator|.
name|endsWith
argument_list|(
literal|")"
argument_list|)
condition|)
block|{
name|urlStr
operator|=
name|urlStr
operator|.
name|substring
argument_list|(
literal|1
argument_list|,
name|urlStr
operator|.
name|length
argument_list|()
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
name|links
operator|.
name|add
argument_list|(
operator|new
name|LinkData
argument_list|(
name|urlStr
argument_list|,
name|fr
operator|.
name|getWidth
argument_list|(
name|text
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|m
operator|.
name|start
argument_list|()
argument_list|)
argument_list|)
argument_list|,
name|fr
operator|.
name|getWidth
argument_list|(
name|text
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|m
operator|.
name|end
argument_list|()
argument_list|)
argument_list|)
argument_list|)
argument_list|)
expr_stmt|;
block|}
return|return
name|links
return|;
block|}
block|}
end_class

end_unit

