begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_comment
comment|/*  * $ProjectName$  * $ProjectRevision$  * -----------------------------------------------------------  * $Id: Util.java,v 1.3 2003/04/10 19:49:04 jarnbjo Exp $  * -----------------------------------------------------------  *  * $Author: jarnbjo $  *  * Description:  *  * Copyright 2002-2003 Tor-Einar Jarnbjo  * -----------------------------------------------------------  *  * Change History  * -----------------------------------------------------------  * $Log: Util.java,v $  * Revision 1.3  2003/04/10 19:49:04  jarnbjo  * no message  *  * Revision 1.2  2003/03/16 01:11:12  jarnbjo  * no message  *  *  */
end_comment

begin_package
package|package
name|de
operator|.
name|jarnbjo
operator|.
name|vorbis
package|;
end_package

begin_class
specifier|final
specifier|public
class|class
name|Util
block|{
specifier|public
specifier|static
specifier|final
name|float
name|float32unpack
parameter_list|(
name|int
name|x
parameter_list|)
block|{
name|float
name|mantissa
init|=
name|x
operator|&
literal|0x1fffff
decl_stmt|;
name|float
name|e
init|=
operator|(
name|x
operator|&
literal|0x7fe00000
operator|)
operator|>>
literal|21
decl_stmt|;
if|if
condition|(
operator|(
name|x
operator|&
literal|0x80000000
operator|)
operator|!=
literal|0
condition|)
block|{
name|mantissa
operator|=
operator|-
name|mantissa
expr_stmt|;
block|}
return|return
name|mantissa
operator|*
operator|(
name|float
operator|)
name|Math
operator|.
name|pow
argument_list|(
literal|2.0
argument_list|,
name|e
operator|-
literal|788.0
argument_list|)
return|;
block|}
specifier|public
specifier|static
specifier|final
name|int
name|highNeighbour
parameter_list|(
name|int
index|[]
name|v
parameter_list|,
name|int
name|x
parameter_list|)
block|{
name|int
name|min
init|=
name|Integer
operator|.
name|MAX_VALUE
decl_stmt|,
name|n
init|=
literal|0
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|v
operator|.
name|length
operator|&&
name|i
operator|<
name|x
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|v
index|[
name|i
index|]
operator|<
name|min
operator|&&
name|v
index|[
name|i
index|]
operator|>
name|v
index|[
name|x
index|]
condition|)
block|{
name|min
operator|=
name|v
index|[
name|i
index|]
expr_stmt|;
name|n
operator|=
name|i
expr_stmt|;
block|}
block|}
return|return
name|n
return|;
block|}
specifier|public
specifier|static
specifier|final
name|int
name|icount
parameter_list|(
name|int
name|value
parameter_list|)
block|{
name|int
name|res
init|=
literal|0
decl_stmt|;
while|while
condition|(
name|value
operator|>
literal|0
condition|)
block|{
name|res
operator|+=
name|value
operator|&
literal|1
expr_stmt|;
name|value
operator|>>=
literal|1
expr_stmt|;
block|}
return|return
name|res
return|;
block|}
specifier|public
specifier|static
specifier|final
name|int
name|ilog
parameter_list|(
name|int
name|x
parameter_list|)
block|{
name|int
name|res
init|=
literal|0
decl_stmt|;
while|while
condition|(
name|x
operator|>
literal|0
condition|)
block|{
name|x
operator|>>=
literal|1
expr_stmt|;
name|res
operator|++
expr_stmt|;
block|}
return|return
name|res
return|;
block|}
specifier|public
specifier|static
specifier|final
name|int
name|intPow
parameter_list|(
name|int
name|base
parameter_list|,
name|int
name|e
parameter_list|)
block|{
name|int
name|res
init|=
literal|1
decl_stmt|;
while|while
condition|(
name|e
operator|>
literal|0
condition|)
block|{
name|e
operator|--
expr_stmt|;
name|res
operator|*=
name|base
expr_stmt|;
block|}
return|return
name|res
return|;
block|}
specifier|public
specifier|static
specifier|final
name|boolean
name|isBitSet
parameter_list|(
name|int
name|value
parameter_list|,
name|int
name|bit
parameter_list|)
block|{
return|return
operator|(
name|value
operator|&
operator|(
literal|1
operator|<<
name|bit
operator|)
operator|)
operator|!=
literal|0
return|;
block|}
specifier|public
specifier|static
specifier|final
name|int
name|lookup1Values
parameter_list|(
name|int
name|a
parameter_list|,
name|int
name|b
parameter_list|)
block|{
name|int
name|res
init|=
operator|(
name|int
operator|)
name|Math
operator|.
name|pow
argument_list|(
name|Math
operator|.
name|E
argument_list|,
name|Math
operator|.
name|log
argument_list|(
name|a
argument_list|)
operator|/
name|b
argument_list|)
decl_stmt|;
return|return
name|intPow
argument_list|(
name|res
operator|+
literal|1
argument_list|,
name|b
argument_list|)
operator|<=
name|a
condition|?
name|res
operator|+
literal|1
else|:
name|res
return|;
block|}
specifier|public
specifier|static
specifier|final
name|int
name|lowNeighbour
parameter_list|(
name|int
index|[]
name|v
parameter_list|,
name|int
name|x
parameter_list|)
block|{
name|int
name|max
init|=
operator|-
literal|1
decl_stmt|,
name|n
init|=
literal|0
decl_stmt|;
for|for
control|(
name|int
name|i
init|=
literal|0
init|;
name|i
operator|<
name|v
operator|.
name|length
operator|&&
name|i
operator|<
name|x
condition|;
name|i
operator|++
control|)
block|{
if|if
condition|(
name|v
index|[
name|i
index|]
operator|>
name|max
operator|&&
name|v
index|[
name|i
index|]
operator|<
name|v
index|[
name|x
index|]
condition|)
block|{
name|max
operator|=
name|v
index|[
name|i
index|]
expr_stmt|;
name|n
operator|=
name|i
expr_stmt|;
block|}
block|}
return|return
name|n
return|;
block|}
specifier|public
specifier|static
specifier|final
name|void
name|renderLine
parameter_list|(
specifier|final
name|int
name|x0
parameter_list|,
specifier|final
name|int
name|y0
parameter_list|,
specifier|final
name|int
name|x1
parameter_list|,
specifier|final
name|int
name|y1
parameter_list|,
specifier|final
name|float
index|[]
name|v
parameter_list|)
block|{
specifier|final
name|int
name|dy
init|=
name|y1
operator|-
name|y0
decl_stmt|;
specifier|final
name|int
name|adx
init|=
name|x1
operator|-
name|x0
decl_stmt|;
specifier|final
name|int
name|base
init|=
name|dy
operator|/
name|adx
decl_stmt|;
specifier|final
name|int
name|sy
init|=
name|dy
operator|<
literal|0
condition|?
name|base
operator|-
literal|1
else|:
name|base
operator|+
literal|1
decl_stmt|;
name|int
name|x
init|=
name|x0
decl_stmt|;
name|int
name|y
init|=
name|y0
decl_stmt|;
name|int
name|err
init|=
literal|0
decl_stmt|;
specifier|final
name|int
name|ady
init|=
operator|(
name|dy
operator|<
literal|0
condition|?
operator|-
name|dy
else|:
name|dy
operator|)
operator|-
operator|(
name|base
operator|>
literal|0
condition|?
name|base
operator|*
name|adx
else|:
operator|-
name|base
operator|*
name|adx
operator|)
decl_stmt|;
name|v
index|[
name|x
index|]
operator|*=
name|Floor
operator|.
name|DB_STATIC_TABLE
index|[
name|y
index|]
expr_stmt|;
for|for
control|(
name|x
operator|=
name|x0
operator|+
literal|1
init|;
name|x
operator|<
name|x1
condition|;
name|x
operator|++
control|)
block|{
name|err
operator|+=
name|ady
expr_stmt|;
if|if
condition|(
name|err
operator|>=
name|adx
condition|)
block|{
name|err
operator|-=
name|adx
expr_stmt|;
name|v
index|[
name|x
index|]
operator|*=
name|Floor
operator|.
name|DB_STATIC_TABLE
index|[
name|y
operator|+=
name|sy
index|]
expr_stmt|;
block|}
else|else
block|{
name|v
index|[
name|x
index|]
operator|*=
name|Floor
operator|.
name|DB_STATIC_TABLE
index|[
name|y
operator|+=
name|base
index|]
expr_stmt|;
block|}
block|}
block|}
specifier|public
specifier|static
specifier|final
name|int
name|renderPoint
parameter_list|(
name|int
name|x0
parameter_list|,
name|int
name|x1
parameter_list|,
name|int
name|y0
parameter_list|,
name|int
name|y1
parameter_list|,
name|int
name|x
parameter_list|)
block|{
name|int
name|dy
init|=
name|y1
operator|-
name|y0
decl_stmt|;
name|int
name|ady
init|=
name|dy
operator|<
literal|0
condition|?
operator|-
name|dy
else|:
name|dy
decl_stmt|;
name|int
name|off
init|=
operator|(
name|ady
operator|*
operator|(
name|x
operator|-
name|x0
operator|)
operator|)
operator|/
operator|(
name|x1
operator|-
name|x0
operator|)
decl_stmt|;
return|return
name|dy
operator|<
literal|0
condition|?
name|y0
operator|-
name|off
else|:
name|y0
operator|+
name|off
return|;
block|}
block|}
end_class

end_unit

