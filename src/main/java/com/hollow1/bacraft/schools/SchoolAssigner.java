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
package com.hollow1.bacraft.schools;
//
import com.hollow1.bacraft.BAcraft;
//
import java.util.Random;
import java.util.UUID;

public class SchoolAssigner
{
    private static final Random rand = new Random();

    public static void assignSchool(UUID playerID)
    {
        if (BAcraft.studentList.containsKey(playerID)) { return; }

        int randomIndex = rand.nextInt(School.schoolList.size());
        School randomSchool = School.schoolList.get(randomIndex);

        BAcraft.studentList.put(playerID, randomSchool);
    }

    public static void changeSchool(UUID playerID)
    {
        int newIndex = rand.nextInt(School.schoolList.size());
        School newSchool = School.schoolList.get(newIndex);

        BAcraft.studentList.put(playerID, newSchool);
    }
}
