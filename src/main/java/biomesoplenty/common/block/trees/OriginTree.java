package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class OriginTree extends TreeNoConfig
{
	@Override
	protected Feature<?> getFeature(Random random)
	{
		return (random.nextInt(10) == 0 ? BOPBiomeFeatures.BIG_ORIGIN_TREE : BOPBiomeFeatures.ORIGIN_TREE);
	}
}