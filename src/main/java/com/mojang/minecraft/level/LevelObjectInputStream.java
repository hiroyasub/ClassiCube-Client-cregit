begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|level
package|;
end_package

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
name|ObjectInputStream
import|;
end_import

begin_import
import|import
name|java
operator|.
name|io
operator|.
name|ObjectStreamClass
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|HashSet
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Set
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
name|LogUtil
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|LevelObjectInputStream
extends|extends
name|ObjectInputStream
block|{
specifier|private
specifier|final
name|Set
argument_list|<
name|String
argument_list|>
name|classes
init|=
operator|new
name|HashSet
argument_list|<>
argument_list|()
decl_stmt|;
specifier|public
name|LevelObjectInputStream
parameter_list|(
name|InputStream
name|inputStream
parameter_list|)
throws|throws
name|IOException
block|{
name|super
argument_list|(
name|inputStream
argument_list|)
expr_stmt|;
name|classes
operator|.
name|add
argument_list|(
literal|"Player$1"
argument_list|)
expr_stmt|;
name|classes
operator|.
name|add
argument_list|(
literal|"Creeper$1"
argument_list|)
expr_stmt|;
name|classes
operator|.
name|add
argument_list|(
literal|"Skeleton$1"
argument_list|)
expr_stmt|;
block|}
annotation|@
name|Override
specifier|protected
specifier|final
name|ObjectStreamClass
name|readClassDescriptor
parameter_list|()
block|{
try|try
block|{
name|ObjectStreamClass
name|var1
init|=
name|super
operator|.
name|readClassDescriptor
argument_list|()
decl_stmt|;
return|return
name|classes
operator|.
name|contains
argument_list|(
name|var1
operator|.
name|getName
argument_list|()
argument_list|)
condition|?
name|ObjectStreamClass
operator|.
name|lookup
argument_list|(
name|Class
operator|.
name|forName
argument_list|(
name|var1
operator|.
name|getName
argument_list|()
argument_list|)
argument_list|)
else|:
name|var1
return|;
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
literal|"Error reading class descriptor from LevelObjectInputStream"
argument_list|,
name|ex
argument_list|)
expr_stmt|;
return|return
literal|null
return|;
block|}
block|}
block|}
end_class

end_unit

