package biomesoplenty.liquids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.ftfluidsapi.BlockFluidClassic;
import biomesoplenty.ftfluidsapi.Fluid;
import biomesoplenty.ftfluidsapi.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidLiquidPoison extends BlockFluidClassic 
{
	public static Icon liquidPoisonStillIcon;
	public static Icon liquidPoisonFlowingIcon;
	
	private static FluidStack stack;
	
	public BlockFluidLiquidPoison(int id, Fluid fluid, Material material) 
	{
		super(id, fluid, material);
		
		this.quantaPerBlock = 4;
        this.setLightOpacity(3);
        
		for (int i = 8; i < 11; i++)
		{
			this.displacementIds.put(i, false);
		}
	}
	
	@Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {

        int bId = world.getBlockId(x, y, z);

        if (bId == 0) {
            return true;
        }
        if (bId == blockID) {
            return false;
        }
        if (displacementIds.containsKey(bId)) {
            return displacementIds.get(bId);
        }
        Material material = Block.blocksList[bId].blockMaterial;

        if (material.blocksMovement() || material == Material.water || material == Material.lava || material == Material.portal) 
        {
        	return false;
        }
        
        return true;
    }
	
    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
    {
        int meta = par1World.getBlockMetadata(x, y, z);
        
        if (par5Entity instanceof EntityLiving) 
        {
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.hunger.id, 100));
        }  
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) 
	{
		this.liquidPoisonStillIcon = iconRegister.registerIcon("BiomesOPlenty:liquid_poison_still");
		this.liquidPoisonFlowingIcon = iconRegister.registerIcon("BiomesOPlenty:liquid_poison_flowing");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return par1 != 0 && par1 != 1 ? this.liquidPoisonFlowingIcon : this.liquidPoisonStillIcon;
    }
}
