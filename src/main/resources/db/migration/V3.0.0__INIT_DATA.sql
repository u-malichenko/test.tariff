INSERT INTO demo_tariff (create_date, modified_date, archived, removed, title) VALUES ('2022-06-29 21:00:00.000000', null, false, true, 'super_tariff');
INSERT INTO demo_tariff (create_date, modified_date, archived, removed, title) VALUES ('2022-06-30 21:00:00.000000', null, false, true, 'ultra_tariff');
INSERT INTO demo_tariff (create_date, modified_date, archived, removed, title) VALUES ('2022-06-30 21:00:00.000000', null, false, false, 'extra_tariff');

INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 0, false,'voice_packet_1000', 1000, 1);
INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 1, false,'sms_packet_1000', 1000, 1);
INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 2, false,'internet_packet_1000', 1000, 1);

INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 0, false,'voice_packet_10000', 10000, 2);
INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 1, false,'sms_packet_10000', 10000, 2);
INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 2, false,'internet_packet_10000', 10000, 2);


INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 0, false,'voice_packet_100000', 100000, 3);
INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 1, false,'sms_packet_100000', 100000, 3);
INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 2, false,'internet_packet_100000', 100000, 3);

INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 0, true,'voice_packet_10', 10, 1);
INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 1, true,'sms_packet_10', 10, 2);
INSERT INTO demo_package_of_services (create_date, modified_date, category, removed, title, value, tariff_id) VALUES ('2022-06-20 21:00:00.000000', '2022-06-20 21:00:00.000000', 2, true,'internet_packet_10', 10, 3);