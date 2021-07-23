package factorioMain;

import org.newdawn.slick.GameContainer;

public class ScienceManager {
	public ScienceManager() {
		// TODO Auto-generated constructor stub
	}
	public void loadScience(AssetManager asset, ScienceHUD SH, GameContainer container) {
		//LOGISTICS I SCIENCE 
		asset.LogisticsI = new Science(asset.logisticsI, "LogisticsI");
		asset.LogisticsI.addContent(asset.ConveyorII);
		asset.LogisticsI.addContent(asset.longinserter);
		Craft clogisticsI = new Craft(asset);
		clogisticsI.AddRequiredItems(new ItemSlot(asset.RedPotion,10));
		asset.LogisticsI.setCraft(clogisticsI);
		SH.addScience(asset.LogisticsI, container);
		//FRAME TECH SCIENCE 
		asset.FrameTech = new Science(asset.frametech, "Frame Tech I");
		asset.FrameTech.addContent(asset.framemaker);
		asset.FrameTech.addContent(asset.GreenPotionMaker);
		asset.FrameTech.addContent(asset.MonitorMaker);
		Craft cFrame = new Craft(asset);
		cFrame.AddRequiredItems(new ItemSlot(asset.RedPotion,20));
		asset.FrameTech.setCraft(cFrame);
		SH.addScience(asset.FrameTech, container);
		
		
		
		
		//ASSIMILATION DES TECHNOLOGIES DEPENDANTES
		asset.LogisticsI.addUnlockedScience(asset.FrameTech);
		
		
	}
	

}
