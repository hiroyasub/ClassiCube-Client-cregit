begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|render
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
name|level
operator|.
name|tile
operator|.
name|Block
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|BlockData
block|{
specifier|public
name|int
name|x
decl_stmt|;
specifier|public
name|int
name|y
decl_stmt|;
specifier|public
name|int
name|z
decl_stmt|;
specifier|public
name|Block
name|block
decl_stmt|;
specifier|public
name|BlockData
parameter_list|(
name|int
name|x
parameter_list|,
name|int
name|y
parameter_list|,
name|int
name|z
parameter_list|,
name|Block
name|block
parameter_list|)
block|{
name|this
operator|.
name|x
operator|=
name|x
expr_stmt|;
name|this
operator|.
name|y
operator|=
name|y
expr_stmt|;
name|this
operator|.
name|z
operator|=
name|z
expr_stmt|;
name|this
operator|.
name|block
operator|=
name|block
expr_stmt|;
block|}
block|}
end_class

end_unit

