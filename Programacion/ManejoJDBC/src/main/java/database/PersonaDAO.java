package database;

import domain.Persona;
import java.sql.*;
import java.util.*;

public class PersonaDAO {
    private static final String SQL_SELECT = "SELECT idPersona, nombrePersona, apellidoPersona, emailPersona, telefonoPersona FROM Persona";
    private static final String SQL_INSERT = "INSERT INTO Persona ( nombrePersona, apellidoPersona, emailPersona, telefonoPersona ) VALUES ( ?, ?, ?, ? )";
    private static final String SQL_SELECT_ONE = "SELECT idPersona, nombrePersona, apellidoPersona, emailPersona, telefonoPersona FROM Persona WHERE idPersona = ?";
    private static final String SQL_UPDATE_ONE = "UPDATE Persona SET nombrePersona = ?, apellidoPersona = ?, emailPersona = ?, telefonoPersona = ? WHERE idPersona = ?";
    private static final String SQL_DELETE_ONE = "DELETE FROM Persona WHERE idPersona = ?";
    
    public List<Persona> find() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<Persona>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement( PersonaDAO.SQL_SELECT );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                int idPersona = rs.getInt( "idPersona" );
                String nombrePersona = rs.getString( "nombrePersona" );
                String apellidoPersona = rs.getString( "apellidoPersona" );
                String emailPersona = rs.getString( "emailPersona" );
                String telefonoPersona = rs.getString( "telefonoPersona" );
                persona = new Persona( idPersona, nombrePersona, apellidoPersona, emailPersona, telefonoPersona );
                personas.add( persona );
            }
        } catch (SQLException ex) {
            ex.printStackTrace( System.out );
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace( System.out );
            }
        }
        return personas;
    }
    
    public int create( Persona persona ) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement( PersonaDAO.SQL_INSERT );
            stmt.setString( 1, persona.getNombrePersona() );
            stmt.setString( 2, persona.getApellidoPersona() );
            stmt.setString( 3, persona.getEmailPersona() );
            stmt.setString( 4, persona.getTelefonoPersona() );
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace( System.out );
        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace( System.out );
            }
        }
        return registros;
    }
    
    public static Persona findOne ( Persona persona ) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona personaEncontrada = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement( PersonaDAO.SQL_SELECT_ONE );
            stmt.setInt( 1, persona.getIdPersona() );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                int idPersona = rs.getInt( "idPersona" );
                String nombrePersona = rs.getString( "nombrePersona" );
                String apellidoPersona = rs.getString( "apellidoPersona" );
                String emailPersona = rs.getString( "emailPersona" );
                String telefonoPersona = rs.getString( "telefonoPersona" );
                personaEncontrada = new Persona( idPersona, nombrePersona, apellidoPersona, emailPersona, telefonoPersona );
            }
        } catch (SQLException ex) {
            ex.printStackTrace( System.out );
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace( System.out );
            }
        }
        return personaEncontrada;
    }
    
    public static int updateOne ( Persona persona ) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_ONE);
            stmt.setString( 1 , persona.getNombrePersona() );
            stmt.setString( 2 , persona.getApellidoPersona() );
            stmt.setString( 3 , persona.getEmailPersona());
            stmt.setString( 4 , persona.getTelefonoPersona() );
            stmt.setInt( 5, persona.getIdPersona() );
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace( System.out );
        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace( System.out );
            }
        }
        return registros;
    }
    
    public static int deleteOne ( Persona persona ) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_ONE);
            stmt.setInt( 1, persona.getIdPersona() );
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace( System.out );
        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch ( SQLException ex ) {
                ex.printStackTrace( System.out );
            }
        }
        return registros;
    }
}
