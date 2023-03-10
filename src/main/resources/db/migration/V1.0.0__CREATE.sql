CREATE TABLE IF NOT EXISTS demo_tariff (
id int8 generated BY DEFAULT AS identity,
create_date timestamp NOT NULL,
modified_date timestamp,
archived boolean DEFAULT FALSE NOT NULL,
removed boolean DEFAULT FALSE NOT NULL,
title varchar(128) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS demo_package_of_services (
id int8 generated BY DEFAULT AS identity,
create_date timestamp NOT NULL,
modified_date timestamp,
category int4 NOT NULL,
removed boolean DEFAULT FALSE NOT NULL,
title varchar(128) NOT NULL,
value int8 NOT NULL,
tariff_id int8 NOT NULL,
FOREIGN KEY (tariff_id) REFERENCES demo_tariff,
PRIMARY KEY (id));
