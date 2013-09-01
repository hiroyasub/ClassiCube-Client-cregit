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
name|RenderingHints
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
name|javax
operator|.
name|imageio
operator|.
name|ImageIO
import|;
end_import

begin_import
import|import
name|org
operator|.
name|lwjgl
operator|.
name|LWJGLException
import|;
end_import

begin_import
import|import
name|org
operator|.
name|lwjgl
operator|.
name|opengl
operator|.
name|AWTGLCanvas
import|;
end_import

begin_comment
comment|// MinecraftCanvas
end_comment

begin_class
specifier|public
class|class
name|MinecraftApplet$1
extends|extends
name|AWTGLCanvas
block|{
specifier|private
name|BufferedImage
name|image
decl_stmt|;
specifier|private
name|BufferedImage
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
comment|// I HAVE to send this or the server responds with 403
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
comment|/*@Override     public void paint(Graphics g) { 	if (image == null) { 	    try { 		SetImage(); 	    } catch (IOException e) { 		// TODO Auto-generated catch block 		e.printStackTrace(); 	    } 	} 	Graphics2D g2 = (Graphics2D) g; 	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 		RenderingHints.VALUE_ANTIALIAS_ON); 	Font font = new Font("Serif", Font.BOLD, 18); 	g2.setFont(font); 	if (!ResourceDownloadThread.Done) { 	    g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null); 	    font = new Font("Purisa", Font.BOLD, 48); 	    g2.setFont(font); 	    g.setColor(Color.black); 	    g2.drawString("ClassiCube", 12, 50); // shadow 	    g.setColor(Color.white); 	    g2.drawString("ClassiCube", 10, 48); // normal 	    font = new Font("Serif", Font.BOLD, 18); 	    g2.setFont(font); 	    g.setColor(Color.black); 	    g2.drawString(GameSettings.PercentString, 12, 100); // shadow 	    g2.drawString(GameSettings.StatusString, 12, 80); 	    g.setColor(Color.white); 	    g2.drawString(GameSettings.PercentString, 10, 98); // normal 	    g2.drawString(GameSettings.StatusString, 10, 78); 	} else { 	    if (image2 == null) { 		try { 		    SetImage2(); 		} catch (IOException e) { 		    // TODO Auto-generated catch block 		    e.printStackTrace(); 		} 	    } 	    g.drawImage(image2, 0, 0, this.getWidth(), this.getHeight(), null); 	}     }*/
specifier|public
name|MinecraftApplet$1
parameter_list|(
name|MinecraftApplet
name|minecraftApplet
parameter_list|)
throws|throws
name|LWJGLException
block|{
name|this
operator|.
name|applet
operator|=
name|minecraftApplet
expr_stmt|;
block|}
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
name|applet
operator|.
name|startGameThread
argument_list|()
expr_stmt|;
block|}
annotation|@
name|Override
specifier|public
specifier|synchronized
name|void
name|removeNotify
parameter_list|()
block|{
name|applet
operator|.
name|stopGameThread
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
specifier|private
name|MinecraftApplet
name|applet
decl_stmt|;
block|}
end_class

end_unit

