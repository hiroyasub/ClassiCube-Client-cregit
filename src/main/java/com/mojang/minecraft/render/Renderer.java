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
name|Entity
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
name|Minecraft
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
name|level
operator|.
name|Level
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
name|level
operator|.
name|liquid
operator|.
name|LiquidType
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
name|level
operator|.
name|tile
operator|.
name|Block
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
name|model
operator|.
name|Vec3D
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
name|player
operator|.
name|Player
import|;
end_import

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|MathHelper
import|;
end_import

begin_import
import|import
name|java
operator|.
name|nio
operator|.
name|FloatBuffer
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|Random
import|;
end_import

begin_import
import|import
name|org
operator|.
name|lwjgl
operator|.
name|BufferUtils
import|;
end_import

begin_import
import|import
name|org
operator|.
name|lwjgl
operator|.
name|opengl
operator|.
name|GL11
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|Renderer
block|{
specifier|public
name|Minecraft
name|minecraft
decl_stmt|;
specifier|public
name|float
name|fogColorMultiplier
init|=
literal|1.0F
decl_stmt|;
specifier|public
name|boolean
name|displayActive
init|=
literal|false
decl_stmt|;
specifier|public
name|float
name|fogEnd
init|=
literal|0.0F
decl_stmt|;
specifier|public
name|HeldBlock
name|heldBlock
decl_stmt|;
specifier|public
name|int
name|levelTicks
decl_stmt|;
specifier|public
name|Entity
name|entity
init|=
literal|null
decl_stmt|;
specifier|public
name|Random
name|random
init|=
operator|new
name|Random
argument_list|()
decl_stmt|;
specifier|private
specifier|volatile
name|int
name|unused1
init|=
literal|0
decl_stmt|;
specifier|private
specifier|volatile
name|int
name|unused2
init|=
literal|0
decl_stmt|;
specifier|private
name|FloatBuffer
name|buffer
init|=
name|BufferUtils
operator|.
name|createFloatBuffer
argument_list|(
literal|16
argument_list|)
decl_stmt|;
specifier|public
name|float
name|fogRed
decl_stmt|;
specifier|public
name|float
name|fogBlue
decl_stmt|;
specifier|public
name|float
name|fogGreen
decl_stmt|;
specifier|public
name|Renderer
parameter_list|(
name|Minecraft
name|var1
parameter_list|)
block|{
name|this
operator|.
name|minecraft
operator|=
name|var1
expr_stmt|;
name|this
operator|.
name|heldBlock
operator|=
operator|new
name|HeldBlock
argument_list|(
name|var1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|Vec3D
name|getPlayerVector
parameter_list|(
name|float
name|var1
parameter_list|)
block|{
name|Player
name|var4
decl_stmt|;
name|float
name|var2
init|=
operator|(
name|var4
operator|=
name|this
operator|.
name|minecraft
operator|.
name|player
operator|)
operator|.
name|xo
operator|+
operator|(
name|var4
operator|.
name|x
operator|-
name|var4
operator|.
name|xo
operator|)
operator|*
name|var1
decl_stmt|;
name|float
name|var3
init|=
name|var4
operator|.
name|yo
operator|+
operator|(
name|var4
operator|.
name|y
operator|-
name|var4
operator|.
name|yo
operator|)
operator|*
name|var1
decl_stmt|;
name|float
name|var5
init|=
name|var4
operator|.
name|zo
operator|+
operator|(
name|var4
operator|.
name|z
operator|-
name|var4
operator|.
name|zo
operator|)
operator|*
name|var1
decl_stmt|;
return|return
operator|new
name|Vec3D
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var5
argument_list|)
return|;
block|}
specifier|public
name|void
name|hurtEffect
parameter_list|(
name|float
name|var1
parameter_list|)
block|{
name|Player
name|var3
decl_stmt|;
name|float
name|var2
init|=
operator|(
name|float
operator|)
operator|(
name|var3
operator|=
name|this
operator|.
name|minecraft
operator|.
name|player
operator|)
operator|.
name|hurtTime
operator|-
name|var1
decl_stmt|;
if|if
condition|(
name|var3
operator|.
name|health
operator|<=
literal|0
condition|)
block|{
name|var1
operator|+=
operator|(
name|float
operator|)
name|var3
operator|.
name|deathTime
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
literal|40.0F
operator|-
literal|8000.0F
operator|/
operator|(
name|var1
operator|+
literal|200.0F
operator|)
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|var2
operator|>=
literal|0.0F
condition|)
block|{
name|var2
operator|=
name|MathHelper
operator|.
name|sin
argument_list|(
operator|(
name|var2
operator|/=
operator|(
name|float
operator|)
name|var3
operator|.
name|hurtDuration
operator|)
operator|*
name|var2
operator|*
name|var2
operator|*
name|var2
operator|*
literal|3.1415927F
argument_list|)
expr_stmt|;
name|var1
operator|=
name|var3
operator|.
name|hurtDir
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
name|var3
operator|.
name|hurtDir
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
name|var2
operator|*
literal|14.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
name|var1
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|applyBobbing
parameter_list|(
name|float
name|var1
parameter_list|)
block|{
name|Player
name|var4
decl_stmt|;
name|float
name|var2
init|=
operator|(
name|var4
operator|=
name|this
operator|.
name|minecraft
operator|.
name|player
operator|)
operator|.
name|walkDist
operator|-
name|var4
operator|.
name|walkDistO
decl_stmt|;
name|var2
operator|=
name|var4
operator|.
name|walkDist
operator|+
name|var2
operator|*
name|var1
expr_stmt|;
name|float
name|var3
init|=
name|var4
operator|.
name|oBob
operator|+
operator|(
name|var4
operator|.
name|bob
operator|-
name|var4
operator|.
name|oBob
operator|)
operator|*
name|var1
decl_stmt|;
name|float
name|var5
init|=
name|var4
operator|.
name|oTilt
operator|+
operator|(
name|var4
operator|.
name|tilt
operator|-
name|var4
operator|.
name|oTilt
operator|)
operator|*
name|var1
decl_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
name|MathHelper
operator|.
name|sin
argument_list|(
name|var2
operator|*
literal|3.1415927F
argument_list|)
operator|*
name|var3
operator|*
literal|0.5F
argument_list|,
operator|-
name|Math
operator|.
name|abs
argument_list|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var2
operator|*
literal|3.1415927F
argument_list|)
operator|*
name|var3
argument_list|)
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
name|MathHelper
operator|.
name|sin
argument_list|(
name|var2
operator|*
literal|3.1415927F
argument_list|)
operator|*
name|var3
operator|*
literal|3.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
name|Math
operator|.
name|abs
argument_list|(
name|MathHelper
operator|.
name|cos
argument_list|(
name|var2
operator|*
literal|3.1415927F
operator|+
literal|0.2F
argument_list|)
operator|*
name|var3
argument_list|)
operator|*
literal|5.0F
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
name|var5
argument_list|,
literal|1.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|setLighting
parameter_list|(
name|boolean
name|var1
parameter_list|)
block|{
if|if
condition|(
operator|!
name|var1
condition|)
block|{
name|GL11
operator|.
name|glDisable
argument_list|(
literal|2896
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|16384
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|GL11
operator|.
name|glEnable
argument_list|(
literal|2896
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|16384
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|2903
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColorMaterial
argument_list|(
literal|1032
argument_list|,
literal|5634
argument_list|)
expr_stmt|;
name|float
name|var4
init|=
literal|0.7F
decl_stmt|;
name|float
name|var2
init|=
literal|0.3F
decl_stmt|;
name|Vec3D
name|var3
init|=
operator|(
operator|new
name|Vec3D
argument_list|(
literal|0.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|0.5F
argument_list|)
operator|)
operator|.
name|normalize
argument_list|()
decl_stmt|;
name|GL11
operator|.
name|glLight
argument_list|(
literal|16384
argument_list|,
literal|4611
argument_list|,
name|this
operator|.
name|createBuffer
argument_list|(
name|var3
operator|.
name|x
argument_list|,
name|var3
operator|.
name|y
argument_list|,
name|var3
operator|.
name|z
argument_list|,
literal|0.0F
argument_list|)
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glLight
argument_list|(
literal|16384
argument_list|,
literal|4609
argument_list|,
name|this
operator|.
name|createBuffer
argument_list|(
name|var2
argument_list|,
name|var2
argument_list|,
name|var2
argument_list|,
literal|1.0F
argument_list|)
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glLight
argument_list|(
literal|16384
argument_list|,
literal|4608
argument_list|,
name|this
operator|.
name|createBuffer
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
literal|1.0F
argument_list|)
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glLightModel
argument_list|(
literal|2899
argument_list|,
name|this
operator|.
name|createBuffer
argument_list|(
name|var4
argument_list|,
name|var4
argument_list|,
name|var4
argument_list|,
literal|1.0F
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
specifier|final
name|void
name|enableGuiMode
parameter_list|()
block|{
name|int
name|var1
init|=
name|this
operator|.
name|minecraft
operator|.
name|width
operator|*
literal|240
operator|/
name|this
operator|.
name|minecraft
operator|.
name|height
decl_stmt|;
name|int
name|var2
init|=
name|this
operator|.
name|minecraft
operator|.
name|height
operator|*
literal|240
operator|/
name|this
operator|.
name|minecraft
operator|.
name|height
decl_stmt|;
name|GL11
operator|.
name|glClear
argument_list|(
literal|256
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glMatrixMode
argument_list|(
literal|5889
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glLoadIdentity
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glOrtho
argument_list|(
literal|0.0D
argument_list|,
operator|(
name|double
operator|)
name|var1
argument_list|,
operator|(
name|double
operator|)
name|var2
argument_list|,
literal|0.0D
argument_list|,
literal|100.0D
argument_list|,
literal|300.0D
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glMatrixMode
argument_list|(
literal|5888
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glLoadIdentity
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
literal|0.0F
argument_list|,
literal|0.0F
argument_list|,
operator|-
literal|200.0F
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|updateFog
parameter_list|()
block|{
name|Level
name|var1
init|=
name|this
operator|.
name|minecraft
operator|.
name|level
decl_stmt|;
name|Player
name|var2
init|=
name|this
operator|.
name|minecraft
operator|.
name|player
decl_stmt|;
name|GL11
operator|.
name|glFog
argument_list|(
literal|2918
argument_list|,
name|this
operator|.
name|createBuffer
argument_list|(
name|this
operator|.
name|fogRed
argument_list|,
name|this
operator|.
name|fogBlue
argument_list|,
name|this
operator|.
name|fogGreen
argument_list|,
literal|1.0F
argument_list|)
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glNormal3f
argument_list|(
literal|0.0F
argument_list|,
operator|-
literal|1.0F
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
literal|1.0F
argument_list|)
expr_stmt|;
name|Block
name|var5
decl_stmt|;
if|if
condition|(
operator|(
name|var5
operator|=
name|Block
operator|.
name|blocks
index|[
name|var1
operator|.
name|getTile
argument_list|(
operator|(
name|int
operator|)
name|var2
operator|.
name|x
argument_list|,
operator|(
name|int
operator|)
operator|(
name|var2
operator|.
name|y
operator|+
literal|0.12F
operator|)
argument_list|,
operator|(
name|int
operator|)
name|var2
operator|.
name|z
argument_list|)
index|]
operator|)
operator|!=
literal|null
operator|&&
name|var5
operator|.
name|getLiquidType
argument_list|()
operator|!=
name|LiquidType
operator|.
name|NOT_LIQUID
condition|)
block|{
name|LiquidType
name|var6
init|=
name|var5
operator|.
name|getLiquidType
argument_list|()
decl_stmt|;
name|GL11
operator|.
name|glFogi
argument_list|(
literal|2917
argument_list|,
literal|2048
argument_list|)
expr_stmt|;
name|float
name|var3
decl_stmt|;
name|float
name|var4
decl_stmt|;
name|float
name|var7
decl_stmt|;
name|float
name|var8
decl_stmt|;
if|if
condition|(
name|var6
operator|==
name|LiquidType
operator|.
name|WATER
condition|)
block|{
name|GL11
operator|.
name|glFogf
argument_list|(
literal|2914
argument_list|,
literal|0.1F
argument_list|)
expr_stmt|;
name|var7
operator|=
literal|0.4F
expr_stmt|;
name|var8
operator|=
literal|0.4F
expr_stmt|;
name|var3
operator|=
literal|0.9F
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|minecraft
operator|.
name|settings
operator|.
name|anaglyph
condition|)
block|{
name|var4
operator|=
operator|(
name|var7
operator|*
literal|30.0F
operator|+
name|var8
operator|*
literal|59.0F
operator|+
name|var3
operator|*
literal|11.0F
operator|)
operator|/
literal|100.0F
expr_stmt|;
name|var8
operator|=
operator|(
name|var7
operator|*
literal|30.0F
operator|+
name|var8
operator|*
literal|70.0F
operator|)
operator|/
literal|100.0F
expr_stmt|;
name|var3
operator|=
operator|(
name|var7
operator|*
literal|30.0F
operator|+
name|var3
operator|*
literal|70.0F
operator|)
operator|/
literal|100.0F
expr_stmt|;
name|var7
operator|=
name|var4
expr_stmt|;
name|var8
operator|=
name|var8
expr_stmt|;
name|var3
operator|=
name|var3
expr_stmt|;
block|}
name|GL11
operator|.
name|glLightModel
argument_list|(
literal|2899
argument_list|,
name|this
operator|.
name|createBuffer
argument_list|(
name|var7
argument_list|,
name|var8
argument_list|,
name|var3
argument_list|,
literal|1.0F
argument_list|)
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|var6
operator|==
name|LiquidType
operator|.
name|LAVA
condition|)
block|{
name|GL11
operator|.
name|glFogf
argument_list|(
literal|2914
argument_list|,
literal|2.0F
argument_list|)
expr_stmt|;
name|var7
operator|=
literal|0.4F
expr_stmt|;
name|var8
operator|=
literal|0.3F
expr_stmt|;
name|var3
operator|=
literal|0.3F
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|minecraft
operator|.
name|settings
operator|.
name|anaglyph
condition|)
block|{
name|var4
operator|=
operator|(
name|var7
operator|*
literal|30.0F
operator|+
name|var8
operator|*
literal|59.0F
operator|+
name|var3
operator|*
literal|11.0F
operator|)
operator|/
literal|100.0F
expr_stmt|;
name|var8
operator|=
operator|(
name|var7
operator|*
literal|30.0F
operator|+
name|var8
operator|*
literal|70.0F
operator|)
operator|/
literal|100.0F
expr_stmt|;
name|var3
operator|=
operator|(
name|var7
operator|*
literal|30.0F
operator|+
name|var3
operator|*
literal|70.0F
operator|)
operator|/
literal|100.0F
expr_stmt|;
name|var7
operator|=
name|var4
expr_stmt|;
name|var8
operator|=
name|var8
expr_stmt|;
name|var3
operator|=
name|var3
expr_stmt|;
block|}
name|GL11
operator|.
name|glLightModel
argument_list|(
literal|2899
argument_list|,
name|this
operator|.
name|createBuffer
argument_list|(
name|var7
argument_list|,
name|var8
argument_list|,
name|var3
argument_list|,
literal|1.0F
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|GL11
operator|.
name|glFogi
argument_list|(
literal|2917
argument_list|,
literal|9729
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glFogf
argument_list|(
literal|2915
argument_list|,
literal|0.0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glFogf
argument_list|(
literal|2916
argument_list|,
name|this
operator|.
name|fogEnd
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glLightModel
argument_list|(
literal|2899
argument_list|,
name|this
operator|.
name|createBuffer
argument_list|(
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
literal|1.0F
argument_list|,
literal|1.0F
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glEnable
argument_list|(
literal|2903
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColorMaterial
argument_list|(
literal|1028
argument_list|,
literal|4608
argument_list|)
expr_stmt|;
block|}
specifier|private
name|FloatBuffer
name|createBuffer
parameter_list|(
name|float
name|var1
parameter_list|,
name|float
name|var2
parameter_list|,
name|float
name|var3
parameter_list|,
name|float
name|var4
parameter_list|)
block|{
name|this
operator|.
name|buffer
operator|.
name|clear
argument_list|()
expr_stmt|;
name|this
operator|.
name|buffer
operator|.
name|put
argument_list|(
name|var1
argument_list|)
operator|.
name|put
argument_list|(
name|var2
argument_list|)
operator|.
name|put
argument_list|(
name|var3
argument_list|)
operator|.
name|put
argument_list|(
name|var4
argument_list|)
expr_stmt|;
name|this
operator|.
name|buffer
operator|.
name|flip
argument_list|()
expr_stmt|;
return|return
name|this
operator|.
name|buffer
return|;
block|}
block|}
end_class

end_unit

