class Principal {
    public static void main(String[] args) {
	Grafo g = new Grafo();
        g.AdicionarVertice();
        g.AdicionarVertice();
        g.AdicionarVertice();
        g.AdicionarVertice();
        g.AdicionarAresta("0", "0", 50);
        g.AdicionarAresta("0", "0", 20);
        g.AdicionarAresta("1", "4", 50);
        g.ImprimirVertices();
        g.ImprimirArestas();
        g.SubstituirVertice("1", "4");
        g.SubstituirVertice("1", "4");
        g.MatrizAdjacencia();
        g.MatrizIncidencia();
        g.SubstituirAresta("0", 100);
        //g.RemoverAresta("0", "0", 100);
        g.ImprimirArestas();
        g.MatrizCusto();
    }
}
