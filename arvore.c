#include <stdio.h>
#include <stdlib.h>

typedef struct No{
    int elemento;
    struct No *esq, *dir; 
}No; 

No* novoNo(int elemento){
    No *novo = (No*) malloc(sizeof(No));
    if(novo == NULL){
        fprintf(stderr, "ERRO\n");
        exit(1);
    }
    novo->elemento = elemento; 
    novo->esq = novo->dir = NULL;
    return novo; 
}

No *raiz; 

void start(void){
    raiz = NULL; 
}

void inserirRec(int x, No **i){
    if(*i == NULL){
        *i = novoNo(x); 
    }else if(x < (*i)->elemento){
        inserirRec(x, &((*i)->esq));
    }else if(x > (*i)->elemento){
        inserirRec(x, &((*i)->dir));
    }else{
        fprintf(stderr, "EXISTE!\n");
        exit(1);
    }
}

void inserir(int x){
    inserirRec(x, &raiz); 
}

void mostrar(No *i){
    if(i != NULL){
        mostrar((i)->esq);
        printf("%d\n", (i)->elemento);
        mostrar((i)->dir);
    }
}

int espelhada(No *a, No *b){
    int resp = 1; 
    if(a != NULL && b != NULL){
        if(a->elemento != b->elemento){
            resp = 0; 
        }else{
            resp = espelhada(a->dir, b->esq); 
            if(resp == 1){
                resp = espelhada(a->esq, b->dir); 
            }
        }
    }else{
        if(a != NULL || b != NULL){
            resp = 0; 
        }
    }
    return resp;
}

int main(void){
    start();
    inserir(10);
    inserir(5);
    inserir(15);
    inserir(2);
    mostrar(raiz);                        // 2 5 10 15

    No *b = novoNo(10);
    b->esq = novoNo(15);
    b->dir = novoNo(5);
    b->dir->dir = novoNo(2);

    printf("%d\n", espelhada(raiz, b));   // 1

    b->dir->dir->elemento = 99;
    printf("%d\n", espelhada(raiz, b));   // 0
    
    return 0;
}