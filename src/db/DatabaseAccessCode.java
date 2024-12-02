package db;

import model.Attendance;
import model.Equipment;
import model.User;

import java.sql.SQLException;

public class DatabaseAccessCode {

    public static boolean saveMember(User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO user VALUES(?,?,?,?,?,?)",
                user.getUserId(),
                user.getName(),
                user.getContact(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }
    public static <T> T getAllMember() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM user WHERE role=?","MEMBER");
    }
    public static boolean deleteMemberById(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM user WHERE userId=?",id);
    }
    public static <T> T findMemberById(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM user WHERE userId=?",id);
    }

    public static boolean updateMemberById(User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE user SET name = ? , email=? , contact=? WHERE userId=? ",
                user.getName(),user.getEmail(),user.getContact(),user.getUserId());
    }
    public static boolean saveAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO attendance VALUES(?,?,?)",
                attendance.getaId(),
                attendance.getDate(),
                attendance.getUser_userId());
    }
    public static <T> T getAllAttendance(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM attendance WHERE aid=?",id);
    }
    public static boolean deleteAttendanceById(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM attendance WHERE aId=?",id);
    }
    public static <T> T findAttendanceById(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM attendance WHERE aId=?",id);
    }
    public static boolean updateAttendanceById(Attendance attendance) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE attendance SET date = ? , user_userId=? WHERE aId=? ",
                attendance.getDate(),attendance.getUser_userId(),attendance.getaId());
    }

    public static boolean saveEquipment(Equipment equipment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO equipments VALUES(?,?,?)",
                equipment.geteId(),
                equipment.getTitle(),
                equipment.getDescription());
    }
    public static <T> T findAllMember() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM user WHERE role=?","MEMBER");
    }
}
