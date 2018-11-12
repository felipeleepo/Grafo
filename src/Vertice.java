public class Vertice {
    private Object Valor;

    public Vertice(Object Valor) {
        this.Valor = Valor;
    }

    public Object getValor() {
        return Valor;
    }
    
    @Override
    public String toString(){
        return "V["+getValor()+"]";
    }
}
