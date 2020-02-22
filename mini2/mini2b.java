/*******************************************************
Nimi: 	Miniprojekti 2 B
Tekijä:	Maarit Parkkonen
Pvm:	20.6.2018
Kuvaus:	- Kysytään käyttäjältä seitsemän viikonpäivän
		  sademäärät
		- Tulostetaan sademäärät näytölle
		- Huomioidaan käyttäjän virhesyötteet
		  (oma huomio: todella kätevä tapa!!)
********************************************************/

//luokkakirjastot
import java.util.*;
import java.lang.String;

//ohjelmaluokka
public class  mini2b{
   //viikonpäivien lukumäärä vakio
   public static final int LKM=7;	
   
   //pääohjelma
   public static void main(String [ ] args) {
	  
	  int i=0; 					//indeksoi viikonpäivää
	  boolean ok=false;			//onko ohjelma onnistuneesti suoritettu
	  int [] sademaara;			//viitemuuttuja kokonaislukutaulukkoon
	  sademaara=new int[LKM]; 	//viittaus uuteen kokonaislukutaulukko-olioon
	  
	  //viitemuuttuja viikonpäivien nimitaulukkoon ja taulukon alustus
	  String [] paiva={"Maanantai","Tiistai","Keskiviikko","Torstai","Perjantai","Lauantai","Sunnuntai"};

	  
	  //syötteen lukuolion esittely ja luonti
	  Scanner lukija;
	  lukija=new Scanner(System.in);
	  
	  //englantilainen desimaalityyli (piste), HUOM! ei toiminut syötteiden luvussa??
	  //Locale.setDefault(Locale.ENGLISH);
	  
	 do{
	  try{ 
		if (i==0) //ei tulostu, jos kyseessä virheellisen syötteen korjaus
			System.out.println("Anna päivittäiset sademäärät (ml): ");	  
		
		//luetaan päivittäiset sademäärät:
		//jos käyttäjä antaa virheellisen syötteen, virheen pyydystys ilmoittaa virheestä
		//ja palauttaa takaisin tähän. Indeksi i kuitenkin säilyttää arvonsa, joten
		//syötteiden luku jatkuu virheellisestä kohdasta
		while (i<LKM){ 
		  System.out.print(paiva[i]+": ");
		  sademaara[i]=lukija.nextInt();
		  i++;
		}
		//tietojen tulostus
		System.out.println("******************************************");
		System.out.println("Viikon sademäärät:");
		for (i=0;i<LKM;i++)
			System.out.println(paiva[i]+":\t"+sademaara[i]+" ml");		  
		System.out.println("******************************************");
		ok=true;
	  }
	  //käyttäjän antamien epäsopivien syötteiden aiheuttamien virheiden pyydystys
	  catch (InputMismatchException e){
		  System.out.println("Virheellinen sademäärä. Anna määrä uudestaan.");
		  lukija= new Scanner(System.in);
	  }
	  //muiden virheiden pyydystys
	  catch (Exception e) {
		  System.out.print("Yllättävä virhe. Lopetetaan.");
		  System.exit(1);
	  }
	 }while (ok!=true);
   } 
} 