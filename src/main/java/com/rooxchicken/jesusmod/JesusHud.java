package com.rooxchicken.jesusmod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rooxchicken.jesusmod.client.JesusModClient;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class JesusHud implements HudRenderCallback
{
    private boolean wasShowing = false;
    //private boolean soundPlayed = false;
    private float t = 0;

    private Identifier jesus = Identifier.of("jesus-mod", "textures/jesus.png");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter)
    {
        MinecraftClient client = MinecraftClient.getInstance();
        float hp = client.player.getHealth();

        if(!client.isPaused())
            t -= tickCounter.getLastFrameDuration();

        if(hp <= 4)
        {
            if(!wasShowing)
            {
                client.world.playSound(client.player, client.player.getBlockPos(), JesusModClient.bell, SoundCategory.MASTER, 0.4f, 1f);
                t = 30;
            }
            
            wasShowing = true;
        }
        else if(t <= 0)
        {
            wasShowing = false;
            return;
        }

        // if(t < 4f && !soundPlayed && hp <= 4)   
        // {
        //     soundPlayed = true;
        //     client.world.playSound(client.player, client.player.getBlockPos(), JesusModClient.bell, SoundCategory.MASTER, 0.4f, 1f);
        // }

        if(t <= 0f && hp <= 4)
        {
            client.world.playSound(client.player, client.player.getBlockPos(), JesusModClient.bell, SoundCategory.MASTER, 0.4f, 1f);
            t = 30;
        }

        RenderSystem.enableBlend();

        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, t/36f);
        drawContext.drawTexture(jesus, 0, 0, 0, 0, width, height, width, height);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);

        RenderSystem.disableBlend();
    }
}
