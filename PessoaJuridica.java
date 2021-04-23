public class PessoaJuridica extends Cliente{

    private String cnpj;

    public PessoaJuridica(String nome, String cnpj){
       super(nome);
       this.cnpj = cnpj;
    }
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public String cnpj(){
        return cnpj;
    }

    @Override
    public String toString() {
        return "Pessoa Juridica{" + getNome() + "cnpj: " + cnpj + '\'' +   
                '}';
    }
    public Object getCnpj() {
        return null;
    }
}