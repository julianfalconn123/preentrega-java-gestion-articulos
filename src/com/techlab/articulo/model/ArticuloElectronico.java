package com.techlab.articulo.model;

/**
 * Representa un artículo de tipo electrónico en el sistema.
 */
public class ArticuloElectronico extends Articulo {
    private int garantiaMeses;

    public ArticuloElectronico(int codigo, String nombre, double precio, Categoria categoria, int garantiaMeses) {
        super(codigo, nombre, precio, categoria);
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipoArticulo() {
        return "Electrónico";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Garantía: " + garantiaMeses + " meses";
    }

    /**
     * Devuelve el número telefónico de la mesa de ayuda para reclamos de artículos electrónicos.
     * @return Número de contacto para reclamos.
     */
    public String nroTelMesaDeAyudaParaReclamos() {
        return "0800-123-4567";
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo electrónico]";
    }
}
