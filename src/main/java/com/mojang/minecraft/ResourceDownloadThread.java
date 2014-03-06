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
name|FileNotFoundException
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
name|URL
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Enumeration
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
name|ZipEntry
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
name|ZipFile
import|;
end_import

begin_class
specifier|public
class|class
name|ResourceDownloadThread
extends|extends
name|Thread
block|{
specifier|private
specifier|static
specifier|final
name|String
index|[]
name|resourceFiles
init|=
operator|new
name|String
index|[]
block|{
literal|"music/calm1.ogg"
block|,
literal|"music/calm2.ogg"
block|,
literal|"music/calm3.ogg"
block|,
literal|"newmusic/hal1.ogg"
block|,
literal|"newmusic/hal2.ogg"
block|,
literal|"newmusic/hal3.ogg"
block|,
literal|"newmusic/hal4.ogg"
block|,
literal|"newsound/step/grass1.ogg"
block|,
literal|"newsound/step/grass2.ogg"
block|,
literal|"newsound/step/grass3.ogg"
block|,
literal|"newsound/step/grass4.ogg"
block|,
literal|"newsound/step/gravel1.ogg"
block|,
literal|"newsound/step/gravel2.ogg"
block|,
literal|"newsound/step/gravel3.ogg"
block|,
literal|"newsound/step/gravel4.ogg"
block|,
literal|"newsound/step/stone1.ogg"
block|,
literal|"newsound/step/stone2.ogg"
block|,
literal|"newsound/step/stone3.ogg"
block|,
literal|"newsound/step/stone4.ogg"
block|,
literal|"newsound/step/wood1.ogg"
block|,
literal|"newsound/step/wood2.ogg"
block|,
literal|"newsound/step/wood3.ogg"
block|,
literal|"newsound/step/wood4.ogg"
block|,
literal|"newsound/step/cloth1.ogg"
block|,
literal|"newsound/step/cloth2.ogg"
block|,
literal|"newsound/step/cloth3.ogg"
block|,
literal|"newsound/step/cloth4.ogg"
block|,
literal|"newsound/step/sand1.ogg"
block|,
literal|"newsound/step/sand2.ogg"
block|,
literal|"newsound/step/sand3.ogg"
block|,
literal|"newsound/step/sand4.ogg"
block|,
literal|"newsound/step/snow1.ogg"
block|,
literal|"newsound/step/snow2.ogg"
block|,
literal|"newsound/step/snow3.ogg"
block|,
literal|"newsound/step/snow4.ogg"
block|,
literal|"sound3/dig/grass1.ogg"
block|,
literal|"sound3/dig/grass2.ogg"
block|,
literal|"sound3/dig/grass3.ogg"
block|,
literal|"sound3/dig/grass4.ogg"
block|,
literal|"sound3/dig/gravel1.ogg"
block|,
literal|"sound3/dig/gravel2.ogg"
block|,
literal|"sound3/dig/gravel3.ogg"
block|,
literal|"sound3/dig/gravel4.ogg"
block|,
literal|"sound3/dig/stone1.ogg"
block|,
literal|"sound3/dig/stone2.ogg"
block|,
literal|"sound3/dig/stone3.ogg"
block|,
literal|"sound3/dig/stone4.ogg"
block|,
literal|"sound3/dig/wood1.ogg"
block|,
literal|"sound3/dig/wood2.ogg"
block|,
literal|"sound3/dig/wood3.ogg"
block|,
literal|"sound3/dig/wood4.ogg"
block|,
literal|"sound3/dig/cloth1.ogg"
block|,
literal|"sound3/dig/cloth2.ogg"
block|,
literal|"sound3/dig/cloth3.ogg"
block|,
literal|"sound3/dig/cloth4.ogg"
block|,
literal|"sound3/dig/sand1.ogg"
block|,
literal|"sound3/dig/sand2.ogg"
block|,
literal|"sound3/dig/sand3.ogg"
block|,
literal|"sound3/dig/sand4.ogg"
block|,
literal|"sound3/dig/snow1.ogg"
block|,
literal|"sound3/dig/snow2.ogg"
block|,
literal|"sound3/dig/snow3.ogg"
block|,
literal|"sound3/dig/snow4.ogg"
block|,
literal|"sound3/random/glass1.ogg"
block|,
literal|"sound3/random/glass2.ogg"
block|,
literal|"sound3/random/glass3.ogg"
block|}
decl_stmt|;
specifier|private
specifier|final
name|File
name|dir
decl_stmt|;
specifier|private
specifier|final
name|Minecraft
name|minecraft
decl_stmt|;
specifier|private
name|boolean
name|finished
init|=
literal|false
decl_stmt|;
specifier|public
specifier|static
name|boolean
name|Done
init|=
literal|false
decl_stmt|;
name|boolean
name|running
init|=
literal|false
decl_stmt|;
specifier|public
name|ResourceDownloadThread
parameter_list|(
name|File
name|minecraftFolder
parameter_list|,
name|Minecraft
name|minecraft
parameter_list|)
throws|throws
name|IOException
block|{
name|this
operator|.
name|minecraft
operator|=
name|minecraft
expr_stmt|;
name|setName
argument_list|(
literal|"Resource download thread"
argument_list|)
expr_stmt|;
name|setDaemon
argument_list|(
literal|true
argument_list|)
expr_stmt|;
name|dir
operator|=
operator|new
name|File
argument_list|(
name|minecraftFolder
argument_list|,
literal|"resources/"
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|dir
operator|.
name|exists
argument_list|()
operator|&&
operator|!
name|dir
operator|.
name|mkdirs
argument_list|()
condition|)
block|{
throw|throw
operator|new
name|RuntimeException
argument_list|(
literal|"The working directory could not be created: "
operator|+
name|dir
argument_list|)
throw|;
block|}
block|}
specifier|public
name|boolean
name|deleteDir
parameter_list|(
name|File
name|dir
parameter_list|)
block|{
if|if
condition|(
name|dir
operator|.
name|isDirectory
argument_list|()
condition|)
block|{
name|String
index|[]
name|children
init|=
name|dir
operator|.
name|list
argument_list|()
decl_stmt|;
for|for
control|(
name|String
name|child
range|:
name|children
control|)
block|{
name|boolean
name|success
init|=
name|deleteDir
argument_list|(
operator|new
name|File
argument_list|(
name|dir
argument_list|,
name|child
argument_list|)
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
name|success
condition|)
block|{
return|return
literal|false
return|;
block|}
block|}
block|}
return|return
name|dir
operator|.
name|delete
argument_list|()
return|;
block|}
specifier|public
name|boolean
name|isFinished
parameter_list|()
block|{
return|return
name|finished
return|;
block|}
annotation|@
name|Override
specifier|public
name|void
name|run
parameter_list|()
block|{
name|File
name|musicFolder
init|=
operator|new
name|File
argument_list|(
name|dir
argument_list|,
literal|"music"
argument_list|)
decl_stmt|;
name|File
name|stepsFolder
init|=
operator|new
name|File
argument_list|(
operator|new
name|File
argument_list|(
name|dir
argument_list|,
literal|"newsound"
argument_list|)
argument_list|,
literal|"step"
argument_list|)
decl_stmt|;
name|File
name|digFolder
init|=
operator|new
name|File
argument_list|(
operator|new
name|File
argument_list|(
name|dir
argument_list|,
literal|"sound3"
argument_list|)
argument_list|,
literal|"dig"
argument_list|)
decl_stmt|;
name|File
name|randomFolder
init|=
operator|new
name|File
argument_list|(
operator|new
name|File
argument_list|(
name|dir
argument_list|,
literal|"sound3"
argument_list|)
argument_list|,
literal|"random"
argument_list|)
decl_stmt|;
name|File
name|newMusicFolder
init|=
operator|new
name|File
argument_list|(
name|dir
argument_list|,
literal|"newmusic"
argument_list|)
decl_stmt|;
try|try
block|{
name|GameSettings
operator|.
name|PercentString
operator|=
literal|"5%"
expr_stmt|;
name|GameSettings
operator|.
name|StatusString
operator|=
literal|"Downloading music and sounds..."
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Downloading music and sounds..."
argument_list|)
expr_stmt|;
name|int
name|percent
init|=
literal|5
decl_stmt|;
for|for
control|(
name|String
name|fileName
range|:
name|resourceFiles
control|)
block|{
if|if
condition|(
name|percent
operator|>=
literal|80
condition|)
block|{
name|percent
operator|=
literal|80
expr_stmt|;
block|}
name|percent
operator|+=
literal|3
expr_stmt|;
name|File
name|file
init|=
operator|new
name|File
argument_list|(
name|dir
argument_list|,
name|fileName
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
name|GameSettings
operator|.
name|PercentString
operator|=
name|percent
operator|+
literal|"%"
expr_stmt|;
name|GameSettings
operator|.
name|StatusString
operator|=
literal|"Downloading https://s3.amazonaws.com/MinecraftResources/"
operator|+
name|fileName
operator|+
literal|"..."
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Downloading https://s3.amazonaws.com/MinecraftResources/"
operator|+
name|fileName
operator|+
literal|"..."
argument_list|)
expr_stmt|;
name|URL
name|url
init|=
operator|new
name|URL
argument_list|(
literal|"https://s3.amazonaws.com/MinecraftResources/"
operator|+
name|fileName
argument_list|)
decl_stmt|;
name|InputStream
name|is
init|=
name|url
operator|.
name|openStream
argument_list|()
decl_stmt|;
try|try
block|{
name|IOUtil
operator|.
name|copyStreamToFile
argument_list|(
name|is
argument_list|,
name|file
argument_list|)
expr_stmt|;
block|}
finally|finally
block|{
name|is
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
name|GameSettings
operator|.
name|StatusString
operator|=
literal|"Downloaded https://s3.amazonaws.com/MinecraftResources/"
operator|+
name|fileName
operator|+
literal|"!"
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Downloaded https://s3.amazonaws.com/MinecraftResources/"
operator|+
name|fileName
operator|+
literal|"!"
argument_list|)
expr_stmt|;
block|}
block|}
name|GameSettings
operator|.
name|PercentString
operator|=
literal|"85%"
expr_stmt|;
name|GameSettings
operator|.
name|StatusString
operator|=
literal|"Downloaded music and sounds!"
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Downloaded music and sounds!"
argument_list|)
expr_stmt|;
name|GameSettings
operator|.
name|StatusString
operator|=
literal|""
expr_stmt|;
name|GameSettings
operator|.
name|PercentString
operator|=
literal|""
expr_stmt|;
name|Done
operator|=
literal|true
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|ex
parameter_list|)
block|{
name|ex
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<=
literal|3
condition|;
name|i
operator|++
control|)
block|{
name|minecraft
operator|.
name|sound
operator|.
name|registerMusic
argument_list|(
literal|"calm"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|,
operator|new
name|File
argument_list|(
name|musicFolder
argument_list|,
literal|"calm"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|randomFolder
argument_list|,
literal|"glass"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"random/glass"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|1
init|;
name|i
operator|<=
literal|4
condition|;
name|i
operator|++
control|)
block|{
name|minecraft
operator|.
name|sound
operator|.
name|registerMusic
argument_list|(
literal|"calm"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|,
operator|new
name|File
argument_list|(
name|newMusicFolder
argument_list|,
literal|"hal"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|stepsFolder
argument_list|,
literal|"grass"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"step/grass"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|stepsFolder
argument_list|,
literal|"gravel"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"step/gravel"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|stepsFolder
argument_list|,
literal|"stone"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"step/stone"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|stepsFolder
argument_list|,
literal|"wood"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"step/wood"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|stepsFolder
argument_list|,
literal|"cloth"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"step/cloth"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|stepsFolder
argument_list|,
literal|"sand"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"step/sand"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|stepsFolder
argument_list|,
literal|"snow"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"step/snow"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|digFolder
argument_list|,
literal|"grass"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"dig/grass"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|digFolder
argument_list|,
literal|"gravel"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"dig/gravel"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|digFolder
argument_list|,
literal|"stone"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"dig/stone"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|digFolder
argument_list|,
literal|"wood"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"dig/wood"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|digFolder
argument_list|,
literal|"cloth"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"dig/cloth"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|digFolder
argument_list|,
literal|"sand"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"dig/sand"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
name|minecraft
operator|.
name|sound
operator|.
name|registerSound
argument_list|(
operator|new
name|File
argument_list|(
name|digFolder
argument_list|,
literal|"snow"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
argument_list|,
literal|"dig/snow"
operator|+
name|i
operator|+
literal|".ogg"
argument_list|)
expr_stmt|;
block|}
name|finished
operator|=
literal|true
expr_stmt|;
block|}
specifier|public
name|void
name|unpack
parameter_list|(
name|String
name|filename1
parameter_list|)
block|{
name|String
name|filename
init|=
name|filename1
decl_stmt|;
name|File
name|srcFile
init|=
operator|new
name|File
argument_list|(
name|filename
argument_list|)
decl_stmt|;
name|String
name|zipPath
init|=
name|filename
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|filename
operator|.
name|length
argument_list|()
operator|-
literal|4
argument_list|)
decl_stmt|;
name|File
name|temp
init|=
operator|new
name|File
argument_list|(
name|zipPath
argument_list|)
decl_stmt|;
name|temp
operator|.
name|mkdir
argument_list|()
expr_stmt|;
name|ZipFile
name|zipFile
init|=
literal|null
decl_stmt|;
try|try
block|{
name|zipFile
operator|=
operator|new
name|ZipFile
argument_list|(
name|srcFile
argument_list|)
expr_stmt|;
name|Enumeration
argument_list|<
name|?
extends|extends
name|ZipEntry
argument_list|>
name|e
init|=
name|zipFile
operator|.
name|entries
argument_list|()
decl_stmt|;
while|while
condition|(
name|e
operator|.
name|hasMoreElements
argument_list|()
condition|)
block|{
name|ZipEntry
name|entry
init|=
name|e
operator|.
name|nextElement
argument_list|()
decl_stmt|;
name|File
name|destinationPath
init|=
operator|new
name|File
argument_list|(
name|zipPath
argument_list|,
name|entry
operator|.
name|getName
argument_list|()
argument_list|)
decl_stmt|;
name|destinationPath
operator|.
name|getParentFile
argument_list|()
operator|.
name|mkdirs
argument_list|()
expr_stmt|;
if|if
condition|(
operator|!
name|entry
operator|.
name|isDirectory
argument_list|()
condition|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Extracting file: "
operator|+
name|destinationPath
argument_list|)
expr_stmt|;
name|InputStream
name|is
init|=
name|zipFile
operator|.
name|getInputStream
argument_list|(
name|entry
argument_list|)
decl_stmt|;
try|try
block|{
name|IOUtil
operator|.
name|copyStreamToFile
argument_list|(
name|is
argument_list|,
name|destinationPath
argument_list|)
expr_stmt|;
block|}
finally|finally
block|{
name|is
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
block|}
block|}
block|}
catch|catch
parameter_list|(
name|IOException
name|e1
parameter_list|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Error opening zip file"
operator|+
name|e1
argument_list|)
expr_stmt|;
block|}
finally|finally
block|{
try|try
block|{
if|if
condition|(
name|zipFile
operator|!=
literal|null
condition|)
block|{
name|zipFile
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|IOException
name|e2
parameter_list|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Error while closing zip file"
operator|+
name|e2
argument_list|)
expr_stmt|;
block|}
block|}
block|}
specifier|public
specifier|static
name|void
name|copyFolder
parameter_list|(
name|File
name|src
parameter_list|,
name|File
name|dest
parameter_list|)
block|{
try|try
block|{
if|if
condition|(
name|src
operator|.
name|isDirectory
argument_list|()
condition|)
block|{
if|if
condition|(
operator|!
name|dest
operator|.
name|exists
argument_list|()
condition|)
block|{
name|dest
operator|.
name|mkdir
argument_list|()
expr_stmt|;
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Directory copied from "
operator|+
name|src
operator|+
literal|"  to "
operator|+
name|dest
argument_list|)
expr_stmt|;
block|}
name|String
name|files
index|[]
init|=
name|src
operator|.
name|list
argument_list|()
decl_stmt|;
for|for
control|(
name|String
name|file
range|:
name|files
control|)
block|{
name|File
name|srcFile
init|=
operator|new
name|File
argument_list|(
name|src
argument_list|,
name|file
argument_list|)
decl_stmt|;
name|File
name|destFile
init|=
operator|new
name|File
argument_list|(
name|dest
argument_list|,
name|file
argument_list|)
decl_stmt|;
name|copyFolder
argument_list|(
name|srcFile
argument_list|,
name|destFile
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|InputStream
name|in
init|=
operator|new
name|FileInputStream
argument_list|(
name|src
argument_list|)
decl_stmt|;
try|try
block|{
name|IOUtil
operator|.
name|copyStreamToFile
argument_list|(
name|in
argument_list|,
name|dest
argument_list|)
expr_stmt|;
block|}
finally|finally
block|{
name|in
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"File copied from "
operator|+
name|src
operator|+
literal|" to "
operator|+
name|dest
argument_list|)
expr_stmt|;
block|}
block|}
catch|catch
parameter_list|(
name|FileNotFoundException
name|e
parameter_list|)
block|{
name|e
operator|.
name|printStackTrace
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
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
end_class

end_unit

