public interface Pagamento{

    public static double CalcularPagamento(boolean pagamentoAVista){
        if(pagamentoAVista){
            return 3200*0.9; //desconto de 10% 
        }else{
            return 3200;
        }
    }

}