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

begin_enum
specifier|public
enum|enum
name|OperatingSystem
block|{
name|LINUX
argument_list|(
literal|"linux"
argument_list|,
literal|0
argument_list|)
block|,
name|SOLARIS
argument_list|(
literal|"solaris"
argument_list|,
literal|1
argument_list|)
block|,
name|WINDOWS
argument_list|(
literal|"windows"
argument_list|,
literal|2
argument_list|)
block|{
specifier|public
name|File
name|getMinecraftFolder
parameter_list|(
name|String
name|home
parameter_list|,
name|String
name|folder
parameter_list|)
block|{
name|String
name|appData
init|=
name|System
operator|.
name|getenv
argument_list|(
literal|"APPDATA"
argument_list|)
decl_stmt|;
if|if
condition|(
name|appData
operator|!=
literal|null
condition|)
block|{
return|return
operator|new
name|File
argument_list|(
name|appData
argument_list|,
name|folder
operator|+
literal|'/'
argument_list|)
return|;
block|}
else|else
block|{
return|return
operator|new
name|File
argument_list|(
name|home
argument_list|,
name|folder
operator|+
literal|'/'
argument_list|)
return|;
block|}
block|}
block|}
block|,
name|MAC_OS_X
argument_list|(
literal|"macos"
argument_list|,
literal|3
argument_list|)
block|{
specifier|public
name|File
name|getMinecraftFolder
parameter_list|(
name|String
name|home
parameter_list|,
name|String
name|folder
parameter_list|)
block|{
return|return
operator|new
name|File
argument_list|(
name|home
argument_list|,
literal|"Library/Application Support/"
operator|+
name|folder
argument_list|)
return|;
block|}
block|}
block|,
name|UNKNOWN
argument_list|(
literal|"unknown"
argument_list|,
literal|4
argument_list|)
block|;
specifier|public
specifier|static
specifier|final
name|OperatingSystem
index|[]
name|values
init|=
operator|new
name|OperatingSystem
index|[]
block|{
name|LINUX
block|,
name|SOLARIS
block|,
name|WINDOWS
block|,
name|MAC_OS_X
block|,
name|UNKNOWN
block|}
decl_stmt|;
specifier|public
specifier|final
name|String
name|folderName
decl_stmt|;
specifier|public
specifier|final
name|int
name|id
decl_stmt|;
specifier|private
name|OperatingSystem
parameter_list|(
name|String
name|folderName
parameter_list|,
name|int
name|id
parameter_list|)
block|{
name|this
operator|.
name|folderName
operator|=
name|folderName
expr_stmt|;
name|this
operator|.
name|id
operator|=
name|id
expr_stmt|;
block|}
comment|/* Windows and OSX override this */
specifier|public
name|File
name|getMinecraftFolder
parameter_list|(
name|String
name|home
parameter_list|,
name|String
name|folder
parameter_list|)
block|{
return|return
operator|new
name|File
argument_list|(
name|home
argument_list|,
name|folder
operator|+
literal|'/'
argument_list|)
return|;
block|}
specifier|public
specifier|static
name|OperatingSystem
name|detect
parameter_list|()
block|{
name|String
name|s
init|=
name|System
operator|.
name|getProperty
argument_list|(
literal|"os.name"
argument_list|)
operator|.
name|toLowerCase
argument_list|()
decl_stmt|;
if|if
condition|(
name|s
operator|.
name|contains
argument_list|(
literal|"win"
argument_list|)
condition|)
block|{
return|return
name|OperatingSystem
operator|.
name|WINDOWS
return|;
block|}
if|if
condition|(
name|s
operator|.
name|contains
argument_list|(
literal|"mac"
argument_list|)
condition|)
block|{
return|return
name|OperatingSystem
operator|.
name|MAC_OS_X
return|;
block|}
if|if
condition|(
name|s
operator|.
name|contains
argument_list|(
literal|"solaris"
argument_list|)
operator|||
name|s
operator|.
name|contains
argument_list|(
literal|"sunos"
argument_list|)
condition|)
block|{
return|return
name|OperatingSystem
operator|.
name|SOLARIS
return|;
block|}
if|if
condition|(
name|s
operator|.
name|contains
argument_list|(
literal|"linux"
argument_list|)
operator|||
name|s
operator|.
name|contains
argument_list|(
literal|"unix"
argument_list|)
condition|)
block|{
return|return
name|OperatingSystem
operator|.
name|LINUX
return|;
block|}
return|return
name|OperatingSystem
operator|.
name|UNKNOWN
return|;
block|}
block|}
end_enum

end_unit

