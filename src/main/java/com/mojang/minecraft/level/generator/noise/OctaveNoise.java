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
operator|.
name|generator
operator|.
name|noise
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Random
import|;
end_import

begin_class
specifier|public
class|class
name|OctaveNoise
extends|extends
name|Noise
block|{
specifier|public
name|OctaveNoise
parameter_list|(
name|Random
name|random
parameter_list|,
name|int
name|octaves
parameter_list|)
block|{
name|this
operator|.
name|octaves
operator|=
name|octaves
expr_stmt|;
name|perlin
operator|=
operator|new
name|PerlinNoise
index|[
name|octaves
index|]
expr_stmt|;
for|for
control|(
name|int
name|count
init|=
literal|0
init|;
name|count
operator|<
name|octaves
condition|;
name|count
operator|++
control|)
block|{
name|perlin
index|[
name|count
index|]
operator|=
operator|new
name|PerlinNoise
argument_list|(
name|random
argument_list|)
expr_stmt|;
block|}
block|}
annotation|@
name|Override
specifier|public
name|double
name|compute
parameter_list|(
name|double
name|x
parameter_list|,
name|double
name|z
parameter_list|)
block|{
name|double
name|result
init|=
literal|0.0D
decl_stmt|;
name|double
name|noiseLevel
init|=
literal|1.0D
decl_stmt|;
comment|//unknown0
for|for
control|(
name|int
name|count
init|=
literal|0
init|;
name|count
operator|<
name|octaves
condition|;
name|count
operator|++
control|)
block|{
name|result
operator|+=
name|perlin
index|[
name|count
index|]
operator|.
name|compute
argument_list|(
name|x
operator|/
name|noiseLevel
argument_list|,
name|z
operator|/
name|noiseLevel
argument_list|)
operator|*
name|noiseLevel
expr_stmt|;
name|noiseLevel
operator|*=
literal|2.0D
expr_stmt|;
block|}
return|return
name|result
return|;
block|}
specifier|private
name|PerlinNoise
index|[]
name|perlin
decl_stmt|;
specifier|private
name|int
name|octaves
decl_stmt|;
block|}
end_class

end_unit

