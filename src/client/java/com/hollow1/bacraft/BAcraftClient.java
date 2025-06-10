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
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
//
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
//
import java.util.UUID;

public class BAcraftClient implements  ClientModInitializer
{
    private boolean canRender = false;
    private boolean hasJoinedWorld = false;

    @Override
    public void onInitializeClient()
    {
        HudRenderCallback.EVENT.register(this::onHudRender);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!hasJoinedWorld && client.world != null)
            {
                hasJoinedWorld = true;
                canRender = true;
            }
        });
    }

    private void onHudRender(DrawContext context, float tickDelta)
    {
        if (canRender)
        {
            MinecraftClient client = MinecraftClient.getInstance();
            UUID playerID = client.player.getUuid();
            int x = 5;
            int y = 5;

            String schoolName = BAcraft.studentList.get(playerID).getSchoolName();

            Identifier textureId = new Identifier(BAcraft.modID, "textures/emblems/" + schoolName + ".png");
            if (client.getTextureManager().getTexture(textureId) != null)
            {
                context.drawText(client.textRenderer, Text.literal("School: "), x, y, 0xFFFFFF, true);

                // Save current matrix
                context.getMatrices().push();
                // Move origin
                context.getMatrices().translate(x + 35, y - 4, 0);
                // Scale the matrix
                context.getMatrices().scale(16.0f / 32.0f, 16.0f / 29.0f, 1.0f);
                // Draw full texture
                context.drawTexture(textureId, 0, 0, 0, 0, 32, 29, 32, 29);
                // Restore matrix state
                context.getMatrices().pop();
            }
            else
            {
                context.drawText(client.textRenderer, Text.literal("Texture not found " + schoolName), x, y, 0xFFFFFF, true);
            }
        }
    }
}