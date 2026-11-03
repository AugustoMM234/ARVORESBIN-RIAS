public class Estruturash1{
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.inserir(new Contato("Ana", "1111-1111", "ana@email.com", 111));
        agenda.inserir(new Contato("Alice", "2222-2222", "alice@email.com", 222));
        agenda.inserir(new Contato("Bruno", "3333-3333", "bruno@email.com", 333));
        agenda.inserir(new Contato("Carla", "4444-4444", "carla@email.com", 444));

        System.out.println(agenda.pesquisar("Ana"));
        System.out.println(agenda.pesquisar("Alice"));
        System.out.println(agenda.pesquisar(333));
        System.out.println(agenda.pesquisar("Zeca"));

        agenda.remover("Ana");
        System.out.println(agenda.pesquisar("Ana"));
        System.out.println(agenda.pesquisar("Alice"));
    }
}

class Contato {
    String nome;
    String telefone;
    String email;
    int cpf;

    public Contato(String nome, String telefone, String email, int cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    public Contato() {
        this("", "", "", 0);
    }
}

class Celula {
    Contato contato;
    Celula prox;

    public Celula(Contato contato) {
        this.contato = contato;
        this.prox = null;
    }

    public Celula() {
        this(null);
    }
}

class No {
    char letra;
    No esq;
    No dir;
    Celula primeiro;
    Celula ultimo;

    public No(Contato contato) {
        this.letra = contato.nome.charAt(0);
        this.esq = null;
        this.dir = null;
        this.primeiro = new Celula();
        this.ultimo = primeiro;
        inserir(contato);
    }

    void inserir(Contato c) {
        ultimo.prox = new Celula(c);
        ultimo = ultimo.prox;
    }

    boolean pesquisar(String nome) {
        boolean resp = false;
        for (Celula i = primeiro.prox; i != null && !resp; i = i.prox) {
            if (i.contato.nome.equals(nome)) {
                resp = true;
            }
        }
        return resp;
    }

    boolean pesquisar(int cpf) {
        boolean resp = false;
        for (Celula i = primeiro.prox; i != null && !resp; i = i.prox) {
            if (i.contato.cpf == cpf) {
                resp = true;
            }
        }
        return resp;
    }

    void remover(String nome) {
        Celula i = primeiro;
        while (i.prox != null && !i.prox.contato.nome.equals(nome)) {
            i = i.prox;
        }
        if (i.prox != null) {
            Celula tmp = i.prox;
            i.prox = tmp.prox;
            if (tmp == ultimo) {
                ultimo = i;
            }
            tmp.prox = null;
            tmp = null;
        }
    }
}

class Agenda {
    No raiz;

    public Agenda() {
        raiz = null;
    }

    void inserir(Contato contato) {
        raiz = inserir(contato, raiz);
    }

    No inserir(Contato contato, No i) {
        char l = contato.nome.charAt(0);
        if (i == null) {
            i = new No(contato);
        } else if (l < i.letra) {
            i.esq = inserir(contato, i.esq);
        } else if (l > i.letra) {
            i.dir = inserir(contato, i.dir);
        } else {
            i.inserir(contato);
        }
        return i;
    }

    boolean pesquisar(String nome) {
        return pesquisar(nome, raiz);
    }

    boolean pesquisar(String nome, No i) {
        boolean resp = false;
        if (i != null) {
            char l = nome.charAt(0);
            if (l < i.letra) {
                resp = pesquisar(nome, i.esq);
            } else if (l > i.letra) {
                resp = pesquisar(nome, i.dir);
            } else {
                resp = i.pesquisar(nome);
            }
        }
        return resp;
    }

    boolean pesquisar(int cpf) {
        return pesquisar(cpf, raiz);
    }

    boolean pesquisar(int cpf, No i) {
        boolean resp = false;
        if (i != null) {
            resp = i.pesquisar(cpf) || pesquisar(cpf, i.esq) || pesquisar(cpf, i.dir);
        }
        return resp;
    }

    void remover(String nome) {
        remover(nome, raiz);
    }

    void remover(String nome, No i) {
        if (i != null) {
            char l = nome.charAt(0);
            if (l < i.letra) {
                remover(nome, i.esq);
            } else if (l > i.letra) {
                remover(nome, i.dir);
            } else {
                i.remover(nome);
            }
        }
    }
}