import java.util.ArrayList;
import java.util.List;
public class Grafo {
    List<Vertice> V;
    ArrayList<Aresta> A;

    public Grafo() {
        V = new ArrayList<Vertice>();
        A = new ArrayList<Aresta>();
    }
    /* ADICIONAR */
    public void AdicionarVertice(Object valor){        
        V.add(new Vertice(valor));
        System.out.println("ADICIONAR VÉRTICE");
        System.out.println(V.get(V.size()-1).toString());
    }
    
    public void AdicionarAresta(Object x, Object y, Object c){
        System.out.println("ADICIONAR ARESTA");
        Vertice Inicio = ExisteVertice(x); 
        Vertice Fim = ExisteVertice(y);
        if(Inicio == null) 
            System.out.println("Primeiro Vértice não existe");            
        else if(Fim == null)
            System.out.println("Segundo Vértice não existe");
        else{
            A.add(new Aresta(Inicio, Fim, c));
            System.out.println(A.get(A.size()-1).toString());
        }
    }
    /*FIM ADICIONAR*/
    
    /* REMOCAO */
    public void RemoverVertice(Object valor){
        System.out.println("REMOÇÃO VÉRTICE");
        String op = "Valor não Encontrado";
        Vertice v = ExisteVertice(valor);
        if(v != null){
            op = (String)v.getValor();
            V.remove(v);
        }
        System.out.println(op);
    }
    
    public void RemoverAresta(Object v1, Object v2){
        System.out.println("REMOÇÃO ARESTA");
        String op = "Valor não Encontrado";
        Aresta a = ExisteAresta(v1, v2);
        if(a != null){
            op = a.toString();
            A.remove(a);
        }
        System.out.println(op);
    }
    /* FIM REMOCAO */
    
    /* COMPARACAO */
    public Vertice ExisteVertice(Object valor){
        for(Vertice v : this.V)
            if(v.getValor() == valor)
                return v;
        return null;
    }
    
    public Aresta ExisteAresta(Object v1, Object v2){
        for(Aresta a : this.A)
            if(a.getInicioValor() == v1 && a.getFimValor() == v2)
                return a;
        return null;
    }
    
    public boolean ÉAdjacente(Vertice v1, Vertice v2){
        for (Aresta i : A)
            if((i.getInicio() == v1 && i.getFim() == v2) || (i.getInicio() == v2 && i.getFim() == v1))
                return true;
        return false;
    }
    /* FIM COMPARACAO*/
    
    /* IMPRESSAO */
    public void ImprimirVertices(){
        int qt = 0;
        System.out.println("LISTAR VÉRTICES");
        for (Vertice i : V) {
            System.out.println(qt + " - " + i.getValor());
            qt++;
        }
    }
    
    public void ImprimirArestas(){
        int qt = 0;
        System.out.println("LISTAR ARESTAS");
        for (Aresta i : A) {
            System.out.println(qt + " - (" + i.getInicioValor()+","+i.getFimValor()+")["+i.getCusto()+"]");
            qt++;
        }
    }
    
    public void MatrizAdjacencia(){
        System.out.println("MATRIZ ADJACÊNCIA");
        for (Vertice i : V) {
            if(V.indexOf(i) == 0){
                for (Vertice j : V) {
                    if (V.indexOf(j) == 0)                    
                        System.out.print("  " + j.getValor() + "|");
                    else if(V.indexOf(i) < V.size())
                        System.out.print(j.getValor() + "|");
                    else
                        System.out.print(j.getValor());
                }
                System.out.println("");
            }
            for (Vertice j : V) {
                if (V.indexOf(j) == 0)  
                    System.out.print(i.getValor() + "|");
                if(ÉAdjacente(i,j))
                    System.out.print("1|");
                else
                    System.out.print("0|");
            }
            System.out.println("");
        }
    }
    /* FIM IMPRESSAO*/
}
