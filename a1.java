public class a1{
    static void main(){
        Arvore a1 = new Arvore();
        a1.add(5);
        a1.add(7);
        a1.add(3);
        a1.add(8);
        a1.add(2);
        a1.add(1);
        a1.add(4);
        a1.add(6);
        a1.add(0);
        a1.add(9);

        Arvore a2 = new Arvore();
        a2.add(5);
        a2.add(7);
        a2.add(3);
        a2.add(8);
        a2.add(2);
        a2.add(1);
        a2.add(4);
        a2.add(6);
        a2.add(0);
        a2.add(9);

        a1.mostrar(a1.raiz);
         System.out.println();

        System.out.println(a1.somar(a1.raiz));
        System.out.println(a1.pares(a1.raiz));
        System.out.println(iguais(a1.raiz, a2.raiz));
        
    }
}

class No{
    int elemento; 
    No esq;
    No dir; 

    public No(){
        this.elemento = 0; 
        this.esq = this.dir = null; 
    }

    public No(int elemento){
        this.elemento = elemento; 
        this.esq = this.dir = null; 
    }
}

class Arvore{
    No raiz;
    public Arvore(){
        this.raiz = null; 
    }

    public void add(int x){
        raiz = add(x, raiz);
    }

    public No add(int x, No i){
        if(i == null){
            i = new No(x);
        }else if(x < i.elemento){
            i.esq = add(x, i.esq);
        }else if(x > i.elemento){
            i.dir = add(x, i.dir);
        }
        return i;
    }

    public void mostrar(No i){
        if(i != null){
            mostrar(i.esq);
            System.out.print(i.elemento + " ");
            mostrar(i.dir);
        }
    }
    /*questão 1
        //////////////////////////////////////////////////////
    */


    //questao 3
    
    public int somar(No i){
        int soma = 0;
        if(i != null){
            soma += somar(i.esq);
            soma += i.elemento;
            soma += somar(i.dir);
        }
        return soma; 
    }

    //questão 4
    public int pares(No i){
        int pares = 0;
        if(i != null){
            pares += pares(i.esq);
            if(i.elemento%2 == 0){
                pares++;
            }
            pares += pares(i.dir);
        }
        return pares; 
    }

    //questão 5  avalia só os valores. tem que adicionar um pra verificar se a estrutura é igual 

    public static boolean iguais(No a1, No a2){
        boolean resp = true; 
        
        if(a1 != null){
            resp = iguais(a1.dir, a2.dir);
            resp = iguais(a1.esq, a2.esq); 

            if(a1.elemento != a2.elemento){
                resp = false; 
            }
        }

        return resp;
    }
}