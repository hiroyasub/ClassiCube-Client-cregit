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
name|phys
operator|.
name|AABB
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
name|Frustrum
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
name|java
operator|.
name|io
operator|.
name|Serializable
import|;
end_import

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

begin_class
specifier|public
class|class
name|BlockMap
implements|implements
name|Serializable
block|{
specifier|public
specifier|static
specifier|final
name|long
name|serialVersionUID
init|=
literal|0L
decl_stmt|;
comment|// $FF: synthetic method
specifier|static
name|int
name|getDepth
parameter_list|(
name|BlockMap
name|var0
parameter_list|)
block|{
return|return
name|var0
operator|.
name|depth
return|;
block|}
comment|// $FF: synthetic method
specifier|static
name|int
name|getHeight
parameter_list|(
name|BlockMap
name|var0
parameter_list|)
block|{
return|return
name|var0
operator|.
name|height
return|;
block|}
comment|// $FF: synthetic method
specifier|static
name|int
name|getWidth
parameter_list|(
name|BlockMap
name|var0
parameter_list|)
block|{
return|return
name|var0
operator|.
name|width
return|;
block|}
specifier|private
name|int
name|width
decl_stmt|;
specifier|private
name|int
name|depth
decl_stmt|;
specifier|private
name|int
name|height
decl_stmt|;
specifier|private
name|BlockMap$Slot
name|slot
init|=
operator|new
name|BlockMap$Slot
argument_list|(
name|this
argument_list|)
decl_stmt|;
specifier|private
name|BlockMap$Slot
name|slot2
init|=
operator|new
name|BlockMap$Slot
argument_list|(
name|this
argument_list|)
decl_stmt|;
specifier|public
name|List
argument_list|<
name|Entity
argument_list|>
index|[]
name|entityGrid
decl_stmt|;
specifier|public
name|List
argument_list|<
name|Entity
argument_list|>
name|all
init|=
operator|new
name|ArrayList
argument_list|<
name|Entity
argument_list|>
argument_list|()
decl_stmt|;
specifier|private
name|List
argument_list|<
name|Entity
argument_list|>
name|tmp
init|=
operator|new
name|ArrayList
argument_list|<
name|Entity
argument_list|>
argument_list|()
decl_stmt|;
annotation|@
name|SuppressWarnings
argument_list|(
literal|"unchecked"
argument_list|)
specifier|public
name|BlockMap
parameter_list|(
name|int
name|var1
parameter_list|,
name|int
name|var2
parameter_list|,
name|int
name|var3
parameter_list|)
block|{
name|this
operator|.
name|width
operator|=
name|var1
operator|/
literal|16
expr_stmt|;
name|this
operator|.
name|depth
operator|=
name|var2
operator|/
literal|16
expr_stmt|;
name|this
operator|.
name|height
operator|=
name|var3
operator|/
literal|16
expr_stmt|;
if|if
condition|(
name|this
operator|.
name|width
operator|==
literal|0
condition|)
block|{
name|this
operator|.
name|width
operator|=
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|depth
operator|==
literal|0
condition|)
block|{
name|this
operator|.
name|depth
operator|=
literal|1
expr_stmt|;
block|}
if|if
condition|(
name|this
operator|.
name|height
operator|==
literal|0
condition|)
block|{
name|this
operator|.
name|height
operator|=
literal|1
expr_stmt|;
block|}
name|this
operator|.
name|entityGrid
operator|=
operator|new
name|ArrayList
index|[
name|this
operator|.
name|width
operator|*
name|this
operator|.
name|depth
operator|*
name|this
operator|.
name|height
index|]
expr_stmt|;
for|for
control|(
name|var1
operator|=
literal|0
init|;
name|var1
operator|<
name|this
operator|.
name|width
condition|;
operator|++
name|var1
control|)
block|{
for|for
control|(
name|var2
operator|=
literal|0
init|;
name|var2
operator|<
name|this
operator|.
name|depth
condition|;
operator|++
name|var2
control|)
block|{
for|for
control|(
name|var3
operator|=
literal|0
init|;
name|var3
operator|<
name|this
operator|.
name|height
condition|;
operator|++
name|var3
control|)
block|{
name|this
operator|.
name|entityGrid
index|[
operator|(
name|var3
operator|*
name|this
operator|.
name|depth
operator|+
name|var2
operator|)
operator|*
name|this
operator|.
name|width
operator|+
name|var1
index|]
operator|=
operator|new
name|ArrayList
argument_list|<
name|Entity
argument_list|>
argument_list|()
expr_stmt|;
block|}
block|}
block|}
block|}
specifier|public
name|void
name|clear
parameter_list|()
block|{
for|for
control|(
name|int
name|var1
init|=
literal|0
init|;
name|var1
operator|<
name|this
operator|.
name|width
condition|;
operator|++
name|var1
control|)
block|{
for|for
control|(
name|int
name|var2
init|=
literal|0
init|;
name|var2
operator|<
name|this
operator|.
name|depth
condition|;
operator|++
name|var2
control|)
block|{
for|for
control|(
name|int
name|var3
init|=
literal|0
init|;
name|var3
operator|<
name|this
operator|.
name|height
condition|;
operator|++
name|var3
control|)
block|{
name|this
operator|.
name|entityGrid
index|[
operator|(
name|var3
operator|*
name|this
operator|.
name|depth
operator|+
name|var2
operator|)
operator|*
name|this
operator|.
name|width
operator|+
name|var1
index|]
operator|.
name|clear
argument_list|()
expr_stmt|;
block|}
block|}
block|}
block|}
specifier|public
name|List
argument_list|<
name|Entity
argument_list|>
name|getEntities
parameter_list|(
name|Entity
name|var1
parameter_list|,
name|AABB
name|var2
parameter_list|)
block|{
name|this
operator|.
name|tmp
operator|.
name|clear
argument_list|()
expr_stmt|;
return|return
name|this
operator|.
name|getEntities
argument_list|(
name|var1
argument_list|,
name|var2
operator|.
name|x0
argument_list|,
name|var2
operator|.
name|y0
argument_list|,
name|var2
operator|.
name|z0
argument_list|,
name|var2
operator|.
name|x1
argument_list|,
name|var2
operator|.
name|y1
argument_list|,
name|var2
operator|.
name|z1
argument_list|,
name|this
operator|.
name|tmp
argument_list|)
return|;
block|}
specifier|public
name|List
argument_list|<
name|Entity
argument_list|>
name|getEntities
parameter_list|(
name|Entity
name|var1
parameter_list|,
name|AABB
name|var2
parameter_list|,
name|List
argument_list|<
name|Entity
argument_list|>
name|var3
parameter_list|)
block|{
return|return
name|this
operator|.
name|getEntities
argument_list|(
name|var1
argument_list|,
name|var2
operator|.
name|x0
argument_list|,
name|var2
operator|.
name|y0
argument_list|,
name|var2
operator|.
name|z0
argument_list|,
name|var2
operator|.
name|x1
argument_list|,
name|var2
operator|.
name|y1
argument_list|,
name|var2
operator|.
name|z1
argument_list|,
name|var3
argument_list|)
return|;
block|}
specifier|public
name|List
argument_list|<
name|Entity
argument_list|>
name|getEntities
parameter_list|(
name|Entity
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
name|this
operator|.
name|tmp
operator|.
name|clear
argument_list|()
expr_stmt|;
return|return
name|this
operator|.
name|getEntities
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
argument_list|,
name|this
operator|.
name|tmp
argument_list|)
return|;
block|}
specifier|public
name|List
argument_list|<
name|Entity
argument_list|>
name|getEntities
parameter_list|(
name|Entity
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
parameter_list|,
name|List
argument_list|<
name|Entity
argument_list|>
name|var8
parameter_list|)
block|{
name|BlockMap$Slot
name|var9
init|=
name|this
operator|.
name|slot
operator|.
name|init
argument_list|(
name|var2
argument_list|,
name|var3
argument_list|,
name|var4
argument_list|)
decl_stmt|;
name|BlockMap$Slot
name|var10
init|=
name|this
operator|.
name|slot2
operator|.
name|init
argument_list|(
name|var5
argument_list|,
name|var6
argument_list|,
name|var7
argument_list|)
decl_stmt|;
for|for
control|(
name|int
name|var11
init|=
name|BlockMap$Slot
operator|.
name|getXSlot
argument_list|(
name|var9
argument_list|)
operator|-
literal|1
init|;
name|var11
operator|<=
name|BlockMap$Slot
operator|.
name|getXSlot
argument_list|(
name|var10
argument_list|)
operator|+
literal|1
condition|;
operator|++
name|var11
control|)
block|{
for|for
control|(
name|int
name|var12
init|=
name|BlockMap$Slot
operator|.
name|getYSlot
argument_list|(
name|var9
argument_list|)
operator|-
literal|1
init|;
name|var12
operator|<=
name|BlockMap$Slot
operator|.
name|getYSlot
argument_list|(
name|var10
argument_list|)
operator|+
literal|1
condition|;
operator|++
name|var12
control|)
block|{
for|for
control|(
name|int
name|var13
init|=
name|BlockMap$Slot
operator|.
name|getZSlot
argument_list|(
name|var9
argument_list|)
operator|-
literal|1
init|;
name|var13
operator|<=
name|BlockMap$Slot
operator|.
name|getZSlot
argument_list|(
name|var10
argument_list|)
operator|+
literal|1
condition|;
operator|++
name|var13
control|)
block|{
if|if
condition|(
name|var11
operator|>=
literal|0
operator|&&
name|var12
operator|>=
literal|0
operator|&&
name|var13
operator|>=
literal|0
operator|&&
name|var11
operator|<
name|this
operator|.
name|width
operator|&&
name|var12
operator|<
name|this
operator|.
name|depth
operator|&&
name|var13
operator|<
name|this
operator|.
name|height
condition|)
block|{
name|List
argument_list|<
name|?
argument_list|>
name|var14
init|=
name|this
operator|.
name|entityGrid
index|[
operator|(
name|var13
operator|*
name|this
operator|.
name|depth
operator|+
name|var12
operator|)
operator|*
name|this
operator|.
name|width
operator|+
name|var11
index|]
decl_stmt|;
for|for
control|(
name|int
name|var15
init|=
literal|0
init|;
name|var15
operator|<
name|var14
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var15
control|)
block|{
name|Entity
name|var16
decl_stmt|;
if|if
condition|(
operator|(
name|var16
operator|=
operator|(
name|Entity
operator|)
name|var14
operator|.
name|get
argument_list|(
name|var15
argument_list|)
operator|)
operator|!=
name|var1
operator|&&
name|var16
operator|.
name|intersects
argument_list|(
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
condition|)
block|{
name|var8
operator|.
name|add
argument_list|(
name|var16
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
block|}
return|return
name|var8
return|;
block|}
specifier|public
name|void
name|insert
parameter_list|(
name|Entity
name|var1
parameter_list|)
block|{
name|this
operator|.
name|all
operator|.
name|add
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|this
operator|.
name|slot
operator|.
name|init
argument_list|(
name|var1
operator|.
name|x
argument_list|,
name|var1
operator|.
name|y
argument_list|,
name|var1
operator|.
name|z
argument_list|)
operator|.
name|add
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|var1
operator|.
name|xOld
operator|=
name|var1
operator|.
name|x
expr_stmt|;
name|var1
operator|.
name|yOld
operator|=
name|var1
operator|.
name|y
expr_stmt|;
name|var1
operator|.
name|zOld
operator|=
name|var1
operator|.
name|z
expr_stmt|;
name|var1
operator|.
name|blockMap
operator|=
name|this
expr_stmt|;
block|}
specifier|public
name|void
name|moved
parameter_list|(
name|Entity
name|var1
parameter_list|)
block|{
name|BlockMap$Slot
name|var2
init|=
name|this
operator|.
name|slot
operator|.
name|init
argument_list|(
name|var1
operator|.
name|xOld
argument_list|,
name|var1
operator|.
name|yOld
argument_list|,
name|var1
operator|.
name|zOld
argument_list|)
decl_stmt|;
name|BlockMap$Slot
name|var3
init|=
name|this
operator|.
name|slot2
operator|.
name|init
argument_list|(
name|var1
operator|.
name|x
argument_list|,
name|var1
operator|.
name|y
argument_list|,
name|var1
operator|.
name|z
argument_list|)
decl_stmt|;
if|if
condition|(
operator|!
name|var2
operator|.
name|equals
argument_list|(
name|var3
argument_list|)
condition|)
block|{
name|var2
operator|.
name|remove
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|var3
operator|.
name|add
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|var1
operator|.
name|xOld
operator|=
name|var1
operator|.
name|x
expr_stmt|;
name|var1
operator|.
name|yOld
operator|=
name|var1
operator|.
name|y
expr_stmt|;
name|var1
operator|.
name|zOld
operator|=
name|var1
operator|.
name|z
expr_stmt|;
block|}
block|}
specifier|public
name|void
name|remove
parameter_list|(
name|Entity
name|var1
parameter_list|)
block|{
name|this
operator|.
name|slot
operator|.
name|init
argument_list|(
name|var1
operator|.
name|xOld
argument_list|,
name|var1
operator|.
name|yOld
argument_list|,
name|var1
operator|.
name|zOld
argument_list|)
operator|.
name|remove
argument_list|(
name|var1
argument_list|)
expr_stmt|;
name|this
operator|.
name|all
operator|.
name|remove
argument_list|(
name|var1
argument_list|)
expr_stmt|;
block|}
specifier|public
name|void
name|removeAllNonCreativeModeEntities
parameter_list|()
block|{
for|for
control|(
name|int
name|var1
init|=
literal|0
init|;
name|var1
operator|<
name|this
operator|.
name|width
condition|;
operator|++
name|var1
control|)
block|{
for|for
control|(
name|int
name|var2
init|=
literal|0
init|;
name|var2
operator|<
name|this
operator|.
name|depth
condition|;
operator|++
name|var2
control|)
block|{
for|for
control|(
name|int
name|var3
init|=
literal|0
init|;
name|var3
operator|<
name|this
operator|.
name|height
condition|;
operator|++
name|var3
control|)
block|{
name|List
argument_list|<
name|?
argument_list|>
name|var4
init|=
name|this
operator|.
name|entityGrid
index|[
operator|(
name|var3
operator|*
name|this
operator|.
name|depth
operator|+
name|var2
operator|)
operator|*
name|this
operator|.
name|width
operator|+
name|var1
index|]
decl_stmt|;
for|for
control|(
name|int
name|var5
init|=
literal|0
init|;
name|var5
operator|<
name|var4
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var5
control|)
block|{
if|if
condition|(
operator|!
operator|(
operator|(
name|Entity
operator|)
name|var4
operator|.
name|get
argument_list|(
name|var5
argument_list|)
operator|)
operator|.
name|isCreativeModeAllowed
argument_list|()
condition|)
block|{
name|var4
operator|.
name|remove
argument_list|(
name|var5
operator|--
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
block|}
specifier|public
name|void
name|render
parameter_list|(
name|Vec3D
name|var1
parameter_list|,
name|Frustrum
name|var2
parameter_list|,
name|TextureManager
name|var3
parameter_list|,
name|float
name|var4
parameter_list|)
block|{
for|for
control|(
name|int
name|var5
init|=
literal|0
init|;
name|var5
operator|<
name|this
operator|.
name|width
condition|;
operator|++
name|var5
control|)
block|{
name|float
name|var6
init|=
operator|(
name|float
operator|)
operator|(
operator|(
name|var5
operator|<<
literal|4
operator|)
operator|-
literal|2
operator|)
decl_stmt|;
name|float
name|var7
init|=
operator|(
name|float
operator|)
operator|(
operator|(
name|var5
operator|+
literal|1
operator|<<
literal|4
operator|)
operator|+
literal|2
operator|)
decl_stmt|;
for|for
control|(
name|int
name|var8
init|=
literal|0
init|;
name|var8
operator|<
name|this
operator|.
name|depth
condition|;
operator|++
name|var8
control|)
block|{
name|float
name|var9
init|=
operator|(
name|float
operator|)
operator|(
operator|(
name|var8
operator|<<
literal|4
operator|)
operator|-
literal|2
operator|)
decl_stmt|;
name|float
name|var10
init|=
operator|(
name|float
operator|)
operator|(
operator|(
name|var8
operator|+
literal|1
operator|<<
literal|4
operator|)
operator|+
literal|2
operator|)
decl_stmt|;
for|for
control|(
name|int
name|var11
init|=
literal|0
init|;
name|var11
operator|<
name|this
operator|.
name|height
condition|;
operator|++
name|var11
control|)
block|{
name|List
argument_list|<
name|?
argument_list|>
name|var12
decl_stmt|;
if|if
condition|(
operator|(
name|var12
operator|=
name|this
operator|.
name|entityGrid
index|[
operator|(
name|var11
operator|*
name|this
operator|.
name|depth
operator|+
name|var8
operator|)
operator|*
name|this
operator|.
name|width
operator|+
name|var5
index|]
operator|)
operator|.
name|size
argument_list|()
operator|!=
literal|0
condition|)
block|{
name|float
name|var13
init|=
operator|(
name|float
operator|)
operator|(
operator|(
name|var11
operator|<<
literal|4
operator|)
operator|-
literal|2
operator|)
decl_stmt|;
name|float
name|var14
init|=
operator|(
name|float
operator|)
operator|(
operator|(
name|var11
operator|+
literal|1
operator|<<
literal|4
operator|)
operator|+
literal|2
operator|)
decl_stmt|;
if|if
condition|(
name|var2
operator|.
name|isBoxInFrustum
argument_list|(
name|var6
argument_list|,
name|var9
argument_list|,
name|var13
argument_list|,
name|var7
argument_list|,
name|var10
argument_list|,
name|var14
argument_list|)
condition|)
block|{
name|float
name|var16
init|=
name|var14
decl_stmt|;
name|float
name|var17
init|=
name|var10
decl_stmt|;
name|float
name|var15
init|=
name|var7
decl_stmt|;
name|var14
operator|=
name|var13
expr_stmt|;
name|var13
operator|=
name|var9
expr_stmt|;
name|float
name|var18
init|=
name|var6
decl_stmt|;
name|Frustrum
name|var19
init|=
name|var2
decl_stmt|;
name|int
name|var20
init|=
literal|0
decl_stmt|;
name|boolean
name|var10000
decl_stmt|;
while|while
condition|(
literal|true
condition|)
block|{
if|if
condition|(
name|var20
operator|>=
literal|6
condition|)
block|{
name|var10000
operator|=
literal|true
expr_stmt|;
break|break;
block|}
if|if
condition|(
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|0
index|]
operator|*
name|var18
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|1
index|]
operator|*
name|var13
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|2
index|]
operator|*
name|var14
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|3
index|]
operator|<=
literal|0.0F
condition|)
block|{
name|var10000
operator|=
literal|false
expr_stmt|;
break|break;
block|}
if|if
condition|(
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|0
index|]
operator|*
name|var15
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|1
index|]
operator|*
name|var13
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|2
index|]
operator|*
name|var14
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|3
index|]
operator|<=
literal|0.0F
condition|)
block|{
name|var10000
operator|=
literal|false
expr_stmt|;
break|break;
block|}
if|if
condition|(
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|0
index|]
operator|*
name|var18
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|1
index|]
operator|*
name|var17
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|2
index|]
operator|*
name|var14
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|3
index|]
operator|<=
literal|0.0F
condition|)
block|{
name|var10000
operator|=
literal|false
expr_stmt|;
break|break;
block|}
if|if
condition|(
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|0
index|]
operator|*
name|var15
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|1
index|]
operator|*
name|var17
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|2
index|]
operator|*
name|var14
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|3
index|]
operator|<=
literal|0.0F
condition|)
block|{
name|var10000
operator|=
literal|false
expr_stmt|;
break|break;
block|}
if|if
condition|(
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|0
index|]
operator|*
name|var18
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|1
index|]
operator|*
name|var13
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|2
index|]
operator|*
name|var16
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|3
index|]
operator|<=
literal|0.0F
condition|)
block|{
name|var10000
operator|=
literal|false
expr_stmt|;
break|break;
block|}
if|if
condition|(
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|0
index|]
operator|*
name|var15
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|1
index|]
operator|*
name|var13
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|2
index|]
operator|*
name|var16
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|3
index|]
operator|<=
literal|0.0F
condition|)
block|{
name|var10000
operator|=
literal|false
expr_stmt|;
break|break;
block|}
if|if
condition|(
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|0
index|]
operator|*
name|var18
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|1
index|]
operator|*
name|var17
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|2
index|]
operator|*
name|var16
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|3
index|]
operator|<=
literal|0.0F
condition|)
block|{
name|var10000
operator|=
literal|false
expr_stmt|;
break|break;
block|}
if|if
condition|(
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|0
index|]
operator|*
name|var15
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|1
index|]
operator|*
name|var17
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|2
index|]
operator|*
name|var16
operator|+
name|var19
operator|.
name|frustum
index|[
name|var20
index|]
index|[
literal|3
index|]
operator|<=
literal|0.0F
condition|)
block|{
name|var10000
operator|=
literal|false
expr_stmt|;
break|break;
block|}
operator|++
name|var20
expr_stmt|;
block|}
name|boolean
name|var21
init|=
name|var10000
decl_stmt|;
for|for
control|(
name|int
name|var23
init|=
literal|0
init|;
name|var23
operator|<
name|var12
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var23
control|)
block|{
name|Entity
name|var22
decl_stmt|;
if|if
condition|(
operator|(
name|var22
operator|=
operator|(
name|Entity
operator|)
name|var12
operator|.
name|get
argument_list|(
name|var23
argument_list|)
operator|)
operator|.
name|shouldRender
argument_list|(
name|var1
argument_list|)
condition|)
block|{
if|if
condition|(
operator|!
name|var21
condition|)
block|{
name|AABB
name|var24
init|=
name|var22
operator|.
name|bb
decl_stmt|;
if|if
condition|(
operator|!
name|var2
operator|.
name|isBoxInFrustum
argument_list|(
name|var24
operator|.
name|x0
argument_list|,
name|var24
operator|.
name|y0
argument_list|,
name|var24
operator|.
name|z0
argument_list|,
name|var24
operator|.
name|x1
argument_list|,
name|var24
operator|.
name|y1
argument_list|,
name|var24
operator|.
name|z1
argument_list|)
condition|)
block|{
continue|continue;
block|}
block|}
name|var22
operator|.
name|render
argument_list|(
name|var3
argument_list|,
name|var4
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
block|}
block|}
block|}
specifier|public
name|void
name|tickAll
parameter_list|()
block|{
for|for
control|(
name|int
name|var1
init|=
literal|0
init|;
name|var1
operator|<
name|this
operator|.
name|all
operator|.
name|size
argument_list|()
condition|;
operator|++
name|var1
control|)
block|{
name|Entity
name|var2
decl_stmt|;
operator|(
name|var2
operator|=
name|this
operator|.
name|all
operator|.
name|get
argument_list|(
name|var1
argument_list|)
operator|)
operator|.
name|tick
argument_list|()
expr_stmt|;
if|if
condition|(
name|var2
operator|.
name|removed
condition|)
block|{
name|this
operator|.
name|all
operator|.
name|remove
argument_list|(
name|var1
operator|--
argument_list|)
expr_stmt|;
name|this
operator|.
name|slot
operator|.
name|init
argument_list|(
name|var2
operator|.
name|xOld
argument_list|,
name|var2
operator|.
name|yOld
argument_list|,
name|var2
operator|.
name|zOld
argument_list|)
operator|.
name|remove
argument_list|(
name|var2
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|int
name|var3
init|=
operator|(
name|int
operator|)
operator|(
name|var2
operator|.
name|xOld
operator|/
literal|16.0F
operator|)
decl_stmt|;
name|int
name|var4
init|=
operator|(
name|int
operator|)
operator|(
name|var2
operator|.
name|yOld
operator|/
literal|16.0F
operator|)
decl_stmt|;
name|int
name|var5
init|=
operator|(
name|int
operator|)
operator|(
name|var2
operator|.
name|zOld
operator|/
literal|16.0F
operator|)
decl_stmt|;
name|int
name|var6
init|=
operator|(
name|int
operator|)
operator|(
name|var2
operator|.
name|x
operator|/
literal|16.0F
operator|)
decl_stmt|;
name|int
name|var7
init|=
operator|(
name|int
operator|)
operator|(
name|var2
operator|.
name|y
operator|/
literal|16.0F
operator|)
decl_stmt|;
name|int
name|var8
init|=
operator|(
name|int
operator|)
operator|(
name|var2
operator|.
name|z
operator|/
literal|16.0F
operator|)
decl_stmt|;
if|if
condition|(
name|var3
operator|!=
name|var6
operator|||
name|var4
operator|!=
name|var7
operator|||
name|var5
operator|!=
name|var8
condition|)
block|{
name|this
operator|.
name|moved
argument_list|(
name|var2
argument_list|)
expr_stmt|;
block|}
block|}
block|}
block|}
block|}
end_class

end_unit

