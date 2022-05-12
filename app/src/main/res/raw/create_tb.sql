BEGIN TRANSACTION;
DROP TABLE IF EXISTS "android_metadata";
CREATE TABLE IF NOT EXISTS "android_metadata" (
	"locale"	TEXT
);
DROP TABLE IF EXISTS "users";
CREATE TABLE IF NOT EXISTS "users" (
	"id"	INTEGER NOT NULL,
	"full_name"	TEXT NOT NULL,
	"email"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "restaurants";
CREATE TABLE IF NOT EXISTS "restaurants" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT NOT NULL,
	"address"	TEXT NOT NULL,
	"comment"	TEXT,
	"image"	TEXT,
	"type"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "foods";
CREATE TABLE IF NOT EXISTS "foods" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT NOT NULL,
	"image"	TEXT,
	"description"	BLOB,
	"price"	REAL NOT NULL,
	"restaurant_id"	INTEGER,
	FOREIGN KEY("restaurant_id") REFERENCES "restaurants"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "bookings";
CREATE TABLE IF NOT EXISTS "bookings" (
	"id"	INTEGER NOT NULL,
	"user_id"	INTEGER NOT NULL,
	"food_id"	INTEGER NOT NULL,
	"bill_id"	INTEGER,
	"quantity"	INTEGER NOT NULL DEFAULT 0,
	FOREIGN KEY("food_id") REFERENCES "foods"("id"),
	FOREIGN KEY("user_id") REFERENCES "users"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "comments";
CREATE TABLE IF NOT EXISTS "comments" (
	"user_id"	INTEGER NOT NULL,
	"content"	TEXT NOT NULL,
	"food_id"	INTEGER NOT NULL,
	"rate"	INTEGER,
	"comment_at"	TEXT,
	FOREIGN KEY("user_id") REFERENCES "users"("id")
);
DROP TABLE IF EXISTS "bills";
CREATE TABLE IF NOT EXISTS "bills" (
	"id"	INTEGER NOT NULL,
	"user_id"	INTEGER NOT NULL,
	"total_price"	REAL,
	"create_at"	TEXT,
	"address" TEXT NOT NULL,
	"phone_number" TEXT NOT NULL,
	FOREIGN KEY("user_id") REFERENCES "users"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "saveds";
CREATE TABLE IF NOT EXISTS "saveds" (
	"user_id"	INTEGER NOT NULL,
	"food_id"	INTEGER,
	"restaurant_id"	INTEGER,
	FOREIGN KEY("food_id") REFERENCES "foods"("id"),
	FOREIGN KEY("user_id") REFERENCES "users"("id"),
	FOREIGN KEY("restaurant_id") REFERENCES "restaurants"("id")
);
COMMIT;