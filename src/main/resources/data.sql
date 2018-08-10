INSERT INTO client (id, first_name, last_name, address, email_address, phone_number, active)
VALUES (1, 'Gigi', 'Masinuta', 'Bucuresti', 'gigi@email.com', '07669504', true);

INSERT INTO reservation (id, status, from_date, to_date, client_id, reservation_date)
VALUES (1, 'NEW', '2017-08-01', '2017-08-20', 1, '2017-07-20');