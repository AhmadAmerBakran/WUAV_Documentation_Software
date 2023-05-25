package easv_2nd_term_exam.dal;

import easv_2nd_term_exam.be.Picture;
import easv_2nd_term_exam.dal.connector.DBConnector;
import easv_2nd_term_exam.dal.interfaces.IPictureDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PictureDAO implements IPictureDAO {

    private DBConnector dbConnector;

    public PictureDAO() {
        dbConnector = new DBConnector();
    }

    @Override
    public Picture createPicture(Picture picture) throws SQLException {
        String sql = "INSERT INTO Picture (InstallationId, PictureName, ImageData) VALUES (?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, picture.getInstallationId());
            pstmt.setString(2, picture.getPictureName());
            pstmt.setBytes(3, picture.getImageData());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    picture.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating picture failed, no ID obtained.");
                }
            }
        }
        return picture;
    }

    public List<Picture> createPictures(List<Picture> pictures) throws SQLException {
        List<Picture> createdPictures = new ArrayList<>();
        String sql = "INSERT INTO Picture (InstallationId, PictureName, ImageData) VALUES (?, ?, ?)";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            for (Picture picture : pictures) {
                pstmt.setInt(1, picture.getInstallationId());
                pstmt.setString(2, picture.getPictureName());
                pstmt.setBytes(3, picture.getImageData());

                pstmt.executeUpdate(); // Execute each prepared statement individually

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        picture.setId(generatedKeys.getInt(1));
                        createdPictures.add(picture);
                    } else {
                        throw new SQLException("Creating picture failed, no ID obtained.");
                    }
                }

                pstmt.clearParameters();
            }
        }
        return createdPictures;
    }


    @Override
    public List<Picture> getPicturesByInstallationId(int installationId) throws SQLException {
        List<Picture> pictures = new ArrayList<>();
        String sql = "SELECT * FROM Picture WHERE InstallationId = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, installationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pictures.add(new Picture(
                            rs.getInt("id"),
                            rs.getString("pictureName"),
                            rs.getBytes("imageData"),
                            rs.getInt("installationId")
                    ));
                }
            }
        }
        return pictures;
    }

    @Override
    public Picture getPicture(int id) throws SQLException {
        Picture picture = null;
        String sql = "SELECT * FROM Picture WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    picture = new Picture(
                            rs.getInt("ID"),
                            rs.getString("PictureName"),
                            rs.getBytes("ImageData"),
                            rs.getInt("InstallationId")
                    );
                }
            }
        }
        return picture;
    }

    @Override
    public List<Picture> getAllPictures() throws SQLException {
        List<Picture> pictures = new ArrayList<>();
        String sql = "SELECT * FROM Picture";
        try (Connection connection = dbConnector.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                pictures.add(new Picture(
                        rs.getInt("ID"),
                        rs.getString("PictureName"),
                        rs.getBytes("ImageData"),
                        rs.getInt("InstallationId")
                ));
            }
        }
        return pictures;
    }

    @Override
    public void updatePicture(Picture picture) throws SQLException {
        String sql = "UPDATE Picture SET InstallationId = ?, PictureName = ?, ImageData = ? WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, picture.getInstallationId());
            pstmt.setString(2, picture.getPictureName());
            pstmt.setBytes(3, picture.getImageData());
            pstmt.setInt(4, picture.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletePicture(int id) throws SQLException {
        String sql = "DELETE FROM Picture WHERE ID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
