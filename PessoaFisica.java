public class PessoaFisica extends Cliente(){

    private final String cpf;
    
    public PessoaFisica(String nome, String cpf){
        super(nome);
        this.cpf = cpf;

    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return cpf;
    }

    @Override
    public String toString() {
        return "Pessoa Fisica{" +
                "cpf: " + cpf + '\'' +   
                '}';
    }
}