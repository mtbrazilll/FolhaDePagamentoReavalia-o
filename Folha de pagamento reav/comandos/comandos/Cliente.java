package comandos;
import java.util.*;
import java.io.IOException;

public class Cliente {

    static void layout1() {
        System.out.print("\n                                     MyPayroll"
                       + "\n\n\nMenu Principal:"
                       + "\n\n"
                       + "\n 0 - sair"
                       + "\n 1 - Cadastrar Novo Colaborador"
                       + "\n 2 - Excluir Novo Colaborador"
                       + "\n 3 - Lançar Cartão De Ponto"
                       + "\n 4 - Lançar resultado de venda"
                       + "\n 5 - Lançar taxa de serviço"
                       + "\n 6 - Alterar informações de um empregado"
                       + "\n 7 - Rodar folha de pagamento"
                       + "\n 8 - desfazer"
                       + "\n 9 - refazer"
                       + "\n 10- Cria agenda de pagamento"
                       + "\n 11- Altera agenda de Pagamento"
                       //+ "\n a - Consultar Lista dos Sindicatos Cadastrados"
                       //+ "\n b - Cadastrar Novo Sindicato"
                       + "\n a - Consultar Lista Colaboradores Cadastrados"
                      // + "\n d - Mostrar resultado de venda"
                       + "\n\n\nDigite o Número da Opção Desejada: ");
    }

    static void layout2() {
        System.out.print("\nDeseja Retornar ao Menu? (1 - Sim / 0 - Não):");
    }

    static void limpaTela() {
        for (int a = 1; a <= 32; a++) {
            System.out.print("\n");
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        // TODO Auto-generated method stub
        //Invoker.invoke("1");

        layout1();

        Invoker menu = new Invoker();

        Scanner teclado = new Scanner(System.in);
        //menu.desfazer();

        String comando = teclado.next();
        String auxiliar = "1";


        while(!comando.equals("0")) {
            if(comando.equals("8")) {
            	System.out.println();
            	System.out.println();
            	menu.desfazer();
            }
            else if(comando.equals("9")) {
            	System.out.println();
            	System.out.println();
            	menu.refazer();
            }
            else {
            	System.out.println();
            	menu.invoke(comando);
            }
            layout2();
            auxiliar = teclado.next();
            if (auxiliar.equals("0")) {
                break;
            }
            //limpaTela();
            layout1();
            comando = teclado.next();

        }

    }

}