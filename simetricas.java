class Celula{
    int elemento;
    Celula esq, dir; 

    public Celula(int elemento){
        this.elemento = elemento;
        esq = dir = null; 
    }
    public Celula(){
        this(-1);
    }
}

class Arvore{
    Celula raiz;
    public Arvore(){
        raiz = null;
    }

    public void adicionar(int x){
        raiz = adicionar(x, raiz); 
    }

    private Celula adicionar(int x, Celula i){
        if(i == null){
            i = new Celula(x);
        }else if(x < i.elemento){
            i.esq = adicionar(x, i.esq);
        }else if(x > i.elemento){
            i.dir = adicionar(x, i.dir); 
        }else{
            System.out.println("erro");
        }
        return i; 
    }

    private void mostrar(Celula i){
        if(i != null){
            mostrar(i.esq);
            System.out.println(i.elemento);
            mostrar(i.dir);
        }
    } 

    public boolean espelho(Celula a, Celula b){
        boolean resp = true;
        if(a != null && b != null){
            if(a.elemento != b.elemento){
                resp = false; 
            }else{
                resp = espelho(a.esq, b.dir); 
                if (resp == true){
                    resp = espelho(a.dir, b.esq);
                }
            }
        }else{
            if((a == null && b != null) || (a != null && b == null)){
                resp = false;
            }
        }
        return resp;
    } 
}