package factorioMain;

public class CraftManager {

	public CraftManager() {
		// TODO Auto-generated constructor stub
	}
	public void LoadCraft(AssetManager asset) {
		//CRAFT FOR CIRCUIT ASSEMBLER
		Craft circuitCraft = new Craft(asset);
		circuitCraft.AddRequiredItems(new ItemSlot(asset.IronPlate,4));
		circuitCraft.AddRequiredItems(new ItemSlot(asset.CopperPlate,4));
		asset.circuitmaker.setCraft(circuitCraft);
		//CRAFT FOR INSERTER
		Craft inserterCraft = new Craft(asset);
		inserterCraft.AddRequiredItems(new ItemSlot(asset.Iron,2));
		asset.inserter.setCraft(inserterCraft);
		//CRAFT FOR LONG INSERTER
		Craft LonginserterCraft = new Craft(asset);
		LonginserterCraft.AddRequiredItems(new ItemSlot(asset.IronPlate,2));
		asset.longinserter.setCraft(LonginserterCraft);
		//CRAFT FOR FURNACE
		Craft FurnaceCraft = new Craft(asset);
		FurnaceCraft.AddRequiredItems(new ItemSlot(asset.Coal,2));
		FurnaceCraft.AddRequiredItems(new ItemSlot(asset.Iron,2));
		asset.Furnace.setCraft(FurnaceCraft);
		//CRAFT FOR STORAGE CHEST
		Craft ChestCraft = new Craft(asset);
		ChestCraft.AddRequiredItems(new ItemSlot(asset.Wood,5));
		asset.itemcollector.setCraft(ChestCraft);
		//CRAFT FOR FRAME ASSEMBLER
		Craft AssemblerCraft = new Craft(asset);
		AssemblerCraft.AddRequiredItems(new ItemSlot(asset.Circuit,4));
		AssemblerCraft.AddRequiredItems(new ItemSlot(asset.IronGear,4));
		AssemblerCraft.AddRequiredItems(new ItemSlot(asset.Iron,10));
		asset.framemaker.setCraft(AssemblerCraft);
		//CRAFT FOR GEAR MAKER
		Craft GearMakerCraft = new Craft(asset);
		GearMakerCraft.AddRequiredItems(new ItemSlot(asset.Circuit,1));
		GearMakerCraft.AddRequiredItems(new ItemSlot(asset.CopperPlate,4));
		asset.gearmaker.setCraft(GearMakerCraft);
		//CRAFT FOR FOAM
		Craft foam = new Craft(asset);
		foam.AddRequiredItems(new ItemSlot(asset.Steel,1));
		asset.Concrete.setCraft(foam);
		//CRAFT FOR CONVEYOR BELT
		Craft conveyor = new Craft(asset);
		conveyor.AddRequiredItems(new ItemSlot(asset.Iron,1));
		asset.Conveyor.setCraft(conveyor);
		//CRAFT FOR CONVEYOR BELT II
		Craft conveyorII = new Craft(asset);
		conveyorII.AddRequiredItems(new ItemSlot(asset.Steel,1));
		asset.ConveyorII.setCraft(conveyorII);
		//CRAFT FOR MINER
		Craft Miner = new Craft(asset);
		Miner.AddRequiredItems(new ItemSlot(asset.Copper,4));
		Miner.AddRequiredItems(new ItemSlot(asset.Iron,4));
		asset.Miner.setCraft(Miner);
		//CRAFT FOR  RED POTION ASSEMBLY
		Craft redscience = new Craft(asset);
		redscience.AddRequiredItems(new ItemSlot(asset.Circuit,2));
		redscience.AddRequiredItems(new ItemSlot(asset.Steel,2));
		redscience.AddRequiredItems(new ItemSlot(asset.IronGear,10));
		asset.RedPotionMaker.setCraft(redscience);
		//CRAFT FOR GREEN POTION ASSEMBLY
		Craft greencience = new Craft(asset);
		greencience.AddRequiredItems(new ItemSlot(asset.Frame,2));
		greencience.AddRequiredItems(new ItemSlot(asset.Coppercoil,20));
		greencience.AddRequiredItems(new ItemSlot(asset.IronGear,20));
		asset.GreenPotionMaker.setCraft(greencience);
		//CRAFT FOR COIL MAKER
		Craft coilmaker = new Craft(asset);
		coilmaker.AddRequiredItems(new ItemSlot(asset.Circuit,2));
		coilmaker.AddRequiredItems(new ItemSlot(asset.IronPlate,5));
		asset.WireMaker.setCraft(coilmaker);
		//CRAFT FOR COMPUTER
		Craft computer = new Craft(asset);
		computer.AddRequiredItems(new ItemSlot(asset.Frame,4));
		computer.AddRequiredItems(new ItemSlot(asset.Circuit,4));
		computer.AddRequiredItems(new ItemSlot(asset.IronPlate,40));
		computer.AddRequiredItems(new ItemSlot(asset.Coppercoil,20));
		asset.MonitorMaker.setCraft(computer);
		
	}


}
