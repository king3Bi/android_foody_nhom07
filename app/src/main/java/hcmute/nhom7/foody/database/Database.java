package hcmute.nhom7.foody.database;

import android.content.ContentValues;
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
import hcmute.nhom7.foody.model.Bill;
import hcmute.nhom7.foody.model.Booking;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.Restaurant;
import hcmute.nhom7.foody.model.Saved;
import hcmute.nhom7.foody.model.User;

public class Database extends SQLiteOpenHelper
        implements UserDAO, QuanDAO, FoodDAO, SavedDAO, MoreDAO, HomeDAO, LoginDAO, SignupDAO, CartDAO, HistoryDAO {
    public static final String DATABASE_NAME = "foody_db.db";
    public static final int VERSION= 1;
    private Context context;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        SQLiteDatabase db = this.getWritableDatabase();
//        try {
//            initTable(db);
//            initDatabase(db);
//            Toast.makeText(this.context, "Init database successfully ", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("Create DB");
        try {
            initTable(sqLiteDatabase);
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

    public void initTable(SQLiteDatabase sqLiteDatabase) throws IOException {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        InputStream insertsStream = context.getResources().openRawResource(R.raw.create_tb);
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
    public List<Restaurant> getAllQuan() {
        List<Restaurant> restaurants = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM restaurants";
        Cursor result = sqLiteDatabase.rawQuery(sql, null);

        while (result.moveToNext()) {
            String name = result.getString(1);
            String address = result.getString(2);
            String comment = result.getString(3);
            String image = result.getString(4);
            String type = result.getString(5);
            restaurants.add(new Restaurant(image, name, comment, type));
        }

        return restaurants;
    }

    @Override
    public Restaurant getQuanById(int id) {
        Restaurant restaurant = null;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM restaurants WHERE id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[] {Integer.toString(id)});

        if (result.moveToNext()) {
            String name = result.getString(1);
            String address = result.getString(2);
            String comment = result.getString(3);
            String image = result.getString(4);
            restaurant = new Restaurant(image, name, comment, "Review");
        }
        return restaurant;
    }

    @Override
    public boolean insertQuan(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants(name, address, comment , image, type) " +
                "VALUES(?, ?, ?, ?, ?)";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {restaurant.getName(), "HCM", restaurant.getComment(), restaurant.getImage(), restaurant.getType()});
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateQuan(Restaurant restaurant) {
        String sql = "UPDATE restaurants " +
                "SET name = ?, address = ?, comment = ? , image = ?, type = ? " +
                "WHERE id = ?";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {restaurant.getName(), "HCM", restaurant.getComment(), restaurant.getImage(),
                            restaurant.getType(), Integer.toString(restaurant.getId())});
            System.out.println("Update restaurant successfully: " + restaurant.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean bookFood(User user, Food food, int quantity) {
        String sql = "INSERT INTO bookings(user_id, food_id, quantity)" +
                "VALUES(?, ?, ?)";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {Integer.toString(user.getId()),
                            Integer.toString(food.getId()), Integer.toString(quantity)});
            System.out.println("Booking successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean saveFood(User user, Food food) {
        String sql = "INSERT INTO saveds(user_id, food_id)" +
                "VALUES(?, ?)";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {Integer.toString(user.getId()),
                            Integer.toString(food.getId())});
            System.out.println("Saved food successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Food> getAllFood() {
        List<Food> foods = new ArrayList<>();

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
            foods.add(new Food(image, name, description, price, restaurantId));
        }

        return  foods;
    }

    @Override
    public List<Food> getFoodByRestaurantId(int restaurantId) {
        List<Food> foods = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM foods WHERE restaurant_id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(restaurantId)});

        while (result.moveToNext()) {
            int id = result.getInt(1);
            String name = result.getString(2);
            String image = result.getString(3);
            String description = result.getString(4);
            Double price = result.getDouble(5);
            foods.add(new Food(image, name, description, price, restaurantId));
        }

        return  foods;
    }

    @Override
    public List<Food> findFoodByName(String name) {
        return null;
    }

    @Override
    public Food getFoodId(int id) {
        Food food = null;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM foods WHERE id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(id)});

        if (result.moveToNext()) {
            String name = result.getString(1);
            String image = result.getString(2);
            String description = result.getString(3);
            Double price = result.getDouble(4);
            food = new Food(id, image, name, description, price);
        }

        return  food;
    }

    @Override
    public boolean deleteCart(List<Booking> bookingList) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String sql = "BEGIN TRANSACTION";
        sqLiteDatabase.execSQL(sql);

        try {
            bookingList.forEach(booking -> {
                String sqlDelete = "DELETE FROM bookings WHERE id = ?";
                sqLiteDatabase.execSQL(sqlDelete, new String[]{Integer.toString(booking.getId())});
            });
        } catch (Exception e) {
            e.printStackTrace();
            String rollBack = "ROLLBACK";
            sqLiteDatabase.execSQL(rollBack);
            return false;
        }

        String commit = "COMMIT";
        sqLiteDatabase.execSQL(commit);

        return true;
    }

    @Override
    public boolean createBill(Bill bill, List<Booking> bookingList) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String sql = "BEGIN TRANSACTION";
        sqLiteDatabase.execSQL(sql);

        try {
            ContentValues values = new ContentValues();
            values.put("user_id", Integer.toString(bill.getUserId()));
            values.put("total_price", Double.toString(bill.getTotalPrice()));
            values.put("address", bill.getAddress());
            values.put("phone_number", bill.getPhoneNumber());
            long insertedId= sqLiteDatabase.insert("bills", "", values) ;

            Bill billInserted = getBillByRowId(insertedId);

            bookingList.forEach(booking -> {
                String updateBooking = "UPDATE bookings " +
                        "SET bill_id = ? WHERE id = ?";
                sqLiteDatabase.execSQL(updateBooking,
                        new String[]{Long.toString(billInserted.getId()), Integer.toString(booking.getId())});
            });
        } catch (Exception e) {
            e.printStackTrace();
            String rollBack = "ROLLBACK";
            sqLiteDatabase.execSQL(rollBack);
            return false;
        }

        String commit = "COMMIT";
        sqLiteDatabase.execSQL(commit);

        return true;
    }

    public Bill getBillByRowId(long rowId) {
        Bill bill = null;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM bills WHERE _rowid_ = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[] {Long.toString(rowId)});

        if (result.moveToNext()) {
            int id = result.getInt(0);
            bill = new Bill(id);
        }
        return bill;
    }

    @Override
    public boolean removeSaved(int userId, int foodId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "DELETE FROM saveds WHERE user_id = ? AND food_id = ?";

        try {
            sqLiteDatabase.execSQL(sql, new String[]{Integer.toString(userId), Integer.toString(foodId)});
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean checkSavedFood(int userId, int foodId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM saveds WHERE user_id = ? AND food_id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(userId), Integer.toString(foodId)});

        if (result.moveToNext()) {
            return true;
        }

        return  false;
    }

    @Override
    public boolean insertFood(Food food) {
        String sql = "INSERT INTO foods(name, image, description, price, restaurant_id) " +
                "VALUES(?, ?, ?, ?, ?)";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {food.getName(), food.getImage(), food.getDescription(),
                            food.getPriceString(), Integer.toString(food.getRestaurantId())});
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
    public List<Saved> getAllSavedOfUser(int userId) {
        return null;
    }

    @Override
    public List<Saved> getAllFoodSavedOfUser(int userId) {
        List<Saved> saveds = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT food_id FROM saveds WHERE user_id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(userId)});

        while (result.moveToNext()) {
            int foodId = result.getInt(0);
            saveds.add(new Saved(userId, foodId));
        }

        return  saveds;
    }

    @Override
    public List<Saved> getAllRestaurantSavedOfUser(int userId) {
        return null;
    }

    @Override
    public List<Food> searchMonAn(String keyWord) {
        List<Food> foods = new ArrayList<>();

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
            foods.add(new Food(id, image, name, description, price));
        }

        return foods;
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
    public boolean signUp(User user) {
        String sql = "INSERT INTO users(full_name, email, password) " +
                "VALUES(?, ?, ?)";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new String[] {user.getHoTen(), user.getEmail(), user.getPassword()});
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Booking> getBookingOfUser(User user) {
        List<Booking> bookingList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM bookings WHERE user_id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(user.getId())});

        while (result.moveToNext()) {
            int id = result.getInt(0);
            int foodId = result.getInt(1);
            int quantity = result.getInt(2);
            bookingList.add(new Booking(id, user.getId(), foodId, quantity));
        }

        return  bookingList;
    }

    @Override
    public List<Booking> getBookingInCartOfUser(User user) {
        List<Booking> bookingList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM bookings WHERE user_id = ? AND bill_id IS NULL";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(user.getId())});

        while (result.moveToNext()) {
            int id = result.getInt(0);
            int foodId = result.getInt(1);
            int quantity = result.getInt(2);
            bookingList.add(new Booking(id, user.getId(), foodId, quantity));
        }

        return  bookingList;
    }

    @Override
    public List<Bill> getAllBillOfUser(User user) {
        List<Bill> billList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM bills WHERE user_id = ?";
        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(user.getId())});

        while (result.moveToNext()) {
            int id = result.getInt(0);
            double totalOrice = result.getDouble(2);
            String address = result.getString(4);
            String phoneNumber = result.getString(5);
            billList.add(new Bill(id, user.getId(), totalOrice, address, phoneNumber));
        }

        return  billList;
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor result = sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(id)});
        if (result.moveToNext()) {
            String fullName = result.getString(1);
            String email = result.getString(2);
            String password = result.getString(3);
            return  new User(id, fullName, email, password);
        }

        return null;
    }

    @Override
    public boolean updateFullInfo(User user) {
        String sql = "UPDATE users " +
                "SET full_name = ?, email = ?, password = ? " +
                "WHERE id = ?";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new Object[]{user.getHoTen(), user.getEmail(), user.getPassword(), user.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateInfo(User user) {
        String sql = "UPDATE users " +
                "SET full_name = ?, email = ? " +
                "WHERE id = ?";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL(sql,
                    new Object[]{user.getHoTen(), user.getEmail(), user.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
