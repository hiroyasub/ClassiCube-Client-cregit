begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|gui
package|;
end_package

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|ArrayList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
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
name|input
operator|.
name|Keyboard
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

begin_import
import|import
name|com
operator|.
name|mojang
operator|.
name|minecraft
operator|.
name|ChatLine
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
name|PlayerListNameData
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
name|gamemode
operator|.
name|SurvivalGameMode
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
name|player
operator|.
name|Inventory
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
name|com
operator|.
name|mojang
operator|.
name|util
operator|.
name|MathHelper
import|;
end_import

begin_class
specifier|public
specifier|final
class|class
name|HUDScreen
extends|extends
name|Screen
block|{
specifier|public
name|List
argument_list|<
name|ChatLine
argument_list|>
name|chat
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
specifier|private
name|Random
name|random
init|=
operator|new
name|Random
argument_list|()
decl_stmt|;
specifier|private
name|Minecraft
name|mc
decl_stmt|;
specifier|public
name|int
name|width
decl_stmt|;
specifier|public
name|int
name|height
decl_stmt|;
specifier|public
name|String
name|hoveredPlayer
init|=
literal|null
decl_stmt|;
specifier|public
name|int
name|ticks
init|=
literal|0
decl_stmt|;
specifier|public
specifier|static
name|String
name|Compass
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|String
name|ServerName
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|String
name|UserDetail
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|String
name|BottomRight1
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|String
name|BottomRight2
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|String
name|BottomRight3
init|=
literal|""
decl_stmt|;
specifier|public
specifier|static
name|String
name|Announcement
init|=
literal|""
decl_stmt|;
specifier|public
name|List
argument_list|<
name|ChatScreenData
argument_list|>
name|chatsOnScreen
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
name|int
name|Page
init|=
literal|0
decl_stmt|;
specifier|public
name|HUDScreen
parameter_list|(
name|Minecraft
name|minecraft
parameter_list|,
name|int
name|var2
parameter_list|,
name|int
name|var3
parameter_list|)
block|{
name|mc
operator|=
name|minecraft
expr_stmt|;
name|width
operator|=
name|var2
operator|*
literal|240
operator|/
name|var3
expr_stmt|;
name|height
operator|=
name|var3
operator|*
literal|240
operator|/
name|var3
expr_stmt|;
block|}
specifier|public
specifier|final
name|void
name|addChat
parameter_list|(
name|String
name|text
parameter_list|)
block|{
if|if
condition|(
name|text
operator|.
name|contains
argument_list|(
literal|"^detail.user="
argument_list|)
condition|)
block|{
name|Compass
operator|=
name|text
operator|.
name|replace
argument_list|(
literal|"^detail.user="
argument_list|,
literal|""
argument_list|)
expr_stmt|;
return|return;
block|}
name|chat
operator|.
name|add
argument_list|(
literal|0
argument_list|,
operator|new
name|ChatLine
argument_list|(
name|text
argument_list|)
argument_list|)
expr_stmt|;
while|while
condition|(
name|chat
operator|.
name|size
argument_list|()
operator|>
literal|50
condition|)
block|{
name|chat
operator|.
name|remove
argument_list|(
name|chat
operator|.
name|size
argument_list|()
operator|-
literal|1
argument_list|)
expr_stmt|;
block|}
block|}
specifier|public
name|int
name|findGroupChanges
parameter_list|(
name|int
name|Page
parameter_list|,
name|List
argument_list|<
name|PlayerListNameData
argument_list|>
name|playerListNames
parameter_list|)
block|{
name|int
name|groupChanges
init|=
literal|0
decl_stmt|;
name|String
name|lastGroupName
init|=
literal|""
decl_stmt|;
name|int
name|rangeA
init|=
literal|28
operator|*
name|Page
decl_stmt|;
name|int
name|rangeB
init|=
name|rangeA
operator|+
literal|28
decl_stmt|;
name|rangeB
operator|=
name|Math
operator|.
name|min
argument_list|(
name|rangeB
argument_list|,
name|playerListNames
operator|.
name|size
argument_list|()
argument_list|)
expr_stmt|;
name|List
argument_list|<
name|PlayerListNameData
argument_list|>
name|namesToPrint
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|k
init|=
name|rangeA
init|;
name|k
operator|<
name|rangeB
condition|;
name|k
operator|++
control|)
block|{
name|namesToPrint
operator|.
name|add
argument_list|(
name|playerListNames
operator|.
name|get
argument_list|(
name|k
argument_list|)
argument_list|)
expr_stmt|;
block|}
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|namesToPrint
operator|.
name|size
argument_list|()
condition|;
operator|++
name|i
control|)
block|{
name|PlayerListNameData
name|pi
init|=
name|namesToPrint
operator|.
name|get
argument_list|(
name|i
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
name|lastGroupName
operator|.
name|equals
argument_list|(
name|pi
operator|.
name|groupName
argument_list|)
condition|)
block|{
name|lastGroupName
operator|=
name|pi
operator|.
name|groupName
expr_stmt|;
name|groupChanges
operator|++
expr_stmt|;
block|}
block|}
return|return
name|groupChanges
return|;
block|}
specifier|public
specifier|final
name|void
name|render
parameter_list|(
name|float
name|var1
parameter_list|,
name|boolean
name|var2
parameter_list|,
name|int
name|var3
parameter_list|,
name|int
name|var4
parameter_list|)
block|{
name|FontRenderer
name|fontRenderer
init|=
name|mc
operator|.
name|fontRenderer
decl_stmt|;
name|mc
operator|.
name|renderer
operator|.
name|enableGuiMode
argument_list|()
expr_stmt|;
name|TextureManager
name|var6
init|=
name|mc
operator|.
name|textureManager
decl_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|mc
operator|.
name|textureManager
operator|.
name|load
argument_list|(
literal|"/gui/gui.png"
argument_list|)
argument_list|)
expr_stmt|;
name|ShapeRenderer
name|var7
init|=
name|ShapeRenderer
operator|.
name|instance
decl_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
literal|1F
argument_list|,
literal|1F
argument_list|,
literal|1F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|3042
argument_list|)
expr_stmt|;
name|Inventory
name|var8
init|=
name|mc
operator|.
name|player
operator|.
name|inventory
decl_stmt|;
name|imgZ
operator|=
operator|-
literal|90F
expr_stmt|;
name|drawImage
argument_list|(
name|width
operator|/
literal|2
operator|-
literal|91
argument_list|,
name|height
operator|-
literal|22
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
literal|182
argument_list|,
literal|22
argument_list|)
expr_stmt|;
name|drawImage
argument_list|(
name|width
operator|/
literal|2
operator|-
literal|91
operator|-
literal|1
operator|+
name|var8
operator|.
name|selected
operator|*
literal|20
argument_list|,
name|height
operator|-
literal|22
operator|-
literal|1
argument_list|,
literal|0
argument_list|,
literal|22
argument_list|,
literal|24
argument_list|,
literal|22
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|mc
operator|.
name|textureManager
operator|.
name|load
argument_list|(
literal|"/gui/icons.png"
argument_list|)
argument_list|)
expr_stmt|;
name|drawImage
argument_list|(
name|width
operator|/
literal|2
operator|-
literal|7
argument_list|,
name|height
operator|/
literal|2
operator|-
literal|7
argument_list|,
literal|0
argument_list|,
literal|0
argument_list|,
literal|16
argument_list|,
literal|16
argument_list|)
expr_stmt|;
name|boolean
name|var9
init|=
operator|(
name|mc
operator|.
name|player
operator|.
name|invulnerableTime
operator|/
literal|3
operator|&
literal|1
operator|)
operator|==
literal|1
decl_stmt|;
if|if
condition|(
name|mc
operator|.
name|player
operator|.
name|invulnerableTime
operator|<
literal|10
condition|)
block|{
name|var9
operator|=
literal|false
expr_stmt|;
block|}
name|int
name|health
init|=
name|mc
operator|.
name|player
operator|.
name|health
decl_stmt|;
name|int
name|lastHealth
init|=
name|mc
operator|.
name|player
operator|.
name|lastHealth
decl_stmt|;
name|random
operator|.
name|setSeed
argument_list|(
name|ticks
operator|*
literal|312871
argument_list|)
expr_stmt|;
name|int
name|var12
decl_stmt|;
name|int
name|i
decl_stmt|;
name|int
name|var15
decl_stmt|;
name|int
name|var26
decl_stmt|;
if|if
condition|(
name|mc
operator|.
name|gamemode
operator|.
name|isSurvival
argument_list|()
condition|)
block|{
for|for
control|(
name|var12
operator|=
literal|0
init|;
name|var12
operator|<
literal|10
condition|;
operator|++
name|var12
control|)
block|{
name|byte
name|var13
init|=
literal|0
decl_stmt|;
if|if
condition|(
name|var9
condition|)
block|{
name|var13
operator|=
literal|1
expr_stmt|;
block|}
name|i
operator|=
name|width
operator|/
literal|2
operator|-
literal|91
operator|+
operator|(
name|var12
operator|<<
literal|3
operator|)
expr_stmt|;
name|var15
operator|=
name|height
operator|-
literal|32
expr_stmt|;
if|if
condition|(
name|health
operator|<=
literal|4
condition|)
block|{
name|var15
operator|+=
name|random
operator|.
name|nextInt
argument_list|(
literal|2
argument_list|)
expr_stmt|;
block|}
name|drawImage
argument_list|(
name|i
argument_list|,
name|var15
argument_list|,
literal|16
operator|+
name|var13
operator|*
literal|9
argument_list|,
literal|0
argument_list|,
literal|9
argument_list|,
literal|9
argument_list|)
expr_stmt|;
if|if
condition|(
name|var9
condition|)
block|{
if|if
condition|(
operator|(
name|var12
operator|<<
literal|1
operator|)
operator|+
literal|1
operator|<
name|lastHealth
condition|)
block|{
name|drawImage
argument_list|(
name|i
argument_list|,
name|var15
argument_list|,
literal|70
argument_list|,
literal|0
argument_list|,
literal|9
argument_list|,
literal|9
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
operator|(
name|var12
operator|<<
literal|1
operator|)
operator|+
literal|1
operator|==
name|lastHealth
condition|)
block|{
name|drawImage
argument_list|(
name|i
argument_list|,
name|var15
argument_list|,
literal|79
argument_list|,
literal|0
argument_list|,
literal|9
argument_list|,
literal|9
argument_list|)
expr_stmt|;
block|}
block|}
if|if
condition|(
operator|(
name|var12
operator|<<
literal|1
operator|)
operator|+
literal|1
operator|<
name|health
condition|)
block|{
name|drawImage
argument_list|(
name|i
argument_list|,
name|var15
argument_list|,
literal|52
argument_list|,
literal|0
argument_list|,
literal|9
argument_list|,
literal|9
argument_list|)
expr_stmt|;
block|}
if|if
condition|(
operator|(
name|var12
operator|<<
literal|1
operator|)
operator|+
literal|1
operator|==
name|health
condition|)
block|{
name|drawImage
argument_list|(
name|i
argument_list|,
name|var15
argument_list|,
literal|61
argument_list|,
literal|0
argument_list|,
literal|9
argument_list|,
literal|9
argument_list|)
expr_stmt|;
block|}
block|}
if|if
condition|(
name|mc
operator|.
name|player
operator|.
name|isUnderWater
argument_list|()
condition|)
block|{
name|var12
operator|=
operator|(
name|int
operator|)
name|Math
operator|.
name|ceil
argument_list|(
operator|(
name|mc
operator|.
name|player
operator|.
name|airSupply
operator|-
literal|2
operator|)
operator|*
literal|10D
operator|/
literal|300D
argument_list|)
expr_stmt|;
name|var26
operator|=
operator|(
name|int
operator|)
name|Math
operator|.
name|ceil
argument_list|(
name|mc
operator|.
name|player
operator|.
name|airSupply
operator|*
literal|10D
operator|/
literal|300D
argument_list|)
operator|-
name|var12
expr_stmt|;
for|for
control|(
name|i
operator|=
literal|0
init|;
name|i
operator|<
name|var12
operator|+
name|var26
condition|;
operator|++
name|i
control|)
block|{
if|if
condition|(
name|i
operator|<
name|var12
condition|)
block|{
name|drawImage
argument_list|(
name|width
operator|/
literal|2
operator|-
literal|91
operator|+
operator|(
name|i
operator|<<
literal|3
operator|)
argument_list|,
name|height
operator|-
literal|32
operator|-
literal|9
argument_list|,
literal|16
argument_list|,
literal|18
argument_list|,
literal|9
argument_list|,
literal|9
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|drawImage
argument_list|(
name|width
operator|/
literal|2
operator|-
literal|91
operator|+
operator|(
name|i
operator|<<
literal|3
operator|)
argument_list|,
name|height
operator|-
literal|32
operator|-
literal|9
argument_list|,
literal|25
argument_list|,
literal|18
argument_list|,
literal|9
argument_list|,
literal|9
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3042
argument_list|)
expr_stmt|;
name|String
name|var23
decl_stmt|;
for|for
control|(
name|var12
operator|=
literal|0
init|;
name|var12
operator|<
name|var8
operator|.
name|slots
operator|.
name|length
condition|;
operator|++
name|var12
control|)
block|{
name|var26
operator|=
name|width
operator|/
literal|2
operator|-
literal|90
operator|+
name|var12
operator|*
literal|20
expr_stmt|;
name|i
operator|=
name|height
operator|-
literal|16
expr_stmt|;
if|if
condition|(
operator|(
name|var15
operator|=
name|var8
operator|.
name|slots
index|[
name|var12
index|]
operator|)
operator|>
literal|0
condition|)
block|{
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
name|var26
argument_list|,
name|i
argument_list|,
operator|-
literal|50F
argument_list|)
expr_stmt|;
if|if
condition|(
name|var8
operator|.
name|popTime
index|[
name|var12
index|]
operator|>
literal|0
condition|)
block|{
name|float
name|var18
decl_stmt|;
name|float
name|var21
init|=
operator|-
name|MathHelper
operator|.
name|sin
argument_list|(
operator|(
name|var18
operator|=
operator|(
name|var8
operator|.
name|popTime
index|[
name|var12
index|]
operator|-
name|var1
operator|)
operator|/
literal|5F
operator|)
operator|*
name|var18
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
operator|*
literal|8F
decl_stmt|;
name|float
name|var19
init|=
name|MathHelper
operator|.
name|sin
argument_list|(
name|var18
operator|*
name|var18
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
operator|+
literal|1F
decl_stmt|;
name|float
name|var16
init|=
name|MathHelper
operator|.
name|sin
argument_list|(
name|var18
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|PI
argument_list|)
operator|+
literal|1F
decl_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
literal|10F
argument_list|,
name|var21
operator|+
literal|10F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
name|var19
argument_list|,
name|var16
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
operator|-
literal|10F
argument_list|,
operator|-
literal|10F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glScalef
argument_list|(
literal|10F
argument_list|,
literal|10F
argument_list|,
literal|10F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
literal|1F
argument_list|,
literal|0.5F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
operator|-
literal|30F
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glRotatef
argument_list|(
literal|45F
argument_list|,
literal|0F
argument_list|,
literal|1F
argument_list|,
literal|0F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glTranslatef
argument_list|(
operator|-
literal|1.5F
argument_list|,
literal|0.5F
argument_list|,
literal|0.5F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
operator|-
literal|1F
argument_list|,
operator|-
literal|1F
argument_list|,
operator|-
literal|1F
argument_list|)
expr_stmt|;
name|int
name|var20
init|=
name|var6
operator|.
name|load
argument_list|(
literal|"/terrain.png"
argument_list|)
decl_stmt|;
name|GL11
operator|.
name|glBindTexture
argument_list|(
literal|3553
argument_list|,
name|var20
argument_list|)
expr_stmt|;
name|var7
operator|.
name|begin
argument_list|()
expr_stmt|;
name|Block
operator|.
name|blocks
index|[
name|var15
index|]
operator|.
name|renderFullBrightness
argument_list|(
name|var7
argument_list|)
expr_stmt|;
name|var7
operator|.
name|end
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
if|if
condition|(
name|var8
operator|.
name|count
index|[
name|var12
index|]
operator|>
literal|1
condition|)
block|{
name|var23
operator|=
literal|""
operator|+
name|var8
operator|.
name|count
index|[
name|var12
index|]
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|var23
argument_list|,
name|var26
operator|+
literal|19
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|var23
argument_list|)
argument_list|,
name|i
operator|+
literal|6
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
block|}
block|}
comment|// if (Minecraft.isSinglePlayer)
comment|// var5.render("Development Build", 2, 32, 16777215);
if|if
condition|(
name|mc
operator|.
name|settings
operator|.
name|showDebug
condition|)
block|{
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|0.7F
argument_list|,
literal|0.7F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
literal|"ClassiCube"
argument_list|,
literal|2
argument_list|,
literal|2
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
comment|// lol fuck that.
name|fontRenderer
operator|.
name|render
argument_list|(
name|mc
operator|.
name|debug
argument_list|,
literal|2
argument_list|,
literal|12
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
literal|"Position: ("
operator|+
operator|(
name|int
operator|)
name|mc
operator|.
name|player
operator|.
name|x
operator|+
literal|", "
operator|+
operator|(
name|int
operator|)
name|mc
operator|.
name|player
operator|.
name|y
operator|+
literal|", "
operator|+
operator|(
name|int
operator|)
name|mc
operator|.
name|player
operator|.
name|z
operator|+
literal|")"
argument_list|,
literal|2
argument_list|,
literal|22
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
block|}
name|fontRenderer
operator|.
name|render
argument_list|(
name|Compass
argument_list|,
name|width
operator|-
operator|(
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|Compass
argument_list|)
operator|+
literal|2
operator|)
argument_list|,
literal|12
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|ServerName
argument_list|,
name|width
operator|-
operator|(
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|ServerName
argument_list|)
operator|+
literal|2
operator|)
argument_list|,
literal|2
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|UserDetail
argument_list|,
name|width
operator|-
operator|(
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|UserDetail
argument_list|)
operator|+
literal|2
operator|)
argument_list|,
literal|24
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|BottomRight1
argument_list|,
name|width
operator|-
operator|(
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|BottomRight1
argument_list|)
operator|+
literal|2
operator|)
argument_list|,
name|height
operator|-
literal|35
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|BottomRight2
argument_list|,
name|width
operator|-
operator|(
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|BottomRight2
argument_list|)
operator|+
literal|2
operator|)
argument_list|,
name|height
operator|-
literal|45
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|BottomRight3
argument_list|,
name|width
operator|-
operator|(
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|BottomRight3
argument_list|)
operator|+
literal|2
operator|)
argument_list|,
name|height
operator|-
literal|55
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|1.5F
argument_list|,
literal|1.5F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|Announcement
argument_list|,
operator|(
name|width
operator|/
literal|3
operator|)
operator|-
operator|(
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|Announcement
argument_list|)
operator|/
literal|2
operator|)
argument_list|,
literal|35
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glPushMatrix
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glScalef
argument_list|(
literal|0.7F
argument_list|,
literal|0.7F
argument_list|,
literal|1F
argument_list|)
expr_stmt|;
if|if
condition|(
name|mc
operator|.
name|player
operator|.
name|flyingMode
operator|&&
operator|!
name|mc
operator|.
name|player
operator|.
name|noPhysics
condition|)
block|{
name|fontRenderer
operator|.
name|render
argument_list|(
literal|"Fly: ON."
argument_list|,
literal|2
argument_list|,
literal|32
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
operator|!
name|mc
operator|.
name|player
operator|.
name|flyingMode
operator|&&
name|mc
operator|.
name|player
operator|.
name|noPhysics
condition|)
block|{
name|fontRenderer
operator|.
name|render
argument_list|(
literal|"NoClip: ON."
argument_list|,
literal|2
argument_list|,
literal|32
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
if|else if
condition|(
name|mc
operator|.
name|player
operator|.
name|flyingMode
operator|&&
name|mc
operator|.
name|player
operator|.
name|noPhysics
condition|)
block|{
name|fontRenderer
operator|.
name|render
argument_list|(
literal|"Fly: ON. NoClip: ON"
argument_list|,
literal|2
argument_list|,
literal|32
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
name|GL11
operator|.
name|glPopMatrix
argument_list|()
expr_stmt|;
if|if
condition|(
name|mc
operator|.
name|gamemode
operator|instanceof
name|SurvivalGameMode
condition|)
block|{
name|String
name|var24
init|=
literal|"Score:&e"
operator|+
name|mc
operator|.
name|player
operator|.
name|getScore
argument_list|()
decl_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|var24
argument_list|,
name|width
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|var24
argument_list|)
operator|-
literal|2
argument_list|,
literal|2
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
literal|"Arrows: "
operator|+
name|mc
operator|.
name|player
operator|.
name|arrows
argument_list|,
name|width
operator|/
literal|2
operator|+
literal|8
argument_list|,
name|height
operator|-
literal|33
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
name|byte
name|chatLinesInScreen
init|=
literal|10
decl_stmt|;
comment|// chats per screen
name|boolean
name|isLargeChatScreen
init|=
literal|false
decl_stmt|;
if|if
condition|(
name|mc
operator|.
name|currentScreen
operator|instanceof
name|ChatInputScreen
condition|)
block|{
name|chatLinesInScreen
operator|=
literal|20
expr_stmt|;
name|isLargeChatScreen
operator|=
literal|true
expr_stmt|;
block|}
name|chatLinesInScreen
operator|=
operator|(
name|byte
operator|)
operator|(
name|chatLinesInScreen
operator|+
operator|(
name|chatLinesInScreen
operator|-
name|chatLinesInScreen
operator|*
name|mc
operator|.
name|settings
operator|.
name|scale
operator|)
operator|-
literal|1
operator|)
expr_stmt|;
if|if
condition|(
name|isLargeChatScreen
condition|)
block|{
name|int
name|chatX
init|=
literal|2
decl_stmt|;
name|int
name|chatY
init|=
name|height
operator|-
name|chatsOnScreen
operator|.
name|size
argument_list|()
operator|*
literal|9
operator|-
literal|30
decl_stmt|;
comment|// The longest line's length
name|String
name|longestMessageNoColor
init|=
literal|""
decl_stmt|;
name|String
name|longestMessage
init|=
literal|""
decl_stmt|;
for|for
control|(
name|ChatScreenData
name|line
range|:
name|chatsOnScreen
control|)
block|{
name|String
name|lineNoColor
init|=
name|FontRenderer
operator|.
name|stripColor
argument_list|(
name|line
operator|.
name|string
argument_list|)
decl_stmt|;
if|if
condition|(
name|lineNoColor
operator|.
name|length
argument_list|()
operator|>
name|longestMessageNoColor
operator|.
name|length
argument_list|()
condition|)
block|{
name|longestMessage
operator|=
name|line
operator|.
name|string
expr_stmt|;
name|longestMessageNoColor
operator|=
name|lineNoColor
expr_stmt|;
block|}
block|}
name|int
name|messageWidth
init|=
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|longestMessage
argument_list|)
decl_stmt|;
name|int
name|chatWidth
init|=
name|chatX
operator|+
name|messageWidth
operator|+
literal|6
decl_stmt|;
comment|// Get the chat lines, multiply by their height to get the chat
comment|// height.
name|int
name|chatHeight
init|=
name|chatY
operator|+
name|chatsOnScreen
operator|.
name|size
argument_list|()
operator|*
literal|9
operator|+
literal|6
decl_stmt|;
name|drawBox
argument_list|(
name|chatX
argument_list|,
name|chatY
argument_list|,
name|chatWidth
argument_list|,
name|chatHeight
argument_list|,
name|ChatInputScreen
operator|.
name|ChatRGB
argument_list|)
expr_stmt|;
block|}
name|chatsOnScreen
operator|.
name|clear
argument_list|()
expr_stmt|;
for|for
control|(
name|i
operator|=
literal|0
init|;
name|i
operator|<
name|chat
operator|.
name|size
argument_list|()
operator|&&
name|i
operator|<
name|chatLinesInScreen
condition|;
operator|++
name|i
control|)
block|{
if|if
condition|(
name|chat
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|.
name|time
operator|<
literal|200
operator|||
name|isLargeChatScreen
condition|)
block|{
name|String
name|message
init|=
name|chat
operator|.
name|get
argument_list|(
name|i
argument_list|)
operator|.
name|message
decl_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|message
argument_list|,
literal|4
argument_list|,
name|height
operator|-
literal|8
operator|-
name|i
operator|*
literal|9
operator|-
literal|27
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
comment|// add click data for urls
name|chatsOnScreen
operator|.
name|add
argument_list|(
operator|new
name|ChatScreenData
argument_list|(
literal|1
argument_list|,
literal|8
argument_list|,
literal|4
argument_list|,
name|height
operator|-
literal|8
operator|-
name|i
operator|*
literal|9
operator|-
literal|27
argument_list|,
name|message
argument_list|,
name|fontRenderer
argument_list|)
argument_list|)
expr_stmt|;
block|}
block|}
name|i
operator|=
name|width
operator|/
literal|2
expr_stmt|;
name|var15
operator|=
name|height
operator|/
literal|2
expr_stmt|;
name|hoveredPlayer
operator|=
literal|null
expr_stmt|;
if|if
condition|(
name|Keyboard
operator|.
name|isCreated
argument_list|()
condition|)
block|{
if|if
condition|(
name|Keyboard
operator|.
name|isKeyDown
argument_list|(
literal|15
argument_list|)
operator|&&
name|mc
operator|.
name|networkManager
operator|!=
literal|null
operator|&&
name|mc
operator|.
name|networkManager
operator|.
name|isConnected
argument_list|()
condition|)
block|{
for|for
control|(
name|int
name|l
init|=
literal|2
init|;
name|l
operator|<
literal|11
condition|;
name|l
operator|++
control|)
block|{
if|if
condition|(
name|Keyboard
operator|.
name|isKeyDown
argument_list|(
name|l
argument_list|)
condition|)
block|{
name|Page
operator|=
name|l
operator|-
literal|2
expr_stmt|;
block|}
block|}
name|List
argument_list|<
name|String
argument_list|>
name|playersOnWorld
init|=
name|mc
operator|.
name|networkManager
operator|.
name|getPlayers
argument_list|()
decl_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|3042
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3553
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBlendFunc
argument_list|(
literal|770
argument_list|,
literal|771
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glBegin
argument_list|(
literal|7
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0F
argument_list|,
literal|0.7F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
name|i
operator|+
literal|132
argument_list|,
name|var15
operator|-
literal|72
operator|-
literal|12
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
name|i
operator|-
literal|132
argument_list|,
name|var15
operator|-
literal|72
operator|-
literal|12
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glColor4f
argument_list|(
literal|0.2F
argument_list|,
literal|0.2F
argument_list|,
literal|0.2F
argument_list|,
literal|0.8F
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
name|i
operator|-
literal|132
argument_list|,
name|var15
operator|+
literal|72
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glVertex2f
argument_list|(
name|i
operator|+
literal|132
argument_list|,
name|var15
operator|+
literal|72
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnd
argument_list|()
expr_stmt|;
name|GL11
operator|.
name|glDisable
argument_list|(
literal|3042
argument_list|)
expr_stmt|;
name|GL11
operator|.
name|glEnable
argument_list|(
literal|3553
argument_list|)
expr_stmt|;
name|boolean
name|drawDefault
init|=
literal|false
decl_stmt|;
name|List
argument_list|<
name|PlayerListNameData
argument_list|>
name|playerListNames
init|=
name|mc
operator|.
name|playerListNameData
decl_stmt|;
if|if
condition|(
name|playerListNames
operator|.
name|isEmpty
argument_list|()
condition|)
block|{
name|drawDefault
operator|=
literal|true
expr_stmt|;
block|}
name|int
name|maxStringsPerColumn
init|=
literal|14
decl_stmt|;
name|int
name|maxStringsPerScreen
init|=
literal|28
decl_stmt|;
name|var23
operator|=
operator|!
name|drawDefault
condition|?
literal|"Players online: (Page "
operator|+
operator|(
name|Page
operator|+
literal|1
operator|)
operator|+
literal|")"
else|:
literal|"Players online:"
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|var23
argument_list|,
name|i
operator|-
name|fontRenderer
operator|.
name|getWidth
argument_list|(
name|var23
argument_list|)
operator|/
literal|2
argument_list|,
name|var15
operator|-
literal|64
operator|-
literal|12
argument_list|,
literal|25855
argument_list|)
expr_stmt|;
if|if
condition|(
name|drawDefault
condition|)
block|{
for|for
control|(
name|lastHealth
operator|=
literal|0
init|;
name|lastHealth
operator|<
name|playersOnWorld
operator|.
name|size
argument_list|()
condition|;
operator|++
name|lastHealth
control|)
block|{
name|int
name|var28
init|=
name|i
operator|+
name|lastHealth
operator|%
literal|2
operator|*
literal|120
operator|-
literal|120
decl_stmt|;
name|int
name|var17
init|=
name|var15
operator|-
literal|64
operator|+
operator|(
name|lastHealth
operator|/
literal|2
operator|<<
literal|3
operator|)
decl_stmt|;
if|if
condition|(
name|var2
operator|&&
name|var3
operator|>=
name|var28
operator|&&
name|var4
operator|>=
name|var17
operator|&&
name|var3
operator|<
name|var28
operator|+
literal|120
operator|&&
name|var4
operator|<
name|var17
operator|+
literal|8
condition|)
block|{
name|hoveredPlayer
operator|=
name|playersOnWorld
operator|.
name|get
argument_list|(
name|lastHealth
argument_list|)
expr_stmt|;
name|fontRenderer
operator|.
name|renderNoShadow
argument_list|(
name|playersOnWorld
operator|.
name|get
argument_list|(
name|lastHealth
argument_list|)
argument_list|,
name|var28
operator|+
literal|2
argument_list|,
name|var17
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|fontRenderer
operator|.
name|renderNoShadow
argument_list|(
name|playersOnWorld
operator|.
name|get
argument_list|(
name|lastHealth
argument_list|)
argument_list|,
name|var28
argument_list|,
name|var17
argument_list|,
literal|15658734
argument_list|)
expr_stmt|;
block|}
block|}
block|}
else|else
block|{
comment|// draw the new screen
name|String
name|lastGroupName
init|=
literal|""
decl_stmt|;
name|int
name|x
init|=
name|i
operator|+
literal|8
decl_stmt|;
name|int
name|y
init|=
name|var15
operator|-
literal|73
decl_stmt|;
name|int
name|groupChanges
init|=
literal|0
decl_stmt|;
name|boolean
name|hasStartedNewColumn
init|=
literal|false
decl_stmt|;
name|List
argument_list|<
name|PlayerListNameData
argument_list|>
name|namesToPrint
init|=
operator|new
name|ArrayList
argument_list|<>
argument_list|()
decl_stmt|;
for|for
control|(
name|int
name|m
init|=
literal|0
init|;
name|m
operator|<
name|Page
condition|;
name|m
operator|++
control|)
block|{
name|groupChanges
operator|+=
name|findGroupChanges
argument_list|(
name|m
argument_list|,
name|playerListNames
argument_list|)
expr_stmt|;
block|}
name|int
name|rangeA
init|=
name|maxStringsPerScreen
operator|*
name|Page
operator|-
name|groupChanges
decl_stmt|;
name|int
name|rangeB
init|=
name|rangeA
operator|+
name|maxStringsPerScreen
operator|-
name|findGroupChanges
argument_list|(
name|Page
argument_list|,
name|playerListNames
argument_list|)
decl_stmt|;
name|rangeB
operator|=
name|Math
operator|.
name|min
argument_list|(
name|rangeB
argument_list|,
name|playerListNames
operator|.
name|size
argument_list|()
argument_list|)
expr_stmt|;
for|for
control|(
name|int
name|k
init|=
name|rangeA
init|;
name|k
operator|<
name|rangeB
condition|;
name|k
operator|++
control|)
block|{
name|namesToPrint
operator|.
name|add
argument_list|(
name|playerListNames
operator|.
name|get
argument_list|(
name|k
argument_list|)
argument_list|)
expr_stmt|;
block|}
name|int
name|groupsOnThisPage
init|=
literal|0
decl_stmt|;
for|for
control|(
name|lastHealth
operator|=
literal|0
init|;
name|lastHealth
operator|<
name|namesToPrint
operator|.
name|size
argument_list|()
condition|;
operator|++
name|lastHealth
control|)
block|{
if|if
condition|(
name|lastHealth
operator|<
name|maxStringsPerColumn
operator|-
name|groupsOnThisPage
condition|)
block|{
name|x
operator|=
name|i
operator|-
literal|128
operator|+
literal|8
expr_stmt|;
block|}
else|else
block|{
if|if
condition|(
name|lastHealth
operator|>=
name|maxStringsPerColumn
operator|-
name|groupsOnThisPage
operator|&&
operator|!
name|hasStartedNewColumn
condition|)
block|{
name|y
operator|=
name|var15
operator|-
literal|73
expr_stmt|;
name|hasStartedNewColumn
operator|=
literal|true
expr_stmt|;
block|}
name|x
operator|=
name|i
operator|+
literal|8
expr_stmt|;
block|}
name|y
operator|+=
literal|9
expr_stmt|;
name|PlayerListNameData
name|pi
init|=
name|namesToPrint
operator|.
name|get
argument_list|(
name|lastHealth
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
name|lastGroupName
operator|.
name|equals
argument_list|(
name|pi
operator|.
name|groupName
argument_list|)
condition|)
block|{
name|lastGroupName
operator|=
name|pi
operator|.
name|groupName
expr_stmt|;
name|fontRenderer
operator|.
name|render
argument_list|(
name|lastGroupName
argument_list|,
name|x
operator|+
literal|2
argument_list|,
name|y
argument_list|,
literal|51455
argument_list|)
expr_stmt|;
name|groupsOnThisPage
operator|++
expr_stmt|;
name|y
operator|+=
literal|9
expr_stmt|;
block|}
name|String
name|playerName
init|=
name|FontRenderer
operator|.
name|stripColor
argument_list|(
name|pi
operator|.
name|playerName
argument_list|)
decl_stmt|;
name|String
name|listName
init|=
name|pi
operator|.
name|listName
decl_stmt|;
if|if
condition|(
name|var2
operator|&&
name|var3
operator|>=
name|x
operator|&&
name|var4
operator|>=
name|y
operator|&&
name|var3
operator|<
name|x
operator|+
literal|120
operator|&&
name|var4
operator|<
name|y
operator|+
literal|8
condition|)
block|{
comment|// if your mouse is hovered over this name
name|hoveredPlayer
operator|=
name|playerName
expr_stmt|;
name|fontRenderer
operator|.
name|renderNoShadow
argument_list|(
name|listName
argument_list|,
name|x
operator|+
literal|8
argument_list|,
name|y
argument_list|,
literal|16777215
argument_list|)
expr_stmt|;
block|}
else|else
block|{
comment|// else render a normal name
name|fontRenderer
operator|.
name|renderNoShadow
argument_list|(
name|listName
argument_list|,
name|x
operator|+
literal|6
argument_list|,
name|y
argument_list|,
literal|15658734
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

