package factorioMain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public class AssetManager {
	Image stone, grass, dirt, water, cursorimg, conveyor, voidimg, Inventory, iron, coal, copper, infobox, conveyorII, tree, concrete, coalentity, copperentity, ironentity,
	ironplate, copperplate, steel, irongear, circuit, frame, coppergear,slotimage, woodentity, chargebarimg, CraftImg, unknowScience,logisticsI, bluepotion, 
	greenpotion, redpotion, frametech, coppercoil, monitor, menubackground, buttonImg, loading, pipe,oilpumpImg, oilImg;
	Tile voidt, Grass, Dirt,oil ,Conveyor, ConveyorII, Tree, Concrete, Miner, Furnace, inserter, gearmaker, circuitmaker, framemaker, itemcollector, longinserter,
	GreenPotionMaker, RedPotionMaker, BluePotionMaker, WireMaker, MonitorMaker, Water, CoalOre, IronOre, CopperOre, Oilpump;
	Animation Mineranim, furnaceanim, inserteranim, longinserteranim,gearmakeranim, circuitassembleranim, frameassembleranim, itemcollectoranim, GreenScience,
	RedScience, BlueScience, WireCutter, Monitormaker, oilpump;
	Cursor cursor;
	Entity Iron, Copper, Coal, Wood, IronPlate, CopperPlate, Circuit, IronGear,CopperGear,Frame, Steel, BluePotion, RedPotion, GreenPotion, Coppercoil, Monitor;
	Science LogisticsI, FrameTech;
	private ItemInventory iteminventory;
	private ScienceHUD scienceHUD;
	private ArrayList<Tile> allTiles;
	private ArrayList<Entity> allEntity;
	public AssetManager() {
		// TODO Auto-generated constructor stub
		try {
			this.allTiles = new ArrayList<Tile>();
			this.allEntity = new ArrayList<Entity>();
			this.loadTexture();
			this.loadEntity();
			this.loadTiles();
			CraftManager craftManager = new CraftManager();
			craftManager.LoadCraft(this);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadTexture() throws SlickException {
			try {
				voidimg = loadImage("texture/void.png");
				grass = loadImage("texture/Grass.png");
				stone = loadImage("texture/Stone.png");
				dirt = loadImage("texture/Dirt.png");
				water = loadImage("texture/water.png");
				cursorimg = loadImage("texture/cursor.png");
				conveyor = loadImage("texture/conveyor.png");
				Inventory = loadImage("texture/Inventory.png");
				iron = loadImage("texture/iron.png");
				coal = loadImage("texture/coal.png");
				copper = loadImage("texture/copper.png");
				infobox = loadImage("texture/infobox.png");
				conveyorII = loadImage("texture/conveyorII.png");
				tree = loadImage("texture/TreeTest.png");
				concrete = loadImage("texture/Concrete.png");
				coalentity = loadImage("texture/coalEntity.png");
				copperentity = loadImage("texture/copperEntity.png");
				ironentity = loadImage("texture/ironEntity.png");
				ironplate = loadImage("texture/ironplate.png");
				copperplate = loadImage("texture/copperplate.png");
				irongear = loadImage("texture/gear.png");
				steel = loadImage("texture/steel.png");
				circuit = loadImage("texture/circuit.png");
				frame = loadImage("texture/frame.png");
				coppergear = loadImage("texture/coppergear.png");
				slotimage = loadImage("texture/slot.png");
				woodentity = loadImage("texture/woodentity.png");
				chargebarimg = loadImage("texture/chargebar.png");
				CraftImg = loadImage("texture/infoCraft.png");
				unknowScience = loadImage("texture/unknowscience.png");
				logisticsI = loadImage("texture/logisticsI.png");
				bluepotion = loadImage("texture/BluePotion.png");
				greenpotion = loadImage("texture/GreenPotion.png");
				redpotion = loadImage("texture/RedPotion.png");
				frametech = loadImage("texture/FrameTech.png");
				coppercoil = loadImage("texture/coppercoil.png");
				monitor = loadImage("texture/monitor.png");
				menubackground = loadImage("texture/mainBack.PNG");
				buttonImg = loadImage("texture/buttons.png");
				loading = loadImage("texture/loading.png");
				pipe = loadImage("texture/pipe.png");
				oilpumpImg = loadImage("texture/oilpump.png");
				oilImg = loadImage("texture/oil.png");
				SpriteSheet inserter = new SpriteSheet("texture/inserterAnime.png",32,32);
				SpriteSheet longinserter = new SpriteSheet("texture/longinserter.png",32,32);
				SpriteSheet miner = new SpriteSheet("texture/Miner.png",32,32);
				SpriteSheet furnace = new SpriteSheet("texture/furnaceAnime.png",32,32);
				SpriteSheet gearmaker = new SpriteSheet("texture/gearmaker.png",32,32);
				SpriteSheet circuitassemb = new SpriteSheet("texture/CircuitAssembler.png",32,32);
				SpriteSheet frameassemb = new SpriteSheet("texture/frameassembler.png",32,32);
				SpriteSheet itemcollector = new SpriteSheet("texture/itemcollector.png",32,32);
				SpriteSheet greenscience = new SpriteSheet("texture/greenmaker.png",32,32);
				SpriteSheet redscience = new SpriteSheet("texture/redmaker.png",32,32);
				SpriteSheet bluescience = new SpriteSheet("texture/bluemaker.png",32,32);
				SpriteSheet wirecutter = new SpriteSheet("texture/WireCutter.png",32,32);
				SpriteSheet monitormaker = new SpriteSheet("texture/monitormaker.png",32,32);
				SpriteSheet oilpump = new SpriteSheet("texture/oilpump.png",32,32);
				inserteranim = this.loadAnimation(inserter, 0, 3, 0);
				longinserteranim = this.loadAnimation(longinserter, 0, 3, 0);
				Mineranim = this.loadAnimation(miner, 0, 3, 0);
				furnaceanim = this.loadAnimation(furnace, 0, 3, 0);
				gearmakeranim = this.loadAnimation(gearmaker, 0, 3, 0);
				circuitassembleranim = this.loadAnimation(circuitassemb, 0, 3, 0);
				GreenScience = this.loadAnimation(greenscience, 0, 3, 0);
				RedScience = this.loadAnimation(redscience, 0, 3, 0);
				BlueScience = this.loadAnimation(bluescience, 0, 3, 0);
				WireCutter = this.loadAnimation(wirecutter, 0, 3, 0);
				Monitormaker = this.loadAnimation(monitormaker, 0, 4, 0);
				frameassembleranim = this.loadAnimation(frameassemb, 0, 6, 0);
				itemcollectoranim = this.loadAnimation(itemcollector, 0, 6, 0);
				this.oilpump = this.loadAnimation(oilpump, 0, 4, 0);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


    	
	}
	public void loadTiles() {
		this.iteminventory = new ItemInventory(this);
		Tree = new Tile(this.tree, "Tree",20); allTiles.add(Tree);
		CoalOre = new Iron(coal, "Coal", Coal); allTiles.add(CoalOre);
		IronOre = new Iron(iron, "Iron", Iron); allTiles.add(IronOre);
		CopperOre = new Iron(copper, "Copper", Copper); allTiles.add(CopperOre);
		Water = new Tile(this.water, "Water"); allTiles.add(Water);
		voidt = new Tile(this.voidimg,"void"); allTiles.add(voidt);
		Grass = new Tile(this.grass,"Grass"); allTiles.add(Grass);
		Dirt = new Tile(this.dirt,"Dirt"); allTiles.add(Dirt);
		oil = new Oil(this.oilImg,"oil"); allTiles.add(oil);
		Concrete = new Tile(this.concrete,"Concrete"); allTiles.add(Concrete);
		Conveyor = new Conveyor(this.conveyor,"Conveyor belt", true,2); allTiles.add(Conveyor);
		ConveyorII = new Conveyor(this.conveyorII, "Conveyor belt II", true,4); allTiles.add(ConveyorII);
		Miner = new Miner(this.Mineranim,"Miner", true); allTiles.add(Miner);
		cursor = new Cursor(this.cursorimg); 
		Furnace = new Furnace (this.furnaceanim, "Furnace"); allTiles.add(Furnace);
		inserter = new Inserter(this.inserteranim,"Inserter",true); allTiles.add(inserter);
		longinserter = new Inserter(this.longinserteranim,"longinserter",true,3); allTiles.add(longinserter);
		gearmaker = new Gearmaker(this.gearmakeranim,"Gear maker"); allTiles.add(gearmaker);
		itemcollector = new ItemCollector(this.itemcollectoranim,"Item collector"); allTiles.add(itemcollector);
		WireMaker = new WireCrafter(this.WireCutter,"Wire maker"); allTiles.add(WireMaker);
		//ASSEMBLY MACHINES (ANIMATION/MACHINE NAME/PRODUCED ITEM/REQUIRED ITEM1/ REQUIRED ITEM2)
		circuitmaker = new AssemblyMachine(this.circuitassembleranim,"Circuit assembler",this.Circuit,this.IronPlate,this.CopperPlate); allTiles.add(circuitmaker);
		framemaker = new AssemblyMachine(this.frameassembleranim,"Frame assembler", this.Frame,this.Circuit,this.IronGear); allTiles.add(framemaker);
		RedPotionMaker = new AssemblyMachine(this.RedScience,"Red potion maker", this.RedPotion,this.Coppercoil,this.IronGear); allTiles.add(RedPotionMaker);
		//HIGH ASSEMBLY MACHINES (ANIMATION/MACHINE NAME/PRODUCED ITEM/REQUIRED ITEM1/ REQUIRED ITEM2/REQUIRED ITEM3) 
		GreenPotionMaker = new HighAssemblyMachine(this.GreenScience,"Green potion factory",this.GreenPotion,this.Coppercoil,this.Frame,this.Circuit); allTiles.add(GreenPotionMaker);
		MonitorMaker = new HighAssemblyMachine(this.Monitormaker,"Monitor maker",this.Monitor,this.Circuit,this.Coppercoil,this.Steel); allTiles.add(MonitorMaker);	
		Oilpump = new Oilpump(this.oilpump,"Oil pump", false); allTiles.add(Oilpump);
	}
	public Tile loadTileByName(String name) {
		for (Tile t : this.allTiles) {
			if(t.getName().equals(name)) {
				return t;
			}
		}
		System.out.println("Not found : " + name);
		return voidt;
	}
	public void loadEntity() {
		Iron = new Entity(this.ironentity,0,0,"Iron"); allEntity.add(Iron);
		Copper = new Entity(this.copperentity,0,0,"Copper"); allEntity.add(Copper);
		Coal = new Entity(this.coalentity,0,0,"Coal"); allEntity.add(Coal);
		Wood = new Entity(this.woodentity,0,0,"Wood"); allEntity.add(Wood);
		IronPlate = new Entity(this.ironplate,0,0,"Iron plate"); allEntity.add(IronPlate);
		CopperPlate = new Entity(this.copperplate,0,0,"Copper plate"); allEntity.add(CopperPlate);
		Circuit = new Entity(this.circuit,0,0,"Circuit"); allEntity.add(Circuit);
		IronGear = new Entity(this.irongear,0,0,"Iron gear"); allEntity.add(IronGear);
		CopperGear = new Entity(this.coppergear,0,0,"Copper gear"); allEntity.add(CopperGear);
		Frame = new Entity(this.frame,0,0,"Frame"); allEntity.add(Frame);
		Steel = new Entity(this.steel,0,0,"Steel"); allEntity.add(Steel);
		RedPotion = new Entity(this.redpotion,0,0,"Red potion"); allEntity.add(RedPotion);
		BluePotion = new Entity(this.bluepotion,0,0,"Blue potion"); allEntity.add(BluePotion);
		GreenPotion = new Entity(this.greenpotion,0,0,"Green potion"); allEntity.add(GreenPotion);
		Coppercoil = new Entity(this.coppercoil,0,0,"Copper coil"); allEntity.add(Coppercoil);
		Monitor = new Entity(this.monitor,0,0,"Monitor"); allEntity.add(Monitor);
	}
	private static Image loadImage(String path) throws IOException, SlickException
	{
	    BufferedImage bufferedImage = ImageIO.read(new File(path));
	    Texture texture = BufferedImageUtil.getTexture("", bufferedImage);
	    Image image = new Image(texture.getImageWidth(), texture.getImageHeight());
	    image.setTexture(texture); 
	    return image;
	}
    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }
	public ItemInventory getIteminventory() {
		return iteminventory;
	}
	public void setIteminventory(ItemInventory iteminventory) {
		this.iteminventory = iteminventory;
	}
	public ScienceHUD getScienceHUD() {
		return scienceHUD;
	}
	public ArrayList<Entity> getAllEntity(){
		return this.allEntity;
	}
	public void setScienceHUD(ScienceHUD scienceHUD) {
		this.scienceHUD = scienceHUD;
	}

}
