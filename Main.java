import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        List<Reserva> cliente = new ArrayList();

        int selecionar;
        do {
            printMenu();

            /* -- Tentativa de exceção --
            do{ 
             * continueloop = true; 
             * try{
             * System.out.println("Digite um número de 1 a 6"); 
             * int selecionar = input.nextInt(); 
             *      if(selecionar >= 1 && selecionar <7){ 
             *          continueloop = false;
             *      }else{ 
             *      continueloop = true; 
             *      } 
             * }catch(InputMismatchException){
             *      System.out.println("Não pode ser outra coisa, sem ser número de 1 a 6") 
             *  }
             *}while(continueloop);
             * 
             */ selecionar = teclado.nextInt();
            if (selecionar > 0 && selecionar < 7) {
                switch (selecionar) {

                case 1:
                    cliente.add(ReservarMesa(cliente));
                    System.out.println(cliente.get(0));
                    break;

                case 2:
                    Pesquisar(cliente);
                    break;

                case 3:
                    ImprimirReserva(cliente);
                    break;

                case 4:
                    ImprimirLista(cliente);
                    break;

                case 5:
                    Cancelar(cliente);
                    break;

                }
            } else {
                System.out.println("Essa opção é inválida!Tente novamente com um número de 1 a 6.");

            }
        } while (selecionar != 6);

        System.out.println("Finalizado!");

    }

    // métodos

    public static void printMenu() {
        System.out.println("Restaurante SABOR SOFISTICADO:");
        System.out.println("1. Reservar Mesa");
        System.out.println("2. Pesquisar Reserva");
        System.out.println("3. Imprimir Reserva");
        System.out.println("4. Imprimir lista de espera");
        System.out.println("5. Cancelar Reserva");
        System.out.println("6. Finalizar");
    }

    public static PessoaFisica cadastroPf() {
        System.out.println("cpf: ");
        String cpf = new Scanner(System.in).nextLine();
        System.out.println("nome: ");
        String nome = new Scanner(System.in).nextLine();
        PessoaFisica pf = new PessoaFisica("", "");
        pf.setCpf(cpf);
        pf.setNome(nome);
        return pf;
    }

    public static PessoaJuridica cadastroPJ() {
        System.out.println("cnpj: ");
        String cnpj = new Scanner(System.in).nextLine();
        System.out.println("nome: ");
        String nome = new Scanner(System.in).nextLine();
        PessoaJuridica pj = new PessoaJuridica("", "");
        pj.setCnpj(cnpj);
        pj.setNome(nome);
        return pj;
    }

    public static Reserva ReservarMesa(List<Reserva> cliente) {
        int mesaVaga = 0;

        Scanner teclado = new Scanner(System.in);
        System.out.println("Deseja informar o cpf, ou cnpj: ");
        String informe = teclado.nextLine();
        Cliente cl = null;
        // Vaga para reservar

        if (informe.equalsIgnoreCase("cpf") && mesaVaga <= 6) {
            cl = cadastroPf();
            mesaVaga++;
        } else if (informe.equalsIgnoreCase("cnpj") && mesaVaga <= 6) {
            cl = cadastroPJ();
            mesaVaga++;

            // Sem vagas, vai para lista de espera

        } else if (informe.toLowerCase() == "cpf" && mesaVaga >= 7) {
            System.out.println("Adcionando na lista de espera");
            cl = cadastroPf();
            mesaVaga++;
        } else if (informe.toLowerCase() == "cnpj" && mesaVaga >= 7) {
            System.out.println("Adcionando na lista de espera");
            cl = cadastroPJ();
            mesaVaga++;

            // Informação inválida para realizar a reserva

        } else {
            System.out.println("Informação inserida incorreta! Tente novamente.");
        }

        // Informar Forma de Pagamento

        System.out.println("A forma de pagamento será a vista? \n Digite 1, para sim \n Digite 2, para não");
        int digito = teclado.nextInt();
        Reserva cli = new Reserva(null, false);
        cli.setCliente(cl);
        if (digito == 1) {

            cli.setPagamentoAVista(true);
        } else {
            cli.setPagamentoAVista(false);
        }

        return cli;
    }

    public static void Pesquisar(List<Reserva> cliente) {
        Scanner teclado = new Scanner(System.in);

        for (int i = 0; i < cliente.size(); i++) {

            // pesquisar por cpf

            System.out.println("Informe o seu cpf, ou seu cnpj: ");
            boolean confere = false;
            String cpf = teclado.nextLine();
            if (cliente.get(i).getCliente() instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) cliente.get(i).getCliente();
                if (pf.getCpf().equals(cpf)) {
                    confere = true;
                    System.out.println("Reserva da Pessoa Física --> " + cliente.get(i) + "->" + pf.getCpf());

                }

            }
            // pesquisar por cnpj

            if (cliente.get(i).getCliente() instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) cliente.get(i).getCliente();
                if (pj.getCnpj().equals(cpf)) {
                    confere = true;
                    System.out.println("Reserva da Pessoa Jurídica --> " + cliente.get(i) + "->" + pj.getCnpj());
                }
            }
            if (confere == false) {
                System.out.println("A reserva não foi registrada");
            }

        }

    }

    public static void ImprimirReserva(List<Reserva> cliente) {

        
        for (int i = 0; i < cliente.size(); i++) {
              System.out.println(cliente.get(i));
            if(i == 5){
                break;
            }
        }

    }

    public static void ImprimirLista(List<Reserva> cliente) {
        for (int i = 6; i < cliente.size(); i++) {

            if (cliente.get(i).getCliente() instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) cliente.get(i).getCliente();
                System.out.println(cliente.get(i));

                // lista de Reservas pessoa Jurídica

            } else if (cliente.get(i).getCliente() instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) cliente.get(i).getCliente();
                System.out.println(cliente.get(i));

            }

        }

    }

    public static void Cancelar(List<Reserva> cliente) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Informe o seu cpf, ou seu cnpj: ");
        boolean confere = false;
        String cpf = teclado.nextLine();

        for (int i = 0; i < cliente.size(); i++) {

            if (cliente.get(i).getCliente() instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) cliente.get(i).getCliente();
                if (pf.getCpf().equals(cpf)) {
                    confere = true;
                    System.out.println(
                            "Reserva da Pessoa Física a ser removida --> " + cliente.get(i) + "->" + pf.getCpf());
                    cliente.remove(i);
                }

            }
            // pesquisar por cnpj

            if (cliente.get(i).getCliente() instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) cliente.get(i).getCliente();
                if (pj.getCnpj().equals(cpf)) {
                    confere = true;
                    System.out.println(
                            "Reserva da Pessoa Jurídica a ser removida --> " + cliente.get(i) + "->" + pj.getCnpj());
                    cliente.remove(i);
                }
            }
            if (confere == false) {
                System.out.println("Esse cliente é inexistente");
            }

        }

    }

}



/*
 * Anotações para Estudo: cliente.forEach(cliente ->
 * System.out.println(cliente)); --> para cada cliente pegue-o e o imprima.
 * inicia a variável de pessoa física e dentro dos ()-> pessoa fisica do
 * cliente. (linha 200)
 */