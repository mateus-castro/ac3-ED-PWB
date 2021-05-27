package br.com.bandtec.ac3edpweb.utils;

public class PilhaObjeto<Wow> {
    private Integer topo;
    private Wow[] pilha;

    public PilhaObjeto(Integer capacidade) {
        topo = -1;
        pilha = (Wow[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(Wow info) {
        if (!isFull()) {
            pilha[++topo] = info;
        }
        else {
            System.out.println("Pilha cheia");
        }
    }


    public Wow pop() {
        if (!isEmpty()) {
            Wow val = pilha[topo];
            pilha[topo].equals("0");
            topo--;
            return val;
        }
        return null;
    }

    public Wow peek() {
        if(!isEmpty()) {
            return pilha[topo];
        }
        return null;
    }

    public void exibe() {
        if(isEmpty()) {
            System.out.println("Pilha vazia");
        }
        else {
            for(int i = 0; i <= topo; i++) {
                System.out.println(pilha[i]);
            }
        }

    }

    public int getTopo() {
        return topo;
    }

    public Wow[] getPilha() {
        return pilha;
    }

    public void setPilha(Wow[] pilha) {
        this.pilha = pilha;
    }

    public void setTopo(int topo){
        this.topo = topo;
    }
}

