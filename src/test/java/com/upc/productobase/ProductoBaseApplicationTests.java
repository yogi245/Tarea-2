package com.upc.productobase;

import com.upc.productobase.entidades.Producto;
import com.upc.productobase.negocio.ProductoNegocio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class ProductoBaseApplicationTests {

    @Autowired
    private ProductoNegocio productoNegocio;

    @Test
    void contextLoads() {
    }

    @Test
    public void probarRegistro() {
        Producto producto = new Producto();
        producto.setDescripcion("Leche");
        producto.setPrecio(12);
        producto.setStock(100);
        productoNegocio.registrar(producto);

    }

    @Test
    void probarCalcularObtenerPrecioVenta() {
        try {
            double pv;
            pv = productoNegocio.calcularPrecioVenta(1L);
            Assertions.assertEquals(3.54, pv, 0.01);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void probarListado() {
        List<Producto> listado;
        listado = productoNegocio.listado();
        for (Producto producto : listado) {
            System.out.println(producto.getCodigo() + " " + producto.getDescripcion());
        }
        Assertions.assertNotNull(listado);
    }

    @Test
    void probarListadoxPrecio() {
        List<Producto> listado;
        listado = productoNegocio.listadoPrecio(10);
        for (Producto producto : listado) {
            System.out.println(producto.getCodigo() + " " + producto.getDescripcion());
        }
        Assertions.assertNotNull(listado);
    }

    @Test
    void probarListadoxDescripcion() {
        List<Producto> listado;
        listado = productoNegocio.listadoxDescripcion("Lec");
        for (Producto producto : listado) {
            System.out.println(producto.getCodigo() + " " + producto.getDescripcion());
        }
        Assertions.assertNotNull(listado);

    }
    @Test
    void probarListadoTotal(){
        List<Producto> listado;
        listado = productoNegocio.listadoTotal();
        for(Producto producto:listado){
            System.out.println(producto.getCodigo()+ "  " + producto.getDescripcion()
                    + "   " + producto.getPrecioVenta());
        }
        Assertions.assertNotNull(listado);
    }


}