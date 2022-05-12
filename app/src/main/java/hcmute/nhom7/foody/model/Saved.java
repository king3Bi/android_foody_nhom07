package hcmute.nhom7.foody.model;

public class Saved {
    private int userId;
    private int foodId;
    private int restaurantId;

    public Saved(int userId, int foodId, int restaurantId) {
        this.userId = userId;
        this.foodId = foodId;
        this.restaurantId = restaurantId;
    }

    public Saved(int userId, int foodId) {
        this.userId = userId;
        this.foodId = foodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
