public class abstract Cliente(){
    private final String nome;
    
    public Cliente(String nome){
        
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "Nome: " + nome + '\'' +   
                '}';
    }
}