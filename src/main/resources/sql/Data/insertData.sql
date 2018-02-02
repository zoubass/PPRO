-- region Parents
INSERT INTO parents(firstName, lastName, email, bornDate, agreement, tel)
VALUES ('Franta', 'Nicnedělal', 'lenoch@gmail.com', TIMESTAMP '1970-06-06', 1, 777444111);
-- endregion

-- region Tickets
INSERT INTO tickets(entry, endingDate, startingDate, isTimeTicket)
VALUES (0 , DATE '2018-03-01' , TIMESTAMP '2017-10-01', 1);

INSERT INTO tickets(entry, endingDate, startingDate, isTimeTicket)
VALUES (8, NULL , NULL, 0);
-- endregion

-- region Reminders
INSERT INTO reminders(reminderCount, startingDate)
VALUES (3, DATE '2018-01-01');

INSERT INTO reminders(reminderCount, startingDate)
VALUES (1, DATE '2018-01-27');
-- endregion

-- region Users
INSERT INTO users (parent_id, reminder_id, ticket_id, userName, password, firstName, lastName, email, belt, stripes, tel, bornDate, role, enabled)
VALUES (NULL , 1, 1, 'strikac', 'strikac', 'Lukyn', 'Kebouz', 'lukyn@gmail.com', 0, 0, 777888999, DATE '1994-05-27', 1, 1);

INSERT INTO users (parent_id, reminder_id, ticket_id, userName, password, firstName, lastName, email, belt, stripes, tel, bornDate, role, enabled)
VALUES (NULL , NULL , 2, 'Mišánek', 'pass', 'Michal', 'Lapcaz', 'misanek@gmail.com', 0, 3, 111222333, TIMESTAMP '1994-02-01', 0, 0);

INSERT INTO users (parent_id, reminder_id, ticket_id, userName, password, firstName, lastName, email, belt, stripes, tel, bornDate, role, enabled)
VALUES (1 , NULL , NULL , 'Dítě', 'pass', 'Josef', 'Smrádě', 'dite.com', 0, 1, 444555666, TIMESTAMP '2008-12-24', 3, 0);

INSERT INTO users (parent_id, reminder_id, ticket_id, userName, password, firstName, lastName, email, belt, stripes, tel, bornDate, role, enabled)
VALUES (NULL , 2 , NULL , 'Ninja', 'pass', 'John', 'Smith', 'ninja@gmail.com', 3, 1, 222555888, TIMESTAMP '1985-11-20', 2, 0);
-- endregion

-- region Trainings
INSERT INTO trainings(title, beginning, ending, trainingLevel, coach, capacity, note)
VALUES ('Traning 1', TIMESTAMP '2018-02-01 19:30:00', TIMESTAMP '2018-02-01 21:00:00', 2, 'Míra', 20, 'Escapes from side and submissions');

INSERT INTO trainings(title, beginning, ending, trainingLevel, coach, capacity, note)
VALUES ('Traning 2', TIMESTAMP '2018-02-03 19:30:00', TIMESTAMP '2018-02-03 21:00:00', 2, 'Jarda', 20, 'Stand up technique');

INSERT INTO trainings(title, beginning, ending, trainingLevel, coach, capacity, note)
VALUES ('Traning 3', TIMESTAMP '2018-01-31 19:30:00', TIMESTAMP '2018-01-31 21:00:00', 2, 'Jarda', 20, 'Tap tap');

INSERT INTO trainings(title, beginning, ending, trainingLevel, coach, capacity, note)
VALUES ('Traning 4', TIMESTAMP '2018-01-03 19:30:00', TIMESTAMP '2018-01-03 21:00:00', 2, 'Jarda', 20, 'Open mat');
-- endregion

-- region Attendance
INSERT INTO attendance(user_id, training_id)
VALUES (1, 1);

INSERT INTO attendance(user_id, training_id)
VALUES (2, 1);

INSERT INTO attendance(user_id, training_id)
VALUES (2, 2);

-- endregion
INSERT INTO authorities(username, authority) 
values ('strikac', 'ROLE_ADMIN');

--
-- INSERT INTO authorities(fk_authorities_users, username, authority)
-- VALUES (2, 'Mišánek', 2);
--
-- INSERT INTO authorities(fk_authorities_users, username, authority)
-- VALUES (4, 'Ninja', 1);
-- region

INSERT INTO test(test_id, entry)
VALUES (1,3);