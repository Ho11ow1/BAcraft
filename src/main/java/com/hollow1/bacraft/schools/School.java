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
import java.util.ArrayList;
import java.util.List;

public class School
{
    private final String schoolName;
    private final Emblem emblem;
    public static final List<School> schoolList = new ArrayList<School>();

    public School(String schoolName, Emblem emblem)
    {
        this.schoolName = schoolName;
        this.emblem = emblem;
    }

    public String getSchoolName() { return schoolName; }

    public static void initializeSchools()
    {
        schoolList.add(new Trinity());
        schoolList.add(new Millennium());
    }

    public static class Emblem
    {
        private final String texturePath;

        public Emblem(String texturePath)
        {
            this.texturePath = texturePath;
        }
    }
}