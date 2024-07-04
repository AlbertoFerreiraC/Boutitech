package DataBase;

import javax.swing.JOptionPane;
import java.sql.*;

public class ConectoBD {

    public Connection conectar = null;

    public Statement sentencia;
    public ResultSet resultado;

    public Connection getconexion() {
        return conectar;
    }

    public boolean Conectado() {
        if (conectar != null) {
            return true;
        } else {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                //Parametros del getConecction(url("jdbc:mysql://localhost:3306/nombreBD");
                //usuario,contrasenha) 181.120.20.6
                conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/boutitech", "root", "");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos\n"
                        + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    //Metodo de desconexion de la base de datos
    public void Desconectado() {
        try {
            conectar.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al desconectar\n"
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    //Metodo para guardar en la base de datos
    //--------------------------------------------------------------------------
    public boolean guardar(String tabla, String valores) {
        int res = 0;
        try {
            System.err.println("insert into " + tabla + " values (" + valores + ")");
            Statement stmt = conectar.createStatement();
            res = stmt.executeUpdate("insert into " + tabla + " values (" + valores + ")");

        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    //Metodo para guardar en la base de datos
    //--------------------------------------------------------------------------
    public void guardado_especial(String dato) {
        int res = 0;
        try {
            Statement stmt = conectar.createStatement();
            res = stmt.executeUpdate(dato);
        } catch (SQLException ex) {

        }

    }

    //Metodo para seleccionar el maximo
    public ResultSet Maximo(String campos, String tabla) {
        ResultSet rs = null;
        try {
            Statement stmt = conectar.createStatement();
            String query = "SELECT MAX(" + campos + ")+1 from " + tabla + ";";
            System.out.println("Query: " + query);
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n"
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

    //Metodo para listar en la base de datos
    //--------------------------------------------------------------------------
    public ResultSet listar(String campos, String tabla, String condicion) {
        ResultSet rs = null;
        try {
            Statement stmt = conectar.createStatement();
            System.err.println("select " + campos + " from " + tabla
                    + " " + condicion);
            rs = stmt.executeQuery("select " + campos + " from " + tabla
                    + " " + condicion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n"
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

    //Metodo para listar sin condicion
        public ResultSet listarSinCondi(String campos, String tabla) {
        ResultSet rs = null;
        try {
            Statement stmt = conectar.createStatement();
            System.err.println("select " + campos + " from " + tabla
                    + " ");
            rs = stmt.executeQuery("select " + campos + " from " + tabla
                    + " ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n"
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    //Metodo para listado especial en la base de datos
    //--------------------------------------------------------------------------
    public ResultSet listar_especial(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = conectar.createStatement();
            System.out.println(query);
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos\n"
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

    //Metodo para elimina de la base de datos
    //--------------------------------------------------------------------------
    public boolean eliminar(String tabla, String condicion) {
        int res = 0;
        try {
            Statement s = conectar.createStatement();

            res = s.executeUpdate("delete from " + tabla + " where " + condicion);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Error!!" + e.getMessage(),
                    "Atencion", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    //Metodo para actualizar de la base de datos
    //--------------------------------------------------------------------------
    public boolean actualizar(String tabla, String campos) {
        int res = 0;
        try {
            Statement s = conectar.createStatement();
            System.out.println("update " + tabla + " set " + campos);
            res = s.executeUpdate("update " + tabla + " set " + campos);
            /* JOptionPane.showMessageDialog(null, "Se ha guardado satisfactoriamente!!! "
             , "Modificaci\u00f3n", JOptionPane.INFORMATION_MESSAGE);*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Error!!\n"
                    + e.getMessage(), "Atencion", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
        public boolean actualizar(String tabla, String campos, String criterio){
        int res = 0;
        try
        {
            Statement s = conectar.createStatement();
            System.out.println("update "+tabla+" set "+campos+" where " +criterio);
            res = s.executeUpdate("update "+tabla+" set "+campos+" where " +criterio);
           /* JOptionPane.showMessageDialog(null, "Se ha guardado satisfactoriamente!!! "
                    , "Modificaci\u00f3n", JOptionPane.INFORMATION_MESSAGE);*/
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Ocurri\u00f3 un Error!!\n"
                    +e.getMessage() , "Atenci\u00f3n", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

}
