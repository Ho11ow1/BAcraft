package com.hollow1.bacraft.items;
//
import com.hollow1.bacraft.BAcraft;
import com.hollow1.bacraft.SchoolAssigner;
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
    public SchoolChanger(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack itemStack = player.getStackInHand(hand);

        // Only execute on server side
        if (!world.isClient)
        {
            UUID playerID = player.getUuid();

            try
            {
                SchoolAssigner.changeSchool(playerID);
                player.sendMessage(Text.literal("School assigned successfully! (new school: " + BAcraft.studentList.get(playerID).getSchoolName() + ")"), false);

                // Add cooldown (in ticks, 20 ticks = 1 second)
                player.getItemCooldownManager().set(this, 20);

            }
            catch (Exception e)
            {
                player.sendMessage(Text.literal("Failed to assign school!"), false);
            }
        }
        // Consume on use
        if (!player.getAbilities().creativeMode) { itemStack.decrement(1); }
        // return successful action
        return TypedActionResult.success(itemStack, world.isClient);
    }
}
