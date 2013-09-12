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
name|FileWriter
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
name|text
operator|.
name|SimpleDateFormat
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Calendar
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
name|gui
operator|.
name|FontRenderer
import|;
end_import

begin_class
specifier|public
class|class
name|ChatLine
block|{
specifier|public
name|String
name|message
decl_stmt|;
specifier|public
name|int
name|time
decl_stmt|;
specifier|public
specifier|static
name|String
name|eol
init|=
name|System
operator|.
name|getProperty
argument_list|(
literal|"line.separator"
argument_list|)
decl_stmt|;
specifier|public
name|ChatLine
parameter_list|(
name|String
name|message
parameter_list|)
block|{
name|this
operator|.
name|message
operator|=
name|message
expr_stmt|;
name|this
operator|.
name|time
operator|=
literal|0
expr_stmt|;
name|Calendar
name|cal
init|=
name|Calendar
operator|.
name|getInstance
argument_list|()
decl_stmt|;
name|String
name|month
init|=
operator|new
name|SimpleDateFormat
argument_list|(
literal|"MMM"
argument_list|)
operator|.
name|format
argument_list|(
name|cal
operator|.
name|getTime
argument_list|()
argument_list|)
decl_stmt|;
name|String
name|serverName
init|=
name|ProgressBarDisplay
operator|.
name|title
operator|.
name|toLowerCase
argument_list|()
operator|.
name|contains
argument_list|(
literal|"connecting.."
argument_list|)
condition|?
literal|""
else|:
name|ProgressBarDisplay
operator|.
name|title
decl_stmt|;
if|if
condition|(
name|serverName
operator|==
literal|""
operator|||
name|Minecraft
operator|.
name|isSinglePlayer
condition|)
return|return;
name|serverName
operator|=
name|FontRenderer
operator|.
name|stripColor
argument_list|(
name|serverName
argument_list|)
expr_stmt|;
name|serverName
operator|=
name|serverName
operator|.
name|replaceAll
argument_list|(
literal|"[^A-Za-z0-9\\._-]+"
argument_list|,
literal|"_"
argument_list|)
expr_stmt|;
name|File
name|logDir
init|=
operator|new
name|File
argument_list|(
name|Minecraft
operator|.
name|getMinecraftDirectory
argument_list|()
argument_list|,
literal|"/logs/"
argument_list|)
decl_stmt|;
name|File
name|serverDir
init|=
operator|new
name|File
argument_list|(
name|logDir
argument_list|,
name|serverName
argument_list|)
decl_stmt|;
name|File
name|monthDir
init|=
operator|new
name|File
argument_list|(
name|serverDir
argument_list|,
literal|"/"
operator|+
name|month
operator|+
literal|"/"
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
name|logDir
operator|.
name|exists
argument_list|()
condition|)
name|logDir
operator|.
name|mkdir
argument_list|()
expr_stmt|;
if|if
condition|(
operator|!
name|serverDir
operator|.
name|exists
argument_list|()
condition|)
name|serverDir
operator|.
name|mkdir
argument_list|()
expr_stmt|;
if|if
condition|(
operator|!
name|monthDir
operator|.
name|exists
argument_list|()
condition|)
name|monthDir
operator|.
name|mkdir
argument_list|()
expr_stmt|;
name|String
name|dateStamp
init|=
operator|new
name|SimpleDateFormat
argument_list|(
literal|"MM-dd-yyyy"
argument_list|)
operator|.
name|format
argument_list|(
name|Calendar
operator|.
name|getInstance
argument_list|()
operator|.
name|getTime
argument_list|()
argument_list|)
decl_stmt|;
name|String
name|timeStamp
init|=
operator|new
name|SimpleDateFormat
argument_list|(
literal|"HH:mm:ss"
argument_list|)
operator|.
name|format
argument_list|(
name|Calendar
operator|.
name|getInstance
argument_list|()
operator|.
name|getTime
argument_list|()
argument_list|)
decl_stmt|;
try|try
block|{
name|File
name|logFile
init|=
operator|new
name|File
argument_list|(
name|monthDir
argument_list|,
name|dateStamp
operator|+
literal|".log"
argument_list|)
decl_stmt|;
name|String
name|str
init|=
name|FontRenderer
operator|.
name|stripColor
argument_list|(
name|this
operator|.
name|message
argument_list|)
decl_stmt|;
name|FileWriter
name|fileWriter
init|=
operator|new
name|FileWriter
argument_list|(
name|logFile
argument_list|,
literal|true
argument_list|)
decl_stmt|;
name|fileWriter
operator|.
name|write
argument_list|(
literal|"["
operator|+
name|timeStamp
operator|+
literal|"] "
operator|+
name|str
operator|+
name|eol
argument_list|)
expr_stmt|;
name|fileWriter
operator|.
name|close
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|IOException
name|IOException
parameter_list|)
block|{
name|System
operator|.
name|out
operator|.
name|println
argument_list|(
literal|"Cannot log to chatlog: "
operator|+
name|IOException
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

