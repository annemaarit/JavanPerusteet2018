/*********************************************************************
Nimi:	miniprojekti 9 B
Tekijä:	Maarit Parkkonen
Pvm:	21.7.2018
Kuvaus:	Ohjelma pyytää ensin uusia asiakastietoja, uudet tiedot voi
		syöttää itse tai antaa ohjelman luoda testiasiakkaita. Ohjelma 
		tallentaa asiakkaan tiedot TEKSTItiedostoon. Ohjelma tulostaa 
		kaikkien asiakkaiden tiedot ja lopuksi kysyy etsittävän asiakkaan
		ID -tunnusta sekä tulostaa löydetyt tiedot.
		- jos tekstitiedosto on jo olemassa, ohjelma säilyttää
		  sen tiedot ja lisää uudet tiedot tiedostoon entisten perään
		- asiakastunnus muodostetaan ohjelmallisesti

		Luokat:
		Asiakastiedosto
		- atribuutit: nimi, kpl
		- metodit: konstruktori, seuraavaID, tallennaUusiAsiakas,
		  laskeAsiakkaat, etsiAsiakas, tulostaAsiakkaat
		Asiakas
		- atribuutit: ID, nimi, email ja puhnro
		- metodit: konstruktori, konstruktori testaukseen, kysyTiedot,
		  tulostaTiedot
		
		Funktiot:
		- vastausOk, luoTestiAsiakkaita, arvoIndeksi
		
		(ä=\u00E4 ö=\u00F6)
***********************************************************************/

//luokkakirjastot
import java.io.*;
import java.util.*;

//Asiakastiedosto -luokka
class Asiakastiedosto{
	private String nimi;				//asiakastiedoston nimi ja sijainti
	private int kpl;					//asiakkaiden määrä tiedostossa
	
	//konstruktori
	Asiakastiedosto(String tdNimi){
		nimi=tdNimi;					//tiedoston nimi ja sijainti
		kpl=laskeAsiakkaat();			//pyytää asiakkaiden määrän laskemista tiedostosta
	}
	
	//palauttaa seuraavana vuorossa olevan asiakasID:n
	public int seuraavaID(){
		return kpl+1;
	}
	
	//tallentaa uuden asiakkaan tiedot tekstitiedostoon
	public void tallennaUusiAsiakas(int id,String name,String meili,int puhelin){
		try {
			BufferedWriter txtKirjoittaja = new BufferedWriter(new FileWriter(nimi,true)); //luodaan uusi puskuroitu tiedostoon kirjoitus -olio
			txtKirjoittaja.write("Asiakkaan #"+id+" tiedot");							   //ID tiedostoon
			txtKirjoittaja.newLine();
			txtKirjoittaja.write("Nimi:\t"+name);										   //Nimi tiedostoon
			txtKirjoittaja.newLine();
			txtKirjoittaja.write("Email:\t"+meili);										   //sähköposti tiedostoon
			txtKirjoittaja.newLine();
			txtKirjoittaja.write("Puh:\t"+puhelin+"\n");								   //puhelin tiedostoon
			txtKirjoittaja.newLine();
			txtKirjoittaja.close();														   //suljetaan tietovirta
			kpl++;																		   //lisätään asiakkaiden määrää yhdellä
		} catch(IOException ex) { 														   //poikkeustilanne
			System.out.println("Virhe tiedostoon kirjoittamisessa. Ohjelma lopetetaan");
			System.exit(1);
		}	
	}
	
	//laskee ja palauttaa asiakkaiden määrän tiedostossa
	private int laskeAsiakkaat(){
		File asiakastiedosto = new File(nimi);											//luodaan tiedosto -olio
		int asiakasKpl=0;																//asiakaslaskuri
		if (asiakastiedosto.exists())													//jos tiedosto on olemassa
		    try {
				BufferedReader txtLukija = new BufferedReader(new FileReader(nimi));	//luodaan uusi puskuroitu tiedoston lukuolio
				String rivi;															//tiedoston rivi
				while ((rivi = txtLukija.readLine()) != null) {							//toistetaan kunnes tiedoston rivit loppuvat
					if (rivi.startsWith("Asiakkaan #"))									//jos rivi alkaa asiakastunnuksella
						asiakasKpl++;													//kasvatetaan asiakaslaskuria						
				}
				txtLukija.close();														//suljetaan tietovirta
				
			}
			catch(IOException ex) { 													//poikkeustilanne
				System.out.println("Virhe tiedostosta lukemisessa. Ohjelma lopetetaan");
				System.exit(1);
			}
		return asiakasKpl;																//palautetaan asiakkaiden lukumäärä
	}

	//etsii ja tulostaa tiedostosta halutun asiakkaan tiedot
	//- parametri id yksilöi etsityn asiakkaan
	public void etsiAsiakas(int id){
		File asiakastiedosto = new File(nimi);											//luodaan tiedosto -olio
		if (!asiakastiedosto.exists())													//jos asiakastiedostoa ei ole
			System.out.println("Ei asiakastietoja");
		else{																			//asiakastiedosto on olemassa
		    try {
				BufferedReader txtLukija = new BufferedReader(new FileReader(nimi));	//luodaan uusi puskuroitu tiedoston lukuolio
				String rivi;															//tiedoston rivi
				Boolean loytyi=false;													//onko asiakas löydetty
				while (((rivi = txtLukija.readLine()) != null)&&(loytyi==false)) {		//toistetaan kunnes tiedoston rivit loppuvat tai asiakas löytyy
					if (rivi.startsWith("Asiakkaan #"+id)){								//jos rivi alkaa etsityllä asiakastunnuksella
						loytyi=true;													//asiakas löytyi
						System.out.println("\n"+rivi);									//tulostetaan asiakastunnusrivi
						for (int i=1;i<5;i++){											//tulostetaan asiakastiedot (kolme riviä)
							rivi = txtLukija.readLine();
							System.out.println(rivi);
						}
					}
				}
				if (loytyi==false)														//jos asiakasta ei löytynyt
					System.out.println("\nEi asiakastietoja ID:ll\u00E4 "+id);
				txtLukija.close();														//suljetaan tietovirta
				
			}
			catch(IOException ex) {														//poikkeustilanne
				System.out.println("Virhe tiedostosta lukemisessa. Ohjelma lopetetaan");
				System.exit(1);
			}
		}
	}
	
	//tulostaa kaikkien asiakkaiden tiedot tiedostosta
	public void tulostaAsiakkaat(){
		File asiakastiedosto = new File(nimi);											//luodaan tiedosto -olio
		if (!asiakastiedosto.exists())													//jos asiakastiedostoa ei ole
			System.out.println("Ei asiakastietoja");
		else{																			//asiakastiedosto on olemassa
		    try {
				BufferedReader txtLukija = new BufferedReader(new FileReader(nimi));	//luodaan uusi puskuroitu tiedoston lukuolio
				String rivi;															//tiedoston rivi
				while ((rivi = txtLukija.readLine()) != null) {							//toistetaan kunnes tiedoston rivit loppuvat	
					System.out.println(rivi);											//tulostetaan rivi näytölle
				}
				txtLukija.close();														//suljetaan tietovirta
			}
			catch(IOException ex) { 													//poikkeustilanne
				System.out.println("Virhe tiedostosta lukemisessa. Ohjelma lopetetaan");
				System.exit(1);
			}	
		}	
	}

}

//Asiakas -luokka
class Asiakas{
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
	
	//asiakastietojen tallennus tekstitiedostoon
	public void tallennaAsiakas(Asiakastiedosto tiedosto){
		tiedosto.tallennaUusiAsiakas(ID,nimi,email,puhnro);		
	}
	
	//tulostaa asiakkaan tiedot näytölle
	public void tulostaTiedot(){
		System.out.println("Asiakkaan #"+ID+" tiedot");							  
		System.out.println("Nimi:\t"+nimi);										  
		System.out.println("Email:\t"+email);									   
		System.out.println("Puh:\t"+puhnro+"\n");								  	
	}
}


public class mini9B {
	
	public static final String TIEDOSTO="asiakastiedotB.txt";	//asiakastiedoston nimi ja sijainti
	public static final int TESTIMAX=8;							//testiaineiston vakiotaulukoiden koko
	
	//pääohjelma
	public static void main(String [ ] args) {
		int vastaus=1;											//käyttäjän antama vastaus
		Scanner lukija=new Scanner(System.in);					//syötteen lukuolion luonti	
		Asiakastiedosto asiakkaat=new Asiakastiedosto(TIEDOSTO);//asiakastiedosto -olio
		
		System.out.println("\nANNA UUSIA ASIAKASTIETOJA**********************************************");
		try{
		  do{
			do{
				System.out.print("K\u00E4ytet\u00E4\u00E4nk\u00F6 testiasiakkaita (kyll\u00E4: 1, ei: -1) ");
				vastaus=lukija.nextInt();
			}while(!vastausOk(vastaus));
			if (vastaus!=1){									//käyttäjä syöttää itse tiedot
				Asiakas asiakas=new Asiakas(asiakkaat);			//uusi asiakas -olio
				asiakas.tallennaAsiakas(asiakkaat);				//asiakastietojen tallennus tekstitiedostoon
				asiakas=null;
			}
			else{												//käytetään testiaineistoa
				System.out.print("Kuinka monta testiasiakasta luon?:  ");
				vastaus=lukija.nextInt();
				luoTestiAsiakkaita(asiakkaat,vastaus);			//pyydetään testiasiakkaiden luomista
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
			for (int i=0;i<kpl;i++){							//toistetaan halutun määrän verran 
				enimi=etunimet[arvoIndeksi()];					//asiakastietojen luonti
				snimi=sukunimet[arvoIndeksi()];
				sposti=enimi+"."+snimi+"@ankkalinna.com";
				Asiakas testiasiakas=new Asiakas(td,(enimi+" "+snimi),sposti,puhnrot[arvoIndeksi()]);	//asiakkaan luonti
				testiasiakas.tallennaAsiakas(td);				//asiakastietojen tallennus tekstitiedostoon
			}
	}
	
	//palauttaa satunnaisen kokonaisluvun väliltä 0-(TESTIMAX-1)
	public static int arvoIndeksi(){
		Random arpoja=new Random();								//uusi satunnaisluku -olio
		return (arpoja.nextInt(TESTIMAX));						//satunnaisluku väliltä 0-(TESTIMAX-1)
	}
	
} 