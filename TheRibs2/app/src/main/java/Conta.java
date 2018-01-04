import android.icu.text.DateTimePatternGenerator;

import java.util.Date;

/**
 * Created by 16165872 on 14/11/2017.
 */

public class Conta {

    private int id_pedido;
    private int id_garcom;
    private int id_cliente;
    private int status;
    private Date data_hora_abertura;
    private int id_mesa;
    private String codigo;

    private Conta(int id_cliente, int id_garcom, int id_mesa, int id_pedido, Date data_hora_abertura, int status, String codigo){
        this.id_cliente = id_cliente;
        this.id_garcom = id_garcom;
        this.id_mesa = id_mesa;
        this.id_pedido = id_pedido;
        this.status = status;
        this.data_hora_abertura = data_hora_abertura;
        this.codigo = codigo;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_garcom() {
        return id_garcom;
    }

    public void setId_garcom(int id_garcom) {
        this.id_garcom = id_garcom;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getData_hora_abertura() {
        return data_hora_abertura;
    }

    public void setData_hora_abertura(Date data_hora_abertura) {
        this.data_hora_abertura = data_hora_abertura;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}