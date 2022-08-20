package com.upc.productobase.repositorio;

import com.upc.productobase.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepositorio extends JpaRepository<Producto,Long> {

    public List<Producto> findByPrecioIsLessThan(double precio);
    @Query("SELECT p FROM Producto p WHERE p.precio<=:xprecio")
    public List<Producto> listadoPrecio(@Param("xprecio") double xprecio);
    public List<Producto> findByDescripcionStartingWith(String prefijo);
}
