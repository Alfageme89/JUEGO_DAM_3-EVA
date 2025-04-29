package minirpg.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3300/minirpg";
    private static final String USUARIO = "root";
    private static final String CLAVE = "2234";


    /**
     * Establece la conexi n con la base de datos "minirpg" en localhost:3300
     * con el usuario "root" y el password "2234".
     *
     * @return La conexi n establecida con la base de datos.
     * @throws SQLException Si hubo algn error al establecer la conexi n.
     */
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
