package TFC.Items.ItemBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import TFC.TFCBlocks;
import TFC.API.Constant.Global;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFlowers extends ItemTerraBlock
{
	public ItemFlowers(Block b)
	{
		super(b);
		MetaNames = Global.FLOWER_META_NAMES;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return TFCBlocks.Flowers.getIcon(0, par1);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

		if (movingobjectposition == null)
		{
			return par1ItemStack;
		}
		else
		{
			if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
					return par1ItemStack;

				if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
					return par1ItemStack;

				if (TFCBlocks.Flowers.canBlockStay(par2World, i, j + 1, k) && par2World.isAirBlock(i, j + 1, k))
				{
					par2World.setBlock(i, j + 1, k, TFCBlocks.Flowers, par1ItemStack.getItemDamage(), 0x3);
					par2World.playSoundEffect((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), TFCBlocks.Flowers.stepSound.func_150496_b(), (TFCBlocks.Flowers.stepSound.getVolume() + 1.0F) / 2.0F, TFCBlocks.Flowers.stepSound.getPitch() * 0.8F);
					if (!par3EntityPlayer.capabilities.isCreativeMode) --par1ItemStack.stackSize;
				}
			}
			return par1ItemStack;
		}
	}

}
