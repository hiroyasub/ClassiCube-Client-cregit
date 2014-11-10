begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|oyasunadev
operator|.
name|mcraft
operator|.
name|client
operator|.
name|core
package|;
end_package

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|BorderLayout
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|Canvas
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|Color
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|Font
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|Graphics
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|Graphics2D
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|Image
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|RenderingHints
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|Toolkit
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|event
operator|.
name|WindowAdapter
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|event
operator|.
name|WindowEvent
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|File
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
name|io
operator|.
name|InputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|MalformedURLException
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

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|URLConnection
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

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Map
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
name|javax
operator|.
name|swing
operator|.
name|JFrame
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|GameSettings
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|LogUtil
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|Minecraft
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|MinecraftApplet
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|ResourceDownloadThread
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|SessionData
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|StreamingUtil
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
name|java
operator|.
name|net
operator|.
name|InetAddress
import|;
end_import

begin_import
import|import
name|java
operator|.
name|net
operator|.
name|UnknownHostException
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
comment|/**  * Run Minecraft Classic standalone version.  */
end_comment

begin_class
specifier|public
class|class
name|ClassiCubeStandalone
block|{
specifier|private
specifier|static
specifier|final
name|String
name|CODE_BASE_URL
init|=
literal|"http://minecraft.net:80/"
decl_stmt|,
name|DOCUMENT_BASE_URL
init|=
literal|"http://minecraft.net:80/play.jsp"
decl_stmt|,
name|BACKGROUND_URL_1
init|=
literal|"http://static.classicube.net/client/rsbg.jpg"
decl_stmt|,
name|BACKGROUND_URL_2
init|=
literal|"http://static.classicube.net/client/bg.jpg"
decl_stmt|;
specifier|private
name|ClassiCubeStandalone
parameter_list|()
block|{
block|}
comment|/**      * The game window.      */
specifier|public
class|class
name|MinecraftFrame
extends|extends
name|JFrame
block|{
comment|/**          * Override the MinecraftApplet class because we need to fake the Document Base and Code          * Base.          */
specifier|public
class|class
name|MCraftApplet
extends|extends
name|MinecraftApplet
block|{
specifier|private
specifier|final
name|Map
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
name|parameters
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
annotation|@
name|Override
specifier|public
name|URL
name|getCodeBase
parameter_list|()
block|{
try|try
block|{
return|return
operator|new
name|URL
argument_list|(
name|CODE_BASE_URL
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
name|MalformedURLException
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error getting applet code base."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
return|return
literal|null
return|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|URL
name|getDocumentBase
parameter_list|()
block|{
try|try
block|{
return|return
operator|new
name|URL
argument_list|(
name|DOCUMENT_BASE_URL
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
name|MalformedURLException
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error getting applet document base."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
return|return
literal|null
return|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|String
name|getParameter
parameter_list|(
name|String
name|name
parameter_list|)
block|{
return|return
name|parameters
operator|.
name|get
argument_list|(
name|name
argument_list|)
return|;
block|}
block|}
comment|/**          * A canvas for the Minecraft thread.          */
specifier|public
class|class
name|MinecraftCanvas
extends|extends
name|Canvas
block|{
specifier|public
name|Image
name|image
decl_stmt|;
specifier|private
name|Image
name|image2
decl_stmt|;
specifier|private
name|Minecraft
name|minecraft
decl_stmt|;
comment|/**              * The Minecraft thread.              */
specifier|private
name|Thread
name|thread
decl_stmt|;
comment|/**              * Start the thread.              */
annotation|@
name|Override
specifier|public
specifier|synchronized
name|void
name|addNotify
parameter_list|()
block|{
name|super
operator|.
name|addNotify
argument_list|()
expr_stmt|;
name|startThread
argument_list|()
expr_stmt|;
block|}
specifier|public
name|void
name|download
parameter_list|(
name|String
name|address
parameter_list|,
name|String
name|localFileName
parameter_list|)
block|{
name|URLConnection
name|connection
decl_stmt|;
try|try
block|{
name|URL
name|url
init|=
operator|new
name|URL
argument_list|(
name|address
argument_list|)
decl_stmt|;
name|connection
operator|=
name|url
operator|.
name|openConnection
argument_list|()
expr_stmt|;
name|connection
operator|.
name|setRequestProperty
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
try|try
init|(
name|InputStream
name|in
init|=
name|connection
operator|.
name|getInputStream
argument_list|()
init|)
block|{
name|StreamingUtil
operator|.
name|copyStreamToFile
argument_list|(
name|in
argument_list|,
operator|new
name|File
argument_list|(
name|localFileName
argument_list|)
argument_list|)
expr_stmt|;
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
name|logError
argument_list|(
literal|"Error downloading an applet resource."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|Image
name|getImage
parameter_list|()
block|{
return|return
name|image
return|;
block|}
specifier|public
name|Image
name|getImage2
parameter_list|()
block|{
return|return
name|image2
return|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|paint
parameter_list|(
name|Graphics
name|g
parameter_list|)
block|{
if|if
condition|(
name|image
operator|==
literal|null
condition|)
block|{
try|try
block|{
name|SetImage
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error setting applet background image."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
name|Graphics2D
name|g2
init|=
operator|(
name|Graphics2D
operator|)
name|g
decl_stmt|;
name|g2
operator|.
name|setRenderingHint
argument_list|(
name|RenderingHints
operator|.
name|KEY_ANTIALIASING
argument_list|,
name|RenderingHints
operator|.
name|VALUE_ANTIALIAS_ON
argument_list|)
expr_stmt|;
name|Font
name|font
init|=
operator|new
name|Font
argument_list|(
literal|"Serif"
argument_list|,
name|Font
operator|.
name|BOLD
argument_list|,
literal|18
argument_list|)
decl_stmt|;
name|g2
operator|.
name|setFont
argument_list|(
name|font
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|ResourceDownloadThread
operator|.
name|done
condition|)
block|{
name|g
operator|.
name|drawImage
argument_list|(
name|getImage
argument_list|()
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
name|getWidth
argument_list|()
argument_list|,
name|getHeight
argument_list|()
argument_list|,
literal|null
argument_list|)
expr_stmt|;
name|font
operator|=
operator|new
name|Font
argument_list|(
literal|"Purisa"
argument_list|,
name|Font
operator|.
name|BOLD
argument_list|,
literal|48
argument_list|)
expr_stmt|;
name|g2
operator|.
name|setFont
argument_list|(
name|font
argument_list|)
expr_stmt|;
name|g
operator|.
name|setColor
argument_list|(
name|Color
operator|.
name|black
argument_list|)
expr_stmt|;
name|g2
operator|.
name|drawString
argument_list|(
literal|"ClassiCube"
argument_list|,
literal|12
argument_list|,
literal|50
argument_list|)
expr_stmt|;
comment|// shadow
name|g
operator|.
name|setColor
argument_list|(
name|Color
operator|.
name|white
argument_list|)
expr_stmt|;
name|g2
operator|.
name|drawString
argument_list|(
literal|"ClassiCube"
argument_list|,
literal|10
argument_list|,
literal|48
argument_list|)
expr_stmt|;
comment|// normal
name|font
operator|=
operator|new
name|Font
argument_list|(
literal|"Serif"
argument_list|,
name|Font
operator|.
name|BOLD
argument_list|,
literal|18
argument_list|)
expr_stmt|;
name|g2
operator|.
name|setFont
argument_list|(
name|font
argument_list|)
expr_stmt|;
name|g
operator|.
name|setColor
argument_list|(
name|Color
operator|.
name|black
argument_list|)
expr_stmt|;
name|g2
operator|.
name|drawString
argument_list|(
name|GameSettings
operator|.
name|PercentString
argument_list|,
literal|12
argument_list|,
literal|100
argument_list|)
expr_stmt|;
comment|// shadow
name|g2
operator|.
name|drawString
argument_list|(
name|GameSettings
operator|.
name|StatusString
argument_list|,
literal|12
argument_list|,
literal|80
argument_list|)
expr_stmt|;
name|g
operator|.
name|setColor
argument_list|(
name|Color
operator|.
name|white
argument_list|)
expr_stmt|;
name|g2
operator|.
name|drawString
argument_list|(
name|GameSettings
operator|.
name|PercentString
argument_list|,
literal|10
argument_list|,
literal|98
argument_list|)
expr_stmt|;
comment|// normal
name|g2
operator|.
name|drawString
argument_list|(
name|GameSettings
operator|.
name|StatusString
argument_list|,
literal|10
argument_list|,
literal|78
argument_list|)
expr_stmt|;
block|}
else|else
block|{
if|if
condition|(
name|image2
operator|==
literal|null
condition|)
block|{
try|try
block|{
name|SetImage2
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|ex
parameter_list|)
block|{
name|LogUtil
operator|.
name|logError
argument_list|(
literal|"Error setting applet background image #2."
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
name|g
operator|.
name|drawImage
argument_list|(
name|getImage2
argument_list|()
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
name|getWidth
argument_list|()
argument_list|,
name|getHeight
argument_list|()
argument_list|,
literal|null
argument_list|)
expr_stmt|;
block|}
block|}
comment|/**              * Stop the thread.              */
annotation|@
name|Override
specifier|public
specifier|synchronized
name|void
name|removeNotify
parameter_list|()
block|{
name|stopThread
argument_list|()
expr_stmt|;
name|super
operator|.
name|removeNotify
argument_list|()
expr_stmt|;
block|}
name|void
name|SetImage
parameter_list|()
throws|throws
name|IOException
block|{
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|getMinecraftDirectory
argument_list|()
operator|.
name|getPath
argument_list|()
operator|+
literal|"/rsbg.jpg"
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
name|file
operator|.
name|exists
argument_list|()
condition|)
block|{
name|download
argument_list|(
name|BACKGROUND_URL_1
argument_list|,
name|file
operator|.
name|getAbsolutePath
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|image
operator|=
name|ImageIO
operator|.
name|read
argument_list|(
operator|new
name|File
argument_list|(
name|file
operator|.
name|getAbsolutePath
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|void
name|SetImage2
parameter_list|()
throws|throws
name|IOException
block|{
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|getMinecraftDirectory
argument_list|()
operator|.
name|getPath
argument_list|()
operator|+
literal|"/bg.jpg"
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
name|file
operator|.
name|exists
argument_list|()
condition|)
block|{
name|download
argument_list|(
name|BACKGROUND_URL_2
argument_list|,
name|file
operator|.
name|getAbsolutePath
argument_list|()
argument_list|)
expr_stmt|;
block|}
name|image2
operator|=
name|ImageIO
operator|.
name|read
argument_list|(
operator|new
name|File
argument_list|(
name|file
operator|.
name|getAbsolutePath
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
block|}
comment|/**              * Set the "minecraft" variable.              *              * @param minecraft The new Minecraft variable.              */
specifier|public
name|void
name|setMinecraft
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|)
block|{
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
block|}
comment|/**              * Start the Minecraft client thread.              */
specifier|public
specifier|synchronized
name|void
name|startThread
parameter_list|()
block|{
if|if
condition|(
name|thread
operator|==
literal|null
condition|)
block|{
name|thread
operator|=
operator|new
name|Thread
argument_list|(
name|minecraft
argument_list|,
literal|"GameLoop-Standalone"
argument_list|)
expr_stmt|;
name|thread
operator|.
name|start
argument_list|()
expr_stmt|;
block|}
block|}
comment|/**              * Stop the Minecraft client.              */
specifier|private
specifier|synchronized
name|void
name|stopThread
parameter_list|()
block|{
if|if
condition|(
name|thread
operator|!=
literal|null
condition|)
block|{
name|minecraft
operator|.
name|isRunning
operator|=
literal|false
expr_stmt|;
try|try
block|{
name|thread
operator|.
name|join
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|ex
parameter_list|)
block|{
name|minecraft
operator|.
name|shutdown
argument_list|()
expr_stmt|;
block|}
name|thread
operator|=
literal|null
expr_stmt|;
block|}
block|}
block|}
specifier|private
name|Minecraft
name|minecraft
decl_stmt|;
specifier|public
name|MinecraftFrame
parameter_list|()
block|{
name|setSize
argument_list|(
literal|1024
argument_list|,
literal|512
argument_list|)
expr_stmt|;
name|setDefaultCloseOperation
argument_list|(
name|JFrame
operator|.
name|DO_NOTHING_ON_CLOSE
argument_list|)
expr_stmt|;
name|setLayout
argument_list|(
operator|new
name|BorderLayout
argument_list|()
argument_list|)
expr_stmt|;
name|addWindowListener
argument_list|(
operator|new
name|WindowAdapter
argument_list|()
block|{
annotation|@
name|Override
specifier|public
name|void
name|windowClosing
parameter_list|(
name|WindowEvent
name|e
parameter_list|)
block|{
name|setVisible
argument_list|(
literal|false
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|isRunning
operator|=
literal|false
expr_stmt|;
block|}
block|}
argument_list|)
expr_stmt|;
block|}
comment|/**          * Starts Minecraft Classic          *          * @param player Player name          * @param server Server address          * @param mppass The player's MPPass          * @param port Server port          * @param skinServer The URL of the skin server.          * @param fullscreen True if the game should be in fullScreen.          */
specifier|public
name|void
name|startMinecraft
parameter_list|(
name|String
name|player
parameter_list|,
name|String
name|server
parameter_list|,
name|String
name|mppass
parameter_list|,
name|int
name|port
parameter_list|,
name|String
name|skinServer
parameter_list|,
name|boolean
name|fullscreen
parameter_list|)
block|{
name|MCraftApplet
name|applet
init|=
operator|new
name|MCraftApplet
argument_list|()
decl_stmt|;
specifier|final
name|MinecraftCanvas
name|canvas
init|=
operator|new
name|MinecraftCanvas
argument_list|()
decl_stmt|;
name|minecraft
operator|=
operator|new
name|Minecraft
argument_list|(
name|canvas
argument_list|,
name|applet
argument_list|,
name|getWidth
argument_list|()
argument_list|,
name|getHeight
argument_list|()
argument_list|,
name|fullscreen
argument_list|,
literal|false
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|session
operator|=
operator|new
name|SessionData
argument_list|(
name|player
argument_list|,
literal|"noidea"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|session
operator|.
name|mppass
operator|=
name|mppass
expr_stmt|;
name|minecraft
operator|.
name|session
operator|.
name|haspaid
operator|=
literal|true
expr_stmt|;
name|minecraft
operator|.
name|server
operator|=
name|server
expr_stmt|;
name|minecraft
operator|.
name|port
operator|=
name|port
expr_stmt|;
if|if
condition|(
name|skinServer
operator|!=
literal|null
condition|)
block|{
name|minecraft
operator|.
name|skinServer
operator|=
name|skinServer
expr_stmt|;
block|}
if|if
condition|(
name|player
operator|==
literal|null
operator|&&
name|server
operator|==
literal|null
operator|&&
name|mppass
operator|==
literal|null
condition|)
block|{
name|minecraft
operator|.
name|session
operator|=
literal|null
expr_stmt|;
block|}
name|canvas
operator|.
name|setMinecraft
argument_list|(
name|minecraft
argument_list|)
expr_stmt|;
name|canvas
operator|.
name|setSize
argument_list|(
name|getSize
argument_list|()
argument_list|)
expr_stmt|;
name|add
argument_list|(
name|canvas
argument_list|,
literal|"Center"
argument_list|)
expr_stmt|;
name|canvas
operator|.
name|setFocusable
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|pack
argument_list|()
expr_stmt|;
name|setLocation
argument_list|(
operator|(
name|Toolkit
operator|.
name|getDefaultToolkit
argument_list|()
operator|.
name|getScreenSize
argument_list|()
operator|.
name|width
operator|-
name|getWidth
argument_list|()
operator|)
operator|/
literal|2
argument_list|,
operator|(
name|Toolkit
operator|.
name|getDefaultToolkit
argument_list|()
operator|.
name|getScreenSize
argument_list|()
operator|.
name|height
operator|-
name|getHeight
argument_list|()
operator|)
operator|/
literal|2
argument_list|)
expr_stmt|;
name|setVisible
argument_list|(
literal|true
argument_list|)
expr_stmt|;
while|while
condition|(
literal|true
condition|)
block|{
if|if
condition|(
operator|!
name|minecraft
operator|.
name|isRunning
condition|)
block|{
name|minecraft
operator|.
name|shutdown
argument_list|()
expr_stmt|;
name|System
operator|.
name|exit
argument_list|(
literal|0
argument_list|)
expr_stmt|;
block|}
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|10
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|ex
parameter_list|)
block|{
block|}
block|}
block|}
block|}
specifier|public
specifier|static
name|String
index|[]
name|storedArgs
decl_stmt|;
specifier|public
specifier|static
name|void
name|main
parameter_list|(
name|String
index|[]
name|args
parameter_list|)
block|{
name|storedArgs
operator|=
name|args
expr_stmt|;
name|String
name|player
init|=
literal|null
decl_stmt|;
name|String
name|server
init|=
literal|null
decl_stmt|;
name|int
name|port
init|=
literal|0
decl_stmt|;
name|String
name|mppass
init|=
literal|null
decl_stmt|;
name|String
name|skinServer
init|=
literal|null
decl_stmt|;
name|boolean
name|startFullScreen
init|=
literal|false
decl_stmt|;
try|try
block|{
if|if
condition|(
name|args
operator|!=
literal|null
operator|&&
name|args
operator|.
name|length
operator|<=
literal|3
condition|)
block|{
comment|// direct-connect URL
name|ServerJoinInfo
name|details
init|=
name|getDetailsFromDirectUrl
argument_list|(
name|args
index|[
literal|0
index|]
argument_list|)
decl_stmt|;
if|if
condition|(
name|details
operator|!=
literal|null
condition|)
block|{
name|server
operator|=
name|details
operator|.
name|address
operator|.
name|getHostAddress
argument_list|()
expr_stmt|;
name|port
operator|=
name|details
operator|.
name|port
expr_stmt|;
name|player
operator|=
name|details
operator|.
name|playerName
expr_stmt|;
name|mppass
operator|=
name|details
operator|.
name|pass
expr_stmt|;
block|}
if|if
condition|(
name|args
operator|.
name|length
operator|>
literal|1
condition|)
block|{
name|skinServer
operator|=
name|args
index|[
literal|1
index|]
expr_stmt|;
block|}
if|if
condition|(
name|args
operator|.
name|length
operator|>
literal|2
condition|)
block|{
name|startFullScreen
operator|=
name|Boolean
operator|.
name|parseBoolean
argument_list|(
name|args
index|[
literal|2
index|]
argument_list|)
expr_stmt|;
block|}
block|}
if|else if
condition|(
name|args
operator|!=
literal|null
operator|&&
name|args
operator|.
name|length
operator|>
literal|3
condition|)
block|{
name|server
operator|=
name|args
index|[
literal|0
index|]
expr_stmt|;
name|port
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|args
index|[
literal|1
index|]
argument_list|)
expr_stmt|;
name|player
operator|=
name|args
index|[
literal|2
index|]
expr_stmt|;
name|mppass
operator|=
name|args
index|[
literal|3
index|]
expr_stmt|;
if|if
condition|(
name|args
operator|.
name|length
operator|>
literal|4
condition|)
block|{
name|skinServer
operator|=
name|args
index|[
literal|4
index|]
expr_stmt|;
block|}
if|if
condition|(
name|args
operator|.
name|length
operator|>
literal|5
condition|)
block|{
name|startFullScreen
operator|=
name|Boolean
operator|.
name|parseBoolean
argument_list|(
name|args
index|[
literal|5
index|]
argument_list|)
expr_stmt|;
block|}
block|}
name|ClassiCubeStandalone
name|classicubeStandalone
init|=
operator|new
name|ClassiCubeStandalone
argument_list|()
decl_stmt|;
if|if
condition|(
name|player
operator|==
literal|null
operator|||
name|server
operator|==
literal|null
operator|||
name|mppass
operator|==
literal|null
operator|||
name|port
operator|<=
literal|0
condition|)
block|{
name|classicubeStandalone
operator|.
name|startMinecraft
argument_list|(
literal|null
argument_list|,
literal|null
argument_list|,
literal|null
argument_list|,
literal|0
argument_list|,
name|skinServer
argument_list|,
name|startFullScreen
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|classicubeStandalone
operator|.
name|startMinecraft
argument_list|(
name|player
argument_list|,
name|server
argument_list|,
name|mppass
argument_list|,
name|port
argument_list|,
name|skinServer
argument_list|,
name|startFullScreen
argument_list|)
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|System
operator|.
name|err
operator|.
name|println
argument_list|(
literal|"ClassiCube client: Cannot parse parameters: "
operator|+
name|e
operator|.
name|getMessage
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|startMinecraft
parameter_list|()
block|{
name|MinecraftFrame
name|minecraftFrame
init|=
operator|new
name|MinecraftFrame
argument_list|()
decl_stmt|;
name|minecraftFrame
operator|.
name|startMinecraft
argument_list|(
literal|null
argument_list|,
literal|null
argument_list|,
literal|null
argument_list|,
literal|0
argument_list|,
literal|null
argument_list|,
literal|false
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|startMinecraft
parameter_list|(
name|String
name|player
parameter_list|,
name|String
name|server
parameter_list|,
name|String
name|mppass
parameter_list|,
name|int
name|port
parameter_list|,
name|String
name|skinServer
parameter_list|,
name|boolean
name|fullscreen
parameter_list|)
block|{
name|MinecraftFrame
name|minecraftFrame
init|=
operator|new
name|MinecraftFrame
argument_list|()
decl_stmt|;
name|minecraftFrame
operator|.
name|startMinecraft
argument_list|(
name|player
argument_list|,
name|server
argument_list|,
name|mppass
argument_list|,
name|port
argument_list|,
name|skinServer
argument_list|,
name|fullscreen
argument_list|)
expr_stmt|;
block|}
comment|// Direct URL parsing
specifier|private
specifier|static
specifier|final
name|String
name|directUrlPattern
init|=
literal|"^mc://"
comment|// scheme
operator|+
literal|"(localhost|(\\d{1,3}\\.){3}\\d{1,3}|([a-zA-Z0-9\\-]+\\.)+([a-zA-Z0-9\\-]+))"
comment|// host/IP
operator|+
literal|"(:(\\d{1,5}))?/"
comment|// port
operator|+
literal|"([^/]+)"
comment|// username
operator|+
literal|"(/(.*))?$"
decl_stmt|;
comment|// mppass
specifier|private
specifier|static
specifier|final
name|Pattern
name|directUrlRegex
init|=
name|Pattern
operator|.
name|compile
argument_list|(
name|directUrlPattern
argument_list|)
decl_stmt|;
specifier|private
specifier|static
specifier|final
name|String
name|BLANK_MPPASS
init|=
literal|"00000000000000000000000000000000"
decl_stmt|;
specifier|private
specifier|static
name|ServerJoinInfo
name|getDetailsFromDirectUrl
parameter_list|(
specifier|final
name|String
name|url
parameter_list|)
block|{
if|if
condition|(
name|url
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|NullPointerException
argument_list|(
literal|"url"
argument_list|)
throw|;
block|}
name|ServerJoinInfo
name|result
init|=
operator|new
name|ServerJoinInfo
argument_list|()
decl_stmt|;
specifier|final
name|Matcher
name|directUrlMatch
init|=
name|directUrlRegex
operator|.
name|matcher
argument_list|(
name|url
argument_list|)
decl_stmt|;
if|if
condition|(
name|directUrlMatch
operator|.
name|matches
argument_list|()
condition|)
block|{
try|try
block|{
name|result
operator|.
name|address
operator|=
name|InetAddress
operator|.
name|getByName
argument_list|(
name|directUrlMatch
operator|.
name|group
argument_list|(
literal|1
argument_list|)
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
specifier|final
name|UnknownHostException
name|ex
parameter_list|)
block|{
return|return
literal|null
return|;
block|}
specifier|final
name|String
name|portNum
init|=
name|directUrlMatch
operator|.
name|group
argument_list|(
literal|6
argument_list|)
decl_stmt|;
if|if
condition|(
name|portNum
operator|!=
literal|null
operator|&&
name|portNum
operator|.
name|length
argument_list|()
operator|>
literal|0
condition|)
block|{
try|try
block|{
name|result
operator|.
name|port
operator|=
name|Integer
operator|.
name|parseInt
argument_list|(
name|portNum
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
specifier|final
name|NumberFormatException
name|ex
parameter_list|)
block|{
return|return
literal|null
return|;
block|}
block|}
else|else
block|{
name|result
operator|.
name|port
operator|=
literal|25565
expr_stmt|;
block|}
name|result
operator|.
name|playerName
operator|=
name|directUrlMatch
operator|.
name|group
argument_list|(
literal|7
argument_list|)
expr_stmt|;
specifier|final
name|String
name|mppass
init|=
name|directUrlMatch
operator|.
name|group
argument_list|(
literal|9
argument_list|)
decl_stmt|;
if|if
condition|(
name|mppass
operator|!=
literal|null
operator|&&
name|mppass
operator|.
name|length
argument_list|()
operator|>
literal|0
condition|)
block|{
name|result
operator|.
name|pass
operator|=
name|mppass
expr_stmt|;
block|}
else|else
block|{
name|result
operator|.
name|pass
operator|=
name|BLANK_MPPASS
expr_stmt|;
block|}
return|return
name|result
return|;
block|}
return|return
literal|null
return|;
block|}
specifier|static
class|class
name|ServerJoinInfo
block|{
specifier|public
name|String
name|playerName
decl_stmt|;
specifier|public
name|InetAddress
name|address
decl_stmt|;
specifier|public
name|int
name|port
decl_stmt|;
specifier|public
name|String
name|pass
decl_stmt|;
block|}
block|}
end_class

end_unit

