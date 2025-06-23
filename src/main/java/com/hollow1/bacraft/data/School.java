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
import com.hollow1.bacraft.schools.*;
//
import net.minecraft.entity.effect.StatusEffectInstance;
//
import java.util.ArrayList;
import java.util.List;

public abstract class School
{
    private final String schoolName;
    private final Emblem emblem;
    public static final List<School> schoolList = new ArrayList<School>();
    public abstract StatusEffectInstance getTickEffect();

    public School(String schoolName, Emblem emblem)
    {
        this.schoolName = schoolName;
        this.emblem = emblem;
    }

    public static void initializeSchools()
    {
        schoolList.add(new Abydos());
        schoolList.add(new Arius());
        schoolList.add(new Gehenna());
        schoolList.add(new HighLander());
        schoolList.add(new Hyakkiyako());
        schoolList.add(new Millennium());
        schoolList.add(new RedWinter());
        schoolList.add(new Schale());
        schoolList.add(new Shanhaijing());
        schoolList.add(new SpecialResponeTeam());
        schoolList.add(new Trinity());
        schoolList.add(new Valkyrie());
    }

    public String getSchoolName() { return schoolName; }
    public static School getSchoolByName(String schoolName)
    {
        for (School school : schoolList)
        {
            if (school.schoolName.equals(schoolName))
            {
                return school;
            }
        }
        return null;
    }

    public static class Emblem
    {
        private String texturePath;

        public Emblem(String texturePath) { this.texturePath = texturePath; }

        public String getPath() { return texturePath; }
        public void setPath(String texturePath) { this.texturePath = texturePath; }
    }
}