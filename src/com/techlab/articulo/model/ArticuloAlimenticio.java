package com.techlab.articulo.model;

/**
 * Representa un artículo de tipo alimenticio en el sistema.
 */
public class ArticuloAlimenticio extends Articulo {
    private int diasParaVencimiento;

    public ArticuloAlimenticio(int codigo, String nombre, double precio, Categoria categoria, int diasParaVencimiento) {
        super(codigo, nombre, precio, categoria);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    public int getDiasParaVencimiento() {
        return diasParaVencimiento;
    }

    public void setDiasParaVencimiento(int diasParaVencimiento) {
        this.diasParaVencimiento = diasParaVencimiento;
    }

    @Override
    public String getTipoArticulo() {
        return "Alimenticio";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Días restantes para el vencimiento: " + diasParaVencimiento;
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo alimenticio]";
    }
}
