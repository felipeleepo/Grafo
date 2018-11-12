public class Aresta {
    private Vertice Inicio, Fim;
    private Object Custo;

    public Aresta(Vertice Inicio, Vertice Fim, Object Custo) {
        this.Inicio = Inicio;
        this.Fim = Fim;
        this.Custo = Custo;
    }
    
    public Object getInicioValor(){
        return Inicio.getValor();
    }
    
    public Object getFimValor(){
        return Fim.getValor();
    }

    public Object getCusto() {
        return Custo;
    }

    public Vertice getInicio() {
        return Inicio;
    }

    public Vertice getFim() {
        return Fim;
    }

    public void setInicio(Vertice Inicio) {
        this.Inicio = Inicio;
    }

    public void setFim(Vertice Fim) {
        this.Fim = Fim;
    }

    public void setCusto(Object Custo) {
        this.Custo = Custo;
    }
    
    @Override
    public String toString(){
        return "A["+getInicioValor()+","+getFimValor()+"]("+getCusto()+")";
    }
}
