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
name|awt
operator|.
name|Color
import|;
end_import

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
comment|/**  * Custom class used to store data compatible with GL11.Color3f Each color (R G  * B A) is fixed to become a maximum of 1f  *  * @author Jon  */
end_comment

begin_class
specifier|public
class|class
name|ColorCache
implements|implements
name|Serializable
block|{
specifier|private
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|1L
decl_stmt|;
specifier|public
name|float
name|R
decl_stmt|;
specifier|public
name|float
name|G
decl_stmt|;
specifier|public
name|float
name|B
decl_stmt|;
specifier|public
name|float
name|A
decl_stmt|;
specifier|public
name|ColorCache
parameter_list|(
name|float
name|r
parameter_list|,
name|float
name|g
parameter_list|,
name|float
name|b
parameter_list|)
block|{
name|R
operator|=
name|FixColor
argument_list|(
name|r
argument_list|)
expr_stmt|;
name|G
operator|=
name|FixColor
argument_list|(
name|g
argument_list|)
expr_stmt|;
name|B
operator|=
name|FixColor
argument_list|(
name|b
argument_list|)
expr_stmt|;
name|A
operator|=
literal|1F
expr_stmt|;
block|}
specifier|public
name|ColorCache
parameter_list|(
name|float
name|r
parameter_list|,
name|float
name|g
parameter_list|,
name|float
name|b
parameter_list|,
name|float
name|a
parameter_list|)
block|{
name|R
operator|=
name|FixColor
argument_list|(
name|r
argument_list|)
expr_stmt|;
name|G
operator|=
name|FixColor
argument_list|(
name|g
argument_list|)
expr_stmt|;
name|B
operator|=
name|FixColor
argument_list|(
name|b
argument_list|)
expr_stmt|;
name|A
operator|=
name|a
expr_stmt|;
block|}
specifier|public
specifier|static
name|ColorCache
name|parseHex
parameter_list|(
name|String
name|hex
parameter_list|)
block|{
name|Color
name|col
init|=
name|Color
operator|.
name|decode
argument_list|(
literal|"#"
operator|+
name|hex
argument_list|)
decl_stmt|;
name|float
name|r
init|=
name|col
operator|.
name|getRed
argument_list|()
operator|/
literal|255f
decl_stmt|;
name|float
name|g
init|=
name|col
operator|.
name|getGreen
argument_list|()
operator|/
literal|255f
decl_stmt|;
name|float
name|b
init|=
name|col
operator|.
name|getBlue
argument_list|()
operator|/
literal|255f
decl_stmt|;
return|return
operator|new
name|ColorCache
argument_list|(
name|r
argument_list|,
name|g
argument_list|,
name|b
argument_list|)
return|;
block|}
specifier|private
name|float
name|FixColor
parameter_list|(
name|float
name|color
parameter_list|)
block|{
if|if
condition|(
name|color
operator|>
literal|1f
condition|)
block|{
return|return
literal|1f
return|;
block|}
if|if
condition|(
name|color
operator|<
literal|0f
condition|)
block|{
return|return
literal|0f
return|;
block|}
return|return
name|color
return|;
block|}
block|}
end_class

end_unit

