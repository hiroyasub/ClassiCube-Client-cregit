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

begin_class
specifier|public
class|class
name|HackState
block|{
specifier|public
specifier|static
name|boolean
name|Noclip
decl_stmt|,
name|Speed
decl_stmt|,
name|Fly
decl_stmt|,
name|OpHacks
init|=
literal|true
decl_stmt|;
specifier|public
specifier|static
name|void
name|setAllDisabled
parameter_list|()
block|{
name|Noclip
operator|=
literal|false
expr_stmt|;
name|Speed
operator|=
literal|false
expr_stmt|;
name|Fly
operator|=
literal|false
expr_stmt|;
name|OpHacks
operator|=
literal|false
expr_stmt|;
block|}
specifier|public
specifier|static
name|void
name|setAllEnabled
parameter_list|()
block|{
name|Noclip
operator|=
literal|true
expr_stmt|;
name|Speed
operator|=
literal|true
expr_stmt|;
name|Fly
operator|=
literal|true
expr_stmt|;
name|OpHacks
operator|=
literal|true
expr_stmt|;
block|}
block|}
end_class

end_unit

