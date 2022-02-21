DROP SCHEMA IF EXISTS CarRentalSpring;
CREATE SCHEMA IF NOT EXISTS CarRentalSpring DEFAULT CHARACTER SET utf8 ;
USE CarRentalSpring ;

-- -----------------------------------------------------
-- Table CarRentalSpring.role
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS CarRentalSpring.role (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX name_UNIQUE (`name` ASC) VISIBLE,
  UNIQUE INDEX idrole_UNIQUE (`id` ASC) VISIBLE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table CarRentalSpring.user
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS CarRentalSpring.user (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  password VARCHAR(200) NOT NULL,
  email VARCHAR(45) NOT NULL,
  phone VARCHAR(45) NOT NULL,
  role_id INT NOT NULL,
  blocked bit NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX id_UNIQUE (`id` ASC) VISIBLE,
  INDEX fk_user_role1_idx (`role_id` ASC) VISIBLE,
  CONSTRAINT fk_user_role1
    FOREIGN KEY (`role_id`)
    REFERENCES CarRentalSpring.role (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table CarRentalSpring.city
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS CarRentalSpring.city (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX id_UNIQUE (`id` ASC) VISIBLE)
 ENGINE = InnoDB;
 
 
-- -----------------------------------------------------
-- Table CarRentalSpring.brand
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS CarRentalSpring.brand (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX name_UNIQUE (`name` ASC) VISIBLE,
  UNIQUE INDEX idrole_UNIQUE (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table CarRentalSpring.category
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS CarRentalSpring.category (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX name_UNIQUE (`name` ASC) VISIBLE,
  UNIQUE INDEX idrole_UNIQUE (`id` ASC) VISIBLE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table CarRentalSpring.status
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS CarRentalSpring.status (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX name_UNIQUE (`name` ASC) VISIBLE,
  UNIQUE INDEX idrole_UNIQUE (`id` ASC) VISIBLE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table CarRentalSpring.car
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS CarRentalSpring.car (
  id INT NOT NULL AUTO_INCREMENT,
  brand_id INT NOT NULL,
  model VARCHAR(45) NOT NULL,
  passengers INT NOT NULL,
  price INT NOT NULL,
  status_id INT NOT NULL,
  transmission VARCHAR(45) NOT NULL,
  city_id INT NOT NULL,
  category_id INT NOT NULL,
  image_url VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX id_UNIQUE (`id` ASC) VISIBLE,
  CONSTRAINT fk_car_city
    FOREIGN KEY (`city_id`)
    REFERENCES CarRentalSpring.city (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_car_brand
    FOREIGN KEY (`brand_id`)
    REFERENCES CarRentalSpring.brand (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_car_category
    FOREIGN KEY (`category_id`)
    REFERENCES CarRentalSpring.category (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_car_status
    FOREIGN KEY (`status_id`)
    REFERENCES CarRentalSpring.status (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table CarRentalSpring.order
-- -----------------------------------------------------


CREATE TABLE IF NOT EXISTS CarRentalSpring.orders (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  car_id INT,
  city_id INT NOT NULL,
  pickup_date DATE NOT NULL,
  dropoff_date DATE NOT NULL,
  total_price INT NOT NULL,
  status_id INT NOT NULL,
  driver bit NOT NULL,
  comments VARCHAR(100),
  PRIMARY KEY (`id`),
  UNIQUE INDEX id_UNIQUE (`id` ASC) VISIBLE,
  INDEX fk_order_user1_idx (`user_id` ASC) VISIBLE,
  INDEX fk_order_car1_idx (`car_id` ASC) VISIBLE,
  CONSTRAINT fk_order_user1
    FOREIGN KEY (`user_id`)
    REFERENCES CarRentalSpring.user (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_order_car1
    FOREIGN KEY (`car_id`)
    REFERENCES CarRentalSpring.car (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT fk_order_city
    FOREIGN KEY (`city_id`)
    REFERENCES CarRentalSpring.city (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_order_status
    FOREIGN KEY (`status_id`)
    REFERENCES CarRentalSpring.status (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Insert roles
insert into CarRentalSpring.role (name) values('user'), ('admin'), ('manager');

-- Insert cities
insert into CarRentalSpring.city (name) values('Kyiv'), ('Kharkiv');

-- Insert users
insert into CarRentalSpring.user (name, password, email, phone, role_id, blocked) values
('Dmytro Hihiniak', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email@gmail.com', '464748383', 1, 0), 
('Dima Admin', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email2@gmail.com', '464748385', 2, 0),
('Volodymyr', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email3@gmail.com', '464748387', 3, 0),
('Vladyslav', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email4@gmail.com', '464748383', 3, 0),
('Wendy T', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email5@gmail.com', '464748383', 1, 0),
('Maxim H', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email6@gmail.com', '464748383', 3, 0),
('Kate J', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email7@gmail.com', '464748383', 1, 0),
('Jason K', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email8@gmail.com', '464748383', 1, 0),
('Anton Z', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email9@gmail.com', '464748383', 1, 0),
('Mykhailo Y', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email10@gmail.com', '464748383', 1, 0),
('Dmytro A', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email11@gmail.com', '464748383', 1, 0),
('Artem V', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email12@gmail.com', '464748383', 1, 0),
('Alex L', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email13@gmail.com', '464748383', 1, 0),
('Maria D', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email14@gmail.com', '464748383', 1, 0),
('Jane E', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email15@gmail.com', '464748383', 1, 0),
('Lebron J', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email16@gmail.com', '464748383', 1, 0),
('Kobe B', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email17@gmail.com', '464748383', 1, 0),
('Thiery H', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email18@gmail.com', '464748383', 1, 0),
('Tom H', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email19@gmail.com', '464748383', 1, 0),
('Zendaya I', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email20@gmail.com', '464748383', 1, 0),
('Vin D', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email21@gmail.com', '464748383', 1, 0),
('Paul V', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email22@gmail.com', '464748383', 1, 0),
('Jason S', '$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm', 'email23@gmail.com', '464748383', 1, 0);


-- Insert categories
insert into CarRentalSpring.category (name) values('Economy'), ('Standard'), ('Premium');

-- Insert brands
insert into CarRentalSpring.brand (name) values('Mercedes-Benz'), ('Porsche'),('Audi'), ('Toyota'),
('Hyundai'), ('Volkswagen');

-- Insert statuses
insert into CarRentalSpring.status (name) values('Available'), ('Busy'), ('New'), 
('Approved'), ('Canceled'), ('Done');



-- Kyiv cars
insert into CarRentalSpring.car (brand_id, model, passengers, price, status_id, transmission, city_id, category_id, image_url) 
values
(3,'R8 Spyder V10 Convertible', 2, 185, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-45-iz0207-01p.jpg'),
(2, 'Panamera Turbo', 4, 120, 2, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-45-iz0286-01p.jpg'),
(3, 'A7', 4, 90, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-48-iz0073-01p.jpg'),
(3, 'Q7 TDI Premium', 4, 70, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/02/aut-15-iz0930-01p.jpg'),
(1,'CLS63 AMG', 4, 80, 2, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/01/aut-05-iz0072-01p.jpg'),
(1,'A6', 4, 80, 1, 'automatic', 1, 2, 'https://www.kimballstock.com/pix/car/p/05/aut-48-iz0063-01p.jpg'),
(6,'CC Tiptronic Sport', 4, 40, 2, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0758-01p.jpg'),
(4,'Camry Hybrid', 4, 40, 1, 'automatic', 1, 2, 'https://www.kimballstock.com/pix/car/p/05/aut-42-iz0002-01p.jpg'),
(5,'Accent GLS 6', 4, 20, 1, 'manual', 1, 1, 'https://www.kimballstock.com/pix/car/p/05/aut-50-iz0389-01p.jpg'),
(1,'C Class S63 AMG', 4, 100, 1, 'manual', 1, 3, 'https://www.kimballstock.com/pix/car/p/06/aut-50-iz1108-01p.jpg'),
(5,'Elantra', 4, 25, 1, 'automatic', 1, 1, 'https://www.kimballstock.com/pix/car/p/05/aut-50-iz0414-01p.jpg'),
(5,'Sonata Hybrid Limited', 4, 60, 1, 'automatic', 1, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0372-01p.jpg'),
(3,'S6', 4, 90, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-50-iz0112-01p.jpg'),
(1,'CLA Class 250', 4, 100, 1, 'manual', 1, 3, 'https://www.kimballstock.com/pix/car/p/06/aut-50-iz0868-01p.jpg'),
(6,'Passat 2.5l SE', 4, 40, 1, 'automatic', 1, 1, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0792-01p.jpg'),
(4,'Tacoma Prerunner', 4, 70, 1, 'automatic', 1, 2, 'https://www.kimballstock.com/pix/car/p/01/aut-14-iz0278-01p.jpg'),
(4,'4-Runner SR5 4x4', 4, 75, 1, 'automatic', 1, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0660-01p.jpg'),
(5,' Santa Fe GLS', 4, 65, 1, 'manual', 1, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0371-01p.jpg'),
(6,'Tiguan 2.0t', 4, 50, 1, 'automatic', 1, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0799-01p.jpg'),
(4,'Venza', 4, 85, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/01/aut-15-iz0718-01p.jpg'),
(6,'Touran Highline', 4, 70, 1, 'automatic', 1, 2, 'https://www.kimballstock.com/pix/car/p/05/aut-45-iz0326-01p.jpg'),
(4,'Tundra', 4, 95, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/01/aut-14-iz0304-01p.jpg'),
(4,'Land Cruiser', 4, 115, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0702-01p.jpg'),
(1,'GLK350', 4, 64, 1, 'manual', 1, 2, 'https://www.kimballstock.com/pix/car/p/01/aut-15-iz0742-01p.jpg'),
(1,'G-Class G550', 4, 130, 1, 'manual', 1, 3, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0470-01p.jpg'),
(1,' ML63', 4, 100, 1, 'manual', 1, 3, 'https://www.kimballstock.com/pix/car/p/01/aut-15-iz0727-01p.jpg'),
(2,' Macan', 4, 130, 1, 'manual', 1, 3, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz2975-01p.jpg'),
(3, 'Q3', 4, 70, 1, 'automatic', 1, 2, 'https://www.kimballstock.com/pix/car/p/02/aut-15-iz0868-01p.jpg'),
(4,'FJ Cruiser', 4, 75, 1, 'automatic', 1, 2, 'https://www.kimballstock.com/pix/car/p/01/aut-15-iz0079-01p.jpg'),
(6,'Multivan', 4, 100, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-45-iz0164-01p.jpg'),
(2, '911 Speedster Convertible', 2, 180, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/07/por-07-rk0137-01p.jpg'),
(6,'Eos ', 4, 40, 1, 'automatic', 1, 1, 'https://www.kimballstock.com/pix/car/p/05/aut-42-rk0125-01p.jpg'),
(1,' SLS', 4, 150, 1, 'manual', 1, 3, 'https://www.kimballstock.com/pix/car/p/01/aut-05-rk0691-01p.jpg'),
(3, 'TT Roadster', 4, 100, 1, 'automatic', 1, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-43-iz0012-01p.jpg'),


-- Kharkiv cars
(1, 'C-Class C300 Sport Sedan', 4, 60, 1, 'automatic', 2, 2,  'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0462-01p.jpg'),
(3, 'TT Roadster', 4, 100, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-43-iz0012-01p.jpg'),
(6,'Eos ', 4, 40, 1, 'automatic', 2, 1, 'https://www.kimballstock.com/pix/car/p/05/aut-42-rk0125-01p.jpg'),
(1,' SLS', 4, 150, 1, 'manual', 2, 3, 'https://www.kimballstock.com/pix/car/p/01/aut-05-rk0691-01p.jpg'),
(3, 'A7', 4, 90, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-48-iz0073-01p.jpg'),
(2, '911 Speedster Convertible', 2, 180, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/07/por-07-rk0137-01p.jpg'),
(6,'Multivan', 4, 100, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-45-iz0164-01p.jpg'),
(3, 'Q3', 4, 70, 1, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/02/aut-15-iz0868-01p.jpg'),
(4,'FJ Cruiser', 4, 75, 1, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/01/aut-15-iz0079-01p.jpg'),
(1,' ML63', 4, 100, 1, 'manual', 2, 3, 'https://www.kimballstock.com/pix/car/p/01/aut-15-iz0727-01p.jpg'),
(1,'GLK350', 4, 64, 1, 'manual', 2, 2, 'https://www.kimballstock.com/pix/car/p/01/aut-15-iz0742-01p.jpg'),
(1,'G-Class G550', 4, 110, 1, 'manual', 2, 3, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0470-01p.jpg'),
(4,'Land Cruiser', 4, 115, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0702-01p.jpg'),
(4,'Venza', 4, 85, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/01/aut-15-iz0718-01p.jpg'),
(6,'Touran Highline', 4, 70, 1, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/05/aut-45-iz0326-01p.jpg'),
(2,' Macan', 4, 130, 1, 'manual', 2, 3, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz2975-01p.jpg'),
(2, '911 Turbo', 2, 150, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/07/por-03-rk0145-01p.jpg'),
(5, 'Accent', 4, 25, 1, 'manual', 2, 1, 'https://www.kimballstock.com/pix/car/p/05/aut-50-iz0385-01p.jpg'),
(4,'Camry Hybrid', 4, 40, 1, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/05/aut-46-iz0242-01p.jpg'),
(3,'S6', 4, 90, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/05/aut-50-iz0112-01p.jpg'),
(6,'CC Tiptronic Sport', 4, 40, 1, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0758-01p.jpg'),
(3, 'A4', 4, 40, 1, 'manual', 2, 2, 'https://www.kimballstock.com/pix/car/p/05/aut-44-iz0055-01p.jpg'),
(4,'Tundra', 4, 95, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/01/aut-14-iz0304-01p.jpg'),
(5,' Santa Fe GLS', 4, 65, 1, 'manual', 2, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0371-01p.jpg'),
(5,'Elantra', 4, 25, 1, 'automatic', 2, 1, 'https://www.kimballstock.com/pix/car/p/05/aut-50-iz0414-01p.jpg'),
(6,'Tiguan 2.0t', 4, 50, 1, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0799-01p.jpg'),
(1,'CLA Class 250', 4, 100, 1, 'manual', 2, 3, 'https://www.kimballstock.com/pix/car/p/06/aut-50-iz0868-01p.jpg'),
(6,'Passat 2.5l SE', 4, 40, 1, 'automatic', 2, 1, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0792-01p.jpg'),
(3, 'Q7 TDI Premium', 4, 70, 1, 'automatic', 2, 3, 'https://www.kimballstock.com/pix/car/p/02/aut-15-iz0930-01p.jpg'),
(4,'Tacoma Prerunner', 4, 70, 1, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/01/aut-14-iz0278-01p.jpg'),
(4,'4-Runner SR5 4x4', 4, 75, 1, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0660-01p.jpg'),
(5,'Sonata Hybrid Limited', 4, 60, 1, 'automatic', 2, 2, 'https://www.kimballstock.com/pix/car/p/06/aut-51-iz0372-01p.jpg');

-- Insert orders
INSERT INTO CarRentalSpring.orders(user_id, car_id, city_id, pickup_date, dropoff_date, total_price, status_id, driver, comments) VALUES
(1, 2, 1, '2022-03-15', '2022-03-17', 240, 3, 0, ""),
(5, 5, 1, '2022-03-10', '2022-03-13', 240, 3, 0, ""),
(6, 7, 1, '2022-03-12', '2022-03-20', 320, 3, 0, ""),
(10, 25, 1, '2022-02-12', '2022-02-17', 600, 6, 1, "Everything is ok"),
(12, 26, 1, '2022-02-01', '2022-02-10', 1670, 6, 0, "Car has been damaged. 500$ penalty fee"),
(15, 12, 1, '2022-02-03', '2022-02-15', 720, 5, 0, "Client canceled booking"),
(17, 34, 1, '2022-02-05', '2022-02-07', 160, 5, 1, "Client didn`t approve booking");