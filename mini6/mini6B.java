/***************************************************************************
Nimi:	Miniprojekti 6 B
Tekijä:	Maarit Parkkonen
Pvm:	1.7.2018
Kuvaus: Pelihahmo -luokka
		- attribuutit: pelinimi, sukupuoli,
		  ikä, hahmo ja ase.
		- metodit: konstruktori, naytaPelihahmonTiedot ja
		  naytaPeliHahmonAseenTiedot.
		Hahmo -luokka
		- attribuutit: tyyppi, taso ja taidot.
		- metodit: konstruktori ja naytaHahmonTiedot.
		Ase -luokka:
		- attribuutit: tyyppi, vahinko ja bonukset.
		- metodit: konstruktori ja naytaAseenTiedot.
		
		Pelihahmo tarkoittaa pelaajan henkilökohtaista
		pelioliota. Hahmo on yleisluokka pelaajan pelihahmon roolille. 
		Usealla  pelaajalla voi olla siis sama hahmo eli rooli 
		mutta jokaisella pelaajalla on vain yksi oma pelihahmo. 
		Ase on pelaajan henkilökohtainen ja luodaan siksi yhdessä 
		pelihahmon kanssa.

		Pääohjelma testaa luokkien toimintaa.		  
***************************************************************************/

//luokkakirjasto
import java.lang.String;

//pelihahmon eli pelaajan henkilökohtainen ase
class Ase{
	//vakio, valittavissa olevat asetyypit
	public static final String asetyypit[]={"kiv\u00E4\u00E4ri","keih\u00E4s","jousi"};
	
	//attribuutit	
	private int tyyppi;		//asetyypin indeksinro
	private double vahinko;	//teho eli tappoprosentti, esim. 0.2=20% (1 eli 100% -> kuoli)
	private int bonukset;	//kuinka monta saalista tarvitaan lisäpisteisiin

	
	//konstruktori
	Ase(int t, double v, int b){
		tyyppi=t;
		vahinko=v;
		bonukset=b;
	}	
	
	//tulostaa olion attribuuttien arvot näytölle
	public void naytaAseenTiedot(){			
		System.out.println("Ase:\t\t"+asetyypit[tyyppi]);		//asetyypin nimi
		System.out.println("Tappo%:\t\t"+vahinko);				//tappoprosentti
		System.out.println("BonusKpl:\t"+bonukset);				//bonukseen tarvittava saalismäärä
	}
}

//Pelaajan pelihahmon rooli
class Hahmo{
	//vakio, valittavissa olevat hahmoluokan nimet
	public static final	String luokkanimi[]={"mets\u00E4st\u00E4j\u00E4","poliisi","ampuja"};
	public static final	String taidot[]={"tarkkuus","nopeus","kuulo","voimakkuus"};
	
	//attribuutit	
	private int luokka;		//luokkanimen indeksi
	private int taso;		//pelitaso
	private int[] kyvyt; 	//erityistaidot

	//konstruktori
	Hahmo(int tyyppi, int level, int[] kyky){
		luokka=tyyppi;
		taso=level;
		kyvyt=kyky;
	}	
	
	//tulostaa Hahmo -olion attribuuttien arvot näytölle
	public void naytaHahmonTiedot(Boolean tahdet){
		if (tahdet)							//jos otsikko tarvitaan
			System.out.println("\n***HAHMOLUOKKA***************************************");
		System.out.println("Luokkanimi:\t"+luokkanimi[luokka]);
		System.out.println("Taso:\t\t"+taso);
		System.out.print("Taidot:\t\t");
		for (int i=0;i<kyvyt.length;i++)	//hahmon erityistaidot 
			System.out.print(taidot[kyvyt[i]]+" "); //taitojen nimet taidot -taulukosta kyvyt -taulukon indeksien avulla
		System.out.println("");
	}
}

//Pelaajan henkilökohtainen pelihahmo eli peliolio
class Pelihahmo{
	//attribuutit	
	private String pelinimi;	//pelaajanimi
	private Boolean nainen;		//true=nainen, false=mies
	private int ika;		 	//ikä
	private Hahmo hahmoluokka;	//rooli	
	private Ase varuste; 	 	//ase

	//konstruktori
	Pelihahmo(String nimi,  Boolean sukupuoli, int vuodet, Hahmo ego, int aseTyyppi, double aseVahinko, int aseBonus){
		pelinimi=nimi;
		nainen=sukupuoli;
		ika=vuodet;
		hahmoluokka=ego;		
		varuste=new Ase(aseTyyppi,aseVahinko,aseBonus);		//luodaan pelihahmolle oma ase
	}	
	
	//tulostaa Pelihahmo -olion attribuuttien arvot näytölle
	public void naytaPelihahmonTiedot(){
		System.out.println("\n***PELIHAHMO*****************************************");
		System.out.println("Pelinimi:\t"+pelinimi);
		hahmoluokka.naytaHahmonTiedot(false);			//Hahmo -olion tietojen tulostuspyyntö
		if (nainen==true)
			System.out.println("Sukupuoli:\tnainen");
		else
			System.out.println("Sukupuoli:\tmies");
		System.out.println("Ik\u00E4:\t\t"+ika);
		varuste.naytaAseenTiedot();						//Ase -olion tietojen tulostuspyyntö
	}
	
	//tulostaa pelihahmon nimen ja pelihahmon aseen tiedot
	public void naytaPelihahmonAseenTiedot(){
		System.out.println("\n***ASE*****************************************");		
		System.out.println("Pelinimi:\t"+pelinimi);		
		varuste.naytaAseenTiedot();						//Ase -olion tietojen tulostuspyyntö
	}
}

//toteutusluokka
public class mini6B{
   //pääohjelma (ä=\u00E4 ö=\u00F6)
   public static void main(String [ ] args) {
	  
	  //luodaan Hahmo -olioita
	  //voitaisi luoda myös erikoistettuja hahmoja,
	  //kuten ampujaroolille erilaisilla kyvyillä ja tasoilla: tarkka-ampuja, jousiampuja...
	  //nyt loin nyt vain perushahmoja
	  int[] erityiskyvyt1={0,1,2}; 
	  Hahmo metsastaja=new Hahmo(0,5,erityiskyvyt1);
	  int[] erityiskyvyt2={1,3}; 
	  Hahmo poliisi=new Hahmo(1,3,erityiskyvyt2);
	  int[] erityiskyvyt3={0,1}; 
	  Hahmo ampuja=new Hahmo(2,4,erityiskyvyt3);	

	  
	  //tulostetaan näytölle Hahmo -olioiden tiedot
	  System.out.println("\nTestaus, hahmot:");
	  metsastaja.naytaHahmonTiedot(true);			//default: metsästäjä, 5, tarkkuus nopeus kuulo
	  poliisi.naytaHahmonTiedot(true);				//default: poliisi, 3, nopeus voimakkuus
	  ampuja.naytaHahmonTiedot(true);				//default: ampuja, 4, tarkkuus nopeus
	  
	  //luodaan pelihahmoja eli pelaajia
	  Pelihahmo pelaaja1=new Pelihahmo("\u00E4ss\u00E4",false,25,ampuja,0,0.8,8);
	  Pelihahmo pelaaja2=new Pelihahmo("Peto",true,42,metsastaja,2,0.3,3);
	  Pelihahmo pelaaja3=new Pelihahmo("Power",false,58,poliisi,0,0.5,5);
	  Pelihahmo pelaaja4=new Pelihahmo("Fauni",true,19,ampuja,2,0.5,5);
	  
	  //tulostetaan pelihahmo -olioiden tiedot	
	  System.out.println("\nTestaus, Pelihahmot:");	  
	  pelaaja1.naytaPelihahmonTiedot();				//default: ässä, ampuja, 4, tarkkuus nopeus, mies, 25, kivääri, 0.8, 8
	  pelaaja2.naytaPelihahmonTiedot();				//default: Peto, metsästäjä, 5, tarkkuus nopeus kuulo, nainen, 42, jousi, 0.3, 3
	  pelaaja3.naytaPelihahmonTiedot();				//default: Power, poliisi, 3, nopeus voimakkuus, mies, 58, kivääri, 0.5, 5
	  pelaaja4.naytaPelihahmonTiedot();				//default: Fauni, ampuja, 4, tarkkuus nopeus, nainen, 19, jousi, 0.5, 5
	  
	  //tulostetaan Ase -olioiden tiedot	
	  System.out.println("\nTestaus, Aseet:");	
	  pelaaja1.naytaPelihahmonAseenTiedot();		//default: ässä, kivääri, 0.8, 8
	  pelaaja2.naytaPelihahmonAseenTiedot();		//default: Peto, jousi, 0.3, 3
	  pelaaja3.naytaPelihahmonAseenTiedot();		//default: Power, kivääri, 0.5, 5
	  pelaaja4.naytaPelihahmonAseenTiedot();		//default: Fauni, jousi, 0.5, 5
   }
} 