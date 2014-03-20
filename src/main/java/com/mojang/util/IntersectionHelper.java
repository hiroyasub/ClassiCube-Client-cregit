begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|util
package|;
end_package

begin_comment
comment|/**  * Helper class for determining intersections.  *  * @author tyteen4a03  */
end_comment

begin_class
specifier|public
class|class
name|IntersectionHelper
block|{
comment|/**      * Returns whether the given YZ plane intersects the vector.      *      * @param vec      * @param y0      * @param z0      * @param y1      * @param z1      * @return      */
specifier|public
specifier|static
name|boolean
name|xIntersects
parameter_list|(
name|Vec3D
name|vec
parameter_list|,
name|float
name|y0
parameter_list|,
name|float
name|z0
parameter_list|,
name|float
name|y1
parameter_list|,
name|float
name|z1
parameter_list|)
block|{
return|return
name|vec
operator|!=
literal|null
operator|&&
name|vec
operator|.
name|y
operator|>=
name|y0
operator|&&
name|vec
operator|.
name|y
operator|<=
name|y1
operator|&&
name|vec
operator|.
name|z
operator|>=
name|z0
operator|&&
name|vec
operator|.
name|z
operator|<=
name|z1
return|;
block|}
comment|/**      * Returns whether the given XZ plane intersects the vector.      *      * @param vec      * @param x0      * @param z0      * @param x1      * @param z1      * @return      */
specifier|public
specifier|static
name|boolean
name|yIntersects
parameter_list|(
name|Vec3D
name|vec
parameter_list|,
name|float
name|x0
parameter_list|,
name|float
name|z0
parameter_list|,
name|float
name|x1
parameter_list|,
name|float
name|z1
parameter_list|)
block|{
return|return
name|vec
operator|!=
literal|null
operator|&&
name|vec
operator|.
name|x
operator|>=
name|x0
operator|&&
name|vec
operator|.
name|x
operator|<=
name|x1
operator|&&
name|vec
operator|.
name|z
operator|>=
name|z0
operator|&&
name|vec
operator|.
name|z
operator|<=
name|z1
return|;
block|}
comment|/**      * Returns whether the given XZ plane intersects the vector.      *      * @param vec      * @param x0      * @param y0      * @param x1      * @param y1      * @return      */
specifier|public
specifier|static
name|boolean
name|zIntersects
parameter_list|(
name|Vec3D
name|vec
parameter_list|,
name|float
name|x0
parameter_list|,
name|float
name|y0
parameter_list|,
name|float
name|x1
parameter_list|,
name|float
name|y1
parameter_list|)
block|{
return|return
name|vec
operator|!=
literal|null
operator|&&
name|vec
operator|.
name|x
operator|>=
name|x0
operator|&&
name|vec
operator|.
name|x
operator|<=
name|x1
operator|&&
name|vec
operator|.
name|y
operator|>=
name|y0
operator|&&
name|vec
operator|.
name|y
operator|<=
name|y1
return|;
block|}
block|}
end_class

end_unit

