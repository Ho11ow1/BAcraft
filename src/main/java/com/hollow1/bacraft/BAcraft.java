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
import com.hollow1.bacraft.common.PlayerData;
import com.hollow1.bacraft.items.ModItems;
import com.hollow1.bacraft.common.School;
import com.hollow1.bacraft.common.SchoolManager;
//
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
//
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BAcraft implements ModInitializer
{
    public static final String MOD_ID = "bacraft";
    public static Map<UUID, PlayerData> playerMap = new HashMap<UUID, PlayerData>();
    private final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize()
    {
        LOGGER.info("{} initialized by Hollow1!", MOD_ID);
        School.initializeSchools();
        ModItems.registerItems();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            onPlayerJoin(player);
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            onPlayerLeave(player);
        });

        ServerTickEvents.END_SERVER_TICK.register(this::applySchoolEffects);
    }

    private void onPlayerJoin(ServerPlayerEntity player)
    {
        UUID playerID = player.getUuid();
        SchoolManager.assignSchool(playerID);

        String schoolName = BAcraft.playerMap.get(playerID).getSchoolName();
        player.sendMessage(Text.literal("You've been assigned to the school of " + schoolName + "."), false);

        LOGGER.info("Assigned school({}) to player({})", playerMap.get(playerID).getSchoolName() , player.getName().getString());
    }

    private void onPlayerLeave(ServerPlayerEntity player)
    {
        // Emblem disappears with the player
        // Save school to NBT
    }

    private void applySchoolEffects(MinecraftServer server)
    {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList())
        {
            School school = School.getSchoolByName(playerMap.get(player.getUuid()).getSchoolName());
            if (school == null) { return; }

            StatusEffectInstance effect = school.getTickEffect();
            if (effect != null)
            {
                player.addStatusEffect(effect);
            }
        }
    }


}
