/***********************************************************
Nimi:   miniprojekti 7 A
Tekijä:	Maarit Parkkonen
Pvm:    9.7.2018
Kuvaus: Luokat 
        Elain
		- attribuutit: nimi, elossa
		- metodit: konstruktori, vaihdaNimi, kerroNimi, 
		  onkoElossa, merkitseKuolleeksi, testaaElain
		Kissa (perii Elain -luokan)
		- attribuutit: elamatKpl
		- metodit: konstruktori, vahennaElama, montakoElamaa
		Merirosvo
		- attribuutit: nimi
		- metodit: konstruktori, kerroNimi		
		Papukaija (perii Elain -luokan)
		- attribuutit: isanta (Merirosvo)
		- vaihdaIsanta, kerroIsanta
		Lammas (perii Elain -luokan)
		- attribuutit: valkeaLammas
		- metodit: konstruktori, vaihdaVari, onkoValkoinen
		
		Pääohjelma testaa luokkien toimintaa.
		(ä=\u00E4 ö=\u00F6)
*************************************************************/

//luokkakirjasto
import java.lang.String;

//Elain -luokka
class Elain{
	protected String nimi;			//eläimen nimi
	protected Boolean elossa;		//onko eläin elossa=true vai kuollut=false
	
	//konstruktori
	Elain(String n){
		nimi=n;
		elossa=true;				//luotu eläin on aina aluksi elossa
	}
	
	//vaihtaa eläimelle uuden nimen
	public void vaihdaNimi(String uusiNimi){
		nimi=uusiNimi;
	}
	
	//tulostaa eläimen nimen
	public void kerroNimi(){
		System.out.println("El\u00E4imen nimi on "+nimi+".");
	}
	
	//tulostaa onko eläin elossa vai kuollut
	public void onkoElossa(){
		if (elossa)
			System.out.println(nimi+" on elossa.");
		else
			System.out.println(nimi+" on kuollut.");			
	}
	
	//vaihtaa eläimen tilan kuolleeksi
	public void merkitseKuolleeksi(){
		elossa=false;
	}
	
	//testaa Elain -luokan metodeja (ei merkitseKuolleeksi, koska Kissa -luokka ylikirjoittaa sen)
	public void testaaElain(String uusiNimi){
		kerroNimi();
		vaihdaNimi(uusiNimi);
		kerroNimi();
		onkoElossa();
	}
}

class Kissa extends Elain {
	private int elamatKpl;			//jäljellä olevien elämien määrä
	
	//konstruktori
	Kissa(String n, int kpl){
		super(n);					//yliluokan rakentajan kutsu
		elamatKpl=kpl;
	}
	
	//vähentää yhden elämän, jos kissa on elossa
	public void vahennaElama(){
		if (elossa)					//jos kissa elossa
			elamatKpl--;
		else						//jos kuollut
			System.out.println("El\u00E4m\u00E4\u00E4 ei voi v\u00E4hent\u00E4\u00E4. Kissa: "+nimi+", on jo kuollut.");
		if (elamatKpl==0)			//jos elämät loppuivat
			merkitseKuolleeksi();	//merkitään kissa kuolleeksi
	}
	
	//ylikirjoitetaan yliluokan metodi
	public void merkitseKuolleeksi(){
		elossa=false;
		elamatKpl=0;				//nollataan kissan elämät
	}

	//palauttaa kissalla jäljellä olevien elämien kappalemäärän
	// ja tulostaa ne myös näytölle
	public int montakoElamaa(){
		if (elossa)					//jos kissa elossa
			System.out.println("Kissalla: "+nimi+", on el\u00E4m\u00E4\u00E4 j\u00E4ljell\u00E4 "+elamatKpl+" kpl.");
		else						//jos kissa kuollut
		    System.out.println("Kissa: "+nimi+", on kuollut.");
		return elamatKpl;	
	}
}

//Merirosvo -luokka, papukaijan isännäksi
class Merirosvo{
	private String nimi;
	
	//konstruktori
	Merirosvo(String n){
		nimi=n;
	}
	
	//palauttaa merirosvon nimen
	public String kerroNimi(){
		return nimi;
	}
}

//Papukaija -luokka
class Papukaija extends Elain{
	private Merirosvo isanta;		//papukaijan isäntä
	
	//konstruktori
	Papukaija(String n, Merirosvo rosvo){
		super(n);					//yliluokan konstruktorin kutsu
		isanta=rosvo;
	}
	
	//vaihdetaan papukaijalle uusi isäntä
	//kuollut papukaija on täytetty, joten sekin voi vielä vaihtaa isäntää :)
	public void vaihdaIsanta(Merirosvo rosvo){
			isanta=rosvo;
	}
	
	//tulostaa papukaijan isännän nimen näytölle
	public void kerroIsanta(){
		    System.out.println("Papukaijan: "+nimi+", is\u00E4nt\u00E4 on "+isanta.kerroNimi()+".");	
	}
}

//Lammas -luokka
class Lammas extends Elain{
	private Boolean valkeaLammas;	//lampaan väri, valkoinen=true musta=false
	
	//konstruktori
	Lammas(String n){
		super(n);					//yliluokan konstruktorin kutsu
		valkeaLammas=true;  		//luotu lammas on aina aluksi valkoinen
	}
	
	//vaihtaa lampaan värin jos lammas on elossa
	public void vaihdaVari(){
		if (elossa)
			if (valkeaLammas)			//jos lammas valkea -> mustaksi
				valkeaLammas=false;
			else						//jos lammas musta -> valkeaksi
				valkeaLammas=true;
		else							//lammas on kuollut
		    System.out.println("Lammas: "+nimi+", on kuollut. V\u00E4ri\u00E4 ei voi en\u00E4\u00E4 muuttaa.");			
	}
	
	//tulostaa lampaan värin
	public void kerroVari(){
		if (valkeaLammas)
		    System.out.println("Lammas: "+nimi+", on valkoinen.");
		else
		    System.out.println("Lammas: "+nimi+", on musta.");			
	}
}
//
public class  mini7A{
   //pääohjelma
   public static void main (String [ ] args) {
      
	  //luodaan eläin
	  Elain elain1=new Elain("Jeppe");
	  
	  //testataan eläintä
	  System.out.println("Testataan Elain -luokka:");
	  elain1.testaaElain("Jassu");		 //Default: Eläimen nimi on Jeppe. Eläimen nimi on Jassu. Jassu on elossa.
	  elain1.merkitseKuolleeksi();		 
	  elain1.onkoElossa();				 //Default: Jassu on kuollut.
	  
	  //luodaan kissa
	  Kissa kissa1=new Kissa("Miisu",3);
	  
	  //testataan kissaa
	  System.out.println("\nTestataan Kissa -luokka:");
	  kissa1.testaaElain("Viiru");		//Default: Eläimen nimi on Miisu. Eläimen nimi on Viiru. Viiru on elossa
	  while (kissa1.montakoElamaa()>0)	//Default: Kissalla: Viiru, on elämää jäljellä 3 kpl.. 2 kpl .. 1kpl Kissa: Viiru, on kuollut.
		kissa1.vahennaElama();			
	  kissa1.onkoElossa();				//Default: Viiru on kuollut.
	  kissa1.vahennaElama();		 	//Default: Elämää ei voi vähentää. Kissa: Viiru, on jo kuollut.
	  Kissa kissa2=new Kissa("Mikki",6);	  
	  kissa2.onkoElossa();				//Default: Mikki on elossa.
	  kissa2.merkitseKuolleeksi();		
	  while (kissa2.montakoElamaa()>0)	//Default: Kissa: Mikki, on kuollut.
		kissa2.vahennaElama();			
	  kissa2.onkoElossa();				//Default: Mikki on kuollut.
	  
	  
	  //luodaan merirosvoja
	  Merirosvo rosvo1=new Merirosvo("Kapteeni Koukku");
	  Merirosvo rosvo2=new Merirosvo("Seitsem\u00E4nmeren kauhu");  
	  
	  //luodaan papukaija
	  Papukaija kaija1=new Papukaija("Rilli",rosvo1);
	  
	  //testataan papukaijaa ja merirosvoja
	  System.out.println("\nTestataan Papukaija ja Merirosvo -luokat:");
	  kaija1.testaaElain("Polli");		//Default: Eläimen nimi on Rilli. Eläimen nimi on Polli. Polli on elossa.
	  kaija1.kerroIsanta();				//Default: Papukaijan: Polli, isäntä on Kapteeni Koukku.
	  kaija1.vaihdaIsanta(rosvo2);
	  kaija1.kerroIsanta();				//Default: Papukaijan: Polli, isäntä on Seitsemänmeren kauhu.
	  kaija1.merkitseKuolleeksi();
	  kaija1.onkoElossa();				//Default: Polli on kuollut.
	  
	  //luodaan lammas
	  Lammas lammas1=new Lammas("P\u00E4k\u00E4");
	  
	  //testataan lammas
	  System.out.println("\nTestataan Lammas -luokka:");
	  lammas1.testaaElain("Pumpuli");	//Default: Eläimen nimi on Päkä. Eläimen nimi on Pumpuli. Pumpuli on elossa.
	  lammas1.kerroVari();				//Default: Lammas: Pumpuli, on valkoinen.
	  lammas1.vaihdaVari();
	  lammas1.kerroVari();				//Default: Lammas: Pumpuli, on musta.
	  lammas1.merkitseKuolleeksi();
	  lammas1.onkoElossa();	  			//Default: Pumpuli on kuollut.
	  lammas1.vaihdaVari();				//Default: Lammas: Pumpuli, on kuollut. Väriä ei voi enää muuttaa.
	}
	     
} 