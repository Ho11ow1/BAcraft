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
package com.hollow1.bacraft.data;
//
import com.hollow1.bacraft.BAcraft;
//
import net.minecraft.entity.player.PlayerEntity;
//
import java.util.Random;

public class SchoolManager
{
    private static final Random RAND = new Random();

    public static void assignSchool(PlayerEntity player)
    {
        IPersistentPlayerData persistentPlayer = (IPersistentPlayerData) player;
        PlayerData playerData = persistentPlayer.getPlayerData();

        if (BAcraft.playerCacheMap.containsKey(player.getUuid())) { return; }
        if (playerData.getSchool() != null) { return; }

        int randomIndex = RAND.nextInt(School.schoolList.size());
        School randomSchool = School.schoolList.get(randomIndex);

        playerData.setSchool(randomSchool);
        BAcraft.playerCacheMap.put(player.getUuid(), playerData);
    }

    public static void changeSchool(PlayerEntity player)
    {
        IPersistentPlayerData persistentPlayer = (IPersistentPlayerData) player;
        PlayerData playerData = persistentPlayer.getPlayerData();
        School currentSchool = playerData.getSchool();

        School newSchool;
        do
        {
            int newIndex = RAND.nextInt(School.schoolList.size());
            newSchool = School.schoolList.get(newIndex);
        }
        while (newSchool.equals(currentSchool));

        playerData.setSchool(newSchool);
        BAcraft.playerCacheMap.put(player.getUuid(), playerData);
    }

}
