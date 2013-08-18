begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
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
name|core
operator|.
name|MinecraftStandalone
import|;
end_import

begin_comment
comment|/**  * Created with IntelliJ IDEA.  * User: Oliver Yasuna  * Date: 9/30/12  * Time: 5:26 PM  */
end_comment

begin_class
specifier|public
class|class
name|TestClient
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
operator|new
name|TestClient
argument_list|()
expr_stmt|;
block|}
specifier|public
name|TestClient
parameter_list|()
block|{
operator|new
name|MinecraftStandalone
argument_list|()
operator|.
name|startMinecraft
argument_list|()
expr_stmt|;
block|}
block|}
end_class

end_unit

