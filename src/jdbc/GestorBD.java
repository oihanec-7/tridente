package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.Scanner;
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

    public void cargarContenidoDesdeCSV(String ruta) {
        int contador = 0;
        try (Scanner sc = new Scanner(new File(ruta))) {
        	if (sc.hasNextLine()) {
          		 sc.nextLine();
          	 }
               while (sc.hasNextLine()) {
                  String linea = sc.nextLine();
                  String[] partes = linea.split(";");
                  
                  String tipo = partes[0].trim();
                  String titulo = partes[1].trim();
                  
                  ArrayList<String> generos = new ArrayList<>();
                  for (String genero : partes[2].split(",")) {
                      generos.add(genero.trim());
                  }
                  
                  ArrayList<Double> puntuaciones = new ArrayList<>();
                  for (String puntuacionStr : partes[3].split(",")) {
                      puntuaciones.add(Double.parseDouble(puntuacionStr.trim()));
                  }
                  
                  ArrayList<String> cast = new ArrayList<>();
                  for (String actor : partes[4].split(",")) {
                      cast.add(actor.trim());
                  }

                  int valorNumerico = Integer.parseInt(partes[5].trim());

                  Contenido nuevoContenido = null;
                  if (tipo.equalsIgnoreCase("Pelicula")) {
                      nuevoContenido = new Pelicula(titulo, generos, puntuaciones, cast, valorNumerico);
                  } else if (tipo.equalsIgnoreCase("Serie")) {
                      nuevoContenido = new Serie(titulo, generos, puntuaciones, cast, valorNumerico);
                  }

                  if (nuevoContenido != null) {
                      insertarContenido(nuevoContenido); 
                      contador++;
                  }
              }
              
              logger.info(String.format("Carga finalizada. Se han insertado %d contenidos desde el CSV.", contador));
        } catch (FileNotFoundException e) {
            logger.warning(String.format("Error: Archivo CSV no encontrado en la ruta %s", ruta));
        };  
   }
    
    //Metodo para crear las tablas
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
        	
        	if (!pStmt1.execute() && !pStmt2.execute() && !pStmt3.execute()) {
	        	logger.info("Se han creado las tablas");
	        }
		} catch (Exception ex) {
			logger.warning(String.format("Error al crear las tablas: %s", ex.getMessage()));
		}
    }
    
    // Metodo borrar las tablas
    public void borrarBBDD() {
        if (properties.get("deleteBBDD").equals("true")) { 	
            String sql1 = "DROP TABLE IF EXISTS FAVORITOS;";
            String sql2 = "DROP TABLE IF EXISTS USUARIOS;"; 
            String sql3 = "DROP TABLE IF EXISTS CONTENIDOS;";
            
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

    //Insetar usuario
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
    
    //Metodo para insertar contenido
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
    
    //Metodo para añadir una nueva fila a FAVORTIOS
    public void añadirFavorito(String nombreUsuario, String tituloContenido) {
        String sqlIDs = "SELECT U.id_usuario, C.id_contenido FROM USUARIOS U, CONTENIDOS C " +
                        "WHERE U.nombre_usuario = ? AND C.titulo = ?;";
        
        String sqlInsert = "INSERT INTO FAVORITOS(id_usuario_fk, id_contenido_fk) VALUES (?, ?);";
        
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pStmtIDs = con.prepareStatement(sqlIDs);
             PreparedStatement pStmtInsert = con.prepareStatement(sqlInsert)) {
            
            pStmtIDs.setString(1, nombreUsuario);
            pStmtIDs.setString(2, tituloContenido);
            ResultSet rs = pStmtIDs.executeQuery();
            
            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                int idContenido = rs.getInt("id_contenido");
                
                pStmtInsert.setInt(1, idUsuario);
                pStmtInsert.setInt(2, idContenido);
                
                if (pStmtInsert.executeUpdate() == 1) {
                    logger.info(String.format("Favorito añadido: '%s' para el usuario '%s'.", tituloContenido, nombreUsuario));
                }
            } else {
                logger.warning(String.format("Error: Usuario ('%s') o Contenido ('%s') no encontrado.", nombreUsuario, tituloContenido));
            }
            
        } catch (SQLException ex) {
            logger.warning(String.format("Error al añadir favorito (puede que ya exista): %s", ex.getMessage()));
        }
    }
    
    //Metodo para eliminar una fila de FAVORITOS
    public void eliminarFavorito(String nombreUsuario, String tituloContenido) {
        String sql = "DELETE FROM FAVORITOS WHERE id_usuario_fk = (" +
                     " SELECT id_usuario FROM USUARIOS WHERE nombre_usuario = ?" +
                     ") AND id_contenido_fk = (" +
                     " SELECT id_contenido FROM CONTENIDOS WHERE titulo = ?" +
                     ");";
        
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            
            pStmt.setString(1, nombreUsuario);
            pStmt.setString(2, tituloContenido);

            int filasAfectadas = pStmt.executeUpdate();

            if (filasAfectadas > 0) {
                logger.info(String.format("Favorito eliminado: '%s' del usuario '%s'.", tituloContenido, nombreUsuario));
            } else {
                 logger.warning(String.format("No se eliminó ningún favorito. La relación ('%s' y '%s') no existía.", nombreUsuario, tituloContenido));
            }
            
        } catch (SQLException ex) {
            logger.warning(String.format("Error al eliminar favorito: %s", ex.getMessage()));
        }
    }
    
    // Metodo para ahcer update de la contraseña
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
    
    
    //Metodos de consulta
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT nombre, nombre_usuario, contraseña, apellido, email FROM USUARIOS;";
        
        try (Connection con = DriverManager.getConnection(connectionString);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
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
    
    public List<String> getContenidos() {
        List<String> contenidos = new ArrayList<>();
        String sql = "SELECT titulo, tipo, duracion_o_temporadas FROM CONTENIDOS;";
        
        try (Connection con = DriverManager.getConnection(connectionString);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String duracionOTemporadas = rs.getString("duracion_o_temporadas");
                
                String info = String.format("[%s] %s - %s: %s", 
                    tipo, 
                    rs.getString("titulo"), 
                    tipo.equals("PELICULA") ? "Duración (min)" : "Temporadas",
                    duracionOTemporadas
                );
                contenidos.add(info);
            }
            logger.info(String.format("Se recuperaron %d contenidos.", contenidos.size()));
        } catch (SQLException ex) {
            logger.warning(String.format("Error al consultar contenidos: %s", ex.getMessage()));
        }
        return contenidos;
    }
    
    
    public List<String> getFavoritosUsuario(String nombreUsuario) {
        List<String> favoritos = new ArrayList<>();
        
        String sql = "SELECT C.titulo, C.tipo FROM CONTENIDOS C JOIN FAVORITOS F ON C.id_contenido = F.id_contenido_fk " +
                     "JOIN USUARIOS U ON U.id_usuario = F.id_usuario_fk WHERE U.nombre_usuario = ?;";

        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pStmt = con.prepareStatement(sql)) {
            
            pStmt.setString(1, nombreUsuario);
            ResultSet rs = pStmt.executeQuery();
            
            while (rs.next()) {
                favoritos.add(String.format("[%s] %s", rs.getString("tipo"), rs.getString("titulo")));
            }
            logger.info(String.format("Se recuperaron %d favoritos para '%s'.", favoritos.size(), nombreUsuario));
            
        } catch (SQLException ex) {
            logger.warning(String.format("Error al consultar favoritos: %s", ex.getMessage()));
        }
        return favoritos;
    }
}