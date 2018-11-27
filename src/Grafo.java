import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import jdk.nashorn.internal.objects.NativeArray;
public class Grafo {
    List<Vertice> V;
    ArrayList<Aresta> A;
    int espaco = 1;
    public Grafo() {
        V = new ArrayList<Vertice>();
        A = new ArrayList<Aresta>();
    }
    /* ADICIONAR */
    public void AdicionarVertice(){ 
        System.out.print("ADICIONAR VÉRTICE = ");
        V.add(new Vertice("v"+V.size()));
        System.out.println(V.get(V.size()-1).toString());
    }
    
    public void AdicionarAresta(String x, String y, Object c){
        System.out.print("ADICIONAR ARESTA = ");
        Vertice Inicio = ExisteVertice(x); 
        Vertice Fim = ExisteVertice(y);
        if(Inicio == null) 
            System.out.println("Primeiro Vértice não existe");            
        else if(Fim == null)
            System.out.println("Segundo Vértice não existe");
        else if(ExisteAresta(Inicio.Valor, Fim.Valor, c) != null){
            System.out.println("Já Existe essa Aresta");
        }else{            
            Aresta a = new Aresta(Inicio, Fim, c, "a"+A.size());
            A.add(a);
            System.out.println(A.get(A.size()-1).toString());
            Espacamento(c, true);
        }
    }
    /*FIM ADICIONAR*/
    
    /* REMOCAO */
    public void RemoverVertice(String valor){
        System.out.println("REMOÇÃO VÉRTICE");
        String op = "Valor não Encontrado";
        Vertice v = ExisteVertice(valor);
        if(v != null){
            op = (String)v.Valor;
            V.remove(v);
        }
        System.out.println(op);
    }
    
    public void RemoverAresta(String v1, String v2, Object custo){
        System.out.print("REMOÇÃO ARESTA = ");
        String op = "Valor não Encontrado";
        Aresta a = ExisteAresta(v1, v2, custo);
        if(a != null){
            op = a.toString();
            A.remove(a);
            Espacamento(custo, false);
        }
        System.out.println(op);
    }
    /* FIM REMOCAO */
    
    /* COMPARACAO */
    public Vertice ExisteVertice(String valor){
        valor = "v" + valor;
        for(Vertice v : this.V)
            if(v.Valor.equals(valor))
                return v;
        return null;
    }
    
    public Aresta ExisteAresta(String v1, String v2, Object custo){
        v1 = "v"+v1;
        v2 = "v"+v2;
        for(Aresta a : this.A)
            if(a.Inicio.Valor.equals(v1) && a.Fim.Valor.equals(v2) && a.Custo == custo)
                return a;
        return null;
    }
    
    public Aresta ExisteArestaSemCusto(Vertice v1, Vertice v2){
        for(Aresta a : this.A)
            if(a.Inicio == v1 && a.Fim == v2)
                return a;
        return null;
    }
    
    public Aresta ExisteArestaNome(String nome){
        for (Aresta a : A)
            if(a.nome.equals(nome))
                return a;
        return null;
    }
    public boolean ÉAdjacente(Vertice v1, Vertice v2){
        for (Aresta i : A)
            if((i.Inicio == v1 && i.Fim == v2))
                return true;
        return false;
    }
    /* FIM COMPARACAO*/
    
    /* CALCULO */
    public int Adjacencia(Vertice i, Vertice j){
        int total = 0;
        for (Aresta a : A)
            if((a.Inicio == i && a.Fim == j))
                total++;
        return total;
    }
    
    public String TotalCusto(Vertice i, Vertice j){
        int total = 0;
        for (Aresta a : A)
            if((a.Inicio == i && a.Fim == j))
                total += (Integer)a.Custo;
        return ""+total;
    }
    
    public void Distancia(String inicio, String fim){
        System.out.println("MENOR CAMINHO = v" + inicio + "->v" + fim );
        Vertice Inicio = ExisteVertice(inicio);
        Vertice Fim = ExisteVertice(fim);
        if(Inicio == null || Fim == null){
            System.out.println("Vértice(s) Inexiste(s)");
        }else{
            List<Caminho> Abertos = new ArrayList<Caminho>();
            List<Caminho> Fechados = new ArrayList<Caminho>();
            
            Caminho CaminhoPai = new Caminho();
            CaminhoPai.v = Inicio;
            CaminhoPai.peso = FuncaoL(Inicio, Inicio);
            CaminhoPai.Ant = null;
            Abertos.add(CaminhoPai);
            
            while(!CaminhoPai.v.equals(Fim)){
                List<Caminho> adjacentes = CaminhosAdjacentes(CaminhoPai);
                for (int i = 1; i < adjacentes.size(); i++) {
                    if(CaminhoPai.Ant != null)
                        adjacentes.get(i).Ant.peso = CaminhoPai.Ant.peso + FuncaoL(CaminhoPai.v, adjacentes.get(i).v);
                    else
                        adjacentes.get(i).Ant.peso = FuncaoL(CaminhoPai.v, adjacentes.get(i).v);                    
                    adjacentes.get(i).peso = adjacentes.get(i).Ant.peso;
                    if(!Fechados.contains(adjacentes.get(i)))
                        adjacentes.get(i).Ant = CaminhoPai;
                    Abertos.add(adjacentes.get(i));
                }
                Fechados.add(CaminhoPai);
                Abertos.remove(CaminhoPai);
                
                CaminhoPai = MenorCaminhoAdjacente(adjacentes);                
            }
            
            Caminho aux = CaminhoPai;
            int custo = 0;
            String texto = "";
            System.out.println("Caminho a Percorrer:");
            while(aux != null){
                custo += aux.peso;
                if(aux.Ant == null)
                    texto = aux.v.Valor + "["+aux.peso+"]" + texto;
                else
                    texto = " -> " + aux.v.Valor + "["+aux.peso+"]" + texto;
                aux = aux.Ant;
            }
            System.out.println(texto + "\nCusto Total: " + custo);
        }
    }
    
    public int FuncaoL(Vertice I, Vertice F){
        Aresta a = ExisteArestaSemCusto(I,F);
        if (I == F)
            return 0;
        else if(a != null)
            return (Integer)a.Custo;
        else 
            return 999;
    }
    /* FIM CALCULO */
    
    /* IMPRESSAO */
    public void ImprimirVertices(){
        int qt = 0;
        System.out.println("LISTAR VÉRTICES");
        for (Vertice i : V) {
            System.out.println(i.Valor);
            qt++;
        }
    }
    
    public void ImprimirArestas(){
        int qt = 0;
        System.out.println("LISTAR ARESTAS");
        for (Aresta i : A) {
            System.out.println(i.toString());
            qt++;
        }
    }
    
    public void MatrizAdjacencia(){
        System.out.println("MATRIZ ADJACÊNCIA");
        for (Vertice i : V) {
            if(V.indexOf(i) == 0){
                for (Vertice j : V) {
                    if (V.indexOf(j) == 0)                    
                        System.out.print("   " + j.Valor + "|");
                    else if(V.indexOf(i) < V.size())
                        System.out.print(j.Valor + "|");
                    else
                        System.out.print(j.Valor);
                }
                System.out.println("");
            }
            for (Vertice j : V) {
                if (V.indexOf(j) == 0)  
                    System.out.print(i.Valor + "| ");
                System.out.print(Adjacencia(i, j) + "| ");
            }
            System.out.println("");
        }
    }
    
    public void MatrizIncidencia(){
        System.out.println("MATRIZ INCIDÊNCIA");
        for (Vertice i : V) {
            if(V.indexOf(i) == 0){
                for (Aresta j : A) {
                    if (A.indexOf(j) == 0)                    
                        System.out.print("   "+j.nome+"|");
                    else if(A.indexOf(i) < A.size())
                        System.out.print(j.nome + "|");
                    else
                        System.out.print(j.nome);
                }
                System.out.println("");
            }
            for (Aresta j : A) {
                if (A.indexOf(j) == 0)                    
                    System.out.print(i.Valor+ "|");
                if(i.Valor.equals(j.Inicio.Valor) && i.Valor.equals(j.Fim.Valor))
                    System.out.print("+2|");
                else if(i.Valor.equals(j.Inicio.Valor))
                    System.out.print("+1|");
                else if(i.Valor.equals(j.Fim.Valor))
                    System.out.print("-1|");
                else
                    System.out.print(" 0|");
            }
            System.out.println("");
        }  
    }
    
    public void MatrizCusto(){
        System.out.println("MATRIZ CUSTO");
        for (Vertice i : V) {
            if(V.indexOf(i) == 0){
                for (Vertice j : V) {
                    if (V.indexOf(j) == 0)                    
                        System.out.print("   " + this.Espacar(espaco, j.Valor));
                    else if(V.indexOf(i) < V.size())
                        System.out.print(this.Espacar(espaco, j.Valor));
                    else
                        System.out.print(this.Espacar(espaco, j.Valor));
                }
                System.out.println("");
            }
            for (Vertice j : V) {
                if (V.indexOf(j) == 0)  
                    System.out.print(i.Valor + "|");
                System.out.print(Espacar(espaco,TotalCusto(i,j)));
            }
            System.out.println("");
        }
    }
    /* FIM IMPRESSAO*/
    
    /* EXTRA */
    public void SubstituirVertice(String antes, String depois){
        boolean nao_sub = true;
        for (Vertice v : V) {
            if(v.Valor.equals("v"+antes)){
                v.Valor = "v"+depois;
                System.out.println("SUBSTITUIR VÉRTICE = v"+antes +" -> v"+depois);
                nao_sub = false;
            }
        }
        if(nao_sub)
            System.out.println("SUBSTITUIR VÉRTICE = v"+antes+" não encontrado");
    }
    
    public void SubstituirAresta(String aresta, Object custo){
        boolean nao_sub = true;
        for (Aresta a : A) {
            if(a.nome.equals("a"+aresta)){
                System.out.println("SUBSTITUIR ARESTA = " + a.toString() +" -> ["+ custo+"]");
                a.Custo = custo;
                nao_sub = false;
                Espacamento(custo, true);
            }            
        }
        if(nao_sub)
            System.out.println("SUBSTITUIR ARESTA = a"+aresta+" não encontrado");
    }
    
    /*public int Espacar(int count, String custo) {
        return custo.length();
    }*/
    
    public void Espacamento(Object c, boolean add){
        String VerCusto = c.toString();
        if(add){
            if(VerCusto.length() > espaco)
                espaco = VerCusto.length();
        }else{
            espaco = 1;
            for (Aresta a : A) {
                if(a.Custo.toString().length() > espaco)
                espaco = a.Custo.toString().length();
            }
        }
    }
    public String Espacar(int count, String custo) {
        String result = "";
        int i = espaco;
        while(i > custo.length()){
            result += " ";
            i--;
        }
        return result + custo + "|";
    }
    /* FIM EXTRA */

    private List<Caminho> CaminhosAdjacentes(Caminho CaminhoPai) {
        List<Caminho> l = new ArrayList<Caminho>();
        for (Aresta a : A) {
            if(CaminhoPai.v == a.Inicio){
                Caminho c = new Caminho();
                c.v = a.Fim;
                c.Ant = CaminhoPai;
                c.peso = (Integer)a.Custo;
                l.add(c);
            }            
        }
        return l;
    }
    
    private Caminho MenorCaminhoAdjacente(List<Caminho> l){
        int menor = 999;
        Caminho c = l.get(0);
        for (Caminho caminho : l) {
            if(menor > caminho.peso)
                c = caminho;
        }
        return c;
    }
}
