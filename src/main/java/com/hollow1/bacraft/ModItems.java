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
package com.hollow1.bacraft;
//
import com.hollow1.bacraft.items.SchoolChanger;
//
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems
{
    public static final Item schoolChanger = new SchoolChanger(new Item.Settings());

    public static void registerItems()
    {
        Registry.register(Registries.ITEM, new Identifier(BAcraft.modID.toLowerCase(), "school_changer"), schoolChanger);
    }

}

