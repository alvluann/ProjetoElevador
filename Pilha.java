public class Pilha {
    private Object[] dados;
    private int topo;

    public Pilha(int capacidade) {
        dados = new Object[capacidade];
        topo = 0;
    }

    public void push(Object elemento) {
        if (topo < dados.length) {
            dados[topo++] = elemento;
        } else {
            System.out.println("Pilha cheia!");
        }
    }

    public Object pop() {
        if (!isEmpty()) {
            return dados[--topo];
        }
        return null;
    }

    public Object peek() {
        if (!isEmpty()) {
            return dados[topo - 1];
        }
        return null;
    }

    public boolean isEmpty() {
        return topo == 0;
    }

    public int size() {
        return topo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < topo; i++) {
            builder.append(dados[i]);
            if (i < topo - 1) builder.append(", ");
        }
        builder.append("]");
        return builder.toString();
    }
}
