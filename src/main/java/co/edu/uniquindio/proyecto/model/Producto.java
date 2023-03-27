package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Producto implements Serializable {


    //-------------------------------- Atributos -------------------------------

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoVendedor;

    @Column (nullable = false)
    @NotBlank (message = "El nombre del producto es obligatorio")
    @Length(max = 100)
    private String nombre;

    @Column(nullable = false)
    @PositiveOrZero
    private int unidades;

    @NotBlank(message = "Debe añadir una descripción")
    @Length(max = 300)
    @Column(nullable = false)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private double precio;

    @Future
    @Column(nullable = false)
    private Date fechaLimiteProducto;

    //-------------------------------- Relaciones -------------------------------

    @ManyToOne
    private Usuario vendedor; //Un producto tiene 1 vendedor. El producto depende del vendedor

    //    @ElementCollection?????????
    //@Column(nullable = false)
    private estadoPublicacion estado; //@Enum 1 producto tiene 1 estado

    @ManyToMany(mappedBy = "productosFav")
    private List<Usuario> usuarioss; //Un producto tiene muchos usuarios . El producto depende del usuario que elija la lista de productos favoritos

    @ElementCollection //26/03 activé el element collection 11:07 pm
//    @Column(nullable = false)
    private List<Categoria> categoria; //@ENUM    1 producto Tiene 1 categoría

    @OneToMany(mappedBy = "codigoProducto") //Un producto no depende de un comentario
    private List<Comentario> comentarios; // Un producto tiene muchos comentarios

    @OneToMany(mappedBy = "fotoProducto")
    private List<Imagen> imagenesProducto; //@ElemenCollection?

    @ManyToOne() //Producto no depende de detalle compra
    private DetalleCompra detalleCompra; //Un producto pertenece a 1 detalleCompra

}
