package ui;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
//BOZE BAGARIC 17.5.2023 Bogu hvala
public class Solution {
	public  static List<List<String>> ispisi = new ArrayList<>();

	public static String provjero = "";
	public static int dubinaSada = 0;

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(new File(args[0]));
		String s = sc.nextLine();

		List<String> listaPrviRed = Arrays.asList(s.split(","));

		List<List<String>> vrijednost = new ArrayList<>(); // D glavni iz normane datoteke
		List<String> value = new ArrayList<>();



		while (sc.hasNext()) {
			s = sc.nextLine();
			value.addAll(Arrays.asList(s.split(",")));
			// System.out.println(value);
			// vrijednost.add(Arrays.asList(s.split(",")));
			vrijednost.add(Arrays.asList(s.split(",")));
		}
		//  System.out.println(vrijednost);
		//  System.out.println(listaPrviRed);

		Map<String, List<String>> prijelazi = new LinkedHashMap<>();
		String zadnje = "";
		int kk = 0;
		for (List<String> lis : vrijednost) {
			kk++;
		}
		//  System.out.println(kk + "   ukupan broj redaka");

		int k = 0;
		for (String naziv : listaPrviRed) {

			List<String> prijelaz = new ArrayList<>();

			for(List<String> list : vrijednost) {
				if (!prijelaz.contains(list.get(k))) {
					prijelaz.add(list.get(k));
				}
			}
			prijelazi.put(naziv, prijelaz);
			k++;
			zadnje = naziv;
		}
		//prijelazi su svi moguci prijelazi odredenog stanja

		//  System.out.println(prijelazi);
		//  System.out.println(zadnje);

		List<String> moguceVrijednosti = prijelazi.get(zadnje);
		//  System.out.println(moguceVrijednosti);

		//   Ig(prijelazi, vrijednost, zadnje);

		//id3(vrijednost, vrijednost, listaPrviRed, moguceVrijednosti, prijelazi, zadnje);

		//System.out.println(nadiNovuVrijednost(vrijednost, "hot", listaPrviRed, "temperature"));
		ui.Solution.Node<String> node = null;


		Scanner sc2 = new Scanner(new File(args[1]));
		String scc = sc2.nextLine();

		List<String> listaPrviRedc = Arrays.asList(scc.split(","));

		List<List<String>> vrijednostc = new ArrayList<>(); // D iz test filea
		List<String> valuec = new ArrayList<>();
		while (sc2.hasNext()) {
			scc = sc2.nextLine();
			valuec.addAll(Arrays.asList(scc.split(",")));
			// System.out.println(value);
			// vrijednost.add(Arrays.asList(s.split(",")));
			vrijednostc.add(Arrays.asList(scc.split(",")));
		}
		//System.out.println(Integer.valueOf(ss) + "  ss");
		if (args.length == 2) {
			node = id3(vrijednost, vrijednost, listaPrviRed, moguceVrijednosti, prijelazi, zadnje, new ArrayList<>());//ako nije podrezivanje
		}
		else if (args.length == 3) {
			String ss = args[2];

			int dubina = Integer.valueOf(args[2]);
			node = id3DUBINA(vrijednost, vrijednost, listaPrviRed, moguceVrijednosti, prijelazi, zadnje, new ArrayList<>(), Integer.valueOf(ss), dubinaSada);//sa podrezivanjem
//
		}
//        System.out.println(listaPrviRed);
//        System.out.println(prijelazi);
		//  System.out.println(node.ime);
		// System.out.println(node.getDjeca().get("humidityeee"));
		//   System.out.println(node.getDjeca().keySet());
		System.out.println();
		//  System.out.println(node.ime + "  korjen");
		// System.out.println(node.getDjeca().get("weather").djeca);
		//  System.out.println(node.ime);
		// System.out.println(node.getDjeca().keySet());
		// System.out.println(nadiNovuVrijednost(vrijednost, "rainy", listaPrviRed, "weather"));
		// System.out.println(ispisi);

		//  System.out.println(moguceVrijednosti);

		//  System.out.println("[BRANCHES] :");
//        List<List<String>> novoo = new ArrayList<>();
//
//        for (List<String> str : ispisi) {
//            List<String> vr = new ArrayList<>();
//            if (!moguceVrijednosti.contains(str.get(0))) {
//                vr.add(str.get(0));
//                vr.add(str.get(1));
//                vr.add(str.get(2));
//                novoo.add(vr);
//            }
//        }
//        System.out.println(novoo + "    novooo");
//
//        for (List<String> str : ispisi){
//            if (moguceVrijednosti.contains(str.get(0))) {
//                if (node.getIme().equals(str.get(1))) {
//                    System.out.println("1:" + str.get(1)  + "=" + str.get(2) + " " + str.get(0));
//                } else {
//                    for (List<String> l : novoo) {
//                        if (str.get(1).equals(l.get(0))) {
//                            System.out.println("1:" + l.get(1) + ":" + l.get(2) + " 2:" + l.get(0) + "=" + str.get(2) + " " + str.get(0));
//                        }
//                    }
//                }
//
//                //System.out.println("1:" + str.get(1)  + "=" + str.get(2) + " " + str.get(0));
//            }
//        }
		// printChildrenAndRoot(node);
//        System.out.println();
//        System.out.println();
		//printAllChildren(node, prijelazi.get(zadnje), "");
		System.out.println("[BRANCHES]:");
		node.printLeafPaths(); // metoda za branches



//
//        Scanner sc2 = new Scanner(new File("fileTest.csv"));
//        String scc = sc2.nextLine();
//
//        List<String> listaPrviRedc = Arrays.asList(scc.split(","));
//
//        List<List<String>> vrijednostc = new ArrayList<>();
//        List<String> valuec = new ArrayList<>();
//        while (sc2.hasNext()) {
//            scc = sc2.nextLine();
//            valuec.addAll(Arrays.asList(scc.split(",")));
//            // System.out.println(value);
//            // vrijednost.add(Arrays.asList(s.split(",")));
//            vrijednostc.add(Arrays.asList(scc.split(",")));
//        }
		//  System.out.println(vrijednost);
		//  System.out.println(listaPrviRed);

		// System.out.println(listaPrviRedc + " prvi red");

		//System.out.println(vrijednostc);


		//   provjeriPredikcije(node, vrijednostc, listaPrviRedc);
		String root = node.getIme();
		String ispis = "";
		for (List<String> lista : vrijednostc) {
			ispis +=  provjeriPredikcije(node, lista, listaPrviRed, vrijednost, prijelazi, zadnje) + " "; //metoda za predikcije koja sprema ispis u provjera
		}
		System.out.println("[PREDICTIONS]: " + provjero);


		List<String> lis = Arrays.asList(provjero.split(" "));

		// System.out.println(lis);
		// System.out.println(lis);
		int iteracija = 0;
		double poklapaSe = 0;
		int ukupnoSve = 0;
		for (List<String> li : vrijednostc) {
			// System.out.println(li.get(li.size() - 1));
			//System.out.println(lis.get(iteracija));
			if (li.get(li.size() - 1).equals(lis.get(iteracija))) {
				poklapaSe++;

			}
			ukupnoSve++;
			iteracija++;
		}
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		symbols.setDecimalSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("0.00000", symbols);

		String format = decimalFormat.format(poklapaSe / ukupnoSve);

		System.out.println("[ACCURACY]: " + format); // iznad izracunato koliko se poklapa koliko ne pa je naden accuracy

		// System.out.println(prijelazi.get(zadnje).size());
		int[][] matrica = new  int[prijelazi.get(zadnje).size()][prijelazi.get(zadnje).size()];

		List<String> lista = new ArrayList<>();
		lista = prijelazi.get(zadnje);
		// lista.add("amte");

		Collections.sort(lista);

		// System.out.println(lista);

		List<String> predikcije = lis;

// iteriranje kroz matricu i onda spremanje na odredeno mjesto odredenje vrijedosti i dobijemo onda pravu matricu

		for (int  i = 0; i < prijelazi.get(zadnje).size(); i++) {
			for (int j = 0; j < prijelazi.get(zadnje).size(); j++) {
				int index = 0;
				int broj =0;
				for (List<String> list : vrijednostc) {
					String vr = list.get(list.size()-1);
					//  System.out.println(vr);
					if (vr.equals(lista.get(i))) {
						// System.out.println("tu sam");
						if (predikcije.get(index).equals(lista.get(j))) {
							broj++;
							//   System.out.println("tu sam");
						}
					}
					index++;
				}
				//  System.out.println(broj);
				matrica[i][j] = broj;
			}
		}
		System.out.println("[CONFUSION_MATRIX]: ");

		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0 ; j < matrica.length; j++) {
				System.out.printf(matrica[i][j] + " ");
			}
			System.out.println();
		}//ispis matrice


	}

	public static String provjeriPredikcije(ui.Solution.Node<String> node, List<String> vrijednosti, List<String> listaPrviRed, List<List<String>> sveVrijednostiOriginalne, Map<String, List<String>> prijelazi, String zadnje) {
//        String root = node.getIme();
//        Map<String, Node<String>> djeca = node.getDjeca();
//
//        for (List<String> lista : vrijednosti) {
//            String prijelaz = lista.get(listaPrviRed.indexOf(root));
//            if (djeca.get(prijelaz).leaf == true) {
//                System.out.println(root + "  " + prijelaz);
//                System.out.println(djeca.get(prijelaz).ime);
//                //return;
//            } else {
//                provjeriPredikcije(node.djeca.get(prijelaz), vrijednosti.subList(0, vrijednosti.indexOf(lista)), listaPrviRed);
//            }
//        }

		//rekurzivna metoda kojoj saljem node i koja ide do leafa
		String root = node.getIme();
		Map<String, ui.Solution.Node<String>> djeca = node.getDjeca();

		String prijelaz = vrijednosti.get(listaPrviRed.indexOf(root));

		if (!prijelazi.get(root).contains(prijelaz)) {//ako ne postoji taj prijelaz da se vrati onaj koji je najcesci i prvi po abecedi
			//    System.out.println(root + "  " + prijelaz);
			// System.out.println("tu sam");
			// System.out.println();
			// String value = nadiNajcesci(sveVrijednostiOriginalne);
			int zbrojPrvi = 0;
			int zbrojDrugi = 0;
			int ukupanBroj = 0;
			List<String> l = prijelazi.get(zadnje);
			for (List<String> lis : sveVrijednostiOriginalne) {
				ukupanBroj++;
				if (lis.get(lis.size() -1).equals(l.get(0))) {
					zbrojPrvi++;
				} else if (lis.get(lis.size() -1).equals(l.get(1))) {
					zbrojDrugi++;
				}
			}
			Map<String, Integer> novaMapa = new TreeMap<>();
			novaMapa.put(l.get(0), zbrojPrvi);
			novaMapa.put(l.get(1), zbrojPrvi);
			novaMapa.put(l.get(2), ukupanBroj - zbrojPrvi - zbrojDrugi);
			int najveca = 0;
			String vrati = "";

			for (String s : novaMapa.keySet()){
				if (novaMapa.get(s) > najveca) {
					najveca = novaMapa.get(s);
					vrati = s;
				}
			}
			for (String s : novaMapa.keySet()) {
				if (novaMapa.get(s) == najveca) {
					provjero += s + " ";
					return null;
				}
			}

		} else if (djeca.get(prijelaz).leaf == true) { // ako smo dosli do leafa dodajemo u provjeru novi ispis
			provjero += djeca.get(prijelaz).ime + " ";
			// System.out.println(djeca.get(prijelaz).ime);
			return djeca.get(prijelaz).ime;
		} else {
			provjeriPredikcije(node.djeca.get(prijelaz), vrijednosti, listaPrviRed, sveVrijednostiOriginalne, prijelazi, zadnje); //rekurzivni pozivfunckije
		}
		return null;
		// return "";

	}

	public static ui.Solution.Node id3(List<List<String>> vrijednosti, List<List<String>> vrijednostiParent, List<String> ulazni, List<String> moguceVrijednosti, Map<String, List<String>> prijelazi, String zadnje, List<String> koristene) {
		// System.out.println(vrijednosti);
		// System.out.println("uso sam");
		//metoda id3 glavni algoritam lab vjezbe


		if (vrijednosti.size() == 0) { //prvi if ako je D prazan onda vraca leaf sa najcescim vrijednostima u parentu
			String najcesci = nadiNajcesci(vrijednostiParent);

			ui.Solution.Node<String> node = new ui.Solution.Node(najcesci);
			return node;
		}
		String najcesci = nadiNajcesci(vrijednosti); //najcesca vrijednost u D
		List<List<String>> vrijednostiSaNajcescim = new ArrayList<>();
		for (List<String> lista : vrijednosti) {
			if (lista.get(lista.size() - 1).equals(najcesci)) {
				vrijednostiSaNajcescim.add(lista);
			}
		}
//        System.out.println(vrijednosti + " vrijednosti");
//        System.out.println(vrijednostiSaNajcescim + "  vrijdnosti sa najcescim");
//      //  System.out.println(vrijednostiSaNajcescim);
		//if (ulazni.size() == 0 || vrijednostiSaNajcescim.size() == vrijednosti.size()) { //ovo je krivo Bagaricu moras ispravit
		// System.out.println(koristene);
		List<String> lista = ulazni;
		String[] stringArray = lista.toArray(new String[lista.size()]);
		String[] newArray = new String[stringArray.length - 1];

		if (stringArray[stringArray.length-1].equals(zadnje)) {
			System.arraycopy(stringArray, 0, newArray, 0, stringArray.length - 1);

		}

		//System.out.println(Arrays.stream(newArray).toList() + " ovo");

		List<String> novaLista = Arrays.stream(newArray).toList(); //lista koja oznacava ulazne parametre(prvi red)
		// System.out.println(novaLista);

		//  System.out.println(novaLista + "  novalista");


		// if ((ulazni.containsAll(koristene) && koristene.containsAll(ulazni)) || vrijednostiSaNajcescim.size() == vrijednosti.size()) { //ovo je krivo Bagaricu moras ispravit
	//drugi if u pseudokodu ako nam je prazna lista prvog reda odnosno ako smo sve korstili ili ako je sve yes ili no vracamo leaf
		if ((novaLista.containsAll(koristene) && koristene.containsAll(novaLista)) || vrijednostiSaNajcescim.size() == vrijednosti.size()) { //ovo je krivo Bagaricu moras ispravit
			ui.Solution.Node<String> node = new ui.Solution.Node(najcesci);

			// System.out.println((novaLista.containsAll(koristene) && koristene.containsAll(novaLista)) );
			// System.out.println("tu sam  " + koristene);
			// System.out.println(vrijednosti);
			return node;
		}
		String x = Ig(prijelazi, vrijednosti, zadnje, koristene); //poziv funkcije za racunanje iga
		//System.out.println(x);
		//System.out.println(x);
		//System.out.println(x + " iks");
		Map<String, ui.Solution.Node<String>> djeca = new LinkedHashMap<>();
		String[] p = prijelazi.get(x).toArray(new String[0]);
		//ystem.out.println(prijelazi.get(x) + "  po ovim prijelazima cemo");

		for (String prijelaz : prijelazi.get(x)) { //za sve prijelaze dobivenog xa ( oblacno suncano kisovito)

			List<List<String>> novaVrijednost = nadiNovuVrijednost(vrijednosti, prijelaz, ulazni, x); //poz funkcije koja ostavlja ssamo one redove sa prijelazom
//            int ind = ulazni.indexOf(x);
//            ulazni.remove(x);
//            List<String> prij = prijelazi.get(x);

			koristene.add(x); //u listu koristene dodajemo x
			//  System.out.println(koristene + " koristene prije");
			ui.Solution.Node<String> noviNode = id3(novaVrijednost, vrijednosti, ulazni, moguceVrijednosti, prijelazi, zadnje, koristene); //rekurzivni poziv id3 metode
			List<String> dodaj = new ArrayList<>();
			dodaj.add(noviNode.ime);
			dodaj.add(x);
			dodaj.add(prijelaz);

			ispisi.add(dodaj);

			//  System.out.println(noviNode.ime + "  " + x + "  " + prijelaz);
//            ulazni.add(ind, x);
//            prijelazi.put(x, prij);

			koristene.remove(koristene.indexOf(x)); //izbacujemo iz koristenih x
			//  System.out.println(koristene + " koristene psolije");
			djeca.put(prijelaz, noviNode); //dodajemo u djecu kljuc prijelaz a value node koji smo dobili
		}
		// System.out.println(djeca.keySet() + "  djeca");
		// Node<String> vratiNode = new Node(x, false, djeca);
		ui.Solution.Node<String> vratiNode = new ui.Solution.Node<String>(x, djeca); //stvaramo novi node sa imenom x a djecom
		//  System.out.println("vracam " + x + "  " + djeca.get(x).ime);
		return vratiNode;//vracamo taj node
	}

	public static ui.Solution.Node id3DUBINA(List<List<String>> vrijednosti, List<List<String>> vrijednostiParent, List<String> ulazni, List<String> moguceVrijednosti, Map<String, List<String>> prijelazi, String zadnje, List<String> koristene, int dubinaZadana, int dubinaTrenutna) {
		// System.out.println(vrijednosti);
		// System.out.println("uso sam");
//		if (dubinaTrenutna >= dubinaZadana) {
//			// uzet najcesce rjesenje koje se pojavljujen na toj dubini
//			String najcesci = nadiNajcesci(vrijednosti);
//			Node<String> node = new Node<>(najcesci);
//			return node;
//		}

		//id3 algoritam koji koristimo samo kad oznacimo dubinu odredenu. sve isto ko u ovom iznad samo jedna stvar razlicite dole cu objasnit

		if (vrijednosti.size() == 0) {
			String najcesci = nadiNajcesci(vrijednostiParent);

			ui.Solution.Node<String> node = new ui.Solution.Node(najcesci);
			return node;
		}
		String najcesci = nadiNajcesci(vrijednosti);
		List<List<String>> vrijednostiSaNajcescim = new ArrayList<>();
		for (List<String> lista : vrijednosti) {
			if (lista.get(lista.size() - 1).equals(najcesci)) {
				vrijednostiSaNajcescim.add(lista);
			}
		}
//        System.out.println(vrijednosti + " vrijednosti");
//        System.out.println(vrijednostiSaNajcescim + "  vrijdnosti sa najcescim");
//      //  System.out.println(vrijednostiSaNajcescim);
		//if (ulazni.size() == 0 || vrijednostiSaNajcescim.size() == vrijednosti.size()) { //ovo je krivo Bagaricu moras ispravit
		// System.out.println(koristene);
		List<String> lista = ulazni;
		String[] stringArray = lista.toArray(new String[lista.size()]);
		String[] newArray = new String[stringArray.length - 1];

		if (stringArray[stringArray.length-1].equals(zadnje)) {
			System.arraycopy(stringArray, 0, newArray, 0, stringArray.length - 1);

		}

		//System.out.println(Arrays.stream(newArray).toList() + " ovo");

		List<String> novaLista = Arrays.stream(newArray).toList();
		// System.out.println(novaLista);

		//  System.out.println(novaLista + "  novalista");


		// if ((ulazni.containsAll(koristene) && koristene.containsAll(ulazni)) || vrijednostiSaNajcescim.size() == vrijednosti.size()) { //ovo je krivo Bagaricu moras ispravit
		if ((novaLista.containsAll(koristene) && koristene.containsAll(novaLista)) || vrijednostiSaNajcescim.size() == vrijednosti.size()) { //ovo je krivo Bagaricu moras ispravit
			ui.Solution.Node<String> node = new ui.Solution.Node(najcesci);

			// System.out.println((novaLista.containsAll(koristene) && koristene.containsAll(novaLista)) );
			// System.out.println("tu sam  " + koristene);
			// System.out.println(vrijednosti);
			return node;
		}
		if (dubinaTrenutna >= dubinaZadana) { //dakle ako smo stigli do odredenje dubine pronalazimo najcescu vrijednost po abecedi i vracamo taj leaf
			// uzet najcesce rjesenje koje se pojavljujen na toj dubini
			String najcescii = nadiNajcesciDUBINA(vrijednosti);
			ui.Solution.Node<String> node = new ui.Solution.Node<>(najcescii);
			return node;
		}
		String x = Ig(prijelazi, vrijednosti, zadnje, koristene);
		//System.out.println(x);
		//System.out.println(x);
		//System.out.println(x + " iks");
		Map<String, ui.Solution.Node<String>> djeca = new LinkedHashMap<>();
		String[] p = prijelazi.get(x).toArray(new String[0]);
		//ystem.out.println(prijelazi.get(x) + "  po ovim prijelazima cemo");

		for (String prijelaz : prijelazi.get(x)) {

			List<List<String>> novaVrijednost = nadiNovuVrijednost(vrijednosti, prijelaz, ulazni, x);
//            int ind = ulazni.indexOf(x);
//            ulazni.remove(x);
//            List<String> prij = prijelazi.get(x);
			// int dubinaSad = dubinaTrenutna + 1;
			++dubinaSada; //povecavanmo dubimo
			koristene.add(x);
			//  System.out.println(koristene + " koristene prije");
			ui.Solution.Node<String> noviNode = id3DUBINA(novaVrijednost, vrijednosti, ulazni, moguceVrijednosti, prijelazi, zadnje, koristene, dubinaZadana, dubinaSada);
			List<String> dodaj = new ArrayList<>();
			dodaj.add(noviNode.ime);
			dodaj.add(x);
			dodaj.add(prijelaz);
			dubinaSada--; //smanjujemo dubinu

			ispisi.add(dodaj);

			//  System.out.println(noviNode.ime + "  " + x + "  " + prijelaz);
//            ulazni.add(ind, x);
//            prijelazi.put(x, prij);

			koristene.remove(koristene.indexOf(x));
			//  System.out.println(koristene + " koristene psolije");
			djeca.put(prijelaz, noviNode);
		}
		// System.out.println(djeca.keySet() + "  djeca");
		// Node<String> vratiNode = new Node(x, false, djeca);
		ui.Solution.Node<String> vratiNode = new ui.Solution.Node<String>(x, djeca);
		//  System.out.println("vracam " + x + "  " + djeca.get(x).ime);
		return vratiNode;
	}


	public static List<List<String>> nadiNovuVrijednost(List<List<String>> vrijednosti, String prijelaz, List<String> ulazni, String x) {
		//metoda koja ostavlja redove sa odredenim prijelazom koji smo mu poslali za odredeni x

		List<List<String>> vrati = new ArrayList<>();
		int index = ulazni.indexOf(x);

		List<List<String>> iter = vrijednosti;

		for (List<String> prijelazi : vrijednosti) {
			if (prijelazi.get(index).equals(prijelaz)) {
				vrati.add(prijelazi);
			}
		}

		//  System.out.println(vrati);
		// System.out.println(vrati);
		String[][] stringArray = new String[vrati.size()][];

		for (int i = 0; i < vrati.size(); i++) {
			List<String> innerList = vrati.get(i);
			stringArray[i] = innerList.toArray(new String[0]);
		}

//        int numRows = stringArray.length;
//        int numCols = stringArray[0].length - 1;
//
//        // Create a new array with the desired columns removed
//        String[][] newData = new String[numRows][numCols];
//        for (int i = 0; i < numRows; i++) {
//            int destCol = 0;
//            for (int j = 0; j < stringArray[i].length; j++) {
//                if (j != index) {
//                    newData[i][destCol++] = stringArray[i][j];
//                }
//            }
//        }
//        for (String[] row : newData) {
//            for (String item : row) {
//                System.out.print(item + " ");
//            }
//            System.out.println();
//        }

//        List<List<String>> list = new ArrayList<>();
//        for (String[] row : newData) {
//            List<String> sublist = Arrays.asList(row);
//            list.add(sublist);
//        }

		//  System.out.println(list);


		// return list;
		return vrati;
	}
	public static String nadiNajcesciDUBINA(List<List<String>> vrijednosti) { //metoda koja pronalazi najcescu vrijednost i vraca onu koja je prva po abecedi ako imaju istu vrijednost
		Map<String, Integer> countMap = new HashMap<>();

		for (List<String> row : vrijednosti) {
			String lastColumn = row.get(row.size() - 1);

			int count = countMap.getOrDefault(lastColumn, 0);
			countMap.put(lastColumn, count + 1);
		}

		String mostFrequentObject = null;
		int maxCount = 0;

		for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
			if (entry.getValue() > maxCount) {
				mostFrequentObject = entry.getKey();
				maxCount = entry.getValue();
			}
		}
		List<String> list = new ArrayList<>();
		for (Map.Entry<String,Integer> entry : countMap.entrySet()){
			if(entry.getValue() == maxCount) {
				list.add(entry.getKey());
			}
		}
		Collections.sort(list);
		return list.get(0);
	}

	public static String nadiNajcesci(List<List<String>> vrijednosti) { //metoda koja pronalazi najcescu vrijednost
		Map<String, Integer> countMap = new HashMap<>();

		for (List<String> row : vrijednosti) {
			String lastColumn = row.get(row.size() - 1);

			int count = countMap.getOrDefault(lastColumn, 0);
			countMap.put(lastColumn, count + 1);
		}

		String mostFrequentObject = null;
		int maxCount = 0;

		for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
			if (entry.getValue() > maxCount) {
				mostFrequentObject = entry.getKey();
				maxCount = entry.getValue();
			}
		}
		return mostFrequentObject;
	}

	public static String Ig(Map<String, List<String>> prijelazi, List<List<String>> vrijednosti, String zadnje, List<String> koristene) {
		//metoda za racunanje IG

		// System.out.println(vrijednosti + "  ovo su prijelazi");
		// System.out.println(vrijednosti);
		if (prijelazi.get(zadnje).size() == 2) { //ako su samo dvije opcije (yes ili no npr)
			String vrati = "";
			//System.out.println(prijelazi);
			int couter = 0;
			int ukupniBroj = 0;
			List<String> a = prijelazi.get(zadnje);
			for (List<String> lista : vrijednosti) {
				ukupniBroj++;
				//  System.out.println(lista);
//            if (lista.contains(a.get(0))) {
//                couter++;
//            }
				if (lista.get(lista.size() - 1).equals(a.get(0))) {
					couter++;
				}
			}

			double probability = (double) couter / ukupniBroj;
			double prob2 = (double) (ukupniBroj - couter) / ukupniBroj;

			double entropy = -(probability * (Math.log(probability) / Math.log(2)) + prob2 * (Math.log(prob2) / Math.log(2)));
//pronalazimo pocetnu entropiju
			//  System.out.println(entropy + "  pocetna entropija");
			// System.out.println(couter);
			// System.out.println(entropy);
			// System.out.println(entropy + "  pocetna entropija");

			//System.out.println(entropy + "  entropija");

			Set<String> iksevi = prijelazi.keySet();
			List<String> iksi = new ArrayList<>(iksevi);
			//System.out.println(iksevi);
			Map<String, Double> noveVrijednosti = new HashMap<>();
			// System.out.println(a + "  da ili ne");
			//System.out.println(iksi + "  iksi");
			// System.out.println(prijelazi + "  prijelazi unutar");
			for (String iks : iksi) { // za sve prijelaze
				//System.out.println("ovo sada radim za   " + iks);
				if (koristene.contains(iks)) { // ako je x koristen preskocimo ga
					//  System.out.println("tu sam koristena je vec " + iks);
					continue;
				}
				if (iks.equals(zadnje)) {
					continue;
				} else {
					double sum = 0;
					for (String prijelaz : prijelazi.get(iks)) { // za sve prijelaze od x
						//     System.out.println(prijelaz);
						//    System.out.println(prijelazi.get(iks));
						// System.out.println(prijelaz);
						// System.out.println("usao sam ");
						//  System.out.println("Provjeravam za  " + iks + "  " + prijelaz);

						couter = 0;
						int ukupniBroj1 = 0;
						for (List<String> lista : vrijednosti) {
							//   System.out.println(lista);
							//System.out.println(lista);
							//  if (lista.contains(prijelaz)) {
							if (lista.get(iksi.indexOf(iks)).equals(prijelaz)) { // ako je to taj prijelaz u listi vrijednosti povecavamo ukuoni broj
								//  System.out.println("usao sam");
								ukupniBroj1++;
//                            if (lista.contains(a.get(0))) {
//                                System.out.println("uso sam");
//                                couter++;
//                            }
								//  System.out.println("tu sam");
//                            System.out.println(iksi.indexOf(iks));
//                            System.out.println(a.get(0));
//                            System.out.println(lista.get(lista.size()-1));
								if (lista.get(lista.size() - 1).equals(a.get(0))) { //ako je npr yes povecavanjo drugi broj
									// System.out.println("uso sam");
									couter++;
								}
							}
						}
						//  System.out.println(couter + " zboj za ");
						// System.out.println(ukupniBroj1 + " ukupno");
						//  System.out.println("provjeravam za " + prijelaz + " vrijednosti su " + (ukupniBroj1-couter) + " " + couter);

						// System.out.println(couter + "  " + ukupniBroj1 + iks);
						//System.out.println(ukupniBroj + "  " + couter);
						double probability1 = (double) couter / ukupniBroj1;
						double prob21 = (double) (ukupniBroj1 - couter) / ukupniBroj1;

						//  System.out.println(probability1 + "  " + prob21);

						double entropy1 = -(probability1 * (Math.log(probability1) / Math.log(2)) + prob21 * (Math.log(prob21) / Math.log(2)));
						// pronadena druga entropija za prijelaz
						if (Double.isNaN(entropy1)) {
							entropy1 = 0;
						}
						// System.out.println("Provjeravam za  " + iks + "  " + prijelaz);
						// System.out.println(entropy1 + " entropiija" + couter + " " + ukupniBroj1);

						// System.out.println(entropy1 + "  druga ent");
						int couter2 = ukupniBroj1 - couter;
//                    System.out.println(couter2 + couter);
//                    System.out.println(ukupniBroj);
//                    System.out.println(entropy1);
						sum = sum + ((double) (couter2 + couter) / ukupniBroj) * entropy1; // zbrajanje sume koju cemo oduzet od pocetne entropije
						//System.out.println(sum + "  prvi sum");

						// System.out.println(couter + "  " + (ukupniBroj - couter));
						//System.out.println(entropy1 + " " + prijelaz);
					}
					double nova = entropy - sum; //finalna entropije
					// System.out.println("ovo je nova vrrijednost" + nova);
					//System.out.println(nova + "  nova");
					noveVrijednosti.put(iks, nova); // stavljanje u mapu vrijednosti gdje je X kljuc a entropija vrijednost

					// System.out.println(noveVrijednosti);
					// System.out.println(noveVrijednosti);
				}
				double najveca = -1;
				int interacija = 0;
				//
				// System.out.println(noveVrijednosti);
				for (Map.Entry<String, Double> entry : noveVrijednosti.entrySet()) { //trazenje najvece entropije koju cemo vratiti x
					double vri = entry.getValue();
					//System.out.println(entry.getKey());
					if (vri > najveca) {
						najveca = vri;
						vrati = entry.getKey();
					}
					//   System.out.println(najveca);
				}
				// System.out.println(vrati);
				// System.out.println(najveca + );
				// System.out.println(noveVrijednosti);

			}
			System.out.println(noveVrijednosti);

			//    System.out.println(vrati);
			return vrati;
		} else { // sve isto samo za tri vrijednosti y (yes no unknown)
			String vrati = "";
			//System.out.println(prijelazi);
			int couter0 = 0;
			int couter1 = 0;
			int ukupniBroj = 0;
			List<String> a = prijelazi.get(zadnje);
			for (List<String> lista : vrijednosti) {
				ukupniBroj++;
				//  System.out.println(lista);
//            if (lista.contains(a.get(0))) {
//                couter++;
//            }
				if (lista.get(lista.size() - 1).equals(a.get(0))) {
					couter0++;
				} else if (lista.get(lista.size() - 1).equals(a.get(1))) {
					couter1++;
				}
			}

			double probability = (double) couter0 / ukupniBroj;
			double prob2 = (double) couter1 / ukupniBroj;
			double prob3 = (double) (ukupniBroj - couter0 - couter1) / ukupniBroj;

			double entropy = -(probability * (Math.log(probability) / Math.log(2)) + prob2 * (Math.log(prob2) / Math.log(2)) +  prob3 * (Math.log(prob3) / Math.log(2)));

			//  System.out.println(entropy + "  pocetna entropija");
			// System.out.println(couter);
			// System.out.println(entropy);
			// System.out.println(entropy + "  pocetna entropija");

			//System.out.println(entropy + "  entropija");

			Set<String> iksevi = prijelazi.keySet();
			List<String> iksi = new ArrayList<>(iksevi);
			//System.out.println(iksevi);
			Map<String, Double> noveVrijednosti = new HashMap<>();
			// System.out.println(a + "  da ili ne");
			//System.out.println(iksi + "  iksi");
			// System.out.println(prijelazi + "  prijelazi unutar");
			for (String iks : iksi) {
				//System.out.println("ovo sada radim za   " + iks);
				if (koristene.contains(iks)) {
					//  System.out.println("tu sam koristena je vec " + iks);
					continue;
				}
				if (iks.equals(zadnje)) {
					continue;
				} else {
					double sum = 0;
					for (String prijelaz : prijelazi.get(iks)) {
						//     System.out.println(prijelaz);
						//    System.out.println(prijelazi.get(iks));
						// System.out.println(prijelaz);
						// System.out.println("usao sam ");
						//  System.out.println("Provjeravam za  " + iks + "  " + prijelaz);

						couter0 = 0;
						couter1 = 0;
						int ukupniBroj1 = 0;
						for (List<String> lista : vrijednosti) {
							//   System.out.println(lista);
							//System.out.println(lista);
							//  if (lista.contains(prijelaz)) {
							if (lista.get(iksi.indexOf(iks)).equals(prijelaz)) {
								//  System.out.println("usao sam");
								ukupniBroj1++;
//                            if (lista.contains(a.get(0))) {
//                                System.out.println("uso sam");
//                                couter++;
//                            }
								//  System.out.println("tu sam");
//                            System.out.println(iksi.indexOf(iks));
//                            System.out.println(a.get(0));
//                            System.out.println(lista.get(lista.size()-1));
								if (lista.get(lista.size() - 1).equals(a.get(0))) {
									// System.out.println("uso sam");
									couter0++;
								} else if (lista.get(lista.size() - 1).equals(a.get(1))) {
									couter1++;
								}
							}
						}
						//  System.out.println(couter + " zboj za ");
						// System.out.println(ukupniBroj1 + " ukupno");
						//  System.out.println("provjeravam za " + prijelaz + " vrijednosti su " + (ukupniBroj1-couter) + " " + couter);

						// System.out.println(couter + "  " + ukupniBroj1 + iks);
						//System.out.println(ukupniBroj + "  " + couter);
						double probability1 = (double) couter0 / ukupniBroj1;
						double prob21 = (double) couter1 / ukupniBroj1;
						double prob31 = (double) (ukupniBroj1 - couter0 - couter1) / ukupniBroj1;
						// System.out.println(probability1 + " " + prob21 + " " + prob31);

						// System.out.println(couter0 + " " + couter1 + " " +  (ukupniBroj1 - couter0 - couter1) + " " + ukupniBroj1 + " " + iks + " " + prijelaz);

						//  System.out.println(probability1 + "  " + prob21);
						double entropy1;
						if (probability1 == 0) {
							entropy1 = -( prob21 * (Math.log(prob21) / Math.log(2)) + prob31 * (Math.log(prob31) / Math.log(2)));
						} else if (prob21 == 0) {
							entropy1 = -(probability1 * (Math.log(probability1) / Math.log(2)) + prob31 * (Math.log(prob31) / Math.log(2)));

						}
						else if (prob31 == 0) {
							entropy1 = -(probability1 * (Math.log(probability1) / Math.log(2)) + prob21 * (Math.log(prob21) / Math.log(2)) );

						} else {
							entropy1 = -(probability1 * (Math.log(probability1) / Math.log(2)) + prob21 * (Math.log(prob21) / Math.log(2)) + prob31 * (Math.log(prob31) / Math.log(2)));
							// System.out.println(entropy1);
						}
						if (Double.isNaN(entropy1)) {
							entropy1 = 0;
						}
						// System.out.println("Provjeravam za  " + iks + "  " + prijelaz);
						// System.out.println(entropy1 + " entropiija" + couter + " " + ukupniBroj1);

						// System.out.println(entropy1 + "  druga ent");
						int couter2 = ukupniBroj1 - couter0 - couter1;
						int couter22 = couter1;
//                    System.out.println(couter2 + couter);
//                    System.out.println(ukupniBroj);
//                    System.out.println(entropy1);
						sum = sum + ((double) (ukupniBroj1) / ukupniBroj) * entropy1;
						//System.out.println(sum + "  prvi sum");

						// System.out.println(couter + "  " + (ukupniBroj - couter));
						//System.out.println(entropy1 + " " + prijelaz);
					}
					double nova = entropy - sum;
					// System.out.println("ovo je nova vrrijednost" + nova);
					//System.out.println(nova + "  nova");
					noveVrijednosti.put(iks, nova);

					// System.out.println(noveVrijednosti);
					// System.out.println(noveVrijednosti);
				}
				double najveca = -1;
				int interacija = 0;
				//
				// System.out.println(noveVrijednosti);
				for (Map.Entry<String, Double> entry : noveVrijednosti.entrySet()) {
					double vri = entry.getValue();
					//System.out.println(entry.getKey());
					if (vri > najveca) {
						najveca = vri;
						vrati = entry.getKey();
					}
					//   System.out.println(najveca);
				}
				// System.out.println(vrati);
				// System.out.println(najveca + );
				// System.out.println(noveVrijednosti);

			}
			System.out.println(noveVrijednosti);

			//    System.out.println(vrati);
			return vrati;

		}
	}
	public static void printChildrenAndRoot(ui.Solution.Node<String> node) {
		System.out.println("Root Node: " + node.getIme());
		printNonNullChildren(node);
	}

	public static void printNonNullChildren(ui.Solution.Node<String> node) {
		if (node.getDjeca() != null) {
			for (ui.Solution.Node<String> child : node.getDjeca().values()) {
				if (child != null) {
					System.out.println("Child Node: " + child.getIme());
					printNonNullChildren(child);
				}
			}
		}
	}

	public static class Node<T> { //klasa node koja ima djecu pitanje je li leaf i djecu (mapa kljuc prijelaz vrijednost djeca)
		private String ime;
		private boolean leaf;
		private Map<String, ui.Solution.Node<String>> djeca;

		public Node(String ime, Map<String, ui.Solution.Node<String>> djeca) {
			this.ime = ime;
			this.leaf = false;
			this.djeca = djeca;
		}

		public Node(String ime) {
			this.ime = ime;
			this.leaf = true;
			this.djeca = null;
		}

		public String getIme() {
			return ime;
		}

		public void setIme(String ime) {
			this.ime = ime;
		}

		public boolean isLeaf() {
			return leaf;
		}

		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}

		public Map<String, ui.Solution.Node<String>> getDjeca() {
			return djeca;
		}

		public void setDjeca(Map<String, ui.Solution.Node<String>> djeca) {
			this.djeca = djeca;
		}

		int i = 1 ;
		public void printLeafPaths() {
			printLeafPathsHelper((ui.Solution.Node<String>) this, "", i);
		}

		private void printLeafPathsHelper(ui.Solution.Node<String> node, String path, int i) {
			if (node.isLeaf()) { // ako smo dosli do leafa ispisujemo cijeli redak koji se sastoji od prethodnog patha i vrijednosti y
				String absolutePath = path + node.getIme();
				System.out.println(absolutePath);
				return;
			}
			for (Map.Entry<String, ui.Solution.Node<String>> entry : node.getDjeca().entrySet()) { //ako nismo dosli do leafa za svu djecu dodajemo u path vrijednosti tako da dobijemo ispravan ispis
				ui.Solution.Node<String> child = entry.getValue();
				String newPath = path + i + ":" + node.getIme() + "="  + entry.getKey() +" ";
				printLeafPathsHelper(child, newPath, i+1);
			}
		}





	}
}