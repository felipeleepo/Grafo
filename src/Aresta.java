public class Aresta {
    Vertice Inicio, Fim;
    Object Custo;
    String nome;

    public Aresta(Vertice Inicio, Vertice Fim, Object Custo, String nome) {
        this.Inicio = Inicio;
        this.Fim = Fim;
        this.Custo = Custo;
        this.nome = nome;
    }@Override
    public String toString(){
        return nome+"("+Inicio.Valor+","+Fim.Valor+")["+Custo+"]";
    }
    
    public Object FinalVertice(){
        return Fim.Valor;
    }
}