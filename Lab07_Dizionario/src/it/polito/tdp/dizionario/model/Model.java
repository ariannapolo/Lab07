package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	UndirectedGraph<String, DefaultEdge> grafo;
	
	public List<String> createGraph(int numeroLettere) {
		grafo= new Multigraph<String, DefaultEdge>(DefaultEdge.class);
		WordDAO wdao= new WordDAO();
		List<String> lista = new ArrayList<String>(wdao.getAllWordsFixedLength(numeroLettere));
		for(String s : lista){
			checkWord(s,lista);
		}
		
		return wdao.getAllWordsFixedLength(numeroLettere) ;
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
	
	
}
