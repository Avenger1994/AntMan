package thecrafterl.mods.heroes.antman.entity;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import thecrafterl.mods.heroes.antman.AntMan;
import cpw.mods.fml.common.registry.EntityRegistry;

public class AMEntities {

	public static void init() {
		createEntity(EntityCrazyAnt.class, "CrazyAnt", 0xffa201, 0xffd501);
		EntityRegistry.registerModEntity(EntityRepulsor.class, "antman_repulsor", EntityRegistry.findGlobalUniqueEntityId(), AntMan.instance, 64, 10, true);
	}
	
	public static void createEntity(Class entityClass, String name, int solidColor, int spotColor) {
		int randomId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, name, randomId);
		EntityRegistry.registerModEntity(entityClass, name, randomId, AntMan.instance, 64, 1, true);
		EntityRegistry.addSpawn(entityClass, 10, 0, 2, EnumCreatureType.ambient, BiomeGenBase.forest);
		
		createEgg(randomId, solidColor, spotColor);
	}

	private static void createEgg(int randomId, int solidColor, int spotColor) {
		EntityList.entityEggs.put(Integer.valueOf(randomId), new EntityList.EntityEggInfo(randomId, solidColor, spotColor));
	}
	
}
