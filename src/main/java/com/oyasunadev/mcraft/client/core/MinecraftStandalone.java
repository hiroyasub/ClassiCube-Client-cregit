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
name|*
import|;
end_import

begin_import
import|import
name|java
operator|.
name|awt
operator|.
name|*
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
name|BufferedInputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|BufferedOutputStream
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
name|FileOutputStream
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
name|io
operator|.
name|OutputStream
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

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Map
import|;
end_import

begin_comment
comment|/**  * Created with IntelliJ IDEA.  * User: Oliver Yasuna  * Date: 9/30/12  * Time: 7:16 PM  */
end_comment

begin_comment
comment|/**  * Run Minecraft Classic standalone version.  */
end_comment

begin_class
specifier|public
class|class
name|MinecraftStandalone
block|{
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
name|MinecraftStandalone
name|minecraftStandalone
init|=
operator|new
name|MinecraftStandalone
argument_list|()
decl_stmt|;
name|minecraftStandalone
operator|.
name|startMinecraft
argument_list|()
expr_stmt|;
block|}
comment|/** 	 * Default constructor. 	 */
specifier|public
name|MinecraftStandalone
parameter_list|()
block|{
block|}
comment|/** 	 * Start Minecraft Classic. 	 */
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
argument_list|()
expr_stmt|;
block|}
comment|/** 	 * A class representing the Minecraft Classic game. 	 */
specifier|private
class|class
name|MinecraftFrame
extends|extends
name|JFrame
block|{
comment|/** 		 * Default constructor. 		 */
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
comment|// setResizable(false);
name|setDefaultCloseOperation
argument_list|(
name|JFrame
operator|.
name|EXIT_ON_CLOSE
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
name|minecraft
operator|.
name|running
operator|=
literal|false
expr_stmt|;
block|}
block|}
argument_list|)
expr_stmt|;
block|}
comment|/** 		 * Minecraft reference. 		 */
specifier|private
name|Minecraft
name|minecraft
decl_stmt|;
comment|/** 		 * Start Minecraft Classic. 		 */
specifier|public
name|void
name|startMinecraft
parameter_list|()
block|{
name|boolean
name|RunFakeNetwork
init|=
literal|false
decl_stmt|;
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
literal|false
argument_list|,
literal|false
argument_list|)
expr_stmt|;
if|if
condition|(
name|RunFakeNetwork
condition|)
block|{
name|minecraft
operator|.
name|host
operator|=
literal|"127.0.0.1"
expr_stmt|;
name|minecraft
operator|.
name|host
operator|=
name|minecraft
operator|.
name|host
operator|+
literal|":"
operator|+
literal|"25565"
expr_stmt|;
name|minecraft
operator|.
name|session
operator|=
operator|new
name|SessionData
argument_list|(
literal|"Jonty8001"
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
literal|"c0dd4746a88c5785952cd0190e8214a6"
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
literal|"127.0.0.1"
expr_stmt|;
name|minecraft
operator|.
name|port
operator|=
literal|25565
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
operator|new
name|Thread
argument_list|(
operator|new
name|Runnable
argument_list|()
block|{
specifier|public
name|void
name|run
parameter_list|()
block|{
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
name|running
condition|)
block|{
name|minecraft
operator|.
name|shutdown
argument_list|()
expr_stmt|;
name|dispose
argument_list|()
expr_stmt|;
block|}
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|1
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
block|}
block|}
block|}
argument_list|)
operator|.
name|start
argument_list|()
expr_stmt|;
name|boolean
name|pass
init|=
literal|false
decl_stmt|;
while|while
condition|(
operator|!
name|pass
condition|)
block|{
try|try
block|{
name|Thread
operator|.
name|sleep
argument_list|(
literal|1000
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|InterruptedException
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|minecraft
operator|.
name|running
condition|)
block|{
name|pass
operator|=
literal|true
expr_stmt|;
block|}
block|}
comment|// DO SHIT...?
block|}
comment|/** 		 * Override the MinecraftApplet class because we need to fake the 		 * Document Base and Code Base. 		 */
specifier|private
class|class
name|MCraftApplet
extends|extends
name|MinecraftApplet
block|{
comment|/** 			 * Default constructor. 			 */
specifier|public
name|MCraftApplet
parameter_list|()
block|{
name|parameters
operator|=
operator|new
name|HashMap
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
argument_list|()
expr_stmt|;
block|}
comment|/** 			 * Fake the Document Base. 			 *  			 * @return new URL("http://minecraft.net:80/play.jsp") 			 */
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
literal|"http://minecraft.net:80/play.jsp"
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
name|MalformedURLException
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
return|return
literal|null
return|;
block|}
comment|/** 			 * Fake the Code Base. 			 *  			 * @return new URL("http://minecraft.net:80/") 			 */
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
literal|"http://minecraft.net:80/"
argument_list|)
return|;
block|}
catch|catch
parameter_list|(
name|MalformedURLException
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
return|return
literal|null
return|;
block|}
comment|/** 			 * Return our own parameters variable. 			 *  			 * @param name 			 * @return 			 */
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
comment|/** 			 * Use our own parameters map. 			 */
specifier|private
name|Map
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
name|parameters
decl_stmt|;
block|}
comment|/** 		 * A canvas for the Minecraft thread. 		 */
specifier|private
class|class
name|MinecraftCanvas
extends|extends
name|Canvas
block|{
specifier|private
name|Image
name|image
decl_stmt|;
specifier|private
name|Image
name|image2
decl_stmt|;
name|void
name|SetImage
parameter_list|()
throws|throws
name|IOException
block|{
name|image
operator|=
name|ImageIO
operator|.
name|read
argument_list|(
name|getClass
argument_list|()
operator|.
name|getResourceAsStream
argument_list|(
literal|"/resources"
operator|+
literal|"/rsbg.jpg"
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
name|image2
operator|=
name|ImageIO
operator|.
name|read
argument_list|(
name|getClass
argument_list|()
operator|.
name|getResourceAsStream
argument_list|(
literal|"/resources"
operator|+
literal|"/bg.jpg"
argument_list|)
argument_list|)
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
name|OutputStream
name|out
init|=
literal|null
decl_stmt|;
name|URLConnection
name|connection
init|=
literal|null
decl_stmt|;
name|InputStream
name|in
init|=
literal|null
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
name|out
operator|=
operator|new
name|BufferedOutputStream
argument_list|(
operator|new
name|FileOutputStream
argument_list|(
name|localFileName
argument_list|)
argument_list|)
expr_stmt|;
name|connection
operator|=
name|url
operator|.
name|openConnection
argument_list|()
expr_stmt|;
comment|//I HAVE to send this or the server responds with 403
name|connection
operator|.
name|setRequestProperty
argument_list|(
literal|"Content-Type"
argument_list|,
literal|"application/x-www-form-urlencoded"
argument_list|)
expr_stmt|;
name|connection
operator|.
name|setRequestProperty
argument_list|(
literal|"Content-Language"
argument_list|,
literal|"en-US"
argument_list|)
expr_stmt|;
name|connection
operator|.
name|setRequestProperty
argument_list|(
literal|"User-Agent"
argument_list|,
literal|"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11"
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
name|setDoInput
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|connection
operator|.
name|setDoOutput
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|in
operator|=
name|connection
operator|.
name|getInputStream
argument_list|()
expr_stmt|;
name|byte
index|[]
name|buffer
init|=
operator|new
name|byte
index|[
literal|1024
index|]
decl_stmt|;
name|int
name|numRead
decl_stmt|;
name|long
name|numWritten
init|=
literal|0
decl_stmt|;
while|while
condition|(
operator|(
name|numRead
operator|=
name|in
operator|.
name|read
argument_list|(
name|buffer
argument_list|)
operator|)
operator|!=
operator|-
literal|1
condition|)
block|{
name|out
operator|.
name|write
argument_list|(
name|buffer
argument_list|,
literal|0
argument_list|,
name|numRead
argument_list|)
expr_stmt|;
name|numWritten
operator|+=
name|numRead
expr_stmt|;
block|}
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|localFileName
operator|+
literal|"\t"
operator|+
name|numWritten
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|exception
parameter_list|)
block|{
name|exception
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
finally|finally
block|{
try|try
block|{
if|if
condition|(
name|in
operator|!=
literal|null
condition|)
block|{
name|in
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|out
operator|!=
literal|null
condition|)
block|{
name|out
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|IOException
name|ioe
parameter_list|)
block|{
block|}
block|}
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
name|e
parameter_list|)
block|{
comment|// TODO Auto-generated catch block
name|e
operator|.
name|printStackTrace
argument_list|()
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
name|Done
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
name|this
operator|.
name|getWidth
argument_list|()
argument_list|,
name|this
operator|.
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
comment|//shadow
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
comment|//normal
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
comment|//shadow
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
comment|//normal
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
name|e
parameter_list|)
block|{
comment|// TODO Auto-generated catch block
name|e
operator|.
name|printStackTrace
argument_list|()
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
name|this
operator|.
name|getWidth
argument_list|()
argument_list|,
name|this
operator|.
name|getHeight
argument_list|()
argument_list|,
literal|null
argument_list|)
expr_stmt|;
block|}
block|}
comment|/** 			 * Default constructor. 			 */
specifier|public
name|MinecraftCanvas
parameter_list|()
block|{
block|}
comment|/** 			 * Start the thread. 			 */
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
comment|/** 			 * Stop the thread. 			 */
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
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|1L
decl_stmt|;
comment|/** 			 * The Minecraft variable. 			 */
specifier|private
name|Minecraft
name|minecraft
decl_stmt|;
comment|/** 			 * The Minecraft thread. 			 */
specifier|private
name|Thread
name|thread
decl_stmt|;
comment|/** 			 * Set the "minecraft" variable. 			 *  			 * @param minecraft 			 *            The new Minecraft variable. 			 */
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
comment|/** 			 * Start the Minecraft client thread. 			 */
specifier|private
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
literal|"Client"
argument_list|)
expr_stmt|;
name|thread
operator|.
name|start
argument_list|()
expr_stmt|;
block|}
block|}
comment|/** 			 * Stop the Minecraft client. 			 */
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
name|running
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
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
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
name|void
name|setImage
parameter_list|(
name|Image
name|image
parameter_list|)
block|{
name|this
operator|.
name|image
operator|=
name|image
expr_stmt|;
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
specifier|public
name|void
name|setImage2
parameter_list|(
name|Image
name|image2
parameter_list|)
block|{
name|this
operator|.
name|image2
operator|=
name|image2
expr_stmt|;
block|}
block|}
block|}
block|}
end_class

end_unit

