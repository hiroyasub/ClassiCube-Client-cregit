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
name|player
operator|.
name|Player
import|;
end_import

begin_import
import|import
name|com
operator|.
name|oyasunadev
operator|.
name|mcraft
operator|.
name|client
operator|.
name|util
operator|.
name|Constants
import|;
end_import

begin_import
import|import
name|javax
operator|.
name|imageio
operator|.
name|ImageIO
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|image
operator|.
name|BufferedImage
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|HttpURLConnection
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|URL
import|;
end_import

begin_class
specifier|public
class|class
name|SkinDownloadThread
extends|extends
name|Thread
block|{
name|String
name|skinServer
decl_stmt|;
specifier|private
name|Minecraft
name|minecraft
decl_stmt|;
specifier|public
name|SkinDownloadThread
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|,
name|String
name|skinServer
parameter_list|)
block|{
name|super
argument_list|()
expr_stmt|;
name|this
operator|.
name|skinServer
operator|=
name|skinServer
expr_stmt|;
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|run
parameter_list|()
block|{
if|if
condition|(
name|minecraft
operator|.
name|session
operator|!=
literal|null
condition|)
block|{
name|HttpURLConnection
name|connection
init|=
literal|null
decl_stmt|;
try|try
block|{
name|connection
operator|=
operator|(
name|HttpURLConnection
operator|)
operator|new
name|URL
argument_list|(
name|skinServer
operator|+
name|minecraft
operator|.
name|session
operator|.
name|username
operator|+
literal|".png"
argument_list|)
operator|.
name|openConnection
argument_list|()
expr_stmt|;
name|connection
operator|.
name|addRequestProperty
argument_list|(
literal|"User-Agent"
argument_list|,
name|Constants
operator|.
name|USER_AGENT
argument_list|)
expr_stmt|;
name|connection
operator|.
name|setDoInput
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|connection
operator|.
name|setDoOutput
argument_list|(
literal|false
argument_list|)
expr_stmt|;
name|connection
operator|.
name|setUseCaches
argument_list|(
literal|false
argument_list|)
expr_stmt|;
name|connection
operator|.
name|connect
argument_list|()
expr_stmt|;
if|if
condition|(
name|connection
operator|.
name|getResponseCode
argument_list|()
operator|!=
literal|404
condition|)
block|{
name|BufferedImage
name|image
init|=
name|ImageIO
operator|.
name|read
argument_list|(
name|connection
operator|.
name|getInputStream
argument_list|()
argument_list|)
decl_stmt|;
if|if
condition|(
name|image
operator|.
name|getHeight
argument_list|()
operator|==
name|image
operator|.
name|getWidth
argument_list|()
condition|)
block|{
name|Player
operator|.
name|newTexture
operator|=
name|image
operator|.
name|getSubimage
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
name|image
operator|.
name|getWidth
argument_list|()
argument_list|,
name|image
operator|.
name|getHeight
argument_list|()
operator|/
literal|2
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|Player
operator|.
name|newTexture
operator|=
name|image
operator|.
name|getSubimage
argument_list|(
literal|0
argument_list|,
literal|0
argument_list|,
name|image
operator|.
name|getWidth
argument_list|()
argument_list|,
name|image
operator|.
name|getHeight
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logWarning
argument_list|(
literal|"Error downloading skin."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
finally|finally
block|{
if|if
condition|(
name|connection
operator|!=
literal|null
condition|)
block|{
name|connection
operator|.
name|disconnect
argument_list|()
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

