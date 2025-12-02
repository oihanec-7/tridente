package jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import domain.Usuario;
import domain.Pelicula;
import domain.Serie;
import domain.Contenido;
 
public class GestorBD {

    private final String PROPERTIES_FILE = "resources/config/app.properties";
    
    private Properties properties;
    private String driverName;
    private String connectionString;
	private String databaseFile;

    
    private static Logger logger = Logger.getLogger(GestorBD.class.getName());

    public GestorBD() {
        try (FileInputStream fis = new FileInputStream("resources/config/logger.properties")) {
           
            LogManager.getLogManager().readConfiguration(fis);
            
            properties = new Properties();
            properties.load(new FileReader(PROPERTIES_FILE));
            
            driverName = properties.getProperty("driver");
            connectionString = properties.getProperty("connection");
            databaseFile = properties.getProperty("file");
            
            // Cargar el driver SQLite
            Class.forName(driverName);
        } catch (Exception ex) {
            logger.warning(String.format("Error al cargar configuración o driver de BBDD: %s", ex.getMessage()));
        }
    } 

    public void crearBBDD() {        
        // TABLA 1: USUARIOS
        String sql1 = "CREATE TABLE IF NOT EXISTS USUARIOS (\n"
                + " id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " nombre TEXT NOT NULL,\n"
                + " nombre_usuario TEXT NOT NULL UNIQUE,\n"
                + " contraseña TEXT NOT NULL,\n"
                + " apellido TEXT,\n"
                + " email TEXT NOT NULL UNIQUE\n"
                + ");";
 
        // TABLA 2: CONTENIDOS 
        String sql2 = "CREATE TABLE IF NOT EXISTS CONTENIDOS (\n"
                + " id_contenido INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " titulo TEXT NOT NULL UNIQUE,\n"
                + " tipo TEXT NOT NULL CHECK(tipo IN ('PELICULA', 'SERIE')),\n"
                + " duracion_o_temporadas INTEGER\n" // duracion si es una peli y temporadas si es serie
                + ");";

        // TABLA 3: FAVORITOS (que relaciona la tabla USUARIOS con la tabla CONTENIDOS)
        String sql3 = "CREATE TABLE IF NOT EXISTS FAVORITOS (\n"
                + " id_usuario_fk INTEGER,\n"
                + " id_contenido_fk INTEGER,\n"
                + " PRIMARY KEY(id_usuario_fk, id_contenido_fk),\n"
                + " FOREIGN KEY(id_usuario_fk) REFERENCES USUARIOS(id_usuario) ON DELETE CASCADE,\n"
                + " FOREIGN KEY(id_contenido_fk) REFERENCES CONTENIDOS(id_contenido) ON DELETE CASCADE\n"
                + ");";
        
        try (Connection con = DriverManager.getConnection(connectionString);
        	PreparedStatement pStmt1 = con.prepareStatement(sql1);
        	PreparedStatement pStmt2 = con.prepareStatement(sql2);
        	PreparedStatement pStmt3 = con.prepareStatement(sql3)) {
            
        	pStmt1.execute(sql1);
        	pStmt2.execute(sql2);
        	pStmt3.execute(sql3);

        	if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute()) {
	        	logger.info("Se han creado las tablas");
	        }
		} catch (Exception ex) {
			logger.warning(String.format("Error al crear las tablas: %s", ex.getMessage()));
		}
    }
    

    //Metodo insert
    public void insertarUsuario(Usuario u) {
        String sql = "INSERT INTO USUARIOS(nombre, nombre_usuario, contraseña, apellido, email) VALUES (?, ?, ?, ?, ?);";
        
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            
            pStmt.setString(1, u.getNombre());
            pStmt.setString(2, u.getNombre_usuario());
            pStmt.setString(3, u.getContraseña());
            pStmt.setString(4, u.getApellido());
            pStmt.setString(5, u.getEmail());
            
            if (pStmt.executeUpdate() == 1) {
                logger.info(String.format("Usuario insertado: %s", u.getNombre_usuario()));
            } else {
                logger.warning(String.format("No se pudo insertar el usuario: %s", u.getNombre_usuario()));
            }
        } catch (SQLException ex) {
            logger.warning(String.format("Error al insertar usuario %s: %s", u.getNombre_usuario(), ex.getMessage()));
        }
    }
    
    // Metodo update
    public boolean modificarContrasenaUsuario(String nombreUsuario, String nuevaContrasena) {
        String sql = "UPDATE USUARIOS SET contraseña = ? WHERE nombre_usuario = ?;";
        
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            
            pStmt.setString(1, nuevaContrasena);
            pStmt.setString(2, nombreUsuario);
            
            int filasAfectadas = pStmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                logger.info(String.format("Contraseña de %s modificada.", nombreUsuario));
                return true;
            } else {
                logger.warning(String.format("No se encontró al usuario %s para modificar.", nombreUsuario));
                return false;
            }
        } catch (SQLException ex) {
            logger.warning(String.format("Error al modificar contraseña: %s", ex.getMessage()));
            return false;
        }
    }
    
    // Metodo borrar
    public void borrarBBDD() {
        if (properties.get("deleteBBDD").equals("true")) { 	
            String sql1 = "DROP TABLE IF EXISTS FAVORITOS;";
            String sql2 = "DROP TABLE IF EXISTS USUARIOS;"; 
            String sql3 = "DROP TABLE IF EXISTS CONTENIDO;";
            
            try (Connection con = DriverManager.getConnection(connectionString);
                 PreparedStatement pStmt1 = con.prepareStatement(sql1);
                 PreparedStatement pStmt2 = con.prepareStatement(sql2);
                 PreparedStatement pStmt3 = con.prepareStatement(sql3)) {
                
                // Se ejecutan las sentencias de borrado de las tablas
                if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute()) {
                    logger.info("Se han borrado las tablas: FAVORITOS, USUARIOS y CONTENIDO.");
                }
            } catch (Exception ex) {
                logger.warning(String.format("Error al borrar las tablas: %s", ex.getMessage()));
            }
            
            try {
                Files.delete(Paths.get(databaseFile));
                logger.info("Se ha borrado el fichero de la BBDD.");
            } catch (Exception ex) {
                logger.warning(String.format("Error al borrar el fichero de la BBDD: %s", ex.getMessage()));
            }
        }
    }

    
    //Metodo para borrar el contenido de una tabla
    public void borrarDatos() {
		if (properties.get("cleanBBDD").equals("true")) {	
			String sql1 = "DELETE FROM FAVORITOS;";
			String sql2 = "DELETE FROM USUARIOS;";
			String sql3 = "DELETE FROM CONTENIDO;";
			
			try (Connection con = DriverManager.getConnection(connectionString);
			     PreparedStatement pStmt1 = con.prepareStatement(sql1);
				 PreparedStatement pStmt2 = con.prepareStatement(sql2);
				 PreparedStatement pStmt3 = con.prepareStatement(sql3)) {
				
		        if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute()) {
		        	logger.info("Se han borrado los datos");
		        }
			} catch (Exception ex) {
				logger.warning(String.format("Error al borrar los datos: %s", ex.getMessage()));
			}
		}
	}
    
    
    
    // REQUISITO: CONSULTA
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT nombre, nombre_usuario, contraseña, apellido, email FROM USUARIOS;";
        
        try (Connection con = DriverManager.getConnection(connectionString);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                // NOTA: La contraseña no debería recuperarse en una aplicación real, 
                // pero se incluye aquí para hacer el objeto Usuario completo.
                Usuario u = new Usuario(
                    rs.getString("nombre"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contraseña"),
                    rs.getString("apellido"),
                    rs.getString("email")
                );
                usuarios.add(u);
            }
            logger.info(String.format("Se han recuperado %d usuarios.", usuarios.size()));
        } catch (SQLException ex) {
            logger.warning(String.format("Error al consultar usuarios: %s", ex.getMessage()));
        }
        return usuarios;
    }
    
    // --- MÉTODOS ADICIONALES PARA CONTENIDO ---
    
    public void insertarContenido(Contenido c) {
        String sql = "INSERT INTO CONTENIDOS(titulo, tipo, duracion_o_temporadas) VALUES (?, ?, ?);";
        String tipo;
        int valorNumerico = 0;
        
        if (c instanceof Pelicula) {
            tipo = "PELICULA";
            valorNumerico = ((Pelicula) c).getDuracion();
        } else if (c instanceof Serie) {
            tipo = "SERIE";
            valorNumerico = ((Serie) c).getTemporadas();
        } else {
            logger.warning("Tipo de Contenido no válido.");
            return;
        }
        
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            
            pStmt.setString(1, c.getTitulo());
            pStmt.setString(2, tipo);
            pStmt.setInt(3, valorNumerico);
            
            if (pStmt.executeUpdate() == 1) {
                logger.info(String.format("Contenido (%s) insertado: %s", tipo, c.getTitulo()));
            }
        } catch (SQLException ex) {
            logger.warning(String.format("Error al insertar contenido %s: %s", c.getTitulo(), ex.getMessage()));
        }
    }
    
    // --- MÉTODO MAIN DE PRUEBA (Siguiendo el estilo del profesor) ---
    
    public static void main(String[] args) {
        // NOTA: Para que este main funcione, debes tener el driver SQLite-JDBC en tu classpath
        // y los archivos logger.properties y app.properties en la carpeta resources/config.
        
        GestorBD db = new GestorBD();
        
        // 1. CREAR ESTRUCTURA DE LA BD
        db.crearBBDD(); 

        // 2. CREAR OBJETOS DE DOMINIO
        Usuario u1 = new Usuario("Laura", "laura_dev", "pass123", "García", "laura@mail.com");
        Pelicula p1 = new Pelicula("Interstellar", null, null, null, null, 169);
        Serie s1 = new Serie("Stranger Things", null, null, null, null, 4);

        // 3. INSERCIÓN
        db.insertarUsuario(u1);
        db.insertarContenido(p1);
        db.insertarContenido(s1);

        // 4. CONSULTA
        System.out.println("\n--- CONSULTA DE USUARIOS INICIAL ---");
        db.getUsuarios().forEach(u -> System.out.printf("  -> %s (%s)%n", u.getNombre_usuario(), u.getEmail()));
        
        // 5. MODIFICACIÓN
        db.modificarContrasenaUsuario("laura_dev", "nuevaPasswordFuerte"); 
        
        // 6. CONSULTA para verificar la modificación
        System.out.println("\n--- CONSULTA TRAS MODIFICACIÓN ---");
        db.getUsuarios().forEach(u -> System.out.printf("  -> %s / Contraseña: %s%n", u.getNombre_usuario(), u.getContraseña()));
        
     // 7. AÑADIR AQUÍ: BORRADO DE DATOS
        // Llama al método que has creado para vaciar las tablas.
        db.borrarDatos();
        
        
        // 8. CONSULTA para verificar el borrado
        System.out.println("\n--- CONSULTA TRAS BORRADO ---");
        db.getUsuarios().forEach(u -> System.out.printf("  -> %s%n", u.getNombre_usuario()));
    }
}