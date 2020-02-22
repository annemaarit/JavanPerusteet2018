/*********************************************************************
Nimi:	miniprojekti 9 C 2
Tekijä:	Maarit Parkkonen
Pvm:	2.8.2018
Kuvaus:	Ohjelma pyytää ensin uusia asiakastietoja, uudet tiedot voi
		syöttää itse tai antaa ohjelman luoda testiasiakkaita. Ohjelma 
		tallentaa asiakkaan tiedot BINÄÄRItiedostoon. Jos tiedosto on
		olemassa, tiedot kirjoitetaan sen jatkoksi. Ohjelma tulostaa 
		kaikkien asiakkaiden tiedot ja lopuksi kysyy etsittävän asiakkaan
		ID -tunnusta sekä tulostaa löydetyt tiedot.
		- asiakastunnus muodostetaan ohjelmallisesti

		Luokat:
		OmaObjectOutputStream extends ObjectOutputStream
		- metodit: konstruktori, @Override writeStreamHeader
		Asiakastiedosto
		- atribuutit: nimi, kpl
		- metodit: konstruktori, seuraavaID, tallennaUusiAsiakas,
		  etsiAsiakas, tulostaAsiakkaat
		Asiakas
		- atribuutit: ID, nimi, email ja puhnro
		- metodit: konstruktori, konstruktori testaukseen, annaID, kysyTiedot,
		  tulostaTiedot
		
		Funktiot:
		- vastausOk, luoTestiAsiakkaita, arvoIndeksi
		
		(ä=\u00E4 ö=\u00F6)
***********************************************************************/

//luokkakirjastot
import java.io.*;
import java.util.*;

class OmaObjectOutputStream extends ObjectOutputStream {

  // Konstruktori, välittää tiedot ObjectOutputStream-luokan konstruktorille.
  public OmaObjectOutputStream(OutputStream out) throws IOException
  {
    super(out);
  }

  // Ylikirjoitetaan writeStreamHeader-metodi
  // - mahdollistaa tiedon lisäämisen olemassa olevan binääritiedoston loppuun ilman headeriä
  @Override protected void writeStreamHeader() throws IOException {
    // Ei tehdä mitään.
  }
}

//Asiakastiedosto -luokka
class Asiakastiedosto implements Serializable{
	private String nimi;				//asiakastiedoston nimi ja sijainti
	private int kpl;					//asiakkaiden määrä tiedostossa
	
	//konstruktori
	Asiakastiedosto(String tdNimi){
		nimi=tdNimi;					//tiedoston nimi ja sijainti
		kpl=laskeAsiakkaat();			//asiakkaiden määrä
	}

	
	//laskee ja palauttaa asiakkaiden määrän tiedostosta
	private int laskeAsiakkaat(){
		File asiakastiedosto = new File(nimi);											//luodaan tiedosto -olio
		int asiakasKpl=0;																//asiakaslaskuri
		Asiakas as=null;
		ObjectInputStream olionLukija=null;
		if (asiakastiedosto.exists()){													//jos tiedosto on olemassa
		    try {
				olionLukija=new ObjectInputStream(new FileInputStream(nimi));	//luodaan uusi puskuroitu tiedoston lukuolio
				while (true) {															//toistetaan kunnes tiedoston lukuvirhe (tiedosto tyhjä)
					as=(Asiakas)olionLukija.readObject();								//yksittäinen asiakas
					asiakasKpl++;														//kasvatetaan asiakaslaskuria						
				}
				//olionLukija.close();													//suljetaan tietovirta	
			}
			catch (EOFException eof){													//tiedosto loppui poikkeustilanne
				//System.out.println("Kpl on "+asiakasKpl);

			}
			catch (IOException | ClassNotFoundException ex) {							//tiedoston lukuvirhe tai olion luokka puuttuu
				System.out.println("Virhe tiedostosta lukemisess asiakkaiden laskemissa. Ohjelma lopetetaan.");
				System.exit(1);
			}
			try {
				olionLukija.close();
			} 
			catch(IOException ex) {													
				System.out.println("Virhe tiedoston sulkemisessa asiakkaiden laskemisen jälkeen. Ohjelma lopetetaan.");
				System.exit(1);	
			}	
		}			
		return asiakasKpl;																//palautetaan asiakkaiden lukumäärä
	}
	
	//palauttaa seuraavana vuorossa olevan asiakasID:n
	public int seuraavaID(){
		return kpl+1;
	}
	
	//tallentaa uuden asiakkaan tiedot 
	public void tallennaUusiAsiakas(Asiakas as){
		File asiakastiedosto = new File(nimi);
		if (asiakastiedosto.exists())					//jos tiedosto on jo olemassa	
			try{
				OmaObjectOutputStream olionKirjoittaja=new OmaObjectOutputStream(new FileOutputStream(nimi,true)); //ei headeria
				olionKirjoittaja.writeObject(as);		//olion tiedostoon sarjoitus
				kpl++;
				olionKirjoittaja.close();
				//System.out.println("Uusi asiakas nro: "+kpl+" lisätty");
			}
			catch (IOException ex){
				System.out.print("Asiakkaan sarjallistamisessa olemassa olevaan tiedostoon tapahtui virhe. Lopetetaan.");
				System.exit(1);			
			}	
		else											//tiedostoa ei ole olemassa
			try{
				ObjectOutputStream olionKirjoittaja=new ObjectOutputStream(new FileOutputStream(nimi));	//header tiedoston alkuun
				olionKirjoittaja.writeObject(as);		//olion tiedostoon sarjoitus
				kpl++;
				olionKirjoittaja.close();
				//System.out.println("Uusi asiakas nro: "+kpl+" lisätty uuteen tiedostoon");
			}
			catch (IOException ex){
				System.out.print("Asiakkaan sarjallistamisessa uuteen tiedostoon tapahtui virhe. Lopetetaan.");
				System.exit(1);			
			}			
	}

	//etsii ja tulostaa tiedostosta halutun asiakkaan tiedot
	//- parametri id yksilöi etsityn asiakkaan
	public void etsiAsiakas(int id){
		File asiakastiedosto = new File(nimi);					//luodaan tiedosto -olio
		Asiakas as=null;
		if (asiakastiedosto.exists()){							//jos tiedosto on olemassa	
			try{
				ObjectInputStream olionLukija=new ObjectInputStream(new FileInputStream(nimi)); //luodaan uusi puskuroitu tiedostosta lukuolio
				int i=1;
				Boolean loytyi=false;
				while ((i<=kpl)&&(loytyi==false)){				//tiedoston olioiden läpikäyntiä ellei oliota löydy
					as=(Asiakas)olionLukija.readObject();		//olion lukutiedostosta
					if (id==as.annaID()){						//jos asiakastunnus on sama
						as.tulostaTiedot();						//pyydetään tietojen tulostamista
						loytyi=true;
					}
					as=null;
					i++;
				}
				if (loytyi==false)														//jos asiakasta ei löytynyt
					System.out.println("\nEi asiakastietoja ID:ll\u00E4 "+id);				
				olionLukija.close();
			}				
			catch (IOException | ClassNotFoundException ex){	//tiedoston lukuvirhe tai olion luokka puuttuu
				System.out.print("Asiakkaiden tulostamisessa tiedostosta tapahtui virhe. Lopetetaan.");
				System.exit(1);			
			}				
		}	
		else
			System.out.println("Ei asiakkaita.");	
	}
	
	//tulostaa kaikkien asiakkaiden tiedot tiedostosta
	public void tulostaAsiakkaat(){
		File asiakastiedosto = new File(nimi);				//luodaan tiedosto -olio
		Asiakas as=null;
		if (asiakastiedosto.exists()){						//jos tiedosto on olemassa	
			try{
				ObjectInputStream olionLukija=new ObjectInputStream(new FileInputStream(nimi));	//luodaan uusi puskuroitu tiedostosta lukuolio
				for (int i=1;i<=kpl;i++){					//tiedoston olioiden läpikäynti
					as=(Asiakas)olionLukija.readObject();	//yksittäinen asiakas
					as.tulostaTiedot(); 					//pyydetään asiakastietojen tulostamista
					as=null;
				}
				olionLukija.close();						//suljetaan lukuolio
			}				
			catch (IOException | ClassNotFoundException ex){//tiedoston lukuvirhe tai olion luokka puuttuu
				System.out.print("Asiakkaiden tulostamisessa tiedostosta tapahtui virhe. Lopetetaan.");
				System.exit(1);			
			}				
		}	
		else
			System.out.println("Ei asiakkaita.");			
	}

}

//Asiakas -luokka
class Asiakas implements Serializable{
	private int ID;				//asiakasnumero, luodaan ohjelmallisesti
	private String nimi;		
	private String email;
	private int puhnro;
	
	//konstruktori
	Asiakas(Asiakastiedosto tiedosto){
		ID=tiedosto.seuraavaID();								//asiakastiedostossa oleva seuraava vapaa id
		kysyTiedot();											//asiakastietojen kysely
	}
	
	//konstruktori testausta varten
	Asiakas(Asiakastiedosto tiedosto, String name, String sposti, int puh){
		ID=tiedosto.seuraavaID();								//asiakastiedostossa oleva seuraava vapaa id
		nimi=name;
		email=sposti;
		puhnro=puh;
	}
	
	//palauttaa asiakastunnuksen
	public int annaID(){
		return ID;
	}
	
	//kysyy asiakkaan tiedot (nimi, email ja puhelinnumero) käyttäjältä
	//ja sijoittaa ne olion vastaaviin attribuutteihin
	private void kysyTiedot(){
		Boolean ok=false;
		Scanner syotelukija=new Scanner(System.in);				//syötteen lukuolion luonti		
		System.out.print("**Uusi Asiakas************************\n");	
		System.out.print("Nimi:");								//nimi
		nimi=syotelukija.nextLine();
		System.out.print("Email:");								//sähköposti
		email=syotelukija.nextLine();	
		do{														//puhelinnumero
			try{
				System.out.print("Puh:");	
				puhnro=syotelukija.nextInt();			
				if ((puhnro>0)&&(puhnro<=2147483647))			//numeron tarkistus
					ok=true;
				else
					System.out.println("Virheellinen puhelinnumero. Kirjoitithan numeroita?");
			}
			catch (InputMismatchException e){					//syötetty väärä tietotyyppi poikkeustilanne
				System.out.println("Virheellinen puhelinnumero. Kirjoitithan numeroita?");
				syotelukija= new Scanner(System.in);			//uusi syötteen lukuolio (koska try -lohko sulki edellisen)
			}
		} while (ok==false);									//toistetaan, kunnes puhelinnumero on ok	
	}
	
	//tulostaa asiakkaan tiedot näytölle
	public void tulostaTiedot(){
		System.out.println("Asiakkaan "+ID+" tiedot");							  
		System.out.println("Nimi:\t"+nimi);										  
		System.out.println("Email:\t"+email);									   
		System.out.println("Puh:\t"+puhnro+"\n");								  	
	}
}


public class mini9C2 {
	
	public static final String ASIAKASTIEDOSTO="asiakastiedotC2.dat";	//asiakastiedoston nimi ja sijainti
	public static final int TESTIMAX=8;									//testiaineiston vakiotaulukoiden koko
	
	//pääohjelma
	public static void main(String [ ] args) {	
		int vastaus=1;													//käyttäjän antama vastaus
		Scanner lukija=new Scanner(System.in);							//syötteen lukuolion luonti	

		Asiakastiedosto asiakkaat=new Asiakastiedosto(ASIAKASTIEDOSTO); //asiakastiedosto -olio
		
		System.out.println("\nANNA UUSIA ASIAKASTIETOJA**********************************************");
		try{
		  do{
			do{
				System.out.print("K\u00E4ytet\u00E4\u00E4nk\u00F6 testiasiakkaita (kyll\u00E4: 1, ei: -1) ");
				vastaus=lukija.nextInt();
			}while(!vastausOk(vastaus));
			if (vastaus!=1){									//käyttäjä syöttää itse tiedot
				Asiakas asiakas=new Asiakas(asiakkaat);			//uusi asiakas -olio
				asiakkaat.tallennaUusiAsiakas(asiakas);			//asiakastietojen tallennus binääritiedostoon
				asiakas=null;
			}
			else{												//käytetään testiaineistoa
				System.out.print("Kuinka monta testiasiakasta luon?:  ");
				vastaus=lukija.nextInt();
				luoTestiAsiakkaita(asiakkaat,vastaus);	 		//pyydetään testiasiakkaiden luomista
			}
			do{												
				System.out.print("Haluatko viel\u00E4 sy\u00F6tt\u00E4\u00E4 asiakkaita (kyll\u00E4: 1, ei: -1) ");
				vastaus=lukija.nextInt();
			}while(!vastausOk(vastaus));						//toistetaan, kunnes käyttäjän vastaus on ok				
		  }while (vastaus!=-1);									//toistetaan, kunnes käyttäjä haluaa lopettaa asiakkaiden luomisen
		
		  System.out.println("\nKAIKKI ASIAKKAAT, TULOSTE*******************************************");
		  asiakkaat.tulostaAsiakkaat();							//tulostetaan kaikki asiakastiedot tiedostosta
		  
		  System.out.println("\nETSI ASIAKAS********************************************************");
		  do{
			System.out.print("Kirjoita asiakkaan ID (luku): ");	
			vastaus=lukija.nextInt();
			asiakkaat.etsiAsiakas(vastaus);						//pyydetään asiakkaan tietojen etsimistä tiedostosta
			do{
				System.out.print("Etsit\u00E4\u00E4nk\u00F6 uudelleen (kyll\u00E4: 1, ei: -1) ");
				vastaus=lukija.nextInt();
			}while(!vastausOk(vastaus));						//toistetaan, kunnes käyttäjän vastaus on ok
		  }while (vastaus!=-1);									//toistetaan, kunnes käyttäjä haluaa lopettaa asiakkaiden etsimisen
		
		  System.out.println("\nOHJELMA LOPETETTU***************************************************");
		  //olionKirjoittaja.close();
		}
		catch(InputMismatchException e){						//syötetty väärä tietotyyppi poikkeustilanne
			System.out.println("K\u00E4ytt\u00E4j\u00E4 antoi vastaukseksi muun kuin numeron. Ohjelma lopetetaan.");
			System.exit(1);
		}
	    catch (Exception e) {									//muu poikkeustilanne
		  System.out.print("Yll\u00E4tt\u00E4v\u00E4 virhe. Lopetetaan.");
		  System.exit(1);
		}
	}	
	
	//tarkistaa onko käyttäjän antama kokonaisluku ok
	//- palauttaa true, jos on
	//- palauttaa false, jos ei ole ja antaa ilmoituksen
	public static Boolean vastausOk(int v){
			if ((v==1)||(v==-1))
				return true;
			else{
				System.out.println("Vastauksesi on virheellinen");
				return false;
			}
	}
	
	//generoi vakiotaulukoiden tietojen avulla satunnaisia testiasiakkaita kpl määrän verran td tiedostoon
	public static void luoTestiAsiakkaita(Asiakastiedosto td, int kpl){
			String [] etunimet = {"Roope","Hessu","Milla","Aku","Hannu","Kroisos","Pekka","Minni"};
			String [] sukunimet = {"Ankka","Hopo","Magia","Ankka","Hanhi","Pennonen","Hiiri","Musta"};
			String enimi, snimi, sposti;
			int [] puhnrot = {552312,412,9632,131,41111,61423,45662,124};
			for (int i=0;i<kpl;i++){											//toistetaan halutun määrän verran 
				enimi=etunimet[arvoIndeksi()];									//asiakastietojen luonti
				snimi=sukunimet[arvoIndeksi()];
				sposti=enimi+"."+snimi+"@ankkalinna.com";
				Asiakas testiasiakas=new Asiakas(td,(enimi+" "+snimi),sposti,puhnrot[arvoIndeksi()]);	//asiakkaan luonti
				td.tallennaUusiAsiakas(testiasiakas);							//asiakastietojen tallennus binääritiedostoon
			}
	}
	
	//palauttaa satunnaisen kokonaisluvun väliltä 0-(TESTIMAX-1)
	public static int arvoIndeksi(){
		Random arpoja=new Random();								//uusi satunnaisluku -olio
		return (arpoja.nextInt(TESTIMAX));						//satunnaisluku väliltä 0-(TESTIMAX-1)
	}
	
} 