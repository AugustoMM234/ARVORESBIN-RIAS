public class Estruturash2{
    public static void main(){

    }
}


class CelulaInt{
    CelulaInt esq, dir; 
    String palavra; 

    public CelulaInt(String palavra){
        this.palavra = palavra; 
        esq = dir = null; 
    }

}

class CelulaExt{
    CelulaInt raiz;
    CelulaExt esq, dir;
    char letra;

    public CelulaExt(char letra){
        this.letra = letra;
        esq = dir = null; 
        raiz = null; 
    }
}

class Arvore{
    CelulaExt raiz;

    public Arvore(){
        raiz = null;
    }

    void acharCelulaExt(String palavra){
        raiz = acharCelulaExt(palavra, raiz);
    }

    CelulaExt acharCelulaExt(String palavra, CelulaExt i){
        if(i == null){
            i = new CelulaExt(palavra.charAt(0));
            i.raiz = adicionarPalavra(palavra, i.raiz);
        }else if (i.letra == palavra.charAt(0)){
            i.raiz = adicionarPalavra(palavra, i.raiz);
        }else if(palavra.charAt(0) < i.letra){
            i.esq = acharCelulaExt(palavra, i.esq);
        }else{
            i.dir = acharCelulaExt(palavra, i.dir);
        }
        return i;
    }

    CelulaInt adicionarPalavra(String palavra, CelulaInt i){
        if(i == null){
            i = new CelulaInt(palavra);
        }else if(palavra.compareTo(i.palavra) < 0){
            i.esq = adicionarPalavra(palavra, i.esq);
        }else if(palavra.compareTo(i.palavra) > 0){
            i.dir = adicionarPalavra(palavra, i.dir);
        }
        return i; 
    }
}