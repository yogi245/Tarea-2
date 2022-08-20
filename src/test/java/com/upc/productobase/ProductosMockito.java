package com.upc.productobase;

import com.upc.productobase.entidades.Producto;
import com.upc.productobase.negocio.ProductoNegocio;
import com.upc.productobase.repositorio.ProductoRepositorio;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductosMockito {

    @Autowired
    private ProductoNegocio productoNegocio;
    @MockBean
    private ProductoRepositorio productoRepositorio;

    @Test
    void registrarTest(){
        Producto producto = new Producto(3L,"Fresa",12,12);
        when(productoRepositorio.save(producto)).thenReturn(producto);
        Assertions.assertEquals(producto,productoNegocio.registrar(producto));
    }

    @Test
    void calcularPrecioVentaTest(){
        Producto producto = new Producto(3L,"Fresa",12,12);
        when(productoRepositorio.findById(3L)).thenReturn(Optional.of(producto));
        try {
            double precioVenta = productoNegocio.calcularPrecioVenta(3L);
            Assertions.assertEquals(14.16,precioVenta,0.01);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
@Test
    void obtenerIGVtest(){
    Producto producto = new Producto(1L, "Leche", 4, 100);
        when(productoRepositorio.findById(1L)).thenReturn(Optional.of(producto));
        Assertions.assertEquals(0.72, productoNegocio.calcularIGV(producto),0.01);
}

@Test
    void obtenerProductosTest(){
        when(productoRepositorio.findByDescripcionStartingWith("Lec")).thenReturn(
                Stream.of(
                        new Producto(1L, "Leche", 4,100),
                        new Producto(2L, "Leche Light", 3, 10)).
                        collect(Collectors.toList())
                );
                Assertions.assertEquals(2, productoNegocio.listadoxDescripcion("lech").size());
}

}
