package com.upc.productobase.negocio;

import com.upc.productobase.entidades.Producto;
import com.upc.productobase.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoNegocio {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public Producto registrar(Producto producto){
        return productoRepositorio.save(producto);
    }

    public List<Producto> listado(){
        return productoRepositorio.findAll();
    }

    public List<Producto> listadoTotal(){
        List<Producto> listado;
        List<Producto> lista;
        lista = productoRepositorio.findAll();
        for(Producto producto:lista){
            producto.setPrecioVenta(calcularPrecioVenta(producto));
        }
        return lista;

    }

    public List<Producto> listadoPrecio(double precio){
        return productoRepositorio.findByPrecioIsLessThan(precio);
    }

    public List<Producto> listadoxDescripcion(String prefijo){
        return productoRepositorio.findByDescripcionStartingWith(prefijo);
    }

    public Producto buscar(Long codigo) throws Exception{
        Producto producto;
        producto = productoRepositorio.findById(codigo).orElseThrow(() -> new
                Exception("No se encontro entidad"));
        return producto;
    }

    public double calcularPrecioVenta(Long codigo) throws Exception {
        Producto producto;
        producto = buscar(codigo);
        return calcularPrecioVenta(producto);
    }

    public double calcularDescuento(Producto producto){
        double descuento=0;
        if(producto.getStock()>20){
                descuento = 0.10*producto.getPrecio();
        }
        return descuento;
    }
    public double calcularIGV(Producto producto){

        return producto.getPrecio()*0.18;

    }

    public double calcularPrecioVenta(Producto producto){
        return producto.getPrecio() + calcularIGV(producto) - calcularDescuento(producto);
    }

}
