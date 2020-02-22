/*********************************************
Nimi:	Miniprojekti 6 A
Tekijä:	Maarit Parkkonen
Pvm:	30.6.2018
Kuvaus: Teos -luokka kirjastokäyttöön
		- atribuutit: tekijä, nimi, luokka, 
		  ISBN13, sivut ja tila
		- metodit: konstruktori, 
		  naytaTeoksenTiedot,
		  naytaTila ja
		  naytaTeoksenTila.
		
		Pääohjelma testaa luokan toimintaa.		  
*********************************************/

//luokkakirjasto
import java.lang.String;

//Teos -luokka
class Teos{
	//attribuutit
	private String tekija;	//kirjailija(t)
	private String nimi;	//teoksen nimi
	private double luokka;	//luokitusjärjestelmän luokkanumero ~ genre
	private int[] ISBN13;	//indeksit: 0=etuliite, 1=maa, 2=kustantaja, 3=nimike, 4=tarkiste
	private int sivut;		//sivumäärä
	private int tila; 		//1=paikalla,2=lainassa,3=varattu,4=kateissa
	
	//konstruktori
	Teos(String kirjailija,String kirja, double genre, int[] tunnus, int sivuja, int tilanne){
		tekija=kirjailija;
		nimi=kirja;
		luokka=genre;
		ISBN13=tunnus;
		sivut=sivuja;
		tila=tilanne;
	}	
	
	//tulostaa olion attribuuttien arvot näytölle
	public void naytaTeoksenTiedot(){
		int kpl=ISBN13.length;
		System.out.println("********************************************");
		System.out.println("Tekij\u00E4: \t"+tekija);
		System.out.println("Teos:\t\t"+nimi);
		System.out.println("Luokitus: \t"+luokka);
		System.out.print("ISBN: \t\t");		
		for (int i=0;i<kpl;i++){
			System.out.print(ISBN13[i]);
				if (i<(kpl-1))
					System.out.print("-");
		}
		System.out.println("\nSivum\u00E4\u00E4r\u00E4: \t"+sivut);
		naytaTila();
	}
	
	//tulostaa teoksen tilatiedon
	public void naytaTila(){
		switch (tila){
			case 1: 
				System.out.println("Tila:\t\tpaikalla");
				break;
			case 2: 
				System.out.println("Tila:\t\tlainassa");
				break;	
			case 3: 
				System.out.println("Tila:\t\tvarattu");
				break;
			case 4: 
				System.out.println("Tila:\t\tkateissa");
				break;
			default:
				System.out.println("Tila tuntematon");
		}		
	}
	
	//vaihtaa parametrina saadun arvon teoksen tilatiedoksi
	public void muutaTila(int uusi){
		naytaTeoksenTila();
		tila=uusi;
		System.out.println("P\u00E4ivitetty");	
		naytaTila();		
	}
	
	//tulostaa teoksen nimen ja tilatiedon
	public void naytaTeoksenTila(){
		System.out.println("********************************************");		
		System.out.println("Teoksen:\t"+nimi);
		naytaTila();
	}
}

//toteuttava luokka
public class mini6A{
   //pääohjelma (ä=\u00E4 ö=\u00F6)
   public static void main(String [ ] args) {
	 
	 //luodaan Teos -olioita
	  int[] ISBN1={978,1,22,528745,9}; 
	  Teos kirja1=new Teos("Matti Meik\u00E4l\u00E4inen","Meik\u00E4l\u00E4isten k\u00E4sikirja",0.4,ISBN1,512,2);
	  
	  int[] ISBN2={978,1,28,861420,1}; 
	  Teos kirja2=new Teos("Maija Mehil\u00E4inen","Parhaat hunaja reseptit",6.12,ISBN2,168,1);
	  
	  int[] ISBN3={978,2,13,114258,6}; 
	  Teos kirja3=new Teos("Rikkanen, Kotilo & Kirva","Puutarhan tuholaiset",5.3,ISBN3,352,3);
	  
	  //tulostetaan näytölle olioiden tiedot
	  System.out.println("\nTestaus, uudet kirjat:");
	  kirja1.naytaTeoksenTiedot();	//default: Matti Meikäläinen, Meikäläisten käsikirja, 0.4, 978-1-22-528745-9, 512, lainassa
	  kirja2.naytaTeoksenTiedot();  //default: Maija Mehiläinen, Parhaat hunaja reseptit, 6.12, 978-1-28-861420-1, 168, paikalla
	  kirja3.naytaTeoksenTiedot();  //default: Rikkanen, Kotilo & Kirva, Puutarhan tuholaiset, 5.3, 978-2-13-114258-6, 352, varattu
	  
	  //muutetaan teosten tilatietoja
	  System.out.println("\nTestaus, tilan muutos:");
	  kirja1.muutaTila(4);			//default: Meikäläisten käsikirja, lainassa -> kateissa
	  kirja3.muutaTila(2);			//default: Puutarhan tuholaiset, varattu -> lainassa
	 
	  //tulostetaan teosten tilatiedot
	  System.out.println("\nTestaus, teoksen tila:");
	  kirja2.naytaTeoksenTila();	//default: Parhaat hunaja reseptit, paikalla
	  kirja3.naytaTeoksenTila();	//default: Puutarhan tuholaiset, lainassa
   }
} 