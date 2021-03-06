BEGIN TRANSACTION;
INSERT INTO "android_metadata" VALUES ('en_US');
INSERT INTO "users" VALUES (1,'Vo Van Hau','hau@gmail.com','1');
INSERT INTO "users" VALUES (2,'Lam Tam Nhu','nhu@gmail.com','1');
INSERT INTO "restaurants" VALUES (1,'Hamburger - Điện Biên Phủ','625 Điện Biên Phủ, P. 25, Bình Thạnh, TP. HCM','','hambuger_dien_bien_phu','Delivery');
INSERT INTO "foods" VALUES (1,'Khoai tây chiên','khoai_tay_chien','size nhỏ',13500,1);
INSERT INTO "foods" VALUES (2,'Combo Gà Viên + Khoai Tây','combo_ga_khoai_tay_chien','',54000,1);
INSERT INTO "foods" VALUES (3,'Gà nướng tóp mỡ cay','ga_nuong','nửa con',104000,1);
INSERT INTO "restaurants" VALUES (2,'Thành Mập','159/49/9 Bạch Đằng, P. 2,  Quận Tân Bình, TP. HCM','','thanh_map','Review');
INSERT INTO "foods" VALUES (4,'Chân gà rút xương ngâm sả tắc','chan_ga_rut_xuong','nhỏ',110000 ,2);
INSERT INTO "foods" VALUES (5,'Chè tuyết yến nhựa đào','che_tuyet','',32000,2);
INSERT INTO "foods" VALUES (6,'Rong biển cháy tỏi mè - 70gr','rong_bien','',35000,2);
INSERT INTO "restaurants" VALUES (3,'Ka Coffee - Pasteur','230 Pasteur, P. 6,  Quận 3, TP. HCM','','ka_coffee','Review');
INSERT INTO "foods" VALUES (7,'Pạc sỉu','pac_siu','',70000,3);
INSERT INTO "foods" VALUES (8,'Combo Ngậy Ngậy','','',276000,3);
INSERT INTO "foods" VALUES (9,'Combo Truyền Thống','','',150000,3);
INSERT INTO "restaurants" VALUES (4,'Ngọc Châu - Bánh Mì & Xôi Mặn','6 Nguyễn Thị Tần, P. 3,  Quận 8, TP. HCM','','ngoc_chau','Delivery');
INSERT INTO "foods" VALUES (10,'Xôi mặn','xoi_man','xoi_man',25000,1);
INSERT INTO "foods" VALUES (11,'Bánh mì thịt nguội','banh_mi_thit','',25000,1);
INSERT INTO "foods" VALUES (12,'Bánh giò','banh_gio','',15000,1);
INSERT INTO "restaurants" VALUES (5,'Hadilao','HCM','Great!!!','hadilao','Review');
INSERT INTO "foods" VALUES (13,'Tên món ăn','','Mô tả',200000,1);
INSERT INTO "foods" VALUES (14,'Tên món ăn','','Mô tả',300000,1);
INSERT INTO "foods" VALUES (15,'Tên món ăn','','Mô tả',100000,1);
INSERT INTO "restaurants" VALUES (6,'Sushi House','HCM','Great!!!','japan','Review');
INSERT INTO "foods" VALUES (16,'Tên món ăn','','Mô tả',200000,1);
INSERT INTO "foods" VALUES (17,'Tên món ăn','','Mô tả',300000,1);
INSERT INTO "foods" VALUES (18,'Tên món ăn','','Mô tả',100000,1);
INSERT INTO "restaurants" VALUES (7,'Snowee','HCM','Great!!!','snowee','Delivery');
INSERT INTO "foods" VALUES (19,'Tên món ăn','','Mô tả',200000,1);
INSERT INTO "foods" VALUES (20,'Tên món ăn','','Mô tả',300000,1);
INSERT INTO "foods" VALUES (21,'Tên món ăn','','Mô tả',100000,1);
INSERT INTO "restaurants" VALUES (8,'Cocktail','HCM','Great!!!','drink','Review');
INSERT INTO "foods" VALUES (22,'Tên món ăn','','Mô tả',200000,1);
INSERT INTO "foods" VALUES (23,'Tên món ăn','','Mô tả',300000,1);
INSERT INTO "foods" VALUES (24,'Tên món ăn','','Mô tả',100000,1);
INSERT INTO "restaurants" VALUES (9,'Pum Pum Tea','HCM','Comment comment','milktea','Delivery');
INSERT INTO "foods" VALUES (25,'Tên món ăn','','Mô tả',200000,1);
INSERT INTO "foods" VALUES (26,'Tên món ăn','','Mô tả',300000,1);
INSERT INTO "foods" VALUES (27,'Tên món ăn','','Mô tả',100000,1);
INSERT INTO "restaurants" VALUES (10,'Korean Food','HCM','Great!!!','korean','Review');
INSERT INTO "restaurants" VALUES (11,'Gogi House','HCM','Great!!!','gogi','Review');
INSERT INTO "restaurants" VALUES (12,'Koi Thé','HCM','Great!!!','koi','Delivery');
INSERT INTO "restaurants" VALUES (13,'Hadilao','HCM','Great!!!','hadilao','Review');
INSERT INTO "restaurants" VALUES (14,'Sushi House','HCM','Great!!!','japan','Review');
INSERT INTO "restaurants" VALUES (15,'Snowee','HCM','Great!!!','snowee','Delivery');
INSERT INTO "restaurants" VALUES (16,'Cocktail','HCM','Great!!!','drink','Review');
INSERT INTO "restaurants" VALUES (17,'Pum Pum Tea','HCM','Comment comment','milktea','Delivery');
COMMIT;
