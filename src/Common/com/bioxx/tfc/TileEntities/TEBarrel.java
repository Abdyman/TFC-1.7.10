package com.bioxx.tfc.TileEntities;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

import com.bioxx.tfc.TFCBlocks;
import com.bioxx.tfc.TFCItems;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.IPipeConnectable;
import com.bioxx.tfc.api.TFCOptions;

public class TEBarrel extends NetworkTileEntity implements IInventory
{
	public int liquidLevel;
	public int Type;
	public int barrelType;
	public int mode;
	public ItemStack[] storage;
	public ItemStack output;
	private boolean sealed;
	private int sealtimecounter;
	public Item[] alcohols;
	public final int SEALTIME = TFCOptions.enableDebugMode ? 0 : (int)((TFC_Time.hourLength * 12) / 100);//default 80

	public TEBarrel()
	{
		liquidLevel = 0;
		sealed = false;
		//itemstack = new ItemStack(1,0,0);
		sealtimecounter = 0;
		alcohols = new Item[]{TFCItems.Beer, TFCItems.Cider, TFCItems.Vodka, TFCItems.Whiskey, TFCItems.RyeWhiskey, TFCItems.Sake, TFCItems.Rum};
		storage = new ItemStack[12];
	}

	public void careForInventorySlot()
	{
		/*if(Type == 1 && itemstack != null && itemstack.getItem() instanceof ItemTerra)
		{
			if(TFC_ItemHeat.HasTemp(itemstack))
			{
				float temp = TFC_ItemHeat.GetTemp(itemstack);
				if(liquidLevel >= 1 && temp > 1)
				{
					temp -= 100;
					liquidLevel -= 1;
					TFC_ItemHeat.SetTemp(itemstack, temp);
					TFC_ItemHeat.HandleItemHeat(itemstack);
				}
			}
		}*/
	}

	public boolean getSealed()
	{
		return sealed;
	}

	private void ProcessItems()
	{
		/*ItemStack itemstack2;
		if(itemstack != null && Type == 1)
		{
			if (itemstack.getItem() == TFCItems.ScrapedHide)
			{
				int damage = itemstack.getItemDamage();
				itemstack2 = new ItemStack(TFCItems.PrepHide, 0, damage);
				while(liquidLevel >= (16 + (damage * 10)) && itemstack.stackSize > 0)
				{
					liquidLevel -= (16 + (damage * 10));
					itemstack2.stackSize++;
					itemstack.stackSize--;
				}
				if(itemstack2.stackSize > 0)
					output = itemstack2;
			}
			else if (itemstack.getItem() == TFCItems.Jute)
			{
				itemstack2 = new ItemStack(TFCItems.JuteFibre, 0, 0);
				while(liquidLevel >= 18 && itemstack.stackSize > 0)
				{
					liquidLevel -= 18;
					itemstack2.stackSize++;
					itemstack.stackSize--;
				}
				if(itemstack2.stackSize > 0)
					output = itemstack2;
			}
			else if(itemstack.getItem() == TFCItems.Logs)
			{
				itemstack.stackSize--;
				if(itemstack.stackSize == 0)
					itemstack=null;
				Type = 3;
			}*/
		/*			else if(itemstack.getItem() == TFCItems.BarleyGrain)
			{
				itemstack.stackSize--;
				if(itemstack.stackSize == 0)
					itemstack=null;
				Type = 5;
			}
			else if((itemstack.getItem() == TFCItems.RedApple || itemstack.getItem() == TFCItems.GreenApple))
			{
				itemstack.stackSize--;
				if(itemstack.stackSize == 0)
					itemstack=null;
				Type = 6;
			}
			else if(itemstack.getItem() == TFCItems.Potato)
			{
				itemstack.stackSize--;
				if(itemstack.stackSize == 0)
					itemstack=null;
				Type = 7;
			}
			else if(itemstack.getItem() == TFCItems.WheatGrain)
			{
				itemstack.stackSize--;
				if(itemstack.stackSize == 0)
					itemstack=null;
				Type = 8;
			}
			else if(itemstack.getItem() == TFCItems.RyeGrain)
			{
				itemstack.stackSize--;
				if(itemstack.stackSize == 0)
					itemstack=null;
				Type = 9;
			}
			else if(itemstack.getItem() == TFCItems.RiceGrain)
			{
				itemstack.stackSize--;
				if(itemstack.stackSize == 0)
					itemstack=null;
				Type = 10;
			}
		 */
		/*else if(itemstack.getItem() == Items.sugar)
			{
				itemstack.stackSize--;
				if(itemstack.stackSize == 0)
					itemstack=null;
				Type = 11;
			}
		}
		else if(itemstack != null && Type == 2)
		{
			if(itemstack.getItem() == TFCItems.Hide)
			{
				int damage = itemstack.getItemDamage();
				itemstack2 = new ItemStack(TFCItems.SoakedHide, 0, damage);
				while(liquidLevel >= (16 + (damage * 10)) && itemstack.stackSize > 0)
				{
					liquidLevel -= (16 + (damage * 10));
					itemstack2.stackSize++;
					itemstack.stackSize--;
				}
				if(itemstack2.stackSize > 0)
					output = itemstack2;
			}
		}
		else if(itemstack != null && Type == 3)
		{
			if(itemstack.getItem() == TFCItems.PrepHide)
			{
				Random rand = new Random();
				int damage = itemstack.getItemDamage();
				itemstack2 = new ItemStack(TFCItems.TerraLeather, 0, 0);
				while(liquidLevel >= (16 + (damage * 10)) && itemstack.stackSize > 0)
				{
					liquidLevel -= (16 + (damage * 10));
					itemstack2.stackSize += (1 + (rand.nextBoolean() ? damage : (damage > 0 ? damage -1 : 0)));
					itemstack.stackSize--;
				}
				if(itemstack2.stackSize > 0)
					output = itemstack2;
			}
		}
		else if(itemstack == null && Type >= 5 && Type <= 11)
			Type = 12;
		else if(itemstack == null && Type == 14)
		{
			itemstack2 = new ItemStack(TFCItems.Cheese);
			float cheeseWeight = 0.00f;
			while(liquidLevel >= 32)
			{
				liquidLevel -= 32;
				cheeseWeight += 20;
			}
			ItemFoodTFC.createTag(itemstack2, cheeseWeight, -24.0f);
			output = itemstack2;
		}
		if (liquidLevel == 0)
			Type = 0;
		if (itemstack != null && itemstack.stackSize == 0)
			itemstack = null;
		 */
	}

	public void setSealed()
	{
		sealed = true;
	}

	public void setUnsealed(String reason)
	{
		if(reason.equals("killing fuse"))
			sealed = false;
	}

	public static String getType(int type)
	{
		switch (type)
		{
		case 0: return StatCollector.translateToLocal("gui.Barrel.Empty");
		case 1: return StatCollector.translateToLocal("gui.Barrel.Water");
		case 2: return StatCollector.translateToLocal("gui.Barrel.Limewater");
		case 3: return StatCollector.translateToLocal("gui.Barrel.Tannin");
		case 4: return StatCollector.translateToLocal("gui.Barrel.Gunpowder");
		case 5: return StatCollector.translateToLocal("gui.Barrel.Beer");
		case 6: return StatCollector.translateToLocal("gui.Barrel.Cider");
		case 7: return StatCollector.translateToLocal("gui.Barrel.Vodka");
		case 8: return StatCollector.translateToLocal("gui.Barrel.Whiskey");
		case 9: return StatCollector.translateToLocal("gui.Barrel.RyeWhiskey");
		case 10: return StatCollector.translateToLocal("gui.Barrel.Sake");
		case 11: return StatCollector.translateToLocal("gui.Barrel.Rum");
		case 12: return StatCollector.translateToLocal("gui.Barrel.Vinegar");
		case 13: return StatCollector.translateToLocal("gui.Barrel.Milk");
		case 14: return StatCollector.translateToLocal("gui.Barrel.CurdledMilk");
		case 15: return StatCollector.translateToLocal("gui.Barrel.SaltWater");
		default: return "";
		}
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if(storage[i] != null)
		{
			if(storage[i] .stackSize <= j)
			{
				ItemStack is = storage[i];
				storage[i] = null;
				return is;
			}
			ItemStack isSplit = storage[i].splitStack(j);
			if(storage[i].stackSize == 0)
				storage[i] = null;
			return isSplit;
		}
		else
		{
			return null;
		}
	}

	public void ejectContents()
	{
		float f3 = 0.05F;
		EntityItem entityitem;
		Random rand = new Random();
		float f = rand.nextFloat() * 0.8F + 0.1F;
		float f1 = rand.nextFloat() * 2.0F + 0.4F;
		float f2 = rand.nextFloat() * 0.8F + 0.1F;

		for (int i = 0; i < getSizeInventory(); i++)
		{
			if(storage[i] != null)
			{
				entityitem = new EntityItem(worldObj, xCoord + f, yCoord + f1, zCoord + f2, storage[i]);
				entityitem.motionX = (float)rand.nextGaussian() * f3;
				entityitem.motionY = (float)rand.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float)rand.nextGaussian() * f3;
				worldObj.spawnEntityInWorld(entityitem);
			}
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public String getInventoryName()
	{
		return "Barrel";
	}

	@Override
	public int getSizeInventory()
	{
		return 12;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return storage[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return storage[i];
	}

	public int getInvCount()
	{
		int count = 0;
		for(ItemStack is : storage)
		{
			if(is != null)
				count++;
		}
		if(storage[0] != null && count == 1)
			return 0;
		return count;
	}

	public void readFromItemNBT(NBTTagCompound nbt)
	{
		liquidLevel = nbt.getInteger("liqLev");
		Type = nbt.getInteger("type");
		sealed = nbt.getBoolean("sealed");

		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbt1 = nbttaglist.getCompoundTagAt(i);
			byte byte0 = nbt1.getByte("Slot");
			if(byte0 >= 0 && byte0 < 2)
				setInventorySlotContents(byte0,ItemStack.loadItemStackFromNBT(nbt1));
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return false;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack is)
	{
		storage[i] = is;
	}

	//FIXME This needs to use blocks and not ids from now on
	public boolean checkValidAddition(int i)
	{
		if((i == Type || Type == 0 || (Type == 13 && i == 12)) && !sealed && liquidLevel < 256)
		{
			liquidLevel = Math.min(liquidLevel + 32, 256);
			if(Type == 0 || Type == 13)
			{
				Type = (i == 12 && Type == 13) ? 14 : i;
			}
			updateGui();
			return true;
		}
		updateGui();
		return false;
	}

	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{
			careForInventorySlot();
			if(sealed)
			{
				//entityplayer.closeScreen();
				/*This is where we handle the counter for producing charcoal.
				 *Once it reaches 24hours, we add charcoal to the fire and remove the wood.*/
				if(sealtimecounter == 0)
					sealtimecounter = (int) TFC_Time.getTotalTicks();

				if(sealtimecounter > 0 && sealtimecounter + (SEALTIME * 100) < TFC_Time.getTotalTicks())
				{
					sealtimecounter = 0;
					sealed = false;
					ProcessItems();
				}
			}

			if(mode == 1 && liquidLevel > 0 && TFC_Time.getTotalTicks() % 2 == 0 &&
					((IPipeConnectable)(TFCBlocks.SteamPipe)).feed(worldObj, 0, xCoord, yCoord, zCoord, true, true))
			{
				liquidLevel -= 4;
				updateGui();
			}
			if(liquidLevel == 0)
				Type = 0;

			/*if(mode == 0)
			{
				if(itemstack == null)
				{
					if(output != null)
					{
						itemstack = output;
						output = null;
					}
				}

				if (itemstack != null)
				{
					if ((Type ==0 || Type == 2) && itemstack.getItem() == TFCItems.Limewater && liquidLevel < 256)
					{
						liquidLevel = Math.min(liquidLevel + 32, 256);
						Type = 2;
						itemstack = new ItemStack(TFCItems.WoodenBucketEmpty);
						updateGui();
					}
					else if ((Type == 0 || Type == 1) && (itemstack.getItem() == TFCItems.WoodenBucketWater) && liquidLevel < 256)
					{
						liquidLevel = Math.min(liquidLevel + 32, 256);
						Type = 1;
						itemstack = new ItemStack(TFCItems.WoodenBucketEmpty);
						updateGui();
					}
					else if ((Type == 0 || Type == 15) && (itemstack.getItem() == TFCItems.WoodenBucketSaltWater) && liquidLevel < 256)
					{
						liquidLevel = Math.min(liquidLevel + 32, 256);
						Type = 15;
						itemstack = new ItemStack(TFCItems.WoodenBucketEmpty);
						updateGui();
					}
					else if ((Type == 0||Type == 1) && (itemstack.getItem() == TFCItems.RedSteelBucketWater) && liquidLevel < 256)
					{
						liquidLevel = Math.min(liquidLevel + 32, 256);
						Type = 1;
						itemstack = new ItemStack(TFCItems.RedSteelBucketEmpty);
						updateGui();
					}
					else if ((Type == 0 || Type == 4) && itemstack.getItem() == Items.gunpowder && liquidLevel < 256)
					{
						liquidLevel = Math.min(liquidLevel + 1, 256);
						Type = 4;
						itemstack.stackSize -= 1;
						if(itemstack.stackSize == 0)
							itemstack=null;
						updateGui();
					}
					else if((Type == 0 || Type == 13 || Type == 14) && itemstack.getItem() == TFCItems.WoodenBucketMilk && liquidLevel < 256)
					{
						liquidLevel = Math.min(liquidLevel + 32, 256);
						Type = Math.max(13, Type);
						itemstack = new ItemStack(TFCItems.WoodenBucketEmpty);
						updateGui();
					}
					else if(Type == 13 && itemstack.getItem() == TFCItems.Vinegar && liquidLevel < 256)
					{
						liquidLevel = Math.min(liquidLevel + 32, 256);
						Type = 14;
						itemstack = new ItemStack(TFCItems.WoodenBucketEmpty);
						updateGui();
					}
				}
			}
			else if (itemstack != null)
			{
				if((Type >= 5 && Type <= 11) && itemstack.getItem() == Items.glass_bottle && liquidLevel > 9 * itemstack.stackSize)
				{
					liquidLevel = Math.max(0, liquidLevel - 9 * itemstack.stackSize);
					itemstack = new ItemStack(alcohols[Type-5]);
					updateGui();
				}
				else if (Type == 1 && (itemstack.getItem() == TFCItems.PotteryJug && itemstack.getItemDamage() == 1) && liquidLevel >= 16)
				{
					liquidLevel = Math.max(liquidLevel - 16, 0);
					Type = 1;
					itemstack.setItemDamage(2);
					updateGui();
				}
				else if(Type == 1 && itemstack.getItem() == Items.glass_bottle && liquidLevel > 9 * itemstack.stackSize)
				{
					liquidLevel = Math.max(0, liquidLevel - 9 * itemstack.stackSize);
					itemstack = new ItemStack(TFCItems.Potion);
					updateGui();
				}
				else if(Type == 12 && itemstack.getItem() == TFCItems.WoodenBucketEmpty && liquidLevel >= 32)
				{
					liquidLevel = Math.max(liquidLevel - 32, 0);
					Type = liquidLevel > 0 ? Type : 0;
					itemstack = new ItemStack(TFCItems.Vinegar);
					updateGui();
				}
				else if(Type == 1 && itemstack.getItem() == TFCItems.WoodenBucketEmpty && liquidLevel >= 32)
				{
					liquidLevel = Math.max(liquidLevel - 32, 0);
					Type = liquidLevel > 0 ? Type : 0;
					itemstack = new ItemStack(TFCItems.WoodenBucketWater);
					updateGui();
				}
				else if(Type == 2 && itemstack.getItem() == TFCItems.WoodenBucketEmpty && liquidLevel >= 32)
				{
					liquidLevel = Math.max(liquidLevel - 32, 0);
					Type = liquidLevel > 0 ? Type : 0;
					itemstack = new ItemStack(TFCItems.Limewater);
					updateGui();
				}
				else if(Type == 15 && itemstack.getItem() == TFCItems.WoodenBucketEmpty && liquidLevel >= 32)
				{
					liquidLevel = Math.max(liquidLevel - 32, 0);
					Type = liquidLevel > 0 ? Type : 0;
					itemstack = new ItemStack(TFCItems.WoodenBucketSaltWater);
					updateGui();
				}
				//Fill milk bucket
				else if(Type == 13 && itemstack.getItem() == TFCItems.WoodenBucketEmpty && liquidLevel >= 32)
				{
					liquidLevel = Math.max(liquidLevel - 32, 0);
					Type = liquidLevel > 0 ? Type : 0;
					itemstack = new ItemStack(TFCItems.WoodenBucketMilk, 1, 0);
					ItemCustomBucketMilk.createTag(itemstack, 20f);
					updateGui();
				}
			}*/
		}
	}

	public int getLiquidScaled(int i)
	{
		return (int)((liquidLevel / 256f) * i);
	}

	public boolean actionSeal()
	{
		if(output==null && liquidLevel > 0 && isItemValid())
		{
			sealed = true;
			updateGui();
			return true;
		}
		return false;
	}

	private boolean isItemValid()
	{
		if(mode == 0)
		{
			/*if(itemstack == null && ((Type >= 5 && Type <= 11) || Type == 14))
				return true;
			else if(itemstack == null)
				return false;
			else
			{
				Item id = itemstack.getItem();
				if(Type == 1)
				{
					if(id == TFCItems.ScrapedHide)
						return true;
					if(id == TFCItems.Jute)
						return true;
					if(id == TFCItems.Logs)
						return true;
					if(id == TFCItems.WheatGrain ||
							id == TFCItems.BarleyGrain ||
							id == TFCItems.RyeGrain ||
							id == TFCItems.RiceGrain ||
							id == TFCItems.Potato ||
							id == TFCItems.RedApple ||
							id == TFCItems.GreenApple ||
							id == Items.sugar)
						return true;
					if(id == TFCItems.Hide && Type == 2)
						return true;
					if(id == TFCItems.PrepHide && Type == 3)
						return true;
				}
			}*/
		}
		return false;
	}

	public void actionEmpty()
	{
		liquidLevel = 0;
		Type = 0;
		updateGui();
	}

	public void actionMode()
	{
		mode = (mode - 1) * -1;
		updateGui();
	}

	public void actionSwitchTab(int tab, EntityPlayer player)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setByte("tab", (byte)tab);
		nbt.setString("player", player.getDisplayName());
		this.broadcastPacketInRange(this.createDataPacket(nbt));
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("liqLev", liquidLevel);
		nbt.setInteger("Type", Type);
		nbt.setBoolean("Sealed", sealed);
		nbt.setInteger("SealTime", sealtimecounter);
		nbt.setInteger("mode", mode);

		NBTTagList nbttaglist = new NBTTagList();
		for(int i = 0; i < storage.length; i++)
		{
			if(storage[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				storage[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbt.setTag("Items", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		liquidLevel = nbt.getInteger("liqLev");
		Type = nbt.getInteger("Type");
		sealed = nbt.getBoolean("Sealed");
		sealtimecounter = nbt.getInteger("SealTime");
		mode = nbt.getInteger("mode");

		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		storage = new ItemStack[getSizeInventory()];
		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte byte0 = nbttagcompound1.getByte("Slot");
			if(byte0 >= 0 && byte0 < storage.length)
				storage[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}

	}

	public void updateGui()
	{
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		//validate();
	}

	@Override
	public void handleInitPacket(NBTTagCompound nbt) {

	}

	@Override
	public void createInitNBT(NBTTagCompound nbt) {

	}

	@Override
	public void handleDataPacket(NBTTagCompound nbt)
	{
		if(!worldObj.isRemote)
		{
			int tab = nbt.getByte("tab");
			if(tab == 0)
				worldObj.getPlayerEntityByName(nbt.getString("player")).openGui(TerraFirmaCraft.instance, 35, worldObj, xCoord, yCoord, zCoord);
			else if(tab == 1)
				worldObj.getPlayerEntityByName(nbt.getString("player")).openGui(TerraFirmaCraft.instance, 36, worldObj, xCoord, yCoord, zCoord);
		}
	}

}
