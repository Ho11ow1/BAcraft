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
import com.hollow1.bacraft.data.SchoolManager;
//
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
//
import java.util.UUID;

public class SchoolChanger extends Item
{
    public SchoolChanger(Settings settings) { super(settings); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!world.isClient)
        {
            UUID playerID = player.getUuid();

            try
            {
                SchoolManager.changeSchool(player);
                player.sendMessage(Text.literal("School assigned successfully! (new school: " + BAcraft.playerCacheMap.get(playerID).getSchoolName() + ")"), false);

                player.getItemCooldownManager().set(this, 20);

            }
            catch (Exception e)
            {
                player.sendMessage(Text.literal("Failed to assign school!"), false);
            }
        }

        if (!player.getAbilities().creativeMode) { itemStack.decrement(1); }

        return TypedActionResult.success(itemStack, world.isClient);
    }
}
