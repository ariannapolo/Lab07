package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	UndirectedGraph<String, DefaultEdge> grafo;
	ArrayList<String> listaTuttiVicini;
		
	public List<String> createGraph(int numeroLettere) {
		grafo= new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		WordDAO wdao= new WordDAO();
		List<String> lista = new ArrayList<String>(wdao.getAllWordsFixedLength(numeroLettere));
		for(String s : lista){
			checkWord(s,lista);
			//grafo.addVertex(s);
		}
			
//			for(String v : grafo.vertexSet()){
//				List<String> list = new ArrayList<String>(wdao.getAllSimilarWords(v, numeroLettere));
//				for(String s: list){
//					if(s.compareTo(v)!=0)
//						grafo.addEdge(v, s);
//				}
//				
				
			//}
		
		
		
		return lista;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		return Graphs.neighborListOf(grafo, parolaInserita);
	}

	public String findMaxDegree() {
		int max=0;
		String smax="";
		for(String s: grafo.vertexSet()){
			int degree= grafo.degreeOf(s);
			if(degree >max){
				smax = s;
				max = degree;
			}
				
		}
		return "vertice: "+smax+" grado: "+max;
	}
	
	private void checkWord(String word, List<String> listaDAO){
			for(String s : listaDAO){
				int cnt = 0;
				for(int i=0; i<s.length();i++){
					if(s.charAt(i)!=word.charAt(i))
						cnt++;
				}
				if(cnt==1){
					grafo.addVertex(s);
					grafo.addVertex(word);
					grafo.addEdge(s, word);
				}
			}
		
	}
	
	public List<String> displayAllNeighbours(String parolaInserita) {
		//SOLUZIONE ITERATIVA
//		LinkedList<String> daVisitare = new LinkedList<String>();
//		ArrayList<String> visitati = new ArrayList<String>();
//		daVisitare.add(parolaInserita);
//		
//		while(daVisitare.size()!=0){
//			String s = daVisitare.get(0);
//			for(String v : this.displayNeighbours(s)){
//				if(!daVisitare.contains(v)&& !visitati.contains(v)){
//					daVisitare.add(v);
//				}
//			}
//			daVisitare.remove(0);
//			if(s.compareTo(parolaInserita)!=0)
//				visitati.add(s);
//		}
//		
//		//System.out.println("Dimensione visitati: "+visitati.size());
//		return visitati;
		
		//SOLUZIONE RICORSIVA
		 listaTuttiVicini = new ArrayList<String>();
		 recursive( parolaInserita);
		 System.out.println("Dimensione visitati: "+listaTuttiVicini.size());
		return listaTuttiVicini;
	}
	
	private void recursive(String parola){
		for(String s : this.displayNeighbours(parola)){
			if(!listaTuttiVicini.contains(s)){
				listaTuttiVicini.add(s);
				recursive(s);
			}
		}
		
	}
	
}
