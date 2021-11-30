package dev.suxior.crates.utils.item;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.controller.Controller;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemUtils implements Controller<SuxiorCrates> {

    private static Map<String, String> map;

    @Override public void onEnable(SuxiorCrates crates) {
        map = new HashMap<>();

        map.put("1", "Stone");
        map.put("1:1", "Granite");
        map.put("1:2", "Polished Granite");
        map.put("1:3", "Diorite");
        map.put("1:4", "Polished Diorite");
        map.put("1:5", "Andesite");
        map.put("1:6", "Polished Andesite");
        map.put("2", "Grass Block");
        map.put("3", "Dirt");
        map.put("3:1", "Coarse Dirt");
        map.put("3:2", "Podzol");
        map.put("4", "Cobblestone");
        map.put("5", "Oak Wood Planks");
        map.put("5:1", "Spruce Wood Planks");
        map.put("5:2", "Birch Wood Planks");
        map.put("5:3", "Jungle Wood Planks");
        map.put("5:4", "Acacia Wood Planks");
        map.put("5:5", "Dark Oak Wood Planks");
        map.put("6", "Oak Sapling");
        map.put("6:1", "Spruce Sapling");
        map.put("6:2", "Birch Sapling");
        map.put("6:3", "Jungle Sapling");
        map.put("6:4", "Acacia Sapling");
        map.put("6:5", "Dark Oak Sapling");
        map.put("7", "Bedrock");
        map.put("8", "Water (No Spread)");
        map.put("9", "Water");
        map.put("10", "Lava (No Spread)");
        map.put("11", "Lava");
        map.put("12", "Sand");
        map.put("12:1", "Red Sand");
        map.put("13", "Gravel");
        map.put("14", "Gold Ore");
        map.put("15", "Iron Ore");
        map.put("16", "Coal Ore");
        map.put("17", "Oak Wood");
        map.put("17:1", "Spruce Wood");
        map.put("17:2", "Birch Wood");
        map.put("17:3", "Jungle Wood");
        map.put("18", "Oak Leaves");
        map.put("18:1", "Spruce Leaves");
        map.put("18:2", "Birch Leaves");
        map.put("18:3", "Jungle Leaves");
        map.put("19", "Sponge");
        map.put("19:1", "Wet Sponge");
        map.put("20", "Glass");
        map.put("21", "Lapis Lazuli Ore");
        map.put("22", "Lapis Block");
        map.put("23", "Dispenser");
        map.put("24", "Sandstone");
        map.put("24:1", "Chiseled Sandstone");
        map.put("24:2", "Smooth Sandstone");
        map.put("25", "Note Block");
        map.put("26", "Bed");
        map.put("27", "Powered Rail");
        map.put("28", "Detector Rail");
        map.put("29", "Sticky Piston");
        map.put("30", "Web");
        map.put("31", "Shrub");
        map.put("31:1", "Grass");
        map.put("31:2", "Fern");
        map.put("32", "Dead Bush");
        map.put("33", "Piston");
        map.put("34", "Piston (Head)");
        map.put("35", "Wool");
        map.put("35:1", "Orange Wool");
        map.put("35:2", "Magenta Wool");
        map.put("35:3", "Light Blue Wool");
        map.put("35:4", "Yellow Wool");
        map.put("35:5", "Lime Wool");
        map.put("35:6", "Pink Wool");
        map.put("35:7", "Gray Wool");
        map.put("35:8", "Light Gray Wool");
        map.put("35:9", "Cyan Wool");
        map.put("35:10", "Purple Wool");
        map.put("35:11", "Blue Wool");
        map.put("35:12", "Brown Wool");
        map.put("35:13", "Green Wool");
        map.put("35:14", "Red Wool");
        map.put("35:15", "Black Wool");
        map.put("37", "Dandelion");
        map.put("38", "Rose");
        map.put("38:1", "Blue Orchid");
        map.put("38:2", "Allium");
        map.put("38:3", "Azure Bluet");
        map.put("38:4", "Red Tulip");
        map.put("38:5", "Orange Tulip");
        map.put("38:6", "White Tulip");
        map.put("38:7", "Pink Tulip");
        map.put("38:8", "Oxeye Daisy");
        map.put("39", "Brown Mushroom");
        map.put("40", "Red Mushroom");
        map.put("41", "Gold Block");
        map.put("42", "Iron Block");
        map.put("43", "Stone Slab (Double)");
        map.put("43:1", "Sandstone Slab (Double)");
        map.put("43:2", "Wooden Slab (Double)");
        map.put("43:3", "Cobblestone Slab (Double)");
        map.put("43:4", "Brick Slab (Double)");
        map.put("43:5", "Stone Brick Slab (Double)");
        map.put("43:6", "Nether Brick Slab (Double)");
        map.put("43:7", "Quartz Slab (Double)");
        map.put("43:8", "Smooth Stone Slab (Double)");
        map.put("43:9", "Smooth Sandstone Slab (Double)");
        map.put("44", "Stone Slab");
        map.put("44:1", "Sandstone Slab");
        map.put("44:2", "Wooden Slab");
        map.put("44:3", "Cobblestone Slab");
        map.put("44:4", "Brick Slab");
        map.put("44:5", "Stone Brick Slab");
        map.put("44:6", "Nether Brick Slab");
        map.put("44:7", "Quartz Slab");
        map.put("45", "Brick");
        map.put("46", "TNT");
        map.put("47", "Bookcase");
        map.put("48", "Moss Stone");
        map.put("49", "Obsidian");
        map.put("50", "Torch");
        map.put("51", "Fire");
        map.put("52", "Mob Spawner");
        map.put("53", "Oak Wood Stairs");
        map.put("54", "Chest");
        map.put("55", "Redstone Wire");
        map.put("56", "Diamond Ore");
        map.put("57", "Diamond Block");
        map.put("58", "Crafting Table");
        map.put("59", "Wheat (Crop)");
        map.put("60", "Farmland");
        map.put("61", "Furnace");
        map.put("62", "Furnace (Smelting)");
        map.put("63", "Sign (Block)");
        map.put("64", "Wood Door (Block)");
        map.put("65", "Ladder");
        map.put("66", "Rails");
        map.put("67", "Stone Stairs");
        map.put("68", "Sign (Wall Block)");
        map.put("69", "Lever");
        map.put("70", "Pressure Plate");
        map.put("71", "Iron Door (Block)");
        map.put("72", "Pressure Plate");
        map.put("73", "Redstone Ore");
        map.put("74", "Redstone Ore (Glowing)");
        map.put("75", "Redstone Torch (Off)");
        map.put("76", "Redstone Torch");
        map.put("77", "Button");
        map.put("78", "Snow");
        map.put("79", "Ice");
        map.put("80", "Snow Block");
        map.put("81", "Cactus");
        map.put("82", "Clay Block");
        map.put("83", "Sugar Cane (Block)");
        map.put("84", "Jukebox");
        map.put("85", "Fence");
        map.put("86", "Pumpkin");
        map.put("87", "Netherrack");
        map.put("88", "Soul Sand");
        map.put("89", "Glowstone");
        map.put("90", "Portal");
        map.put("91", "Jack-O-Lantern");
        map.put("92", "Cake (Block)");
        map.put("93", "Redstone Repeater (Block Off)");
        map.put("94", "Redstone Repeater (Block On)");
        map.put("95", "Stained Glass");
        map.put("96", "Wooden Trapdoor");
        map.put("97", "Stone Monster Egg");
        map.put("97:1", "Cobblestone Monster Egg");
        map.put("97:2", "Stone Brick Monster Egg");
        map.put("97:3", "Mossy Stone Brick Monster Egg");
        map.put("97:4", "Cracked Stone Brick Monster Egg");
        map.put("97:5", "Chiseled Stone Brick Monster Egg");
        map.put("98", "Stone Bricks");
        map.put("98:1", "Mossy Stone Bricks");
        map.put("98:2", "Cracked Stone Bricks");
        map.put("98:3", "Chiseled Stone Bricks");
        map.put("99", "Brown Mushroom (Block)");
        map.put("100", "Red Mushroom (Block)");
        map.put("101", "Iron Bars");
        map.put("102", "Glass Pane");
        map.put("103", "Melon (Block)");
        map.put("104", "Pumpkin Vine");
        map.put("105", "Melon Vine");
        map.put("106", "Vines");
        map.put("107", "Fence Gate");
        map.put("108", "Brick Stairs");
        map.put("109", "Stone Brick Stairs");
        map.put("110", "Mycelium");
        map.put("111", "Lily Pad");
        map.put("112", "Nether Brick");
        map.put("113", "Nether Brick Fence");
        map.put("114", "Nether Brick Stairs");
        map.put("115", "Nether Wart");
        map.put("116", "Enchantment Table");
        map.put("117", "Brewing Stand (Block)");
        map.put("118", "Cauldron (Block)");
        map.put("119", "End Portal");
        map.put("120", "End Portal Frame");
        map.put("121", "End Stone");
        map.put("122", "Dragon Egg");
        map.put("123", "Redstone Lamp (Inactive)");
        map.put("124", "Redstone Lamp (Active)");
        map.put("125", "Double Wood Slab");
        map.put("126", "Oak Wood Slab");
        map.put("126:1", "Spruce Wood Slab");
        map.put("126:2", "Birch Slab");
        map.put("126:3", "Jungle Slab");
        map.put("126:4", "Acacia Wood Slab");
        map.put("126:5", "Dark Oak Wood Slab");
        map.put("127", "Cocoa Plant");
        map.put("128", "Sandstone Stairs");
        map.put("129", "Emerald Ore");
        map.put("130", "Ender Chest");
        map.put("131", "Tripwire Hook");
        map.put("132", "Tripwire");
        map.put("133", "Emerald Block");
        map.put("134", "Spruce Wood Stairs");
        map.put("135", "Birch Wood Stairs");
        map.put("136", "Jungle Wood Stairs");
        map.put("137", "Command Block");
        map.put("138", "Beacon Block");
        map.put("139", "Cobblestone Wall");
        map.put("139:1", "Mossy Cobblestone Wall");
        map.put("140", "Flower Pot");
        map.put("141", "Carrots");
        map.put("142", "Potatoes");
        map.put("143", "Button");
        map.put("144", "Head");
        map.put("145", "Anvil");
        map.put("146", "Trapped Chest");
        map.put("147", "Weighted Pressure Plate (Light)");
        map.put("148", "Weighted Pressure Plate (Heavy)");
        map.put("149", "Redstone Comparator (inactive)");
        map.put("150", "Redstone Comparator (active)");
        map.put("151", "Daylight Sensor");
        map.put("152", "Redstone Block");
        map.put("153", "Nether Quartz Ore");
        map.put("154", "Hopper");
        map.put("155", "Quartz Block");
        map.put("155:1", "Chiseled Quartz Block");
        map.put("155:2", "Pillar Quartz Block");
        map.put("156", "Quartz Stairs");
        map.put("157", "Activator Rail");
        map.put("158", "Dropper");
        map.put("159", "Stained Clay");
        map.put("160", "Stained Glass Pane");
        map.put("160:1", "Orange Stained Glass Pane");
        map.put("160:2", "Magenta Stained Glass Pane");
        map.put("160:3", "Light Blue Stained Glass Pane");
        map.put("160:4", "Yellow Stained Glass Pane");
        map.put("160:5", "Lime Stained Glass Pane");
        map.put("160:6", "Pink Stained Glass Pane");
        map.put("160:7", "Gray Stained Glass Pane");
        map.put("160:8", "Light Gray Stained Glass Pane");
        map.put("160:9", "Cyan Stained Glass Pane");
        map.put("160:10", "Purple Stained Glass Pane");
        map.put("160:11", "Blue Stained Glass Pane");
        map.put("160:12", "Brown Stained Glass Pane");
        map.put("160:13", "Green Stained Glass Pane");
        map.put("160:14", "Red Stained Glass Pane");
        map.put("160:15", "Black Stained Glass Pane");
        map.put("161", "Acacia Leaves");
        map.put("161:1", "Dark Oak Leaves");
        map.put("162", "Acacia Wood");
        map.put("162:1", "Dark Oak Wood");
        map.put("163", "Acacia Wood Stairs");
        map.put("164", "Dark Oak Wood Stairs");
        map.put("165", "Slime Block");
        map.put("166", "Barrier");
        map.put("167", "Iron Trapdoor");
        map.put("168", "Prismarine");
        map.put("168:1", "Prismarine Bricks");
        map.put("168:2", "Dark Prismarine");
        map.put("169", "Sea Lantern");
        map.put("170", "Hay Block");
        map.put("171", "Carpet");
        map.put("172", "Hardened Clay");
        map.put("173", "Coal Block");
        map.put("174", "Packed Ice");
        map.put("175", "Sunflower");
        map.put("175:1", "Lilac");
        map.put("175:2", "Double Tallgrass");
        map.put("175:3", "Large Fern");
        map.put("175:4", "Rose Bush");
        map.put("175:5", "Peony");
        map.put("256", "Iron Shovel");
        map.put("257", "Iron Pickaxe");
        map.put("258", "Iron Axe");
        map.put("259", "Flint and Steel");
        map.put("260", "Apple");
        map.put("261", "Bow");
        map.put("262", "Arrow");
        map.put("263", "Coal");
        map.put("263:1", "Charcoal");
        map.put("264", "Diamond");
        map.put("265", "Iron Ingot");
        map.put("266", "Gold Ingot");
        map.put("267", "Iron Sword");
        map.put("268", "Wooden Sword");
        map.put("269", "Wooden Shovel");
        map.put("270", "Wooden Pickaxe");
        map.put("271", "Wooden Axe");
        map.put("272", "Stone Sword");
        map.put("273", "Stone Shovel");
        map.put("274", "Stone Pickaxe");
        map.put("275", "Stone Axe");
        map.put("276", "Diamond Sword");
        map.put("277", "Diamond Shovel");
        map.put("278", "Diamond Pickaxe");
        map.put("279", "Diamond Axe");
        map.put("280", "Stick");
        map.put("281", "Bowl");
        map.put("282", "Mushroom Stew");
        map.put("283", "Gold Sword");
        map.put("284", "Gold Shovel");
        map.put("285", "Gold Pickaxe");
        map.put("286", "Gold Axe");
        map.put("287", "String");
        map.put("288", "Feather");
        map.put("289", "Gunpowder");
        map.put("290", "Wooden Hoe");
        map.put("291", "Stone Hoe");
        map.put("292", "Iron Hoe");
        map.put("293", "Diamond Hoe");
        map.put("294", "Gold Hoe");
        map.put("295", "Seeds");
        map.put("296", "Wheat");
        map.put("297", "Bread");
        map.put("298", "Leather Helmet");
        map.put("299", "Leather Chestplate");
        map.put("300", "Leather Leggings");
        map.put("301", "Leather Boots");
        map.put("302", "Chainmail Helmet");
        map.put("303", "Chainmail Chestplate");
        map.put("304", "Chainmail Leggings");
        map.put("305", "Chainmail Boots");
        map.put("306", "Iron Helmet");
        map.put("307", "Iron Chestplate");
        map.put("308", "Iron Leggings");
        map.put("309", "Iron Boots");
        map.put("310", "Diamond Helmet");
        map.put("311", "Diamond Chestplate");
        map.put("312", "Diamond Leggings");
        map.put("313", "Diamond Boots");
        map.put("314", "Gold Helmet");
        map.put("315", "Gold Chestplate");
        map.put("316", "Gold Leggings");
        map.put("317", "Gold Boots");
        map.put("318", "Flint");
        map.put("319", "Raw Porkchop");
        map.put("320", "Cooked Porkchop");
        map.put("321", "Painting");
        map.put("322", "Gold Apple");
        map.put("322:1", "Gold Apple (Enchanted)");
        map.put("323", "Sign");
        map.put("324", "Wooden Door");
        map.put("325", "Bucket");
        map.put("326", "Water Bucket");
        map.put("327", "Lava Bucket");
        map.put("328", "Minecart");
        map.put("329", "Saddle");
        map.put("330", "Iron Door");
        map.put("331", "Redstone");
        map.put("332", "Snowball");
        map.put("333", "Boat");
        map.put("334", "Leather");
        map.put("335", "Milk Bucket");
        map.put("336", "Brick");
        map.put("337", "Clay");
        map.put("338", "Sugar Cane");
        map.put("339", "Paper");
        map.put("340", "Book");
        map.put("341", "Slime Ball");
        map.put("342", "Storage Minecart");
        map.put("343", "Powered Minecart");
        map.put("344", "Egg");
        map.put("345", "Compass");
        map.put("346", "Fishing Rod");
        map.put("347", "Watch");
        map.put("348", "Glowstone Dust");
        map.put("349", "Raw Fish");
        map.put("349:1", "Raw Salmon");
        map.put("349:2", "Clownfish");
        map.put("349:3", "Pufferfish");
        map.put("350", "Cooked Fish");
        map.put("350:1", "Cooked Salmon");
        map.put("351", "Ink Sack [Black Dye]");
        map.put("351:1", "Rose Red [Red Dye]");
        map.put("351:2", "Cactus Green [Green Dye]");
        map.put("351:3", "Cocoa Bean [Brown Dye]");
        map.put("351:4", "Lapis Lazuli [Blue Dye]");
        map.put("351:5", "Purple Dye");
        map.put("351:6", "Cyan Dye");
        map.put("351:7", "Light Gray Dye");
        map.put("351:8", "Gray Dye");
        map.put("351:9", "Pink Dye");
        map.put("351:10", "Lime Dye");
        map.put("351:11", "Dandelion Yellow [Yellow Dye]");
        map.put("351:12", "Light Blue Dye");
        map.put("351:13", "Magenta Dye");
        map.put("351:14", "Orange Dye");
        map.put("351:15", "Bone Meal [White Dye]");
        map.put("352", "Bone");
        map.put("353", "Sugar");
        map.put("354", "Cake");
        map.put("355", "Bed");
        map.put("356", "Redstone Repeater");
        map.put("357", "Cookie");
        map.put("358", "Map");
        map.put("359", "Shears");
        map.put("360", "Melon");
        map.put("361", "Pumpkin Seeds");
        map.put("362", "Melon Seeds");
        map.put("363", "Raw Beef");
        map.put("364", "Steak");
        map.put("365", "Raw Chicken");
        map.put("366", "Roast Chicken");
        map.put("367", "Rotten Flesh");
        map.put("368", "Ender Pearl");
        map.put("369", "Blaze Rod");
        map.put("370", "Ghast Tear");
        map.put("371", "Gold Nugget");
        map.put("372", "Nether Wart");
        map.put("373", "Water Bottle");
        map.put("373:16", "Awkward Potion");
        map.put("373:32", "Thick Potion");
        map.put("373:64", "Mundane Potion");
        map.put("373:8193", "Regeneration Potion (0:45)");
        map.put("373:8194", "Swiftness Potion (3:00)");
        map.put("373:8195", "Fire Resistance Potion (3:00)");
        map.put("373:8196", "Poison Potion (0:45)");
        map.put("373:8197", "Healing Potion");
        map.put("373:8200", "Weakness Potion (1:30)");
        map.put("373:8201", "Strength Potion (3:00)");
        map.put("373:8202", "Slowness Potion (1:30)");
        map.put("373:8203", "Potion of Leaping (3:00)");
        map.put("373:8204", "Harming Potion");
        map.put("373:8225", "Regeneration Potion II (0:22)");
        map.put("373:8226", "Swiftness Potion II (1:30)");
        map.put("373:8228", "Poison Potion II (0:22)");
        map.put("373:8229", "Healing Potion II");
        map.put("373:8230", "Night Vision Potion (3:00)");
        map.put("373:8233", "Strength Potion II (1:30)");
        map.put("373:8235", "Potion of Leaping (1:30)");
        map.put("373:8236", "Harming Potion II");
        map.put("373:8237", "Water Breathing Potion (3:00)");
        map.put("373:8238", "Invisibility Potion (3:00)");
        map.put("373:8257", "Regeneration Potion (2:00)");
        map.put("373:8258", "Swiftness Potion (8:00)");
        map.put("373:8259", "Fire Resistance Potion (8:00)");
        map.put("373:8260", "Poison Potion (2:00)");
        map.put("373:8262", "Night Vision Potion (8:00)");
        map.put("373:8264", "Weakness Potion (4:00)");
        map.put("373:8265", "Strength Potion (8:00)");
        map.put("373:8266", "Slowness Potion (4:00)");
        map.put("373:8269", "Water Breathing Potion (8:00)");
        map.put("373:8270", "Invisibility Potion (8:00)");
        map.put("373:16378", "Fire Resistance Splash (2:15)");
        map.put("373:16385", "Regeneration Splash (0:33)");
        map.put("373:16386", "Swiftness Splash (2:15)");
        map.put("373:16388", "Poison Splash (0:33)");
        map.put("373:16389", "Healing Splash");
        map.put("373:16392", "Weakness Splash (1:07)");
        map.put("373:16393", "Strength Splash (2:15)");
        map.put("373:16394", "Slowness Splash (1:07)");
        map.put("373:16396", "Harming Splash");
        map.put("373:16418", "Swiftness Splash II (1:07)");
        map.put("373:16420", "Poison Splash II (0:16)");
        map.put("373:16421", "Healing Splash II");
        map.put("373:16422", "Night Vision Splash (2:15)");
        map.put("373:16425", "Strength Splash II (1:07)");
        map.put("373:16428", "Harming Splash II");
        map.put("373:16429", "Water Breathing Splash (2:15)");
        map.put("373:16430", "Invisibility Splash (2:15)");
        map.put("373:16449", "Regeneration Splash (1:30)");
        map.put("373:16450", "Swiftness Splash (6:00)");
        map.put("373:16451", "Fire Resistance Splash (6:00)");
        map.put("373:16452", "Poison Splash (1:30)");
        map.put("373:16454", "Night Vision Splash (6:00)");
        map.put("373:16456", "Weakness Splash (3:00)");
        map.put("373:16457", "Strength Splash (6:00)");
        map.put("373:16458", "Slowness Splash (3:00)");
        map.put("373:16461", "Water Breathing Splash (6:00)");
        map.put("373:16462", "Invisibility Splash (6:00)");
        map.put("373:16471", "Regeneration Splash II (0:16)");
        map.put("374", "Glass Bottle");
        map.put("375", "Spider Eye");
        map.put("376", "Fermented Spider Eye");
        map.put("377", "Blaze Powder");
        map.put("378", "Magma Cream");
        map.put("379", "Brewing Stand");
        map.put("380", "Cauldron");
        map.put("381", "Eye of Ender");
        map.put("382", "Glistering Melon");
        map.put("383", "Spawn Egg");
        map.put("383:50", "Spawn Creeper");
        map.put("383:51", "Spawn Skeleton");
        map.put("383:52", "Spawn Spider");
        map.put("383:54", "Spawn Zombie");
        map.put("383:55", "Spawn Slime");
        map.put("383:56", "Spawn Ghast");
        map.put("383:57", "Spawn Pigman");
        map.put("383:58", "Spawn Enderman");
        map.put("383:59", "Spawn Cave Spider");
        map.put("383:60", "Spawn Silverfish");
        map.put("383:61", "Spawn Blaze");
        map.put("383:62", "Spawn Magma Cube");
        map.put("383:65", "Spawn Bat");
        map.put("383:66", "Spawn Witch");
        map.put("383:67", "Spawn Endermite");
        map.put("383:68", "Spawn Guardian");
        map.put("383:90", "Spawn Pig");
        map.put("383:91", "Spawn Sheep");
        map.put("383:92", "Cow Egg");
        map.put("383:93", "Spawn Chicken");
        map.put("383:94", "Spawn Squid");
        map.put("383:95", "Spawn Wolf");
        map.put("383:96", "Spawn Mooshroom");
        map.put("383:98", "Spawn Ocelot");
        map.put("383:100", "Spawn Horse");
        map.put("383:101", "Spawn Rabbit");
        map.put("383:120", "Spawn Villager");
        map.put("384", "Bottle o' Enchanting");
        map.put("385", "Fire Charge");
        map.put("386", "Book and Quill");
        map.put("387", "Written Book");
        map.put("388", "Emerald");
        map.put("389", "Item Frame");
        map.put("390", "Flower Pot");
        map.put("391", "Carrot");
        map.put("392", "Potato");
        map.put("393", "Baked Potato");
        map.put("394", "Poisonous Potato");
        map.put("395", "Empty Map");
        map.put("396", "Golden Carrot");
        map.put("397", "Skull Item");
        map.put("397:0", "Skeleton Skull");
        map.put("397:1", "Wither Skeleton Skull");
        map.put("397:2", "Zombie Head");
        map.put("373:3", "Head");
        map.put("373:4", "Creeper Head");
        map.put("398", "Carrot on a Stick");
        map.put("399", "Nether Star");
        map.put("400", "Pumpkin Pie");
        map.put("401", "Firework Rocket");
        map.put("402", "Firework Star");
        map.put("403", "Enchanted Book");
        map.put("404", "Redstone Comparator");
        map.put("405", "Nether Brick");
        map.put("406", "Nether Quartz");
        map.put("407", "Minecart with TNT");
        map.put("408", "Minecart with Hopper");
        map.put("409", "Prismarine Shard");
        map.put("410", "Prismarine Crystals");
        map.put("411", "Raw Rabbit");
        map.put("412", "Cooked Rabbit");
        map.put("413", "Rabbit Stew");
        map.put("414", "Rabbit Foot");
        map.put("415", "Rabbit Hide");
        map.put("417", "Iron Horse Armor");
        map.put("418", "Gold Horse Armor");
        map.put("419", "Diamond Horse Armor");
        map.put("420", "Lead");
        map.put("421", "Name Tag");
        map.put("422", "Minecart with Command Block");
        map.put("423", "Raw Mutton");
        map.put("424", "Cooked Mutton");
        map.put("2256", "Music Disk (13)");
        map.put("2257", "Music Disk (Cat)");
        map.put("2258", "Music Disk (Blocks)");
        map.put("2259", "Music Disk (Chirp)");
        map.put("2260", "Music Disk (Far)");
        map.put("2261", "Music Disk (Mall)");
        map.put("2262", "Music Disk (Mellohi)");
        map.put("2263", "Music Disk (Stal)");
        map.put("2264", "Music Disk (Strad)");
        map.put("2265", "Music Disk (Ward)");
        map.put("2266", "Music Disk (11)");
        map.put("2267", "Music Disk (wait)");
    }

    @Override public void onDisable(SuxiorCrates crates) {
        map.clear();
    }

    @SuppressWarnings("deprecation")
    public static ItemStack parseItem(String itemValue) {
        for (String itemID : map.keySet()) {

            if (itemID.equalsIgnoreCase(itemValue)) {
                if (itemID.contains(":")) {
                    return new ItemStack(
                            Material.getMaterial(Integer.parseInt(itemID.split(":")[0])),
                            1,
                            Short.parseShort(itemID.split(":")[1])
                    );
                } else {
                    return new ItemStack(
                            Material.getMaterial(Integer.parseInt(itemID)),
                            1,
                            (short) 0
                    );
                }
            }

        }

        return null;
    }

}