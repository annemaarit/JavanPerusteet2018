/*********************************************
Nimi:	mini10A
Tekijä:	Maarit Parkkonen
Pvm:	25.7.2018
Kuvaus:	Luokat:
		cdLevy
		- attribuutit: nimi, artisti, vuosi
		- metodit: kysyTiedot, tulostaTiedot,
		  annaLevynimi
		Levykokoelma-luokka
		- attribuutit: levyt vektori(ArrayList)
		- metodit: lisaaLevy, poistaLevy, annaLevy,
		  tulostaLevyt,
		
		Pääohjelma testaa luokkien toimintaa.
		
		(ä=\u00E4 ö=\u00F6)
*********************************************/

//luokkakirjasto
import java.util.*;

class cdLevy{
	private String nimi;
	private String artisti;	
	private int vuosi;

	//konstrukstori, jonka kautta kysytään käyttäjältä tiedot
	cdLevy(){
		kysyTiedot();
	}
	
	//konstruktori
	cdLevy(String name, String esiintyja, int julkaistu){
		nimi=name;
		artisti=esiintyja;
		vuosi=julkaistu;
	}
	
	//kysyy käyttäjältä levyn tiedot
	private void kysyTiedot(){
		Boolean ok=false;
		Scanner syotelukija=new Scanner(System.in);				//syötteen lukuolion luonti		
		System.out.println("**UUSI LEVY, anna tiedot*************");	
		System.out.print("Nimi:");								//levyn nimi
		nimi=syotelukija.nextLine();
		System.out.print("Artisti:");							//artistin nimi
		artisti=syotelukija.nextLine();			
		do{
		  try{
			System.out.print("Vuosi:");							//julkaisu vuosi
			vuosi=syotelukija.nextInt();
			ok=true;
		  }
		  catch (InputMismatchException e){						//syötetty väärä tietotyyppi poikkeustilanne
			System.out.println("Sy\u00F6tith\u00E4n vuosiluvun numeroina?");	
			syotelukija=new Scanner(System.in);		    		//uusi syötteen lukuolio (koska try -lohko sulki edellisen)
		  }
		}while(ok==false);										//toistetaan, kunnes vuosi on oikeaa tietotyyppiä
		System.out.println();	
	}
	
	//tulostaa levyn tiedot
	public void tulostaTiedot(){
		System.out.println("Levyn nimi:\t"+nimi);
		System.out.println("Artisti:\t"+artisti);		
		System.out.println("Vuosi:\t\t"+vuosi+"\n");
	}
	
	//palauttaa levyn nimen
	public String annaNimi(){
		return nimi;
	}
}

class Levykokoelma{
	private Vector<cdLevy> levyt;			//vektorirakenne levyolioiden säilytykseen
	
	//konstruktori
	Levykokoelma(){
		levyt=new Vector<cdLevy>();	
	}
	
	//lisää levyn kokoelmaan
	public void lisaaLevy(cdLevy cd){
		levyt.add(cd);
		System.out.println("Levy \""+cd.annaNimi()+"\" on lis\u00E4tty kokoelmaan.\n");
	}
	
	//poistaa levyn kokoelmasta
	public void poistaLevy(cdLevy cd){
		System.out.println("**LEVYN POISTO*************************");
		if (levyt.isEmpty())										//jos kokoelma on tyhjä
			System.out.println("Kokoelma on tyhj\u00E4. Ei poistettavia levyj\u00E4.\n");
		else	
			if (levyt.indexOf(cd)==-1)								//jos levyä ei löydy
				System.out.println("Levy\u00E4: \""+cd.annaNimi()+"\" ei ole kokoelmassa. Levy\u00E4 ei voi poistaa.\n");
			else{													//levy löytyi
				levyt.remove(cd);									//levyn poisto
				System.out.println("Levy \""+cd.annaNimi()+"\" on poistettu kokoelmasta.\n");
			}			
	}
	
	//palauttaa pyydetyn levyolion kokoelmasta
	//- jos annettua indeksiä ei ole, palauttaa null
	public cdLevy annaLevy(int index){
			int maxIndex=levyt.size()-1;							//vektorin maksimi indeksi
			if ((index>=0)&&(index<=maxIndex))						//jos parametri on vektorin indeksialueella
				return levyt.get(index);							//palautetaan levy-olio
			else{													//indeksiä ei ole vektorin indeksialueella
				System.out.println("**LIIAN SUURI INDEKSI*************************");
				System.out.println("Ei levy\u00E4 indeksiss\u00E4: "+index+"\n");
				return null;
			}
	}
	
	//tyhjentää kokoelman
	public void poistaKaikkiLevyt(){
		System.out.println("**LEVYKOKOELMA*************************");
		levyt.clear();		
		System.out.println("Levykokoelma on tyhjennetty.");	
	}
	
	//tulostaa kaikkien kokoelman levyjen tiedot
	public void tulostaLevyt(){
		if (levyt.isEmpty())										//jos kokoelma on tyhjä
			System.out.println("Levykokoelma on tyhj\u00E4.\n");	
		else{
			cdLevy cd;
			Iterator<cdLevy> i=levyt.iterator();					//askeltaja					
			System.out.println("**LEVYKOKOELMA*************************");
			while (i.hasNext()){									//käydään läpi kaikki kokoelman levyt
				cd=i.next();
				cd.tulostaTiedot();									//kutsutaan levyolion tietojen tulostusta
			}
		}
	}
}

//
public class mini10A{
   //pääohjelma
   public static void main(String [ ] args) {
	  cdLevy cd=null;												//muuttuja yksittäiselle levyoliolle		
	
	  try{
		Levykokoelma omatlevyt=new Levykokoelma();					//luodaan rakenne levykokoelmalle
		if (kaytetaankoTestiaineistoa()==true){						//testiaineisto valittu, luodaan testilevyt		
			System.out.println("**TESTAUS: 4 levy\u00E4*************************");	
				
			cd=new cdLevy("Lassi Laulaja","Laulajan vuodet",2011); 	//default: Levy "Lassi Laulaja" on lisätty kokoelmaan.
			omatlevyt.lisaaLevy(cd);
			cd=new cdLevy("Abba Oy","Hur mår du idag?",1986);		//default: Levy "Abba Oy" on lisätty kokoelmaan.
			omatlevyt.lisaaLevy(cd);
			cd=new cdLevy("Kolinaa","Mustaakin mustempi",2016);		//default: Levy "Kolinaa" on lisätty kokoelmaan.
			omatlevyt.lisaaLevy(cd);
			cd=new cdLevy("Pikkuoravat","Nyt hypitään",2004);		//default: Levy "Pikkuoravat" on lisätty kokoelmaan.
			omatlevyt.lisaaLevy(cd);
	  
			//tulostetaan kokoelman sisältö
			omatlevyt.tulostaLevyt();					//default: tulostaa näkyviin kaikkien neljän levyn tiedot
	  
			//ensimmäisen levyn poistaminen
			cd=omatlevyt.annaLevy(0);					//testiaineistossa levy Lassi Laulaja
			omatlevyt.poistaLevy(cd);					//testiaineisto default: Levy "Lassi Laulaja" on poistettu kokoelmasta.
			omatlevyt.tulostaLevyt();					//testiaineisto default: Tulostuu levyjen Abba Oy, Kolinaa ja Pikkuoravat tiedot
	  
			//yritetään poistaa jo poistettua levyä
			omatlevyt.poistaLevy(cd);					//testiaineisto default: Levyä: "Lassi Laulaja" ei ole kokoelmassa. Levyä ei voi poistaa.
			
			//yritetään hakea levyä indeksillä, jota ei ole
			cd=omatlevyt.annaLevy(12);					//testiaineistossa levy Lassi Laulaja	  
	  
			//poistetaan viimeinen levy
			cd=omatlevyt.annaLevy(2);					//testiaineistossa levy Pikkuoravat
			omatlevyt.poistaLevy(cd);	 				//testiaineisto default: Levy "Pikkuoravat" on poistettu kokoelmasta.
			omatlevyt.tulostaLevyt();					//testiaineisto default: Tulostuu levyjen Abba Oy ja Kolinaa tiedot
	  
			//otetaan yksi levy talteen, tyhjennetään kokoelma
			cd=omatlevyt.annaLevy(1);					//testiaineistossa levy Kolinaa
			omatlevyt.poistaKaikkiLevyt();				//testiaineisto default: Levykokoelma on tyhjennetty.
			omatlevyt.tulostaLevyt();					//testiaineisto default: Levykokoelma on tyhjä.
	  
			//yritetään poistaa levy tyhjästä kokoelmasta
			omatlevyt.poistaLevy(cd);					//testiaineisto default: Kokoelma on tyhjä. Ei poistettavia levyjä.
	  
			//tarkistetaan levyn tiedot
			cd.tulostaTiedot();							//testiaineisto default: Kolinaa -levyn tiedot
		}
		else
			syotaLevyt(omatlevyt);
			//tulostetaan kokoelman sisältö
			omatlevyt.tulostaLevyt();					//default: tulostaa syötettyjen levyjen tiedot	
	  }
	  catch (ArrayIndexOutOfBoundsException ex)	{	//indeksivirhe
		System.out.println("Viittauksessa ylitetty taulukon indeksointi. Ohjelma lopetetaan.");
		System.exit(1);
	  }
	  catch (Exception e) {							//muu poikkeustilanne
		System.out.print("Yll\u00E4tt\u00E4v\u00E4 virhe. Lopetetaan.");
		System.exit(1);
	  }
   }
   
   //palauttaa tiedon käyttäjän valinnasta käytetäänkö testiaineistoa
   //- true = kyllä, false = ei
   public static Boolean kaytetaankoTestiaineistoa(){
	  int vastaus=0;										//käyttäjän vastaus
	  Boolean ok=false;
	  Scanner lukija=new Scanner(System.in);				//syötteen lukuolion luonti	
	  do{
		try{
			do{			
				System.out.println("Valitse k\u00E4yt\u00E4tk\u00F6 testiaineistoa: 1 vai annatko tiedot itse: 2");
				vastaus=lukija.nextInt();
			}while (!vastausOk(vastaus));					//toistetaan, kunnes käyttäjän vastaus on ok
			ok=true;
		}
		catch (InputMismatchException e){					//tyyppipoikkeama
			System.out.println("Vastauksesi on virheellinen.");	
			lukija=new Scanner(System.in);		    		//uusi syötteen lukuolio (koska try -lohko sulki edellisen)
		}
	  }while(ok!=true);	   
	  if (vastaus==1)
		  return true;
	  else
		  return false;
   }
   
	//tarkistaa onko käyttäjän antama kokonaisluku ok
	//- palauttaa true, jos on
	//- palauttaa false, jos ei ole ja antaa ilmoituksen
	public static Boolean vastausOk(int v){
			if ((v==1)||(v==2))
				return true;
			else{
				System.out.println("Vastauksesi on virheellinen.");
				return false;
			}
	}
	
	public static void syotaLevyt(Levykokoelma kokoelma){
		cdLevy levy=null;
		Scanner lukija=new Scanner(System.in);					//syötteen lukuolion luonti	
		int vastaus=0;
		do{
			try{
				do{
					levy=new cdLevy();	
					kokoelma.lisaaLevy(levy);			
					System.out.println("Haluatko antaa lis\u00E4\u00E4 levytietoja? 1 kyll\u00E4, 2 en");
					vastaus=lukija.nextInt();
				}while (!vastausOk(vastaus));					//toistetaan, kunnes käyttäjän vastaus on ok
			}
			catch (InputMismatchException e){					//tyyppipoikkeama
				System.out.println("Vastauksesi on virheellinen.");	
				lukija=new Scanner(System.in);		    		//uusi syötteen lukuolio (koska try -lohko sulki edellisen)
			}
		}while(vastaus!=2);	
	}

} 

