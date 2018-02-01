-- region Parents
INSERT INTO parents(firstName, lastName, emial, bornDate, agreement, tel)
VALUES ('Franta', 'Nicnedělal', 'lenoch@gmail.com', DATE '1970-06-06', 1, 777444111);
-- endregion

-- region Tickets
INSERT INTO tickets(entry, endingDate, startingDate, isTimeTicket)
VALUES (0 , DATE '2018-03-01' , DATE '2017-10-01', 1);

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

-- endregion


INSERT INTO authorities(username, authority) values ('strikac', 'ROLE_ADMIN');

-- region Trainings
INSERT INTO trainings(title, beginning, ending, trainingLevel, coach, capacity, note)
VALUES ('Traning 1', TIMESTAMP '2018-02-01 19:30:00', TIMESTAMP '2018-02-01 21:00:00', 2, 'Míra', 20, 'Escapes from side and submissions');

INSERT INTO trainings(title, beginning, ending, trainingLevel, coach, capacity, note)
VALUES ('Traning 2', TIMESTAMP '2018-02-03 19:30:00', TIMESTAMP '2018-02-03 21:00:00', 2, 'Jarda', 20, 'Stand up technique');
-- endregion


-- endregion
-- INSERT INTO authorities(fk_authorities_users, username, authority)
-- VALUES (1, 'Strikac123', 0);
--
-- INSERT INTO authorities(fk_authorities_users, username, authority)
-- VALUES (2, 'Mišánek', 2);
--
-- INSERT INTO authorities(fk_authorities_users, username, authority)
-- VALUES (4, 'Ninja', 1);
-- region