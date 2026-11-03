import java.util.Scanner;

public class ativerde {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arvore a1 = new Arvore();

        while (sc.hasNext()) {
            String comando = sc.next();

            switch (comando) {
                case "I":
                    a1.inserir(sc.nextInt());
                    break;
                case "E":
                    a1.mostrarOrdem(a1.raiz);
                    System.out.println();
                    break;
                case "PRE":
                    a1.mostrarPREOrdem(a1.raiz);
                    System.out.println();
                    break;
                case "POS":
                    a1.mostrarPOSOrdem(a1.raiz);
                    System.out.println();
                    break;
                case "O":
                    System.out.println(a1.pesquisarM(sc.nextInt()));
                    break;
                case "P":
                    System.out.println(a1.pesquisar(sc.nextInt()));
                    break;
            }
        }
        sc.close();
    }
}

class No {
    int valor;
    No esq, dir;

    public No() {
        this(-1);
    }

    public No(int valor) {
        this.valor = valor;
        this.esq = this.dir = null;
    }
}

class Arvore {
    No raiz;

    public Arvore() {
        raiz = null;
    }

    // INSERIR
    public void inserir(int x) {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) {
        if (i == null) {
            i = new No(x);
        } else if (x < i.valor) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.valor) {
            i.dir = inserir(x, i.dir);
        }
        return i;
    }

    // PESQUISAR
    public char pesquisar(int x) {
        return pesquisar(x, raiz) ? 'V' : 'N';
    }

    private boolean pesquisar(int x, No i) {
        if (i == null) return false;
        if (x == i.valor) return true;
        return x < i.valor ? pesquisar(x, i.esq) : pesquisar(x, i.dir);
    }

    // PESQUISAR MOSTRANDO O CAMINHO
    public char pesquisarM(int x) {
        boolean achou = pesquisarM(x, raiz);
        System.out.println();
        return achou ? 'V' : 'N';
    }

    private boolean pesquisarM(int x, No i) {
        if (i == null) return false;
        System.out.print(i.valor + " ");
        if (x == i.valor) return true;
        return x < i.valor ? pesquisarM(x, i.esq) : pesquisarM(x, i.dir);
    }

    // EM ORDEM
    public void mostrarOrdem(No i) {
        if (i != null) {
            mostrarOrdem(i.esq);
            System.out.print(i.valor + " ");
            mostrarOrdem(i.dir);
        }
    }

    // PRÉ-ORDEM
    public void mostrarPREOrdem(No i) {
        if (i != null) {
            System.out.print(i.valor + " ");
            mostrarPREOrdem(i.esq);
            mostrarPREOrdem(i.dir);
        }
    }

    // PÓS-ORDEM
    public void mostrarPOSOrdem(No i) {
        if (i != null) {
            mostrarPOSOrdem(i.esq);
            mostrarPOSOrdem(i.dir);
            System.out.print(i.valor + " ");
        }
    }
}