BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "android_metadata" (
	"locale"	TEXT
);
CREATE TABLE IF NOT EXISTS "users" (
	"id"	INTEGER NOT NULL,
	"full_name"	TEXT NOT NULL,
	"email"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "restaurants" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT NOT NULL,
	"address"	TEXT NOT NULL,
	"comment"	TEXT,
	"image"	TEXT,
	"type"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
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
CREATE TABLE IF NOT EXISTS "bookings" (
	"id"	INTEGER NOT NULL,
	"user_id"	INTEGER NOT NULL,
	"food_id"	INTEGER NOT NULL,
	"bill_id"	INTEGER NOT NULL,
	FOREIGN KEY("food_id") REFERENCES "foods"("id"),
	FOREIGN KEY("user_id") REFERENCES "users"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "bills" (
	"id"	INTEGER NOT NULL,
	"user_id"	INTEGER NOT NULL,
	"total_price"	REAL,
	FOREIGN KEY("user_id") REFERENCES "users"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "comments" (
	"id"	INTEGER NOT NULL,
	"content"	TEXT NOT NULL,
	"food_id"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "android_metadata" VALUES ('en_US');
INSERT INTO "users" VALUES (1,'Vo Van Hau','hau@gmail.com','1');
INSERT INTO "users" VALUES (2,'Lam Tam Nhu','nhu@gmail.com','1');
INSERT INTO "restaurants" VALUES (1,'Pum Pum Tea','HCM','Comment comment','milktea','Delivery');
INSERT INTO "restaurants" VALUES (2,'Korean Food','HCM','Great!!!','korean','Review');
INSERT INTO "restaurants" VALUES (3,'Gogi House','HCM','Great!!!','gogi','Review');
INSERT INTO "restaurants" VALUES (4,'Koi Thé','HCM','Great!!!','koi','Delivery');
INSERT INTO "restaurants" VALUES (5,'Hadilao','HCM','Great!!!','hadilao','Review');
INSERT INTO "restaurants" VALUES (6,'Sushi House','HCM','Great!!!','japan','Review');
INSERT INTO "restaurants" VALUES (7,'Snowee','HCM','Great!!!','snowee','Delivery');
INSERT INTO "restaurants" VALUES (8,'Cocktail','HCM','Great!!!','drink','Review');
COMMIT;
