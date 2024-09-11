package com.rooxchicken.jesusmod.client;

import com.rooxchicken.jesusmod.JesusHud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class JesusModClient implements ClientModInitializer
{
    public static Identifier bell_id = Identifier.of("jesus-mod", "bell");
    public static SoundEvent bell = SoundEvent.of(bell_id);

    @Override
    public void onInitializeClient()
    {
        Registry.register(Registries.SOUND_EVENT, bell_id, bell);
        HudRenderCallback.EVENT.register(new JesusHud());
    }
    
}
