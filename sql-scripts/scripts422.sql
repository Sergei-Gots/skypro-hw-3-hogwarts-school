/* Описание структуры: у каждого человека есть машина.
  Причем несколько человек могут пользоваться одной машиной.
  У каждого человека есть имя, возраст и признак того,
  что у него есть права (или их нет).
  У каждой машины есть марка, модель и стоимость.
  Также не забудьте добавить таблицам первичные ключи и связать их.
 */
DROP TABLE cars;
DROP TABLE people;
CREATE TABLE people
(
    id             BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name           VARCHAR(63)                 NOT NULL,
    age            INT2 CHECK ( age > 0 ),
    car_id         BIGINT REFERENCES cars (id) NOT NULL,
    driver_license BOOLEAN
);

CREATE TABLE cars
(
    id                 BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    manufacturer_brand VARCHAR(15),
    model              VARCHAR(31),
    price              NUMERIC CHECK ( price > 0 )
);

INSERT INTO cars DEFAULT
VALUES;

INSERT INTO people (name, age, car_id)
VALUES ('Sergei', 46, 1);
INSERT INTO people (name, age, car_id)
VALUES ('Sergei-Linux', 46, 1);
UPDATE people
SET driver_license = TRUE
WHERE name like '%x';

INSERT INTO cars (manufacturer_brand, model, price)
VALUES ('Mitsubishi', 'Eclipse cross', 20000);
INSERT INTO people (name, age, car_id)
VALUES ('Name3', 18, 2),
       ('Name4', 18, 2),
       ('Name5', 18, 2),
       ('Name6', 18, 2);

UPDATE people
SET driver_license = TRUE
WHERE name LIKE '%3'
   OR name LIKE '%4';


SELECT name, age, manufacturer_brand, model, price
FROM people
         INNER JOIN cars ON people.car_id = cars.id
WHERE driver_license is TRUE;

ALTER TABLE people
    ALTER COLUMN driver_license SET DEFAULT (false);