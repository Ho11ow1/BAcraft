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
//
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
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
	public static final String modID = "BAcraft";
	public static final Logger logger = LoggerFactory.getLogger(modID);
    public static Map<UUID, School> studentList = new HashMap<UUID, School>();

	@Override
	public void onInitialize()
	{
		logger.info("{} initialized by Hollow1!", modID);
        School.initializeSchools();
        ModItems.registerItems();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            onPlayerJoin(player);
        });
	}

    private void onPlayerJoin(ServerPlayerEntity player)
    {
        UUID playerID = player.getUuid();

        SchoolAssigner.assignSchool(playerID);
        player.sendMessage(Text.literal("You've been assigned the school of " + studentList.get(playerID).getSchoolName() + "."), false);
        logger.info("Assigned school({}) to player({})",studentList.get(playerID).getSchoolName() , player.getName().getString());
    }
}

