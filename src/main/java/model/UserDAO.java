package model;

import java.sql.*;

public class UserDAO {

    public UserBean doRetrieveById(int id_utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT *" +
                            " FROM Utente" + " WHERE ID_Utente = ?");

            ps.setInt(1, id_utente);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserBean user = new UserBean();

                user.setId_utente(rs.getInt(1));
                user.setNome(rs.getString(2));
                user.setCognome(rs.getString(3));
                user.setUsername(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));
                if(rs.getString(7) != null){
                    user.setDataNascita(rs.getString(7));
                }

                if(rs.getString(8) != null) {
                    user.setCitta(rs.getString(8));
                }

                if(rs.getString(9) != null) {
                    user.setProvincia(rs.getString(9));
                }

                if(rs.getString(10) != null) {
                    user.setIndirizzo(rs.getString(10));
                }

                if(rs.getString(11) != null) {
                    user.setCodicePostale(rs.getString(11));
                }

                if(rs.getInt(12) == 1) {
                    user.setAmministratore(rs.getInt(12));
                } else{
                    user.setAmministratore(0);
                }

                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public UserBean doRetrieveByEmailAndPassword(String emailInserita, String passwordInserita) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT *" +
                            " FROM Utente" + " WHERE Email = ? AND Passw = ?");

            ps.setString(1, emailInserita);
            ps.setString(2, passwordInserita);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserBean user = new UserBean();
                user.setId_utente(rs.getInt(1));
                user.setNome(rs.getString(2));
                user.setCognome(rs.getString(3));
                user.setUsername(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));
                if(rs.getString(7) != null){
                    user.setDataNascita(rs.getString(7));
                }

                if(rs.getString(8) != null) {
                    user.setCitta(rs.getString(8));
                }

                if(rs.getString(9) != null) {
                    user.setProvincia(rs.getString(9));
                }

                if(rs.getString(10) != null) {
                    user.setIndirizzo(rs.getString(10));
                }

                if(rs.getString(11) != null) {
                    user.setCodicePostale(rs.getString(11));
                }

                if(rs.getInt(12) == 1) {
                    user.setAmministratore(rs.getInt(12));
                } else{
                    user.setAmministratore(0);
                }

                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(UserBean userBean) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (Nome, Cognome, Username, Email, Passw) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, userBean.getNome());
            ps.setString(2, userBean.getCognome());
            ps.setString(3, userBean.getUsername());
            ps.setString(4, userBean.getEmail());
            ps.setString(5, userBean.getPassword());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            userBean.setId_utente(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(UserBean userBean) {

        try (Connection con = ConPool.getConnection()) {

            Statement st = con.createStatement();
            String query = "UPDATE Utente SET Nome = '" + userBean.getNome() + "', Cognome = '" + userBean.getCognome() + "', Username = '" + userBean.getUsername() + "', Email = '" + userBean.getEmail() + "', Passw = '" + userBean.getPassword() + "', Data_Nascita = '" + userBean.getDataNascita() +  "', Citta = '" + userBean.getCitta() + "', Provincia = '" + userBean.getProvincia() + "', Indirizzo = '" + userBean.getIndirizzo() + "', Codice_Postale = '" + userBean.getCodicePostale() + "', Amministratore = '" + userBean.getAmministratore() + "' WHERE Id_Utente = " + userBean.getId_utente();
            st.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(UserBean userBean) {

        try (Connection con = ConPool.getConnection()) {

            Statement st = con.createStatement();
            String query = "DELETE FROM Utente WHERE ID_Utente = " + userBean.getId_utente();
            st.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
