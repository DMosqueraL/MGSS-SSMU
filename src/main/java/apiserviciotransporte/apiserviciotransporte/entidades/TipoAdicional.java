package apiserviciotransporte.apiserviciotransporte.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipoadicional")
public class TipoAdicional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tipoadicional")
    @GenericGenerator(name = "secuencia_tipoadicional", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Column(name = "tipo", length = 100, unique = true)
    private String tipo;

    public TipoAdicional() {
    }

    public TipoAdicional(long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
