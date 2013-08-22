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
name|tile
package|;
end_package

begin_enum
specifier|public
enum|enum
name|BlockID
block|{
name|Undefined
argument_list|(
literal|255
argument_list|)
block|,
comment|// for error checking
name|Air
argument_list|(
literal|0
argument_list|)
block|,
name|Stone
argument_list|(
literal|1
argument_list|)
block|,
name|Grass
argument_list|(
literal|2
argument_list|)
block|,
name|Dirt
argument_list|(
literal|3
argument_list|)
block|,
name|Cobblestone
argument_list|(
literal|4
argument_list|)
block|,
name|Wood
argument_list|(
literal|5
argument_list|)
block|,
name|Plant
argument_list|(
literal|6
argument_list|)
block|,
name|Admincrete
argument_list|(
literal|7
argument_list|)
block|,
name|Water
argument_list|(
literal|8
argument_list|)
block|,
name|StillWater
argument_list|(
literal|9
argument_list|)
block|,
name|Lava
argument_list|(
literal|10
argument_list|)
block|,
name|StillLava
argument_list|(
literal|11
argument_list|)
block|,
name|Sand
argument_list|(
literal|12
argument_list|)
block|,
name|Gravel
argument_list|(
literal|13
argument_list|)
block|,
name|GoldOre
argument_list|(
literal|14
argument_list|)
block|,
name|IronOre
argument_list|(
literal|15
argument_list|)
block|,
name|Coal
argument_list|(
literal|16
argument_list|)
block|,
name|Log
argument_list|(
literal|17
argument_list|)
block|,
name|Leaves
argument_list|(
literal|18
argument_list|)
block|,
name|Sponge
argument_list|(
literal|19
argument_list|)
block|,
name|Glass
argument_list|(
literal|20
argument_list|)
block|,
name|Red
argument_list|(
literal|21
argument_list|)
block|,
name|Orange
argument_list|(
literal|22
argument_list|)
block|,
name|Yellow
argument_list|(
literal|23
argument_list|)
block|,
name|Lime
argument_list|(
literal|24
argument_list|)
block|,
name|Green
argument_list|(
literal|25
argument_list|)
block|,
name|Teal
argument_list|(
literal|26
argument_list|)
block|,
name|Aqua
argument_list|(
literal|27
argument_list|)
block|,
name|Cyan
argument_list|(
literal|28
argument_list|)
block|,
name|Blue
argument_list|(
literal|29
argument_list|)
block|,
name|Indigo
argument_list|(
literal|30
argument_list|)
block|,
name|Violet
argument_list|(
literal|31
argument_list|)
block|,
name|Magenta
argument_list|(
literal|32
argument_list|)
block|,
name|Pink
argument_list|(
literal|33
argument_list|)
block|,
name|Black
argument_list|(
literal|34
argument_list|)
block|,
name|Gray
argument_list|(
literal|35
argument_list|)
block|,
name|White
argument_list|(
literal|36
argument_list|)
block|,
name|YellowFlower
argument_list|(
literal|37
argument_list|)
block|,
name|RedFlower
argument_list|(
literal|38
argument_list|)
block|,
name|BrownMushroom
argument_list|(
literal|39
argument_list|)
block|,
name|RedMushroom
argument_list|(
literal|40
argument_list|)
block|,
name|Gold
argument_list|(
literal|41
argument_list|)
block|,
name|Iron
argument_list|(
literal|42
argument_list|)
block|,
name|DoubleStair
argument_list|(
literal|4
argument_list|)
block|,
name|Stair
argument_list|(
literal|44
argument_list|)
block|,
name|Brick
argument_list|(
literal|45
argument_list|)
block|,
name|TNT
argument_list|(
literal|46
argument_list|)
block|,
name|Books
argument_list|(
literal|47
argument_list|)
block|,
name|MossyRocks
argument_list|(
literal|48
argument_list|)
block|,
name|Obsidian
argument_list|(
literal|49
argument_list|)
block|,
name|CobblestoneSlab
argument_list|(
literal|50
argument_list|)
block|,
name|Butter
argument_list|(
literal|51
argument_list|)
block|,
name|DarkGrass
argument_list|(
literal|52
argument_list|)
block|,
name|SpiderWeb
argument_list|(
literal|53
argument_list|)
block|;
specifier|private
name|int
name|number
decl_stmt|;
specifier|private
name|BlockID
parameter_list|(
name|int
name|number
parameter_list|)
block|{
name|this
operator|.
name|number
operator|=
name|number
expr_stmt|;
block|}
specifier|public
name|int
name|getNumber
parameter_list|()
block|{
return|return
name|number
return|;
block|}
block|}
end_enum

end_unit

