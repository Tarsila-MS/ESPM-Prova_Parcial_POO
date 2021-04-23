import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        List<Reserva>cliente = new ArrayList()<>;
       
        do{
            printMenu();
            int selecionar = teclado.nextInt();
            do{ 
                continueloop = true;
                try{
                System.out.println("Digite um número de 1 a 6")
                int selecionar = input.nextInt();
                if(selecionar >= 1 && selecionar <7){
                    continueloop = false;
                }else{
                    continueloop = true;
                }
             }catch(inputMissmatchException){
                System.out.println("Não pode ser outra coisa, sem ser número de 1 a 6")
                }
        }while(continueloop);

            if(selecionar > 0 && selecionar < 7){
                switch(opcao){
                                       
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
                }else{
                System.out.println("Essa opção é inválida!Tente novamente com um número de 1 a 6.")

            }while(opcao != 6){
                System.out.println("Finalizado!");
            }
        
           
        }
    }
    //métodos

    public static void printMenu(){
            System.out.println("Restaurante SABOR SOFISTICADO:" ); 
            System.out.println("1. Reservar Mesa"); 
            System.out.println("2. Pesquisar Reserva"); 
            System.out.println("3. Imprimir Reserva"); 
            System.out.println("4. Imprimir lista de espera"); 
            System.out.println("5. Cancelar Reserva"); 
            System.out.println("6. Finalizar");
    }

    

       public static Cliente cadastrarPessoa(){
            Scanner teclado = new Scanner(System.in);
        
                Cliente cliente = null;
        
                String tipoPessoa = inputTipoPessoa();
                switch(tipoPessoa){
                    case "j":
                        cliente = cadastrarPJ();
                        break;
                    case "f":
                        cliente = cadastrarPF();
                        break;
                }
                System.out.println("Nome: ");
                Cliente.setNome(Scanner.nextLine);
    }

    public static inputTipoPessoa(){
        String tipoPessoa = null;
        while(tipoPessoa == null){
            System.out.println("Pessoa Jurídica (j) ou Pessoa Física (f): ");
            tipoPessoa = new Scanner(System.in).nextLine();
            if(!"f".equals(tipoPessoa) && !"j".equals(tipoPessoa)){
                tipoPessoa = null;
                System.out.println("Opção invalida");
            }
        }
        return tipoPessoa;
    }


    public static PessoaFisica cadastroPf(){
        System.out.println("cpf: " );
        String cpf = new Scanner(System.in).nextLine();

        PessoaFisica pf = new PessoaFisica();
        pf.setCpf(cpf);
        return pf;
    }
    
     public static PessoaJuridica cadastroPJ(){
        System.out.println("cnpj: " );
        String cnpj = new Scanner(System.in).nextLine();

        PessoaJuridica pj = new PessoaJuridica();
        pj.setCpf(cnpj);
        return pj;
    }

    public static Reserva ReservarMesa(List<Reserva>cliente){
        int mesaVaga = 0;

        Scanner teclado = new Scanner(System.in);
        System.out.println("Deseja informar o cpf, ou cnpj: ");
        String informe = teclado.nextLine();

        //Vaga para reservar 

        if(informe.equalsIgnoreCase("cpf") && mesaVaga <= 6){
            cadastroPessoaFisica();
            mesaVaga++;
        }else if(informe.equalsIgnoreCase("cnpj") && mesaVaga <= 6){
            cadastroPessoaJuridica();
            mesaVaga++;

            //Sem vagas, vai para lista de espera

        }else if(informe.toLowerCase() == "cpf" && mesaVaga >= 7){
            System.out.println("Adcionando na lista de espera");
            cadastroPessoaFisica();
            mesaVaga++;
        }else if(informe.toLowerCase() == "cnpj" && mesaVaga >= 7){
            System.out.println("Adcionando na lista de espera");
            cadastroPessoaJuridica();
            mesaVaga++;

            //Informação inválida para realizar a reserva

        }else{
            System.out.println("Informação inserida incorreta! Tente novamente.")
        }   

        //Informar Forma de Pagamento

        System.out.println("A forma de pagamento será a vista? \n Digite 1, para sim \n Digite 2, para não");
        int digito = teclado.nextInt();
        if(digito == 1){
           cliente.setPagamentoAVista(true);
        }else{
            cliente.setPagamentoAVista(false);
        }
    }

    public static void Pesquisar(List<Reserva>cliente){
        Scanner teclado = new Scanner(System.in);
       
        

        for(i = 0; i < cliente.size(); i++){
           
            //pesquisar por cpf

            System.out.println("Informe o seu cpf, ou seu cnpj: ");
            boolean confere = false;
            String cpf = teclado.nextLine();
            if(Cliente.cliente instanceOf PessoaFisica){
                PessoaFisica pf = (PessoaFisica) cliente; 
                if(pf.getCpf().equals(cpf)){
                    confere = true;
                    System.out.println("Reserva da Pessoa Física --> " + cliente.get(i) + '->' + pf.getCpf());

                }
            
            }
            //pesquisar por cnpj

            if(Cliente.cliente instanceOf PessoaJuridica){
                PessoaJuridica pj = (PessoaJuridica) cliente; 
                if(pj.getCnpj().equals(cpf)){
                    confere = true;
                    System.out.println("Reserva da Pessoa Jurídica --> " + cliente.get(i) + '->' + pj.getCnpj());
                }
            }if(confere == false){
                System.out.println("A reserva não foi registrada");
            }
    
    
        }

        

    }

    public static void ImprimirReserva(List<Reserva>cliente){

        //lista de Reservas pessoa Física

        for (int i = 0; i < 6; i++){

            if(Cliente.cliente instanceOf PessoaFisica){
                PessoaFisica pf = (PessoaFisica) cliente; 
            System.out.println("Reserva da Pessoa Física --> " + cliente.get(i) + '->' + pf.getCpf());
             
            //lista de Reservas pessoa Jurídica

            }else if(cliente.cliente instanceOf PessoaJuridica){
                PessoaJuridica pj = (PessoaJuridica) cliente;
            System.out.println("Reserva da Pessoa Jurídica --> " + cliente.get(i) + '->' + pj.getCnpj());
           
            }
             
        }

    }

    public static void ImprimirLista(List<Reserva>cliente){
        for (int i = 6; i < cliente.size(); i++){

            if(Cliente.cliente instanceOf PessoaFisica){
                PessoaFisica pf = (PessoaFisica) cliente; 
            System.out.println("Resarva da Pessoa Física --> " + cliente.get(i) + '->' + pf.getCpf());
             
            //lista de Reservas pessoa Jurídica

            }else if(cliente.cliente instanceOf PessoaJuridica){
                PessoaJuridica pj = (PessoaJuridica) cliente;
            System.out.println("Reserva da Pessoa Jurídica --> " + cliente.get(i) + '->' + pj.getCnpj());
           
            }
             
        }


    }

    public static Reserva Cancelar(List<Reserva>cliente){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Informe o seu cpf, ou seu cnpj: ");
            boolean confere = false;
            String cpf = teclado.nextLine();
   
        for(i = 0; i < cliente.size(); i++){
         
            if(Cliente.cliente instanceOf PessoaFisica){
                PessoaFisica pf = (PessoaFisica) cliente; 
                if(pf.getCpf().equals(cpf)){
                    confere = true;
                    System.out.println("Reserva da Pessoa Física a ser removida --> " + cliente.get(i) + '->' + pf.getCpf());
                    cliente.remove(i);
                }
            
            }
            //pesquisar por cnpj

            if(Cliente.cliente instanceOf PessoaJuridica){
                PessoaJuridica pj = (PessoaJuridica) cliente; 
                if(pj.getCnpj().equals(cpf)){
                    confere = true;
                    System.out.println("Reserva da Pessoa Jurídica a ser removida --> " + cliente.get(i) + '->' + pj.getCnpj());
                    cliente.remove(i);
                }
            }if(confere == false){
                System.out.println("Esse cliente é inexistente");
            }
    
    
        }
        return cliente;

    }

}

/* Anotações para Estudo:
cliente.forEach(cliente -> System.out.println(cliente)); --> para cada cliente pegue-o e o imprima.
inicia a variável de pessoa física e dentro dos ()-> pessoa fisica do cliente. (linha 201)
*/