public class Elevador {
    private Pilha pilhaDeAndares;
    private int andarAtual;
    private int ultimoAndar;

    public Elevador(int capacidade, int ultimoAndar) {
        this.pilhaDeAndares = new Pilha(capacidade);
        this.andarAtual = 0;
        this.ultimoAndar = ultimoAndar;
    }

    public void solicitarAndar(int andar) {
        if (andar > ultimoAndar) {
            System.out.println("Andar inválido. O prédio possui até " + ultimoAndar + " andares.");
        } else if (andar == andarAtual) {
            System.out.println("Você já está no andar " + andar);
        } else {
            pilhaDeAndares.push(andar);
            System.out.println("Andar " + andar + " solicitado.");
        }
    }

    public void iniciarMovimentacao() {
        while (!pilhaDeAndares.isEmpty()) {
            int proximoAndar = (int) pilhaDeAndares.pop();
            System.out.println("Movendo-se para o andar " + proximoAndar + "...");
            andarAtual = proximoAndar;
            System.out.println("Parado no andar " + andarAtual);
            exibirStatus();
        }
        retornarTerreo();
    }

    private void exibirStatus() {
        System.out.println("Andares restantes na pilha: " + pilhaDeAndares);
        if (!pilhaDeAndares.isEmpty()) {
            System.out.println("Próximo destino: andar " + pilhaDeAndares.peek());
        }
    }

    private void retornarTerreo() {
        if (andarAtual != 0) {
            System.out.println("Retornando ao térreo...");
            andarAtual = 0;
        }
    }

    public void iniciarMovimentacao(ElevadorInterface interfaceElevador) {
        while (!pilhaDeAndares.isEmpty()) {
            int proximoAndar = (int) pilhaDeAndares.pop();
            try {
                Thread.sleep(1000); // Delay para simular a movimentação
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Movendo-se para o andar " + proximoAndar + "...");
            andarAtual = proximoAndar;
            interfaceElevador.atualizarElevador(andarAtual);
            System.out.println("Parado no andar " + andarAtual);
        }
        retornarTerreo();
    }
    
}
