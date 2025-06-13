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
package com.hollow1.bacraft.mixins;
//
import com.hollow1.bacraft.data.IPersistentPlayerData;
import com.hollow1.bacraft.data.PlayerData;
//
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
//
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IPersistentPlayerData
{
    @Unique
    private static final String CUSTOM_PLAYER_DATA_KEY = "BAcraft_PlayerData";

    @Unique
    private PlayerData playerData;

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void bacraft_writeNbt(NbtCompound nbt, CallbackInfo ci)
    {
        if (this.playerData == null) { this.playerData = new PlayerData(); }

        NbtCompound modDataCompound = new NbtCompound();

        this.playerData.writeToNbt(modDataCompound);

        if (!modDataCompound.isEmpty())
        {
            nbt.put(CUSTOM_PLAYER_DATA_KEY, modDataCompound);
        }
        else
        {
            nbt.remove(CUSTOM_PLAYER_DATA_KEY);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void bacraft_readNbt(NbtCompound nbt, CallbackInfo ci)
    {
        if (nbt.contains(CUSTOM_PLAYER_DATA_KEY, NbtCompound.COMPOUND_TYPE))
        {
            NbtCompound modDataCompound = nbt.getCompound(CUSTOM_PLAYER_DATA_KEY);
            if (this.playerData == null) { this.playerData = new PlayerData(); }

            this.playerData.readFromNbt(modDataCompound);
        }
        else
        {
            this.playerData = new PlayerData();
        }
    }

    @Override
    public PlayerData getPlayerData()
    {
        if (this.playerData == null) { this.playerData = new PlayerData(); }

        return this.playerData;
    }
}
