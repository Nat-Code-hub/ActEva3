class Pedido:
    """Representa un pedido con un ID y el nombre del plato."""

    def __init__(self, id, nombre_plato):
         
         
        """ Inicializa un pedido.
        :param id: Identificador numérico del pedido.
        :param nombre_plato: Nombre del plato solicitado.
        """
        self.id = id
        self.nombre_plato = nombre_plato
        
        """Devuelve una representación legible del pedido."""
        
    def __str__(self):
        return f"Pedido #{self.id} ({self.nombre_plato})"