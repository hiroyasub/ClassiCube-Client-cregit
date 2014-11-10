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
name|Serializable
import|;
end_import

begin_comment
comment|/**  * Holds information about a keybinding  */
end_comment

begin_class
specifier|public
class|class
name|KeyBinding
implements|implements
name|Serializable
block|{
specifier|public
name|String
name|name
decl_stmt|;
specifier|public
name|int
name|key
decl_stmt|;
specifier|public
name|KeyBinding
parameter_list|(
name|String
name|name
parameter_list|,
name|int
name|key
parameter_list|)
block|{
name|this
operator|.
name|name
operator|=
name|name
expr_stmt|;
name|this
operator|.
name|key
operator|=
name|key
expr_stmt|;
block|}
block|}
end_class

end_unit

