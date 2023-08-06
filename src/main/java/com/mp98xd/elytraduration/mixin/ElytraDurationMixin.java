package com.mp98xd.elytraduration.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class ElytraDurationMixin {

	private final MinecraftClient client = MinecraftClient.getInstance();
	private final TextRenderer fontRenderer = this.client.textRenderer;

	@Inject(at = @At("HEAD"), method = "render")
	public void renderDurability(DrawContext context, float tickDelta, CallbackInfo info) {
		if (!this.client.options.hudHidden && this.client.player != null) {
			ItemStack chestplate = this.client.player.getInventory().getArmorStack(2);

			if (chestplate.getItem().equals(Items.ELYTRA)) {
				context.drawTextWithShadow(this.fontRenderer, String.valueOf(chestplate.getMaxDamage() - chestplate.getDamage()), 0, 0, 14737632);
			}
		}
	}

}
