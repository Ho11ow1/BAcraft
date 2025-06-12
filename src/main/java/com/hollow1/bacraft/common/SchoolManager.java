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
package com.hollow1.bacraft.common;
//
import com.hollow1.bacraft.BAcraft;
import com.hollow1.bacraft.schools.Schale;
//
import net.minecraft.nbt.NbtCompound;
//
import java.util.Random;
import java.util.UUID;

public class SchoolManager
{
    private static final Random RAND = new Random();

    public static void assignSchool(UUID playerID)
    {
        if (BAcraft.playerMap.containsKey(playerID)) { return; }

        int randomIndex = RAND.nextInt(School.schoolList.size());
        School randomSchool = School.schoolList.get(randomIndex);

        PlayerData data = new PlayerData(playerID, randomSchool);
        NbtCompound nbt = new NbtCompound(); // Get Compound from file or somewhere else
        data.writeSchoolNBT(nbt);

        BAcraft.playerMap.put(playerID, data);
    }

    public static void changeSchool(UUID playerID)
    {
        int newIndex = RAND.nextInt(School.schoolList.size());
        School newSchool = School.schoolList.get(newIndex);

        PlayerData data = new PlayerData(playerID, newSchool);
        NbtCompound nbt = new NbtCompound(); // Get Compound from file or somewhere else
        data.writeSchoolNBT(nbt);

        BAcraft.playerMap.put(playerID, data);
    }

    public static void loadSchool(UUID playerID)
    {
        PlayerData data = new PlayerData(playerID, new Schale());
        NbtCompound nbt = new NbtCompound(); // Get Compound from file or somewhere else
        data.readSchoolNBT(nbt);

        BAcraft.playerMap.put(playerID, data);
    }

    private static NbtCompound getNbtCompound(UUID playerID)
    {
        return null;
    }
}
