package ui;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class Solution {

	// klasa node koju koristim kao cvor .. dajem joj naziv, tezinnu puta i pokazivac na cvor roditelja odnosno
	// onaj cvor koji je otvorio taj cvor
	private static class Node {
		private final String state;
		private final double cost;
		private final ui.Solution.Node parent;

		public Node(String state, double cost, ui.Solution.Node parent) {
			this.state = state;
			this.cost = cost;
			this.parent = parent;
		}

		public String getState() {
			return state;
		}

		public double getCost() {
			return cost;
		}

		public ui.Solution.Node getParent() {
			return parent;
		}
	}
	public static String imeHeuristicneDatoteke = "";


	public static void main(String[] args) throws IOException {


		// List<String> linijeSve = null;

		//linijeSve = Files.readAllLines(Paths.get(args[1]), StandardCharsets.UTF_8);

		// if (args[1] == "bfs" || args[1] == "ucs") {
		// File file = new File(Paths.get(args[1]));
//         FileReader fileReader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("file.txt"))  ;

		String imeDatoteke = "";
		String imeAlgoritma = "";
		boolean jeLiOptimisticnost = false;
		boolean jeLiKonzistentnost = false;

		//System.out.println(imeDatoteke);

		//provjeravam ima li u argsu "-ss", ukoliko postoji uzimam sljedeci argument i koristim ga kao path do datoteke iz
		//koje cu ucitavati podatke
		for(int i = 0; i < args.length; i++) {
			if (args[i].equals("--ss")) {
				imeDatoteke = args[i+1];
			}
		}
		//  System.out.println(imeDatoteke);
		//provjeravam postoji li u argsu "--alg" . ukoliko postoji uzimam sljedeci arg te definiram naziv algoritma
		for (int i  = 0; i < args.length; i ++) {
			if (args[i].equals("--alg")) {
				imeAlgoritma = args[i+1];
			}
		}
		// System.out.println(imeDatoteke);
		// if (imeAlgoritma.equals("astar")) {


		//provjeravam postoji li u argsu "--h", ukoliko postoji uzimam sljedeci arg i definiram naziv detoteke iz koje cu citati heuristike
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--h")) {
				imeHeuristicneDatoteke = args[i+1];
			}
		}
		// System.out.println(imeHeuristicneDatoteke);

		//ukoliko u args postoji "--check-optimistic" stavljam varijablu na true te cu kasnije pomocu toga znaci da se trazi provjera optimisticnosti
		for(String s : args) {
			if(s.equals("--check-optimistic")) {
				jeLiOptimisticnost = true;
			}
		}

		//ukoliko u args postoji "--check-consistent" stavljam varijablu na true te cu kasnije pomocu toga znaci da se trazi provjera konzistentnosti

		for (String s : args) {
			if(s.equals("--check-consistent")) {
				jeLiKonzistentnost = true;
			}
		}
		//System.out.println(imeDatoteke);

		//postupak ucitavanja podataka iz datoteke

		File file = new File(imeDatoteke);

		FileInputStream f = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(f, StandardCharsets.UTF_8);
		BufferedReader bufferedReader = new BufferedReader(isr);
		String line;
		String pocetnoStanje;
		List<String> prihvatljivaStanja = new ArrayList<>();
		pocetnoStanje = bufferedReader.readLine();
		while (pocetnoStanje.charAt(0) == '#') {
			pocetnoStanje = bufferedReader.readLine();
		}
		//System.out.println(pocetnoStanje);
		Map<String, Map<String, Double>> prijelaziFinal = new HashMap<>();
		line = bufferedReader.readLine();
		String[] prih = line.split(" ");
		for (String s : prih) {
			prihvatljivaStanja.add(s);
		}
		String kk = "";
		while ((kk = bufferedReader.readLine()) != null) {
			Map<String, Double> prijelaziUnutar = new HashMap<>();
			String k = kk.split(": ")[0];
			int lenght = kk.length();
			if (kk.charAt(lenght - 1) == ':') {
				continue;
			} else if (kk.split(": ")[1].split(" ").length == 1) {
				prijelaziUnutar.put(kk.split(": ")[1].split(",")[0], Double.valueOf(kk.split(": ")[1].split(",")[1]));
			} else {
				String[] prijelazi = kk.split(": ")[1].split(" ");
				for (String ss : prijelazi) {
					prijelaziUnutar.put(ss.split(",")[0], Double.valueOf(ss.split(",")[1]));
				}
			}

			prijelaziFinal.put(k, prijelaziUnutar);
			// prijelaziFinal su svi prijelazi koji su procitani. oblika su mape unutar mape
			// prvi keyValue su svi cvorovi, a druga mapa oznacava prijelaze iz tih cvorova
		}
		//  System.out.println(imeAlgoritma);

		//za svaki algoritam provjeravam o kojemu se algoritmu radi i pozivam funckije za izrsavanje tih algoritama
		if (imeAlgoritma.equals("bfs")) {
			//  System.out.println("tu sam");
			BFS(pocetnoStanje, prihvatljivaStanja, prijelaziFinal);
			return;
		} else if(imeAlgoritma.equals("ucs")) {
			UCS(pocetnoStanje, prihvatljivaStanja,prijelaziFinal);
		} else if(imeAlgoritma.equals("astar")) {

			//ukoliko se trazi astar algoritam moram ucitati podatke iz mape heuristike sto radim u mapi mapaHeuristika

			Map<String, Double> mapaHeuristika = new LinkedHashMap<>();


			File file1 = new File(imeHeuristicneDatoteke);
			FileReader fileReader1 = new FileReader(file1);
			BufferedReader br = new BufferedReader(fileReader1);
			String procitao = "";
			while ((procitao = br.readLine()) != null) {
				// System.out.println(procitao);
				mapaHeuristika.put(procitao.split(": ")[0], Double.valueOf(procitao.split(": ")[1]));
			}
			AStar(pocetnoStanje, prihvatljivaStanja, prijelaziFinal, mapaHeuristika);

		} else if(jeLiOptimisticnost == true) { //ukoliko je trazena provjera optimisticnosti takoder citam podatke heuristike i pozivam metodu
			Map<String, Double> mapaHeuristika = new LinkedHashMap<>();

			//     System.out.println(imeHeuristicneDatoteke);
			File file1 = new File(imeHeuristicneDatoteke);
			FileReader fileReader1 = new FileReader(file1);
			BufferedReader br = new BufferedReader(fileReader1);
			String procitao = "";
			while ((procitao = br.readLine()) != null) {
				// System.out.println(procitao);
				mapaHeuristika.put(procitao.split(": ")[0], Double.valueOf(procitao.split(": ")[1]));
			}
			provjeraOptimisticnosti(prijelaziFinal,prihvatljivaStanja, mapaHeuristika );

			//ukoliko se trazi provjerra konzistentnosti citam podatke heuristike i zovem metodu za provjeru konzistentnosti
		} else if (jeLiKonzistentnost == true) {
			Map<String, Double> mapaHeuristika = new LinkedHashMap<>();

			//     System.out.println(imeHeuristicneDatoteke);
			File file1 = new File(imeHeuristicneDatoteke);
			FileReader fileReader1 = new FileReader(file1);
			BufferedReader br = new BufferedReader(fileReader1);
			String procitao = "";
			while ((procitao = br.readLine()) != null) {
				// System.out.println(procitao);
				mapaHeuristika.put(procitao.split(": ")[0], Double.valueOf(procitao.split(": ")[1]));
			}
			provjeraKonzistentnosti(prijelaziFinal, mapaHeuristika );

		}


//                    Map<String, Double> mapaHeuristika = new LinkedHashMap<>();
//
//
//                    File file1 = new File("heuristika.txt");
//                    FileReader fileReader1 = new FileReader(file1);
//                    BufferedReader br = new BufferedReader(fileReader1);
//                    String procitao = "";
//                    while ((procitao = br.readLine()) != null) {
//                        // System.out.println(procitao);
//                        mapaHeuristika.put(procitao.split(": ")[0], Double.valueOf(procitao.split(": ")[1]));
//                    }
		// System.out.println(mapaHeuristika);

		//provjeraOptimisticnosti(prijelaziFinal, prihvatljivaStanja, mapaHeuristika);
		// System.out.println(UCSZaOptimisticnost(pocetnoStanje, prihvatljivaStanja, prijelaziFinal));
//
		//  BFS(pocetnoStanje, prihvatljivaStanja, prijelaziFinal);
////
		// AStar(pocetnoStanje, prihvatljivaStanja, prijelaziFinal, mapaHeuristika);

	}


	// bfs algoritam
	public static void BFS(String pocetnoStanje, List<String> prihvatljivaStanja, Map<String, Map<String, Double>> prijelazi) {
		System.out.println("# BFS");

		//open lista za otvorene cvorove koji imaju prioritet po vremenu ubacivanja
		Queue<ui.Solution.Node> open = new LinkedList<>();
		Set<String> closed = new HashSet<>();
		open.add(new ui.Solution.Node(pocetnoStanje, 0.0, null));
		int stateVisited = 0; // broj posjecenih stanja

		//sve dok postoje otvoreni cvorovi
		while (!open.isEmpty()) {
			stateVisited++;

			//node mi oznacava glavu odnosmo pokazatelj na kojemu sam cvoru trenutno
			ui.Solution.Node node = open.poll();

			//lista closed u koju dodajem vec sve posjecene cvorove
			closed.add(node.getState());

			//ukoliko je glava prihvatljivo stanje ulazim u if , pravim listu koja ce mi pomocu pokazivaca na parentNode
			//naci put do pocetnog stanja te za svaku stavku liste zbrajam cost sto mi rezultira total cost
			// ispod je ispis elemenata
			if (prihvatljivaStanja.contains(node.getState())) {
				List<ui.Solution.Node> listaa = new ArrayList<>();
				ui.Solution.Node n = node;
				while (n != null){
					listaa.add(n);
					n = n.parent;
				}
				Collections.reverse(listaa);
				Double totalCost = 0.0;
				for(ui.Solution.Node nod : listaa) {
					totalCost+=nod.cost;
				}

				System.out.println("[FOUND_SOLUTION]: yes");
				System.out.println("[STATES_VISITED]: " + stateVisited);
				System.out.println("[PATH_LENGTH]: " + listaa.size());
				System.out.println("[TOTAL_COST]: " + totalCost);
				System.out.println("[PATH]: " + listaa.stream().map(ui.Solution.Node::getState).collect(Collectors.joining(" => ")));

				return;
			}
			if (prijelazi.get(node.getState()) != null) { //ukoliko postoje prijelazi iz navedenog stanja
				for (String next : prijelazi.get(node.getState()).keySet()) { // svaki prijelaz
					if (!closed.contains(next)) { //ukoliko vec nije posjecen
						open.add(new ui.Solution.Node(next,  prijelazi.get(node.getState()).get(next), node)); //spremanje novih cvorova u listu

					}
				}
			}
		}
		System.out.println("[FOUND_SOLUTION]: no");
	}
	public static void UCS (String pocetnoStanje, List<String> prihvatljivaStanja, Map<String, Map<String, Double>> prijelazi) {
		System.out.println("# UCS");

		//lista otvorenih cvorova koji se slazu prioritetom costa pa onda abecednim redom naziva
		PriorityQueue<ui.Solution.Node> open = new PriorityQueue<>(Comparator.comparing(ui.Solution.Node::getCost).thenComparing(ui.Solution.Node::getState));
		Map<String, Double> posjecenaStanja = new HashMap<>(); //mapa posjecih stanja
		posjecenaStanja.put(pocetnoStanje, 0.0);

//        Map<String, Double> openUCS = new HashMap<>();
//        openUCS.put(pocetnoStanje, 0.0);
//        costMap.put(pocetnoStanje, 0.0);

		open.add(new ui.Solution.Node(pocetnoStanje, 0.0, null)); //dodavanja u open pocetno stanje
		int stateVisited = 0;
		Map<String, String> parentMap = new HashMap<>();
		parentMap.put(pocetnoStanje, null);


		while (!open.isEmpty()) {
			stateVisited++;
			ui.Solution.Node node = open.poll();

			//isti postupak kao i kod bfs algoritma..
			//ako je glava prihvatljivo stanje racuna se put i cost puta i ispisuju se vrijednosti
			if (prihvatljivaStanja.contains(node.getState())) {
				ui.Solution.Node n = node;
				List<ui.Solution.Node> listaa = new ArrayList<>();
				while (n != null){
					listaa.add(n);
					n = n.parent;
				}
				Collections.reverse(listaa);


				System.out.println("[FOUND_SOLUTION]: yes");
				System.out.println("[STATES_VISITED]: " + stateVisited);
				System.out.println("[PATH_LENGHT]: " +  listaa.size());
				System.out.println("[TOTAL_COST]: " + node.getCost());
				System.out.println("[PATH]: " + listaa.stream().map(ui.Solution.Node::getState).collect(Collectors.joining(" => ")));

				return;
			}

			if (prijelazi.get(node.getState()) != null) {
				for (String next : prijelazi.get(node.getState()).keySet()) {
					if (posjecenaStanja.containsKey(next) && posjecenaStanja.get(next) < node.cost + prijelazi.get(node.getState()).get(next)) {
						//ukoliko je stanje vec posjeceno i ima manji cost onda ga ne unosimo u listu otvorenih cvorova te tako postizemo optimizaciju algoritma
					} else {
						posjecenaStanja.put(next, node.cost + prijelazi.get(node.getState()).get(next));
						open.add(new ui.Solution.Node(next, node.cost + prijelazi.get(node.getState()).get(next), node));
						//unosimo u listu otvorenih stanja nova stanja, sa njihovim ukupnim costovima
					}

				}
			}
		}


		System.out.println("[FOUND_SOLUTION]: no");
	}




	public static void AStar(String pocetnoStanje, List<String> prihvatljivaStanja, Map<String, Map<String, Double>> prijelazi, Map<String, Double> heuristika) {
		System.out.println("# A-STAR " + imeHeuristicneDatoteke);

//        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparing(node -> node.getCost() + heuristika.get(node.getState())));
		PriorityQueue<ui.Solution.Node> open = new PriorityQueue<>(Comparator.comparing((ui.Solution.Node node) -> node.getCost() + heuristika.get(node.getState()))
				.thenComparing(ui.Solution.Node::getState));
		//lista open poredana je prvo po ukupnom costu + vrijednosti heuristika, pa onda abecedno po nazivu cvorova

		// PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparing(Node::getState).thenComparing(node -> node.getCost() + heuristika.get(node.getState()));
		Map<String, Double> posjecenaStanja = new HashMap<>();
		Map<String, List<String>> pathMap = new HashMap<>();
		//  Map<String, String> parentMap = new HashMap<>();

		open.add(new ui.Solution.Node(pocetnoStanje, 0.0, null)); //obacivanje pocetkog stanja u otvoreno stanje
		posjecenaStanja.put(pocetnoStanje, 0.0);
		int statesVisited = 0;
		while (!open.isEmpty()) {
			statesVisited++;

			ui.Solution.Node node = open.poll(); //glava, odnosno open stanje
			if (prihvatljivaStanja.contains(node.getState())) { //ukoliko je prihvaljivo isti postupak kao i kod drugih algoritama
				List<ui.Solution.Node> path = new ArrayList<>();
				ui.Solution.Node n = node;
				while (n != null) {
					path.add(n);
					n = n.getParent();
				}
				Collections.reverse(path);

				System.out.println("[FOUND_SOLUTION]: yes");
				System.out.println("[STATES_VISITED]: " + statesVisited);
				System.out.println("[PATH_LENGTH]: " + path.size());
				System.out.println("[TOTAL_COST]: " + node.getCost());
				System.out.println("[PATH]: " + path.stream().map(ui.Solution.Node::getState).collect(Collectors.joining(" => ")));
				return;
			}

			if (prijelazi.get(node.getState()) != null) {
				for (String next : prijelazi.get(node.getState()).keySet()) { //svako novo stanje
					if (posjecenaStanja.containsKey(next) && posjecenaStanja.get(next) < node.cost + prijelazi.get(node.getState()).get(next)) {
						//ukoliko je vec posjeceno i ima manji trosak preskacemo stanje
					} else {
						posjecenaStanja.put(next, node.cost + prijelazi.get(node.getState()).get(next));
						open.add(new ui.Solution.Node(next, node.cost + prijelazi.get(node.getState()).get(next), node));
						//ubacivamo u listu open nova stanja
					}

				}
			}

		}

		System.out.println("[FOUND_SOLUTION]: no");
	}


	//isti ucs algoritam kao i iznad opisan, samo koji ne ispisuje nista nego vraca double vrijednost ukupnog puta
	//koristimo ga kod racunanja optimisticnosti
	public static Double UCSZaOptimisticnost (String pocetnoStanje, List<String> prihvatljivaStanja, Map<String, Map<String, Double>> prijelazi) {
		PriorityQueue<ui.Solution.Node> open = new PriorityQueue<>(Comparator.comparing(ui.Solution.Node::getCost).thenComparing(ui.Solution.Node::getState));
		Map<String, Double> posjecenaStanja = new HashMap<>();
		posjecenaStanja.put(pocetnoStanje, 0.0);

//        Map<String, Double> openUCS = new HashMap<>();
//        openUCS.put(pocetnoStanje, 0.0);
//        costMap.put(pocetnoStanje, 0.0);

		open.add(new ui.Solution.Node(pocetnoStanje, 0.0, null));
		int stateVisited = 0;
		Map<String, String> parentMap = new HashMap<>();
		parentMap.put(pocetnoStanje, null);

		while (!open.isEmpty()) {
			stateVisited++;
			ui.Solution.Node node = open.poll();

			if (prihvatljivaStanja.contains(node.getState())) {
				ui.Solution.Node n = node;
				List<ui.Solution.Node> listaa = new ArrayList<>();
				while (n != null){
					listaa.add(n);
					n = n.parent;
				}
				Collections.reverse(listaa);


//                System.out.println("[FOUND_SOLUTION]: yes");
//                System.out.println("[STATES_VISITED]: " + stateVisited);
//                System.out.println("[PATH_LENGHT]: " +  listaa.size());
//                System.out.println("[TOTAL COST]: " + node.getCost());
//                System.out.println("[PATH]: " + listaa.stream().map(Node::getState).collect(Collectors.joining(" => ")));

				return node.getCost();
			}

			if (prijelazi.get(node.getState()) != null) {
				for (String next : prijelazi.get(node.getState()).keySet()) {
					if (posjecenaStanja.containsKey(next) && posjecenaStanja.get(next) < node.cost + prijelazi.get(node.getState()).get(next)) {
					} else {
						posjecenaStanja.put(next, node.cost + prijelazi.get(node.getState()).get(next));
						open.add(new ui.Solution.Node(next, node.cost + prijelazi.get(node.getState()).get(next), node));
					}

				}
			}
		}

		return null;
	}

	public static void provjeraOptimisticnosti(Map<String, Map<String, Double>> transitions, List<String> prihvatljivaStanja, Map<String, Double> heuristika) {
		System.out.println("# HEURISTIC-OPTIMISTIC " + imeHeuristicneDatoteke);
		boolean dobroJe = true; //inicijalizacija da je optimisticno

		//za svako stanje iz mape heuristike racunamo cost od ucs te usporedujemo sa heuristikom navedenog cvora,
		//ukoliko je heuristika <= onda je optimistina
		for (String cvor : heuristika.keySet()) {
			Double cijenaUCS = UCSZaOptimisticnost(cvor, prihvatljivaStanja, transitions);
			if (heuristika.get(cvor) <= cijenaUCS ) {
				System.out.println("[CONDITION]: [OK] h(" + cvor + ") <= h*: " + heuristika.get(cvor) + " <= " + cijenaUCS);
			} else {
				System.out.println("[CONDITION]: [ERR] h(" + cvor + ") <= h*: " +  heuristika.get(cvor) + " <= " + cijenaUCS);
				dobroJe = false; //ukoliko bilo koje stanje ne ispunjava uvjet onda cijeli graf nije optimistican
			}
		}

		//ispisivanje optimisticno ili nije optimisticno
		if (dobroJe) {
			System.out.println("[CONCLUSION]: Heuristic is optimistic.");
		} else {
			System.out.println("[CONCLUSION]: Heuristic is not optimistic.");
		}

	}


	public static void provjeraKonzistentnosti(Map<String, Map<String, Double>> transitions, Map<String, Double> heuristika) {
		System.out.println("# HEURISTIC-CONSISTENT " + imeHeuristicneDatoteke);

		double vrijednostHeuristikaPocetni;
		double vrijednostPrijelaza;
		double vrijenostHeuristikaZavrsni;
		boolean jeLiKonzistentna = true; //varijabla koju stavljamo na false ako jedna konzistentnost nije ispunjena

		//za svako stanje, i njegove prijelaze racunamo ispinjuva li se uvjet konzistentnosti
		//uvjet konzistentnosti: vrijednost heuristike cvora mora biti manje ili jednaka vrijednosti heuristike cvora u koji ide prijelaz i cijene prijelaza
		for(String stanje : transitions.keySet()) {
			for (String stanjePrijelaza : transitions.get(stanje).keySet()) {
				vrijednostHeuristikaPocetni = heuristika.get(stanje);
				vrijenostHeuristikaZavrsni = heuristika.get(stanjePrijelaza);
				vrijednostPrijelaza = transitions.get(stanje).get(stanjePrijelaza);

				//ukoliko se ispunja uvjet ispisujemo ok
				if (vrijednostHeuristikaPocetni <= vrijenostHeuristikaZavrsni + vrijednostPrijelaza) {
					System.out.println("[CONDITION]: [OK] h(" + stanje + ") <= h(" + stanjePrijelaza + ") + c: " + vrijednostHeuristikaPocetni + " <= " + vrijenostHeuristikaZavrsni + " + " + vrijednostPrijelaza);
				} else {
					//ukoliko nije ispiunjen uvjet ispis err
					System.out.println("[CONDITION]: [ERR] h(" + stanje + ") <= h(" + stanjePrijelaza + ") + c: " + vrijednostHeuristikaPocetni + " <= " + vrijenostHeuristikaZavrsni + " + " + vrijednostPrijelaza);
					jeLiKonzistentna = false;  //ukoliko se ne ispunja bar 1 konzistentnost cijela heuristika nije konzistentna
				}
			}
		}

		//ovisno o varijabli ispisujemo je li konzistentno ili nije.
		if (jeLiKonzistentna == true) {
			System.out.println("[CONCLUSION]: Heuristic is consistent.");
		} else {
			System.out.println("[CONCLUSION]: Heuristic is not consistent.");
		}
	}
}
