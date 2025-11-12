/**
 * Representa un pedido con un id y el nombre del plato.
 */
public class Pedido {
    private final int id;// Identificador único del pedido
    private final String nombrePlato;// Nombre del plato solicitado

    /**
     * Crea un nuevo pedido.
     * @param id identificador del pedido
     * @param nombrePlato nombre del plato
     */
    public Pedido(int id, String nombrePlato) {
        this.id = id;
        this.nombrePlato = nombrePlato;
    }

    /** @return id del pedido */
    public int getId() {
        return id;
    }

     /** @return nombre del plato */
    public String getNombrePlato() {
        return nombrePlato;
    }

    /** @return representación rápida del pedido */
    @Override
    public String toString() {
        return "Pedido #" + id + " (" + nombrePlato + ")";
    }
}