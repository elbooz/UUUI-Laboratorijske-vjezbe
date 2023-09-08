package ui;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

class Solution {


	public static void main(String[] args) throws IOException {
		if (args[0].equals("cooking")) { //provjera radim li ccoking

			//ucitavanje podataka za coocking
			File file = new File(args[1]);

			FileInputStream f = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(f, StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(isr);

			File fileDrugo = new File(args[2]);

			FileInputStream fdrugo = new FileInputStream(fileDrugo);
			InputStreamReader isrdrugo = new InputStreamReader(fdrugo, StandardCharsets.UTF_8);
			BufferedReader bufferedReaderdrugo = new BufferedReader(isrdrugo);


			Set<ui.Solution.Klauzula> pocetneKlauzule = new LinkedHashSet<>();

			String line = "";
			String lastLine = "";
			List<String> zadnjaLinija = new LinkedList<>();

			while ((line = bufferedReader.readLine()) != null) {
				if (line.charAt(0) == '#') {
					continue;
				} else {
					line = line.toLowerCase(Locale.ROOT);
					String input = line;
					String[] items = input.split(" v ");  // Split the string into an array

					Set<String> itemSet = new LinkedHashSet<>(Arrays.asList(items));  // Create a set from the array

					String output = String.join(" v ", itemSet);

					pocetneKlauzule.add(new ui.Solution.Klauzula(output, null, null, Arrays.asList(output.split(" v "))));
					// System.out.println(Arrays.stream(line.split(" v ")).toList());
					//  System.out.println( Arrays.asList(line.split(" v ")));
					//System.out.println(Arrays.asList(output.split(" v ")));
				}
				// System.out.println("kraj");
			}
			String linija = "";

			while ((linija = bufferedReaderdrugo.readLine()) != null) { // provjere cita li se plus minus ili upitnik i radnje sukladno procitano
				if (linija.endsWith("+")) {
					linija = linija.substring(0, linija.length() - 2);
					linija = linija.toLowerCase(Locale.ROOT);

					String input = linija;
					String[] items2 = input.split(" v ");  // Split the string into an array

					Set<String> itemSet2 = new LinkedHashSet<>(Arrays.asList(items2));  // Create a set from the array

					String output = String.join(" v ", itemSet2);

					pocetneKlauzule.add(new ui.Solution.Klauzula(output, null, null, Arrays.asList(output.split(" v "))));

//                    for (Klauzula k : pocetneKlauzule) {
//                        System.out.println(k.getSkupLiterala());
//                    }

				} else if (linija.endsWith("-")) {
					linija = linija.substring(0, linija.length() - 2);
					linija = linija.toLowerCase(Locale.ROOT);

					String input = linija;
					String[] items2 = input.split(" v ");  // Split the string into an array

					Set<String> itemSet2 = new LinkedHashSet<>(Arrays.asList(items2));  // Create a set from the array

					String output = String.join(" v ", itemSet2);
					ui.Solution.Klauzula nova = new ui.Solution.Klauzula(output, null, null, Arrays.asList(output.split(" v ")));

					List<ui.Solution.Klauzula> lista = new ArrayList<>(pocetneKlauzule);
					List<ui.Solution.Klauzula> listaaaa = new ArrayList<>(pocetneKlauzule);

					for (int i = 0; i < lista.size(); i ++) {
						if (lista.get(i).getSkupLiterala().containsAll(nova.getSkupLiterala()) && nova.getSkupLiterala().containsAll(lista.get(i).getSkupLiterala())) {
							listaaaa.remove(lista.get(i));
						}
					}

					Set<ui.Solution.Klauzula> kkk = new LinkedHashSet<>(listaaaa);
					pocetneKlauzule = kkk;

//					for (ui.Solution.Klauzula k : pocetneKlauzule) {
//						System.out.println(k.getSkupLiterala());
//					}



				} else if (linija.endsWith("?")) {

					linija = linija.substring(0, linija.length() - 2);
					linija = linija.toLowerCase(Locale.ROOT);


					Set<ui.Solution.Klauzula> goalKlauzole = new LinkedHashSet<>();
					// System.out.println(zadnja.klauzula);

					for (String s : linija.split(" v ")) {
						if (s.charAt(0) == '~') {


							goalKlauzole.add(new ui.Solution.Klauzula(String.valueOf(s.charAt(1)), null, null, Arrays.asList(String.valueOf(s.charAt(1)))));
						} else {
							goalKlauzole.add(new ui.Solution.Klauzula("~" + s, null, null, Arrays.asList("~" + s)));
						}
					}

					Set<ui.Solution.Klauzula> zadnjePocetne = izbaciTautologije(pocetneKlauzule);
					zadnjePocetne = izbaciNevaznaStanje(zadnjePocetne);

					nadiRjesenje(zadnjePocetne, goalKlauzole, linija);
					System.out.println();
					System.out.println();
					System.out.println();






				}
			}





		} else { // ako nije coocking radi onda samo ovo, ucitaj podatke iz samo jedne datoteke i posalji u finkciju nadi rjesenje

			File file = new File(args[1]);

			FileInputStream f = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(f, StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(isr);

			Set<ui.Solution.Klauzula> pocetneKlauzule = new LinkedHashSet<>();


			String line = "";
			String lastLine = "";
			List<String> zadnjaLinija = new LinkedList<>();

			while ((line = bufferedReader.readLine()) != null) {
				if (line.charAt(0) == '#') {
					continue;
				} else {
					line = line.toLowerCase(Locale.ROOT);
					String input = line;
					String[] items = input.split(" v ");  // Split the string into an array

					Set<String> itemSet = new LinkedHashSet<>(Arrays.asList(items));  // Create a set from the array

					String output = String.join(" v ", itemSet);

					pocetneKlauzule.add(new ui.Solution.Klauzula(output, null, null, Arrays.asList(output.split(" v "))));
					// System.out.println(Arrays.stream(line.split(" v ")).toList());
					//  System.out.println( Arrays.asList(line.split(" v ")));
					//System.out.println(Arrays.asList(output.split(" v ")));
				}
				lastLine = line;
				// System.out.println("kraj");
			}
			ui.Solution.Klauzula zadnja = new ui.Solution.Klauzula(null, null, null, null);
			for (ui.Solution.Klauzula k : pocetneKlauzule) {
				if (k.klauzula.equals(lastLine)) {
					zadnja = k;

				}
			}
			pocetneKlauzule.remove(zadnja);


			Set<ui.Solution.Klauzula> goalKlauzole = new LinkedHashSet<>();
			// System.out.println(zadnja.klauzula);

			for (String s : zadnja.klauzula.split(" v ")) {
				if (s.charAt(0) == '~') {


					goalKlauzole.add(new ui.Solution.Klauzula(String.valueOf(s.charAt(1)), null, null, Arrays.asList(String.valueOf(s.charAt(1)))));
				} else {
					goalKlauzole.add(new ui.Solution.Klauzula("~" + s, null, null, Arrays.asList("~" + s)));
				}
			}


			Set<ui.Solution.Klauzula> zadnjePocetne = izbaciTautologije(pocetneKlauzule);
			zadnjePocetne = izbaciNevaznaStanje(zadnjePocetne);
			for (ui.Solution.Klauzula s : zadnjePocetne) {
				//
				// System.out.println(s.getSkupLiterala() + "  zadnje pocetne");
			}
			ui.Solution.Klauzula ciljna = new ui.Solution.Klauzula(lastLine, null, null, Arrays.asList(lastLine.split(" v ")));


//        Klauzula prva = new Klauzula("C v B v ~D v ~S", null, null, Arrays.asList("C v B v ~D v ~S".split(" v ")));
//        Klauzula druga = new Klauzula("~S", null, null, Arrays.asList("~S".split(" v ")));
//
//        usporediDvije(prva, druga);

			nadiRjesenje(zadnjePocetne, goalKlauzole, lastLine);
		}
//        String li = "~water";
//
//       // System.out.println(izbaciNevaznaStanja2(pocetneKlauzule, new Klauzula(li, null, null, Arrays.asList(li.split(" v ")) )));
//
//        Klauzula kk = new Klauzula(li, null, null, Arrays.asList(li.split(" v ")) );
//        Set<Klauzula> ss = izbaciNevaznaStanja2(pocetneKlauzule, kk);
//
//        if (ss == null) {
//            System.out.println(ss);
//        } else {
//            for (Klauzula k : ss) {
//                System.out.println(k.getSkupLiterala());
//                System.out.println(k.skupLiterala.size());
//            }
//        }
		//System.out.println(ss.toString());

//        String li = "b";
//        String lii = "v";
//
//
//       // System.out.println(izbaciNevaznaStanja2(pocetneKlauzule, new Klauzula(li, null, null, Arrays.asList(li.split(" v ")) )));
//
//        Klauzula kk = new Klauzula(li, null, null, Arrays.asList(li.split(" v ")) );
//        kk.setDrugiRoditelj(kk);
//
//        Klauzula kk2 = new Klauzula(lii, null, null, Arrays.asList(lii.split(" v ")) );
//
//
//        System.out.println(provjeriPostojiLiVec(kk, pocetneKlauzule));
//
//        for (Klauzula k : pocetneKlauzule) {
//            System.out.println(k.skupLiterala + " ovo je pocetni");
//        }
//        System.out.println(kk.skupLiterala + " ovo je koju ispitujem");
//


//        for (Klauzula k : pocetneKlauzule) {
//            System.out.println(k.getSkupLiterala() + "  ovo je sve");
//        }
//        System.out.println(kk.getSkupLiterala());
	}


	public static void ispisiRjesenje(Set<ui.Solution.Klauzula> pocetneKlauzule, Set<ui.Solution.Klauzula> goal, ui.Solution.Klauzula prvaa, ui.Solution.Klauzula drugaa, String lastline) {
		//funkcija za pronalazak rjesnja. Dohvaca sve roditelje te ih ispisuje.. prvo one koji se nalaze u pocetnik klauzalama pa ostale
		List<ui.Solution.Klauzula> sve = new ArrayList<>();
		sve.addAll(prvaa.getAllParents());
		sve.addAll(drugaa.getAllParents());


		sve.add(prvaa);
		sve.add(drugaa);

//        for (Klauzula k : sve) {
//            System.out.println(k.getSkupLiterala() + " ovo je sve");
//        }


		List<ui.Solution.Klauzula> commonElements = new ArrayList<>(pocetneKlauzule);
		commonElements.retainAll(sve);



		List<ui.Solution.Klauzula> uniqueElements = new ArrayList<>(sve);
		uniqueElements.removeAll(commonElements);

		List<ui.Solution.Klauzula> uniDruga = uniqueElements;




//        for (Klauzula k : uniqueElements) {
//            if (goal.contains(k)) {
//                uniDruga.remove(k);
//            }
//        }

		for (int i = 0; i < uniqueElements.size(); i ++ ) {
			if (goal.contains(uniqueElements.get(i))) {
				uniDruga.remove(uniqueElements.get(i));
			}
		}
		uniqueElements = uniDruga;

		for (ui.Solution.Klauzula k : uniqueElements) {
			// System.out.println(k.getSkupLiterala() + "  uni druga");
		}

		//    System.out.println("ISPISSSSS");

		for (ui.Solution.Klauzula k : commonElements) {
			System.out.println(k.klauzula);
		}
		for (ui.Solution.Klauzula k : goal) {
			System.out.println(k.klauzula);
		}
		System.out.println("============");

//        for (Klauzula k : uniqueElements) {
//            System.out.println(k.getSkupLiterala() + " unique");
//        }

		for (ui.Solution.Klauzula k : uniqueElements) {
			System.out.println(k.klauzula + " (" + k.getPrviRoditelj().klauzula + " + " +  k.getDrugiRoditelj().klauzula + ")");
		}
		System.out.println("NILL (" + prvaa.klauzula + ", " + drugaa.klauzula + ")");
		String ispis = "";
		for (ui.Solution.Klauzula k : goal) {
			ispis += k.klauzula + " ";
		}
		ispis = Arrays.stream(ispis.split(" ")).collect(Collectors.joining(" v "));
		System.out.println("[CONCLUSION]: " + lastline + " is true");
//
//        for (Klauzula k : uniqueElements) {
//            System.out.println(k.getSkupLiterala() + " ovo je jedinstveno");
//        }


//        for (Klauzula k : commonElements) {
//            System.out.println(k.getSkupLiterala());
//        }
//        List<Klauzula> svePrije = new ArrayList<>();
//
//        List<Klauzula> sve2 = new ArrayList<>();
//        sve2 = sve;
		//System.out.println(sve2.size());

//        for (int i = 0; i < sve2.size(); i++) {
//            System.out.println(sve2.size());
//            if (goal.contains(sve2.get(i))) {
//                sve.remove(sve2.get(i));
//                continue;
//            }
//            if (pocetneKlauzule.contains(sve2.get(i))) {
//                sve.remove(sve2.get(i));
//                svePrije.add(sve2.get(i));
//                continue;
//            }
//        }
//
//        for (Klauzula k : svePrije) {
//            System.out.println(k.getSkupLiterala());
//        }
//        for (Klauzula k : sve) {
//            if (goal.contains(k)) {
//                continue;
//            }
//                                            Klauzula prva = k.getPrviRoditelj();
//                                Klauzula druga = k.getDrugiRoditelj();
//                                if (prva == null && druga != null) {
//                                    System.out.println(k.skupLiterala + "  " + "null" + "  " + k.getDrugiRoditelj().klauzula);
//                                } else if(prva != null && druga == null) {
//                                    System.out.println(k.skupLiterala + "  " + k.getPrviRoditelj().klauzula + "  " + "null");
//
//                                } else if (prva == null && druga == null) {
//                                  System.out.println(k.skupLiterala + "  " + "null" + "  " + "null");
//
//                                } else {
//                                    System.out.println(k.skupLiterala + "  " + k.getPrviRoditelj().klauzula + "  " + k.getDrugiRoditelj().klauzula);
//
//                                }
//
//        }



	}

	public static boolean provjeriPostojiLiVec(ui.Solution.Klauzula rjesenje, Set<ui.Solution.Klauzula> sve) { // provjerava postoji li rjesenje u setu "sve"
		boolean areEqual;
		areEqual = false;

		for(ui.Solution.Klauzula k : sve) {
			if (rjesenje.getSkupLiterala().size() == k.getSkupLiterala().size()) {
				Collections.sort(k.getSkupLiterala());
				Collections.sort(rjesenje.getSkupLiterala());

				for (int i = 0; i < k.getSkupLiterala().size(); i++) {
					if (k.getSkupLiterala().get(i).equals(rjesenje.getSkupLiterala().get(i))) {
						areEqual = true;
						return areEqual;
					}
				}
			}
		}
		return areEqual;
	}



	public static void nadiRjesenje(Set<ui.Solution.Klauzula> pocetneKlauzule, Set<ui.Solution.Klauzula> goal, String lastline) {

		//algoritam za pronalazak rjesenja

		Set<ui.Solution.Klauzula> sveSve = new LinkedHashSet<>();
		Set<ui.Solution.Klauzula> pocetnePlusSos = new LinkedHashSet<>();
		Set<ui.Solution.Klauzula> sos = new LinkedHashSet<>();
		sos.addAll(goal);

		sveSve.addAll(pocetneKlauzule);
		sveSve.addAll(goal);
		pocetnePlusSos.addAll(pocetneKlauzule);
		pocetnePlusSos.addAll(goal);

		ui.Solution.Klauzula usporedi;
		boolean ubaci = true;
		Set<ui.Solution.Klauzula> oneKojeTrebaZamijeniti = new LinkedHashSet<>();

		while (true) { //beskonacno iteriranje
			Set<ui.Solution.Klauzula> kojeTrebaIzbaciti = new LinkedHashSet<>();
			Set<ui.Solution.Klauzula> novaStanja = new LinkedHashSet<>();
			for(ui.Solution.Klauzula glava : sos) { // iteriranje po sosu ( ispocetka samo prihvatljivo stanje )
				//System.out.println(glava.klauzula + "    glava");
				//  System.out.println(glava.klauzula + " glava" + glava.getSkupLiterala());
				for (ui.Solution.Klauzula klauz : pocetnePlusSos) { // iteriranje po pocetni + sos
					// System.out.println(klauz.klauzula + " klauzula" + klauz.getSkupLiterala());
					if (glava.equals(klauz)) {
						continue;
					}
					if (glava.getSkupLiterala().size() == 1) { //
						if (glava.klauzula.equals("~" + klauz.klauzula) || klauz.klauzula.equals("~" + glava.klauzula)) { // ukoliko smo dosli do rjesnja
							//  System.out.println("Nadeno rjesenje");


//                            for (Klauzula k : pocetnePlusSos) {
//                               // System.out.println(k.klauzula);
//                            }
//                            System.out.println(glava.klauzula + "ovo je glava");
//
//                            for (Klauzula k : pocetnePlusSos) {
//                                System.out.println(k.skupLiterala + " ovo je na kraju");
//                            }
//                            System.out.println(glava.klauzula + " glva");
//                            for (Klauzula k : glava.getAllParents()) {
//                                Klauzula prva = k.getPrviRoditelj();
//                                Klauzula druga = k.getDrugiRoditelj();
//                                if (prva == null && druga != null) {
//                                    System.out.println(k.skupLiterala + "  " + "null" + "  " + k.getDrugiRoditelj().klauzula);
//                                } else if(prva != null && druga == null) {
//                                    System.out.println(k.skupLiterala + "  " + k.getPrviRoditelj().klauzula + "  " + "null");
//
//                                } else if (prva == null && druga == null) {
//                                  System.out.println(k.skupLiterala + "  " + "null" + "  " + "null");
//
//                                } else {
//                                    System.out.println(k.skupLiterala + "  " + k.getPrviRoditelj().klauzula + "  " + k.getDrugiRoditelj().klauzula);
//
//                                }
//                            }
							//   System.out.println(glava.klauzula + "  ---   " + klauz.klauzula);
							//System.out.println("nadeno");
							ispisiRjesenje(pocetneKlauzule, goal, glava, klauz, lastline); // funkcija za ispis rjesenja

//
							return;
						}
					}

					if ((usporedi = usporediDvije(glava, klauz)) != null) { // ako smo dobili novu klauzulu


						// System.out.println("tu sam");
						if (provjeriPostojiLiVec(usporedi, pocetnePlusSos) == false) { // ako ne postoji vec
							// System.out.println(usporedi.skupLiterala + " ovo sam uspporedio i krivo je");

							//kojeTrebaIzbaciti.addAll(izbaciNevaznaStanja2(sveSve, usporedi));
							if (kojeTrebaIzbaciti != null) { // ako je dobra za unos u sos
								novaStanja.add(usporedi); // nova stanja nakon iteracija dodajemo u sos
							}
						}
					}
				}
//                for (Klauzula k : novaStanja) {
//                    sveSve.add(k);
//                }
			}
			if (novaStanja.isEmpty()) { // ako se ne napravi nijedno novo stanje znaci da nema rjesnja te ispisujemo da je unknown
				System.out.println("[CONCLUSION]: " + lastline + " is unknown");
				return;
			}
			for (ui.Solution.Klauzula k : novaStanja) { // nova stanja dodajemo u sos i u listu sos + pocetne
				sos.add(k);
				//System.out.println("dodajem " + k.getSkupLiterala());
				sveSve.add(k);
				pocetnePlusSos.add(k);
			}
//			for (Klauzula k : kojeTrebaIzbaciti) {
//				sos.remove(k);
//				pocetnePlusSos.remove(k);
//			}
		}

	}










	public static ui.Solution.Klauzula usporediDvije(ui.Solution.Klauzula prva, ui.Solution.Klauzula druga) {
		// funkcija koja gleda dvije klauzule te gleda mogu li te dvije stvoriti novu klauzulu
		ui.Solution.Klauzula vrati = null;
		List<String> novaLista = new ArrayList<>();

		for (String s : prva.getSkupLiterala()){
			for (String s2 : druga.getSkupLiterala()) {

				// System.out.println("tu sam" + s + " " + "~" + s2);
				//System.out.println(s2 + " es dva");
				//   System.out.println(s + "    " + s2);

				if (s.equals("~" + s2) || s2.equals("~" + s)){ // ukoliko postoji u jednoj i drugoj dva suprotna clana

					List<String> rjesenje = new ArrayList<>();
					rjesenje.addAll(prva.getSkupLiterala());
					rjesenje.addAll(druga.getSkupLiterala());



					novaLista =  izbaciTautologije2(rjesenje);

					Set<String> itemSet = new LinkedHashSet<>(novaLista);  // Create a set from the list

					novaLista = new ArrayList<>(itemSet);


					vrati = new ui.Solution.Klauzula(novaLista.stream().collect(Collectors.joining(" v ")) , prva, druga, novaLista);
					//System.out.println(vrati.klauzula);
					return vrati; //vracam novu ako je nasao
					// System.out.println(novaLista);
				}
			}
		}
		//System.out.println(vrati.klauzula);
		return vrati; // vracam null ako nisam


	}
	//    public static List<Klauzula> izbaciNevaznaStanje2 (List<Klauzula> pocetneKlauzule, Klauzula novaKlauzola) {
//        List<Klauzula> vrati = pocetneKlauzule;
//        Set<Klauzula> nesto = new LinkedHashSet<>();
//
//        Klauzula[] vrti = new Klauzula[pocetneKlauzule.size()];
//        pocetneKlauzule.toArray(vrti);
//
//       // for (int i = 0; i < vrti.length; i++) {
//            for (int i = 0; i < vrti.length; i++) {
//                // System.out.println(vrti[i].skupLiterala + "   " + vrti[j].skupLiterala + " ovo je ");
//                if (vrti[i].skupLiterala.containsAll(novaKlauzola.skupLiterala) || novaKlauzola.skupLiterala.containsAll(vrti[i].skupLiterala)) {
//                    // System.out.println("tu sam");
//                    if (vrti[i].skupLiterala.size() >novaKlauzola.skupLiterala.size()) {
//                        vrati.remove(vrti[i]);
//                    } else {
//                        //vrati.remove(vrti[j]);
//                        return null;
//                    }
//                }
//           // }
//        }
//        return vrati;
//    }
	public static Set<ui.Solution.Klauzula> izbaciNevaznaStanja2(Set<ui.Solution.Klauzula> sveKlauzoleDosad, ui.Solution.Klauzula novaKlauzola) {

		//algoritam koji mi goovori hocu li ubaciti novu klauzulu i koji mi daje listu klauzula koje ona izbacuje

		Set<ui.Solution.Klauzula> vrati = new LinkedHashSet<>();


//        for (Klauzula k : sveKlauzoleDosad) { //prvo radimo ako je veca od neke onda je ne stavljamo
//            if (novaKlauzola.getSkupLiterala().containsAll(k.getSkupLiterala())) {
//                if (novaKlauzola.getSkupLiterala().size() > k.getSkupLiterala().size()) {
//                    return null;
//                }
//            }
//        }

		for (ui.Solution.Klauzula k : sveKlauzoleDosad) { // ako neka vec klauzula je manja od nove a sadrzi sve elemente ko nova
			if (k.getSkupLiterala().containsAll(novaKlauzola.getSkupLiterala()) || novaKlauzola.getSkupLiterala().containsAll(k.getSkupLiterala())) {
				if (novaKlauzola.getSkupLiterala().size() > k.getSkupLiterala().size()) {
					return null;
				}
			}
		}

		for (ui.Solution.Klauzula k : sveKlauzoleDosad) { // ako je nova manja od stare a sadrzi sve elemente ko stara. tadaa staru ubacujem u listu za vratiti te cu kasnije sve iz te liste izbaciti iz pocetne + sos
			if (k.getSkupLiterala().containsAll(novaKlauzola.getSkupLiterala()) || novaKlauzola.getSkupLiterala().containsAll(k.getSkupLiterala())) {
				if (novaKlauzola.getSkupLiterala().size() < k.getSkupLiterala().size()) {
					vrati.add(k);
				}
			}
		}

		return vrati;
	}

	public static Set<ui.Solution.Klauzula> izbaciNevaznaStanje(Set<ui.Solution.Klauzula> pocetneKlauzule) {
		//funkcija koja izbacuje nevazna stanja kod pocetnih klauzula
		Set<ui.Solution.Klauzula> vrati = pocetneKlauzule;

		ui.Solution.Klauzula[] vrti = new ui.Solution.Klauzula[pocetneKlauzule.size()];
		pocetneKlauzule.toArray(vrti);

		//iteriranje po svim klauzulama i gledanje je li neka podskup druge i izbacivanje dulje klauzule
		for (int i = 0; i < vrti.length; i++) {
			for (int j = i + 1; j < vrti.length; j++) {
				// System.out.println(vrti[i].skupLiterala + "   " + vrti[j].skupLiterala + " ovo je ");
				if (vrti[i].skupLiterala.containsAll(vrti[j].skupLiterala) || vrti[j].skupLiterala.containsAll(vrti[i].skupLiterala)) {
					// System.out.println("tu sam");
					if (vrti[i].skupLiterala.size() > vrti[j].skupLiterala.size()) {
						vrati.remove(vrti[i]);
					} else {
						vrati.remove(vrti[j]);
					}
				}
			}
		}
		return vrati;
	}

	public static List<String> izbaciTautologije2(List<String> pocetneKlauzule) {

		//funckija koja izbacuje tautologije iz liste stringova

		List<String> vrati = new ArrayList<>();
		vrati.addAll(pocetneKlauzule);

		for (String iteral : pocetneKlauzule) {

			if (iteral.startsWith("~")) {
				String key = iteral.substring(1);
				if (pocetneKlauzule.contains(key)) {
					vrati.remove(iteral);
					vrati.remove(key);
				}
			}
//            if (iteral.length() > 1) {
//                if (pocetneKlauzule.contains(" " + iteral.charAt(1))) {
//                    vrati.remove(iteral);
//                    vrati.remove(String.valueOf(iteral.charAt(1)));
//                }
//            } else {
//                if (pocetneKlauzule.contains("~" + iteral.charAt(0))) {
//                    vrati.remove(iteral);
//                    vrati.remove("~" + iteral);
//                }
//            }
		}

		return vrati;
	}

	public static Set<ui.Solution.Klauzula> izbaciTautologije(Set<ui.Solution.Klauzula> pocetneKlauzule) {

		// funkcija koja izbacuje tautologije iz liste klauzula
		Set<ui.Solution.Klauzula> vrati = pocetneKlauzule;
		ui.Solution.Klauzula[] vrti = new ui.Solution.Klauzula[pocetneKlauzule.size()];
		pocetneKlauzule.toArray(vrti);
		//vrati.addAll(pocetneKlauzule);


		for(int i = 0; i < vrti.length; i ++) {
			//System.out.println(klauzula.klauzula);
			for (String iteral : vrti[i].klauzula.split(" v ")) {
				if (iteral.startsWith("~")) {
					String key = iteral.substring(1);
					if (vrti[i].getSkupLiterala().contains(key)) {
						vrati.remove(vrti[i]);
					}
				}
//               if (iteral.length() > 1) {
//                    if (vrti[i].klauzula.contains(" " + iteral)) {
//                        vrati.remove(vrti[i]);
//                    }
//                } else {
//                    if (vrti[i].klauzula.contains("~" + iteral)) {
//                        vrati.remove(vrti[i]);
//                    }
//                }
			}
		}
		return vrati;
	}


	public static class Klauzula {

		// razred klauzula koji ima svoj string odnosno klauzulu, pokazivac na prvog i drugog roditelja i skup literala zadan
		// kao lista
		public String getKlauzula() {
			return klauzula;
		}

		public void setKlauzula(String klauzula) {
			this.klauzula = klauzula;
		}

		public ui.Solution.Klauzula getPrviRoditelj() {
			return prviRoditelj;
		}

		public void setPrviRoditelj(ui.Solution.Klauzula prviRoditelj) {
			this.prviRoditelj = prviRoditelj;
		}

		public ui.Solution.Klauzula getDrugiRoditelj() {
			return drugiRoditelj;
		}

		public void setDrugiRoditelj(ui.Solution.Klauzula drugiRoditelj) {
			this.drugiRoditelj = drugiRoditelj;
		}

		public List<String> getSkupLiterala() {
			return skupLiterala;
		}

		public void setSkupLiterala(List<String> skupLiterala) {
			this.skupLiterala = skupLiterala;
		}

		private String klauzula;
		private ui.Solution.Klauzula prviRoditelj;
		private ui.Solution.Klauzula drugiRoditelj;
		private List<String> skupLiterala;


		Klauzula(String klauzula, ui.Solution.Klauzula prviRoditelj, ui.Solution.Klauzula drugiRoditelj, List<String> skupLiterala) {
			this.klauzula = klauzula;
			this.prviRoditelj = prviRoditelj;
			this.drugiRoditelj = drugiRoditelj;
			this.skupLiterala = skupLiterala;
		}

		//funckija koja vraca sve roditelje odredene klauzule.. znaci vraca i prvog i drugog sve dok nisu svi roditelji null
		public List<ui.Solution.Klauzula> getAllParents() {
			List<ui.Solution.Klauzula> parents = new ArrayList<>();
			getAllParentsHelper(this.prviRoditelj, parents);
			getAllParentsHelper(this.drugiRoditelj, parents);
			return parents;
		}
		//ovo je isto ta funckija koja izbacuje nove roditelje
		private void getAllParentsHelper(ui.Solution.Klauzula parent, List<ui.Solution.Klauzula> parents) {
			if (parent != null) {
				parents.add(parent);
				getAllParentsHelper(parent.prviRoditelj, parents);
				getAllParentsHelper(parent.drugiRoditelj, parents);
			}
		}


	}
}
