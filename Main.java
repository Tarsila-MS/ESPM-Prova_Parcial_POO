import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int mesaVaga = 0;
    public static void main(String[] args) {
        System.out.println("me da 10 humberto <3");
        Scanner teclado = new Scanner(System.in);
        List<Reserva> cliente = new ArrayList();
        int selecionar;
        do {
            printMenu();
           
            while(true){ 
              try{
              System.out.print("Digite um número de 1 a 6: "); 
              selecionar = teclado.nextInt(); 
                   if(selecionar > 0 && selecionar <7){
                       break;
                   }else{ 
                     System.out.println("Não pode ser outra coisa, sem ser número de 1 a 6");
                   } 
              }catch(InputMismatchException e){
                   teclado.next();
                   System.out.println("Não pode ser outra coisa, sem ser número de 1 a 6: " + e); 
              }
            }
             
              
             
            if (selecionar > 0 && selecionar < 7) {
                switch (selecionar) {

                case 1:
                    cliente.add(ReservarMesa(cliente));
                   
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
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║ Restaurante SABOR SOFISTICADO:  ║");
        System.out.println("║ 1 → Reservar Mesa               ║");
        System.out.println("║ 2 → Pesquisar Reserva           ║");
        System.out.println("║ 3 → Imprimir Reserva            ║");
        System.out.println("║ 4 → Imprimir lista de espera    ║");
        System.out.println("║ 5 → Cancelar Reserva            ║");
        System.out.println("║ 6 → Finalizar                   ║");
        System.out.println("╟─────────────────────────────────╢");
        System.out.println("║ Mesas ocupadas: " + mesaVaga + "               ║");
        System.out.println("╚═════════════════════════════════╝");
    }

    public static PessoaFisica cadastroPf() {
        System.out.print("Insira o cpf: ");
        String cpf = new Scanner(System.in).nextLine();
        System.out.print("Insira o nome: ");
        String nome = new Scanner(System.in).nextLine();
        PessoaFisica pf = new PessoaFisica("", "");
        pf.setCpf(cpf);
        pf.setNome(nome);
        return pf;
    }

    public static PessoaJuridica cadastroPJ() {
        System.out.print("Insira o cnpj: ");
        String cnpj = new Scanner(System.in).nextLine();
        System.out.print("Insira o nome: ");
        String nome = new Scanner(System.in).nextLine();
        PessoaJuridica pj = new PessoaJuridica("", "");
        pj.setCnpj(cnpj);
        pj.setNome(nome);
        return pj;
    }

    public static Reserva ReservarMesa(List<Reserva> cliente) {

        Scanner teclado = new Scanner(System.in);
        System.out.print("Deseja informar o CPF, ou CNPJ? ");
        String informe = teclado.nextLine();
        Cliente cl = null;
        // Vaga para reservar
       
        if (informe.equalsIgnoreCase("cpf") && mesaVaga <= 6) {
            cl = cadastroPf();
            mesaVaga += 1;
        } else if (informe.equalsIgnoreCase("cnpj") && mesaVaga <= 6){ 
          cl = cadastroPJ();
            mesaVaga += 1;
      
            // Sem vagas, vai para lista de espera

        } else if (informe.equalsIgnoreCase("cpf") && mesaVaga >= 7) {
            System.out.print(" - Adicionando na lista de espera\n");
            cl = cadastroPf();
            mesaVaga++;
        } else if (informe.equalsIgnoreCase("cnpj") && mesaVaga >= 7){
         System.out.println("- Adicionando na lista de espera\n");
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
        System.out.println("\n\n");
        return cli;
    }

    public static void Pesquisar(List<Reserva> cliente) {
        Scanner teclado = new Scanner(System.in);
        boolean confere = false;

        System.out.print("Informe o seu cpf, ou seu cnpj: ");
        
        String tipo = teclado.nextLine();
        for (int i = 0; i < cliente.size(); i++) {
            // pesquisar por cpf

            if (cliente.get(i).getCliente() instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) cliente.get(i).getCliente();
                if (pf.getCpf().equals(tipo)) {
                    confere = true;
                    System.out.println("Reserva da Pessoa Física --> " + cliente.get(i) + "->" + pf.getCpf());
                    break;
                }

            }
            // pesquisar por cnpj

            if (cliente.get(i).getCliente() instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) cliente.get(i).getCliente();
                if (pj.getCnpj().equals(tipo)) {
                    confere = true;
                    System.out.println("Reserva da Pessoa Jurídica --> " + cliente.get(i) + "->" + pj.getCnpj());
                    break;
                }
            }
        }

        if (confere == false) {
            System.out.println("A reserva não foi registrada");
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

                // lista de Reservas pessoa Física
                
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
        
        String tipo = teclado.nextLine();

        for (int i = 0; i < cliente.size(); i++) {

            // cpf

            if (cliente.get(i).getCliente() instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) cliente.get(i).getCliente();
                if (pf.getCpf().equals(tipo)) {
                    
                    System.out.println(
                            "Reserva da Pessoa Física a ser CANCELADA --> " + cliente.get(i) + "->" + pf.getCpf());
                    cliente.remove(i);
                }

            }
            // cnpj

            else if (cliente.get(i).getCliente() instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) cliente.get(i).getCliente();
                
                 if (pj.getCnpj().equals(tipo)){
                   
                    System.out.println(
                            "Reserva da Pessoa Jurídica a ser CANCELADA --> " + cliente.get(i) + "->" + pj.getCnpj());
                    cliente.remove(i);
                    
                }
            }
            else{
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