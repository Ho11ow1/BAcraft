/* Copyright 2025 Hollow1
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.hollow1.bacraft.items;
//
import com.hollow1.bacraft.BAcraft;
//
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
//
import net.minecraft.item.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;

public class ModItems
{
    public static final RegistryKey<ItemGroup> BA_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(BAcraft.MOD_ID, "ba_tab"));
    public static final ItemGroup BA_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(Items.BOOK))
        .displayName(Text.literal("BAcraft Items"))
        .build();

    public static final Item SCHOOL_CHANGER = new SchoolChanger(new Item.Settings());

    public static void registerItems()
    {
        // Register the item group
        Registry.register(Registries.ITEM_GROUP, BA_GROUP_KEY, BA_GROUP);

        // Register item to /give
        Registry.register(Registries.ITEM, new Identifier(BAcraft.MOD_ID, "school_changer"), SCHOOL_CHANGER);

        addItemEntries();
    }

    private static void addItemEntries()
    {
        // Add items to BA_GROUP
        ItemGroupEvents.modifyEntriesEvent(BA_GROUP_KEY).register(entries -> {
            entries.add(SCHOOL_CHANGER);
        });
    }
}

