begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|sound
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
name|LogUtil
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
name|util
operator|.
name|*
import|;
end_import

begin_comment
comment|// TODO.
end_comment

begin_class
specifier|public
specifier|final
class|class
name|SoundManager
block|{
specifier|public
name|Map
argument_list|<
name|String
argument_list|,
name|Object
argument_list|>
name|sounds
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
specifier|private
name|Map
argument_list|<
name|String
argument_list|,
name|Object
argument_list|>
name|music
init|=
operator|new
name|HashMap
argument_list|<>
argument_list|()
decl_stmt|;
specifier|public
name|Random
name|random
init|=
operator|new
name|Random
argument_list|()
decl_stmt|;
specifier|public
name|long
name|lastMusic
init|=
name|System
operator|.
name|currentTimeMillis
argument_list|()
operator|+
literal|60000L
decl_stmt|;
specifier|public
specifier|final
name|AudioInfo
name|getAudioInfo
parameter_list|(
name|String
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|)
block|{
name|List
argument_list|<
name|?
argument_list|>
name|var4
init|=
literal|null
decl_stmt|;
synchronized|synchronized
init|(
name|sounds
init|)
block|{
name|var4
operator|=
operator|(
name|List
argument_list|<
name|?
argument_list|>
operator|)
name|sounds
operator|.
name|get
argument_list|(
name|var1
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var4
operator|==
literal|null
condition|)
block|{
return|return
literal|null
return|;
block|}
else|else
block|{
name|SoundData
name|var7
init|=
operator|(
name|SoundData
operator|)
name|var4
operator|.
name|get
argument_list|(
name|random
operator|.
name|nextInt
argument_list|(
name|var4
operator|.
name|size
argument_list|()
argument_list|)
argument_list|)
decl_stmt|;
return|return
operator|new
name|SoundInfo
argument_list|(
name|var7
argument_list|,
name|var3
argument_list|,
name|var2
argument_list|)
return|;
block|}
block|}
specifier|public
name|boolean
name|playMusic
parameter_list|(
name|SoundPlayer
name|var1
parameter_list|,
name|String
name|var2
parameter_list|)
block|{
name|List
argument_list|<
name|?
argument_list|>
name|var3
init|=
literal|null
decl_stmt|;
synchronized|synchronized
init|(
name|music
init|)
block|{
name|var3
operator|=
operator|(
name|List
argument_list|<
name|?
argument_list|>
operator|)
name|music
operator|.
name|get
argument_list|(
name|var2
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var3
operator|==
literal|null
condition|)
block|{
return|return
literal|false
return|;
block|}
else|else
block|{
name|File
name|var8
init|=
operator|(
name|File
operator|)
name|var3
operator|.
name|get
argument_list|(
name|random
operator|.
name|nextInt
argument_list|(
name|var3
operator|.
name|size
argument_list|()
argument_list|)
argument_list|)
decl_stmt|;
try|try
block|{
name|var1
operator|.
name|play
argument_list|(
operator|new
name|Music
argument_list|(
name|var1
argument_list|,
name|var8
operator|.
name|toURI
argument_list|()
operator|.
name|toURL
argument_list|()
argument_list|)
argument_list|)
expr_stmt|;
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
literal|"Error queueing music to play from "
operator|+
name|var2
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
return|return
literal|true
return|;
block|}
block|}
annotation|@
name|SuppressWarnings
argument_list|(
literal|"unchecked"
argument_list|)
specifier|public
specifier|final
name|void
name|registerMusic
parameter_list|(
name|String
name|var1
parameter_list|,
name|File
name|var2
parameter_list|)
block|{
synchronized|synchronized
init|(
name|music
init|)
block|{
name|var1
operator|=
name|var1
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|var1
operator|.
name|length
argument_list|()
operator|-
literal|4
argument_list|)
operator|.
name|replaceAll
argument_list|(
literal|"/"
argument_list|,
literal|"."
argument_list|)
expr_stmt|;
while|while
condition|(
name|Character
operator|.
name|isDigit
argument_list|(
name|var1
operator|.
name|charAt
argument_list|(
name|var1
operator|.
name|length
argument_list|()
operator|-
literal|1
argument_list|)
argument_list|)
condition|)
block|{
name|var1
operator|=
name|var1
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|var1
operator|.
name|length
argument_list|()
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
name|Object
name|var4
init|=
name|music
operator|.
name|get
argument_list|(
name|var1
argument_list|)
decl_stmt|;
if|if
condition|(
name|var4
operator|==
literal|null
condition|)
block|{
name|var4
operator|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
expr_stmt|;
name|music
operator|.
name|put
argument_list|(
name|var1
argument_list|,
name|var4
argument_list|)
expr_stmt|;
block|}
operator|(
operator|(
name|List
argument_list|<
name|File
argument_list|>
operator|)
name|var4
operator|)
operator|.
name|add
argument_list|(
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|SuppressWarnings
argument_list|(
literal|"unchecked"
argument_list|)
specifier|public
name|void
name|registerSound
parameter_list|(
name|File
name|var1
parameter_list|,
name|String
name|var2
parameter_list|)
block|{
try|try
block|{
name|var2
operator|=
name|var2
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|var2
operator|.
name|length
argument_list|()
operator|-
literal|4
argument_list|)
operator|.
name|replaceAll
argument_list|(
literal|"/"
argument_list|,
literal|"."
argument_list|)
expr_stmt|;
while|while
condition|(
name|Character
operator|.
name|isDigit
argument_list|(
name|var2
operator|.
name|charAt
argument_list|(
name|var2
operator|.
name|length
argument_list|()
operator|-
literal|1
argument_list|)
argument_list|)
condition|)
block|{
name|var2
operator|=
name|var2
operator|.
name|substring
argument_list|(
literal|0
argument_list|,
name|var2
operator|.
name|length
argument_list|()
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
name|SoundData
name|var7
init|=
name|SoundReader
operator|.
name|read
argument_list|(
name|var1
operator|.
name|toURI
argument_list|()
operator|.
name|toURL
argument_list|()
argument_list|)
decl_stmt|;
synchronized|synchronized
init|(
name|sounds
init|)
block|{
name|Object
name|var4
decl_stmt|;
if|if
condition|(
operator|(
name|var4
operator|=
name|sounds
operator|.
name|get
argument_list|(
name|var2
argument_list|)
operator|)
operator|==
literal|null
condition|)
block|{
name|var4
operator|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
expr_stmt|;
name|sounds
operator|.
name|put
argument_list|(
name|var2
argument_list|,
name|var4
argument_list|)
expr_stmt|;
block|}
operator|(
operator|(
name|List
argument_list|<
name|SoundData
argument_list|>
operator|)
name|var4
operator|)
operator|.
name|add
argument_list|(
name|var7
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
literal|"Error registering sound "
operator|+
name|var2
operator|+
literal|" from "
operator|+
name|var1
argument_list|,
name|ex
argument_list|)
expr_stmt|;
block|}
block|}
block|}
end_class

end_unit

