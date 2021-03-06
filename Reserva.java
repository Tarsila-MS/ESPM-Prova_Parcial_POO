public class Reserva implements Pagamento{

    private Cliente cliente;
    private boolean pagamentoAVista;

    public Reserva(Cliente cliente, boolean pagamentoAVista){

    }
    public boolean getPagamentoAVista(){
        return pagamentoAVista;
    }
    public void setPagamentoAVista(boolean pagamentoAVista){
        this.pagamentoAVista = pagamentoAVista;
    }

    public Cliente getCliente(){
        return cliente;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "Cliente: " + cliente + '\''+
                "Pagamento a Vista:" + pagamentoAVista + '\'' +   
                '}';
    }

}