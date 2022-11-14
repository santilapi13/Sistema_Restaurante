package persistencia;

import modelo.Cerveceria;

public class UtilPersistencia {
    private static CerveceriaDTO respuesta;

    public static CerveceriaDTO CerveceriaToCerveceriaDTO(Cerveceria cerveceria) {
        CerveceriaDTO respuesta = new CerveceriaDTO();

        respuesta.setNombreLocal(cerveceria.getNombreLocal());
        respuesta.setSueldo(cerveceria.getSueldo());
        respuesta.setAdmin(cerveceria.getAdmin());
        respuesta.setMozos(cerveceria.getMozos());
        respuesta.setMesas(cerveceria.getMesas());
        respuesta.setProductos(cerveceria.getProductos());
        respuesta.setOperarios(cerveceria.getOperarios());
        respuesta.setComandasAbiertas(cerveceria.getComandasAbiertas());
        respuesta.setVentas(cerveceria.getVentas());
        respuesta.setPromoTemporales(cerveceria.getPromoTemporales());
        respuesta.setPromosProducto(cerveceria.getPromosProducto());
        return respuesta;
    }

    public static void CerveceriaDTOToCerveceria(CerveceriaDTO cerveceriaDTO, Cerveceria cerveceria) {
        cerveceria.setNombreLocal(cerveceriaDTO.getNombreLocal());
        cerveceria.setSueldo(cerveceriaDTO.getSueldo());
        cerveceria.setAdmin(cerveceriaDTO.getAdmin());
        cerveceria.setMozos(cerveceriaDTO.getMozos());
        cerveceria.setMesas(cerveceriaDTO.getMesas());
        cerveceria.setProductos(cerveceriaDTO.getProductos());
        cerveceria.setOperarios(cerveceriaDTO.getOperarios());
        cerveceria.setComandasAbiertas(cerveceriaDTO.getComandasAbiertas());
        cerveceria.setVentas(cerveceriaDTO.getVentas());
        cerveceria.setPromoTemporales(cerveceriaDTO.getPromoTemporales());
        cerveceria.setPromosProducto(cerveceriaDTO.getPromosProducto());
    }
}

