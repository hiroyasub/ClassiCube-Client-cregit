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
name|util
package|;
end_package

begin_comment
comment|/**  * Created with IntelliJ IDEA. User: Oliver Yasuna Date: 9/30/12 Time: 7:57 PM  */
end_comment

begin_class
specifier|public
class|class
name|Constants
block|{
comment|/**      * Just a reference to the Minecraft version if needed.      */
specifier|public
specifier|static
specifier|final
name|String
name|MINECRAFT_VERSION
init|=
literal|"0.30"
decl_stmt|;
comment|/**      * ClassiCube Version.      */
specifier|public
specifier|static
specifier|final
name|String
name|CLASSICUBE_VERSION
init|=
literal|"0.13"
decl_stmt|;
comment|/**      * The Minecraft Classic protocol version. Default is 0x07.      */
specifier|public
specifier|static
specifier|final
name|byte
name|PROTOCOL_VERSION
init|=
literal|0x07
decl_stmt|;
comment|/**      * The client type sent to the server to identify what client is being used.      * Default is 0x00.      */
specifier|public
specifier|static
specifier|final
name|byte
name|CLIENT_TYPE
init|=
literal|0x42
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|String
name|CLIENT_NAME
init|=
literal|"ClassiCube Client"
decl_stmt|;
comment|// TODO Add system information
specifier|public
specifier|static
specifier|final
name|String
name|USER_AGENT
init|=
literal|"ClassiCube "
operator|+
name|CLASSICUBE_VERSION
operator|+
literal|"(Minecraft "
operator|+
name|MINECRAFT_VERSION
operator|+
literal|"; Protocol "
operator|+
name|PROTOCOL_VERSION
operator|+
literal|")"
decl_stmt|;
specifier|public
specifier|static
specifier|final
name|byte
name|CUSTOM_BLOCK_SUPPORT_LEVEL
init|=
literal|1
decl_stmt|;
block|}
end_class

end_unit

