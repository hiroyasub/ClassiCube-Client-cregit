begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|mob
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
name|level
operator|.
name|tile
operator|.
name|BlockModelRenderer
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
name|AnimalModel
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
name|HumanoidModel
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
name|Model
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
name|render
operator|.
name|ShapeRenderer
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
name|render
operator|.
name|TextureManager
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
class|class
name|HumanoidMob
extends|extends
name|Mob
block|{
specifier|public
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|0L
decl_stmt|;
specifier|public
specifier|static
name|boolean
name|isInteger
parameter_list|(
name|String
name|s
parameter_list|)
block|{
try|try
block|{
name|Integer
operator|.
name|parseInt
argument_list|(
name|s
argument_list|)
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|NumberFormatException
name|e
parameter_list|)
block|{
return|return
literal|false
return|;
block|}
return|return
literal|true
return|;
block|}
specifier|public
name|boolean
name|helmet
init|=
name|Math
operator|.
name|random
argument_list|()
operator|<
literal|0.20000000298023224D
decl_stmt|;
specifier|public
name|boolean
name|armor
init|=
name|Math
operator|.
name|random
argument_list|()
operator|<
literal|0.20000000298023224D
decl_stmt|;
name|BlockModelRenderer
name|block
decl_stmt|;
specifier|public
name|HumanoidMob
parameter_list|(
name|Level
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
name|super
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|this
operator|.
name|modelName
operator|=
literal|"humanoid"
expr_stmt|;
name|this
operator|.
name|setPos
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|renderModel
parameter_list|(
name|TextureManager
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
parameter_list|,
name|float
name|var5
parameter_list|,
name|float
name|var6
parameter_list|,
name|float
name|var7
parameter_list|)
block|{
if|if
condition|(
name|this
operator|.
name|modelName
operator|==
literal|"sheep"
condition|)
block|{
name|renderSheep
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
expr_stmt|;
return|return;
block|}
if|else if
condition|(
name|isInteger
argument_list|(
name|this
operator|.
name|modelName
argument_list|)
condition|)
block|{
try|try
block|{
name|block
operator|=
operator|new
name|BlockModelRenderer
argument_list|(
name|Block
operator|.
name|blocks
index|[
name|Integer
operator|.
name|parseInt
argument_list|(
name|this
operator|.
name|modelName
argument_list|)
index|]
operator|.
name|textureId
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
operator|-
literal|0.5f
argument_list|,
literal|0.4f
argument_list|,
operator|-
literal|0.5f
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|var1
operator|.
name|load
argument_list|(
literal|"/terrain.png"
argument_list|)
argument_list|)
expr_stmt|;
name|block
operator|.
name|renderPreview
argument_list|(
name|ShapeRenderer
operator|.
name|instance
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
block|}
catch|catch
parameter_list|(
name|Exception
name|e
parameter_list|)
block|{
name|this
operator|.
name|modelName
operator|=
literal|"humanoid"
expr_stmt|;
block|}
return|return;
block|}
name|super
operator|.
name|renderModel
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
expr_stmt|;
name|Model
name|model
init|=
name|modelCache
operator|.
name|getModel
argument_list|(
name|this
operator|.
name|modelName
argument_list|)
decl_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|3008
argument_list|)
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|allowAlpha
condition|)
block|{
name|GL11
operator|.
name|glEnable
argument_list|(
literal|2884
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|hasHair
operator|&&
name|model
operator|instanceof
name|HumanoidModel
condition|)
block|{
name|GL11
operator|.
name|glDisable
argument_list|(
literal|2884
argument_list|)
expr_stmt|;
name|HumanoidModel
name|modelHeadwear
init|=
literal|null
decl_stmt|;
operator|(
name|modelHeadwear
operator|=
operator|(
name|HumanoidModel
operator|)
name|model
operator|)
operator|.
name|headwear
operator|.
name|yaw
operator|=
name|modelHeadwear
operator|.
name|head
operator|.
name|yaw
expr_stmt|;
name|modelHeadwear
operator|.
name|headwear
operator|.
name|pitch
operator|=
name|modelHeadwear
operator|.
name|head
operator|.
name|pitch
expr_stmt|;
name|modelHeadwear
operator|.
name|headwear
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|2884
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|armor
operator|||
name|this
operator|.
name|helmet
condition|)
block|{
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|var1
operator|.
name|load
argument_list|(
literal|"/armor/plate.png"
argument_list|)
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|2884
argument_list|)
expr_stmt|;
name|HumanoidModel
name|modelArmour
decl_stmt|;
operator|(
name|modelArmour
operator|=
operator|(
name|HumanoidModel
operator|)
name|modelCache
operator|.
name|getModel
argument_list|(
literal|"humanoid.armor"
argument_list|)
operator|)
operator|.
name|head
operator|.
name|render
operator|=
name|this
operator|.
name|helmet
expr_stmt|;
name|modelArmour
operator|.
name|body
operator|.
name|render
operator|=
name|this
operator|.
name|armor
expr_stmt|;
name|modelArmour
operator|.
name|rightArm
operator|.
name|render
operator|=
name|this
operator|.
name|armor
expr_stmt|;
name|modelArmour
operator|.
name|leftArm
operator|.
name|render
operator|=
name|this
operator|.
name|armor
expr_stmt|;
name|modelArmour
operator|.
name|rightLeg
operator|.
name|render
operator|=
literal|false
expr_stmt|;
name|modelArmour
operator|.
name|leftLeg
operator|.
name|render
operator|=
literal|false
expr_stmt|;
name|HumanoidModel
name|var11
init|=
operator|(
name|HumanoidModel
operator|)
name|model
decl_stmt|;
name|modelArmour
operator|.
name|head
operator|.
name|yaw
operator|=
name|var11
operator|.
name|head
operator|.
name|yaw
expr_stmt|;
name|modelArmour
operator|.
name|head
operator|.
name|pitch
operator|=
name|var11
operator|.
name|head
operator|.
name|pitch
expr_stmt|;
name|modelArmour
operator|.
name|rightArm
operator|.
name|pitch
operator|=
name|var11
operator|.
name|rightArm
operator|.
name|pitch
expr_stmt|;
name|modelArmour
operator|.
name|rightArm
operator|.
name|roll
operator|=
name|var11
operator|.
name|rightArm
operator|.
name|roll
expr_stmt|;
name|modelArmour
operator|.
name|leftArm
operator|.
name|pitch
operator|=
name|var11
operator|.
name|leftArm
operator|.
name|pitch
expr_stmt|;
name|modelArmour
operator|.
name|leftArm
operator|.
name|roll
operator|=
name|var11
operator|.
name|leftArm
operator|.
name|roll
expr_stmt|;
name|modelArmour
operator|.
name|rightLeg
operator|.
name|pitch
operator|=
name|var11
operator|.
name|rightLeg
operator|.
name|pitch
expr_stmt|;
name|modelArmour
operator|.
name|leftLeg
operator|.
name|pitch
operator|=
name|var11
operator|.
name|leftLeg
operator|.
name|pitch
expr_stmt|;
name|modelArmour
operator|.
name|head
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|modelArmour
operator|.
name|body
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|modelArmour
operator|.
name|rightArm
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|modelArmour
operator|.
name|leftArm
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|modelArmour
operator|.
name|rightLeg
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|modelArmour
operator|.
name|leftLeg
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|2884
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3008
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|renderSheep
parameter_list|(
name|TextureManager
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
parameter_list|,
name|float
name|var5
parameter_list|,
name|float
name|var6
parameter_list|,
name|float
name|var7
parameter_list|)
block|{
name|AnimalModel
name|var8
decl_stmt|;
name|float
name|var9
init|=
operator|(
name|var8
operator|=
operator|(
name|AnimalModel
operator|)
name|modelCache
operator|.
name|getModel
argument_list|(
literal|"sheep"
argument_list|)
operator|)
operator|.
name|head
operator|.
name|y
decl_stmt|;
name|float
name|var10
init|=
name|var8
operator|.
name|head
operator|.
name|z
decl_stmt|;
name|super
operator|.
name|renderModel
argument_list|(
name|var1
argument_list|,
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|,
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|var1
operator|.
name|load
argument_list|(
literal|"/mob/sheep_fur.png"
argument_list|)
argument_list|)
expr_stmt|;
name|AnimalModel
name|var11
decl_stmt|;
operator|(
name|var11
operator|=
operator|(
name|AnimalModel
operator|)
name|modelCache
operator|.
name|getModel
argument_list|(
literal|"sheep.fur"
argument_list|)
operator|)
operator|.
name|head
operator|.
name|yaw
operator|=
name|var8
operator|.
name|head
operator|.
name|yaw
expr_stmt|;
name|var11
operator|.
name|head
operator|.
name|pitch
operator|=
name|var8
operator|.
name|head
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|head
operator|.
name|y
operator|=
name|var8
operator|.
name|head
operator|.
name|y
expr_stmt|;
name|var11
operator|.
name|head
operator|.
name|x
operator|=
name|var8
operator|.
name|head
operator|.
name|x
expr_stmt|;
name|var11
operator|.
name|body
operator|.
name|yaw
operator|=
name|var8
operator|.
name|body
operator|.
name|yaw
expr_stmt|;
name|var11
operator|.
name|body
operator|.
name|pitch
operator|=
name|var8
operator|.
name|body
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|leg1
operator|.
name|pitch
operator|=
name|var8
operator|.
name|leg1
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|leg2
operator|.
name|pitch
operator|=
name|var8
operator|.
name|leg2
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|leg3
operator|.
name|pitch
operator|=
name|var8
operator|.
name|leg3
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|leg4
operator|.
name|pitch
operator|=
name|var8
operator|.
name|leg4
operator|.
name|pitch
expr_stmt|;
name|var11
operator|.
name|head
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|body
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|leg1
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|leg2
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|leg3
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var11
operator|.
name|leg4
operator|.
name|render
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var8
operator|.
name|head
operator|.
name|y
operator|=
name|var9
expr_stmt|;
name|var8
operator|.
name|head
operator|.
name|z
operator|=
name|var10
expr_stmt|;
block|}
block|}
end_class

end_unit

