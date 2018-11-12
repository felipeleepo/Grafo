class Principal {
    public static void main(String[] args) {
	Grafo g = new Grafo();
        g.AdicionarVertice("a");
        g.AdicionarVertice("b");
        g.AdicionarVertice("c");
        g.AdicionarVertice("d");
        g.AdicionarAresta("a", "b", 50);
        g.AdicionarAresta("b", "b", 20);
        g.AdicionarAresta("b", "d", 20);
       // g.ImprimirVertices();
       // g.ImprimirArestas();
        g.MatrizAdjacencia();
        g.RemoverAresta("b", "b");
        g.MatrizAdjacencia();
    }
}
