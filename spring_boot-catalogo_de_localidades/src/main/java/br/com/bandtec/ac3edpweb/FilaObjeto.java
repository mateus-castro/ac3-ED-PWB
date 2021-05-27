package br.com.bandtec.ac3edpweb;

// Representa uma fila de String
public class FilaObjeto<Wow> {

    private int tamanho;
    private Wow[] fila;

    public FilaObjeto(int capacidade) {
        tamanho = 0;
        fila = (Wow[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(Wow info) {
        if (isFull()) {
            System.out.println("Fila cheia");
        }
        else {
            fila[tamanho++] = info;
        }
    }

    public Wow peek() {
        return fila[0];
    }

    public Wow poll() {
        Wow primeiro = peek();

        if (!isEmpty()) {

            for (int i = 0; i < tamanho - 1; i++) {
                fila[i] = fila[i + 1];
            }

            fila[tamanho - 1] = null;

            tamanho--;
        }

        return primeiro;
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
        } else {
            for (int i = 0; i < tamanho; i++) {
                System.out.println(fila[i]);
            }
        }
    }

    public int getTamanho() {
        return tamanho;
    }
}
