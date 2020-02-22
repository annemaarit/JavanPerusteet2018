/*******************************************************************************
Nimi:	Miniprojekti 5 A
Tekijä: Maarit Parkkonen
Pvm:	29.6.2018
Kuvaus: tulostaPaivays -metodi:
		- parametrit vuosi, kuukausi, päivä ja tulostusformaatti
		- tarkistaa parametrien oikeellisuuden 
		(yleensä kai lähetettävät parametrit tarkistetaan ennen metodin 
		kutsua mutta nyt innostuin liikaa...)
		- tulostaa päiväyksen halutussa formaatissa tai virheilmoituksen
		
		Pääohjelma testaa metodia
********************************************************************************/

//luokkakirjasto
import java.io.*;
import java.lang.String;
//
public class mini5A{
   //pääohjelma (ä=\u00E4 ö=\u00F6)
   public static void main(String [ ] args) {
	  try{
		  //testataan metodia
		  //"virheettömiä" syötteitä
		  System.out.println("");
		  tulostaPaivays(2018,12,12,1);	//default: 12. joulukuuta 2018
		  tulostaPaivays(1000,1,1,2);	//default: 1.1.1000  
		  tulostaPaivays(2012,5,31,3);	//default: 2012-5-31
		  tulostaPaivays(1600,2,29,2);	//default: 29.2.1600		  
		  
		  //virheellisiä syötteitä
		  System.out.print("\nVirheellisi\u00E4 sy\u00F6tteit\u00E4:\n");
		  tulostaPaivays(2018,13,11,1);	//default: Kuukausinumero: 13 on virheellinen 
		  tulostaPaivays(2018,6,29,5);	//default: Tulostusformaatti: 5 on tuntematon.
		  tulostaPaivays(1600,2,30,2);	//default: Päivien määrä: 30 on virheellinen, helmikuussa 1600 on 1-29 päivää.
		  tulostaPaivays(1601,2,29,2);	//default: Päivien määrä: 29 on virheellinen, helmikuussa 1601 on 1-28 päivää.
	  }
	  catch (Exception e) {	//virheiden pyydystys		  
		  System.out.print("Yllättävä virhe. Lopetetaan.");
		  System.exit(1);
	  }

   }
   
   //Tarkistaa parametrien oikeellisuuden ja tulostaa sen perusteella
   //-päiväys ok: päiväyksen halutussa formaatissa
   //-päiväyksen osissa virheitä: virheilmoituksen
   public static void tulostaPaivays(int vuosi,int kk, int paiva, int muoto){
		String nimetKK [] ={"tammi","helmi","maalis","huhti","touko","kes\u00E4","hein\u00E4","elo","syys","loka","marras","joulu"};
		int paiviaKpl;
		if ((kk<13)&&(kk>0)){
		  paiviaKpl=paiviaKK(vuosi,kk);			
		  if ((paiva>0)&&(paiva<=paiviaKpl)){
			switch (muoto){
				case 1:	//normaali
					System.out.println(paiva+". "+nimetKK[kk-1]+"kuuta "+vuosi);
					break;
				case 2:	//lyhyt
					System.out.println(paiva+"."+kk+"."+vuosi);  	
					break;
				case 3:	//kansainvälinen
					System.out.println(vuosi+"-"+kk+"-"+paiva);  	
					break;
				default:
					System.out.println("Tulostusformaatti: "+muoto+" on tuntematon.");  	
					break;
			}
		  }
		  else{
			System.out.print("P\u00E4ivien m\u00E4\u00E4r\u00E4: "+paiva+" on virheellinen, ");
			System.out.println(nimetKK[kk-1]+"kuussa "+vuosi+" on 1-"+paiviaKpl+" p\u00E4iv\u00E4\u00E4.");
		  }
		}
		 else
			System.out.println("Kuukausinumero: "+kk+" on virheellinen.");  			
   }
   
   //Palauttaa yhden kuukauden päivien lukumäärän
   public static int paiviaKK(int v, int k){						//v=vuosi,k=kuukausi
 	    int paivatKK [] = {31,28,31,30,31,30,31,31,30,31,31,31}; 	//ei-karkausvuoden kuukausien päivien lukumäärät  
		if ((k==2)&&(karkausvuosi(v)==true))						//jos kyseessä karkausvuosi
			return 29;
		else
			return paivatKK [k-1];
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