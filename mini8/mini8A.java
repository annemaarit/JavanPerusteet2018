/*******************************************************************
Nimi:	miniprojekti 8 A
Tekijä:	Maarit Parkkonen
Pvm:	10.7.2018
Kuvaus: onkoAnagrammi -funktio: 
		- tarkastaa onko kaksi sen parametrinaan saamat merkkijonot
		  anagrammeja keskenään.
		- tyhjiä välejä ei huomioida merkkeinä (wikipedia: Anagrammilla 
		  tarkoitetaan sanan tai sanajoukon kirjainten uudelleen 
		  ryhmittelyä siten, että niistä muodostetaan yksi tai useampi 
		  uusi sana.)
		poistaValit -funktio:
		- poistaa merkkijonosta tyhjät välit (\t\n\r) ja palauttaa
		  "puhdistetun" merkkijonon
		
		testaaMerkkijonot -funktio:
		- tulostaa testituloksen.
		
		Pääohjelma testaa testifunktion kautta anagrammifunktion
		toimintaa.
		
		(ä=\u00E4 ö=\u00F6)
********************************************************************/

//luokkakirjasto
import java.lang.String;
import java.util.StringTokenizer;

//
public class  mini8A{
	
	//pääohjelma
	public static void main(String [ ] args) {
	  try{											
		testaaMerkkijonot("kissa","koira");			//default: kissa ja koira eivät ole anagrammeja.
		testaaMerkkijonot("koira","rikao");			//default: koira ja rikao ovat anagrammeja.
		testaaMerkkijonot("saaressa","sessaraa");	//default: saaressa ja sessaraa ovat anagrammeja.
		testaaMerkkijonot("saaressa","sesaraa");	//default: saaressa ja sesaraa eivät ole anagrammeja.
		testaaMerkkijonot("kilpikonna voitti pikajuoksun","kotisivujani lipputanko nokki");	//default: kilpikonna voitti pikajuoksun ja kotisivujani lipputanko nokki ovat anagrammeja.
		testaaMerkkijonot("ei voi olla totta","veloittaa oliot");		//default: ei voi olla totta ja veloittaa oliot ovat anagrammeja.
		testaaMerkkijonot("ei voi olla totta","kassa veloittaa oliot");	//default: ei voi olla totta ja kassa veloittaa oliot eivät ole anagrammeja.
		testaaMerkkijonot("Ei voi olla totta","Tievalot olioita");		//default: Ei voi olla totta ja Tievalot olioita eivät ole anagrammeja.
		testaaMerkkijonot("Ei voi olla totta","Tievalot oliota");		//default: Ei voi olla totta ja Tievalot oliota ovat anagrammeja.
	  }
	  catch(IndexOutOfBoundsException e){ 			 //napataan virheitä
	  	  System.out.print("Merkkitaulukon indeksin ylitys. Lopetetaan.");
		  System.exit(1);
	  }
	  catch (Exception e) {
		  System.out.print("Yllättävä virhe. Lopetetaan.");
		  System.exit(1);
	  }
	}
	
	//testifunktio, joka kutsuu saamillaan parametreilla anagrammifunktiota
	//ja tulostaa anagrammifunktion palautuksen mukaisen tekstin näytölle
	public static void testaaMerkkijonot(String j1, String j2){
		if (onkoAnagrammi(j1,j2))
			System.out.println("\""+j1+"\" ja \""+j2+"\" ovat anagrammeja.");
		else
			System.out.println("\""+j1+"\" ja \""+j2+"\" eiv\u00E4t ole anagrammeja.");	
	}
	
	//testaa onko parametreina saadut merkkijonot toistensa anagrammeja
	// - välilyöntejä ei huomioida merkkeinä
	// - palauttaa true=ovat anagrammeja, false=eivät ole anagrammeja
	public static Boolean onkoAnagrammi(String j1, String j2){
		Boolean loytyi=true;								//ilmoittaa merkin löytymisestä
		int i=0,j=0;										//taulukoiden läpikäynti indeksit
		String jono1=poistaValit(j1);						//poistetaan tyhjät välit merkkijoukkojen välistä
		String jono2=poistaValit(j2);
		int pituus1=jono1.length();							//merkkijonojen pituudet
		int pituus2=jono2.length();
	
		if (pituus1==pituus2){ 								//jos merkkejä on sama määrä
		  char[] mjono1=jono1.toLowerCase().toCharArray();	//merkkijono pikkukirjaimiksi ja muunnos merkkitaulukoksi
		  char[] mjono2=jono2.toLowerCase().toCharArray();		 
		  while (i<pituus1){								//merkkitaulukon 1 läpikäynti
			if (mjono1[i]!=' ')								//ohittaa taulukon 1 välilyönnit
				loytyi=false;
			j=0;
			while ((loytyi==false)&&(j<pituus2)){ 			//merkkiä ei ole löytynyt ja taulukkoa 2 indeksejä on jäljellä
				if (mjono1[i]==mjono2[j]){		  			//jos merkit samat
					loytyi=true;				  
					mjono2[j]=' ';			  	  			//korvataan valilyonnilla
				}
				else
					j++;			//seuraava taulukon 2 indeksi
			}
			if (loytyi)				//merkki löytyi
				i++;				//seuraava taulukon 1 indeksi
			else					//kirjainta ei löytynyt
				return false;		//eivät ole anagrammeja
		  }
		  return true;				//kaikki yksittäiset kirjaimet löytyivät, ovat anagrammeja
		}				
		else
			return false;			//merkkejä on eri määrä, eivät voi olla anagrammeja
	}
	
	//poistaa tyhjat valit parametrina saadusta merkkijonosta
	//- palauttaa merkkijonon ilman tyhjiä välejä
	public static String poistaValit(String jono){
		String apujono="";										
		StringTokenizer pilkottava = new StringTokenizer(jono); //jonon purkava olio, erottimina \t\n\r
		while (pilkottava.hasMoreTokens()){						//onko merkkijoukkoja jäljellä
			String pala=pilkottava.nextToken();					//merkkijoukko
			apujono=apujono.concat(pala);						//liitetään joukko suoraan edellisten perään
		}			
		return apujono;
	}
   
} 