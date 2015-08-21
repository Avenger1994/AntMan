package thecrafterl.mods.heroes.antman.entity;

public enum AntManSizes {

	SMALL(0.1F),
	NORMAL(1F),
	GIANT(3F);
	
	private AntManSizes(float multiplier) {
		this.multiplier = multiplier;
	}
	
	private float multiplier;
	
	public float getMultiplier() {
		return multiplier;
	}
	
	public static AntManSizes getSizeFromOrdinal(int i) {
		for(AntManSizes sizes : AntManSizes.values()) {
			if(sizes.ordinal() == i) {
				return sizes;
			}
		}
		
		return null;
	}
	
}
