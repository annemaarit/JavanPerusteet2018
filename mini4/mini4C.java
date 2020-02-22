/****************************************************************************
Nimi: 	Miniprojekti 4 C
Tekijä: Maarit Parkkonen
Pvm:	28.6.2018
Kuvaus: Ohjelma kyselee käyttäjältä vuosilukuja, kunnes käyttäjä syöttää
        luvun -1. Vuosiluvun perusteella ohjelma tulostaa kyseisen vuoden 
		jokaisen kuukauden päivien lukumäärän. Ohjelma huomioi karkausvuoden.
*****************************************************************************/

//luokkakirjasto
import java.io.*;
import java.util.*;    //mm. Scanner
import java.lang.String;

public class mini4C{
	
   public static final int KK=12;				//kuukausien lukumäärä
   public static final int LOPETA=-1;			//ohjelman lopetusmerkki
   public static final int KARKAUSKK=29;		//karkausvuoden helmikuun päivien määrä
   public static final int TESTIKPL=6;		    //testilukujen kappalemäärä / taulukko
	
   //pääohjelma
   public static void main(String [ ] args) {
      int vuosi=0;						//
	  int paivatKK [] = {31,28,31,30,31,30,31,31,30,31,31,31}; 	//ei-karkausvuoden kuukausien päivien lukumäärät
	  String nimetKK [] ={"tammi","helmi","maalis","huhti","touko","kesä","heinä","elo","syys","loka","marras","joulu"};
	  
	  int testausON [] ={1600,1956,2000,2012,2020,-1};	//testiaineisto: karkausvuosia
      int testausEI [] ={1800,1900,1950,2001,2019,-1};	//testiaineisto: ei karkausvuosia
	  int syottotapa = 0;								//käyttäjä vai testiaineistot
	  
	  int i=0;		//kuukausien indeksi
	  int j=0;		//testiaineiston indeksi

	  //syötteen lukuolio
	  Scanner lukija;					//esittely
	  lukija=new Scanner(System.in);	//luonti
	  
	  //aloitusohjeet (ä=\u00E4 ö=\u00F6)
	  System.out.println("\nKun annat minulle vuoden, tulostan kyseisen vuoden");	  
      System.out.println("jokaisen kuukauden p\u00E4ivien lukum\u00E4\u00E4r\u00E4n.\n");	
	  
	  //tietojen syöttötavan kysyminen
	  try{
		System.out.println("Miten haluat antaa tiedot?");  
		System.out.print("sy\u00F6t\u00E4n itse=0, testiaineisto karkausvuodet=1, testiaineisto ei-karkausvuodet=2:");
		syottotapa=lukija.nextInt();
		lukija.nextLine();	
	  }
	  //virheiden pyydystys
	  catch (Exception e) {		//muut virheet
		  System.out.print("Valinnassa virhe. Lopetetaan.");
		  System.exit(1);
	  }		
	  
	  do{ 
		try{
			if ((syottotapa!=1)&&(syottotapa!=2)){		//käyttäjä syöttää tiedon
				System.out.print("Anna vuosi(lopeta ohjelma sy\u00F6ttamalla -1 ):");	
				vuosi=lukija.nextInt();
				lukija.nextLine();	
			}	
			else{										//testiaineistosta tieto
				if (syottotapa==1)						//karkausvuodet
					vuosi=testausON[j];
				else									//ei-karkausvuodet
					vuosi=testausEI[j];
				System.out.println("Vuosi: "+vuosi);
				j++;
			}
			
			//tulostus
			if (vuosi!=LOPETA)							//jos ei ohjelmaa lopeteta
				if (karkausvuosi(vuosi)!=true)			//jos ei-karkausvuosi
					for (i=0;i<KK;i++)
						System.out.println(nimetKK[i]+":\t"+paivatKK[i]+" paiv\u00E4\u00E4.");
				else									//on karkausvuosi
					for (i=0;i<KK;i++)
						if (i==1)						//jos helmikuu
							System.out.println(nimetKK[i]+":\t"+KARKAUSKK+" paiv\u00E4\u00E4.");						
						else							//muut kuukaudet
							System.out.println(nimetKK[i]+":\t"+paivatKK[i]+" paiv\u00E4\u00E4.");						
						
		}
		//virheiden pyydystys
		catch (InputMismatchException e){				//käyttäjän antamien syötteiden tietotyyppivirheet
		  System.out.println("Virheellinen vuosiluku, annoitko vuoden lukuna?"); 
		  lukija= new Scanner(System.in);
		}
		catch (Exception e) {					   		//muut virheet
		  System.out.print("Yll\u00E4tt\u00E4v\u00E4 virhe. Lopetetaan.");
		  System.exit(1);
		}
	  } while (vuosi!=LOPETA); 							//jatketaan, kunnes käyttäjä päättää lopettaa
   }

   
   //laskee onko parametrina saatu vuosiluku karkausvuosi
   //- palauttaa true=on karkausvuosi, false=ei ole
   public static boolean karkausvuosi(int v){
		boolean karkausvuosi=false;	
		if (v%4==0){				//jos vuosi on neljällä jaollinen
			if (v%100==0){			//jos vuosi on sadalla jaollinen
				if (v%400==0)		//jos vuosi on neljälläsadalla jaollinen
					karkausvuosi=true;
			}
			else 					//vuosi on neljällä mutta ei sadalla jaollinen
				karkausvuosi=true;
		}
		return karkausvuosi;
   }
} 