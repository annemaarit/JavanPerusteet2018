/******************************************************************************************
Nimi:   miniprojekti 8 C
Tekijä:	Maarit Parkkonen
Pvm:    12.7.2018
Kuvaus: Surrealistinen lausegeneraattori. Generaattori arpoo satunnaisesti 
		sanajoukosta yhden sanan, jonka sijoittaa lauseenjäsenelle määriteltyyn paikkaan.

		Luokat 
        Sanajoukko
		- attribuutit: sanat[]
		- metodit: konstruktori, ArvoSana
		Lause
		- attribuutit: lause[]
		- metodit: konstruktori, tulostaLause
		
		Pääohjelma tuottaa lauseita olioiden kautta.
		(ä=\u00E4 ö=\u00F6)
********************************************************************************************/

//luokkakirjasto
import java.lang.String;
import java.util.Random;

//Sanajoukko -luokka
class Sanajoukko{
	private String [] sanat;		//sanataulukon esittely
	private int kpl;				//sanojen lukumäärä
	private int edIndex=-1;			//edellinen satunnaisluku indeksi, vähentää toistoa

	
	//konstruktori
	Sanajoukko(String [] s, int lkm){
		kpl=lkm;
		sanat=new String [kpl];		//uusi sanataulukko
		sanat=s;					//sanataulukko osoittamaan parametrina saatuun taulukkoon VAI
									//kopioituuko parametrina saatu taulukko????
	}
	
	//palauttaa sanat -taulukosta satunnaisesti valitun indeksin sisältämän sanan
	public String arvoSana(){
	   	Random arpoja=new Random();		//uusi satunnaisluku -olio
		int index;						//satunnaisluku indeksi
		do{
			index = arpoja.nextInt(kpl);//satunnaisluku väliltä 0-kpl
		} while(index==edIndex);		//toistetaan, kunnes arvottu luku on eri kuin edellisellä kerralla
		edIndex=index;					//luku talteen edelliseksi luvuksi
		return sanat[index];			//arvotun sanan palautus
	}

}

//Lause -luokka
class Lause{
	private String [] lause;		//lausetaulukon esittely
	
	//konstruktori
	Lause(Sanajoukko sj1, Sanajoukko sj2, Sanajoukko sj3, Sanajoukko sj4){
		lause=new String [5];		//uusi lausetaulukko
		lause[0]=sj1.arvoSana();	//sanojen arpominen taulukkoon, adjektiivi
		lause[0]=(lause[0].substring(0,1).toUpperCase())+(lause[0].substring(1,lause[0].length())); //iso alkukirjain
		lause[1]=sj2.arvoSana();	//subjekti
		lause[2]=sj3.arvoSana();	//predikaatti
		lause[3]=sj1.arvoSana()+"a";//adjektiivi
		lause[4]=sj4.arvoSana()+".";//objekti
	}
	
	//tulostaa kokonaisen lauseen näytölle
	public void tulostaLause(){
		for (int i=0;i<5;i++)		//käy läpi lausetaulukon
			System.out.print(lause[i]+" ");
		System.out.print("\n");
	}
}


public class  mini8C{
   public static final int KPL=10;	//sanojen lukumäärä
	
   //pääohjelma
   public static void main (String [ ] args) {
	  //adjektiivit
      String [] sanat1={"naurettava","sulava","hauska","inhottava","iso","kuuma","rauhaisa","vaahtoava","haiseva","ujo"};
	  //subjektit
	  String [] sanat2={"mummo","apina","Donald Trump","joulupukki","Helin\u00E4 Keiju","lumiukko","pyyhekumi","aurinko","hernekeitto","koppakuoriainen"};
	  //predikaatit
	  String [] sanat3={"yskii","laulaa","kutittaa","ui","ajattelee","sy\u00F6","leipoo","jokeltaa","kalastaa","ry\u00F6mii"};
	  //objektit
	  String [] sanat4={"kissaa","Putinia","humanoidia","isin autoa","Aku Ankkaa","haukea","saapasta","maitolasia","oliota","siivoojaa"};
		
	  //luodaan sanajoukko -oliot
	  Sanajoukko adjektiivit=new Sanajoukko(sanat1,KPL);
	  Sanajoukko subjektit=new Sanajoukko(sanat2,KPL);
	  Sanajoukko predikaatit=new Sanajoukko(sanat3,KPL);
	  Sanajoukko objektit=new Sanajoukko(sanat4,KPL);
	  
	  //luodaan lause -oliot
	  Lause lause1=new Lause(adjektiivit,subjektit,predikaatit,objektit);
	  Lause lause2=new Lause(adjektiivit,subjektit,predikaatit,objektit);
	  Lause lause3=new Lause(adjektiivit,subjektit,predikaatit,objektit);
	  Lause lause4=new Lause(adjektiivit,subjektit,predikaatit,objektit);
	  Lause lause5=new Lause(adjektiivit,subjektit,predikaatit,objektit);
	  Lause lause6=new Lause(adjektiivit,subjektit,predikaatit,objektit);
	  
	  //tulostetaan lauseet
	  lause1.tulostaLause();
	  lause2.tulostaLause();
	  lause3.tulostaLause();
	  lause4.tulostaLause();
	  lause5.tulostaLause();
	  lause6.tulostaLause();	 
	}
} 