package hcmute.nhom7.foody.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.model.MonAn;
import hcmute.nhom7.foody.model.Quan;
import hcmute.nhom7.foody.model.Saved;
import hcmute.nhom7.foody.model.User;

public class Database extends SQLiteOpenHelper
        implements UserDAO, QuanDAO, FoodDAO, SavedDAO, MoreDAO, HomeDAO, LoginDAO, SignupDAO {
    public static final String DATABASE_NAME = "foody_db.db";
    public static final int VERSION= 1;
    private Context context;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            initDatabase(db);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("Create DB");
        try {
            initDatabase(sqLiteDatabase);
            Toast.makeText(this.context, "Init database successfully ", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this.context, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("Upgrade DB");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void initDatabase(SQLiteDatabase sqLiteDatabase) throws IOException {
        // Open the resource
        InputStream insertsStream = context.getResources().openRawResource(R.raw.db);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

        // Iterate through lines (assuming each insert has its own line and theres no other stuff)
        String sql = "";
        while (insertReader.ready()) {
            String lineSQL = insertReader.readLine();
            sql = sql.concat("\n");
            sql = sql.concat(lineSQL);
        }
        String[] sqls = sql.split(";");
        for (String statement: sqls) {
            System.out.println(statement);
            sqLiteDatabase.execSQL(statement);
        }
        insertReader.close();
    }

    @Override
    public User login(String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        User user = null;
        String sql = "SELECT * FROM users " +
                "WHERE email = ? AND password = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[] {email, password});
        if (result.moveToNext()) {
            int id = result.getInt(0);
            String hoTen = result.getString(1);
            user = new User(id, hoTen, email, password);
        }
        return user;
    }

    @Override
    public List<Quan> getAllQuan() {
        List<Quan> quans = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM restaurants";
        Cursor result = sqLiteDatabase.rawQuery(sql, null);

        while (result.moveToNext()) {
            String name = result.getString(1);
            String address = result.getString(2);
            String comment = result.getString(3);
            String image = result.getString(4);
            String type = result.getString(5);
            quans.add(new Quan(image, name, comment, type));
        }

        return  quans;
    }

    @Override
    public Quan getQuanById(int id) {
        Quan quan = null;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM restaurants WHERE id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[] {Integer.toString(id)});

        if (result.moveToNext()) {
            String name = result.getString(1);
            String address = result.getString(2);
            String comment = result.getString(3);
            String image = result.getString(4);
            quan = new Quan(image, name, comment, "Review");
        }
        return quan;
    }

    @Override
    public boolean insertQuan(Quan quan) {
        String sql = "INSERT INTO restaurants(name, address, comment , image, type) " +
                "VALUES(?, ?, ?, ?, ?)";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {quan.getName(), "HCM", quan.getComment(), quan.getImage(), quan.getType()});
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateQuan(Quan quan) {
        String sql = "UPDATE restaurants " +
                "SET name = ?, address = ?, comment = ? , image = ?, type = ? " +
                "WHERE id = ?";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {quan.getName(), "HCM", quan.getComment(), quan.getImage(),
                            quan.getType(), Integer.toString(quan.getId())});
            System.out.println("Update restaurant successfully: " + quan.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<MonAn> getAllFood() {
        List<MonAn> foods = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM foods";
        Cursor result = sqLiteDatabase.rawQuery(sql, null);

        while (result.moveToNext()) {
            int id = result.getInt(0);
            String name = result.getString(1);
            String image = result.getString(2);
            String description = result.getString(3);
            Double price = result.getDouble(4);
            int restaurantId = result.getInt(5);
            foods.add(new MonAn(image, name, description, Double.toString(price)));
        }

        return  foods;
    }

    @Override
    public List<MonAn> getFoodByRestaurantId(int restaurantId) {
        List<MonAn> foods = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM foods WHERE restaurant_id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(restaurantId)});

        while (result.moveToNext()) {
            int id = result.getInt(1);
            String name = result.getString(2);
            String image = result.getString(3);
            String description = result.getString(4);
            Double price = result.getDouble(5);
            foods.add(new MonAn(image, name, description, Double.toString(price)));
        }

        return  foods;
    }

    @Override
    public List<MonAn> findFoodByName(String name) {
        return null;
    }

    @Override
    public MonAn getFoodId(int id) {
        MonAn food = null;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM foods WHERE id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(id)});

        if (result.moveToNext()) {
            String name = result.getString(2);
            String image = result.getString(3);
            String description = result.getString(4);
            Double price = result.getDouble(5);
            food = new MonAn(image, name, description, Double.toString(price));
        }

        return  food;
    }

    @Override
    public boolean insertFood(MonAn monAn) {
        String sql = "INSERT INTO foods(name, image, description, price, restaurant_id) " +
                "VALUES(?, ?, ?, ?, ?)";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {monAn.getTenMonAn(), monAn.getImage(), monAn.getMoTa(), monAn.getGia(), "1"});
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Saved> getAllSaved() {
        return null;
    }

    @Override
    public List<Saved> getAllFoodSaved() {
        return null;
    }

    @Override
    public List<Saved> getAllRestaurantSaved() {
        return null;
    }

    @Override
    public List<MonAn> searchMonAn(String keyWord) {
        List<MonAn> foods = new ArrayList<>();

        String formatKeyWord = String.format("%%%s%%", keyWord);

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM foods WHERE name LIKE ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{formatKeyWord});

        while (result.moveToNext()) {
            int id = result.getInt(0);
            String name = result.getString(1);
            String image = result.getString(2);
            String description = result.getString(3);
            Double price = result.getDouble(4);
            foods.add(new MonAn(id, image, name, description, Double.toString(price)));
        }

        return  foods;
    }

    @Override
    public boolean checkExistsEmail(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT email FROM users WHERE email = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{email});

        if (result.moveToNext()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean signUp(String fullName, String email, String password) {
        String sql = "INSERT INTO users(full_name, email, password) " +
                "VALUES(?, ?, ?)";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {fullName, email, password});
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
