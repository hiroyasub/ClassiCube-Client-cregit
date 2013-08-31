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
name|HUDScreen
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
name|net
operator|.
name|PacketType
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
name|render
operator|.
name|ShapeRenderer
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
name|Display
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
name|GL11
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|BufferedReader
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
name|FileInputStream
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
name|InputStreamReader
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|OutputStreamWriter
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
name|net
operator|.
name|URLEncoder
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|channels
operator|.
name|FileChannel
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
name|zip
operator|.
name|GZIPInputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|zip
operator|.
name|InflaterInputStream
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|ProgressBarDisplay
block|{
specifier|public
name|String
name|text
init|=
literal|""
decl_stmt|;
specifier|private
name|Minecraft
name|minecraft
decl_stmt|;
specifier|public
name|String
name|title
init|=
literal|""
decl_stmt|;
specifier|private
name|long
name|start
init|=
name|System
operator|.
name|currentTimeMillis
argument_list|()
decl_stmt|;
specifier|public
name|ProgressBarDisplay
parameter_list|(
name|Minecraft
name|var1
parameter_list|)
block|{
name|this
operator|.
name|minecraft
operator|=
name|var1
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|setTitle
parameter_list|(
name|String
name|var1
parameter_list|)
block|{
if|if
condition|(
operator|!
name|this
operator|.
name|minecraft
operator|.
name|running
condition|)
block|{
throw|throw
operator|new
name|StopGameException
argument_list|()
throw|;
block|}
else|else
block|{
name|this
operator|.
name|title
operator|=
name|var1
expr_stmt|;
name|int
name|var3
init|=
name|this
operator|.
name|minecraft
operator|.
name|width
operator|*
literal|240
operator|/
name|this
operator|.
name|minecraft
operator|.
name|height
decl_stmt|;
name|int
name|var2
init|=
name|this
operator|.
name|minecraft
operator|.
name|height
operator|*
literal|240
operator|/
name|this
operator|.
name|minecraft
operator|.
name|height
decl_stmt|;
name|GL11
operator|.
name|glClear
argument_list|(
literal|256
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glMatrixMode
argument_list|(
literal|5889
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glLoadIdentity
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glOrtho
argument_list|(
literal|0.0D
argument_list|,
operator|(
name|double
operator|)
name|var3
argument_list|,
operator|(
name|double
operator|)
name|var2
argument_list|,
literal|0.0D
argument_list|,
literal|100.0D
argument_list|,
literal|300.0D
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glMatrixMode
argument_list|(
literal|5888
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glLoadIdentity
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
operator|-
literal|200.0F
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|static
name|String
name|terrainId
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|String
name|sideId
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|String
name|edgeId
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|HashMap
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
name|serverConfig
init|=
operator|new
name|HashMap
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
argument_list|()
decl_stmt|;
annotation|@
name|SuppressWarnings
argument_list|(
literal|"deprecation"
argument_list|)
specifier|private
name|boolean
name|passServerCommand
parameter_list|(
name|String
name|lineText
parameter_list|)
block|{
if|if
condition|(
name|lineText
operator|==
literal|null
condition|)
return|return
literal|false
return|;
if|if
condition|(
name|lineText
operator|.
name|contains
argument_list|(
literal|"cfg="
argument_list|)
condition|)
block|{
name|int
name|i
init|=
name|lineText
operator|.
name|indexOf
argument_list|(
literal|"cfg="
argument_list|)
decl_stmt|;
if|if
condition|(
name|i
operator|>
operator|-
literal|1
condition|)
block|{
name|String
name|splitlineText
init|=
name|lineText
operator|.
name|substring
argument_list|(
name|i
operator|+
literal|4
argument_list|)
operator|.
name|split
argument_list|(
literal|" "
argument_list|)
index|[
literal|0
index|]
decl_stmt|;
name|String
name|Url
init|=
literal|"http://"
operator|+
name|splitlineText
operator|.
name|replace
argument_list|(
literal|"$U"
argument_list|,
name|this
operator|.
name|minecraft
operator|.
name|session
operator|.
name|username
argument_list|)
decl_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Fetching config from: "
operator|+
name|Url
argument_list|)
expr_stmt|;
name|serverConfig
operator|=
name|fetchConfig
argument_list|(
name|Url
argument_list|)
expr_stmt|;
if|if
condition|(
name|serverConfig
operator|.
name|containsKey
argument_list|(
literal|"server.detail"
argument_list|)
condition|)
block|{
try|try
block|{
name|String
name|str
init|=
name|serverConfig
operator|.
name|get
argument_list|(
literal|"server.detail"
argument_list|)
decl_stmt|;
name|this
operator|.
name|text
operator|=
name|str
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
name|e
operator|.
name|getMessage
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
else|else
return|return
literal|false
return|;
comment|// return false if no "cfg=" was found
if|if
condition|(
name|serverConfig
operator|.
name|containsKey
argument_list|(
literal|"server.sendwomid"
argument_list|)
condition|)
block|{
name|byte
index|[]
name|b
init|=
operator|new
name|byte
index|[
literal|66
index|]
decl_stmt|;
name|int
name|i
init|=
literal|0
decl_stmt|;
name|byte
index|[]
name|tempB
init|=
name|b
decl_stmt|;
name|tempB
index|[
name|i
index|]
operator|=
operator|(
operator|(
name|byte
operator|)
operator|(
name|tempB
index|[
name|i
index|]
operator||
literal|0xD
operator|)
operator|)
expr_stmt|;
name|int
name|tempI
init|=
literal|1
decl_stmt|;
name|byte
index|[]
name|tempArr
init|=
name|b
decl_stmt|;
name|tempArr
index|[
name|tempI
index|]
operator|=
operator|(
operator|(
name|byte
operator|)
operator|(
name|tempArr
index|[
name|tempI
index|]
operator||
literal|0xFF
operator|)
operator|)
expr_stmt|;
name|String
name|Command
init|=
literal|"/womid "
operator|+
name|this
operator|.
name|minecraft
operator|.
name|session
operator|.
name|username
decl_stmt|;
name|Command
operator|.
name|getBytes
argument_list|(
literal|0
argument_list|,
name|Command
operator|.
name|length
argument_list|()
argument_list|,
name|b
argument_list|,
literal|2
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|networkManager
operator|.
name|netHandler
operator|.
name|send
argument_list|(
name|PacketType
operator|.
name|CHAT_MESSAGE
argument_list|,
operator|new
name|Object
index|[]
block|{
name|Integer
operator|.
name|valueOf
argument_list|(
operator|-
literal|1
argument_list|)
block|,
name|Command
block|}
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|serverConfig
operator|.
name|containsKey
argument_list|(
literal|"server.name"
argument_list|)
condition|)
block|{
name|HUDScreen
operator|.
name|ServerName
operator|=
name|serverConfig
operator|.
name|get
argument_list|(
literal|"server.name"
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|serverConfig
operator|.
name|containsKey
argument_list|(
literal|"user.detail"
argument_list|)
condition|)
block|{
name|HUDScreen
operator|.
name|UserDetail
operator|=
name|serverConfig
operator|.
name|get
argument_list|(
literal|"user.detail"
argument_list|)
expr_stmt|;
block|}
return|return
literal|true
return|;
block|}
comment|/*      * public static void InitEnv(Minecraft minecraft) { if (serverConfig ==      * null) return; int i1; if (serverConfig.containsKey("environment.level"))      * { i1 = Integer.parseInt(serverConfig.get("environment.level")); if (i1>=      * 0) { minecraft.level.waterLevel = i1;      * System.out.println("Changing water level to " + i1); } }      *       * if (serverConfig.containsKey("environment.fog")) { i1 =      * Integer.parseInt(serverConfig.get("environment.fog")); if (i1>= 0) {      * minecraft.level.fogColor = i1;      * System.out.println("Changing fog colour to " + i1); } }      *       * if (serverConfig.containsKey("environment.sky")) { i1 =      * Integer.parseInt(serverConfig.get("environment.sky")); if (i1>= 0) {      * minecraft.level.skyColor = i1;      * System.out.println("Changing sky colour to " + i1); } }      *       * if (serverConfig.containsKey("environment.cloud")) { i1 =      * Integer.parseInt(serverConfig.get("environment.cloud")); if (i1>= 0) {      * minecraft.level.cloudColor = i1;      * System.out.println("Changing cloud colour to " + i1); } }      *       * }      */
specifier|public
specifier|static
name|HashMap
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
name|fetchConfig
parameter_list|(
name|String
name|location
parameter_list|)
block|{
name|HashMap
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
name|localHashMap
init|=
operator|new
name|HashMap
argument_list|<
name|String
argument_list|,
name|String
argument_list|>
argument_list|()
decl_stmt|;
try|try
block|{
name|URLConnection
name|urlConnection
init|=
name|makeConnection
argument_list|(
name|location
argument_list|,
literal|""
argument_list|)
decl_stmt|;
name|InputStream
name|localInputStream
init|=
name|getInputStream
argument_list|(
name|urlConnection
argument_list|)
decl_stmt|;
name|BufferedReader
name|bufferedReader
init|=
operator|new
name|BufferedReader
argument_list|(
operator|new
name|InputStreamReader
argument_list|(
name|localInputStream
argument_list|)
argument_list|)
decl_stmt|;
name|String
name|str
decl_stmt|;
while|while
condition|(
operator|(
name|str
operator|=
name|bufferedReader
operator|.
name|readLine
argument_list|()
operator|)
operator|!=
literal|null
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
operator|new
name|StringBuilder
argument_list|()
operator|.
name|append
argument_list|(
literal|"Read line: "
argument_list|)
operator|.
name|append
argument_list|(
name|str
argument_list|)
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
name|String
index|[]
name|arrayOfString
init|=
name|str
operator|.
name|split
argument_list|(
literal|"="
argument_list|,
literal|2
argument_list|)
decl_stmt|;
if|if
condition|(
name|arrayOfString
operator|.
name|length
operator|>
literal|1
condition|)
block|{
name|localHashMap
operator|.
name|put
argument_list|(
name|arrayOfString
index|[
literal|0
index|]
operator|.
name|trim
argument_list|()
argument_list|,
name|arrayOfString
index|[
literal|1
index|]
operator|.
name|trim
argument_list|()
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
operator|new
name|StringBuilder
argument_list|()
operator|.
name|append
argument_list|(
literal|"Adding config "
argument_list|)
operator|.
name|append
argument_list|(
name|arrayOfString
index|[
literal|0
index|]
operator|.
name|trim
argument_list|()
argument_list|)
operator|.
name|append
argument_list|(
literal|" = "
argument_list|)
operator|.
name|append
argument_list|(
name|arrayOfString
index|[
literal|1
index|]
operator|.
name|trim
argument_list|()
argument_list|)
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
name|bufferedReader
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|e
parameter_list|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
operator|new
name|StringBuilder
argument_list|()
operator|.
name|append
argument_list|(
literal|"Caught exception: "
argument_list|)
operator|.
name|append
argument_list|(
name|e
argument_list|)
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
block|}
return|return
name|localHashMap
return|;
block|}
annotation|@
name|SuppressWarnings
argument_list|(
block|{
literal|"deprecation"
block|,
literal|"static-access"
block|}
argument_list|)
specifier|public
name|void
name|downloadSkin
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|)
block|{
try|try
block|{
name|File
name|File1
init|=
name|minecraft
operator|.
name|mcDir
decl_stmt|;
comment|// my folder
name|File
name|File2
init|=
operator|new
name|File
argument_list|(
name|File1
argument_list|,
literal|"/resources/Skins"
argument_list|)
decl_stmt|;
comment|// skins
comment|// folder
if|if
condition|(
operator|!
name|File2
operator|.
name|exists
argument_list|()
operator|&&
operator|!
name|File2
operator|.
name|mkdirs
argument_list|()
condition|)
block|{
comment|// dunno yet
block|}
name|File
name|File3
decl_stmt|;
name|File
name|File4
decl_stmt|;
if|if
condition|(
name|terrainId
operator|!=
literal|null
condition|)
if|if
condition|(
name|terrainId
operator|!=
literal|""
condition|)
block|{
name|terrainId
operator|=
name|terrainId
operator|.
name|replaceAll
argument_list|(
literal|"[^0-9a-fA-F]+"
argument_list|,
literal|""
argument_list|)
expr_stmt|;
name|File3
operator|=
operator|new
name|File
argument_list|(
name|File2
argument_list|,
literal|"terrain.png"
argument_list|)
expr_stmt|;
name|File4
operator|=
operator|new
name|File
argument_list|(
name|File2
argument_list|,
literal|"terrain-"
operator|+
name|terrainId
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|File4
operator|.
name|exists
argument_list|()
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Fetching file to "
operator|+
name|File4
argument_list|)
expr_stmt|;
name|fetchUrl
argument_list|(
name|File4
argument_list|,
literal|"http://files.worldofminecraft.com/skin.php?type=terrain&id="
operator|+
name|URLEncoder
operator|.
name|encode
argument_list|(
name|terrainId
argument_list|)
argument_list|,
literal|""
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Fetched file to "
operator|+
name|File4
argument_list|)
expr_stmt|;
block|}
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Copying "
operator|+
name|File4
operator|+
literal|" to "
operator|+
name|File3
argument_list|)
expr_stmt|;
name|copyFile
argument_list|(
name|File4
argument_list|,
name|File3
argument_list|)
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Copied "
operator|+
name|File4
operator|+
literal|" to "
operator|+
name|File3
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|levelRenderer
operator|.
name|refresh
argument_list|()
expr_stmt|;
block|}
if|if
condition|(
name|edgeId
operator|!=
literal|null
condition|)
block|{
name|edgeId
operator|=
name|edgeId
operator|.
name|replaceAll
argument_list|(
literal|"[^0-9a-fA-F]+"
argument_list|,
literal|""
argument_list|)
expr_stmt|;
name|File3
operator|=
operator|new
name|File
argument_list|(
name|File2
argument_list|,
literal|"water.png"
argument_list|)
expr_stmt|;
name|File4
operator|=
operator|new
name|File
argument_list|(
name|File2
argument_list|,
literal|"edge-"
operator|+
name|edgeId
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|File4
operator|.
name|exists
argument_list|()
condition|)
block|{
name|fetchUrl
argument_list|(
name|File4
argument_list|,
literal|"http://files.worldofminecraft.com/skin.php?type=edge&id="
operator|+
name|URLEncoder
operator|.
name|encode
argument_list|(
name|edgeId
argument_list|)
argument_list|,
literal|""
argument_list|)
expr_stmt|;
block|}
name|copyFile
argument_list|(
name|File4
argument_list|,
name|File3
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|sideId
operator|!=
literal|null
condition|)
block|{
name|sideId
operator|=
name|sideId
operator|.
name|replaceAll
argument_list|(
literal|"[^0-9a-fA-F]+"
argument_list|,
literal|""
argument_list|)
expr_stmt|;
name|File3
operator|=
operator|new
name|File
argument_list|(
name|File2
argument_list|,
literal|"rock.png"
argument_list|)
expr_stmt|;
name|File4
operator|=
operator|new
name|File
argument_list|(
name|File2
argument_list|,
literal|"side-"
operator|+
name|sideId
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|File4
operator|.
name|exists
argument_list|()
condition|)
block|{
name|fetchUrl
argument_list|(
name|File4
argument_list|,
literal|"http://files.worldofminecraft.com/skin.php?type=side&id="
operator|+
name|URLEncoder
operator|.
name|encode
argument_list|(
name|sideId
argument_list|)
argument_list|,
literal|""
argument_list|)
expr_stmt|;
block|}
name|copyFile
argument_list|(
name|File4
argument_list|,
name|File3
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
name|out
operator|.
name|println
argument_list|(
name|e
operator|.
name|getMessage
argument_list|()
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|static
name|void
name|copyFile
parameter_list|(
name|File
name|paramFile1
parameter_list|,
name|File
name|paramFile2
parameter_list|)
block|{
name|FileChannel
name|fileChannel1
init|=
literal|null
decl_stmt|;
name|FileChannel
name|fileChannel2
init|=
literal|null
decl_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Copy "
operator|+
name|paramFile1
operator|+
literal|" to "
operator|+
name|paramFile2
argument_list|)
expr_stmt|;
try|try
block|{
if|if
condition|(
operator|!
name|paramFile2
operator|.
name|exists
argument_list|()
condition|)
block|{
name|paramFile2
operator|.
name|createNewFile
argument_list|()
expr_stmt|;
block|}
name|fileChannel1
operator|=
operator|new
name|FileInputStream
argument_list|(
name|paramFile1
argument_list|)
operator|.
name|getChannel
argument_list|()
expr_stmt|;
name|fileChannel2
operator|=
operator|new
name|FileOutputStream
argument_list|(
name|paramFile2
argument_list|)
operator|.
name|getChannel
argument_list|()
expr_stmt|;
name|fileChannel2
operator|.
name|transferFrom
argument_list|(
name|fileChannel1
argument_list|,
literal|0L
argument_list|,
name|fileChannel1
operator|.
name|size
argument_list|()
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|ex
parameter_list|)
block|{
name|paramFile2
operator|.
name|delete
argument_list|()
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"IO Error copying file: "
operator|+
name|ex
argument_list|)
expr_stmt|;
block|}
finally|finally
block|{
try|try
block|{
if|if
condition|(
name|fileChannel1
operator|!=
literal|null
condition|)
name|fileChannel1
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|ex
parameter_list|)
block|{
block|}
try|try
block|{
if|if
condition|(
name|fileChannel2
operator|!=
literal|null
condition|)
name|fileChannel2
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|ex
parameter_list|)
block|{
block|}
block|}
block|}
specifier|private
specifier|static
name|URLConnection
name|makeConnection
parameter_list|(
name|String
name|url
parameter_list|,
name|String
name|s1
parameter_list|,
name|String
name|s2
parameter_list|,
name|boolean
name|AddWomProperty
parameter_list|)
throws|throws
name|IOException
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
operator|new
name|StringBuilder
argument_list|()
operator|.
name|append
argument_list|(
literal|"Making connection to "
argument_list|)
operator|.
name|append
argument_list|(
name|url
argument_list|)
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
name|URLConnection
name|localURLConnection
init|=
operator|new
name|URL
argument_list|(
name|url
argument_list|)
operator|.
name|openConnection
argument_list|()
decl_stmt|;
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"Referer"
argument_list|,
name|s2
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|setReadTimeout
argument_list|(
literal|40000
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|setConnectTimeout
argument_list|(
literal|15000
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|setDoInput
argument_list|(
literal|true
argument_list|)
expr_stmt|;
if|if
condition|(
name|AddWomProperty
condition|)
block|{
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"X-Wom-Version"
argument_list|,
literal|"WoMClient-2.0.8"
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"X-Wom-Username"
argument_list|,
literal|"Greg0001"
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"User-Agent"
argument_list|,
operator|new
name|StringBuilder
argument_list|()
operator|.
name|append
argument_list|(
literal|"WoM/"
argument_list|)
operator|.
name|append
argument_list|(
literal|"WoMClient-2.0.8"
argument_list|)
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"User-Agent"
argument_list|,
literal|"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.7; rv:6.0) Gecko/20100101 Firefox/6.0 FirePHP/0.5"
argument_list|)
expr_stmt|;
block|}
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"Accept"
argument_list|,
literal|"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"Accept-Language"
argument_list|,
literal|"en-us,en;q=0.5"
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"Accept-Encoding"
argument_list|,
literal|"gzip, deflate, compress"
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"Connection"
argument_list|,
literal|"keep-alive"
argument_list|)
expr_stmt|;
if|if
condition|(
name|s1
operator|.
name|length
argument_list|()
operator|>
literal|0
condition|)
block|{
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"Content-Type"
argument_list|,
literal|"application/x-www-form-urlencoded"
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|addRequestProperty
argument_list|(
literal|"Content-Length"
argument_list|,
name|Integer
operator|.
name|toString
argument_list|(
name|s1
operator|.
name|length
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
name|localURLConnection
operator|.
name|setDoOutput
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|OutputStreamWriter
name|localOutputStreamWriter
init|=
operator|new
name|OutputStreamWriter
argument_list|(
name|localURLConnection
operator|.
name|getOutputStream
argument_list|()
argument_list|)
decl_stmt|;
name|localOutputStreamWriter
operator|.
name|write
argument_list|(
name|s1
argument_list|)
expr_stmt|;
name|localOutputStreamWriter
operator|.
name|flush
argument_list|()
expr_stmt|;
name|localOutputStreamWriter
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
name|localURLConnection
operator|.
name|connect
argument_list|()
expr_stmt|;
return|return
name|localURLConnection
return|;
block|}
specifier|private
specifier|static
name|URLConnection
name|makeConnection
parameter_list|(
name|String
name|paramString1
parameter_list|,
name|String
name|paramString2
parameter_list|)
throws|throws
name|IOException
block|{
return|return
name|makeConnection
argument_list|(
name|paramString1
argument_list|,
name|paramString2
argument_list|,
name|paramString1
argument_list|,
literal|true
argument_list|)
return|;
block|}
specifier|private
specifier|static
name|InputStream
name|getInputStream
parameter_list|(
name|URLConnection
name|paramURLConnection
parameter_list|)
throws|throws
name|IOException
block|{
name|Object
name|localObject
init|=
name|paramURLConnection
operator|.
name|getInputStream
argument_list|()
decl_stmt|;
name|String
name|str
init|=
name|paramURLConnection
operator|.
name|getContentEncoding
argument_list|()
decl_stmt|;
if|if
condition|(
name|str
operator|!=
literal|null
condition|)
block|{
name|str
operator|=
name|str
operator|.
name|toLowerCase
argument_list|()
expr_stmt|;
if|if
condition|(
name|str
operator|.
name|contains
argument_list|(
literal|"gzip"
argument_list|)
condition|)
block|{
name|localObject
operator|=
operator|new
name|GZIPInputStream
argument_list|(
operator|(
name|InputStream
operator|)
name|localObject
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|str
operator|.
name|contains
argument_list|(
literal|"deflate"
argument_list|)
condition|)
block|{
name|localObject
operator|=
operator|new
name|InflaterInputStream
argument_list|(
operator|(
name|InputStream
operator|)
name|localObject
argument_list|)
expr_stmt|;
block|}
block|}
return|return
operator|(
name|InputStream
operator|)
name|localObject
return|;
block|}
specifier|public
specifier|static
name|int
name|fetchUrl
parameter_list|(
name|File
name|paramFile
parameter_list|,
name|String
name|paramString1
parameter_list|,
name|String
name|paramString2
parameter_list|)
block|{
try|try
block|{
name|URLConnection
name|localURLConnection
init|=
name|makeConnection
argument_list|(
name|paramString1
argument_list|,
name|paramString2
argument_list|)
decl_stmt|;
name|InputStream
name|localInputStream
init|=
name|getInputStream
argument_list|(
name|localURLConnection
argument_list|)
decl_stmt|;
name|FileOutputStream
name|localFileOutputStream
init|=
operator|new
name|FileOutputStream
argument_list|(
name|paramFile
argument_list|)
decl_stmt|;
name|byte
index|[]
name|arrayOfByte
init|=
operator|new
name|byte
index|[
literal|10240
index|]
decl_stmt|;
name|int
name|i
init|=
literal|0
decl_stmt|;
name|int
name|j
init|=
literal|0
decl_stmt|;
while|while
condition|(
operator|(
name|j
operator|=
name|localInputStream
operator|.
name|read
argument_list|(
name|arrayOfByte
argument_list|,
literal|0
argument_list|,
literal|10240
argument_list|)
operator|)
operator|>=
literal|0
condition|)
block|{
if|if
condition|(
name|j
operator|>
literal|0
condition|)
block|{
name|localFileOutputStream
operator|.
name|write
argument_list|(
name|arrayOfByte
argument_list|,
literal|0
argument_list|,
name|j
argument_list|)
expr_stmt|;
name|i
operator|+=
name|j
expr_stmt|;
block|}
block|}
name|localFileOutputStream
operator|.
name|close
argument_list|()
expr_stmt|;
name|localInputStream
operator|.
name|close
argument_list|()
expr_stmt|;
return|return
name|i
return|;
block|}
catch|catch
parameter_list|(
name|IOException
name|localIOException
parameter_list|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
operator|new
name|StringBuilder
argument_list|()
operator|.
name|append
argument_list|(
literal|"Error fetching "
argument_list|)
operator|.
name|append
argument_list|(
name|paramString1
argument_list|)
operator|.
name|append
argument_list|(
literal|" to file: "
argument_list|)
operator|.
name|append
argument_list|(
name|paramFile
argument_list|)
operator|.
name|append
argument_list|(
literal|": "
argument_list|)
operator|.
name|append
argument_list|(
name|localIOException
argument_list|)
operator|.
name|toString
argument_list|()
argument_list|)
expr_stmt|;
name|paramFile
operator|.
name|delete
argument_list|()
expr_stmt|;
block|}
return|return
literal|0
return|;
block|}
specifier|public
specifier|final
name|void
name|setText
parameter_list|(
name|String
name|var1
parameter_list|)
block|{
if|if
condition|(
operator|!
name|this
operator|.
name|minecraft
operator|.
name|running
condition|)
block|{
throw|throw
operator|new
name|StopGameException
argument_list|()
throw|;
block|}
else|else
block|{
if|if
condition|(
operator|!
name|passServerCommand
argument_list|(
name|var1
argument_list|)
condition|)
block|{
name|this
operator|.
name|text
operator|=
name|var1
expr_stmt|;
block|}
if|if
condition|(
name|minecraft
operator|.
name|HackState
operator|==
literal|null
condition|)
block|{
comment|// change only once per session
if|if
condition|(
name|this
operator|.
name|minecraft
operator|.
name|session
operator|==
literal|null
condition|)
block|{
comment|// presume singleplayer
name|minecraft
operator|.
name|HackState
operator|=
name|HackState
operator|.
name|HacksTagEnabled
expr_stmt|;
return|return;
block|}
if|if
condition|(
name|this
operator|.
name|text
operator|.
name|toLowerCase
argument_list|()
operator|.
name|contains
argument_list|(
literal|"+hax"
argument_list|)
condition|)
block|{
name|minecraft
operator|.
name|HackState
operator|=
name|HackState
operator|.
name|HacksTagEnabled
expr_stmt|;
block|}
if|else if
condition|(
name|this
operator|.
name|text
operator|.
name|toLowerCase
argument_list|()
operator|.
name|contains
argument_list|(
literal|"-hax"
argument_list|)
condition|)
block|{
name|minecraft
operator|.
name|HackState
operator|=
name|HackState
operator|.
name|HacksTagDisabled
expr_stmt|;
name|minecraft
operator|.
name|settings
operator|.
name|CanSpeed
operator|=
literal|false
expr_stmt|;
block|}
if|else if
condition|(
name|this
operator|.
name|text
operator|.
name|toLowerCase
argument_list|()
operator|.
name|contains
argument_list|(
literal|"+ophacks"
argument_list|)
operator|||
name|this
operator|.
name|text
operator|.
name|toLowerCase
argument_list|()
operator|.
name|contains
argument_list|(
literal|"+ophax"
argument_list|)
condition|)
block|{
name|minecraft
operator|.
name|HackState
operator|=
name|HackState
operator|.
name|OpHacks
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|minecraft
operator|.
name|player
operator|.
name|userType
operator|<
literal|100
condition|)
block|{
name|minecraft
operator|.
name|settings
operator|.
name|CanSpeed
operator|=
literal|false
expr_stmt|;
block|}
block|}
else|else
block|{
name|minecraft
operator|.
name|HackState
operator|=
name|HackState
operator|.
name|NoHacksTagShown
expr_stmt|;
block|}
block|}
name|this
operator|.
name|setProgress
argument_list|(
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|setProgress
parameter_list|(
name|int
name|var1
parameter_list|)
block|{
if|if
condition|(
operator|!
name|this
operator|.
name|minecraft
operator|.
name|running
condition|)
block|{
throw|throw
operator|new
name|StopGameException
argument_list|()
throw|;
block|}
else|else
block|{
name|long
name|var2
decl_stmt|;
if|if
condition|(
operator|(
name|var2
operator|=
name|System
operator|.
name|currentTimeMillis
argument_list|()
operator|)
operator|-
name|this
operator|.
name|start
operator|<
literal|0L
operator|||
name|var2
operator|-
name|this
operator|.
name|start
operator|>=
literal|20L
condition|)
block|{
name|this
operator|.
name|start
operator|=
name|var2
expr_stmt|;
name|int
name|var4
init|=
name|this
operator|.
name|minecraft
operator|.
name|width
operator|*
literal|240
operator|/
name|this
operator|.
name|minecraft
operator|.
name|height
decl_stmt|;
name|int
name|var5
init|=
name|this
operator|.
name|minecraft
operator|.
name|height
operator|*
literal|240
operator|/
name|this
operator|.
name|minecraft
operator|.
name|height
decl_stmt|;
name|GL11
operator|.
name|glClear
argument_list|(
literal|16640
argument_list|)
expr_stmt|;
name|ShapeRenderer
name|var6
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|int
name|var7
init|=
name|this
operator|.
name|minecraft
operator|.
name|textureManager
operator|.
name|load
argument_list|(
literal|"/dirt.png"
argument_list|)
decl_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|var7
argument_list|)
expr_stmt|;
name|float
name|var10
init|=
literal|32.0F
decl_stmt|;
name|var6
operator|.
name|begin
argument_list|()
expr_stmt|;
name|var6
operator|.
name|color
argument_list|(
literal|4210752
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertexUV
argument_list|(
literal|0.0F
argument_list|,
operator|(
name|float
operator|)
name|var5
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
operator|(
name|float
operator|)
name|var5
operator|/
name|var10
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertexUV
argument_list|(
operator|(
name|float
operator|)
name|var4
argument_list|,
operator|(
name|float
operator|)
name|var5
argument_list|,
literal|0.0F
argument_list|,
operator|(
name|float
operator|)
name|var4
operator|/
name|var10
argument_list|,
operator|(
name|float
operator|)
name|var5
operator|/
name|var10
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertexUV
argument_list|(
operator|(
name|float
operator|)
name|var4
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
operator|(
name|float
operator|)
name|var4
operator|/
name|var10
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertexUV
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|end
argument_list|()
expr_stmt|;
if|if
condition|(
name|var1
operator|>=
literal|0
condition|)
block|{
name|var7
operator|=
name|var4
operator|/
literal|2
operator|-
literal|50
expr_stmt|;
name|int
name|var8
init|=
name|var5
operator|/
literal|2
operator|+
literal|16
decl_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3553
argument_list|)
expr_stmt|;
name|var6
operator|.
name|begin
argument_list|()
expr_stmt|;
name|var6
operator|.
name|color
argument_list|(
literal|8421504
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
name|var7
argument_list|,
operator|(
name|float
operator|)
name|var8
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
name|var7
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var8
operator|+
literal|2
operator|)
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
operator|(
name|var7
operator|+
literal|100
operator|)
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var8
operator|+
literal|2
operator|)
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
operator|(
name|var7
operator|+
literal|100
operator|)
argument_list|,
operator|(
name|float
operator|)
name|var8
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|color
argument_list|(
literal|8454016
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
name|var7
argument_list|,
operator|(
name|float
operator|)
name|var8
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
name|var7
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var8
operator|+
literal|2
operator|)
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
operator|(
name|var7
operator|+
name|var1
operator|)
argument_list|,
operator|(
name|float
operator|)
operator|(
name|var8
operator|+
literal|2
operator|)
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|vertex
argument_list|(
operator|(
name|float
operator|)
operator|(
name|var7
operator|+
name|var1
operator|)
argument_list|,
operator|(
name|float
operator|)
name|var8
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|var6
operator|.
name|end
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|3553
argument_list|)
expr_stmt|;
block|}
name|this
operator|.
name|minecraft
operator|.
name|fontRenderer
operator|.
name|render
argument_list|(
name|this
operator|.
name|title
argument_list|,
operator|(
name|var4
operator|-
name|this
operator|.
name|minecraft
operator|.
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|this
operator|.
name|title
argument_list|)
operator|)
operator|/
literal|2
argument_list|,
name|var5
operator|/
literal|2
operator|-
literal|4
operator|-
literal|16
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|this
operator|.
name|minecraft
operator|.
name|fontRenderer
operator|.
name|render
argument_list|(
name|this
operator|.
name|text
argument_list|,
operator|(
name|var4
operator|-
name|this
operator|.
name|minecraft
operator|.
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|this
operator|.
name|text
argument_list|)
operator|)
operator|/
literal|2
argument_list|,
name|var5
operator|/
literal|2
operator|-
literal|4
operator|+
literal|8
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|Display
operator|.
name|update
argument_list|()
expr_stmt|;
try|try
block|{
name|Thread
operator|.
name|yield
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|var9
parameter_list|)
block|{
empty_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

