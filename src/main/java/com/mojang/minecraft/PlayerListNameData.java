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
name|PlayerListNameData
block|{
specifier|public
name|short
name|nameID
decl_stmt|;
specifier|public
name|String
name|playerName
decl_stmt|;
specifier|public
name|String
name|listName
decl_stmt|;
specifier|public
name|String
name|groupName
decl_stmt|;
specifier|public
name|Byte
name|groupRank
decl_stmt|;
specifier|public
name|PlayerListNameData
parameter_list|(
name|short
name|NameID
parameter_list|,
name|String
name|PlayerName
parameter_list|,
name|String
name|ListName
parameter_list|,
name|String
name|GroupName
parameter_list|,
name|Byte
name|GroupRank
parameter_list|)
block|{
name|this
operator|.
name|nameID
operator|=
name|NameID
expr_stmt|;
name|this
operator|.
name|playerName
operator|=
name|PlayerName
expr_stmt|;
name|this
operator|.
name|listName
operator|=
name|ListName
expr_stmt|;
name|this
operator|.
name|groupName
operator|=
name|GroupName
expr_stmt|;
name|this
operator|.
name|groupRank
operator|=
name|GroupRank
expr_stmt|;
block|}
block|}
end_class

end_unit

