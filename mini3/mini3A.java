/******************************************************
Nimi: Miniprojekti 3 A
Tekijä: Maarit Parkkonen
Pvm: 27.6.2018
Kuvaus: Tulostaa käyttäjän valinnan mukaisesti
        viikonpäivän nimen suomeksi, englanniksi
		tai ruotsiksi
		- lukee päivien nimet kielikohtaisista
		  ini-tiedostoista
*******************************************************/

//luokkakirjastot
import java.io.*; 			//mm. BufferedReader, File, FileReader, IOException
import java.util.*; 		//mm. Scanner
import java.lang.String;
//
public class mini3A{
	
   public static final int KIELET=3;				//kielien lukumäärä
   public static final int PVM=7;					//viikonpäivien lukumäärä

	
   //pääohjelma
   public static void main(String [ ] args) {
    
	//kielikohtainen viikonpäivien nimimatriisi
	String [][] paivat;								//esittely
	paivat=new String[KIELET][PVM];					//luonti			
	
	//kielikohtaiset ini-tiedostot muuttujiin
	File tiedosto1 = new File("Finnish.ini");		//suomi
	File tiedosto2 = new File("English.ini");		//englanti
	File tiedosto3 = new File("Swedish.ini");		//ruotsi
	
	File [] tiedostot = {tiedosto1,tiedosto2,tiedosto3};			//tiedostotaulukko
	String [] kielella ={"suomeksi","englanniksi","ruotsiksi"};		//kielten nimet tulostusta varten
	
	String rivi;									//tiedostosta luettava rivi
	int kieli,paiva;								//käsiteltävänä olevan kielen/viikonpäivän numero (matriisin rivi/sarakenumerot)
	byte kieliValinta=0, paivaValinta=0;			//käyttäjän valinnat

	//syötteen lukuolio
	Scanner lukija;									//esittely
	lukija=new Scanner(System.in);					//luonti
	
	//puskuriluokan olio rivien lukua varten
	BufferedReader reader = null;					//esittely ja tyhjäksi alustus

	try {		
		//luetaan viikonpäivien nimet matriisiin
		for (kieli=0;kieli<KIELET;kieli++){									//yksi kielitiedosto kerrallaan 
			reader = new BufferedReader(new FileReader(tiedostot[kieli]));	//luodaan tiedoston luku- ja puskurioliot
			paiva=0;
			while ((rivi = reader.readLine()) != null) {					//luetaan tiedosto rivi kerrallaan 
				if (paiva<PVM)												//estetään matriisin "ylivuotaminen"
					paivat[kieli][paiva]=rivi;								//talletetaan luettu rivi matriisiin
				paiva++;
			}
		}
		
		//testausta varten:
		//for (kieli=0;kieli<KIELET;kieli++)
		//	for (paiva=0;paiva<PVM;paiva++)
		//		System.out.println(paivat[kieli][paiva]);	
		
		//kysytään käyttäjän valinnat
		System.out.println("Ohjelma tulostaa haluamasi viikonpäivän nimen valitsemallasi kielellä:");
		do{ 																//vastauksen kontrollointi
			System.out.print("Valitse kieli (1=Suomi,2=Englanti,3=Ruotsi): ");
			kieliValinta=lukija.nextByte();
			lukija.nextLine();			
		}while ((vastausOk(kieliValinta,1,KIELET))!=true); 					//metodi: vastausOK tarkistaa vastauksen oikeellisuuden					
			
		do{ 																//vastauksen kontrollointi
			System.out.print("Valitse viikonpäivä (1 = maanantai, 2 = tiistai, ..., 7 = sunnuntai): ");
			paivaValinta=lukija.nextByte();
			lukija.nextLine();			
		}while ((vastausOk(paivaValinta,1,PVM))!=true); 					//metodi: vastausOK tarkistaa vastauksen oikeellisuuden	
		
		//näytölle tulostus
		kieliValinta--;														//taulukon indeksiksi
		System.out.println(paivaValinta+". viikonpäivä on "+kielella[kieliValinta]+": "+paivat[kieliValinta][paivaValinta-1]);


	} 
	//virheiden pyydystys
	catch (IOException e) {													//tiedoston luku virheet
		System.out.print("Virhe tiedoston luvussa. Lopetetaan.");
		System.exit(1);
	}
	catch (InputMismatchException e){										//käyttäjän antamien syötteiden tietotyyppivirheet
		  System.out.print("Virheellinen syöte. Lopetetaan.");
		  System.exit(1);
	}
	catch (Exception e) {													//muut virheet
		  System.out.print("Yllättävä virhe. Lopetetaan.");
		  System.exit(1);
	}
   }
   
   //käyttäjän antamien vastausten oikeellisuuden tarkistus
   //- palauttaa true = vastaus ok, false = vastaus virheellinen
   public static boolean vastausOk(int vastaus, int min, int max){
	   boolean ok=true;
	   //jos vastaus virheellinen
	   if ((vastaus<min)||(vastaus>max)){
			ok=false;
			System.out.println("Virheellinen valinta. Yritä uudestaan.");			
	   }
	   return ok;
   }
} 